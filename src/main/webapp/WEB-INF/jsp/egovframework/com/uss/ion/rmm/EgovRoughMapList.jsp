<%
 /**
  * @Class Name : EgovRoughMapList.jsp
  * @Description : EgovNewsInfoList 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2014.08.28	옥찬우		최초 생성
  * @ 2018.09.10   최두영       v3.8 퍼블리싱 점검
  * @ 2018.09.12   최두영       다국어처리
  * @ 2018.10.10   최두영       약도 관리 API 테스트 및 변경
  *
  *  @author 유지보수팀 
  *  @since 2014.08.28
  *  @version 1.0
  *  @see
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title><spring:message code="comUssIonRmm.roughMapList.title" /></title><!-- 약도 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
	
	/*********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init_roughmaplist(){
	    // 첫 입력란에 포커스..
	    document.searchVO.searchKeyword.focus();
	}
	
	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fn_egov_select_linkPage(pageNo){
	    document.RoughMapForm.pageIndex.value = pageNo;
	    document.RoughMapForm.action = "<c:url value='/com/uss/ion/rmm/selectRoughMapList.do'/>";
	    document.RoughMapForm.submit();
	}
	
	/*********************************************************
	 * 조회 처리 함수
	 ******************************************************** */
	function fn_egov_search_roughmap(){
	    document.RoughMapForm.pageIndex.value = 1;
	    document.RoughMapForm.submit();
	}

	/* ********************************************************
	 * 상세회면 처리 함수
	 ******************************************************** */
	function fn_egov_inquire_roughmapdetail(roughMapId) {       
	    // 약도id
	    document.RoughMapForm.roughMapId.value = roughMapId;    
	    document.RoughMapForm.action = "<c:url value='/com/uss/ion/rmm/selectRoughMapDetail.do'/>";
	    document.RoughMapForm.submit(); 
	}
	
	/*********************************************************
	 * 등록 처리 함수
	 ******************************************************** */
	function fn_egov_regist_roughmap(){
	    document.RoughMapForm.action = "<c:url value='/com/uss/ion/rmm/registRoughMap.do'/>";
	    document.RoughMapForm.submit(); 
	}
	
	/*********************************************************
	 * 수정 처리 함수
	 ******************************************************** */
	function fn_egov_updt_roughmap(){
	    document.RoughMapForm.action = "<c:url value='/com/uss/ion/rmm/updateRoughMapView.do'/>";
	    document.RoughMapForm.submit(); 
	}
	
</script>
</head>
<body onLoad="fn_egov_init_roughmaplist();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comUssIonRmm.roughMapList.title" /> <spring:message code="title.inquire" /></h1><!-- 약도목록 조회 -->
	<span><spring:message code="comUssIonRmm.roughMapList.info"/></span><!-- 약도관리는 Daum 지도 API 키를 발급받아야 합니다. -->
	<span>(※ Guide Link : <a href="http://apis.map.daum.net/web/guide/" target="_new"><spring:message code="comUssIonRmm.roughMapList.info2"/></a>)</span> <!-- Daum 지도 API 가이드 -->

	<form name="searchVO" action="<c:url value='/com/uss/ion/rmm/selectRoughMapList.do'/>" method="post">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="select.searchCondition" />"><!-- 조회조건 선택 -->
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="roughMapSj" <c:if test="${searchVO.searchCondition == 'roughMapSj'}">selected="selected"</c:if> ><spring:message code="comUssIonRmm.roughMapList.roughMapSj" /></option><!-- 약도제목 -->
					<option value="roughMapAddress" <c:if test="${searchVO.searchCondition == 'roughMapAddress'}">selected="selected"</c:if> ><spring:message code="comUssIonRmm.roughMapList.roughMapAddress" /></option><!-- 약도주소 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' size="35" maxlength="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" /><!-- 검색어 입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_search_roughmap(); return false;" />
				<span class="btn_b"><a href="<c:url value='/com/uss/ion/rmm/registRoughMap.do'/>" onclick="fn_egov_regist_roughmap(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
		<input name="roughMapId" type="hidden" value="">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>
	
	<table class="board_list">
		<caption><spring:message code="comUssIonRmm.roughMapList.title" /></caption><!-- 약도목록 -->
		<colgroup>
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:50%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonRmm.roughMapList.roughMapNo" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comUssIonRmm.roughMapList.roughMapSj" /></th><!-- 약도제목 -->
			   <th scope="col"><spring:message code="comUssIonRmm.roughMapList.roughMapDetailAddress" /></th><!--약도상세주소  -->
			   <th scope="col"><spring:message code="comUssIonRmm.roughMapList.lastUpdtPnttm" /></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(resultList) == 0}">
				<tr> 
					<td colspan="4">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		    
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				<tr>
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	        
					<td>
						<form name="roughMap" method="post" action="<c:url value='/com/uss/ion/rmm/selectRoughMapDetail.do'/>">
						<input id="roughMapId" name="roughMapId" type="hidden" value="${resultInfo.roughmapId}" />
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
						<span class="link">
							<input type="submit"  value="<c:out value="${resultInfo.roughmapsj}"/>" onclick="fn_egov_inquire_roughmapdetail('<c:out value="${resultInfo.roughmapId}"/>'); return false;">
						</span>
			   			</form>
					</td>		
					<td><c:out value="${resultInfo.roughmapaddress}"/></td>		
					<td><c:out value="${resultInfo.lastUpdtPnttm}"/></td>			
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
		</ul>
	</div>
</div>
</body>
</html>
