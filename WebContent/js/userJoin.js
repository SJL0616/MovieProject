let check = 0;
// 입력값들의 유효성 체크
function validCheck(form) {
  if (!form.id.value.trim()) {
    alert(`아이디를 입력하세요.`);
    form.id.focus();
    return false;
  }
  if (!form.pw.value.trim()) {
    alert("패스워드를 입력해주세요");
    form.pw.focus();
    return false;
  }
  if (!form.email.value.match(/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/)) {
    alert("이메일 형식이 다릅니다");
    form.email.value = "test@test.com";
    form.email.focus();
    return false;
  }
  if (!form.tel.value.match(/010-\d{3,4}-\d{4}/)) {
    alert("전화번호 형식이 다릅니다");
    form.tel.value = "010-1234-1234";
    form.tel.focus();
    return false;
  }

  // id 중복 체크
  if (check == 0) {
    alert('id 중복체크 해주세요');
    return false;
  } else if (check == -1) {
    alert('id 중복체크 다시하세요');
    return false;
  }
  form.submit();
}

// id 중복 체크하는 버튼 이벤트
document.getElementById("checkId").addEventListener("click", () => {
  let id = document.getElementById("id").value.trim();

  if (id.length === 0) {
    alert("id 값을 입력해주세요");
    document.getElementById("id").focus();
    document.getElementById("id").style.border = "";
    return;
  }

  fetch("userIdValidAjax.do", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
    body: "id=" + id,
  })
    .then(response => response.text())
    .then(getResult)
    .catch(error => console.log("error= ", error));
});
function getResult(data) {
  if (data === "valid") {
    alert("사용 가능한 아이디입니다.");
    document.getElementById("pw").focus();
    document.getElementById("id").style.border = "3px blue solid";
    check = 1;
  } else if (data === "notValid") {
    alert("이미 사용중인 아이디입니다.");
    document.getElementById("id").value = "";
    document.getElementById("id").focus();
    document.getElementById("id").style.border = "3px red solid";
    check = -1;
  }
  console.log(check);
}
document.getElementById(`id`).addEventListener(`keyup`, (e) => {
  if (e.KeyCode === 8) {
    check = 0;
    document.getElementById("id").style.border = "";
  }
});
