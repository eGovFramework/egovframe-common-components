<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovSanctnerList.jsp
 * @Description : 약식결재자 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.06.28   장철호          최초 생성
 * @ 2018.09.12   최두영          퍼블리싱 점검 및 다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.06.28
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonIsm.sanctnerList.title" /></title><!-- 사용자 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">
	function fnInit(){
		getDialogArguments();
		
		var openerdata = parent.window.dialogArguments;
		document.frm.title.value = openerdata[1]; 
		parent.document.title = openerdata[1] + " <spring:message code="title.list" /> <spring:message code="title.inquire" />"; /* 목록조회 */
	}
	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_sanctner('1');
		}
	}
	
	function fn_egov_select_sanctner(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/uss/ion/ism/selectSanctnerList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_sanctner(uniqId, emplNo, emplyrNm, orgnztNm) {
		getDialogArguments();
		
		var openerdata = parent.window.dialogArguments;

		if(openerdata[2] != '' && openerdata[0].document.getElementById(openerdata[2]) != null){
			openerdata[0].document.getElementById(openerdata[2]).value = uniqId;
		}

		if(openerdata[3] != '' && openerdata[0].document.getElementById(openerdata[3]) != null){
			openerdata[0].document.getElementById(openerdata[3]).value = emplNo;
		}

		if(openerdata[4] != '' && openerdata[0].document.getElementById(openerdata[4]) != null){
			openerdata[0].document.getElementById(openerdata[4]).value = emplyrNm;
		}

		if(openerdata[5] != '' && openerdata[0].document.getElementById(openerdata[5]) != null){
			openerdata[0].document.getElementById(openerdata[5]).value = orgnztNm;
		}

		setReturnValue(true);
		
		parent.window.returnValue = true;
		parent.window.close();
	}
</script>
</head>
<body onLoad="fnInit()" topmargin="0" leftmargin="0">

<div class="board">
	
	<form name="frm" method="post" action="<c:url value='/uss/ion/ism/selectSanctnerList.do'/>">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	
	<h1><input name="title" id="title" type="text" value="" style="height:15px; font-size:15px; font-weight:bold;border:0;color:black;background:none;vertical-align:top" readonly="readonly"/><spring:message code="input.cSelect" /></h1><!-- 선택 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonIsm.sanctnerList.searchCondition" /> : </label><!-- 조회조건 -->
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition" />"><!-- 조회조건 선택 -->
					<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comUssIonIsm.sanctnerList.deptNm" /></option><!-- 부서명 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comUssIonIsm.sanctnerList.employeeNm" /></option><!-- 사원명 -->
			   </select>
				<input class="s_input2 vat" name="searchWrd" type="text" value="" size="35" onkeypress="press(event);" title="<spring:message code="title.search" /> <spring:message code="input.input" />" /><!-- 검색어 입력 -->			
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_sanctner('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	</form>

	<table class="board_list">
		<caption><spring:message code="title.list" /></caption><!-- 목록 -->
		<colgroup>
			<col style="width:10%" />
			<col style="width:25%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonIsm.sanctnerList.dept" /></th><!-- 부서 -->
			   <th scope="col"><spring:message code="comUssIonIsm.sanctnerList.class" /></th><!-- 직위 -->
			   <th scope="col"><spring:message code="comUssIonIsm.sanctnerList.employeeNo" /></th><!-- 사번 -->
			   <th scope="col"><spring:message code="comUssIonIsm.sanctnerList.employeeNm" /></th><!-- 사원명 -->
			   <th scope="col"><spring:message code="input.select" /></th><!-- 선택 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>		    
			    <td><c:out value="${result.orgnztNm}"/></td>
			    <td><c:out value="${result.ofcpsNm}"/></td>
			    <td><c:out value="${result.emplNo}"/></td>
			    <td><c:out value="${result.emplyrNm}"/></td>
			    <td>
					<input class="s_btn" type="submit" value="<spring:message code="input.select" />" onclick="fn_egov_inqire_sanctner('<c:out value="${result.uniqId}"/>', '<c:out value="${result.emplNo}"/>', '<c:out value="${result.emplyrNm}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;" style="padding:6px 10px 6px 10px; background-color:#4688d2; color:#fff; font-size:11px; border-radius:1px;">
				</td>
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
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_sanctner"/>
		</ul>
	</div>
</div>
</body>
</html>