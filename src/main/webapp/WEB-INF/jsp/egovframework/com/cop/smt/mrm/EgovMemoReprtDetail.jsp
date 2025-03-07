<% 
/**
 * @Class Name : EgovMemoReprtDetail.jsp
 * @Description : 메모보고 상세보기
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.23   장철호          최초 생성
 * @ 2018.09.06   최두영          V3.8 오류 개선 및 퍼블리싱 점검/다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.23
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
<title><spring:message code="comCopSmtMrm.memoReprtDetail.title"/></title><!-- 메모보고 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="memoReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_MemoReprt(){
	
	}
	/* ********************************************************
	 * 메모보고 수정 으로 가기
	 ******************************************************** */
	
	function fn_egov_modify_memoreprt() {
		document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/modifyMemoReprt.do'/>";
		document.memoReprtVO.submit();					
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_memoreprt(){
		document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/selectMemoReprtList.do'/>";
		document.memoReprtVO.submit();	
	}	

	function fn_egov_delete_memoreprt(){
		if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
			document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/deleteMemoReprt.do'/>";
			document.memoReprtVO.submit();
		}
	}

	function fn_egov_confirm_memoreprt(){
		if(confirm("<spring:message code="comCopSmtMrm.memoReprtDetail.memoreprt.confirm"/>")){/* 의견등록을 하시겠습니까? */
			document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/updateMemoReprtDrctMatter.do'/>";
			document.memoReprtVO.submit();
		}
	}

	/* ********************************************************
	* 아이디  팝업창열기
	******************************************************** */
	function fn_egov_reportr_MemoReprt(strTitle, frmUniqId, frmEmplNo, frmEmplyrNm, frmOrgnztNm){
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
<body onLoad="fn_egov_init_MemoReprt()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="memoReprtVO" name="memoReprtVO" method="post" action="${pageContext.request.contextPath}/cop/smt/mrm/updateMemoReprt.do' />">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtMrm.memoReprtDetail.title"/></h2><!-- 메모보고 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtDe"/> <span class="pilsu">*</span></th><!-- 보고일자 -->
			<td class="left">
			    <c:out value="${memoReprt.reprtDe}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자 -->
			<td class="left">
			    <c:out value="${memoReprt.wrterClsfNm}" escapeXml="false" />
	      		<c:out value="${memoReprt.wrterNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reportrNm"/> <span class="pilsu">*</span></th><!-- 보고 대상자 -->
			<td class="left">
			    <c:out value="${memoReprt.reportrClsfNm}" escapeXml="false" />
				<c:out value="${memoReprt.reportrNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtSj"/> <span class="pilsu">*</span></th><!-- 보고제목 -->
			<td class="left">
			    <c:out value="${memoReprt.reprtSj}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtRegist.reprtCn"/><span class="pilsu">*</span></th><!-- 보고내용 -->
			<td class="left">
			    <c:out value="${fn:replace(memoReprt.reprtCn , crlf , '<br>')}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtRegist.attachFile"/><span class="pilsu">*</span></th><!--  파일첨부-->
			<td class="left">
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
				<c:param name="param_atchFileId" value="${egovc:encrypt(memoReprt.atchFileId)}" />
				</c:import>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtList.searchSttus"/> <span class="pilsu">*</span></th><!-- 보고서 상태 -->
			<td class="left">
				<c:out value="${memoReprt.reprtSttus}" escapeXml="false" />			    
			</td>
		</tr>
		
		<c:if test="${memoReprt.reportrId eq uniqId}">
		<tr>
	        <th><spring:message code="comCopSmtMrm.memoReprtDetail.drctMatter"/> <span class="pilsu">*</span></th><!-- 의견/지시사항 -->
	        <td class="left">
	            <textarea id="drctMatter" name="drctMatter" rows="5" cols="75" title="<spring:message code='comCopSmtMrm.memoReprtDetail.drctMatter'/>"><c:out value="${memoReprt.drctMatter}" escapeXml="false" /></textarea><!-- 의견/지시사항 -->
	            <div><form:errors path="drctMatter" cssClass="error"/></div>
	        </td>
	    </tr>
		</c:if>
		<c:if test="${memoReprt.wrterId eq uniqId and not empty memoReprt.drctMatter}">
	    <tr>
	        <th><spring:message code="comCopSmtMrm.memoReprtDetail.drctMatter"/> <span class="pilsu">*</span></th><!-- 의견/지시사항 -->
	        <td class="left">
        	    <c:out value="${memoReprt.drctMatter}" escapeXml="false" />
	        </td>
	    </tr>
		</c:if>
		
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${memoReprt.reportrId eq uniqId}">
			<span class="btn_s" style="width:64px !important"><a href="<c:url value='/cop/smt/mrm/updateMemoReprtDrctMatter.do'/>?searchWrd=<c:out value='${memoReprtVO.searchWrd}'/>&amp;searchCnd=<c:out value='${memoReprtVO.searchCnd}'/>&amp;pageIndex=<c:out value='${memoReprtVO.pageIndex}'/>&amp;searchSttus=<c:out value='${memoReprtVO.searchSttus}'/>&amp;searchDrctMatter=<c:out value='${memoReprtVO.searchDrctMatter}'/>&amp;searchBgnDe=<c:out value='${memoReprtVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${memoReprtVO.searchEndDe}'/>" onclick="fn_egov_confirm_memoreprt(); return false;"><spring:message code="comCopSmtMrm.memoReprtDetail.memoreprt"/></a></span><!-- 의견등록 -->
		</c:if>
		<c:if test="${(memoReprt.reprtSttus eq '미확인' or memoReprt.reprtSttus eq 'Unidentified') && memoReprt.wrterId eq uniqId}">
			<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_modify_memoreprt(); return false;" />
			<span class="btn_s"><a href="<c:url value='/cop/smt/mrm/deleteMemoReprt.do'/>?reprtId=<c:out value='${egovc:encryptId(memoReprtVO.reprtId)}'/>" onclick="fn_egov_delete_memoreprt(); return false;"><spring:message code="button.delete" /></a></span>
		</c:if>
		<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_memoreprt(); return false;" />
	</div>
	<div style="clear:both;"></div>
</div>

	<!--form:hidden path="reprtId" /-->
	<input type="hidden" name="reprtId" value="<c:out value='${egovc:encryptId(memoReprtVO.reprtId)}'/>" />
	
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${memoReprtVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${memoReprtVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${memoReprtVO.pageIndex}'/>" />
    <input type="hidden" name="searchSttus" value="<c:out value='${memoReprtVO.searchSttus}'/>" />
    <input type="hidden" name="searchDrctMatter" value="<c:out value='${memoReprtVO.searchDrctMatter}'/>" />
    <input type="hidden" name="searchBgnDe" value="<c:out value='${memoReprtVO.searchBgnDe}'/>" />
    <input type="hidden" name="searchEndDe" value="<c:out value='${memoReprtVO.searchEndDe}'/>" />
    <!-- 검색조건 유지 -->

</form:form>

</body>
</html>
