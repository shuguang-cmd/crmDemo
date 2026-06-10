package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MiscController extends BaseController {

    @RequestMapping("data_dic/index")
    public String dataDic(Model model) {
        model.addAttribute("moduleName", "字典管理");
        return "placeholder";
    }

    @RequestMapping("module/index")
    public String module(Model model) {
        model.addAttribute("moduleName", "菜单管理");
        return "placeholder";
    }
}
