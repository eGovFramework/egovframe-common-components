<%
/**
 * @Class Name : EgovVcatnRegist.java
 * @Description : EgovVcatnRegist.jsp
 * @Modification Information
 * @
 * @  수정일            수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영     퍼블리싱 점검
 * @ 2018.09.18    최두영       다국어처리& 달력처리
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
<title><spring:message code="comUssIonVct.vcatnRegist.title"/></title><!-- 휴가신청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="vcatnManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
	function initCalendar(){
	 var varForm = document.getElementById("vcatnManage");	
		$("#bgnde").datepicker(
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
		if(varForm.vcatnSe.value != "02"){
		$("#endde").datepicker(
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
		else {
			fncNoonSeSpan("02");
		}
	}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEgovVcatnManageList(){
	location.href = "<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 
function fncInsertEventVcatnManage() {
    var varForm = document.getElementById("vcatnManage");
    if(varForm.bgnde.value == ""){
        alert("<spring:message code="comUssIonVct.common.validate.bgnde"/>");/* 휴가 시작일자가 존재하지 않습니다. 휴가일자를 확인하세요. */
        return;
    }
    if(varForm.vcatnSe.value != "02" && varForm.endde.value == ""){
        alert("<spring:message code="comUssIonVct.common.validate.endde"/>");/* 휴가종료일자가 존재하지 않습니다. 휴가일자를 확인하세요. */
        return;
    }
    
    if(varForm.vcatnSe.value != "02" && varForm.bgnde.value > varForm.endde.value){
        if(varForm.vcatnSe.value != "02"){
	        alert("<spring:message code="comUssIonVct.common.validate.vcatnSe"/>");/* 휴가일자 검색조건의 시작일자가 종료일자보다  늦습니다. 휴가일자를 확인하세요. */
	        return;
        }
	}

    varForm.action = "<c:url value='/uss/ion/vct/insertVcatnManage.do'/>";
    if(confirm("<spring:message code="common.save.msg" />")){ /* 저장 하시겠습니까? */
        if(varForm.vcatnSe.value == "02"){
        	varForm.endde.value = varForm.bgnde.value;
        }
        if(!validateVcatnManage(varForm)){           
            return;
        }else{
        	varForm.submit();
        } 
    }
}


function fncNoonSeSpan(vValue){
	var vTemp = "";
    if(vValue != "02"){
		vTemp += "  <input name='enddeView' id='endde' type='text' value='<c:out value='${resultInfo.endde}'/>' maxlength='10' readonly='readonly' style='width:70px' />";
        nameSpan.innerHTML = "<label for='endde'><spring:message code="comUssIonVct.common.endde"/></label>";/* 종료일자 */
        noonSeSpan.innerHTML = vTemp;
        initCalendar();
    }
    if(vValue == "02"){
    	vTemp += "  <input name='noonSe' type='radio' value='1' checked><spring:message code="comUssIonVct.common.noonSe1"/>";/* 오전 */
    	vTemp += "  <input name='noonSe' type='radio' value='2'><spring:message code="comUssIonVct.common.noonSe2"/>";/* 오후 */
    	vTemp += '   <form:form modelAttribute="vcatnManage" name="vcatnManage" method="post" ><form:hidden path="endde"/></form:form>';
        nameSpan.innerHTML = "<label for='noonSe'><spring:message code="comUssIonVct.common.noonSe"/></label>";/* 정오구분 */
        noonSeSpan.innerHTML = vTemp;
    }    
}

<c:if test="${!empty errorMessage}">alert("${errorMessage}");</c:if>

-->
</script>
</head>

<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<div class="wTableFrm">

	<form:form modelAttribute="vcatnManage" name="vcatnManage" method="post" >
	<form:hidden  path="applcntId" id="applcntId"/>
	<form:errors  path="applcntId"/>

	<!-- 타이틀 -->
	<h2 class=""><spring:message code="comUssIonVct.vcatnRegist.title"/></h2><!-- 휴가신청 -->

	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.vcatnRegist.vcatnAplyr"/></h3><!-- 휴가 신청자 -->
	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.applcntNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.applcntNm}'/>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.orgnztNm}'/>
			</td>
		</tr>
		<tr>
			<td class="left" colspan="4">
				<span>[<spring:message code="comUssIonVct.common.occrncYrycCo"/>: ${vcatnManageVO.occrncYrycCo}  ]</span><!-- 발생연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.useYrycCo"/>: ${vcatnManageVO.useYrycCo}  ]</span><!-- 사용연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.remndrYrycCo"/>: ${vcatnManageVO.remndrYrycCo}  ]</span><!-- 잔여연차 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnSe"/> <span class="pilsu">*</span></th><!-- 휴가구분 -->
			<td class="left" colspan="3">
				<form:select path="vcatnSe" onchange="javascript:fncNoonSeSpan(this.value);" title="휴가구분">
					<form:options items="${vcatnSeCode}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.startDate"/> <span class="pilsu">*</span></th><!-- 시작일자 -->
			<td class="left">
				<form:input  path="bgnde" maxlength="10" readonly="true" title="휴가 시작일자" cssStyle="width:70px" />
			</td>
			<th><span id="nameSpan"><spring:message code="comUssIonVct.common.endDate"/></span> <span class="pilsu">*</span></th><!-- 종료일자 -->
			<td class="left">
				<span id="noonSeSpan"> 
					<input name="enddeView" id="endde" type="text" value="<c:out value='${resultInfo.endde }'/>" maxlength="10" readonly="readonly" title="휴가 종료일자" style="width:70px" />
				</span>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnResn"/> <span class="pilsu">*</span></th><!-- 휴가사유 -->
			<td class="left" colspan="3">
			    <textarea id="vcatnResn" class="txta01" name="vcatnResn" rows="4" cols="70" title="<spring:message code="comUssIonVct.common.vcatnResn"/>"><c:out value='${vcatnManage.vcatnResn}' escapeXml="false" /></textarea><%-- 휴가사유 --%>
			</td>
		</tr>
	</table>	
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 지정 Include -->
		<c:import url="/WEB-INF/jsp/egovframework/com/uss/ion/ism/EgovInfrmlSanctnRegist.jsp" charEncoding="utf-8"/>
	<!-- //결재권자 지정 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fncInsertEventVcatnManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>?searchCondition=1" onclick="fncEgovVcatnManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>