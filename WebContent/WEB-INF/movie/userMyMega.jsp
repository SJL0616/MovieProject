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
				<tr>
					<th scope="row"><label>휴대폰</label> <em>*</em></th>
					<td>
						<p>${user.getPhone()}</p> <label>변경할 휴대폰 : </label> <input
						type="text" id="chPhone" placeholder="010-xxxx-xxxx"
						title="변경할 휴대폰 번호 입력" maxlength="11">
						<button type="button" id="phoneChgBtn" title="휴대폰번호 변경">휴대폰번호
							변경</button>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="email">이메일</label> <em
						class="font-orange">*</em></th>
					<td><input type="text" id="email" name="mbEmail"
						class="input-text w500px" value="${user.getEmail()}"></td>
				</tr>
				<tr>
					<th scope="row">비밀번호 <em class="font-orange">*</em></th>
					<td><a href="/on/oh/ohh/Mypage/userPwdChangePage.do"
						class="button small gray-line" title="비밀번호 변경">비밀번호 변경</a> 마지막
						비밀번호 변경: 17시간전에 함 (2024-02-26 17:15:21)</td>
				</tr>
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
						<td><button class="connect_to_kakao" onclick="location.href='https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.jsp'">연동</button></td>
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
						<td><button class="connect_to_naver">연동</button></td>
					</c:if>
					<c:if test="${user.getNaver() ne null }">
						<td>id : ${user.getNaver()}</td>
						<td><button class="disconnect_to_naver">해제</button></td>
					</c:if>
				</tr>
			</table>
		</div>

		<br> <br>

		<h2>개인정보</h2>
		<div class="mymega_info">
			<table border="1">
				<tr>
					<th scope="row"><label>휴대폰</label> <em>*</em></th>
					<td>
						<p>${user.getPhone()}</p> <label>변경할 휴대폰 : </label> <input
						type="text" id="chPhone" placeholder="010-xxxx-xxxx"
						title="변경할 휴대폰 번호 입력" maxlength="11">
						<button type="button" id="phoneChgBtn" title="휴대폰번호 변경">휴대폰번호
							변경</button>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="email">이메일</label> <em
						class="font-orange">*</em></th>
					<td><input type="text" id="email" name="mbEmail"
						class="input-text w500px" value="${user.getEmail()}"></td>
				</tr>
				<tr>
					<th scope="row">비밀번호 <em class="font-orange">*</em></th>
					<td><a href="/on/oh/ohh/Mypage/userPwdChangePage.do"
						class="button small gray-line" title="비밀번호 변경">비밀번호 변경</a> 마지막
						비밀번호 변경: 17시간전에 함 (2024-02-26 17:15:21)</td>
				</tr>
			</table>
		</div>

		<br> <br>

		<h2>회원탈퇴</h2>
		<div class="mymega_resign">
			<table border="1">
				<tr>
					<th scope="row"><label>휴대폰</label> <em>*</em></th>
					<td>
						<p>${user.getPhone()}</p> <label>변경할 휴대폰 : </label> <input
						type="text" id="chPhone" placeholder="010-xxxx-xxxx"
						title="변경할 휴대폰 번호 입력" maxlength="11">
						<button type="button" id="phoneChgBtn" title="휴대폰번호 변경">휴대폰번호
							변경</button>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="email">이메일</label> <em
						class="font-orange">*</em></th>
					<td><input type="text" id="email" name="mbEmail"
						class="input-text w500px" value="${user.getEmail()}"></td>
				</tr>
				<tr>
					<th scope="row">비밀번호 <em class="font-orange">*</em></th>
					<td><a href="/on/oh/ohh/Mypage/userPwdChangePage.do"
						class="button small gray-line" title="비밀번호 변경">비밀번호 변경</a> 마지막
						비밀번호 변경: 17시간전에 함 (2024-02-26 17:15:21)</td>
				</tr>
			</table>
		</div>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br>

	</div>
</div>
