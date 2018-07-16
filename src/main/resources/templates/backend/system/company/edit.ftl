<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="companyInfoForm">
		
			<input type="hidden" id="id" value="${company.id}">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="name" labelName="企业名称" underline="true" value="${company.name!}"/>

					<@u.input id="introduction" labelName="介绍" underline="true" value="${company.introduction!}"/>

					<@u.input id="description" labelName="描述" underline="true" value="${company.description!}"/>

					<@u.input id="email" labelName="邮箱" type="email" value="${company.email!}"/>
				</div>
				<div class="col-sm-6">
					<div id="driverInfoContent">
						<@u.input id="telephone" labelName="座机" underline="true" value="${company.telephone!}"/>

						<@u.input id="hibuick" labelName="官微" underline="true" value="${company.hibuick!}"/>
						<@u.input id="weibo" labelName="微博" underline="true" value="${company.weibo!}"/>

						<@u.input id="address" labelName="地址" value="${company.address!}"/>
					</div>
				</div>
			</div>


			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="CompanyInfoDlg.editSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="CompanyInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/company/edit.js"></script>

</#assign>
<#include "/backend/shared/container.ftl" />  
