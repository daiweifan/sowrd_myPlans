<#assign contentBody>
<div class="row">
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>企业管理</h5>
			</div>
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-lg-10 col-sm-9">
								<div class="row">
									<div class="col-lg-10 col-sm-9">
										<div class="row">
											<div class="col-lg-4 col-sm-12">
												<@u.nameCon id="name" buttonValue="企业名称" placeholder="企业全称"/>
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
												<@u.button value="搜索" icon="fa-search" clickFun="MgrCompany.search()" class="btn-primary" />
												<@u.button value="重置" icon="fa-trash" clickFun="MgrCompany.resetSearch()" class="btn-default" />
											</div>
										</div>
									</div>
								</div>
								<div class="hidden-xs" id="companyTableToolbar" role="group">
									<@u.button value="添加" icon="fa-plus" clickFun="MgrCompany.openAddMgr()" class="btn-primary" id=""/>

									<@u.button value="修改" icon="fa-edit" clickFun="MgrCompany.openChangeCompany()" class="btn-primary" id=""/>
									<@u.button value="删除" icon="fa-remove" clickFun="MgrCompany.delMgrCompany()" class="btn-primary" id=""/>
									<@u.button value="冻结" icon="fa-warning" clickFun="MgrCompany.freezeAccount()" class="btn-primary" id=""/>
									<@u.button value="解除冻结" icon="fa-check-circle" clickFun="MgrCompany.unfreeze()" class="btn-primary" id=""/>
								</div>
								<@u.table id="companyTable"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${u.ctx}/js/backend/system/company/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  