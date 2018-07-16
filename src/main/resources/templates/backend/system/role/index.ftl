<#assign contentBody>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>角色管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                                <@u.nameCon id="roleName" buttonValue="角色名称" />
                            </div>
                             <div class="col-sm-3">
                                <@u.nameCon id="roleCode" buttonValue="角色代码" />
                            </div>
                            <div class="col-sm-3">
                                <@u.button value="搜索" icon="fa-search" clickFun="Role.search()"  class="btn-primary"/>
                            </div>
                        </div>
                        <div class="hidden-xs" id="roleTableToolbar" role="group">
                            <shiro:hasPermission name="/role/add">
                                <@u.button value="添加" icon="fa-plus" clickFun="Role.openAddRole()" class="btn-primary"/>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/role/edit">
                                <@u.button value="修改" icon="fa-edit" clickFun="Role.openChangeRole()" class="btn-primary ml-20"/>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/role/remove">
                                <@u.button value="删除" icon="fa-remove" clickFun="Role.delRole()" class="btn-primary ml-20"/>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/role/setAuthority">
                                <@u.button value="权限配置" icon="fa-user-secret" clickFun="Role.assign()"  class="btn-primary ml-20"/>
                            </shiro:hasPermission>
                        </div>
                        <@u.table id="roleTable"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${u.ctx}/js/backend/system/role/index.js"></script> 
</#assign>
<#include "/backend/shared/container.ftl" />  
