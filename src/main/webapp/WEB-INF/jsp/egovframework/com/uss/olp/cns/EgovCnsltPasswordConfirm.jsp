<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovCnsltPasswordConfirm.jsp
  * @Description : 작성비밀번호 확인 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.18   장동한              표준프레임워크 v3.6 개선
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlpCns.passwordConfirm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<base target="_self" >
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_cnsltdtls(){

	// 작성글 비밀번호 입력란에 포커스..
	document.CnsltManageForm.writngPassword.focus();

}

/* ********************************************************
 * 확인처리
 ******************************************************** */
function fn_egov_confirm_qnapassword(){
	
	getDialogArguments();
	/* var opener = window.dialogArguments; */
	var opener;
 
	if (window.dialogArguments) {
	    opener = window.dialogArguments; // Support IE
	} else {
	    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
	}

	//  현재화면의 작성 비밀번호
	var	ls_writngPassword = document.CnsltManageForm.writngPassword.value;
	
	// 부모화면으로 작성비밀번호를 넘긴다.
	opener.document.CnsltManageForm.writngPassword.value = ls_writngPassword;	
	

	// 부모화면으로 결과값을 True 넘긴다.
	window.returnValue = true;	
	setReturnValue(true);
	window.close();	

		
}

/* ********************************************************
 * 취소처리
 ******************************************************** */
function fn_egov_cancel_qnapassword() {
	
	self.close();
			
}

</script>

</head>
<body onLoad="fn_egov_initl_cnsltdtls();">

<form name="CnsltManageForm"  method="post" onsubmit="fn_egov_confirm_qnapassword();" action="">
<DIV id="content" style="width:270px" class="popup">

	<!-- 타이틀 -->
	<h1>${pageTitle} <spring:message code="title.create" /></h1>

<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: ;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="pop_search_box"><spring:message code="input.input" /></c:set>
		<!-- 작성글 비밀번호 -->
		<c:set var="title"><spring:message code="comUssOlpCns.passwordConfirm.password"/></c:set>
		<tr>
			<th><label for="useStplatNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			   <input name="writngPassword" type="password" size="20" value="" maxlength="20" title="${title} ${inputTxt}">
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<button class="btn_style3" onClick="fn_egov_confirm_qnapassword();return false;" title="<spring:message code="button.confirm" /> <spring:message code="input.button" />"><spring:message code="button.confirm" /></button>
		<button class="btn_style3" onClick="fn_egov_cancel_qnapassword();return false;" title="<spring:message code="button.reset" /> <spring:message code="input.button" />"><spring:message code="button.reset" /></button>
	</div><div style="clear:both;"></div>
	

</DIV>
</form>

</body>
</html>
