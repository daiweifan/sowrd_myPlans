/**
 * 定时任务详情对话框（可用于添加和修改对话框）
 */
var ScheduleJobInfoDlg = {
    scheduleJobInfoData: {},
    validateFields: {
        beanName: {
            validators: {
                notEmpty: {
                    message: 'bean名称不能为空'
                }
            }
        },
        params: {
            validators: {
                notEmpty: {
                    message: '参数不能为空'
                }
            }
        },
        methodName: {
            validators: {
                notEmpty: {
                    message: '方法名称不能为空'
                }
            }
        },
        cronExpression: {
            validators: {
                notEmpty: {
                    message: 'cron表达式不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ScheduleJobInfoDlg.clearData = function () {
    this.scheduleJobInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleJobInfoDlg.set = function (key, val) {
    this.scheduleJobInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleJobInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ScheduleJobInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrScheduleJob.layerIndex);
};



/**
 * 收集数据
 */
ScheduleJobInfoDlg.collectData = function () {
    this.set('id').set('beanName').set('methodName').set('params').set('cronExpression').set('note');
};


/**
 * 验证数据是否为空
 */
ScheduleJobInfoDlg.validate = function () {
    $('#scheduleJobInfoForm').data("bootstrapValidator").resetForm();
    $('#scheduleJobInfoForm').bootstrapValidator('validate');
    return $("#scheduleJobInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加定时任务
 */
ScheduleJobInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    $.ajax({
        type: "post",
        url:  Feng.ctxPath + "/backend/system/scheduleJob/save",
        dataType: "json",
        data:this.scheduleJobInfoData,
        async: true,
        success: function(data){
        	if(data.code==1){
        		   Feng.success("添加成功!");
                   window.parent.MgrScheduleJob.table.refresh();
                   ScheduleJobInfoDlg.close();
        	}else{
        		Feng.error(data.msg);
        	}
         
        }
    });
        
};



$(function () {
	
    Feng.initValidator("scheduleJobInfoForm", ScheduleJobInfoDlg.validateFields);

});
