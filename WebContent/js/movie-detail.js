/**
 * 
 */
let tablist = null;
let summary = null;
let info = null;
let titUtil = null;
let titUtil2 = null;
let comments = null;
let trailer = null;
let trailderNum = 0;
let players = null;
$(document).ready(function() {
	//데이트피커 초기화
	loadData();

}); // document ready function


function loadData() {

	console.log("메인 로드");
	tablist = Array.from(document.querySelector(".tab-list").firstElementChild.children);
	summary = document.querySelector(".movie-summary");
	info = document.querySelector(".movie-info");

	titUtil = document.querySelector(".tit-util");
	titUtil2 = document.querySelector(".movie-list-util");
	comments = document.querySelector(".movie-idv-story");

	trailer = document.querySelector(".videoContainer");
	trailer.style.display = 'none';
	videoSet();



}

function videoSet() {
	// 2. This code loads the IFrame Player API code asynchronously.
	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
	// 3. This function creates an <iframe> (and YouTube player)
	//    after the API code downloads.
	var player;


}



function tablistSet(event) {
	tablist.forEach((e) => {
		/*console.log(e);
		console.log(e.classList.contains("on"));*/
		if (e.classList.contains("on")) {
			e.classList.remove("on");
		}
		/*console.log(e.firstElementChild == event.target);*/
		if (e.firstElementChild == event.target) {
			e.classList.add("on");
		}
	});
}

function showComments(event) {
	tablistSet(event);
	summary.style.display = 'none';
	info.style.display = 'none';
	trailer.style.display = 'none';
	titUtil.style.display = 'block';
	titUtil2.style.display = 'block';
	comments.style.display = 'block';

}

function showMain(event) {
	tablistSet(event);
	summary.style.display = 'block';
	info.style.display = 'block';
	titUtil.style.display = 'block';
	titUtil2.style.display = 'block';
	comments.style.display = 'block';
	trailer.style.display = 'none';
}

function showTrailer(event) {
	tablistSet(event);
	summary.style.display = 'none';
	info.style.display = 'none';
	titUtil.style.display = 'none';
	titUtil2.style.display = 'none';
	comments.style.display = 'none';
	trailer.style.display = 'block';
}

function onYouTubeIframeAPIReady() {
	let keys = Array.from(document.querySelectorAll(".videoKey"));

	let keyNum = 0;
	keys.forEach(e => {
		let num = keyNum;
		let id = 'player' + (num + 1);
		player = new YT.Player(id, {
			height: '450',
			width: '800',
			videoId: e.value,
			events: {
				onReady: function(event) {
					event.target.mute();
				}
			}
		});
		keyNum += 1;
	});
	players = Array.from(document.querySelectorAll(".player"));
	if (players.length == 1) {
		document.querySelector(".swiper-button-next").classList.add("swiper-button-disabled");
		document.querySelector(".swiper-button-prev").classList.add("swiper-button-disabled");
	}
	videoOffSet();
}

function videoOffSet() {
	let num = 0;
	players.forEach(e => {
		if (num == trailderNum) {
			e.style.display = "block";
		} else {
			e.style.display = "none";
		}
		num++;
	});

}
function showPre() {
	console.log("showNext");
	trailderNum = trailderNum - 1 > -1 ? trailderNum - 1 : players.length - 1;
	videoOffSet();
}
function showNext() {
	console.log("showNext");
	trailderNum = trailderNum + 1 < players.length ? trailderNum + 1 : 0;
	videoOffSet();
}


function showReview(event ,id) {
	console.log(event.target);
	console.log(event.target.getAttribute("pagenum"));
	console.log(id);
	let pageNum = event.target.getAttribute("pagenum");
	fetch("showReview.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body:  "currentPage=" +  pageNum +"&"
			+ "id= "+id
		}) 
		.then(response => response.text())
		.then((data) => {
			if (data === `fail`) {
			alert(`실패했습니다.`);
			} else {
				let list = JSON.parse(data);
				console.log(data);
				console.log(list);
			}
	})
}

function controlReviewForm(){
	let form = document.getElementById("layer_regi_reply_review");
	if(!form.classList.contains("on")){
		form.classList.add("on");
	}else{
		form.classList.remove("on");
	}
}

var selectCIdx;
var selectPIdx;
var totalPoint;
var vPoint;
//한줄평 분야 설정
$('.point .box .btn').on('click', function() {
	$('.point .box .btn').removeClass('on');
	$(this).addClass('on');
	vPoint =  $(this).text();
});

// 한줄평 별점 마우스 이벤트
$('.box-star-score .star button').on('mouseover', function() {
	var cIdx = $(this).index();
	var pIdx = $(this).parent().index();

	fn_bindStart(cIdx, pIdx);
}).on('mouseout', function() {
	$('.box-star-score .star button').removeClass('on');

	fn_bindStart(selectCIdx, selectPIdx);
}).on('click', function() {
	selectCIdx = $(this).index();
	selectPIdx = $(this).parent().index();

	fn_bindStart(selectCIdx, selectPIdx);
});

// 한줄평 별점 설정
function fn_bindStart(cIdx, pIdx) {
	totalPoint = 0;

	$('.box-star-score .star button').removeClass('on');

	for (var i = 0; i <= pIdx; ++i) {
		if (i < pIdx) {
			$('.box-star-score .group').eq(i).find('button').addClass('on');

			totalPoint += 2;
		} else if (i >= pIdx) {
			for (var j = 0; j <= cIdx; ++j) {
				$('.box-star-score .group').eq(i).find('button').eq(j).addClass('on');

				totalPoint += 1;
			}
		}
	}

	$('.box-star-score .num em').html(totalPoint);
};

$('#textarea').on('keyup', function() {
	if (this.value.length > 100) this.value = this.value.substr(0, 100);

	$('.textarea .count span').html(this.value.length);
	
});

$('#regOneBtn').on('click', function() {
	let mid = $(this).attr("data-mno");
	let uid = 'qwer'
	let content = $('#textarea').val();
	console.log("아이디 : "+mid +", 점수 : "+totalPoint +", 내용 "+ content +", 분야 "+vPoint);
	
	fetch("regReview.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body:  "uid=" +  uid + "&"
	    +"mid=" + mid + "&"
        +"point=" + totalPoint+ "&"
        +"vPoint="+ vPoint+ "&"
	    +"content="+ content
	}) 
	.then(response => response.text())
	.then((data) => {
		if (data === `fail`) {
		alert(`로그인이 필요한 메뉴입니다.`);
		} else {
			alert(`성공했습니다.`);
			controlReviewForm();
		}
	})
	
});
