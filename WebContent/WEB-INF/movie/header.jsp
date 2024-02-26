<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE html>
<html>

<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<meta charset="UTF-8">
<title>header</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- slick.js -->
<script type="text/javascript"
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/header.css" />

<%-- <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" /> --%>
</head>

<body>
	<c:set var="log" value="${sessionScope.log}" />
	<script>
		console.log("${log}");
	</script>
	<header>
		<div class=header-content>
			<div class="top-of-header">
				<div class="left-link">
					<c:if test="${log ne null and log  ne 'admin'}">
						<a href="${ctx}/myReserve.do?id=${log}">내 예매</a>
						<a href="#" onclick="resign()">회원탈퇴</a>
						<a href="${ctx}/logout.do">로그아웃</a>
					</c:if>
					<c:if test="${log  eq 'admin'}">
						<a href="${ctx}/register.do?form=true">등록</a>
						<a href="${ctx}/logout.do">로그아웃</a>
					</c:if>
					<c:if test="${empty log  }">
						<a href="${ctx}/login.do">로그인</a>
						<a href="${ctx}/userJoin.do">회원가입</a>
					</c:if>
				</div>
			</div>
			<div class="bottom-of-header">
				<div>영화</div>
				<div>예매</div>
				<div class="ci">
					<a href="/" title="MEGABOX 메인으로 가기">MEGABOX : Life Theater</a>
				</div>
				<div>극장</div>
				<div>
					<a href="#" class="mymega" title="나의 메가박스">나의 메가박스</a>
				</div>
			</div>
		</div>
	</header>