package com.chengyu.core.controller.goods;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.GoodsPublishSeckillForm;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.goods.GoodsSeckillService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Api(tags = "秒杀")
@Controller
@RequestMapping("/member")
public class GoodsSeckillController extends ShopBaseController {
	
	@Autowired
	private GoodsSeckillService goodsSeckillService;

	@OperationLog
	@ApiOperation(value = "添加编辑秒杀活动")
	@ResponseBody
	@RequestMapping(value="/seckill/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(GoodsPublishSeckillForm publishForm) throws ServiceException {
		goodsSeckillService.addSeckillGoods(getCurrentShop(), publishForm);
		return CommonResult.success(null);
	}

	private List<Integer> getIdList(String ids){
		List<String> idStrList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		return Convert.convert(new TypeReference<List<Integer>>() {}, idStrList);
	}

	@OperationLog
	@ApiOperation(value = "删除秒杀活动")
	@ResponseBody
	@RequestMapping(value="/seckill/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsSeckillService.deleteSeckill(goodsIdList);
		return CommonResult.success(null);
	}
}