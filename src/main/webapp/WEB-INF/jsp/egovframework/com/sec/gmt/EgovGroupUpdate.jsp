<%
/**
 * @Class Name  : EgovAuthorUpdate.java
 * @Description : EgovAuthorUpdate jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *   2016.06.13    장동한             표준프레임워크 v3.6 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 */
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSecGmt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title><!-- 그룹관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="groupManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
function fncSelectGroupList() {
    var varFrom = document.getElementById("groupManage");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    varFrom.submit();
}

function fncGroupUpdate(form) {
	if(confirm("<spring:message code="common.save.msg" />")){ //저장하시겠습니까?
        if(!validateGroupManage(form)){
            return false;
        }else{
        	form.submit();
        }
    }
}

function fncGroupDelete() {
    var varFrom = document.getElementById("frmIdDelete");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupDelete.do'/>";
    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
    	varFrom.submit();
    }else{
    	return false;
    }
}
</script>
</head>

<body>

<form:form commandName="groupManage" method="post" action="${pageContext.request.contextPath}/sec/gmt/EgovGroupUpdate.do" onSubmit="fncGroupUpdate(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2><!-- 그룹관리 등록 -->

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 그룹아이디 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupId" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
				${groupManage.groupId}
			</td>
		</tr>
		
		<!-- 그룹명 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupNm" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="groupNm" title="${title} ${inputTxt}" size="40" maxlength="50" />
				<div><form:errors path="groupNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 설명 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupDc" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <form:textarea path="groupDc" title="${title} ${inputTxt}" cols="300" rows="10" />   
				<div><form:errors path="groupDc" cssClass="error" /></div> 
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<!-- <span class="btn_s"><a href="#" onClick="fncGroupDelete(); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>  -->
	<div class="btn">
	<span class="btn_s"><a href="<c:url value='/sec/gmt/EgovGroupList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	<button class="btn_s2" onClick="fncGroupDelete();return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button><!-- 삭제 -->
	<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div><div style="clear:both;"></div>
	
</div>

<input type="hidden" name="groupId" value="<c:out value='${groupManage.groupId}'/>"/>
<input type="hidden" name="searchCondition" value="<c:out value='${groupManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${groupManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>
</form:form>

<form id="frmIdDelete" name="frmDelete" method="post">
<input type="hidden" name="groupId" value="<c:out value='${groupManage.groupId}'/>"/>
<input type="hidden" name="searchCondition" value="<c:out value='${groupManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${groupManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>
</form>

</body>
</html>

