<%--
/**
 * @Class Name  : EgovServerUpdt.java
 * @Description : EgovServerUpdt jsp
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
<title><spring:message code="comSymSymSrv.serverUpdt.title"/></title><!-- 서버정보 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="server" staticJavascript="false" xhtml="true" cdata="false"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js'/>"></script>
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

function fncSelectServerList() {
    var varFrom = document.getElementById("server");
    varFrom.action = "<c:url value='/sym/sym/srv/selectServerList.do'/>";
    varFrom.submit();
}

function fncServerUpdate() {
    var varFrom = document.getElementById("server");
    varFrom.action = "<c:url value='/sym/sym/srv/updtServer.do'/>";

    if(confirm("<spring:message code="comSymSymSrv.serverUpdt.validate.save"/>")){ //저장 하시겠습니까?
        if(!validateServer(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncServerDelete() {
    var varFrom = document.getElementById("server");
    varFrom.action = "<c:url value='/sym/sym/srv/removeServer.do'/>";
    if(confirm("<spring:message code="comSymSymSrv.serverUpdt.validate.delete"/>")){ //삭제 하시겠습니까?
        varFrom.submit();
    }
}
-->
</script>
</head>

<body onload="initCalendar()">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymSymSrv.serverUpdt.pageTop.title"/></h2><!-- 서버S/W 수정 -->

	<form name="server" id="server" method="post" action="${pageContext.request.contextPath}/sym/sym/srv/updtServer.do">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymSymSrv.serverUpdt.serverId"/> <span class="pilsu">*</span></th><!-- 서버S/W ID -->
			<td class="left">
			    <label for="serverId"><input name="serverId" id="serverId" type="text" value="<c:out value='${server.serverId}'/>" readonly="readonly" style="width:200px" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverUpdt.serverNm"/> <span class="pilsu">*</span></th><!-- 서버S/W 명 -->
			<td class="left">
			    <label for="serverNm"><input name="serverNm" id="serverNm" type="text" value="<c:out value='${server.serverNm}'/>" maxLength="30" style="width:200px" />&nbsp;<form:errors path="serverNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverUpdt.serverKndNm"/> <span class="pilsu">*</span></th><!-- 서버S/W 종류 -->
			<td class="left">
			    <label for="regstYmd">
		          <select name="serverKnd">
		            <c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
		              <option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.code == server.serverKnd}">selected</c:if> ><c:out value="${cmmCodeDetail.codeNm}"/></option>
		            </c:forEach>
		          </select>
		        </label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymSymSrv.serverUpdt.regstYmd"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
			<td class="left">
			    <label for="regstYmd">
		            <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
		            <!-- <input type="text" name="regstYmd" id="regstYmd" value="<c:out value="${server.regstYmd}"/>" size="10" maxlength="10" title="등록일자" class="readOnlyClass" readonly onClick="javascript:fn_egov_NormalCalendar(document.server, document.server.registerDate, document.server.regstYmd);" onKeyDown="javascript:if (event.keyCode == 13) { fn_egov_NormalCalendar(document.server, document.server.registerDate, document.server.regstYmd); }" > -->
		            <input type="text" name="regstYmd" id="regstYmd" value="<c:out value="${server.regstYmd}"/>" maxlength="10" title="<spring:message code="comSymSymSrv.serverUpdt.regstYmd"/>" style="width:70px" /><!-- 등록일자 -->
		            <input type="hidden" name="registerDate" value=""/>
		            &nbsp;<form:errors path="regstYmd" />
		        </label>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncServerUpdate(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/sym/srv/selectServerList.do'/>?pageIndex=<c:out value='${serverVO.pageIndex}'/>&amp;strServerNm=<c:out value="${serverVO.strServerNm}"/>" onclick="fncSelectServerList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	
	    <!-- 검색조건 유지 -->
    <input type="hidden" name="strServerNm" value="<c:out value='${serverVO.strServerNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${serverVO.pageIndex}'/>" >
	</form>
</div>

</body>
</html>

