<%--
/**
 * @Class Name  : EgovNtwrkDetail.java
 * @Description : EgovNtwrkDetail jsp
 * @Modification Information
 * @
 * @ 수정일                수정자             수정내용
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
<title><spring:message code="comSymSymNwk.ntwrkUpdt.title"/></title><!-- 네트워크 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ntwrk" staticJavascript="false" xhtml="true" cdata="false"/>
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

function fncSelectNtwrkList() {
    var varFrom = document.getElementById("ntwrk");
    varFrom.action = "<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>";
    varFrom.submit();
}

function fncNtwrkUpdate() {
    var varFrom = document.getElementById("ntwrk");
    varFrom.action = "<c:url value='/sym/sym/nwk/updtNtwrk.do'/>";

    if(confirm("<spring:message code="comSymSymNwk.ntwrkUpdt.validate.save"/>")){ //저장 하시겠습니까?
        if(!validateNtwrk(varFrom)){
            return;
        }else{
            if(!ipValidate(varFrom.ntwrkIp.value, ''))
                return;
            else if(!ipValidate(varFrom.gtwy.value, '<spring:message code="comSymSymNwk.ntwrkUpdt.gtwy"/>')) //게이트웨이
                return;
            else if(!ipValidate(varFrom.subnet.value, '<spring:message code="comSymSymNwk.ntwrkUpdt.subnet"/>')) //서브넷
                return;
            else if(!ipValidate(varFrom.domnServer.value, '<spring:message code="comSymSymNwk.ntwrkUpdt.domnServer"/>')) //DNS
                return;
            else
                varFrom.submit();
        }
    }
}

function fncNtwrkDelete() {
    var varFrom = document.getElementById("ntwrk");
    varFrom.action = "<c:url value='/sym/sym/nwk/removeNtwrk.do'/>";
    if(confirm("<spring:message code="comSymSymNwk.ntwrkUpdt.validate.delete"/>")){ //삭제 하시겠습니까?
        varFrom.submit();
    }
}

function ipValidate(ipValue, ipName) {

    var varFrom = document.getElementById("ntwrk");
    // var IPvalue = varFrom.gtwy.value;
    var IPvalue = ipValue;
    var IPName = ipName;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = true;
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + " : <spring:message code="comSymSymNwk.ntwrkUpdt.validate.ip.notAllowed"/>"); // 예외 아이피 입니다.
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + " : <spring:message code="comSymSymNwk.ntwrkUpdt.validate.ip.notAllowed"/>"); //예외 아이피 입니다.
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert(IPName+" : <spring:message code="comSymSymNwk.ntwrkUpdt.validate.ip.formatMismatch"/>"); //IP 형식이 일치 하지않습니다.
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert(IPName+" : <spring:message code="comSymSymNwk.ntwrkUpdt.validate.ip.formatMismatch"/>"); //IP 형식이 일치 하지않습니다.
                result = false;
            }

            if ((i == 0) && (thisSegment > 255)) {
                alert(IPName+" : <spring:message code="comSymSymNwk.ntwrkUpdt.validate.ip.formatMismatch"/>"); //IP 형식이 일치 하지않습니다.
                result = false;
            }
        }
    }

    return result;
}

-->
</script>
</head>

<body onload="initCalendar()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="ntwrk" id="ntwrk" method="post" action="${pageContext.request.contextPath}/sym/sym/nwk/updtNtwrk.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymSymNwk.ntwrkUpdt.pageTop.title"/></h2><!-- 네트워크 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.ntwrkId"/> <span class="pilsu">*</span></th><!-- 네트워크ID -->
			<td class="left">
			    <label for="ntwrkId"><input name="ntwrkId" id="ntwrkId" type="text" value="<c:out value='${ntwrk.ntwrkId}'/>" class="readOnlyClass" readonly="readonly" style="width:200px"/></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.ntwrkIp"/> <span class="pilsu">*</span></th><!-- IP -->
			<td class="left">
			    <label for="ntwrkIp"><input name="ntwrkIp" id="ntwrkIp" type="text" value="<c:out value='${ntwrk.ntwrkIp}'/>" maxLength="23" style="width:200px" />&nbsp;<form:errors path="ntwrkIp" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.gtwy"/> <span class="pilsu">*</span></th><!-- 게이트웨이 -->
			<td class="left">
			    <label for="gtwy"><input name="gtwy" id="gtwy" type="text" value="<c:out value='${ntwrk.gtwy}'/>" maxLength="23" style="width:200px" />&nbsp;<form:errors path="gtwy" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.subnet"/> <span class="pilsu">*</span></th><!-- 서브넷 -->
			<td class="left">
			    <label for="subnet"><input name="subnet" id="subnet" type="text" value="<c:out value='${ntwrk.subnet}'/>" maxLength="23" style="width:200px" />&nbsp;<form:errors path="subnet" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.domnServer"/> <span class="pilsu">*</span></th><!-- DNS -->
			<td class="left">
			    <label for="domnServer"><input name="domnServer" id="domnServer" type="text" value="<c:out value='${ntwrk.domnServer}'/>" maxLength="23" style="width:200px" />&nbsp;<form:errors path="domnServer" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.manageIem"/> <span class="pilsu">*</span></th><!-- 관리항목 -->
			<td class="left">
			    <label for="manageIem">
		          <select name="manageIem">
		            <c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
		              <option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.codeNm == ntwrk.manageIem}">selected</c:if> ><c:out value="${cmmCodeDetail.codeNm}"/></option>
		            </c:forEach>
		          </select>
		        </label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.userNm"/> <span class="pilsu">*</span></th><!-- 사용자명 -->
			<td class="left">
			    <label for="userNm"><input name="userNm" id="userNm" type="text" value="<c:out value='${ntwrk.userNm}'/>" maxLength="30" style="width:128px" />&nbsp;<form:errors path="userNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
			    <label for="useAt">
		          <select name="useAt">
		              <option value="Y" <c:if test="${ntwrk.useAt == 'Y'}">selected</c:if> >Y</option>
		              <option value="N" <c:if test="${ntwrk.useAt == 'N'}">selected</c:if> >N</option>
		          </select>
		       </label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymNwk.ntwrkUpdt.regstYmd"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
			<td class="left">
			    <label for="regstYmd">
		        	<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
		            <!--  <input type="text" name="regstYmd" id="regstYmd" value="<c:out value="${ntwrk.regstYmd}"/>" size="10" maxlength="10" title="등록일자" class="readOnlyClass" readonly onClick="javascript:fn_egov_NormalCalendar(document.ntwrk, document.ntwrk.registerDate, document.ntwrk.regstYmd);" onKeyDown="javascript:if (event.keyCode == 13) { fn_egov_NormalCalendar(document.ntwrk, document.ntwrk.registerDate, document.ntwrk.regstYmd); }" > -->
		            <input type="text" name="regstYmd" id="regstYmd" value="<c:out value="${ntwrk.regstYmd}"/>" maxlength="10" title="<spring:message code="comSymSymNwk.ntwrkUpdt.regstYmd"/>" style="width:70px"/>
		            <input type="hidden" name="registerDate" value=""/>
		            &nbsp;<form:errors path="regstYmd" />
		        </label>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncNtwrkUpdate(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/nwk/selectNtwrkList.do'/>?pageIndex=<c:out value='${ntwrkVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${ntwrkVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectNtwrkList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>


<!-- 검색조건 유지 -->
    <input type="hidden" name="strManageIem" value="<c:out value='${ntwrkVO.strManageIem}'/>" />
    <input type="hidden" name="strUserNm" value="<c:out value='${ntwrkVO.strUserNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${ntwrkVO.pageIndex}'/>" >
</form>

</body>
</html>

