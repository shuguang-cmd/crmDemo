<!DOCTYPE html>
<html>
<head>
    <title>客户流失管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="cusNo" class="layui-input searchVal" placeholder="客户编号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="cusName" class="layui-input searchVal" placeholder="客户名" />
                </div>
                <div class="layui-input-inline">
                    <select name="state" id="state">
                        <option value="" >流失状态</option>
                        <option value="0">暂缓流失</option>
                        <option value="1">确认流失</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>

    <table id="customerLossList" class="layui-table"  lay-filter="customerLosses"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                更新流失状态
            </a>
        </div>
    </script>


    <!--操作-->
    <script type="text/html" id="customerLossListBar">
        <#--  根据流失状态判断显示的按钮  -->
        {{# if(d.state == 0){ }}
            <a class="layui-btn layui-btn-xs" id="edit" lay-event="add">添加暂缓</a>
        {{# } else { }}
            <a class="layui-btn layui-btn-xs layui-btn-normal" id="info" lay-event="info">查看详情</a>
        {{# } }}
    </script>
</form>

<script type="text/javascript" src="${ctx}/js/customerLoss/customer.loss.js"></script>
</body>
</html>
