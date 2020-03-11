
<%
	/**
	 * @Class Name : EgovArticleUpdt.jsp
	 * @Description : EgovArticleUpdt 화면
	 * @Modification Information
	 * @
	 * @  수정일             수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2009.02.01   박정규              최초 생성
	 *   2016.06.13   김연호              표준프레임워크 v3.6 개선
	 *  @author 공통서비스팀 
	 *  @since 2009.02.01
	 *  @version 1.0
	 *  @see
	 *  
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<c:set var="pageTitle">
	<spring:message code="comCopBbs.articleVO.title" />
</c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle }<spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<validator:javascript formName="articleVO" staticJavascript="false"	xhtml="true" cdata="false" />
<script type="text/javascript">
$(function() {
	$("#ntceBgnde").datepicker(   
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
	$("#ntceEndde").datepicker(   
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
});



/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init() {

	var ckeditor_config = {
		filebrowserImageUploadUrl: '${pageContext.request.contextPath}/utl/wed/insertImageCk.do', // 파일 업로드를 처리 할 경로 설정.
	};

	CKEDITOR.replace('nttCn',ckeditor_config);

	<c:if test="${boardMasterVO.fileAtchPosblAt == 'Y'}">

	//------------------------------------------
	//------------------------- 첨부파일 수정 Start
	//-------------------------------------------
	var existFileNum = document.getElementById("articleVO").fileListCnt.value;
	var maxFileNum = document.getElementById("articleVO").atchPosblFileNumber.value;
	
	if (existFileNum == "undefined" || existFileNum == null) {
		existFileNum = 0;
	}
	if (maxFileNum == "undefined" || maxFileNum == null) {
		maxFileNum = 0;
	}
	
	var uploadableFileNum = maxFileNum - existFileNum;
	if (uploadableFileNum < 1) {
		uploadableFileNum = 1;
	}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum, 'file_label');
	multi_selector.addElement( document.getElementById( 'egovfile_1' ) );
	fn_egov_multi_selector_update_setting(multi_selector);
	//------------------------- 첨부파일 수정 End
	</c:if>
	
	// 첫 입력란에 포커스..
	document.getElementById("articleVO").nttSj.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_article(form) {
	
	CKEDITOR.instances.nttCn.updateElement();
	
	if (!validateArticleVO(form)) {
		return false;
	} else {
		
		var validateForm = document.getElementById("articleVO");
		
		//비밀글은 제목 진하게 할 수 없음.
		//비밀글은 공지게시 불가.
		if(validateForm.secretAt.checked) {
			if(validateForm.sjBoldAt.checked) {
				alert("<spring:message code="comCopBbs.articleVO.secretBold" />");
				return;
			}
			if(validateForm.noticeAt.checked) {
				alert("<spring:message code="comCopBbs.articleVO.secretNotice" />");
				return;
			}
		}
		
		//게시기간 
		var ntceBgnde = getRemoveFormat(validateForm.ntceBgnde.value);
		var ntceEndde = getRemoveFormat(validateForm.ntceEndde.value);

		if(ntceBgnde == '' && ntceEndde != '') {
			validateForm.ntceBgnde.value = '1900-01-01';
		}
		if(ntceBgnde != '' && ntceEndde == '') {
			validateForm.ntceEndde.value = '9999-12-31';
		}
		if(ntceBgnde == '' && ntceEndde == '') {
			validateForm.ntceBgnde.value = '1900-01-01';
			validateForm.ntceEndde.value = '9999-12-31';
		}

		ntceBgnde = getRemoveFormat(validateForm.ntceBgnde.value);
		ntceEndde = getRemoveFormat(validateForm.ntceEndde.value);
		
		if(ntceBgnde > ntceEndde){
			alert("<spring:message code="comCopBbs.articleVO.ntceDeError" />");
			return;
		}
		
		if (confirm("<spring:message code="common.update.msg" />")) {
			form.submit();
		}
	}
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_articlelist() {
	articleVO.action = "<c:url value='/cop/bbs/selectArticleList.do'/>";
	articleVO.submit();
}

</script>
</head>
<body onLoad="fn_egov_init();">

	<!-- javascript warning tag  -->
	<noscript class="noScriptTitle">	<spring:message code="common.noScriptTitle.msg" />	</noscript>

	<!-- 상단타이틀 -->
	<form:form commandName="articleVO" action="${pageContext.request.contextPath}/cop/bbs/updateArticle.do" method="post" onSubmit="fn_egov_updt_article(document.forms[0]); return false;" enctype="multipart/form-data">
		<div class="wTableFrm">
			<h2>${pageTitle} <spring:message code="title.update" /></h2>

			<!-- 수정폼 -->
			<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
				<caption>${pageTitle} <spring:message code="title.update" /></caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width:;">
					<col style="width:;">
					<col style="width:;">
				</colgroup>
				<tbody>
					<!-- 입력 -->
					<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
					<!-- 글 제목  -->
					<c:set var="title"><spring:message code="comCopBbs.articleVO.updt.nttSj" /></c:set>
					<tr>
						<th><label for="nttSj">${title}<span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="nttSj" title="${title} ${inputTxt }" size="70" maxlength="70" />
							<div>	<form:errors path="nttSj" cssClass="error" /></div>
						</td>
						<c:set var="title"><spring:message code="comCopBbs.articleVO.updt.sjBoldAt" /></c:set>
						<th><label for="sjBoldAt">${title}</label></th>
						<td class="left">
							<form:checkbox path="sjBoldAt" value="Y" />
							<div>	<form:errors path="sjBoldAt" cssClass="error" /></div>
						</td>
					</tr>

					<!-- 글 내용  -->
					<c:set var="title"><spring:message code="comCopBbs.articleVO.updt.nttCn" /></c:set>
					<tr>
						<th><label for="nttCn">${title}<span class="pilsu">*</span></label></th>
						<td class="nopd" colspan="3">
							<form:textarea path="nttCn" title="${title} ${inputTxt}" cols="300" rows="20" />

							<div>	<form:errors path="nttCn" cssClass="error" /></div>
						</td>
					</tr>

					<!-- 공지신청 여부  -->
					<c:set var="title"><spring:message code="comCopBbs.articleVO.updt.noticeAt" /></c:set>
					<tr>
						<th><label for="noticeAt">${title}</label></th>
						<td class="left" colspan="3">
							<form:checkbox path="noticeAt"	value="Y" />
							<div>	<form:errors path="noticeAt" cssClass="error" /></div>
						</td>
					</tr>

					<!-- 비밀글 여부 -->
					<c:set var="title"><spring:message code="comCopBbs.articleVO.updt.secretAt" /></c:set>
					<tr>
						<th><label for="secretAt">${title}</label></th>
						<td class="left" colspan="3">
							<form:checkbox path="secretAt"	value="Y" />
							<div>	<form:errors path="secretAt" cssClass="error" /></div>
						</td>
					</tr>

					<!-- 유효기간 설정  -->
					<c:set var="title"><spring:message code="comCopBbs.articleVO.updt.ntceDe"/> </c:set>
					<tr>
						<th><label for="ntceBgnde">${title}</label></th>
						<td class="left" colspan="3">
							<form:input path="ntceBgnde" title="${title} ${inputTxt}" size="70" maxlength="70"  style="width:70px;"/>
							&nbsp;~&nbsp;<form:input path="ntceEndde" title="${title} ${inputTxt}" size="70" maxlength="70"  style="width:70px;"/>
							<div><form:errors path="ntceBgnde" cssClass="error" /></div>       
							<div><form:errors path="ntceEndde" cssClass="error" /></div>       
						</td>
					</tr>

			<c:if test="${boardMasterVO.fileAtchPosblAt == 'Y'}">
					<!-- 첨부파일 -->
					<c:set var="title"><spring:message code="comUssIonNws.newsVO.atchFile"/></c:set>
					<tr>
						<th><label for="file_1">${title}</label> </th>
						<td class="nopd" colspan="3">
						<!-- 첨부목록을 보여주기 위한 -->
						<c:if test="${not empty articleVO.atchFileId}">
							<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
								<c:param name="param_atchFileId" value="${articleVO.atchFileId}" />
							</c:import>		
						</c:if>
						<c:if test="${articleVO.atchFileId == ''}">
							<input type="hidden" name="fileListCnt" value="0" />
						</c:if>
					    <!-- attached file Start -->
						<div>
							<div class="egov_file_box">
							<label for="egovfile_1" id="file_label"><spring:message code="title.attachedFileSelect" /></label> 
							<input type="file" name="file_1" id="egovfile_1" multiple> 
							</div>
							<div id="egovComFileList"></div>
						</div>
						<!-- attached file End -->
						
						</td>
					</tr>
				</tbody>
			</table>
			
			</c:if>

			<!-- 하단 버튼 -->
			<div class="btn">
				<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
				<span class="btn_s"><a href="<c:url value='/cop/bbs/selectArticleBlogList.do' />?bbsId=${boardMasterVO.bbsId}"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
			</div>
			<div style="clear: both;"></div>

		</div>

		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>" />
		<input type="hidden" name="bbsTyCode" value="<c:out value='${boardMasterVO.bbsTyCode}'/>" />
		<input type="hidden" name="replyPosblAt"	value="<c:out value='${boardMasterVO.replyPosblAt}'/>" />
		<input type="hidden" name="fileAtchPosblAt"	value="<c:out value='${boardMasterVO.fileAtchPosblAt}'/>" />
		<input type="hidden" name="atchPosblFileNumber" value="<c:out value='${boardMasterVO.atchPosblFileNumber}'/>" />
		<input type="hidden" name="atchPosblFileSize" value="<c:out value='${boardMasterVO.atchPosblFileSize}'/>" />
		<input type="hidden" name="tmplatId" value="<c:out value='${boardMasterVO.tmplatId}'/>" />
		<input name="nttId" type="hidden" value="${articleVO.nttId}">
		<input name="bbsId" type="hidden" value="${boardMasterVO.bbsId}">
	</form:form>

</body>
</html>
