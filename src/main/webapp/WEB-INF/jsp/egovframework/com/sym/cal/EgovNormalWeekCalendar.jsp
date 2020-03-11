<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovNormalWeekCalendar.jsp
  * @Description : EgovNormalWeekCalendar 화면
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
<c:set var="weeks"   value="${resultList[0].weeks}" />
<c:set var="maxWeeks"   value="${resultList[0].maxWeeks}" />

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
function fn_egov_change_Calendar(form){
	form.submit();
}

-->
</script>
<title>일반달력 주간</title>
</head>

<body>
<DIV id="content">
<!-- ------------------------------------------------------------------ 상단타이틀 -->
<form name="normalWeekCalendar" action ="<c:url value='/sym/cal/EgovNormalWeekCalendar.do'/>" method="post">
	<input type="hidden" name="init" value="${init}" />
	<input type="hidden" name="day" />
	<table width="700" cellpadding="8" class="table-search" border="0">
	  <tr>
	    <td width="200" class="title_left"><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle" alt="제목아이콘이미지">&nbsp;일반달력 주간</td>
	  	<td>
	  		<div style={text-align:right;}>
	  		<select name="year" onChange="javascript:fn_egov_change_Calendar(document.normalWeekCalendar);" title="연도선택">
	  			<c:if test="${year_b3 > 0 && year_b3 < 10000}"><option value="${year_b3}"                    >${year_b3}</option></c:if>
	  			<c:if test="${year_b2 > 0 && year_b2 < 10000}"><option value="${year_b2}"                    >${year_b2}</option></c:if>
	  			<c:if test="${year_b1 > 0 && year_b1 < 10000}"><option value="${year_b1}"                    >${year_b1}</option></c:if>
	  			<c:if test="${year    > 0 && year    < 10000}"><option value="${year   }" selected="selected">${year   }</option></c:if>
	  			<c:if test="${year_a1 > 0 && year_a1 < 10000}"><option value="${year_a1}"                    >${year_a1}</option></c:if>
	  			<c:if test="${year_a2 > 0 && year_a2 < 10000}"><option value="${year_a2}"                    >${year_a2}</option></c:if>
	  			<c:if test="${year_a3 > 0 && year_a3 < 10000}"><option value="${year_a3}"                    >${year_a3}</option></c:if>
	  		</select> 년
	  		&nbsp;&nbsp;
	  		<select name="month" onChange="javascript:fn_egov_change_Calendar(document.normalWeekCalendar);" title="월선택">
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
	  		&nbsp;&nbsp;
	  		<select name="weeks" onChange="javascript:fn_egov_change_Calendar(document.normalWeekCalendar);" title="일선택">
	  			<c:if test="${maxWeeks >= 1}"><option value=0  <c:if test="${weeks==0 }">selected="selected"</c:if> >선택</option></c:if>
	  			<c:if test="${maxWeeks >= 1}"><option value=1  <c:if test="${weeks==1 }">selected="selected"</c:if> >1</option></c:if>
	  			<c:if test="${maxWeeks >= 2}"><option value=2  <c:if test="${weeks==2 }">selected="selected"</c:if> >2</option></c:if>
	  			<c:if test="${maxWeeks >= 3}"><option value=3  <c:if test="${weeks==3 }">selected="selected"</c:if> >3</option></c:if>
	  			<c:if test="${maxWeeks >= 4}"><option value=4  <c:if test="${weeks==4 }">selected="selected"</c:if> >4</option></c:if>
	  			<c:if test="${maxWeeks >= 5}"><option value=5  <c:if test="${weeks==5 }">selected="selected"</c:if> >5</option></c:if>
	  			<c:if test="${maxWeeks == 6}"><option value=6  <c:if test="${weeks==6 }">selected="selected"</c:if> >6</option></c:if>
	  		</select> 주
	  		</div>
	  	</td>
	  </tr>

	</table>

	<table cellpadding="1" class="table-line">
	 <thead>
	  <tr>
	    <th style={height:50px;} class="title" width="80" nowrap>요일</th>
	    <th style={height:50px;} class="title" width="120" nowrap>날짜</th>
	    <th style={height:50px;} class="title" width="300" nowrap>휴일</th>
	  </tr>
	 </thead>
	 <tbody>


		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:red;} >일</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_1[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_1[0].year}년 ${resultList_1[0].month}월 ${resultList_1[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_1[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_1}" varStatus="status">
		    	<c:if test="${resultList_1[0].year eq restde.year && resultList_1[0].month eq restde.month && resultList_1[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:black;} >월</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_2[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_2[0].year}년 ${resultList_2[0].month}월 ${resultList_2[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_2[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_2}" varStatus="status">
		    	<c:if test="${resultList_2[0].year eq restde.year && resultList_2[0].month eq restde.month && resultList_2[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:black;} >화</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_3[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_3[0].year}년 ${resultList_3[0].month}월 ${resultList_3[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_3[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_3}" varStatus="status">
		    	<c:if test="${resultList_3[0].year eq restde.year && resultList_3[0].month eq restde.month && resultList_3[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:black;} >수</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_4[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_4[0].year}년 ${resultList_4[0].month}월 ${resultList_4[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_4[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_4}" varStatus="status">
		    	<c:if test="${resultList_4[0].year eq restde.year && resultList_4[0].month eq restde.month && resultList_4[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:black;} >목</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_5[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_5[0].year}년 ${resultList_5[0].month}월 ${resultList_5[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_5[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_5}" varStatus="status">
		    	<c:if test="${resultList_5[0].year eq restde.year && resultList_5[0].month eq restde.month && resultList_5[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:black;} >금</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_6[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_6[0].year}년 ${resultList_6[0].month}월 ${resultList_6[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_6[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_6}" varStatus="status">
		    	<c:if test="${resultList_6[0].year eq restde.year && resultList_6[0].month eq restde.month && resultList_6[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
		<tr>
			<td style={height:50px;text-align:center;vertical-align:middle;color:red;} >토</td>
			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_7[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList_7[0].year}년 ${resultList_7[0].month}월 ${resultList_7[0].day}일
			</td>

			<td style={height:50px;text-align:center;vertical-align:middle;<c:if test="${resultList_7[0].restAt == 'Y'}">color:red;</c:if>} >
		    <c:forEach var="restde" items="${RestdeList_7}" varStatus="status">
		    	<c:if test="${resultList_7[0].year eq restde.year && resultList_7[0].month eq restde.month && resultList_7[0].day eq restde.day}">
		    	<div style='width:92px;border:solid 0px;'>${restde.restdeNm}</div>
		    	</c:if>
		    </c:forEach>
		    </td>
		</tr>
	 </tbody>
	</table>
</form>
</DIV>
</body>
</html>
