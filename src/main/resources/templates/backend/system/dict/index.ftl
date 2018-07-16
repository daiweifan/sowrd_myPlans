<#assign contentBody>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>字典管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                                <@u.nameCon  id="dictName" buttonValue="字典名称" placeholder="输入字典名称..." />
                            </div>
                             <div class="col-sm-3">
                                <@u.nameCon  id="dictCode" buttonValue="字典编码" placeholder="输入字典编码..." />
                            </div>
                            <div class="col-sm-3">
                                <@u.button value="搜索" icon="fa-search" clickFun="Dict.search()" class="btn-primary" />
                            </div>
                        </div>
                        <div class="hidden-xs" id="dictTableToolbar" role="group">
                            <@shiro.hasPermission name="system:dict:add">
                                <@u.button value="添加" icon="fa-plus" clickFun="Dict.openAdd()" class="btn-primary" />
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="system:dict:edit">
                                <@u.button value="修改" icon="fa-edit" clickFun="Dict.openEdit()" class="btn-primary ml-20" />
                            </@shiro.hasPermission>
                            <shiro.hasPermission name="system:dict:delete">
                                <@u.button value="删除" icon="fa-remove" clickFun="Dict.del()" class="btn-primary ml-20" />
                            </shiro.hasPermission>
                        </div>
                        <@u.table id="dictTable"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${u.ctx}/js/backend/system/dict/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
