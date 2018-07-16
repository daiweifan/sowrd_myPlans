<#assign ctx=request.contextPath />

<#macro redstar>
<span style="color:red">*</span>
</#macro>

<#macro table id>
<table id="${id!}" data-mobile-responsive="true" data-click-to-select="true">
    <thead>
        <tr>
            <th data-field="selectItem" data-checkbox="true"></th>
        </tr>
    </thead>
</table>
</#macro>

<#macro avatar id labelName avatarImg clickFun="" class="" value="" underline="false" readonly="" disabled="" type="text" hidden="" hiddenValue="" style="" placeholder="" required="false">
<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">${labelName!}</label>
    <div class="col-sm-4">
        <div id="${id!}PreId">
            <div><img width="100px" height="100px"
                <#if avatarImg??>
                      src="${ctx}/img/girl.gif" ></div>
                <#else>
                      src="${ctx}/kaptcha/${avatarImg!}" ></div>
                </#if>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="head-scu-btn upload-btn webuploader-container" id="${id!}BtnId">
            <i class="fa fa-upload"></i>&nbsp;上传
        </div>
    </div>
    <input type="hidden" id="${id!}" value="${avatarImg!}"/>
</div>
<#if underline=="true" >
 	<div class="hr-line-dashed"></div>
</#if>
</#macro>

<#macro input id labelName clickFun="" class="" value="" underline="false" readonly="" disabled="" type="text" hidden="" hiddenValue="" style="" selectFlag="false" selectId="" selectTreeId="" selectStyle="" placeholder="" required="false">
<div class="form-group">
    <label class="col-sm-4 control-label">
    	${labelName!}
    	<#if required=="true" >
		 	<span style="color:red">*</span>
		</#if>
    </label>
    <div class="col-sm-8">
        <input class="form-control ${class!}" id="${id!}" name="${id!}" type="${type!}" placeholder="${placeholder}"
            <#if value?? && value!="">
	        	 value="${value!}" 
	        </#if>
	        <#if style?? && style!="">
	        	 style="${style!}" 
	        </#if>
	        <#if clickFun?? && clickFun!="">
	        	onclick="${clickFun!}" 
	        </#if>
	        <#if readonly?? && readonly!="">
	        	 readonly="${readonly!}" 
	        </#if>
	       	<#if disabled?? && disabled!="">
	        	disabled="${disabled!}"
	        </#if>
        >
      	<#if selectFlag=="true" >
        	<div id="${selectId}" style="display: none; position: absolute; z-index: 200;">
                <ul id="${selectTreeId}" class="ztree tree-box" style="${selectStyle!}"></ul>
            </div>
        </#if>
    </div>
</div>
<#if underline=="true" >
 	<div class="hr-line-dashed"></div>
</#if>

<#if hidden?? && hidden!="">
    <input class="form-control" type="hidden" id="${hidden!}" name="${hidden!}" value="${hiddenValue!}">
</#if>
</#macro>


<#macro nameCon buttonValue id="" placeholder="">
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle"
                type="button">${buttonValue!}
        </button>
    </div>
    <input type="text" class="form-control" id="${id!}" placeholder="${placeholder!}" />
</div>
</#macro>

<#macro timeCon buttonValue id isTime pattern>
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle"
                type="button">${buttonValue!}
        </button>
    </div>
    <input type="text" class="form-control layer-date"
           onclick="laydate({istime: ${isTime!}, format: '${pattern!}'})" id="${id!}"/>
</div>
</#macro>

<#macro button value  class icon clickFun id="">
<button type="button" class="btn  ${class!}" onclick="${clickFun!}" id="${id!}">
    <i class="fa ${icon!}"></i>&nbsp;${value!}
</button>
</#macro>


<#macro label labelName  value="" underline="false" >
<div class="form-group">
    <label class="col-sm-4 control-label">
    	${labelName!}
    </label>
    <div class="col-sm-8">
        <label class="control-label">${value!}</label>
    </div>
</div>
<#if underline=="true" >
 	<div class="hr-line-dashed"></div>
</#if>
</#macro>