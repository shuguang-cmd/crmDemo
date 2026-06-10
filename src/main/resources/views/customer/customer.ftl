<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>客户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="name" class="layui-input searchVal" placeholder="客户名称" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="khno" class="layui-input searchVal" placeholder="客户编号" />
                </div>
                <div class="layui-input-inline">
                    <select name="level" id="level">
                        <option value="">客户等级</option>
                        <option value="战略合作伙伴">战略合作伙伴</option>
                        <option value="大客户">大客户</option>
                        <option value="普通客户">普通客户</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>

    <table id="customerList" lay-filter="customers"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i> 添加客户
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe640;</i> 批量删除
            </a>
        </div>
    </script>

    <script type="text/html" id="customerListBar">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/customer/customer.js"></script>
</body>
</html>
