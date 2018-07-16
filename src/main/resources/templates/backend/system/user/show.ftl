<#assign contentBody>  
<div class="col-md-10 col-md-offset-1 col-sm-10  col-sm-offset-1">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>用户信息</h5>
        </div>
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content" style="border:none !important; ">
                            <div class="form-horizontal" id="userInfoForm">

                                <input type="hidden" id="id" value="${user.id}">
                                <input type="hidden" id="url" value="${url!}">

                                <div class="row">
                                    <div class="col-sm-4 b-r">
										    <label class="control-label mb-30" >头像</label>
										    <input id="uploadAvatar"  type="file" style="max-height:100%!important" multiple class="file-loading img-responsive" >
                                    		<input id="avatar" name="avatar" type="hidden" value="${(user.avatar.id)!}">
                                    </div>
 									<div class="col-sm-4 b-r">

                                        <@u.input id="username" labelName="用户名" underline="true" value="${user.username!}" disabled="disabled" />

                                        <div class="form-group">
										    <label class="col-sm-4 control-label">性别</label>
										    <div class="col-sm-8">
										        <select class="form-control" id="sex" value="${user.sex!}">
										            <option value="1">男</option>
													<option value="0">女</option>
										        </select>
										    </div>
										</div>
										<div class="hr-line-dashed"></div>
										<@u.input id="roleid" labelName="角色" underline="true" value="${roleName!}" disabled="disabled"/>
					
										<@u.input id="email" labelName="邮箱" type="email" value="${user.email!}"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <div id="driverInfoContent">
                                         <script>alert("${user.realname!}")</script>
                                        	<@u.input id="realname" labelName="真实姓名" underline="true" value="${user.realname!}" />

											<@u.input id="birthday" labelName="出生日期" underline="true" value="${birthday}" type="date"
													clickFun="laydate({istime: false, format: 'YYYY-MM-DD'})"/>
					
											<@u.input id="phone" labelName="电话" value="${user.phone!}"/>



                                        </div>
                                    </div>
                                </div>

                                <div class="progress progress-striped" id="progressTipArea" style="margin-top: 20px;">
                                    <div id="progressBar" style="width: 0%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="0" role="progressbar" class="progress-bar progress-bar-info">
                                    </div>
                                </div>

                                <div class="row btn-group-m-t">
                                    <div class="col-sm-10">
                                        <@u.button class="btn-info" value="提交" id="ensure" icon="fa-check" clickFun="UserInfoDlg.editSubmit()"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${u.ctx}/js/backend/system/user/show.js"></script>
</#assign>  
<#include "/backend/shared/container.ftl" />  