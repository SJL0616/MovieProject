<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<div class="container">
      <div class="ticket-true">
        <h2>티켓 취소 리스트</h2>
        <div class="ticket-table">
          <ul class="title">
            <li>코드</li>
            <li>유저</li>
            <li>인원수</li>
            <li>관람날짜</li>
            <li>영화관</li>
            <li>영화</li>
            <li>결제날짜</li>
            <li>총금액</li>
            <li>취소</li>
          </ul>
          <c:forEach var="list" items="${userList}">
	          <ul>
	            <li>${list.ticketID}</li>
	            <li>${list.userID}</li>
	            <li>${list.numberPeople}</li>
	            <li>${list.previewDate}</li>
	            <li>${list.movieName}</li>
	            <li>${list.title}</li>
	            <li>${list.paymentDate}</li>
	            <li>${list.ticketPrice}</li>
	            <li><button>취소</button></li>
	          </ul>
          </c:forEach>
        </div>
      </div>
      <div class="review-true">
        <h2>리뷰 신고 리스트</h2>
        <div class="review-table">
          <ul class="title">
            <li>유저</li>
            <li>내용</li>
            <li>등록일</li>
            <li>경과시간</li>
            <li>점수</li>
            <li>좋아요수</li>
            <li>삭제</li>
          </ul>
          <c:forEach var="list" items="${reviewList}">
	          <ul>
	            <li>${list.reviewID}</li>
	            <li>${list.content}></li>
	            <li>${list.regDate}</li>
	            <li>${list.elapsedTime}</li>
	            <li>${list.pint}</li>
	            <li>${list.like}</li>
	            <li><button>삭제</button></li>
	          </ul>
          </c:forEach>
        </div>
      </div>
    </div>
<%@include file="./footer.jsp"%>