/**
 * 角色管理的单例
 */
var Dict = {
    id: "dictTable",	//表格id
    seItem: null,		//选中的条目
    seItems: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dict.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'50px'},
        {title: '字典名称', field: 'parentName', align: 'center', valign: 'middle', sortable: true},
        {title: '字典编号', field: 'parentCode', align: 'center', valign: 'middle', sortable: true},
        {title: '字典值', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '字典码', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Dict.checkUpdate = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 1) {
    	Dict.seItem = selected[0];
        return true;
    } else{
    	Feng.info("请选中表格中的某一条记录进行修改！");
        return false;
    }
    
};

/**
 * 检查是否选中
 */
Dict.checkDelete = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length  != 1 ) {
        Feng.info("请选中表格中的一条记录进行删除！");
        return false;
    } else {
        dict.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加字典
 */
Dict.openAdd = function () {
    var index = layer.open({
        type: 2,
        title: '添加字典',
        area: ['830px', '470px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/backend/system/dict/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Dict.openEdit = function () {
    if (this.checkUpdate()) {
        var index = layer.open({
            type: 2,
            title: '修改字典',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/backend/system/dict/edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Dict.del= function () {
    if (this.checkDelete()) {
    	var ids =dict.seItem.id;
//   	var names ="";
//    	for(i=0;i<dict.seItems.length;i++){
//    		if(i==(dict.seItems.length-1)){
//    			console.info(dict.seItems[i]);
//    			alert(dict.seItems[i].name);
//    			alert(dict.seItems[i].isdictName);
//    			if(dict.seItems[i].isdictName=='是'){
//    				ids+=dict.seItems[i].id;
//        			names+=dict.seItems[i].name;
//    			}else{
//    				 Feng.info("选中的字典有子节点，不能进行删除，只能先删除子节点!");
//    				return false;
//    			}
//    		
//    		}else{
//    			ids+=dict.seItems[i].id+",";
//    			names+=dict.seItems[i].name+",";
//    		}
//    	}
    	var operation = function(){
            $.ajax({
                type: "post",
                url:  Feng.ctxPath + "/backend/system/dict/delete",
                dataType: "json",
                data:{'ids':ids},
                async: true,
                success: function(data){
                	if(data.code==1){
                		  Feng.success("删除成功!");
                		  dict.table.refresh();
                	}else{
                		Feng.error(data.msg);
                	}
                }
            });
        };

        Feng.confirm("是否刪除选中字典?", operation);
    }
};

Dict.resetSearch = function () {
    $("#dictName").val("");
    $("#dictCode").val("");
    Dict.search();
}

/**
 * 搜索
 */
Dict.search = function () {
    var queryData = {};

    queryData['dictName'] = $("#dictName").val();
    queryData['dictCode'] = $("#dictCode").val();

    Dict.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Dict.initColumn();
    var table = new BSTable("dictTable", "/backend/system/dict/list", defaultColunms);
    table.setPage(10,[5,10,15,20]);
    Dict.table = table.init();
});
