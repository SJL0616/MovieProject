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
