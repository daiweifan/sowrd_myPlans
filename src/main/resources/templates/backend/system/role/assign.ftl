<#assign contentBody>
<script type="text/javascript">
    $(function () {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

        $("#btn_close").bind("click", function () {
            parent.layer.close(index);
        });

        $("#btn_save").bind("click", function () {
            var ids = Feng.zTreeCheckedNodes("zTree");
              $.ajax({
		        type: "post",
		        url:  Feng.ctxPath + "/backend/system/role/setAuthority",
		        dataType: "json",
		        data:{ids:ids,roleId:${role.id}},
		        async: true,
		        success: function(data){
		        	if(data.code==1){
		        		   Feng.success("添加成功!");
		                   window.parent.Role.table.refresh();
		                   parent.layer.close(index);
		        	}else{
		        		Feng.error(data.msg);
		        	}
		         
		        }
		    });
        });

        initZtree();
    });

    function initZtree() {
        var setting = {
            check: {
                enable: true,
                chkboxType: { "Y": "ps", "N": "ps" }
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var ztree = new $ZTree("zTree", "/backend/system/menu/selectMenuTreeListByRoleId/"
            + "${role.id}");
        ztree.setSettings(setting);
        ztree.init();
    }
</script>


<!-- 配置grid -->
<div class="container" style="padding:  0px 10px !important;margin-top: -10px;text-align: center !important;">
    <div class="row">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>${role.name!}</h5>
            </div>
            <div class="ibox-content">
                <ul id="zTree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <button class="btn btn-sm btn-info" type="button" id="btn_save">
                <i class="ace-icon fa fa-check bigger-110"></i>保存
            </button>
            &nbsp;
            <button class="btn btn-sm btn-danger" type="button" id="btn_close">
                <i class="ace-icon fa fa-close bigger-110"></i>关闭
            </button>
        </div>
    </div>
</div>
</#assign>
<#include "/backend/shared/container.ftl" />  
