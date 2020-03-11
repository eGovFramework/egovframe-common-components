<%--
  Class Name : EgovTwitterRecptn.jsp
  Description : 트위터 수신(목록) 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.07    장동한          최초 생성
     2018.10.29    이정은          공통컴포넌트 3.8 개선
 
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
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonTir.twitterRecptn.twitterRecptn"/></title><!-- 트위터(Twitter) 수신 -->
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
	document.twitterInfo.action = "<c:url value='/uss/ion/tir/listTwitterRecptn.do'/>";
   	document.twitterInfo.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_TwitterRecptn(unityLinkId){
	var vFrom = document.twitterInfo; 
}

function numchk(){
	var value = document.twitterInfo.pageSize.value;
	if(value == ""){
		alert("<spring:message code="ussIonTir.twitterRecptn.validate.number"/>");/* 숫자를 입력해주세요! */
		document.twitterInfo.pageSize.value="";
		document.twitterInfo.pageSize.focus();
		return false;
	}else if(isNaN(value)){
		alert("<spring:message code="ussIonTir.twitterRecptn.validate.onlyNumber"/>");/* 숫자만 입력이 가능합니다 */
		document.twitterInfo.pageSize.value="";
		document.twitterInfo.pageSize.focus();
		return false;
	}else{
		return true;	
	}
}

</script>

</head>
<body>
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form id="twitterInfo" name="twitterInfo" action="<c:url value='/uss/ion/tir/listTwitterRecptn.do'/>" method="post" enctype="multipart/form-data">
	<h1><spring:message code="ussIonTir.twitterRecptn.twitterRecptn"/></h1><!-- 트위터 수신(목록) -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<span class="btn_b"><a href="<c:url value='/uss/ion/tir/registTwitterTrnsmit.do'/>" onclick="" title="등록 버튼"><spring:message code="button.create"/></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussIonTir.twitterRecptn.twitterProfileImageURL"/></th><!-- 프로필 이미지 -->
			   <th scope="col"><spring:message code="ussIonTir.twitterRecptn.text"/></th><!-- 내용 -->
			</tr>
		</thead>
		<tbody>
		<!-- 데이터를 없을때 화면에 메세지를 출력해준다 -->
		<c:if test="${fn:length(resultList) == 0}">
		<tr> 
			<td colspan="2">
				<spring:message code="common.nodata.msg" />
			</td>
		</tr>   	          				 			   
		</c:if>
		<!-- 데이터를 화면에 출력해준다 -->
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
		<tr>
			<td>
				<img src="<c:url value='${resultInfo.twitterProfileImageURL}'/>" alt="${resultInfo.twitterNmae}프로필이미지" width="48px" height="48px">
			</td>
			<td>
				${resultInfo.twitterNmae}(${resultInfo.twitterCreatedAt})<br>
				<c:out value="${resultInfo.twitterText}" escapeXml="false" /><br>
				<c:if test="${resultInfo.twitterURL ne 'null'}">
				<font color="#0080C0"><a href="<c:url value='${resultInfo.twitterURL}'/>" target="_blank"  title="새 창으로 이동" ><c:url value='${resultInfo.twitterURL}'/></a></font>
			</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

</DIV>

</body>
</html>