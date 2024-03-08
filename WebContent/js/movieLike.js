	/**
 * 
 */

$(document).ready(function() {
	//데이트피커 초기화
	console.log("movieLike.js on");
}); // document ready function


$(document).on('click','.btn-like', function(e){
	console.log("like btn clicked");
	console.log(e.currentTarget.className);
	if (!isLoggedIn()) {
		alert("로그인이 필요한 서비스입니다.");
		return;
	}
	let movieID = $(e.currentTarget).data("movie-no");
	let isLike = true;
	if($(e.currentTarget).data("mylike") == true){
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
					if($(e.currentTarget).hasClass("btn")){
						$(e.currentTarget).toggleClass('on');
					}else{
						$(e.currentTarget).find(".ico-heart-toggle-gray").toggleClass('on');
					}
					
					$(e.currentTarget).data("mylike", isLike);
					$(e.currentTarget).find('span').text(($(e.currentTarget).text()-(isLike ? -1:1)));
				}
		})
});


