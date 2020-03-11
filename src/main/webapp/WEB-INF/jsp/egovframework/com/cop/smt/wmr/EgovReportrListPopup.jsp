<%--
  Class Name : EgovReportrListPopup.jsp
  Description : 보고자 목록 조회
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.09.03    장철호          최초 생성
     2018.09.28    이정은          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장철호
    since    : 2010.09.03
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<title></title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javaScript" language="javascript">

</script>
</head>
<body>
<DIV id="content" style="width:775px">
<!-- ------------------------------------------------------------------ iframe -->
<iframe id="ReportrListView" src="<c:url value='/cop/smt/wmr/selectReportrList.do' />" width="100%" height="475px" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" title="<spring:message code="copSmtWmr.reportrListPopup.reportrList"/>">
</iframe>
</DIV>
</body>
</html>