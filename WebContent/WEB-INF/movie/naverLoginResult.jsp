<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<script>
	displayCode()
	function displayCode() {
		const code = new URL(window.location.href).searchParams.get("code");
		console.log("code", code);
		fetch("naverLogin.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body: "code=" + code,
		})
			.then(response => response.text())
			.then(getResult)
			.catch(error => console.log("error= ", error));
	}
	function getResult(data) {
		if (data === "valid") {
			location.href="${ctx}/main.do";
			alert("로그인 성공");
		} else if (data === "notValid") {
			location.href="${ctx}/login.do";
			alert("네이버와 연동된 유저가 없습니다.");
		} else if (data === "connected") {
			location.href = "${ctx}/userMyMega.do";
			alert("네이버 연동에 성공했습니다.");
		}
	}
</script>
<%@include file="./footer.jsp"%>
