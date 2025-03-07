<%
 /**
  * @Class Name  : EgovComDamMapTeamList.jsp
  * @Description : EgovComDamMapTeamList 화면
  * @Modification Information
  * @
  * @ 수정일      수정자           수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.07.23  박종선          최초 생성
  *   2018.09.11  신용호          공통컴포넌트 3.8 개선
  *   2024.10.29  권태성		  press(event) 추가
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
		<title><spring:message code="comDamMapTea.comDamMapTeamList.title"/></title><!-- 지식맵(조직별)관리 목록 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">		
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_MapTeam(){
			// 첫 입력란에 포커스..
			var form = document.listForm;
			form.searchCondition.focus();
		}		
		/* ********************************************************
		 * 페이징 처리 함수
		 ******************************************************** */
		function linkPage(pageNo){
			var form = document.listForm;			
			form.pageIndex.value = pageNo;
			form.action = "<c:url value='/dam/map/tea/EgovComDamMapTeamList.do'/>";
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
		 * 등록 처리 함수 
		 ******************************************************** */
		function fnRegist(){
			location.href = "<c:url value='/dam/map/tea/EgovComDamMapTeamRegist.do'/>";
		}
		
		function press(event) {
            if (event.keyCode == 13) {
                fnSearch();
            }
        }
		-->
		</script>
	</head>
	
	<body onLoad="fn_egov_initl_MapTeam();">
	
	<!-- 자바스크립트 경고 태그  -->
	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
	
	<div class="board">
		<h1><spring:message code="comDamMapTea.comDamMapTeamList.pageTop.title"/></h1><!-- 지식맵(조직별) 목록 -->
	
		<form name="listForm" action="<c:url value='/dam/map/tea/EgovComDamMapTeamList.do'/>" method="post">
		<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
			<ul>
				<li>
					<select name="searchCondition" class="select" title="<spring:message code="title.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.cSelect"/>--</option><!-- 선택하세요 -->
					<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comDamMapTea.comDamMapTeamList.orgnztNm"/></option><!-- 조직명 -->
					<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comDamMapTea.comDamMapTeamList.orgnztId"/></option><!-- 조직ID -->
					</select>
					<input class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword}" maxlength="35" size="35" onkeypress="press(event);" title="<spring:message code="title.search"/>" /><!-- 검색어 입력 -->
					
					<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fnSearch(); return false;" /><!-- 조회 -->
					<span class="btn_b"><a href="<c:url value='/dam/map/tea/EgovComDamMapTeamRegist.do'/>" onclick="" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
				</li>
			</ul>
		</div>
	
		<table class="board_list">
			<caption></caption>
			<colgroup>
				<col style="width:10%" />
				<col style="width:20%" />
				<col style="width:20%" />
				<col style="width:30%" />
				<col style="width:20%" />
			</colgroup>
			<thead>
				<tr>
				   <th scope="col"><spring:message code="comDamMapTea.comDamMapTeamList.index"/></th><!-- 순번 -->
				   <th scope="col"><spring:message code="comDamMapTea.comDamMapTeamList.orgnztNm"/></th><!-- 조직명 -->
				   <th scope="col"><spring:message code="comDamMapTea.comDamMapTeamList.orgnztId"/></th><!-- 조직ID -->
				   <th scope="col"><spring:message code="comDamMapTea.comDamMapTeamList.knoUrl"/></th><!-- 지식URL -->
				   <th scope="col"><spring:message code="comDamMapTea.comDamMapTeamList.clYmd"/></th><!-- 분류일자 -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
					<tr>				
						<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
						<td>
						<a href="<c:url value='/dam/map/tea/EgovComDamMapTeamDetail.do'/>?pageIndex=${searchVO.pageIndex}&amp;orgnztId=${resultInfo.orgnztId}"><c:out value="${resultInfo.orgnztNm}"/></a>								
						</td>
						<td>${resultInfo.orgnztId}</td>
						<td>${resultInfo.knoUrl}</td>
						<td>${resultInfo.clYmd}</td>
					</tr>   
				</c:forEach>
				
				<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
				<c:if test="${fn:length(resultList) == 0}">
					<tr> 
						<td colspan="5">
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
		
		<input type="hidden" name="orgnztId">	
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		</form>
	</div>
	
	</body>
</html>

