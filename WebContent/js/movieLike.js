	/**
 * 
 */

$(document).ready(function() {
	//데이트피커 초기화
	console.log("movieLike.js on");
}); // document ready function


$(document).on('click','.btn-like', function(e){
	console.log("like btn clicked");
	console.log($(e.target).closest(".btn-like").data("movie-no"));
	console.log($(e.target).closest(".btn-like").data("mylike"));
	if (!isLoggedIn()) {
		alert("로그인이 필요한 서비스입니다.");
		return;
	}
	let movieID = $(e.target).closest(".btn-like").data("movie-no");
	let isLike = true;
	if($(e.target).closest(".btn-like").data("mylike") == true){
		isLike = false;
	}
			fetch("movieLike.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body: "movieID=" + movieID+"&"
			      +"isLike="+ isLike
		})
			.then(response => response.text())
			.then((data) => {
				if (data == 1) {
					console.log(`실패했습니다.`);
				} else {
					console.log(`좋아요 했습니다.`);
					console.log($(e.target));/*
					$(e.target).find(".btn-like").toggleClass('on');
					$(e.target).parent().find(".btn-like").toggleClass('on');*/
					/*$(e.target).find(".ico-heart-toggle-gray").toggleClass('on');
					$(e.target).parent().find(".ico-heart-toggle-gray").toggleClass('on');*/
					
					if($(e.target).hasClass("btn")|| $(e.target).hasClass("button")){
						if(!$(e.target).hasClass("btn")){
							$(e.target).find(".ico-heart-toggle-gray").toggleClass('on');
						}else{
							$(e.target).toggleClass('on');
						}
					}else{
						$(e.target).toggleClass('on');
					}
					$(e.target).closest(".btn-like").data("mylike", isLike);
					$(e.target).closest(".btn-like").find('span').text(($(e.target).closest(".btn-like").text()-(isLike ? -1:1)));
				}
		})
});


