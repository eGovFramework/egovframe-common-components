<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovCommentList.jsp
  * @Description : 댓글
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.06.29   한성곤          최초 생성
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.29
  *  @version 1.0
  *  @see
  *
  */
%>


<c:if test="${type == 'body'}">
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!-- 댓글 내용  -->
	<c:set var="replyTitle"><spring:message code="comCopCmt.articleCommentVO.title"/></c:set>
	<h3>${replyTitle} <c:out value="${resultCnt}"/></h3>
	<div class="reply">
		<ul>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<li>
				<div class="top">
					<strong><c:out value="${result.wrterNm}" /></strong>
					<span class="bar">|</span>
					<span class="date"><c:out value="${result.frstRegisterPnttm}" /></span>
				</div>
				<p class="txt">
					<c:out value="${fn:replace(result.commentCn , crlf , '<br/>')}" escapeXml="false" />
				</p>
				<div class="bottom">
					<c:if test="${result.wrterId == sessionUniqId}">
					<span class="btn_s"><a href="javascript:fn_egov_selectCommentForupdt(${result.commentNo})"  title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /> </a></span>&nbsp;
					<span class="btn_s"><a href="javascript:fn_egov_deleteCommentList(${result.commentNo})"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
					</c:if>
				</div>
			</li>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<li>
		  		<p class="txt"><spring:message code="common.nocomment.msg" /></p>
	  		</li>
			 </c:if>
		</ul>
	</div>
	
	<!-- paging navigation -->
		<div class="paging">
			<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_commentList"/>
			</ul>
		</div>
		
	<form:form commandName="articleCommentVO" action="${pageContext.request.contextPath}/cop/cmt/insertArticleComment.do" method="post" onSubmit="fn_egov_insert_commentList(); return false;" style="float:left; clear:both;">
	<div class="wTableFrm" >
	<table class="board_list top_line">
		<caption>${replyTitle } <spring:message code="title.create" /></caption>
		<colgroup>
			<col style="width: 16%;"><col style="width: ;">
		</colgroup>
		<tbody>
			<!-- 댓글 내용  -->
			<c:set var="title"><spring:message code="comCopCmt.articleCommentVO.commentCn"/> </c:set>
			<tr>
				<th><label for="commentCn">${title } <span class="pilsu">*</span></label></th>
				<td class="nopd">
					<form:textarea path="commentCn" title="${title} ${inputTxt}" cols="300" rows="20" cssClass="re_txt"/>   
					<div><form:errors path="commentCn" cssClass="error" /></div>
					<c:choose>
						<c:when test="${searchVO.commentNo == '' }">
							<span style="float:left;"><a href="javascript:fn_egov_insert_commentList(); " class="btn_s re_btn" title="<spring:message code="button.comment" /> <spring:message code="input.button" />"><spring:message code="button.comment" /><spring:message code="button.create" /></a></span>
						</c:when>
						<c:otherwise>
							<span style="float:left;"><a href="javascript:fn_egov_updt_commentList(); " class="btn_s re_btn"title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.comment" /><spring:message code="button.update" /></a></span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
<input name="subPageIndex" type="hidden" value="<c:out value='${searchVO.subPageIndex}'/>">
<input name="commentNo" type="hidden" value="<c:out value='${searchVO.commentNo}'/>">
<input name="modified" type="hidden" value="false">
<input name="nttId" type="hidden" value="<c:out value="${result.nttId}" />">
<input name="bbsId" type="hidden" value="<c:out value="${boardMasterVO.bbsId}" />">
</form:form>

</c:if>
