<#assign contentBody>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>部门管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                                <@u.nameCon  id="departmentName" buttonValue="部门名称" placeholder="输入部门名称..." />
                            </div>
                            <div class="col-sm-3">
                                <@u.button value="搜索" icon="fa-search" clickFun="Department.search()" class="btn-primary" />
                            </div>
                        </div>
                        <div class="hidden-xs mt-20" id="DepartmentTableToolbar" role="group">
                            <shiro.hasPermission name="system:department:add">
                                <@u.button value="添加" icon="fa-plus" clickFun="Department.openAddDepartment()" class="btn-primary" />
                            </shiro.hasPermission>
                            <shiro.hasPermission name="system:department:edit">
                                <@u.button value="修改" icon="fa-edit" clickFun="Department.openChangeDepartment()" class="btn-primary ml-20" />
                            </shiro.hasPermission>
                            <shiro.hasPermission name="system:department:remove">
                                <@u.button value="删除" icon="fa-remove" clickFun="Department.delDepartment()" class="btn-primary ml-20" />
                            </shiro.hasPermission>
                        </div>
                        <@u.table id="departmentTable"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${u.ctx}/js/backend/system/department/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  