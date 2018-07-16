/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var CompanyInfoDlg = {
    companyInfoData: {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '企业名称不能为空'
                }
            }
        },
        introduction: {
            validators: {
                notEmpty: {
                    message: '介绍不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CompanyInfoDlg.clearData = function () {
    this.companyInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyInfoDlg.set = function (key, val) {
    this.companyInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
CompanyInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrCompany.layerIndex);
};



/**
 * 收集数据
 */
CompanyInfoDlg.collectData = function () {
    this.set('id').set('name').set('introduction').set('description').set('telephone')
        .set('hibuick').set('weibo').set('email').set('address').set('messageNotice');
};


/**
 * 验证数据是否为空
 */
CompanyInfoDlg.validate = function () {
    $('#companyInfoForm').data("bootstrapValidator").resetForm();
    $('#companyInfoForm').bootstrapValidator('validate');
    return $("#companyInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
CompanyInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/company/save",
        dataType: "json",
        data:this.companyInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("添加成功!");
                   window.parent.MgrCompany.table.refresh();
                   CompanyInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
        
};



$(function () {
	
	
    Feng.initValidator("companyInfoForm", CompanyInfoDlg.validateFields);

});
