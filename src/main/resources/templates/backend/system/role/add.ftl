<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="roleInfoForm">
		
			<input type="hidden" id="id" value="">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="name" labelName="角色名称" underline="true"/>
					<@u.input id="deptName" labelName="部门名称" hidden="department" readonly="readonly"
							clickFun="RoleInfoDlg.showDeptSelectTree(); return false;"
							style="background-color: #ffffff !important;" underline="true"/>
					<@u.input id="note" labelName="备注" underline="true"/>
				</div>
				<div class="col-sm-6">
						<@u.input id="code" labelName="角色代码" underline="true"/>
					<@u.input id="sort" labelName="排序"/>
				</div>
			</div>

			<!-- 这是部门下拉框 -->
			<div id="deptContent" class="menuContent"
				style="display: none; position: absolute; z-index: 200;">
				<ul id="deptTree" class="ztree tree-box" style="width: 250px !important;"></ul>
			</div>
			

			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="RoleInfoDlg.addSubmit()"/>
					<@u.button class="btn-cancel" value="取消" id="cancel" icon="fa-eraser" clickFun="RoleInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/role/info.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
