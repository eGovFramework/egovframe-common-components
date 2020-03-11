<%
/**
 * @Class Name : EgovCtsnnManageList.java
 * @Description : EgovCtsnnManageList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.16    최 두 영     퍼블리싱 점검/오류개선
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
<title><spring:message code="comUssIonCtn.ctsnnManageList.title"/></title><!--경조사관리 목록-->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script type="text/javaScript" language="javascript">	
	function initCalendar(){
		$("#searchFromDate").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});		
		$("#searchToDate").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});			
	}

	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	/*설명 : 경조 목록 페이지 조회 */
	function linkPage(pageNo){
		var varForm	= document.all["listForm"];
		if(varForm.searchFromDate.value != ""){
			if(varForm.searchFromDate.value > varForm.searchToDate.value){
				alert("<spring:message code="comUssIonCtn.ctsnnManageList.validate.searchFromDate"/>");/* 경조신청일자 검색조건의 시작일자가 종료일자보다  늦습니다. 경조신청일자를 확인하세요. */
				return;
			}
		} else varForm.searchToDate.value = "";
	
		varForm.searchCondition.value = "1";
		varForm.pageIndex.value = pageNo;
		varForm.action = "<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>";
		varForm.submit();
	}

	/* ********************************************************
	 * 조회 처리
	 ******************************************************** */
	 /*설명 : 목록 조회 */
	 function fncSelectCtsnnManageList(pageNo){
		 var varForm = document.all["listForm"];
		 if(varForm.searchFromDate.value != ""){
		     if(varForm.searchFromDate.value > varForm.searchToDate.value){
		    	 alert("<spring:message code="comUssIonCtn.ctsnnManageList.validate.searchFromDate"/>");/* 경조신청일자 검색조건의 시작일자가 종료일자보다  늦습니다. 경조신청일자를 확인하세요. */
		         return;
			  }
		 }else varForm.searchToDate.value = "";
		 //varForm.searchCondition.value = "1";
		 varForm.pageIndex.value = pageNo;
		 varForm.action = "<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>";
		 varForm.submit();
	 }

	/* ********************************************************
	 * 등록 화면 호출 함수
	 ******************************************************** */
	function fncCtsnnRegist(){
		location.href = "<c:url value='/uss/ion/ctn/EgovCtsnnRegist.do'/>";
	}

	/* ********************************************************
	 * 상세회면 호출함수
	 ******************************************************** */
	function fncCtsnnManageDetail(ctsnnId){
		var varForm				 = document.all["listForm"];
		varForm.ctsnnId.value       = ctsnnId;
		varForm.action           = "<c:url value='/uss/ion/ctn/EgovCtsnnManageDetail.do'/>";
		varForm.submit();
	}
</script>
</head>
<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonCtn.ctsnnManageList.title"/></h1><!--경조사관리 목록-->

	<form name="listForm" action="<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>" method="post">
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="ctsnnId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty ctsnnManageVO.pageIndex }">1</c:if><c:if test="${!empty ctsnnManageVO.pageIndex }"><c:out value='${ctsnnManageVO.pageIndex}'/></c:if>">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<div class="con">
			<label for=""><spring:message code="comUssIonCtn.ctsnnManageList.searchKeyword"/> : </label><!-- 경조구분 -->
			<select name="searchKeyword" title="<spring:message code="comUssIonCtn.ctsnnManageList.searchKeyword"/>">
	        	<option value="" <c:if test="${ctsnnManageVO.searchKeyword eq '' }">selected</c:if>><spring:message code="comUssIonCtn.ctsnnManageList.selectedAll"/></option><!-- 전체 -->
	            <c:forEach items="${ctsnnCodeList}" var="result" varStatus="status">
		       	   <option value="<c:out value="${result.code }"/>" <c:if test="${ctsnnManageVO.searchKeyword eq result.code }">selected</c:if>><c:out value="${result.codeNm }"/></option>
	            </c:forEach>
	      	</select>
		</div>
		<div class="con">
			<label for=""><spring:message code="comUssIonCtn.ctsnnManageList.occurDate"/> : </label><!-- 발생일자 -->
			<c:if test="${!empty ctsnnManageVO.searchFromDate}">
			 	<c:set var="fromNow" value="${fn:substring(ctsnnManageVO.searchFromDate,0,4)}-${fn:substring(ctsnnManageVO.searchFromDate,4,6)}-${fn:substring(ctsnnManageVO.searchFromDate,6,8)}" />
			 </c:if>
			 <c:if test="${!empty ctsnnManageVO.searchToDate}">
			 	<c:set var="toNow" value="${fn:substring(ctsnnManageVO.searchToDate,0,4)}-${fn:substring(ctsnnManageVO.searchToDate,4,6)}-${fn:substring(ctsnnManageVO.searchToDate,6,8)}" />
	         </c:if>
		      <input type="text" name="searchFromDate"  id="searchFromDate" maxlength="10" value="${fromNow}" readonly="readonly"  title="<spring:message code="comUssIonCtn.ctsnnManageList.searchFromDate"/>" style="width:68px" /><!-- 경조신청 시작일자 -->
			  ~ <input type="text" name="searchToDate" id="searchToDate" maxlength="10" value="${toNow}" readonly="readonly"  title="<spring:message code="comUssIonCtn.ctsnnManageList.searchToDate"/>" style="width:68px" /><!-- 경조신청 종료일자 -->
		</div>
		<div class="con">
			<label for=""><spring:message code="comUssIonCtn.ctsnnManageList.searchNm"/> : </label><!-- 신청자 -->
			<input name="searchNm" type="text" value="${ctsnnManageVO.searchNm}"  maxlength="100" title="<spring:message code="comUssIonCtn.ctsnnManageList.searchNm"/>" /><!-- 신청자 -->
		</div>
		<div class="con">
			<label for=""><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt"/> : </label><!-- 진행구분 -->
			<select name="searchConfmAt" title="<spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt"/>"><!-- 진행구분 -->
		       	<option value=""  <c:if test="${ctsnnManageVO.searchConfmAt eq '' }">selected</c:if>><spring:message code="comUssIonCtn.ctsnnManageList.selectedAll"/></option><!-- 전체 -->
		       	<option value="A" <c:if test="${ctsnnManageVO.searchConfmAt eq 'A' }">selected</c:if>><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt.A"/></option><!-- 신청중 -->
		       	<option value="C" <c:if test="${ctsnnManageVO.searchConfmAt eq 'C' }">selected</c:if>><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt.C"/></option><!-- 승인 -->
		       	<option value="R" <c:if test="${ctsnnManageVO.searchConfmAt eq 'R' }">selected</c:if>><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt.R"/></option><!-- 반려 -->
	      	</select>
		</div>
		
		<div class="bt_a">
			<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="<spring:message code="title.inquire"/>" onclick="fncSelectCtsnnManageList('1'); return false;" /><!-- 조회 -->
			<span class="btn_b"><a href="<c:url value='/uss/ion/ctn/EgovCtsnnRegist.do'/>" onclick="fncCtsnnRegist(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
		</div>
	</div>
	</form>	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.searchKeyword"/></th><!-- 경조구분 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.ctsnnCdNm"/></th><!-- 경조명 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.usNm"/></th><!-- 신청자 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.orgnztNm"/></th><!-- 소속 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.occurDate"/></th><!-- 발생일자 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt"/></th><!-- 진행구분 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.sanctnDt"/></th><!-- 승인일자 -->
			   <th scope="col"><spring:message code="comUssIonCtn.ctsnnManageList.sanctnerNm"/></th><!-- 승인자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ctsnnManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(ctsnnManageVO.pageIndex - 1) * ctsnnManageVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.ctsnnCdNm  }"/></td>
				<td>
				  <form name="item" method="post" action="<c:url value='/uss/ion/ctn/EgovCtsnnManageDetail.do'/>">
<%-- 		           	<input type="hidden" name="ctsnnId"    value="<c:out value="${resultInfo.ctsnnId    }"/>"> --%>
		            <span class="link"><input type="submit" value="<c:out value="${resultInfo.ctsnnNm}"/>" onclick="fncCtsnnManageDetail('<c:out value="${resultInfo.ctsnnId}"/>'); return false;" style="text-align : left;"></span>
		          </form>
				</td>
				<td><c:out value="${resultInfo.usNm  }"/></td>
				<td><c:out value="${resultInfo.orgnztNm   }"/></td>
				<td><c:out value="${resultInfo.occrrDe    }"/></td>
				<td>
		          <c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt.A"/></c:if><!-- 신청중 -->
		          <c:if test="${resultInfo.confmAt eq 'C'}"><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt.C"/></c:if><!-- 승인 -->
		          <c:if test="${resultInfo.confmAt eq 'R'}"><spring:message code="comUssIonCtn.ctsnnManageList.searchConfmAt.R"/></c:if><!-- 반려 -->
				</td>
				<td class="lt_textL" nowrap><c:out value="${resultInfo.sanctnDt   }"/></td>
				<td class="lt_textL" nowrap><c:out value="${resultInfo.sanctnerNm }"/></td>
			</tr>
			</c:forEach>
		
			<c:if test="${fn:length(ctsnnManageList) == 0}">
				<tr>
					<td colspan="9">
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
