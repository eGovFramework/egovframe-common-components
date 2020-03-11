<%
/**
 * @Class Name : EgovAuthorRoleManage.java
 * @Description : EgovAuthorRoleManage.jsp
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
<c:set var="pageTitle"><spring:message code="comCopSecRam.authorRoleList.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 권한롤관리 목록 -->
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
    var checkRegYn = document.listForm.regYn;
    var returnValue = "";
    var returnRegYns = "";
    var checkedCount = 0;
    var returnBoolean = false;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;

	                if(returnValue == "") {
	                    returnValue = checkField[i].value;
	                    returnRegYns = checkRegYn[i].value;
	                }
	                else {
	                    returnValue = returnValue + ";" + checkField[i].value;
	                    returnRegYns = returnRegYns + ";" + checkRegYn[i].value;
	                }
                }
            }

            if(checkedCount > 0)
            	returnBoolean = true;
            else {
                alert("<spring:message code="comCopSecRam.authorRoleList.validate.alert.noSelect" />");//선택된  롤이 없습니다.
                returnBoolean = false;
            }
        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecRam.authorRoleList.validate.alert.noSelect" />");//선택된 롤이 없습니다.
            	returnBoolean = false;
            }
            else {
            	returnValue = checkId.value;
                returnRegYns = checkRegYn.value;

                returnBoolean = true;
            }
        }
    } else {
        alert("<spring:message code="comCopSecRam.authorRoleList.validate.alert.noResult" />");//조회된 결과가 없습니다.
    }

    document.listForm.roleCodes.value = returnValue;
    document.listForm.regYns.value = returnRegYns;

    return returnBoolean;

}

function fncSelectAuthorRoleList() {
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = "1";
    document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleList.do'/>";
    document.listForm.submit();
}

function fncSelectAuthorList(){
    // document.listForm.searchCondition.value = "1";
    // document.listForm.pageIndex.value = "1";
    
    //document.listForm.searchKeyword.value = "";
    //document.listForm.action = "<c:url value='/sec/ram/EgovAuthorList.do'/>";
    //document.listForm.submit();
    location.href = "<c:url value='/sec/ram/EgovAuthorList.do'/>";
}

function fncSelectAuthorRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/sec/ram/EgovRole.do'/>";
    document.listForm.submit();
}

function fncAddAuthorRoleInsert() {
	if(fncManageChecked()) {
	    if(confirm("<spring:message code="comCopSecRam.authorRoleList.validate.confirm.regist" />")) {//등록하시겠습니까?
            document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleInsert.do'/>";
            document.listForm.submit();
	    }
	} else return;
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectAuthorRoleList();
    }
}
</script>
</head>
<body>
<div class="board">
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/ram/EgovAuthorRoleList.do" method="post">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 권한롤관리 목록 -->
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="comCopSecRam.regist.authorCode" /> : </div></li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<input type="button" class="s_btn" value="<spring:message code="button.list" />" onClick="fncSelectAuthorList()" title="<spring:message code="button.list" /> <spring:message code="input.button" />" />
				<input type="button" class="s_btn" value="<spring:message code="button.create" />" onClick="fncAddAuthorRoleInsert()" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
			</li>
		</ul>
	</div>
	
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 3%;">
		<col style="width: 12%;">
		<col style="width: 20%;">
		<col style="width: 12%;">
		<col style="width: 10%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th>
		<th class="board_th_link"><spring:message code="comCopSecRam.authorRoleList.rollId" /></th><!-- 롤 ID -->
		<th><spring:message code="comCopSecRam.authorRoleList.rollNm" /></th><!-- 롤 명 -->
		<th><spring:message code="comCopSecRam.authorRoleList.rollType" /></th><!-- 롤 타입 -->
		<th><spring:message code="comCopSecRam.authorRoleList.rollSort" /></th><!-- 롤 Sort -->
		<th><spring:message code="comCopSecRam.authorRoleList.rollDc" /></th><!-- 롤 설명 -->
		<th><spring:message code="table.regdate" /></th><!--등록일 -->
		<th><spring:message code="comCopSecRam.authorRoleList.regYn" /></th><!-- 등록여부 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(authorRoleList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="authorRole" items="${authorRoleList}" varStatus="status">
	<tr>
		<td><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${authorRole.roleCode}"/>" /></td>
		<td><c:out value="${authorRole.roleCode}"/></td>
		<td><c:out value="${authorRole.roleNm}"/></td>
		<td><c:out value="${authorRole.roleTyp}"/></td>
		<td><c:out value="${authorRole.roleSort}"/></td>
		<td><c:out value="${authorRole.roleDc}"/></td>
		<td><c:out value="${fn:substring(authorRole.creatDt,0,10)}"/></td>
		<td>
			<select name="regYn" title="<spring:message code="comCopSecRam.authorRoleList.regYn" />">
			<option value="Y" <c:if test="${authorRole.regYn == 'Y'}">selected</c:if> ><spring:message code="comCopSecRam.authorRoleList.regY" /></option><!-- 등록 -->
			<option value="N" <c:if test="${authorRole.regYn == 'N'}">selected</c:if> ><spring:message code="comCopSecRam.authorRoleList.regN" /></option><!-- 미등록 -->
			</select>
		</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty authorRoleManageVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
		</div>
	</c:if>
	
	<!-- 버튼역역 -->
	<!-- 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/sec/ram/EgovAuthorList.do'/>"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<span class="btn_s"><a href="javascript:fncAddAuthorRoleInsert()"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
	</div>
	 -->
	
	<input type="hidden" name="roleCodes"/>
	<input type="hidden" name="regYns"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${authorRoleManageVO.pageIndex}'/>"/>
	<input type="hidden" name="authorCode" value="<c:out value="${authorRoleManageVO.searchKeyword}"/>"/>
	<input type="hidden" name="searchCondition">
</form:form>
</div><!-- end div board -->

</body>
</html>
