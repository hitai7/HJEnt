<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="format-detection" content="telephone=no">
	<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
	<title>ImageResizing</title>
	
	<script src="${pageContext.request.contextPath}/resources/js/plugins/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/plugins/jquery.ae.image.resize.min.js"></script>
	
<%-- 	<link href="${pageContext.request.contextPath}/resources/css/portal.css" rel="stylesheet" /> --%>
	
	<script>
    	$(function() {
      		$( ".resizeme" ).aeImageResize({ height: 250, width: 250 });
    	});
	</script>

</head>
<body>

	<h5>Original</h5>
    <img src="${pageContext.request.contextPath}/resources/image/resize/samplePhoto.jpg">
	<h5>Resizing</h5>
	<img class="resizeme" src="${pageContext.request.contextPath}/resources/image/resize/samplePhoto.jpg">

</body>
</html>
