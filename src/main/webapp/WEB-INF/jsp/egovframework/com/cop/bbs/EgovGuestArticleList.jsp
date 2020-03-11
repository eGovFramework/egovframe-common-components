<%
 /**
  * @Class Name : EgovGuestArticleList.jsp
  * @Description : EgovGuestArticleList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comCopBbs.articleVO.guest.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_guest(nttId){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.articleForm.nttId.value = nttId;	
		document.articleForm.action = "<c:url value='/cop/bbs/deleteGuestArticle.do'/>";
		document.articleForm.submit();	
	}	
}	
 /* ********************************************************
  * 등록처리
  ******************************************************** */
 function fn_egov_insert_guest(form) {
		if (!validateArticleVO(form)){
			return;
		}
		if (confirm('<spring:message code="common.regist.msg" />')) {
			form.submit();
		}
	}
 /* ********************************************************
  * 수정처리
  ******************************************************** */
	function fn_egov_updt_guest(form) {
		if (!validateArticleVO(form)){
			return;
		}

		if (confirm('<spring:message code="common.update.msg" />')) {

			form.action = "<c:url value='/cop/bbs/updateGuestArticle.do'/>";
			form.submit();
		}
	}
/* ********************************************************
 * 수정전 처리
 ******************************************************** */
	function fn_egov_selectGuestForupdt(nttId) {
		document.articleForm.nttId.value = nttId;	
		document.articleForm.action = "<c:url value='/cop/bbs/updateGuestArticleView.do'/>";
		document.articleForm.submit();
	}
	

/* ********************************************************
 * 페이징 처리
 ******************************************************** */
function fn_egov_select_guestList(pageNo) {
	document.articleForm.pageIndex.value = pageNo;
	document.articleForm.nttId.value = 0;
	document.articleForm.action = "<c:url value='/cop/bbs/selectGuestArticleList.do'/>";
	document.articleForm.submit();
}
</script>
<!-- 댓글 작성 스크립트  -->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="articleVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 방명록 리스트 출력 -->
<form name="articleForm" action="<c:url value='/cop/bbs/updateGuestArticleView.do'/>" method="post">
<div class="board">
<h2>${pageTitle}</h2>
<div class="reply">
	<ul>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<li>
			<div class="top">
				<strong><c:out value="${result.frstRegisterNm}" /></strong>
				<span class="bar">|</span>
				<span class="date"><c:out value="${result.frstRegisterPnttm}" /></span>
			</div>
			<p class="txt">
				<c:out value="${fn:replace(result.nttCn , crlf , '<br/>')}" escapeXml="false" />
			</p>
			<div class="bottom">
				<c:if test="${result.frstRegisterId == sessionUniqId}">
				<span class="btn_s"><a href="javascript:fn_egov_selectGuestForupdt(<c:out value="${result.nttId}"/>)"  title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /> </a></span>&nbsp;
				<span class="btn_s"><a href="javascript:fn_egov_delete_guest(<c:out value="${result.nttId}"/>)"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
				</c:if>
			</div>
		</li>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<li>
	  		<p class="txt"><spring:message code="common.noguest.msg" /></p>
  		</li>
		 </c:if>
	</ul>
</div>

<!-- 방명록 페이징 -->
<div class="paging">
		<ul>
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_guestList"/>
		</ul>
</div>

</div>

<input name="pageIndex" type="hidden" value="<c:out value='${articleVO.pageIndex}'/>">
<input name="nttId" type="hidden" value="">
<input name="bbsId" type="hidden" value="<c:out value='${boardMasterVO.bbsId}'/>">
</form>

<!-- 방명록 입력폼 -->
<form:form commandName="articleVO" action="${pageContext.request.contextPath}/cop/bbs/insertGuestArticle.do" method="post" onSubmit="fn_egov_insert_guest(document.forms[1]); return false; " style="float:left; clear:both;">
	<div class="wTableFrm" >
	<table class="board_list top_line">
		<caption>${pageTitle } <spring:message code="title.create" /></caption>
		<colgroup>
			<col style="width: 16%;"><col style="width: ;">
		</colgroup>
		<tbody>
			<!-- 방명록 내용  -->
			<c:set var="title"><spring:message code="comCopBbs.articleVO.regist.nttCn"/> </c:set>
			<tr>
				<th><label for="nttCn">${title } <span class="pilsu">*</span></label></th>
				<td class="nopd">
					<form:textarea path="nttCn" title="${title} ${inputTxt}" cols="300" rows="20" cssClass="re_txt"/>   
					<div><form:errors path="nttCn" cssClass="error" /></div>
					<c:choose>
						<c:when test="${articleVO.nttId == '0'}">
							<span style="float:left;"><a href="javascript:fn_egov_insert_guest(document.forms[1]); " class="btn_s re_btn" title="<spring:message code="button.comment" /> <spring:message code="input.button" />"><spring:message code="button.comment" /><spring:message code="button.create" /></a></span>
						</c:when>
						<c:otherwise>
							<span style="float:left;"><a href="javascript:fn_egov_updt_guest(document.forms[1]); " class="btn_s re_btn"title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.comment" /><spring:message code="button.update" /></a></span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	</div>

<input name="nttId" type="hidden" value="<c:out value='${articleVO.nttId}'/>">
<input name="bbsId" type="hidden" value="<c:out value='${boardMasterVO.bbsId}'/>">
<!-- validator 검증용 -->
<input name="nttSj" type="hidden" value="guestbook"/>
</form:form>


</body>
</html>
