<#assign contentBody>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>菜单管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                                <@u.nameCon  id="menuName" buttonValue="菜单名称" placeholder="输入菜单名称..." />
                            </div>
                            <div class="col-sm-3">
                                <@u.nameCon  id="level" buttonValue="层级" placeholder="层级.." />
                            </div>
                            <div class="col-sm-3">
                                <@u.button value="搜索" icon="fa-search" clickFun="Menu.search()" class="btn-primary" />
                            </div>
                        </div>
                        <div class="hidden-xs" id="menuTableToolbar" role="group">
                            <@shiro.hasPermission name="system:menu:add">
                                <@u.button value="添加" icon="fa-plus" clickFun="Menu.openAddMenu()" class="btn-primary" />
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="system:menu:edit">
                                <@u.button value="修改" icon="fa-edit" clickFun="Menu.openChangeMenu()" class="btn-primary ml-20" />
                            </@shiro.hasPermission>
                            <shiro.hasPermission name="system:menu:delete">
                                <@u.button value="删除" icon="fa-remove" clickFun="Menu.delMenu()" class="btn-primary ml-20" />
                            </shiro.hasPermission>
                        </div>
                        <@u.table id="menuTable"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${u.ctx}/js/backend/system/menu/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
