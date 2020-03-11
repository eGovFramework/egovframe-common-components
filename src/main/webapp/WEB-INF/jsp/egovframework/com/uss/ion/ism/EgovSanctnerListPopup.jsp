<%--
  Class Name : EgovSanctnerListPopup.jsp
  Description : 사용자관리 목록 조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.06.29    장철호          최초 생성

    author   : 공통서비스 개발팀 장철호
    since    : 2010.06.29

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
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uss/ion/ism/com.css'/>" />
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">
</script>
</head>
<body  topmargin="0" leftmargin="0">
<DIV id="content" style="width:775px">
<!-- iframe -->
<iframe id="SanctnerListView" src="<c:url value='/uss/ion/ism/selectSanctnerList.do'/>" width="100%" height="475px" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" title="사용자목록">
</iframe>
</DIV>
</body>
</html>

