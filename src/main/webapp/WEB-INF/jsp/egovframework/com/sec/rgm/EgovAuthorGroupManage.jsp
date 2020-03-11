<%
 /**
  * @Class Name : EgovAuthorGroupManage.java
  * @Description : EgovAuthorGroupManage List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자                    수정내용
  * @ -------       --------    ---------------------------
  * @ 2009.03.23    Lee.m.j       최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author Lee.m.j
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
<c:set var="pageTitle"><spring:message code="comCopSecRgm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 권한그룹관리 목록 -->
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

	var resultCheck = false;

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var selectAuthor = document.listForm.authorManageCombo;
    var booleanRegYn = document.listForm.regYn;
    var listMberTyCode = document.listForm.mberTyCode;

    var returnId = "";
    var returnAuthor = "";
    var returnRegYn = "";
    var returnmberTyCode = "";

    var checkedCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnId == "") {
                        returnId = checkField[i].value;
                        returnAuthor = selectAuthor[i].value;
                        returnRegYn = booleanRegYn[i].value;
                        returnmberTyCode = listMberTyCode[i].value;
                    }
                    else {
                    	returnId = returnId + ";" + checkField[i].value;
                    	returnAuthor = returnAuthor + ";" + selectAuthor[i].value;
                    	returnRegYn = returnRegYn + ";" + booleanRegYn[i].value;
                    	returnmberTyCode = returnmberTyCode + ";" + listMberTyCode[i].value;

                    }
                }
            }

            if(checkedCount > 0)
            	resultCheck = true;
            else {
                alert("<spring:message code="comCopSecRgm.list.validate.alert.notSelect" />");//선택된  항목이 없습니다.
                resultCheck = false;
            }

        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecRgm.list.validate.alert.notSelect" />");//선택 항목이 없습니다.
                resultCheck = false;
            }
            else {
                returnId = checkId.value;
                returnAuthor = selectAuthor.value;
                returnRegYn = booleanRegYn.value;
                returnmberTyCode = listMberTyCode.value;

                resultCheck = true;
            }
        }
    } else {
        alert("<spring:message code="comCopSecRgm.list.validate.alert.noResult" />");//조회된 결과가 없습니다.
    }

    document.listForm.userIds.value = returnId;
    document.listForm.authorCodes.value = returnAuthor;
    document.listForm.regYns.value = returnRegYn;
    document.listForm.mberTyCodes.value = returnmberTyCode;
    return resultCheck;
}

function fncSelectAuthorGroupList(pageNo){
    //document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupList.do'/>";
    document.listForm.submit();
}

function fncAddAuthorGroupInsert() {

	if(!fncManageChecked()) return;

    if(confirm("<spring:message code="comCopSecRgm.list.validate.confirm.regist" />")) {//등록하시겠습니까?

        document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupInsert.do'/>";
        document.listForm.submit();
    }
}

function fncAuthorGroupDeleteList() {

	if(!fncManageChecked()) return;

    if(confirm("<spring:message code="comCopSecRgm.list.validate.confirm.delete" />")) { //삭제하시겠습니까?
        document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupDelete.do'/>";
        document.listForm.submit();
    }
}

function linkPage(pageNo){
    //document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupList.do'/>";
    document.listForm.submit();
}

function fncSelectAuthorGroupPop() {

    if(document.listForm.searchCondition.value == '3') {
    	window.open("<c:url value='/sec/gmt/EgovGroupSearchList.do'/>","notice","height=500, width=485, top=50, left=20, scrollbars=no, resizable=no");
    } else {
        alert("<spring:message code="comCopSecRgm.list.validate.alert.selectGroup" />");//그룹을 선택하세요.
        return;
    }

    /*
    var url = "<c:url value='/sec/gmt/EgovGroupSearchView.do'/>";
    var varParam = new Object();
    var openParam = "dialogWidth:500px;dialogHeight:485px;scroll:no;status:no;center:yes;resizable:yes;";
    var retVal;

    if(document.listForm.searchCondition.value == '3') {
        retVal = window.showModalDialog(url, varParam, openParam);
        if(retVal) {
            document.listForm.searchKeyword.value = retVal;
        }
    } else {
        alert("그룹을 선택하세요.");
        return;
    }
    */

}

function onSearchCondition() {
	document.listForm.searchKeyword.value = "";
	if(document.listForm.searchCondition.value == '3') {
        document.listForm.searchKeyword.readOnly = true;
	} else {
		document.listForm.searchKeyword.readOnly = false;
	}
}

function press() {

    if (event.keyCode==13) {
    	fncSelectAuthorGroupList('1');
    }
}

</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/ram/EgovAuthorGroupList.do" method="post">
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 권한그룹관리 목록 -->
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<!-- 조회조건 -->
			<li>
				<select name="searchCondition" onchange="onSearchCondition()" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
				<option value="1" <c:if test="${authorGroupVO.searchCondition == '1'}">selected</c:if> ><spring:message code="comCopSecRgm.searchCondition.userId" /></option><!-- 사용자 ID -->
				<option value="2" <c:if test="${authorGroupVO.searchCondition == '2'}">selected</c:if> ><spring:message code="comCopSecRgm.searchCondition.userNm" /></option><!-- 사용자 명- -->
				<option value="3" <c:if test="${authorGroupVO.searchCondition == '3'}">selected</c:if> ><spring:message code="comCopSecRgm.searchCondition.group" /></option><!-- 그룹 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >

				<input type="button" class="s_btn" onClick="fncSelectAuthorGroupPop()" value="<spring:message code="comCopSecRgm.btn.groupInquire" />" title="<spring:message code="comCopSecRgm.btn.groupInquire" /> <spring:message code="input.button" />" /><!-- 그룹조회팝업 -->
				<input type="button" class="s_btn" onClick="fncSelectAuthorGroupList('1')" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				
				<input type="button" class="s_btn" onClick="fncAuthorGroupDeleteList()" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" /><!-- 삭제 -->
				<input type="button" class="s_btn" onClick="fncAddAuthorGroupInsert()" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="button.create" />" /><!-- 등록 -->
			</li>
		</ul>
	</div>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 20%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th>
		<th class="board_th_link"><spring:message code="comCopSecRgm.list.userId" /></th><!-- 사용자 ID -->
		<th><spring:message code="comCopSecRgm.list.userNm" /></th><!-- 사용자 명 -->
		<th><spring:message code="comCopSecRgm.list.userType" /></th><!-- 사용자 유형 -->
		<th><spring:message code="comCopSecRgm.list.author" /></th><!-- 권한 -->
		<th><spring:message code="comCopSecRgm.list.regYn" /></th><!--등록 여부 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(authorGroupList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="authorGroup" items="${authorGroupList}" varStatus="status">
	<tr>
		<td><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${authorGroup.uniqId}"/>"/></td>
		<td><c:out value="${authorGroup.userId}"/></td>
		<td><c:out value="${authorGroup.userNm}"/></td>
		<td><c:out value="${authorGroup.mberTyNm}"/><input type="hidden" name="mberTyCode" value="${authorGroup.mberTyCode}"/></td>
		<td><select name="authorManageCombo" title="<spring:message code="comCopSecRgm.list.authorManageCombo" />"> <!-- 권한선택 -->
			<c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
			<option value="<c:out value="${authorManage.authorCode}"/>" <c:if test="${authorManage.authorCode == authorGroup.authorCode}">selected</c:if> ><c:out value="${authorManage.authorNm}"/></option>
			</c:forEach>
			</select></td>
		<td><c:out value="${authorGroup.regYn}"/><input type="hidden" name="regYn" value="<c:out value="${authorGroup.regYn}"/>"></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty authorGroupVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/></ul>
		</div>
	</c:if>

</div><!-- end div board -->

<input type="hidden" name="userId"/>
<input type="hidden" name="userIds"/>
<input type="hidden" name="authorCodes"/>
<input type="hidden" name="regYns"/>
<input type="hidden" name="mberTyCodes"/>
<input type="hidden" name="pageIndex" value="<c:out value='${authorGroupVO.pageIndex}'/>"/>
</form:form>

</body>
</html>
