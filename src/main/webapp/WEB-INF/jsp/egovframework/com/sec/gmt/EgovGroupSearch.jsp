<%
 /**
  * @Class Name : EgovGroupSearch.java
  * @Description : EgovGroupSearch Search 화면
  * @Modification Information
  * @
  * @  수정일                     수정자               수정내용
  * @ ----------    --------    ---------------------------
  * @ 2009.03.23    lee.m.j     최초 생성
  *   2016.07.06    장동한          표준프레임워크 v3.6 개선
  *
  *  @author lee.m.j
  *  @since 2009.03.23
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comCopSecGmt.groupPopup.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 그룹 조회 팝업 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript" defer="defer">
function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var checkCount = 0;
    var returnBoolean = false;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    returnValue = checkField[i].value;
                }
            }

            if(checkCount > 1) {
                alert("<spring:message code="comCopSecGmt.validate.groupOneSelect"/>");
                return;
            } else if(checkCount < 1) {
                alert("<spring:message code="comCopSecGmt.validate.groupSelect"/>");
                return;
            }
        } else {
        	if(checkField.checked == true) {
        		returnValue = checkId.value;
        	} else {
                alert("<spring:message code="comCopSecGmt.validate.groupSelect"/>");
                return;
            }
        }

        returnBoolean = true;

    } else {
    	alert("<spring:message code="comCopSecGmt.validate.groupSelectResult"/>");
    }

    document.listForm.groupId.value = returnValue;

    return returnBoolean;

}

function fncSelectGroupList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
 // document.listForm.action = "<c:url value='/sec/gmt/EgovGroupSearchList.do'/>";
    document.listForm.submit();
}

function fncSelectGroup(groupId) {
 // window.returnValue = groupId;
    opener.listForm.searchKeyword.value = groupId;
    window.close();
}

function fncSelectGroupConfirm() {
	if(fncManageChecked()) {
		opener.listForm.searchKeyword.value = document.listForm.groupId.value;
     // window.returnValue = document.listForm.groupId.value;
		window.close();
	}
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
 // document.listForm.action = "<c:url value='/sec/gmt/EgovGroupSearchList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectGroupList('1');
    }
}
</script>

</head>

<body>
<DIV id="main" style="display:">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/gmt/EgovGroupSearchList.do" method="post">
<div class="popup">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 그룹 조회 팝업 -->
	<!-- 검색영역 -->
	<div class="pop_search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="comCopSecGmt.groupPopup.searchKeywordText" /> : </div></li><!-- 그룹명 -->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${groupManageVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<input type="button" class="s_btn" onClick="fncSelectGroupConfirm()" value="<spring:message code="button.confirm" />" title="<spring:message code="button.confirm" /> <spring:message code="input.button" />" /><!-- 확인 -->
			
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="pop_board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 33%;">
		<col style="width: 30%;">
	</colgroup>
	<thead>
	<tr>
		<th></th><!-- 선택 -->
		<th class="board_th_link"><spring:message code="comCopSecGmt.groupPopupList.groupId" /></th><!-- 그룹 ID -->
		<th><spring:message code="comCopSecGmt.groupPopupList.groupNm" /></th><!-- 그룹 명 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(groupList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="group" items="${groupList}" varStatus="status">
	<tr>
		<td class="lt_text3" nowrap="nowrap"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${group.groupId}"/>" /></td>
		<td class="lt_text" nowrap="nowrap"><a href="#LINK" onclick="javascript:fncSelectGroup('<c:out value="${group.groupId}"/>')"><c:out value="${group.groupId}"/></a></td>
		<td class="lt_text" nowrap="nowrap"><c:out value="${group.groupNm}"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty groupManageVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
		</div>
	</c:if>


</div><!-- end div board -->


<input type="hidden" name="groupId"/>
<input type="hidden" name="groupIds"/>
<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form:form>


</DIV>
</body>
</html>
