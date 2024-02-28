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
<title>4조 MEGABOX</title>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- slick.js -->
<script type="text/javascript"
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/header.css?after" />
<script type="text/javascript" src="${ctx}/js/header.js"></script>
</head>

<body>
	<header>
		<div class=header-content>
			<div class="ci" onclick="location.href='${ctx}/index.jsp'"
				title="MEGABOX 메인으로 가기">
				<a>MEGABOX : Life Theater</a>
			</div>
			<div class="top-of-header">
				<div class="left-links">
					<a href="#">VIP LOUNGE</a> <a href="#">멤버십</a> <a href="#">고객센터</a>
				</div>
				<div class="right-links">
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
					<a href="#">빠른예매</a>
				</div>
			</div>
			<div class="bottom-of-header">
				<div class="header-hamberger" title="사이트맵">
					<img
						src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-sitemap-white.png" />
				</div>
				<div class="header-magnifier" title="검색">
					<img
						src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-search-white.png" />
				</div>
				<div title="영화">영화</div>
				<div title="예매" onclick="location.href='${ctx}/ticketing.do'">예매</div>
				<div title="극장">극장</div>

				<div class="temp-division-for-ci-positioning"></div>

				<div title="이벤트">이벤트</div>
				<div title="스토어">스토어</div>
				<div title="혜택">혜택</div>
				<div title="상영시간표" class="header-calendar">
					<img
						src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-schedule-white.png" />
				</div>
				<div class="mymega" title="나의 메가박스" onclick="toMyMega()">
					<img
						src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-mymega-white.png" />
				</div>
			</div>
		</div>
	</header>