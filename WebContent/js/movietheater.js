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
	});
});

moviebutton.addEventListener("click", () => {
	if (moviebutton.getAttribute("data-code") == null) {
		alert("영화관 선택해주세요!");
		return;
	}
	let btn = moviebutton.getAttribute("data-code");
	let ctx = moviebutton.getAttribute("data-url");
	$.ajax({
		url:"seat.do",
		type: "GET",
		data: {btn: btn},
		success: function(data){
			let list = null;
			if(data != null){
				list = data;
			}
			let body = document.querySelector(".body-iframe");
			setBodyChange(body,list);
			getScriptSetting(body,ctx);
		},
		error:	function(xhr, status, error) {
			console.error(error);
		}
	})
});
function setBodyChange(body,list,ctx){
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
							<span class="age-12">12</span>
						</div>
						<div class="title">
							<p class="tit">파묘</p>
							<p class="cate">2D</p>
						</div>
					</div>
					<div class="info-area">
						<div class="info">
							<p class="theater">광명</p>
							<!--해당하는 영화관-->
							<p class="special">뭐어쩌고저쩌고</p>
							<p class="date">
								<span>2024.02.28</span> <em>(수)</em>
							</p>
						</div>
						<div class="poster">
							<img
								src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"
								alt="" />
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
						<a href="" class="button" id="pageprevious">결제</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>`;
}
// script 적용
function getScriptSetting(body,ctx) {
	setTimeout(() => {
		let script = document.createElement("script");

		script.src = `${ctx}`;

		body.appendChild(script);

	}, 100);
}
