layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    // 流失客户列表展示
    var  tableIns = table.render({
        elem: '#customerLossList',
        url : ctx + '/customer_loss/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerLossListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'cusNo', title: '客户编号',align:"center"},
            {field: 'cusName', title: '客户名称',align:"center"},
            {field: 'cusManager', title: '客户经理',align:"center"},
            {field: 'lastOrderTime', title: '最后下单时间',align:"center"},
            {field: 'confirmLossTime', title: '确认流失时间',align:"center"},
            {field: 'state', title: '流失状态',align:"center",templet:function (d) {
                    if(d.state==0){
                        return "<div style='color: yellow'>暂缓流失</div>";
                    }else if(d.state==1){
                        return "<div style='color: red'>确认流失</div>";
                    }
                }},
            {field: 'lossReason', title: '流失原因',align:"center"},
            {title: '操作', fixed:"right",align:"center", minWidth:150,templet:"#customerLossListBar"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("customerLossListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                cusNo:$("input[name='cusNo']").val(),
                cusName:$("input[name='cusName']").val(),
                state:$("#state").val()
            }
        })
    });

    // 监听工具栏
    table.on('toolbar(customerLosses)', function(obj){
        switch(obj.event){
            case "add":
                updateCustomerLossState();
                break;
        };
    });

    // 监听行工具栏
    table.on('tool(customerLosses)', function(obj){
        var layEvent = obj.event;
        if(layEvent === "add"){
            openCustomerReprievePage("暂缓措施管理",obj.data.id);
        } else if(layEvent === "info"){
            openCustomerReprievePage("流失详情查看",obj.data.id);
        }
    });

    // 更新流失状态 (触发后端检测逻辑)
    function updateCustomerLossState() {
        layer.confirm('确定更新客户流失状态吗？', {icon: 3, title: '系统提示'}, function (index) {
            $.post(ctx + "/customer_loss/updateCustomerLossState", {}, function (data) {
                if (data.code == 200) {
                    layer.msg("更新成功！");
                    tableIns.reload();
                } else {
                    layer.msg(data.msg, {icon: 5});
                }
            });
        });
    }

    // 打开暂缓措施页面
    function openCustomerReprievePage(title,lossId) {
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:ctx + "/customer_loss/toReprievePage?id="+lossId
        });
    }

});
