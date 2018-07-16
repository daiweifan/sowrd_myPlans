/**
 * 角色管理的单例
 */
var {{= bean.name}} = {
    id: "{{= bean.lowerName}}Table",	//表格id
    seItem: null,		//选中的条目
    seItems: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
{{= bean.name}}.initColumn = function () {
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
{{= bean.name}}.check = function () {
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
{{= bean.name}}.checkUpdate = function () {
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
{{= bean.name}}.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length  <1 ) {
        Feng.info("请至少选中表格中的一条记录！");
        return false;
    } else {
        Menu.seItems = selected;
        return true;
    }
};

/**
 * 点击添加菜单
 */
{{= bean.name}}.openAdd = function () {
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
{{= bean.name}}.openEdit= function () {
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
{{= bean.name}}.openDel = function () {
    if (this.checkDelete()) {
    	var ids ="";
    	var names ="";
    	for(i=0;i<{{= bean.name}}.seItems.length;i++){
    		if(i==({{= bean.name}}.seItems.length-1)){
    			if({{= bean.name}}.seItems[i].parentCode){
    				ids+=Menu.seItems[i].id;
        			names+=Menu.seItems[i].name;
    			}else{
    				 Feng.info("选中的菜单有子节点，不能进行删除，只能先删除子节点!");
    				return false;
    			}
    		
    		}else{
    			ids+={{= bean.name}}.seItems[i].id+",";
    			names+={{= bean.name}}.seItems[i].name+",";
    		}
    	}
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
                	}
                }
            });
        };

        Feng.confirm("是否刪除以下菜单<br> "+names+"?", operation);
    }
};

/**
 * 搜索
 */
{{= bean.name}}.search = function () {
    var queryData = {};

    queryData['{{= bean.lowerName}}Name'] = $("#{{= bean.lowerName}}Name").val();

    {{= bean.name}}.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = {{= bean.name}}.initColumn();
    var table = new BSTable({{= bean.name}}.id, "/backend/system/user/list", defaultColunms);
    table.setPaginationType("client");
    {{= bean.name}}.table = table.init();
});
