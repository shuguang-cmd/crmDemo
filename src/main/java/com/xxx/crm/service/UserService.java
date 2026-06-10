package com.xxx.crm.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.xxx.base.BaseMapper;
import com.xxx.base.BaseService;
import com.xxx.crm.dao.UserMapper;
import com.xxx.crm.model.UserModel;
import com.xxx.crm.utils.AssertUtil;
import com.xxx.crm.utils.Md5Util;
import com.xxx.crm.utils.StringUtil;
import com.xxx.crm.utils.UserIDBase64;
import com.xxx.crm.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    private UserMapper userMapper;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(int userId, String oldPassword, String newPassword, String confirmPassword){
        // 1. 获取用户信息（仅查询一次）
        User user = userMapper.selectByPrimaryKey(userId);
        // 2. 参数校验
        checkParams(user, oldPassword, newPassword, confirmPassword);
        // 3. 设置新密码（MD5 加密）
        user.setUserPwd(Md5Util.encode(newPassword));
        // 4. 执行更新
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "用户密码更新失败");
    }

    public void checkParams(User user, String oldPassword, String newPassword, String confirmPassword){
        AssertUtil.isTrue(null == user, "用户不存在");
        AssertUtil.isTrue(StringUtil.isBlank(oldPassword), "原始密码不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(newPassword), "新密码不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(confirmPassword), "确认密码不能为空");

        // 原始密码验证
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))), "原始密码不正确");
        // 新密码与确认密码一致性验证
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "新密码与确认密码不一致");
        // 新旧密码重复验证（修复后的正确逻辑）
        AssertUtil.isTrue(oldPassword.equals(newPassword), "新密码不能与旧密码相同");
    }


    @Override
    public BaseMapper<User, Integer> getMapper() {
        return userMapper;
    }

    private void checkUserPwd(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtil.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(userPwd), "密码不能为空");
        // 将用户输入的密码进行 MD5 加密后与数据库中的密文比对
        String encryptedPwd = Md5Util.encode(userPwd);
    }

    public UserModel userLogin(String userName, String userPwd) {
        checkUserPwd(userName, userPwd);
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null == user, "用户不存在");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"用户密码不正确");
        return buildUserModel(user);
    }




    /**
     * 添加用户
     */
    public void addUser(User user) {
        checkParams(user.getUserName(), user.getEmail(), user.getPhone());
        User temp = userMapper.queryUserByName(user.getUserName());
        AssertUtil.isTrue(null != temp, "用户名已存在");
        user.setIsValid(1);
        user.setCreateDate(new java.util.Date());
        user.setUpdateDate(new java.util.Date());
        // 默认密码 123456 (MD5 加密存储)
        user.setUserPwd(Md5Util.encode("123456"));
        AssertUtil.isTrue(userMapper.insertSelective(user) < 1, "用户添加失败");
    }

    /**
     * 更新用户
     */
    public void updateUser(User user) {
        AssertUtil.isTrue(null == user.getId() || null == userMapper.selectByPrimaryKey(user.getId()), "待更新记录不存在");
        checkParams(user.getUserName(), user.getEmail(), user.getPhone());
        User temp = userMapper.queryUserByName(user.getUserName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已存在");
        user.setUpdateDate(new java.util.Date());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "用户更新失败");
    }

    /**
     * 批量删除
     */
    public void deleteByIds(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除记录");
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length, "用户删除失败");
    }

      private void checkParams(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtil.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(email), "邮箱不能为空");
        AssertUtil.isTrue(StringUtil.isBlank(phone), "手机号不能为空");
      }
    }