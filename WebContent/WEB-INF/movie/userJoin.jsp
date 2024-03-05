<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<link href="${ctx}/css/userJoin.css" rel="stylesheet" type="text/css">
<div align="center">
	<form id="userJoinForm" action="${ctx}/userJoin.do" method="post">
		<table>
			<tr id="joinTitle">
				<td colspan="2">회원가입</td>
			</tr>
			<tr>
				<th>아이디 <em>*</em></th>
				<td><input type="text" name="id" id="id" required="required" />
					<button id="checkId">중복확인</button></td>
			</tr>
			<tr>
				<th>패스워드 <em>*</em></th>
				<td><input type="password" name="pw" id="pw"
					required="required" /></td>
			</tr>
			<tr>
				<th>이메일 <em>*</em></th>
				<td><input type="text" name="email" id="email"
					required="required" /></td>
			</tr>
			<tr>
				<th>전화번호 <em>*</em></th>
				<td><input type="text" name="phone" id="phone"
					required="required" maxlength="13" /></td>
			</tr>
		</table>
		<br>
		<div align="center">
			<button type="reset" id="reset">취소</button>
			<button type="button" onclick="validCheck(form)">가입</button>
		</div>
		<br>
	</form>
</div>
<script src="${ctx}/js/userJoin.js"></script>
<%@include file="./footer.jsp"%>
