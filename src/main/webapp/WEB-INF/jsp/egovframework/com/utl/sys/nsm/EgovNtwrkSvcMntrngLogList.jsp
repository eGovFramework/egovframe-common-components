<%
/**
 * @Class Name : EgovNtwrkSvcMntrngLogList.jsp
 * @Description : 네트워크서비스모니터링 로그 목록조회
 * @Modification Information
 * @
 * @ 수정일               수정자          수정내용
 * @ ----------   --------  ---------------------------
 * @ 2010.08.17   장철호          최초 생성
 *   2018.11.02   신용호          표준프레임워크 v3.8 개선
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.17
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript">

	function initCalendar() {
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

	function fn_egov_init_ntwrksvcmntrnglog(){
		initCalendar();
		document.frm.searchBgnHour.value = '<c:out value="${searchVO.searchBgnHour}"/>';
		document.frm.searchEndHour.value = '<c:out value="${searchVO.searchEndHour}"/>';
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_ntwrksvcmntrnglog('1');
		}
	}

	function fn_egov_select_ntwrksvcmntrnglog(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>";

		var bgnDe = document.frm.searchBgnDe.value;
		var endDe = document.frm.searchEndDe.value;

		if(bgnDe != ""){
			if(isDate(bgnDe, "<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.validate.alert.bgnDe" />") == false) { //검색시작일자
		        return;
		    }
		}

		if(endDe != ""){
		    if(isDate(endDe, "<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.validate.alert.endDe" />") == false) { //검색종료일자
		        return;
		    }
		}

		if(bgnDe.length == 8){
			bgnDe = bgnDe.substring(0,4) + "-" + bgnDe.substring(4,6) + "-" + bgnDe.substring(6,8);
			document.frm.searchBgnDe.value = bgnDe;
		}

		if(endDe.length == 8){
			endDe = endDe.substring(0,4) + "-" + endDe.substring(4,6) + "-" + endDe.substring(6,8);
			document.frm.searchEndDe.value = endDe;
		}

		var bgnHour = document.frm.searchBgnHour.value;
		var endHour = document.frm.searchEndHour.value;

		var bgnDeHour = "";
		var endDeHour = "";
		if(bgnDe != "" && endDe != ""){
			if(bgnHour == ""){
				document.frm.searchBgnHour.value = "00";
				bgnHour = "00";
			}
			if(endHour == ""){
				document.frm.searchEndHour.value = "00";
				endHour = "00";
			}
			bgnDeHour = bgnDe + bgnHour;
			endDeHour = endDe + endHour;

			if(bgnDeHour > endDeHour){
				alert("<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.validate.alert.diffBgnDeEndDe" />"); //검색종료일시가 검색시작일시보다 빠를수 없습니다.
				return;
			}
		}

		document.frm.submit();
	}

	function fn_egov_inqire_ntwrksvcmntrnglog(sysIp, sysPort, logId) {
		document.frm.sysIp.value = sysIp;
		document.frm.sysPort.value = sysPort;
		document.frm.logId.value = logId;
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLog.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_ntwrksvcmntrng(){
		document.frm.action = "<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>";
		document.frm.submit();
	}

</script>
</head>
<body onLoad="fn_egov_init_ntwrksvcmntrnglog()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

	<form name="frm" method="post" action="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" name="sysIp">
	<input type="hidden" name="sysPort">
	<input type="hidden" name="logId">

	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
				<input id="searchBgnDe" name="searchBgnDe" type="text" size="10" maxlength="10" value="<c:out value="${searchVO.searchBgnDe}"/>" title="<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title.searchBgnDe" />"><!-- 조회시작일자 입력 -->
			    <select name="searchBgnHour" class="select" title="<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title.searchBgnHour" />"><!-- 조회시작 시간 선택 -->
			    	<c:forEach var="bgnHour" items="${searchBgnHour}" varStatus="status">
		            <option value="<c:out value="${bgnHour.code}"/>"><c:out value="${bgnHour.codeNm}"/></option>
		            </c:forEach>
		        </select>
			    ~
			    <input id="searchEndDe" name="searchEndDe" type="text" size="10" maxlength="10" value="<c:out value="${searchVO.searchEndDe}"/>" title="<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title.searchEndDe" />"><!-- 조회종료일자 입력 -->
			  	<select name="searchEndHour" class="select" title="<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title.searchEndHour" />"><!-- 조회종료 시간 선택 -->
			  		<c:forEach var="endHour" items="${searchEndHour}" varStatus="status">
		            <option value="<c:out value="${endHour.code}"/>"><c:out value="${endHour.codeNm}"/></option>
		            </c:forEach>
		        </select>
		        
		        <select name="searchCnd" class="select" title="<spring:message code='common.searchCondition.msg' />">
					<option value="">--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysNm.label" /></option>
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysIp.label" /></option>
					<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.mngrNm.label" /></option>
					<option value="3" <c:if test="${searchVO.searchCnd == '3'}">selected</c:if> ><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.mntrngSttus.label" /></option>
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" size="27" onkeypress="press(event);" title="<spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.title.searchWrd" />" /><!-- 검색어 입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code='title.inquire' />" title="<spring:message code='title.inquire' />" onclick="fn_egov_select_ntwrksvcmntrnglog('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngList.do'/>" onclick="fn_egov_select_ntwrksvcmntrng(); return false;" title="<spring:message code='title.list' />"><spring:message code='title.list' /></a></span><!-- 목록 -->
			</li>
		</ul>
	</div>
	
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:30%" />
			<col style="width:14%" />
			<col style="width:16%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.num.label" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysIp.label" /></th><!-- 시스템IP -->
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysPort.label" /></th><!-- 시스템포트 -->
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.sysNm.label" /></th><!-- 시스템명 -->
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.mntrngSttus.label" /></th><!-- 모니터링상태 -->
			   <th scope="col"><spring:message code="comUtlSysNsm.ntwrkSvcMntrngLog.creatDt.label" /></th><!-- 생성일시 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.sysIp}"/></td>
			    <td><c:out value="${result.sysPort}"/></td>
			    <td>
			     <form name="ntwrkSvcMntrngVO" method="post" action="<c:url value='/utl/sys/nsm/selectNtwrkSvcMntrngLog.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
			    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
			    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
			    	<input name="searchBgnHour" type="hidden" value="<c:out value='${searchVO.searchBgnHour}'/>">
			    	<input name="searchEndHour" type="hidden" value="<c:out value='${searchVO.searchEndHour}'/>">
					<input type="hidden" name="sysIp" value="<c:out value="${result.sysIp}"/>">
					<input type="hidden" name="sysPort" value="<c:out value="${result.sysPort}"/>">
					<input type="hidden" name="logId" value="<c:out value="${result.logId}"/>">
					<span class="link"><input type="submit" value="<c:out value="${result.sysNm}"/>" onclick="javascript:fn_egov_inqire_ntwrksvcmntrnglog('<c:out value="${result.sysIp}"/>', '<c:out value="${result.sysPort}"/>', '<c:out value="${result.logId}"/>'); return false;" style="text-align : left;"></span>
				 </form>
				</td>
				<td><c:out value="${result.mntrngSttus}"/></td>
			    <td><c:out value="${result.creatDt}"/></td>
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
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_ntwrksvcmntrnglog"/>
		</ul>
	</div>
</div>

</body>
</html>