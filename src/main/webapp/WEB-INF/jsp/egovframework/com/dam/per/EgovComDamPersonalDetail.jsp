<%
 /**
  * @Class Name  : EgovComDamPersonalDetail.jsp
  * @Description : EgovComDamPersonalDetail 화면
  * @Modification Information
  * @
  * @ 수정일             수정자           수정내용
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
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title><spring:message code="comDamPer.comDamPersonalDetail.title"/></title><!-- 개인지식 상세조회 -->
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
			location.href = "<c:url value='/dam/per/EgovComDamPersonalList.do'/>";
		}
		/* ********************************************************
		 * 수정화면으로  바로가기
		 ******************************************************** */
		function fnModify(){
			var varForm			= document.all["Form"];
			varForm.action      = "<c:url value='/dam/per/EgovComDamPersonalModifyView.do'/>";
			varForm.knoId.value = "${result.knoId}";
			varForm.submit();
		}
		/* ********************************************************
		 * 삭제 처리 함수
		 ******************************************************** */
		function fnDelete(){
			if (confirm("<spring:message code="common.delete.msg" />")) {
				var varForm			= document.all["Form"];
				varForm.action      = "<c:url value='/dam/per/EgovComDamPersonalRemove.do'/>";
				varForm.knoId.value = "${result.knoId}";
				varForm.submit();
			}
		}
		-->
		</script>
	</head>
	
	<body>
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
	
	<form name="Form" action="<c:url value='/dam/per/EgovComDamPersonalModify.do'/>" method="post">
	<input name="knoId" type="hidden">
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comDamPer.comDamPersonalDetail.pageTop.title"/></h2><!-- 개인지식 상세조회 -->
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				    ${result.orgnztNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    ${result.knoTypeNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
				<td class="left">
				    ${result.knoNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
				<td class="left">
				    <textarea name="knoCn" class="textarea" title="<spring:message code="comDamPer.comDamPersonalDetail.knoCn"/>"  cols="300" rows="10" readonly="readonly">${result.knoCn}</textarea><!-- 지식내용 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.colYmd"/> <span class="pilsu">*</span></th><!-- 수집일자 -->
				<td class="left">
				    ${result.colYmd}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.othbcAt"/></th><!-- 공개여부 -->
				<td class="left">
				    <c:choose>
				    	<c:when test="${result.othbcAt == 'Y'}">
				    		<spring:message code="comDamPer.comDamPersonalDetail.public" />
				    	</c:when>
				    	<c:otherwise>
				    		<spring:message code="comDamPer.comDamPersonalDetail.private" />
				    	</c:otherwise>
				    </c:choose>
				</td>
			</tr>
			<c:if test="${result.atchFileId != ''}">
			<tr>
				<th><spring:message code="comDamPer.comDamPersonalDetail.atchFileId"/></th><!-- 첨부파일 목록 -->
				<td class="left">
				    <c:import url="/cmm/fms/selectFileInfs.do" >
						<c:param name="param_atchFileId" value="${result.atchFileId}" />
					</c:import>
				</td>
			</tr>			
			</c:if>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fnModify(); return false;" /><!-- 수정 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fnDelete(); return false;" /><!-- 삭제 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fnList(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
	</div>
	
	</form>

	</body>
</html>