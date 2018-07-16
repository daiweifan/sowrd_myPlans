/**
 * 初始化部门详情对话框
 */
var DepartmentInfoDlg = {
    departmentInfoData : {},
    zTreeInstance : null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '部门名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '部门编号不能为空'
                }
            }
        },
        parent: {
            validators: {
                notEmpty: {
                    message: '上级名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DepartmentInfoDlg.clearData = function() {
    this.departmentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DepartmentInfoDlg.set = function(key, val) {
    this.departmentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DepartmentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DepartmentInfoDlg.close = function() {
    parent.layer.close(window.parent.Department.layerIndex);
}

/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
DepartmentInfoDlg.onClickDepartment = function(e, treeId, treeNode) {
    $("#parentName").attr("value", DepartmentInfoDlg.zTreeInstance.getSelectedVal());
    $("#parent").attr("value", treeNode.id);
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
DepartmentInfoDlg.showDepartmentSelectTree = function() {
    var pName = $("#parentName");
    var pNameOffset = $("#parentName").offset();
    $("#parentDepartmentMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
DepartmentInfoDlg.hideDepartmentSelectTree = function() {
    $("#parentDepartmentMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

/**
 * 收集数据
 */
DepartmentInfoDlg.collectData = function() {
    this.set('id').set('name').set('code').set('parent').set('sort').set('introduction');
}

/**
 * 验证数据是否为空
 */
DepartmentInfoDlg.validate = function () {
    $('#departmentInfoForm').data("bootstrapValidator").resetForm();
    $('#departmentInfoForm').bootstrapValidator('validate');
    return $("#departmentInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加部门
 */
DepartmentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/department/save",
        dataType: "json",
        data:this.departmentInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("添加成功!");
                   window.parent.Department.table.refresh();
                   DepartmentInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
}

/**
 * 提交修改
 */
DepartmentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/department/save",
        dataType: "json",
        data:this.departmentInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("修改成功!");
                   window.parent.Department.table.refresh();
                   DepartmentInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDepartmentMenu" || $(
            event.target).parents("#parentDepartmentMenu").length > 0)) {
        DepartmentInfoDlg.hideDepartmentSelectTree();
    }
}

$(function() {
    Feng.initValidator("departmentInfoForm", DepartmentInfoDlg.validateFields);

    var ztree = new $ZTree("parentDepartmentMenuTree", "/backend/system/department/selectDepartmentTreeList");
    ztree.bindOnClick(DepartmentInfoDlg.onClickDepartment);
    ztree.init();
    DepartmentInfoDlg.zTreeInstance = ztree;
});
