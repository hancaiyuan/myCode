<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看商品列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
$(function(){

	var params = '{"id": 1,"name": "测试商品","price": 99.9,"detail": "测试商品描述","pic": "123456.jpg"}';

	$.ajax({
		url : "${pageContext.request.contextPath }/json.action",
		data : params,
		contentType : "application/json;charset=UTF-8",//发送数据的格式
		type : "post",
		dataType : "json",//回调的数据类型
		success : function(data){
			alert(data.name);
		}
		
	});
});
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/queryitem.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>商品名称:<input type="text" name="name" value=""/>&nbsp;&nbsp;<input type="submit" value="查询"/></td>
</tr>
</table>
</form>
<!-- 当前是批量更新代码，被注释掉的代码是批量删除代码 -->
<!-- 批量更新代码name="list[${s.index }].id"   
	list是包装类的属性名,
	${s.index }获取当前行的行号,从0开始
	list[${s.index }]表示list集合中的某个元素
	list[${s.index }].id表示list集合中的某个元素的id属性
	 -->
<%-- <form action="${pageContext.request.contextPath }/deleteitem.action" method="post"> --%>
<form action="${pageContext.request.contextPath }/batchupdateitem.action" method="post">
商品列表：
<table width="100%" border=1>
<tr>
<!-- 	<td><input type="checkbox" name="all"/></td> -->
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<%-- <c:forEach items="${items }" var="item"> --%>
<c:forEach items="${items }" var="item" varStatus="s">
<input type="hidden" name="list[${s.index }].id" value="${item.id }"/>
<tr>
<%-- 	<td><input type="checkbox" name="ids" value="${item.id }"/></td>
	<td>${item.name }</td>
	<td>${item.price }</td> --%>
	<td><input type="text" name="list[${s.index }].name" value="${item.name }"/></td>
	<td><input type="text" name="list[${s.index }].price" value="${item.price }"/></td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${item.detail }</td>
	
	<td><a href="${pageContext.request.contextPath }/itemEdit.action?id=${item.id}">修改</a></td>
</tr>
</c:forEach>

</table>
<!-- <input type="submit" value="批量删除"/> -->
<input type="submit" value="批量更新"/>
</form>
</body>

</html>