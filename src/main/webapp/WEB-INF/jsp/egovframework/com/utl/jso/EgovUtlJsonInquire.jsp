<%
 /**
  * @Class Name : EgovUtlJsonInquire.jsp
  * @Description : 요소기술 - 단건 AJAX JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2016.07.13    장동한          최초 생성
  *
  *  @author 2016 표준프레임워크유지보수 장동한
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
<title>AJAX</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
}

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_json_inquire(){

	$.ajax({
		type:"POST",
		url:"<c:url value='/utl/jso/EgovUtlJsonInquire.do' />",
		data:{
			"param1":"param1",
			"param2":"파라미터2"			
			},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			
			console.trace("상태값>"+status);
			console.trace("결과값>"+returnData);
			$.each(returnData, function(key, value) { 
				console.trace(key + ': ' + value); 
			});
			
			if(status == "success") {
			}else{ alert("ERROR!");return;} 
		}
		});
	
	
}
</script>
</head>
<body onload="fn_egov_init()">

<div class="board">
<!-- Trigger/Open The Modal -->
<button id="egovBtn" onclick="fn_egov_json_inquire()">단건 조회 AJAX</button>


</body>
</html>
