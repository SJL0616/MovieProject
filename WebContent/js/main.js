/**
 * 
 */
let searchEvent = false;
$(document).ready(function() {
	//데이트피커 초기화
	searchBarInit();
}); // document ready function

function searchBarInit(){
	document.getElementById("movieName").addEventListener("keypress",function(event){
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
	let keyword = document.getElementById("movieName").value.trim();
	console.log(keyword +" "+ keyword.length);
	
	const keywords = keyword.split(/\s+/);
	console.log(keywords);
	console.log(JSON.stringify(keywords));
	let ctx = "${ctx}";
	
	let form = document.createElement("form");
	
	form.setAttribute("charset", "UTF-8");
	form.setAttribute("method", "Post");  //Post 방식
	form.setAttribute("action", "/MovieProject/movieSearchFromMain.do"); //요청 보낼 주소
	
	hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "keywords");
	hiddenField.setAttribute("value", JSON.stringify(keywords));
	form.appendChild(hiddenField);
	
	hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "fromIndex");
	hiddenField.setAttribute("value", "true");
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
	
}