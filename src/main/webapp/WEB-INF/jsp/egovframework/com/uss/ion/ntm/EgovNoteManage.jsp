<%
/* 
  Class Name : EgovNoteManage.jsp
  Description : 쪽지 관리(보내기) 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.13    장동한           최초 생성
     2017.06.05   최두영          공통컴포넌트 3.7 개선
    author   : 공통서비스 개발팀 장동한
    since    : 2010.07.13
 */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!-- 쪽지관리 -->
<c:set var="pageTitle"><spring:message code="comUssIonNtm.NoteManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="noteManage" staticJavascript="false" xhtml="true" cdata="false" />
<script type="text/javaScript">
/* ********************************************************
* 저장
******************************************************** */
function fn_egov_save_NoteManage(){
	var vFrom = document.noteManage;
	//수신자 처리
	fn_egov_empList_NoteManage();
	if(confirm("<spring:message code="common.save.msg"/>")){
		vFrom.action = "<c:url value='/uss/ion/ntm/registEgovNoteManageActor.do'/>";
		if(!validateNoteManage(vFrom)){
			return;
		}else{
			vFrom.submit();
			alert("<spring:message code="comUssIonNtm.NoteMange.success"/>")
		}
	}
}

/* ********************************************************
* 초기화
******************************************************** */
function fn_egov_init_NoteManage(){
	//수신구분 초기화
	document.getElementsByName("recptnSe")[0].checked = true;
	//초기 recptnEmp 삭제 0
   	document.getElementById("recptnEmp").options[0].selected = true;
	fn_egov_delete_NoteManage(0);

	<c:if test="${cmd eq 'reply'}">
		//답변 수신자 처리
		var option = document.createElement("option");
		option.appendChild(document.createTextNode("수신:${noteManageMap.trnsmiterNm}(${noteManageMap.trnsmiterId})"));
		option.setAttribute("value", "${noteManageMap.trnsmiterOrgId}");
		option.recptnSe = "1";
		document.getElementById("recptnEmp").appendChild(option);
	</c:if>
}

/* ********************************************************
* 팝업창에서 수진자 목록에서 값받기
******************************************************** */
function fn_egov_recptnEmpOption_NoteManage(sText,sValue,sRecptnSe){
	//수신자가 중복 될때 빠져 나가기
	if(fn_egov_recptnEmpSearch_NoteManage(sValue)){
		return;
	};
	var option = document.createElement("option");
	option.appendChild(document.createTextNode(sText));
	option.setAttribute("value", sValue);
	option.recptnSe = sRecptnSe;
	document.getElementById("recptnEmp").appendChild(option);
}

/* ********************************************************
* 수신자 목록 / 참조목록
******************************************************** */
function fn_egov_empList_NoteManage(){
	var sbName = "recptnEmp";
	var FValue = document.getElementById(sbName).length;
	var sEmpList = "";
	var sRecptnSeList = "";
	if (FValue == 0) {
		return;
	}
	var a = document.getElementById(sbName).options[0].value;
	for(var i=0; i < FValue; i++){
		if(document.getElementById(sbName).options[i].value != ""){
			sEmpList = sEmpList + document.getElementById(sbName).options[i].value + ",";
			sRecptnSeList = sRecptnSeList + document.getElementById(sbName).options[i].recptnSe + ",";
		}
		if(document.getElementById(sbName).value != ""){
			sEmpList = sEmpList + document.getElementById(sbName).value;
		}
	}
	sEmpList = sEmpList.substring(0,sEmpList.length-1);
	sRecptnSeList = sRecptnSeList.substring(0,sRecptnSeList.length-1);
	document.getElementById("recptnEmpList").value = sEmpList;
	document.getElementById("recptnSeList").value = sRecptnSeList;
}

/* ********************************************************
* 수신자 삭제
******************************************************** */
function fn_egov_delete_NoteManage(nChk){
	var sbName = "recptnEmp";
	var FValue = document.getElementById(sbName).length;
	var DValue = 0;
	//삭제시 삭제 갯수 체크
	if(nChk){
		if(FValue == 0 || document.getElementById(sbName).selectedIndex == -1){
			alert("<spring:message code="comUssIonNtm.NoteMange.alert.noList"/>");
			document.getElementById(sbName).focus();
			return;
		}
	}
	for(var i=FValue-1; i >= 0; i--){
		if(document.getElementById(sbName).options[i].selected == true){
			DValue++;
			document.getElementById(sbName).options[i] = null;
		}
	}
	document.getElementById(sbName).length = FValue-DValue;
}

/* ********************************************************
* 수신자 찾기
******************************************************** */
function fn_egov_recptnEmpSearch_NoteManage(sSearchName){
	var sbName = "recptnEmp";
	var FValue = document.getElementById(sbName).length;
	var DValue = 0;
	for(var i=0; i < FValue; i++)
	{
		if(document.getElementById(sbName).options[i].value == sSearchName){
			return true;
		}
	}
	return false;
}

/* ********************************************************
* 수신자 목록 팝업
******************************************************** */
function  fn_egov_recptnEmpSearchPupup(){
	var width = 800;
	var height = 600;
	var left = (screen.width-width)/2;
	var top = (screen.height-height)/3;
	var url = "<c:url value='/uss/ion/ntm/listEgovNoteEmpListPopup.do'/>";
	var name = "recptnEmpSearchPupup";

	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus()}
}

</script>
<style>
.btnNote { text-align: center;  margin-top: 18px; margin-bottom: 15px; font-size: 11px; }
.btnNote input.s_submit { height: 21px; vertical-align:middle; padding:4px 10px 7px 11px;  margin-top: 1px; background: #4688d2; font-size: 11px; font-family:'돋움', '굴림', 'Arial', 'AppleGothic', 'sans-serif';  font-weight: 200; border: none; color: #fff; border-radius: 1px; cursor: pointer; }
.btnNote input.s_submit:hover { background: #7dabdf; }
</style>
</head>
<body onLoad="fn_egov_init_NoteManage();">
<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="noteManage"  name="noteManage"  action="/uss/ion/ntm/registEgovNoteManage.do" method="post" enctype="multipart/form-data" >

<div class="wTableFrm">
	<h2>${pageTitle}</h2>

		<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<caption>${pageTitle}</caption>
			<colgroup>
				<col style="width: 22%;">
				<col style="width: ;">
			</colgroup>
		<tbody>
				<tr>
					<th><img src="<c:url value='/images/egovframework/com/uss/ion/ntm/check.png'/>" alt="check"/><label for="noteSj"><spring:message code="comUssIonNtm.NoteMange.subject"/></label></th><!-- 제목 -->
					<!-- 제목 -->
					<c:set var="subject"><spring:message code="comUssIonNtm.NoteManage.title"/></c:set>
					<td><form:input path="noteSj" title="${subject}" size="87" maxlength="255"/><!-- 제목 : 쪽지내용 입력 -->
					</td>
				</tr>
				<tr>
					<th><img src="<c:url value='/images/egovframework/com/uss/ion/ntm/check.png'/>" alt="check"/> <spring:message code="comUssIonNtm.NoteMange.receiver"/></th>
					<td>
						<div style="clear:both;"></div>
				   		<div style="text-align: left;">		
							<!-- 수신 -->
							<c:set var="reception"><spring:message code="comUssIonNtm.NoteMange.reception"/></c:set>	
							<!-- 참조 -->
							<c:set var="reference"><spring:message code="comUssIonNtm.NoteMange.reference"/></c:set>	
							<!-- 목록 -->
							<c:set var="recptnEmpList"><spring:message code="comUssIonNtm.NoteMange.receiverList"/></c:set>
							<div>
							<select name="recptnEmp" title="${recptnEmnpList}" id="recptnEmp" style="width:200px;height:80px;" multiple>
								<option value=''></option>
							</select>
							<!-- 수신/참조 선택  -->
							<form:radiobutton path="recptnSe" value="1" style="width:18px;border: 1px solid #dedede;"/><spring:message code="comUssIonNtm.NoteMange.reception"/>
							<form:radiobutton path="recptnSe" value="2" style="width:18px;border: 1px solid #dedede;"/><spring:message code="comUssIonNtm.NoteMange.reference"/>
							<form:errors path="recptnSe" cssClass="error"/>
							<!-- 수신/참조자 선택 목록 팝업창 열기 -->
							<a href="<c:url value='/uss/ion/ntm/listEgovNoteEmpListPopup.do'/>" onClick="fn_egov_recptnEmpSearchPupup();return false;"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" style="border:0px;margin-right:10px;" alt="${recptnEmnpList}" title="${recptnEmnpList}"></a>
							<!-- 수신자 목록 리스트 제외 -->
							<span class="btn_s"><a href="#LINK" onClick="fn_egov_delete_NoteManage(1);"  title="<spring:message code="comUssIonNtm.NoteMange.delete" /> <spring:message code="input.button" />"><spring:message code="comUssIonNtm.NoteMange.delete" /> </a></span>    
						</div>
						</div>
					</td>
				</tr>
				<tr>
					<!-- 쪽지 내용 입력 -->
					<th><img src="<c:url value='/images/egovframework/com/uss/ion/ntm/check.png'/>" alt="check"/> <spring:message code="comUssIonNtm.NoteMange.content"/></th>
					<td>
						<!-- 쪽지내용 -->
						<c:set var="contents"><spring:message code="comUssIonNtm.NoteMange.content"/></c:set>	
						<form:textarea path="noteCn" name="noteCn" id="noteCn" title="${contents}" cols="85" rows="20" style="height:200px;"/>
					</td>
				</tr>
		</tbody>
</table>

<!-- 수신자목록리스트 -->
<input type="hidden" name="recptnEmpList" id="recptnEmpList" value="">
<!-- 수신자구분리스트 -->
<input type="hidden" name="recptnSeList" id="recptnSeList" value="">


<div class="btnNote">
	<input type="submit" class="s_submit" onClick="fn_egov_save_NoteManage(); return false;" value="<spring:message code="comUssIonNtm.NoteMange.send" />" title="<spring:message code="comUssIonNtm.NoteMange.send" /> <spring:message code="input.button" />" />
</div><div style="clear:both;"></div>

</div>
</form:form>
</body>
</html>