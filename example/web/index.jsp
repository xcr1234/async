<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title></title>
  </head>
  <body>
  <p>测试正常的消息：<a href="test1" target="_blank">测试</a></p>
  <p>测试异常：<a href="test2" target="_blank">测试</a></p>
  <p>测试超时：<a href="test3" target="_blank">测试</a></p>
  <p>测试异常2：<a href="test4" target="_blank">测试</a></p>
  <p>百度：<a href="baidu" target="_blank">测试百度</a></p>
  <p>测试跳转：<a href="dispatch" target="_blank">测试</a></p>
  <p>&nbsp;</p>
  <p>测试用户输入：</p>
  <form action="input.do" method="get" target="_blank">
  <p>用户名：<input type="text" name="user"></p>
  <p><input type="submit" value="提交"></p>
  </form>
  </body>
</html>
