/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    userInfoData: {},
    validateFields: {
        username: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                }
            }
        },
        realname: {
            validators: {
                notEmpty: {
                    message: '真实姓名不能为空'
                }
            }
        },
        citySel: {
            validators: {
                notEmpty: {
                    message: '部门不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                },
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                },
            }
        }
    }
};

/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, val) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};



/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set('id').set('username').set('sex').set('password').set('avatar')
        .set('email').set('realname').set('birthday').set('rePassword').set('deptid').set('phone');
};

/**
 * 验证两个密码是否一致
 */
UserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};



/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }


    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/user/update",
        dataType: "json",
        data:this.userInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("修改成功!");
                   window.parent.MgrUser.table.refresh();
                   UserInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
        
};




$(function () {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);



});
