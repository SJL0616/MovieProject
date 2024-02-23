<!-- 
 작성자 : 서원우
 내용 : 로그인 페이지입니다.
 최초 작성일 : 24-02-22
 마지막 수정일 : 24-02-22 (서원우)
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<style>
.loginPage {
	border: 1px solid red;
	display: flex;
	justify-content: center;
}

.loginForm {
	border: 2px solid green;
	width: 850px;
	height: 556px;
}

.loginForm .title {
	width: 835px;
	height: 45px;
	background-color: #503396;
	color: white;
	padding-left: 15px;
	font-size: 18px;
	font-weight: bold;
	display: flex;
	align-items: center;
}

.loginForm .formBody {
	width: 100%;
	height: 100%;
	display: flex;
}

.loginForm .leftSection {
	width: 100%;
	height: calc(100% - 45px);
	flex: 1;
	display: flex;
	flex-direction: column;
}

.loginForm .rightSection {
	background-color: gold;
	width: 100%;
	height: calc(100% - 45px);
	flex: 1;
}

.leftSection .realForm {
	background-color: navy;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	flex: 6;
	padding: 25px;
	margin: 0;
}

.leftSection .otherWays {
	background-color: red;
	height: 100%;
	flex: 4;
}

#id {
	height: 50px;
}

#pw {
	height: 50px;
}

.APIs {
	display: flex;
	flex-direction: column;
	align-content: center;
}
</style>

<div class="loginPage">
	<div class="loginForm">
		<div class="title">로그인</div>
		<div class="formBody">
			<div class="leftSection">
				<form class="realForm" action="${ctx}/login.do" method="post">
					<input id="id" maxlength="20" type="text" placeholder="아이디"
						required="required" /> <input id="pw" maxlength="20"
						type="password" placeholder="비밀번호" required="required" /> <br>
					<button type="button" onclick="validCheck(form)">로그인</button>
				</form>
				<div class=" otherWays">
					<div>
						<button>회원가입</button>
					</div>
					<div class="APIs">
						<a id="kakao-login-btn"
							href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.jsp">
							<img
							src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
							width="200" alt="카카오 로그인 버튼" />
						</a>
						<button>네이버</button>
						<button>애플</button>
						<button>구글</button>
					</div>
				</div>
			</div>
			<div class="rightSection"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
			function validCheck(form) {
				if (!form.id.value.trim()) {
					alert(`아이디를 입력하세요.`);
					form.id.focus();
					return false;
				}
				if (!form.pw.value.trim()) {
					alert("패스워드를 입력하세요.");
					form.pw.focus();
					return false;
				}
				let id = document.getElementById(`id`).value.trim();
				let pw = document.getElementById(`pw`).value.trim();
				fetch("login.do", {
					method: "POST",
					headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
					body: "id=" + id + "&" + "pw=" + pw,
				})
					.then(response => response.text())
					.then(getResult)
					.catch(error => console.log("error : ", error))
			}
			function getResult(data) {
				if (data === "valid") {
					location.href = "${ctx}/index.jsp"
					alert("로그인 성공.");
				} else if (data === "notValid") {
					alert("아이디와 비밀번호를 확인하세요.");
				}
			}
		</script>