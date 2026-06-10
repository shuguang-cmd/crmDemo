layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    // 核心修复：直接从浏览器 URL 探测 contextPath (ctx)
    // 这种方式比后端传值更可靠，能解决不跳转的问题
    var pathName = window.location.pathname;
    var ctx = pathName.substring(0, pathName.indexOf('/', 1));
    if (ctx == "/user" || ctx == "/index") ctx = ""; // 根路径处理
    
    console.log("Detected Context Path: " + ctx);

    // layui 用户登录 表单提交
    form.on('submit(login)', function (data) {
        var formData = data.field;

        if(!formData.username){
            layer.msg("用户名不能为空!");
            return false;
        }
        if(!formData.password){
            layer.msg("密码不能为空!");
            return false;
        }

        $.ajax({
            type:"post",
            url: ctx + "/user/login",
            data:{
                userName: formData.username,
                userPwd: formData.password
            },
            dataType:"json",
            success:function (res) {
                if(res.code == 200){
                    layer.msg("登录成功，正在跳转...", {icon: 1, time: 1000}, function () {
                        var result = res.result;
                        // 强制使用根路径，确保全站可见
                        var cookiePath = "/"; 
                        
                        $.cookie("userIdStr", result.userIdStr, {path: cookiePath});
                        $.cookie("userName", result.userName, {path: cookiePath});
                        $.cookie("trueName", result.trueName, {path: cookiePath});
                        
                        if($("input[type='checkbox']").is(":checked")){
                            $.cookie("userIdStr", result.userIdStr, {expires: 7, path: cookiePath});
                            $.cookie("userName", result.userName, {expires: 7, path: cookiePath});
                            $.cookie("trueName", result.trueName, {expires: 7, path: cookiePath});
                        }
                        
                        // 最终跳转
                        window.location.replace(ctx + "/main");
                    });
                } else {
                    layer.msg(res.msg, {icon: 5});
                }
            }
        });
        return false;
    });
});
