<%-- 
/**
 * @Class Name  : EgovSynchrnServerRegist.java
 * @Description : EgovSynchrnServerRegist jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    lee.m.j     최초 생성
 * @ 2018.11.08        이정은		      공통컴포넌트 3.8 개선
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
<title><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.create" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="synchrnServer" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectSynchrnServerList() {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.action = "<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>";
    varFrom.submit();       
}

function fncSynchrnServerInsert() {
    var varFrom = document.getElementById("synchrnServer");
    varFrom.action = "<c:url value='/utl/sys/ssy/addSynchrnServer.do'/>";

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

function ipValidate(ipValue) {
    
    var varFrom = document.getElementById("serverEqpmn");
    var IPvalue = ipValue;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + "<spring:message code="comUtlSysSsy.synchrnServer.validate.exceptIP"/>");/* 는 예외 아이피 입니다. */
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + "<spring:message code="comUtlSysSsy.synchrnServer.validate.exceptIP"/>");/* 는 예외 아이피 입니다. */
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.misMatchIP"/> ");/* IP 형식이 일치 하지않습니다. */
        result = false;
    } else {
        for (var i=1; i<5; i++) {
            
            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.misMatchIP"/> ");/* IP 형식이 일치 하지않습니다. */
                result = false;
            }
            
            if ((i == 0) && (thisSegment > 255)) {
                alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.misMatchIP"/> ");/* IP 형식이 일치 하지않습니다. */
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
<form:form modelAttribute="synchrnServer" method="post" action="${pageContext.request.contextPath}/utl/sys/ssy/addSynchrnServer.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="serverNm"><input name="serverNm" id="serverNm" type="text" maxLength="60" size="60" >&nbsp;<form:errors path="serverNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="serverIp"><input name="serverIp" id="serverIp" type="text" maxLength="23" style="width:128px" />&nbsp;<form:errors path="serverIp" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.serverPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="serverPort"><input name="serverPort" id="serverPort" type="text" maxLength="10" style="width:50px" >&nbsp;<form:errors path="serverPort" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.ftpId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="ftpId"><input name="ftpId" id="ftpId" type="text" maxLength="20" style="width:128px" />&nbsp;<form:errors path="ftpId" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.ftpPassword.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="ftpPassword"><input name="ftpPassword" id="ftpPassword" type="text" maxLength="20" style="width:128px" />&nbsp;<form:errors path="ftpPassword" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.synchrnLc.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="synchrnLc"><input name="synchrnLc" id="synchrnLc" type="text" maxLength="255" size="60" >&nbsp;<form:errors path="synchrnLc" /></label>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncSynchrnServerInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>?pageIndex=<c:out value='${synchrnServerVO.pageIndex}'/>&amp;strServerNm=<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>" onclick="fncSelectSynchrnServerList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

    <!-- 검색조건 유지 -->
    <input type="hidden" name="strSynchrnServerNm" value="<c:out value='${synchrnServerVO.strSynchrnServerNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${synchrnServerVO.pageIndex}'/>" >
</form:form>

</body>
</html>

