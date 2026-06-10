package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    /**
     * 进入角色管理页面
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }
}
