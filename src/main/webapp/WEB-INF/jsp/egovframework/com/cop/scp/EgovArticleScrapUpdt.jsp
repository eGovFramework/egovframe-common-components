
<%
	/**
	 * @Class Name : EgovArticleScrapUpdt.jsp
	 * @Description : EgovArticleScrapUpdt 화면
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
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<c:set var="pageTitle"><spring:message code="comCopScp.articleScrapVO.title" /></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle }<spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="articleScrapVO" staticJavascript="false"	xhtml="true" cdata="false" />
<script type="text/javascript">
	/* ********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init() {
		// 첫 입력란에 포커스..
		document.getElementById("articleScrapVO").scrapNm.focus();
	}
	/* ********************************************************
	 * 저장처리화면
	 ******************************************************** */
	function fn_egov_updt_articleScrap(form) {
		if (!validateArticleScrapVO(form)) {
			return false;
		} else {
			
			if (confirm("<spring:message code="common.update.msg" />")) {
				form.submit();
			}
		}
	}
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_inqire_articleScrapList() {
		articleScrapVO.action = "<c:url value='/cop/scp/selectArticleScrapList.do'/>";
		articleScrapVO.submit();
	}

</script>
</head>
<body onLoad="fn_egov_init();">

	<!-- javascript warning tag  -->
	<noscript class="noScriptTitle">
		<spring:message code="common.noScriptTitle.msg" />
	</noscript>

	<!-- 상단타이틀 -->
	<form:form commandName="articleScrapVO" action="${pageContext.request.contextPath}/cop/scp/updateArticleScrap.do" method="post"	onSubmit="fn_egov_updt_articleScrap(document.forms[0]); return false;">
		<div class="wTableFrm">
			<h2>${pageTitle}
				<spring:message code="title.update" />
			</h2>

			<!-- 수정폼 -->
			<table class="wTable"
				summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
				<caption>${pageTitle} <spring:message code="title.update" /></caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width:;">
					<col style="width:;">
					<col style="width:;">
				</colgroup>
				<tbody>
					<!-- 입력 -->
					<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
					<!-- 스크랩명  -->
					<c:set var="title"><spring:message code="comCopScp.articleScrapVO.updt.scrapNm" /></c:set>
					<tr>
						<th>${title}<span class="pilsu">*</span></th>
						<td class="left" colspan="5"><form:input path="scrapNm" title="${title} ${inputTxt }" size="70" maxlength="70" />
							<div>	<form:errors path="scrapNm" cssClass="error" /></div>
						</td>
					</tr>

					<!-- 글 제목  -->
					<c:set var="title"><spring:message code="comCopScp.articleScrapVO.updt.nttSj"/> </c:set>
					<tr>
						<th>${title } </th>
						<td class="left" colspan="5">
						<c:out value="${articleVO.nttSj}" escapeXml="false" />
						</td>
					</tr>
					
					<!-- 작성자, 작성시간, 조회수  -->
					<c:set var="title"><spring:message code="table.reger"/> </c:set>
					<tr>
						<th>${title}</th>
						<td>
						<c:out value="${articleVO.frstRegisterNm}" escapeXml="false" />
						</td>
						<c:set var="title"><spring:message code="table.regdate"/> </c:set>
						<th>${title}</th>
						<td>
						<c:out value="${articleVO.frstRegisterPnttm}" escapeXml="false" />
						</td>
						<c:set var="title"><spring:message code="comCopScp.articleScrapVO.updt.inqireCo"/> </c:set>
						<th>${title}</th>
						<td>
						<c:out value="${articleVO.inqireCo}" escapeXml="false" />
						</td>
					</tr>
					
					<!-- 글 내용  -->
					<c:set var="title"><spring:message code="comCopScp.articleScrapVO.updt.nttCn"/> </c:set>
					<tr>
						<th>${title } </th>
						<td class="nopd" colspan="5">
						<c:out value="${fn:replace(articleVO.nttCn , crlf , '<br/>')}" escapeXml="false" />
						</td>
					</tr>

				</tbody>
			</table>

			<!-- 하단 버튼 -->
			<div class="btn">
				<input type="submit" class="s_submit" value="<spring:message code="button.update" />"	title="<spring:message code="button.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
				<span class="btn_s"><a href="<c:url value='/cop/scp/selectArticleScrapList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span></div><!-- 목록 -->
			<div style="clear: both;"></div>

		</div>

		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>" />
		<input name="nttId" type="hidden" value="<c:out value='${articleVO.nttId}'/>"/>
		<input name="bbsId" type="hidden" value="<c:out value='${articleVO.bbsId}'/>"/>
		<input name="scrapId" type="hidden" value="<c:out value='${articleScrapVO.scrapId}'/>"/>
	</form:form>

</body>
</html>
