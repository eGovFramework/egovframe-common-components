<%--
  Class Name : EgovQustnrQestnManageModify.jsp
  Description : 설문문항 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.19    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.19

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQqm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="qustnrQestnManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrQestnManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrQestnManage(){

	var varFrom = document.getElementById("qustnrQestnManageVO");
	varFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
	varFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrQestnManage(){
	var varFrom = document.getElementById("qustnrQestnManageVO");

	if(confirm("<spring:message code='common.save.msg' />")){
		//varFrom.action =  "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageModify.do' />";

		if(!validateQustnrQestnManageVO(varFrom)){
			return;
		}else{
			varFrom.action =  "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageModify.do' />";
			varFrom.submit();
		}
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrQestnManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="qustnrQestnManageVO" name="qustnrQestnManageVO" action="${pageContext.request.contextPath}/uss/olp/qqm/EgovQustnrQestnManageModify.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrQestnManage(document.forms[0]); return false;">
 
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문지정보(제목) -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				${resultList[0].qestnrSj}
      			<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
      			<input name="qestnrId" id="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
      			<input name="searchMode" id="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">
			</td>
		</tr>
		<!-- 질문순번 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnSn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<input name="qestnSn" type="text" size="50" value="${resultList[0].qestnSn}" maxlength="70" style="width:60px;" title="<spring:message code='comUssOlpQqm.regist.qestnSn'/><spring:message code='input.input'/>"><!-- title="질문순번 입력" -->
      			<div><form:errors path="qestnSn" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 질문유형 -->	
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnTyCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="qestnTyCode" title="<spring:message code='comUssOlpQqm.regist.qestnTyCode'/><spring:message code='input.cSelect'/>"><!-- title="질문유형 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach items="${cmmCode018}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList[0].qestnTyCode}">selected</c:if>>${comCodeList.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 질문내용 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<textarea name="qestnCn" id="qestnCn" class="textarea"  cols="75" rows="10" title="<spring:message code='comUssOlpQqm.regist.qestnCn'/><spring:message code='input.input'/>" style="width:99%;">${resultList[0].qestnCn}</textarea><!-- title="질문내용 입력" -->
    			<div><form:errors path="qestnCn" cssClass="error"  /></div>
    		</td>
		</tr>
		<!-- 최대선택건수 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.mxmmChoiseCo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<select name="mxmmChoiseCo" title="<spring:message code='comUssOlpQqm.regist.mxmmChoiseCo'/><spring:message code='input.cSelect'/>"><!-- title="최대선택건수 선택" -->
		       	<option value="1" <c:if test="${resultList[0].mxmmChoiseCo == '1'}">selected</c:if>>1</option>
		       	<option value="2" <c:if test="${resultList[0].mxmmChoiseCo == '2'}">selected</c:if>>2</option>
		       	<option value="3" <c:if test="${resultList[0].mxmmChoiseCo == '3'}">selected</c:if>>3</option>
		       	<option value="4" <c:if test="${resultList[0].mxmmChoiseCo == '4'}">selected</c:if>>4</option>
		       	<option value="5" <c:if test="${resultList[0].mxmmChoiseCo == '5'}">selected</c:if>>5</option>
		       	<option value="6" <c:if test="${resultList[0].mxmmChoiseCo == '6'}">selected</c:if>>6</option>
		       	<option value="7" <c:if test="${resultList[0].mxmmChoiseCo == '7'}">selected</c:if>>7</option>
		       	<option value="8" <c:if test="${resultList[0].mxmmChoiseCo == '8'}">selected</c:if>>8</option>
		       	<option value="9" <c:if test="${resultList[0].mxmmChoiseCo == '9'}">selected</c:if>>9</option>
		       	<option value="10" <c:if test="${resultList[0].mxmmChoiseCo == '10'}">selected</c:if>>10</option>
		      	</select>
			</td>
		</tr>
		
	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_egov_save_QustnrQestnManage(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

</form:form>
</div>
</body>
</html>