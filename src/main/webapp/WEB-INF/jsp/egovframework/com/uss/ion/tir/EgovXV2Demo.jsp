<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Twitter(X) API v2 Demo</title>
<link href="<c:url value='/css/egovframework/com/com.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css'/>" rel="stylesheet" type="text/css">
<style type="text/css">
#content .section { border:1px solid #dcdcdc; padding:12px; margin:12px 0; }
#content .section h3 { margin:0 0 10px 0; }
#content .row { margin:8px 0; }
#content label { display:inline-block; width:140px; font-weight:600; }
#content input[type=text] { width:520px; padding:4px; }
#content textarea { width:100%; box-sizing:border-box; }
#content .methodNote { margin:6px 0 12px 0; color:#555; }
#content .methodBadge { display:inline-block; padding:2px 8px; border:1px solid #c7d7ea; background:#f3f8fd; color:#1d4f8c; font-size:12px; margin-left:6px; }
#statusText { font-weight:600; color:#b00020; margin-left:8px; }
.previewBox { background:#fafafa; border:1px solid #e5e5e5; padding:10px; white-space:pre-wrap; word-break:break-word; max-height:260px; overflow:auto; }
.tweetList { list-style:none; margin:0; padding:0; }
.tweetItem { border:1px solid #e5e5e5; background:#fafafa; margin:8px 0; padding:10px; }
.tweetText { white-space:pre-wrap; word-break:break-word; }
.tweetMeta { margin-top:6px; font-size:12px; color:#666; }
.accountTable td { padding:6px 8px; }
.accountValue { font-weight:600; color:#222; }
/* 공통 com.css의 span.btn_s 고정폭(43px) 해제: 버튼 문구 길이에 맞춰 자동 확장 */
#content span.btn_s { width:auto !important; min-width:43px; padding:0 10px !important; }
#content span.btn_s a { padding:0 2px; white-space:nowrap; }
</style>
</head>
<body>
<div id="content" style="width:900px">
    <div class="wTableFrm">
        <h2>Twitter(X) API v2 연동</h2>
        <p class="methodNote">
		    이 페이지는 <strong>Twitter API v2 연동을 위한 직접 구현 예제</strong>입니다.<span class="methodBadge">라이브러리 미사용</span><br>
		    공식 권장 방식인 <strong>OAuth 2.0 Authorization Code Flow with PKCE</strong> (RFC 6749, RFC 7636 준수)를 구현하였습니다.
		</p>
		<p>인증 후 세션의 <code>twitter_access_token</code>으로 계정 조회/목록 조회/등록/삭제를 수행하며, 등록/삭제 후 목록을 자동 갱신합니다.</p>

        <div class="row" style="margin:12px 0;">
            <span class="btn_s"><a href="#LINK" onclick="startTwitterAuth(); return false;">X 인증 시작(팝업)</a></span>
            <span class="btn_s"><a href="#LINK" onclick="getMe(); return false;">내 계정 정보 조회</a></span>
            <span class="btn_s"><a href="#LINK" onclick="getTweets(); return false;">트윗 목록 조회</a></span>
            <span id="statusText">인증 필요</span>
        </div>

        <div class="section">
            <h3>1) 내 계정 정보</h3>
            <table class="wTable accountTable">
                <colgroup>
                    <col style="width:20%" />
                    <col />
                </colgroup>
                <tr>
                    <th>등록 ID</th>
                    <td class="left"><span id="accountId" class="accountValue">-</span></td>
                </tr>
                <tr>
                    <th>계정 ID(username)</th>
                    <td class="left"><span id="accountUsername" class="accountValue">-</span></td>
                </tr>
                <tr>
                    <th>별명(name)</th>
                    <td class="left"><span id="accountName" class="accountValue">-</span></td>
                </tr>
            </table>
        </div>

        <div class="section">
            <h3>2) 트윗 목록 조회 (최근)</h3>
            <div class="row">
                <label for="userId">userId</label>
                <input type="text" id="userId" placeholder="예: 1234567890">
            </div>
            <div class="row">
                <label for="nextToken">next token</label>
                <input type="text" id="nextToken" placeholder="pagination_token">
            </div>
            <div class="row">
                <span class="btn_s"><a href="#LINK" onclick="getTweets(); return false;">목록 조회</a></span>
                <span class="btn_s"><a href="#LINK" onclick="getNextTweets(); return false;">다음 목록 조회</a></span>
            </div>
            <div class="row">
                <div id="tweetsListArea" class="previewBox">
                    <ul id="tweetsList" class="tweetList">
                        <li>조회 결과가 여기에 표시됩니다.</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="section">
            <h3>3) 트윗 등록</h3>
            <div class="row">
                <label for="tweetText">text</label>
                <input type="text" id="tweetText" placeholder="트윗 내용을 입력하세요">
            </div>
            <div class="row">
                <span class="btn_s"><a href="#LINK" onclick="writeTweet(); return false;">등록</a></span>
            </div>
        </div>

        <div class="section">
            <h3>4) 트윗 삭제</h3>
            <div class="row">
                <label for="tweetId">tweetId</label>
                <input type="text" id="tweetId" placeholder="삭제할 트윗 ID">
            </div>
            <div class="row">
                <span class="btn_s"><a href="#LINK" onclick="deleteTweet(); return false;">삭제</a></span>
            </div>
        </div>

        <div class="section">
            <h3>응답 로그</h3>
            <textarea id="resultBox" rows="16" readonly="readonly"></textarea>
        </div>

        <div class="btn" style="margin-top:12px;">
            <span class="btn_s"><a href="<c:url value='/uss/ion/tir/selectTwitterMain.do'/>">Back to Main</a></span>
        </div>
    </div>
</div>
<script type="text/javascript">
(function() {
    var ctx = "<c:url value='/'/>";
    if (ctx.length > 1 && ctx.charAt(ctx.length - 1) === "/") {
        ctx = ctx.substring(0, ctx.length - 1);
    }
    var authPopup = null;

    // ===== 공통 UI/데이터 처리 유틸 =====

    // 요소 ID로 DOM 노드를 조회한다.
    function byId(id) {
        return document.getElementById(id);
    }

    // 상태 메시지와 색상을 갱신한다.
    function setStatus(text, color) {
        var el = byId("statusText");
        if (!el) {
            return;
        }
        el.textContent = text;
        el.style.color = color;
    }

    // 지정한 요소의 텍스트를 표시용 형식으로 설정한다.
    function setText(id, value) {
        var el = byId(id);
        if (!el) {
            return;
        }
        el.textContent = value == null || value === "" ? "-" : String(value);
    }

    // 계정 정보 표시 영역을 초기화한다.
    function clearAccountInfo() {
        setText("accountId", "-");
        setText("accountUsername", "-");
        setText("accountName", "-");
    }

    // 계정 조회 응답에서 사용자 정보를 화면에 반영한다.
    function updateAccountInfo(payload) {
    	console.log("typeof payload:", typeof payload);
    	console.log("payload:", payload);
        var data = extractMeData(payload);
        if (!data) {
            clearAccountInfo();
            return;
        }
        setText("accountId", data.id);
        setText("accountUsername", data.username);
        setText("accountName", data.name);
    }

    // 로그 출력을 위해 응답 데이터를 문자열로 변환한다.
    function stringifyData(data) {
        if (typeof data === "string") {
            return data;
        }
        try {
            return JSON.stringify(data, null, 2);
        } catch (e) {
            return String(data);
        }
    }

    // 목록 렌더링 전 HTML 특수문자를 이스케이프한다.
    function escapeHtml(value) {
        return String(value == null ? "" : value)
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/\"/g, "&quot;")
            .replace(/'/g, "&#39;");
    }

    // 계정 조회 응답에서 사용자 객체를 추출한다.
    function extractMeData(payload) {
        if (!payload) {
            return null;
        }
		if (typeof payload === "string") {
	        try {
	        payload = JSON.parse(payload);
	        } catch (e) {
	        return null;
	        }
        }
        if (payload.data && typeof payload.data === "object" && !Array.isArray(payload.data)) {
            return payload.data;
        }
        if (Array.isArray(payload.data) && payload.data.length) {
            return payload.data[0];
        }
        if (payload.id && (payload.username || payload.name)) {
            return payload;
        }
        return null;
    }

    // 트윗 목록 응답에서 배열 데이터를 추출한다.
    function extractTweets(payload) {
        if (!payload) {
            return [];
        }
        
        if (typeof payload === "string") {
            try {
                payload = JSON.parse(payload);
            } catch (e) {
                console.error("2차 parse 실패", e);
                return [];
            }
        }
        
        if (Array.isArray(payload.data)) {
            return payload.data;
        }
        if (Array.isArray(payload.tweets)) {
            return payload.tweets;
        }
        if (Array.isArray(payload.statuses)) {
            return payload.statuses;
        }
        return [];
    }

    // 페이징 응답에서 다음 조회 토큰을 추출한다.
    function extractNextToken(payload) {
        if (!payload) {
            return "";
        }
        if (typeof payload === "string") {
            try {
                payload = JSON.parse(payload);
            } catch (e) {
                return "";
            }
        }
        if (payload.meta && payload.meta.next_token) {
            return payload.meta.next_token;
        }
        if (payload.next_token) {
            return payload.next_token;
        }
        if (payload.meta && payload.meta.pagination_token) {
            return payload.meta.pagination_token;
        }
        return "";
    }

    // 추출된 트윗 목록을 화면 리스트로 렌더링한다.
    function renderTweetList(payload) {
    	console.log("payload:", payload);
    	console.log("extractTweets 결과:", extractTweets(payload));
    	
        var list = byId("tweetsList");
        if (!list) {
            return;
        }

        if (typeof payload === "string") {
            try {
                payload = JSON.parse(payload);
            } catch (e) {
                list.innerHTML = "<li>" + escapeHtml(payload) + "</li>";
                return;
            }
        }

        var tweets = extractTweets(payload);
        if (!tweets.length) {
            if (payload && payload.error) {
                list.innerHTML = "<li>" + escapeHtml(payload.error) + "</li>";
                return;
            }
            list.innerHTML = "<li>트윗 목록이 없습니다.</li>";
            return;
        }

        var html = [];
        for (var i = 0; i < tweets.length; i++) {
            var t = tweets[i] || {};
            var text = escapeHtml(t.text || "");
            var tweetId = escapeHtml(t.id || "");
            var username = byId("accountUsername") ? byId("accountUsername").textContent : "";
            var viewLink = t.id ? "<a href=\"https://x.com/" + encodeURIComponent(username && username !== "-" ? username : "i/web") + (username && username !== "-" ? "/status/" : "/status/") + encodeURIComponent(t.id) + "\" target=\"_blank\">X에서 보기</a>" : "";
            if (!username || username === "-") {
                viewLink = t.id ? "<a href=\"https://x.com/i/web/status/" + encodeURIComponent(t.id) + "\" target=\"_blank\">X에서 보기</a>" : "";
            }
            html.push(
                "<li class=\"tweetItem\">" +
                    "<div class=\"tweetText\">" + text + "</div>" +
                    "<div class=\"tweetMeta\">ID: " + tweetId + (viewLink ? " | " + viewLink : "") +
                    " | <a href=\"#LINK\" onclick=\"document.getElementById('tweetId').value='" + tweetId + "'; return false;\">삭제 ID로 채우기</a></div>" +
                "</li>"
            );
        }
        list.innerHTML = html.join("");
    }

    // API 응답을 로그 텍스트 영역 상단에 기록한다.
    function logResult(title, data) {
        var box = byId("resultBox");
        var body = stringifyData(data);
        box.value = "[" + new Date().toLocaleString() + "] " + title + "\n" + body + "\n\n" + box.value;
    }

    // ===== API 호출 및 응답 판별 =====
    // 공통 fetch 호출로 상태값/본문(JSON 또는 문자열)을 반환한다.
    async function callApi(url, options) {
        var response = await fetch(url, options || {});
        var text = await response.text();
        try {
            return { ok: response.ok, status: response.status, body: JSON.parse(text), raw: text };
        } catch (e) {
			console.error("JSON parse 실패:", text);
            return { ok: response.ok, status: response.status, body: text, raw: text };
        }
    }

    // 내 계정 조회가 정상인지 응답 구조 기준으로 판별한다.
    function isMeSuccess(result) {
        return !!(result && result.ok && extractMeData(result.body) && extractMeData(result.body).id);
    }

    // ===== 화면 액션(인증/조회/등록/삭제) =====
    // X OAuth 인증 팝업을 열고 인증 시작 상태를 표시한다.
    window.startTwitterAuth = function() {
        var width = 760;
        var height = 620;
        var left = (window.screen.width - width) / 2;
        var top = (window.screen.height - height) / 2;
        var features = "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top;
        authPopup = window.open(ctx + "/twitter/login.do", "twitterAuthPopup", features);
        setStatus("인증 팝업 여는 중...", "#0a58ca");
        if (!authPopup) {
            logResult("인증 팝업 열기 실패", "브라우저 팝업 차단으로 인증 창을 열 수 없습니다.");
            setStatus("인증 실패(팝업 차단)", "#b00020");
        }
    };

    // 세션 토큰으로 내 계정 정보를 조회하고 초기값을 채운다.
    window.getMe = async function(skipAutoTweets) {
        var result = await callApi(ctx + "/twitter/me.do");
        logResult("GET /twitter/me.do [" + result.status + "]", result.body);
        updateAccountInfo(result.body);
        console.log(result.body);
        if (isMeSuccess(result)) {
            var me = extractMeData(result.body);
            byId("userId").value = me.id;
            byId("nextToken").value = "";
            setStatus("인증 성공", "#1a7f37");
            if (!skipAutoTweets) {
                await window.getTweets(true);
            }
        } else {
            clearAccountInfo();
        }
        return result;
    };

    // 사용자 ID 기준 최근 트윗 목록을 조회한다.
    window.getTweets = async function(silent) {
        var userId = byId("userId").value.trim();
        if (!userId) {
            if (!silent) {
                alert("userId를 입력하세요.");
            }
            return null;
        }
        var result = await callApi(ctx + "/twitter/tweets?userId=" + encodeURIComponent(userId));
        logResult("GET /twitter/tweets [" + result.status + "]", result.body);
        renderTweetList(result.body);
        var nextToken = extractNextToken(result.body);
        if (result.ok && nextToken) {
            byId("nextToken").value = nextToken;
        }
        return result;
    };

    // 다음 페이지 토큰으로 다음 트윗 목록을 조회한다.
    window.getNextTweets = async function() {
        var userId = byId("userId").value.trim();
        var token = byId("nextToken").value.trim();
        if (!userId || !token) {
            alert("userId와 next token을 입력하세요.");
            return;
        }
        var url = ctx + "/twitter/tweets/next?userId=" + encodeURIComponent(userId) + "&token=" + encodeURIComponent(token);
        var result = await callApi(url);
        logResult("GET /twitter/tweets/next [" + result.status + "]", result.body);
        renderTweetList(result.body);
        var nextToken = extractNextToken(result.body);
        if (result.ok && nextToken) {
            byId("nextToken").value = nextToken;
        }
    };

    // 입력한 내용을 트윗으로 등록하고 목록을 갱신한다.
    window.writeTweet = async function() {
        var text = byId("tweetText").value.trim();
        if (!text) {
            alert("text를 입력하세요.");
            return;
        }
        var result = await callApi(ctx + "/twitter/tweet", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" },
            body: "text=" + encodeURIComponent(text)
        });
        logResult("POST /twitter/tweet [" + result.status + "]", result.body);
        if (result.ok && result.body && result.body.data && result.body.data.id) {
            byId("tweetId").value = result.body.data.id;
            byId("tweetText").value = "";
            if (byId("userId").value.trim()) {
                byId("nextToken").value = "";
                await window.getTweets(true);
            }
        }
    };

    // 지정한 트윗을 삭제하고 목록을 다시 조회한다.
    window.deleteTweet = async function() {
        var tweetId = byId("tweetId").value.trim();
        if (!tweetId) {
            alert("tweetId를 입력하세요.");
            return;
        }
        var result = await callApi(ctx + "/twitter/tweet?tweetId=" + encodeURIComponent(tweetId), {
            method: "DELETE"
        });
        logResult("DELETE /twitter/tweet [" + result.status + "]", result.body);
        if (result.ok && byId("userId").value.trim()) {
            byId("nextToken").value = "";
            byId("tweetId").value = "";
            await window.getTweets(true);
        }
    };

    // ===== 인증 콜백 메시지 처리 =====
    // 인증 콜백 팝업에서 전달한 성공/실패 메시지를 처리한다.
    window.addEventListener("message", function(event) {
        if (event.origin !== window.location.origin) {
            return;
        }
        if (event.data === "TWITTER_AUTH_SUCCESS") {
            setStatus("인증 성공", "#1a7f37");
            logResult("인증 이벤트", "TWITTER_AUTH_SUCCESS");
            window.getMe();
        } else if (event.data === "TWITTER_AUTH_FAIL") {
            setStatus("인증 실패", "#b00020");
            logResult("인증 이벤트", "TWITTER_AUTH_FAIL");
        }
        if (authPopup && !authPopup.closed) {
            authPopup.close();
        }
    });

    // 초기 인증 확인 재시도 사이에 잠시 대기한다.
    function sleep(ms) {
        return new Promise(function(resolve) { setTimeout(resolve, ms); });
    }

    // ===== 초기 화면 로딩 =====
    // 화면 진입 시 인증 상태를 확인하고 초기 데이터를 불러온다.
    (async function initDemoView() {
        var meResult = null;
        for (var i = 0; i < 4; i++) {
            meResult = await window.getMe(true);
            if (isMeSuccess(meResult)) {
                setStatus("인증됨", "#1a7f37");
                await window.getTweets(true);
                return;
            }

            var body = meResult ? meResult.body : null;
            if (!(body && body.error && String(body.error).indexOf("twitter_access_token not found in session") >= 0)) {
                break;
            }
            if (i < 3) {
                setStatus("인증 확인 중...", "#0a58ca");
                await sleep(700);
            }
        }
        clearAccountInfo();
        setStatus("인증 필요", "#b00020");
    })();
})();
</script>
</body>
</html>












