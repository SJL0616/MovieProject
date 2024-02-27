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

    naver.maps.Event.addListener(marker, "click", function (e) {
      if (infowindow.getMap()) {
        infowindow.close();
      } else {
        infowindow.open(map, marker);
      }
    });

    infowindow.open(map, marker);
    moviebutton.setAttribute("data-moviename", name);
  });
});

moviebutton.addEventListener("click", () => {
  if (moviebutton.getAttribute("data-moviename") == null) {
    alert("영화관 선택해주세요!");
    return;
  }
});
