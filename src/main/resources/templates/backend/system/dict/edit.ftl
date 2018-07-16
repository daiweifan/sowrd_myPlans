<#assign contentBody>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="form-horizontal" id="dictInfoForm">

            <input type="hidden" id="id" value="${dict.id}">

            <div class="row">
                <div class="col-sm-6 b-r">
                    <@u.input id="name" labelName="字典名称" value="${dict.name}" underline="true" required="true"/>
                    <@u.input id="parentName" labelName="上级菜单" value="${parentName!}" underline="true" placeholder="默认不选为顶级菜单"
                            hidden="parent" readonly="readonly" hiddenValue="${parentId!}"
                            clickFun="DictInfoDlg.showDictSelectTree(); return false;"
                            style="background-color: #ffffff !important;"
                            selectFlag="true" selectId="parentTreeDiv" selectTreeId="parentTree" selectStyle="width:244px !important;"/>
                   
   
                </div>
                <div class="col-sm-6">
                    <@u.input id="code" labelName="字典编号" value="${dict.code}" underline="true" required="true"/>
                    <@u.input id="sort" labelName="排序" value="${dict.sort!}" underline="true" required="true"/>
                </div>
            </div>

            <div class="row btn-group-m-t">
                <div class="col-sm-10">
                    <@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="DictInfoDlg.editSubmit()"/>
                    <@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="DictInfoDlg.close()"/>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="${u.ctx}/js/backend/system/dict/info.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
