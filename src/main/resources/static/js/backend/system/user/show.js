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
console.info(this.userInfoData);
    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/user/updateShow",
        dataType: "json",
        data:this.userInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		console.info(data);
        		Feng.success("修改成功!");
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
        
};



function showPhoto(path,name){ 
	// 预览图片json数据组 
	var preList = new Array(); 
	// 图片类型 
	preList[0]= "<img src=\"/eim/upload/getIMG.do?savePath="+path+"&name="+name+"\" class=\"file-preview-image\">"; 
	var previewJson = preList; 
	// 与上面 预览图片json数据组 对应的config数据 
	var preConfigList = new Array(); 
	var tjson = {caption: array_element.fileIdFile.fileName, // 展示的文件名 
	width: '120px', 
	url: '/eim/project/deleteFile.do', // 删除url 
	key: array_element.id, // 删除是Ajax向后台传递的参数 
	extra: {id: 100} 
	}; 
	preConfigList[0] = tjson; 
	return preConfigList;
}
	
	
$(function () {
	
	
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);

    $("#uploadAvatar").fileinput({
        language: 'zh', //设置语言
        uploadUrl: Feng.ctxPath + "/backend/system/upload/single", //上传的地址
        allowedFileExtensions: ['jpg','png','gif'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        initialPreview: [
                         "<img src='"+Feng.ctxPath +$("#url").val()+"' class='file-preview-image img-responsive' style='max-height:100%!important' alt='Desert' title='Desert'>"
             ]
    });
    $("#uploadAvatar").on("fileuploaded", function (event, data, previewId, index) {
        var response = data.response;
        $("#avatar").val(response.data.id);
    });
 
});
