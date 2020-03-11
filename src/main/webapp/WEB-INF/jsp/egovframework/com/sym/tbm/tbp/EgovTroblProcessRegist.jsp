<%--
/**
 * @Class Name  : EgovTroblProcessRegist.java
 * @Description : EgovTroblProcessRegist jsp
 * @Modification Information
 * @
 * @ 수정일               수정자              수정내용
 * @ ----------   --------    ---------------------------
 * @ 2010.07.01   lee.m.j     최초 생성
 *   2018.09.07   신용호             공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymTbmTbp.troblProcessRegist.title"/></title><!-- 장애처리결과 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="troblProcess" staticJavascript="false" xhtml="true" cdata="false"/>
<link type="text/css"" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function initCalendar(){
	$("#troblProcessD").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
}

function fncTroblProcessInsert() {

	var varFrom = document.getElementById("troblProcess");
    varFrom.action = "<c:url value='/sym/tbm/tbp/addTroblProcess.do'/>";

	var requstTimeTmp;
	var requstTime = "<c:out value='${troblProcess.troblRequstTime}'/>";
	requstTimeTmp = requstTime.substring(0,4);
    requstTimeTmp = requstTimeTmp + requstTime.substring(5,7);
    requstTimeTmp = requstTimeTmp + requstTime.substring(8,10);
    requstTimeTmp = requstTimeTmp + requstTime.substring(11,13);
	requstTimeTmp = requstTimeTmp + requstTime.substring(14,16);
	requstTimeTmp = requstTimeTmp + requstTime.substring(17,19);

	var processTime = varFrom.troblProcessD.value;
	var processTimeTmp = processTime.substring(0,4);
	processTimeTmp = processTimeTmp + processTime.substring(5,7);
    processTimeTmp = processTimeTmp + processTime.substring(8,10);
    processTimeTmp = processTimeTmp + varFrom.troblProcessH.value +
                                      varFrom.troblProcessM.value +
                                      varFrom.troblProcessS.value;

    if(requstTimeTmp > processTimeTmp) {
        alert("<spring:message code="comSymTbmTbp.troblProcessRegist.validate.infoTime"/>"); //처리시간은 장애요청시간 이후로 설정하셔야 합니다.
        return;
    }

    varFrom.troblProcessTime.value = varFrom.troblProcessD.value +
                                     varFrom.troblProcessH.value +
                                     varFrom.troblProcessM.value +
                                     varFrom.troblProcessS.value;

    if(confirm("<spring:message code="comSymTbmTbp.troblProcessRegist.validate.save"/>")){ //저장 하시겠습니까?
        if(!validateTroblProcess(varFrom)) {
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncTroblProcessDelete(troblId) {
    var varFrom = document.getElementById("troblProcess");

    varFrom.troblId.value = troblId;
    varFrom.action = "<c:url value='/sym/tbm/tbp/removeTroblProcess.do'/>";

    if(confirm("<spring:message code="comSymTbmTbp.troblProcessRegist.validate.delete"/>")) { //삭제 하시겠습니까?
        varFrom.submit();
    }
}

function fncSelectTroblProcessList() {
    var varFrom = document.getElementById("troblProcess");
    varFrom.action = "<c:url value='/sym/tbm/tbp/selectTroblProcessList.do'/>";
    varFrom.submit();
}

-->
</script>
</head>

<c:set var="troblProcessTime" value='${troblProcess.troblProcessTime}' />
<c:set var="troblProcessDtmp" value="${fn:substring(troblProcessTime,0,10)}" />
<c:set var="troblProcessHtmp" value="${fn:substring(troblProcessTime,11,13)}" />
<c:set var="troblProcessMtmp" value="${fn:substring(troblProcessTime,14,16)}" />
<c:set var="troblProcessStmp" value="${fn:substring(troblProcessTime,17,19)}" />
<c:if test="${troblProcessHtmp < 10}"><c:set var="troblProcessHtmp" value="${fn:substring(troblProcessTime,12,13)}" /></c:if>
<c:if test="${troblProcessMtmp < 10}"><c:set var="troblProcessMtmp" value="${fn:substring(troblProcessTime,15,16)}" /></c:if>
<c:if test="${troblProcessStmp < 10}"><c:set var="troblProcessStmp" value="${fn:substring(troblProcessTime,18,19)}" /></c:if>

<body onload="initCalendar()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymTbmTbp.troblProcessRegist.pageTop.title"/></h2><!-- 장애처리결과 등록 -->

	<form name="troblProcess" id="troblProcess" method="post" action="${pageContext.request.contextPath}/sym/tbm/tbp/addTroblProcess.do">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblId"/> <span class="pilsu">*</span></th><!-- 장애ID -->
			<td class="left">
			    <c:out value='${troblProcess.troblId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblNm"/> <span class="pilsu">*</span></th><!-- 장애명 -->
			<td class="left">
			    <c:out value='${troblProcess.troblNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblKndNm"/> <span class="pilsu">*</span></th><!-- 장애종류 -->
			<td class="left">
			    <c:out value='${troblProcess.troblKndNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblDc"/> <span class="pilsu">*</span></th><!-- 장애설명 -->
			<td class="left">
			    <label for="troblDc"><textarea name="troblDc" rows="5" cols="80" title="장애설명" readOnly><c:out value='${troblProcess.troblDc}'/></textarea></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblOccrrncTime"/> <span class="pilsu">*</span></th><!-- 장애발생시간 -->
			<td class="left">
			    <c:out value='${troblProcess.troblOccrrncTime}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblRqesterNm"/> <span class="pilsu">*</span></th><!-- 장애등록자 -->
			<td class="left">
			    <c:out value='${troblProcess.troblRqesterNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblRequstTime"/> <span class="pilsu">*</span></th><!-- 장애요청시간 -->
			<td class="left">
			    <c:out value='${troblProcess.troblRequstTime}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.processSttusNm"/> <span class="pilsu">*</span></th><!-- 처리상태 -->
			<td class="left">
			    <c:out value='${troblProcess.processSttusNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblProcessResult"/> <span class="pilsu">*</span></th><!-- 처리결과 -->
			<td class="left">
			    <label for="troblProcessResult"><textarea name="troblProcessResult" rows="5" cols="80" title="처리결과"><c:out value='${troblProcess.troblProcessResult}'/></textarea></label><!-- 처리결과 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblProcessD"/> <span class="pilsu">*</span></th><!-- 처리시간 -->
			<td class="left">
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
	            <!-- <input type="text" name="troblProcessD" id="troblProcessD" value="<c:out value="${troblProcessDtmp}" />" size="10" maxlength="10" title="처리시간" class="readOnlyClass" readonly onClick="javascript:fn_egov_NormalCalendar(document.troblProcess, document.troblProcess.troblProcessDate, document.troblProcess.troblProcessD);" onKeyDown="javascript:if (event.keyCode == 13) { fn_egov_NormalCalendar(document.troblProcess, document.troblProcess.troblProcessDate, document.troblProcess.troblProcessD); }" > -->
	            <label for="troblProcessD"><input type="text" name="troblProcessD" id="troblProcessD" value="<c:out value="${troblProcessDtmp}" />" maxlength="10" title="처리시간" style="width:70px" /></label><!-- 처리시간 -->
	            <input type="hidden" name="troblProcessDate" value=""/>
	            <label for="troblProcessH"><select name="troblProcessH" id="troblProcessH" title="시"><!-- 분 -->
	              <c:forEach var="i" begin="0" end="23" step="1" varStatus="status">
	                <option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblProcessHtmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
	              </c:forEach>
	            </select><spring:message code="comSymTbmTbp.troblProcessRegist.hour"/><!-- 시 -->&nbsp;</label>
	            <label for="troblProcessM"><select name="troblProcessM" id="troblProcessM" title="분"><!-- 분 -->
	              <c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
	                <option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblProcessMtmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
	              </c:forEach>
	            </select><spring:message code="comSymTbmTbp.troblProcessRegist.minute"/><!-- 분 -->&nbsp;</label>
	            <label for="troblProcessS"><select name="troblProcessS" id="troblProcessS" title="초"><!-- 초 -->
	              <c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
	                <option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblProcessStmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
	              </c:forEach>
	            </select><spring:message code="comSymTbmTbp.troblProcessRegist.second"/><!-- 초 --></label>
	            <input type="hidden" name="troblProcessTime" />&nbsp;<form:errors path="troblProcessTime" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbp.troblProcessRegist.troblOpetrNm"/> <span class="pilsu">*</span></th><!-- 처리자 -->
			<td class="left">
			    <label for="troblOpetrNm"><input name="troblOpetrNm" id="troblOpetrNm" type="text" value="<c:out value='${troblProcess.troblOpetrNm}'/>" maxLength="30" size="30" /></label>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.create" />' onclick="fncTroblProcessInsert(); return false;" /><!-- 등록 -->
		
		<c:if test="${troblProcess.processSttus == 'C'}">
		<span class="btn_s"><a href="<c:url value='/sym/tbm/tbp/removeTroblProcess.do'/>?troblId=<c:out value='${troblProcess.troblId}'/>" onclick="fncTroblProcessDelete('${troblProcess.troblId}'); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		</c:if>
		<span class="btn_s"><a href="<c:url value='/sym/tbm/tbp/selectTroblProcessList.do'/>?pageIndex=<c:out value='${troblProcessVO.pageIndex}'/>&amp;strTroblNm=<c:out value="${troblProcessVO.strTroblNm}"/>" onclick="fncSelectTroblProcessList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	
	<input type="hidden" name="troblId" value="<c:out value='${troblProcess.troblId}'/>" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strTroblNm" value="<c:out value='${troblProcessVO.strTroblNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${troblProcessVO.pageIndex}'/>" />
	</form>
	
</div>

</body>
</html>

