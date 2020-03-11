<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovProgramChangeRequstProcess.jsp
  * @Description : 프로그램변경요청처리 조회 화면
  * @Modification Information
  * @
  * @  수정일               수정자            수정내용
  * @ ----------    --------   ---------------------------
  * @ 2009.03.10    이용               최초 생성
  *   2018.09.04    신용호            공통컴포넌트 3.8 개선
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
<title><spring:message code="comSymPrm.programChangeRequstProcess.title"/></title><!-- 프로그램변경요청처리 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script language="javascript1.2"  type="text/javaScript">
<!--
function initCalendar(){

	$("#searchKeywordFrom").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});

	$("#searchKeywordTo").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true 
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
			
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});

}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.programChangeRequstProcessListForm.pageIndex.value = pageNo;
	document.programChangeRequstProcessListForm.action = "<c:url value='/sym/prm/EgovProgramChangeRequstProcessListSelect.do'/>";
   	document.programChangeRequstProcessListForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramChangeRequstProcessList() {
	document.programChangeRequstProcessListForm.pageIndex.value = 1;
	document.programChangeRequstProcessListForm.action = "<c:url value='/sym/prm/EgovProgramChangeRequstProcessListSelect.do'/>";
	document.programChangeRequstProcessListForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectChangeRequstProcessListDetail(progrmFileNm, tmpNo) {
	document.programChangeRequstProcessListForm.tmpProgrmNm.value = progrmFileNm;
	document.programChangeRequstProcessListForm.tmpRqesterNo.value = tmpNo;
	document.programChangeRequstProcessListForm.action = "<c:url value='/sym/prm/EgovProgramChangRequstProcessDetailSelect.do'/>";
	document.programChangeRequstProcessListForm.submit();
}

/* ********************************************************
 * 검색조건 처리 함수
 ******************************************************** */
function fncSearchSpan(vSearch) {
	searchSpan.innerHTML = "&nbsp;&nbsp;"
	if(vSearch == "1"){
		searchSpan.innerHTML += "<spring:message code="comSymPrm.programChangeRequstProcess.searchKeyword.all"/>&nbsp; <input name='searchKeyword' type='hidden' size='10' value='%'  maxlength='20' >";
	}else if(vSearch == "2"){
		searchSpan.innerHTML += "<spring:message code="comSymPrm.programChangeRequstProcess.searchCondition.opt2"/>&nbsp; <select name='searchKeyword'> <option value=A><spring:message code="comSymPrm.programChangeRequstProcess.processSttusA"/> <//option> <option value=P><spring:message code="comSymPrm.programChangeRequstProcess.processSttusP"/> <//option> <option value=R><spring:message code="comSymPrm.programChangeRequstProcess.processSttusR"/>      <//option> <option value=C><spring:message code="comSymPrm.programChangeRequstProcess.processSttusC"/> <//option> <//select>";//처리상태/신청중/진행중/반려/처리완료
	}else if(vSearch == "3"){
        searchSpan.innerHTML += "<spring:message code="comSymPrm.programChangeRequstProcess.rqesterDe"/>&nbsp;"
            + "<input type='hidden' name='cal_url' value=\"<c:url value='/sym/cal/EgovNormalCalPopup.do'/>\" />"
            + "<input id='searchKeywordFrom' name='searchKeywordFrom' type='text' size='10' value=''  maxlength='10'>"
            + "&nbsp;&nbsp;<input id='searchKeywordTo' name='searchKeywordTo'   type='text' size='10' value=''  maxlength='10'>";
		initCalendar();
	}else if(vSearch == "4"){
        searchSpan.innerHTML += "<spring:message code="comSymPrm.programChangeRequstProcess.rqesterPersonId"/>  &nbsp;<input name='searchKeyword' type='text' size='10' value=''  maxlength='20' >";
	}
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>

</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymPrm.programChangeRequstProcess.pageTop.title"/></h1><!-- 프로그램변경요청처리 -->

	<form name="programChangeRequstProcessListForm" method="post" action="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" onchange="fncSearchSpan(this.value);" title="검색조건">
                    <option value="1"><spring:message code="comSymPrm.programChangeRequstProcess.searchCondition.opt1"/></option><!-- 전체 -->
                    <option value="2"><spring:message code="comSymPrm.programChangeRequstProcess.searchCondition.opt2"/></option><!-- 처리상태 -->
                    <option value="3"><spring:message code="comSymPrm.programChangeRequstProcess.searchCondition.opt3"/></option><!-- 요청일자 -->
                    <option value="4"><spring:message code="comSymPrm.programChangeRequstProcess.searchCondition.opt4"/></option><!-- 요청자 -->
                </select>
                <span id="searchSpan" >&nbsp;&nbsp;<spring:message code="comSymPrm.programChangeRequstProcess.searchKeyword.all"/>&nbsp;<input name='searchKeyword' type='hidden' size='10' value='%'  maxlength='20' title="<spring:message code="comSymPrm.programChangeRequstProcess.searchKeyword.value"/>"></span><!-- 전체조회 --><!-- 검색조건값 -->
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="selectProgramChangeRequstProcessList(); return false;" /><!-- 조회 -->
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
			<col style="width:80px" />
			<col style="width:200px" />
			<col style="width:80px" />
			<col style="width:100px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequstProcess.rqesterNo"/></th><!-- 요청번호 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequstProcess.progrmNm"/></th><!-- 프로그램파일명 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequstProcess.processSttus"/></th><!-- 처리상태 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequstProcess.rqesterSj"/></th><!-- 요청제목 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequstProcess.rqesterPersonId"/></th><!-- 요청자 -->
			   <th scope="col"><spring:message code="comSymPrm.programChangeRequstProcess.rqesterDe"/></th><!-- 요청일자 -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(list_changerequst) == 0}">
			<tr>
			<td colspan="6">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			 <c:forEach var="result" items="${list_changerequst}" varStatus="status">
			   <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			  <tr>
			    <td><c:out value="${result.rqesterNo}"/></td>
			    <td>
			        <form name="item" method="post" action="<c:url value='/sym/prm/EgovProgramChangRequstProcessDetailSelect.do'/>">
						<input type="hidden" name="tmpProgrmNm"  value="<c:out value="${result.progrmFileNm  }"/>"/>
						<input type="hidden" name="tmpRqesterNo" value="<c:out value="${result.rqesterNo     }"/>"/>
			            <span class="link"><input type="submit"   value="<c:out value="${result.progrmFileNm  }"/>" onclick="selectChangeRequstProcessListDetail('<c:out value="${result.progrmFileNm}"/>','<c:out value="${result.rqesterNo}"/>'); return false;"></span>
			        </form>
				</td>
			    <td>
			      <c:if test="${empty result.processSttus}">N/A</c:if>
			      <c:if test="${result.processSttus == 'A'}"><spring:message code="comSymPrm.programChangeRequstProcess.processSttusA"/></c:if><!-- 신청중 -->
			      <c:if test="${result.processSttus == 'P'}"><spring:message code="comSymPrm.programChangeRequstProcess.processSttusP"/></c:if><!-- 진행중 -->
			      <c:if test="${result.processSttus == 'R'}"><spring:message code="comSymPrm.programChangeRequstProcess.processSttusR"/></c:if><!-- 반려 -->
			      <c:if test="${result.processSttus == 'C'}"><spring:message code="comSymPrm.programChangeRequstProcess.processSttusC"/></c:if><!-- 처리완료 -->
			    </td>
			    <td><c:out value="${result.rqesterSj}"/></td>
			    <td><c:out value="${result.rqesterPersonId}"/></td>
			    <td><c:out value="${result.rqesterDe}"/></td>
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
