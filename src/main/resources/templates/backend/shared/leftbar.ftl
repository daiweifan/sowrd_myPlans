<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span><img alt="image" style="height:140px;" class="img-circle img-responsive" src="${avatar}" /></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear">
                       <span class="block m-t-xs"><strong class="font-bold">	
                       </strong></span>
                        <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                        </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="${u.ctx}/backend/system/user/show">个人资料</a>
                        </li>
                        <li><a class="J_menuItem" href="${u.ctx}/backend/system/user/changePwd">修改密码</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${u.ctx}/backend/system/logout">安全退出</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">H+
                </div>
            </li>
             <#list menus as menu>
             	 <#if menu.childrens?? && (menu.childrens?size > 0) >
             	 		<li>
	 					   <a href="#">
			                    <i class="fa ${menu.icon!}"></i>
			                    <span class="nav-label">${menu.name}</span>
			                    <span class="fa arrow"></span>
			                </a>
			                <ul class="nav nav-second-level">
			               		<#list menu.childrens as secondMenu>
			               			<#if secondMenu.childrens?? && (secondMenu.childrens?size > 0) >
			               				<li>
				               				 <a href="#">
							                    <i class="fa ${secondMenu.icon!}"></i>
							                    <span class="nav-label">${secondMenu.name}</span>
							                    <span class="fa arrow"></span>
							                </a>
							                <ul class="nav nav-third-level">
							                	<#list secondMenu.childrens as thirdMenu>
							                		<li>
										                <a class="J_menuItem" href="${u.ctx}${thirdMenu.url}"><i class="fa ${thirdMenu.icon!}"></i> <span class="nav-label">${thirdMenu.name}</span></a>
										            </li>
							                	</#list>
							                </ul>
						                </li>
			               			<#else>
			               				<li>
							                <a class="J_menuItem" href="${u.ctx}${secondMenu.url}"><i class="fa ${secondMenu.icon!}"></i> <span class="nav-label">${secondMenu.name}</span></a>
							            </li>
			               			</#if>
			               		</#list>
			                </ul>
			           </li>
 				 <#else>
	            	<li>
		                <a class="J_menuItem" href="${u.ctx}${menu.url}"><i class="fa ${menu.icon!}"></i> <span class="nav-label">${menu.name}</span></a>
		            </li>
                </#if>
            </#list> 
        </ul>
    </div>
</nav>