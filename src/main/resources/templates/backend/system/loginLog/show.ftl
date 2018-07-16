<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="loginLogInfoForm">
		
			<input type="hidden" id="id" value="${loginLog.id}">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.label labelName="日志名称" underline="true" value="${loginLog.name!}"/>

					<@u.label labelName="方法名" underline="true" value="${loginLog.methodName!}"/>

					<@u.label labelName="参数" underline="true" value="${loginLog.params!}"/>

					<@u.label labelName="反应时间"  underline="true" value="${loginLog.resTime!}"/>
					<@u.label labelName="ip地址"  value="${loginLog.ip!}"/>
				</div>
				<div class="col-sm-6">
					<div id="driverInfoContent">
						<@u.label labelName="类名" underline="true" value="${loginLog.className!}"/>

						<@u.label labelName="是否成功" underline="true" value="${loginLog.succeed!}"/>
						<@u.label labelName="操作时间" underline="true" value="${createTime!}"/>
						<@u.label labelName="返回信息" underline="true" value="${loginLog.message!}"/>

						<@u.label labelName="操作人" value="${username!}"/>
					</div>
				</div>
			</div>


			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-danger" value="关闭" id="cancel" icon="fa-eraser" clickFun="LoginLogInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/loginLog/show.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
