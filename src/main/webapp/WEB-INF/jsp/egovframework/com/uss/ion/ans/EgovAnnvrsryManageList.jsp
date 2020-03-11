<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovAnnvrsryManageList.java
 * @Description : EgovAnnvrsryManageList jsp
 * @Modification Information
 * @
 * @  수정일           수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이     용                최초 생성
 * @ 2018.08.13    최 두 영                퍼블리싱 점검 수정
 * @ 2018.09.19    최 두 영                다국어처리 
 *
 *  @author 이      용
 *  @since 2010.06.29
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
<title><spring:message code="comUssIonAns.annvrsryManageList.title"/></title><!-- 기념일관리 목록 조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">

	/*설명 : 기념일 목록 조회 */
	function fncSelectAnnvrsryManageList(pageNo){
	    document.listForm.searchCondition.value = "1";
	    document.listForm.pageIndex.value = pageNo;
	    document.target="main_right";
	    document.listForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>";
	    document.listForm.submit();
	}

	/*설명 : 기념일 상세조회 */
	function fncSelectAnnvrsryManage(annId) {
	    document.listForm.annId.value = annId;
	    document.listForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManage.do'/>";
	    document.listForm.submit();   
	}
	
	/*설명 : 기념일 신규등록 화면 호출 */
	function fncInsertAnnvrsry() {
		if(document.listForm.pageIndex.value == "") {
			document.listForm.pageIndex.value = 1;
		} 
	    document.listForm.action = "<c:url value='/uss/ion/ans/insertViewAnnvrsry.do'/>";
	    document.listForm.submit(); 
	}
	
	/*설명 : 기념일 목록 페이지 조회 */
	function linkPage(pageNo){
	    document.listForm.searchCondition.value = "1";
	    document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>";
	    document.listForm.submit();
	}

	/*설명 : 기념일 엑셀등록 PopUp 화면 호출	 */
    $(document).ready(function () {
        $('#excelPopUp').click(function (e) {
        	e.preventDefault();
            
            var pagetitle = $(this).attr("title");
            var page = "<c:url value='/uss/ion/ans/EgovAnnvrsryManageListPop.do'/>";
        	
            var $dialog = $('<div></div>')
	            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
	            .dialog({
	            	autoOpen: false,
	                modal: true,
	                height: 750,
	                width: 770,
	                title: pagetitle
	        	});
        	$dialog.dialog('open');
    	});
	});

</script>

</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">

	<form name="listForm" action="<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>" method="post">    

	<h1><spring:message code="comUssIonAns.annvrsryManageList.annvrsryManageList"/></h1><!-- 기념일관리 목록 -->
	
	<span>※  <spring:message code="comUssIonAns.annvrsryMainList.option"/></span><!-- 매년반복 옵션을 사용할 경우  년도 조회 시 해당 년도가 아니어도 매년 적용되어 조회됨 -->

	<input type="hidden" name="annId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty annvrsryManageVO.pageIndex }">1</c:if><c:if test="${!empty annvrsryManageVO.pageIndex }"><c:out value='${annvrsryManageVO.pageIndex}'/></c:if>">
	<input type="hidden" name="searchCondition" value="1">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonAns.common.yearList"/> : </label><!-- 년도 -->
				<select name="searchKeyword" title="<spring:message code="comUssIonAns.common.yearList"/>"><!-- 년도 -->
				<c:forEach items="${yearList}" var="result" varStatus="status">
				<option value="<c:out value="${result}"/>"  <c:if test="${annvrsryManageVO.searchKeyword eq result}">selected</c:if>><c:out value="${result}"/></option>
				</c:forEach>
				</select> <spring:message code="comUssIonAns.common.year"/><!-- 년 -->
         
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectAnnvrsryManageList('1'); return false;" style="margin-left:20px" />
				<span class="btn_b"><a href="<c:url value='/uss/ion/ans/insertViewAnnvrsry.do'/>?searchCondition=1" onclick="fncInsertAnnvrsry(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				<span class="btn_b"><a id="excelPopUp"><spring:message code="comUssIonAns.annvrsryManageList.excelRegiser"/></a></span><!-- 새 창으로 이동 --><!-- 기념일엑셀등록 -->
			</li>
		</ul>
	</div>
 </form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="width:20%" />
			<col style="width:15%" />
			<col style="" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryNm"/></th><!-- 기념일제목 -->
			   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryDe"/></th><!-- 기념일(양/음) -->
			   <th scope="col"><spring:message code="comUssIonAns.common.memo"/></th><!-- 메모 -->
			   <th scope="col"><spring:message code="comUssIonAns.common.annvrsrySetup"/></th><!-- 알림여부 -->
			   <th scope="col">D-day</th>
			   <th scope="col"><spring:message code="comUssIonAns.common.reptitSe"/></th><!-- 반복여부 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="annvrsryManage" items="${annvrsryManageList}" varStatus="status">
			<tr>
				<td><c:out value="${(annvrsryManageVO.pageIndex - 1) * annvrsryManageVO.pageSize + status.count}"/></td>
				<td>
					<form name="item" method="post" action="<c:url value='/uss/ion/ans/selectAnnvrsryManage.do'/>">
					<input type="hidden" name="annId"      value="<c:out value="${annvrsryManage.annId     }"/>">
					<span class="link"><input type="submit" value="<c:out value="${annvrsryManage.annvrsryNm}"/>" onclick="fncSelectAnnvrsryManage('<c:out value="${annvrsryManage.annId}"/>'); return false;" style="text-align : left;"></span>
					</form>
				</td>
				<td><c:out value="${annvrsryManage.annvrsryDe}"/>
					<c:if test="${!empty annvrsryManage.cldrSe }">(<c:if test='${annvrsryManage.cldrSe == "1"}'><spring:message code="comUssIonAns.common.cldrSe1"/>
					</c:if><!-- 양 --><c:if test='${annvrsryManage.cldrSe == "2"}'><spring:message code="comUssIonAns.common.cldrSe2"/>
					</c:if>)</c:if><!-- 음 -->
				</td>
				<td><c:out value="${annvrsryManage.memo}"/></td>
				<td><c:if test='${annvrsryManage.annvrsrySetup == "Y"}'>ON</c:if>
							<c:if test='${annvrsryManage.annvrsrySetup == "N"}'>OFF</c:if></td> <!-- 알림여부 -->
				<td><c:if test="${!empty annvrsryManage.annvrsryBeginDe }">D-<c:out value='${annvrsryManage.annvrsryBeginDe}'/><c:set var="dday"><spring:message code="comUssIonAns.common.annvrsryBeginDe"/></c:set><!-- 일전 --></c:if>
				</td><!-- D-day -->
				<td>
					<c:choose>
						<c:when test="${'1' eq annvrsryManage.reptitSe }">Y</c:when>
						<c:otherwise>N</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(annvrsryManageList) == 0}">
			<tr> 
				<td colspan="7">
				<spring:message code="common.nodata.msg" />
				</td>
			</tr>   	          				 			   
			</c:if>
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