<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="companyInfoForm">
		
			<input type="hidden" id="id" name="id" value="">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="name" labelName="企业名称" underline="true"/>

					<@u.input id="introduction" labelName="介绍" underline="true" />

					<@u.input id="description" labelName="描述" underline="true" />

					<@u.input id="email" labelName="邮箱" type="email"/>
				</div>
				<div class="col-sm-6">
					<div id="driverInfoContent">
						<@u.input id="telephone" labelName="座机" underline="true"/>

						<@u.input id="hibuick" labelName="官微" underline="true"/>
						<@u.input id="weibo" labelName="微博" underline="true"/>

						<@u.input id="address" labelName="地址"/>
					</div>
				</div>
			</div>

			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="CompanyInfoDlg.addSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="CompanyInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/company/add.js"></script>

</#assign>
<#include "/backend/shared/container.ftl" />  
