<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./header.jsp"%>
<link href="${ctx}/css/userMyMega.css" rel="stylesheet" type="text/css">
<script defer="defer" type="text/javascript"
	src="${ctx}/js/userMyMega.js"></script>
<div align="center">
	<div class="mymega">
		<h1>나의 메가박스</h1>

		<h2>예매 내역</h2>
		<div class="mymega_reservation">
			<c:if test="${myBookedList eq null}">
				<div class="noList">예매 내역이 없습니다.</div>
			</c:if>
			<c:forEach var="m" items="${myBookedList}">
				<div class="single_data">
					<div class="left_section">
						<a href="${ctx}/movie-detail.do?id=${m.getMovieID()}" class="img"
							title="영화상세보기"><img
							src="https://image.tmdb.org/t/p/w500${m.getImage()}" alt="영화이미지"
							onerror="noImg(this)"></a>
					</div>
					<div class="right_section">
						<table>
							<tr>
								<th scope="row" class="">예매번호</th>
								<td colspan="3">
									<p class="ticketCode">
										<span>${m.getTicketID()}</span>
									</p>
								</td>
							</tr>
							<tr>
								<th scope="row" class="">영화명</th>
								<td colspan="3">
									<p class="">
										<span>${m.getTitle()}</span>
									</p>
								</td>
							</tr>
							<tr>
								<th scope="row" class="">극장/상영관</th>
								<td>${m.getMovieName()}</td>
								<th scope="row">관람인원</th>
								<td>${m.getNumberPeople()}</td>
							</tr>
							<tr>
								<th scope="row" class="">관람일시</th>
								<td>${m.getPreviewDate()}</td>
								<th scope="row">관람좌석</th>
								<td>${m.getSeatList()}</td>
							</tr>
						</table>
						<div class="">
							<table class="">
								<tr>
									<th scope="row" class="">결제일시</th>
									<td>${m.getPaymentDate()}</td>
									<th scope="row">결제가격</th>
									<td>${m.getTicketPrice()}원</td>
								</tr>
							</table>
						</div>
						<div class="btn_section">
							<a href="#" title="" class="button purple">교환권출력</a> <a
								onclick="cancelCheck(${m.getTicketID()})"
								class="button gray" title="예매취소하기">예매취소</a>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="cont">
				<strong>[예매 안내]</strong>
				<ul class="dot-list mb30">
					<li>만 4세(48개월) 이상부터는 영화티켓을 반드시 구매하셔야 입장 가능합니다.</li>
					<li>예매 변경은 불가능하며, 취소 후 재 예매를 하셔야만 합니다.</li>
					<li>메가박스 모바일앱을 이용할 경우 티켓출력없이 모바일티켓을 통해 바로 입장하실 수 있습니다.</li>
				</ul>

				<strong>[티켓교환 안내]</strong>
				<ul class="dot-list mb30">
					<li>극장의 무인발권기(KIOSK)를 통해 예매번호 또는 고객확인번호(생년월일+휴대폰번호)를 입력하여 편리하게
						티켓을 발권하실 수 있습니다.</li>
					<li>무인발권기 이용이 어려우신경우, 티켓교환권을 출력하여 매표소에 방문하시면 티켓을 발권하실 수 있습니다.</li>
					<li>(티켓교환권 출력이 어려운경우 예매번호와 신분증을 지참하여 매표소에 방문하시면 티켓을 발권하실 수
						있습니다)</li>
				</ul>

				<strong>[예매취소 안내]</strong>
				<ul class="dot-list">
					<li>온라인(홈페이지/모바일) 예매 취소는 상영시간 20분전까지 입니다.</li>
					<li>위탁 예매 사이트 이용 시 취소 및 환불 규정은 해당 사이트 규정을 따릅니다.</li>
					<li>LIVE 공연 콘텐트는 취소 기준은 아래와 같습니다.
						<ul class="dash-list">
							<li>관람 4일전 : 취소 가능</li>
							<li>관람 3일 ~ 1일전 : 취소 수수료 부담 후 취소 가능</li>
							<li>관람 당일 : 취소 및 환불 불가</li>
						</ul>
					</li>
					<li>공연 관람시 시작 시간 이후에는 입장이 제한 됩니다.</li>
					<li>발권된 티켓은 상영시간 전까지 현장 방문 시에만 취소가 가능합니다.</li>
				</ul>
			</div>
		</div>

		<br> <br>

		<h2>간편로그인 계정연동</h2>
		<div class="mymega_linked_login">
			<table>
				<tr>
					<th>구분</th>
					<th>연동정보</th>
					<th>연결</th>
				</tr>
				<tr>
					<td class="sns_name">카카오</td>
					<c:if test="${user.getKakao() eq null }">
						<td class="connecting_info">연결된 계정정보가 없습니다.</td>
						<td class="connecting_btn_section"><button
								class="connect_to_kakao"
								onclick="location.href='https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=21ab5b4db87c2754b7ad5637ffdc7eb3&redirect_uri=http://localhost:8085/MovieProject/kakaoLoginResult.do'">연동</button></td>
					</c:if>
					<c:if test="${user.getKakao() ne null }">
						<td class="connecting_info">id : ${user.getKakao()}</td>
						<td class="connecting_btn_section"><button
								class="disconnect_to_kakao"
								onclick="location.href='${ctx}/disconnectToKakao.do'">해제</button></td>
					</c:if>
				</tr>
				<tr>
					<td class="sns_name">네이버</td>
					<c:if test="${user.getNaver() eq null }">
						<td class="connecting_info">연결된 계정정보가 없습니다.</td>
						<td class="connecting_btn_section"><button
								class="connect_to_naver"
								onclick="location.href='https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=SrIGsH8XIxmfND4_eTEH&state=STATE_STRING&redirect_uri=http://localhost:8085/MovieProject/naverLoginResult.do'">연동</button></td>
					</c:if>
					<c:if test="${user.getNaver() ne null }">
						<td class="connecting_info">id : ${user.getNaver()}</td>
						<td class="connecting_btn_section"><button
								class="disconnect_to_naver"
								onclick="location.href='${ctx}/disconnectToNaver.do'">해제</button></td>
					</c:if>
				</tr>
			</table>
		</div>

		<br> <br>

		<h2>개인정보 수정</h2>
		<div class="mymega_update">
			<form action="${ctx}/userUpdate.do" method="post" id="userUpdateForm">
				<table>
					<tr>
						<th scope="row"><label>아이디</label></th>
						<td>${user.getId()}</td>
					</tr>
					<tr>
						<th scope="row"><label>휴대폰</label> <em>*</em></th>
						<td><input type="text" id="inputPhone" name="inputPhone"
							placeholder="010-xxxx-xxxx" title="변경할 휴대폰 번호 입력" maxlength="13"
							value="${user.getPhone()}" required="required" /></td>
					</tr>
					<tr>
						<th scope="row"><label>이메일</label> <em>*</em></th>
						<td><input type="email" id="inputEmail" name="inputEmail"
							value="${user.getEmail()}" placeholder="xxxx@xxxx.xxx"
							required="required" /></td>
					</tr>
					<tr>
						<th scope="row">현재 비밀번호를 입력하세요.<em>*</em></th>
						<td><input type="password" id="currentPw" name="currentPw"
							placeholder="현재 비밀번호를 입력하세요." required="required" /></td>
					</tr>
					<tr>
						<th scope="row">새 비밀번호를 입력하세요.<em>*</em></th>
						<td><input type="password" id="newPw" name="newPw"
							placeholder="새 비밀번호를 입력하세요." required="required" /></td>
					</tr>
				</table>
				<br>
				<button id="updateBtn" type="button" onclick="validCheck(form)">현재
					비밀번호 확인</button>
			</form>
		</div>

		<br> <br>
		<c:if test="${user.getId() ne 'admin'}">
			<h2>회원탈퇴</h2>
			<a>회원님의 비밀번호를 입력하시고 [탈퇴 비밀번호 확인] 버튼을 클릭해주세요.</a>
			<div class="mymega_resign">
				<form action="${ctx}/userResign.do" id="resignForm">
					<table>
						<tr>
							<th scope="row">비밀번호<em>*</em></th>
							<td><input type="password" id="resignPw"
								placeholder="비밀번호를 입력하세요." /></td>
						</tr>
					</table>
					<br>
					<button type="button" id="resignBtn"
						onclick="resignValidCheck(form)">탈퇴 비밀번호 확인</button>
				</form>
			</div>
		</c:if>
	</div>
	<br>
</div>
<div class="cancel_modal">
	<div class="cancel_title">
		알림
		<button type="button" class="btn-modal-close">
			레이어 닫기
			<!--레이어 닫기-->
		</button>
	</div>
	<div class="cancel_body">
		<h4>정말 예매를 취소하시겠습니까?</h4>
		<a class="button gray cancelBtn" id="cancleBtn" title="예매취소하기">예매취소</a>
	</div>
</div>
<div class="cancel_overlay"></div>
<%@include file="./footer.jsp"%>
