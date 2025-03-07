<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovLoginScrinImageList.java
 * @Description : EgovLoginScrinImageList jsp
 * @Modification Information
 * @
 * @  수정일       수정자				수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.07.01    lee.m.j				최초 생성
 * @ 2018.09.14    이정은               공통컴포넌트 3.8 개선
 * @ 2024.10.29    권태성				pageIndex 파라미터 추가
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
<title><spring:message code="ussIonLsi.loginScrinImageList.loginScrinImageList"/></title><!-- 로그인화면이미지관리 목록 -->
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
                alert("<spring:message code="ussIonLsi.loginScrinImageList.noImage"/>");/* 선택된 로그인화면 이미지가 없습니다. */
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="ussIonLsi.loginScrinImageList.noImage"/>");/* 선택된 로그인화면 이미지가 없습니다. */
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("<spring:message code="ussIonLsi.loginScrinImageList.failInquire"/>");/* 조회된 결과가 없습니다. */
    }

    document.listForm.imageIds.value = returnValue;
    return returnBoolean;
}

function fncSelectLoginScrinImageList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/lsi/selectLoginScrinImageList.do'/>";
    document.listForm.submit();
}

function fncSelectLoginScrinImage(imageId) {
    document.listForm.imageId.value = imageId;
    document.listForm.action = "<c:url value='/uss/ion/lsi/getLoginScrinImage.do'/>";
    document.listForm.submit();
}

function fncAddLoginScrinImageInsert() {
    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    }
    document.listForm.action = "<c:url value='/uss/ion/lsi/addViewLoginScrinImage.do'/>";
    document.listForm.submit();
}

function fncLoginScrinImageListDelete() {
	if(fncManageChecked()) {
        if(confirm("<spring:message code="ussIonLsi.loginScrinImageList.deleteImage"/>")) {/* 삭제하시겠습니까? */
            document.listForm.action = "<c:url value='/uss/ion/lsi/removeLoginScrinImageList.do'/>";
            document.listForm.submit();
        }
    }
}

function press() {
    if (event.keyCode==13) {
    	fncSelectLoginScrinImageList('1');
    }
}
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="ussIonLsi.loginScrinImageList.loginScrinImageList"/></h1><!-- 로그인화면이미지 관리 -->

	<form name="listForm" action="<c:url value='/uss/ion/lsi/selectLoginScrinImageList.do'/>" method="post">
	
		<div class="search_box" title=<spring:message code="common.searchCondition.msg"/>><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
			<ul>
				<li>
					<label for="searchKeyword"><spring:message code="ussIonLsi.loginScrinImageList.imageNm"/> : </label><!-- 이미지 명 -->
					<input id="searchKeyword" class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${loginScrinImageVO.searchKeyword}"/>' size="25" onkeypress="press();" title=<spring:message code="button.search"/> /><!-- 검색 -->
					
					<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectLoginScrinImageList('1'); return false;" />
					<span class="btn_b"><a href="<c:url value='/uss/ion/lsi/addViewLoginScrinImage.do'/>?pageIndex=<c:out value='${loginScrinImageVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${loginScrinImageVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncAddLoginScrinImageInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				</li>
			</ul>
		</div>
		<input type="hidden" name="searchCondition" value="1">
		<input type="hidden" name="pageIndex" value="<c:out value='${loginScrinImageVO.pageIndex}'/>">
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:40%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="ussIonLsi.loginScrinImageList.imageNm"/></th><!-- 이미지 명 -->
			   <th scope="col"><spring:message code="ussIonLsi.loginScrinImageList.image"/></th><!-- 이미지 -->
			   <th scope="col"><spring:message code="ussIonLsi.loginScrinImageList.imageDc"/></th><!-- 이미지설명 -->
			   <th scope="col"><spring:message code="ussIonLsi.loginScrinImageList.reflctAtt"/></th><!-- 반영여부 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(loginScrinImageList) == 0}">
			<tr>
				<td colspan="4">
				<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
			
			<c:forEach var="loginScrinImage" items="${loginScrinImageList}" varStatus="status">
			<tr>
				<td>
					<form name="item" method="post" action="<c:url value='/uss/ion/lsi/getLoginScrinImage.do'/>">
						<input type="hidden" name="imageId" value="<c:out value="${loginScrinImage.imageId}"/>">
						<input type="hidden" name="pageIndex" value="<c:out value='${loginScrinImageVO.pageIndex}'/>">
						<input type="hidden" name="searchCondition" value="<c:out value='${loginScrinImageVO.searchCondition}'/>">
						<input type="hidden" name="searchKeyword" value="<c:out value="${loginScrinImageVO.searchKeyword}"/>">
						<input class="link" type="submit" value="<c:out value="${loginScrinImage.imageNm}"/>">
					</form>
				</td>
				<td><c:out value="${loginScrinImage.image}"/></td>
				<td><c:out value="${loginScrinImage.imageDc}"/></td>
				<td><c:out value="${loginScrinImage.reflctAt}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_tmplatInfo"/>
		</ul>
	</div>
</div>
</body>
</html>
