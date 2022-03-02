<%
 /**
  * @Class Name : EgovDiaryManageRegist.jsp
  * @Description : 일지관리 등록 화면
  * @Modification Information
  * @
  * @ 수정일                수정자           수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.09   장동한            최초 생성
  *   2016.08.05   장동한            표준프레임워크 v3.6 개선
  	  2017.08.04     김예영            첨부파일 표준 개선
  	  2019.12.09     신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
  *
  *  @author 공통서비스팀 
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSmtDsm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/popup.js'/>" ></script>
<validator:javascript formName="diaryManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_DiaryManage(){
	var maxFileNum = document.getElementById('posblAtchFileNumber').value;

	var maxFileNum = 3;

	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );

	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_DiaryManage(){
	location.href = "<c:url value='/cop/smt/dsm/EgovDiaryManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_DiaryManage(form){
                                    
	var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
	if (!resultExtension) return true;
	var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
	if (!resultSize) return true;
	
	if(!validateDiaryManageVO(form)){
		return false;
	}else{
		if(confirm("<spring:message code="common.regist.msg" />")){
			form.submit();
		}
	}
}

/* ********************************************************
 * 일정정보 팝업열기
 ******************************************************** */
 function fn_egov_Schdule_DiaryManage(){

	fn_egov_popup("DEPT_DIARY_POPUP","<c:url value='/cop/smt/sdm/EgovDeptSchdulManageListPopup.do' />",800,580);
	
 }
/************************************************************************
//셀렉트박스 값 컨트롤 함수
************************************************************************/
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
	if(document.getElementById(sbName).options[i].selected == true){

	FValue=document.getElementById(sbName).options[i].value;
	}
	}

	return  FValue;
}
</script>
</head>
<body onLoad="fn_egov_init_DiaryManage()">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
		
<form:form modelAttribute="diaryManageVO"  action="${pageContext.request.contextPath}/cop/smt/dsm/EgovDiaryManageRegistActor.do" name="deptSchdulManageVO" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_DiaryManage(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 일지정보 -->
		<c:set var="title"><spring:message code="comCopSmtDsm.regist.schdulCn"/></c:set>
		<tr>
			<th><label for="schdulCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			   <form:input path="schdulCn" size="40" cssClass="txaIpt" readonly="true" maxlength="255" style="width:94%;" />
			   <a href="#" onClick="fn_egov_Schdule_DiaryManage()"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" style="border:0px" alt=<spring:message code="comCopSmtDsm.registBtn.schdulCn"/> title="<spring:message code="comCopSmtDsm.registBtn.schdulCn"/>"></a>
				<div style="display:none"><form:input path="schdulId" /></div>
				<div><form:errors path="schdulId" cssClass="error"/></div>
				<div><form:errors path="schdulCn" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 일지명 -->
		<c:set var="title"><spring:message code="comCopSmtDsm.regist.diaryNm"/></c:set>
		<tr>
			<th><label for="diaryNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:input path="diaryNm" size="73" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="diaryNm" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 지시사항 -->
		<c:set var="title"><spring:message code="comCopSmtDsm.regist.drctMatter"/></c:set>
		<tr>
			<th><label for="drctMatter">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="drctMatter" rows="3" cols="20" cssClass="txaClass"/>
				<div><form:errors path="drctMatter" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 특이사항 -->
		<c:set var="title"><spring:message code="comCopSmtDsm.regist.partclrMatter"/></c:set>
		<tr>
			<th><label for="partclrMatter">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="partclrMatter" rows="3" cols="20" cssClass="txaClass"/>
				<div><form:errors path="partclrMatter" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 진척율 -->
		<c:set var="title"><spring:message code="comCopSmtDsm.regist.diaryProcsPte"/></c:set>
		<tr>
			<th><label for="diaryProcsPte">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
  				<form:input path="diaryProcsPte" size="3" cssClass="txaIpt" maxlength="3" style="width:10%;" /> %
  				<div><form:errors path="diaryProcsPte" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 첨부파일 -->
		<c:set var="title"><spring:message code="comCopSmtDsm.regist.Atch"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label></th>
			<td class="nopd">

		<!-- 첨부파일 갯수를 위한 hidden -->
		<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
		
			<!-- 방법1) 기존의 소스 -->
			<%--
			<!--첨부파일 테이블 레이아웃 End.-->
	    	<table width="580px" cellspacing="0" cellpadding="0" border="0" align="center" class="UseTable">
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>
	   	     --%>
   	    
   	    	<!-- 방법2) 첨부파일 표준에 맞춘 소스 -->
   	    	<!-- attached file Start -->
			<div>
				<input type="file" name="file_1" id="egovComFileUploader" onChange="fnImgChange(this)" multiple> 
				</div>
				<div id="egovComFileList"></div>
			</div>
			<!-- attached file End -->
			
			
   	    	
			</td>
		</tr>
	</tbody>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
		<span class="btn_s"><a href="<c:url value='/cop/smt/dsm/EgovDiaryManageList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div><!-- div end(wTableFrm)  -->

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

</body>
</html>
