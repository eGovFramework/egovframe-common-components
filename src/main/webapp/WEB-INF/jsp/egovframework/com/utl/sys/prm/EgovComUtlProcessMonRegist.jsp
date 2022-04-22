<%
 /**
  * @Class Name  : EgovComUtlProcessMonRegist.jsp
  * @Description : EgovComUtlProcessMonRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2010.06.30  박종선                  최초 생성
  *
  *  @author 공통서비스팀 
  *  @since 2010.05.01
  *  @version 1.0
  *  @see
  *  
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUtlSysPrm.comUtlProcessMonRegist.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>${pageTitle}</title>
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="processMonVO" staticJavascript="false" xhtml="true" cdata="false"/>
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_ProcessMon(){
			// 첫 입력란에 포커스..
			processMonVO.processNm.focus();
		}			
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_ProcessMon(){
			location.href = "<c:url value='/utl/sys/prm/EgovComUtlProcessMonList.do'/>";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		function fn_egov_regist_ProcessMon(form){
			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateProcessMonVO(form)){ 			
					return;
				}else{
					form.submit();
				}
			}
		}
		/* ********************************************************
		 * '~`!@#$%%^&*-=+\|[{]};:\',<.>/?' 문자열은 제거한다.
		 ******************************************************** */
		function cleanQueryTerm(form) {
			var specialChars='~`!@#$%%^&*-=+\|[{]};:\',<>/?';
			var str=processMonVO.processNm.value;
			var i, j;
			if (str == '') {
			return false;
			}
			for (i = 0; i < str.length; i++) {
			for (j = 0; j < specialChars.length; j++) {
			if (str.charAt(i) == specialChars.charAt(j))
			str = str.replace(str.charAt(i), " ");
			}
			}
			processMonVO.processNm.value = str;
		}
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_ProcessMon();">
	
	<form:form modelAttribute="processMonVO" name="processMonVO" method="post">
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2>${pageTitle}</h2>
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comUtlSysPrm.comUtlProcessMon.processName" /> <span class="pilsu">*</span></th><!-- 프로세스명  -->
				<td class="left">
				    <form:input  onkeyup="cleanQueryTerm()" path="processNm" size="30" maxlength="30"/>
					<form:errors path="processNm"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comUtlSysPrm.comUtlProcessMon.managerName" /> <span class="pilsu">*</span></th><!-- 관리자명 -->
				<td class="left">
				    <form:input  path="mngrNm" maxlength="60" cssStyle="width:128px"/>
					<form:errors path="mngrNm"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comUtlSysPrm.comUtlProcessMon.managerEmail" /> <span class="pilsu">*</span></th><!-- 관리자이메일 -->
				<td class="left">
				    <form:input  path="mngrEmailAddr" size="60" maxlength="60"/>
					<form:errors path="mngrEmailAddr"/>
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_ProcessMon(document.processMonVO); return false;" />
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_ProcessMon(); return false;" />
		</div>
		<div style="clear:both;"></div>
	</div>
	
	
		</form:form>

	</body>
</html>