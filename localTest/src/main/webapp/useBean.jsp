<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="memberInfo" class="com.hyo.smart.domain.MemberInfoDTO" scope="page"/>
<jsp:setProperty name="memberInfo" property="note" value="property사용하기"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Using JavaBean Test</title>
</head>
<body>
	
	<h1>Using JavaBean</h1>
	<% memberInfo.setName("나윤찬"); %>
	<div><%= memberInfo.getName() %></div>
	<jsp:getProperty name="memberInfo" property="note"/>

</body>
</html>
