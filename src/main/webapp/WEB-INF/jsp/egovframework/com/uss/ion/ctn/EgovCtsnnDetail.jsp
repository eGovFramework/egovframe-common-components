<%
/**
 * @Class Name : EgovCtsnnManageDetail.java
 * @Description : EgovCtsnnManageDetail.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.16    최 두 영     퍼블리싱 점검/오류개선
 * @ 2018.09.18    최 두 영     다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<title><spring:message code="comUssIonCtn.ctsnnManageDetail.title"/></title><!-- 경조사 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<validator:javascript formName="ctsnnManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fncEgovCtsnnManageList(){
		location.href = "<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>";
	}
<c:if test="${ctsnnManageVO.confmAt eq 'A' }">	
	/* ********************************************************
	* 수정화면으로  바로가기
	******************************************************** */
	function fncEgovCtsnnManage() {
			var varFrom = document.getElementById("ctsnnManage");
			varFrom.action = "<c:url value='/uss/ion/ctn/EgovCtsnnManageDetail.do'/>";
			varFrom.submit();   
	}
	
	/* ********************************************************
	* 삭제처리화면
	******************************************************** */
	function fncDeleteCtsnnManage() {
		    var varFrom = document.getElementById("ctsnnManage");
		    varFrom.action = "<c:url value='/uss/ion/ctn/deleteCtsnnManage.do'/>";
		    if(confirm("삭제 하시겠습니까?")){
	           varFrom.submit();
		    }
	}
</c:if>
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonCtn.ctsnnManageDetail.title"/></h2><!-- 경조사 상세조회 -->
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnManageDetail.ctsnnAplyr"/></h3><!-- 경조 신청자 -->
	
<form name="ctsnnManage" id="ctsnnManage" method="post" >
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonCtn.ctsnnManageDetail.submit"/>" title="<spring:message code="comUssIonCtn.ctsnnManageDetail.submit"/>"></div><!-- 전송 -->
<input type="hidden" name="ctsnnId"       value="<c:out value='${ctsnnManageVO.ctsnnId}'/>"/>
<input type="hidden" name="cmd"        value="updt" />
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.usNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.usNm}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.usOrgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.orgnztNm}'/>
			</td>
		</tr>
	</table>

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.ctsnnNm"/> <span class="pilsu">*</span></th><!-- 경조명 -->
			<td class="left" colspan="3">
			    <c:out value='${ctsnnManageVO.ctsnnNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.ctsnnCd"/> <span class="pilsu">*</span></th><!-- 경조구분 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.ctsnnCdNm}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.occrrDe"/> <span class="pilsu">*</span></th><!-- 발생일 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.occrrDe}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.trgterNm"/> <span class="pilsu">*</span></th><!-- 대상자명 -->
			<td class="left" colspan="3">
			    <c:out value='${ctsnnManageVO.trgterNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.relate"/><span class="pilsu">*</span></th><!-- 관계 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.relateNm}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.brth"/> <span class="pilsu">*</span></th><!-- 생년월일 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.brth}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnManageDetail.remark"/></th><!-- 비고 -->
			<td class="left" colspan="3">
			    <textarea id="remark" name="remark" class="txaClass" rows="4" cols="70" readonly title="<spring:message code="comUssIonCtn.ctsnnManageDetail.remark"/>"><c:out value='${ctsnnManageVO.remark}'/></textarea>
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnManageDetail.infrmlSanctnId"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 정보 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8">
		<c:param name="infrmlSanctnId" value="${ctsnnManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 정보 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${ctsnnManageVO.confmAt eq 'A' }">
			<span class="btn_s"><a href="<c:url value='/uss/ion/ctn/EgovCtsnnManageDetail.do'/>?cmd=updt&usid=<c:out value='${ctsnnManageVO.usid}'/>&ctsnnCd=<c:out value='${ctsnnManageVO.ctsnnCd}'/>&reqstDe=<c:out value='${ctsnnManageVO.reqstDe}'/>" onclick="fncEgovCtsnnManage(); return false;"><spring:message code="button.update" /></a></span>
          	<span class="btn_s"><a href="<c:url value='/uss/ion/ctn/deleteCtsnnManage.do'/>?cmd=delete&usid=<c:out value='${ctsnnManageVO.usid}'/>&ctsnnCd=<c:out value='${ctsnnManageVO.ctsnnCd}'/>&reqstDe=<c:out value='${ctsnnManageVO.reqstDe}'/>&infrmlSanctnId=<c:out value='${ctsnnManageVO.infrmlSanctnId}'/>" onclick="fncDeleteCtsnnManage(); return false;"><spring:message code="button.delete" /></a></span>
        </c:if>
         
		<span class="btn_s"><a href="<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>?searchCondition=1" onclick="fncEgovCtsnnManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	</form>
</div>
</body>
</html>