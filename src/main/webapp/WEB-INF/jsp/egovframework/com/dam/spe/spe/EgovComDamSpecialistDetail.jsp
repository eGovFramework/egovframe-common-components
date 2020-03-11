<%
 /**
  * @Class Name  : EgovComDamSpecialistDetail.jsp
  * @Description : EgovComDamSpecialistDetail 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.06.30  박종선          최초 생성
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
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title><spring:message code="comDamSpeSpe.comDamSpecialistDetail.title"/></title><!-- 지식전문가 상세조회 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">				
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fnInit(){
		}
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fnList(){
			location.href = "<c:url value='/dam/spe/spe/EgovComDamSpecialistList.do'/>";
		}
		/* ********************************************************
		 * 수정화면으로  바로가기
		 ******************************************************** */
		function fnModify(){
			var varForm			= document.all["Form"];
			varForm.action      = "<c:url value='/dam/spe/spe/EgovComDamSpecialistModify.do'/>";
			varForm.speId.value = "${result.speId}";
			varForm.knoTypeCd.value = "${result.knoTypeCd}";
			varForm.appTypeCd.value = "${result.appTypeCd}";						
			varForm.submit();
		}
		/* ********************************************************
		 * 삭제 처리 함수
		 ******************************************************** */
		function fnDelete(){
			if (confirm("<spring:message code="common.delete.msg" />")) {
				var varForm			= document.all["Form"];
				varForm.action      = "<c:url value='/dam/spe/spe/EgovComDamSpecialistRemove.do'/>";
				varForm.speId.value = "${result.speId}";
				varForm.knoTypeCd.value = "${result.knoTypeCd}";
				varForm.appTypeCd.value = "${result.appTypeCd}";							
				varForm.submit();
			}
		}
		-->
		</script>
	</head>
	
	<body>
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comDamSpeSpe.comDamSpecialistDetail.pageTop.title"/></h2><!-- 지식전문가 상세조회 -->
	
		<form name="Form" action="<c:url value='/dam/spe/spe/EgovComDamSpecialistModify.do'/>" method="post">
		<input name="speId" type="hidden">
		<input name="knoTypeCd" type="hidden">
		<input name="appTypeCd" type="hidden">			

		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistDetail.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				    ${result.orgnztNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistDetail.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    ${result.knoTypeNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistDetail.expertNm"/> <span class="pilsu">*</span></th><!-- 전문가명 -->
				<td class="left">
				    ${result.userNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistDetail.rank"/> <span class="pilsu">*</span></th><!-- 등급 -->
				<td class="left">
				    <c:if test="${result.appTypeCd == '1'}"><spring:message code="comDamSpeSpe.comDamSpecialistDetail.rankType1"/></c:if><!-- 수석 -->
				    <c:if test="${result.appTypeCd == '2'}"><spring:message code="comDamSpeSpe.comDamSpecialistDetail.rankType2"/></c:if><!-- 책임 -->
				    <c:if test="${result.appTypeCd == '3'}"><spring:message code="comDamSpeSpe.comDamSpecialistDetail.rankType3"/></c:if><!-- 선임 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistDetail.speExpCn"/> <span class="pilsu">*</span></th><!-- 전문가설명 -->
				<td class="left">
				    <textarea name="speExpCn" class="textarea" title="<spring:message code="comDamSpeSpe.comDamSpecialistDetail.speExpCn"/>"  cols="300" rows="10"  style="height:140px" readonly="readonly">${result.speExpCn}</textarea><!-- 전문가설명 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistDetail.speConfmDe"/> <span class="pilsu">*</span></th><!-- 승인일자 -->
				<td class="left">
				    ${result.speConfmDe}
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fnModify(); return false;" /><!-- 수정 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fnDelete(); return false;" /><!-- 삭제 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fnList(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>

		</form>
	</div>
	
	</body>
</html>