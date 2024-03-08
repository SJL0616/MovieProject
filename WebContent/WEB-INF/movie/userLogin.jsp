<!-- 
 작성자 : 서원우
 내용 : 로그인 페이지입니다.
 최초 작성일 : 24-02-22
 마지막 수정일 : 24-02-22 (서원우)
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<link href="${ctx}/css/userLogin.css" rel="stylesheet" type="text/css">
<div class="loginPage">
	<div class="loginForm">
		<div class="title">로그인</div>
		<div class="formBody">
			<div class="leftSection">
				<form class="realForm" id="realForm" action="${ctx}/login.do"
					method="post">
					<input id="id" maxlength="20" type="text" placeholder="아이디"
						required="required" /> <input id="pw" maxlength="20"
						type="password" placeholder="비밀번호" required="required" /> <br>
					<button id="loginBtn" type="button" onclick="validCheck(form)">로그인</button>
				</form>
				<div class=" otherWays">
					<div class="link">
						<a href="#" title="ID/PW 찾기 선택">ID/PW 찾기<!--ID/PW 찾기--></a> <a
							href="${ctx}/userJoin.do" title="회원가입 선택" id="userJoinBtn">회원가입<!--회원가입--></a>
						<a href="#" title="비회원 예매확인 선택">비회원 예매확인<!--비회원 예매확인--></a>
					</div>
					<a id="kakao-login-btn"
						href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.do">
						<img
						src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
						width="200" height="50" alt="카카오 로그인 버튼" />
					</a> <a id="naver-login-btn"
						href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=SrIGsH8XIxmfND4_eTEH&state=STATE_STRING&redirect_uri=http://localhost:8085/MovieProject/naverLoginResult.do">
						<img src="./img/naver_login.png" width="200" height="50"
						alt="네이버 로그인 버튼" />
					</a>
				</div>
			</div>
			<div class="rightSection">
				<img alt="광고 이미지" src="${ctx}/img/login_page_advertisement.jpg">
			</div>
		</div>
	</div>
</div>

<script src="${ctx}/js/userLogin.js"></script>
<%@include file="./footer.jsp"%>

