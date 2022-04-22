<%
/**
 * @Class Name : EgovMemoReprtRegist.jsp
 * @Description : 메모보고 등록
 * @Modification Information
 * @
 * @ 수정일                수정자           수정내용
 * @ ----------   --------   ---------------------------
 * @ 2010.07.21   장철호            최초 생성
 * @ 2018.09.06   최두영            V3.8 오류 개선 및 퍼블리싱 점검
 * @ 2018.09.17   최두영            다국어처리
 * @ 2019.12.09   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
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
		
		   var maxFileNum = document.getElementById('posblAtchFileNumber').value;

		   if(maxFileNum==null || maxFileNum==""){
		     	maxFileNum = 3;
		    }

		   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );

		   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	}

	function fn_egov_insert_memoreprt() {

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
		
		var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
		if (!resultExtension) return true;
		var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
		if (!resultSize) return true;

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.memoReprtVO.action = "<c:url value='/cop/smt/mrm/insertMemoReprt.do'/>";
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
	<title><spring:message code="comCopSmtMrm.memoReprtRegist.title"/></title><!-- 메모보고 등록 -->
</head>
<body onLoad="initCalendar();">

<div class="board"><h1><spring:message code="comCopSmtMrm.memoReprtRegist.title"/></h1>
	
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="memoReprtVO" name="memoReprtVO" method="post" action="${pageContext.request.contextPath}/cop/smt/mrm/insertMemoReprt.do' />" enctype="multipart/form-data">

<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtDe"/> <span class="pilsu">*</span></th><!-- 보고일자 -->
			<td class="left">
			    <form:input path="reprtDe" maxlength="10" size="10" readonly="true" title="보고일자" cssStyle="width:70px"/>
				<div><form:errors path="reprtDe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자 -->
			<td class="left">
			    <c:out value="${memoReprtVO.wrterClsfNm}" escapeXml="false" />
			    <c:out value="${memoReprtVO.wrterNm}" escapeXml="false" />
				<input type="hidden" name="wrterId" id="wrterId" value="${memoReprtVO.wrterId}"/>
				<input type="hidden" name="wrterNm" id="wrterNm" value="${memoReprtVO.wrterNm}"/>
				<input type="hidden" name="wrterClsfNm" id="wrterClsfNm" value="${memoReprtVO.wrterClsfNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtVO.validate.reportrNm"/> <span class="pilsu">*</span></th><!-- 보고 대상자 -->
			<td class="left">
			    <form:input path="reportrNm" readonly="true" maxlength="10" title="보고대상명" cssStyle="width:128px"/>
			    <a href="<c:url value='/cop/smt/mrm/selectReportrListPopup.do' />" target="_blank"   title="새 창으로 이동"  onclick="fn_egov_reportr_MemoReprt('보고대상', 'reportrId', '', 'reportrNm', '');return false;">
				<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" alt="보고대상 검색" title="보고대상 검색">
				</a>
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
		<tr>
			<th><spring:message code="comCopSmtMrm.memoReprtRegist.attachFile"/><span class="pilsu">*</span></th><!--  파일첨부-->
			<td class="left">
			    <input name="file_1" id="egovComFileUploader" type="file" title="<spring:message code="comCopSmtMrm.memoReprtRegist.attachFile"/>" multiple/>
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_insert_memoreprt(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/mrm/selectMemoReprtList.do'/>?searchWrd=<c:out value='${memoReprtVO.searchWrd}'/>&amp;searchCnd=<c:out value='${memoReprtVO.searchCnd}'/>&amp;pageIndex=<c:out value='${memoReprtVO.pageIndex}'/>&amp;searchSttus=<c:out value='${memoReprtVO.searchSttus}'/>&amp;searchDrctMatter=<c:out value='${memoReprtVO.searchDrctMatter}'/>&amp;searchBgnDe=<c:out value='${memoReprtVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${memoReprtVO.searchEndDe}'/>" onclick="fn_egov_list_memoreprt(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>

	<!-- 첨부파일 갯수 -->
	<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	<!-- //첨부파일 갯수 -->
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

</div>
</body>
</html>
