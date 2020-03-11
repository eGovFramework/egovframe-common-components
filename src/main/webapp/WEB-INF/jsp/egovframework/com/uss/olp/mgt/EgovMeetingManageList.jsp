<%--
  Class Name : EgovMeetingManageList.jsp
  Description : 회의정보 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2009.03.09   장동한            최초 생성
    2010.08.06   장동한            웹표준화/장애인접근성적용
    2018.09.03   이정은            공통컴포넌트 3.8 개선
    2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussOlpMgt.meetingManageList.meetingManageList"/></title><!-- 회의관리 목록 -->
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_MeetingManage(){
	location.href = "<c:url value='/uss/olp/mgt/EgovMeetingManageRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify_MeetingManage(){
	location.href = "<c:url value='/uss/olp/mgt/EgovMeetingManageModify.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_MeetingManage(mtgId){
	var vFrom = document.listForm;
	vFrom.mtgId.value = mtgId;
	vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_MeetingManage(mtgId){
	var vFrom = document.listForm;
	if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
		vFrom.mtgId.value = mtgId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_MeetingManage(){
	var vFrom = document.listForm;


	vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="board">
	<h1><spring:message code="ussOlpMgt.meetingManageList.meetingManageList"/></h1><!-- 회의관리 목록 -->
	<form name="listForm" id="listForm" action="#" method="post">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="select.searchCondition"/>"><!-- 검색조건선택 -->
					<option value=''>--<spring:message code="input.select"/>--</option><!-- 선택하세요 -->
					<option value='MTG_NM' <c:if test="${searchCondition == 'MTG_NM'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageList.mtgNm"/></option><!-- 회의명 -->
					<option value='MTG_MTR_CN' <c:if test="${searchCondition == 'MTG_MTR_CN'}">selected</c:if>><spring:message code="ussOlpMgt.meetingManageList.mtgMtrCn"/></option><!-- 회의안건내용 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value='${searchKeyword}'/>" size="25" title="<spring:message code="input.input"/>" /><!-- 검색단어입력 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="button.inquire" />" title="<spring:message code="button.inquire" />" onclick="fn_egov_search_MeetingManage(); return false;" />
				<span class="btn_b"><a href="<c:url value='/uss/olp/mgt/EgovMeetingManageRegist.do' />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
<input name="mtgId" type="hidden" value="">
<input name="mtgNm" type="hidden" value="">
<input name="mtgMtrCn" type="hidden" value="">
<input name="mtgSn" type="hidden" value="">
<input name="mtgCo" type="hidden" value="">
<input name="mtgDe" type="hidden" value="">
<input name="cmd" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	<table class="board_list">
		<caption><spring:message code="ussOlpMgt.meetingManageList.meetingManageList"/></caption><!-- 회의 관리 목록 -->
		<colgroup>
			<col style="width:55px" />
			<col style="width:60px" />
			<col style="" />
			<col style="width:100px" />
			<col style="width:80px" />
			<col style="width:80px" />
			<col style="width:80px" />
			<col style="width:90px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num"/></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.mtgDe"/></th><!-- 회의일자 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.mtgNm"/></th><!-- 회의명 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.mtgPlace"/></th><!-- 회의장소 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.mtgBeginTime"/></th><!-- 시작시간 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.mtgEndTime"/></th><!-- 종료시간 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.frstRegisterNm"/></th><!-- 작성자명 -->
			   <th scope="col"><spring:message code="ussOlpMgt.meetingManageList.frstRegisterPnttm"/></th><!-- 등록일자 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
				<td>
					<a href="<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do'/>?mtgId=${resultInfo.mtgId}&pageIndex=${searchVO.pageIndex}" onClick="fn_egov_detail_MeetingManage('<c:out value="${resultInfo.mtgId}"/>');return false;"><c:out value="${resultInfo.mtgDe}"/></a>
					
				</td>
				<td>
					<a href="<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do'/>?mtgId=${resultInfo.mtgId}&pageIndex=${searchVO.pageIndex}" onClick="fn_egov_detail_MeetingManage('<c:out value="${resultInfo.mtgId}"/>');return false;"><c:out value="${resultInfo.mtgNm}"/></a>
				</td>
				<td>${resultInfo.mtgPlace}</td>
				<td>
					<c:forTokens var="one"
						items="${resultInfo.mtgBeginTime}"
						delims=":" varStatus="sts">
						<c:if test="${sts.count == 1}">${one}시</c:if>
						<c:if test="${sts.count == 2}">${one}분</c:if>
					</c:forTokens>
				</td>
				<td>
					<c:forTokens var="one"
						items="${resultInfo.mtgEndTime}"
						delims=":" varStatus="sts">
						<c:if test="${sts.count == 1}">${one}시</c:if>
						<c:if test="${sts.count == 2}">${one}분</c:if>
					</c:forTokens>
				</td>
				<td>${resultInfo.frstRegisterNm}</td>
				<td nowrap>${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
			</tr>
		</c:forEach>
		<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
		<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="8">
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
