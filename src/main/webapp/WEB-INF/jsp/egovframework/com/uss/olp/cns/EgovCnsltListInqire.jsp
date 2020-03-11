<%
 /**
  * @Class Name : EgovCnsltListInqire.jsp
  * @Description : 상담 목록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.08.18   장동한              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpCns.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_cnsltlist(){

	document.CnsltListForm.searchKeyword.focus();

}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){

	document.CnsltListForm.pageIndex.value = pageNo;
	document.CnsltListForm.action = "<c:url value='/uss/olp/cns/CnsltListInqire.do'/>";
   	document.CnsltListForm.submit();

}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_cnsltdtls(){

	document.CnsltListForm.pageIndex.value = 1;
	document.CnsltListForm.submit();

}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_cnsltdtls(){

	// 로그인을 할 것인자? 실명확인을 할것인지? 판단 화면
	var loginRealnm_url 	= "<c:url value='/uss/olp/cns/LoginRealnmChoice.do'/>";
	var	loginRealnm_status 	= "dialogWidth=350px;dialogHeight=150px;resizable=no;center=yes";

	// 로그인 화면
	var	login_url 		= "<c:url value='/uat/uia/egovLoginUsr.do'/>";
	var login_status 	= "dialogWidth=700px;dialogHeight=420px;resizable=no;center=yes";


	// 실명확인 화면
	var	realnm_url 	= "<c:url value='/sec/rnc/EgovRlnmCnfirm.do?nextUrlName=button.cnsltregist&nextUrl=C'/>";
	var realnm_status 	= "dialogWidth=740px;dialogHeight=180px;resizable=no;center=yes";

	var	returnValue = false;

	var certificationAt = document.CnsltListForm.certificationAt.value;


	// 인증여부 확인
	if (certificationAt == "N") {

		// 로그인? 실명확인 여부 화면 호출
		returnValue = window.showModalDialog(loginRealnm_url, self, loginRealnm_status);

		// 결과값을 받아. 결과를 Submit한다.
	 	if	(returnValue)	{

	 		ls_loginRealnmAt = document.CnsltListForm.loginRealnmAt.value;

	 		// 로그인처리
	 		if (ls_loginRealnmAt == "L")		{

				// 로그인 화면 호출
			    /* 추후 진행 예정..
	 			returnValue = window.showModalDialog(login_url, self, login_status);

	 			returnValue = true;
				*/

				// 팝업이 아닌 메인 화면으로 처리.
	 			document.CnsltListForm.action = "<c:url value='/uat/uia/egovLoginUsr.do'/>";
	 			document.CnsltListForm.submit();


	 			returnValue = false;

	 		// 실명확인처리
	 		} else if (ls_loginRealnmAt == "R")	{

				// 실명확인 화면 호출
	 			returnValue = window.showModalDialog(realnm_url, self, realnm_status);

	 			ls_wrterNm = document.CnsltListForm.realname.value;

	 			document.CnsltListForm.wrterNm.value = ls_wrterNm;



	 		}  // 로그인처리 혹은 실명확인 경우 end...


 			if	(returnValue)	{

 				// 상담등록화면 호출..
 				fn_egov_regist_cnsltcn();

 			}


	 	}	// 결과값을 받아. 결과를 Submit한다. end..

	} else	{

		// 상담등록화면 호출..
		fn_egov_regist_cnsltcn();

	}

}

function showModalDialogCallback(returnValue) {
	// 결과값을 받아. 결과를 Submit한다.
 	if	(returnValue)	{

 		ls_loginRealnmAt = document.CnsltListForm.loginRealnmAt.value;

 		// 로그인처리
 		if (ls_loginRealnmAt == "L")		{

			// 로그인 화면 호출
		    /* 추후 진행 예정..
 			returnValue = window.showModalDialog(login_url, self, login_status);

 			returnValue = true;
			*/

			// 팝업이 아닌 메인 화면으로 처리.
 			document.CnsltListForm.action = "<c:url value='/uat/uia/egovLoginUsr.do'/>";
 			document.CnsltListForm.submit();


 			returnValue = false;

 		// 실명확인처리
 		} else if (ls_loginRealnmAt == "R")	{

			// 실명확인 화면 호출
 			returnValue = window.showModalDialog(realnm_url, self, realnm_status);

 			ls_wrterNm = document.CnsltListForm.realname.value;

 			document.CnsltListForm.wrterNm.value = ls_wrterNm;



 		}  // 로그인처리 혹은 실명확인 경우 end...


			if	(returnValue)	{

				// 상담등록화면 호출..
				fn_egov_regist_cnsltcn();

			}


 	}	// 결과값을 받아. 결과를 Submit한다. end..
}

/*********************************************************
 * 상담등록화면 호출
 ******************************************************** */
function fn_egov_regist_cnsltcn(){

	document.CnsltListForm.action = "<c:url value='/uss/olp/cns/CnsltDtlsRegistView.do'/>";
	document.CnsltListForm.submit();

}


/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_cnsltdetail(cnsltId) {

	// 사이트 키값(siteId) 셋팅.
	document.CnsltListForm.cnsltId.value = cnsltId;
//	document.CnsltListForm.action = "<c:url value='/uss/olp/cns/CnsltDetailInqire.do'/>";
	document.CnsltListForm.action = "<c:url value='/uss/olp/cns/CnsltInqireCoUpdt.do'/>";
  	document.CnsltListForm.submit();

}

</script>
</head>
<body onLoad="fn_egov_initl_cnsltlist();">

<div class="board">
<form name="CnsltListForm" action="<c:url value='/uss/olp/cns/CnsltListInqire.do'/>" method="post" onSubmit="fn_egov_search_cnsltdtls(); return false;">

	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option selected value=''>--<spring:message code="input.select" />--</option>
					<option value="wrterNm"  <c:if test="${searchVO.searchCondition == 'wrterNm'}">selected="selected"</c:if> ><spring:message code="comUssOlpCns.searchCondition.wrterNm" /></option><!-- 작성자명 -->
					<option value="cnsltSj"  <c:if test="${searchVO.searchCondition == 'cnsltSj'}">selected="selected"</c:if> ><spring:message code="comUssOlpCns.searchCondition.cnsltSj" /></option><!-- 상담제목 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="<c:url value='/uss/olp/cns/CnsltDtlsRegistView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>


<!-- 실명확인을 위한  설정   Start...-->
<input type="hidden" name="ihidnum" value="">
<input type="hidden" name="realname" value="">
<input type="hidden" name ="nextUrlName" value="Q&amp;A 작성">
<input type="hidden" name ="nextUrl" value="<c:url value='/uss/olh/qna/QnaCnRegistView.do' />">
<input type="hidden" name="certificationAt" value="${certificationAt}">
<input type="hidden" name="loginRealnmAt" value="">
<input type="hidden" name="wrterNm" value="">
<!-- 실명확인을 위한  설정 End...... -->

<input name="cnsltId" type="hidden" value="">
<input name="passwordConfirmAt" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: ;">
		
		<col style="width: 10%;">
		<col style="width: 13%;">
		
		<col style="width: 15%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		
		<th class="board_th_link"><spring:message code="comUssOlpCns.list.cnsltSj" /></th><!-- 상담제목 -->

		<th><spring:message code="table.reger" /></th><!-- 작성자 -->
		<th><spring:message code="table.regdate" /></th><!-- 작성일자 -->
		<th><spring:message code="comUssOlpCns.list.qnaProcessSttusCodeNm" /></th><!-- 진행상태 -->
		<th><spring:message code="comUssOlpCns.list.hit" /></th><!-- 조회수 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left">
		<form name="linkForm" method="post" action="<c:url value='/uss/olp/cns/CnsltInqireCoUpdt.do'/>">
	    	<input name="cnsltId" type="hidden" value="${resultInfo.cnsltId}">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			<input name="passwordConfirmAt" type="hidden" value="">
	    	<span class="link"><input type="submit" value="<c:out value="${resultInfo.cnsltSj}"/>"></span>
	    </form>
		<%-- <a href="<c:url value='/uss/olp/cns/CnsltInqireCoUpdt.do'/>?pageIndex=${searchVO.pageIndex}&cnsltId=${resultInfo.cnsltId}&passwordConfirmAt="><c:out value="${resultInfo.cnsltSj}"/></a> --%>
		</td>
		<td><c:out value="${resultInfo.wrterNm}"/></td>
		<td><c:out value="${fn:substring(resultInfo.writngDe, 0, 10)}"/></td>
		<td><c:out value="${resultInfo.qnaProcessSttusCodeNm}"/></td>
		<td><c:out value="${resultInfo.inqireCo}"/></td>
	  </tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/></ul>
	</div>
	
	
</div><!-- end div board -->



</body>
</html>
