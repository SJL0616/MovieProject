/**
 * 
 */
let searchEvent = false;

$(document).ready(function() {
	//데이트피커 초기화
	searchBarInit();
}); // document ready function

function searchBarInit(){
	document.getElementById("ibxMovieNmSearch").addEventListener("keypress",function(event){
		if(event.key === "Enter"){
			if(searchEvent == false){
			searchEvent = true;
			searchEvent = searchMovieByNm();
		    } 
		
		}
	});
	document.getElementById("btnSearch").addEventListener("click", function(){
		
		if(searchEvent == false){
			searchEvent = true;
			searchEvent = searchMovieByNm();
		} 
	});
}

function searchMovieByNm(){
	console.log("이름으로 검색");
	console.log(document.getElementById("ibxMovieNmSearch").value);
	let keyword = document.getElementById("ibxMovieNmSearch").value.trim();
	console.log(keyword +" "+ keyword.length);
	
	const keywords = keyword.split(/\s+/);
	console.log(keywords);
	console.log(JSON.stringify(keywords));
	
	fetch("movieSearch.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "keywords=" + JSON.stringify(keywords)
	})
		.then(response => response.text())
		.then((data) => {
			let result = JSON.parse(data);
			const movieList = document.getElementById("movieList");
			movieList.innerHTML = '';
			if (result == 0) {
				$("#noDataDiv").show();
				$("#totCnt").text("0");
			} else {
				$("#noDataDiv").hide();
				let list = result;
				$("#totCnt").text(list.length);
				for(let i = 0; i < list.length; i++ ){
					let vo = JSON.parse(list[i]);
					let li =  getMovieli(vo);
					movieList.insertAdjacentHTML("beforeend",li);
				}
			 
				console.log(list);
			}
		})
	
	return false;
}

function getMovieli(vo){
	var html = '';
	html += '<li tabindex="0" class="no-img">';
    html += '<div class="movie-list-info">';
   /* html += '<p class="rank" style=""><span class="ir">' + vo.rank + '</span></p>';*/
    html += '<img src="https://image.tmdb.org/t/p/w500' + vo.image + '" alt="듄: 파트2" class="poster lozad" onerror="noImg(this)">';

    html += '<div class="movie-score">';
    html += '<a href="javascript:void(0);" onclick="location.href = generateMovieDetailURL('+vo.movieID+');" class="wrap movieBtn" title="' + vo.title + ' 상세보기">';
    html += '<div class="summary">' + vo.overview + '</div>';
    html += '<div class="my-score equa" style="display: none;">';
    html += '<div class="preview">';
    html += '<p class="tit">관람평</p>';
    html += '<p class="number">0<span class="ir">점</span></p>';
    html += '</div></div></a></div></div>';

    html += '<div class="tit-area">';
    if (vo.watchGrade === '전체관람가') {
        html += '<p class="movie-grade age-all">,</p>';
    } else if (vo.watchGrade === '12세이상관람가') {
        html += '<p class="movie-grade age-12">,</p>';
    } else if (vo.watchGrade === '15세이상관람가') {
        html += '<p class="movie-grade age-15">,</p>';
    } else if (vo.watchGrade === '19세이상관람가') {
        html += '<p class="movie-grade age-19">,</p>';
    }
    html += '<p title="' + vo.title + '" class="tit">' + vo.title + '</p></div>';

    html += '<div class="rate-date">';
    html += '<span class="rate">예매율 46.9%</span> <span class="date">개봉일 ' + vo.openDate+ '</span>';
    html += '</div>';

    html += '<div class="btn-util">';
    html += '<button type="button" class="button btn-like" data-mylike="'+vo.myLike+'"  data-movie-no="'+vo.movieID+'">';

    if(vo.myLike == true){
	 html += '<i title="보고싶어 설정 함" class="iconset ico-heart-toggle-gray on"></i> <span>'+vo.totalLikes +'</span>';
    }else{
	 html += '<i title="보고싶어 안함" class="iconset ico-heart-toggle-gray intrstType"></i> <span>'+vo.totalLikes +'</span>';
    }
    
    html += '</button>';
    html += '<p class="txt movieStat1" style="display: none">상영예정</p>';
    html += '<p class="txt movieStat2" style="display: none">2월 개봉예정</p>';
    html += '<p class="txt movieStat5" style="display: none">개봉예정</p>';
    html += '<p class="txt movieStat6" style="display: none">상영종료</p>';
    html += '<div class="case col-2 movieStat3" style="">';
    html += '<a href="#" class="button purple bokdBtn" data-no="23095500" title="영화 예매하기">예매</a>';
    html += '<a href="#" class="button purple img splBtn" data-no="23095500"></a>';
    html += '</div>';
    html += '<div class="case movieStat4" style="display: none">';
    html += '<a href="#" class="button purple bokdBtn" data-no="23095500" title="영화 예매하기">예매</a>';
    html += '</div></li>';

return html;							
}
function generateMovieDetailURL(movieID) {
            return '/MovieProject/movie-detail.do?id=' + movieID;
}






