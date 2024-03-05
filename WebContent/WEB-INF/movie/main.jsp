<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="./header.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
<div class="main_movie">
	<div class="background">
		<div class="bg-pattern"></div>
		<div class="content">
			<div class="tab-sorting">
				<button type="button" class="on" sort="boxoRankList" name="btnSort">박스오피스</button>
			</div>
			<a href="${ctx}/movie.do" class="more-movie" title="더 많은 영화보기"> 더 많은 영화보기
				<i class="iconset ico-more-corss gray"></i>
			</a>
			<div class="main-movie-list">
				<ol class="list">
					<c:forEach var="vo" items="${list}">
						<li name="li_boxoRankList" class="first">
							<!-- javascript:gfn_moveDetail('24004100') --> 
							<a href="${ctx}/movie-detail.do?id=${vo.getMovieID()}"
							class="movie-list-info" title="영화상세 보기"> <!-- <div class="screen-type2">
													<p><img src="/static/pc/images/common/btn/mov_top_tag_db.png" alt="dolby"></p>
													<p><img src="/static/pc/images/common/btn/mov_top_tag_atmos.png" alt="atmos"></p>
												</div> -->
								<p class="rank">${vo.getRank()}<span class="ir"></span>
								</p> <!-- to 개발 : alt 값에 영화 제목 출력 --> <img
								src="https://image.tmdb.org/t/p/w500${vo.getImage() }" alt="파묘"
								class="poster" onerror="noImg(this, 'main');" />
								<div class="wrap">
									<div class="summary">${vo.getOverview()}</div>
									<div class="score">
										<div class="preview">
											<p class="tit">관람평</p>
											<p class="number">
												<span class="ir">점</span>
											</p>
										</div>
									</div>
								</div>
						</a>
							<div class="btn-util">
								<button type="button" class="button btn-like"
									rpst-movie-no="${vo.getMovieID()}">
									<i title="보고싶어 설정 안함" class="iconset ico-heart-toggle-gray"></i>

								</button>
								<div class="case">
									<!-- 개봉 예매가능 기본-->
									<a href="javascript:moveticketting('${vo.getMovieID()}')"
										class="button gblue" title="영화 예매하기">예매</a>
								</div>
							</div>
						</li>
					</c:forEach>
					<!-- 3개의 list를  loop 한다-->
					<!-- 박스오피스 시작 -->
					<!-- 각 map 별 첫번째 li 에 first클래스 추가 -->

					<!-- 각 map 별 첫번째 li 에 first클래스 추가 -->


					<!-- <!-- 	/*
									각 map 별 첫번째 li 에 first클래스 추가

										각 map 별 첫번째 li 에 first클래스 추가
										<li name="li_boxoRankList"  class="">
											<a href="javascript:gfn_moveDetail('23095500')" class="movie-list-info" title="영화상세 보기">
												<div class="screen-type2">
													<p><img src="/static/pc/images/common/btn/mov_top_tag_db.png" alt="dolby"></p>
													<p><img src="/static/pc/images/common/btn/mov_top_tag_atmos.png" alt="atmos"></p>
													</div>
												<p class="rank">2<span class="ir">위</span></p>
												to 개발 : alt 값에 영화 제목 출력
										      	<img src="https://img.megabox.co.kr/SharedImg/2024/02/07/LVp8bCwlBWpI9BHacDmo6Vun9nm5n9PW_420.jpg" alt="듄: 파트2" class="poster" onerror="noImg(this, 'main');"/>
							                      	<div class="wrap">
													<div class="summary">
														<br>황제의 모략으로 멸문한 가문의 유일한 후계자 폴.(티모시 샬라메)<br>어머니 레이디 제시카(레베카 퍼거슨)와 간신히 목숨만 부지한 채 사막으로 도망친다.<br>그곳에서 만난 반란군들과 숨어 지내다 그들과 함께 황제의 모든 것을 파괴할 전투를 준비한다.<br>한편 반란군들의 기세가 높아질수록 불안해진 황제와 귀족 가문은<br>잔혹한 암살자 페이드 로타(오스틴 버틀러)를 보내 반란군을 몰살하려 하는데&hellip;<br><br>2월, 운명의 반격이 시작된다!</div>
													
														관람 전이 더 높을 때
														<div class="my-score small">

														관람 후가 더 높을 때
														<div class="my-score big">

														관람 후가 더 같을 때
														<div class="my-score equal">
													
													<div class="score">
														<div class="preview">
															<p class="tit">관람평</p>
															<p class="number">0<span class="ir">점</span></p>
														</div>
													</div>
												</div>
											</a>
											<div class="btn-util">
                                                <button type="button" class="button btn-like" rpst-movie-no="23095500">
													<i title="보고싶어 설정 안함" class="iconset ico-heart-toggle-gray"></i>
														2.3k
														</button>
                                                <div class="case">
                                                    개봉 예매가능 기본
                                                            <a href="javascript:moveBokdPage('23095500');" class="button gblue" title="영화 예매하기">예매</a>
                                                        </div>
                                                </div>
										</li>
									각 map 별 첫번째 li 에 first클래스 추가

										각 map 별 첫번째 li 에 first클래스 추가
										<li name="li_boxoRankList"  class="">
											<a href="javascript:gfn_moveDetail('23094400')" class="movie-list-info" title="영화상세 보기">
												<div class="screen-type2">
													<p><img src="/static/pc/images/common/btn/mov_top_tag_atmos.png" alt="atmos"></p>
													</div>
												<p class="rank">3<span class="ir">위</span></p>
												to 개발 : alt 값에 영화 제목 출력
										      	<img src="https://img.megabox.co.kr/SharedImg/2024/01/03/MQNvJyuH0RrzlhmgIOiB5RO1QwfKXZXO_420.jpg" alt="웡카" class="poster" onerror="noImg(this, 'main');"/>
							                      	<div class="wrap">
													<div class="summary">
														세상에서 가장 달콤한 여정<br>좋은 일은 모두 꿈에서부터 시작된다!<br><br>마법사이자 초콜릿 메이커 &lsquo;윌리 웡카&rsquo;의 꿈은<br>디저트의 성지, &lsquo;달콤 백화점&rsquo;에 자신만의 초콜릿 가게를 여는 것.<br>가진 것이라고는 낡은 모자 가득한 꿈과 단돈 12소버린 뿐이지만<br>특별한 마법의 초콜릿으로 사람들을 사로잡을 자신이 있다. <br><br>하지만 먹을 것도, 잠잘 곳도, 의지할 사람도 없는 상황 속에서<br>낡은 여인숙에 머물게 된 &lsquo;웡카&rsquo;는 &lsquo;스크러빗 부인&rsquo;과 &lsquo;블리처&rsquo;의 계략에 빠져<br>눈더미처럼 불어난 숙박비로 인해 순식간에 빚더미에 오른다.<br>게다가 밤마다 초콜릿을 훔쳐가는 작은 도둑 &lsquo;움파 룸파&rsquo;의 등장과<br>&lsquo;달콤 백화점&rsquo;을 독점한 초콜릿 카르텔의 강력한 견제까지.<br>세계 최고의 초콜릿 메이커가 되는 길은 험난하기만 한데&hellip;</div>
													
														관람 전이 더 높을 때
														<div class="my-score small">

														관람 후가 더 높을 때
														<div class="my-score big">

														관람 후가 더 같을 때
														<div class="my-score equal">
													
													<div class="score">
														<div class="preview">
															<p class="tit">관람평</p>
															<p class="number">9<span class="ir">점</span></p>
														</div>
													</div>
												</div>
											</a>
											<div class="btn-util">
                                                <button type="button" class="button btn-like" rpst-movie-no="23094400">
													<i title="보고싶어 설정 안함" class="iconset ico-heart-toggle-gray"></i>
														3k
														</button>
                                                <div class="case">
                                                    개봉 예매가능 기본
                                                            <a href="javascript:moveBokdPage('23094400');" class="button gblue" title="영화 예매하기">예매</a>
                                                        </div>
                                                </div>
										</li>
									각 map 별 첫번째 li 에 first클래스 추가

										각 map 별 첫번째 li 에 first클래스 추가
										<li name="li_boxoRankList"  class="">
											<a href="javascript:gfn_moveDetail('24003500')" class="movie-list-info" title="영화상세 보기">
												<div class="screen-type2">
													</div>
												<p class="rank">4<span class="ir">위</span></p>
												to 개발 : alt 값에 영화 제목 출력
										      	<img src="https://img.megabox.co.kr/SharedImg/2024/01/29/m5ODMBJ1i356guPkTQaGNAxarrwWUz1F_420.jpg" alt="건국전쟁" class="poster" onerror="noImg(this, 'main');"/>
							                      	<div class="wrap">
													<div class="summary">
														<br>1945년 해방 이후 남과 북은 서로 다른 길을 걸어 왔다. 자유를 억압하고 인권을 탄압하는 공산주의 독재 국가 북한과 자유와 민주주의에 기초한 경제 번영과 선진국의 길로 들어선 대한민국. 두 나라는 같은 언어, 역사, 인종을 공유하면서 어떻게 극단적인 두 나라로 갈라졌을까? 지난 70년 역사를 통해서 오늘의 대한민국을 만들고 지켜내기 위해 노력했던 이승만 대통령과 건국1세대들의 희생과 투쟁을 조명한 작품.</div>
													
														관람 전이 더 높을 때
														<div class="my-score small">

														관람 후가 더 높을 때
														<div class="my-score big">

														관람 후가 더 같을 때
														<div class="my-score equal">
													
													<div class="score">
														<div class="preview">
															<p class="tit">관람평</p>
															<p class="number">9.3<span class="ir">점</span></p>
														</div>
													</div>
												</div>
											</a>
											<div class="btn-util">
                                                <button type="button" class="button btn-like" rpst-movie-no="24003500">
													<i title="보고싶어 설정 안함" class="iconset ico-heart-toggle-gray"></i>
														667</button>
                                                <div class="case">
                                                    개봉 예매가능 기본
                                                            <a href="javascript:moveBokdPage('24003500');" class="button gblue" title="영화 예매하기">예매</a>
                                                        </div>
                                                </div>
										</li>*/ -->
					<!-- 박스오피스 종료 -->
				</ol>
			</div>
			<div class="search-link">
						<div class="cell">
							<div class="search">
								<input type="text" placeholder="영화명을 입력해 주세요" title="영화 검색" class="input-text" id="movieName">
								<button type="button" class="btn" id="btnSearch"><i class="iconset ico-search-w"></i> 검색</button>
							</div>
						</div>

						<div class="cell"><a href="/booking/timetable" title="상영시간표 보기"><i class="iconset ico-schedule-main"></i> 상영시간표</a></div>
						<div class="cell"><a href="/movie" title="박스오피스 보기"><i class="iconset ico-boxoffice-main"></i> 박스오피스</a></div>
						<div class="cell"><a href="/booking" title="빠른예매 보기"><i class="iconset ico-quick-reserve-main"></i> 빠른예매</a></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/main.js"></script>