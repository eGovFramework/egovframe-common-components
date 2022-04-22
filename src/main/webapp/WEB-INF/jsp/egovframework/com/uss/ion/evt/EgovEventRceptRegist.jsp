<%
/**
 * @Class Name : EgovEventRceptRegist.java
 * @Description : EgovEventRceptRegist.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2011.08.17    	정진오		선택입력사항을 표시하는 이미지 icon 경로가
 * 								다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함 
 * @ 2018.09.27    최 두 영         다국어처리
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventRceptRegist.title"/></title><!-- 행사접수신청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="eventAtdrn" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEventRceptManageList(){
	location.href = "<c:url value='/uss/ion/evt/EgovEventRcrptManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fncInsertEventRceptManage() {
	    var varFrom = document.getElementById("eventAtdrn");
	    varFrom.action = "<c:url value='/uss/ion/evt/insertEventAtdrn.do'/>";
	    if(confirm("<spring:message code="button.save" />")){/* 저장 하시겠습니까? */
	        if(!validateEventAtdrn(varFrom)){           
	            return;
	        }else{
	             varFrom.submit();
	        }; 
	  };
}

<c:if test="${!empty errMessage}">alert("${errMessage}");</c:if>
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="eventAtdrn" name="eventAtdrn" method="post">
<form:hidden  path="applcntId" id="applcntId"/>
<form:errors  path="applcntId"/>
<form:hidden  path="eventId" id="eventId"/>
<form:errors  path="eventId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonEvt.eventRceptRegist.title"/></h2><!-- 행사접수신청 -->

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonEvt.common.applcntNm"/> <span class="pilsu">*</span></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventTemp6}'/>
			</td>
			<th><spring:message code="comUssIonEvt.eventRceptDetail.eventManageVO.eventTemp7"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventTemp7}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventTemp3"/> <span class="pilsu">*</span></th><!-- 행사구분 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventTemp3}'/>
			</td>
			<th><spring:message code="comUssIonEvt.common.eventNm"/> <span class="pilsu">*</span></th><!-- 행사명 -->
			<td class="left">
			    <c:out value='${eventManageVO.eventNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPurps"/> <span class="pilsu">*</span></th><!-- 행사목적 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.eventPurps}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventDe"/> <span class="pilsu">*</span></th><!-- 행사기간 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.eventBeginDe}'/> ~ <c:out value='${eventManageVO.eventEndDe}'/> (<c:out value='${eventManageVO.eventTemp1}'/>일간)
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventAuspcInsttNm"/> <span class="pilsu">*</span></th><!-- 행사주최 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.eventAuspcInsttNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventMngtInsttNm"/> <span class="pilsu">*</span></th><!-- 행사주관 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.eventMngtInsttNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventPlace"/> <span class="pilsu">*</span></th><!-- 행사장소 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.eventPlace}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.refrnUrl"/></th><!-- 참조URL -->
			<td class="left" colspan="3">
			    <c:if test="${!empty eventManageVO.refrnUrl}">
			       <a href="<c:out value='${eventManageVO.refrnUrl}'/>" target="_blank"  title="<spring:message code="comUssIonEvt.common.toNewWindow"/>" ><c:out value='${eventManageVO.refrnUrl}'/></a><!-- 새 창으로 이동 -->
			    </c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.eventCn"/> <span class="pilsu">*</span></th><!-- 행사내용 -->
			<td class="left" colspan="3">
			    <textarea id="remark" name="eventCn" class="txaClass" rows="4" cols="70" title="<spring:message code="comUssIonEvt.common.eventCn"/>" readonly="readonly"><c:out value='${eventManageVO.eventCn}'/></textarea><!--  행사내용-->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.ctOccrrncAt"/> <span class="pilsu">*</span></th><!-- 참가비용 -->
			<td class="left" colspan="3">
			    <c:if test="${eventManageVO.ctOccrrncAt == '1'}">(<spring:message code="comUssIonEvt.common.free"/>)</c:if><!-- 무료 -->
       			<c:if test="${eventManageVO.ctOccrrncAt == '2'}">(<spring:message code="comUssIonEvt.common.fee"/>) <c:out value='${eventManageVO.partcptCt}'/><spring:message code="comUssIonEvt.common.feeUnit"/></c:if><!-- 유료 --><!-- 만원 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.psncpa"/> <span class="pilsu">*</span></th><!-- 정원 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.psncpa}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonEvt.common.rceptBeginDe.rceptEndDe"/> <span class="pilsu">*</span></th><!-- 행사접수기간 -->
			<td class="left" colspan="3">
			    <c:out value='${eventManageVO.rceptBeginDe}'/> ~ <c:out value='${eventManageVO.rceptEndDe}'/>
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonEvt.eventRceptRegist.infrmlSanctn"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 지정 Include -->
	<c:import url="/WEB-INF/jsp/egovframework/com/uss/ion/ism/EgovInfrmlSanctnRegist.jsp" charEncoding="utf-8"/>
	<!-- //결재권자 지정 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.create" />' onclick="fncInsertEventRceptManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/evt/selectEventRceptList.do'/>?searchCondition=1" onclick="fncEventRceptManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>
