<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/movie/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- Font Awesome 5 -->
<link
   rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
/>
<!-- ticketing.jsp -->
<link rel="stylesheet" href="${ctx}/css/ticketing.css" />
<script defer src="${ctx}/js/ticketting.js"></script>

 <div class="body-iframe" align="center">
    <div class="tit-util">
      <h2 class="tit">빠른예매</h2>
    </div>
    <div class="ticket">
      <div class="wrap">
        <button title="이전 날짜 보기" class="btn-pre">
          <i class="fa fa-chevron-left"></i>
        </button>
        <div class="date-list">
          <!-- <div class="year-area">
          <div class="year">2024.02</div>
          <div class="year">2024.03</div>
        </div> -->
          <div class="date-area" id="formDeList">
            <div class="wrap-list" id="wrap-list">
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
              <button id="date-list"></button>
            </div>
          </div>
        </div>
        <button title="다음 날짜 보기" class="btn-next">
          <i class="fa fa-chevron-right"></i>
        </button>
      </div>
      <div class="quick-reserve-area">
        <div class="movie-choice">
          <p>영화 리스트</p>
          <div class="list">
            <ul>
            <c:forEach var="list" items="${list}">
              <li>
                <button class="btn disabled" movie-nm="${list.title}" movie-no="${list.movieID}" img-path="https://image.tmdb.org/t/p/w500${list.image}">
                  <span class="movie-grade age-${fn:substring(list.watchGrade,0,2) == '전체'? 'all' : fn:substring(list.watchGrade,0,2)}">${fn:substring(list.watchGrade,0,2) == '전체'? 'All' : fn:substring(list.watchGrade,0,2)}</span>
                  <span class="txt">${list.title}</span>
                </button>
              </li>
              </c:forEach>
            </ul>
          </div>
          <div class="view-area">
            <div class="choice-all">
              <strong>모든영화</strong>
              <span>목록에서 영화를 선택하세요.</span>
            </div>
            <div class="choice-list">
              <div class="bg"></div>
              <div class="bg"></div>
              <div class="bg"></div>
            </div>
          </div>
        </div>
        <div class="time-choice">
          <div class="time-area">
            <p>시간</p>
          </div>
          <div class="time-button">
            <button title="이전 날짜 보기" class="time-pre">
              <i class="fa fa-chevron-left"></i>
            </button>
            <div class="time-list">
            <c:forEach var="i" begin="1" end="25">
              <button id="btn-time" data-time="${i >= 10? i : 0+i}">${i}</button>
              </c:forEach>
            </div>
            <button title="다음 날짜 보기" class="time-next">
              <i class="fa fa-chevron-right"></i>
            </button>
          </div>
          <div class="movie-shedule-area">
            <ul>
              <li>
                <button class="btn" play-start-time="1615" onclick="location.href='${ctx}/movietheater.do'">
                  <div class="legend"></div>
                  <span class="time">
                    <strong title="상영 시작">16:15</strong>
                    <em title="상영 종료">~19:08</em>
                  </span>
                  <span class="title">
                    <strong title="블레이드 러너">블레이드 러너 2049</strong>
                    <em>2D Doldy(자막)</em>
                  </span>
                  <div class="info">
                    <span class="theater" title="극장">
                    </span>
                  </div>
                </button>
              </li>
              <li>
                <button class="btn" play-start-time="1615">
                  <div class="legend"></div>
                  <span class="time">
                    <strong title="상영 시작">16:15</strong>
                    <em title="상영 종료">~19:08</em>
                  </span>
                  <span class="title">
                    <strong title="블레이드 러너">블레이드 러너 2049</strong>
                    <em>2D Doldy(자막)</em>
                  </span>
                  <div class="info">
                    <span class="theater" title="극장">
                    </span>
                  </div>
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</html>