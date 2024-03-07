<!-- 
 작성자 : 서원우
 내용 : 카카오API로부터 받아온 인가코드를 서버로 전달하기위한 페이지 입니다.
 최초 작성일 : 24-02-23
 마지막 수정일 : 24-02-23 (서원우)
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<script>
			displayCode()
			function displayCode() {
				const code = new URL(window.location.href).searchParams.get("code");
				fetch("kakaoLogin.do", {
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
					location.href="${ctx}/index.jsp"
				} else if (data === "notValid") {
					location.href="${ctx}/login.do"
					alert("카카오와 연동된 유저가 없습니다.");
				} else if (data === "connected"){
					location.href="${ctx}/userMyMega.do"
					alert("카카오 연동에 성공했습니다.");
				}
			}
		</script>
<%@include file="./footer.jsp"%>
