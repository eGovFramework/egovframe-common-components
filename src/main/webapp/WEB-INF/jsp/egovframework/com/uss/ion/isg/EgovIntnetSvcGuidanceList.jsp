<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovIntnetSvcGuidanceList.jsp
 * @Description : EgovIntnetSvcGuidanceList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.07.01    lee.m.j     최초 생성
 * @ 2018.08.20    이정은               공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2009.08.14
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
<title><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceList" /></title>
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

function fncSelectIntnetSvcGuidanceList(pageNo) {
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>";
    document.listForm.submit();
}

function fncSelectIntnetSvcGuidance(intnetSvcId) {
    document.listForm.intnetSvcId.value = intnetSvcId;
    document.listForm.action = "<c:url value='/uss/ion/isg/getIntnetSvcGuidance.do'/>";
    document.listForm.submit();
}

function fncAddIntnetSvcGuidanceInsert() {
    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    }
    document.listForm.action = "<c:url value='/uss/ion/isg/addViewIntnetSvcGuidance.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectIntnetSvcGuidance('1');
    }
}

function fncViewIntnetSvcGuidance() {

	var url = "<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceResultList.do'/>";
    var openParam = "dialogWidth:800px;dialogHeight:600px;scroll:yes;status:no;center:yes;resizable:yes;";
    window.open(url,"인터넷서비스안내",'width=800,height=600,scrollbars=yes,resizable=no,status=no,center:yes');
}

</script>

</head>

<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>" method="post">
	<h1><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceList" /></h1><!-- 인터넷서비스안내 관리 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceNm" /> : </label><!-- 인터넷서비스 명 -->
				<input id="searchKeyword" class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value="${intnetSvcGuidanceVO.searchKeyword}"/>" size="25" onkeypress="press();" title="검색" />
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="조회" onclick="fncSelectIntnetSvcGuidanceList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/isg/addViewIntnetSvcGuidance.do'/>?pageIndex=<c:out value='${intnetSvcGuidanceVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${intnetSvcGuidanceVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncAddIntnetSvcGuidanceInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				<span class="btn_b"><a href="<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceResultList.do'/>" onclick="fncViewIntnetSvcGuidance(); return false;" title='새 창으로 이동' target="_blank"><spring:message code="button.confirm" /></a></span>
			</li>
		</ul>
	</div>
<input type="hidden" name="intnetSvcId">
<input type="hidden" name="pageIndex" value="<c:if test="${empty intnetSvcGuidanceVO.pageIndex }">1</c:if><c:if test="${!empty intnetSvcGuidanceVO.pageIndex }"><c:out value='${intnetSvcGuidanceVO.pageIndex}'/></c:if>">
<input type="hidden" name="searchCondition" value="1">
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:40%" />
			<col style="width:10%" />
			<col style="width:25%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceId"/></th><!-- 인터넷서비스 ID -->
			   <th scope="col"><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceNm"/></th><!-- 인터넷서비스 명 -->
			   <th scope="col"><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceReflctAt"/></th><!-- 반영여부 -->
			   <th scope="col"><spring:message code="uss.ion.isg.intnetSvcGuidanceList.intnetSvcGuidanceRegDate"/></th><!-- 등록일시 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			 <c:if test="${fn:length(intnetSvcGuidanceList) == 0}">
			 <tr>
			 <td colspan="4">
				<spring:message code="common.nodata.msg" />
			 </td>
			 </tr>
			 </c:if>
			 <tbody>
			 <c:forEach var="intnetSvcGuidance" items="${intnetSvcGuidanceList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/uss/ion/isg/getIntnetSvcGuidance.do'/>">
			            <input type="hidden" name="intnetSvcId" value="<c:out value="${intnetSvcGuidance.intnetSvcId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${intnetSvcGuidanceVO.pageIndex}'/>">
			            <input type="hidden" name="searchCondition" value="<c:out value='${intnetSvcGuidanceVO.searchCondition}'/>">
			            <input type="hidden" name="searchKeyword" value="<c:out value="${intnetSvcGuidanceVO.searchKeyword}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${intnetSvcGuidance.intnetSvcId}"/>" onclick="fncSelectIntnetSvcGuidance('<c:out value="${intnetSvcGuidance.intnetSvcId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${intnetSvcGuidance.intnetSvcNm}"/></td>
			    <td><c:out value="${intnetSvcGuidance.reflctAt}"/></td>
			    <td><c:out value="${fn:substring(intnetSvcGuidance.regDate,0,19)}"/></td>
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
