<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="userInfoForm">
		
			<input type="hidden" id="id" name="id" value="">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="username" labelName="账户" underline="true"/>
					<div class="form-group">
					    <label class="col-sm-3 control-label">性别</label>
					    <div class="col-sm-9">
					        <select class="form-control" id="sex">
					            <option value="1">男</option>
								<option value="2">女</option>
					        </select>
					    </div>
					</div>
					<div class="hr-line-dashed"></div>

					<@u.input id="password" labelName="密码" underline="true" type="password"/>

					<@u.input id="roleid" labelName="角色" underline="true" disabled="disabled"/>

					<@u.input id="email" labelName="邮箱" type="email"/>
				</div>
				<div class="col-sm-6">
					<div id="driverInfoContent">
						<@u.input id="realname" labelName="姓名" underline="true"/>

						<@u.input id="birthday" labelName="出生日期" underline="true" type="date"
								clickFun="laydate({istime: false, format: 'YYYY-MM-DD'})"/>

						<@u.input id="rePassword" labelName="确认密码" type="password" underline="true"/>

						<!--<@u.input id="citySel" labelName="部门" underline="true" readonly="readonly" hidden="deptid"
								clickFun="UserInfoDlg.showDeptSelectTree(); return false;"
								style="background-color: #ffffff !important;"/>-->

						<@u.input id="phone" labelName="电话"/>
					</div>
				</div>
			</div>

			<!-- 这是部门选择的下拉框 -->
			<!--<div id="menuContent" class="menuContent"
				style="display: none; position: absolute; z-index: 200;">
				<ul id="treeDemo" class="ztree tree-box" style="width: 249px !important;"></ul>
			</div>-->

			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="UserInfoDlg.addSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="UserInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/user/add.js"></script>

</#assign>
<#include "/backend/shared/container.ftl" />  
