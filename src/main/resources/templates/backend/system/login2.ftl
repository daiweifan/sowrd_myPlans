<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>Sword 后台管理系统 - 登录</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="${u.ctx}/favicon.ico"> <link href="${u.ctx}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${u.ctx}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${u.ctx}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${u.ctx}/css/animate.css" rel="stylesheet">
    <link href="${u.ctx}/css/style.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎使用 H+</h3>

            <form id="loginForm" class="m-t" role="form" action="${u.ctx}/backend/system/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="密码" required="">
                </div>
                <button id="loginBtn" type="button" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="${u.ctx}/backend/system/register">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
    <script src="${u.ctx}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${u.ctx}/js/bootstrap.min.js?v=3.3.6"></script>
	<script type="text/javascript">
		$(function(){
		 document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0]         
             if(e && e.keyCode==13){ // enter 键
                 $("#loginBtn").click();
            }
        }; 
			$("#loginBtn").click(function(){
				var data = $("#loginForm").serialize();
				console.info(data);
				$.ajax({
					type: "POST",
				    url: "${u.ctx}/backend/system/loginPost",
				    data: data,
				    dataType: "json",
				    success: function(result){
				    	console.info(result);
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
</body>
</html>
