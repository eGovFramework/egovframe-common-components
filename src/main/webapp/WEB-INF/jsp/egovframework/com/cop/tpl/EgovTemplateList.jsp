<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comCopTpl.template.title"/> <spring:message code="title.list" /></c:set>
<%
 /**
  * @Class Name : EgovTemplateList.jsp
  * @Description : 템플릿 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.18   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.18
  *  @version 1.0
  *  @see
  *
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title> <!-- 템플릿 목록 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">   	      
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_tmplatInfo('1');
		}
	}

	function fn_egov_insert_addTmplatInfo(){
		document.frm.action = "<c:url value='/cop/tpl/addTemplateInf.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_tmplatInfo(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/tpl/selectTemplateInfs.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_tmplatInfor(tmplatId){
		document.frm.tmplatId.value = tmplatId;
		document.frm.action = "<c:url value='/cop/tpl/selectTemplateInf.do'/>";
		document.frm.submit();
	}
</script>
</head>
<body>
<form name="frm" action ="" method="post">
<input type="hidden" name="tmplatId" value="" />


<div class="board">
	<h1>${pageTitle}</h1> <!-- 템플릿 목록 -->
	
	<div class="search_box" title="이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다.">
		<ul>
			<!-- 검색키워드 및 조회버튼 -->
			<li style="border: 0px solid #d2d2d2;">
				<select name="searchCnd" class="select" title="검색조건선택">
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopTpl.template.name"/></option> <!-- 템플릿명 -->
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopTpl.template.type"/></option> <!-- 템플릿구분 -->
				</select>
				<input class="s_input2 vat" type="text" name="searchWrd" size="35" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="35" onkeypress="press(event);" title="<spring:message code="title.search" /> <spring:message code="input.input" />" /> <!-- 검색단어입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire"/>" onclick="fn_egov_select_tmplatInfo('1');return false;" /> <!-- 조회 -->
				<input class="s_btn" type="submit" value="<spring:message code="title.create"/>" title="<spring:message code="title.create"/>" onclick="fn_egov_insert_addTmplatInfo();return false;" /> <!-- 등록 -->
			</li>
		</ul>
	</div>
	
	<table class="board_list" summary="">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="table.num"/></th> <!-- 번호 -->
				<th scope="col"><spring:message code="comCopTpl.template.name"/></th> <!-- 템플릿명 -->
				<th scope="col"><spring:message code="comCopTpl.template.type"/></th> <!-- 템플릿구분 -->
				<th scope="col"><spring:message code="comCopTpl.template.path"/></th> <!-- 템플릿경로 -->
				<th scope="col"><spring:message code="comCopTpl.template.useYN"/></th> <!-- 사용여부 -->
				<th scope="col"><spring:message code="comCopTpl.template.registDt"/></th> <!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td>
					<a href="<c:url value='/cop/tpl/selectTemplateInf.do'/>?tmplatId=<c:out value='${result.tmplatId}'/>" onclick="">
					<c:out value="${result.tmplatNm}"/>
					</a>
				</td>
				<td><c:out value="${result.tmplatSeCodeNm}"/></td>
				<td><c:out value="${result.tmplatCours}"/></td>
				<td>
				<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
				<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
				</td>
				<td><c:out value="${result.frstRegisterPnttm}"/></td	>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="5" ><spring:message code="common.nodata.msg" /></td>
			</tr>
		</c:if>
		</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_tmplatInfo"/>
		</ul>
	</div>
</div>

</form>
</body>
</html>
