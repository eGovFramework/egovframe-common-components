<%
/**
 * @Class Name : EgovRoleManage.java
 * @Description : EgovRoleManage jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.02.01    lee.m.j     최초 생성
 *   2016.06.13    장동한        표준프레임워크 v3.6 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.21
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
<c:set var="pageTitle"><spring:message code="comCopSecRmt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 롤관리 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javaScript" language="javascript" defer="defer">
function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("<spring:message code="comCopSecRmt.validate.groupSelectResult"/>"); //조회된 결과가 없습니다.
    }

    document.listForm.roleCodes.value = returnValue;
    return returnBoolean;
}

function fncSelectRoleList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function fncSelectRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRole.do'/>";
    document.listForm.submit();
}

function fncAddRoleInsert() {
    location.href = "<c:url value='/sec/rmt/EgovRoleInsertView.do'/>";
}

function fncRoleListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) { //삭제하시겠습니까?
            document.listForm.action = "<c:url value='/sec/rmt/EgovRoleListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function fncAddRoleView() {
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleUpdate.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectRoleList('1');
    }
}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/rmt/EgovRoleList.do" method="post">
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 롤관리 목록 -->
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="comCopSecRmt.searchCondition.searchKeywordText" /> : </div></li><!-- 롤명-->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${roleManageVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" onClick="fncSelectRoleList(1);return false;"/><!-- 조회 -->
				<input type="button" class="s_btn" onClick="fncRoleListDelete()" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" /><!-- 삭제 -->
				<span class="btn_b"><a href="<c:url value='/sec/rmt/EgovRoleInsertView.do'/>" onClick="javascript:fncAddRoleInsert();"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 3%;">
		<col style="width: 12%;">
		<col style="width: 25%;">
		<col style="width: 7%;">
		<col style="width: 7%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 5%;">
	</colgroup>
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopSecRam.list.rollId" /></th><!-- 롤 ID -->
		<th><spring:message code="comCopSecRam.list.rollNm" /></th><!-- 롤 명 -->
		<th><spring:message code="comCopSecRam.list.rollType" /></th><!-- 롤 타입 -->
		<th><spring:message code="comCopSecRam.list.rollSort" /></th><!-- 롤 Sort -->
		<th><spring:message code="comCopSecRam.list.rollDc" /></th><!-- 롤 설명 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
		<th></th><!--  -->

	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(roleList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="role" items="${roleList}" varStatus="status">
	<tr>
		<td><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${role.roleCode}"/>" /></td>
		<td><a href="<c:url value='/sec/rmt/EgovRoleList.do'/>?roleCode=${role.roleCode}" onclick="javascript:fncSelectRole('<c:out value="${role.roleCode}"/>');return false;"><c:out value="${role.roleCode}"/></a></td>
		<td class="left"><c:out value="${role.roleNm}"/></td>
		<td><c:out value="${role.roleTyp}"/></td>
		<td><c:out value="${role.roleSort}"/></td>
		<td class="left"><c:out value="${role.roleDc}"/></td>
		<td><c:out value="${fn:substring(role.roleCreatDe,0,10)}"/></td>
		<td><a href="<c:url value='/sec/rmt/EgovRoleList.do'/>?roleCode=${role.roleCode}" onclick="javascript:fncSelectRole('<c:out value="${role.roleCode}"/>');return false;s"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" alt="<spring:message code="title.detail" />"  title="<spring:message code="title.detail" />"></a></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty roleManageVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
		</div>
	</c:if>


</div><!-- end div board -->

<input type="hidden" name="roleCode"/>
<input type="hidden" name="roleCodes"/>
<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form:form>


</body>
</html>
