package com.crm.service;

import com.crm.base.BaseMapper;
import com.crm.base.BaseService;
import com.crm.dao.UserMapper;
import com.crm.model.UserModel;
import com.crm.utils.AssertUtil;
import com.crm.utils.StringUtils;
import com.crm.utils.UserIDBase64;
import com.crm.vo.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User, Integer> {
    /**
     * 将 User 实体对象转换为 UserModel
     * @param user
     * @return
     */
    private UserModel buildUserModel(User user) {
        UserModel userModel = new UserModel();
        // 设置加密后的 ID (使用你刚才创建的工具类)
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }


    /**
     * 校验密码
     * @param dbPwd 数据库中的密码
     * @param userPwd 用户输入的密码
     */
    private void checkUserPwd(String dbPwd, String userPwd) {
        // 使用 StringUtils 判断是否为空
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空");

        // 比较密码 (如果你的数据库存的是明文，直接 equals；如果存的是加密后的，这里需要调用加密工具类)
        // 这里假设是明文对比，如果后续有加密需求，在此处替换为 MD5 加密对比
        AssertUtil.isTrue(!dbPwd.equals(userPwd), "密码错误");
    }
    @Resource
    private UserMapper userMapper;

    // 【关键改动】：实现抽象方法，把具体的 Mapper 传给 BaseService
    @Override
    public BaseMapper<User, Integer> getMapper() {
        return (BaseMapper<User, Integer>) userMapper;
    }

    // 登录逻辑依然在这里，因为它属于业务逻辑，不属于基础 CRUD
    public UserModel userLogin(String userName, String userPwd) {
        // 这里的逻辑保持不变
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null == user, "用户不存在");
        checkUserPwd(user.getUserPwd(), userPwd);
        return buildUserModel(user);
    }
}