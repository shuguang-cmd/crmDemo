package com.xxx.crm.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.crm.query.SaleChanceQuery;
import com.xxx.crm.service.SaleChanceService;
import com.xxx.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    /**
     * 进入营销机会管理页面
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "saleChance/sale_chance";
    }

    /**
     * 分页多条件查询营销机会
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(SaleChanceQuery saleChanceQuery) {
        return saleChanceService.queryByParamsForTable(saleChanceQuery);
    }

    /**
     * 添加营销机会
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(SaleChance saleChance, HttpServletRequest request) {
        // 设置创建人 (从 Cookie 中获取，这里简化处理，实际开发中建议通过拦截器存入 Request)
        // String createMan = CookieUtil.getCookieValue(request, "userName");
        // saleChance.setCreateMan(createMan);
        saleChanceService.addSaleChance(saleChance);
        return success("营销机会添加成功");
    }

    /**
     * 更新营销机会
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(SaleChance saleChance) {
        saleChanceService.updateSaleChance(saleChance);
        return success("营销机会更新成功");
    }

    /**
     * 批量删除营销机会
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        saleChanceService.deleteBatch(ids);
        return success("营销机会删除成功");
    }

    /**
     * 进入添加/修改营销机会页面
     */
    @RequestMapping("addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(id));
        }
        return "saleChance/add_update";
    }

    /**
     * 更新营销机会的开发状态
     */
    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(Integer id, Integer devResult) {
        saleChanceService.updateSaleChanceDevResult(id, devResult);
        return success("营销机会状态更新成功");
    }
}
