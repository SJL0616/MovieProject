function toMyMega() {
	fetch("checkLog.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
	})
		.then(response => response.text())
		.then((data) => {
			if (data === `fail`) {
			alert(`로그인이 필요한 메뉴입니다.`);
			} else {
				location.href = "http://localhost:8085/MovieProject/userMyMega.do";
			}
		})
}
