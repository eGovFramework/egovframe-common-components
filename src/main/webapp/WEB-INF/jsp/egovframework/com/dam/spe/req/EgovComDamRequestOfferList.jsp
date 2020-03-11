<%--
  Class Name : EgovComDamRequestOfferList.jsp
  Description : 지식 정보제공/정보요청 목록
  Modification Information

	수정일         		수정자              수정내용
    ----------    	--------    ---------------------------
    2010.08.30  	장동한    		최초 생성
    2011.08.11		정진오		조회 조건 select element 하위 option element value를
    							A.KNO_NM에서 A.KNWLDG_NM로,
 								A.KNO_CN에서 A.KNWLDG_CN로 변경함
    2018.09.12      신용호             공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.30

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comDamSpeReq.comDamRequestOfferList.title"/></title><!-- 지식 정보제공/정보요청-목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_RequestOffer(){
	// 첫 입력란에 포커스..
	document.listForm.searchCondition.focus();
}
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/dam/spe/req/listRequestOffer.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_RequestOffer(noteId,noteTrnsmitId){
	var vFrom = document.listForm;
	vFrom.noteId.value = noteId;
	vFrom.noteTrnsmitId.value = noteTrnsmitId;
	vFrom.action = "<c:url value='/dam/spe/req/detailRequestOffer.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_RequestOffer(){
	var vFrom = document.listForm;
	vFrom.pageIndex.value = "1";
	vFrom.action = "<c:url value='/dam/spe/req/listRequestOffer.do' />";
	vFrom.submit();

}

</script>
</head>
<body onLoad="fn_egov_initl_RequestOffer();">

<!-- 자바스크립트 지원 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comDamSpeReq.comDamRequestOfferList.pageTop.title"/></h1><!-- 지식 정보제공/정보요청 목록 -->

	<form name="listForm" action="<c:url value='/dam/spe/req/listRequestOffer.do'/>" method="post">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition"/>" class="select"><!-- 검색조건 -->
				<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
				<option value='A.KNWLDG_NM' 		<c:if test="${searchCondition == 'A.KNWLDG_NM'}">selected</c:if>><spring:message code="comDamSpeReq.comDamRequestOfferList.knoNm"/></option><!-- 지식명 -->
				<option value='A.KNWLDG_CN' 		<c:if test="${searchCondition == 'A.KNWLDG_CN'}">selected</c:if>><spring:message code="comDamSpeReq.comDamRequestOfferList.knoCn"/></option><!-- 지식내용 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value='${searchKeyword}'/>" maxlength="35" size="10" onkeyup="if(window.event.keyCode==13){fn_egov_search_RequestOffer(); return false;}" title="<spring:message code="title.search"/>" /><!-- 검색어 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_search_RequestOffer(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/dam/spe/req/registRequestOffer.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:7%" />
			<col style="width:10%" />
			<col style="width:18%" />
			<col style="width:35%" />
			<col style="width:15%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comDamSpeReq.comDamRequestOfferList.knoId"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comDamSpeReq.comDamRequestOfferList.orgnztNm"/></th><!-- 조직명 -->
			   <th scope="col"><spring:message code="comDamSpeReq.comDamRequestOfferList.knoTypeNm"/></th><!-- 지식유형명 -->
			   <th scope="col"><spring:message code="comDamSpeReq.comDamRequestOfferList.knoNm"/></th><!-- 지식명 -->
			   <th scope="col"><spring:message code="comDamSpeReq.comDamRequestOfferList.frstRegisterNm"/></th><!-- 등록자 -->
			   <th scope="col"><spring:message code="comDamSpeReq.comDamRequestOfferList.frstRegistPnttm"/></th><!-- 등록일자 -->
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
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.orgnztNm}"/></td>
				<td><c:out value="${resultInfo.knoTypeNm}"/></td>
				<td>
					<c:forEach var="i" begin="1" end="${resultInfo.ansDepth}" step="1">
					<c:if test="${i != resultInfo.ansDepth}">&nbsp;&nbsp;&nbsp;</c:if><c:if test="${i == resultInfo.ansDepth}">┗RE:&nbsp;</c:if>
					</c:forEach>
					<a href="<c:url value='/dam/spe/req/detailRequestOffer.do'/>?pageIndex=${searchVO.pageIndex}&amp;knoId=${resultInfo.knoId}"><c:out value="${resultInfo.knoNm}"/></a>
				</td>
				<td><c:out value="${resultInfo.frstRegisterNm}"/></td>
			    <td><c:out value="${resultInfo.frstRegistPnttm}"/></td>
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

	<input name="cmd" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	</form>

</div>

</body>
</html>