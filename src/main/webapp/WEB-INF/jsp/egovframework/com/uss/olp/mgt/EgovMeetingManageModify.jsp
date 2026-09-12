<%--
  Class Name : EgovMeetingManageModify.jsp
  Description : 회의정보 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09   장동한          최초 생성
	 2011.09.15     서준식           회의 시작, 종료 시간 Validation 추가
	 2018.09.13     이정은           공통컴포넌트 3.8 개선
	 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussOlpMgt.meetingManageModify.meetingManageModify"/></title><!-- 회의관리 수정 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/uss/olp/mgt/EgovUtilMeetingManage.js' />"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_MeetingManage(){


}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_MeetingManage(){
	location.href = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_MeetingManage(){

	var mtgBeginHH = eval(document.getElementById("mtgBeginHH").value);
	var mtgBeginMM = eval(document.getElementById("mtgBeginMM").value);
	var mtgEndHH = eval(document.getElementById("mtgEndHH").value);
	var mtgEndMM = eval(document.getElementById("mtgEndMM").value);

	if(mtgBeginHH > mtgEndHH){
		alert("<spring:message code="ussOlpMgt.meetingManageModify.validate.checkTime"/>");/* 회의 시작시간이 종료 시간보다 늦습니다. */
		return;
	}else if(mtgBeginHH == mtgEndHH){
		if(mtgBeginMM > mtgEndMM){
			alert("<spring:message code="ussOlpMgt.meetingManageModify.validate.checkTime"/>");/* 회의 시작시간이 종료 시간보다 늦습니다. */
			return;
		}
	}


	var varFrom = document.getElementById("meetingManageVO");
	if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
		varFrom.action =  "<c:url value='/uss/olp/mgt/EgovMeetingManageModify.do' />";

		if(!validateMeetingManageVO(varFrom)){
			return;
		}else{

			if(varFrom.nonatdrnCo.value == ""){varFrom.nonatdrnCo.value = "0"};
			if(varFrom.atdrnCo.value == ""){varFrom.atdrnCo.value = "0"};

			varFrom.mtgBeginTime.value = fn_egov_SelectBoxValue('mtgBeginHH') + ":" + fn_egov_SelectBoxValue('mtgBeginMM');
			varFrom.mtgEndTime.value = fn_egov_SelectBoxValue('mtgEndHH') + ":" + fn_egov_SelectBoxValue('mtgEndMM');
			varFrom.submit();
		}
	}
}
/* ********************************************************
 * 주관자 부서 팝업창열기
 ******************************************************** */
function fn_egov_mnaerDept_MeetingManage(){

	var arrParam = new Array(1);
	arrParam[0] = self;
	arrParam[1] = "typeMeeting1";

 	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />", arrParam ,"dialogWidth:800px;dialogHeight:500px;resizable:yes;cente:yes");
 }
/* ********************************************************
 * 주관 부서 팝업창열기
 ******************************************************** */
function fn_egov_mngtDeptNm_MeetingManage(){

	var arrParam = new Array(1);
	arrParam[0] = self;
	arrParam[1] = "typeMeeting2";

 	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />", arrParam ,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
 }

/* ********************************************************
 * 아이디  팝업창열기
 ******************************************************** */
function fn_egov_mnaer_MeetingManage(){
	var arrParam = new Array(1);
	arrParam[0] = window;
	arrParam[1] = "typeMeeting";

  	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do' />", arrParam,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
}
/* ********************************************************
 * 달력
 ******************************************************** */
function fn_egov_init_date(){
	
	$("#mtgDe").datepicker(  
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


	$("#readngBeginDe").datepicker( 
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
</script>
</head>
<body onLoad="fn_egov_init_MeetingManage(); fn_egov_init_date();">

<form:form modelAttribute="meetingManageVO"  action="" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussOlpMgt.meetingManageModify.meetingManageModify"/></h2><!-- 회의관리 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgNm"/> <span class="pilsu">*</span></th><!-- 회의제목 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgNm" var="fieldTitle" />
				<form:input path="mtgNm" maxlength="100" title="${fieldTitle}" htmlEscape="true" />
				<div><form:errors path="mtgNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgMtrCn"/> <span class="pilsu">*</span></th><!-- 회의 안건 내용 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgMtrCn" var="fieldTitle" />
				<form:textarea path="mtgMtrCn" class="textarea"  cols="75" rows="4"  style="width:99%;" title="${fieldTitle}" htmlEscape="true" />
				<div><form:errors path="mtgMtrCn" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgSn"/> <span class="pilsu">*</span></th><!-- 회의순서 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgSn" var="fieldTitle" />
				<form:input path="mtgSn" size="73" maxlength="10" style="width:60px;" title="${fieldTitle}" htmlEscape="true" />
				<div><form:errors path="mtgSn" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgCo"/> <span class="pilsu">*</span></th><!-- 회의회차 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgCo" var="fieldTitle" />
				<form:input path="mtgCo" size="73" maxlength="5" style="width:60px;" title="${fieldTitle}" htmlEscape="true" />
				<div><form:errors path="mtgCo" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgDe"/> <span class="pilsu">*</span></th><!-- 회의일자 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgDe" var="fieldTitle" />
				<form:input id="mtgDe" path="mtgDe" maxlength="10" title="${fieldTitle}" style="width:80px;" htmlEscape="true" />
				<div><form:errors path="mtgDe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgPlace"/> <span class="pilsu">*</span></th><!-- 회의장소 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgPlace" var="fieldTitle" />
				<form:input path="mtgPlace" maxlength="70" title="${fieldTitle}" style="width:200px;" htmlEscape="true" />
				<div><form:errors path="mtgPlace" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgBeginTime"/> <span class="pilsu">*</span></th><!-- 회의시작시간 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgHH" var="fieldTitle" />
				<form:select path="mtgBeginHH" id="mtgBeginHH" title="${fieldTitle}">
					<c:forEach var="value" begin="1" end="24">
						<form:option value="${value}">${value}<spring:message code="ussOlpMgt.meetingManageModify.mtgHH"/></form:option>
					</c:forEach>
				</form:select>
				<spring:message code="ussOlpMgt.meetingManageModify.mtgMM" var="fieldTitle" />
				<form:select path="mtgBeginMM" id="mtgBeginMM" title="${fieldTitle}">
					<c:forEach var="value" begin="0" end="60">
						<form:option value="${value}">${value}<spring:message code="ussOlpMgt.meetingManageModify.mtgMM"/></form:option>
					</c:forEach>
				</form:select>
				<form:hidden path="mtgBeginTime" id="mtgBeginTime" htmlEscape="true" />
				<div><form:errors path="mtgBeginHH" cssClass="error" /></div>
				<div><form:errors path="mtgBeginMM" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgEndTime"/> <span class="pilsu">*</span></th><!-- 회의종료시간 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgHH" var="fieldTitle" />
				<form:select path="mtgEndHH" id="mtgEndHH" title="${fieldTitle}">
					<c:forEach var="value" begin="1" end="24">
						<form:option value="${value}">${value}<spring:message code="ussOlpMgt.meetingManageModify.mtgHH"/></form:option>
					</c:forEach>
				</form:select>
				<spring:message code="ussOlpMgt.meetingManageModify.mtgMM" var="fieldTitle" />
				<form:select path="mtgEndMM" id="mtgEndMM" title="${fieldTitle}">
					<c:forEach var="value" begin="0" end="60">
						<form:option value="${value}">${value}<spring:message code="ussOlpMgt.meetingManageModify.mtgMM"/></form:option>
					</c:forEach>
				</form:select>
				
				<form:hidden path="mtgEndTime" id="mtgEndTime" htmlEscape="true" />
				<div><form:errors path="mtgEndHH" cssClass="error" /></div>
				<div><form:errors path="mtgEndMM" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.clsdrMtgAt"/> <span class="pilsu">*</span></th><!-- 비공개 회의여부 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.clsdrMtgAt" var="fieldTitle" />
				<input type="checkbox" name="clsdrMtgAt" value="1" title="${fn:escapeXml(fieldTitle)}" <c:if test="${meetingManageVO.clsdrMtgAt == '1'}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.readngBeginDe"/> <span class="pilsu">*</span></th><!-- 열람 개시일 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.readngBeginDe" var="fieldTitle" />
				<form:input id="readngBeginDe" path="readngBeginDe" maxlength="10" title="${fieldTitle}" style="width:80px;" htmlEscape="true" />
				<div><form:errors path="readngBeginDe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.readngAt"/> <span class="pilsu">*</span></th><!-- 열람 여부 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.readngAt" var="fieldTitle" />
				<form:select path="readngAt" title="${fieldTitle}">
					<form:option value="N">N</form:option>
					<form:option value="Y">Y</form:option>
				</form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgResultCn"/></th><!-- 회의결과 내용 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgResultCn" var="fieldTitle" />
				<form:textarea path="mtgResultCn" cols="75" rows="4"  title="${fieldTitle}" htmlEscape="true" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtgResultEnnc"/></th><!-- 회의결과 여부 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtgResultEnnc" var="fieldTitle" />
				<input type="checkbox" name="mtgResultEnnc" value="1" title="${fn:escapeXml(fieldTitle)}" <c:if test="${meetingManageVO.mtgResultEnnc == '1'}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.etcMatter"/></th><!-- 기타 사항 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.etcMatter" var="fieldTitle" />
				<form:textarea path="etcMatter" cols="75" rows="2" title="${fieldTitle}" htmlEscape="true" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mngtDeptNm"/></th><!-- 주관부서 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mngtDeptNm" var="fieldTitle" />
				<form:input id="mngtDeptNm" path="mngtDeptNm" maxlength="2000" title="${fieldTitle}" readonly="readonly" style="width:100px;" htmlEscape="true" />
				<a href="javascript:void(0);" onclick="fn_egov_mngtDeptNm_MeetingManage();return false">
					<img alt="<spring:message code="ussOlpMgt.meetingManageModify.mngtDeptNm"/>" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="<spring:message code="ussOlpMgt.meetingManageModify.mngtDeptNm"/>" />
				</a>
				<form:hidden id="mngtDeptId" path="mngtDeptId" htmlEscape="true" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mnaerNm"/></th><!-- 주관자명 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mnaerNm" var="fieldTitle" />
				<form:input id="mnaerNm" path="mnaerNm" title="${fieldTitle}" maxlength="2000" readonly="readonly" style="width:100px;" htmlEscape="true" />
				<a href="javascript:void(0);" onclick="fn_egov_mnaer_MeetingManage();return false">
					<img alt="주관자ID 찾기버튼" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="주관자ID 찾기 버튼" />
				</a>
				<form:hidden id="mnaerId" path="mnaerId" htmlEscape="true" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mnaerDeptNm"/></th><!-- 주관자부서 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mnaerDeptNm" var="fieldTitle" />
				<form:input id="mnaerDeptNm" path="mnaerDeptNm" title="${fieldTitle}" maxlength="2000" style="width:100px;" readonly="readonly" htmlEscape="true" />
				<a href="javascript:void(0);" onclick="fn_egov_mnaerDept_MeetingManage();return false">
					<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" align="middle" style="border:0px" alt="주관자부서 찾기버튼" title="주관자부서 찾기 버튼">
				</a>
				<form:hidden path="mnaerDeptId"  id="mnaerDeptId" htmlEscape="true" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.mtnAt"/></th><!-- 회의여부 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.mtnAt" var="fieldTitle" />
				<form:select path="mtnAt" title="${fieldTitle}">
					<form:option value="Y">Y</form:option>
					<form:option value="N">N</form:option>
				</form:select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.nonatdrnCo"/></th><!-- 불참석자수 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.nonatdrnCo" var="fieldTitle" />
				<form:input id="nonatdrnCo" path="nonatdrnCo" title="${fieldTitle}" maxlength="10" style="width:60px;" htmlEscape="true" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageModify.atdrnCo"/></th><!-- 참석자수 -->
			<td class="left">
				<spring:message code="ussOlpMgt.meetingManageModify.atdrnCo" var="fieldTitle" />
				<form:input id="atdrnCo" path="atdrnCo" size="73" maxlength="10" title="${fieldTitle}" style="width:60px;" htmlEscape="true" />
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.create"/>" onclick="fn_egov_save_MeetingManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/olp/mgt/EgovMeetingManageList.do' />" onclick=""><spring:message code="button.list"/></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<form:hidden path="mtgId" htmlEscape="true" />
<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>
</DIV>

</body>
</html>
