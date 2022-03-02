<%--
/**
 * @Class Name  : EgovTroblReqstDetail.java
 * @Description : EgovTroblReqstDetail jsp
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

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymTbmTbr.troblReqstDetail.title"/></title><!-- 장애신청 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectTroblReqstList() {
	var varFrom = document.getElementById("troblReqst");
    varFrom.action = "<c:url value='/sym/tbm/tbr/selectTroblReqstList.do'/>";
    varFrom.submit();       
}

function fncTroblReqstUpdateView(troblId) {
    var varFrom = document.getElementById("troblReqst");

    if(varFrom.processSttus.value != 'A') {
        alert("<spring:message code="comSymTbmTbr.troblReqstDetail.validate."/>"); //수정할 수 없는 상태입니다.
        return;
    }

    varFrom.troblId.value = troblId;
    varFrom.action = "<c:url value='/sym/tbm/tbr/updtViewTroblReqst.do'/>";
    varFrom.submit();
}

function fncTroblReqstDelete(troblId) {
    var varFrom = document.getElementById("troblReqst");

    if(varFrom.processSttus.value != 'A') {
        alert("<spring:message code="comSymTbmTbr.troblReqstDetail.validate."/>"); //삭제할 수 없는 상태입니다
        return;
    }
    
    varFrom.troblId.value = troblId;
    varFrom.action = "<c:url value='/sym/tbm/tbr/removeTroblReqst.do'/>";

    if(confirm("<spring:message code="comSymTbmTbr.troblReqstDetail.validate."/>")) { //삭제 하시겠습니까?
        varFrom.submit();
    }
}

function fncTroblReqstRequst(troblId) {
    var varFrom = document.getElementById("troblReqst");

    if(varFrom.processSttus.value != 'A') {
        alert("<spring:message code="comSymTbmTbr.troblReqstDetail.validate."/>"); //요청 대상이 아닙니다.
        return;
    }

    varFrom.troblId.value = troblId;
    varFrom.action = "<c:url value='/sym/tbm/tbr/requstTroblReqst.do'/>";
    if(confirm("<spring:message code="comSymTbmTbr.troblReqstDetail.validate.request"/>")) { //요청 하시겠습니까?
        varFrom.submit();
    }
}

function fncTroblReqstRequstCancl(troblId) {
    var varFrom = document.getElementById("troblReqst");

    if(varFrom.processSttus.value != 'R') {
        alert("<spring:message code="comSymTbmTbr.troblReqstDetail.validate."/>"); //요청취소 대상이 아닙니다.
        return;
    }
    
    varFrom.troblId.value = troblId;
    varFrom.action = "<c:url value='/sym/tbm/tbr/requstTroblReqstCancl.do'/>";
    if(confirm("<spring:message code="comSymTbmTbr.troblReqstDetail.validate."/>")) { //요청취소를 하시겠습니까?
        varFrom.submit();
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymTbmTbr.troblReqstDetail.pageTop.title"/></h2><!-- 장애신청 상세조회 -->

	<form:form modelAttribute="troblReqst" method="post" action="${pageContext.request.contextPath}/sym/tbm/tbr/updtViewTroblReqst.do">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblId"/> <span class="pilsu">*</span></th><!-- 장애ID -->
			<td class="left">
			    <c:out value='${troblReqst.troblId}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblNm"/> <span class="pilsu">*</span></th><!-- 장애명 -->
			<td class="left">
			    <c:out value='${troblReqst.troblNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblKndNm"/> <span class="pilsu">*</span></th><!-- 장애종류 -->
			<td class="left">
			    <c:out value='${troblReqst.troblKndNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblDc"/> <span class="pilsu">*</span></th><!-- 장애설명 -->
			<td class="left">
			    <label for="troblDc"><textarea name="troblDc" rows="5" cols="80" title="장애설명" readOnly><c:out value='${troblReqst.troblDc}'/></textarea></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblOccrrncTime"/> <span class="pilsu">*</span></th><!-- 장애발생시간 -->
			<td class="left">
			    <c:out value='${troblReqst.troblOccrrncTime}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblRqesterNm"/> <span class="pilsu">*</span></th><!-- 장애등록자 -->
			<td class="left">
			    <c:out value='${troblReqst.troblRqesterNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblRequstTime"/> <span class="pilsu">*</span></th><!-- 장애요청시간 -->
			<td class="left">
			    <c:out value='${troblReqst.troblRequstTime}'/>
			</td>
		</tr>
		
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.processSttusNm"/> <span class="pilsu">*</span></th><!-- 처리상태 -->
			<td class="left">
			    <c:out value='${troblReqst.processSttusNm}'/>
			</td>
		</tr>
		<c:if test="${troblReqst.processSttus == 'C'}">
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblProcessResult"/></th><!-- 처리결과 -->
			<td class="left">
			    <label for="troblProcessResult"><textarea name="troblProcessResult" rows="5" cols="80" title="처리결과" readOnly><c:out value='${troblReqst.troblProcessResult}'/></textarea></label><!-- 처리결과 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblProcessTime"/></th><!-- 처리시간 -->
			<td class="left">
			    <c:out value='${troblReqst.troblProcessTime}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymTbmTbr.troblReqstDetail.troblOpetrNm"/></th><!-- 처리자 -->
			<td class="left">
			    <c:out value='${troblReqst.troblOpetrNm}'/>
			</td>
		</tr>
		</c:if>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${troblReqst.processSttus == 'A'}">
			<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fncTroblReqstUpdateView('${troblReqst.troblId}'); return false;" />
			<span class="btn_s"><a href="<c:url value='/sym/tbm/tbr/removeTroblReqst.do'/>?troblId=<c:out value='${troblReqst.troblId}'/>" onclick="fncTroblReqstDelete('${troblReqst.troblId}'); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
			<span class="btn_s"><a href="<c:url value='/sym/tbm/tbr/requstTroblReqst.do'/>?troblId=<c:out value='${troblReqst.troblId}'/>" onclick="fncTroblReqstRequst('${troblReqst.troblId}'); return false;"><spring:message code="comSymTbmTbr.troblReqstDetail.request"/></a></span><!-- 요청 -->
		</c:if>
        <c:if test="${troblReqst.processSttus == 'R'}">
        	<span class="btn_s2"><a href="<c:url value='/sym/tbm/tbr/requstTroblReqstCancl.do'/>?troblId=<c:out value='${troblReqst.troblId}'/>" onclick="fncTroblReqstRequstCancl('${troblReqst.troblId}'); return false;"><spring:message code="comSymTbmTbr.troblReqstDetail.cancelRequest"/></a></span><!-- 요청취소 -->
        </c:if>
        <span class="btn_s"><a href="<c:url value='/sym/tbm/tbr/selectTroblReqstList.do'/>?pageIndex=<c:out value='${troblReqstVO.pageIndex}'/>&amp;strTroblNm=<c:out value="${troblManageVO.strTroblNm}"/>" onclick="fncSelectTroblReqstList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	
	<input type="hidden" name="troblId" />
	<input type="hidden" name="processSttus" value="<c:out value='${troblReqst.processSttus}'/>" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strTroblNm" value="<c:out value='${troblReqstVO.strTroblNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${troblReqstVO.pageIndex}'/>" />
	</form:form>
	
</div>

</body>
</html>

