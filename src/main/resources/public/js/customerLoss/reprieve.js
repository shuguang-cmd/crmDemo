layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    // 暂缓措施列表展示
    var  tableIns = table.render({
        elem: '#customerReprieveList',
        url : ctx + '/customer_reprieve/list?lossId=' + $("input[name='id']").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerReprieveListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'measure', title: '暂缓措施',align:"center",edit: 'text'},
            {field: 'createDate', title: '创建时间',align:"center"},
            {field: 'updateDate', title: '更新时间',align:"center"},
            {title: '操作', fixed:"right",align:"center", minWidth:150,templet:"#customerReprieveListBar"}
        ]]
    });

    // 监听单元格编辑
    table.on('edit(customerReprieves)', function(obj){
        var value = obj.value //得到修改后的值
            ,data = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        
        $.post(ctx + "/customer_reprieve/update", {id:data.id, measure:value}, function (res) {
            if (res.code == 200) {
                layer.msg("更新成功！");
            } else {
                layer.msg(res.msg, {icon: 5});
            }
        });
    });

    // 监听工具栏
    table.on('toolbar(customerReprieves)', function(obj){
        switch(obj.event){
            case "add":
                layer.prompt({title: '请输入暂缓措施', formType: 2}, function(text, index){
                    layer.close(index);
                    $.post(ctx + "/customer_reprieve/save", {lossId:$("input[name='id']").val(), measure:text}, function (res) {
                        if (res.code == 200) {
                            layer.msg("添加成功！");
                            tableIns.reload();
                        } else {
                            layer.msg(res.msg, {icon: 5});
                        }
                    });
                });
                break;
            case "confirm":
                layer.prompt({title: '请输入流失原因', formType: 2}, function(text, index){
                    layer.close(index);
                    $.post(ctx + "/customer_loss/confirmLoss", {id:$("input[name='id']").val(), lossReason:text}, function (res) {
                        if (res.code == 200) {
                            layer.msg("确认流失成功！");
                            // 关闭当前弹出层并刷新父页面
                            parent.layer.closeAll();
                            parent.location.reload();
                        } else {
                            layer.msg(res.msg, {icon: 5});
                        }
                    });
                });
                break;
        };
    });

    // 监听行工具栏
    table.on('tool(customerReprieves)', function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit"){
             // 逻辑已在单元格编辑中处理，或者这里弹出编辑框
             layer.msg("请直接在表格中编辑措施内容");
        } else if(layEvent === "del"){
            layer.confirm('确定删除该措施吗？', {icon: 3, title: '系统提示'}, function (index) {
                $.post(ctx + "/customer_reprieve/delete", {id:obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("删除成功！");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            });
        }
    });

});
