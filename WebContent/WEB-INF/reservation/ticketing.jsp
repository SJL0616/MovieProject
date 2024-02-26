<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/movie/header.jsp"%>

 <body class="body-iframe">
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
              <li>
                <button class="btn disabled" movie-nm="파묘" movie-no="1">
                  <span class="movie-grade age-15">15</span>
                  <span class="txt">파묘</span>
                </button>
              </li>
              <li>
                <button class="btn disabled" movie-nm="파묘" movie-no="2">
                  <span class="movie-grade age-12">12</span>
                  <span class="txt">듄 : 파트2</span>
                </button>
              </li>
              <li>
                <button class="btn disabled" movie-nm="파묘" movie-no="3">
                  <span class="movie-grade age-all">All</span>
                  <span class="txt">웡카</span>
                </button>
              </li>
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
              <button id="btn-time">1</button><button id="btn-time">2</button
              ><button id="btn-time">3</button><button id="btn-time">4</button
              ><button id="btn-time">5</button><button id="btn-time">6</button
              ><button id="btn-time">7</button><button id="btn-time">8</button
              ><button id="btn-time">9</button><button id="btn-time">10</button
              ><button id="btn-time">11</button><button id="btn-time">12</button
              ><button id="btn-time">13</button><button id="btn-time">14</button
              ><button id="btn-time">15</button><button id="btn-time">16</button
              ><button id="btn-time">17</button><button id="btn-time">18</button
              ><button id="btn-time">19</button><button id="btn-time">20</button
              ><button id="btn-time">21</button><button id="btn-time">22</button
              ><button id="btn-time">23</button><button id="btn-time">24</button
              ><button id="btn-time">25</button>
            </div>
            <button title="다음 날짜 보기" class="time-next">
              <i class="fa fa-chevron-right"></i>
            </button>
          </div>
          <div class="movie-shedule-area">
            <ul>
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
                      "남양주현대 아울렛 스페이스 원"
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
                      "남양주현대 아울렛 스페이스 원"
                    </span>
                  </div>
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
<script>
  $(".wrap-list").slick({
    slidesToShow: 14,
    slidesToScroll: 1,
    autoplay: false,
    autoplaySpeed: 2000,
    infinite: false,
    prevArrow: $(".btn-pre"),
    nextArrow: $(".btn-next"),
  });
  $(".time-list").slick({
    slidesToShow: 9,
    slidesToScroll: 1,
    autoplay: false,
    autoplaySpeed: 2000,
    infinite: false,
    prevArrow: $(".time-pre"),
    nextArrow: $(".time-next"),
  });
</script>