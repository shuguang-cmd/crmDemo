layui.use(['table','layer', 'jquery'], function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    // 渲染表格
    var tableIns = table.render({
        elem: '#customerList',
        url : ctx + '/customer/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'khno', title: '客户编号',align:"center"},
            {field: 'name', title: '客户名称',  align:'center'},
            {field: 'fr', title: '法人', align:'center'},
            {field: 'cusManager', title: '客户经理', align:'center'},
            {field: 'area', title: '地区', align:'center'},
            {field: 'level', title: '客户等级', align:'center'},
            {field: 'phone', title: '联系电话', align:'center'},
            {field: 'address', title: '详细地址', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {title: '操作', templet:'#customerListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    // 搜索
    $(".search_btn").click(function(){
        tableIns.reload({
            where: {
                name: $("input[name='name']").val(),
                khno: $("input[name='khno']").val(),
                level: $("#level").val()
            },
            page: {
                curr: 1
            }
        });
    });

    // 头工具栏事件
    table.on('toolbar(customers)', function(obj){
        switch(obj.event){
            case "add":
                openAddOrUpdateCustomerDialog();
                break;
            case "del":
                var checkStatus = table.checkStatus(obj.config.id);
                deleteCustomer(checkStatus.data);
                break;
        }
    });

    // 行工具栏事件
    table.on('tool(customers)', function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateCustomerDialog(obj.data.id);
        } else if(layEvent === "del"){
            layer.confirm('确定删除当前数据？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(ctx + "/customer/delete", {ids: obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg);
                    }
                });
            });
        }
    });

    // 打开添加/修改窗口
    function openAddOrUpdateCustomerDialog(id){
        var title = "客户管理 - 添加客户";
        var url = ctx + "/customer/addOrUpdateCustomerPage";
        if(id){
            title = "客户管理 - 修改客户";
            url += "?id=" + id;
        }
        layui.layer.open({
            title : title,
            type : 2,
            area : ["700px","500px"],
            content : url,
            maxmin : true
        });
    }

    // 批量删除
    function deleteCustomer(data){
        if(data.length == 0){
            layer.msg("请选择待删除记录！");
            return;
        }
        layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
            var ids = "ids=";
            for(var i=0; i<data.length; i++){
                if(i < data.length - 1){
                    ids = ids + data[i].id + "&ids=";
                } else {
                    ids = ids + data[i].id;
                }
            }
            $.post(ctx + "/customer/delete", ids, function (data) {
                if (data.code == 200) {
                    layer.msg("操作成功！");
                    tableIns.reload();
                } else {
                    layer.msg(data.msg);
                }
            });
        });
    }
});
