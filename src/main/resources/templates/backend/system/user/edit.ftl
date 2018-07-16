<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="userInfoForm">
		
			<input type="hidden" id="id" value="${user.id}">
			<input type="hidden" name="roles" >

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="username" labelName="用户名" underline="true" value="${user.username!}"/>
					<div class="form-group">
					    <label class="col-sm-3 control-label">性别</label>
					    <div class="col-sm-9">
					        <select class="form-control" id="sex" value="${user.sex!}">
					            <option value="1">男</option>
								<option value="2">女</option>
					        </select>
					    </div>
					</div>
					<div class="hr-line-dashed"></div>


					<@u.input id="roleid" labelName="角色" underline="true" value="${roleName!}" disabled="disabled"/>

					<@u.input id="email" labelName="邮箱" type="email" value="${user.email!}"/>
				</div>
				<div class="col-sm-6">
					<div id="driverInfoContent">
					   <@u.input id="password" labelName="密码" underline="true" value="${user.password!}"  />
						<@u.input id="realname" labelName="真实姓名" underline="true" value="${user.realname!}" />

						<@u.input id="birthday" labelName="出生日期" underline="true" value="${birthday!}" type="date"
								clickFun="laydate({istime: false, format: 'YYYY-MM-DD'})"/>
						<@u.input id="phone" labelName="电话" value="${user.phone!}"/>
					</div>
				</div>
			</div>


			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="UserInfoDlg.editSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="UserInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/user/edit.js"></script>

</#assign>
<#include "/backend/shared/container.ftl" />  
