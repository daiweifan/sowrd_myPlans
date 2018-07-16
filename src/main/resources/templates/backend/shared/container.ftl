<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    
    <!-- 全局css -->
    <link rel="shortcut icon" href="${u.ctx}/favicon.ico">
    <link href="${u.ctx}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${u.ctx}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${u.ctx}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${u.ctx}/css/plugins/validate/bootstrapValidator.min.css" rel="stylesheet">
    <link href="${u.ctx}/css/animate.css" rel="stylesheet">
    <link href="${u.ctx}/css/style.css?v=4.1.0" rel="stylesheet">
    <!-- Morris -->
    <link href="${u.ctx}/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <!-- Gritter -->
    <link href="${u.ctx}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <!--通用css 工具-->
    <link href="${u.ctx}/css/utils.min.css" rel="stylesheet">
    <link href="${u.ctx}/css/_fstyle.css" rel="stylesheet">
    <link href="${u.ctx}/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${u.ctx}/css/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${u.ctx}/css/plugins/ztree/zTreeStyle.css" rel="stylesheet">
    <link href="${u.ctx}/css/plugins/jquery-treegrid/css/jquery.treegrid.css" rel="stylesheet"/>
    <!-- <link href="${u.ctx}/css/plugins/ztree/demo.css" rel="stylesheet"> -->
	<link href="${u.ctx}/css/plugins/fileinput/fileinput.min.css" rel="stylesheet">
    <!-- 全局js -->
    <script src="${u.ctx}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${u.ctx}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${u.ctx}/js/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script src="${u.ctx}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${u.ctx}/js/plugins/validate/bootstrapValidator.min.js"></script>
    <script src="${u.ctx}/js/plugins/validate/zh_CN.js"></script>
    <script src="${u.ctx}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${u.ctx}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${u.ctx}/js/plugins/jquery-treegrid/js/jquery.treegrid.min.js"></script>
    <script src="${u.ctx}/js/plugins/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
    <script src="${u.ctx}/js/plugins/jquery-treegrid/extension/jquery.treegrid.extension.js"></script>
    <script src="${u.ctx}/js/plugins/layer/layer.min.js"></script>
    <script src="${u.ctx}/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${u.ctx}/js/plugins/layer/laydate/laydate.js"></script>
    <script src="${u.ctx}/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="${u.ctx}/js/plugins/fileinput/fileinput.min.js"></script>
    <script src="${u.ctx}/js/plugins/fileinput/zh.js"></script>
    <script src="${u.ctx}/js/common/bootstrap-table-object.js"></script>
    <script src="${u.ctx}/js/common/tree-table-object.js"></script>
    <script src="${u.ctx}/js/common/web-upload-object.js"></script>
    <script src="${u.ctx}/js/common/ztree-object.js"></script>
    <script src="${u.ctx}/js/plugins/easypiechart/jquery.easypiechart.js"></script>
    <script src="${u.ctx}/js/common/Feng.js"></script>
    <script type="text/javascript">
        Feng.addCtx("${u.ctx}");
        Feng.sessionTimeoutRegistry();
        var ctx ="${u.ctx}";
    </script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	${contentBody}
     </div>


</body>


</html>
