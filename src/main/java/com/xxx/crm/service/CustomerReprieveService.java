package com.xxx.crm.service;

import com.xxx.base.BaseMapper;
import com.xxx.base.BaseService;
import com.xxx.crm.dao.CustomerReprieveMapper;
import com.xxx.crm.utils.AssertUtil;
import com.xxx.crm.vo.CustomerReprieve;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve, Integer> {

    @Resource
    private CustomerReprieveMapper customerReprieveMapper;

    @Override
    public BaseMapper<CustomerReprieve, Integer> getMapper() {
        return customerReprieveMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerReprieve(CustomerReprieve customerReprieve) {
        // 1. 参数校验
        AssertUtil.isTrue(null == customerReprieve.getLossId(), "流失ID不能为空");
        AssertUtil.isTrue(null == customerReprieve.getMeasure() || customerReprieve.getMeasure().isEmpty(), "措施内容不能为空");
        
        // 2. 设置默认值
        customerReprieve.setIsValid(1);
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());
        
        // 3. 执行添加
        AssertUtil.isTrue(customerReprieveMapper.insertSelective(customerReprieve) < 1, "添加措施失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerReprieve(CustomerReprieve customerReprieve) {
        // 1. 参数校验
        AssertUtil.isTrue(null == customerReprieve.getId() || null == customerReprieveMapper.selectByPrimaryKey(customerReprieve.getId()), "记录不存在");
        AssertUtil.isTrue(null == customerReprieve.getMeasure() || customerReprieve.getMeasure().isEmpty(), "措施内容不能为空");
        
        // 2. 设置更新时间
        customerReprieve.setUpdateDate(new Date());
        
        // 3. 执行更新
        AssertUtil.isTrue(customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve) < 1, "更新措施失败");
    }
}
