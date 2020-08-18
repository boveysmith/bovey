<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>后台管理系统</title>
  <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">后台管理</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
      <li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
      <li class="layui-nav-item"><a href="javascript:;">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <@shiro.user>
        <li class="layui-nav-item">

            <a href="javascript:;">
              <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img">
              <@shiro.principal/>
            </a>
            <dl class="layui-nav-child">
              <dd><a href="">基本资料</a></dd>
              <dd><a href="">安全设置</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="/user/logout">退出</a></li>
      </@shiro.user>
      <@shiro.guest>
        请<lable style="color: whitel;text-decoration:underline" onclik="javascript:location.href='login';"> 登录</lable>
      </@shiro.guest>
    </ul>
  </div>

  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">

        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">仓库管理</a>
          <dl class="layui-nav-child">
            <@shiro.hasPermission name="sys:c:save"><dd><a href="/c_add" target="mainFrame">入库</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:c:delete"><dd><a href="javascript:;">出库</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:c:update"><dd><a href="javascript:;">修改</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:c:find"><dd><a href="javascript:;">查询</a></dd></@shiro.hasPermission>
          </dl>
        </li>

        <li class="layui-nav-item">
          <a href="javascript:;">订单管理</a>
          <dl class="layui-nav-child">
            <@shiro.hasPermission name="sys:x:save"><dd><a href="javascript:;">新增订单</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:x:delete"><dd><a href="javascript:;">删除订单</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:x:update"><dd><a href="javascript:;">修改订单</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:x:find"><dd><a href="javascript:;">查询订单</a></dd></@shiro.hasPermission>
          </dl>
        </li>

        <li class="layui-nav-item">
          <a href="javascript:;">客户管理</a>
          <dl class="layui-nav-child">
            <@shiro.hasPermission name="sys:k:save"><dd><a href="javascript:;">新增客户</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:k:delete"><dd><a href="javascript:;">删除客户</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:k:update"><dd><a href="javascript:;">修改客户</a></dd></@shiro.hasPermission>
            <@shiro.hasPermission name="sys:k:find"><dd><a href="javascript:;">查询客户</a></dd></@shiro.hasPermission>
          </dl>
        </li>

      </ul>
    </div>
  </div>

  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
<#--      内容主体区域-->
      <iframe id="mainFrame" name="mainFrame" width="100%" height="788" ></iframe>
    </div>
  </div>

  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});

</script>
</body>
</html>