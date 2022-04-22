<%
/**
 * @Class Name : EgovDeptJobBxRegist.jsp
 * @Description : 부서업무함 등록
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.07   장철호          최초 생성
 * @ 2018.08.30   최두영           V3.8 오류개선
 * @ 2018.09.10   최두영           V3.8 퍼블리싱 점검
 * @ 2018.09.14   최두영           다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.07
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<title><spring:message code="comCopSmtDjm.deptJobRegist.title" /></title><!-- 부서업무함 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="deptJobBxVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_deptjobbx(){
		if("${deptJobBxNmDuplicated}" == "true"){
			alert("<spring:message code="comCopSmtDjm.deptJobRegist.deptJobBxNmDuplicated" />");/* 부서내 중복된 부서업무함명이 있습니다. */
		}
	}

	function fn_egov_regist_deptjobbx() {
		if (!validateDeptJobBxVO(document.deptJobBxVO)){
			return;
		}

		var intIndictOrdr = eval(document.deptJobBxVO.indictOrdr.value);
		var deptIndictOrdr = eval(document.deptJobBxVO.deptIndictOrdr.value);

		if(intIndictOrdr < 1){
			alert("<spring:message code="comCopSmtDjm.deptJobRegist.intIndictOrdr" />");/* 표시순서의 값을 1 이상으로 입력해야 합니다. */
			return;
		}

		if(deptIndictOrdr < intIndictOrdr){
			alert("<spring:message code="comCopSmtDjm.deptJobRegist.deptIndictOrdr" />" +  (deptIndictOrdr -1) + "<spring:message code="comCopSmtDjm.deptJobRegist.deptIndictOrdrCause" /> " + deptIndictOrdr + "<spring:message code="comCopSmtDjm.deptJobRegist.deptIndictOrdrInput" />");/* 해당 부서의 부서업무함 표시순서의 최대 값은+이므로+이하로 입력해야 합니다. */
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/insertDeptJobBx.do'/>";
			document.deptJobBxVO.submit();
		}
	}

	function fn_egov_list_deptjobbx() {
		if(document.deptJobBxVO.indictOrdr.value == ""){
			document.deptJobBxVO.indictOrdr.value = "0";
		}
		document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>";
		document.deptJobBxVO.submit();
	}

	/* ********************************************************
	* 부서  팝업창열기
	******************************************************** */
	function fn_egov_dept_DeptJobBx(strType){
		var arrParam = new Array(1);
		arrParam[0] = window;
		arrParam[1] = strType;

		window.showModalDialog("<c:url value='/cop/smt/djm/selectDeptListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");

		if (showModalDialogSupported) {
			fn_egov_indictOrdr_DeptJobBx();
		}
	}

	/* ********************************************************
	* 표시순서가져오기
	******************************************************** */
	function fn_egov_indictOrdr_DeptJobBx(){
		if(document.deptJobBxVO.indictOrdr.value == ""){
			document.deptJobBxVO.indictOrdr.value = "0";
		}
		document.deptJobBxVO.action = "<c:url value='/cop/smt/djm/getDeptJobBxOrdr.do'/>";
		document.deptJobBxVO.submit();
	}

	function showModalDialogCallback(retVal) {
		fn_egov_indictOrdr_DeptJobBx();
	}
</script>
</head>
<body onLoad="fn_egov_init_deptjobbx()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="deptJobBxVO" name="deptJobBxVO" method="post" action="${pageContext.request.contextPath}/cop/smt/djm/insertDeptJobBx.do' />">

	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comCopSmtDjm.deptJobRegist.title" /></h2><!-- 부서업무함 등록 -->
		
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comCopSmtDjm.deptJobRegist.deptNm" /> <span class="pilsu">*</span></th><!-- 부서 -->
				<td class="left">
				    <form:input path="deptNm" size="20" readonly="true" maxlength="20" title="부서" cssStyle="width:128px"/>
				    <a href="<c:url value='/cop/smt/djm/selectDeptListPopup.do' />" target="_blank" title="새 창으로 이동" onclick="fn_egov_dept_DeptJobBx('typeDept');return false;">
						<img alt="부서" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="부서" />
					</a>
					<div><form:errors path="deptNm" cssClass="error"/></div>
					<form:hidden path="deptId" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comCopSmtDjm.deptJobRegist.deptJobBxNm" /> <span class="pilsu">*</span></th><!-- 부서업무함명 -->
				<td class="left">
					<form:input path="deptJobBxNm" size="50" maxlength="255" title="부서업무함명"/>
					<div><form:errors path="deptJobBxNm" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comCopSmtDjm.deptJobRegist.indictOrdr" /> <span class="pilsu">*</span></th><!-- 표시순서 -->
				<td class="left">
					<input id="indictOrdr" type="text" name="indictOrdr" value="<c:out value='${indictOrdrValue}'/>" title="표시순서" size="3" maxlength="6" style="width:26px" />
					<div><form:errors path="indictOrdr" cssClass="error"/></div>
				</td>
			</tr>
		</table>		
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_deptjobbx(); return false;" />
			<span class="btn_s"><a href="<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>?searchWrd=<c:out value='${deptJobBxVO.searchWrd}'/>&amp;searchCnd=<c:out value='${deptJobBxVO.searchCnd}'/>&amp;pageIndex=<c:out value='${deptJobBxVO.pageIndex}'/>" onclick="fn_egov_list_deptjobbx(); return false;"><spring:message code="button.list" /></a></span>
		</div>
		<div style="clear:both;"></div>
	<input type="hidden" name="deptIndictOrdr" value="<c:out value='${indictOrdrValue}'/>" />
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${deptJobBxVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${deptJobBxVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${deptJobBxVO.pageIndex}'/>" />   
	</div>
</form:form>
</body>
</html>
