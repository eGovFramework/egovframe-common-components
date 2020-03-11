<%
 /**
  * @Class Name  : EgovCcmZipSearchPopup.jsp
  * @Description : EgovCcmZipSearchPopup 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<title>우편번호 찾기</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/sym/ccm/zip/zip.css' />" >
</head>
<form name="pForm" action="">
<input type="hidden" name="init" value="">
</form>

<!-- IE
<iframe name="ifcal" src="<c:url value='/sym/ccm/zip/EgovCcmZipSearchList.do'/>" style="width:500px; height:325px;" frameborder=0></iframe>
-->
<!-- FIREFOX -->
<iframe name="ifcal" src="<c:url value='/sym/ccm/zip/EgovCcmZipSearchList.do'/>" style="width:700px; height:340px;" frameborder=0 title="우편번호팝업창호출"></iframe>
