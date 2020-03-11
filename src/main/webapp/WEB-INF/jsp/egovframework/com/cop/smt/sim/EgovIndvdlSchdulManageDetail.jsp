<%
 /**
  * @Class Name : EgovIndvdlSchdulManageDetail.jsp
  * @Description : 일정관리 상세보기
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09   장동한              최초 생성
  *   2016.08.12   장동한              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
String sLinkType = request.getParameter("linkType") == null ? "" : (String)request.getParameter("linkType");
sLinkType = EgovWebUtil.clearXSSMaximum(sLinkType);
%>
<c:set var="pageTitle"><spring:message code="comCopSmtSim.title"/></c:set>
<c:set var="sLinkType" value="<%=sLinkType %>"/>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_IndvdlSchdulManage(){

}


/* ********************************************************
 * 일지관리 이동
 ******************************************************** */
function fn_egov_diary_IndvdlSchdulManage(frm){
	var vFrom = frm;
	//vFrom.cmd.value = '';
	//vFrom.action = "<c:url value='/cop/smt/dsm/EgovDiaryManageList.do' />";;
	vFrom.submit();
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_IndvdlSchdulManage(){
	<%-- 일정목록 이동 --%>
	<c:if test="${sLinkType eq ''}">
		location.href = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageList.do' />";
	</c:if>

	<%-- 전체일정목록 이동 --%>
	<c:if test="${sLinkType eq 'asm'}">
		location.href = "<c:url value='/cop/smt/sam/EgovAllSchdulManageList.do' />";
	</c:if>
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_IndvdlSchdulManage(){
	var vFrom = document.IndvdlSchdulManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageModify.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_IndvdlSchdulManage(frm){
	var vFrom = frm;
	if(confirm("<spring:message code="common.delete.msg" />")){	
		//vFrom.cmd.value = 'del';
		//vFrom.action = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_IndvdlSchdulManage();">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:16%;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 선택 -->
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<!-- 일정구분 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulSe"/></c:set>
		<tr>
			<th><label for="schdulSe">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			    <c:forEach items="${schdulSe}" var="schdulSeInfo" varStatus="status">
			    <c:if test="${schdulSeInfo.code eq resultList[0].schdulSe}">
			     <c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
			    </c:if>
			    </c:forEach>
			</td>
		</tr>
		<!-- 중요도 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulIpcrCode"/></c:set>
		<tr>
			<th><label for="useStplatCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
			    <c:forEach items="${schdulIpcrCode}" var="schdulSeInfo" varStatus="status">
			    <c:if test="${schdulSeInfo.code eq resultList[0].schdulIpcrCode}">
			     <c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
			    </c:if>
			    </c:forEach>
			</td>
		</tr>
		<!-- 일정명 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulNm"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<c:out value="${fn:replace(resultList[0].schdulNm , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 일정내용 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulCn"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<c:out value="${fn:replace(resultList[0].schdulCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 반복구분 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.reptitSeCode"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
			    <c:forEach items="${reptitSeCode}" var="schdulSeInfo" varStatus="status">
			    <c:if test="${schdulSeInfo.code eq resultList[0].reptitSeCode}">
			     <c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
			    </c:if>
			    </c:forEach>
			</td>
		</tr>
		<!-- 날짜/시간 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulDatetime"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				${fn:substring(resultList[0].schdulBgnde, 0, 4)}-${fn:substring(resultList[0].schdulBgnde, 4, 6)}-${fn:substring(resultList[0].schdulBgnde, 6, 8)} ${fn:substring(resultList[0].schdulBgnde, 8, 10)}시  ${fn:substring(resultList[0].schdulBgnde, 10, 12)}분 ~
    			${fn:substring(resultList[0].schdulEndde, 0, 4)}-${fn:substring(resultList[0].schdulEndde, 4, 6)}-${fn:substring(resultList[0].schdulEndde, 6, 8)} ${fn:substring(resultList[0].schdulEndde, 8, 10)}시  ${fn:substring(resultList[0].schdulEndde, 10, 12)}분
			</td>
		</tr>
		<!-- 첨부파일 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulAtch"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> </th>
			<td class="nopd">
				<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
				<c:param name="param_atchFileId" value="${resultList[0].atchFileId}" />
				</c:import>
			</td>
		</tr>
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
	
		<form name="IndvdlSchdulManageForm" id="IndvdlSchdulManageFormh" action="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageModify.do'/>" method="post" style="float:left;">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
		<input name="schdulId" type="hidden" value="${resultList[0].schdulId}">
		<input name="linkType" type="hidden" value="${sLinkType}">
		<input name="cmd" type="hidden" value="<c:out value=''/>"/>
		</form>

		<form name="formDelete" action="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" onclick="fn_egov_delete_IndvdlSchdulManage(this.form); return false;">
		<input name="schdulId" type="hidden" value="${resultList[0].schdulId}">
		<input name="linkType" type="hidden" value="${sLinkType}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
	
		<!-- 2011.09.16 : 일지관리 버튼 존재 방법 변경  -->
		<c:if test="${useDiaryManage == 'true'}">
		<form name="formSubDiaryManage" action="<c:url value='/cop/smt/dsm/EgovDiaryManageList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<input type="submit" class="s_submit" value="<spring:message code="comCopSmtSim.btn.diaryManage" />" title="<spring:message code="comCopSmtSim.btn.diaryManage" /> <spring:message code="input.button" />" onclick="fn_egov_diary_IndvdlSchdulManage(this.form); return false;">
		<input name="schdulId" type="hidden" value="${resultList[0].schdulId}">
		<input name="linkType" type="hidden" value="${sLinkType}">
		<input name="cmd" type="hidden" value=""/>
		</form>

		</c:if>
		<!-- 2011.09.16 끝  -->
		
		<form name="formList" action="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.list" />" title="<spring:message code="button.list" /> <spring:message code="input.button" />" onClick="fn_egov_list_IndvdlSchdulManage(); return false;"><!-- 목록 -->
		</form>
	
	</div><div style="clear:both;"></div>
	
</div>



</body>
</html>
