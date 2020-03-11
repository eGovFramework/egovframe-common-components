<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovFileNmSearch.jsp
  * @Description : 프로그램파일명 검색 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.10    이용          최초 생성
  *   2011.10.18    서준식       프로그램파일명 검색 결과를 부모창으로 넘겨주는 자바스크립트 수정(브라우저 호환성 문제로 수정함)
  
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymPrm.fileNmSearch.title"/></title><!-- 프로그램파일명 검색 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery-1.4.2.min.js' />" ></script>
<script language="javascript1.2"  type="text/javaScript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.progrmManageForm.pageIndex.value = pageNo;
	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListSearch.do'/>";
   	document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramListSearch() {
	document.progrmManageForm.pageIndex.value = 1;
	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListSearch.do'/>";
	document.progrmManageForm.submit();
}

/* ********************************************************
 * 프로그램목록 선택 처리 함수
 ******************************************************** */
function choisProgramListSearch(vFileNm) {
	//eval("opener.document.all."+opener.document.all.tmp_SearchElementName.value).value = vFileNm;
	//opener.document.menuManageVO.progrmFileNm.value = vFileNm;
	var parentFrom = opener.document.getElementsByTagName('form');
	parentFrom[0].progrmFileNm.value = vFileNm;
    window.close();
}
-->
</script>
</head>
<body>
<form name="progrmManageForm" action ="<c:url value='/sym/prm/EgovProgramListSearch.do'/>" method="post">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

<div class="board" style="width:480px">
	<h1><spring:message code="comSymPrm.fileNmSearch.pageTop.title"/></h1><!-- 프로그램파일명 검색 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymPrm.fileNmSearch.progrmFileNm"/> : </label><!-- 프로그램파일명 -->
				<input class="s_input2 vat" name="searchKeyword" type="text" value="" size="30" maxlength="60" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title="<spring:message code="title.inquire"/>" onclick="selectProgramListSearch(); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:50%" />
			<col style="width:50%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymPrm.fileNmSearch.progrmFileNm"/></th><!-- 프로그램파일명 -->
			   <th scope="col"><spring:message code="comSymPrm.fileNmSearch.progrmNm"/></th><!-- 프로그램명 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
			  <tr>
			    <td>
			      <span class="link"><a href="#LINK" onclick="choisProgramListSearch('<c:out value="${result.progrmFileNm}"/>'); return false;">
			      <c:out value="${result.progrmFileNm}"/></a></span></td>
			    <td><c:out value="${result.progrmKoreanNm}"/></td>
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

</form>
</body>
</html>

