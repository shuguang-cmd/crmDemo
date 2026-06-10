package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {

    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type, Model model) {
        String[] titles = {"", "服务创建", "服务分配", "服务处理", "服务反馈", "服务归档"};
        model.addAttribute("moduleName", titles[type]);
        return "placeholder";
    }
}
