<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopTpl.template.title"/> <spring:message code="title.create" /></c:set>
<%
 /**
  * @Class Name : EgovTemplateRegist.jsp
  * @Description : 템플릿 속성 등록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.18   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.18
  *  @version 1.0
  *  @see
  *
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="templateInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_regist_tmplatInfo(){
		if (!validateTemplateInf(document.templateInf)){
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.templateInf.action = "<c:url value='/cop/tpl/insertTemplateInf.do'/>";
			document.templateInf.submit();
		}
	}

	function fn_egov_select_tmplatInfo(){
		document.templateInf.action = "<c:url value='/cop/tpl/selectTemplateInfs.do'/>";
		document.templateInf.submit();
	}

	function fn_egov_selectTmplatType(obj){
		if (obj.value == 'TMPT01') {
			document.getElementById('sometext').innerHTML = "<spring:message code='comCopTpl.template.tmplatCss' />";
		} else if (obj.value == '') {
			document.getElementById('sometext').innerHTML = "";
		} else {
			document.getElementById('sometext').innerHTML = "<spring:message code='comCopTpl.template.tmplatJsp' />";
		}
	}

	function fn_egov_previewTmplat() {
		var frm = document.templateInf;

		var url = frm.tmplatCours.value;

		var target = "";

		if (frm.tmplatSeCode.value == 'TMPT01') {
			target = "<c:url value='/cop/bbs/previewBoardList.do' />";
			width = "750";
		} else if (frm.tmplatSeCode.value == 'TMPT02') {
			target = "<c:url value='/cop/cmy/previewCmmntyMainPage.do' />";
			width = "980";
		} else if (frm.tmplatSeCode.value == 'TMPT03') {
			target = "<c:url value='/cop/cus/previewClubMainPage.do' />";
			width = "980";
		} else {
			alert('<spring:message code="comCopTpl.template.path" /> 지정 후 선택해 주세요.');
		}

		if (target != "") {
			window.open(target + "?searchWrd="+url, "preview", "width=" + width + "px, height=500px;");
		}
	}
</script>
<title>${pageTitle}</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>
<form:form commandName="templateInf" name="templateInf" method="post" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%;" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopTpl.template.name" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <input id="tmplatNm" type="text" name="tmplatNm" value="" title="<spring:message code="comCopTpl.template.name" />" maxlength="" style="width:100%" />
			    <br /><form:errors path="tmplatNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopTpl.template.type" /> <span class="pilsu">*</span></th>
			<td class="left">
				<select name="tmplatSeCode" class="select" onchange="fn_egov_selectTmplatType(this)" title="<spring:message code="comCopTpl.template.type" />">
					<option selected value=''><spring:message code="input.select" /></option> <!-- 선택하세요 -->
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
					</c:forEach>
				</select>
				&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
				<br /><form:errors path="tmplatSeCode" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopTpl.template.path" /> <span class="pilsu">*</span></th> <!-- 템플릿경로 -->
			<td class="left">
				<input id="tmplatCours" type="text" name="tmplatCours" value="" title="<spring:message code="comCopTpl.template.path" />" maxlength="60" style="width:100%" /> 
				<br /><form:errors path="tmplatCours" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopTpl.template.useYN" /> <span class="pilsu">*</span></th>
			<td class="left">
				Y : <input type="radio" name="useAt" class="radio2" value="Y"  checked>&nbsp;
		     	N : <input type="radio" name="useAt" class="radio2" value="N">
		     	<br /><form:errors path="useAt" />
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="" onclick="fn_egov_regist_tmplatInfo();return false;" title="<spring:message code="button.create" />"><spring:message code="button.create" /></a></span> <!-- 등록 -->
		<span class="btn_s"><a href="" onclick="fn_egov_select_tmplatInfo();return false;" title="<spring:message code="button.list" />"><spring:message code="button.list" /></a></span> <!-- 목록 -->
		<span class="btn_s2"><a href="" onclick="fn_egov_previewTmplat();return false;" title="<spring:message code="comCopTpl.template.preview" />"><spring:message code="comCopTpl.template.preview" /></a></span> <!-- 미리보기 -->
	</div>
	<div style="clear:both;"></div>
</div>


</form:form>
</body>
</html>
