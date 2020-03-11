<%
/**
 * @Class Name : EgovEventReqstDetail.java
 * @Description : EgovEventReqstDetail jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2011.08.17    	정진오		선택입력사항을 표시하는 이미지 icon 경로가
 * 								다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함 *
 * @ 2018.09.18    최 두 영         다국어처리
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventReqstDetail.title"/></title><!-- 행사  상세 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<c:if test="${empty check_popup}">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEventReqstManageList(){
	location.href = "<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>";
}
<c:if test="${eventManageVO.eventAtdrnCount == 0 && eventManageVO.eventDayCount >= 0}">
/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fncEventReqstManage() {
		var varFrom = document.getElementById("eventManage");
		varFrom.cmd.value  = "updt";
		varFrom.action = "<c:url value='/uss/ion/evt/EgovEventReqstDetail.do'/>";
		varFrom.submit();   
}
/* ********************************************************
* 삭제처리화면
******************************************************** */

function fncDeleteEventReqstManage() {
   var varFrom = document.getElementById("eventManage");
   varFrom.action = "<c:url value='/uss/ion/evt/EgovEventReqstDelete.do'/>";
   if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
       varFrom.submit();
   }
}
</c:if>
-->
</c:if>
</script>
</head>

<body>
<c:if test="${empty check_popup}">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
</c:if>

<form name="eventManage" id="eventManage" method="post" >
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonEvt.common.submit"/>" title="<spring:message code="comUssIonEvt.common.submit"/>"></div><!-- 전송 -->
<input name="cmd" type="hidden" value="updt"/>
<input name="eventId" type="hidden" value="<c:out value='${eventManageVO.eventId}'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonEvt.eventReqstDetail.title"/></h2><!-- 행사 상세 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonEvt.common.searchSe"/> <span class="pilsu">*</span></th><!-- 행사구분 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventTemp3}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventNm"/> <span class="pilsu">*</span></th><!-- 행사명 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPurps"/> <span class="pilsu">*</span></th><!-- 행사목적 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventPurps}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventDe"/> <span class="pilsu">*</span></th><!-- 행사기간 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventBeginDe}'/> ~ <c:out value='${eventManageVO.eventEndDe}'/> (<c:out value='${eventManageVO.eventTemp1}'/><spring:message code="comUssIonEvt.common.days"/>)<!-- 일간 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventAuspcInsttNm"/> <span class="pilsu">*</span></th><!-- 행사주최 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventAuspcInsttNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventMngtInsttNm"/> <span class="pilsu">*</span></th><!-- 행사주관 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventMngtInsttNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPlace"/> <span class="pilsu">*</span></th><!-- 행사장소 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventPlace}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.refrnUrl"/></th><!-- 참조URL -->
			<td class="left">
			    <c:if test="${!empty eventManageVO.refrnUrl}">
			       <a href="<c:out value='${eventManageVO.refrnUrl}'/>" target="_blank"  title="<spring:message code="comUssIonEvt.common.toNewWindow"/>" ><c:out value='${eventManageVO.refrnUrl}'/></a><!-- 새 창으로 이동 -->
			    </c:if>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventCn"/> <span class="pilsu">*</span></th><!-- 행사내용 -->
			<td class="left">
			    <textarea id="remark" name="eventCn" class="txaClass" rows="4" cols="70" title="행사내용" readonly ><c:out value='${eventManageVO.eventCn}'/></textarea>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.ctOccrrncAt"/> <span class="pilsu">*</span></th><!-- 참가비용 -->
			<td class="left">
			    <c:if test="${eventManageVO.ctOccrrncAt == '1'}"> <spring:message code="comUssIonEvt.common.free"/> </c:if><!-- 무료 -->
				<c:if test="${eventManageVO.ctOccrrncAt == '2'}"> <spring:message code="comUssIonEvt.common.fee"/><!-- 유료 -->       
				<c:out value='${eventManageVO.partcptCt}'/><spring:message code="comUssIonEvt.common.feeUnit"/><!-- 만원 -->
				</c:if>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.psncpa"/> <span class="pilsu">*</span></th><!-- 정원 -->
			<td class="left">
			    <c:out value='${eventManageVO.psncpa}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.rceptBeginDe.rceptEndDe"/> <span class="pilsu">*</span></th><!-- 행사접수기간 -->
			<td class="left">
			    <c:out value='${eventManageVO.rceptBeginDe}'/> ~ <c:out value='${eventManageVO.rceptEndDe}'/>
			</td>
		</tr>
	</table>
</form>
	<!-- 하단 버튼 -->
	<div class="btn">		
		<c:if test="${check_popup == 'Y'}">
		</c:if>
		
		<c:if test="${empty check_popup}">
			<c:if test="${eventManageVO.eventAtdrnCount == 0 && eventManageVO.eventDayCount >= 0}">
				<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fncEventReqstManage(); return false;" />
				<span class="btn_s"><a href="<c:url value='/uss/ion/evt/EgovEventReqstDelete.do'/>?eventId=<c:out value='${eventManageVO.eventId}'/>" onclick="fncDeleteEventReqstManage(); return false;"><spring:message code="button.delete" /></a></span>
			</c:if>
			
			<span class="btn_s"><a href="<c:url value='/uss/ion/evt/EgovEventReqstManageList.do'/>" onclick="fncEventReqstManageList(); return false;"><spring:message code="button.list" /></a></span>
		</c:if>
	</div>
	
	<c:if test="${check_popup == 'Y'}">
	<div style="padding-top:18px; text-align:center">
		<span class="btn_s"><a href="" onclick="window.close(); return false;"><spring:message code="button.close" /></a></span><!-- 닫기 -->
	</div>
	</c:if>

	<div style="clear:both;"></div>
</div>
</body>
</html>
