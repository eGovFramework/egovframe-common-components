<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopTpl.template.title"/> <spring:message code="title.update" /></c:set>
<%
 /**
  * @Class Name : EgovTemplateUpdt.jsp
  * @Description : 템플릿 속성 수정화면
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
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">  
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="templateInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_update_tmplatInfo() {
		if (!validateTemplateInf(document.templateInf)){
			return;
		}

		if (confirm('<spring:message code="common.update.msg" />')) {
			document.templateInf.action = "<c:url value='/cop/tpl/updateTemplateInf.do'/>";
			document.templateInf.submit();
		}
	}

	function fn_egov_select_tmplatInfo() {
		document.templateInf.action = "<c:url value='/cop/tpl/selectTemplateInfs.do'/>";
		document.templateInf.submit();
	}

	function fn_egov_selectTmplatType(obj) {
		if (obj.value == 'TMPT01') {
			document.getElementById('sometext').innerHTML = "게시판 템플릿은 CSS만 가능합니다.";
		} else if (obj.value == '') {
			document.getElementById('sometext').innerHTML = "";
		} else {
			document.getElementById('sometext').innerHTML = "템플릿은 JSP만 가능합니다.";
		}
	}

	function fn_egov_previewTmplat() {
		var frm = document.templateInf;

		var url = frm.tmplatCours.value;

		var target = "";
		var width = "";

		if (frm.tmplatSeCode.value == 'TMPT01') {
			target = "<c:url value='/cop/bbs/previewBoardList.do' />";
			width = "750";
		} else if (frm.tmplatSeCode.value == 'TMPT02') {
			target = "<c:url value='/cop/cmy/previewCmmntyMainPage.do' />";
			width = "980";
		} else if (frm.tmplatSeCode.value == 'TMPT03') {
			target = "<c:url value='/cop/bbs/previewBlogMainPage.do' />";
			width = "1020";
		} else {
			alert('<spring:message code="comCopTpl.template.path" /> 지정 후 선택해 주세요.');
		}

		if (target != "") {
			window.open(target + "?searchWrd="+url, "preview", "width=" + width + "px, height=500px;");
		}
	}
</script>
</head>
<body onload="fn_egov_selectTmplatType(document.templateInf.tmplatSeCode)">
<form:form commandName="templateInf" name="templateInf" method="post" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input name="tmplatNm" type="hidden" value='<c:out value="${TemplateInfVO.tmplatNm}"/>' />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2> <!-- 템플릿 정보수정 -->
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%;" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopTpl.template.name" /></th>
			<td class="left">
			    <input name="tmplatId" type="hidden" value='<c:out value="${TemplateInfVO.tmplatId}"/>' />
	      		<c:out value="${TemplateInfVO.tmplatNm}"/>
	      		<br /><form:errors path="tmplatId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopTpl.template.type" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="tmplatSeCode" class="select" onchange="fn_egov_selectTmplatType(this)" title="<spring:message code="title.searchCondition" /><spring:message code="input.cSelect" />"> <!-- 검색조건선택 -->
				   <option selected value=''>--<spring:message code="input.select" />--</option> <!-- 선택하세요 -->
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<option value='<c:out value="${result.code}"/>' <c:if test="${TemplateInfVO.tmplatSeCode == result.code}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
					</c:forEach>
				</select>&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
				<br/><form:errors path="tmplatSeCode" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopTpl.template.path" />D <span class="pilsu">*</span></th>
			<td class="left">
				<input name="tmplatCours" type="text" value='<c:out value="${TemplateInfVO.tmplatCours}"/>' maxlength="60" title="<spring:message code="comCopTpl.template.path" />" style="width:100%" />
	      		<br/><form:errors path="tmplatCours" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopTpl.template.useYN" /> <span class="pilsu">*</span></th>
			<td class="left">
			    Y : <input type="radio" name="useAt" value="Y" <c:if test="${TemplateInfVO.useAt == 'Y'}"> checked="checked"</c:if> >&nbsp;
	     		N : <input type="radio" name="useAt" value="N" <c:if test="${TemplateInfVO.useAt == 'N'}"> checked="checked"</c:if>>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_update_tmplatInfo();return false;" title="<spring:message code="button.update" />" />
		<span class="btn_s"><a href="" onclick="fn_egov_select_tmplatInfo();return false;" title="<spring:message code="button.list" />"><spring:message code="button.list" /></a></span>
		<span class="btn_s2"><a href="" onclick="fn_egov_previewTmplat();return false;" title="<spring:message code="comCopTpl.template.preview" />"><spring:message code="comCopTpl.template.preview" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>
