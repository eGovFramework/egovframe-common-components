<%
 /**
  * @Class Name  : EgovComUtlHttpMonDetail.jsp
  * @Description : EgovComUtlHttpMonDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUtlSysHtm.comUtlHttpMonDetail.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>${pageTitle}</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fnList(){
			location.href = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonList.do'/>";
		}
		/* ********************************************************
		 * 수정화면으로  바로가기
		 ******************************************************** */
		function fnModify(){
			var varForm				 = document.all["Form"];
			varForm.action           = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonModify.do'/>";
			varForm.sysId.value     = "${result.sysId}";
			varForm.submit();
		}
		/* ********************************************************
		 * 삭제 처리 함수
		 ******************************************************** */
		function fnDelete(){
			if (confirm("<spring:message code="common.delete.msg" />")) {
				var varForm				 = document.all["Form"];
				varForm.action           = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonRemove.do'/>";
				varForm.sysId.value      = "${result.sysId}";
				varForm.submit();
			}
		}
		-->
		</script>
	</head>
	
	<body>
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
	
		<DIV class="wTableFrm">
			<form name="Form" action="" method="post">
				<input name="sysId" type="hidden">

				    <!-- 상단 타이틀  영역 -->
					<h2>&nbsp;${pageTitle}</h2>

					<table width="700" border="0" cellpadding="0" cellspacing="1" class="wTable" 
					summary="<spring:message code="comUtlSysHtm.comUtlHttpMonDetail.summary" />">
					<caption>${pageTitle}</caption>
					  	<tr> 
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysHtm.comUtlHttpMon.webService" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 웹서비스종류 -->
					    	<!-- <td>
						    <c:if test="${result.webKind == '001'}">TOMCAT</c:if>
						    <c:if test="${result.webKind == '002'}">WEBLOGIC</c:if>
						    <c:if test="${result.webKind == '003'}">JEUS</c:if>
						    <c:if test="${result.webKind == '004'}">JBOSS</c:if>							    				    	    					    	
							</td>  -->
					    	<td class="left">${result.webKind}</td>   							
					  	</tr>
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysHtm.comUtlHttpMon.systemURL" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 시스템URL -->          
					    	<td class="left">${result.siteUrl}</td>    
					  	</tr> 					  	
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysHtm.comUtlHttpMon.managerName" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 관리자명 -->          
					    	<td class="left">${result.mngrNm}</td>    
					  	</tr> 
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysHtm.comUtlHttpMon.managerEmail" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 관리자이메일 -->          
					    	<td class="left">${result.mngrEmailAddr}</td>    
					  	</tr>     
					</table>
					
				    <!-- 목록/저장버튼  -->
					<div class="btn">
						<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fnModify(); return false;" />
						<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" onclick="fnDelete(); return false;" />
						<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fnList(); return false;" />
					</div>
					
			</form>
		</DIV>
	</body>					
</html>