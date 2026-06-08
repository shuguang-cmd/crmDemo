package com.crm.controller;

import com.crm.base.BaseController;
import com.crm.base.ResultInfo;
import com.crm.model.UserModel;
import com.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class IndexController extends BaseController {

    @Resource
    private UserService userService;
    // 这个方法的作用是当你访问 /index 时，返回 index.ftl 页面
    @RequestMapping("index")
    public String index() {

        return "index"; // 对应 resources/views/index.ftl
    }

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {
        UserModel userModel = userService.userLogin(userName, userPwd);
        // 使用基类提供的 success 方法
        return success("登录成功", userModel);
    }

    // 关键点：添加这个方法来处理 /main 请求
    @RequestMapping("main")
    public String main() {
        return "main"; // 这会去 resources/views/ 下寻找 main.ftl
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome"; // 这会去 resources/views/ 下寻找 welcome.ftl
    }
}