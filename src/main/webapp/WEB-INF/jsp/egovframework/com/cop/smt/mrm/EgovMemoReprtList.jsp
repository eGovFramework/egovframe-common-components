<%
/**
 * @Class Name : EgovMemoReprtList.jsp
 * @Description : 메모보고 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.22   장철호          최초 생성
 * @ 2018.09.06   최두영          V3.8 오류 개선 및 퍼블리싱 점검
 * @ 2018.09.17   최두영          다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.22
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
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtMrm.memoReprtList.title"/></title><!--메모보고 목록조회-->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript">

	function initCalendar(){
		$("#searchBgnDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'both'
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
		         , showOn: 'both'
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

  	function fn_egov_init_memoreprt(){
		if(document.frm.searchBgnDe.value != ""){
			document.frm.searchBgnDe.value = document.frm.searchBgnDe.value.substring(0,4) + "-" + document.frm.searchBgnDe.value.substring(4,6) + "-" + document.frm.searchBgnDe.value.substring(6,8);
		}

		if(document.frm.searchEndDe.value != ""){
			document.frm.searchEndDe.value = document.frm.searchEndDe.value.substring(0,4) + "-" + document.frm.searchEndDe.value.substring(4,6) + "-" + document.frm.searchEndDe.value.substring(6,8);
		}
	}  

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_memoreprt('1');
		}
	}

	function linkPage(pageNo) {
		fn_egov_select_memoreprt(pageNo);
	}
	
	function fn_egov_select_memoreprt(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/smt/mrm/selectMemoReprtList.do'/>";

		var bgnDe = document.frm.searchBgnDe.value.split("-").join("");
		var endDe = document.frm.searchEndDe.value.split("-").join("");

		if(bgnDe != ""){
			if(isDate(bgnDe, "보고시작일자") == false) {
		        return;
		    }
		}

		if(endDe != ""){
		    if(isDate(endDe, "보고종료일자") == false) {
		        return;
		    }
		}

		if(bgnDe != "" && endDe != ""){
			if(eval(bgnDe) > eval(endDe)){
				alert("보고종료일자가 보고시작일자보다 빠를수 없습니다.");
				return;
			}
		}

		document.frm.submit();
	}
	
	/* ********************************************************
	 * 메모보고 상세조회로 가기
	 ******************************************************** */

	function fn_egov_inqire_memoreprt(reprtId) {
		document.frm.reprtId.value = reprtId;
		document.frm.action = "<c:url value='/cop/smt/mrm/selectMemoReprt.do'/>";
		document.frm.submit();
	}
	/* ********************************************************
	 * 메모보고 등록으로 가기
	 ******************************************************** */
	function fn_egov_insert_memoreprt(){
		document.frm.action = "<c:url value='/cop/smt/mrm/addMemoReprt.do'/>";
		document.frm.submit();
	}
</script>
</head>
<body onLoad="fn_egov_init_memoreprt(); initCalendar();"><!--  fn_egov_init_memoreprt(); -->

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comCopSmtMrm.memoReprtList.title"/></h1><!-- 메모보고 목록 -->

	<form name="frm" method="post" action="<c:url value='/cop/smt/mrm/selectMemoReprtList.do'/>">
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="reprtId">
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<div class="con">
			<label for=""><spring:message code="select.searchCondition" /> : </label><!-- 조회조건 선택 -->
			<select name="searchSttus" class="select" title="<spring:message code="comCopSmtMrm.memoReprtList.select"/>"><!-- 승인여부 선택 -->
				<option value=''><spring:message code="comCopSmtMrm.memoReprtList.searchSttus"/></option><!-- 보고서상태 -->
				<option value="0" <c:if test="${searchVO.searchSttus == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchSttus0"/></option><!-- 미확인 -->
				<option value="1" <c:if test="${searchVO.searchSttus == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchSttus1"/></option><!-- 확인 -->
			</select>
		</div>
		
		<div class="con">
			<label for=""><spring:message code="comCopSmtMrm.memoReprtList.reportDate"/> : </label>
			<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			<input name="searchBgnDe" id="searchBgnDe" type="text" size="10" maxlength="10" readonly="readonly" value="<c:out value="${searchVO.searchBgnDe}"/>" title="<spring:message code="comCopSmtMrm.memoReprtList.searchBgnDe"/>"><!-- 조회시작일자 입력 -->
		    ~
		    <input name="searchEndDe" id="searchEndDe" type="text" size="10" maxlength="10" readonly="readonly" value="<c:out value="${searchVO.searchEndDe}"/>" title="<spring:message code="comCopSmtMrm.memoReprtList.searchEndDe"/>"><!-- 조회종료일자 입력 -->
		</div>
		
		<div class="con full">
			<select name="searchDrctMatter" class="select" title="<spring:message code="comCopSmtMrm.memoReprtList.searchDrctMatterYN"/>" style="margin-left:74px"><!-- 의견등록여부 선택 -->
				<option value=''><spring:message code="comCopSmtMrm.memoReprtList.searchDrctMatterYN"/></option><!-- 의견등록 여부 -->
				<option value="0" <c:if test="${searchVO.searchDrctMatter == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchDrctMatter0"/></option><!-- 미등록 -->
				<option value="1" <c:if test="${searchVO.searchDrctMatter == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchDrctMatter1"/></option><!-- 등록 -->
			</select>
			<select name="searchCnd" class="select" title="<spring:message code="comCopSmtMrm.memoReprtList.searchCnd"/>"><!-- 조회조건 선택 -->
				<option value=''><spring:message code="table.select" /></option><!-- 선택 -->
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchCnd0"/></option><!-- 보고제목 -->
				<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchCnd1"/></option><!-- 보고내용 -->
				<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.memoReprtList.searchCnd2"/></option><!-- 작성자 -->
			</select>
	   
			<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' size="35" onkeypress="press(event);" title="<spring:message code="title.search"/> <spring:message code="input.input"/>" style="width:276px" />
		</div>
		
		<div class="bt_a">
			<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="fn_egov_select_memoreprt('1'); return false;" /><!-- 조회 -->
			<input class="s_btn" type="submit" value="<spring:message code="title.create"/>" title="<spring:message code="title.create"/>" onclick="fn_egov_insert_memoreprt('1'); return false;" /><!-- 등록 -->
		</div>
	</div>
	</form>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:45%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtDe"/></th><!-- 보고일자 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.memoReprtVO.validate.reprtSj"/></th><!-- 보고서제목 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.memoReprtVO.validate.wrterNm"/></th><!-- 작성자 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.memoReprtList.searchSttus"/></th><!-- 보고서 상태 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.memoReprtList.searchDrctMatter"/></th><!-- 의견 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.reprtDe}"/></td>
			    <td>
			     <form name="memoReprtVO" method="post" action="<c:url value='/cop/smt/mrm/selectMemoReprt.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
			    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
			    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
			    	<input name="searchSttus" type="hidden" value="<c:out value='${searchVO.searchSttus}'/>">
			    	<input name="searchDrctMatter" type="hidden" value="<c:out value='${searchVO.searchDrctMatter}'/>">
					<input type="hidden" name="reprtId" value="<c:out value="${egovc:encryptId(result.reprtId)}"/>">
					<span class="link" ><input type="submit" value="<c:out value="${result.reprtSj}"/>" onclick="fn_egov_inqire_memoreprt('<c:out value="${egovc:encryptId(result.reprtId)}"/>'); return false;"  style="text-align : left;"></span>			
				 </form>
				</td>
				<td><c:out value="${result.wrterNm}"/></td>
			    <td><c:out value="${result.reportrInqireDt}"/></td>
			    <td><c:out value="${result.drctMatterRegistDt}"/></td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="6"><spring:message code="common.nodata.msg" /></td>
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
