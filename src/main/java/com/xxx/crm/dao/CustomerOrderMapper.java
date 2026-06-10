package com.xxx.crm.dao;

import com.xxx.base.BaseMapper;
import com.xxx.crm.vo.CustomerOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerOrderMapper extends BaseMapper<CustomerOrder, Integer> {
    CustomerOrder queryLastOrderTimeByCusId(Integer cusId);
}
