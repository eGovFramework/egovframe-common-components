<%
 /**
  * @Class Name : EgovDiaryManageDetail.jsp
  * @Description : 일지관리 상세조회 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.09    장동한          최초 생성
  *   2016.08.05    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.03.09
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
<c:set var="pageTitle"><spring:message code="comCopSmtDsm.title"/></c:set>
<%pageContext.setAttribute("crlf", "\r\n"); %>

<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"> 
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_DiaryManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_DiaryManage(){
	location.href = "<c:url value='/cop/smt/dsm/EgovDiaryManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_DiaryManage(){
	var vFrom = document.DiaryManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/cop/smt/dsm/EgovDiaryManageModify.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_DiaryManage(frm){
	var vFrom = frm;
	if(confirm("<spring:message code="common.delete.msg" />")){	
		vFrom.submit();
	}else{
		return false;
	}
}
</script>
</head>
<body onLoad="fn_egov_init_DiaryManage();">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 일지정보 -->
		<c:set var="title"><spring:message code="comCopSmtSam.regist.schdulCn"/></c:set>
		<tr>
			<th><label for="schdulCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			   <c:out value="${fn:replace(diaryManageVO.schdulCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 일지명 -->
		<c:set var="title"><spring:message code="comCopSmtSam.regist.diaryNm"/></c:set>
		<tr>
			<th><label for="diaryNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<c:out value="${fn:replace(diaryManageVO.diaryNm , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 지시사항 -->
		<c:set var="title"><spring:message code="comCopSmtSam.regist.drctMatter"/></c:set>
		<tr>
			<th><label for="drctMatter">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<c:out value="${fn:replace(diaryManageVO.drctMatter , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 특이사항 -->
		<c:set var="title"><spring:message code="comCopSmtSam.regist.partclrMatter"/></c:set>
		<tr>
			<th><label for="partclrMatter">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<c:out value="${fn:replace(diaryManageVO.partclrMatter , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 진척율 -->
		<c:set var="title"><spring:message code="comCopSmtSam.regist.diaryProcsPte"/></c:set>
		<tr>
			<th><label for="diaryProcsPte">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
  				<c:out value="${fn:replace(diaryManageVO.diaryProcsPte , crlf , '<br/>')}" escapeXml="false" />%
			</td>
		</tr>
		<!-- 첨부파일 -->
		<c:set var="title"><spring:message code="comCopSmtSam.regist.Atch"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> </th>
			<td class="nopd">
				<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
				<c:param name="param_atchFileId" value="${diaryManageVO.atchFileId}" />
				</c:import>   	    			
   	    	</td>
		</tr>
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="QustnrManageForm" action="<c:url value='/cop/smt/dsm/EgovDiaryManageModify.do'/>" method="post" style="float:left;">
			<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
			<input name="diaryId" type="hidden" value="${diaryManageVO.diaryId}">
			<input name="cmd" type="hidden" value="<c:out value=''/>"/>
		</form>
		
		<form name="formDelete" action="<c:url value='/cop/smt/dsm/EgovDiaryManageDetail.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" onclick="fn_egov_delete_DiaryManage(this.form); return false;"><!-- 삭제 -->
			<input name="diaryId" type="hidden" value="${diaryManageVO.diaryId}">
			<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>

		<form name="formList" action="<c:url value='/cop/smt/dsm/EgovDiaryManageList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.list" />"><!-- 목록 -->
		</form>

	</div><div style="clear:both;"></div>
	
</div>




</body>
</html>
