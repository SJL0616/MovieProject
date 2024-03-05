<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="./header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/movie.css">
<div class="container">
	<div class="page-util">
		<div class="inner-wrap">
			<div class="location">
				<span>Home</span> <a href="/movie" title="영화 페이지로 이동">영화</a> <a
					href="/movie" title="전체영화 페이지로 이동">전체영화</a>
			</div>


		</div>
	</div>

	<!-- contents -->
	<div id="contents" class="">
		<!-- inner-wrap -->
		<div class="inner-wrap">
			<h2 class="tit">전체영화</h2>

			<div class="tab-list fixed">
				<ul id="topMenu">
					<li class="on"><a href="/movie" title="박스오피스 탭으로 이동">박스오피스</a></li>
					<li><a href="/movie/comingsoon" title="상영예정작 탭으로 이동">상영예정작</a></li>
					<li><a href="/movie/special" title="특별상영 탭으로 이동">특별상영</a></li>
					<li><a href="/movie/film" title="필름소사이어티 탭으로 이동">필름소사이어티</a></li>
					<li><a href="/movie/classic" title="클래식소사이어티 탭으로 이동">클래식소사이어티</a></li>

				</ul>
			</div>

			<!-- movie-list-util -->
			<div class="movie-list-util mt40">
				<!-- 박스오피스 -->
				<div class="topSort" style="display: block;">
					<div class="movie-sorting sortTab">
						<!-- 						<span><button type="button" class="btn on" sort-type="ticketing">예매율순</button></span> -->
						<!-- 						<span><button type="button" class="btn" sort-type="accmAdnc">누적관객순</button></span> -->
						<!-- 						<span><button type="button" class="btn" sort-type="megaScore">메가스코어순</button></span> -->
					</div>

					<div class="onair-condition">
						<button type="button" title="개봉작만 보기" class="btn-onair btnOnAir">개봉작만</button>
					</div>
				</div>
				<!--// 박스오피스 -->

				<!-- <!-- 상영예정작
				<div class="topSort" style="display: none;">
					<div class="movie-sorting sortTab">
						<span><button type="button" class="btn on" sort-type="rfilmDe">개봉일순</button></span>
						<span><button type="button" class="btn" sort-type="title">가나다순</button></span>
					</div>
				</div>
				// 상영예정작

				특별상영
				<div class="topSort" style="display: none;">
					<div class="onair-condition">
						<button type="button" title="개봉작만 보기" class="btn-onair btnOnAir">개봉작만</button>
					</div>
				</div>
				// 특별상영

				필름소사이어티
				<div class="topSort" style="display: none;">
					<div class="movie-sorting sortTab">
						<span><button type="button" class="btn on" sort-type="ticketing" tab-cd="">전체</button></span>
						
					</div>

					<div class="onair-condition">
						<button type="button" title="개봉작만 보기" class="btn-onair btnOnAir">개봉작만</button>
					</div>
				</div>
				// 필름소사이어티

				클래식소사이어티
				<div class="topSort" style="display: none;">
					<div class="movie-sorting sortTab">
						<span><button type="button" class="btn on" sort-type="ticketing" tab-cd="">전체</button></span>
						
					</div>

					<div class="onair-condition">
						<button type="button" title="개봉작만 보기" class="btn-onair btnOnAir">개봉작만</button>
					</div>
				</div>
				// 클래식소사이어티

				장르모아보기
				<div class="topSort" style="display: none;">
					<div class="movie-sorting sortTab">
						<span><button type="button" class="btn on" sort-type="ticketing">예매율순</button></span>
						<span><button type="button" class="btn" sort-type="accmAdnc">누적관객순</button></span>
						<span><button type="button" class="btn" sort-type="megaScore">메가스코어순</button></span>
					</div>

					<div class="onair-condition">
						<button type="button" title="개봉작만 보기" class="btn-onair btnOnAir">개봉작만</button>
					</div>
				</div>
				 -->
				<!--// 장르모아보기 -->

				<!-- 검색결과 없을 때 -->
				<p class="no-result-count">
					<strong id="totCnt">1</strong>개의 영화가 검색되었습니다.
				</p>
				<!--// 검색결과 없을 때 -->

				<div class="movie-search">
					<input type="text" title="영화명을 입력하세요" id="ibxMovieNmSearch"
						name="ibxMovieNmSearch" placeholder="영화명 검색" class="input-text">
					<button type="button" class="btn-search-input" id="btnSearch">검색</button>
				</div>
			</div>
			<!--// movie-list-util -->

			<div class="bg-loading" style="display: none;">
				<div class="spinner-border" role="status">
					<span class="sr-only">Loading...</span>
				</div>
			</div>

			<!-- movie-list -->
			<div class="movie-list">
				<ol class="list" id="movieList">
					<c:forEach var="vo" items="${list}">
						<li tabindex="0" class="no-img"><div class="movie-list-info">
								<p class="rank" style="">
									<span class="ir">${vo.getRank() }</span>
								</p>
								<img
									src="https://image.tmdb.org/t/p/w500${vo.getImage() }" 
									alt="듄: 파트2" class="poster lozad" onerror="noImg(this)">

								<div class="movie-score">
									<a href="${ctx}/movie-detail.do?id=${vo.getMovieID()}" class="wrap movieBtn" 
										title="${vo.getTitle()} 상세보기">
										<div class="summary">${vo.getOverview()}</div>
										<div class="my-score equa" style="display: none;">
											<div class="preview">
												<p class="tit">관람평</p>
												<p class="number">
													0<span class="ir">점</span>
												</p>
											</div>
										</div>
									</a>
								</div>
							</div>
							<div class="tit-area">
							<c:choose>
							<c:when test="${vo.getWatchGrade() eq '전체관람가'}" >
							<p class="movie-grade age-all">,</p>
							</c:when>
							<c:when test="${vo.getWatchGrade() eq '12세이상관람가'}" >
							<p class="movie-grade age-12">,</p>
							</c:when>
							<c:when test="${vo.getWatchGrade() eq '15세이상관람가'}" >
							<p class="movie-grade age-15">,</p>
							</c:when>
							<c:when test="${vo.getWatchGrade() eq '19세이상관람가'}" >
							<p class="movie-grade age-19">,</p>
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
							
								
								<p title="듄: 파트2" class="tit">${vo.getTitle() }</p>
							</div>
							<div class="rate-date">
								<span class="rate">예매율 46.9%</span> <span class="date">개봉일
									${vo.getOpenDotDate() }</span>
							</div>
							<div class="btn-util">
								<button type="button" class="button btn-like" data-no="23095500">
									<i title="보고싶어 안함"
										class="iconset ico-heart-toggle-gray intrstType"></i> <span></span>
								</button>
								<p class="txt movieStat1" style="display: none">상영예정</p>
								<p class="txt movieStat2" style="display: none">2월 개봉예정</p>
								<p class="txt movieStat5" style="display: none">개봉예정</p>
								<p class="txt movieStat6" style="display: none">상영종료</p>
								<div class="case col-2 movieStat3" style="">
									<a href="javascript:moveticketting('${vo.getMovieID()}')" class="button purple bokdBtn" data-no="23095500"
										title="영화 예매하기">예매</a> <a href="#"
										class="button purple img splBtn" data-no="23095500"> <!-- <img src="/static/pc/images/common/btn/mov_list_db_btn.png" alt="dolby 버튼"> -->
									</a>
								</div>
								<div class="case movieStat4" style="display: none">
									<a href="javascript:moveticketting('${vo.getMovieID()}')" class="button purple bokdBtn" data-no="23095500"
										title="영화 예매하기">예매</a>
								</div>
							</div>
							</li>

					</c:forEach>

				</ol>
			</div>
			<!--// movie-list -->

			<div class="btn-more v1" id="addMovieDiv" style="display: none;">
				<button type="button" class="btn" id="btnAddMovie">
					더보기 <i class="iconset ico-btn-more-arr"></i>
				</button>
			</div>

			<!-- 검색결과 없을 때 -->
			<div class="movie-list-no-result" id="noDataDiv"
				style="display: none;">
				<p>현재 상영중인 영화가 없습니다.</p>
			</div>


			<!-- 검색결과 없을 때 -->
			<div class="movie-list-no-favor" id="noMyGenre"
				style="display: none;">
				<p>선호장르가 등록되지 않았습니다. 선호하시는 장르를 등록해보세요.</p>
				<div class="btn-group center">
					<a href="/mypage/additionalinfo" class="button large purple"
						title="선호장르설정하기 페이지로 이동">선호장르설정하기</a>
				</div>
			</div>


		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/movie.js"></script>