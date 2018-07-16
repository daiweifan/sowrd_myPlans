<#assign contentBody>
<div class="row">
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>用户管理</h5>
			</div>
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-lg-2 col-sm-3">
								<div class="panel panel-default">
									<div class="panel-heading">组织机构</div>
									<div class="panel-body dept-tree">
										<ul id="deptTree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="col-lg-10 col-sm-9">
								<div class="row">
									<div class="col-lg-10 col-sm-9">
										<div class="row">
											<div class="col-lg-4 col-sm-12">
												<@u.nameCon id="name" buttonValue="用户名称" placeholder="帐号/姓名/手机号"/>
											</div>
											<div class="col-lg-4 col-sm-6">
												<@u.timeCon id="beginTime" buttonValue="注册开始日期" isTime="false" pattern="YYYY-MM-DD"/>
											</div>
											<div class="col-lg-4 col-sm-6">
												<@u.timeCon id="endTime" buttonValue="注册结束日期" isTime="false" pattern="YYYY-MM-DD"/>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-sm-3">
										<div class="row">
											<div class="col-lg-12 col-sm-12">
												<@u.button value="搜索" icon="fa-search" clickFun="MgrUser.search()" class="btn-primary" id=""/>
												<@u.button value="重置" icon="fa-trash" clickFun="MgrUser.resetSearch()" class="btn-default" id=""/>
											</div>
										</div>
									</div>
								</div>
								<div class="hidden-xs" id="managerTableToolbar" role="group">
									<@u.button value="添加" icon="fa-plus" clickFun="MgrUser.openAddMgr()" class="btn-primary" id=""/>

									<@u.button value="修改" icon="fa-edit" clickFun="MgrUser.openChangeUser()" class="btn-primary" id=""/>
									<@u.button value="删除" icon="fa-remove" clickFun="MgrUser.delMgrUser()" class="btn-primary" id=""/>
									<@u.button value="重置密码" icon="fa-refresh" clickFun="MgrUser.resetPwd()" class="btn-primary" id=""/>
									<@u.button value="冻结" icon="fa-warning" clickFun="MgrUser.freezeAccount()" class="btn-primary" id=""/>
									<@u.button value="解除冻结" icon="fa-check-circle" clickFun="MgrUser.unfreeze()" class="btn-primary" id=""/>
									<@u.button value="角色分配" icon="fa-user-secret" clickFun="MgrUser.roleAssign()" class="btn-primary" id=""/>
								</div>
								<@u.table id="managerTable"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${u.ctx}/js/backend/system/user/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  