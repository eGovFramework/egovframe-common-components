<%--
/**
 * @Class Name  : EgovLoginPolicyRegist.java
 * @Description : EgovLoginPolicyRegist jsp
 * @Modification Information
 * @
 * @  수정일             수정자            수정내용
 * @ -------      --------   ---------------------------
 * @ 2009.02.01   lee.m.j    최초 생성
 * @ 2018.09.03   신용호            공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<title><spring:message code="comUatUap.loginPolicyRegist.title"/></title><!-- 로그인정책 등록 -->
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="loginPolicy" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectLoginPolicyList() {
    var varFrom = document.getElementById("loginPolicy");
    varFrom.action = "<c:url value='/uat/uap/selectLoginPolicyList.do'/>";
    varFrom.submit();
}

function fncLoginPolicyInsert() {

    var varFrom = document.getElementById("loginPolicy");
    varFrom.action = "<c:url value='/uat/uap/addLoginPolicy.do'/>";

    if(confirm("<spring:message code="comUatUap.loginPolicyRegist.validate.confirm.save"/>")){ //저장 하시겠습니까?
        if(!validateLoginPolicy(varFrom)){
            return;
        }else{
            if(ipValidate())
                varFrom.submit();
            else
                return;
        }
    }
}

function ipValidate() {

    var varFrom = document.getElementById("loginPolicy");
    var IPvalue = varFrom.ipInfo.value;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + " : <spring:message code="comUatUap.loginPolicyRegist.validate.info.exceptionIP"/>"); //예외 아이피 입니다.
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + " : <spring:message code="comUatUap.loginPolicyRegist.validate.info.exceptionIP"/>"); //예외 아이피 입니다.
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("<spring:message code="comUatUap.loginPolicyRegist.validate.info.invalidForm"/>"); //형식이 일치 하지않습니다.
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment > 255) {
                alert("<spring:message code="comUatUap.loginPolicyRegist.validate.info.invalidForm"/>"); //형식이 일치 하지않습니다.
                result = false;
            }

            if ((i == 0) && (thisSegment > 255)) {
                alert("<spring:message code="comUatUap.loginPolicyRegist.validate.info.invalidForm"/>"); //형식이 일치 하지않습니다.
                result = false;
            }
        }
    }

    return result;
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUatUap.loginPolicyRegist.pageTop.title"/></h2><!-- 로그인정책 등록 -->
	<form:form commandName="loginPolicy" method="post" action="${pageContext.request.contextPath}/uat/uap/addLoginPolicy.do' />">

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUatUap.loginPolicyRegist.emplyrId"/> <span class="pilsu">*</span></th><!-- 사용자ID -->
			<td class="left">
			    <input id="emplyrId" name="emplyrId" type="text" value="<c:out value='${loginPolicy.emplyrId}'/>" title="<spring:message code="comUatUap.loginPolicyRegist.emplyrId"/>" size="30" maxlength="30" readonly="readonly" /><!-- 사용자ID -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUatUap.loginPolicyRegist.emplyrNm"/> <span class="pilsu">*</span></th><!-- 사용자명 -->
			<td class="left">
			    <input id="emplyrNm" type="text" name="emplyrNm" value="<c:out value='${loginPolicy.emplyrNm}'/>" title="<spring:message code="comUatUap.loginPolicyRegist.emplyrNm"/>" size="30" maxlength="50" readonly="readonly" /><!-- 사용자명 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUatUap.loginPolicyRegist.ipInfo"/> <span class="pilsu">*</span></th><!-- IP정보 -->
			<td class="left">
			    <input id="ipInfo" type="text" name="ipInfo" title="<spring:message code="comUatUap.loginPolicyRegist.ipInfo"/>" size="30" maxLength="23" />&nbsp;<form:errors path="ipInfo" /><!-- IP정보 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUatUap.loginPolicyRegist.lmttAt"/> <span class="pilsu">*</span></th><!-- IP제한여부 -->
			<td class="left">
			   <select name="lmttAt" id="lmttAt" title="<spring:message code="comUatUap.loginPolicyRegist.lmttAt"/>"><!-- IP제한여부 -->
		          <option value="Y">Y</option>
		          <option value="N">N</option>
		      </select>&nbsp;<form:errors path="lmttAt" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fncLoginPolicyInsert(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/uat/uap/selectLoginPolicyList.do'/>?pageIndex=<c:out value='${loginPolicyVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${loginPolicyVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectLoginPolicyList(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
	<input type="hidden" name="dplctPermAt" value="Y" >
	<input type="hidden" name="searchCondition" value="<c:out value='${loginPolicyVO.searchCondition}'/>" >
	<input type="hidden" name="searchKeyword" value="<c:out value='${loginPolicyVO.searchKeyword}'/>" >
	<input type="hidden" name="pageIndex" value="<c:out value='${loginPolicyVO.pageIndex}'/>" >
	</form:form>
</div>

</body>
</html>

