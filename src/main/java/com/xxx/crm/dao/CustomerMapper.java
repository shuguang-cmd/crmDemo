package com.xxx.crm.dao;

import com.xxx.base.BaseMapper;
import com.xxx.crm.vo.Customer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, Integer> {
    List<Customer> queryLossCustomers();
}
