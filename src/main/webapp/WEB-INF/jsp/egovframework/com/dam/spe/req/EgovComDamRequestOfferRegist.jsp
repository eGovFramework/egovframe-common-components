<%--
  Class Name : EgovComDamRequestOfferRegist.jsp
  Description : 지식 정보제공/정보요청 등록
  Modification Information

       수정일                수정자         수정내용
    ----------   --------  ---------------------------
    2010.08.30   장동한          최초 생성
	2011.10.07     이기하          조직명 선택 후 화면 재로딩되는 오류 주석처리
	2018.09.11     신용호          공통컴포넌트 3.8 개선
	2019.12.09     신용호          KISA 보안약점 조치 (위험한 형식 파일 업로드)

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
<c:set var="JsUrl" value="/js/egovframework/com/dam/spe/req/"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="comDamSpeReq.comDamRequestOfferRegist..title"/></title><!-- 지식 정보제공/정보요청-등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="requestOfferVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
* 초기화
******************************************************** */
function fn_egov_init_RequestOffer(){

	var maxFileNum = document.getElementById('posblAtchFileNumber').value;

	   if(maxFileNum==null || maxFileNum==""){
	     	maxFileNum = 3;
	    }

	   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );

	   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );

}
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

		vFrom.action = "<c:url value='/dam/spe/req/registRequestOfferActor.do' />";

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
 	form.cmd.value = "";
 	form.submit();
}
</script>
</head>
<body onLoad="fn_egov_init_RequestOffer()">

<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="requestOfferVO" name="requestOfferVO" action="${pageContext.request.contextPath}/dam/spe/req/registRequestOffer.do" method="post" enctype="multipart/form-data" >
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comDamSpeReq.comDamRequestOfferRegist..pageTop.title"/> <c:if test="${cmd eq 'reply'}"><spring:message code="comDamSpeReq.comDamRequestOfferRegist.answer"/></c:if><c:if test="${cmd eq ''}"><spring:message code="comDamSpeReq.comDamRequestOfferRegist.regist"/><!-- 답변/등록 -->
</c:if></h2><!-- 지식 정보제공/정보요청 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferRegist.orgnztNm"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <select name="orgnztId" class="select" >
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach var="knoPersonal" items="${mapTeamList}" varStatus="status">
				<option value='<c:out value="${knoPersonal.orgnztId}"/>' <c:if test="${knoPersonal.orgnztId == requestOfferVO.orgnztId}">selected="selected"</c:if> ><c:out value="${knoPersonal.orgnztNm}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferRegist.knoTypeNm"/> <span class="pilsu">*</span></th>
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
			<th><spring:message code="comDamSpeReq.comDamRequestOfferRegist.knoNm"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="knoNm" size="73" title="지식명" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="knoNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferRegist.knoCn"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:textarea path="knoCn" title="지식내용" rows="5" cols="20" cssClass="txaClass"/>
				<div><form:errors path="knoCn" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comDamSpeReq.comDamRequestOfferRegist.atchFile"/> <span class="pilsu">*</span></th>
			<td class="left">
			    <input name="file_1" id="egovComFileUploader" type="file" multiple title="<spring:message code="comDamSpeReq.comDamRequestOfferRegist.atchFile"/>">
			    <div id="egovComFileList"></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_RequestOffer(); return false;" />
		<span class="btn_s"><a href="<c:url value='/dam/spe/req/listRequestOffer.do' />"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<c:if test="${cmd eq 'reply'}">
<form:hidden path="ansParents" />
<form:hidden path="ansDepth" />
<form:hidden path="ansSeq" />
<form:hidden path="ansNumber" />
<input name="cmd" type="hidden" value="<c:out value='reply'/>"/>
</c:if>
<c:if test="${cmd eq ''}">
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</c:if>

</form:form>

</body>
</html>