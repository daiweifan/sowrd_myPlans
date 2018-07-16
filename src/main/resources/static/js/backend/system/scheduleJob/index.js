/**
 * 系统管理--用户管理的单例对象
 */
var MgrScheduleJob = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    seItems:null,       //选中的条目 多个
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MgrScheduleJob.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'bean名称', field: 'beanName', align: 'center', valign: 'middle', sortable: true},
        {title: '方法名称', field: 'methodName', align: 'center', valign: 'middle', sortable: true},
        {title: '参数', field: 'params', align: 'center', valign: 'middle', sortable: true},
        {title: 'cron表达式', field: 'cronExpression', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'note', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'state', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};

/**
 * 检查是否选中
 */
MgrScheduleJob.checkUpdate = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 1) {
    	MgrScheduleJob.seItem = selected[0];
        return true;
    } else{
    	Feng.info("请选中表格中的某一条记录进行修改！");
        return false;
    }
    
};

/**
 * 检查是否选中
 */
MgrScheduleJob.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length  <1 ) {
        Feng.info("请至少选中表格中的一条记录！");
        return false;
    } else {
        MgrScheduleJob.seItems = selected;
        return true;
    }
};

/**
 * 点击添加管理员
 */
MgrScheduleJob.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加定时任务',
        area: ['800px', '460px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/backend/system/scheduleJob/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param 
 */
MgrScheduleJob.openChangescheduleJob = function () {
    if (this.checkUpdate()) {
        var index = layer.open({
            type: 2,
            title: '编辑定时任务',
            area: ['800px', '456px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/backend/system/scheduleJob/edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
MgrScheduleJob.delMgrScheduleJob = function () {
    if (this.checkDelete()) {
    	var ids ="";
    	var names ="";
    	for(i=0;i<MgrScheduleJob.seItems.length;i++){
    		if(i==(MgrScheduleJob.seItems.length-1)){
    			ids+=MgrScheduleJob.seItems[i].id;
    			names+=MgrScheduleJob.seItems[i].name;
    		}else{
    			ids+=MgrScheduleJob.seItems[i].id+",";
    			names+=MgrScheduleJob.seItems[i].name+",";
    		}
    	}
        var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/scheduleJob/delete",
                dataType: "json",
                data:{'ids':ids},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("删除成功!");
                		  MgrScheduleJob.table.refresh();
                	}
                }
            });
        };

        Feng.confirm("是否删除选中的定时任务[" + names + "]?",operation);
    }
};




MgrScheduleJob.resetSearch = function () {
    $("#name").val("");

    MgrScheduleJob.search();
}

MgrScheduleJob.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();

    MgrScheduleJob.table.refresh({query: queryData});
}


$(function () {
    var defaultColunms = MgrScheduleJob.initColumn();
    var table = new BSTable("managerTable", "/backend/system/scheduleJob/list", defaultColunms);
    table.setPage(2,[1,2,3,4]);
   // table.setPaginationType("client");
    console.info(table);
    MgrScheduleJob.table = table.init();
});
