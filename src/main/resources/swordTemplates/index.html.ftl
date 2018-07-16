<#assign contentBody>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>{{= bean.content}}管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                                <@u.nameCon  id="{{= bean.lowerName}}Name" buttonValue="{{= bean.content}}名称" placeholder="输入菜单名称..." />
                            </div>
                            <div class="col-sm-3">
                                <@u.button value="搜索" icon="fa-search" clickFun="{{= bean.name}}.search()" class="btn-primary" />
                            </div>
                        </div>
                        <div class="hidden-xs" id="{{= bean.lowerName}}TableToolbar" role="group">
                            <@shiro.hasPermission name="/{{= bean.lowerName}}/add">
                                <@u.button value="添加" icon="fa-plus" clickFun="{{= bean.name}}.openAdd()" class="btn-primary" />
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/{{= bean.lowerName}}/edit">
                                <@u.button value="修改" icon="fa-edit" clickFun="{{= bean.name}}.openEdit()" class="btn-primary ml-20" />
                            </@shiro.hasPermission>
                            <shiro.hasPermission name="/{{= bean.lowerName}}/remove">
                                <@u.button value="删除" icon="fa-remove" clickFun="{{= bean.name}}.openDel()" class="btn-primary ml-20" />
                            </shiro.hasPermission>
                        </div>
                        <@u.table id="{{= bean.lowerName}}Table"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${u.ctx}/js/backend/system/{{= bean.lowerName}}/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
