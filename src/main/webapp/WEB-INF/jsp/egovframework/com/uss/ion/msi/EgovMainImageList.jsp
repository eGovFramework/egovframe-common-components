<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovMainImageList.java
 * @Description : EgovMainImageList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.07.01    lee.m.j     최초 생성
 * @ 2018.08.30    이정은               공통컴포넌트 3.8 개선
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
<title><spring:message code="uss.ion.msi.mainImageList.mainImageList" /></title><!-- 메인화면이미지 목록조회 -->
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
                alert("<spring:message code="uss.ion.msi.mainImageList.noImage" />");/* 선택된 메인화면 이미지가 없습니다. */
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
            	alert("<spring:message code="uss.ion.msi.mainImageList.noImage" />");/* 선택된 메인화면 이미지가 없습니다. */
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("<spring:message code="uss.ion.msi.mainImageList.failInquire" />");/* 조회된 결과가 없습니다. */
    }

    document.listForm.imageIds.value = returnValue;
    return returnBoolean;
}

function fncSelectMainImageList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/msi/selectMainImageList.do'/>";
    document.listForm.submit();
}

function fncSelectMainImage(imageId) {
    document.listForm.imageId.value = imageId;
    document.listForm.action = "<c:url value='/uss/ion/msi/getMainImage.do'/>";
    document.listForm.submit();
}

function fncAddMainImageInsert() {

    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    }
    document.listForm.action = "<c:url value='/uss/ion/msi/addViewMainImage.do'/>";
    document.listForm.submit();
}

function fncLoginMainImageListDelete() {
	if(fncManageChecked()) {
        if(confirm("<spring:message code="uss.ion.msi.mainImageList.deleteImage" />")) {/* 삭제 하시겠습니까? */
            document.listForm.action = "<c:url value='/uss/ion/msi/removeMainImageList.do'/>";
            document.listForm.submit();
        }
    }
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/msi/selectMainImageList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectMainImageList('1');
    }
}
</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/msi/selectMainImageList.do'/>" method="post">
	<h1><spring:message code="uss.ion.msi.mainImageList.mainImageList" /></h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보에 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="uss.ion.msi.mainImageList.mainImageNm" /> : </label><!-- 이미지 명 -->
				<input id="searchKeyword" class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${mainImageVO.searchKeyword}"/>' size="25" onkeypress="press();" title="<spring:message code="button.search" />" /><!-- 검색 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectMainImageList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/msi/addViewMainImage.do'/>?pageIndex=<c:out value='${mainImageVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${mainImageVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncAddMainImageInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input type="hidden" name="imageId">
<input type="hidden" name="pageIndex" value="<c:if test="${empty mainImageVO.pageIndex }">1</c:if><c:if test="${!empty mainImageVO.pageIndex }"><c:out value='${mainImageVO.pageIndex}'/></c:if>" >
<input type="hidden" name="searchCondition" value="1" >
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:20%" />
			<col style="width:25%" />
			<col style="width:20%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="uss.ion.msi.mainImageList.mainImageNm" /></th><!-- 이미지 명 -->
			   <th scope="col"><spring:message code="uss.ion.msi.mainImageList.mainImage" /></th><!-- 이미지 -->
			   <th scope="col"><spring:message code="uss.ion.msi.mainImageList.mainImageDc" /></th><!-- 이미지 설명 -->
			   <th scope="col"><spring:message code="uss.ion.msi.mainImageList.mainImageReflctAt" /></th><!-- 반영여부 -->
			</tr>
		</thead>
		<tbody>
			 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(mainImageList) == 0}">
			<tr>
				<td colspan="4">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
			
			<c:forEach var="mainImage" items="${mainImageList}" varStatus="status">
			<tr>
				<td>
					<form name="item" method="post" action="<c:url value='/uss/ion/msi/getMainImage.do'/>">
					<input type="hidden" name="imageId" value="<c:out value="${mainImage.imageId}"/>">
					<input type="hidden" name="pageIndex" value="<c:out value='${mainImageVO.pageIndex}'/>">
					<input type="hidden" name="searchCondition" value="<c:out value='${mainImageVO.searchCondition}'/>">
					<input type="hidden" name="searchKeyword" value="<c:out value="${mainImageVO.searchKeyword}"/>">
					<input class="link" type="submit" value="<c:out value="${mainImage.imageNm}"/>" onclick="fncSelectMainImage('<c:out value="${mainImage.imageId}"/>'); return false;">
					</form>
				</td>
				<td><c:out value="${mainImage.image}"/></td>
				<td><c:out value="${mainImage.imageDc}"/></td>
				<td><c:out value="${mainImage.reflctAt}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>
</body>
</html>
