<%
/**
 * @Class Name : EgovMtgPlaceRegist.java
 * @Description : EgovMtgPlaceRegist.jsp
 * @Modification Information 
 * @
 * @  수정일           수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이     용                최초 생성
 * @ 2018.08.20    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.11    최 두  영                다국어처리 적용
 *
 *  @author 이      용
 *  @since 2010.06.29
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonMtg.mtgPlaceRegist.title" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />" ></script>
<script type="text/javaScript" language="javascript">

	function fncMtgPlaceClear() {
		var varFrom = document.getElementById("mtgPlaceManageVO") || document.forms["mtgPlaceManageVO"];
		if (!varFrom) return;
		varFrom.mtgPlaceNm.value       = "";
		varFrom.aceptncPosblNmpr.value = 5;
		varFrom.opnBeginTm.value       = "08:00";
		varFrom.opnEndTm.value         = "21:00";
		varFrom.lcDetail.value         = "";
		if (varFrom.lcSe && varFrom.lcSe[0]) varFrom.lcSe[0].selected = true;
	}

	function fncSelectMtgPlaceManageList() {
	    var varFrom = document.getElementById("mtgPlaceManageVO") || document.forms["mtgPlaceManageVO"];
	    if (varFrom) { varFrom.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>"; varFrom.submit(); }
	}

  	function fncInsertMtgPlace(){
	  var varFrom = document.getElementById("mtgPlaceManageVO") || document.forms["mtgPlaceManageVO"];
	  if (!varFrom) return;
	  if(!validateMtgPlaceManage(varFrom)){
             return;
      	  }
	  if(confirm("<spring:message code="common.save.msg" />")){
		  varFrom.action = "<c:url value='/uss/ion/mtg/insertMtgPlace.do'/>";
	      varFrom.submit();
	   }
	}	
  	
</script>
</head> 
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="mtgPlaceManageVO" name="mtgPlaceManageVO" id="mtgPlaceManageVO" method="post" action="${pageContext.request.contextPath}/uss/ion/mtg/insertMtgPlace.do" enctype="multipart/form-data"> 
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonMtg.mtgPlaceRegist.title" /></h2><!-- 회의실 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:25%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /><span class="pilsu">*</span></th><!-- 회의실명 -->
			<td class="left" colspan="3">
				<c:set var="mtgPlaceNm"><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /></c:set>
			    <form:input path="mtgPlaceNm" title="${mtgPlaceNm}" /><!-- 회의실명 -->
			    <div><form:errors path="mtgPlaceNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceRegist.occupancy" /><span class="pilsu">*</span></th><!-- 수용가능인원 -->
			<td class="left">
			    <select name="aceptncPosblNmpr" title="<spring:message code="comUssIonMtg.mtgPlaceRegist.occupancy"/>"><!-- 수용가능인원 -->
					<option value="0" <c:if test="${empty mtgPlaceManageVO.aceptncPosblNmpr || mtgPlaceManageVO.aceptncPosblNmpr == '0'}">selected</c:if>><spring:message code="input.select" /></option>
					<option value="5" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '5'}">selected</c:if>>5<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option><!-- 명 -->
					<option value="10" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '10'}">selected</c:if>>10<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="15" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '15'}">selected</c:if>>15<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="20" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '20'}">selected</c:if>>20<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="25" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '25'}">selected</c:if>>25<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="30" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '30'}">selected</c:if>>30<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="50" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '50'}">selected</c:if>>50<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="70" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '70'}">selected</c:if>>70<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
					<option value="100" <c:if test="${mtgPlaceManageVO.aceptncPosblNmpr == '100'}">selected</c:if>>100<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
				</select>
			    <div><form:errors path="aceptncPosblNmpr" cssClass="error" /></div>
			</td>
			<th><spring:message code="comUssIonMtg.mtgPlaceRegist.openTime" /> <span class="pilsu">*</span></th><!-- 개방시간 -->
			<td class="left">
				<select name="opnBeginTm" title="<spring:message code="comUssIonMtg.mtgPlaceRegist.openTimeFrom" />"><!-- 개방시작시간 -->
					<option value="" <c:if test="${empty mtgPlaceManageVO.opnBeginTm}">selected</c:if>><spring:message code="input.select" /></option>
					<option value="08:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '08:00'}">selected</c:if>>08:00</option>
					<option value="09:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '09:00'}">selected</c:if>>09:00</option>
					<option value="10:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '10:00'}">selected</c:if>>10:00</option>
					<option value="11:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '11:00'}">selected</c:if>>11:00</option>
					<option value="12:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '12:00'}">selected</c:if>>12:00</option>
					<option value="13:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '13:00'}">selected</c:if>>13:00</option>
					<option value="14:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '14:00'}">selected</c:if>>14:00</option>
					<option value="15:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '15:00'}">selected</c:if>>15:00</option>
					<option value="16:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '16:00'}">selected</c:if>>16:00</option>
					<option value="17:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '17:00'}">selected</c:if>>17:00</option>
					<option value="18:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '18:00'}">selected</c:if>>18:00</option>
					<option value="19:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '19:00'}">selected</c:if>>19:00</option>
					<option value="20:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '20:00'}">selected</c:if>>20:00</option>
					<option value="21:00" <c:if test="${mtgPlaceManageVO.opnBeginTm == '21:00'}">selected</c:if>>21:00</option>
				</select>
				~
				<select name="opnEndTm" title="<spring:message code="comUssIonMtg.mtgPlaceRegist.openTimeTo" />"><!-- 개방종료시간 -->
					<option value="" <c:if test="${empty mtgPlaceManageVO.opnEndTm}">selected</c:if>><spring:message code="input.select" /></option>
					<option value="08:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '08:00'}">selected</c:if>>08:00</option>
					<option value="09:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '09:00'}">selected</c:if>>09:00</option>
					<option value="10:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '10:00'}">selected</c:if>>10:00</option>
					<option value="11:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '11:00'}">selected</c:if>>11:00</option>
					<option value="12:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '12:00'}">selected</c:if>>12:00</option>
					<option value="13:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '13:00'}">selected</c:if>>13:00</option>
					<option value="14:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '14:00'}">selected</c:if>>14:00</option>
					<option value="15:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '15:00'}">selected</c:if>>15:00</option>
					<option value="16:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '16:00'}">selected</c:if>>16:00</option>
					<option value="17:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '17:00'}">selected</c:if>>17:00</option>
					<option value="18:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '18:00'}">selected</c:if>>18:00</option>
					<option value="19:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '19:00'}">selected</c:if>>19:00</option>
					<option value="20:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '20:00'}">selected</c:if>>20:00</option>
					<option value="21:00" <c:if test="${mtgPlaceManageVO.opnEndTm == '21:00'}">selected</c:if>>21:00</option>
				</select>
			    <div><form:errors path="opnBeginTm" cssClass="error" /><form:errors path="opnEndTm" cssClass="error" /><form:errors path="opnTimeRangeValid" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceManageList.location" /><span class="pilsu">*</span></th><!-- 위치 -->
			<td class="left" colspan="3">
			 	<c:set var="selectLoacation"><spring:message code="comUssIonMtg.mtgPlaceRegist.locationSelection" /></c:set>
			 	<c:set var="locationDetail"><spring:message code="comUssIonMtg.mtgPlaceRegist.locationDetail" /></c:set>
			    <form:select path="lcSe" title="${selectLoaction}"><!-- 위치선택 -->
					<form:options items="${lcSeCode}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<form:input path="lcDetail" title="${locationDetail}" cssStyle="width:509px" /><!-- 위치상세 -->
			    <div><form:errors path="lcDetail" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceRegist.imageUpload" /></th><!-- 이미지 파일첨부 -->
			<td class="left" colspan="3">
				<c:set var="attachments"><spring:message code="comUssIonMtg.mtgPlaceRegist.attachments" /></c:set>
			    <input name="file_1" id="egovComFileUploader" type="file" multiple  title="${attachments}"/><!-- 첨부파일 -->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="" onclick="fncMtgPlaceClear(); return false;"><spring:message code="button.init" /></a></span><!-- 초기화 -->
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncInsertMtgPlace(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>?searchCondition=1" onclick="fncSelectMtgPlaceManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>
<script type="text/javascript">
   var maxFileNum = document.getElementById('posblAtchFileNumber').value;
   if(maxFileNum==null || maxFileNum==""){
     maxFileNum = 3;
   }
   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script> 
</body>
</html>
