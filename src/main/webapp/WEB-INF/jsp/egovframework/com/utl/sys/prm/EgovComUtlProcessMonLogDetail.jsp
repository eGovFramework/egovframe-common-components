<%
 /**
  * @Class Name  : EgovComUtlProcessMonLogDetail.jsp
  * @Description : EgovComUtlProcessMonLogDetail 화면
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
  *  Copyright (C) 2009 by MOPAS  All rights reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUtlSysPrm.comUtlProcessMonLogDetail.title"/></c:set>
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
			location.href = "<c:url value='/utl/sys/prm/EgovComUtlProcessMonLogList.do'/>";
		}
		-->
		</script>
	</head>
	
	<body>
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
	
		<DIV class="wTableFrm">						
			<form name="Form" action="" method="post">
				<input name="processNm" type="hidden">
				
				    <!-- 상단 타이틀  영역 -->
					<h2>&nbsp;${pageTitle}</h2>
					
					<table width="700" border="0" cellpadding="0" cellspacing="1" class="wTable" 
					summary="<spring:message code="comUtlSysPrm.comUtlProcessMonLogDetail.summary" />">
					<caption>${pageTitle}</caption>
					  	<tr> 
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.logID" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 로그ID -->
					    	<td class="left">${result.logId}</td>
					  	</tr>
					  	<tr> 
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.processID" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 프로세스ID -->
					    	<td class="left">${result.processId}</td>
					  	</tr>								  						
					  	<tr> 
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.processName" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 프로세스명 -->
					    	<td class="left">${result.processNm}</td>
					  	</tr>
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.status" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 상태 -->          
					    	<td class="left">${result.procsSttus}</td>    
					  	</tr>
						<c:if test="${result.procsSttus == '오류'}">
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.logInfo" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 로그 -->          
					    	<td class="left">${result.logInfo}</td>    
					  	</tr>
		  				</c:if>					  	 					  	 					  	
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.lastCreatedDateTime" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 마지막 생성일시 -->          
					    	<td class="left">${result.creatDt}</td>    
					  	</tr>  
					</table>

					<!-- 하단 버튼 -->
					<div class="btn">
						<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fnList(); return false;" />
					</div>
			</form>
		</DIV>
	</body>					
</html>