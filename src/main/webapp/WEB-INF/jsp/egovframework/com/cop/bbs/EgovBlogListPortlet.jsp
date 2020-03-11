<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EgovBlogListPortlet.jsp
 * @Description : 블로그 목록 조회 포틀릿화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.04.07   이삼섭          최초 생성
 * @ 2018.10.11   이정은          포털 메인화면 - 블로그 목록 조회 포틀릿추가
 *
 *  @author 공통서비스 개발팀 이삼섭
 *  @since 2009.04.07
 *  @version 1.0
 *  @see
 *
 */
%>
<ul>
<c:forEach var="result" items="${resultList}" varStatus="status">
   	<li style="padding-top:5px">
		<form name="blogForm" method="post"  action="<c:url value='/cop/bbs/selectArticleBlogList.do'/>" >
		<input type="hidden" name="blogId" value="<c:out value='${result.blogId}'/>"/>
		<input type="hidden" name="blogNm" value="<c:out value='${result.blogNm}'/>"/>
		<input type="submit" value="<c:out value="${result.blogNm}"/>"/>
		</form>
	</li>
</c:forEach>
</ul>
