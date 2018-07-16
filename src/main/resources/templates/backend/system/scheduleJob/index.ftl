<#assign contentBody>
<div class="row">
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>定时任务管理</h5>
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
												<@u.nameCon id="name" buttonValue="Bean名称" placeholder="Bean名称"/>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-sm-3">
										<div class="row">
											<div class="col-lg-12 col-sm-12">
												<@u.button value="搜索" icon="fa-search" clickFun="MgrScheduleJob.search()" class="btn-primary" />
												<@u.button value="重置" icon="fa-trash" clickFun="MgrScheduleJob.resetSearch()" class="btn-default" />
											</div>
										</div>
									</div>
								</div>
								<div class="hidden-xs" id="managerTableToolbar" role="group">
									<@u.button value="添加" icon="fa-plus" clickFun="MgrScheduleJob.openAddMgr()" class="btn-primary" id=""/>

									<@u.button value="修改" icon="fa-edit" clickFun="MgrScheduleJob.openChangescheduleJob()" class="btn-primary" id=""/>
									<@u.button value="删除" icon="fa-remove" clickFun="MgrScheduleJob.delMgrScheduleJob()" class="btn-primary" id=""/>
									<@u.button value="暂停" icon="fa-warning" clickFun="MgrScheduleJob.freezeAccount()" class="btn-primary" id=""/>
									<@u.button value="恢复" icon="fa-check-circle" clickFun="MgrScheduleJob.unfreeze()" class="btn-primary" id=""/>
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
<script src="${u.ctx}/js/backend/system/scheduleJob/index.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  