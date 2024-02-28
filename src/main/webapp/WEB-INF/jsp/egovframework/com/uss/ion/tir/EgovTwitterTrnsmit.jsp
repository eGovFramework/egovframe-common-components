<%--
  Class Name : EgovTwitterTrnsmit.jsp
  Description : 트위터 송신(등록) 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.13    장동한          최초 생성
     2018.10.29    이정은          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.13
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterTrnsmit.twitterTrnsmit"/></title><!-- 트위터(Twitter)-송신(등록) -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="twitterInfo" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.twitterInfo.pageIndex.value = pageNo;
	document.twitterInfo.action = "<c:url value='/uss/tir/rss/listTwitterRecptn.do'/>";
   	document.twitterInfo.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_TwitterRecptn(unityLinkId){
	var vFrom = document.twitterInfo; 
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_TwitterRecptn(){
	var vFrom = document.twitterInfo;

	if(vFrom.twitterId.value == ""){
		alert("<spring:message code="ussIonTir.twitterTrnsmit.validate.id"/>");/* Twitter 아이디(ID)를 입력해주세요! */
		vFrom.twitterId.focus();
		return;
	}

	if(vFrom.twitterPw.value == ""){
		alert("<spring:message code="ussIonTir.twitterTrnsmit.validate.password"/>");/* Twitter 비밀번호(PW)를 입력해주세요! */
		vFrom.twitterPw.focus();
		return;
	}
	
	vFrom.submit();
}

/* ********************************************************
* 저장
******************************************************** */
function fn_egov_save_Twitter(){
	var vFrom = document.twitterInfo;
	
	if(confirm("<spring:message code="ussIonTir.twitterTrnsmit.validate.send"/>")){/* 작성된 Twitter를 전송 하시겠습니까? */

		vFrom.action = "<c:url value='/uss/ion/tir/registTwitterTrnsmit.do'/>";
		
		if(!validateTwitterInfo(vFrom)){ 			
			return;
		}else{
			vFrom.submit();
		} 
	}
}
</script>
</head>
<body>
<DIV id="content" style="width:730px">
<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<!-- 등록폼 시작  -->
<form:form modelAttribute="twitterInfo" name="twitterInfo" action="" method="post" enctype="multipart/form-data" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonTir.twitterTrnsmit.twitterTrnsmit"/></h2><!-- 트위터 송신(등록) -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<h3><spring:message code="ussIonTir.twitterTrnsmit.linklUser"/> : ${userID }<span style = "font-weight:normal;">(@${userName })</span></h3>
		<tr>
			<th><spring:message code="ussIonTir.twitterTrnsmit.twitterText"/> <span class="pilsu">*</span></th><!-- Twitter 내용 -->
			<td class="left">
			    <form:textarea path="twitterText" title="<spring:message code='ussIonTir.twitterTrnsmit.twitterText'/>" rows="7" cols="20" cssClass="txaClass"/>
				<div><form:errors path="twitterText" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="ussIonTir.twitterTrnsmit.send"/>" onclick="fn_egov_save_Twitter()" /><!-- 전송 -->
		<span class="btn_s"><a href="<c:url value='/uss/ion/tir/listTwitterRecptn.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>
</DIV>

</body>
</html>