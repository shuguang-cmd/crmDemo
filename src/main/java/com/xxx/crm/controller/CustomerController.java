package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.crm.query.CustomerQuery;
import com.xxx.crm.service.CustomerService;
import com.xxx.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    /**
     * 进入客户管理页面
     */
    @RequestMapping("index")
    public String index() {
        return "customer/customer";
    }

    /**
     * 分页多条件查询客户
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CustomerQuery customerQuery) {
        return customerService.queryByParamsForTable(customerQuery);
    }

    /**
     * 添加客户
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Customer customer) {
        customerService.addCustomer(customer);
        return success("客户添加成功");
    }

    /**
     * 修改客户
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Customer customer) {
        customerService.updateCustomer(customer);
        return success("客户修改成功");
    }

    /**
     * 批量删除客户
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        customerService.deleteBatch(ids);
        return success("客户删除成功");
    }

    /**
     * 打开添加/修改客户对话框
     */
    @RequestMapping("addOrUpdateCustomerPage")
    public String addOrUpdateCustomerPage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("customer", customerService.selectByPrimaryKey(id));
        }
        return "customer/add_update";
    }
}
