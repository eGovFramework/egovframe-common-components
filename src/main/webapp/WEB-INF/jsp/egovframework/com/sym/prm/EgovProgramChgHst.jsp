<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovProgramChgHst.jsp
  * @Description : 프로그램변경이력 조회 화면
  * @Modification Information
  * @
  * @  수정일              수정자           수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용              최초 생성
  *   2018.09.04   신용호           공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/prm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/prm/button/";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymPrm.programChgHst.title"/></title><!-- 프로그램변경이력 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//	document.programChgHstForm.searchKeyword.value =
	document.programChgHstForm.pageIndex.value = pageNo;
	document.programChgHstForm.action = "<c:url value='/sym/prm/EgovProgramChgHstListSelect.do'/>";
   	document.programChgHstForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgrmChgHstList() {
	document.programChgHstForm.pageIndex.value = 1;
	document.programChgHstForm.action = "<c:url value='/sym/prm/EgovProgramChgHstListSelect.do'/>";
	document.programChgHstForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectChgHstListDetail(progrmFileNm, tmp_no) {
    document.programChgHstForm.tmpRqesterNo.value = '';
	document.programChgHstForm.tmpProgrmNm.value = progrmFileNm;
	document.programChgHstForm.tmp_rqesterNo.value = tmp_no;
	document.programChgHstForm.action = "<c:url value='/sym/prm/EgovProgramChgHstListDetailSelect.do'/>";
	document.programChgHstForm.submit();
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymPrm.programChgHst.pageTop.title"/></h1><!--프로그램변경이력-->

	<form name="programChgHstForm" action ="<c:url value='/sym/prm/EgovProgramChgHstListSelect.do'/>" method="post">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<div class="search_box" title="이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다.">
		<ul>
			<li>
				<label for=""><spring:message code="comSymPrm.programChgHst.progrmFileNm"/> : </label><!--프로그램파일명-->
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="60" size="80" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>" title="<spring:message code="title.inquire"/>" onclick="selectProgrmChgHstList(); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	<input type="hidden" name="tmpProgrmNm">
	<input type="hidden" name="tmpRqesterNo">
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:30%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymPrm.programChgHst.progrmFileNm"/></th><!--프로그램파일명-->
			   <th scope="col"><spring:message code="comSymPrm.programChgHst.processSttus"/></th><!-- 처리상태 -->
			   <th scope="col"><spring:message code="comSymPrm.programChgHst.rqesterProcessCn"/></th><!-- 처리내용 -->
			   <th scope="col"><spring:message code="comSymPrm.programChgHst.rqesterPersonId"/></th><!-- 변경요청자 -->
			   <th scope="col"><spring:message code="comSymPrm.programChgHst.rqesterDe"/></th><!-- 변경요청일자 -->
			   <th scope="col"><spring:message code="comSymPrm.programChgHst.processDe"/></th><!-- 변경완료일자 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(list_changerequst) == 0}">
			<tr>
			<td colspan="6">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			 <c:forEach var="result" items="${list_changerequst}" varStatus="status">
			  <tr>
			    <td style="cursor:hand;">
			        <form name="item" method="post" action="<c:url value='/sym/prm/EgovProgramChgHstListDetailSelect.do'/>">
						<input type="hidden" name="tmpProgrmNm"   value="<c:out value="${result.progrmFileNm  }"/>"/>
						<input type="hidden" name="tmpRqesterNo"  value="<c:out value="${result.rqesterNo  }"/>"/>
			            <span class="link"><input type="submit" value="<c:out value="${result.progrmFileNm}"/>" onclick="selectChgHstListDetail('<c:out value="${result.progrmFileNm}"/>','<c:out value="${result.rqesterNo}"/>'); return false;"></span>
			        </form></td>
			    <td>
			      <c:if test="${empty result.processSttus}">N/A</c:if>
			      <c:if test="${result.processSttus == 'A'}"><spring:message code="comSymPrm.programChgHst.processSttusA"/></c:if><!-- 신청중 -->
			      <c:if test="${result.processSttus == 'P'}"><spring:message code="comSymPrm.programChgHst.processSttusP"/></c:if><!-- 진행중 -->
			      <c:if test="${result.processSttus == 'R'}"><spring:message code="comSymPrm.programChgHst.processSttusR"/></c:if><!-- 반려 -->
			      <c:if test="${result.processSttus == 'C'}"><spring:message code="comSymPrm.programChgHst.processSttusC"/></c:if><!-- 처리완료 -->
			    </td>
			    <td><c:out value="${result.rqesterProcessCn}"/></td>
			    <td><c:out value="${result.rqesterPersonId}"/></td>
			    <td><c:out value="${result.rqesterDe}"/></td>
			    <td><c:out value="${result.processDe}"/></td>
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
