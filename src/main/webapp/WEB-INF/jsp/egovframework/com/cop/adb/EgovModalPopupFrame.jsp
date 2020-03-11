<%
 /**
  * @Class Name : EgovModalPopupFrame.jsp
  * @Description : 모달 팝업을 위한 외부 프레임
  * @Modification Information
  * @
  * @ 수정일                수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.04.06   이삼섭            최초 생성
  *   2016.08.16   장동한            표준프레임워크 v3.6 개선
  *   2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.06
  *  @version 1.0
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">
	function fn_egov_returnValue(retVal){
		setReturnValue(retVal);
		
		window.returnValue = retVal;
		window.close();
	}

	function closeWindow(){
		window.close();
	}
</script>
<title>선택 목록</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

</head>
<body>
	<iframe title="주소록구성원조회" id="popupFrame" src="<c:url value='${requestUrl}' />" width="<c:out value='${width}'/>" height="<c:out value='${height}'/>" align="middle" frameborder="0"></iframe>
</body>
</html>
