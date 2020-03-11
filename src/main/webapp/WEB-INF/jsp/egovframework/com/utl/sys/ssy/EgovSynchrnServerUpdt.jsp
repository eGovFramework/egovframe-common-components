<%--
/**
 * @Class Name  : EgovSynchrnServerUpdt.java
 * @Description : EgovSynchrnServerUpdt jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    lee.m.j     최초 생성
 * @ 2018.11.02	      이정은	              공통컴포넌트 3.8 개선
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
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<title><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.update" /></title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="synchrnServer" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectSynchrnServerList() {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.action = "<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>";
    varFrom.submit();       
}

function fncSynchrnServerUpdate() {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.action = "<c:url value='/utl/sys/ssy/updtSynchrnServer.do'/>";

    if(confirm("<spring:message code='common.save.msg' />")){
        if(!validateSynchrnServer(varFrom)){           
            return;
        }else{
            if(!ipValidate(varFrom.serverIp.value))
                return;
            else 
                varFrom.submit();
        } 
    }
}

function fncSynchrnServerDelete() {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.action = "<c:url value='/utl/sys/ssy/removeSynchrnServer.do'/>";
    if(confirm("<spring:message code='common.delete.msg' />")){
        varFrom.submit();
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
        alert(IPvalue + "는 예외 아이피 입니다.");/* 는 예외 아이피 입니다. */
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + "는 예외 아이피 입니다.");/* 는 예외 아이피 입니다. */
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("IP 형식이 일치 하지않습니다. ");/* IP 형식이 일치 하지않습니다. */
        result = false;
    } else {
        for (var i=1; i<5; i++) {
            
            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("IP 형식이 일치 하지않습니다. ");/* IP 형식이 일치 하지않습니다. */
                result = false;
            }
            
            if ((i == 0) && (thisSegment > 255)) {
                alert("IP 형식이 일치 하지않습니다. ");/* IP 형식이 일치 하지않습니다. */
                result = false;
            }
        }
    }

    return result;
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form commandName="synchrnServer" method="post" action="${pageContext.request.contextPath}/utl/sys/ssy/updtSynchrnServer.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.update" /></h2><!-- 동기화대상 서버정보 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="serverId" id="serverId" type="text" value="<c:out value='${synchrnServer.serverId}'/>" style="width:50%" readonly>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="serverNm" id="serverNm" type="text" value="<c:out value='${synchrnServer.serverNm}'/>" maxLength="30" style="width:50%">&nbsp;<form:errors path="serverNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="serverIp" id="serverIp" type="text" value="<c:out value='${synchrnServer.serverIp}'/>" maxLength="30" style="width:50%">&nbsp;<form:errors path="serverIp" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="serverPort" id="serverPort" type="text" value="<c:out value='${synchrnServer.serverPort}'/>" maxLength="30" style="width:70px">&nbsp;<form:errors path="serverPort" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.ftpId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="ftpId" id="ftpId" type="text" value="<c:out value='${synchrnServer.ftpId}'/>" maxLength="30" style="width:50%">&nbsp;<form:errors path="ftpId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.ftpPassword.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="ftpPassword" id="ftpPassword" type="text" value="<c:out value='${synchrnServer.ftpPassword}'/>" maxLength="30" style="width:50%">&nbsp;<form:errors path="ftpPassword" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.synchrnLc.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="synchrnLc" id="synchrnLc" type="text" value="<c:out value='${synchrnServer.synchrnLc}'/>" maxLength="30" style="width:50%">&nbsp;<form:errors path="synchrnLc" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncSynchrnServerUpdate()" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>?pageIndex=<c:out value='${synchrnServerVO.pageIndex}'/>&amp;strSynchrnServerNm=<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>" onclick="fncSelectSynchrnServerList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strServerNm" value="<c:out value='${synchrnServerVO.strSynchrnServerNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${synchrnServerVO.pageIndex}'/>" >
</form:form>

</body>
</html>

