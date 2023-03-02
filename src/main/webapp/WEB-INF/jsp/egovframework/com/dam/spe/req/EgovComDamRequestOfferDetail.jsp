<%--
  Class Name : EgovComDamRequestOfferDetail.jsp
  Description : 지식 정보제공/정보요청 등록
  Modification Information
 
       수정일                 수정자          수정내용
    ----------    --------  ---------------------------
    2010.08.30    장동한          최초 생성
    2018.09.11    신용호          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.30
    
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%--  자마스크립트 메세지 출력 --%>
${reusltScript}
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comDamSpeReq.comDamRequestOfferDetail.title"/></title><!-- 지식 정보제공/정보요청 -상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_RequestOffer(){

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_RequestOffer(){
	var vFrom = document.RequestOfferForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/dam/spe/req/updtRequestOffer.do'/>";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_RequestOffer(){
	var vFrom = document.RequestOfferForm;
	if(confirm("삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/dam/spe/req/detailRequestOffer.do'/>";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_RequestOffer();">

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comDamSpeReq.comDamRequestOfferDetail.pageTop.title"/></h2><!-- 지식 정보제공/정보요청  상세조회 -->

	<form name="RequestOfferForm" action="<c:url value='/dam/spe/req/detailRequestOffer.do'/>" method="post">
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferDetail.orgnztNm"/></th><!-- 조직명 -->
			<td class="left">
			    <c:forEach var="knoPersonal" items="${mapTeamList}" varStatus="status">
				<c:if test="${fn:trim(knoPersonal.orgnztId) == fn:trim(requestOfferVO.orgnztId)}"><c:out value="${knoPersonal.orgnztNm}"/></c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferDetail.knoTypeNm"/></th><!-- 지식유형명 -->
			<td class="left">
			    <c:forEach var="knoPersonal" items="${mapMaterialList}" varStatus="status">
				<c:if test="${knoPersonal.knoTypeCd == requestOfferVO.knoTypeCd}"><c:out value="${knoPersonal.knoTypeNm}"/></c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferDetail.knoNm"/></th><!-- 지식명 -->
			<td class="left">
			    <c:out value="${requestOfferVO.knoNm}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferDetail.knoCn"/></th><!-- 지식내용 -->
			<td class="left">
			    <c:out value="${fn:replace(requestOfferVO.knoCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferDetail.atchFile"/></th><!-- 파일첨부 -->
			<td class="left">&nbsp;
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" > 
				<c:param name="param_atchFileId" value="${egovc:encrypt(requestOfferVO.atchFileId)}" /> 
				</c:import>
			</td>
		</tr>
	</table>
	<input name="knoId" type="hidden" value="${requestOfferVO.knoId}">
	<input name="cmd" type="hidden" value="<c:out value=''/>">
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
	</form>

	<!-- 하단 버튼 -->
	<div class="btn">
		<%-- 자기 글일때만 수정/삭제버튼 활성화 --%>
		<c:if test="${requestOfferVO.frstRegisterId eq USER_UNIQ_ID}">
		<form name="formUpdt" action="<c:url value='/dam/spe/req/updtRequestOffer.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_RequestOffer(); return false;" /><!-- 수정 -->
		<input name="knoId" type="hidden" value="${requestOfferVO.knoId}">
		</form>

		<form name="formDelete" action="<c:url value='/dam/spe/req/detailRequestOffer.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fn_egov_delete_RequestOffer(); return false;" /><!-- 삭제 -->
		<input name="knoId" type="hidden" value="${requestOfferVO.knoId}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
		</form>
		</c:if>
		
		<c:if test="${IS_SPE eq 'Y'}">
		<form name="formReply" action="<c:url value='/dam/spe/req/registRequestOffer.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value="답변" onclick="this.form.submit();return false;" /><!-- 답변 -->
		<input name="knoId" type="hidden" value="${requestOfferVO.knoId}">
		
		<input name="ansDepth" type="hidden" value="${requestOfferVO.ansDepth + 1 }">
		<input name="ansSeq" type="hidden" value="${requestOfferVO.ansSeq + 1}">
		<input name="ansParents" type="hidden" value="${requestOfferVO.knoId}">
		<input name="ansNumber" type="hidden" value="${requestOfferVO.ansNumber}">
		
		<input name="orgnztId" type="hidden" value="${requestOfferVO.orgnztId}">
		<input name="knoTypeCd" type="hidden" value="${requestOfferVO.knoTypeCd}">
		<input name="cmd" type="hidden" value="reply">
		</form>
		</c:if>
		
		<form name="formList" action="<c:url value='/dam/spe/req/listRequestOffer.do'/>" method="post" style="display:inline-block; vertical-align:top">
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="" /><!-- 목록 -->
		</form>
	</div>
	<div style="clear:both;"></div>
</div>

</body>
</html>