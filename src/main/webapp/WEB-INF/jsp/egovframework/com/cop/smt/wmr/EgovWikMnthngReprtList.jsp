<%
/**
 * @Class Name : EgovWikMnthngReprtList.jsp
 * @Description : 주간/월간보고 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.19   장철호          최초 생성
 * @ 2018.09.27   이정은          공통컴포넌트 3.8 개선
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.19
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="copSmtWmr.wikMnthngReprtList.wikMnthngReprtList"/></title><!-- 주간/월간보고 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/cmm/jqueryui.css"/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">

	function fn_egov_init_wikmnthngreprt(){
		if(document.frm.searchBgnDe.value != ""){
			document.frm.searchBgnDe.value = document.frm.searchBgnDe.value.substring(0,4) + "-" + document.frm.searchBgnDe.value.substring(4,6) + "-" + document.frm.searchBgnDe.value.substring(6,8);
		}

		if(document.frm.searchEndDe.value != ""){
			document.frm.searchEndDe.value = document.frm.searchEndDe.value.substring(0,4) + "-" + document.frm.searchEndDe.value.substring(4,6) + "-" + document.frm.searchEndDe.value.substring(6,8);
		}
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_linkPage('1');
		}
	}
	
	function fn_egov_select_linkPage(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/smt/wmr/selectWikMnthngReprtList.do'/>";
	   	document.frm.submit();
	}

	function fn_egov_select_wikmnthngreprt() {
		document.frm.pageIndex.value = "1";
		console.log(document.frm.pageIndex.value);
		document.frm.action = "<c:url value='/cop/smt/wmr/selectWikMnthngReprtList.do'/>";

		
		var bgnDe = document.frm.searchBgnDe.value.split("-").join("");
		var endDe = document.frm.searchEndDe.value.split("-").join("");
		
		console.log(bgnDe);
		console.log(endDe);

		if(bgnDe != ""){
			if(isDate(bgnDe, "<spring:message code="copSmtWmr.wikMnthngReprtList.searchBgnDe"/>") == false) {/* 검색시작일자 */
		        return;
		    }
		}

		if(endDe != ""){
		    if(isDate(endDe, "<spring:message code="copSmtWmr.wikMnthngReprtList.searchEndDe"/>") == false) {/* 검색종료일자 */
		        return;
		    }
		}

		if(bgnDe != "" && endDe != ""){
			if(eval(bgnDe) > eval(endDe)){
				alert("<spring:message code="copSmtWmr.wikMnthngReprtList.validate.searchDeAlert"/>");/* 검색종료일자가 검색시작일자보다 빠를수 없습니다. */
				return;
			}
		}

		document.frm.submit();
	}

	function fn_egov_inqire_wikmnthngreprt(reprtId) {
		document.frm.reprtId.value = reprtId;
		document.frm.action = "<c:url value='/cop/smt/wmr/selectWikMnthngReprt.do'/>";
		document.frm.submit();
	}

	function fn_egov_insert_wikmnthngreprt(){
		document.frm.action = "<c:url value='/cop/smt/wmr/addWikMnthngReprt.do'/>";
		document.frm.submit();
	}
/* ********************************************************
 * 달력
 ******************************************************** */
	function fn_egov_init_date(){

		$("#searchBgnDe").datepicker(  
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


		$("#searchEndDe").datepicker( 
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
</script>

</head>
<body onLoad="fn_egov_init_wikmnthngreprt(); fn_egov_init_date()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="frm" method="post" action="<c:url value='/cop/smt/wmr/selectWikMnthngReprtList.do'/>">
	<h1><spring:message code="copSmtWmr.wikMnthngReprtList.wikMnthngReprtList"/></h1><!-- 주간/월간보고 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul style="text-align:left">
			<li>
				<select name="searchSttus" class="select" title="<spring:message code="input.cSelect"/>" style="margin-bottom:2px"><!-- 선택 -->
					<option value="3"><spring:message code="copSmtWmr.wikMnthngReprtList.searchSttus"/></option><!-- 승인여부 -->
					<option value="0" <c:if test="${searchVO.searchSttus == '0'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.unapproved"/></option><!-- 미승인 -->
					<option value="1" <c:if test="${searchVO.searchSttus == '1'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.approval"/></option><!-- 승인 -->
			   </select>
			   
				<select name="searchDe" class="select" title="<spring:message code="input.cSelect"/>" style="width:90px"><!-- 선택 -->
					<option value="3"><spring:message code="copSmtWmr.wikMnthngReprtList.searchDe"/></option>
					<option value="0" <c:if test="${searchVO.searchDe == '0'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.reprtDe"/></option><!-- 보고일자 -->
					<option value="1" <c:if test="${searchVO.searchDe == '1'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.reprtBgnEndDe"/></option><!-- 해당일자 -->
				</select>
				
				<input name="searchBgnDe" id="searchBgnDe" type="text" maxlength="10" value="<c:out value="${searchVO.searchBgnDe}"/>" title="<spring:message code="input.input"/>" style="width:79px" /><!-- 입력 -->
				~<input name="searchEndDe" id="searchEndDe" type="text" maxlength="10" value="<c:out value="${searchVO.searchEndDe}"/>" title="<spring:message code="input.input"/>" style="width:79px" /><!-- 입력 -->
				<br />
				
				<select name="searchSe" class="select" title="<spring:message code="input.cSelect"/>"><!-- 선택 -->
					<option value="3"><spring:message code="copSmtWmr.wikMnthngReprtList.searchSe"/></option><!-- 보고유형 -->
					<option value="1" <c:if test="${searchVO.searchSe == '1'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.WeeklyReport"/></option><!-- 주간보고 -->
					<option value="2" <c:if test="${searchVO.searchSe == '2'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.MonthlyReport"/></option><!-- 월간보고 -->
				</select>
				<select name="searchCnd" class="select" title="<spring:message code="input.cSelect"/>"><!-- 선택 -->
					<option value="3"><spring:message code="copSmtWmr.wikMnthngReprtList.searchCnd"/></option><!-- 제목/작성자 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.reprtSj"/></option><!-- 제목 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="copSmtWmr.wikMnthngReprtList.wrterNm"/></option><!-- 작성자 -->
				</select>
				<input name="searchWrd" type="text" value="<c:out value="${searchVO.searchWrd}"/>" maxlength="35" onkeypress="press(event);" title="<spring:message code="title.search"/>" style="width:380px" /><!-- 검색어  -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire"/>" title="<spring:message code="button.inquire"/>" onclick="fn_egov_select_wikmnthngreprt(); return false;" /><!-- 조회 -->
				<input class="s_btn" type="submit" value="<spring:message code="button.create"/>" title="<spring:message code="button.create"/>" onclick="fn_egov_insert_wikmnthngreprt(); return false;" /><!-- 등록 -->
			</li>
		</ul>
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:30%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 번호 -->
			   <th scope="col"><spring:message code="copSmtWmr.wikMnthngReprtList.searchSe"/></th><!-- 보고유형 -->
			   <th scope="col"><spring:message code="copSmtWmr.wikMnthngReprtList.reprtDe"/></th><!-- 보고일자 -->
			   <th scope="col"><spring:message code="copSmtWmr.wikMnthngReprtList.reprtSuj"/></th><!-- 보고서제목 -->
			   <th scope="col"><spring:message code="copSmtWmr.wikMnthngReprtList.reprtBgnEndDe"/></th><!-- 해당일자 -->
			   <th scope="col"><spring:message code="copSmtWmr.wikMnthngReprtList.wrterNm"/></th><!-- 작성자 -->
			   <th scope="col"><spring:message code="copSmtWmr.wikMnthngReprtList.approval"/></th><!-- 승인 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.reprtSe}"/></td>
			    <td><c:out value="${result.reprtDe}"/></td>
			    <td>
			     <form name="wikMnthngReprtVO" method="post" action="<c:url value='/cop/smt/wmr/selectWikMnthngReprt.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
			    	<input name="searchDe" type="hidden" value="<c:out value='${searchVO.searchDe}'/>">
			    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
			    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
			    	<input name="searchSttus" type="hidden" value="<c:out value='${searchVO.searchSttus}'/>">
					<input type="hidden" name="reprtId" value="<c:out value="${result.reprtId}"/>">
					<span class="link"><input type="submit" value="<c:out value="${result.reprtSj}"/>" onclick="fn_egov_inqire_wikmnthngreprt('<c:out value="${result.reprtId}"/>'); return false;" style="text-align : left;"></span>
				 </form>
				</td>
				<td><c:out value="${result.reprtBgnDe}"/>~<c:out value="${result.reprtEndDe}"/></td>
				<td><c:out value="${result.wrterNm}"/></td>
			    <td><c:out value="${result.confmDt}"/></td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="7"><spring:message code="common.nodata.msg" /></td>
			  </tr>
			 </c:if>
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
