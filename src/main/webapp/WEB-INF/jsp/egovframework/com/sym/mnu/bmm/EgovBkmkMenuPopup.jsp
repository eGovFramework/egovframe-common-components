<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBkmkMenuPopup.jsp
  * @Description : 바로가기메뉴 등록을 위한 메뉴목록
  * @Modification Information
  * @
  * @ 수정일                수정자             수정내용
  * @ ---------    --------    ---------------------------
  * @ 2009.09.25   윤성록             최초 생성
  *   2018.09.10   신용호             표준프레임워크 v3.8 개선
  *
  *  @author 공통컴포넌트팀 윤성록
  *  @since 2009.09.25
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymMnuBmm.BkmkMenuPopup.title" /></title><!-- 메뉴목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_menuInfo('1');
		}
	}
	function fn_egov_return_menuInfo(menuId, menuNm){
		var retVal = menuId +","+ menuNm;
		//parent.fn_egov_returnValue(retVal);
		parent.modalDialogCallback(retVal);
	}

	function fn_egov_select_menuInfo(pageIndex){

		document.frm.pageIndex.value = pageIndex;
		document.frm.action = "<c:url value='/sym/mnu/bmm/selectMenuList.do'/>";
		document.frm.submit();
	}

	function fn_egov_close(){
		parent.closeWindow();
	}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comSymMnuBmm.BkmkMenuPopup.pageTop.title" /></h1><!-- 메뉴목록 -->
	<span>※ "1100. 메뉴생성관리" 메뉴에서 "메뉴생성"을 먼저 해야 목록에서 확인이 가능합니다.</span>
	<form name="frm" action ="<c:url value='/sym/mnu/bmm/selectMenuList.do'/>" method="post">
	<input type="hidden" name="PopFlag" value="Y" >
	<input type="hidden" name="searchCnd" value ="0" >
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comSymMnuBmm.bkmkMenuManageRegist.menuName" /> : </label><!-- 메뉴명 -->
								
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${bkmkMenuManageVO.searchWrd}"/>' size="35" maxlength="35" onkeypress="press(event);" title="<spring:message code="comSymMnuBmm.bkmkMenuManageList.enterSearchName" />" /><!-- 검색단어입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_menuInfo('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="" />
			<col style="width:25%" />
			<col style="width:8%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymMnuBmm.bkmkMenuManageList.No" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comSymMnuBmm.bkmkMenuManageRegist.menuName" /></th><!-- 메뉴명 -->
			   <th scope="col"><spring:message code="comSymMnuBmm.bkmkMenuManageList.menuDC" /></th><!-- 메뉴DC -->
			   <th scope="col"><spring:message code="comSymMnuBmm.bkmkMenuManageList.select" /></th><!-- 선택 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.menuNm}" /></td>
			    <td><c:out value="${result.menuDc}" /></td>
				<td>
					<input class="btn01" type="submit" name="selectUser" value="선택" onclick="fn_egov_return_menuInfo('<c:out value="${result.menuId}" />','${result.menuNm}');" />
				</td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="4" ><spring:message code="common.nodata.msg" /></td>
			  </tr>
		 </c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_menuInfo"/>
		</ul>
	</div>

</div>


</body>
</html>
