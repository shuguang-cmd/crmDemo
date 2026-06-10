package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {

    @RequestMapping("{type}")
    public String index(@PathVariable Integer type, Model model) {
        String[] titles = {"客户贡献分析", "客户构成分析", "客户服务分析", "客户流失分析"};
        if (type >= 0 && type < titles.length) {
            model.addAttribute("moduleName", titles[type]);
        }
        return "placeholder";
    }
}
