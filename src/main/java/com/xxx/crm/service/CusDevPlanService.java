package com.xxx.crm.service;

import com.xxx.base.BaseMapper;
import com.xxx.base.BaseService;
import com.xxx.crm.dao.CusDevPlanMapper;
import com.xxx.crm.utils.AssertUtil;
import com.xxx.crm.utils.StringUtil;
import com.xxx.crm.vo.CusDevPlan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    @Resource
    private CusDevPlanMapper cusDevPlanMapper;

    @Override
    public BaseMapper<CusDevPlan, Integer> getMapper() {
        return cusDevPlanMapper;
    }

    /**
     * 添加计划项
     */
    public void addCusDevPlan(CusDevPlan cusDevPlan) {
        // 1. 参数校验
        checkParams(cusDevPlan.getSaleChanceId(), cusDevPlan.getPlanItem(), cusDevPlan.getPlanDate());
        // 2. 设置默认值
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        // 3. 执行添加
        AssertUtil.isTrue(cusDevPlanMapper.insertSelective(cusDevPlan) < 1, "计划项添加失败");
    }

    /**
     * 更新计划项
     */
    public void updateCusDevPlan(CusDevPlan cusDevPlan) {
        // 1. 参数校验
        AssertUtil.isTrue(null == cusDevPlan.getId() || null == cusDevPlanMapper.selectByPrimaryKey(cusDevPlan.getId()), "待更新记录不存在");
        checkParams(cusDevPlan.getSaleChanceId(), cusDevPlan.getPlanItem(), cusDevPlan.getPlanDate());
        // 2. 设置更新时间
        cusDevPlan.setUpdateDate(new Date());
        // 3. 执行更新
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan) < 1, "计划项更新失败");
    }

    private void checkParams(Integer saleChanceId, String planItem, Date planDate) {
        AssertUtil.isTrue(null == saleChanceId, "营销机会ID不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(planItem), "计划内容不能为空");
        AssertUtil.isTrue(null == planDate, "计划时间不能为空");
    }
}
