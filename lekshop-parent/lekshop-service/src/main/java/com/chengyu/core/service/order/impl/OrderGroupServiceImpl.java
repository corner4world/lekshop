package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.OrderGroupSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomOrderGroupMapper;
import com.chengyu.core.mapper.OmsOrderGroupMapper;
import com.chengyu.core.mapper.OmsOrderGroupMemberMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.member.MemberNewsService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.order.OrderGroupService;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.pay.PayService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderGroupServiceImpl implements OrderGroupService {

	@Autowired
	private OmsOrderGroupMapper assembleMapper;
	@Autowired
	private OmsOrderGroupMemberMapper assembleMemberMapper;
	@Autowired
	private CustomOrderGroupMapper customGroupMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberNewsService newsService;
	@Autowired
	private PayService payService;
	@Autowired
	private OrderService orderService;
	
	@Override
	public List<OmsOrderGroup> getOrderGroupList(OrderGroupSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		OmsOrderGroupExample example = new OmsOrderGroupExample();
		example.setOrderByClause("end_time asc");
		OmsOrderGroupExample.Criteria criteria = example.createCriteria();
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(CollectionUtil.isNotEmpty(form.getShopIdArr())){
			criteria.andShopIdIn(form.getShopIdArr());
		}
		if(form.getGoodsId() != null){
			criteria.andGoodsIdEqualTo(form.getGoodsId());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(StringUtils.isNotBlank(form.getShopName())){
			criteria.andShopNameLike("%"+form.getShopName()+"%");
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return assembleMapper.selectByExample(example);
	}

	@Override
	public List<OmsOrderGroupMember> getGroupMemberList(Integer orderGroupId, Integer limit) {
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.setOrderByClause("add_time desc limit "+limit);
		example.createCriteria().andOrderGroupIdEqualTo(orderGroupId);
		return assembleMemberMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void launchGroup(OmsOrderDetail orderDetail) throws ServiceException {
		//判断是否已发起拼团
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.createCriteria().andOrderNoEqualTo(orderDetail.getOrderNo()).andMemberIdEqualTo(orderDetail.getMemberId());
		long count = assembleMemberMapper.countByExample(example);
		if(count > 0){
			throw new ServiceException("一笔订单只能发起一笔拼团");
		}

		OmsOrderGroup orderGroup = new OmsOrderGroup();
		orderGroup.setMemberId(orderDetail.getMemberId());
		orderGroup.setMemberName(orderDetail.getMemberName());
		orderGroup.setShopId(orderDetail.getShopId());
		orderGroup.setShopName(orderDetail.getShopName());
		orderGroup.setGoodsId(orderDetail.getGoodsId());
		orderGroup.setGoodsName(orderDetail.getGoodsName());
		orderGroup.setGoodsMainImg(orderDetail.getGoodsMainImg());
		orderGroup.setOriginalPrice(orderDetail.getBuyOriPrice());
		orderGroup.setGroupPrice(orderDetail.getBuyPrice());
		orderGroup.setGroupNum(orderDetail.getGroupNum());
		orderGroup.setHaveGroupNum(1);
		orderGroup.setStatus(CommonConstant.YES_INT);
		orderGroup.setAddTime(new Date());
		orderGroup.setEndTime(cn.hutool.core.date.DateUtil.offsetHour(orderGroup.getAddTime(), orderDetail.getGroupLimitHours()));
		orderGroup.setUpdTime(orderGroup.getAddTime());
		assembleMapper.insertSelective(orderGroup);
		Integer orderGroupId = orderGroup.getId();
		
		OmsOrderGroupMember assembleMember = new OmsOrderGroupMember();
		assembleMember.setOrderGroupId(orderGroupId);
		assembleMember.setOrderNo(orderDetail.getOrderNo());
		assembleMember.setMemberId(orderDetail.getMemberId());
		assembleMember.setMemberName(orderDetail.getMemberName());
		assembleMember.setMemberHeadImg(orderDetail.getMemberHeadImg());
		assembleMember.setPayStatus(CommonConstant.SUS_INT);
		assembleMember.setBuyNum(orderDetail.getBuyNum());
		assembleMember.setOriginalPrice(orderDetail.getBuyOriPrice());
		assembleMember.setPayPrice(orderDetail.getBuyPrice().multiply(new BigDecimal(orderDetail.getBuyNum())));
		assembleMember.setAddTime(cn.hutool.core.date.DateUtil.date());
		assembleMember.setUpdTime(assembleMember.getAddTime());
		assembleMemberMapper.insertSelective(assembleMember);
		
		//发团成功后添加拼团到期定时器
//		timerJobService.addTimerJob(orderGroup.getPintuanNo(), TimerJobService.TIMER_TYPE_PINTUAN_EXPIRED, orderGroup.getEndTime());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void joinGroup(Integer orderGroupId, OmsOrderDetail orderDetail) throws ServiceException {
		OmsOrderGroup orderGroup = this.getOrderGroupById(orderGroupId);
		if(orderGroup == null){
			throw new ServiceException("拼团任务不存在");
		}
		
		if(orderGroup.getStatus() == 2) {
			throw new ServiceException("该拼团任务已拼购成功,无法参团");
		}
		if(orderGroup.getStatus() == 3){
			throw new ServiceException("该拼团任务已拼购失败,无法参团");
		}
		if(orderGroup.getHaveGroupNum() >= orderGroup.getGroupNum()){
			throw new ServiceException("该拼购任务已满员,无法参团");
		}
		if(orderGroup.getEndTime().before(new Date())) {
			throw new ServiceException("该拼购任务已超时,无法参团");
		}
		
		//判断用户不能再参加自己的团
		if(orderGroup.getMemberId().equals(orderDetail.getMemberId())) {
			throw new ServiceException("不能参加自己的团");
		}
		
		OmsOrderGroup updaGroup = new OmsOrderGroup();
		updaGroup.setId(orderGroup.getId());
		updaGroup.setHaveGroupNum(orderGroup.getHaveGroupNum()+1);
		updaGroup.setUpdTime(new Date());
		if(updaGroup.getHaveGroupNum() >= orderGroup.getGroupNum()){
			updaGroup.setStatus(2);
		}
		assembleMapper.updateByPrimaryKeySelective(updaGroup);
		
		OmsOrderGroupMember assembleMember = new OmsOrderGroupMember();
		assembleMember.setOrderGroupId(orderGroupId);
		assembleMember.setOrderNo(orderDetail.getOrderNo());
		assembleMember.setMemberId(orderDetail.getMemberId());
		assembleMember.setMemberName(orderDetail.getMemberName());
		assembleMember.setMemberHeadImg(orderDetail.getMemberHeadImg());
		assembleMember.setPayStatus(1);
		assembleMember.setAddTime(cn.hutool.core.date.DateUtil.date());
		assembleMember.setUpdTime(assembleMember.getAddTime());
		assembleMemberMapper.insertSelective(assembleMember);

		if(orderGroup.getStatus() == 2){
			//拼团成功通知所有拼团人员
			List<OmsOrderGroupMember> assembleMemberList = this.getGroupMemberList(orderGroupId, 999);
			for(OmsOrderGroupMember oam : assembleMemberList){
				UmsMember m = memberService.getMemberById(oam.getMemberId());
//				newsService.addMemberNews(m, "拼团成功通知", "您的拼团已成功,等待卖家发货", orderGroup.getGoodsMainImg());
			}
		}else{
			UmsMember member = memberService.getMemberById(orderDetail.getMemberId());
//			newsService.addMemberNews(member, "拼团通知", "您已参团成功,若您的拼团在"+ DateUtil.dateTimeFormat(orderGroup.getEndTime())+"时间内未拼团成功,则拼团失败", orderGroup.getMainFile());
		}
	}

	@Override
	public List<OmsOrderGroup> getOrderGroupPageByMemberId(Integer memberId, Integer status, Integer page,
			Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		return customGroupMapper.getOrderGroupList(memberId, status);
	}

	@Override
	public OmsOrderGroup getOrderGroupById(Integer orderGroupId) {
		return assembleMapper.selectByPrimaryKey(orderGroupId);
	}

	@Override
	public List<OmsOrderGroupMember> getGroupMemberByOrderNo(String orderNo) {
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OmsOrderGroupMember> list = assembleMemberMapper.selectByExample(example);
		if(list == null) {
			return null;
		}
		
		example = new OmsOrderGroupMemberExample();
		example.createCriteria().andOrderGroupIdEqualTo(list.get(0).getOrderGroupId());
		return assembleMemberMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void doRefundForFailPintuan(OmsOrderGroup assemble) {
		OmsOrderGroup updateGroup = new OmsOrderGroup();
		updateGroup.setId(assemble.getId());
		updateGroup.setStatus(3);
		updateGroup.setUpdTime(new Date());
		assembleMapper.updateByPrimaryKeySelective(updateGroup);
		
		List<OmsOrderGroupMember> list = this.getGroupMemberList(assemble.getId(), 9999);
		for(OmsOrderGroupMember assembleMember : list){
			OmsOrderGroupMember updateGroupMember = new OmsOrderGroupMember();
			updateGroupMember.setId(assembleMember.getId());
			updateGroupMember.setPayStatus(3);
			updateGroupMember.setUpdTime(new Date());
			assembleMemberMapper.updateByPrimaryKeySelective(updateGroupMember);
			
//			OmsOrder order = orderService.getOrderByOrderNo(assembleMember.getOrderNo());
//			payService.cancelAndRefundOrder(order.getId());
		}
		
	}

	@Override
	public long countGroupNum(Integer goodsId) {
		return customGroupMapper.countGroupNum(goodsId);
	}

	@Override
	public boolean isInGroup(Integer goodsId) {
		OmsOrderGroupExample example = new OmsOrderGroupExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo(1);
		long count = assembleMapper.countByExample(example);
		return count > 0;
	}

	
}