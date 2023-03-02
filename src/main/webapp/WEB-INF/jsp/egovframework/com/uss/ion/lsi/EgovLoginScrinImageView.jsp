<%--
/**
 * @Class Name  : EgovLoginScrinImageView.jsp
 * @Description : EgovLoginScrinImageView.jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 * @ 2014.03.31    유지보수         fileSn=0 삭제(파일 수정시 오류)
 * @ 2018.09.17    이정은               공통컴포넌트 3.8 개선
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
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">

<!DOCTYPE html>
<title><spring:message code="uss.ion.msi.mainImageView.mainImageView" /></title><!-- 로그인화면이미지 반영 -->

<div class="wTableFrm">
<!-- 타이틀 -->
<h2><spring:message code="ussIonLsi.loginScrinImageView.loginScrinImageView" /></h2><!-- 로그인화면이미지 반영  -->
<span><spring:message code="ussIonLsi.loginScrinImageView.loginScrinImageViewDc" /></span>
<table>
  <tr>
    <c:forEach var="fileVO" items="${fileList}" varStatus="status">
       <td>
            <img alt="<spring:message code="ussIonLsi.loginScrinImageView.loginScrinImageView" />" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${egovc:encryptSession(fileVO.imageFile,pageContext.session.id)}"/>' />
       </td>
    </c:forEach>
    <td></td>
  </tr>
</table>
