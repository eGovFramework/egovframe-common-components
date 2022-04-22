<%
 /**
  * @Class Name : EgovIndvdlSchdulManageModify.jsp
  * @Description : 일정관리 수정 페이지
  * @Modification Information
  * @
  * @ 수정일                수정자           수정내용
  * @ ----------   --------   ---------------------------
  *   2008.03.09   장동한            최초 생성
  *   2016.08.12   장동한            표준프레임워크 v3.6 개선
  *   2020.10.28   신용호            파일 업로드 수정
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSmtSim.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="indvdlSchdulManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_IndvdlSchdulManage(){

	$("#schdulBgndeYYYMMDD").datepicker(  
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


	$("#schdulEnddeYYYMMDD").datepicker(  
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
	
	fn_egov_check_file("Y");
	//------------------------------------------
	//------------------------- 첨부파일 수정 Start
	//-------------------------------------------
	var maxFileNum = document.getElementById('posblAtchFileNumber').value;
	if(maxFileNum==null || maxFileNum==""){ maxFileNum = 3;}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum, 'file_label');
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	//------------------------- 첨부파일 수정 End
}
/* ******************************************************* 
 ******************************************************** */
function fn_egov_list_IndvdlSchdulManage(){
	location.href = "<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_IndvdlSchdulManage(form){
	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateIndvdlSchdulManageVO(form)){
			return;
		}else{
			var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
			var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;

			form.schdulBgnde.value = schdulBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('schdulBgndeHH') +  fn_egov_SelectBoxValue('schdulBgndeMM');
			form.schdulEndde.value = schdulEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('schdulEnddeHH') +  fn_egov_SelectBoxValue('schdulEnddeMM');

			form.submit();
		}
	}
}

function fn_egov_check_file(flag) {
	if(flag=="Y") {
		document.getElementById('file_upload_posbl').style.display = "block";
		document.getElementById('file_upload_imposbl').style.display = "none";
	} else {
		document.getElementById('file_upload_posbl').style.display = "none";
		document.getElementById('file_upload_imposbl').style.display = "block";
	}
}

/* ********************************************************
* 주관 부서 팝업창열기
******************************************************** */
function fn_egov_schdulDept_DeptSchdulManage(){

	var arrParam = new Array(1);
	arrParam[0] = self;
	arrParam[1] = "typeDeptSchdule";

	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />", arrParam ,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
}


/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_egov_schdulCharger_DeptSchdulManagee(){
	var arrParam = new Array(1);
	arrParam[0] = window;
	arrParam[1] = "typeDeptSchdule";

 	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
}

/* ********************************************************
* RADIO BOX VALUE FUNCTION
******************************************************** */
function fn_egov_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	return FValue;
}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
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
/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
</script>
</head>
<body onLoad="fn_egov_init_IndvdlSchdulManage();">

<!--  상단타이틀 -->
<form:form modelAttribute="indvdlSchdulManageVO" name="indvdlSchdulManageVO" action="${pageContext.request.contextPath}/cop/smt/sim/EgovIndvdlSchdulManageModifyActor.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_IndvdlSchdulManage(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.update" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 선택 -->
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<!-- 일정구분 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulSe"/></c:set>
		<tr>
			<th><label for="schdulSe">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:select path="schdulSe">
					<form:option value="" label="${inputSelect}"/>/>
					<form:options items="${schdulSe}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="schdulSe" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 중요도 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulIpcrCode"/></c:set>
		<tr>
			<th><label for="useStplatCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		        <form:select path="schdulIpcrCode">
		            <form:option value="" label="선택"/>
		            <form:options items="${schdulIpcrCode}" itemValue="code" itemLabel="codeNm"/>
		        </form:select>
		        <div><form:errors path="schdulIpcrCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 부서 -->
		<form:hidden path="schdulDeptName" />
		<form:hidden path="schdulDeptId" />
		<!-- 일정명 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulNm"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:input path="schdulNm" size="73" cssClass="txaIpt" maxlength="255" />
				<div><form:errors path="schdulNm" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 일정내용 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulCn"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="schdulCn" rows="3" cols="20" cssClass="txaClass"/>
				<div><form:errors path="schdulCn" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 반복구분 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.reptitSeCode"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<div style="float:left;"><form:radiobutton path="reptitSeCode" value="1"/><spring:message code="comCopSmtSdm.regist.schdulSe1"/> </div> <!-- 당일 -->
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="reptitSeCode" value="2"/><spring:message code="comCopSmtSdm.regist.schdulSe2"/> </div><!-- 반복 --> 
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="reptitSeCode" value="3"/><spring:message code="comCopSmtSdm.regist.schdulSe3"/> </div><!-- 연속 -->
				<div style="clear:both;"><form:errors path="reptitSeCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 날짜/시간 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulDatetime"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:input path="schdulBgndeYYYMMDD" size="10" readonly="true" maxlength="10" style="width:70px;"/>
				</a>
				&nbsp;&nbsp;~&nbsp;&nbsp;
				<form:input path="schdulEnddeYYYMMDD" size="10" readonly="true" maxlength="10" style="width:70px;"/>
				&nbsp;
				
        <form:select path="schdulBgndeHH">
            <form:options items="${schdulBgndeHH}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulYYYY"/> <!-- 시 -->
        <form:select path="schdulBgndeMM">
            <form:options items="${schdulBgndeMM}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulMM"/> <!-- 분 -->
		~
        <form:select path="schdulEnddeHH">
            <form:options items="${schdulEnddeHH}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulYYYY"/> <!-- 시 -->
        <form:select path="schdulEnddeMM">
            <form:options items="${schdulEnddeMM}" itemValue="code" itemLabel="codeNm"/>
        </form:select><spring:message code="comCopSmtSdm.regist.schdulMM"/> <!-- 분 -->
			</td>
		</tr>
		<!-- 담당자 -->
		<form:hidden path="schdulChargerName" />
		<form:hidden path="schdulChargerId" />
		<!-- 첨부파일 -->
		<c:set var="title"><spring:message code="comCopSmtSim.regist.schdulAtch"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
			 <!--첨부목록을 보여주기 위한 -->
			<c:if test="${indvdlSchdulManageVO.atchFileId ne null && indvdlSchdulManageVO.atchFileId ne ''}">
			<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
			<c:param name="param_atchFileId" value="${indvdlSchdulManageVO.atchFileId}" />
			</c:import>
			</c:if>
  			
  			 <!-- 첨부화일 업로드를 위한 Start -->
			<c:if test="${indvdlSchdulManageVO.atchFileId eq null || indvdlSchdulManageVO.atchFileId eq ''}">
			<input type="hidden" name="fileListCnt" value="0" />
			<input name="atchFileAt" type="hidden" value="N">
			</c:if>
			<c:if test="${indvdlSchdulManageVO.atchFileId ne null && indvdlSchdulManageVO.atchFileId ne ''}">
			<input name="atchFileAt" type="hidden" value="Y">
			</c:if>
			
		    <div id="file_upload_posbl"  style="display:none;" >
			<!-- attached file Start -->
			<div>
				<input name="file_1" id="egovComFileUploader" type="file" title="<spring:message code="title.attachedFileSelect"/>" multiple/><!-- 첨부파일명 입력 -->
			    <div id="egovComFileList"></div>
			</div>
			<!-- attached file End -->
			</div>
			<div id="file_upload_imposbl"  style="display:none;" >
	            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="left" class="UseTable">
				    <tr>
				        <td style="padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;"><spring:message code="common.imposbl.fileupload" /></td>
				    </tr>
	   	        </table>
			</div>
		
			</td>
		</tr>
	</tbody>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div><!-- div end(wTableFrm)  -->

<form:hidden path="schdulId" />
<form:hidden path="schdulKindCode" />
<input type="hidden" name="schdulBgnde" id="schdulBgnde" value="" />
<input type="hidden" name="schdulEndde" id="schdulEndde" value="" />

<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
<input type="hidden" name="cmd" id="cmd" value="<c:out value='save'/>" />
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
<input type="hidden" name="returnUrl" value="<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageModify.do'/>"/>
</form:form>

</body>
</html>
