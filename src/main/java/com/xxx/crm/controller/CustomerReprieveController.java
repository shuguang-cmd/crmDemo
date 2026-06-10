package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.crm.query.CustomerReprieveQuery;
import com.xxx.crm.service.CustomerReprieveService;
import com.xxx.crm.vo.CustomerReprieve;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_reprieve")
public class CustomerReprieveController extends BaseController {

    @Resource
    private CustomerReprieveService customerReprieveService;

    /**
     * 分页查询暂缓措施
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CustomerReprieveQuery query) {
        return customerReprieveService.queryByParamsForTable(query);
    }

    /**
     * 添加暂缓措施
     */
    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(CustomerReprieve reprieve) {
        customerReprieveService.addCustomerReprieve(reprieve);
        return success("添加成功");
    }

    /**
     * 更新暂缓措施
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(CustomerReprieve reprieve) {
        customerReprieveService.updateCustomerReprieve(reprieve);
        return success("更新成功");
    }

    /**
     * 删除暂缓措施
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        customerReprieveService.deleteByPrimaryKey(id);
        return success("删除成功");
    }
}
