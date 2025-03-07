<% 
/**
 * @Class Name : EgovWikMnthngReprtDetail.jsp
 * @Description : 주간/월간보고 상세보기
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.21   장철호          최초 생성
 * @ 2018.10.02   이정은          공통컴포넌트 3.8 개선
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.21
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
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="copSmtWmr.wikMnthngReprtDetail.wikMnthngReprtDetail"/></title><!-- 주간/월간보고 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="wikMnthngReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_WikMnthngReprt(){
		
	}

	function fn_egov_modify_wikmnthngreprt() {
		document.wikMnthngReprtVO.action = "<c:url value='/cop/smt/wmr/modifyWikMnthngReprt.do'/>";
		document.wikMnthngReprtVO.submit();					
	}

	function fn_egov_delete_wikmnthngreprt(){
		if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
			document.wikMnthngReprtVO.action = "<c:url value='/cop/smt/wmr/deleteWikMnthngReprt.do'/>";
			document.wikMnthngReprtVO.submit();
		}
	}

	function fn_egov_confirm_wikmnthngreprt(){
		if(confirm("<spring:message code="common.acknowledgement.msg"/>")){/* 승인 하시겠습니까? */
			document.wikMnthngReprtVO.action = "<c:url value='/cop/smt/wmr/confirmWikMnthngReprt.do'/>";
			document.wikMnthngReprtVO.submit();
		}
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_wikmnthngreprt(){
		document.wikMnthngReprtVO.action = "<c:url value='/cop/smt/wmr/selectWikMnthngReprtList.do'/>";
		document.wikMnthngReprtVO.submit();	
	}	


	/* ********************************************************
	* 아이디  팝업창열기
	******************************************************** */
	function fn_egov_reportr_WikMnthngReprt(strTitle, frmUniqId, frmEmplNo, frmEmplyrNm, frmOrgnztNm){
		var arrParam = new Array(6);
		arrParam[0] = window;
		arrParam[1] = strTitle;
		arrParam[2] = frmUniqId;
		arrParam[3] = frmEmplNo;
		arrParam[4] = frmEmplyrNm;
		arrParam[5] = frmOrgnztNm;

	 	window.showModalDialog("<c:url value='/uss/ion/ism/selectSanctnerListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}
</script>

</head>
<body onLoad="fn_egov_init_WikMnthngReprt()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="wikMnthngReprtVO" name="wikMnthngReprtVO" method="post" action="${pageContext.request.contextPath}/cop/smt/wmr/modifyWikMnthngReprt.do' />">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="copSmtWmr.wikMnthngReprtDetail.wikMnthngReprtDetail"/></h2><!-- 주간/월간보고 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtSeInfo"/> <span class="pilsu">*</span></th><!-- 보고유형 -->
			<td class="left">
			    <c:forEach items="${reprtSe}" var="reprtSeInfo" varStatus="status">
				<c:if test="${reprtSeInfo.code eq wikMnthngReprt.reprtSe}">	
				<c:out value="${reprtSeInfo.codeNm}" escapeXml="false" />
				</c:if>
				</c:forEach>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtDe"/> <span class="pilsu">*</span></th><!-- 보고일자 -->
			<td class="left">
			    <c:out value="${wikMnthngReprt.reprtDe}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtBgnEndDe"/> <span class="pilsu">*</span></th><!-- 해당일자 -->
			<td class="left">
			    <c:out value="${wikMnthngReprt.reprtBgnDe}" escapeXml="false" />
				~
				<c:out value="${wikMnthngReprt.reprtEndDe}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자 -->
			<td class="left">
				<c:out value="${wikMnthngReprt.wrterClsfNm}" escapeXml="false" />
				<c:out value="${wikMnthngReprt.wrterNm}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reportrNm"/> <span class="pilsu">*</span></th><!-- 보고대상자 -->
			<td class="left">
			    <c:out value="${wikMnthngReprt.reportrClsfNm}" escapeXml="false" />
				<c:out value="${wikMnthngReprt.reportrNm}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtSuj"/> <span class="pilsu">*</span></th><!-- 보고서제목 -->
			<td class="left">
			    <c:out value="${wikMnthngReprt.reprtSj}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtThswikCn"/> <span class="pilsu">*</span></th><!-- 금주보고내용 -->
			<td class="left">
			    <c:out value="${fn:replace(wikMnthngReprt.reprtThswikCn , crlf , '<br>')}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtLesseeCn"/> <span class="pilsu">*</span></th><!-- 차주보고내용 -->
			<td class="left">
			    <c:out value="${fn:replace(wikMnthngReprt.reprtLesseeCn , crlf , '<br>')}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.partclrMatter"/></th><!-- 특이사항 -->
			<td class="left">
			    <c:out value="${fn:replace(wikMnthngReprt.partclrMatter , crlf , '<br>')}" escapeXml="false" />&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.file"/></th><!-- 파일첨부 -->
			<td class="left">
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" > 
				<c:param name="param_atchFileId" value="${egovc:encrypt(wikMnthngReprt.atchFileId)}" /> 
				</c:import>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtDetail.reprtSttus"/></th><!-- 보고서 상태 -->
			<td class="left">
			    <c:out value="${wikMnthngReprt.reprtSttus}" escapeXml="false" />&nbsp;
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${fn:substring(wikMnthngReprt.reprtSttus,0,2) eq '등록' && wikMnthngReprt.reportrId eq uniqId}">
		<span class="btn_s"><a href="<c:url value='/cop/smt/wmr/confirmWikMnthngReprt.do'/>?searchWrd=<c:out value='${wikMnthngReprtVO.searchWrd}'/>&amp;searchCnd=<c:out value='${wikMnthngReprtVO.searchCnd}'/>&amp;pageIndex=<c:out value='${wikMnthngReprtVO.pageIndex}'/>&amp;searchSttus=<c:out value='${wikMnthngReprtVO.searchSttus}'/>&amp;searchDe=<c:out value='${wikMnthngReprtVO.searchDe}'/>&amp;searchBgnDe=<c:out value='${wikMnthngReprtVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${wikMnthngReprtVO.searchEndDe}'/>" onclick="fn_egov_confirm_wikmnthngreprt(); return false;"><spring:message code="button.acknowledgment" /></a></span><!-- 승인 -->
		</c:if>
		
		<c:if test="${fn:substring(wikMnthngReprt.reprtSttus,0,2) eq '등록' && wikMnthngReprt.wrterId eq uniqId}">	
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_wikmnthngreprt(); return false;" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/cop/smt/wmr/deleteWikMnthngReprt.do'/>?reprtId=<c:out value='${egovc:encryptId(wikMnthngReprtVO.reprtId)}'/>" onclick="fn_egov_delete_wikmnthngreprt(); return false;"><spring:message code="button.delete" /></a></span>
		</c:if>
		
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_wikmnthngreprt(); return false;" /><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

	<!--form:hidden path="reprtId" / -->
	<input type="hidden" name="reprtId" value="<c:out value='${egovc:encryptId(wikMnthngReprtVO.reprtId)}'/>" />
	
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${wikMnthngReprtVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${wikMnthngReprtVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${wikMnthngReprtVO.pageIndex}'/>" />
    <input type="hidden" name="searchSttus" value="<c:out value='${wikMnthngReprtVO.searchSttus}'/>" />
    <input type="hidden" name="searchDe" value="<c:out value='${wikMnthngReprtVO.searchDe}'/>" />
    <input type="hidden" name="searchBgnDe" value="<c:out value='${wikMnthngReprtVO.searchBgnDe}'/>" />
    <input type="hidden" name="searchEndDe" value="<c:out value='${wikMnthngReprtVO.searchEndDe}'/>" />
    <!-- 검색조건 유지 -->
</form:form>

</body>
</html>
