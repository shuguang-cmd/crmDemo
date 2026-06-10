layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    // 监听表单提交
    form.on("submit(addOrUpdateCustomer)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        
        var url = ctx + "/customer/save";
        if ($("input[name='id']").val()) {
            url = ctx + "/customer/update";
        }

        $.post(url, data.field, function (res) {
            if (res.code == 200) {
                top.layer.close(index);
                top.layer.msg("操作成功！");
                layer.closeAll("iframe");
                // 刷新父页面
                parent.location.reload();
            } else {
                layer.msg(res.msg, {icon: 5});
            }
        });
        return false;
    });

    // 取消按钮
    $("#closeDlg").click(function () {
        layer.closeAll("iframe");
        return false;
    });
});
