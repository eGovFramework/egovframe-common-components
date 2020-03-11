<%
 /**
  * @Class Name  : EgovComUtlHttpMonList.jsp
  * @Description : EgovComUtlHttpMonList 화면
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
<%@ page import="java.util.*"  %>
<%@ page import="java.io.*"  %>
<c:set var="pageTitle"><spring:message code="comUtlSysHtm.comUtlHttpMonList.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title>${pageTitle}</title>
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_HttpMon(){
			// 첫 입력란에 포커스..
			var form = document.listForm; 
			form.searchCondition.focus();
		}		
		/* ********************************************************
		 * 페이징 처리 함수
		 ******************************************************** */
		function linkPage(pageNo){
			var form = document.listForm;			
			document.listForm.pageIndex.value = pageNo;
			document.listForm.action = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonList.do'/>";
			form.submit();
		}
		/* ********************************************************
		 * 조회 처리 
		 ******************************************************** */
		function fnSearch(){
			var form = document.listForm;					
			form.pageIndex.value = 1;
		   	form.submit();
		}
		/* ********************************************************
		 * 로그조회 함수 
		 ******************************************************** */
		function fnSearchLog(){
			location.href = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonLogList.do'/>";
		}
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_HttpMon();">
		
		<!-- 자바스크립트 경고 태그  -->
		<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
		
		<div class="board">
			<h1>${pageTitle}</h1>
		
		<form name="listForm" action="<c:url value='/utl/sys/htm/EgovComUtlHttpMonList.do'/>" method="post">
			<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
				<ul>
					<li>
						<select name="searchCondition" class="select" title="조회조건 선택">
		   					<option value=''>--<spring:message code="input.select" />--</option>
		   					<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comUtlSysHtm.comUtlHttpMon.managerName" /></option><!-- 관리자명 -->
		   					<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comUtlSysHtm.comUtlHttpMon.status" /></option><!-- 상태 -->		   
	   					</select>
						<input class="s_input2 vat" name="searchKeyword" type="text" value='${searchVO.searchKeyword}' maxlength="35" size="35" onkeypress="press();" title="검색어 입력" />
						
						<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fnSearch(); return false;" />
						<span class="btn_b"><a href="<c:url value='/utl/sys/htm/EgovComUtlHttpMonRegist.do'/>" onclick="" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
						<input class="s_btn" type="submit" value="<spring:message code="button.log" />" title="<spring:message code="button.log" />" onclick="fnSearchLog(); return false;" />
					</li>
				</ul>
			</div>
		
			<table class="board_list">
				<caption></caption>
				<colgroup>
					<col style="width:10%" />
					<col style="width:15%" />
					<col style="width:20%" />
					<col style="width:15%" />
					<col style="width:20%" />
					<col style="width:25%" />
				</colgroup>
				<thead>
					<tr>
					   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.seq" /></th><!-- 순번 -->
					   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.webService" /></th><!-- 웹서비스종류 -->
					   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.systemURL" /></th><!-- 시스템URL -->
					   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.status" /></th><!-- 상태 -->
					   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.managerName" /></th><!-- 관리자명 -->
					   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.createdDateTime" /></th><!-- 생성일시 -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
						<tr>	
							<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
							<td>${resultInfo.webKind}</td>
							<td>
							<a href="<c:url value='/utl/sys/htm/EgovComUtlHttpMonDetail.do'/>?pageIndex=${searchVO.pageIndex}&amp;sysId=${resultInfo.sysId}"><c:out value="${resultInfo.siteUrl}"/></a>								
							</td>							
							<td>${resultInfo.httpSttusCd}</td>
							<td>${resultInfo.mngrNm}</td>													
							<td>${resultInfo.creatDt}</td>
						</tr>   
					</c:forEach>
														
					<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
					<c:if test="${fn:length(resultList) == 0}">
						<tr> 
							<td colspan="6">
								<spring:message code="common.nodata.msg" />
							</td>
						</tr>   	          				 			   
					</c:if>
				</tbody>
			</table>
		
			<!-- paging navigation -->
			<div class="pagination">
				<ul>
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
				</ul>
			</div>

		</form>
		</div>

	</body>
</html>