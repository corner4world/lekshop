package com.chengyu.core.service.walk;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.WalkMember;

/**
 * @title  创作号
 * @author LeGreen
 * @date   2022/5/19
 */
public interface WalkMemberService {

    /**
     * 添加创作号
     * @author LeGreen
     * @date   2022/5/19
     * @param  member
     */
    void addWalkMember(UmsMember member);

    /**
     * 更新创作号
     * @author LeGreen
     * @date   2022/5/19
     * @param  walkMember
     */
    void updateWalkMember(WalkMember walkMember);

    /**
     * 获取用户的创作号
     * @author LeGreen
     * @date   2022/5/19
     * @param  memberId
     * @return WalkMember
     */
    WalkMember getWalkMemberByMember(Integer memberId);

    /**
     * 获取创作人信息
     * @author LeGreen
     * @date   2022/5/20
     * @param  walkMemberId
     * @return WalkMember
     */
    WalkMember getWalkMemberById(Integer walkMemberId);
}
