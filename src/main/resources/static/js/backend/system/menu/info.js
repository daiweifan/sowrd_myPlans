/**
 * 菜单详情对话框
 */
var MenuInfoDlg = {
    menuInfoData: {},
    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '菜单名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '菜单编号不能为空'
                }
            }
        },
        url: {
            validators: {
                notEmpty: {
                    message: '请求地址不能为空'
                }
            }
        },
        num: {
            validators: {
                notEmpty: {
                    message: '序号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
MenuInfoDlg.clearData = function () {
    this.menuInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.set = function (key, val) {
    this.menuInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MenuInfoDlg.close = function () {
    parent.layer.close(window.parent.Menu.layerIndex);
}

/**
 * 收集数据
 */
MenuInfoDlg.collectData = function () {
    this.set('id').set('name').set('code').set('parent').set('url').set('sort').set('icon').set("showMenu");
}

/**
 * 验证数据是否为空
 */
MenuInfoDlg.validate = function () {
    $('#menuInfoForm').data("bootstrapValidator").resetForm();
    $('#menuInfoForm').bootstrapValidator('validate');
    return $("#menuInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加用户
 */
MenuInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/menu/save",
        dataType: "json",
        data:this.menuInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("添加成功!");
                   window.parent.Menu.table.refresh();
                   MenuInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
}

/**
 * 提交修改
 */
MenuInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/menu/update",
        dataType: "json",
        data:this.menuInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("修改成功!");
                   window.parent.Menu.table.refresh();
                   MenuInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
}

/**
 * 点击父级编号input框时
 */
MenuInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#parentName").attr("value", MenuInfoDlg.ztreeInstance.getSelectedVal());
    $("#parent").attr("value", treeNode.id);
};


/**
 * 显示父级菜单选择的树
 */
MenuInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("parentName", "parentTreeDiv", 15, 34);
};

$(function () {
    Feng.initValidator("menuInfoForm", MenuInfoDlg.validateFields);

    var ztree = new $ZTree("parentTree", "/backend/system/menu/selectMenuTreeList");
    ztree.bindOnClick(MenuInfoDlg.onClickDept);
    ztree.init();
    MenuInfoDlg.ztreeInstance = ztree;
    //初始化是否是菜单
    if($("#isMenuValue").val() == undefined){
        $("#showMenu").val(0);
    }else{
        $("#showMenu").val($("#isMenuValue").val());
    }
});
