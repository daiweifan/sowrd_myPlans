/**
 * 系统管理--用户管理的单例对象
 */
var MgrCompany = {
    id: "companyTable",//表格id
    seItem: null,		//选中的条目
    seItems:null,       //选中的条目 多个
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MgrCompany.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle' },
        {title: '企业名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '企业logo', field: 'logo', align: 'center', valign: 'middle', sortable: true,formatter : function (value, row, index) {
        	return "<img style='width: 50px;height: 50px;' src='"+ctx+value+"' alt=''>"
        }},
        {title: '简介', field: 'description', align: 'center', valign: 'middle', sortable: true   ,editable:true},
        {title: '网站', field: 'website', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'state', align: 'center', valign: 'middle', sortable: true,  
        formatter : function (value, row, index) {
            if (row['state'] === 11) {
                return '<span style="color:green;font-weight:bold;">正常</span>';
            }
            if (row['status'] === 99) {
                return '<span style="color:red;font-weight:bold;">禁用</span>';
            }
            return '<span style="color:orange;font-weight:bold;">'+value+'</span>';
        }
        }];
    return columns;
};

/**
 * 检查是否选中
 */
MgrCompany.checkUpdate = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 1) {
    	MgrCompany.seItem = selected[0];
        return true;
    } else{
    	Feng.info("请选中表格中的某一条记录进行修改！");
        return false;
    }
    
};

/**
 * 检查是否选中
 */
MgrCompany.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length  <1 ) {
        Feng.info("请至少选中表格中的一条记录！");
        return false;
    } else {
        MgrCompany.seItems = selected;
        return true;
    }
};

/**
 * 点击添加管理员
 */
MgrCompany.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加企业',
        area: ['800px', '460px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/backend/system/company/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param 
 */
MgrCompany.openChangeCompany = function () {
    if (this.checkUpdate()) {
        var index = layer.open({
            type: 2,
            title: '编辑企业',
            area: ['800px', '456px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/backend/system/company/edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
MgrCompany.delMgrCompany = function () {
    if (this.checkDelete()) {
    	var ids ="";
    	var names ="";
    	for(i=0;i<MgrCompany.seItems.length;i++){
    		if(i==(MgrCompany.seItems.length-1)){
    			ids+=MgrCompany.seItems[i].id;
    			names+=MgrCompany.seItems[i].name;
    		}else{
    			ids+=MgrCompany.seItems[i].id+",";
    			names+=MgrCompany.seItems[i].name+",";
    		}
    	}
    	 var operation = function(){
             $.ajax({
                 type: "post",
                 url:  Feng.ctxPath + "/backend/system/company/delete",
                 dataType: "json",
                 data:{'ids':ids},
                 async: true,
                 success: function(data){
                 	if(data.code==1){
                 		  Feng.success("删除成功!");
                 		  MgrCompany.table.refresh();
                 	}
                 }
             });
         };

         Feng.confirm("是否删除选中的企业[" + names + "]?",operation);
    }
};


/**
 * 冻结用户
 */
MgrCompany.freezeAccount = function () {
    if (this.checkDelete()) {
    	var ids ="";
    	var names ="";
    	for(i=0;i<MgrCompany.seItems.length;i++){
    		if(i==(MgrCompany.seItems.length-1)){
    			ids+=MgrCompany.seItems[i].id;
    			names+=MgrCompany.seItems[i].name;
    		}else{
    			ids+=MgrCompany.seItems[i].id+",";
    			names+=MgrCompany.seItems[i].name+",";
    		}
    	}
    	
        var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/company/changeState",
                dataType: "json",
                data:{'ids':ids,'actived':true},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("冻结成功!");
                		  MgrCompany.table.refresh();
                	}
                }
            });
        };
        Feng.confirm("是否把选中的企业[" + names + "]冻结?",operation);
    }
};

/**
 * 冻结用户
 */
MgrCompany.unfreeze = function () {
    if (this.checkDelete()) {
    	var ids ="";
    	var names ="";
    	for(i=0;i<MgrCompany.seItems.length;i++){
    		if(i==(MgrCompany.seItems.length-1)){
    			ids+=MgrCompany.seItems[i].id;
    			names+=MgrCompany.seItems[i].name;
    		}else{
    			ids+=MgrCompany.seItems[i].id+",";
    			names+=MgrCompany.seItems[i].name+",";
    		}
    	}
    	
        var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/company/changeState",
                dataType: "json",
                data:{'ids':ids,'actived':false},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("解除解冻!");
                		  MgrCompany.table.refresh();
                	}
                }
            });
        };
        Feng.confirm("是否把选中的企业[" + names + "]解除冻结?",operation);
    }
};

MgrCompany.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    MgrCompany.search();
}

MgrCompany.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    MgrCompany.table.refresh({query: queryData});
}


$(function () {
    var defaultColunms = MgrCompany.initColumn();
    var table = new BSTable("companyTable", "/backend/system/company/list", defaultColunms);
    table.setPage(5,[2,5,10,15]);
   // table.setPaginationType("client");
    MgrCompany.table = table.init();
});
