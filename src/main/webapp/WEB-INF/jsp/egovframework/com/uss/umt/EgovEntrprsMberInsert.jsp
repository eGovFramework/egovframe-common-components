<%
 /**
  * @Class Name : EgovEntrprsMberInsert.jsp
  * @Description : 기업회원등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  * @ 2016.06.13    장동한          표준프레임워크 v3.6 개선
  * @ 2020.07.20    윤주호          표준프레임워크 v3.10 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.12
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
<c:set var="pageTitle"><spring:message code="comUssUmt.entrprsUserManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 기업회원관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="entrprsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	//모달 셋팅
	fn_modal_setting();

}
/*********************************************************
 * 모달셋팅
 ******************************************************** */
function fn_modal_setting(){
	//버튼에 모달 연결
	$("#btnMbrId").egovModal( "egovModal" );
	
	//타이틀 설졍
	$("#egovModal").setEgovModalTitle("<spring:message code="comUssUmt.userManageRegistModal.title" />"); //아이디 중복 확인
	var content = "";
	content = content + "<div class='modal-alignL' style='margin:5px 0 0 0'>"+"<spring:message code="comUssUmt.userManageRegistModal.userIsId" /> :"+"</div>"; //사용할아이디
	content = content + "<div class='modal-alignL'>"+"<input type='text' id='checkIdModal' name='checkIdModal' value='' size='20' maxlength='20' />"+"</div>";	
	content += "<div style='clear:both;'></div>";
	content += "<div id='divModalResult' style='margin:10px 0 0 0'><spring:message code="comUssUmt.userManageRegistModal.initStatus" /></div>"; //결과 : 중복확인을 실행하십시오.
	//모달 body 설정
	$("#egovModal").setEgovModalBody(content);

	var footer = "";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalOk' onclick='fn_id_checkOk()'>확인</button></div>";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalSelect' onclick='fn_id_check()'>조회</button></div>";
	
	footer += "<span class='btn_style1 blue' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'><spring:message code="button.confirm" /></a></span>&nbsp;";//확인
	footer += "<span class='btn_style1 blue' id='btnModalSelect' onclick='fn_id_check()'><a href='#'><spring:message code="button.inquire" /></a></span>&nbsp;";//조회

	//모달 footer 설정
	$("#egovModal").setEgovModalfooter(footer);
	
	//엔터이벤트처리
	$("input[name=checkIdModal]").keydown(function (key) {
		if(key.keyCode == 13){
			fn_id_check();	
		}
	});
	
	footer = null;
	content = null;
}
/*********************************************************
 * 아이디 체크 AJAX
 ******************************************************** */
function fn_id_check(){	
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					//사용할수 없는 아이디입니다.
					$("#divModalResult").html("<font color='red'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.useMsg" /></font>");
				}else{
					//사용가능한 아이디입니다.
					$("#divModalResult").html("<font color='blue'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.notUseMsg" /></font>");
				}
			}else{ alert("ERROR!");return;} 
		}
		});
}

/*********************************************************
 * 아이디 체크 확인
 ******************************************************** */
function fn_id_checkOk(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					alert("<spring:message code="comUssUmt.userManageRegistModal.noIdMsg" />"); //사용이 불가능한 아이디 입니다.
					return;
				}else{

					$("input[name=entrprsmberId]").val(returnData.checkId);
					$("#egovModal").setEgovModalClose();

				}
			}else{ alert("ERROR!");return;} 
		}
		});
}


function fnIdCheck1(){
    var retVal;
    var url = "<c:url value='/uss/umt/EgovIdDplctCnfirmView.do'/>";
    var varParam = new Object();
    varParam.checkId = document.entrprsManageVO.entrprsmberId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.entrprsManageVO.entrprsmberId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.entrprsManageVO.entrprsmberId.value = retVal;
	}
}


function fnListPage(){
    document.entrprsManageVO.action = "<c:url value='/uss/umt/EgovEntrprsMberManage.do'/>";
    document.entrprsManageVO.submit();
}

function fnInsert(form){
	if(confirm("<spring:message code="common.regist.msg" />")){	
		if(validateEntrprsManageVO(form)){
			if(form.entrprsMberPassword.value != form.entrprsMberPassword2.value){
	            alert("<spring:message code="fail.user.passwordUpdate2" />");
	            return false;
	        }
			form.submit();
			return true;
	    }
	}
}
</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body onload="fn_egov_init()">

<!-- content start -->
<form:form commandName="entrprsManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovEntrprsMberInsert.do" name="entrprsManageVO" method="post" onSubmit="fnInsert(document.forms[0]); return false;"> 

<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
<input name="checkedIdForDel" type="hidden" />
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>

<!-- 우편번호검색 -->
<input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
		<!-- 기업회원아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.id"/></c:set>
		<tr>
			<th><label for="entrprsmberId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="entrprsmberId" id="entrprsmberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" style="width:80%;" />
				<button id="btnMbrId" class="btn_s2" onClick="return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.entrprsUserManageRegistBtn.idSearch" /></button>
				<div><form:errors path="entrprsmberId" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 회사명 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.name"/></c:set>
		<tr>
			<th><label for="cmpnyNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="cmpnyNm" title="${title} ${inputTxt}" size="50" maxlength="60" />
				<div><form:errors path="cmpnyNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 비밀번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.pass"/></c:set>
		<tr>
			<th><label for="entrprsMberPassword">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<div>
					<form:password path="entrprsMberPassword" title="${title} ${inputTxt}" size="50" maxlength="20" />
					<div><form:errors path="entrprsMberPassword" cssClass="error" /></div> 
				</div>
				<div>
					<div><spring:message code="info.password.rule.password1" /></div> 
					<div><spring:message code="info.password.rule.pwdcheckcomb3" /></div> 
					<div><spring:message code="info.password.rule.pwdcheckseries" /></div> 
				</div>
			</td>
		</tr>
		<!-- 비밀번호확인 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.passConfirm"/></c:set>
		<tr>
			<th><label for="entrprsMberPassword2">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			<input name="entrprsMberPassword2" id="entrprsMberPassword2" title="${title} ${inputTxt}" type="password" size="50" maxlength="20" />
			</td>
		</tr>
		<!-- 비밀번호힌트 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.passHit"/></c:set>
		<tr>
			<th><label for="entrprsMberPasswordHint">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="entrprsMberPasswordHint" id="entrprsMberPasswordHint" title="${title} ${inputSelect}">
					<form:option value="" label="--선택하세요--"/>
					<form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="entrprsMberPasswordHint" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 비밀번호정답 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.passOk"/></c:set>
		<tr>
			<th><label for="entrprsMberPasswordCnsr">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="entrprsMberPasswordCnsr" id="entrprsMberPasswordCnsr" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="100" />
				<div><form:errors path="entrprsMberPasswordCnsr" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 업종코드 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.indutyCode"/></c:set>
		<tr>
			<th><label for="indutyCode">${title}</label></th>
			<td class="left">
				<form:select path="indutyCode" id="indutyCode" title="${title} ${inputSelect}">
					<form:option value="" label="${inputSelect}"/>
					<form:options items="${indutyCode_result}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
			</td>
		</tr>
		<!-- 기업구분코드 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.entrprsSeCode"/></c:set>
		<tr>
			<th><label for="entrprsSeCode">${title}</label></th>
			<td class="left">
				<form:select path="entrprsSeCode" id="entrprsSeCode" title="${title} ${inputSelect}">
					<form:option value="" label="${inputSelect}"/>
					<form:options items="${entrprsSeCode_result}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
			</td>
		</tr>
		
		<!-- 대표이사이름 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.cxfc"/></c:set>
		<tr>
			<th><label for="cxfc">${title}</label></th>
			<td class="left">
                    <form:input path="cxfc" id="cxfc" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="15" />
                    <div><form:errors path="cxfc" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사업자등록번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.bizrno"/></c:set>
		<tr>
			<th><label for="bizrno">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="bizrno" id="bizrno" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="10" />
                    <div><form:errors path="bizrno" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 법인등록번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.jurirno"/></c:set>
		<tr>
			<th><label for="jurirno">${title}</label> </th>
			<td class="left">
                    <form:input path="jurirno" id="jurirno" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="15" />
                    <div><form:errors path="jurirno" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 회사전화번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.tel"/></c:set>
		<tr>
			<th><label for="areaNo">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="areaNo" id="areaNo" title="전화번호" size="5" maxlength="5" style="width:40px;"/>
                    - <form:input path="entrprsMiddleTelno" id="entrprsMiddleTelno" size="5" maxlength="5" style="width:40px;"/>
                    - <form:input path="entrprsEndTelno" id="entrprsEndTelno" size="5" maxlength="5" style="width:40px;"/>
                    <div><form:errors path="areaNo" cssClass="error" /></div>
                    <div><form:errors path="entrprsMiddleTelno" cssClass="error" /></div>
                    <div><form:errors path="entrprsEndTelno" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 팩스번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.fax"/></c:set>
		<tr>
			<th><label for="">${title}</label></th>
			<td class="left">
                    <form:input path="fxnum" id="fxnum" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="15" />
                    <div><form:errors path="fxnum" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 우번번호 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.post"/></c:set>
		<tr>
			<th><label for="zip">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="zip" title="${title} ${inputTxt}" type="text" size="20" value="" maxlength="8" style="width:60px;" />
                    <!-- form:hidden path="zip" id="zip" --> 
                    <!-- <button class="btn_s2" onClick="fn_egov_ZipSearch(document.mberManageVO, document.mberManageVO.zip, document.mberManageVO.zip_view, document.mberManageVO.adres);return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">우번번호검색</button>  -->
                    <div><form:errors path="zip" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 주소 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.addr"/></c:set>
		<tr>
			<th><label for="adres">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="adres" id="adres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" />
                    <div><form:errors path="adres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 상세주소 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.addrDetail"/></c:set>
		<tr>
			<th><label for="applcntNm">${title}</label></th>
			<td class="left">
                    <form:input path="detailAdres" id="detailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" />
                    <div><form:errors path="detailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 신청자이름 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageList.regName"/></c:set>
		<tr>
			<th><label for="">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="applcntNm" id="applcntNm" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="20" />
                    <div><form:errors path="applcntNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 신청자이메일주소 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageList.regMail"/></c:set>
		<tr>
			<th><label for="applcntEmailAdres">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="applcntEmailAdres" id="applcntEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" />
                    <div><form:errors path="applcntEmailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 그룹아이디 -->
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.groupId"/></c:set>
		<tr>
			<th><label for="groupId">${title}</label></th>
			<td class="left">
                    <form:select path="groupId" id="groupId" title="${title} ${inputSelect}">
                        <form:option value="" label="${inputSelect}"/>
                        <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="groupId" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 기업회원상태코드 -->
		<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.status"/></c:set>
		<tr>
			<th><label for="entrprsMberSttus">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:select path="entrprsMberSttus" id="entrprsMberSttus" title="${title} ${inputSelect}">
                        <form:option value="" label="${inputSelect}"/>
                        <form:options items="${entrprsMberSttus_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="entrprsMberSttus" cssClass="error"/></div>
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 --> 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/uss/umt/EgovEntrprsMberManage.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
	</div><div style="clear:both;"></div>
	
	
</div>
</form:form>

<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

</body>
</html>
