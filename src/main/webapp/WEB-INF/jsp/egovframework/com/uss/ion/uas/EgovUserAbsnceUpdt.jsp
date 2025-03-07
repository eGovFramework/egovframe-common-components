<%--
/**
 * @Class Name  : EgovMainImageUpdt.java
 * @Description : EgovMainImageUpdt jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 * @ 2018.09.14        이정은          	        공통컴포넌트 3.8 개선
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
<title><spring:message code="ussIonUas.userAbsnceUpdt.userAbsnceUpdt"/></title><!-- 사용자부재 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mainImage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록
 ******************************************************** */
function fncSelectUserAbsnceList() {
    var varFrom = document.getElementById("userAbsnce");
    varFrom.action = "<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>";
    varFrom.submit();       
}
/* ********************************************************
 * 수정
 ******************************************************** */
function fncUserAbsnceUpdate() {


    var varFrom = document.getElementById("userAbsnce");
    varFrom.action = "<c:url value='/uss/ion/uas/updtUserAbsnce.do'/>";

    if(confirm("<spring:message code="ussIonUas.userAbsnceUpdt.saveMsg"/>")){/* 저장 하시겠습니까? */
        varFrom.submit();
    }
}
/* ********************************************************
 * 삭제
 ******************************************************** */
function fncUserAbsnceDelete() {
    var varFrom = document.getElementById("userAbsnce");
    varFrom.action = "<c:url value='/uss/ion/uas/removeUserAbsnce.do'/>";
    if(confirm("<spring:message code="ussIonUas.userAbsnceUpdt.deleteMsg"/>")){/* 삭제 하시겠습니까? */
        varFrom.submit();
    }
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
<form:form modelAttribute="userAbsnce" action="${pageContext.request.contextPath}/uss/ion/uas/updtUserAbsnce.do" method="post">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonUas.userAbsnceUpdt.userAbsnceUpdt"/></h2><!-- 사용자부재 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonUas.userAbsnceUpdt.userId"/> <span class="pilsu">*</span></th><!-- 사용자ID -->
			<td class="left">
			    <input name="userId" id="userId" title="<spring:message code="ussIonUas.userAbsnceUpdt.userId"/>" type="text" value="<c:out value='${userAbsnce.userId}'/>" class="readOnlyClass" readonly="readonly" style="width:128px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonUas.userAbsnceUpdt.userNm"/> <span class="pilsu">*</span></th><!-- 사용자명 -->
			<td class="left">
			    <input name="userNm" id="userNm" title="<spring:message code="ussIonUas.userAbsnceUpdt.userNm"/>" type="text" value="<c:out value='${userAbsnce.userNm}'/>" maxLength="50" class="readOnlyClass" readonly="readonly" style="width:128px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonUas.userAbsnceUpdt.userAbsnceAt"/> <span class="pilsu">*</span></th><!-- 부재여부 -->
			<td class="left">
			    <select name="userAbsnceAt" id="userAbsnceAt" title="<spring:message code="ussIonUas.userAbsnceUpdt.userAbsnceAt"/>">
					<option value="Y" <c:if test="${userAbsnce.userAbsnceAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${userAbsnce.userAbsnceAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonUas.userAbsnceUpdt.lastUpdusrPnttm"/> <span class="pilsu">*</span></th><!-- 등록일시 -->
			<td class="left">
			    <input name="lastUpdusrPnttm" id="lastUpdusrPnttm" title="<spring:message code="ussIonUas.userAbsnceUpdt.lastUpdusrPnttm"/>" type="text" value="<c:out value="${fn:substring(userAbsnce.lastUpdusrPnttm, 0, 19)}"/>" maxLength="50" class="readOnlyClass" readonly="readonly" style="width:250px"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUserAbsnceUpdate(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/uas/removeUserAbsnce.do'/>?userId=<c:out value='${userAbsnceVO.userId}'/>&amp;selAbsnceAt=<c:out value='${userAbsnceVO.selAbsnceAt}'/>" onclick="fncUserAbsnceDelete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>?pageIndex=<c:out value='${userAbsnceVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${userAbsnceVO.searchKeyword}"/>&amp;searchCondition=1&amp;selAbsnceAt=<c:out value="${userAbsnceVO.selAbsnceAt}"/>" onclick="fncSelectUserAbsnceList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

  
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${userAbsnceVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${userAbsnceVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${userAbsnceVO.pageIndex}'/>">
<input type="hidden" name="selAbsnceAt" value="<c:out value='${userAbsnceVO.selAbsnceAt}'/>">
</form:form>

</body>
</html>

