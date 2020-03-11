<%--
  Class Name : EgovPopupSample.jsp
  Description : 팝업창관리 팝업샘플
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.10.09    장동한          최초 생성
     2018.09.18    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.10.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonPwm.popupSample.popupSample"/></title><!-- 팝업 샘플페이지 -->
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 쿠키설정
 ******************************************************** */
function fnSetCookiePopup( name, value, expiredays ) {
	  var todayDate = new Date();
	  todayDate.setDate( todayDate.getDate() + expiredays );
	  document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}
/* ********************************************************
* 체크버튼 클릭시
******************************************************** */
function fnPopupCheck() {
	fnSetCookiePopup( "${popupId}", "done" , 365);
	window.close();
}
</script>
<style type="text/css">
<!--
#contents {position:relative; display:block; height:170px; background:#ddf;}
#bottom {position:relative; display:block;font-size:9pt}

//-->
</style>

</head>
<body>
<div id="contents">
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<spring:message code="ussIonPwm.popupSample.popupTest"/>
</div>
<div id="bottom">
<c:if test="${stopVewAt eq 'Y'}">
<spring:message code="ussIonPwm.popupSample.checkBox"/><input type="checkbox" name="chkPopup" value="" onClick="fnPopupCheck()" title="다음부터창열지않기체크">
</c:if>
</div>

</body>
</html>
