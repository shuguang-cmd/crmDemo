package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.crm.query.CustomerLossQuery;
import com.xxx.crm.service.CustomerLossService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {

    @Resource
    private CustomerLossService customerLossService;

    /**
     * 进入客户流失管理页面
     */
    @RequestMapping("index")
    public String index() {
        return "customerLoss/customer_loss";
    }

    /**
     * 分页多条件查询流失客户
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CustomerLossQuery query) {
        return customerLossService.queryByParamsForTable(query);
    }

    /**
     * 更新客户流失状态 (自动化检测)
     */
    @PostMapping("updateCustomerLossState")
    @ResponseBody
    public ResultInfo updateCustomerLossState() {
        customerLossService.updateCustomerLossState();
        return success("客户流失状态更新成功");
    }

    /**
     * 进入暂缓措施页面
     */
    @RequestMapping("toReprievePage")
    public String toReprievePage(Integer id, Model model) {
        // 根据ID查询流失记录
        model.addAttribute("customerLoss", customerLossService.selectByPrimaryKey(id));
        return "customerLoss/reprieve";
    }

    /**
     * 确认流失
     */
    @PostMapping("confirmLoss")
    @ResponseBody
    public ResultInfo confirmLoss(Integer id, String lossReason) {
        customerLossService.confirmLoss(id, lossReason);
        return success("确认流失成功");
    }
}
