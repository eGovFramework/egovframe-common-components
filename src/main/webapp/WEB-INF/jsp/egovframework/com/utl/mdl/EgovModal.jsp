<%
 /**
  * @Class Name : EgovModal.jsp
  * @Description : 모달 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2016.07.13    장동한          최초 생성
  *
  *  @author 2016 표준프레임워크유지보수 개발팀 장동한
  *  @since 2016.07.13
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>modal</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	$('#egovBtn').egovModal( 'egovModal' );
	$('#egovBtn2').egovModal( 'egovModal2' );
}
</script>
<style>
.modal-content {width: 80%;}
</style>
</head>
<body onload="fn_egov_init()">


<!-- Trigger/Open The Modal -->
<button id="egovBtn">Open Modal</button>
<button id="egovBtn2">Open Modal2</button>


<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>


<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="N" />
	<c:param name="modalName" value="egovModal2" />
</c:import>



</body>
</html>
