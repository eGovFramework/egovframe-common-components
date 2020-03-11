<%
 /**
  * @Class Name : EgovArticleDetail.jsp
  * @Description : EgovArticleDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
       2017.09.06   양희훈              표준프레임워크 v3.7 개선
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
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comCopBbs.articleVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title><!-- 블로그 메인 내용상세 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_article(form){
	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		form.submit();	
	}	
}	

/* ********************************************************
 * 답글작성
 ******************************************************** */
 function fn_egov_reply_article() {
		document.articleForm.action = "<c:url value='/cop/bbs/replyArticleView.do'/>";
		document.articleForm.submit();
	}
	
</script>
<!-- 댓글 작성 스크립트  -->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="articleCommentVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
function fn_egov_insert_commentList() {
	
	var form = document.getElementById("articleCommentVO");

	if (!validateArticleCommentVO(form)){
		return;
	}
	if (confirm('<spring:message code="common.regist.msg" />')) {
		form.submit();
	}
}

function fn_egov_updt_commentList() {
	
	var form = document.getElementById("articleCommentVO");
	
	if (!validateArticleCommentVO(form)){
		return;
	}

	if (confirm('<spring:message code="common.update.msg" />')) {
		form.modified.value = "true";
		form.action = "<c:url value='/cop/cmt/updateArticleComment.do'/>";
		form.submit();
	}
}

function fn_egov_selectCommentForupdt(commentNo) {
	
	var form = document.getElementById("articleCommentVO");
	
	form.commentNo.value = commentNo;
	form.action = "<c:url value='/cop/bbs/selectArticleDetail.do'/>";
	form.submit();
}

function fn_egov_deleteCommentList(commentNo) {

	var form = document.getElementById("articleCommentVO");
	
	if (confirm('<spring:message code="common.delete.msg" />')) {
		form.modified.value = "true";
		form.commentNo.value = commentNo;
		form.action = "<c:url value='/cop/cmt/deleteArticleComment.do'/>";
		form.submit();
	}
}

/* 댓글페이징 */
function fn_egov_select_commentList(pageNo) {
	
	var form = document.getElementById("articleCommentVO");
	
	form.subPageIndex.value = pageNo;
	form.commentNo.value = '';
	form.action = "<c:url value='/cop/bbs/selectArticleDetail.do'/>";
	form.submit();
}
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<%-- <h2>${pageTitle} <spring:message code="title.detail" /></h2> --%>

	<!-- 상세조회 -->
	<table class="" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
		<col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 글 제목 -->
		<div class="blog_title">
			<h5><c:out value="${result.nttSj}"/></h5>
			<span><a href="">맛스타그램</a></span>
			<p>2017.08.22 10:23</p>
		</div>
		<!-- 글 내용 -->
		<c:if test="${result.nttSj == null}">
			<tr>
				<td colspan="5"><spring:message code="common.nodata.msg" /></td>
			</tr>
		</c:if>
		<tr>
			<%-- <th class="vtop"><spring:message code="comCopBbs.articleVO.detail.nttCn" /></th> --%>
			<td colspan="5" class="cnt">
				<c:out value="${fn:replace(result.nttCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 첨부파일  -->
		<c:if test="${not empty result.atchFileId}">
		<tr>
			<th><spring:message code="comCopBbs.articleVO.detail.atchFile" /></th>
			<td colspan="5">
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				<c:param name="param_atchFileId" value="${result.atchFileId}" />
			</c:import>
			</td>
		</tr>
	  	</c:if>
		
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${result.ntcrId != 'anonymous'}">
		<!-- 익명글 수정/삭제 불가  -->
		<form name="articleForm" action="<c:url value='/cop/bbs/updateArticleView.do'/>" method="post" style="float:left;">
			<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
			<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
			<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
			<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
			<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >
			<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
			<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
			<input name="blogAt" type="hidden" value="chkBlog">
		</form>
		<form name="formDelete" action="<c:url value='/cop/bbs/deleteArticle.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" title="<spring:message code="button.delete" /> <spring:message code="input.button" />" onclick="fn_egov_delete_article(this.form); return false;"><!-- 삭제 -->
			<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
			<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
			<input name="blogAt" type="hidden" value="chkBlog">
		</form>
		</c:if>
		<c:if test="${boardMasterVO.replyPosblAt == 'Y' }">
		<form name="formReply" action="<c:url value='/cop/bbs/replyArticleView.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.reply" />"><!-- 답글 -->
			<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
			<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
		</form>
		</c:if>
		<form name="formList" action="<c:url value='/cop/bbs/selectArticleBlogList.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.list" />"><!-- 목록 -->
			<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
			<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
		</form>
		<form name="formScrap" action="<c:url value='/cop/scp/insertArticleScrapView.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
			<input type="submit" class="s_submit" value="<spring:message code="button.scrap" />"><!-- 스크랩 -->
			<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
			<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
		</form>
		
	</div><div style="clear:both;"></div>
	
</div>

<!-- 댓글 -->
<c:import url="/cop/cmt/selectArticleCommentList.do" charEncoding="utf-8">
</c:import>

</body>
</html>
