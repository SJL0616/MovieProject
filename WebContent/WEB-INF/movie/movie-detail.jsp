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
						<li class="on"><a href="javascript:void(0);"
							onclick='showMain(event)' title="주요정보 탭으로 이동">주요정보</a></li>
						<li><a href="javascript:void(0);"
							onclick='showComments(event)' title="실관람평 탭으로 이동">실관람평</a></li>
						<li><a href="#" title="무비포스트 탭으로 이동">무비포스트</a></li>
						<li><a href="javascript:void(0);"
							onclick='showTrailer(event)' title="예고편/스틸컷 탭으로 이동">예고편/스틸컷</a></li>
					</ul>
				</div>
				<div class="movie-summary infoContent" id="info">

					<div class="txt">${vo.getOverviewInLine()}</div>

					<!-- <div class="btn-more toggle">
						<button type="button" class="btn">
							<span>더보기</span> <i class="iconset ico-btn-more-arr"></i>
						</button>
					</div> -->

				</div>
				<div class="movie-info infoContent">
					<p>상영타입 : ${vo.getShowTypes() }</p>

					<div class="line">
						<p>감독&nbsp;: ${vo.getDirector() }</p>
						<p>장르&nbsp;: ${vo.getGenre() } / ${vo.getShowTime() } 분</p>
						<p>등급&nbsp;: ${vo.getWatchGrade() }</p>

						<p>개봉일&nbsp;: ${vo.getOpenDotDate() }</p>


						<!--           -->
					</div>
					<p>출연진&nbsp;: ${vo.getActors() }</p>
				</div>
				<div class="videoContainer">
					<h2 class="tit">
						<span id="movieTitle">티저 예고편</span>
					</h2>
					<div class="slide-btn">
						<a href="javascript:void(0);" onclick='showPre()' title="이전 영상"
							class="videoBtn btn-prev swiper-button-prev swiper-button-disabled"
							tabindex="0" role="button" aria-label="Previous slide"
							aria-disabled="true"> <i class="iconset ico-prev-circle"></i>
							<span>이전 영상</span>
						</a> <a href="javascript:void(0);" onclick='showNext()' title="다음 영상"
							class="videoBtn btn-next swiper-button-next" tabindex="0"
							role="button" aria-label="Next slide" aria-disabled="false">
							<i class="iconset ico-next-circle"></i> <span>다음 영상</span>
						</a>
					</div>

					<div
						class="swiper-container gallery-top swiper-container-fade swiper-container-initialized swiper-container-horizontal">
						<div class="swiper-wrapper">

							<div class="swiper-slide swiper-slide-active"
								style="width: 800px; opacity: 1; transform: translate3d(0px, 0px, 0px);">
							
								<c:forEach var="src" items="${vo.getTrailer()}" varStatus="status">
									<input type="text" hidden="true" value="${src}"
										class="videoKey">
									<div id="player<c:out value="${status.count}"/>" class="player"></div>
								</c:forEach>
							</div>
						</div>
					</div>
				
					


				</div>



				<div class="tit-util mt70 mb15 oneContent">
					<h2 class="tit small">
						${vo.getTitle() }에 대한 <span class="font-gblue">0</span>개의 이야기가
						있어요!
					</h2>
					<div class="right">
						<a href="#saw_movie_regi" class="button btn-modal-open"
							id="regBtn" w-data="600" h-data="470" title="본 영화 등록">본 영화 등록</a>
					</div>
				</div>
				<div class="movie-list-util mt30 oneContent">
					<div class="movie-sorting">
						<span><button type="button" class="btn sortBtn on"
								data-cd="">
								전체 <em class="font-gblue" id="cnt1">11,420</em> 건
							</button></span>
					</div>

					<div class="movie-sorting-right">
						<span><button type="button" class="btn orderBtn on"
								data-cd="">최신순</button></span> <span><button type="button"
								class="btn orderBtn" data-cd="one">공감순</button></span> <span><button
								type="button" class="btn orderBtn" data-cd="star">평점순</button></span>
					</div>
				</div>


				<div class="movie-idv-story oneContent">
					<ul>
						<!-- 로그인이 안되있을시 -->
						<li class="type03">
							<div class="story-area">
								<!-- 프로필영역 -->
								<div class="user-prof">
									<div class="prof-img">
										<img
											src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-mega-profile.png"
											alt="MEGABOX">
									</div>
									<p class="user-id">MEGABOX</p>
								</div>
								<!-- // 프로필영역 -->

								<!-- 내용 영역 -->
								<div class="story-box">
									<div class="story-wrap">
										<div class="story-cont">
											<span class="font-gblue">${vo.getTitle() }</span> 재미있게 보셨나요?
											영화의 어떤 점이 좋았는지 이야기해주세요.
										</div>

										<div class="story-write">
											<a href="#tooltip-layer01" title="관람평쓰기"
												class="tooltip-click oneWrtNonMbBtn"><i
												class="iconset ico-story-write"></i> 관람평쓰기</a>
											<div id="tooltip-layer01" class="tooltip-cont"
												style="width: 225px; height: 80px;">
												<div class="wrap loginTagClick">
													로그인이 필요한 서비스 입니다.<br> <a
														href="javaScript:fn_viewLoginPopup('default','pc');"
														class="font-green" title="로그인 바로가기">로그인 바로가기 <i
														class="iconset ico-arr-right-green"></i></a>
													<button type="button" class="btn-close-tooltip">툴팁
														닫기</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- // 내용 영역 -->
							</div>
						</li>
						<li class="type01 oneContentTag">
							<div class="story-area">
								<div class="user-prof">
									<div class="prof-img">
										<img
											src="https://www.megabox.co.kr/static/pc/images/mypage/bg-photo.png"
											alt="프로필 사진" title="프로필 사진" onerror="noImg(this, 'human')">
									</div>
									<p class="user-id">invu3**</p>
								</div>
								<div class="story-box">
									<div class="story-wrap review">
										<div class="tit">관람평</div>
										<div class="story-cont">
											<div class="story-point">
												<span>9</span>
											</div>
											<div class="story-txt">기억과 상처를 애니메이션으로 풀어내다니..</div>
											<div class="story-like">
												<button type="button" class="oneLikeBtn" title="댓글 추천"
													data-no="2725453" data-is="N">
													<i class="iconset ico-like-gray"></i> <span>0</span>
												</button>
											</div>
											<div class="story-util">
												<div class="post-funtion">
													<!-- <div class="wrap">
														<button type="button" class="btn-alert">옵션보기</button>
														<div class="balloon-space user">
															<div class="balloon-cont">
																<div class="user">
																	<p class="reset a-c">
																		스포일러 및 욕설/비방하는<br>내용이 있습니까?
																	</p>
																	<button type="button" class="maskOne" data-no="2725453">
																		댓글 신고 <i class="iconset ico-arr-right-green"></i>
																	</button>
																</div>
																<div class="btn-close">
																	<a href="#" title="닫기"><img
																		src="/static/pc/images/common/btn/btn-balloon-close.png"
																		alt="닫기"></a>
																</div>
															</div>
														</div>
													</div> -->
												</div>
											</div>
										</div>
									</div>


								</div>
							</div>
							<div class="story-date">
								<div class="review on">
									<span>1 시간전</span>
								</div>
							</div>
						</li>
					</ul>

				</div>
			</div>
		</div>


	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/movie-detail.js"></script>