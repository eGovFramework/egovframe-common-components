<%
/**
 * @Class Name  : EgovComUtlHttpMonLogList.jsp
 * @Description : EgovComUtlHttpMonLogList 화면
 * @Modification Information
 * @
 * @  수정일             수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2010.09.7    박종선                  최초 생성
 *
 *  @author 공통서비스팀
 *  @since 2010.05.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUtlSysHtm.comUtlHttpMonLogList.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>${pageTitle}</title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

		<script type="text/javascript">

		function fn_egov_init_httpMonLog(){
			document.frm.searchBgnHour.value = '<c:out value="${searchVO.searchBgnHour}"/>';
			document.frm.searchEndHour.value = '<c:out value="${searchVO.searchEndHour}"/>';

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
			
			document.frm.searchKeyword.focus();
		}

		function press(event) {
			if (event.keyCode==13) {
				fn_egov_select_httpMonLog('1');
			}
		}

		function fn_egov_select_httpMonLog(pageNo) {
			document.frm.pageIndex.value = pageNo;
			document.frm.action = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonLogList.do'/>";

			var bgnDe = document.frm.searchBgnDe.value;
			var endDe = document.frm.searchEndDe.value;

			if(bgnDe != ""){
				if(isDate(bgnDe, "검색시작일자") == false) {
			        return;
			    }
			}

			if(endDe != ""){
			    if(isDate(endDe, "검색종료일자") == false) {
			        return;
			    }
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
					alert("검색종료일시가 검색시작일시보다 빠를수 없습니다.");
					return;
				}
			}
			document.frm.submit();
		}

		function fn_egov_inqire_httpMonLog(webKind, logId) {
			document.frm.webKind.value = webKind;
			document.frm.logId.value = logId;
			document.frm.action = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonDetailLog.do'/>";
			document.frm.submit();
		}

		/* ********************************************************
		 * 마스터목록조회 함수
		 ******************************************************** */
		function fnSearch(){
			location.href = "<c:url value='/utl/sys/htm/EgovComUtlHttpMonList.do'/>";
		}
		</script>
	</head>

	<body onLoad="fn_egov_init_httpMonLog()">

	<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

	<div class="board">

		<h1>${pageTitle}</h1>

			<form name="frm" method="post" action="<c:url value='/utl/sys/prm/EgovComUtlProcessMonLogList.do'/>">

			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			<input type="hidden" name="webKind">
			<input type="hidden" name="logId">


		<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
			<ul>
				<li>
					<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
					<input name="searchBgnDe" id="searchBgnDe" type="text" size="10" maxlength="10" value="<c:out value="${searchVO.searchBgnDe}"/>" title="<spring:message code="comUtlSysPrm.comUtlProcessMon.searchBgnDe" />"><!-- 조회시작일자 입력 -->
    				<select name="searchBgnHour" class="select" title="<spring:message code="comUtlSysPrm.comUtlProcessMon.searchBgnHour" />"><!-- 조회시작 시 선택 -->
	    				<c:forEach var="bgnHour" items="${searchBgnHour}" varStatus="status">
            			<option value="<c:out value="${bgnHour.code}"/>"><c:out value="${bgnHour.codeNm}"/></option>
            			</c:forEach>
       				</select>
    				~
    				<input name="searchEndDe" id="searchEndDe"type="text" size="10" maxlength="10" value="<c:out value="${searchVO.searchEndDe}"/>" title="<spring:message code="comUtlSysPrm.comUtlProcessMon.searchEndDe" />"><!-- 조회종료일자 입력 -->
				  	<select name="searchEndHour" class="select" title="<spring:message code="comUtlSysPrm.comUtlProcessMon.searchEndHour" />"><!-- 조회종료 시 선택 -->
				  		<c:forEach var="endHour" items="${searchEndHour}" varStatus="status">
			            <option value="<c:out value="${endHour.code}"/>"><c:out value="${endHour.codeNm}"/></option>
			            </c:forEach>
			        </select>
			        
			        <select name="searchCondition" class="select" title="<spring:message code="select.searchCondition" />">
						<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
						<option value="1" <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUtlSysHtm.comUtlHttpMon.webService" /></option><!-- 웹서비스종류 -->
						<option value="2" <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comUtlSysHtm.comUtlHttpMon.status" /></option><!-- 상태 -->
				   	</select>
				   	<input name="searchKeyword" type="text" size="27" value='<c:out value="${searchVO.searchKeyword}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">

					<!-- <input class="s_input2 vat" name="searchKeyword" type="text" value="" size="25" onkeypress="press();" title="사용자명검색" /> -->
					<input class="s_btn" type="submit" value="<spring:message code="button.search" />" title="<spring:message code="button.search" />" onclick="fn_egov_select_httpMonLog('1'); return false;" />
					<input class="s_btn" type="submit" value="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick="fnSearch(); return false;" />
				</li>
			</ul>
		</div>

			</form>

		<table class="board_list">
			<caption></caption>
			<colgroup>
				<col style="width:9%" />
				<col style="width:22%" />
				<col style="width:15%" />
				<col style="width:22%" />
				<col style="width:9%" />
				<col style="width:23%" />
			</colgroup>
			<thead>
				<tr>
				   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.seq" /></th><!-- 순번 -->
				   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.logID" /></th><!-- 로그ID -->
				   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.webService" /></th><!-- 웹서비스종류 -->
				   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.systemURL" /></th><!-- 시스템URL -->
				   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.status" /></th><!-- 상태 -->
				   <th scope="col"><spring:message code="comUtlSysHtm.comUtlHttpMon.createdDateTime" /></th><!-- 생성일시 -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
			  		<tr>
					    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td><c:out value="${result.logId}"/></td>
					    <td>
						    	<form name="httpMonLogVO" method="post" action="<c:url value='EgovComUtlHttpMonLogList.do'/>">
						    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
						    	<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>">
						    	<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>">
						    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
						    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
						    	<input name="searchBgnHour" type="hidden" value="<c:out value='${searchVO.searchBgnHour}'/>">
						    	<input name="searchEndHour" type="hidden" value="<c:out value='${searchVO.searchEndHour}'/>">
								<span class="link"><input type="submit" value="<c:out value="${result.webKind}"/>"
								onclick="javascript:fn_egov_inqire_httpMonLog('<c:out value="${result.webKind}"/>'
								, '<c:out value="${result.logId}"/>'); return false;" style="text-align : left;"></span>
							 	</form>
						</td>
						<td><c:out value="${result.siteUrl}"/></td>
						<td><c:out value="${result.httpSttusCd}"/></td>
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
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_processMonLog"/>
			</ul>
		</div>

	</div>
	</body>
</html>