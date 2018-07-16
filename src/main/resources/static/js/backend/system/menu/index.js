/**
 * 角色管理的单例
 */
var Menu = {
    id: "menuTable",	//表格id
    seItem: null,		//选中的条目
    seItems: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'50px'},
        {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true,width:'12%'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true,width:'17%'},
        {title: '上级菜单编号', field: 'parentCode', align: 'center', valign: 'middle', sortable: true},
        {title: '上级菜单名称', field: 'parentName', align: 'center', valign: 'middle', sortable: true},
        {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true,width:'30%'},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle', sortable: true,width:'5%'},
        {title: '是否是菜单', field: 'isMenuName', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};
/**
 * 检查是否选中
 */
Menu.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Menu.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中
 */
Menu.checkUpdate = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 1) {
    	Menu.seItem = selected[0];
        return true;
    } else{
    	Feng.info("请选中表格中的某一条记录进行修改！");
        return false;
    }
    
};

/**
 * 检查是否选中
 */
Menu.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length  != 1 ) {
        Feng.info("请选中表格中的一条记录进行删除！");
        return false;
    } else {
        Menu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Menu.openAddMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['830px', '470px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/backend/system/menu/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Menu.openChangeMenu = function () {
    if (this.checkUpdate()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/backend/system/menu/edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Menu.delMenu = function () {
    if (this.checkDelete()) {
    	var ids =Menu.seItem.id;
//   	var names ="";
//    	for(i=0;i<Menu.seItems.length;i++){
//    		if(i==(Menu.seItems.length-1)){
//    			console.info(Menu.seItems[i]);
//    			alert(Menu.seItems[i].name);
//    			alert(Menu.seItems[i].isMenuName);
//    			if(Menu.seItems[i].isMenuName=='是'){
//    				ids+=Menu.seItems[i].id;
//        			names+=Menu.seItems[i].name;
//    			}else{
//    				 Feng.info("选中的菜单有子节点，不能进行删除，只能先删除子节点!");
//    				return false;
//    			}
//    		
//    		}else{
//    			ids+=Menu.seItems[i].id+",";
//    			names+=Menu.seItems[i].name+",";
//    		}
//    	}
    	var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/menu/delete",
                dataType: "json",
                data:{'ids':ids},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("删除成功!");
                		  Menu.table.refresh();
                	}else{
                		Feng.error(data.msg);
                	}
                }
            });
        };

        Feng.confirm("是否刪除选中菜单?", operation);
    }
};

/**
 * 搜索
 */
Menu.search = function () {
    var queryData = {};

    queryData['menuName'] = $("#menuName").val();
    queryData['level'] = $("#level").val();

    Menu.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Menu.initColumn();
    var table = new BSTreeTable(Menu.id, "/backend/system/menu/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentCode");
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
});
