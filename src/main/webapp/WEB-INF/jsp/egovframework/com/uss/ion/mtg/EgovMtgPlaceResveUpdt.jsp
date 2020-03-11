<%
/**
 * @Class Name : EgovMtgPlaceResveUpdt.java
 * @Description : EgovMtgPlaceResveUpdt.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용                최초 생성
 * @ 2018.08.21    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.12    최 두 영           다국어처리 & showModalDialog.js 오류 개선 & datepicker 적용
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
<title><spring:message code="comUssIonMtg.mtgPlaceResveUpdt.title" /></title><!-- 회의실 예약수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mtgPlaceResve" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
	function initCalendar(){
		$("#resveDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'both'
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
	
	function fncSelectMtgPlaceResveManageList() {
	    var varFrom = document.getElementById("mtgPlaceResve");
	    varFrom.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>";
	    varFrom.submit();
	}
	
	function fncUpdtMtgPlaceResve() {
	     var varFrom = document.getElementById("mtgPlaceResve");
	     varFrom.action = "<c:url value='/uss/ion/mtg/updtMtgPlaceResve.do'/>";
		 if(varFrom.dplactCeck.value == "")  alert("<spring:message code="comUssIonMtg.mtgPlaceResveRegist.dplactCeck" />");/* 회의실 예약 중복확인을 하신 후 회의실 예약을 해주세요. */
		 else if(varFrom.dplactCeck.value == "N") alert("<spring:message code="comUssIonMtg.mtgPlaceResveRegist.reserve" />");/* 이미 회의실이 예약되어 있습니다. */
		 else if(varFrom.dplactCeck.value == "Y") {
		     if(confirm("<spring:message code="common.save.msg" />")){
			     if(!validateMtgPlaceResve(varFrom)){
			         return;
			     }else{
			        varFrom.submit();
			     }
		     }
		 }
	}
	
	function fncDeleteMtgPlaceResve() {
	    var varFrom = document.getElementById("mtgPlaceResve");
	    varFrom.action = "<c:url value='/uss/ion/mtg/deleteMtgPlaceResve.do'/>";
	    if(confirm("<spring:message code="common.delete.msg" />")){
	        varFrom.submit();
	    }
	}
	
	/* ********************************************************
	* 회의실 중복여부 확인  팝업창열기
	* fn_egov_dplact_ceck
	* ******************************************************** */
	function fn_egov_dplact_ceck(){
	
		var varFrom = document.getElementById("mtgPlaceResve");
		var beginTm = varFrom.resveBeginTm.value;
		var endTm   = varFrom.resveEndTm.value;
	
		if((endTm-beginTm) > 0){
			var arrParam = new Array(1);
			arrParam[0] = window;
		    sTempValue = "sTmResveDe="+varFrom.resveDe.value+"&sTmResveBeginTm="+varFrom.resveBeginTm.value+"&sTmResveEndTm="+varFrom.resveEndTm.value+"&sTmMtgPlaceId="+varFrom.mtgPlaceId.value+"&sTmResveId="+varFrom.resveId.value;
		 	window.showModalDialog("<c:url value='/uss/ion/mtg/mtgPlaceResveDplactCeck.do'/>?"+sTempValue, arrParam,"dialogWidth=450px;dialogHeight=150px;resizable=yes;center=yes");
	   }
	   else alert("<spring:message code="comUssIonMtg.mtgPlaceResveUpdt.timeCheck" />");
	}
	
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
<body onLoad="initCalendar();">

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form:form commandName="mtgPlaceResve" name="mtgPlaceResve" method="post" >
<input type="hidden" name="cmd" value="updt" >
<input type="hidden" name="mtgPlaceId" value ="<c:out value='${mtgPlaceManageVO.mtgPlaceId}'/>">
<input type="hidden" name="resveId"    value ="<c:out value='${mtgPlaceManageVO.resveId   }'/>"/>
<input type="hidden" name="resveManId" value ="<c:out value='${mtgPlaceManageVO.resveManId   }'/>"/>
<input type="hidden" name="dplactCeck" id="dplactCeck">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonMtg.mtgPlaceResveUpdt.title" /></h2><!-- 회의실 예약수정 -->

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
			    <input name="mtgSj" type="text" value="<c:out value='${mtgPlaceManageVO.mtgSj}'/>" maxlength="70" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgSj" />" />
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
			    <c:out value='${mtgPlaceManageVO.lcSe}'/> <c:out value='${mtgPlaceManageVO.lcDetail}'/>
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
			    <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
		        <input name="resveDe" id="resveDe" type="text" size="10" value="${mtgPlaceManageVO.resveDe }" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveDe" />" maxlength="10" style="width:70px" />
	        <select name="resveBeginTm" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveBeginTm" />"><!-- 예약시작시간 -->
		       	<option value="800"  <c:if test="${mtgPlaceManageVO.resveBeginTm == '800' }"> selected </c:if>>08:00</option>
		       	<option value="830"  <c:if test="${mtgPlaceManageVO.resveBeginTm == '830' }"> selected </c:if>>08:30</option>
		       	<option value="900"  <c:if test="${mtgPlaceManageVO.resveBeginTm == '900' }"> selected </c:if>>09:00</option>
		       	<option value="930"  <c:if test="${mtgPlaceManageVO.resveBeginTm == '930' }"> selected </c:if>>09:30</option>
		       	<option value="1000" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1000'}"> selected </c:if>>10:00</option>
		       	<option value="1030" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1030'}"> selected </c:if>>10:30</option>
		       	<option value="1100" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1100'}"> selected </c:if>>11:00</option>
		       	<option value="1130" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1130'}"> selected </c:if>>11:30</option>
		       	<option value="1200" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1200'}"> selected </c:if>>12:00</option>
		       	<option value="1230" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1230'}"> selected </c:if>>12:30</option>
		       	<option value="1300" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1300'}"> selected </c:if>>13:00</option>
		       	<option value="1330" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1330'}"> selected </c:if>>13:30</option>
		       	<option value="1400" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1400'}"> selected </c:if>>14:00</option>
		       	<option value="1430" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1430'}"> selected </c:if>>14:30</option>
		       	<option value="1500" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1500'}"> selected </c:if>>15:00</option>
		       	<option value="1530" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1530'}"> selected </c:if>>15:30</option>
		       	<option value="1600" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1600'}"> selected </c:if>>16:00</option>
		       	<option value="1630" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1630'}"> selected </c:if>>16:30</option>
		       	<option value="1700" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1700'}"> selected </c:if>>17:00</option>
		       	<option value="1730" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1730'}"> selected </c:if>>17:30</option>
		       	<option value="1800" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1800'}"> selected </c:if>>18:00</option>
		       	<option value="1830" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1830'}"> selected </c:if>>18:30</option>
		       	<option value="1900" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1900'}"> selected </c:if>>19:00</option>
		       	<option value="1930" <c:if test="${mtgPlaceManageVO.resveBeginTm == '1930'}"> selected </c:if>>19:30</option>
		       	<option value="2000" <c:if test="${mtgPlaceManageVO.resveBeginTm == '2000'}"> selected </c:if>>20:00</option>
		       	<option value="2030" <c:if test="${mtgPlaceManageVO.resveBeginTm == '2030'}"> selected </c:if>>20:30</option>
		       	<option value="2100" <c:if test="${mtgPlaceManageVO.resveBeginTm == '2100'}"> selected </c:if>>21:00</option>
	      	</select>
	      	~
				<select name="resveEndTm" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.resveEndTm" />"><!-- 예약종료시간 -->
		       	<option value="800"  <c:if test="${mtgPlaceManageVO.resveEndTm == '800' }"> selected </c:if>>08:00</option>
		       	<option value="830"  <c:if test="${mtgPlaceManageVO.resveEndTm == '830' }"> selected </c:if>>08:30</option>
		       	<option value="900"  <c:if test="${mtgPlaceManageVO.resveEndTm == '900' }"> selected </c:if>>09:00</option>
		       	<option value="930"  <c:if test="${mtgPlaceManageVO.resveEndTm == '930' }"> selected </c:if>>09:30</option>
		       	<option value="1000" <c:if test="${mtgPlaceManageVO.resveEndTm == '1000'}"> selected </c:if>>10:00</option>
		       	<option value="1030" <c:if test="${mtgPlaceManageVO.resveEndTm == '1030'}"> selected </c:if>>10:30</option>
		       	<option value="1100" <c:if test="${mtgPlaceManageVO.resveEndTm == '1100'}"> selected </c:if>>11:00</option>
		       	<option value="1130" <c:if test="${mtgPlaceManageVO.resveEndTm == '1130'}"> selected </c:if>>11:30</option>
		       	<option value="1200" <c:if test="${mtgPlaceManageVO.resveEndTm == '1200'}"> selected </c:if>>12:00</option>
		       	<option value="1230" <c:if test="${mtgPlaceManageVO.resveEndTm == '1230'}"> selected </c:if>>12:30</option>
		       	<option value="1300" <c:if test="${mtgPlaceManageVO.resveEndTm == '1300'}"> selected </c:if>>13:00</option>
		       	<option value="1330" <c:if test="${mtgPlaceManageVO.resveEndTm == '1330'}"> selected </c:if>>13:30</option>
		       	<option value="1400" <c:if test="${mtgPlaceManageVO.resveEndTm == '1400'}"> selected </c:if>>14:00</option>
		       	<option value="1430" <c:if test="${mtgPlaceManageVO.resveEndTm == '1430'}"> selected </c:if>>14:30</option>
		       	<option value="1500" <c:if test="${mtgPlaceManageVO.resveEndTm == '1500'}"> selected </c:if>>15:00</option>
		       	<option value="1530" <c:if test="${mtgPlaceManageVO.resveEndTm == '1530'}"> selected </c:if>>15:30</option>
		       	<option value="1600" <c:if test="${mtgPlaceManageVO.resveEndTm == '1600'}"> selected </c:if>>16:00</option>
		       	<option value="1630" <c:if test="${mtgPlaceManageVO.resveEndTm == '1630'}"> selected </c:if>>16:30</option>
		       	<option value="1700" <c:if test="${mtgPlaceManageVO.resveEndTm == '1700'}"> selected </c:if>>17:00</option>
		       	<option value="1730" <c:if test="${mtgPlaceManageVO.resveEndTm == '1730'}"> selected </c:if>>17:30</option>
		       	<option value="1800" <c:if test="${mtgPlaceManageVO.resveEndTm == '1800'}"> selected </c:if>>18:00</option>
		       	<option value="1830" <c:if test="${mtgPlaceManageVO.resveEndTm == '1830'}"> selected </c:if>>18:30</option>
		       	<option value="1900" <c:if test="${mtgPlaceManageVO.resveEndTm == '1900'}"> selected </c:if>>19:00</option>
		       	<option value="1930" <c:if test="${mtgPlaceManageVO.resveEndTm == '1930'}"> selected </c:if>>19:30</option>
		       	<option value="2000" <c:if test="${mtgPlaceManageVO.resveEndTm == '2000'}"> selected </c:if>>20:00</option>
		       	<option value="2030" <c:if test="${mtgPlaceManageVO.resveEndTm == '2030'}"> selected </c:if>>20:30</option>
		       	<option value="2100" <c:if test="${mtgPlaceManageVO.resveEndTm == '2100'}"> selected </c:if>>21:00</option>
	      	</select>
	      	<a class="btn02" href="#LINK" onclick="fn_egov_dplact_ceck(); return false;" title="새창으로">중복체크</a>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.attendPeople" /></th><!-- 참석인원 -->
			<td class="left" colspan="3">
			    <input name="atndncNmpr" type="text" value="<c:out value='${mtgPlaceManageVO.atndncNmpr}'/>" maxlength="3" style="width:30px;" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.attendPeople" />" /> <spring:message code="comUssIonMtg.mtgPlaceManageList.persons" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgCn" /></th><!-- 회의내용 -->
			<td class="left" colspan="3">
			    <textarea id="mtgCn" name="mtgCn" class="txaClass" rows="4" cols="70" title="<spring:message code="comUssIonMtg.mtgPlaceResveRegist.mtgCn" />"><c:out value='${mtgPlaceManageVO.mtgCn}'/></textarea><!-- 회의내용 -->
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtMtgPlaceResve(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/selectMtgPlaceResveManageList.do'/>?searchCondition=1" onclick="fncSelectMtgPlaceResveManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>