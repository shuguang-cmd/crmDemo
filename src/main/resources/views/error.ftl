<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>错误页面</title>
    <#include "common.ftl">
</head>
<body class="childrenBody">
<div style="text-align: center; padding: 50px;">
    <i class="layui-icon layui-icon-face-cry" style="font-size: 100px; color: #FF5722;"></i>
    <h1 style="margin-top: 20px;">出错了！</h1>
    <p style="margin-top: 10px; font-size: 18px;">错误代码：${code! 500}</p>
    <p style="margin-top: 10px; color: #666;">错误信息：${msg! "未知错误"}</p>
    <hr>
    <a href="javascript:history.back()" class="layui-btn layui-btn-primary">返回上一页</a>
</div>
</body>
</html>
