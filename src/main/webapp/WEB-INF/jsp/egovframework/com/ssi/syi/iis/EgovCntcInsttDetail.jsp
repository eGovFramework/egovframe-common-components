<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCntcInsttDetail.jsp
  * @Description : EgovCntcInsttDetail 화면
  * @Modification Information
  * @
  * @ 수정일               수정자              수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *   2011.09.14   서준식              삭제 취소시에도 삭제되는 오류 해결
  *   2018.09.10   신용호              표준프레임워크 v3.8 개선
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comSsiSyiIis.cntcInsttDetail.title"/></title><!-- 연계기관 상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CntcInstt(){
	location.href = "<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_modify_CntcInstt(){
	var varForm				= document.all["Form"];
	varForm.action          = "<c:url value='/ssi/syi/iis/updateCntcInstt.do'/>";
	varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_CntcInstt(){
	if (confirm("<spring:message code='common.delete.msg' />")) {
		var varForm				= document.all["Form"];
		varForm.action			= "<c:url value='/ssi/syi/iis/removeCntcInstt.do'/>";
		varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
		varForm.submit();
	}
}
/* ********************************************************
* 연계시스템 등록 화면으로  바로가기
******************************************************** */
function fn_egov_regist_CntcSystem(){
	var varForm				= document.all["Form"];
	varForm.action          = "<c:url value='/ssi/syi/iis/addCntcSystem.do'/>";
	varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
	varForm.submit();
}
/* ********************************************************
* 연계서비스 등록화면으로  바로가기
******************************************************** */
function fn_egov_regist_CntcService(sysId){
	var varForm				= document.all["Form"];
	varForm.action          = "<c:url value='/ssi/syi/iis/addCntcService.do'/>";
	varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
	varForm.sysId.value		= sysId;
	varForm.submit();
}
/* ********************************************************
* 연계시스템 수정화면으로  바로가기
******************************************************** */
function fn_egov_modify_CntcSystem(sysId){
	var varForm				= document.all["Form"];
	varForm.action          = "<c:url value='/ssi/syi/iis/updateCntcSystem.do'/>";
	varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
	varForm.sysId.value		= sysId;
	varForm.submit();
}
/* ********************************************************
* 연계서비스 수정화면으로  바로가기
******************************************************** */
function fn_egov_modify_CntcService(sysId, svcId){
	var varForm				= document.all["Form"];
	varForm.action          = "<c:url value='/ssi/syi/iis/updateCntcService.do'/>";
	varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
	varForm.sysId.value		= sysId;
	varForm.svcId.value		= svcId;
	varForm.submit();
}
/* ********************************************************
* 연계시스템 삭제 처리 함수
******************************************************** */
function fn_egov_delete_CntcSystem(sysId){
	if (confirm("<spring:message code='common.delete.msg' />")) {
		var varForm				= document.all["Form"];
		varForm.action			= "<c:url value='/ssi/syi/iis/removeCntcSystem.do'/>";
		varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
		varForm.sysId.value		= sysId;
		varForm.submit();
	}
}
/* ********************************************************
* 연계서비스 삭제 처리 함수
******************************************************** */
function fn_egov_delete_CntcService(sysId, svcId){
	if (confirm("<spring:message code='common.delete.msg' />")) {
		var varForm				= document.all["Form"];
		varForm.action			= "<c:url value='/ssi/syi/iis/removeCntcService.do'/>";
		varForm.insttId.value	= "<c:out value='${result.insttId}'/>";
		varForm.sysId.value		= sysId;
		varForm.svcId.value		= svcId;
		varForm.submit();
	}
}


//-->
</script>
<style type="text/css">
.board_list thead th, .board_list tbody th {padding:5px 0; text-align:center; }
.board_list tbody tr:last-child td {border-bottom:0; }
</style>
</head>
<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form name="Form" action="" method="post">
	<input name="insttId" type="hidden">
	<input name="sysId"   type="hidden">
	<input name="svcId"   type="hidden">
</form>
<form name="listForm" action="<c:url value='/ssi/syi/iis/getCntcInsttDetail.do'/>" method="post">
</form>


<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSsiSyiIis.cntcInsttDetail.pageTop.title"/></h2><!-- 연계기관 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcInsttDetail.insttId"/> <span class="pilsu">*</span></th><!-- 연계기관ID -->
			<td class="left">
			    <c:out value="${result.insttId}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSsiSyiIis.cntcInsttDetail.insttNm"/> <span class="pilsu">*</span></th><!-- 연계기관명 -->
			<td class="left">
			    <c:out value="${result.insttNm}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="formUpdt" action="<c:url value='/ssi/syi/iis/updateCntcInstt.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_CntcInstt(); return false;" /><!-- 수정 -->
		<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
		<input name="sysId" type="hidden" value="">
		<input name="svcId" type="hidden" value="">
		</form>
			
		<form name="formDelete" action="<c:url value='/ssi/syi/iis/removeCntcInstt.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete_CntcInstt(); return false;" /><!-- 삭제 -->
		<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
		<input name="sysId"   type="hidden" value="">
		<input name="svcId"   type="hidden" value="">
		</form>

		<form name="formList" action="<c:url value='/ssi/syi/iis/getCntcInsttList.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_CntcInstt(); return false;" /><!-- 목록 -->
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

<div class="board">
	<h1><spring:message code="comSsiSyiIis.cntcInsttDetail.pageSub.title"/></h1><!-- 연계시스템 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<form name="formCntcSystemRegist" action="<c:url value='/ssi/syi/iis/addCntcSystem.do'/>" method="post">
					<input class="s_btn" type="submit" value="<spring:message code="comSsiSyiIis.cntcInsttDetail.button.regContactSystem"/>" title="<spring:message code="comSsiSyiIis.cntcInsttDetail.insttId"/>" onclick="" /><!-- 연계시스템등록 -->
					<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
				</form>
			</li>
		</ul>
	</div>

	<table class="board_list">
		<colgroup>
			<col style="width:30%" />
			<col style="width:70%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="comSsiSyiIis.cntcInsttDetail.contactSystem"/></th><!-- 연계시스템 -->
				<th scope="col"><spring:message code="comSsiSyiIis.cntcInsttDetail.contactService"/></th><!-- 연계서비스 -->
			</tr>
		</thead>
	
		<tbody>
		<c:forEach items="${cntcSystemList}" var="resultInfo" varStatus="status">
			<tr>
				<td style="vertical-align:top">
					<table width="100%" cellpadding="0" class="table-register" border="0" style="border-top:1px solid #d9d9d9">
						<colgroup>
							<col style="width:40%" />
							<col style="width:60%" />
						</colgroup>
						<tr>
							<th class="title" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.index"/></th><td width="120" class="lt_text3" nowrap><c:out value="${(cntcSystemVO.pageIndex - 1) * cntcSystemVO.pageSize + status.count}"/></td><!-- 순번 -->
						</tr>
						<tr>
							<th class="title" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.sysId"/></th><td width="120" class="lt_text3" nowrap><c:out value="${resultInfo.sysId}"/></td><!-- 시스템ID -->
						</tr>
						<tr>
							<th class="title" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.sysNm"/></th><td width="120" class="lt_text"  nowrap><c:out value="${resultInfo.sysNm}"/></td><!-- 시스템명 -->
						</tr>
						<tr>
							<th class="title" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.sysIp"/></th><td width="120" class="lt_text"  nowrap><c:out value="${resultInfo.sysIp}"/></td><!-- 시스템IP -->
						</tr>
						<tr>
							<td colspan="2">
								<form name="formCntcInsttUpdt" action="<c:url value='/ssi/syi/iis/updateCntcSystem.do'/>" method="post" style="display:inline-block; vertical-align:top">
								<span class="button"><input type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_CntcSystem('<c:out value="${resultInfo.sysId}"/>'); return false;"></span><!-- 수정 -->
								<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
								<input name="sysId"   type="hidden" value="<c:out value='${resultInfo.sysId}'/>">
								<input name="svcId"   type="hidden" value="">
								</form>
								
								<form name="formCntcInsttDelete" action="<c:url value='/ssi/syi/iis/removeCntcSystem.do'/>" method="post" style="display:inline-block; vertical-align:top">
								<span class="button"><input type="submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete_CntcSystem('<c:out value="${resultInfo.sysId}"/>'); return false;"></span><!-- 삭제 -->
								<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
								<input name="sysId"   type="hidden" value="<c:out value='${resultInfo.sysId}'/>">
								</form>
								
								<form name="formCntcInsttList" action="<c:url value='/ssi/syi/iis/addCntcService.do'/>" method="post" style="display:inline-block; vertical-align:top">
								<span class="button"><input type="submit" value="<spring:message code="comSsiSyiIis.cntcInsttDetail.button.regContactService"/>" onclick="fn_egov_regist_CntcService('${resultInfo.sysId}'); return false;"></span><!-- 연계서비스등록 -->
								<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
								<input name="sysId"   type="hidden" value="<c:out value='${resultInfo.sysId}'/>">
								</form>
							</td>
						</tr>
					</table>
				</td>
				<td style="vertical-align:top">
					<table width="100%" cellpadding="0" class="table-list" border="0">
						<colgroup>
							<col style="" />
							<col style="" />
							<col style="" />
							<col style="width:20%" />							
						</colgroup>
						<thead>
							<tr>
								<th class="title ac" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.svcNm"/></th><!-- 서비스명 -->
								<th class="title ac" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.requestMessageNm"/></th><!-- 요청메시지 -->
								<th class="title ac" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.rspnsMessageNm"/></th><!-- 응답메시지 -->
								<th class="title ac" nowrap><spring:message code="comSsiSyiIis.cntcInsttDetail.process"/></th><!-- 처리 -->
							</tr>
						</thead>
			
						<tbody>
							<c:forEach items="${cntcServiceList}" var="resultServiceInfo">
							<c:if test="${resultInfo.sysId == resultServiceInfo.sysId}">
							<tr>
								<td class="lt_text"  nowrap><c:out value="${resultServiceInfo.svcNm}"/></td>
								<td class="lt_text3" nowrap>
									<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
										<c:if test="${result.cntcMessageId == resultServiceInfo.requestMessageId}"><c:out value="${result.cntcMessageNm}"/></c:if>
									</c:forEach>
								</td>
								<td class="lt_text3" nowrap>
									<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
										<c:if test="${result.cntcMessageId == resultServiceInfo.rspnsMessageId}"><c:out value="${result.cntcMessageNm}"/></c:if>
									</c:forEach>
								</td>
								<td>
									<form name="formCntcUpdt" action="<c:url value='/ssi/syi/iis/updateCntcService.do'/>" method="post" style="display:inline-block; vertical-align:top">
									<span class="button"><input type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_CntcService('<c:out value="${resultInfo.sysId}"/>','<c:out value="${resultServiceInfo.svcId}"/>'); return false;"></span><!-- 수정 -->
									<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
									<input name="sysId"   type="hidden" value="<c:out value='${resultInfo.sysId}'/>">
									<input name="svcId"   type="hidden" value="<c:out value='${resultServiceInfo.svcId}'/>">
									</form>
									
									<form name="formCntcDelete" action="<c:url value='/ssi/syi/iis/removeCntcService.do'/>" method="post" style="display:inline-block; vertical-align:top">
									<span class="button"><input type="submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete_CntcService('<c:out value="${resultInfo.sysId}"/>','<c:out value="${resultServiceInfo.svcId}"/>'); return false;"></span><!-- 삭제 -->
									<input name="insttId" type="hidden" value="<c:out value='${result.insttId}'/>">
									<input name="sysId"   type="hidden" value="<c:out value='${resultInfo.sysId}'/>">
									<input name="svcId"   type="hidden" value="<c:out value='${resultServiceInfo.svcId}'/>">
									</form>
								</td>
							</tr>
							</c:if>
							</c:forEach>
					
						<c:if test="${fn:length(cntcServiceList) == 0}">
							<tr>
								<td class="lt_text3" colspan="5">
									<spring:message code="common.nodata.msg" />
								</td>
							</tr>
						</c:if>
				
						</tbody>
					</table>
			
				</td>
			</tr>
			</c:forEach>
		
		<c:if test="${fn:length(cntcSystemList) == 0}">
			<tr>
				<td class="lt_text3" colspan="2">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
		</c:if>
		
		</tbody>
	</table>

</div>

</body>
</html>
