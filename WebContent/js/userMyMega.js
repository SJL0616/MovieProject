let check = false;
function validCheck(form) {
	if (!form.inputPhone.value.match(/010-\d{3,4}-\d{4}/)) {
		alert("전화번호 형식이 다릅니다.");
		form.inputPhone.value = "010-1234-1234";
		form.inputPhone.focus();
		return false;
	}
	if (!form.inputEmail.value.match(/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/)) {
		alert("이메일 형식이 다릅니다.");
		form.inputEmail.value = "test@test.com";
		form.inputEmail.focus();
		return false;
	}
	if (check == false) return false;
	form.submit();
}
document.getElementById("updateBtn").addEventListener("click", () => {
	const inputPw = document.getElementById("currentPw").value;
	fetch("userPwValidAjax.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "inputPw=" + inputPw,
	})
		.then(response => response.text())
		.then(getResult)
		.catch(error => console.log("error= ", error));
});
function getResult(data) {
	if (data === "valid") {
		check = true;
	} else if (data === "notValid") {
		document.getElementById("currentPw").focus();
		check = false;
	}
	console.log(check);
}
