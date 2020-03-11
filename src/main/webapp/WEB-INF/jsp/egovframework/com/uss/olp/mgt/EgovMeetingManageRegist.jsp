<%--
  Class Name : EgovMeetingManageRegist.jsp
  Description : 회의정보 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
    2008.03.09   장동한            최초 생성
	2011.09.15     서준식             회의 시작, 종료 시간 Validation 추가
	2018.09.13     이정은             공통컴포넌트 3.8 개선
	
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
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussOlpMgt.meetingManageRegist.meetingManageRegist"/></title><!-- 회의관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="meetingManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
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
		alert("<spring:message code="ussOlpMgt.meetingManageRegist.validate.checkTime"/>");/* 회의 시작시간이 종료 시간보다 늦습니다. */
		return;
	}else if(mtgBeginHH == mtgEndHH){
		if(mtgBeginMM > mtgEndMM){
			alert("<spring:message code="ussOlpMgt.meetingManageRegist.validate.checkTime"/>");/* 회의 시작시간이 종료 시간보다 늦습니다. */
			return;
		}
	}

	var varFrom = document.getElementById("meetingManageVO");

	if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
		varFrom.action =  "<c:url value='/uss/olp/mgt/EgovMeetingManageRegist.do' />";

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
 * 주관자 부서 팝업창열기
 ******************************************************** */
function fn_egov_mnaerDept_MeetingManage(){

	var arrParam = new Array(1);
	arrParam[0] = self;
	arrParam[1] = "typeMeeting1";

 	window.showModalDialog("<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />", arrParam ,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
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
<DIV id="content" style="width:712px">
<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<!-- 상단타이틀 -->
<form:form commandName="meetingManageVO"  action="" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussOlpMgt.meetingManageRegist.meetingManageRegist"/></h2><!-- 회의관리 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgNm"/> <span class="pilsu">*</span></th><!-- 회의제목 -->
			<td class="left">
			    <input name="mtgNm" type="text" value="${resultList[0].mtgNm}" size="73" maxlength="250" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgNm"/>" /><!-- 회의제목 -->
				<div><form:errors path="mtgNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgMtrCn"/> <span class="pilsu">*</span></th><!-- 회의안건 내용 -->
			<td class="left">
			    <textarea class="textarea" name="mtgMtrCn" cols="75" rows="4" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgMtrCn"/>"></textarea><!-- 회의안건 내용 -->
				<div><form:errors path="mtgMtrCn" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgSn"/> <span class="pilsu">*</span></th><!-- 회의순서 -->
			<td class="left">
			    <input type="text" name="mtgSn" value="" maxlength="10" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgSn"/>" style="width:60px;" /><!-- 회의순서 -->
				<div><form:errors path="mtgSn" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgCo"/> <span class="pilsu">*</span></th><!-- 회의회차 -->
			<td class="left">
			    <input type="text" name="mtgCo" value="" maxlength="5" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgCo"/>" style="width:60px;" /><!-- 회의회차 -->
				<div><form:errors path="mtgCo" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgDe"/> <span class="pilsu">*</span></th><!-- 회의일자 -->
			<td class="left">
			    <input id="mtgDe" type="text" name="mtgDe" value="" maxlength="10" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgDe"/>" readonly="readonly" style="width:80px;" /><!-- 회의일자 -->
				<div><form:errors path="mtgDe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgPlace"/> <span class="pilsu">*</span></th><!-- 회의장소 -->
			<td class="left">
			    <input type="text" name="mtgPlace" value="" maxlength="250" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgPlace"/>" style="width:200px;" /><!-- 회의장소 -->
				<div><form:errors path="mtgPlace" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgBeginTime"/> <span class="pilsu">*</span></th><!-- 회의시작시간 -->
			<td class="left">
				<select name="mtgBeginHH" id="mtgBeginHH" title="시 선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<c:forEach var="h" begin="1" end="24" step="1">
					<option value="${h}">${h}시</option>
					</c:forEach>
				</select>
				
				<select name="mtgBeginMM" id="mtgBeginMM" title="분 선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<option value="0">0분</option>
					<c:forEach var="m" begin="1" end="60" step="1">
					<option value="${m}">${m}분</option>
					</c:forEach>
				</select>
				
				<input id="mtgBeginTime" name="mtgBeginTime" type="hidden" value="" />
				<div><form:errors path="mtgBeginHH" cssClass="error" /></div>
				<div><form:errors path="mtgBeginMM" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgEndTime"/> <span class="pilsu">*</span></th><!-- 회의종료시간 -->
			<td class="left">
			    <select name="mtgEndHH" id="mtgEndHH" title="시 선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<c:forEach var="h" begin="1" end="24" step="1">
					<option value="${h}">${h}시</option>
					</c:forEach>
				</select>
				
				<select name="mtgEndMM" id="mtgEndMM" title="분 선택">
					<option value=""><spring:message code="input.cSelect"/></option>
					<option value="0">0분</option>
					<c:forEach var="m" begin="1" end="60" step="1">
					<option value="${m}">${m}분</option>
					</c:forEach>
				</select>
				<input name="mtgEndTime" id="mtgEndTime" type="hidden" value="">
				<div><form:errors path="mtgEndHH" cssClass="error" /></div>
				<div><form:errors path="mtgEndMM" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.clsdrMtgAt"/> <span class="pilsu">*</span></th><!-- 비공개 회의여부 -->
			<td class="left">
			    <input type="checkbox" name="clsdrMtgAt" value="1" title="<spring:message code="ussOlpMgt.meetingManageRegist.clsdrMtgAt"/>" checked="checked" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.readngBeginDe"/> <span class="pilsu">*</span></th><!-- 열람 개시일 -->
			<td class="left">
			    <input id="readngBeginDe" type="text" name="readngBeginDe" value="" maxlength="10" title="<spring:message code="ussOlpMgt.meetingManageRegist.readngBeginDe"/>" readonly="readonly" style="width:80px;" />
				<div><form:errors path="readngBeginDe" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.readngAt"/> <span class="pilsu">*</span></th><!-- 열람 여부 -->
			<td class="left">
			    <select name="readngAt" title="<spring:message code="ussOlpMgt.meetingManageRegist.readngAt"/>">
			      	<option value="N">N</option>
			      	<option value="Y">Y</option>
		     	</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgResultCn"/></th><!-- 회의결과 내용 -->
			<td class="left">
			    <textarea name="mtgResultCn" cols="75" rows="4" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgResultCn"/>"></textarea>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtgResultEnnc"/></th><!-- 회의결과 여부 -->
			<td class="left">
			    <input type="checkbox" name="mtgResultEnnc" value="1" title="<spring:message code="ussOlpMgt.meetingManageRegist.mtgResultEnnc"/>" checked="checked" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.etcMatter"/></th><!-- 기타 사항 -->
			<td class="left">
			    <textarea name="etcMatter" cols="75" rows="2" title="<spring:message code="ussOlpMgt.meetingManageRegist.etcMatter"/>"></textarea>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mngtDeptNm"/></th><!-- 주관부서 -->
			<td class="left">
				<input id="mngtDeptNm" type="text" name="mngtDeptNm" value="" maxlength="2000" title="<spring:message code="ussOlpMgt.meetingManageRegist.mngtDeptNm"/>" readonly="readonly" style="width:100px;" />
				<a href="<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />" target="_blank" title="팝업 새창으로" onclick="fn_egov_mngtDeptNm_MeetingManage();return false">
					<img alt="주관부서 찾기버튼" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="주관부서 찾기버튼" />
				</a>
				
				<input name="mngtDeptId" id="mngtDeptId" type="hidden" value="" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mnaerNm"/></th><!-- 주관자명 -->
			<td class="left">
				<input id="mnaerNm" type="text" name="mnaerNm" value="" title="<spring:message code="ussOlpMgt.meetingManageRegist.mnaerNm"/>" maxlength="2000" readonly="readonly" style="width:100px;" />
				<a href="<c:url value='/uss/olp/mgt/EgovMeetingManageLisEmpLyrPopup.do' />" target="_blank" title="주관자ID 선택  팝업 새창으로" onclick="fn_egov_mnaer_MeetingManage();return false">
					<img alt="주관자ID 찾기버튼" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="주관자ID 찾기 버튼" />
				</a>
				
				<input id="mnaerId" type="hidden" name="mnaerId" value="" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mnaerDeptNm"/></th><!-- 주관자부서 -->
			<td class="left">
				<input id="mnaerDeptNm" type="text" name="mnaerDeptNm" value="" title="<spring:message code="ussOlpMgt.meetingManageRegist.mnaerDeptNm"/>" maxlength="2000" readonly="readonly" style="width:100px;" />
				<a href="<c:url value='/uss/olp/mgt/EgovMeetingManageLisAuthorGroupPopup.do' />" target="_blank" title="주관자부서 선택  팝업 새창으로" onclick="fn_egov_mnaerDept_MeetingManage();return false">
					<img alt="주관자부서 찾기버튼" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="주관자부서 찾기 버튼" />
				</a>
				
				<input name="mnaerDeptId" id="mnaerDeptId" type="hidden" value="" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.mtnAt"/></th><!-- 회의여부 -->
			<td class="left">
				<select name="mtnAt" title="<spring:message code="input.cSelect"/>">
					<option value="N">N</option>
					<option value="Y">Y</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.nonatdrnCo"/></th><!-- 불참석자수 -->
			<td class="left">
			    <input id="nonatdrnCo" type="text" name="nonatdrnCo" value="" maxlength="10" title="<spring:message code="ussOlpMgt.meetingManageRegist.nonatdrnCo"/>" style="width:60px;" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageRegist.atdrnCo"/></th><!-- 참석자수 -->
			<td class="left">
			    <input id="atdrnCo" type="text" name="atdrnCo" value="" maxlength="10" title="<spring:message code="ussOlpMgt.meetingManageRegist.atdrnCo"/>" style="width:60px;" />
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

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>
</DIV>

</body>
</html>
