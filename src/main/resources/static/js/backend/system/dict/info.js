/**
 * 菜单详情对话框
 */
var DictInfoDlg = {
    dictInfoData: {},
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
        sort: {
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
DictInfoDlg.clearData = function () {
    this.dictInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictInfoDlg.set = function (key, val) {
    this.dictInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DictInfoDlg.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
}

/**
 * 收集数据
 */
DictInfoDlg.collectData = function () {
    this.set('id').set('name').set('code').set('parent').set('sort');
}

/**
 * 验证数据是否为空
 */
DictInfoDlg.validate = function () {
    $('#dictInfoForm').data("bootstrapValidator").resetForm();
    $('#dictInfoForm').bootstrapValidator('validate');
    return $("#dictInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加用户
 */
DictInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/dict/save",
        dataType: "json",
        data:this.dictInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("添加成功!");
                   window.parent.Dict.table.refresh();
                   DictInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
}

/**
 * 提交修改
 */
DictInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/dict/update",
        dataType: "json",
        data:this.dictInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("修改成功!");
                   window.parent.Dict.table.refresh();
                   DictInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
}

/**
 * 点击父级编号input框时
 */
DictInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#parentName").attr("value", DictInfoDlg.ztreeInstance.getSelectedVal());
    $("#parent").attr("value", treeNode.id);
};


/**
 * 显示父级菜单选择的树
 */
DictInfoDlg.showDictSelectTree = function () {
    Feng.showInputTree("parentName", "parentTreeDiv", 15, 34);
};

$(function () {
    Feng.initValidator("dictInfoForm", DictInfoDlg.validateFields);

    var ztree = new $ZTree("parentTree", "/backend/system/dict/selectDictTreeList");
    ztree.bindOnClick(DictInfoDlg.onClickDept);
    ztree.init();
    DictInfoDlg.ztreeInstance = ztree;
});
