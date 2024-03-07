<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/movie/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Font Awesome 5 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	
<!-- ticketing.jsp -->
<link rel="stylesheet" href="${ctx}/css/ticketing.css" />
<script defer src="${ctx}/js/ticketting.js"></script>
<link rel="stylesheet" href="${ctx}/css/modallogin.css" />

<!-- NAVER Maps API -->
<script type="text/javascript"
	src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=lmbt0p4wmz"></script>
<!-- movietheater.jsp -->
<link rel="stylesheet" href="${ctx}/css/movietheater.css" />

<!-- seat.jsp -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="${ctx}/css/seat.css" />

<div class="body-iframe" align="center">
<input class="move" type="hidden" value="${move ne null? move : 'exist'}">
<input class="userID" type="hidden" value="${log ne null? log : 'exist'}">
	<div class="tit-util">
		<h2 class="tit">빠른예매</h2>
	</div>
	<div class="ticket">
		<div class="wraps">
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
								<button class="btn" movie-nm="${list.title}"
									movie-no="${list.movieID}"
									img-path="https://image.tmdb.org/t/p/w500${list.image}"
									movie-type="${list.showTypes}" movie-time="${list.showTime}"
									movie-ctx= "${ctx}" movie-age="${fn:substring(list.watchGrade,0,2) == '전체'? 'all' : fn:substring(list.watchGrade,0,2)}"
									>
									<span
										class="movie-grade age-${fn:substring(list.watchGrade,0,2) == '전체'? 'all' : fn:substring(list.watchGrade,0,2)}">${fn:substring(list.watchGrade,0,2) == '전체'? 'All' : fn:substring(list.watchGrade,0,2)}</span>
									<span class="txt">${list.title}</span>
								</button>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="view-area">
					<div class="choice-all">
						<strong>모든영화</strong> <span>목록에서 영화를 선택하세요.</span>
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
						<c:forEach var="i" begin="9" end="25">
							<button id="btn-time" data-time="${i >= 10? (i >= 24? i % 24 : i) : 0+(i >= 25? i % 24 : i)}">${i}</button>
						</c:forEach>
					</div>
					<button title="다음 날짜 보기" class="time-next">
						<i class="fa fa-chevron-right"></i>
					</button>
				</div>
				<div class="movie-shedule-area">
					<ul>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="modal">
      <div class="loginPage">
        <div class="loginForm">
          <div class="title">로그인</div>
          <div class="formBody">
            <div class="leftSection">
              <form class="realForm" action="${ctx}/login.do" method="post">
                <input
                  id="id"
                  maxlength="20"
                  type="text"
                  placeholder="아이디"
                  required="required"
                />
                <input
                  id="pw"
                  maxlength="20"
                  type="password"
                  placeholder="비밀번호"
                  required="required"
                />
                <br />
                <button id="loginBtn" type="button" onclick="validCheck(form)">
                  로그인
                </button>
              </form>
              <div class="otherWays">
                <div class="link">
                  <a href="#" title="ID/PW 찾기 선택"
                    >ID/PW 찾기<!--ID/PW 찾기--></a
                  >
                  <a
                    href="${ctx}/userJoin.do"
                    title="회원가입 선택"
                    id="userJoinBtn"
                    >회원가입<!--회원가입--></a
                  >
                  <a href="#" title="비회원 예매확인 선택"
                    >비회원 예매확인<!--비회원 예매확인--></a
                  >
                </div>
                <a
                  id="kakao-login-btn"
                  href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.do"
                >
                  <img
                    src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
                    width="200"
                    height="50"
                    alt="카카오 로그인 버튼"
                  />
                </a>
                <a
                  id="naver-login-btn"
                  href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=SrIGsH8XIxmfND4_eTEH&state=STATE_STRING&redirect_uri=http://localhost:8085/MovieProject/naverLoginResult.do"
                >
                  <img
                    src="./img/naver_login.png"
                    width="200"
                    height="50"
                    alt="네이버 로그인 버튼"
                  />
                </a>
              </div>
            </div>
            <div class="rightSection">
              <img
                alt="광고 이미지"
                src="${ctx}/img/login_page_advertisement.jpg"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="overlay"></div>
</div>

<%@include file="/WEB-INF/movie/footer.jsp"%>