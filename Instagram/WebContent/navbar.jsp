<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<a class="" href="#">
        <img src="images/yestagram.png" class="ui image centered small" id="brand" alt="Yestagram">
    </a>

    <div class="ui huge secondary pointing stackable menu violet">
        <a class="item" href="./writenewsfeed.jsp">새 포스트</a>
        <a class="item" id="liked">좋아한 글</a>
        <a class="item">태그</a>
        <div class="right menu">
            <div class="item">
                <div class="ui small input">
                    <input placeholder="검색" type="text" />
                </div>
            </div>
            <a class="ui item" href="./member/logout.jsp">로그아웃</a>
        </div>
    </div>

    <div class="ui custom popup bottom center transition hidden"></div>