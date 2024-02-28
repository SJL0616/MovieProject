<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/movie/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
      integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link rel="stylesheet" href="${ctx}/css/seat.css" />
    <script defer src="${ctx}/js/seat.js"></script>
<div class="sort">
      <div class="seat-select-section">
        <div class="seat-section">
          <div class="tit-util">
            <h3 class="tit small">관람인원선택</h3>
            <div class="right">
              <button class="buttons">
                <i class="fa fa-refresh"></i>
                초기화
              </button>
            </div>
          </div>
          <div class="seat-area">
            <div class="seat-count">
              <div class="cell">
                <p class="txt">성인</p>
                <div class="count">
                  <button class="down" title="성인 좌석 감소">-</button>
                  <div class="number">
                    <p>0</p>
                  </div>
                  <button class="up" title="성인 좌석 증가">+</button>
                </div>
              </div>
              <div class="cell">
                <p class="txt">청소년</p>
                <div class="count">
                  <button class="down" title="청소년 좌석 감소">-</button>
                  <div class="number">
                    <p>0</p>
                  </div>
                  <button class="up" title="청소년 좌석 증가">+</button>
                </div>
              </div>
              <div class="cell">
                <p class="txt">우대</p>
                <div class="count">
                  <button class="down" title="우대 좌석 감소">-</button>
                  <div class="number">
                    <p>0</p>
                  </div>
                  <button class="up" title="우대 좌석 증가">+</button>
                </div>
              </div>
            </div>
            <div class="seat-layout">
              <div class="seatList">
                <img
                  src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"
                  alt=""
                />
                <div class="seat-wrapper"></div>
              </div>
            </div>
          </div>
          <div class="seat-result">
            <div class="wrap">
              <div class="tit-area">
                <div class="movie-grade">
                  <span class="age-12">12</span>
                </div>
                <div class="title">
                  <p class="tit">파묘</p>
                  <p class="cate">2D</p>
                </div>
              </div>
              <div class="info-area">
                <div class="info">
                  <p class="theater">광명</p>
                  <!--해당하는 영화관-->
                  <p class="special">뭐어쩌고저쩌고</p>
                  <p class="date">
                    <span>2024.02.28</span>
                    <em>(수)</em>
                  </p>
                </div>
                <div class="poster">
                  <img
                    src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div class="choice-seat-area">
                <div class="seat-num">
                  <p class="tit">선택좌석</p>
                  <div class="my-seat">
                    <div class="seats choice">-</div>
                    <div class="seats all">-</div>
                    <div class="seats all">-</div>
                    <div class="seats all">-</div>
                    <div class="seats all">-</div>
                    <div class="seats all">-</div>
                    <div class="seats all">-</div>
                    <div class="seats all">-</div>
                  </div>
                </div>
              </div>
              <div class="pay-area">
                <p class="count">
                  <span>
                    청소년
                    <em>1</em>
                  </span>
                </p>
                <div class="pay">
                  <p class="tit">최종결제금액</p>
                  <div class="money">
                    <em>7000</em>
                    <span>원</span>
                  </div>
                </div>
              </div>
              <div class="btn-group">
                <a href="" class="button" id="pageprevious">이전</a>
                <a href="" class="button active" id="pageNext">다음</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</html>