<%
/**
 * @Class Name : EgovWikMnthngReprtUpdt.jsp
 * @Description : 주간/월간보고 수정
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.21   장철호          최초 생성
 * @ 2018.10.02   이정은          공통컴포넌트 3.8 개선
 * @ 2019.12.06   신용호          KISA 보안약점 조치 (위험한 형식 파일 업로드)
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="copSmtWmr.wikMnthngReprtUpdt.wikMnthngReprtUpdt"/></title><!-- 주간/월간보고 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="wikMnthngReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
	function fn_egov_update_wikmnthngreprt() {
		if (!validateWikMnthngReprtVO(document.wikMnthngReprtVO)){
			return;
		}

		var bgnDe = document.wikMnthngReprtVO.reprtBgnDe.value.split("-").join("");
		var endDe = document.wikMnthngReprtVO.reprtEndDe.value.split("-").join("");

		if(bgnDe != ""){
			if(isDate(bgnDe, "<spring:message code="copSmtWmr.wikMnthngReprtUpdt.bgnDe"/>") == false) {/* 해당시작일자 */
		        return;
		    }
		}

		if(endDe != ""){
		    if(isDate(endDe, "<spring:message code="copSmtWmr.wikMnthngReprtUpdt.endDe"/>") == false) {/* 해당종료일자 */
		        return;
		    }
		}

		if(bgnDe != "" && endDe != ""){
			if(eval(bgnDe) > eval(endDe)){
				alert("<spring:message code="copSmtWmr.wikMnthngReprtUpdt.validate.searchDeAlert"/>");/* 해당종료일자가 해당시작일자보다 빠를수 없습니다. */
				return;
			}
		}

		var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
		if (!resultExtension) return true;
		var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
		if (!resultSize) return true;
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.wikMnthngReprtVO.action = "<c:url value='/cop/smt/wmr/updateWikMnthngReprt.do'/>";
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

	 	window.showModalDialog("<c:url value='/cop/smt/wmr/selectReportrListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}
/* ********************************************************
 * 달력
 ******************************************************** */
	function fn_egov_init_date(){
		
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

		$("#reprtBgnDe").datepicker( 
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
		
		$("#reprtEndDe").datepicker( 
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
</script>
</head>
<body onLoad="fn_egov_init_date();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form modelAttribute="wikMnthngReprtVO" name="wikMnthngReprtVO" method="post" action="${pageContext.request.contextPath}/cop/smt/wmr/updateWikMnthngReprt.do' />" enctype="multipart/form-data">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="copSmtWmr.wikMnthngReprtUpdt.wikMnthngReprtUpdt"/></h2><!-- 주간/월간보고 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.searchSe"/> <span class="pilsu">*</span></th><!-- 보고유형 -->
			<td class="left">
			    <form:radiobutton path="reprtSe" value="1" /><spring:message code="copSmtWmr.wikMnthngReprtUpdt.WeeklyReport"/><!-- 주간보고 -->
				<form:radiobutton path="reprtSe" value="2" /><spring:message code="copSmtWmr.wikMnthngReprtUpdt.MonthlyReport"/><!-- 월간보고 -->
				<div><form:errors path="reprtSe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtDe"/> <span class="pilsu">*</span></th><!-- 보고일자 -->
			<td class="left">
				<c:set var="reprtDate"><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtDe"/></c:set>
				<form:input path="reprtDe" maxlength="10" title="${reprtDate}" cssStyle="width:70px" />
				<div><form:errors path="reprtDe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtBgnEndDe"/> <span class="pilsu">*</span></th><!-- 해당일자 -->
			<td class="left">
				<c:set var="reprtBgnDate"><spring:message code="copSmtWmr.wikMnthngReprtRegist.bgnDe"/></c:set>
				<c:set var="reprtEndDate"><spring:message code="copSmtWmr.wikMnthngReprtRegist.endDe"/></c:set>
			    <form:input path="reprtBgnDe" maxlength="10" title="${reprtBgnDate}" cssStyle="width:70px" /> ~ 
				<form:input path="reprtEndDe" maxlength="10" title="${reprtEndDate}" cssStyle="width:70px"/>
				<div><form:errors path="reprtBgnDe" cssClass="error"/></div>
				<div><form:errors path="reprtEndDe" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.wrterNm"/> <span class="pilsu">*</span></th><!-- 작성자 -->
			<td class="left">
				<c:out value="${wikMnthngReprtVO.wrterNm}" escapeXml="false" />
				<input type="hidden" name="wrterId" id="wrterId" value="${wikMnthngReprtVO.wrterId}"/>
				<input type="hidden" name="wrterNm" id="wrterNm" value="${wikMnthngReprtVO.wrterNm}"/>
				<input type="hidden" name="wrterClsfNm" id="wrterClsfNm" value="${wikMnthngReprtVO.wrterClsfNm}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reportrNm"/> <span class="pilsu">*</span></th><!-- 보고대상자 -->
			<td class="left">
				<form:input path="reportrNm" readonly="true" maxlength="10" title="보고대상명" cssStyle="width:98px"/>
				<a href="<c:url value='/cop/smt/wmr/selectReportrListPopup.do' />" target="_blank"  title="새 창으로 이동"  onclick="fn_egov_reportr_WikMnthngReprt('보고대상자', 'reportrId', '', 'reportrNm', '');return false;">
					<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" alt="보고대상자 검색" title="보고대상자 검색" />
				</a>
			
				<div><form:errors path="reportrNm" cssClass="error"/></div>
				<form:hidden path="reportrId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtSuj"/> <span class="pilsu">*</span></th><!-- 보고서제목 -->
			<td class="left">
				<c:set var="reprtSubject"><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtSuj"/></c:set>
			    <form:input path="reprtSj" maxlength="255" title="${reprtSubject}"/>
      			<div><form:errors path="reprtSj" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtThswikCn"/> <span class="pilsu">*</span></th><!-- 금주보고내용 -->
			<td class="left">
			    <c:set var="reprtThswikContent"><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtThswikCn"/></c:set>
			    <form:textarea path="reprtThswikCn" rows="7" cols="90" title="${reprtThswikContent}"/>
  				<div><form:errors path="reprtThswikCn" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtLesseeCn"/> <span class="pilsu">*</span></th><!-- 차주보고내용 -->
			<td class="left">
			    <c:set var="reprtLesseeContent"><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtLesseeCn"/></c:set>
			    <form:textarea path="reprtLesseeCn" rows="7" cols="90" title="${reprtLesseeContent}"/>
  				<div><form:errors path="reprtLesseeCn" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.partclrMatter"/></th><!-- 특이사항 -->
			<td class="left">
			    <c:set var="particularMatter"><spring:message code="copSmtWmr.wikMnthngReprtUpdt.partclrMatter"/></c:set>
			    <form:textarea path="partclrMatter" rows="5" cols="90" title="${particularMatter}"/>
  				<div><form:errors path="partclrMatter" cssClass="error"/></div>
			</td>
		</tr>
		<c:if test="${wikMnthngReprtVO.atchFileId ne null && wikMnthngReprtVO.atchFileId ne ''}">
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.atchFile"/></th><!-- 첨부파일 목록 -->
			<td class="left">
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
					<c:param name="param_atchFileId" value="${egovc:encrypt(wikMnthngReprtVO.atchFileId)}" />
				</c:import>&nbsp;
			</td>
		</tr>
		</c:if>
		<tr>
			<th><label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label> <!-- 파일선택 --></th><!-- <spring:message code="copSmtWmr.wikMnthngReprtUpdt.file"/> 파일첨부 -->
			<td class="left">
				<input name="file_1" id="egovComFileUploader" type="file" title="${title}" multiple/><!-- 첨부파일명 입력 -->
				<div id="egovComFileList"></div>  
			</td>
			<c:choose>
				<c:when test="${empty wikMnthngReprtVO.atchFileId}">
					<input name="atchFileAt" type="hidden" value="N">
				</c:when>
				<c:otherwise>
					<input name="atchFileAt" type="hidden" value="Y">
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th><spring:message code="copSmtWmr.wikMnthngReprtUpdt.reprtSttus"/></th><!-- 보고서 상태 -->
			<td class="left">
			    <c:out value="${wikMnthngReprtVO.reprtSttus}" escapeXml="false" />&nbsp;
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_update_wikmnthngreprt(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/cop/smt/wmr/selectWikMnthngReprtList.do'/>?searchWrd=<c:out value='${wikMnthngReprtVO.searchWrd}'/>&amp;searchCnd=<c:out value='${wikMnthngReprtVO.searchCnd}'/>&amp;pageIndex=<c:out value='${wikMnthngReprtVO.pageIndex}'/>&amp;searchSttus=<c:out value='${wikMnthngReprtVO.searchSttus}'/>&amp;searchDe=<c:out value='${wikMnthngReprtVO.searchDe}'/>&amp;searchBgnDe=<c:out value='${wikMnthngReprtVO.searchBgnDe}'/>&amp;searchEndDe=<c:out value='${wikMnthngReprtVO.searchEndDe}'/>" onclick="fn_egov_list_wikmnthngreprt(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

	<input type="hidden" name="returnUrl" value="<c:url value='/cop/smt/wmr/modifyWikMnthngReprt.do' />" />
	<!--form:hidden path="reprtId" / -->
	<input type="hidden" name="reprtId" value="<c:out value='${egovc:encryptId(wikMnthngReprtVO.reprtId)}'/>" />
	

	<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	<!-- //첨부파일 개수를 위한 hidden -->

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
