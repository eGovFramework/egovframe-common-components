<%--
  Class Name : EgovRssTagManageList.jsp
  Description : RSS태그관리 목록조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.05    장동한          최초 생성
     2018.09.11    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.05

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonRss.rssTagManageList.rssTagManageList"/></title><!-- RSS태그관리 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/ion/rss/listRssTagManage.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_egov_detail_RssTagManage(noteId,noteTrnsmitId){
	var vFrom = document.listForm;
	vFrom.noteId.value = noteId;
	vFrom.noteTrnsmitId.value = noteTrnsmitId;
	vFrom.action = "<c:url value='/uss/ion/rss/detailRssTagManage.do'/>";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_RssTagManage(){
	var vFrom = document.listForm;
	vFrom.pageIndex.value = "1";

	if( vFrom.searchCondition.selectedIndex == 0){
		alert("<spring:message code="ussIonRss.rssTagManageList.validate.searchCondition"/>"); /* 검색조건을 선택해주세요! */
		vFrom.searchCondition.focus();
		return;
	}

	if( vFrom.searchKeyword.value == ""){
		alert("<spring:message code="ussIonRss.rssTagManageList.validate.searchKeyword"/>"); /* 검색어를 입력 해주세요! */
		vFrom.searchKeyword.focus();
		return;
	}


	vFrom.action = "<c:url value='/uss/ion/rss/listRssTagManage.do'/>";
	vFrom.submit();

}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_RssTagManage(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.listForm.checkList.checked = checkAllValue;
	}{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
	}

}
/* ********************************************************
* 체크 박스 선태 건수
******************************************************** */
var g_nDelCount  = 0;
function fn_egov_delCnt_NoteRecptn(){

	g_nDelCount = 0;
	var FLength = document.getElementsByName("checkList").length;

	//undefined
	if( FLength == 1){
		if(document.listForm.checkList.checked == true){g_nDelCount++;}
	}{
			for(var i=0; i < FLength; i++)
			{
				if(document.getElementsByName("checkList")[i].checked == true){g_nDelCount++;}
			}
	}

	return g_nDelCount;

}
/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_egov_delete_RssTagManage(){
	var vFrom = document.listForm;

	if(fn_egov_delCnt_NoteRecptn() == 0){alert("<spring:message code="ussIonRss.rssTagManageList.validate.selectDelete"/>"); document.getElementById('checkAll').focus();return;}/* 삭제할 목록을 선택해주세요! */

	if(confirm("<spring:message code="ussIonRss.rssTagManageList.validate.deleteAlert"/>")){/* 선택된 정보를 삭제 하시겠습니까? */
		vFrom.action = "<c:url value='/uss/ion/rss/listRssTagManage.do'/>";
		vFrom.cmd.value = 'del';
		vFrom.submit();
	}

}
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
<form name="listForm" action="<c:url value='/uss/ion/rss/listRssTagManage.do'/>" method="post">
	<h1><spring:message code="ussIonRss.rssTagManageList.rssTagManageList"/></h1><!-- RSS태그관리 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="select.searchCondition"/>" class="select"><!-- 검색조건 -->
				   <option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
				   <option value='A.TRGET_SVC_NM' 		<c:if test="${searchCondition == 'A.TRGET_SVC_NM'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.trgetSvcNm"/></option><!-- 대상서비스명 -->
				   <option value='A.TRGET_SVC_TABLE'	<c:if test="${searchCondition == 'A.TRGET_SVC_TABLE'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.trgetSvcTable"/></option><!-- 대상테이블명 -->
				   <option value='A.HDER_TITLE'			<c:if test="${searchCondition == 'A.HDER_TITLE'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.hderTitle"/></option><!-- 헤더TITLE -->
				   <option value='A.HDER_LINK'			<c:if test="${searchCondition == 'A.HDER_LINK'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.hderLink"/></option><!-- 헤더LINK -->
				   <option value='A.HDER_DESCRIPTION'	<c:if test="${searchCondition == 'A.HDER_DESCRIPTION'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.hderDescription"/></option><!-- 헤더DESCRIPTION -->
				   <option value='A.HDER_TAG'			<c:if test="${searchCondition == 'A.HDER_TAG'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.hderTag"/></option><!-- 헤더TAG -->
				   <option value='A.HDER_ETC'			<c:if test="${searchCondition == 'A.HDER_ETC'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.hderEtc"/></option><!-- 헤더ETC -->
				   <option value='A.BDT_LINK'			<c:if test="${searchCondition == 'A.BDT_LINK'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.bdtLink"/></option><!-- 본문LINK -->
				   <option value='A.BDT_DESCRIPTION'	<c:if test="${searchCondition == 'A.BDT_DESCRIPTION'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.bdtDescription"/></option><!-- 본문DESCRIPTION -->
				   <option value='A.BDT_TAG'			<c:if test="${searchCondition == 'A.BDT_TAG'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.bdtTag"/></option><!-- 본문TAG -->
				   <option value='A.BDT_ETC'			<c:if test="${searchCondition == 'A.BDT_ETC'}">selected</c:if>><spring:message code="ussIonRss.rssTagManageList.bdtEtc"/></option><!-- 본문ETC -->
			   </select>
			   
				<input class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value='${searchKeyword}'/>" size="10" maxlength="35" onkeypress="press();" onkeyup="if(window.event.keyCode==13){fn_egov_search_RssTagManage(); return false;}" title="<spring:message code="input.input"/>" /><!-- 검색어 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_search_RssTagManage(); return false;" />
				<input class="s_btn" type="submit" value="<spring:message code="button.delete"/>" title="<spring:message code="button.delete"/>" onclick="fn_egov_delete_RssTagManage(); return false;" /><!-- 삭제 -->
				<span class="btn_b"><a href="<c:url value='/uss/ion/rss/registRssTagManage.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:30px" />
			<col style="width:35px" />
			<col style="" />
			<col style="width:200px" />
			<col style="width:70px" />
			<col style="width:70px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="checkAll" id="checkAll" title="전체선택" value="1" onclick="fn_egov_checkAll_RssTagManage();"></th>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
			   <th scope="col"><spring:message code="ussIonRss.rssTagManageList.trgetSvcNm"/></th><!-- 대상서비스명 -->
			   <th scope="col"><spring:message code="ussIonRss.rssTagManageList.trgetSvcTable"/></th><!-- 대상테이블명 -->
			   <th scope="col"><spring:message code="ussIonRss.rssTagManageList.frstRegisterNm"/></th><!-- 작성자 -->
			   <th scope="col"><spring:message code="ussIonRss.rssTagManageList.frstRegisterPnttm"/></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			 <c:if test="${fn:length(resultList) == 0}">
			<tr>
			<td colspan="6">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td>
					<input type="checkbox" name="checkList" title="선택" value="${resultInfo.rssId}"><!-- 선택 -->
				</td>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td style="word-break;break-all">
				<div style="visibility:hidden;display:none;"><a href="#LINK_PAGE${status.count}"></a></div>
				<a href="<c:url value='/uss/ion/rss/detailRssTagManage.do?pageIndex=${searchVO.pageIndex}&amp;rssId=${resultInfo.rssId}'/>"><c:out value="${resultInfo.trgetSvcNm}"/></a>
				</td>
				<td style="word-break;break-all">
					<div class="divDotText" style="width:200px;border:0px;">
					<a href="<c:url value='/uss/ion/rss/detailRssTagManage.do?pageIndex=${searchVO.pageIndex}&amp;rssId=${resultInfo.rssId}'/>"><c:out value="${resultInfo.trgetSvcTable}"/></a>
					</div>
				</td>
				<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
			    <td><c:out value="${resultInfo.frstRegisterPnttm}"/></td>
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
<input name="cmd" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>
</body>
</html>