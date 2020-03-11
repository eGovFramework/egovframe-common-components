<%
 /**
  * @Class Name  : EgovComDamManagementDetail.jsp
  * @Description : EgovComDamManagementDetail 화면
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
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title><spring:message code="comDamMgm.comDamManagementDetail.title"/></title><!-- 지식정보 상세조회 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fnList(){
			location.href = "<c:url value='/dam/mgm/EgovComDamManagementList.do'/>";
		}
		/* ********************************************************
		 * 수정화면으로  바로가기
		 ******************************************************** */
		function fnModify(){
			var varForm			= document.all["Form"];
			varForm.action      = "<c:url value='/dam/mgm/EgovComDamManagementModify.do'/>";
			varForm.knoId.value = "${result.knoId}";
			//alert(varForm.knoId.value);	
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
		<h2><spring:message code="comDamMgm.comDamManagementDetail.pageTop.title"/></h2><!-- 지식정보 상세조회 -->
	
		<form name="Form" action="<c:url value='/dam/mgm/EgovComDamManagementModify.do'/>" method="post">
		<input name="knoId" type="hidden">
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				    ${result.orgnztNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    ${result.knoTypeNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.frstRegisterPnttm"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
				<td class="left">
				    ${result.frstRegisterPnttm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.userNm"/> <span class="pilsu">*</span></th><!-- 등록자명 -->
				<td class="left">
				    ${result.userNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
				<td class="left">
				    ${result.knoNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
				<td class="left">
				    <textarea name="knoCn" class="textarea" title="<spring:message code="comDamMgm.comDamManagementDetail.knoCn"/>"  cols="300" rows="5"  style="height:140px" readonly="readonly">${result.knoCn}</textarea><!-- 지식내용 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.othbcAt"/> <span class="pilsu">*</span></th><!-- 공개여부 -->
				<td class="left">
				    <c:choose>
					<c:when test="${result.othbcAt == 'Y'}">
						<spring:message code="comDamMgm.comDamManagementDetail.public" />
					</c:when>
					<c:otherwise>
						<spring:message code="comDamMgm.comDamManagementDetail.private" />
					</c:otherwise>
				    </c:choose>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.appYmd"/> <span class="pilsu">*</span></th><!-- 평가일자 -->
				<td class="left">
				    <c:if test="${result.appYmd == null}"><spring:message code="comDamMgm.comDamManagementDetail.status.proceeding"/></c:if><!-- 진행중 -->
		    		<c:if test="${result.appYmd != null}">${result.appYmd}</c:if>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.speNm"/> <span class="pilsu">*</span></th><!-- 평가자명 -->
				<td class="left">
				    ${result.speNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.knoAps"/> <span class="pilsu">*</span></th><!-- 평가결과 -->
				<td class="left">
				    <c:if test="${result.knoAps == null}"><spring:message code="comDamMgm.comDamManagementDetail.status.evaluating"/></c:if><!-- 평가중 -->
				    <c:if test="${result.knoAps == '1'}"><spring:message code="comDamMgm.comDamManagementDetail.status.approved"/></c:if><!-- 승인 -->
				    <c:if test="${result.knoAps == '2'}"><spring:message code="comDamMgm.comDamManagementDetail.status.denied"/></c:if><!-- 반려 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.knoAps"/> <span class="pilsu">*</span></th><!-- 지식정제 -->
				<td class="left">
				    <c:if test="${result.knoAps == '1'}"><spring:message code="comDamMgm.comDamManagementDetail.status.available"/></c:if><!-- 가용 -->
		    		<c:if test="${result.knoAps == '3'}"><spring:message code="comDamMgm.comDamManagementDetail.status.disposal"/></c:if><!-- 폐기 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.junkYmd"/></th><!-- 폐기일자 -->
				<td class="left">
				    ${result.junkYmd}
				</td>
			</tr>
			<c:if test="${result.atchFileId != ''}">
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementDetail.atchFileList"/> <span class="pilsu">*</span></th><!-- 첨부파일 목록 -->
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
			<c:if test="${result.authorCode = 'ROLE_SYM'}"><!-- 사용하지 않음 -->
			  	<!-- <input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fnModify(); return false;"> --><!-- 수정 -->
		  	</c:if>
			<input class="s_submit" type="submit" value="<spring:message code="comDamMgm.comDamManagementDetail.disposal"/>" onclick="fnModify(); return false;" /><!-- 폐기 -->
		  	<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fnList(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>

	</form>

	</div>
	
	</body>
</html>