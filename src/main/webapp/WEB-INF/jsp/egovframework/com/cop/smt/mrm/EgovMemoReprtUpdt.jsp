<%
/**
 * @Class Name : EgovMemoReprtUpdt.jsp
 * @Description : 메모보고 수정
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.23   장철호          최초 생성
 * @ 2018.09.17   최두영          퍼블리 점검&다국어처리
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtMrm.memoReprtUpdt.title"/></title><!-- 메모보고 수정 -->
<link href="<c:url value='/css/egovframework/com/com.css'/>"  rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css'/>"  rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="memoReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

	function initCalendar(){
		$("#reprtDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});
	}

	function fn_egov_update_memoreprt() {

		if( document.memoReprtVO.reportrNm.value == "" ){
			alert("<spring:message code="comCopSmtMrm.memoReprtRegist.reprtCn.reportrNm"/>");/* 보고대상자는 필수 입력값입니다. */
			return;
		}

		if( document.memoReprtVO.reprtSj.value == "" ){
			alert("<spring:message code="comCopSmtMrm.memoReprtRegist.reprtCn.reprtSj"/>");/* 보고서제목은 필수 입력값입니다. */
			return;
		}

		if( document.memoReprtVO.reprtCn.value == "" ){
			alert("<spring:message code="comCopSmtMrm.memoReprtRegist.reprtCn.essential"/>");/* 보고내용은 필수 입력값입니다. */
			return;
		}

		if (!validateMemoReprtVO(document.memoReprtVO)){
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/updateMemoReprt.do'/>";
			document.memoReprtVO.submit();
		}
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_memoreprt(){
		document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/selectMemoReprtList.do'/>";
		document.memoReprtVO.submit();
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

	 	window.showModalDialog("<c:url value='/cop/smt/mrm/selectReportrListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}
</script>
</head>
<body onLoad="initCalendar();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="memoReprtVO" name="memoReprtVO" method="post" action="${pageContext.request.contextPath}/cop/smt/mrm/updateMemoReprt.do' />" enctype="multipart/form-data">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtMrm.memoReprtUpdt.title"/></h2><!-- 메모보고 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtDe"/> <span class="pilsu">*</span></th><!-- 보고일자 -->
			<td class="left">
			    <form:input path="reprtDe" maxlength="10" readonly="true" title="보고일자" cssStyle="width:70px"/>
				<div><form:errors path="reprtDe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자 -->
			<td class="left">
			    <c:out value="${memoReprtVO.wrterClsfNm}" escapeXml="false" />
				&nbsp;
				<c:out value="${memoReprtVO.wrterNm}" escapeXml="false" />
				<input type="hidden" name="wrterId" id="wrterId" value="${memoReprtVO.wrterId}"/>
				<input type="hidden" name="wrterNm" id="wrterNm" value="${memoReprtVO.wrterNm}"/>
				<input type="hidden" name="wrterClsfNm" id="wrterClsfNm" value="${memoReprtVO.wrterClsfNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reportrNm"/> <span class="pilsu">*</span></th><!-- 보고 대상자 -->
			<td class="left">
				<form:input path="reportrNm" readonly="true" maxlength="10" title="보고대상명" cssStyle="width:70px"/>
				<a href="<c:url value='/cop/smt/mrm/selectReportrListPopup.do' />" target="_blank"  title="새 창으로 이동"  onclick="fn_egov_reportr_MemoReprt('보고대상', 'reportrId', '', 'reportrNm', '');return false;">
				<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" alt="보고대상 검색" title="보고대상 검색"/>
				</a>
			
			    <div><form:errors path="reportrNm" cssClass="error"/></div>
	       		<form:hidden path="reportrId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtSj"/> <span class="pilsu">*</span></th><!-- 보고제목 -->
			<td class="left">
			    <form:input path="reprtSj" size="75" maxlength="255" title="제목"/>
	      		<div><form:errors path="reprtSj" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtRegist.reprtCn"/><span class="pilsu">*</span></th><!-- 보고내용 -->
			<td class="left">
			    <form:textarea path="reprtCn" rows="35" cols="95" title="보고내용"/>
    	  		<div><form:errors path="reprtCn" cssClass="error"/></div>
			</td>
		</tr>
		<c:if test="${memoReprtVO.atchFileId ne null && memoReprtVO.atchFileId ne ''}">
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtRegist.attachFile"/></th><!-- 첨부파일 목록 -->
			<td class="left">
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
					<c:param name="param_atchFileId" value="${memoReprtVO.atchFileId}" />
				</c:import>
			</td>
		</tr>
		</c:if>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtRegist.attachFile"/><span class="pilsu">*</span></th><!--  파일첨부-->
			<td class="left">
				<input name="file_1" id="egovComFileUploader" type="file" title="<spring:message code="comCopSmtMrm.memoReprtRegist.attachFile"/>" multiple/><!-- 첨부파일 -->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_update_memoreprt(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/mrm/selectMemoReprtList.do'/>?searchWrd=<c:out value='${memoReprtVO.searchWrd}'/>&amp;searchCnd=<c:out value='${memoReprtVO.searchCnd}'/>&amp;pageIndex=<c:out value='${memoReprtVO.pageIndex}'/>&amp;searchSttus=<c:out value='${memoReprtVO.searchSttus}'/>&amp;searchDrctMatter=<c:out value='${memoReprtVO.searchDrctMatter}'/>&amp;searchBgnDe=<c:out value='${memoReprtVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${memoReprtVO.searchEndDe}'/>" onclick="fn_egov_list_memoreprt(); return false;" onclick=""><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

	<input type="hidden" name="returnUrl" value="<c:url value='/cop/smt/mrm/modifyMemoReprt.do' />" />
	<form:hidden path="reprtId" />
	<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	<!-- //첨부파일 갯수를 위한 hidden -->

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
<!-- 첨부파일 업로드 가능화일 설정 Start..-->  
<script type="text/javascript">
var maxFileNum = document.getElementById('posblAtchFileNumber').value;
if(maxFileNum==null || maxFileNum==""){
	maxFileNum = 3;
}
var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script> 
<!-- 첨부파일 업로드 가능화일 설정 End.-->
</body>
</html>
