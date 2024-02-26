
$(".wrap-list").slick({
	slidesToShow: 14,
	slidesToScroll: 1,
	autoplay: false,
	autoplaySpeed: 2000,
	infinite: false,
	prevArrow: $(".btn-pre"),
	nextArrow: $(".btn-next"),
});
$(".time-list").slick({
	slidesToShow: 9,
	slidesToScroll: 1,
	autoplay: false,
	autoplaySpeed: 2000,
	infinite: false,
	prevArrow: $(".time-pre"),
	nextArrow: $(".time-next"),
});

const wraplist = [...document.querySelectorAll("#date-list")];
const timelist = [...document.querySelectorAll("#btn-time")];
const movielist = [...document.querySelectorAll(".disabled")];
const bglist = [...document.querySelectorAll(".bg")];
let bgCnt = 0;
init();
// 날짜 초기값
function init() {
	let today = new Date();

	let year = today.getFullYear(); // 년
	let month = today.getMonth() + 1; // 월 는 0부터 시작해서 +1 해줘야함
	let date = today.getDate(); // 날짜
	let day = today.getDay(); // 요일

	//이번달의 마지막날 날짜
	let endDay = new Date(year, month, 0);
	let nextDate = endDay.getDate(); //날짜
	// 날짜 출력 포문
	for (let i = 1; i <= 23; i += 1) {
		if (day == 7) {
			day = 0;
		}
		// 마지막 일수일경우
		if (date > nextDate) {
			// 년수가 달라질때
			if (month == 12) {
				month = 0 + 1;
				year += 1;
			}
			// 월도 증가
			month += 1;
			endDay = new Date(year, month, 0);
			nextDate = endDay.getDate();
			date = 1;
		}
		// 일요일 색
		if (day == 0) {
			wraplist[i - 1].style.color = "red";
		}
		// 토요일 색
		if (day == 6) {
			wraplist[i - 1].style.color = "blue";
		}
		// day 값 넣기 및 date 값 data 에 담기
		wraplist[i - 1].innerHTML = date + " " + days(day);
		wraplist[i - 1].setAttribute("data-date", year + "-" + month + "-" + date);
		date += 1;
		day += 1;
	}
}
// 날짜 클릭이벤트 추가
wraplist.forEach((wrap) => {
	wrap.addEventListener("click", () => {
		wrapOnCheck();
		wrap.id = "on";
	});
});
// 날짜 이벤트 초기화
function wrapOnCheck() {
	wraplist.forEach((wrap) => {
		if (wrap.id != null) {
			wrap.removeAttribute("id");
		}
	});
}
// 시간 클릭이벤트 추가
timelist.forEach((time) => {
	time.addEventListener("click", () => {
		timeOncheck();
		time.id = "on";
	});
});
// 시간 이벤트 초기화
function timeOncheck() {
	timelist.forEach((time) => {
		if (time.id != null) {
			time.removeAttribute("id");
		}
	});
}
// 무비 이벤트 추가
movielist.forEach((movie) => {
	movie.addEventListener("click", () => {
		if (movie.id == "on") {
			return;
		}
		if (bgCnt >= 3) {
			alert("3개이상 체크 불가능합니다.");
			return;
		}
		movie.id = "on";
		let num = movie.getAttribute("movie-no");
		document.querySelector(".choice-all").style.display = "none";
		document.querySelector(".choice-list").style.display = "block";
		let bg = bglist.find((bg) => bg.innerHTML == "");
		bg.innerHTML = `<div class="wrap">
                              <div class="img" data-num="${num}"></div>
                              <button class="del" onclick=imgDeleteBtn('${num}')>X</button>
                              </div>`;
		bgCnt += 1;
	});
});
// 영화 이미지 버튼 삭제
function imgDeleteBtn(code) {
	movielist.forEach((movie) => {
		if (movie.getAttribute("movie-no") == code) {
			movie.id = "";
		}
	});
	const num = document.querySelector(".img[data-num='" + code + "']");
	const wrap = num.closest(".wrap");
	wrap.remove();
	if (document.querySelectorAll(".bg > .wrap").length == 0) {
		document.querySelector(".choice-all").style.display = "block";
		document.querySelector(".choice-list").style.display = "none";
	}
	bgCnt -= 1;
}
// 요일 표현
function days(day) {
	if (day == 0) {
		return "일";
	} else if (day == 1) {
		return "월";
	} else if (day == 2) {
		return "화";
	} else if (day == 3) {
		return "수";
	} else if (day == 4) {
		return "목";
	} else if (day == 5) {
		return "금";
	} else if (day == 6) {
		return "토";
	}
}
// 스크롤 위치에 맞게 보내기
function smoothScroll(id) {
	let scroll = document.getElementById(id);
	console.log(scroll);
	if (scroll) {
		scroll.scrollIntoView({ behavior: "smooth" });
	}
}
