<%
 /** 
  * @Class Name : EgovDeptSearch.java
  * @Description : EgovDeptSearch Search 화면
  * @Modification Information
  * @
  * @ 수정일                 수정자             수정내용
  * @ ----------    --------    ---------------------------
  * @ 2009.03.26    lee.m.j     최초 생성
  *   2016.07.06    장동한             표준프레임워크 v3.6 개선
  *   2018.12.03    신용호             표준프레임워크 v3.8 개선
  *
  *  @author lee.m.j
  *  @since 2009.03.26
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
<c:set var="pageTitle"><spring:message code="comCopSecDrm.deptSearchPopup.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 부서조회팝업 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript" defer="defer">
function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked)
                    checkField[i].value = checkId[i].value;
                if(returnValue == "")
                    returnValue = checkField[i].value;
                else 
                    returnValue = returnValue + ";" + checkField[i].value;
            }
        }
    } 

    document.listForm.groupIds.value = returnValue;
}

function fncSelectDeptList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
 // document.listForm.action = "<c:url value='/sec/drm/EgovDeptSearchList.do'/>";
    document.listForm.submit();
}

function fncSelectDept(deptCode, deptNm) {
 // window.returnValue = deptCode + "|" + deptNm;
    parent.document.listForm.deptCode.value = deptCode;
    parent.document.listForm.deptNm.value = deptNm;
    
    parent.$('.ui-dialog-content').dialog('close');
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
   //document.listForm.action = "<c:url value='/sec/drm/EgovDeptSearchList.do'/>";
    document.listForm.submit();
}

function fncSelectDeptConfirm() {
	var checkField = document.listForm.delYn;
    var checkFieldCd = document.listForm.checkId;
	var checkFieldNm = document.listForm.checkNm;
	var checkCount = 0;

	var org_cd;
	var org_nm;
	
	if(checkField) {
		if(checkField.length > 1) {
			for(var i=0; i<checkField.length; i++) {
				if(checkField[i].checked) {
					checkCount++;
                    org_cd = checkFieldCd[i].value;
                    org_nm = checkFieldNm[i].value;
				}
			}

			if(checkCount == 1) {
             // window.returnValue = org_cd + "|" + org_nm; 
                parent.document.listForm.deptCode.value = org_cd;
                parent.document.listForm.deptNm.value = org_nm;
                parent.$('.ui-dialog-content').dialog('close');
		    } else {
			    alert("<spring:message code="comCopSecDrm.deptSearchPopup.validate.alert.selectOne" />"); //하나의 부서를 선택하세요.
			    return;
			}
		} else {
			if(document.listForm.delYn.checked) {
             // window.returnValue = document.listForm.checkId.value + "|" + document.listForm.checkNm.value;
                parent.document.listForm.deptCode.value = document.listForm.checkId.value;
                parent.document.listForm.deptNm.value = document.listForm.checkNm.value;
                parent.$('.ui-dialog-content').dialog('close');
			} else {
	            alert("<spring:message code="comCopSecDrm.deptSearchPopup.validate.alert.selectNothing" />"); //선택된 항목이 없습니다.
	            return;
			}
		} 
	} else {
        alert("<spring:message code="comCopSecDrm.deptSearchPopup.validate.alert.searchAfter" />"); //조회 후 선택하시기 바랍니다.
        return;
	}
}

function press() {

    if (event.keyCode==13) {
    	fncSelectDeptList('1');
    }
}
</script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/drm/EgovDeptSearchList.do" method="post">
<div class="popup">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 부서조회팝업 목록 -->
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="comCopSecDrm.deptSearchPopup.searchKeywordText" /> : </div></li><!-- 권한명 -->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${deptAuthorVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<input type="button" class="s_btn" onClick="fncSelectDeptConfirm()" value="<spring:message code="button.confirm" />" title="<spring:message code="button.confirm" /> <spring:message code="input.button" />" /><!-- 확인 -->
			
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 33%;">
		<col style="width: 30%;">
	</colgroup>
	<thead>
	<tr>
		<th></th><!-- 선택 -->
		<th class="board_th_link"><spring:message code="comCopSecDrm.deptSearchPopupList.deptId" /></th><!-- 부서 ID -->
		<th><spring:message code="comCopSecDrm.deptSearchPopupList.deptNm" /></th><!-- 부서 명 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(deptList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="dept" items="${deptList}" varStatus="status">
	<tr>
	    <td><input type="checkbox" name="delYn" title="checkField <c:out value='${status.count}'/>"><input type="hidden" name="checkId" value="<c:out value="${dept.deptCode}"/>" /><input type="hidden" name="checkNm" value="<c:out value="${dept.deptNm}"/>" /></td>
	    <td><a href="#LINK" onclick="javascript:fncSelectDept('<c:out value="${dept.deptCode}"/>', '<c:out value="${dept.deptNm}"/>')"><c:out value="${dept.deptCode}"/></a></td>
	    <td class="left"><c:out value="${dept.deptNm}"/></td>
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


<input type="hidden" name="pageIndex" value="<c:out value='${deptAuthorVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition" value="1"/>
</form:form>

</body>
</html>
