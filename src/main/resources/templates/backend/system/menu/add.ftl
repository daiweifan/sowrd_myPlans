<#assign contentBody>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="form-horizontal" id="menuInfoForm">
		
			<input type="hidden" id="id" value="">

			<div class="row">
				<div class="col-sm-6 b-r">
					<@u.input id="name" labelName="菜单名称" underline="true" required="true"/>
					<@u.input id="code" labelName="菜单编号" underline="true" required="true"/>
					<@u.input id="parentName" labelName="上级菜单" placeholder="默认不选为顶级菜单" underline="true"
							hidden="parent" readonly="readonly"
							clickFun="MenuInfoDlg.showMenuSelectTree(); return false;"
							style="background-color: #ffffff !important;"
							selectFlag="true" selectId="parentTreeDiv" selectTreeId="parentTree" selectStyle="width:258px !important;"/>
					<div class="form-group">
					    <label class="col-sm-3 control-label">是否菜单</label>
					    <div class="col-sm-9">
					        <select class="form-control" id="showMenu" name="showMenu">
					            <option value="1">是</option>
								<option value="0">不是</option>
					        </select>
					    </div>
					</div>
				</div>
				<div class="col-sm-6">
					<@u.input id="url" labelName="请求地址" underline="true" value="#" required="true"/>
					<@u.input id="sort" labelName="排序" underline="true"  required="true"/>
					<@u.input id="icon" labelName="图标" underline="false" placeholder="例如：fa-address-book" />
					<div style="margin-left:150px;"><a href="http://www.fontawesome.com.cn/faicons/" target="_blank" >参考图标库请看这里</a></div>
				

				</div>
			</div>

			<div class="row btn-group-m-t">
				<div class="col-sm-10">
					<@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="MenuInfoDlg.addSubmit()"/>
					<@u.button class="btn-danger" value="取消" id="cancel" icon="fa-eraser" clickFun="MenuInfoDlg.close()"/>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="${u.ctx}/js/backend/system/menu/info.js"></script>
</#assign>
<#include "/backend/shared/container.ftl" />  
