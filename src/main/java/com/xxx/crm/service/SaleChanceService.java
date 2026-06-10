package com.xxx.crm.service;

import com.xxx.base.BaseMapper;
import com.xxx.base.BaseService;
import com.xxx.crm.dao.SaleChanceMapper;
import com.xxx.crm.utils.AssertUtil;
import com.xxx.crm.utils.StringUtil;
import com.xxx.crm.vo.SaleChance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {

    @Resource
    private SaleChanceMapper saleChanceMapper;

    @Override
    public BaseMapper<SaleChance, Integer> getMapper() {
        return saleChanceMapper;
    }

    /**
     * 添加营销机会
     */
    public void addSaleChance(SaleChance saleChance) {
        // 1. 参数校验
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        // 2. 设置相关默认值
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        // 判断是否设置了分配人
        if (StringUtil.isBlank(saleChance.getAssignMan())) {
            saleChance.setState(0); // 未分配
            saleChance.setDevResult(0); // 未开发
        } else {
            saleChance.setState(1); // 已分配
            saleChance.setDevResult(1); // 开发中
            saleChance.setAssignTime(new Date());
        }
        // 3. 执行添加
        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance) < 1, "营销机会添加失败");
    }

    /**
     * 更新营销机会
     */
    public void updateSaleChance(SaleChance saleChance) {
        // 1. 参数校验
        AssertUtil.isTrue(null == saleChance.getId(), "待更新记录不存在");
        SaleChance temp = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在");
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        
        // 2. 设置相关值
        saleChance.setUpdateDate(new Date());
        // 分配人处理
        if (StringUtil.isBlank(temp.getAssignMan()) && StringUtil.isNotBlank(saleChance.getAssignMan())) {
            // 原本未分配，现在分配了
            saleChance.setState(1);
            saleChance.setDevResult(1);
            saleChance.setAssignTime(new Date());
        } else if (StringUtil.isNotBlank(temp.getAssignMan()) && StringUtil.isBlank(saleChance.getAssignMan())) {
            // 原本已分配，现在清空了
            saleChance.setState(0);
            saleChance.setDevResult(0);
            saleChance.setAssignTime(null);
        }
        
        // 3. 执行更新
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance) < 1, "营销机会更新失败");
    }

    private void checkParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtil.isBlank(customerName), "客户名称不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(linkMan), "联系人不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(linkPhone), "联系电话不能为空");
    }

    /**
     * 更新营销机会的开发状态
     */
    public void updateSaleChanceDevResult(Integer id, Integer devResult) {
        AssertUtil.isTrue(null == id, "待更新记录不存在");
        SaleChance temp = saleChanceMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == temp, "待更新记录不存在");
        
        temp.setDevResult(devResult);
        temp.setUpdateDate(new Date());
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(temp) < 1, "营销机会状态更新失败");
    }
}
