/**
 * 系统管理--用户管理的单例对象
 */
var LoginLog = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    seItems:null,       //选中的条目 多个
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LoginLog.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '操作人', field: 'username', align: 'center', valign: 'middle', sortable: true},
        {title: '日志名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '登录时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '参数', field: 'params', align: 'center', valign: 'middle', sortable: true},
        {title: '反应时间', field: 'resTime', align: 'center', valign: 'middle', sortable: true},
        {title: '是否成功', field: 'succeed', align: 'center', valign: 'middle', sortable: true},
        {title: '返回信息', field: 'message', align: 'center', valign: 'middle', sortable: true},
        {title: 'ip地址', field: 'ip', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};

/**
 * 检查是否选中
 */
LoginLog.checkUpdate = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 1) {
    	LoginLog.seItem = selected[0];
        return true;
    } else{
    	Feng.info("请选中表格中的某一条记录进行修改！");
        return false;
    }
    
};

/**
 * 检查是否选中
 */
LoginLog.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length  <1 ) {
        Feng.info("请至少选中表格中的一条记录！");
        return false;
    } else {
        LoginLog.seItems = selected;
        return true;
    }
};


/**
 * 点击详情按钮时
 * @param 
 */
LoginLog.show = function () {
    if (this.checkUpdate()) {
        var index = layer.open({
            type: 2,
            title: '日志详情',
            area: ['800px', '456px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/backend/system/loginLog/show/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
LoginLog.del = function () {
    if (this.checkDelete()) {
    	var ids ="";
    	var names ="";
    	for(i=0;i<LoginLog.seItems.length;i++){
    		if(i==(LoginLog.seItems.length-1)){
    			ids+=LoginLog.seItems[i].id;
    			names+=LoginLog.seItems[i].name;
    		}else{
    			ids+=LoginLog.seItems[i].id+",";
    			names+=LoginLog.seItems[i].name+",";
    		}
    	}
        var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/loginLog/delete",
                dataType: "json",
                data:{'ids':ids},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("删除成功!");
                		  LoginLog.table.refresh();
                	}
                }
            });
        };

        Feng.confirm("是否删除选中的日志[" + names + "]?",operation);
    }
};




LoginLog.resetSearch = function () {
    $("#name").val("");

    LoginLog.search();
}

LoginLog.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();

    LoginLog.table.refresh({query: queryData});
}


$(function () {
    var defaultColunms = LoginLog.initColumn();
    var table = new BSTable("managerTable", "/backend/system/loginLog/list", defaultColunms);
   // table.setPaginationType("client");
    LoginLog.table = table.init();
});
