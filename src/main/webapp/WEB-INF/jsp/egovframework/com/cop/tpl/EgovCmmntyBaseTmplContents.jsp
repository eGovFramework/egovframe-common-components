<!DOCTYPE html>
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

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title>커뮤니티기본템플릿</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
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
	function fn_egov_loadArticleList(bbsId, bbsTyCode) {
	}

	function fn_egov_loadArticle(bbsId, nttId) {
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
<!--
	function fn_egov_loadArticleList(bbsId, bbsTyCode) {
		if (bbsId == '') {
			return;
		}
		location.href="<c:url value='/cop/bbs/selectBoardList.do' />?bbsId="+bbsId+"&bbsTyCode="+bbsTyCode;
	}

	function fn_egov_loadArticle(bbsId, nttId) {
		document.frm.nttId.value = nttId;
		document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/cop/bbs/selectBoardArticle.do' />";
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
	<input type="hidden" name="bbsTyCode" value="" />
</form>

<table width="708" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="top" width="330"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="28" align="left"><span class="comun_board_title"><c:out value="${bbsList[0].bbsNm}" /></span></td>
								<td height="22" align="right">
									<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[0].bbsId}" />','<c:out value="${bbsList[0].bbsTyCode}" />');"><img src="<c:url value='/images/egovframework/com/cop/tpl/more_08.gif' />" width="36" height="12" alt="더보기이미지아이콘" /></a>
								</td>
							</tr>
						</table></td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지"/></td>
								<td width="100%" height="9"><table width="100%" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
										<c:if test="${fn:length(articleList[0]) == 0}">
											<tr>
												<td height="22" align="left">등록된 내용이 없습니다.</td>
											</tr>
										</c:if>
										<c:forEach var="article" items="${articleList[0]}" varStatus="status">
											<tr>
												<td height="22" align="left"><span class="right_board_date"><img src="<c:url value='/images/egovframework/com/cop/tpl/dot.gif' />" width="12" height="4" alt="도트이미지"/>
												[<c:out value="${article.frstRegisterPnttm}" />]</span> <a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');" class="right_list">
													<c:if test="${fn:length(article.nttSj) <= 20}">
														<c:out value="${article.nttSj}" />
													</c:if>
													<c:if test="${fn:length(article.nttSj) > 20}">
														<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
													</c:if>
												</a></td>
											</tr>
										</c:forEach>
										</table></td>
									</tr>
								</table></td>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지"/></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
						</table></td>
					</tr>
				</table></td>
				<td align="center"><img src="<c:url value='/images/egovframework/com/cop/tpl/gray_white.gif' />" width="20" height="23" alt="여백이미지" /></td>
				<td align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="28" align="left"><span class="comun_board_title"><c:out value="${bbsList[1].bbsNm}" /></span></td>
								<td height="22" align="right">
									<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[1].bbsId}" />','<c:out value="${bbsList[1].bbsTyCode}" />');"><img src="<c:url value='/images/egovframework/com/cop/tpl/more_08.gif' />" width="36" height="12" alt="더보기이미지아이콘" /></a>
								</td>
							</tr>
						</table></td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" height="9"><table width="100%" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
										<c:if test="${fn:length(articleList[1]) == 0}">
											<tr>
												<td height="22" align="left">등록된 내용이 없습니다.</td>
											</tr>
										</c:if>
										<c:forEach var="article" items="${articleList[1]}" varStatus="status">
											<tr>
												<td height="22" align="left"><span class="right_board_date"><img src="<c:url value='/images/egovframework/com/cop/tpl/dot.gif' />" width="12" height="4" alt="도트이미지" />
												[<c:out value="${article.frstRegisterPnttm}" />]</span> <a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');" class="right_list">
													<c:if test="${fn:length(article.nttSj) <= 20}">
														<c:out value="${article.nttSj}" />
													</c:if>
													<c:if test="${fn:length(article.nttSj) > 20}">
														<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
													</c:if>
												</a></td>
											</tr>
										</c:forEach>
										</table></td>
									</tr>
								</table></td>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
						</table></td>
					</tr>
				</table></td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="top" width="330"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="28" align="left"><span class="comun_board_title"><c:out value="${bbsList[2].bbsNm}" /></span></td>
								<td height="22" align="right">
									<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[2].bbsId}" />','<c:out value="${bbsList[2].bbsTyCode}" />');"><img src="<c:url value='/images/egovframework/com/cop/tpl/more_08.gif' />" width="36" height="12" alt="더보기이미지아이콘" /></a>
								</td>
							</tr>
						</table></td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" height="9"><table width="100%" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
										<c:if test="${fn:length(articleList[2]) == 0}">
											<tr>
												<td height="22" align="left">등록된 내용이 없습니다.</td>
											</tr>
										</c:if>
										<c:forEach var="article" items="${articleList[2]}" varStatus="status">
											<tr>
												<td height="22" align="left"><span class="right_board_date"><img src="<c:url value='/images/egovframework/com/cop/tpl/dot.gif' />" width="12" height="4" alt="도트이미지" />
												[<c:out value="${article.frstRegisterPnttm}" />]</span> <a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');" class="right_list">
													<c:if test="${fn:length(article.nttSj) <= 20}">
														<c:out value="${article.nttSj}" />
													</c:if>
													<c:if test="${fn:length(article.nttSj) > 20}">
														<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
													</c:if>
												</a></td>
											</tr>
										</c:forEach>
										</table></td>
									</tr>
								</table></td>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
						</table></td>
					</tr>
				</table></td>
				<td align="center"><img src="<c:url value='/images/egovframework/com/cop/tpl/gray_white.gif' />" width="20" height="23" alt="spacer" /></td>
				<td align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="28" align="left"><span class="comun_board_title"><c:out value="${bbsList[3].bbsNm}" /></span></td>
								<td height="22" align="right">
									<a href="javascript:fn_egov_loadArticleList('<c:out value="${bbsList[3].bbsId}" />','<c:out value="${bbsList[3].bbsTyCode}" />');"><img src="<c:url value='/images/egovframework/com/cop/tpl/more_08.gif' />" width="36" height="12" alt="더보기이미지아이콘" /></a>
								</td>
							</tr>
						</table></td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" height="9"><table width="100%" border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
										<c:if test="${fn:length(articleList[3]) == 0}">
											<tr>
												<td height="22" align="left">등록된 내용이 없습니다.</td>
											</tr>
										</c:if>
										<c:forEach var="article" items="${articleList[3]}" varStatus="status">
											<tr>
												<td height="22" align="left"><span class="right_board_date"><img src="<c:url value='/images/egovframework/com/cop/tpl/dot.gif' />" width="12" height="4" alt="도트이미지" />
												[<c:out value="${article.frstRegisterPnttm}" />]</span> <a href="javascript:fn_egov_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />');" class="right_list">
													<c:if test="${fn:length(article.nttSj) <= 20}">
														<c:out value="${article.nttSj}" />
													</c:if>
													<c:if test="${fn:length(article.nttSj) > 20}">
														<c:out value="${fn:substring(article.nttSj, 0, 20)}" />..
													</c:if>
												</a></td>
											</tr>
										</c:forEach>
										</table></td>
									</tr>
								</table></td>
								<td height="9" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
							<tr>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td width="100%" class="rightGap"><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
								<td><img src="<c:url value='/images/egovframework/com/cop/tpl/right_gap.gif' />" width="9" height="9" alt="여백이미지" /></td>
							</tr>
						</table></td>
					</tr>
				</table></td>
			</tr>
		</table></td>
	</tr>
</table>
</body>
</html>
