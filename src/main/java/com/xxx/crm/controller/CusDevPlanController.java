package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.crm.query.CusDevPlanQuery;
import com.xxx.crm.service.CusDevPlanService;
import com.xxx.crm.service.SaleChanceService;
import com.xxx.crm.vo.CusDevPlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {

    @Resource
    private CusDevPlanService cusDevPlanService;
    
    @Resource
    private SaleChanceService saleChanceService;

    /**
     * 进入客户开发计划页面
     */
    @RequestMapping("index")
    public String index() {
        return "cusDevPlan/cus_dev_plan";
    }

    /**
     * 进入详情页面（展示具体的计划项）
     */
    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model) {
        // sid 是营销机会 ID
        model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    /**
     * 查询计划项列表
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CusDevPlanQuery cusDevPlanQuery) {
        return cusDevPlanService.queryByParamsForTable(cusDevPlanQuery);
    }

    /**
     * 添加计划项
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(CusDevPlan cusDevPlan) {
        cusDevPlanService.addCusDevPlan(cusDevPlan);
        return success("计划项添加成功");
    }

    /**
     * 更新计划项
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(CusDevPlan cusDevPlan) {
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项更新成功");
    }

    /**
     * 删除计划项
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        cusDevPlanService.deleteByPrimaryKey(id);
        return success("计划项删除成功");
    }

    /**
     * 打开添加或修改计划项的窗口
     */
    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid, Integer id, Model model) {
        model.addAttribute("sid", sid);
        if (null != id) {
            model.addAttribute("cusDevPlan", cusDevPlanService.selectByPrimaryKey(id));
        }
        return "cusDevPlan/add_update";
    }
}
