<%
 /**
  * @Class Name : EgovDeptAuthorManage.java
  * @Description : EgovDeptAuthorManage List 화면
  * @Modification Information
  * @
  * @ 수정일                 수정자               수정내용
  * @ ----------    ---------    ---------------------------
  * @ 2009.03.23    Lee.m.j      최초 생성
  * @ 2011.11.11       이기하		  부서권한관리 등록시 오류 수정
  *   2016.07.06    장동한               표준프레임워크 v3.6 개선
  *   2018.12.03    신용호               표준프레임워크 v3.8 개선
  *
  *  @author Lee.m.j
  *  @since 2009.03.23
  *  @version 1.0
  *  @see
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSecDrm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 부서권한관리 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

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

    var resultCheck = true;

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var selectAuthor = document.listForm.authorManageCombo;
    var booleanRegYn = document.listForm.regYn;

    var returnId = "";
    var returnAuthor = "";
    var returnRegYn = "";

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
                    }
                    else {
                        returnId = returnId + ";" + checkField[i].value;
                        returnAuthor = returnAuthor + ";" + selectAuthor[i].value;
                        returnRegYn = returnRegYn + ";" + booleanRegYn[i].value;
                    }
                }
            }
            if(checkedCount > 0)
                resultCheck = true;
            else {
                alert("<spring:message code="comCopSecDrm.validate.deptAuthorSelect" />"); //선택된  사용자가 없습니다.
                resultCheck = false;
            }
        } else {
        	if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecDrm.validate.deptAuthorSelect" />"); //선택된 사용자가  없습니다.
                resultCheck = false;
            }
            else {
            	returnId = checkId.value;
                returnAuthor = selectAuthor.value;
                returnRegYn = booleanRegYn.value;
                resultCheck = true;
            }
        }
    } else {
    	resultCheck = false;
        alert("<spring:message code="comCopSecDrm.validate.deptAuthorNoSelectResult" />"); //조회된 결과가 없습니다.
    }

    document.listForm.userIds.value = returnId;
    document.listForm.authorCodes.value = returnAuthor;
    document.listForm.regYns.value = returnRegYn;

    return resultCheck;
}

function fncSelectDeptAuthorList(pageNo) {
	if(document.listForm.deptCode.value == '') {
		alert("<spring:message code="comCopSecDrm.validate.deptSelect" />"); //부서를 선택하세요.
		return;
	}

    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/drm/EgovDeptAuthorList.do'/>";
    document.listForm.submit();
}

function fncAddDeptAuthorInsert() {

    if(!fncManageChecked()) return;

    if(confirm("<spring:message code="common.regist.msg" />")){	//등록하겠습니까?
        document.listForm.action = "<c:url value='/sec/drm/EgovDeptAuthorInsert.do'/>";
        document.listForm.submit();
    }
}

function fncDeptAuthorDeleteList() {

    if(!fncManageChecked()) return;

    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
        document.listForm.action = "<c:url value='/sec/drm/EgovDeptAuthorDelete.do'/>";
        document.listForm.submit();
    }
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/drm/EgovDeptAuthorList.do'/>";
    document.listForm.submit();
}


/*
function fncSelectDeptAuthorPop() {

    var url = "<c:url value='/sec/drm/EgovDeptSearchView.do'/>";
    var varParam = new Object();
    var openParam = "dialogWidth:500px;dialogHeight:485px;scroll:no;status:no;center:yes;resizable:yes;";

    var retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.listForm.deptCode.value = retVal.substring(0, retVal.indexOf("|"));
        document.listForm.deptNm.value = retVal.substring(retVal.indexOf("|")+1, retVal.length);
    }
}
*/
function fncSelectDeptAuthorPop() {


    var url = "<c:url value='/sec/drm/EgovDeptSearchList.do'/>";
    var openParam = "dialogWidth:500px;dialogHeight:485px;scroll:no;status:no;center:yes;resizable:yes;";
    /*
    var retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.listForm.deptCode.value = retVal.substring(0, retVal.indexOf("|"));
        document.listForm.deptNm.value = retVal.substring(retVal.indexOf("|")+1, retVal.length);
    }
    */

    window.open(url,"<spring:message code="comCopSecDrm.list.searchDept" />",'width=500,height=485,scrollbars=no,resizable=no,status=no,center:yes'); //부서검색

}

function press() {

    if (event.keyCode==13) {
    	fncSelectDeptAuthorList('1');
    }
}

</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#deptSelectPopup').click(function (e) {
        	e.preventDefault();
            //var page = $(this).attr("href");
            var pagetitle = $(this).attr("title");
            var page = "<c:url value='/sec/drm/EgovDeptSearchList.do'/>";
            var $dialog = $('<div></div>')
            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
            .dialog({
            	autoOpen: false,
                modal: true,
                height: 500,
                width: 520,
                title: pagetitle
        	});
        	$dialog.dialog('open');
    	});

	});
</script>

</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/drm/EgovDeptAuthorList.do" method="post">
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 부서권한관리 목록 -->
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="comCopSecDrm.searchCondition.searchKeywordText" /> : </div></li><!-- 부서권한관리 -->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input name="deptCode" type="text" value="<c:out value='${deptAuthorVO.deptCode}' />" size="22" title="<spring:message code="comCopSecDrm.list.deptCd" />" onkeypress="press();" readonly="readonly" /><!-- 부서코드 -->
				<input name="deptNm" type="text" value="<c:out value='${deptAuthorVO.deptNm}'/>" size="15" title="<spring:message code="comCopSecDrm.list.deptNm" />" onkeypress="press();" readonly="readonly" /><!-- 부서명 -->
				<input id="deptSelectPopup" type="button" class="s_btn" value="<spring:message code="comCopSecDrm.btn.deptSelectPopup" />" title="<spring:message code="comCopSecDrm.btn.deptSelectPopup" /> <spring:message code="input.button" />" /><!-- 부서조회팝업 -->
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<input type="button" class="s_btn" onClick="fncDeptAuthorDeleteList();return false;" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" /><!-- 삭제 -->
				<input type="button" class="s_btn" onClick="fncAddDeptAuthorInsert();return false;" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
			</li>
		</ul>
	</div>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 3%;">
		<col style="width: 13%;">
		<col style="width: 13%;">
		<col style="width: %;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopSecDrm.list.userId" /></th><!--사용자 ID -->
		<th><spring:message code="comCopSecDrm.list.userNm" /></th><!--사용자 명 -->
		<th><spring:message code="comCopSecDrm.list.author" /></th><!-- 권한 -->
		<th><spring:message code="comCopSecDrm.list.regAt" /></th><!-- 등록 여부 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(deptAuthorList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="deptAuthor" items="${deptAuthorList}" varStatus="status">
	<tr>
		<td><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${deptAuthor.uniqId}"/>" /></td>
		<td><c:out value="${deptAuthor.userId}"/></td>
		<td><c:out value="${deptAuthor.userNm}"/></td>
		<td><select name="authorManageCombo" title="등록여부">
			<c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
			<option value="<c:out value="${authorManage.authorCode}"/>" <c:if test="${authorManage.authorCode == deptAuthor.authorCode}">selected</c:if> ><c:out value="${authorManage.authorNm}"/></option>
			</c:forEach>
			</select></td>
		<td><c:out value="${deptAuthor.regYn}"/><input type="hidden" name="regYn" value="<c:out value="${deptAuthor.regYn}"/>"></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty deptAuthorVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
		</div>
	</c:if>

</div><!-- end div board -->

<input type="hidden" name="userId"/>
<input type="hidden" name="userIds"/>
<input type="hidden" name="authorCodes"/>
<input type="hidden" name="regYns"/>
<input type="hidden" name="pageIndex" value="<c:out value='${deptAuthorVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form:form>



</body>
</html>
