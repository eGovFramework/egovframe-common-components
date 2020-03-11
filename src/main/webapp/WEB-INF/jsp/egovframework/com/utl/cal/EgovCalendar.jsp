<%
 /**
  * @Class Name : EgovCalendar.jsp
  * @Description : 캘린더 JSP
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
<meta charset="UTF-8">
<title>calendar</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			showMonthAfterYear: true,
			showOtherMonths: true,
			selectOtherMonths: true,
			dateFormat: "yy-mm-dd"
		});
	});

/*
	$(function() {
		$( "#datepicker" ).datepicker({
			showMonthAfterYear: true,
			//yearSuffix: "년",
			//monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
			//dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
			showOtherMonths: true,
			selectOtherMonths: true,
			dateFormat: "yy-mm-dd"
		});
	});
*/
</script>


</head>
<body>
<p>Date: <input type="text" id="datepicker" value="2016-07-16"></p>

</body>
</html>