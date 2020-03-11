<%--
  Class Name : EgovIndvdlInfoPolicyDetail.jsp
  Description : 개인정보보호정책 상세보기
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2018.09.03    이정은          공통컴포넌트 3.8 개선  
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images/egovframework/com/uss/sam/ipm/"/>
<c:set var="CssUrl" value="/css/egovframework/com/uss/sam/ipm/"/>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="ussSamIpm.indvdlInfoPolicyDetail.indvdlInfoPolicyDetail"/></title><!-- 개인정보보호정책 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">

<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_IndvdlInfoPolicy(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_IndvdlInfoPolicy(){
	location.href = "<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_IndvdlInfoPolicy(){
	var vFrom = document.formUpdt;
	vFrom.action = "<c:url value='/uss/sam/ipm/updtIndvdlInfoPolicy.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_IndvdlInfoPolicy(){
	var vFrom = document.formDelete;
	if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/sam/ipm/detailIndvdlInfoPolicy.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_IndvdlInfoPolicy();">

<div class="wTableFrm">
<form name="IndvdlInfoPolicyForm" action="<c:url value=''/>" method="post">
	<!-- 타이틀 -->
	<h2><spring:message code="ussSamIpm.indvdlInfoPolicyDetail.indvdlInfoPolicyDetail"/></h2><!-- 개인정보보호정책 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:24%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussSamIpm.indvdlInfoPolicyDetail.indvdlInfoNm"/> <span class="pilsu">*</span></th><!-- 개인정보보호정책 명 -->
			<td class="left">
			    <c:out value="${indvdlInfoPolicy.indvdlInfoNm}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussSamIpm.indvdlInfoPolicyDetail.indvdlInfoYn"/> <span class="pilsu">*</span></th><!-- 동의여부 -->
			<td class="left">
			    <c:if test="${indvdlInfoPolicy.indvdlInfoYn == 'Y'}"><spring:message code="input.yes"/></c:if>
				<c:if test="${indvdlInfoPolicy.indvdlInfoYn == 'N'}"><spring:message code="input.no"/></c:if>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussSamIpm.indvdlInfoPolicyDetail.indvdlInfoDc"/> <span class="pilsu">*</span></th><!-- 개인정보보호정책 내용 -->
			<td class="left">
			    <c:out value="${indvdlInfoPolicy.indvdlInfoDc}" escapeXml="false" />
			</td>
		</tr>
	</table>
</form>
	<!-- 하단 버튼 -->
	<div class="btn">		
		<form name="formUpdt" action="<c:url value='/uss/sam/ipm/updtIndvdlInfoPolicy.do'/>" method="post" style="display:inline"> 
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_IndvdlInfoPolicy(); return false;" />
		<input name="indvdlInfoId" type="hidden" value="${indvdlInfoPolicy.indvdlInfoId}">
		</form>
	
		<form name="formDelete" action="<c:url value='/uss/sam/ipm/detailIndvdlInfoPolicy.do'/>" method="post" style="display:inline">
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete_IndvdlInfoPolicy(); return false;" />
		<input name="indvdlInfoId" type="hidden" value="${indvdlInfoPolicy.indvdlInfoId}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>">
		</form>
	
		<form name="formList" action="<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do'/>" method="post" style="display:inline">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_IndvdlInfoPolicy(); return false;" />
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

</body>
</html>
