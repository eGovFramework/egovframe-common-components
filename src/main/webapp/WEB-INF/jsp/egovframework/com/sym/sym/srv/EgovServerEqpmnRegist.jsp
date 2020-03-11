<%--
/**
 * @Class Name  : EgovServerEqpmnRegist.java
 * @Description : EgovServerEqpmnRegist jsp
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
<title><spring:message code="comSymSymSrv.serverEqpmnRegist.title"/></title><!-- 서버장비 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="serverEqpmn" staticJavascript="false" xhtml="true" cdata="false"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function initCalendar(){
	$("#regstYmd").datepicker( 
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

function fncSelectServerEqpmnList() {
    var varFrom = document.getElementById("serverEqpmn");
    varFrom.action = "<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>";
    varFrom.submit();
}

function fncServerEqpmnInsert() {
    var varFrom = document.getElementById("serverEqpmn");
    varFrom.action = "<c:url value='/sym/sym/srv/addServerEqpmn.do'/>";

    if(confirm("<spring:message code="comSymSymSrv.serverEqpmnRegist.validate.save"/>")){ //저장 하시겠습니까?
        if(!validateServerEqpmn(varFrom)){
            return;
        }else{
            if(!ipValidate(varFrom.serverEqpmnIp.value) || !isEmail(varFrom.mngrEmailAddr.value))
                return;
            else
                varFrom.submit();
        }
    }
}

function ipValidate(ipValue) {

    var varFrom = document.getElementById("serverEqpmn");
    var IPvalue = ipValue;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + " : <spring:message code="comSymSymSrv.serverEqpmnRegist.validate.ip.notAllowed"/>"); //예외 아이피 입니다.
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + " : <spring:message code="comSymSymSrv.serverEqpmnRegist.validate.ip.notAllowed"/>"); //예외 아이피 입니다.
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("<spring:message code="comSymSymSrv.serverEqpmnRegist.validate.ip.formatMismatch"/>"); //IP 형식이 일치 하지않습니다.
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("<spring:message code="comSymSymSrv.serverEqpmnRegist.validate.ip.formatMismatch"/>"); //IP 형식이 일치 하지않습니다.
                result = false;
            }

            if ((i == 0) && (thisSegment > 255)) {
                alert("<spring:message code="comSymSymSrv.serverEqpmnRegist.validate.ip.formatMismatch"/>"); //IP 형식이 일치 하지않습니다.
                result = false;
            }
        }
    }

    return result;
}

function isEmail(str) {

	var isEmailValue = false;
    var supported = 0;

    if (window.RegExp) {
        var tempStr = "a";
        var tempReg = new RegExp(tempStr);
        if (tempReg.test(tempStr)) supported = 1;
    }

    if (!supported)
        return (str.indexOf(".") > 2) && (str.indexOf("@") > 0);

    var r1 = new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)");
    var r2 = new RegExp("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");

    isEmailValue = (!r1.test(str) && r2.test(str));
    if(!isEmailValue)
        alert(str+" : <spring:message code="comSymSymSrv.serverEqpmnRegist.validate.email.formatMismatch"/>"); //유효하지 않은 이메일 주소입니다.

    return isEmailValue;
}

-->
</script>
</head>

<body onload="initCalendar()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymSymSrv.serverEqpmnRegist.pageTop.title"/></h2><!-- 서버H/W 등록 -->

    <form name="serverEqpmn" id="serverEqpmn" method="post" action="${pageContext.request.contextPath}/sym/sym/srv/addServerEqpmn.do">

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.serverEqpmnNm"/> <span class="pilsu">*</span></th><!-- 서버H/W 명 -->
			<td class="left">
			    <label for="serverEqpmnNm"><input name="serverEqpmnNm" id="serverEqpmnNm" type="text" maxLength="30" style="width:200px" />&nbsp;<form:errors path="serverEqpmnNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.serverEqpmnIp"/> <span class="pilsu">*</span></th><!-- 서버H/W IP -->
			<td class="left">
			    <label for="serverEqpmnIp"><input name="serverEqpmnIp" id="serverEqpmnIp" type="text" maxLength="23" style="width:200px" />&nbsp;<form:errors path="serverEqpmnIp" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.serverEqpmnMngrNm"/> <span class="pilsu">*</span></th><!-- 관리자 -->
			<td class="left">
			    <label for="serverEqpmnMngrNm"><input name="serverEqpmnMngrNm" id="serverEqpmnMngrNm" type="text" maxLength="30"  style="width:200px" />&nbsp;<form:errors path="serverEqpmnMngrNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.mngrEmailAddr"/> <span class="pilsu">*</span></th><!-- 관리자이메일주소 -->
			<td class="left">
			    <label for="mngrEmailAddr"><input name="mngrEmailAddr" id="mngrEmailAddr" type="text" maxLength="50" size="50" >&nbsp;<form:errors path="mngrEmailAddr" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.opersysmInfo"/> <span class="pilsu">*</span></th><!-- OS정보 -->
			<td class="left">
			    <label for="opersysmInfo"><input name="opersysmInfo" id="opersysmInfo" type="text" maxLength="1000" size="80" >&nbsp;<form:errors path="opersysmInfo" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.cpuInfo"/> <span class="pilsu">*</span></th><!-- CPU정보 -->
			<td class="left">
			    <label for="cpuInfo"><input name="cpuInfo" id="cpuInfo" type="text" maxLength="1000" size="80" >&nbsp;<form:errors path="cpuInfo" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.moryInfo"/> <span class="pilsu">*</span></th><!-- 메모리정보 -->
			<td class="left">
			    <label for="moryInfo"><input name="moryInfo" id="moryInfo" type="text" maxLength="1000" size="80" >&nbsp;<form:errors path="moryInfo" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.hdDisk"/> <span class="pilsu">*</span></th><!-- 하드디스크 -->
			<td class="left">
			    <label for="hdDisk"><input name="hdDisk" id="hdDisk" type="text" maxLength="1000" size="80" >&nbsp;<form:errors path="hdDisk" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.etcInfo"/></th><!-- 기타정보 -->
			<td class="left">
			    <input name="etcInfo" id="etcInfo" type="text" maxLength="1000" size="80" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverEqpmnRegist.regstYmd"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
			<td class="left">
			    <label for="regstYmd">
		        	<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
		            <!--  <input type="text" name="regstYmd" id="regstYmd" size="10" maxlength="10" title="등록일자" class="readOnlyClass" readonly onClick="javascript:fn_egov_NormalCalendar(document.serverEqpmn, document.serverEqpmn.registerDate, document.serverEqpmn.regstYmd);" onKeyDown="javascript:if (event.keyCode == 13) { fn_egov_NormalCalendar(document.serverEqpmn, document.serverEqpmn.registerDate, document.serverEqpmn.regstYmd); }" >-->
		            <input type="text" name="regstYmd" id="regstYmd" maxlength="10" readonly="readonly" title="<spring:message code="comSymSymSrv.serverEqpmnRegist.regstYmd"/>" style="width:70px" />
		            <input type="hidden" name="registerDate" value=""/>
		            &nbsp;<form:errors path="regstYmd" />
		        </label>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncServerEqpmnInsert(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/selectServerEqpmnList.do'/>?pageIndex=<c:out value='${serverEqpmnVO.pageIndex}'/>&amp;strServerEqpmnNm=<c:out value="${serverEqpmnVO.strServerEqpmnNm}"/>" onclick="fncSelectServerEqpmnList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>

    <!-- 검색조건 유지 -->
    <input type="hidden" name="strServerEqpmnNm" value="<c:out value='${serverEqpmnVO.strServerEqpmnNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${serverEqpmnVO.pageIndex}'/>" >
	</form>

</div>

</body>
</html>

