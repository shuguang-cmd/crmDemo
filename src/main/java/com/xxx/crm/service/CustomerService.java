package com.xxx.crm.service;

import com.xxx.base.BaseMapper;
import com.xxx.base.BaseService;
import com.xxx.crm.dao.CustomerMapper;
import com.xxx.crm.utils.AssertUtil;
import com.xxx.crm.utils.StringUtil;
import com.xxx.crm.vo.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CustomerService extends BaseService<Customer, Integer> {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public BaseMapper<Customer, Integer> getMapper() {
        return customerMapper;
    }

    /**
     * 添加客户
     */
    public void addCustomer(Customer customer) {
        // 1. 参数校验
        checkParams(customer.getName(), customer.getFr(), customer.getPhone());
        // 2. 设置默认值
        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        // 生成唯一的客户编号 (KH + 时间戳)
        String khno = "KH" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        customer.setKhno(khno);
        
        // 3. 执行添加
        AssertUtil.isTrue(customerMapper.insertSelective(customer) < 1, "客户添加失败");
    }

    /**
     * 更新客户
     */
    public void updateCustomer(Customer customer) {
        // 1. 参数校验
        AssertUtil.isTrue(null == customer.getId(), "待更新记录不存在");
        Customer temp = customerMapper.selectByPrimaryKey(customer.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在");
        checkParams(customer.getName(), customer.getFr(), customer.getPhone());
        
        // 2. 设置更新时间
        customer.setUpdateDate(new Date());
        
        // 3. 执行更新
        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer) < 1, "客户更新失败");
    }

    private void checkParams(String name, String fr, String phone) {
        AssertUtil.isTrue(StringUtil.isBlank(name), "客户名称不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(fr), "法人不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(phone), "手机号不能为空");
    }
}
