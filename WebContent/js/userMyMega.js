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
	if (form.currentPw.value.trim() == "") {
		alert("현재 비밀번호를 입력하세요.");
		form.currentPw.value = null;
		form.currentPw.focus();
		return false;
	}
	if (form.newPw.value.trim() == "") {
		alert("새 비밀번호를 입력하세요.");
		form.newPw.value = null;
		form.newPw.focus();
		return false;
	}
	if (check == false) return false;
	form.submit();
	alert(`개인벙보를 수정했습니다.`);
}

document.getElementById("updateBtn").addEventListener("click", () => {
	const currentPw = document.getElementById("currentPw").value;
	fetch("userPwValidAjax.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "currentPw=" + currentPw,
	})
		.then(response => response.text())
		.then(getResult)
		.catch(error => console.log("error= ", error));
});

function getResult(data) {
	if (data === "valid") {
		const updateBtn = document.getElementById("updateBtn");
		updateBtn.innerHTML = "수정";
		check = true;
	} else if (data === "invalid") {
		document.getElementById("currentPw").focus();
		const userUpdateForm = document.getElementById(`userUpdateForm`);
		let $p = document.createElement("p");
		$p.classList.add("pwInvalidAlert");
		$p.innerHTML = `현재 비밀번호가 일치하지 않습니다.`;
		userUpdateForm.appendChild($p);
		setTimeout(() => {
			$p.remove();
		}, 2000);
		check = false;
	}
	console.log(check);
}

// 회원 탈퇴 비밀번호 체크
let resignCheck = false;

function resignValidCheck(form) {
	if (form.resignPw.value.trim() == "") {
		alert("비밀번호를 입력하세요.");
		form.resignPw.value = null;
		form.resignPw.focus();
		return false;
	}
	if (resignCheck == false) return false;
	form.submit();
}

const resignBtn = document.getElementById("resignBtn");
if (resignBtn != null) {
	resignBtn.addEventListener("click", () => {
		const resignPw = document.getElementById("resignPw").value;
		fetch("userResignValidAjax.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body: "resignPw=" + resignPw,
		})
			.then(response => response.text())
			.then(getResignResult)
			.catch(error => console.log("error= ", error));
	})
}

function getResignResult(data) {
	if (data === `resignValid`) {
		const updateBtn = document.getElementById("resignBtn");
		updateBtn.innerHTML = "탈퇴";
		resignCheck = true;
	} else if (data === "resignInvalid") {
		document.getElementById("resignPw").focus();
		const resignForm = document.getElementById(`resignForm`);
		let $p = document.createElement("p");
		$p.classList.add("pwInvalidAlert");
		$p.innerHTML = `비밀번호가 일치하지 않습니다.`;
		resignForm.appendChild($p);
		setTimeout(() => {
			$p.remove();
		}, 2000);
		resignCheck = false;
	}
}

function cancelCheck(code) {
	const cancelModal = document.querySelector(".cancel_modal");
	const cancelOverlay = document.querySelector(".cancel_overlay");
	const cancelBtn = document.querySelector(".cancelBtn");
	cancelModal.classList.add("active");
	cancelOverlay.classList.add("active");

	document.querySelector(".btn-modal-close").addEventListener("click", () => {
		cancelModal.classList.remove("active");
		cancelOverlay.classList.remove("active");
	});
	
	cancelBtn.addEventListener(`click`, () => {
		cancelModal.classList.remove("active");
		cancelOverlay.classList.remove("active");
		fetch("reserveCancel.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body: "ticketCode=" + code,
		})
			.then(response => response.text())
			.then(getCancelResult)
			.catch(error => console.log("error= ", error));
	});
}

function getCancelResult(data) {
	if (data === `canceled`) {
		window.location.reload();
	} else if (data === "notCanceled") {
		alert("예매취소에 실패했습니다.")
	}
}
