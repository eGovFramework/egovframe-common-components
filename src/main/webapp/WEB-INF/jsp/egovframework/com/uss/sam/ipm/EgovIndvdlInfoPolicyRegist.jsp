<%--
  Class Name : EgovIndvdlInfoPolicyRegist.jsp
  Description : 개인정보보호정책 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -----------    -------------    ---------------------------
     2008.03.09    장동한                      최초 생성
     2014.12.08    표준프레임워크          웹에디터(WYSIWYG) 적용
     2018.09.03    이정은                      공통컴포넌트 3.8 개선  

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
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<c:set var="ImgUrl" value="/images/egovframework/com/uss/sam/ipm/"/>
<c:set var="CssUrl" value="/css/egovframework/com/uss/sam/ipm/"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussSamIpm.indvdlInfoPolicyRegist.indvdlInfoPolicyRegist"/></title><!-- 개인정보보호정책 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<validator:javascript formName="indvdlInfoPolicy" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_IndvdlInfoPolicy(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_IndvdlInfoPolicy(){
	location.href = "<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_IndvdlInfoPolicy(){
	CKEDITOR.instances.indvdlInfoDc.updateElement();
	var varFrom = document.indvdlInfoPolicy;
	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/uss/sam/ipm/registIndvdlInfoPolicy.do' />";
		if(!validateIndvdlInfoPolicy(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	}
}


</script>
</head>
<body onLoad="fn_egov_init_IndvdlInfoPolicy();">

<form:form modelAttribute="indvdlInfoPolicy" name="indvdlInfoPolicy" action="${pageContext.request.contextPath}/uss/sam/ipm/registIndvdlInfoPolicy.do" method="post" enctype="multipart/form-data" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussSamIpm.indvdlInfoPolicyRegist.indvdlInfoPolicyRegist"/></h2><!-- 개인정보보호정책 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:25%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussSamIpm.indvdlInfoPolicyRegist.indvdlInfoNm"/> <span class="pilsu">*</span></th><!-- 개인정보보호정책 명 -->
			<td class="left">
			    <form:input path="indvdlInfoNm" size="73" cssClass="txaIpt" maxlength="255"/>
				<form:errors path="indvdlInfoNm" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussSamIpm.indvdlInfoPolicyRegist.indvdlInfoYn"/> <span class="pilsu">*</span></th><!-- 동의여부 -->
			<td class="left">
			    <select title="<spring:message code="ussSamIpm.indvdlInfoPolicyRegist.indvdlInfoYn"/>" name="indvdlInfoYn" id="indvdlInfoYn">
					<option value="Y"><spring:message code="input.yes"/></option><!-- 예 -->
					<option value="N"><spring:message code="input.no"/></option><!-- 아니오 -->
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussSamIpm.indvdlInfoPolicyRegist.indvdlInfoDc"/> <span class="pilsu">*</span></th><!-- 개인정보보호정책 내용 -->
			<td class="left">
			    <form:textarea path="indvdlInfoDc" rows="75" cols="14" cssClass="txaClass2"/>
				<form:errors path="indvdlInfoDc" cssClass="error"/>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_save_IndvdlInfoPolicy(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/sam/ipm/listIndvdlInfoPolicy.do' />"><spring:message code="button.list" /></a></span>
	</div>
</div>




<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

<ckeditor:replace replace="indvdlInfoDc" basePath="${pageContext.request.contextPath}/html/egovframework/com/cmm/utl/ckeditor/" />
</body>
</html>
