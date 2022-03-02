<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%

/**
 * @Class Name : EgovAnnvrsryUpdt.java
 * @Description : EgovAnnvrsryUpdt.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.30    이      용                최초 생성
 * @ 2018.08.13    최 두 영          퍼블리싱 검증 
 * @ 2018.09.19    최 두 영       다국어처리 
 *
 *  @author 이      용
 *  @since 2010.06.30
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonAns.annvrsryUpdt.title"/></title><!-- 기념일 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="annvrsryManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--

function initCalendar(){
	$("#annvrsryDe").datepicker(
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

/*설명 : 기념일 목록조회*/
function fncSelectAnnvrsryManageList() {
    var varFrom = document.getElementById("annvrsryManage");
    varFrom.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>";
    varFrom.submit();       
}
/*설명 : 기념일 수정처리*/
function fncUpdateAnnvrsry() {
    var varFrom = document.getElementById("annvrsryManage");
    varFrom.action = "<c:url value='/uss/ion/ans/updateAnnvrsryManage.do'/>";

    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
        if(!validateAnnvrsryManage(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
    }
}


-->
</script>
</head>

<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="annvrsryManage" name="annvrsryManage" method="post" > 
<form:hidden path="annId"   />
<form:hidden path="usid"   />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonAns.annvrsryUpdt.title"/></h2><!-- 기념일 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp1"/> <span class="pilsu">*</span></th><!-- 신청자 -->
			<td class="left">
			    <c:out value="${annvrsryManageVO.annvrsryTemp1      }"/>
			</td>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp2"/> <span class="pilsu">*</span></th><!-- 소속 -->
			<td class="left">
			    <c:out value="${annvrsryManageVO.annvrsryTemp2      }"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp3"/> <span class="pilsu">*</span></th><!-- 기념일구분 -->
			<td class="left">
				<c:set var="annvrsrySe"><spring:message code="comUssIonAns.common.annvrsryTemp3"/></c:set>
				<form:select path="annvrsrySe" title="${annvrsrySe}"><!-- 기념일구분 -->
				<form:options items="${annvrsrySeCode}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
			</td>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp4"/> <span class="pilsu">*</span></th><!-- 기념일 -->
			<td class="left">
				<form:input  path="annvrsryDe" maxlength="10" readonly="true"  title="기념일" cssStyle="width:70px" />
				<spring:message code="comUssIonAns.common.cldrSe1"/> : <form:radiobutton path="cldrSe"  value="1" title="${cldrSe1}"/>&nbsp;<!-- 양력 -->
				<spring:message code="comUssIonAns.common.cldrSe2"/> : <form:radiobutton path="cldrSe"  value="2" title="${cldrSe2}"/><!-- 음력 -->
				<br/><form:errors path="cldrSe" />
				&nbsp;&nbsp;<spring:message code="comUssIonAns.common.reptitSeEvery"/> : <input type="checkbox" name="reptitSe" id="reptitSe" title="<spring:message code="comUssIonAns.common.reptitSe"/>" value="1" <c:if test="${'1' eq annvrsryManageVO.reptitSe}">checked</c:if> style="vertical-align:-2px" /><!-- 매년반복 : --> <!-- 반복여부 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryNm"/> <span class="pilsu">*</span></th><!--기념일명  -->
			<td class="left" colspan="3">
			    <c:set var="annvrsryNm"><spring:message code="comUssIonAns.common.annvrsryNm"/></c:set>
				<form:input  path="annvrsryNm" size="80" maxlength="255" title="${annvrsryNm}"/>
      			<form:errors path="annvrsryNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.memo"/> <span class="pilsu">*</span></th><!-- 메모 -->
			<td class="left" colspan="3">
			    <c:set var="memo"><spring:message code="comUssIonAns.common.memo"/></c:set>
			    <textarea name="memo" id="memo" style="width:95%;height:100px;" title="${memo}"><c:out value="${annvrsryManageVO.memo      }"   escapeXml="false"/></textarea>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryBeginDe"/></th><!-- 알림시작일 -->
			<td class="left">
				D-
		        <select name="annvrsryBeginDe" title="<spring:message code="comUssIonAns.common.annvrsryBeginDe"/>"><!-- 알림시작일 -->
			       	<option value="7" <c:if test='${annvrsryManageVO.annvrsryBeginDe == "7"}'>selected</c:if>><spring:message code="comUssIonAns.common.annvrsryBeginDe.7"/></option><!-- 일주일 -->
			       	<option value="3" <c:if test='${annvrsryManageVO.annvrsryBeginDe == "3"}'>selected</c:if>><spring:message code="comUssIonAns.common.annvrsryBeginDe.3"/></option><!-- 3일 -->
			       	<option value="2" <c:if test='${annvrsryManageVO.annvrsryBeginDe == "2"}'>selected</c:if>><spring:message code="comUssIonAns.common.annvrsryBeginDe.2"/></option><!-- 2일 -->
			       	<option value="1" <c:if test='${annvrsryManageVO.annvrsryBeginDe == "1"}'>selected</c:if>><spring:message code="comUssIonAns.common.annvrsryBeginDe.1"/></option><!-- 1일 -->
		      	</select> <spring:message code="comUssIonAns.common.annvrsryBeginDe.alarm"/><!-- 전 부터 알림 -->
			</td>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp5"/> <span class="pilsu">*</span></th><!-- 알림설정 -->
			<td class="left">
			    <input name="annvrsrySetup" type="radio" value="Y" title="<spring:message code="comUssIonAns.common.annvrsryTemp5"/>" <c:if test='${annvrsryManageVO.annvrsrySetup == "Y"}'>checked</c:if>>ON <input name="annvrsrySetup" type="radio" style="border:0px;" title="<spring:message code="comUssIonAns.common.annvrsryTemp5"/>" value="N" <c:if test='${annvrsryManageVO.annvrsrySetup == "N"}'>checked</c:if>>OFF
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdateAnnvrsry(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>?searchCondition=1" onclick="fncSelectAnnvrsryManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>
