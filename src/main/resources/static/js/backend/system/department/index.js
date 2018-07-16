/**
 * 部门管理初始化
 */
var Department = {
    id: "departmentTable",	//表格id
    seItem: null,		//选中的条目
    seItems: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Department.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', align: 'center', valign: 'middle',width:'50px'},
        {title: '部门编号', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '部门名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '上级部门编号', field: 'parentCode', align: 'center', valign: 'middle', sortable: true},
        {title: '上级部门名称', field: 'parentName', align: 'center', valign: 'middle', sortable: true},
        {title: '介绍', field: 'introduction', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle', sortable: true}];
};

/**
 * 检查是否选中
 */
Department.checkUpdate = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 1) {
    	Department.seItem = selected[0];
        return true;
    } else{
    	Feng.info("请选中表格中的某一条记录进行修改！");
        return false;
    }
    
};

/**
 * 检查是否选中
 */
Department.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length  !=1 ) {
        Feng.info("请选中表格中的一条记录进行删除！");
        return false;
    } else {
    	Department.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加部门
 */
Department.openAddDepartment = function () {
    var index = layer.open({
        type: 2,
        title: '添加部门',
        area: ['800px', '435px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/backend/system/department/add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看部门详情
 */
Department.openChangeDepartment = function () {
    if (this.checkUpdate()) {
        var index = layer.open({
            type: 2,
            title: '部门详情',
            area: ['800px', '435px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/backend/system/department/edit/' + Department.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除部门
 */
Department.delDepartment = function () {
    if (this.checkDelete()) {
    	var ids =Department.seItem.id;
/*    	var names ="";
    	for(i=0;i<Department.seItems.length;i++){
    		if(i==(Department.seItems.length-1)){
    			if(Department.seItems[i].parentCode){
    				ids+=Department.seItems[i].id;
        			names+=Department.seItems[i].name;
    			}else{
    				 Feng.info("选中的菜单有子节点，不能进行删除，只能先删除子节点!");
    				return false;
    			}
    		
    		}else{
    			ids+=Department.seItems[i].id+",";
    			names+=Department.seItems[i].name+",";
    		}
    	}*/
    	var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/department/delete",
                dataType: "json",
                data:{'ids':ids},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("删除成功!");
                		  Department.table.refresh();
                	}else{
                		Feng.error(data.msg);
                	}
                }
            });
        };
        Feng.confirm("是否刪除该部门 ?", operation);
    }
};

/**
 * 查询部门列表
 */
Department.search = function () {
    var queryData = {};
    queryData['departmentName'] = $("#departmentName").val();
    Department.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Department.initColumn();
    var table = new BSTreeTable(Department.id, "/backend/system/department/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentCode");
    table.setExpandAll(true);
    table.init();
    Department.table = table;
});
