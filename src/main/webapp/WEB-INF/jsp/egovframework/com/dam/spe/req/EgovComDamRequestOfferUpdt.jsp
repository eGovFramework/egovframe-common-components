<%--
  Class Name : EgovComDamRequestOfferUpdt.jsp
  Description : 지식 정보제공/정보요청 수정
  Modification Information
 
       수정일                수정자           수정내용
    ----------   --------   ---------------------------
    2010.08.30   장동한           최초 생성
    2018.09.11   신용호           공통컴포넌트 3.8 개선
    2019.12.09   신용호           KISA 보안약점 조치 (위험한 형식 파일 업로드)
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.30
    
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<c:set var="JsUrl" value="/js/egovframework/com/dam/spe/req/"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comDamSpeReq.comDamRequestOfferUpdt..title"/></title><!-- 지식 정보제공/정보요청-수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="requestOfferVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
* 저장
******************************************************** */
function fn_egov_save_RequestOffer(){
	var vFrom = document.requestOfferVO;
	
	var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
	if (!resultExtension) return true;
	var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
	if (!resultSize) return true;
	
	if(confirm("<spring:message code="common.save.msg" />")){

		vFrom.action = "<c:url value='/dam/spe/req/updtRequestOfferActor.do'/>";
		
		if(!validateRequestOfferVO(vFrom)){ 			
			return;
		}else{
			vFrom.submit();
		} 
	}
}
/* ********************************************************
 * 지식유형 가져오기
 ******************************************************** */
function fn_egov_get_CodeId(form){
 	form.cmd.value = "change";
 	form.submit();
}
</script>
</head>
<body>

<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="requestOfferVO" name="requestOfferVO" action="${pageContext.request.contextPath}/dam/spe/req/updtRequestOffer.do" method="post" enctype="multipart/form-data" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comDamSpeReq.comDamRequestOfferUpdt..pageTop.title"/></h2><!-- 지식 정보제공/정보요청 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferUpdt.orgnztNm"/> <span class="pilsu">*</span></th><!--조직명-->
			<td class="left">
			    <select name="orgnztId" class="select" onChange="javascript:fn_egov_get_CodeId(document.requestOfferVO)">
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach var="knoPersonal" items="${mapTeamList}" varStatus="status">							
				<option value='<c:out value="${knoPersonal.orgnztId}"/>' <c:if test="${fn:trim(knoPersonal.orgnztId) == fn:trim(requestOfferVO.orgnztId)}">selected="selected"</c:if> ><c:out value="${knoPersonal.orgnztNm}"/></option>
				</c:forEach>			  		   
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferUpdt.knoTypeNm"/> <span class="pilsu">*</span></th><!--지식유형명-->
			<td class="left">
			    <select name="knoTypeCd" class="select">
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach var="knoPersonal" items="${mapMaterialList}" varStatus="status">
				<option value='<c:out value="${knoPersonal.knoTypeCd}"/>' <c:if test="${knoPersonal.knoTypeCd == requestOfferVO.knoTypeCd}">selected="selected"</c:if> ><c:out value="${knoPersonal.knoTypeNm}"/></option>
				</c:forEach>			  		   
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferUpdt.knoNm"/> <span class="pilsu">*</span></th><!--지식명-->
			<td class="left">
			    <form:input path="knoNm" size="73" title="<spring:message code='comDamSpeReq.comDamRequestOfferUpdt.knoNm'/>" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="knoNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferUpdt.knoCn"/> <span class="pilsu">*</span></th><!--지식내용-->
			<td class="left">
			    <form:textarea path="knoCn" title="<spring:message code='comDamSpeReq.comDamRequestOfferUpdt.knoCn'/>" rows="5" cols="20" cssClass="txaClass"/><!--지식내용-->
				<div><form:errors path="knoCn" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 첨부파일 테이블 레이아웃 설정 Start -->
  		<c:if test="${requestOfferVO.atchFileId ne ''}">
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferUpdt.atchFileList"/></th><!--파일첨부 목록-->
			<td class="left">
			    <!-- c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" -->
				<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${egovc:encrypt(requestOfferVO.atchFileId)}" />
				</c:import>
			</td>
		</tr>
		</c:if>	
		<!-- 첨부파일 테이블 레이아웃 End -->
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferUpdt.atchFile"/></th><!--파일첨부-->
			<td class="left">
			    <input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comDamSpeReq.comDamRequestOfferUpdt.atchFile"/>"/><!--파일첨부-->
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_RequestOffer(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/dam/spe/req/listRequestOffer.do'/>"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>

</div>
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3">
<input type="hidden" name="returnUrl" value="<c:url value='/dam/spe/req/updtRequestOffer.do'/>" >

<form:hidden path="ansParents" />
<form:hidden path="ansDepth" />
<form:hidden path="ansSeq" />
<form:hidden path="ansNumber" />

<input name="knoId" type="hidden" value="${requestOfferVO.knoId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
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