package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.crm.model.UserModel;
import com.xxx.crm.query.UserQuery;
import com.xxx.crm.service.UserService;
import com.xxx.crm.vo.User;
import com.xxx.crm.utils.CookieUtil;
import com.xxx.crm.utils.UserIDBase64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    // ==================== 【数据接口区域 (返回 JSON)】 ====================

    /**
     * 修改密码 (数据处理接口)
     */
    @PostMapping("updatePassword") // 规范：使用 PostMapping
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        // 1. 获取 Cookie 中的用户 ID
        String userIdStr = CookieUtil.getCookieValue(request, "userIdStr");
        // 2. 解析 ID
        Integer userId = UserIDBase64.decoderUserID(userIdStr);
        // 3. 调用 Service 层修改密码
        userService.updateUserPassword(userId, oldPassword, newPassword, confirmPassword);
        // 4. 返回结果
        return success("密码修改成功");
    }

    /**
     * 用户登录
     */
    @PostMapping("login") // 规范：登录建议也用 PostMapping
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {
        UserModel userModel = userService.userLogin(userName, userPwd);
        return success("用户登录成功", userModel);
    }

    /**
     * 用户分页列表查询
     */
    @RequestMapping("list")
    @ResponseBody
    public java.util.Map<String, Object> list(UserQuery userQuery) {
        return userService.queryByParamsForTable(userQuery);
    }

    /**
     * 添加用户
     */
    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        userService.addUser(user);
        return success("用户添加成功");
    }

    /**
     * 更新用户
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        userService.updateUser(user);
        return success("用户更新成功");
    }

    /**
     * 删除用户
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        userService.deleteByIds(ids);
        return success("用户删除成功");
    }

    // ==================== 【页面跳转区域 (返回 String 视图名)】 ====================

    /**
     * 进入修改密码页面
     */
    @RequestMapping("toPasswordPage") // 解决了冲突的门牌号
    public String toPasswordPage() {
        return "user/password"; // 修复了返回值，精确指向 views/user/password.ftl
    }

    /**
     * 进入用户管理页面
     */
    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    /**
     * 进入个人设置页面
     */
    @RequestMapping("toSettingPage")
    public String toSettingPage() {
        return "welcome";
    }

    /**
     * 打开添加或修改用户的对话框
     */
    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdateUserPage(Integer id, org.springframework.ui.Model model) {
        if (null != id) {
            model.addAttribute("user", userService.selectByPrimaryKey(id));
        }
        return "user/add_update";
    }
}