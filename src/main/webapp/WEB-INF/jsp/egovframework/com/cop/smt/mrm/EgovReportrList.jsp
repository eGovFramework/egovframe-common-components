<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovReportrList.jsp
 * @Description : 보고자 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.09.03   장철호          최초 생성
 * @ 2018.09.17   최두영          퍼블리 점검&다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.09.03
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtMrm.reportrList.title"/></title><!-- 보고대상자 목록조회 -->
<link href="<c:url value='/css/egovframework/com/com.css'/>"  rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css'/>"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">
	function fnInit(){
		getDialogArguments();
		var opener = parent.window.dialogArguments;
		
		//document.frm.title.value = opener[1]; 
		parent.document.title = opener[1] + " 목록조회"; 
	}
	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_reportr('1');
		}
	}
	
	function fn_egov_select_reportr(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/mrm/selectReportrList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_reportr(uniqId, emplNo, emplyrNm, orgnztNm) {
		getDialogArguments();
		var opener = parent.window.dialogArguments;

		if(opener[2] != '' && opener[0].document.getElementById(opener[2]) != null){
			opener[0].document.getElementById(opener[2]).value = uniqId;
		}

		if(opener[3] != '' && opener[0].document.getElementById(opener[3]) != null){
			opener[0].document.getElementById(opener[3]).value = emplNo;
		}

		if(opener[4] != '' && opener[0].document.getElementById(opener[4]) != null){
			opener[0].document.getElementById(opener[4]).value = emplyrNm;
		}

		if(opener[5] != '' && opener[0].document.getElementById(opener[5]) != null){
			opener[0].document.getElementById(opener[5]).value = orgnztNm;
		}

		parent.window.returnValue = true;
		parent.window.close();
	}
</script>
</head>
<body onLoad="fnInit()">

<div class="board" style="width:765px">
	<h1><spring:message code="comCopSmtMrm.reportrList.select"/></h1><!-- 보고대상자 선택 -->

	<form name="frm" method="post" action="<c:url value='/cop/smt/mrm/selectReportrList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="select.searchCondition" /> : </label><!-- 조회조건 -->
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition" />"><!-- 조회조건 선택 -->
					<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.reportrList.orgnztNm"/></option><!-- 부서명 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtMrm.reportrList.emplyrNm"/></option><!-- 사원명 -->
			   </select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" size="35" onkeypress="press(event);" title="<spring:message code="title.search"/> <spring:message code="input.input"/>" /><!-- 검색어 입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="fn_egov_select_reportr('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	
	</form>

	<table class="board_list">
		<caption></caption>
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
			   <th scope="col"><spring:message code="table.num" /></th><!-- 조회 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.reportrList.orgnztNm"/></th><!-- 부서명 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.reportrList.ofcpsNm"/></th><!-- 직위 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.reportrList.emplNo"/></th><!-- 사번 -->
			   <th scope="col"><spring:message code="comCopSmtMrm.reportrList.emplyrNm"/></th><!-- 사원명 -->
			   <th scope="col"><spring:message code="table.select" /></th><!-- 선택 -->
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
					<span class="link"><input type="submit" value="<spring:message code="table.select" />" onclick="javascript:fn_egov_inqire_reportr('<c:out value="${result.uniqId}"/>', '<c:out value="${result.emplNo}"/>', '<c:out value="${result.emplyrNm}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;"></span>
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
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_reportr"/>
		</ul>
	</div>
</div>
</body>
</html>
