<#assign contentBody>
<div class="row">
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>操作日志管理</h5>
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
												<@u.nameCon id="name" buttonValue="日志名称" placeholder="日志全称"/>
											</div>
											<div class="col-lg-4 col-sm-6">
												<@u.timeCon id="beginTime" buttonValue="操作日期开始日期" isTime="false" pattern="YYYY-MM-DD"/>
											</div>
											<div class="col-lg-4 col-sm-6">
												<@u.timeCon id="endTime" buttonValue="操作日期结束日期" isTime="false" pattern="YYYY-MM-DD"/>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-sm-3">
										<div class="row">
											<div class="col-lg-12 col-sm-12">
												<@u.button value="搜索" icon="fa-search" clickFun="OperationLog.search()" class="btn-primary" />
												<@u.button value="重置" icon="fa-trash" clickFun="OperationLog.resetSearch()" class="btn-default" />
											</div>
										</div>
									</div>
								</div>
								<div class="hidden-xs" id="managerTableToolbar" role="group">
									<@shiro.hasPermission name="system:menu:add">
										<@u.button value="详情" icon="fa-edit" clickFun="OperationLog.show()" class="btn-primary" id=""/>
									</@shiro.hasPermission>
									<@u.button value="删除" icon="fa-remove" clickFun="OperationLog.del()" class="btn-primary" id=""/>
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
<script src="${u.ctx}/js/backend/system/operationLog/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  