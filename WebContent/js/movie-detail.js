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


// 최신순/ 공감순 / 평점순 변경 버튼으로 리뷰 출력
$('.movie-sorting-right .btn.orderBtn').on('click', function() {
	$('.movie-sorting-right .btn.orderBtn').removeClass('on');
	$(this).addClass('on');
	submit(1, $(this).attr('data-cd'), $(this).text());
});
// 페이지 버튼 클릭으로 리뷰 출력
function showReview(event, id) {
	let pageNum = event.target.getAttribute("pagenum");
	let order = document.querySelector('.orderBtn.on').textContent;
	submit(pageNum, id, order);
}

function submit(pageNum, id, order) {
	let rBox = Array.from(document.querySelector('.reviewBox').children);
	fetch("showReview.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "currentPage=" + pageNum + "&"
			+ "id=" + id + "&"
			+ "order=" + order
	})
		.then(response => response.text())
		.then((data) => {
			if (data === `fail`) {
				alert(`실패했습니다.`);
			} else {
				let list = JSON.parse(data);
				let pageCxt = JSON.parse(list[0]);
				removeReviews(rBox);
				let rlist = list[1];
				for (let i = 0; i < rlist.length; i++) {
					let review = JSON.parse(rlist[i]);
					appendReviews(review);
				}
				setPaging(pageCxt, id);
			}
		})
}

function removeReviews(rBox) {
	rBox.forEach(e => {
		if (e.classList.contains("type01")) {
			document.querySelector('.reviewBox').removeChild(e);
		}
	});
}


function appendReviews(vo) {
	
	
	var html = '<li class="type01 oneContentTag">' +
    '<div class="story-area">';
if(vo.userID == sessionLog){
	html +='<div class="user-prof my">';
}else{
	html +='<div class="user-prof">';
}
    html +='<div class="prof-img">' +
    '<img src="https://www.megabox.co.kr/static/pc/images/mypage/bg-photo.png" alt="프로필 사진" title="프로필 사진" >' +
    '</div>' +
    '<p class="user-id">' + vo.userID + '</p>' +
    '</div>' +
    '<div class="story-box">' +
    '<div class="story-wrap review">' +
    '<div class="tit">관람평</div>' +
    '<div class="story-cont">' +
    '<div class="story-point">' +
    '<span>' + vo.point + '</span>' +
    '</div>' +
    '<div class="story-recommend"><em>' + vo.viewPoint + '</em></div>' +
    '<div class="story-txt">' + vo.content + '</div>' +
    '<div class="story-like">';

if (vo.myLike == true) {
    html += '<button type="button" class="oneLikeBtn" title="댓글 추천">' +
        '<i class="iconset ico-like-purple"></i> <span>' + vo.like + '</span>' +
        '</button>';
} else {
    html += '<button type="button" class="oneLikeBtn" onclick="like(' + vo.reviewID + ')" title="댓글 추천" data-no="' + vo.movieID + '" data-is="N">' +
        '<i class="iconset ico-like-gray"></i> <span>' + vo.like + '</span>' +
        '</button>';
}

html += '</div>' +
    '<div class="story-util">' +
    '<div class="post-funtion">' +
    '<div class="wrap">' +
    '<button type="button" class="btn-alert" onclick="switchOpt(event)">옵션보기</button>' +
    '<div class="balloon-space user">' +
    '<div class="balloon-cont">' +
    '<div class="user">';
if(vo.userID == sessionLog){
	html +='<p class="reset a-c">' +
    '리뷰를 <br>삭제하시겠습니까?' +
    '</p>' +
    '<button type="button" class="maskOne" data-fnc="delete" data-no="' + vo.reviewID + '">' +
    '삭제하기 <i class="iconset ico-arr-right-green"></i>' +
    '</button>' +
    '</div>';
}else{
	html +='<p class="reset a-c">' +
    '스포일러 및 욕설/비방하는<br>내용이 있습니까?' +
    '</p>' +
    '<button type="button" class="maskOne" data-fnc="report" data-no="' + vo.reviewID + '">' +
    '댓글 신고 <i class="iconset ico-arr-right-green"></i>' +
    '</button>' +
    '</div>';
}
html +='<div class="btn-close">' +
    '<a href="#" title="닫기"><img src="https://www.megabox.co.kr/static/pc/images/common/btn/btn-balloon-close.png" alt="닫기"></a>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>' +
   
    '</div>' +
 
    '</div>' +
'<div class="story-date">' +
    '<div class="review on">' +
    '<span>' + vo.elapsedTime + '</span>' +
    '</div>' +
    '</div>' +
    '</li>';

	document.querySelector('.reviewBox').insertAdjacentHTML("beforeend", html);

}

function setPaging(pageCxt, id) {
	const pagination = document.querySelector('.pagination');
	pagination.innerHTML = '';


	var paging = '';
	if (pageCxt.startPage > 10) {
		paging += '<a title="이전 페이지 보기" href="javascript:void(0)"'
			+ 'onclick="showReview(event,' + id + ')"'
			+ 'class="control next" pagenum="' + (pageCxt.startPage - 1) + '">prev</a>';
	}
	for (let i = pageCxt.startPage; i <= pageCxt.endPage; i++) {
		if (i == pageCxt.currentPage) {
			paging += '<strong class="active">' + i + '</strong>';
		} else {
			paging += '<a title="' + i + '페이지보기" href="javascript:void(0)" onclick="showReview(event ,' + id + ')" pagenum="' + i + '">' + i + '</a>';
		}
	}
	if (pageCxt.endPage < pageCxt.totalPages) {
		paging += '<a title="이후 페이지 보기" href="javascript:void(0)"'
			+ 'onclick="showReview(event,' + id + ')"'
			+ 'class="control next" pagenum="' + (pageCxt.endPage + 1) + '">next</a>';
	}
	paging += '<a title="마지막 페이지 보기" href="javascript:void(0)"'
		+ 'onclick="showReview(event,' + id + ')"'
		+ 'class="control next" pagenum="' + (pageCxt.totalPages) + '">last</a>';
	pagination.innerHTML = paging;

}





function controlReviewForm() {

	let form = document.getElementById("layer_regi_reply_review");
	if (!form.classList.contains("on")) {
		if (!isLoggedIn()) {
			alert("로그인이 필요한 서비스입니다.");
			return;
		}
		fetch("getReviewCnt.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body: "movieID=" + $(".movie-sorting-right .btn.orderBtn.on").attr('data-cd')
		})
			.then(response => response.text())
			.then((data) => {
				if (data == 0) {
					form.classList.add("on");
				} else {
					alert(`이미 리뷰를 쓴 게시물입니다.`);
				}
		})
	} else {
		form.classList.remove("on");
		resetReviewForm();
	}
}

// 말풍선 - 리뷰 옵션 
$(document).on('click', '.balloon-space .btn-close a', function(e){
    e.preventDefault();
    $(this).closest('.balloon-space').removeClass('on');
    $(this).closest('.post-funtion').find('.btn-alert').focus();
});

function switchOpt(event){
	console.log("리뷰 추가기능");
	if($(event.target).next().hasClass('on')){
		$(event.target).next().removeClass('on');
		$(event.target).next().children().children('div:eq(0)').css({"display": "none"});
	}else{
		$(event.target).next().addClass('on');
		$(event.target).next().children().children('div:eq(0)').css({"display": "block"});
	}
};

  $(document).on('click','.maskOne', function() {
	if (!isLoggedIn()) {
		alert("로그인이 필요한 서비스입니다.");
		return;
	}
	
	console.log($(this).data('no'));
	console.log($(this).data('fnc'));
	console.log($(this).data('fnc') == 'report');
	
	let content = $(this).data('fnc') == 'report'? "해당 댓글을 신고하시겠습니까?" : "정말로 삭제하시겠습니까?"
		// Create a new div element
	var newDiv = document.querySelector(".alert-popup");
	if(newDiv == null || newDiv ==undefined){
		 newDiv = document.createElement("div");
	
	// Set the CSS styles for the div element
	newDiv.style.position = "fixed";
	newDiv.style.paddingTop = "45px";
	newDiv.style.background = "rgb(255, 255, 255)";
	newDiv.style.zIndex = "5006";
	newDiv.style.top = "50%";
	newDiv.style.left = " 50%";
	newDiv.style.transform = "translate(-50%, 0)";
	newDiv.style.width = "300px";
	newDiv.style.opacity = "1";
	
	newDiv.style.display = "block";
	// Add the 'alert-popup' class to the div element
	newDiv.classList.add("alert-popup");
	newDiv.innerHTML = `
    <div class="wrap">
        <header class="layer-header">
            <h3 class="tit">알림</h3>
        </header>
        <div class="layer-con" style="height:200px">
            <p class="txt-common">`+content+`</p>
    <div class="btn-group">
                <button type="button" class="button lyclose">취소</button>
                <button type="button" data-fnc="`+$(this).data('fnc')+`" class="button purple confirm">확인</button>
            </div>
        </div>
        <button type="button" class="btn-layer-close">레이어 닫기</button>
    </div>
`;
	// Append the div element to the body
	document.body.appendChild(newDiv);
	}else{
		$(newDiv).css('display','block');
		$(newDiv).find('.txt-common').text(content);
		$(newDiv).find('.button.purple.confirm').data("fnc",$(this).data('fnc'));
	}
	$(newDiv).data('rid',$(this).data('no'));
	$('.alertStyle').css('opacity','0.7').css('display','block');
	$('body').addClass('no-scroll');
	
  });

// 신고 - 닫기 
$(document).on('click', '.alert-popup .btn-layer-close ,.alert-popup .lyclose',function(e){
    e.preventDefault();
	closeModal(this);
});
function closeModal(div){
	$(div).closest('.alert-popup').data('rid','0');
    $(div).closest('.alert-popup').css('display','none');
    $('.alertStyle').css('opacity','0').css('display','none');
	$('body').removeClass('no-scroll');
}

// 신고 - 보내기 
$(document).on('click', '.alert-popup .confirm',function(e){
    e.preventDefault();
    if($(e.target).data('fnc') === 'report'){
	    reportReview($(this).closest('.alert-popup').data('rid'), this);
	}else{
		deleteReview($(this).closest('.alert-popup').data('rid'), this);
	}
  
});

function reportReview(reviewID,div){
	fetch("reportReview.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "reviewID=" + reviewID
	})
		.then(response => response.text())
		.then((data) => {
			if (data == 0) {
				alert(`실패했습니다.`);
			} else {
				alert(`신고 했습니다.`);
				closeModal(div);
				$(".balloon-space .btn-close a").closest('.balloon-space').removeClass('on');
			}
	})
}

function deleteReview(reviewID,div){
	fetch("deleteReview.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "reviewID=" + reviewID
	})
		.then(response => response.text())
		.then((data) => {
			if (data == 0) {
				alert(`실패했습니다.`);
			} else {
				alert(`삭제 했습니다.`);
				closeModal(div);
				submit(1, $(".movie-sorting-right .btn.orderBtn.on").attr('data-cd'), "최신순");
			}
	})
}


var selectCIdx;
var selectPIdx;
var totalPoint = 0;
var vPoint = '';
//한줄평 분야 설정
$('.point .box .btn').on('click', function() {
	$('.point .box .btn').removeClass('on');
	$(this).addClass('on');
	vPoint = $(this).text();
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
	let content = $('#textarea').val();
	console.log("영화 아이디 : " + mid + ", 점수 : " + totalPoint + ", 내용 " + content + ", 분야 " + vPoint);
	if (totalPoint == 0) {
		$('.errText').html('이 영화에 대한 별점을 선택해주세요.');
		$('.errText').show();
	} else if (isEmpty(content)) {
		$('.errText').html('이 영화에 대한 실관람평을 작성해주세요.');
		$('.errText').show();
	} else if (isEmpty(vPoint)) {
		$('.errText').html('이 영화에 대한 관람 포인트를 1개 선택해주세요.');
		$('.errText').show();
	} else if (content.length < 10) {
		$('.errText').html('관람평을 최소 10글자 이상 입력해주세요');
		$('.errText').show();
	} else {
		fetch("regReview.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
			body: "mid=" + mid + "&"
				+ "point=" + totalPoint + "&"
				+ "vPoint=" + vPoint + "&"
				+ "content=" + content
		})
			.then(response => response.text())
			.then((data) => {
				if (data === `fail`) {
					alert(`로그인이 필요한 메뉴입니다.`);
				} else {
					alert(`성공했습니다.`);
					controlReviewForm();
					submit(1, $(".movie-sorting-right .btn.orderBtn.on").attr('data-cd'), "최신순");
				}
			})
	}
});

function isEmpty(val) {
	if (val == '' || val == undefined || val == null) {
		return true;
	}
	return false;
}

function resetReviewForm() {
	$('.box-star-score .star button').removeClass('on');
	totalPoint = 0;
	$('.box-star-score .num em').html('0');
	$('#textarea').val('');
	$('.point .box .btn').removeClass('on');
	vPoint = '';
	content = '';
	$('.textarea .count span').html('0');

	$('.errText').html('');
	$('.errText').hide();
}

// 좋아요 증가 테이블
function like(reviewID) {
	console.log(reviewID);
	if (!isLoggedIn()) {
		alert("로그인이 필요한 서비스입니다.");
		return;
	}
	sendLike(reviewID);
}

function sendLike(reviewID) {
	fetch("likeReview.do", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", },
		body: "reviewID=" + reviewID
	})
		.then(response => response.text())
		.then((data) => {
			if (data == 0) {
				alert(`실패했습니다.`);
			} else {
				alert(`좋아요 했습니다.`);
				submit($(".pagination .active").text(), $(".movie-sorting-right .btn.orderBtn.on").attr('data-cd'), $(".movie-sorting-right .btn.orderBtn.on").text());
			}
		})
}







