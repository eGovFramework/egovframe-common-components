<%
 /**
  * @Class Name : EgovPasswordUpdt.jsp
  * @Description : 암호수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.02    조재영          최초 생성
  * @ 2016.07.26    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.04.02
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManagePasswordUpdt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
function fnListPage(){
    document.passwordChgVO.action = "<c:url value='/uss/umt/EgovUserManage.do'/>";
    document.passwordChgVO.submit();
}
function fnUpdate(form){
    if(validatePasswordChgVO(form)){
        if(form.newPassword.value != form.newPassword2.value){
            alert("<spring:message code="fail.user.passwordUpdate2" />");
            return false;
        }
        document.passwordChgVO.submit();
        return  true;
    }else{
    	return false;
    }
}
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>
</head>
<body>
<form name="passwordChgVO" method="post"  action="<c:url value="${'/uss/umt/EgovUserPasswordUpdt.do'}"/>" onsubmit="fnUpdate(document.forms[0]); return false;">
	<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
	<input name="checkedIdForDel" type="hidden" />
	<!-- 검색조건 유지 -->
	<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
	<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
	<!-- 우편번호검색 -->
	<input type="hidden" name="url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />

<div class="wTableFrm">
 	<!-- 타이틀 -->
	<h2><spring:message code="comUssUmt.deptUserManage.title" /> ${pageTitle}</h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 일반회원아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.id" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
				<input name="emplyrId" id="emplyrId" title="사용자아이디" type="text" size="20" value="<c:out value='${userManageVO.emplyrId}'/>"  maxlength="20" readonly/>
				<input name="uniqId" id="uniqId" title="uniqId" type="hidden" size="20" value="<c:out value='${userManageVO.uniqId}'/>"/>
				<input name="userTy" id="userTy" title="userTy" type="hidden" size="20" value="<c:out value='${userManageVO.userTy}'/>"/>
			</td>
		</tr>
		
		<!-- 기존 비밀번호 -->
		<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.oldPass" /></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<input name="oldPassword" id="oldPassword" type="password" size="20" value=""  maxlength="100" >
			</td>
		</tr>
		<!-- 비밀번호 -->
		<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.pass" /></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
			    <input name="newPassword" id="newPassword" type="password" size="20" value=""  maxlength="100" >
			</td>
		</tr>
		<!-- 비밀번호확인 -->
		<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.passConfirm" /></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
			    <input name="newPassword2" id="newPassword2" type="password" size="20" value=""  maxlength="100" >
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
	<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />

	<span class="btn_s"><a href="<c:url value='/uss/umt/EgovUserManage.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	<button class="btn_s2" onClick="document.passwordChgVO.reset();return false;" title="<spring:message code="button.reset" /> <spring:message code="input.button" />"><spring:message code="button.reset" /></button>
	</div><div style="clear:both;"></div>
	
</div>
</form>

</body>
</html>
