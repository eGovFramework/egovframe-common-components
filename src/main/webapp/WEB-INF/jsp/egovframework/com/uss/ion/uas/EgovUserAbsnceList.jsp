<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovUserAbsnceList.java
 * @Description : EgovUserAbsnceList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.07.01    lee.m.j     최초 생성
 * @ 2018.08.16        이정은	              공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2009.07.01
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonUas.userAbsnceList.userAbsnceList" /></title><!-- 사용자부재 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
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
                alert("<spring:message code="ussIonUas.userAbsnceList.noUserSlct" />");/* 선택된 사용자가 없습니다. */
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="ussIonUas.userAbsnceList.noUserSlct" />");/* 선택된 사용자가 없습니다. */
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("<spring:message code="ussIonUas.userAbsnceList.failInquir" />");/* 조회된 결과가 없습니다. */
    }

    document.listForm.userIds.value = returnValue;
    return returnBoolean;
}

function fncInsertCheckId() {

	var checkedCounter = 0;
	var checkIds = document.listForm.delYn;
	var checkIdv = document.listForm.checkId;
	var checkRegYn = document.listForm.regYn;

    if(checkIds == null) {
        alert("<spring:message code="ussIonUas.userAbsnceList.regiAftrView" />");/* 조회 후 등록하시기 바랍니다 */
        return;
    }
    else {

	    for(var i=0; i<checkIds.length; i++) {
            if(checkIds[i].checked) {
                if(checkRegYn[i].value == 'Y') {
                    alert("<spring:message code="ussIonUas.userAbsnceList.alreadyRegis" />");/* 이미 등록되어 있습니다. */
                    return false;;
                }
		        document.listForm.userId.value = checkIdv[i].value;
                checkedCounter++;
            }
	    }

        if(checkedCounter > 1) {
            alert("<spring:message code="ussIonUas.userAbsnceList.selectOnlyOne" />");/* 등록대상 하나만 선택하십시오 */
            return false;
	    } else if(checkedCounter < 1) {
            alert("<spring:message code="ussIonUas.userAbsnceList.noRegisTargetSlct" />");/* 선택된 등록대상이  없습니다 */
            return false;
        }

        return true;
    }
}

function fncSelectUserAbsnceList(pageNo) {
    //document.listForm.searchCondition.value = "1";
    //document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>";
    document.listForm.submit();
}

function fncSelectUserAbsnce(userId, regYn) {
	if(regYn == 'N') {
        if(confirm("<spring:message code="ussIonUas.userAbsnceList.goToregisPage" />")) {/* 등록된 사용자부재 정보가 없습니다. 등록페이지로 이동하시겠습니까? */
        	location.replace("<c:url value='/uss/ion/uas/addViewUserAbsnce.do'/>?userId="+userId);
        } else {
            return;
        }
	}
    document.listForm.userId.value = userId;
    document.listForm.action = "<c:url value='/uss/ion/uas/getUserAbsnce.do'/>";
    document.listForm.submit();
}

function fncAddUserAbsnceInsert() {
	if(fncInsertCheckId()) {
        document.listForm.action = "<c:url value='/uss/ion/uas/addViewUserAbsnce.do'/>";
        document.listForm.submit();
	}
}

function fncLoginUserAbsnceListDelete() {
	if(fncManageChecked()) {
        if(confirm("<spring:message code="ussIonUas.userAbsnceList.deleteMsg" />")) {/* 삭제하시겠습니까? */
            document.listForm.action = "<c:url value='/uss/ion/uas/removeUserAbsnceList.do'/>";
            document.listForm.submit();
        }
    }
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectUserAbsnceList('1');
    }
}
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/uas/selectUserAbsnceList.do'/>" method="post">
	<h1><spring:message code="ussIonUas.userAbsnceList.userAbsnceList" /></h1><!-- 사용자부재 관리 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="ussIonUas.userAbsnceList.userNm" /> : </label><!-- 사용자 명 -->
				<input name="searchKeyword" type="text" value="<c:out value="${userAbsnceVO.searchKeyword}"/>" size="25" onkeypress="press();" title="검색단어입력"/>
				
				<label for="" style="margin-left:10px"><spring:message code="ussIonUas.userAbsnceList.userAbsnceAt" /> : </label><!-- 부재여부 -->
				<select name="selAbsnceAt" title="<spring:message code="ussIonUas.userAbsnceList.userAbsnceAt" />"><!-- 부재여부선택 -->
                     <option value="A" <c:if test="${userAbsnceVO.selAbsnceAt eq 'A'}">selected</c:if>><spring:message code="ussIonUas.userAbsnceList.all" /></option>
                     <option value="Y" <c:if test="${userAbsnceVO.selAbsnceAt eq 'Y'}">selected</c:if>>Y</option>
                     <option value="N" <c:if test="${userAbsnceVO.selAbsnceAt eq 'N'}">selected</c:if>>N</option>
                 </select>
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectUserAbsnceList('1'); return false;" />
			</li>
		</ul>
	</div>
	<input type="hidden" name="searchCondition" value="1">
	<input type="hidden" name="userId">
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussIonUas.userAbsnceList.userId" /></th><!-- 사용자 ID -->
			   <th scope="col"><spring:message code="ussIonUas.userAbsnceList.userNm" /></th><!-- 사용자 명 -->
			   <th scope="col"><spring:message code="ussIonUas.userAbsnceList.userAbsnceAt" /></th><!-- 부재여부 -->
			   <th scope="col"><spring:message code="ussIonUas.userAbsnceList.regYn" /></th><!-- 등록여부 -->
			   <th scope="col"><spring:message code="ussIonUas.userAbsnceList.lastUpdusrPnttm" /></th><!-- 등록일시 -->
			</tr>
		</thead>
		<tbody>
			 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(userAbsnceList) == 0}">
			<tr>
			<td colspan="5">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			 <c:forEach var="userAbsnce" items="${userAbsnceList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/uss/ion/uas/getUserAbsnce.do'/>">
			            <input type="hidden" name="userId" value="<c:out value="${userAbsnce.userId}"/>">
			            <input type="hidden" name="selAbsnceAt" value="<c:out value="${userAbsnceVO.selAbsnceAt}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${userAbsnceVO.pageIndex}'/>">
			            <input type="hidden" name="searchCondition" value="<c:out value='${userAbsnceVO.searchCondition}'/>">
			            <input type="hidden" name="searchKeyword" value="<c:out value="${userAbsnceVO.searchKeyword}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${userAbsnce.userId}"/>" onclick="fncSelectUserAbsnce('<c:out value="${userAbsnce.userId}"/>', '<c:out value="${userAbsnce.regYn}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${userAbsnce.userNm}"/></td>
			    <td>
			      <c:if test="${userAbsnce.userAbsnceAt eq 'Y'}" ><c:out value="Y"/></c:if>
			      <c:if test="${userAbsnce.userAbsnceAt eq 'N'}" ><c:out value="N"/></c:if>
			    </td>
			    <td>
			      <c:if test="${userAbsnce.regYn eq 'Y'}" ><c:out value="Y"/></c:if>
			      <c:if test="${userAbsnce.regYn eq 'N'}" ><c:out value="N"/></c:if>
			    </td>
			    <td><c:out value="${fn:substring(userAbsnce.lastUpdusrPnttm, 0, 19)}"/></td>
			  </tr>
			 </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<c:if test="${!empty userAbsnceVO.pageIndex }">
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
	</c:if>
</div>
</body>
</html>
