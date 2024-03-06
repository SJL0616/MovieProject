let sessionLog = null;
$(document).ready(function() {
	//데이트피커 초기화let
	sessionLog = document.getElementById("session").value;
}); // document ready function

function toMyMega() {
	if(isLoggedIn()){
	    location.href = "http://localhost:8085/MovieProject/userMyMega.do";
	}else{
		alert(`로그인이 필요한 메뉴입니다.`);
	}
}

function isLoggedIn(){
	console.log("현재 세션값 : "+sessionLog);
	if(sessionLog != '' && sessionLog != undefined && sessionLog != null){
	    return true;
	}else{
		return false;
	}
}