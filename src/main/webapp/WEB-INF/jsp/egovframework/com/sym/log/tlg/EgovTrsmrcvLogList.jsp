<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymLogTlg.trsmrcvLog.title"/> <spring:message code="title.list" /></c:set>
<%
 /**
  * @Class Name : EgovTrsmrcvLogList.jsp
  * @Description : 송수신 로그 정보목록 화면
  * @Modification Information
  * @
  * @  수정일         수정자             수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.11      이삼섭		최초 생성
  * @ 2011.07.08      이기하		패키지 분리로 경로 수정(sym.log -> sym.log.tlg)
  *   2011.09.14      서준식		검색 후 화면에 검색일자 표시안되는 오류 수정
  *	  2024.10.29      ryeon8		정의되지 않은 함수 호출에 따른 콘솔 오류 제거
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.11
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js'/>" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
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
		
		document.frm.searchWrd.focus();

	}

	function fn_egov_select_trsmrcvLog(pageNo){
		var fromDate = document.frm.searchBgnDe.value;
		var toDate = document.frm.searchEndDe.value;

		if(fromDate > toDate) {
			alert("<spring:message code="comSymLogTlg.validate.dateCheck" />");  //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	        //return false;
	    } else {
			document.frm.pageIndex.value = pageNo;
			document.frm.action = "<c:url value='/sym/log/tlg/SelectTrsmrcvLogList.do'/>";
			document.frm.submit();
	    }
	}

	function fn_egov_add_trsmrcvLog(){
		document.frm.action = "<c:url value='/sym/log/tlg/AddTrsmrcvLog.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_trsmrcvLog(requstId){
		var url = "<c:url value='/sym/log/tlg/InqireTrsmrcvLog.do' />?requstId="+requstId;

		var openParam = "scrollbars=yes,toolbar=0,location=no,resizable=0,status=0,menubar=0,width=750,height=360,left=0,top=0";
		window.open(url,"p_trsmrcvLogInqire", openParam);
	}
</script>
</head>
<body onload="fn_egov_init_date();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>

<form name="frm" action ="<c:url value='/sym/log/tlg/SelectTrsmrcvLogList.do'/>" method="post">
<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<spring:message code="comSymLogTlg.trsmrcvLog.occrrncDe" /><!-- 발생일자 -->
				<input type="text" name="searchBgnDe" id="searchBgnDe" size="15" maxlength="10" value="${searchVO.searchBgnDe}" title="<spring:message code="comSymLogPlg.seachWrd.searchBeginDate" />" > ~ <!-- 검색시작일  -->
				<input type="text" name="searchEndDe" id="searchEndDe" size="15" maxlength="10" value="${searchVO.searchEndDe}" title="<spring:message code="comSymLogPlg.seachWrd.searchEndDate" />" >&nbsp;<!-- 검색종료일  -->
				<label for="" style="margin-left:10px"><spring:message code="comSymLogTlg.trsmrcvLog.trsmrcvType" /> : </label> <!-- 송수신구분 -->
				<input id="searchWrd" class="s_input2 vat" name="searchWrd" type="text" value='<c:out value='${searchVO.searchWrd}'/>' size="15" maxlength="15" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  />
				<input class="s_btn" type="button" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_trsmrcvLog('1'); return false;" /> <!-- 조회 -->
				<input class="s_btn" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.test" />" title="<spring:message code="comSymLogTlg.trsmrcvLog.test" />" onclick="fn_egov_add_trsmrcvLog(); return false;" /> <!-- 송수신테스트 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:10%" />			
			<col style="width:15%" />
			<col style="width:5%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="table.num" /></th> <!-- 번호 -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.requestId" /></th> <!-- 요청ID -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.occrrncDe" /></th> <!-- 발생일자 -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.trsmrcvType" /></th> <!-- 송수신구분 -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.linkId" /></th> <!-- 연계ID -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.providerId" /></th> <!-- 제공기관ID -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.requestOrgId" /></th> <!-- 요청기관ID -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.requestNm" /></th> <!-- 요청자 -->
				<th scope="col"><spring:message code="comSymLogTlg.trsmrcvLog.detail" /></th> <!-- 상세보기 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td><c:out value="${result.requstId}"/></td>
				<td><c:out value="${result.occrrncDe}"/></td>
				<td><c:out value="${result.trsmrcvSeCodeNm}"/></td>
				<td><c:out value="${result.cntcId}"/></td>
				<td><c:out value="${result.provdInsttId}"/></td>
				<td><c:out value="${result.requstInsttId}"/></td>
				<td><c:out value="${result.rqsterNm}"/></td>
				<td>
					<a href="#noscript" onclick="fn_egov_inqire_trsmrcvLog('<c:out value="${result.requstId}"/>'); return false;" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>"  alt="<spring:message code="title.detail" />" title="<spring:message code="title.detail" />"  /></a>
				</td>
			</tr>
			</c:forEach>
		
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
			<td class="lt_text3" colspan="9">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_trsmrcvLog"/>
		</ul>
	</div>
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>	
</div>



</body>
</html>
