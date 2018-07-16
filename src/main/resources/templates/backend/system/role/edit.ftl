<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="roleInfoForm">
		
			<input type="hidden" id="id" value="${role.id}">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="name" labelName="角色名称" underline="true" value="${role.name}"/>
						
					<@u.input id="deptName" labelName="部门名称" hidden="department"  hiddenValue="${role.department.id}" readonly="readonly" value="${role.deptName!}"
							clickFun="RoleInfoDlg.showDeptSelectTree(); return false;"
							style="background-color: #ffffff !important;" underline="true" value="${deptName}" />
					<@u.input id="note" labelName="备注" value="${role.note}" />
				</div>
				<div class="col-sm-6">
					<@u.input id="code" labelName="角色代码" underline="true" value="${role.code}"/>
					<@u.input id="sort" labelName="排序" value="${role.sort}"/>
				</div>
			</div>

			<!-- 这是部门下拉框 -->
			<div id="deptContent" class="menuContent"
				style="display: none; position: absolute; z-index: 200;">
				<ul id="deptTree" class="ztree tree-box" style="width: 250px !important;"></ul>
			</div>
			

			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="RoleInfoDlg.editSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="RoleInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/role/info.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
