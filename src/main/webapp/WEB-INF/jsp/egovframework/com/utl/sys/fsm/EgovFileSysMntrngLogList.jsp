<%
/**
 * @Class Name : EgovFileSysMntrngLogList.jsp
 * @Description : 파일시스템모니터링 로그 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.08.18   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.08.18
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUtlSysFsm.fileSysMntrngLog.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle} <spring:message code="title.list" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js' />"></script>
<script type="text/javascript">

	function fn_egov_init_filesysmntrng(){
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
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_filesysmntrnglog('1');
		}
	}

	function fn_egov_select_filesysmntrnglog(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngLogList.do'/>";

		var bgnDe = document.frm.searchBgnDe.value;
		var endDe = document.frm.searchEndDe.value;

		if(bgnDe != ""){
			if(isDate(bgnDe, "<spring:message code="comUtlSysFsm.fileSysMntrngLogList.validate.bgnDe"/>") == false) { /* 검색시작일자 */
		        return;
		    }
		}

		if(endDe != ""){
		    if(isDate(endDe, "<spring:message code="comUtlSysFsm.fileSysMntrngLogList.validate.endDe"/>") == false) { /* 검색종료일자 */
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
				alert("<spring:message code="comUtlSysFsm.fileSysMntrngLogList.validate.bgnDeHourendDeHour"/>");/* 검색종료일시가 검색시작일시보다 빠를수 없습니다. */
				return;
			}
		}
		document.frm.submit();
	}

	function fn_egov_inqire_filesysmntrnglog(fileSysId, logId) {
		document.frm.fileSysId.value = fileSysId;
		document.frm.logId.value = logId;
		document.frm.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngLog.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_filesysmntrng(){
		document.frm.action = "<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>";
		document.frm.submit();
	}


</script>
</head>
<body onLoad="fn_egov_init_filesysmntrng()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>

		<form name="frm" method="post" action="<c:url value='/utl/sys/fsm/selectFileSysMntrngLogList.do'/>">

		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input type="hidden" name="fileSysId">
		<input type="hidden" name="logId">
		
		<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
			<ul>
				<li>
					<input name="searchBgnDe" id="searchBgnDe" type="text" size="10" maxlength="10" value="<c:out value="${searchVO.searchBgnDe}"/>" title="조회시작일자 입력">
				    <select name="searchBgnHour" class="select" title="조회시작 시 선택">
				    	<c:forEach var="bgnHour" items="${searchBgnHour}" varStatus="status">
			            <option value="<c:out value="${bgnHour.code}"/>"><c:out value="${bgnHour.codeNm}"/></option>
			            </c:forEach>
			        </select>
				    ~
				    <input name="searchEndDe" id="searchEndDe" type="text" size="10" maxlength="10" value="<c:out value="${searchVO.searchEndDe}"/>" title="조회종료일자 입력">
				  	<select name="searchEndHour" class="select" title="조회종료 시 선택">
				  		<c:forEach var="endHour" items="${searchEndHour}" varStatus="status">
			            <option value="<c:out value="${endHour.code}"/>"><c:out value="${endHour.codeNm}"/></option>
			            </c:forEach>
			        </select>
			        
			        <select name="searchCnd" class="select" title="조회조건 선택">
						<option value=''>--<spring:message code="comUtlSysFsm.fileSysMntrngLogList.select"/>--</option><!-- 선택하세요 -->
						<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysNm.label" /></option>
						<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysManageNm.label" /></option>
						<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngLogList.mngrNm.label" /></option>
						<option value="3" <c:if test="${searchVO.searchCnd == '3'}">selected="selected"</c:if> ><spring:message code="comUtlSysFsm.fileSysMntrngLogList.mntrngSttus.label" /></option>
					</select>
					<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" size="27" onkeypress="press(event);" title="검색어 입력" />
					<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="fn_egov_select_filesysmntrnglog('1'); return false;" /><!-- 조회 -->
					<span class="btn_b"><a href="<c:url value='/utl/sys/fsm/selectFileSysMntrngList.do'/>" onclick="fn_egov_select_filesysmntrng(); return false;" title="<spring:message code="comUtlSysFsm.fileSysMntrngLogList.list"/>"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.list"/></a></span><!-- 목록 -->
				</li>
			</ul>	
		</div>
	</form>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:17%" />
			<col style="width:17%" />
			<col style="width:8%" />
			<col style="width:11%" />
			<col style="width:11%" />
			<col style="width:10%" />
			<col style="width:16%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.seq.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysManageNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysMg.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysThrhld.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.fileSysUsgQty.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.mntrngSttus.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysFsm.fileSysMntrngLogList.creatDt.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.fileSysNm}"/></td>
			    <td>
			     <form name="fileSysMntrngLogVO" method="post" action="<c:url value='/utl/sys/fsm/selectFileSysMntrngLog.do'/>">
			    	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			    	<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
			    	<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
			    	<input name="searchBgnDe" type="hidden" value="<c:out value='${searchVO.searchBgnDe}'/>">
			    	<input name="searchEndDe" type="hidden" value="<c:out value='${searchVO.searchEndDe}'/>">
			    	<input name="searchBgnHour" type="hidden" value="<c:out value='${searchVO.searchBgnHour}'/>">
			    	<input name="searchEndHour" type="hidden" value="<c:out value='${searchVO.searchEndHour}'/>">
					<input type="hidden" name="fileSysId" value="<c:out value="${result.fileSysId}"/>">
					<span class="link"><input type="submit" value="<c:out value="${result.fileSysManageNm}"/>" onclick="javascript:fn_egov_inqire_filesysmntrnglog('<c:out value="${result.fileSysId}"/>', '<c:out value="${result.logId}"/>'); return false;" style="text-align : left;"></span>
				 </form>
				</td>
				<td><c:out value="${result.fileSysMg}"/>G</td>
				<td><c:out value="${result.fileSysThrhldRt}"/>%(<c:out value="${result.fileSysThrhld}"/>G)</td>
				<td><c:out value="${result.fileSysUsgRt}"/>%(<c:out value="${result.fileSysUsgQty}"/>G)</td>
				<td><c:out value="${result.mntrngSttus}"/></td>
			    <td><c:out value="${result.creatDt}"/></td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="8"><spring:message code="common.nodata.msg" /></td>
			  </tr>
			 </c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_filesysmntrnglog"/>
		</ul>
	</div>
</div>
</body>
</html>