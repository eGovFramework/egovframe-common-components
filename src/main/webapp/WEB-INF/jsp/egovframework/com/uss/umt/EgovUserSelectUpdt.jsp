<%
 /**
  * @Class Name : EgovUserSelectUpdt.jsp
  * @Description : 사용자상세조회, 수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  * @ 2015.06.16	조정국		  password 중복필드 정리
  * @ 2016.07.26    장동한          표준프레임워크 v3.6 개선
  * @ 2017.07.21  장동한 			로그인인증제한 작업
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.02
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
<c:set var="pageTitle"><spring:message code="comUssUmt.deptUserManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>

<script type="text/javaScript" language="javascript" defer="defer">
function fnListPage(){
    document.userManageVO.action = "<c:url value='/uss/umt/EgovUserManage.do'/>";
    document.userManageVO.submit();
}
function fnDeleteUser(checkedIds) {
	if(confirm("<spring:message code="common.delete.msg" />")){
	    document.userManageVO.checkedIdForDel.value=checkedIds;
	    document.userManageVO.action = "<c:url value='/uss/umt/EgovUserDelete.do'/>";
	    document.userManageVO.submit();
	}
}
function fnPasswordMove(){
	document.userManageVO.action = "<c:url value='/uss/umt/EgovUserPasswordUpdtView.do'/>";
    document.userManageVO.submit();
}


function fnLockIncorrect(){
	if(confirm("<spring:message code="comUssUmt.common.lockAtConfirm" />")){
	    document.userManageVO.action = "<c:url value='/uss/umt/EgovUserLockIncorrect.do'/>";
	    document.userManageVO.selectedId.value=document.userManageVO.uniqId.value;
	    document.userManageVO.submit();
	}
}

function fnUpdate(form){
	if(confirm("<spring:message code="common.save.msg" />")){
	    if(validateUserManageVO(form)){
	    	form.submit();
	        return true;
	    }else{
	    	return false;
	    }
	}
}
function fn_egov_inqire_cert() {
	var url = "<c:url value='/uat/uia/EgovGpkiRegist.do' />";
	var popupwidth = '500';
	var popupheight = '400';
	var title = '인증서';

	Top = (window.screen.height - popupheight) / 3;
	Left = (window.screen.width - popupwidth) / 2;
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,	scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	PopUpWindow = window.open(url, title, Future)
	PopUpWindow.focus();
}

function fn_egov_dn_info_setting(dn) {
	var frm = document.userManageVO;

	frm.subDn.value = dn;
}

</script>
</head>
<body>
<!-- content start -->
<form:form commandName="userManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovUserSelectUpdt.do" name="userManageVO" method="post" onSubmit="fnUpdate(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
	<input name="checkedIdForDel" type="hidden" />
	<!-- 검색조건 유지 -->
	<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
	<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
	<!-- 우편번호검색 -->
	<input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
	<!-- 사용자유형정보 : password 수정화면으로 이동시 타겟 유형정보 확인용, 만약검색조건으로 유형이 포함될경우 혼란을 피하기위해 userTy명칭을 쓰지 않음-->
	<input type="hidden" name="userTyForPassword" value="<c:out value='${userManageVO.userTy}'/>" />
	<!-- for validation -->
	<input type="hidden" name="password" id="password" value="Test#$123)"/>
	<input type="hidden" name="selectedId" id="selectedId" value=""/>  
	
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<!-- 일반회원아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.id"/></c:set>
		<tr>
			<th><label for="emplyrId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="emplyrId" id="emplyrId" cssClass="txaIpUmt" size="20" maxlength="20" readonly="true" title="${title} ${inputTxt}" />
                    <form:errors path="emplyrId" cssClass="error"/>
                    <form:hidden path="uniqId" />
			</td>
		</tr>
		<!-- 일반회원이름 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.name"/></c:set>
		<tr>
			<th><label for="emplyrNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="emplyrNm" title="${title} ${inputTxt}" size="50" maxlength="60" />
				<div><form:errors path="emplyrNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 비밀번호힌트 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passHit"/></c:set>
		<tr>
			<th><label for="passwordHint">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="passwordHint" id="passwordHint" title="${title} ${inputSelect}">
					<form:option value="" label="${inputSelect}"/>
					<form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="passwordHint" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 비밀번호정답 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passOk"/></c:set>
		<tr>
			<th><label for="passwordCnsr">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="passwordCnsr" id="passwordCnsr" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="100" />
				<div><form:errors path="passwordCnsr" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 소속기관코드 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.insttCode"/></c:set>
		<tr>
			<th><label for="insttCode">${title}</label></th>
			<td class="left">
                    <form:select path="insttCode" id="insttCode" title="${title} ${inputSelect}">
                       <form:option value="" label="${inputSelect}"/>
                       <form:options items="${insttCode_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="insttCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 조직아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.orgnztId"/></c:set>
		<tr>
			<th><label for="orgnztId">${title}</label></th>
			<td class="left">
                  <form:select path="orgnztId" id="orgnztId" title="${title} ${inputSelect}">
                       <form:option value="" label="${inputSelect}"/>
                       <form:options items="${orgnztId_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="orgnztId" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 직위 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.ofcps"/></c:set>
		<tr>
			<th><label for="">${title}</label></th>
			<td class="left">
                    <form:input path="ofcpsNm" id="ofcpsNm" title="${title} ${inputTxt}" size="20" maxlength="50" />
                    <div><form:errors path="ofcpsNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사번 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.emplNum"/></c:set>
		<tr>
			<th><label for="emplNo">${title}</label></th>
			<td class="left">
                    <form:input path="emplNo" id="emplNo" title="${title} ${inputTxt}" size="20" maxlength="20" />
                    <div><form:errors path="emplNo" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 성별구분코드 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.saxTypeCode"/></c:set>
		<tr>
			<th><label for="sexdstnCode">${title}</label></th>
			<td class="left">
				<form:select path="sexdstnCode" id="sexdstnCode" title="${title} ${inputSelect}">
					<form:option value="" label="${inputSelect}"/>
					<form:options items="${sexdstnCode_result}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
			</td>
		</tr>
		<!-- 생일 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.brth"/></c:set>
		<tr>
			<th><label for="brth">${title}</label></th>
			<td class="left">
				<form:input path="brth" id="brth"  title="${title} ${inputTxt}" size="20" maxlength="8" />
                <div><form:errors path="brth" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 집전화번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.tel"/></c:set>
		<tr>
			<th><label for="areaNo">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="areaNo" id="areaNo" title="${title} ${inputSelect}" size="5" maxlength="5" style="width:40px;"/>
                    - <form:input path="homemiddleTelno" id="homemiddleTelno" size="5" maxlength="5" style="width:40px;"/>
                    - <form:input path="homeendTelno" id="homeendTelno"  size="5" maxlength="5" style="width:40px;"/>
                    <div><form:errors path="areaNo" cssClass="error" /></div>
                    <div><form:errors path="homemiddleTelno" cssClass="error" /></div>
                    <div><form:errors path="homeendTelno" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사무실전화번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.areaNo"/></c:set>
		<tr>
			<th><label for="offmTelno">${title}</label></th>
			<td class="left">
                    <form:input path="offmTelno" id="offmTelno" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="15" />
                    <div><form:errors path="offmTelno" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 팩스번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.offmTelno"/></c:set>
		<tr>
			<th><label for="fxnum">${title}</label></th>
			<td class="left">
                    <form:input path="fxnum" id="fxnum" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="15" />
                    <div><form:errors path="fxnum" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 헨드폰번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.phone"/></c:set>
		<tr>
			<th><label for="moblphonNo">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="moblphonNo" id="moblphonNo" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20" maxlength="15" />
                    <div><form:errors path="moblphonNo" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 이메일주소 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.email"/></c:set>
		<tr>
			<th><label for="emailAdres">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="emailAdres" id="mberEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" />
                    <div><form:errors path="emailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 우편번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.post"/></c:set>
		<tr>
			<th><label for="zip">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <input name="zip" id="zip" title="${title} ${inputTxt}" type="text" size="20" value="<c:out value='${userManageVO.zip}'/>" maxlength="8" style="width:60px;" />
                    <!-- form:hidden path="zip" id="zip" --> 
                    <!-- <button class="btn_s2" onClick="fn_egov_ZipSearch(document.mberManageVO, document.mberManageVO.zip, document.mberManageVO.zip_view, document.mberManageVO.adres);return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">우번번호검색</button>  -->
                    <div><form:errors path="zip" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 주소 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.addr"/></c:set>
		<tr>
			<th><label for="">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="homeadres" id="homeadres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" />
                    <div><form:errors path="homeadres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 상세주소 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.addrDetail"/></c:set>
		<tr>
			<th><label for="detailAdres">${title}</label></th>
			<td class="left">
                    <form:input path="detailAdres" id="detailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" />
                    <div><form:errors path="detailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 그룹아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.groupId"/></c:set>
		<tr>
			<th><label for="groupId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:select path="groupId" id="groupId" title="${title} ${inputSelect}">
                        <form:option value="" label="${inputSelect}"/>
                        <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="groupId" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 일반회원상태코드 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.status"/></c:set>
		<tr>
			<th><label for="emplyrSttusCode">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:select path="emplyrSttusCode" id="emplyrSttusCode" title="${title} ${inputSelect}">
                        <form:option value="" label="${inputSelect}"/>
                        <form:options items="${emplyrSttusCode_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="emplyrSttusCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 로그인인증제한여부 -->
		<c:set var="title"><spring:message code="comUssUmt.common.lockAt"/></c:set>
		<tr>
			<th><label for="lockAt">${title}</label></th>
			<td class="left">
			<c:if test="${userManageVO.lockAt eq 'Y'}">예</c:if>
			<c:if test="${userManageVO.lockAt == null || userManageVO.lockAt eq '' || userManageVO.lockAt eq 'N'}">아니오</c:if>
			</td>
		</tr>
		
		<!-- 사용자DN -->
		<!-- 
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.subDn"/></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="subDn" id="subDn" title="${title} ${inputTxt}" size="40" maxlength="400" style="width:80%;" />
                    <button id="btnSubdn" class="btn_s2" onClick="fn_egov_inqire_cert(); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.deptUserManageRegistBtn.Search" /></button>
                    <div><form:errors path="subDn" cssClass="error" /></div>
			</td>
		</tr>
		 -->
		 <form:hidden path="subDn" />
	</tbody>
	</table>			


	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" />
		<button class="btn_s2" onClick="fnDeleteUser('<c:out value='${mberManageVO.userTy}'/>:<c:out value='${mberManageVO.uniqId}'/>'); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button>
		<span class="btn_s"><a href="<c:url value='/uss/umt/EgovUserManage.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<button class="btn_s2" onClick="fnPasswordMove(); return false;" title="<spring:message code="comUssUmt.userManageModifyBtn.passwordChange" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.userManageModifyBtn.passwordChange" /></button>
		<button class="btn_s2" onClick="fnLockIncorrect(); return false;" title="<spring:message code="comUssUmt.common.lockAtBtn" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.common.lockAtBtn" /></button>
		<button class="btn_s2" onClick="document.userManageVO.reset(); return false;" title="<spring:message code="button.reset" /> <spring:message code="input.button" />"><spring:message code="button.reset" /></button>
	</div><div style="clear:both;"></div>

</div>
</form:form>
<!-- content end -->

</body>
</html>
