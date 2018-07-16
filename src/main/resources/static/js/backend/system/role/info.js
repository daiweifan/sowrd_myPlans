/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RoleInfoDlg = {
    roleInfoData: {},
    deptZtree: null,
    pNameZtree: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '编码不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '父级名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RoleInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfoDlg.set = function (key, val) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoleInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RoleInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RoleInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RoleInfoDlg.deptZtree.getSelectedVal());
    $("#department").attr("value", treeNode.id);
};


/**
 * 显示部门选择的树
 *
 * @returns
 */
RoleInfoDlg.showDeptSelectTree = function () {
    Feng.showInputTree("deptName", "deptContent");
};

/**
 * 收集数据
 */
RoleInfoDlg.collectData = function () {
    this.set('id').set('name').set('department').set('code').set('sort').set('note');
};

/**
 * 验证数据是否为空
 */
RoleInfoDlg.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
RoleInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/role/save",
        dataType: "json",
        data:this.roleInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("添加成功!");
                   window.parent.Role.table.refresh();
                   RoleInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
};

/**
 * 提交修改
 */
RoleInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/role/save",
        dataType: "json",
        data:this.roleInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("修改成功!");
                   window.parent.Role.table.refresh();
                   RoleInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
};

$(function () {
    Feng.initValidator("roleInfoForm", RoleInfoDlg.validateFields);

    var deptTree = new $ZTree("deptTree", "/backend/system/department/selectDepartmentTreeList");
    deptTree.bindOnClick(RoleInfoDlg.onClickDept);
    deptTree.init();
    RoleInfoDlg.deptZtree = deptTree;

});
