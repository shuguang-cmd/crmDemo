package com.xxx.crm.dao;

import com.xxx.base.BaseMapper;
import com.xxx.crm.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User, Integer> {
    User queryUserByName(String userName);
}
