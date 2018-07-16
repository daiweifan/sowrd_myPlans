<#assign contentBody>
<div class="col-md-4 col-md-offset-4 col-sm-8  col-sm-offset-2">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>修改密码</h5>
        </div>
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content" style="border:none !important; ">
                            <div class="form-horizontal">
                                <div class="row">
                                    <div class="col-sm-12">
<div class="form-group">
    <label class="col-sm-3 control-label">原密码</label>
    <div class="col-sm-9">
        <input class="form-control" id="oldPwd" type="password">

    </div>
</div>
    <div class="hr-line-dashed"></div>


<div class="form-group">
    <label class="col-sm-3 control-label">新密码</label>
    <div class="col-sm-9">
        <input class="form-control" id="newPwd" type="password">

    </div>
</div>
    <div class="hr-line-dashed"></div>


<div class="form-group">
    <label class="col-sm-3 control-label">新密码验证</label>
    <div class="col-sm-9">
        <input class="form-control" id="rePwd" type="password">

    </div>
</div>


                                    </div>
                                </div>
                                <div class="row btn-group-m-t mt-20">
                                    <div class="col-sm-4 col-sm-offset-4">

<button type="button" class="btn btn-info " onclick="UserInfoDlg.chPwd()" id="ensure">
    <i class="fa fa-check"></i>&nbsp;提交
</button>

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

</#assign>
<#include "/backend/shared/container.ftl" />  