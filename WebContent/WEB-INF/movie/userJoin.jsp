<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<link href="${ctx}/css/userJoin.css" rel="stylesheet" type="text/css">
<div align="center">
	<h1 class="py-3">회원 가입</h1>
	<form action="${ctx}/userJoin.do" method="post">
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id" required="required" />
					<input type="button" value="중복체크" id="checkId"></td>
			</tr>
			<tr align="center">
				<td>패스워드</td>
				<td><input type="password" name="pw" id="pw"
					required="required" /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" id="email" /></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" id="phone" /></td>
			</tr>
			<tr>
				<td align="center"><input type="button" value="가입"
					onclick="validCheck(form)" /> <input type="reset" value="취소" /></td>
			</tr>
		</table>
	</form>
</div>
<script src="${ctx}/js/userJoin.js"></script>
<%@include file="./footer.jsp"%>
