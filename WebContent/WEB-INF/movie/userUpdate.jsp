<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@include file="./header.jsp" %>
		<div class="user-update" align="center">
			<h1>개인정보 수정</h1>
			<table border="1">
				<tr>
					<th scope="row"><label>휴대폰</label> <em>*</em></th>
					<td>
						<p>${user.getPhone()}</p>
						<label>변경할 휴대폰 : </label>
						<input type="text" id="chPhone" placeholder="010-xxxx-xxxx" title="변경할 휴대폰 번호 입력" maxlength="11">
						<button type="button" id="phoneChgBtn" title="휴대폰번호 변경">휴대폰번호 변경</button>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="email">이메일</label> <em class="font-orange">*</em></th>
					<td><input type="text" id="email" name="mbEmail" class="input-text w500px" value="${user.getEmail()}"></td>
				</tr>
				<tr>
					<th scope="row">비밀번호 <em class="font-orange">*</em></th>
					<td><a href="/on/oh/ohh/Mypage/userPwdChangePage.do" class="button small gray-line" title="비밀번호 변경">비밀번호
							변경</a> 마지막 비밀번호
						변경: 17시간전에 함 (2024-02-26 17:15:21)</td>
				</tr>
			</table>
		</div>
		