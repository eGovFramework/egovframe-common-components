<% 
/**
 * @Class Name : EgovDeptJobList.jsp
 * @Description : 부서업무 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.09   장철호          최초 생성
 * @ 2018.09.10   최두영           V3.8 퍼블리싱 점검
 * @ 2018.09.14   최두영           다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.09
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
<c:set var="tmpDeptId" value=""/>
<c:set var="tmpDeptNm" value=""/>
<c:set var="wtText" value=""/>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtDjm.deptJobList.title" /></title><!-- 부서업무 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">

	function fn_egov_init_deptjob(){
		fn_egov_hide_ListStyle();
		var idsrc = document.getElementById(document.frm.searchDeptId.value);
		idsrc.style.display="";
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_deptjob('1');
		}
	}
	
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	
	function fn_egov_select_deptjob(pageNo) {
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/djm/selectDeptJobList.do'/>";
		document.frm.submit();	
	}

	function fn_egov_select_deptjobbx(deptId, deptJobBxId, deptJobBxNm) {
		document.frm.searchDeptId.value = deptId; 
		document.frm.searchDeptJobBxId.value = deptJobBxId; 
		document.frm.deptJobBxNm.value = deptJobBxNm; 
		document.frm.action = "<c:url value='/cop/smt/djm/selectDeptJobList.do'/>";
		document.frm.submit();	
	}
	/* ********************************************************
	* 부서업무 상세조회
	******************************************************** */
	function fn_egov_inqire_deptjob(deptJobId) {
		document.frm.deptJobId.value = deptJobId;
		document.frm.action = "<c:url value='/cop/smt/djm/selectDeptJob.do'/>";
		document.frm.submit();	
	}

	/* ********************************************************
	* 부서업무 등록창 가기
	******************************************************** */
	function fn_egov_insert_deptjob(){	
		document.frm.action = "<c:url value='/cop/smt/djm/addDeptJob.do'/>";
		document.frm.submit();
	}

	function fn_egov_change_ListStyle(list){
		fn_egov_hide_ListStyle();

		var idsrc = document.getElementById(list);
		idsrc.style.display="";
	}

	function fn_egov_hide_ListStyle(){
		<c:forEach var="resultBxFn" items="${resultBxList}" varStatus="st">
		<c:if test="${tmpDeptId != resultBxFn.deptId}">
		<c:set var="tmpDeptId" value="${resultBxFn.deptId}"/>
		var idsrc${st.count} = document.getElementById("${resultBxFn.deptId}");
		idsrc${st.count}.style.display="none";
		</c:if>
		</c:forEach>	 
	}
</script>

</head>
<body onLoad="fn_egov_init_deptjob()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comCopSmtDjm.deptJobList.title" /></h1><!-- 부서업무 목록 -->
	
	<form name="frm" method="post" action="<c:url value='/cop/smt/djm/selectDeptJobList.do'/>">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<input type="hidden" id="deptJobId" name="deptJobId">
	<input type="hidden" id="searchDeptId" name="searchDeptId" value="<c:out value='${searchVO.searchDeptId}'/>">
	<input type="hidden" id="searchDeptJobBxId" name="searchDeptJobBxId" value="<c:out value='${searchVO.searchDeptJobBxId}'/>">
	<input type="hidden" id="deptJobBxNm" name="deptJobBxNm" value="<c:out value='${searchVO.deptJobBxNm}'/>">
	
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="select.searchCondition" /> : </label><!-- 조회조건선택 -->
				<select name="searchCnd" class="select" title="조회조건 선택">
					<option value=''>--<spring:message code="input.select" />--</option>
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobList.subject" /></option><!-- 제목 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobList.content" /></option><!-- 내용 -->
					<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobList.charger" /></option><!-- 담당자 -->
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' size="25" onkeypress="press(event);" title="검색어 입력" />		
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_deptjob('1'); return false;" />
				<input class="s_btn" type="submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" />" onclick="fn_egov_insert_deptjob('1'); return false;">
			</li>
		</ul>
	</div>
	</form>
		
	<table>
		<colgroup>
			<col style="width:25%" />
			<col style="" />
		</colgroup>
		<tr>
			<!-- left -->
			<td style="vertical-align:top">
				<div id="scale" style="width:160px">
					<c:forEach var="resultBx" items="${resultBxList}" varStatus="st">
						<c:if test="${tmpDeptNm != resultBx.deptNm}">
						<c:set var="tmpDeptNm" value="${resultBx.deptNm}"/>
							<table class="board_list">
								<tr> 
									<th onclick="fn_egov_change_ListStyle('<c:out value="${resultBx.deptId}"/>')" style="cursor:hand">&nbsp;<c:out value='${resultBx.deptNm}'/></th>
								</tr>
							</table>
							<div id="<c:out value="${resultBx.deptId}"/>">
							<table width="100%" cellpadding="1" class="table-list" summary="<spring:message code="comCopSmtDjm.deptJobList.summary" />"><!-- 부서업무함에 대한 목록을 제공합니다. -->
						</c:if>
								<tr>
									<td>&nbsp;&nbsp;
										<form name="searchVO" method="post" action="<c:url value='/cop/smt/djm/selectDeptJobList.do'/>">
											<input type="hidden" class="searchDeptId" value="<c:out value="${resultBx.deptId}"/>">
											<input type="hidden" class="searchDeptJobBxId" value="<c:out value="${resultBx.deptJobBxId}"/>">
											<input type="hidden" class="deptJobBxNm" value="<c:out value="${resultBx.deptJobBxNm}"/>">
											<input class="btn01" type="submit" value="<c:out value="${resultBx.deptJobBxNm}"/>" onclick="fn_egov_select_deptjobbx('<c:out value="${resultBx.deptId}"/>', '<c:out value="${resultBx.deptJobBxId}"/>', '<c:out value="${resultBx.deptJobBxNm}"/>'); return false;"  style="text-align:left;">
										</form>
									</td>		    
								</tr>
						<c:if test="${tmpDeptNm != resultBxList[st.count].deptNm}">
							</table>
							</div>
						</c:if>
					</c:forEach>	  
				</div>
			</td>
			
			<!-- right -->
			<td style="vertical-align:top">
				<table class="board_list">
					<caption><spring:message code="comCopSmtDjm.deptJobList.title" /></caption>
					<colgroup>
						<col style="width:10%" />
						<col style="" />
						<col style="width:15%" />
						<col style="width:15%" />
					</colgroup>
					<thead>
						<tr>
						   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
						   <th scope="col"><spring:message code="comCopSmtDjm.deptJobList.subject" /></th><!-- 제목 -->
						   <th scope="col"><spring:message code="comCopSmtDjm.deptJobList.charger" /></th><!-- 담당자 -->
						   <th scope="col"><spring:message code="comCopSmtDjm.deptJobList.writerDate" /></th><!-- 작성일 -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<c:if test="${result.priort == '1'}">
								<c:set var="wtText" value="font-weight : bold; text-align : left; "/>
							</c:if>
							<c:if test="${result.priort == '2'}">
								<c:set var="wtText" value="text-align : left;"/>
							</c:if>
							<c:if test="${result.priort == '3'}">
								<c:set var="wtText" value="text-align : left;"/>
							</c:if>
							<tr>
								<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
								<td>
									<form name="deptJobVO" method="post" action="<c:url value='/cop/smt/djm/selectDeptJob.do'/>">
										<input type="hidden" name="searchDeptId" value="<c:out value='${searchVO.searchDeptId}'/>">
										<input type="hidden" name="searchDeptJobBxId" value="<c:out value='${searchVO.searchDeptJobBxId}'/>">
										<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
										<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
										<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
										<input type="hidden" name="deptJobId" value="<c:out value="${result.deptJobId}"/>">
										<span class="link"><input type="submit" value="<c:out value="${result.deptJobNm}"/>" onclick="fn_egov_inqire_deptjob('<c:out value="${result.deptJobId}"/>'); return false;" style="${wtText}"></span>
									</form>
								</td>
								<td><c:out value="${result.chargerNm}"/></td>
								<td><c:out value="${fn:substring(result.frstRegisterPnttm, 0, 10)}"/></td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="4"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
				<!-- paging navigation -->
				<div class="pagination">
					<ul>
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_deptjob"/>
					</ul>
				</div>			
			</td>
		</tr>
	</table>
</div>

</body>
</html>
