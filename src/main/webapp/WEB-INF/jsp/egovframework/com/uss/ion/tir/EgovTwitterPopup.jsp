<%--
  Class Name : EgovTwitterRecptn.jsp
  Description : 트위터 수신(목록) 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.07    장동한          최초 생성
     2018.10.26    이정은          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.07
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%
String sCmd = request.getParameter("cmd") == null ? "": (String)request.getParameter("cmd");
String sErrorMessage = request.getAttribute("errorMessage") == null ? "": String.valueOf(request.getAttribute("errorMessage"));
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterPopup.twitterPopup"/></title><!-- 트위터(Twitter) 인증요청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
// 초기화
function fn_init_TwitterPopup(){
}

/* ********************************************************
 * 인증 시작 전 입력값을 검사한다.
 ******************************************************** */
function fn_egov_search_TwitterRecptn(){
    var vFrom = document.twitterInfo;
    var key = vFrom.ConsumerKey.value;
    var secret = vFrom.ConsumerSecret.value;

    if(key === ""){
        alert("<spring:message code="ussIonTir.twitterPopup.consumerKey"/>");
        vFrom.ConsumerKey.focus();
        return false;
    }

    if(secret === ""){
        alert("<spring:message code="ussIonTir.twitterPopup.consumerSecret"/>");
        vFrom.ConsumerSecret.focus();
        return false;
    }

    return true;
}
</script>
</head>
<body onLoad="fn_init_TwitterPopup()">
<DIV id="content" style="width:712px">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form id="twitterInfo" name="twitterInfo" action="<c:url value='/twitter/login.do'/>" method="post" enctype="multipart/form-data" onsubmit="return fn_egov_search_TwitterRecptn();">

<div class="wTableFrm">
    <h2><spring:message code="ussIonTir.twitterPopup.twitterPopupKey"/></h2><!-- 트위터(Twitter) 인증요청 - 인증키 입력 -->

    <div style="margin:8px 0; color:#b06a00; font-weight:600; line-height:1.5;">
        저장 기능은 임시 비활성화 상태입니다. 활성화를 위해서는 컨트롤러를 확인해주세요. <br/>
        인증에 사용할 Client ID / Client Secret을 입력해 주세요.
    </div>

    <% if(!"".equals(sErrorMessage)){ %>
    <div style="margin:8px 0; color:#b00020; font-weight:600;"><%= sErrorMessage %></div>
    <% } %>

    <table class="wTable">
        <colgroup>
            <col style="width:25%" />
            <col style="" />
        </colgroup>
        <tr>
            <th>Client ID <span class="pilsu">*</span></th>
            <td class="left">
                <input type="text" id="ConsumerKey" name="ConsumerKey" title="ConsumerKey" value="" maxlength="255" style="width:100%" placeholder="Client ID 입력">
            </td>
        </tr>
        <tr>
            <th>Client Secret <span class="pilsu">*</span></th>
            <td class="left">
                <input type="password" id="ConsumerSecret" name="ConsumerSecret" title="ConsumerSecret" value="" maxlength="255" style="width:100%; height:24px;" placeholder="Client Secret 입력">
            </td>
        </tr>
        <tr>
            <td class="left" colspan="2">
                <input type="checkbox" id="chkKeyDisabled" title="인증키 저장 기능 비활성화" disabled="disabled">
                <label for="chkKeyDisabled">인증키 저장 기능 (비활성화 상태)</label>
            </td>
        </tr>
    </table>

    <div class="btn">
        <input class="s_submit" type="submit" value="<spring:message code="ussIonTir.twitterPopup.send"/>" />
    </div>
    <div style="clear:both;"></div>
</div>

<input type="hidden" name="cmd" value="<c:out value="${sCmd}"/>">
<input type="hidden" name="chkKey" value="0">
</form>

</DIV>
</body>
</html>
