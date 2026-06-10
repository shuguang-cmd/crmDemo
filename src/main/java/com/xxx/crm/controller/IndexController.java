package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    // 这个方法的作用是当你访问 /index 时，返回 index.ftl 页面
    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request, Model model) {
        // 核心：把 /crm 这个路径传给前端页面！
        model.addAttribute("ctx", request.getContextPath());
        return "index";
    }

    // 关键点：添加这个方法来处理 /main 请求
    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model) {
        model.addAttribute("ctx", request.getContextPath());
        return "main";
    }

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        model.addAttribute("ctx", request.getContextPath());
        return "welcome";
    }
}