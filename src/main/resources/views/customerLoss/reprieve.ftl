<!DOCTYPE html>
<html>
<head>
	<title>暂缓措施管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-col-md12">
	<div class="layui-card">
		<div class="layui-card-body">
			<form class="layui-form" >
				<input name="id" type="hidden" value="${customerLoss.id}"/>
				<div class="layui-form-item layui-row">
					<div class="layui-col-xs6">
						<label class="layui-form-label">客户名称</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input"
								   name="cusName" id="cusName" value="${customerLoss.cusName}" readonly="readonly">
						</div>
					</div>
					<div class="layui-col-xs6">
						<label class="layui-form-label">客户编号</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input"
								   name="cusNo" id="cusNo" value="${customerLoss.cusNo}" readonly="readonly">
						</div>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>

<div class="layui-col-md12">
	<table id="customerReprieveList" class="layui-table"  lay-filter="customerReprieves"></table>
</div>

<#-- 当流失状态为暂缓流失时，显示头部工具栏 -->
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<#if customerLoss.state == 0>
			<a class="layui-btn layui-btn-normal" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加措施
			</a>
			<a class="layui-btn layui-btn-danger" lay-event="confirm">
				<i class="layui-icon">&#xe608;</i>
				确认流失
			</a>
		</#if>
	</div>
</script>

<!--操作-->
<script type="text/html" id="customerReprieveListBar">
	<#if customerLoss.state == 0>
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	<#else>
		<span class="layui-badge layui-bg-gray">不可操作</span>
	</#if>
</script>


<script type="text/javascript" src="${ctx}/js/customerLoss/reprieve.js"></script>
</body>
</html>
