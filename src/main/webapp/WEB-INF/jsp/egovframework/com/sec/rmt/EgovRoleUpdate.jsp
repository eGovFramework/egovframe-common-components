<%
/**
 * @Class Name  : EgovRoleUpdate.java
 * @Description : EgovRoleUpdate jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j       최초 생성
 *   2016.06.13    장동한          표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comCopSecRmt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title><!-- 롤관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectRoleList() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    varFrom.submit();
}

function fncRoleUpdate(form) {  
    if(confirm("<spring:message code="common.save.msg" />")){ //저장하시겠습니까?
        if(!validateRoleManage(form)){
            return false;
        }else{
        	form.submit();
        }
    }
}

function fncRoleDelete() {
    var varFrom = document.getElementById("frmIdDelete");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleDelete.do'/>";
    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
        varFrom.submit();
    }else{
    	return false;
    }
}

</script>
</head>

<body>

<form:form commandName="roleManage" method="post" action="${pageContext.request.contextPath}/sec/rmt/EgovRoleUpdate.do" onSubmit="fncRoleUpdate(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.update" /></h2><!-- 롤관리 수정 -->

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 롤아이디 -->
		<c:set var="title"><spring:message code="comCopSecRam.list.rollId" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				${roleManage.roleCode}
			</td>
		</tr>
		<!-- 롤 명 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollNm" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="roleNm" title="${title} ${inputTxt}" size="40" maxlength="50" />
				<div><form:errors path="roleNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 패턴 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollPtn" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="rolePtn" title="${title} ${inputTxt}" size="40" maxlength="200" />
				<div><form:errors path="rolePtn" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 설명 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollDc" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
			    <form:textarea path="roleDc" title="${title} ${inputTxt}" cols="300" rows="10" />   
				<div><form:errors path="roleDc" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 타입 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollType" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="roleTyp">
					<form:options items="${cmmCodeDetailList}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="roleTyp" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 Sort -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollSort" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="roleSort" title="${title} ${inputTxt}" size="40" maxlength="10" />
				<div><form:errors path="roleSort" cssClass="error" /></div> 
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sec/rmt/EgovRoleList.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<button class="btn_s2" onClick="fncRoleDelete();return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button>
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="button.save" />" />
	</div><div style="clear:both;"></div>
	
</div>

<input type="hidden" name="roleCode" value="<c:out value='${roleManage.roleCode}'/>"/>
<input type="hidden" name="searchCondition" value="<c:out value='${roleManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${roleManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
</form:form>

<form id="frmIdDelete" name="frmDelete" method="post">
<input type="hidden" name="roleCode" value="<c:out value='${roleManage.roleCode}'/>"/>
<input type="hidden" name="searchCondition" value="<c:out value='${roleManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${roleManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
</form>

</body>
</html>

