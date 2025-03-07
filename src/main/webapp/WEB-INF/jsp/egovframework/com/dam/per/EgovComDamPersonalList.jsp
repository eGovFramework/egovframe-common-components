<%
 /**
  * @Class Name  : EgovComDamPersonalList.jsp
  * @Description : EgovComDamPersonalList 화면
  * @Modification Information
  * @
  * @ 수정일              수정자             수정내용
  * @ ----------  --------    ---------------------------
  * @ 2010.08.12  박종선            최초 생성
  *   2018.09.11  신용호            공통컴포넌트 3.8 개선
  *   2024.10.29  권태성		 	목록으로 돌아올 때 검색 조건이 유지되도록 수정(#3)
  *   2024.10.29  권태성		 	press(event) 추가
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
		<title><spring:message code="comDamPer.comDamPersonalList.title"/></title><!-- 개인지식관리 목록 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">		
		
		<script type="text/javaScript" language="javascript">
		<!--
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_Personal(){
			// 첫 입력란에 포커스..
			document.listForm.searchCondition.focus();
		}
		/* ********************************************************
		 * 페이징 처리 함수
		 ******************************************************** */
		function linkPage(pageNo){
			document.listForm.pageIndex.value = pageNo;
			document.listForm.action = "<c:url value='/dam/per/EgovComDamPersonalList.do'/>";
		   	document.listForm.submit();
		}
		/* ********************************************************
		 * 조회 처리 
		 ******************************************************** */
		function fnSearch(){
			document.listForm.pageIndex.value = 1;
		   	document.listForm.submit();
		}
		/* ********************************************************
		 * 등록 처리 함수 
		 ******************************************************** */
		function fnRegist(){
			document.listForm.action = "<c:url value='/dam/per/EgovComDamPersonalRegistView.do' />";
            document.listForm.submit();
		}
        /* ********************************************************
         * 상세화면 이동 함수 
         ******************************************************** */
        function fnDetail(knoId) {
            document.listForm.action = "<c:url value='/dam/per/EgovComDamPersonal.do'/>";
            document.listForm.knoId.value = knoId;
            document.listForm.submit();
        }
        
        function press(event) {
            if (event.keyCode == 13) {
                fnSearch();
            }
        }
		-->
		</script>
	</head>
	
	<body onLoad="fn_egov_initl_Personal();">
	
	<!-- 자바스크립트 경고 태그  -->
		<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
		
		<div class="board">
			<h1><spring:message code="comDamPer.comDamPersonalList.pageTop.title"/></h1><!-- 개인지식관리 목록 -->
		
			<form name="listForm" action="<c:url value='/dam/per/EgovComDamPersonalList.do'/>" method="post">
		
			<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
				<ul>
					<li>
						<select name="searchCondition" class="select" title="<spring:message code="select.searchCondition"/>"><!-- 검색조건선택 -->
						<option value=''>--<spring:message code="input.cSelect"/>--</option><!-- 선택하세요 -->
						<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comDamPer.comDamPersonalList.knoTypeNm"/></option><!-- 지식유형명 -->
						<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comDamPer.comDamPersonalList.knoNm"/></option><!-- 지식명 -->
						</select>
						<input class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword}" maxlength="35" size="35" onkeypress="press(event);" title="<spring:message code="title.searchCondition"/>" /><!-- 검색어 입력 -->
						
						<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fnSearch(); return false;" /><!-- 조회 -->
						<span class="btn_b"><a href="#" onclick="fnRegist(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
					</li>
				</ul>
			</div>
		
			<table class="board_list">
				<caption></caption>
				<colgroup>
					<col style="width:10%" />
					<col style="width:10%" />
					<col style="width:15%" />
					<col style="width:20%" />
					<col style="width:15%" />
					<col style="width:15%" />
					<col style="width:15%" />
				</colgroup>
				<thead>
					<tr>
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.index"/></th><!-- 순번 -->
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.orgnztNm"/></th><!-- 조직명 -->
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.knoTypeNm"/></th><!-- 지식유형 -->
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.knoNm"/></th><!-- 지식명 -->
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.userNm"/></th><!-- 등록자 -->
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.colYmd"/></th><!-- 수집일자 -->
					   <th scope="col"><spring:message code="comDamPer.comDamPersonalList.othbcAt"/></th><!-- 공개여부 -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
						<tr>
							<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
							<td>${resultInfo.orgnztNm}</td>
							<td>${resultInfo.knoTypeNm}</td>								
							<td>
								<a href="#" onclick="fnDetail('${resultInfo.knoId}'); return false;"><c:out value="${resultInfo.knoNm}"/></a>
							</td>
							<td>${resultInfo.userNm}</td>
							<td>${resultInfo.colYmd}</td>
						    <td>
							    <c:if test="${resultInfo.othbcAt == 'Y'}"><spring:message code="comDamPer.comDamPersonalList.public"/></c:if><!-- 공개 -->
							    <c:if test="${resultInfo.othbcAt == 'N'}"><spring:message code="comDamPer.comDamPersonalList.private"/></c:if><!-- 비공개 -->
						    </td>									
						</tr>   
					</c:forEach>
					
					<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
					<c:if test="${fn:length(resultList) == 0}">
						<tr> 
							<td colspan="7">
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

			<input type="hidden" id="knoId" name="knoId">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			</form>

		</div>
		
	</body>
</html>

