<#assign contentBody>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="form-horizontal" id="departmentInfoForm">

            <input type="hidden" id="id" value="${department.id}">

            <div class="row">
                <div class="col-sm-6 b-r">
                    <@u.input id="name" labelName="部门名称" underline="true" value="${department.name!}" required="true"/>

                    <@u.input id="code" labelName="部门编号" underline="true" value="${department.code!}" required="true"/>

                    <@u.input id="introduction" labelName="介绍" underline="true" value="${department.introduction!}"/>
                </div>
                <div class="col-sm-6">
                    <@u.input id="sort" labelName="排序" underline="true" value="${department.sort!}"  required="true"/>

                    <@u.input id="parentName" labelName="上级部门"readonly="readonly" hidden="parent"
                            hiddenValue="${parentId!}" value="${parentName!}"
                            clickFun="DepartmentInfoDlg.showDepartmentSelectTree(); return false;"
                            style="background-color: #ffffff !important;"/>
                </div>
            </div>

            <!-- 父级部门的选择框 -->
            <div id="parentDepartmentMenu" class="menuContent"
                 style="display: none; position: absolute; z-index: 200;">
                <ul id="parentDepartmentMenuTree" class="ztree tree-box" style="width: 250px !important;"></ul>
            </div>

            <div class="row btn-group-m-t">
                <div class="col-sm-10">
                    <@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="DepartmentInfoDlg.editSubmit()"/>
                    <@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="DepartmentInfoDlg.close()"/>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="${u.ctx}/js/backend/system/department/info.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
