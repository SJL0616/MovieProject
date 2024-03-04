<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<link href="${ctx}/css/userMyMega.css" rel="stylesheet" type="text/css">
<script defer="defer" type="text/javascript"
	src="${ctx}/js/userMyMega.js"></script>
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
					<td class="sns_name">카카오</td>
					<c:if test="${user.getKakao() eq null }">
						<td class="connecting_info">연결된 계정정보가 없습니다.</td>
						<td class="connecting_btn_section"><button
								class="connect_to_kakao"
								onclick="location.href='https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.do'">연동</button></td>
					</c:if>
					<c:if test="${user.getKakao() ne null }">
						<td class="connecting_info">id : ${user.getKakao()}</td>
						<td class="connecting_btn_section"><button
								class="disconnect_to_kakao"
								onclick="location.href='${ctx}/disconnectToKakao.do'">해제</button></td>
					</c:if>
				</tr>
				<tr>
					<td class="sns_name">네이버</td>
					<c:if test="${user.getNaver() eq null }">
						<td class="connecting_info">연결된 계정정보가 없습니다.</td>
						<td class="connecting_btn_section"><button
								class="connect_to_naver"
								onclick="location.href='https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=SrIGsH8XIxmfND4_eTEH&state=STATE_STRING&redirect_uri=http://localhost:8085/MovieProject/naverLoginResult.do'">연동</button></td>
					</c:if>
					<c:if test="${user.getNaver() ne null }">
						<td class="connecting_info">id : ${user.getNaver()}</td>
						<td class="connecting_btn_section"><button
								class="disconnect_to_naver"
								onclick="location.href='${ctx}/disconnectToNaver.do'">해제</button></td>
					</c:if>
				</tr>
			</table>
		</div>

		<br> <br>

		<h2>개인정보 수정</h2>
		<div class="mymega_update">
			<form action="${ctx}/userUpdate.do" method="post" id="userUpdateForm">
				<table>
					<tr>
						<th scope="row"><label>아이디</label></th>
						<td>${user.getId()}</td>
					</tr>
					<tr>
						<th scope="row"><label>휴대폰</label> <em>*</em></th>
						<td><input type="text" id="inputPhone" name="inputPhone"
							placeholder="010-xxxx-xxxx" title="변경할 휴대폰 번호 입력" maxlength="13"
							value="${user.getPhone()}" required="required" /></td>
					</tr>
					<tr>
						<th scope="row"><label>이메일</label> <em>*</em></th>
						<td><input type="email" id="inputEmail" name="inputEmail"
							value="${user.getEmail()}" placeholder="xxxx@xxxx.xxx"
							required="required" /></td>
					</tr>
					<tr>
						<th scope="row">현재 비밀번호를 입력하세요.<em>*</em></th>
						<td><input type="password" id="currentPw" name="currentPw"
							placeholder="현재 비밀번호를 입력하세요." required="required" /></td>
					</tr>
					<tr>
						<th scope="row">새 비밀번호를 입력하세요.<em>*</em></th>
						<td><input type="password" id="newPw" name="newPw"
							placeholder="새 비밀번호를 입력하세요." required="required" /></td>
					</tr>
				</table>
				<br>
				<button id="updateBtn" type="button" onclick="validCheck(form)">현재
					비밀번호 확인</button>
			</form>
		</div>

		<br> <br>

		<h2>회원탈퇴</h2>
		<a>회원님의 비밀번호를 입력하시고 [탈퇴 비밀번호 확인] 버튼을 클릭해주세요.</a>
		<div class="mymega_resign">
			<form action="${ctx}/userResign.do" id="resignForm">
				<table>
					<tr>
						<th scope="row">비밀번호<em>*</em></th>
						<td><input type="password" id="resignPw"
							placeholder="비밀번호를 입력하세요." /></td>
					</tr>
				</table>
				<br>
				<button type="button" id="resignBtn"
					onclick="resignValidCheck(form)">탈퇴 비밀번호 확인</button>
			</form>
		</div>
	</div>
</div>
<br>
<%@include file="./footer.jsp"%>
