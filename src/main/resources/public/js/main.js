layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();


    $(".login-out").click(function () {
        layer.confirm('确定退出系统吗?', {icon: 3, title:'提示'}, function(index){
            $.removeCookie("userIdStr", {path:ctx});
            $.removeCookie("userName", {path:ctx});
            $.removeCookie("trueName", {path:ctx});
            window.parent.location.href = ctx + "/index";
        });
    })





});

