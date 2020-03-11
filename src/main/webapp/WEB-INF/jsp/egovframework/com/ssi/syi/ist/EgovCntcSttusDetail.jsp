<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcSttusDetail.jsp
  * @Description : EgovCntcSttusDetail 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2018.09.10   신용호              표준프레임워크 v3.8 개선
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comSsiSyiIst.cntcSttusDetail.title"/></title><!-- 연계현황 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIst.cntcSttusDetail.pageTop.title"/></h2><!-- 연계현황 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.cntcId"/> <span class="pilsu">*</span></th><!-- 연계ID -->
			<td class="left">
			    <c:out value="${result.cntcId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.cntcNm"/> <span class="pilsu">*</span></th><!-- 연계명 -->
			<td class="left">
			    <c:out value="${result.cntcNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.cntcType"/> <span class="pilsu">*</span></th><!-- 연계유형 -->
			<td class="left">
			    <c:out value="${result.cntcType}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.provdInsttId"/> <span class="pilsu">*</span></th><!-- 제공기관 -->
			<td class="left">
			    <c:out value="${result.provdInsttNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.provdSysId"/> <span class="pilsu">*</span></th><!-- 제공시스템 -->
			<td class="left">
			    <c:out value="${result.provdSysNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.provdSvcId"/> <span class="pilsu">*</span></th><!-- 제공서비스 -->
			<td class="left">
			    <c:out value="${result.provdSvcNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.requstInsttId"/> <span class="pilsu">*</span></th><!-- 요청기관 -->
			<td class="left">
			    <c:out value="${result.requstInsttNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.requstSysId"/> <span class="pilsu">*</span></th><!-- 요청시스템 -->
			<td class="left">
			    <c:out value="${result.requstSysNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.cntAll"/> <span class="pilsu">*</span></th><!-- 총 연계 건수 -->
			<td class="left">
			    <c:out value="${result.cntAll}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.cntSuccess"/> <span class="pilsu">*</span></th><!-- 성공 건수 -->
			<td class="left">
			    <c:out value="${result.cntSuccess}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIst.cntcSttusDetail.successRate"/> <span class="pilsu">*</span></th><!-- 성공율(%) -->
			<td class="left">
			    <fmt:formatNumber value="${ 100 * result.cntSuccess / result.cntAll }" maxFractionDigits="0"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/ssi/syi/ist/getCntcSttusList.do'/>" onclick=""><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

</body>
</html>
