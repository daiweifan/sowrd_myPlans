<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sword后台管理系统</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${u.ctx}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${u.ctx}/css/font-awesome.min.css">
		<link rel="stylesheet" href="${u.ctx}/css/form-elements.css">
        <link rel="stylesheet" href="${u.ctx}/css/logon.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="${u.ctx}/favicon.ico">

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>Sword</strong>后台管理系统</h1>
                            <div class="description">
                            	<p>
	                            	<strong>方便、</strong> <strong>快捷、</strong><strong>易用、</strong><strong>高效</strong>
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>欢迎使用！</h3>
                            		<p>Enter your username and password to log on:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-lock"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" id="loginForm" action="" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Username</label>
			                        	<input type="text" name="username" placeholder="用户名" class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Password</label>
			                        	<input type="password" name="password" placeholder="密码" class="form-password form-control" id="form-password">
			                        </div>
			                         <div class="checkbox">
									    <label>
									      <input type="checkbox" style="margin-top: 10px;"> 记住密码
									    </label>
									  </div>
			                        <button type="button" class="btn" id="loginBtn" >登录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	<h4> ——  其他登录方式   ——</h4>
                        	<div class="social-login-buttons">
	                        	<a class="btn btn-link-2" href="#">
	                        		<i class="fa fa-qq"></i> 
	                        	</a>
	                        	<a class="btn btn-link-2" href="#">
	                        		<i class="fa fa-wechat"></i> 
	                        	</a>
	                        	<a class="btn btn-link-2" href="#">
	                        		<i class="fa fa-weibo"></i> 
	                        	</a>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="${u.ctx}/js/jquery.min.js"></script>
        <script src="${u.ctx}/js/bootstrap.min.js"></script>
        <script src="${u.ctx}/js/jquery.backstretch.min.js"></script>
        <script type="text/javascript">
		$(function(){
		
      		 $.backstretch("${u.ctx}/img/logon_background.jpg");
			 document.onkeydown=function(event){
	            var e = event || window.event || arguments.callee.caller.arguments[0]         
	             if(e && e.keyCode==13){ // enter 键
	                 $("#loginBtn").click();
	            }
	        }; 
	        
	        
	        
	        $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
		    	$(this).removeClass('input-error');
		    });
			$("#loginBtn").click(function(){
				  	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
			    		if( $(this).val() == "" ) {
			    			e.preventDefault();
			    			$(this).addClass('input-error');
			    		}
			    		else {
			    			$(this).removeClass('input-error');
			    		}
			    	});
			
				var data = $("#loginForm").serialize();
				$.ajax({
					type: "POST",
				    url: "${u.ctx}/backend/system/loginPost",
				    data: data,
				    dataType: "json",
				    success: function(result){
						if(result.code == 1){//登录成功
							window.location.href ='${u.ctx}/backend/system/dashboard';
						}else{
							alert("用户名密码不正确!");
						}
					}
				});
			});
		});
		
		</script>
        
        <!--[if lt IE 10]>
            <script src="${u.ctx}/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>