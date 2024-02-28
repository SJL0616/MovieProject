<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<link href="${ctx}/css/userMyMega.css" rel="stylesheet" type="text/css">
<div align="center">
	<div class="mymega">
		<h1>나의 메가박스</h1>

		<h2>예매 내역</h2>
		<div class="mymega_reservation">
			<table border="1">
			</table>
		</div>

		<br> <br>

		<h2>간편로그인 계정연동</h2>
		<div class="mymega_linked_login">
			<table>
				<tr>
					<th>구분</th>
					<th>연동정보</th>
					<th>연결</th>
				</tr>
				<tr>
					<td>카카오</td>
					<c:if test="${user.getKakao() eq null }">
						<td>연결된 계정정보가 없습니다.</td>
						<td><button class="connect_to_kakao"
								onclick="location.href='https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.do'">연동</button></td>
					</c:if>
					<c:if test="${user.getKakao() ne null }">
						<td>id : ${user.getKakao()}</td>
						<td><button class="disconnect_to_kakao"
								onclick="location.href='${ctx}/disconnectToKakao.do'">해제</button></td>
					</c:if>
				</tr>
				<tr>
					<td>네이버</td>
					<c:if test="${user.getNaver() eq null }">
						<td>연결된 계정정보가 없습니다.</td>
						<td><button class="connect_to_naver"
								onclick="location.href='https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=SrIGsH8XIxmfND4_eTEH&state=STATE_STRING&redirect_uri=http://localhost:8085/MovieProject/naverLoginResult.do'">연동</button></td>
					</c:if>
					<c:if test="${user.getNaver() ne null }">
						<td>id : ${user.getNaver()}</td>
						<td><button class="disconnect_to_naver"
								onclick="location.href='${ctx}/disconnectToNaver.do'">해제</button></td>
					</c:if>
				</tr>
			</table>
		</div>

		<br> <br>

		<h2>개인정보 수정</h2>
		<div class="mymega_update">
			<form action="${ctx}/userUpdate.do" method="post">
				<table>
					<tr>
						<th scope="row"><label>아이디</label> <em>*</em></th>
						<td>${user.getId()}</td>
					</tr>
					<tr>
						<th scope="row"><label>휴대폰</label> <em>*</em></th>
						<td><input type="text" name="inputPhone"
							placeholder="010-xxxx-xxxx" title="변경할 휴대폰 번호 입력" maxlength="11"
							value="${user.getPhone()}" required="required" /></td>
					</tr>
					<tr>
						<th scope="row"><label>이메일</label> <em>*</em></th>
						<td><input type="email" name="inputEmail"
							value="${user.getEmail()}" placeholder="xxxx@xxxx.xxx"
							required="required" /></td>
					</tr>
					<tr>
						<th scope="row">현재 비밀번호를 입력하세요.<em>*</em></th>
						<td><input type="password" name="inputPw"
							placeholder="현재 비밀번호를 입력하세요." required="required" /></td>
					</tr>
					<tr>
						<th scope="row">변경할 비밀번호를 입력하세요.<em>*</em></th>
						<td><input type="password" name="inputPw"
							placeholder="변경할 비밀번호를 입력하세요." required="required" /></td>
					</tr>
				</table>
				<button>수정</button>
			</form>
		</div>

		<br> <br>

		<h2>회원탈퇴</h2>
		<div class="mymega_resign">
			<table border="1">
			</table>
		</div>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br>

	</div>
</div>
