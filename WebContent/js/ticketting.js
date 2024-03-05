
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
const movielist = [...document.querySelectorAll(".btn")];
const bglist = [...document.querySelectorAll(".bg")];
const btnlist = [...document.querySelectorAll(".btn")];
const overlay = document.querySelector(".overlay");
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
		wraplist[i - 1].innerHTML = month + "월<br>" + date + " " + days(day);
		wraplist[i - 1].setAttribute("data-date", year + "-" + month + "-" + date);
		wraplist[i - 1].setAttribute("data-day", days(day));
		date += 1;
		day += 1;
	}
	if (document.querySelector(".move").value != 'exist') {
		let on = movielist.find((movie) => movie.getAttribute("movie-no") == document.querySelector(".move").value);
		movieOn(on);
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
		// 해당하는 스크롤 하기
		let list = [...document.querySelectorAll(".movie-shedule-area > ul > li .btn")];

		for (let i = 0; i < list.length; i += 1) {
			let id = list[i].id.substring(0, 2);
			let ti = parseInt(time.getAttribute("data-time")) < 10 ? "0" + time.getAttribute("data-time").toString() : time.getAttribute("data-time");

			console.log(ti, ":", id);
			if (ti == id) {
				smoothScroll(list[i].id);
				return;
			}
		};
	});
});
// 스크롤 위치에 맞게 보내기
function smoothScroll(id) {
	let scroll = document.getElementById(id);
	if (scroll) {
		scroll.scrollIntoView({ behavior: "smooth" });
	}
}
// 시간 이벤트 초기화
function timeOncheck() {
	timelist.forEach((time) => {
		if (time.id != null) {
			time.removeAttribute("id");
		}
	});
}
function movieOn(movie) {
	if (movie.className.includes("on")) {
		return;
	}
	if (bgCnt >= 3) {
		alert("3개이상 체크 불가능합니다.");
		return;
	}
	movie.classList.remove("disabled");
	movie.classList.add("on");
	let img = movie.getAttribute("img-path")
	let num = movie.getAttribute("movie-no");
	document.querySelector(".choice-all").style.display = "none";
	document.querySelector(".choice-list").style.display = "block";
	let bg = bglist.find((bg) => bg.innerHTML == "");
	bg.innerHTML = `<div class="wrap">
                           <div class="img">
                              <img src="${img}" data-num="${num}">
                            </div>
                            <button class="del" onclick=imgDeleteBtn('${num}')>X</button>
                         </div>`;
	bgCnt += 1;
	if (document.querySelector(".btn.on")) {
		let on = [...document.querySelectorAll(".btn.on")];
		getTimeSetting(on);
	}
}
// 무비 이벤트 추가
movielist.forEach((movie) => {
	movie.addEventListener("click", () => { movieOn(movie) });
});
// 영화 이미지 버튼 삭제
function imgDeleteBtn(code) {
	movielist.forEach((movie) => {
		if (movie.getAttribute("movie-no") == code) {
			movie.classList.remove("on");
			movie.classList.add("disabled");
		}
	});

	if (document.querySelector(".btn.on")) {
		let on = [...document.querySelectorAll(".btn.on")];
		getTimeSetting(on);
	} else {
		document.querySelector(".movie-shedule-area > ul").innerHTML = "";
	}
	const num = document.querySelector("img[data-num='" + code + "']");
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
// 로그인 체크
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
		.then((data) => { getResult(data, id) })
		.catch(error => console.log("error : ", error))
}
function getResult(data, id) {
	if (data === "valid") {
		alert("로그인 성공.");
		$('.modal, .overlay').removeClass('active');
		document.querySelector(".userID").value = id;
		console.log(id);
	} else if (data === "notValid") {
		alert("아이디와 비밀번호를 확인하세요.");
	}
}
overlay.addEventListener("click", () => {
	$('.modal, .overlay').removeClass('active');
});

// 비동기로 페이지 바꾸기
function getMovieTheater(ctx, playStartTime, name, showtime, type, imgPath, age, code) {
	//login 체크
	if (document.querySelector(".userID").value == 'exist') {
		$('.modal, .overlay').addClass('active');
		return;
	}
	// 시간체크
	if (!getDataSave(playStartTime, name, showtime, type, imgPath, age, code)) {
		return false;
	}
	$.ajax({
		url: "movietheater.do",
		type: "GET",
		dataType: "json",
		success: function(data) {
			let list = data;

			let body = document.querySelector(".body-iframe");
			setBodyChange(body, list, ctx);
			getScriptSetting(body, ctx);
		},
		error: function(xhr, status, error) {
			console.error(error);
		}
	});
}

// movietheater html 가지고오기
function setBodyChange(body, list, ctx) {
	body.innerHTML = `<div class="map-container">
							<div class="map-header">
								<h1>영화관</h1>
							</div>
							<div class="mapinner">
								<div class="map-navi">
				</div>
						<div id="map"></div>
					</div>
					<div class="movie-result">
						<button class="movie-button" data-url="${ctx}/js/seat.js">선택완료</button>
					</div>`;
	let navi = document.querySelector(".map-navi");
	list.forEach(list => {
		navi.innerHTML += `<div class="movietheater" data-code="${list.movieThcd}" data-x="${list.locationX}"
							data-y="${list.locationY}" data-address="${list.movieAddress}"
							data-name="${list.movieName}">${list.movieName}</div>`;
	})
}
// script 적용
function getScriptSetting(body, ctx) {
	setTimeout(() => {
		let script = document.createElement("script");

		script.src = `${ctx + '/js/movietheater.js'}`;

		body.appendChild(script);

	}, 100);
}
//시간 리스트
function getTimeSetting(on) {
	document.querySelector(".movie-shedule-area > ul").innerHTML = "";
	// 현재 시간
	let hours = 9;
	let minutes = 0;
	// 영화 시간
	for (let i = hours; i <= 25;) {
		let rand = Math.floor(Math.random() * on.length);

		let name = on[rand].getAttribute("movie-nm");
		let showtime = on[rand].getAttribute("movie-time");
		let type = on[rand].getAttribute("movie-type");
		let imgPath = on[rand].getAttribute("img-path");
		let ctx = on[rand].getAttribute("movie-ctx");
		let age = on[rand].getAttribute("movie-age");
		let code = on[rand].getAttribute("movie-no");

		let curhours = Math.floor(parseInt(showtime) / 60);
		let curminutes = parseInt(showtime) % 60;
		console.log(curminutes);
		// 정확한값을 주기위해 엘레멘탈 생성후 넣기
		let li = document.createElement("li");
		let legend = document.createElement("div");
		// 빈공간
		legend.classList.add("legend");
		// button class 생성
		let button = document.createElement("button");
		button.classList.add("btn");

		// play start time
		button.setAttribute("play-start-time", `${(minutes + 10 >= 60 ? i + 1 : i) < 10 ? 0 + "" + (minutes + 10 >= 60 ? i + 1 : i)
			: (minutes + 10 >= 60 ? i + 1 : i)}${i == 9 ? "00"
				: (minutes + 10 >= 60 ? ((minutes + 10) % 60 < 10 ? 0 + "" + ((minutes + 10) % 60)
					: ((minutes + 10) % 60))
					: minutes + 10)}`);


		// id
		button.id = `${(minutes + 10 >= 60 ? i + 1 : i) < 10 ? 0 + "" + (minutes + 10 >= 60 ? i + 1 : i)
			: (minutes + 10 >= 60 ? i + 1 : i)}${i == 9 ? "00" :
				(minutes + 10 >= 60 ? ((minutes + 10) % 60 < 10 ?
					0 + "" + ((minutes + 10) % 60)
					: ((minutes + 10) % 60))
					: minutes + 10)}`;
		// time 생성
		let time = document.createElement("span");
		let strong = document.createElement("strong");
		let em = document.createElement("em");

		time.classList = "time";

		strong.setAttribute("title", "상영 시작");
		em.setAttribute("title", "상영 종료");



		timeSet(minutes, curminutes, i, em, curhours, strong);
		// time 업펜드
		time.appendChild(strong);
		time.appendChild(em);
		// button 에 엡펜드
		button.appendChild(legend);
		button.appendChild(time);
		// 제목 타입 넣기
		button.innerHTML += `<span class="title"> 
									<strong title="${name}">${name}
										</strong> <em>${type}</em>
								</span>
								<div class="info">
									<span class="theater" title="극장"> </span>
								</div>`
		li.appendChild(button);

		document.querySelector(".movie-shedule-area > ul").appendChild(li);

		let playStartTime = button.getAttribute("play-start-time");
		button.onclick = function() {
			getMovieTheater(ctx, playStartTime, name, showtime, type, imgPath, age, code);
		}
		minutes += curminutes;
		if (minutes >= 60) {
			i += 1;
			minutes = minutes % 60;
		}
		i += curhours;
	}

}
// 값을 더정확하게 전달하기위한 작업 1시 -> 01시 2분 -> 02분 
function timeSet(minutes, curminutes, i, em, curhours, strong) {

	// 상영 시작
	if (minutes + 10 >= 60) {
		strong.innerHTML = i + 1 < 10 ? 0 + "" + i + 1 : i + 1;
		strong.innerHTML += ":" + (((minutes + 10) % 60) < 10 ? 0 + "" + ((minutes + 10) % 60) : ((minutes + 10) % 60));
	} else {
		strong.innerHTML = i < 10 ? 0 + "" + i : i;
		strong.innerHTML += ":" + (i == 9 ? "00" : minutes + 10);
	}
	// 상영 종료
	if (minutes + curminutes >= 60) {
		em.innerHTML = "~";
		em.innerHTML += i + 1 + curhours < 10 ? 0 + "" + i + 1 + curhours : i + 1 + curhours;
		em.innerHTML += ":" + (((minutes + curminutes) % 60) < 10 ? 0 + "" + ((minutes + curminutes) % 60) : ((minutes + curminutes) % 60));
	} else {
		em.innerHTML = "~";
		em.innerHTML += i + curhours < 10 ? 0 + "" + i + curhours : i + curhours;
		em.innerHTML += ":" + ((minutes + curminutes) < 10 ? 0 + "" + (minutes + curminutes) : (minutes + curminutes));
	}

}

function getDataSave(time, name, showtime, type, imgPath, age, code) {
	console.log(document.querySelector(".wrap-list #on"));
	if (!document.querySelector(".wrap-list #on")) {
		alert("날짜를 선택후 이용해주세요!");
		return false;
	}
	let date = document.querySelector(".wrap-list #on");
	let selectDate = document.querySelector(".userDt .select-date");
	let movieTime = document.querySelector(".userDt .movie-time");
	let movieType = document.querySelector(".userDt .movie-type");
	let movieName = document.querySelector(".userDt .movie-name");
	let movieShowTime = document.querySelector(".userDt .movie-show-time");
	let movieAge = document.querySelector(".userDt .movie-age");
	let movieImg = document.querySelector(".userDt .movie-img");
	let selectDay = document.querySelector(".userDt .select-day");
	let movieCode = document.querySelector(".userDt .movie-code");

	selectDate.value = date.getAttribute("data-date");
	movieTime.value = time;
	movieType.value = type;
	movieName.value = name;
	movieShowTime.value = showtime;
	selectDay.value = date.getAttribute("data-day");
	movieImg.value = imgPath;
	movieAge.value = age;
	movieCode.value = code;
	return true;
}