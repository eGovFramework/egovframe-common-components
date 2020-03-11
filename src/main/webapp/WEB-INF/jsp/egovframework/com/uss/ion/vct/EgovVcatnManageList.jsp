<%
/**
 * @Class Name : EgovVcatnManageList.java
 * @Description : EgovVcatnManageList jsp
 * @Modification Information
 * @
 * @  수정일            수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용    최초 생성
 * @ 2018.08.14    최 두 영     퍼블리싱 점검
 * @ 2018.09.18    최 두 영     다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonVct.vcatnManageList.title"/></title><!-- 휴가관리 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
 /*설명 : 휴가 목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				 = document.all["listForm"];
	varForm.searchCondition.value = "1";
	varForm.pageIndex.value = pageNo;
	varForm.action = "<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>";
	varForm.submit();
 }

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectVcatnManageList(pageNo){
	 var varForm				 = document.all["listForm"];
	 //varForm.searchCondition.value = "1";
	 varForm.pageIndex.value = pageNo;
	 varForm.action = "<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>";
	 varForm.submit();
 }

/* ********************************************************
 * 등록 화면 호출 함수 
 ******************************************************** */
function fncVcatnRegist(){
	var occrncYrycCo = document.getElementById("occrncYrycCo").value;
	
	if(!occrncYrycCo){
		alert("<spring:message code="comUssIonVct.vcatnManageList.validate.guide"/>"); /* 902.개인연차관리의 발생연차가 등록이 되어 있어야 사용가능 */
		return true;
	}else if(occrncYrycCo == 0){
		alert("<spring:message code="comUssIonVct.vcatnManageList.validate.makeVcatn"/>")/* 연차가 없습니다. 개인연차 확인이 필요합니다. */
		location.href = "<c:url value='/uss/ion/yrc/EgovIndvdlYrycManageList.do'/>";		
	}else{
		location.href = "<c:url value='/uss/ion/vct/EgovVcatnRegist.do'/>";
	}
}

/* ********************************************************
 * 상세화면 호출함수
 ******************************************************** */
function fncVcatnManageDetail(applcntId,vcatnSe,bgnde,endde){
	
	var access = document.getElementById("access").value;
	
	if(!access || access == " "){
		alert("<spring:message code="comUssIonVct.vcatnManageList.validate.access"/>")/* 상세화면 접근을 위해서는 사용자 권한과 조직 아이디가 필요합니다. */
		return true;
	}else{	
	var varForm				 = document.all["listForm"];
	varForm.applcntId.value  = applcntId;
	varForm.vcatnSe.value    = vcatnSe;
	varForm.bgnde.value      = bgnde.replace("-","");
	varForm.endde.value      = endde.replace("-","");
	varForm.action           = "<c:url value='/uss/ion/vct/EgovVcatnManageDetail.do'/>";
	varForm.submit();
	}
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonVct.vcatnManageList.title"/></h1><!-- 휴가관리 목록 --><!-- 902.개인연차관리의 발생연차가 등록이 되어 있어야 사용가능 -->

	<span><spring:message code="comUssIonVct.vcatnManageList.validate.guide"/></span>

	<form name="listForm" action="<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>" method="post">
		<input type="hidden" id="access" value="${access}">
		<input type="hidden" name="searchCondition">
		<input type="hidden" name="applcntId">
		<input type="hidden" name="vcatnSe">
		<input type="hidden" name="bgnde">
		<input type="hidden" name="endde">
		<input type="hidden" id="occrncYrycCo" value="${vcatnManageVO.occrncYrycCo}">
		<input type="hidden" name="pageIndex" value="<c:if test="${empty vcatnManageVO.pageIndex }">1</c:if><c:if test="${!empty vcatnManageVO.pageIndex }"><c:out value='${vcatnManageVO.pageIndex}'/></c:if>">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonVct.vcatnManageList.vctYr"/> : </label><!-- 휴가년도 -->
				<select name="searchKeyword" title="<spring:message code="comUssIonVct.vcatnManageList.vctYr"/>"><!-- 휴가년도 -->
					<option value="" selected ><spring:message code="comUssIonVct.vcatnManageList.selectedAll"/></option><!-- 전체 -->
					<c:forEach items="${yearList}" var="result" varStatus="status">
					<option value="<c:out value="${result }"/>"  <c:if test="${vcatnManageVO.searchKeyword eq result}">selected</c:if>><c:out value="${result }"/></option>
					</c:forEach>
				</select><spring:message code="comUssIonVct.vcatnManageList.year"/><!-- 년 -->				
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectVcatnManageList('1'); return false;"  style="margin-left:10px" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/vct/EgovVcatnRegist.do'/>" onclick="fncVcatnRegist();" title="<spring:message code="button.create" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="" />
			<col style="width:15%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnManageList.vcatnSeNm"/></th><!-- 휴가구분 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnManageList.bgnde"/></th><!-- 시작일 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnManageList.endde"/></th><!-- 종료일 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnManageList.vcatnResn"/></th><!-- 휴가사유 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnManageList.confmAt"/></th><!-- 구분 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnManageList.sanctnerNm"/></th><!-- 승인자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vcatnManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(vcatnManageVO.pageIndex - 1) * vcatnManageVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.vcatnSeNm}"/></td>
				<td><c:out value="${resultInfo.bgnde}"/></td>
				<td><c:out value="${resultInfo.endde}"/></td>
				<td>
					<form name="item" method="post" action="<c:url value='/uss/ion/vct/EgovVcatnManageDetail.do'/>">
						<input type="hidden" name="applcntId" value="<c:out value="${resultInfo.applcntId}"/>">
						<input type="hidden" name="vcatnSe"   value="<c:out value="${resultInfo.vcatnSe}"/>">
						<input type="hidden" name="bgnde"     value="<c:out value="${resultInfo.bgnde}"/>">
						<input type="hidden" name="endde"     value="<c:out value="${resultInfo.endde}"/>">
						<input class="link" type="submit" value="<c:out value="${resultInfo.vcatnResn}"  escapeXml="false"/>" onclick="fncVcatnManageDetail('<c:out value="${resultInfo.applcntId}"/>','<c:out value="${resultInfo.vcatnSe}"/>','<c:out value="${resultInfo.bgnde}"/>','<c:out value="${resultInfo.endde}"/>'); return false;" style="text-align : left;" />
					</form>
				</td>
				<td>
					<c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonVct.vcatnManageList.confmAt.A"/></c:if><!-- 신청중 -->
					<c:if test="${resultInfo.confmAt eq 'C'}"><spring:message code="comUssIonVct.vcatnManageList.confmAt.C"/></c:if><!-- 승인 -->
					<c:if test="${resultInfo.confmAt eq 'R'}"><spring:message code="comUssIonVct.vcatnManageList.confmAt.R"/></c:if><!-- 반려 -->
				</td>
				<td><c:out value="${resultInfo.sanctnerNm}"/></td>
			</tr>    
			</c:forEach>
			
			<c:if test="${fn:length(vcatnManageList) == 0}">
			<tr> 
				<td class="lt_text3" colspan="7">
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
	</div>
</body>
</html>
