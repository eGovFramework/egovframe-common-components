<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovProgramChangeRequst.jsp
  * @Description : 프로그램변경요청 화면
  * @Modification Information
  * @
  * @  수정일              수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용               최초 생성
  *   2011.09.14   서준식            리스트 내용 없음 표시
  *   2018.09.03   신용호            공통컴포넌트 3.8 개선
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
<title><spring:message code="comSymPrm.programChangeRequst.title"/></title><!-- 프로그램변경요청 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//	document.progrmChangeRequstForm.searchKeyword.value =
	document.progrmChangeRequstForm.pageIndex.value = pageNo;
	document.progrmChangeRequstForm.action = "<c:url value='/sym/prm/EgovProgramChangeRequstSelect.do'/>";
   	document.progrmChangeRequstForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgrmChangeRequstList() {
	document.progrmChangeRequstForm.pageIndex.value = 1;
	document.progrmChangeRequstForm.action = "<c:url value='/sym/prm/EgovProgramChangeRequstSelect.do'/>";
	document.progrmChangeRequstForm.submit();
}

/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertChangeRequst() {
   	document.progrmChangeRequstForm.action = "<c:url value='/sym/prm/EgovProgramChangRequstStre.do'/>";
   	document.progrmChangeRequstForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtChangeRequstListDetail(progrmFileNm, tmpNo) {
	document.progrmChangeRequstForm.tmpProgrmNm.value  = progrmFileNm;
	document.progrmChangeRequstForm.tmpRqesterNo.value = tmpNo;
	document.progrmChangeRequstForm.action = "<c:url value='/sym/prm/EgovProgramChangRequstDetailSelect.do'/>";
	document.progrmChangeRequstForm.submit();
}

function deleteChangeRequstList() {
	progrmChangeRequstForm.submit();
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript>

<div class="board">
	<h1><spring:message code="comSymPrm.programChangeRequst.pageTop.title"/></h1><!-- 프로그램변경요청 -->

	<form name="progrmChangeRequstForm" action ="<c:url value='/sym/prm/EgovProgramChangeRequstSelect.do'/>?pageIndex=1" method="post">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymPrm.programChangeRequst.progrmFileNm"/> : </label><!-- 프로그램파일명 -->
				<input class="s_input2 vat" name="searchKeyword" type="text" value="" size="80" maxlength="60" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="조회" onclick="selectProgrmChangeRequstList(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/prm/EgovProgramChangRequstStre.do'/>" onclick="insertChangeRequst(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	<input type="hidden" name="tmpProgrmNm">
	<input type="hidden" name="tmpRqesterNo">
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:80px" />
			<col style="width:177px" />
			<col style="width:200px" />
			<col style="width:80px" />
			<col style="width:100px" />
			<col style="width:80px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequst.rqesterNo"/></th><!-- 요청번호 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequst.progrmFileNm"/></th><!-- 프로그램파일명 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequst.rqesterSj"/></th><!-- 요청제목 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequst.rqesterPersonId"/></th><!-- 요청자 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequst.rqesterDe"/></th><!-- 요청일자 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequst.processSttus"/></th><!-- 처리여부 -->
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
			    <td><c:out value="${result.rqesterNo}"/></td>
			    <td>
			      <form name="item" method="post" action="<c:url value='/sym/prm/EgovProgramChangRequstDetailSelect.do'/>">
					<input type="hidden" name="tmpProgrmNm"  value="<c:out value="${result.progrmFileNm  }"/>"/>
					<input type="hidden" name="tmpRqesterNo" value="<c:out value="${result.rqesterNo     }"/>"/>
			        <span class="link"><input type="submit"   value="<c:out value="${result.progrmFileNm  }"/>" onclick="selectUpdtChangeRequstListDetail('<c:out value="${result.progrmFileNm}"/>','<c:out value="${result.rqesterNo}"/>'); return false;"></span>
			      </form>
			    </td>
			    <td><c:out value="${result.rqesterSj}"/></td>
			    <td><c:out value="${result.rqesterPersonId}"/></td>
			    <td><c:out value="${result.rqesterDe}"/></td>
			    <td>
			      <c:if test="${empty result.processSttus}">N/A</c:if>
			      <c:if test="${result.processSttus == 'A'}"><spring:message code="comSymPrm.programChangeRequst.processSttusA"/></c:if><!-- 신청중 -->
			      <c:if test="${result.processSttus == 'P'}"><spring:message code="comSymPrm.programChangeRequst.processSttusP"/></c:if><!-- 진행중 -->
			      <c:if test="${result.processSttus == 'R'}"><spring:message code="comSymPrm.programChangeRequst.processSttusR"/></c:if><!-- 반려 -->
			      <c:if test="${result.processSttus == 'C'}"><spring:message code="comSymPrm.programChangeRequst.processSttusC"/></c:if><!-- 처리완료 -->
			    </td>
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
