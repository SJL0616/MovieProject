<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/movie/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script
      type="text/javascript"
      src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=lmbt0p4wmz"
    ></script>
<!-- movietheater.jsp -->
<link rel="stylesheet" href="${ctx}/css/movietheater.css" />
<script defer src="${ctx}/js/movietheater.js"></script>
<div class="sortcenter" align="center">
      <div class="map-container">
        <div class="map-header">
          <h1>영화관</h1>
        </div>
        <div class="mapinner">
          <div class="map-navi">
          <c:forEach var="list" items="${list}">
            <div class="movietheater" data-x="${list.locationX}"
              data-y="${list.locationY}" data-address="${list.movieAddress}"
              data-name="${list.movieName}">
              ${list.movieName}
            </div>
            </c:forEach>
          </div>
          <div id="map"></div>
        </div>
        <div class="movie-result">
          <button class="movie-button">선택완료</button>
        </div>
      </div>
    </div>
</html>