<%
 /**
  * @Class Name  : EgovComUtlProcessMonDetail.jsp
  * @Description : EgovComUtlProcessMonDetail 화면
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
<c:set var="pageTitle"><spring:message code="comUtlSysPrm.comUtlProcessMonDetail.title"/></c:set>

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
			location.href = "<c:url value='/utl/sys/prm/EgovComUtlProcessMonList.do'/>";
		}
		/* ********************************************************
		 * 수정화면으로  바로가기
		 ******************************************************** */
		function fnModify(){
			var varForm				 = document.all["Form"];
			varForm.action           = "<c:url value='/utl/sys/prm/EgovComUtlProcessMonModify.do'/>";
			varForm.processNm.value  = "${result.processNm}";
			varForm.submit();
		}
		/* ********************************************************
		 * 삭제 처리 함수
		 ******************************************************** */
		function fnDelete(){
			if (confirm("<spring:message code="common.delete.msg" />")) {
				var varForm				 = document.all["Form"];
				varForm.action           = "<c:url value='/utl/sys/prm/EgovComUtlProcessMonRemove.do'/>";
				varForm.processNm.value  = "${result.processNm}";
				varForm.submit();
			}
		}
		-->
		</script>
	</head>
	
	<body>
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
	
	<div class="wTableFrm">					
			<form name="Form" action="" method="post">
				<input name="processNm" type="hidden">
					<h2>&nbsp;${pageTitle}</h2>
					<table width="700" border="0" cellpadding="0" cellspacing="1" class="wTable" 
					summary="<spring:message code="comUtlSysPrm.comUtlProcessMonDetail.summary" />">
					<caption>${pageTitle}</caption>
					  	<tr> 
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.processName" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 프로세스명 -->
					    	<td class="left">${result.processNm}</td>
					  	</tr>
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.status" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 상태 -->          
					    	<td class="left">${result.procsSttus}</td>    
					  	</tr> 					  	
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.managerName" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 관리자명 -->          
					    	<td class="left">${result.mngrNm}</td>    
					  	</tr> 
					  	<tr>
					    	<th scope="row" width="20%" height="23" class="required_text"><spring:message code="comUtlSysPrm.comUtlProcessMon.managerEmail" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력표시"  width="15" height="15"></th><!-- 관리자이메일 -->          
					    	<td class="left">${result.mngrEmailAddr}</td>    
					  	</tr>     
					</table>
					
					<!-- 하단 버튼 -->
					<div class="btn">
						<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fnModify(); return false;" />
						<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" onclick="fnDelete(); return false;" />
						<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fnList(); return false;" />
					</div>
					
			</form>
		</DIV>
	</body>					
</html>