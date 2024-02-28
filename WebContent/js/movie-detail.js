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
		keyNum+=1;
	});
players = Array.from(document.querySelectorAll(".player"));
videoOffSet();
}

function videoOffSet(){
	let num = 0;
	players.forEach(e =>{
		if(num == trailderNum){
			e.style.display ="block";
		}else{
			e.style.display ="none";
		}
		num++;
	});
	
}
function showPre() {
	console.log("showNext");
	trailderNum = trailderNum-1 >-1 ? trailderNum-1  : players.length-1;
	videoOffSet();
}
function showNext() {
	console.log("showNext");
	trailderNum = trailderNum+1 < players.length ? trailderNum+1  : 0;
	videoOffSet();
}

