<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>客户管理 - 添加/修改客户</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:90%; padding-top: 20px;">
    <input name="id" type="hidden" value="${(customer.id)!}"/>
    
    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="name"
                       lay-verify="required" value="${(customer.name)!}" placeholder="请输入客户名称">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">法人</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="fr"
                       lay-verify="required" value="${(customer.fr)!}" placeholder="请输入法人">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户经理</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="cusManager"
                       value="${(customer.cusManager)!}" placeholder="请输入客户经理">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户等级</label>
            <div class="layui-input-block">
                <select name="level" id="level">
                    <option value="">请选择客户等级</option>
                    <option value="战略合作伙伴" ${(customer.level == '战略合作伙伴')?string('selected', '')}>战略合作伙伴</option>
                    <option value="大客户" ${(customer.level == '大客户')?string('selected', '')}>大客户</option>
                    <option value="普通客户" ${(customer.level == '普通客户')?string('selected', '')}>普通客户</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="phone"
                       lay-verify="required|phone" value="${(customer.phone)!}" placeholder="请输入联系电话">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">地区</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="area"
                       value="${(customer.area)!}" placeholder="请输入所在地区">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs12">
            <label class="layui-form-label">详细地址</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="address"
                       value="${(customer.address)!}" placeholder="请输入详细地址">
            </div>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCustomer">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-primary" id="closeDlg">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/customer/add.update.js"></script>
</body>
</html>
