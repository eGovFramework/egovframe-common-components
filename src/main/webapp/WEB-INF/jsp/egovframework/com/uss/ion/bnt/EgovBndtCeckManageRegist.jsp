<%
/**
 * @Class Name : EgovBndtCeckManageRegist.java
 * @Description : EgovBndtCeckManageRegist jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검
 * @ 2018.09.27    최 두 영              다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
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
<title><spring:message code="comUssIonBnt.bndtCeckManageRegist.title"/></title><!-- 당직체크  등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bndtCeckManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtCeckManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>";
}

/* ********************************************************
* 초기화 으로 가기
******************************************************** */
function fncBndtCeckManageClear() {
	var varFrom = document.getElementById("bndtCeckManage");
	varFrom.bndtCeckSe[0].selected = true;
	varFrom.bndtCeckCd.value       = "";
	varFrom.bndtCeckCdNm.value     = "";
	varFrom.useAt.value            = "Y";
 
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fncInsertBndtCeckManage() {
	    var varFrom = document.getElementById("bndtCeckManage");
	    varFrom.action = "<c:url value='/uss/ion/bnt/insertBndtCeckManage.do'/>";

	    if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
	        if(!validateBndtCeckManage(varFrom)){           
	            return;
	        }else{
	            varFrom.submit();
	        } 
	    }
	}

<c:if test="${!empty dplctMessage}">alert("${dplctMessage}");</c:if>
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="bndtCeckManage" name="bndtCeckManage" method="post" >
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonBnt.bndtCeckManageRegist.title"/></h2><!-- 당직체크  등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtCeckTemp1"/> <span class="pilsu">*</span></th><!-- 당직체크구분 -->
			<td class="left">
				<form:select path="bndtCeckSe" title="<spring:message code='comUssIonBnt.common.bndtCeckTemp1'/>"><!-- 당직체크구분 -->
			      <form:options items="${bndtCeckSeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtCeckCd"/> <span class="pilsu">*</span></th><!--당직체크코드 -->
			<td class="left">
			    <form:input  path="bndtCeckCd" size="15" maxlength="10" title="<spring:message code='comUssIonBnt.common.bndtCeckCd'/>" cssStyle="width:250px"/><!-- 당직체크코드 -->
     			<form:errors path="bndtCeckCd"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.bndtCeckCdName"/> <span class="pilsu">*</span></th><!-- 당직체크코드명 -->
			<td class="left">
			    <form:input  path="bndtCeckCdNm" size="60" maxlength="60" title="<spring:message code='comUssIonBnt.common.bndtCeckCdName'/>" cssStyle="width:250px"/><!-- 당직체크코드명 -->
      			<form:errors path="bndtCeckCdNm"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonBnt.common.useAt"/> <span class="pilsu">*</span></th><!-- 사용여부 -->
			<td class="left">
				<c:set var="useAtY"><spring:message code="comUssIonBnt.common.useAt.y"/></c:set>
				<c:set var="useAtN"><spring:message code='comUssIonBnt.common.useAt.n'/></c:set>
			    <form:select path="useAt" title="<spring:message code='comUssIonBnt.common.useAt'/> "><!-- 사용여부 -->
					<form:option value="Y" label="${useAtY}" /><!-- 사용 -->
					<form:option value="N" label="${useAtN}" /><!-- 미사용 -->
				</form:select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.init"/>" onclick="fncBndtCeckManageClear(); return false;" /><!-- 초기화 -->
		<span class="btn_s"><a href="#LINK" onclick="fncInsertBndtCeckManage(); return false;"><spring:message code="button.save" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnt/EgovBndtCeckManageList.do'/>?searchCondition=1" onclick="fncBndtCeckManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>

</body>
</html>