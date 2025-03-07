<%--
  Class Name : EgovBatchOpertUpdate.jsp
  Description : 배치작업 수정 페이지
  Modification Information
 
       수정일                 수정자              수정내용
    ----------    --------    ---------------------------
    2010.07.09    김진만              최초 생성
    2018.09.04    신용호              공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.07.09
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymBat.batchOpertUpdt.title"/></title><!-- 배치작업 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="batchOpert" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
    var varForm = document.getElementById("batchOpert");
    varForm.action = "<c:url value='/sym/bat/getBatchOpertList.do'/>";
    varForm.submit()
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
    var varForm = document.getElementById("batchOpert");

    if(confirm("<spring:message code='common.save.msg' />")){
        varForm.action =  "<c:url value='/sym/bat/updateBatchOpert.do'/>";

        if(!validateBatchOpert(varForm)){             
            return;
        }else{
            varForm.submit();
        } 
    }
}

</script>
</head>
<body>
<%-- noscript 태그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="batchOpert" id="batchOpert" action="${pageContext.request.contextPath}/sym/bat/updateBatchOpert.do" method="post">

    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default="1"/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymBat.batchOpertUpdt.pageTop.title"/></h2><!-- 배치작업 수정 -->
	<span>※ "배치프로그램은 globals.properties > SHELL.(UNIX/WINDOWS).batchShellFiles에 미리 등록하여야 실행이 가능하다.(WhiteList)</span>
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymBat.batchOpertUpdt.batchOpertId"/> <span class="pilsu">*</span></th><!-- 배치작업ID -->
			<td class="left">
			    <form:input path="batchOpertId" size="20" maxlength="20" readonly="true" cssClass="readOnlyClass"/>
	        	<form:errors path="batchOpertId" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchOpertUpdt.batchOpertNm"/> <span class="pilsu">*</span></th><!-- 배치작업명 -->
			<td class="left">
			    <form:input path="batchOpertNm" size="60" maxlength="60"/>
	        	<form:errors path="batchOpertNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchOpertUpdt.batchProgrm"/> <span class="pilsu">*</span></th><!-- 배치프로그램 -->
			<td class="left">
			    <form:input path="batchProgrm" size="60" maxlength="255"/>
	        	<form:errors path="batchProgrm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymBat.batchOpertUpdt.paramtr"/></th><!-- 파라미터 -->
			<td class="left">
			    <form:input path="paramtr" size="60" maxlength="250"/>
	      		<form:errors path="paramtr" cssClass="error" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/bat/getBatchOpertList.do'></c:url>" onclick="fn_egov_list(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>