<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovCmmntyBaseTmplContents.jsp
  * @Description : 커뮤니티 기본 템플릿 기본 컨텐츠
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.28   이삼섭          최초 생성

  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.28
  *  @version 1.0
  *  @see
  *
  */
%>
<c:set var="pageTitle"><spring:message code="comCopCmy.communityUseMgrBaseTmpl.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/cop/cmy/community.css' />" rel="stylesheet" type="text/css">
<style type="text/css">

A.comun:link 	{	font-size:9pt;	font-family:"gulim";	color:#ffffff;	font-weight: bold;	text-decoration:none;}
A.comun:visited	{ font-size:9pt; 	font-family:"gulim";	color:#ffffff;		font-weight: bold; text-decoration:none; 	}
A.comun:active  	{ font-size:9pt; 	font-family:"gulim";	color:#ffffff;	font-weight: bold; text-decoration:none; 	}
A.comun:hover	{	font-size:9pt;	font-family:"gulim";	color:#ffcb2c;	font-weight: bold;	text-decoration:none;}

.f_step {
	font-size: 9pt;
	font-weight: bold;
	font-family: "gulim";
	color:#ffffff;
}

.board_title {
	font-size: 12pt;
	font-weight: bold;
	font-family: "gulim";
	color:#3a5870;
}

.comun_board_title {
	font-size: 12pt;
	font-weight: bold;
	font-family: "gulim";
	color:#5e5e5e;
}

.big_title {
	font-size: 11pt;
	font-weight: bold;
	font-family: "dotum";
	color:#000000;
}

.cal {
	font-size: 9pt;
	font-weight: bold;
	font-family: "dotum";
	color:#ffffff;
}

td.rightGap {background:url(<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif'/>) }
</style>
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">
<!--
	function fn_egov_loadArticleList(bbsId, bbsAttrbCode) {
	}

	function fn_egov_loadArticle(bbsId, nttId) {
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
<!--
	function fn_egov_loadArticleList(bbsId, bbsAttrbCode) {
		if (bbsId == '') {
			return;
		}
		location.href="<c:url value='/cop/bbs/selectArticleList.do' />?bbsId="+bbsId+"&bbsAttrbCode="+bbsAttrbCode;
	}

	function fn_egov_loadArticle(bbsId, nttId) {
		document.frm.nttId.value = nttId;
		document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/cop/bbs/selectArticleDetail.do' />";
		document.frm.submit();
	}
//-->
</script>
</c:otherwise>
</c:choose>
</head>
<body>

<form action="" name="frm" method="post" >
	<input type="hidden" name="bbsId" value="" />
	<input type="hidden" name="nttId" value="" />
	<input type="hidden" name="bbsAttrbCode" value="" />
</form>

<!-- contents 영역 -->
<div id="if_cmnt_content">
	<ul class="main_board">
		<li>
			<strong><c:out value="${bbsList[0].bbsNm}" />&nbsp;</strong>
			<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[0].bbsId}" />','');" class="more"><spring:message code="comCopCmy.communityUseMgrBaseTmpl.more"/></a> <!-- 더보기 -->
			<c:if test="${fn:length(articleList[0]) == 0}">
			<ul class="list null">
				<li><spring:message code="comCopCmy.communityUseMgrBaseTmpl.noList"/></li> <!-- 등록 된 내용이 없습니다. -->
			</ul>
			</c:if>
			<c:if test="${fn:length(articleList[0]) > 0}">
			<ul class="list">
			<c:forEach var="article" items="${articleList[0]}" varStatus="status">
			<li>
				<a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');">
				<c:if test="${fn:length(article.nttSj) <= 20}">
				<c:out value="${article.nttSj}" />
				</c:if>
				<c:if test="${fn:length(article.nttSj) > 20}">
				<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
				</c:if>
				</a>
				<span class="date"><c:out value="${article.frstRegisterPnttm}" /></span>
			</li>							
			</c:forEach>	
			</ul>
			</c:if>
			
		</li>
		<li>
			<strong><c:out value="${bbsList[1].bbsNm}" />&nbsp;</strong>
			<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[1].bbsId}" />','');" class="more"><spring:message code="comCopCmy.communityUseMgrBaseTmpl.more"/></a>
			<c:if test="${fn:length(articleList[1]) == 0}">
			<ul class="list null">
				<li><spring:message code="comCopCmy.communityUseMgrBaseTmpl.noList"/></li> <!-- 등록 된 내용이 없습니다. -->
			</ul>
			</c:if>
			<c:if test="${fn:length(articleList[1]) > 0}">
			<ul class="list">
			<c:forEach var="article" items="${articleList[1]}" varStatus="status">
			<li>
				<a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');">
				<c:if test="${fn:length(article.nttSj) <= 20}">
				<c:out value="${article.nttSj}" />
				</c:if>
				<c:if test="${fn:length(article.nttSj) > 20}">
				<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
				</c:if>
				</a>
				<span class="date"><c:out value="${article.frstRegisterPnttm}" /></span>
			</li>							
			</c:forEach>
			</ul>
			</c:if>
			
		</li>
		<li>
			<strong><c:out value="${bbsList[2].bbsNm}" />&nbsp;</strong>
			<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[2].bbsId}" />','');" class="more"><spring:message code="comCopCmy.communityUseMgrBaseTmpl.more"/></a>
			<c:if test="${fn:length(articleList[2]) == 0}">
			<ul class="list null">
				<li><spring:message code="comCopCmy.communityUseMgrBaseTmpl.noList"/></li> <!-- 등록 된 내용이 없습니다. -->
			</ul>
			</c:if>
			<c:if test="${fn:length(articleList[2]) > 0}">
			<ul class="list">
			<c:forEach var="article" items="${articleList[2]}" varStatus="status">
			<li>
				<a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');">
				<c:if test="${fn:length(article.nttSj) <= 20}">
				<c:out value="${article.nttSj}" />
				</c:if>
				<c:if test="${fn:length(article.nttSj) > 20}">
				<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
				</c:if>
				</a>
				<span class="date"><c:out value="${article.frstRegisterPnttm}" /></span>
			</li>
			</c:forEach>
			</ul>
			</c:if>
		</li>
		<li>
			<strong><c:out value="${bbsList[3].bbsNm}" />&nbsp;</strong>
			<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[3].bbsId}" />','');" class="more"><spring:message code="comCopCmy.communityUseMgrBaseTmpl.more"/></a>
			<c:if test="${fn:length(articleList[3]) == 0}">
			<ul class="list null">
				<li><spring:message code="comCopCmy.communityUseMgrBaseTmpl.noList"/></li> <!-- 등록 된 내용이 없습니다. -->
			</ul>
			</c:if>
			<c:if test="${fn:length(articleList[3]) > 0}">
			<ul class="list">
			<c:forEach var="article" items="${articleList[3]}" varStatus="status">
			<li>
				<a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');">
				<c:if test="${fn:length(article.nttSj) <= 20}">
				<c:out value="${article.nttSj}" />
				</c:if>
				<c:if test="${fn:length(article.nttSj) > 20}">
				<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
				</c:if>
				</a>
				<span class="date"><c:out value="${article.frstRegisterPnttm}" /></span>
			</li>									
			</c:forEach>
			</ul>
			</c:if>
			
		</li>
	</ul>
</div>
<!-- contents 영역 //-->


</body>
</html>
