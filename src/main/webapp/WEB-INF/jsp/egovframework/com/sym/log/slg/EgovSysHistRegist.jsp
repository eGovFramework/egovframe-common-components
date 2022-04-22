<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovSysHistRegist.jsp
  * @Description : 시스템 이력 정보 등록 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2009.03.09  이삼섭          최초 생성
  *   2011.07.08  이기하          패키지 분리로 경로 수정(sym.log -> sym.log.slg)
  *   2018.09.11  신용호          공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymLogSlg.sysHistRegist.title"/></title><!-- 시스템 이력 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="history" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_regist_sysHist(){
		debugger;
		var varForm = document.getElementById("history");
	    if(!validateHistory(varForm)){
	        return;
	    }else{
	        varForm.submit();
	    }

		
		document.history.action = "<c:url value='/sym/log/slg/InsertSysHistory.do'/>";
		document.history.submit();
	}

	function fn_egov_select_sysHist(){
		document.history.action = "<c:url value='/sym/log/slg/SelectSysHistoryList.do'/>";
		document.history.submit();
	}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form modelAttribute="history" name="history" method="post" enctype="multipart/form-data" >
<input name="pageIndex" type="hidden" value="1" />
<input type="hidden" name="posblAtchFileNumber" value="3" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymLogSlg.sysHistRegist.pageTop.title"/></h2><!-- 시스템 이력등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistRegist.histSeCodeNm"/> <span class="pilsu">*</span></th><!-- 이력구분 -->
			<td class="left">
			    <select name="histSeCode" class="select" title="<spring:message code="comSymLogSlg.sysHistRegist.histSeCodeNm" />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
					</c:forEach>
				</select>
				<form:errors path="histSeCode" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistRegist.sysNm"/> <span class="pilsu">*</span></th><!-- 시스템명  -->
			<td class="left">
			    <input name="sysNm" type="text" size="60" value="<c:out value='${history.sysNm}'/>" maxlength="60" id="sysNm">
      			<form:errors path="sysNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistRegist.histCn"/> <span class="pilsu">*</span></th><!-- 이력내용 -->
			<td class="left">
			    <textarea name="histCn" class="textarea"  cols="50" rows="8"  style="width:450px;" id="histCn"><c:out value='${history.histCn}'/></textarea>
      			<form:errors path="histCn" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogSlg.sysHistRegist.atchFile"/></th><!-- 첨부파일 -->
			<td class="left">
				<input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comSymLogSlg.sysHistRegist.atchFile"/>"/><!-- 첨부파일 선택 -->
				<div id="egovComFileList"></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.create" />" onclick="fn_egov_regist_sysHist(); return false;" /><!-- 등록 -->
		<span class="btn_s"><a href="" onclick="fn_egov_select_sysHist(); return false;"><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<script type="text/javascript">
	var maxFileNum = document.history.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
		maxFileNum = 3;
	}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script>
     
</form:form>
</body>
</html>
