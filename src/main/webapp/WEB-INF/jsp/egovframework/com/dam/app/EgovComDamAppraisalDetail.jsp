<%
 /**
  * @Class Name  : EgovComDamAppraisalDetail.jsp
  * @Description : EgovComDamAppraisalDetail 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.08.17  박종선          최초 생성
  *   2018.09.11  신용호          공통컴포넌트 3.8 개선
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

<c:set var="ImgUrl" value="/images/egovframework/com/cmm/"/>


<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title><spring:message code="comDamApp.comDamAppraisalDetail.title"/></title><!-- 지식평가 상세조회 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">						
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fnList(){
			location.href = "<c:url value='/dam/app/EgovComDamAppraisalList.do'/>";
		}
		/* ********************************************************
		 * 수정화면으로  바로가기
		 ******************************************************** */
		function fnModify(){
			var varForm			= document.all["Form"];
			varForm.action      = "<c:url value='/dam/app/EgovComDamAppraisalModify.do'/>";
			varForm.knoId.value = "${result.knoId}";
			varForm.submit();
		}
		-->
		</script>
	</head>
	
	<body>
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comDamApp.comDamAppraisalDetail.pageTop.title"/></h2><!-- 지식평가 상세조회 -->
		<form name="Form" action="<c:url value='/dam/per/EgovComDamPersonalModify.do'/>" method="post">
		<input name="knoId" type="hidden">
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				    ${result.orgnztNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    ${result.knoTypeNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
				<td class="left">
				    ${result.knoNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
				<td class="left">
				    <textarea name="knoCn" class="textarea" title="<spring:message code="comDamApp.comDamAppraisalDetail.knoCn"/>"  cols="300" rows="10"  style="width:450px;" readonly>${result.knoCn}</textarea><!-- 지식내용 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.atchFileList"/></th><!-- 첨부파일 목록 -->
				<td class="left">
				    <c:import url="/cmm/fms/selectFileInfs.do" >
						<c:param name="param_atchFileId" value="${result.atchFileId}" />
					</c:import>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.appYmd"/> <span class="pilsu">*</span></th><!-- 평가일자 -->
				<td class="left">
				    <c:if test="${result.appYmd == null}"><spring:message code="comDamApp.comDamAppraisalDetail.status.proceeding"/></c:if><!-- 진행중 -->
		    		<c:if test="${result.appYmd != null}">${result.appYmd}</c:if>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalDetail.knoAps"/> <span class="pilsu">*</span></th><!-- 평가결과 -->
				<td class="left">
				    <c:if test="${result.knoAps == null}"><spring:message code="comDamApp.comDamAppraisalDetail.status.evaluating"/></c:if><!-- 평가중 -->
				    <c:if test="${result.knoAps == '1'}"><spring:message code="comDamApp.comDamAppraisalDetail.status.approved"/></c:if><!-- 승인 -->
				    <c:if test="${result.knoAps == '2'}"><spring:message code="comDamApp.comDamAppraisalDetail.status.denied"/></c:if><!-- 반려 -->
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value="<spring:message code="comDamApp.comDamAppraisalDetail.button.evaluation"/>" onclick="fnModify(); return false;" /><!-- 평가 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fnList(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
		
		</form>
		
	</div>
	
	</body>
</html>