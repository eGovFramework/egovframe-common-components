<%--
/**
 * @Class Name  : EgovProxySvcUpdt.java
 * @Description : EgovProxySvcUpdt jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.01    lee.m.j     최초 생성
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
<c:set var="pageTitle"><spring:message code="comUtlSysPxy.proxySvc.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<title>${pageTitle} <spring:message code="title.update" /></title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="proxySvc" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectProxySvcList() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>";
    varFrom.submit();       
}

function fncProxySvcUpdate() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/pxy/updtProxySvc.do'/>";

    if(confirm("<spring:message code='common.save.msg' />")){
        if(!validateProxySvc(varFrom)){           
            return;
        }else{
            if(!ipValidate(varFrom.proxyIp.value))
                return;
            else if(!ipValidate(varFrom.svcIp.value))
                return;
            else 
                varFrom.submit();
        } 
    }
}

function fncProxySvcDelete() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/pxy/removeProxySvc.do'/>";
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
        alert(IPvalue + "<spring:message code="comUtlSysPxy.proxySvcUpdt.ipValueException"/>");/* 는 예외 아이피 입니다. */
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(IPvalue + "<spring:message code="comUtlSysPxy.proxySvcUpdt.ipValueException"/>");/* 는 예외 아이피 입니다. */
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("<spring:message code="comUtlSysPxy.proxySvcUpdt.ipFormalException"/>");/* IP 형식이 일치 하지않습니다. */
        result = false;
    } else {
        for (var i=1; i<5; i++) {
            
            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("<spring:message code="comUtlSysPxy.proxySvcUpdt.ipFormalException"/>");/* IP 형식이 일치 하지않습니다. */
                result = false;
            }
            
            if ((i == 0) && (thisSegment > 255)) {
                alert("<spring:message code="comUtlSysPxy.proxySvcUpdt.ipFormalException"/>");/* IP 형식이 일치 하지않습니다. */
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

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUtlSysPxy.proxySvcUpdtl.title" /></h2><!-- 프록시설정 수정 -->

    <form:form commandName="proxySvc" method="post" action="${pageContext.request.contextPath}/utl/sys/pxy/updtProxySvc.do">

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyId.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="proxyId" id="proxyId" type="text" value="<c:out value='${proxySvc.proxyId}'/>" size="30" class="readOnlyClass" readOnly>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="proxyNm" id="proxyNm" type="text" value="<c:out value='${proxySvc.proxyNm}'/>" maxLength="60" size="60" >&nbsp;<form:errors path="proxyNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="proxyIp" id="proxyIp" type="text" value="<c:out value='${proxySvc.proxyIp}'/>" maxLength="23" style="width:50%">&nbsp;<form:errors path="proxyIp" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="proxyPort" id="proxyPort" type="text" value="<c:out value='${proxySvc.proxyPort}'/>" maxLength="10" style="width:60px">&nbsp;<form:errors path="proxyPort" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.trgetSvcNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="trgetSvcNm" id="trgetSvcNm" type="text" value="<c:out value='${proxySvc.trgetSvcNm}'/>" maxLength="30" size="30" >&nbsp;<form:errors path="trgetSvcNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcDc.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="svcDc" id="svcDc" type="text" value="<c:out value='${proxySvc.svcDc}'/>" maxLength="255" size="60" >
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="svcIp" id="svcIp" type="text" value="<c:out value='${proxySvc.svcIp}'/>" maxLength="23" style="width:50%">&nbsp;<form:errors path="svcIp" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="svcPort" id="svcPort" type="text" value="<c:out value='${proxySvc.svcPort}'/>" maxLength="10" style="width:60px">&nbsp;<form:errors path="svcPort" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcSttusNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="svcSttus">
					<option value="01" <c:if test="${proxySvc.svcSttus == '01'}">selected</c:if>><spring:message code="comUtlSysPxy.proxySvcUpdt.svcSttus.run"/></option><!-- 정상 -->
					<option value="03" <c:if test="${proxySvc.svcSttus == '03'}">selected</c:if>><spring:message code="comUtlSysPxy.proxySvcUpdt.svcSttus.stop"/></option><!-- 중지 -->
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncProxySvcUpdate()" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>?pageIndex=<c:out value='${proxySvcVO.pageIndex}'/>&amp;strProxyNm=<c:out value="${proxySvcVO.strProxyNm}"/>" onclick="fncSelectProxySvcList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	
    <input type="hidden" name="strPreSvcSttus" value="<c:out value='${proxySvc.svcSttus}'/>" />
    <!-- 검색조건 유지 -->
    <input type="hidden" name="strProxyNm" value="<c:out value='${proxySvcVO.strProxyNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${proxySvcVO.pageIndex}'/>" >
</form:form>
</div>
</body>
</html>