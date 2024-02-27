<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="./header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/movie-detail.css">
<div class="container">
	<!-- contents -->
	<div id="contents" class="">
		<div class="movie-detail-page">
			<div class="bg-img"
				style="background-image: url('https://img.megabox.co.kr/SharedImg/2024/01/31/hJbBoTOJWJtvmRHz1dlA2zcDPr3qLWhU_570.jpg');"></div>
			<div class="bg-pattern"></div>
			<div class="bg-mask"></div>

			<!-- movie-detail-cont -->
			<div class="movie-detail-cont">
				<!-- 개봉 예매가능-->
				<p class="contents-type"></p>
				<p class="title">${vo.getTitle() }</p>
				<p class="title-eng">${vo.getTitleEn() }</p>
				<div class="btn-util">
					<button type="button" title="보고싶어 안함" class="btn btn-like"
						rpst-movie-no="24001400">
						<i class="iconset ico-heart-line"></i> <span title="보고싶어 한 명수"
							id="intrstCnt"> 0 </span>
					</button>
					<!-- <div class="sns-share">
						<a href="#" class="btn btn-common-share" title="공유하기"> <i
							class="iconset ico-sns-line"></i> 공유하기 공유하기

						</a>

						<div class="btn-sns-share-wrap">
							<div class="cont-area">
								<div class="btn-sns-share-group">
									<button type="button" title="카카오톡 공유하기" class="btn btnSns kakao">카카오톡 </button> 카카오톡
									<button type="button" title="페이스북 공유하기" class="btn btnSns face">
										페이스북
										페이스북
									</button>
									<button type="button" title="밴드 공유하기" class="btn btnSns band">
										밴드
										밴드
									</button>
									<button type="button" title="트위터 공유하기"
										class="btn btnSns twitter">
										트위터
										트위터
									</button>
									<button type="button" title="링크 공유하기" class="btn btnSns link">
										링크공유
										링크공유
									</button>
								</div>
							</div>
						</div>
					</div> -->
				</div>

				<!-- info -->
				<div class="info">
					<div class="score">
						<p class="tit">실관람 평점</p>
						<div class="number gt" id="mainMegaScore">
							<!--  <div class="number lt" id="mainMegaScore">
					<p title="관람 전 점수" class="before"><em>0</em><span class="ir">점</span></p>
					<p title="관람 후 점수" class="after"><em>8.7</em><span class="ir">점</span></p> -->
							<p title="실관람 평점" class="before">
								<em>0</em><span class="ir">점</span>
							</p>
						</div>
					</div>

					<div class="rate">
						<p class="tit">예매율</p>
						<p class="cont">
							<em>${vo.getRank()}</em>위
							<!-- (0.1%) -->
						</p>
					</div>

					<div class="audience ">
						<div class="tit ">
							<span class="m-tooltip-wrap ">누적관객수 <!-- 2019-09-11 툴팁 보기 수정 -->
								<em class="m-tooltip ml05"> <i
									class="iconset ico-tooltip-gray">툴팁보기</i>
									<div class="m-detail-tooltip"
										style="display: none; opacity: 1;">
										<div class="bg-arr bottom"></div>
										<div class="cont-area">
											<p class="reset a-c">
												누적관객 및 전일관객은 영화진흥 위원회<br> 영화관 입장권 통합전산망제공 기준입니다.<br>
												(2024.02.26기준)
											</p>
										</div>
									</div>
							</em>
							</span>
						</div>
						<p class="cont">
							<em>${vo.getAudiCumWithCom()}</em> 명
						</p>
					</div>

				</div>
				<!--// info -->

				<div class="poster">
					<div class="wrap">
						<c:choose>
							<c:when test="${vo.getWatchGrade() eq '전체관람가'}">
								<p class="movie-grade age-all">,</p>
							</c:when>
							<c:when test="${vo.getWatchGrade() eq '12세이상관람가'}">
								<p class="movie-grade age-12">,</p>
							</c:when>
							<c:when test="${vo.getWatchGrade() eq '15세이상관람가'}">
								<p class="movie-grade age-15">,</p>
							</c:when>
							<c:when test="${vo.getWatchGrade() eq '19세이상관람가'}">
								<p class="movie-grade age-19">,</p>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>

						<img src="https://image.tmdb.org/t/p/w500${vo.getImage() }"
							onerror="noImg(this)" alt="${vo.getTitle() }"> <a href="#"
							class="btn-poster-down" title="포스터 다운로드" data-no="1192872"
							data-sn="1">포스터 다운로드</a>
					</div>
				</div>
				<div class="reserve screen-type col">
					<div class="reserve">
						<a href="javascript:fn_bookingForm('24001400','basic');"
							class="btn reserve" title="영화 예매하기">예매</a>
					</div>
				</div>
			</div>
			<!--// movie-detail-cont -->
		</div>
		<div id="contentData">
			<div class="inner-wrap">
				<div class="tab-list fixed">
					<ul>
						<li class="on"><a href="/on/oh/oha/Movie/selectMovieInfo.do"
							title="주요정보 탭으로 이동">주요정보</a></li>
						<li><a href="/on/oh/oha/Movie/selectMovieOneDetail.do"
							title="실관람평 탭으로 이동">실관람평</a></li>
						<li><a href="/on/oh/oha/Movie/selectMoviePostDetailC.do"
							title="무비포스트 탭으로 이동">무비포스트</a></li>
						<li><a href="/on/oh/oha/Movie/selectMovieStilDetail.do"
							title="예고편/스틸컷 탭으로 이동">예고편/스틸컷</a></li>
					</ul>
				</div>
				<div class="movie-summary infoContent" id="info">
        
            <div class="txt">
            ${vo.getOverviewInLine()}
            </div>
            
            <div class="btn-more toggle">
                <button type="button" class="btn"><span>더보기</span> <i class="iconset ico-btn-more-arr"></i></button>
            </div>
        
    </div>
			</div>
		</div>


	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/main.js"></script>