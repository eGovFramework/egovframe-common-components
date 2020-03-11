<%
 /**
  * @Class Name  : EgovComUtlHttpMonRegist.jsp
  * @Description : EgovComUtlHttpMonRegist 화면
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
<c:set var="pageTitle"><spring:message code="comUtlSysHtm.comUtlHttpMonRegist.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>${pageTitle}</title>
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="httpMon" staticJavascript="false" xhtml="true" cdata="false"/>
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_HttpMon(){
			// 첫 입력란에 포커스..
			httpMon.webKind.focus();
		}			
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_HttpMon(){
			location.href = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonList.do'/>";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		 function fn_egov_regist_HttpMon(form){
			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateHttpMon(form)){ 			
					return;
				}else{
					form.submit();
				}
			}
		}
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_HttpMon();">
	
	<form:form commandName="httpMon" name="httpMon" method="post">
	
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
					<th><spring:message code="comUtlSysHtm.comUtlHttpMon.webService" /> <span class="pilsu">*</span></th><!-- 웹서비스종류 -->
					<td class="left">
					    <select name="webKind" title="<spring:message code="comUtlSysHtm.comUtlHttpMon.webService" />">
							<option value="TOMCAT">TOMCAT</option>
							<option value="WEBLOGIC">WEBLOGIC</option>
							<option value="JEUS">JEUS</option>
							<option value="JBOSS">JBOSS</option>								   							   	
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comUtlSysHtm.comUtlHttpMon.systemURL" /> <span class="pilsu">*</span></th><!-- 시스템URL -->
					<td class="left">
					    <form:input  path="siteUrl" size="30" maxlength="30"/>
	      				<form:errors path="siteUrl"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comUtlSysHtm.comUtlHttpMon.managerName" /> <span class="pilsu">*</span></th><!-- 관리자명 -->
					<td class="left">
					    <form:input  path="mngrNm" size="60" maxlength="60"/>
	      				<form:errors path="mngrNm"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comUtlSysHtm.comUtlHttpMon.managerEmail" /> <span class="pilsu">*</span></th><!-- 관리자이메일 -->
					<td class="left">
					    <form:input  path="mngrEmailAddr" size="60" maxlength="60"/>
	      				<form:errors path="mngrEmailAddr"/>
					</td>
				</tr>
			</table>
		
			<!-- 하단 버튼 -->
			<div class="btn">
				<input class="s_submit" type="submit" value="저장" onclick="fn_egov_regist_HttpMon(document.httpMon); return false;" />
				<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_HttpMon(); return false;" />
			</div>
			<div style="clear:both;"></div>
		</div>
		
		</form:form>
		
	</body>
</html>