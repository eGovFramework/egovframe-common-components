<%-- 
/**
 * @Class Name  : EgovProxySvcRegist.java
 * @Description : EgovProxySvcRegist jsp
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
<title>${pageTitle} <spring:message code="title.create" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="proxySvc" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectProxySvcList() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>";
    varFrom.submit();       
}

function fncProxySvcInsert() {
    var varFrom = document.getElementById("proxySvc");
    varFrom.action = "<c:url value='/utl/sys/pxy/addProxySvc.do'/>";

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

function ipValidate(ipValue) {
    
    var varFrom = document.getElementById("serverEqpmn");
    var IPvalue = ipValue;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + "<spring:message code="comUtlSysPxy.proxySvcRegist.ipValueException"/>");/* 는 예외 아이피 입니다.. */
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + "<spring:message code="comUtlSysPxy.proxySvcRegist.ipValueException"/>");/* 는 예외 아이피 입니다.. */
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("<spring:message code="comUtlSysPxy.proxySvcRegist.ipFormalException"/> ");/* IP 형식이 일치 하지않습니다. */
        result = false;
    } else {
        for (var i=1; i<5; i++) {
            
            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("<spring:message code="comUtlSysPxy.proxySvcRegist.ipFormalException"/> ");/* IP 형식이 일치 하지않습니다. */
                result = false;
            }
            
            if ((i == 0) && (thisSegment > 255)) {
                alert("<spring:message code="comUtlSysPxy.proxySvcRegist.ipFormalException"/> ");/* IP 형식이 일치 하지않습니다. */
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
	<h2>${pageTitle} <spring:message code="title.create" /></h2>
	<form:form commandName="proxySvc" method="post" action="${pageContext.request.contextPath}/utl/sys/pxy/addProxySvc.do">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="proxyNm"><input name="proxyNm" id="proxyNm" type="text" maxLength="60" size="60" >&nbsp;<form:errors path="proxyNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="proxyIp"><input name="proxyIp" id="proxyIp" type="text" maxLength="23" style="width:128px" />&nbsp;<form:errors path="proxyIp" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.proxyPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="proxyPort"><input name="proxyPort" id="proxyPort" type="text" maxLength="10" style="width:50px" />&nbsp;<form:errors path="proxyPort" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.trgetSvcNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="trgetSvcNm"><input name="trgetSvcNm" id="trgetSvcNm" type="text" maxLength="30" size="30" >&nbsp;<form:errors path="trgetSvcNm" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcDc.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="svcDc"><input name="svcDc" id="svcDc" type="text" maxLength="255" size="60" ></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcIp.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="svcDc"><input name="svcIp" id="svcIp" type="text" maxLength="23" style="width:128px" />&nbsp;<form:errors path="svcIp" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcPort.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <label for="svcDc"><input name="svcPort" id="svcPort" type="text" maxLength="10" style="width:50px" />&nbsp;<form:errors path="svcPort" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysPxy.proxySvc.svcSttusNm.label" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="svcSttus">
				<option value="01"><spring:message code="comUtlSysPxy.proxySvcRegist.svcSttus.run"/></option><!-- 정상 -->
				<option value="03"><spring:message code="comUtlSysPxy.proxySvcRegist.svcSttus.stop"/></option><!-- 중지 -->
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncProxySvcInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/pxy/selectProxySvcList.do'/>?pageIndex=<c:out value='${proxySvcVO.pageIndex}'/>&amp;strProxyNm=<c:out value="${proxySvcVO.strProxyNm}"/>" onclick="fncSelectProxySvcList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	
	<input type="hidden" name="strProxyNm" value="<c:out value='${proxySvcVO.strProxyNm}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${proxySvcVO.pageIndex}'/>" >
	</form:form>
</div>
</body>
</html>