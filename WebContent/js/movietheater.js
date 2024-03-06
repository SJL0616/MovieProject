/**
 * 
 */
// 네이버 api js 로 좌표 받아와 사용
const HOME_PATH = window.HOME_PATH || ".";

const moviethlist = [...document.querySelectorAll(".movietheater")];
const moviebutton = document.querySelector(".movie-button");

moviethlist.forEach((movie) => {
	movie.addEventListener("click", () => {
		// 좌표값과 주소를 가지고오기
		let x = movie.getAttribute("data-x");
		let y = movie.getAttribute("data-y");
		let address = movie.getAttribute("data-address");
		let name = movie.getAttribute("data-name");
		let code = movie.getAttribute("data-code");
		// 좌표에 맞게 설정
		let cityhall = new naver.maps.LatLng(x, y),
			map = new naver.maps.Map("map", {
				center: cityhall.destinationPoint(0, 500),
				zoom: 15,
			}),
			marker = new naver.maps.Marker({
				map: map,
				position: cityhall,
			});
		// 좌표에 표시
		let contentString = [`
      "<div class="iw_inner">
         <h3>${name}</h3>
         <p>${address}<p>
      </div>"`
		].join("");

		let infowindow = new naver.maps.InfoWindow({
			content: contentString,
		});

		naver.maps.Event.addListener(marker, "click", function(e) {
			if (infowindow.getMap()) {
				infowindow.close();
			} else {
				infowindow.open(map, marker);
			}
		});

		infowindow.open(map, marker);
		moviebutton.setAttribute("data-code", code);
		moviebutton.setAttribute("data-name", name);
	});
});
// 영화관 선택후 다른페이지 비동기 처리
moviebutton.addEventListener("click", () => {
	if (moviebutton.getAttribute("data-code") == null) {
		alert("영화관 선택해주세요!");
		return;
	}
	let btn = moviebutton.getAttribute("data-code");
	let ctx = moviebutton.getAttribute("data-url");
	let name = moviebutton.getAttribute("data-name");
	let form = [...document.querySelectorAll(".userDt input")];
	$.ajax({
		url: "seat.do",
		type: "GET",
		data: { movieThcd: btn,
				moiveID: form[9].value,
				previewDate: form[0].value,
				time: form[3].value},
		success: function(data) {
			let body = document.querySelector(".body-iframe");
			setBodyChange(body, form, name);
			getScriptSetting(body, ctx);
			let list = null;
			if (data != null) {
				list = data;
				getSeatCheck(list);
			}
		},
		error: function(xhr, status, error) {
			console.error(error);
		}
	})
	// 영화관 코드
	let userDt = document.querySelector(".userDt");
	let input = document.createElement("input");
	
	input.name = "movietheater-code";
	input.classList = "movietheater-code";
	input.type = "hidden";
	input.value = btn;
	
	userDt.append(input);
});
//페이지 만들기
function setBodyChange(body, form, name) {
	form[8].value = name;
	body.innerHTML = `<div class="seat-select-section">
	<div class="seat-section">
		<div class="tit-seat">
			<div class="tit-utils">
				<h3 class="tit small">관람인원선택</h3>
				<div class="right">
					<button class="buttons">
						<i class="fa fa-refresh"></i> 초기화
					</button>
				</div>
			</div>
			<div class="seat-area">
				<div class="seat-count">
					<div class="cell">
						<p class="txt">총 인원수</p>
						<div class="count">
							<button class="down" title="좌석 감소">-</button>
							<div class="number">
								<p>0</p>
							</div>
							<button class="up" title="좌석 증가">+</button>
						</div>
					</div>
				</div>
				<div class="seat-layout">
					<div class="seatList">
						<img
							src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"
							alt="" />
						<div class="seat-wrapper"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="pay-seat-result">
			<div class="seat-result">
				<div class="wrap">
					<div class="tit-area">
						<div class="movie-grade">
							<span class="age age-${form[5].value}">${form[5].value}</span>
						</div>
						<div class="title">
							<p class="tit">${form[4].value}</p>
							<p class="cate">${form[2].value}</p>
						</div>
					</div>
					<div class="info-area">
						<div class="info">
							<p class="theater">${form[8].value}</p>
							<!--해당하는 영화관-->
							<p class="special"></p>
							<p class="date">
								<span>${form[0].value}</span> <em>(${form[1].value})</em>
							</p>
						</div>
						<div class="poster">
							<img
								src="https://image.tmdb.org/t/p/w500${form[6].value}"
								alt="${form[4].value}" />
						</div>
					</div>
					<div class="choice-seat-area">
						<div class="seat-num">
							<p class="tit">선택좌석</p>
							<div class="my-seat">
								<div class="seats all">-</div>
								<div class="seats all">-</div>
								<div class="seats all">-</div>
								<div class="seats all">-</div>
								<div class="seats all">-</div>
								<div class="seats all">-</div>
								<div class="seats all">-</div>
								<div class="seats all">-</div>
							</div>
						</div>
					</div>
					<div class="pay-area">
						<p class="count">
							<span> 인원수 <em>0</em>
							</span>
						</p>
						<div class="pay">
							<p class="tit">최종결제금액</p>
							<div class="money">
								<em>0</em> <span>원</span>
							</div>
						</div>
					</div>
					<div class="btn-group">
						<a href="javascript:getPayment()" class="button" id="pageprevious">결제</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>`;
}
// 해당하는 관에서 좌석이 자리가있는지 확인
function getSeatCheck(list){
	list.forEach((seat) =>{
		let input = $('<input>').attr({
					'type': 'hidden',
					'class':'seatCheck',
					'value': `${seat.seatGroup + "" + seat.seatNumber}`,
		})
		$(".seat-section").append(input);
	})
}
// script 적용
function getScriptSetting(body, ctx) {
	setTimeout(() => {
		let script = document.createElement("script");

		script.src = `${ctx}`;

		body.appendChild(script);

	}, 100);
}