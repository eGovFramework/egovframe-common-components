<%
/**
 * @Class Name : EgovMtgPlaceResveDetail.java
 * @Description : EgovMtgPlaceResveDetail.jsp
 * @Modification Information 
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용          최초 생성
 * @ 2018.08.21    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.12    최 두 영           다국어처리
 *
 *  @author 이      용
 *  @since 2010.06.29
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
<title><spring:message code="comUssIonMtg.mtgPlaceResveDetail.title" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mtgPlaceManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">

	function fncSelectMtgPlaceResveManageList() {
	    var varFrom = document.getElementById("mtgPlaceResve");
	    varFrom.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>";
	    varFrom.submit();       
	}

	<c:if test="${mtgPlaceManageVO.resveManId eq mtgPlaceManageVO.usidTemp}">
		/*설명 : 회의실 예약 수정조회 */
		function fncSelectMtgPlaceResveManage() {
			var varFrom = document.getElementById("mtgPlaceResve");
			varFrom.cmd.value  = "updt";
			varFrom.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageDetail.do'/>";
			varFrom.submit();   
		}
		
		/*설명 : 회의실 예약 삭제 */
		function fncDeleteMtgPlaceResve() {
		    var varFrom = document.getElementById("mtgPlaceResve");
		    varFrom.action = "<c:url value='/uss/ion/mtg/deleteMtgPlaceResve.do'/>";
		    if(confirm("<spring:message code="common.delete.msg" />")){
		        varFrom.submit();
		    }
		}
	</c:if>

	/* ********************************************************
	* 회의실 이미지  팝업창열기
	* fn_egov_dplact_ceck
	* ******************************************************** */
	function fn_mtgPlace_image(){
		var varFrom = document.getElementById("mtgPlaceResve");
		var arrParam = new Array(1);
		arrParam[0] = window;
	    sTempValue = "sTmMtgPlaceId="+varFrom.mtgPlaceId.value;
	 	window.showModalDialog("<c:url value='/uss/ion/mtg/selectMtgPlaceImage.do'/>?"+sTempValue, arrParam,"dialogWidth=720px;dialogHeight=400px;resizable=yes;center=yes");
	}
</script>
</head> 
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="mtgPlaceResve" name="mtgPlaceResve" method="post" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonMtg.mtgPlaceResveDetail.title" /></h2>

<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonMtg.mtgPlaceResveDetail.submit" />" title="<spring:message code="comUssIonMtg.mtgPlaceResveDetail.submit" />"></div>
<input type="hidden" name="cmd" value="updt" >
<input type="hidden" name="mtgPlaceId" value ="<c:out value='${mtgPlaceManageVO.mtgPlaceId}'/>">
<input type="hidden" name="resveDe"    value ="<c:out value='${mtgPlaceManageVO.resveDe   }'/>"/>
<input type="hidden" name="resveId"    value ="<c:out value='${mtgPlaceManageVO.resveId   }'/>"/>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgSj" /> <span class="pilsu">*</span></th><!-- 제목 -->
			<td class="left" colspan="3">
			    <c:out value='${mtgPlaceManageVO.mtgSj}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /> <span class="pilsu">*</span></th><!-- 회의실명 -->
			<td class="left" colspan="3">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.location" /> <span class="pilsu">*</span></th><!-- 회의실 위치 -->
			<td class="left" colspan="3">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceTemp3}'/> <c:out value='${mtgPlaceManageVO.lcDetail}'/>
			    <c:if test="${!empty mtgPlaceManageVO.atchFileId}"> 
			    	<span class="button"><a href="#LINK" onclick="fn_mtgPlace_image(); return false;" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.newWindow" />"><spring:message code="comUssIonMtg.mtgPlaceResveRegist.image" /></a></span><!-- 회의실 이미지 -->
			    </c:if>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.reserver" /> <span class="pilsu">*</span></th><!-- 예약자 -->
			<td class="left">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceTemp4}'/>
			</td>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.belong" /></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${mtgPlaceManageVO.mtgPlaceTemp5}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveTime" /> <span class="pilsu">*</span></th><!-- 예약시간 -->
			<td class="left" colspan="3">
			    <c:out value='${mtgPlaceManageVO.resveDe}'/> &nbsp;&nbsp;<c:out value='${mtgPlaceManageVO.resveBeginTm}'/> ~ <c:out value='${mtgPlaceManageVO.resveEndTm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.attendPeople" /> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
			    <c:out value='${mtgPlaceManageVO.atndncNmpr}'/> <spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /><!-- 명 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgCn" /></th><!-- 회의내용 -->
			<td class="left" colspan="3">
			    <textarea id="mtgCn" name="mtgCn" class="txaClass" rows="4" cols="70" disabled title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgCn" />"><c:out value='${mtgPlaceManageVO.mtgCn}'/></textarea>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${mtgPlaceManageVO.resveManId eq mtgPlaceManageVO.usidTemp}">
			<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManage.do'/>?searchCondition=1" onclick="fncSelectMtgPlaceResveManage(); return false;"><spring:message code="button.update" /></a></span>
			<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/deleteMtgPlaceResve.do'/>?searchCondition=1" onclick="fncDeleteMtgPlaceResve(); return false;"><spring:message code="button.delete" /></a></span>     
		</c:if>
		<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>?searchCondition=1" onclick="fncSelectMtgPlaceResveManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>