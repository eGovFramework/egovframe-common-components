<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovNormalMonthCalendar.jsp
  * @Description : EgovNormalMonthCalendar 화면
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

<c:set var="year_b3" value="${resultList[0].year-3}"/>
<c:set var="year_b2" value="${resultList[0].year-2}"/>
<c:set var="year_b1" value="${resultList[0].year-1}"/>
<c:set var="year"    value="${resultList[0].year}"  />
<c:set var="year_a1" value="${resultList[0].year+1}"/>
<c:set var="year_a2" value="${resultList[0].year+2}"/>
<c:set var="year_a3" value="${resultList[0].year+3}"/>
<c:set var="month"   value="${resultList[0].month}" />

<html lang="ko">
<head>
<title>일반달력</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" />
<script type="text/javaScript" language="javascript">
<!--

/* ********************************************************
 * 연월변경
 ******************************************************** */
function fnChangeCalendar(year, month){
	var varForm			= document.all["Form"];
	varForm.action      = "<c:url value='/sym/cal/EgovNormalMonthCalendar.do'/>";
	varForm.year.value  = year;
	varForm.month.value = month;
	varForm.submit();
}

/* ********************************************************
* 연월변경
******************************************************** */
function fn_egov_change_Calendar(form){
	form.submit();
}

-->
</script>
<title>일반달력 월간</title>
</head>

<body>
<DIV id="content">
<!-- ------------------------------------------------------------------ 상단타이틀 -->
<form name="normalMonthCalendar" action ="<c:url value='/sym/cal/EgovNormalMonthCalendar.do'/>" method="post">
	<input type="hidden" name="init" value="${init}" />
	<input type="hidden" name="day" />
	<table width="700" cellpadding="8" class="table-search" border="0">
	  <tr>
	    <td width="400" class="title_left"><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle" alt="제목아이콘이미지">&nbsp;일반달력 월간</td>
	  	<td>
	  		<div style={text-align:right;}>
	  		<select name="year" onChange="javascript:fn_egov_change_Calendar(document.normalMonthCalendar);" title="연도선택">
	  			<c:if test="${year_b3 > 0 && year_b3 < 10000}"><option value="${year_b3}"                    >${year_b3}</option></c:if>
	  			<c:if test="${year_b2 > 0 && year_b2 < 10000}"><option value="${year_b2}"                    >${year_b2}</option></c:if>
	  			<c:if test="${year_b1 > 0 && year_b1 < 10000}"><option value="${year_b1}"                    >${year_b1}</option></c:if>
	  			<c:if test="${year    > 0 && year    < 10000}"><option value="${year   }" selected="selected">${year   }</option></c:if>
	  			<c:if test="${year_a1 > 0 && year_a1 < 10000}"><option value="${year_a1}"                    >${year_a1}</option></c:if>
	  			<c:if test="${year_a2 > 0 && year_a2 < 10000}"><option value="${year_a2}"                    >${year_a2}</option></c:if>
	  			<c:if test="${year_a3 > 0 && year_a3 < 10000}"><option value="${year_a3}"                    >${year_a3}</option></c:if>
	  		</select> 년
	  		&nbsp;&nbsp;
	  		<select name="month" onChange="javascript:fn_egov_change_Calendar(document.normalMonthCalendar);" title="월선택">
	  			<option value=1  <c:if test="${month==1 }">selected="selected"</c:if> >01</option>
	  			<option value=2  <c:if test="${month==2 }">selected="selected"</c:if> >02</option>
	  			<option value=3  <c:if test="${month==3 }">selected="selected"</c:if> >03</option>
	  			<option value=4  <c:if test="${month==4 }">selected="selected"</c:if> >04</option>
	  			<option value=5  <c:if test="${month==5 }">selected="selected"</c:if> >05</option>
	  			<option value=6  <c:if test="${month==6 }">selected="selected"</c:if> >06</option>
	  			<option value=7  <c:if test="${month==7 }">selected="selected"</c:if> >07</option>
	  			<option value=8  <c:if test="${month==8 }">selected="selected"</c:if> >08</option>
	  			<option value=9  <c:if test="${month==9 }">selected="selected"</c:if> >09</option>
	  			<option value=10 <c:if test="${month==10}">selected="selected"</c:if> >10</option>
	  			<option value=11 <c:if test="${month==11}">selected="selected"</c:if> >11</option>
	  			<option value=12 <c:if test="${month==12}">selected="selected"</c:if> >12</option>
	  		</select> 월
	  		</div>
	  	</td>
	  </tr>

	</table>

	<table cellpadding="1" class="table-line">
	 <thead>
	  <tr>
	    <th style={height:50px;color:red;} class="title" width="100" nowrap >일</th>
	    <th class="title" width="100" nowrap>월</th>
	    <th class="title" width="100" nowrap>화</th>
	    <th class="title" width="100" nowrap>수</th>
	    <th class="title" width="100" nowrap>목</th>
	    <th class="title" width="100" nowrap>금</th>
	    <th style={height:50px;color:red;} class="title" width="100" nowrap>토</th>
	  </tr>
	 </thead>
	 <tbody>
		<tr>
	 		<c:forEach var="result" items="${resultList}" varStatus="status">
				<c:choose>
					<c:when test='${result.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result.day}
	    <c:forEach var="restde" items="${RestdeList}" varStatus="status">
	    	<c:if test="${result.year eq restde.year && result.month eq restde.month && result.day eq restde.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result.day}
	    <c:forEach var="restde" items="${RestdeList}" varStatus="status">
	    	<c:if test="${result.year eq restde.year && result.month eq restde.month && result.day eq restde.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
</form>
</DIV>
</body>
</html>
