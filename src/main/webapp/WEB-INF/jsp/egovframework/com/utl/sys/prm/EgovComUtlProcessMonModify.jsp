<%
 /**
  * @Class Name  : EgovComUtlProcessMonModify.jsp
  * @Description : EgovComUtlProcessMonModify 화면
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
<c:set var="pageTitle"><spring:message code="comUtlSysPrm.comUtlProcessMonModify.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>${pageTitle}</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="processMonVO" staticJavascript="false" xhtml="true" cdata="false"/>
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_ProcessMon(){
			location.href = "<c:url value='/utl/sys/prm/EgovComUtlProcessMonList.do'/>";
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
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		function fn_egov_modify_ProcessMon(form){
			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateProcessMonVO(form)){ 			
					return;
				}else{
					form.submit();
				}
			}
		}
		-->
		</script>
	</head>

	<body>
		<div class="wTableFrm">					
			<form:form commandName="processMonVO" name="processMonVO" method="post">
			<input name="cmd" type="hidden" value="Modify">
			<form:hidden path="processId"/>	
				<!-- 상단 타이틀  영역 -->
				<h2>&nbsp;${pageTitle}</h2>

				<table width="700" border="0" cellpadding="0" cellspacing="1" class="wTable" 
					summary="<spring:message code="comUtlSysPrm.comUtlProcessMonDetail.summary" />">
					<caption>${pageTitle}</caption>
  					<tr>
    					<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.processName" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 프로세스명 -->
				    	<td width="80%">
				      	<form:input  onkeyup="cleanQueryTerm()" path="processNm" size="60" maxlength="60"/>
				      	<form:errors path="processNm"/>
				    	</td>  				    	
  					</tr>
				  	<!-- <tr>
				    	<th scope="row" width="20%" height="23" class="required_text">상태<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th>          
				    	<td>${processMonVO.procsSttus}</td>    
				  	</tr> -->   					 
				  	<tr>
				    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.managerName" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 관리자명 -->          
				    	<td width="80%">
				      	<form:input  path="mngrNm" size="60" maxlength="60"/>
				      	<form:errors path="mngrNm"/>
				    	</td>    
				  	</tr> 
				  	<tr>
				  		<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.managerEmail" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 관리자이메일 -->          
				    	<td width="80%">
				      	<form:input  path="mngrEmailAddr" size="60" maxlength="60"/>
				      	<form:errors path="mngrEmailAddr"/>
				    	</td>    
				  	</tr>     
				</table>
				
				<table width="700" border="0" cellspacing="0" cellpadding="0">
				  	<tr> 
				    	<td height="10"></td>
				  	</tr>
				</table>

				<!-- 목록/저장버튼  -->
				<div class="btn">
					<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_ProcessMon(document.processMonVO); return false;" />
					<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_ProcessMon(); return false;" />
				</div>
				
			</form:form>
		</DIV>
	</body>
</html>