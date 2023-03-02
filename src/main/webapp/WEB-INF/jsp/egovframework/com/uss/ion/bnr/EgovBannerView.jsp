<%--
/**
 * @Class Name  : EgovBannerView.jsp
 * @Description : EgovBannerView.jsp
 * @Modification Information
 * @
 * @  수정일        수정자          수정내용
 * @ ----------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 * @ 2014.03.31    유지보수         fileSn=0 삭제(파일 수정시 오류)
 * @ 2018.08.30    이정은               공통컴포넌트 3.8 개선 
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<!-- 세로배열 -->
<c:if test="${resultType == 'vertical'}">
<ul class="vertical">
    <c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<li>
		<a href="<c:out value="${fileVO.linkUrl}"/>" target="_blank"  title="새 창으로 이동" ><img alt="배너 이미지" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${egovc:encryptSession(fileVO.bannerImageFile,pageContext.session.id)}"/>'></a>
    </li>
    </c:forEach>
</ul>
</c:if>

<!-- 가로배열 -->
<c:if test="${resultType == 'horizontal'}">
<ul class="horizon">
    <c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<li>
		<a href="<c:out value="${fileVO.linkUrl}"/>" target="_blank"  title="새 창으로 이동" ><img alt="배너 이미지" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${egovc:encryptSession(fileVO.bannerImageFile,pageContext.session.id)}"/>'></a>
    </li>
    </c:forEach>
</ul>
</c:if>
