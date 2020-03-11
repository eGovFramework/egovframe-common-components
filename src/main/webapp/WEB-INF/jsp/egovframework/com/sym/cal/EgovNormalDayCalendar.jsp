<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovNormalDayCalendar.jsp
  * @Description : EgovNormalDayCalendar 화면
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

<c:set var="year_b3"      value="${resultList[0].year-3}"       />
<c:set var="year_b2"      value="${resultList[0].year-2}"       />
<c:set var="year_b1"      value="${resultList[0].year-1}"       />
<c:set var="year"         value="${resultList[0].year}"         />
<c:set var="year_a1"      value="${resultList[0].year+1}"       />
<c:set var="year_a2"      value="${resultList[0].year+2}"       />
<c:set var="year_a3"      value="${resultList[0].year+3}"       />
<c:set var="month"        value="${resultList[0].month}"        />
<c:set var="week"         value="${resultList[0].week}"         />
<c:set var="day"          value="${resultList[0].day}"          />
<c:set var="lastDayMonth" value="${resultList[0].lastDayMonth}" />

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
<title>일반달력 일간</title>
</head>

<body>
<DIV id="content">
<!-- ------------------------------------------------------------------ 상단타이틀 -->
<form name="normalDayCalendar" action ="<c:url value='/sym/cal/EgovNormalDayCalendar.do'/>" method="post">
	<input type="hidden" name="init" value="${init}" />
	<table width="700" cellpadding="8" class="table-search" border="0">
	  <tr>
	    <td width="200" class="title_left"><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle" alt="제목아이콘이미지">&nbsp;일반달력 일간</td>
	  	<td>
	  		<div style={text-align:right;}>
	  		<select name="year" onChange="javascript:fn_egov_change_Calendar(document.normalDayCalendar);" title="연도선택">
	  			<c:if test="${year_b3 > 0 && year_b3 < 10000}"><option value="${year_b3}"                    >${year_b3}</option></c:if>
	  			<c:if test="${year_b2 > 0 && year_b2 < 10000}"><option value="${year_b2}"                    >${year_b2}</option></c:if>
	  			<c:if test="${year_b1 > 0 && year_b1 < 10000}"><option value="${year_b1}"                    >${year_b1}</option></c:if>
	  			<c:if test="${year    > 0 && year    < 10000}"><option value="${year   }" selected="selected">${year   }</option></c:if>
	  			<c:if test="${year_a1 > 0 && year_a1 < 10000}"><option value="${year_a1}"                    >${year_a1}</option></c:if>
	  			<c:if test="${year_a2 > 0 && year_a2 < 10000}"><option value="${year_a2}"                    >${year_a2}</option></c:if>
	  			<c:if test="${year_a3 > 0 && year_a3 < 10000}"><option value="${year_a3}"                    >${year_a3}</option></c:if>
	  		</select> 년
	  		&nbsp;&nbsp;
	  		<select name="month" onChange="javascript:fn_egov_change_Calendar(document.normalDayCalendar);" title="월선택">
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
	  		<select name="day" onChange="javascript:fn_egov_change_Calendar(document.normalDayCalendar);" title="일선택">
	  			<c:if test="${lastDayMonth >= 28}"><option value=0  <c:if test="${day==0 }">selected="selected"</c:if> >선택</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=1  <c:if test="${day==1 }">selected="selected"</c:if> >01</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=2  <c:if test="${day==2 }">selected="selected"</c:if> >02</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=3  <c:if test="${day==3 }">selected="selected"</c:if> >03</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=4  <c:if test="${day==4 }">selected="selected"</c:if> >04</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=5  <c:if test="${day==5 }">selected="selected"</c:if> >05</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=6  <c:if test="${day==6 }">selected="selected"</c:if> >06</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=7  <c:if test="${day==7 }">selected="selected"</c:if> >07</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=8  <c:if test="${day==8 }">selected="selected"</c:if> >08</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=9  <c:if test="${day==9 }">selected="selected"</c:if> >09</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=10 <c:if test="${day==10}">selected="selected"</c:if> >10</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=11 <c:if test="${day==11}">selected="selected"</c:if> >11</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=12 <c:if test="${day==12}">selected="selected"</c:if> >12</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=13 <c:if test="${day==13}">selected="selected"</c:if> >13</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=14 <c:if test="${day==14}">selected="selected"</c:if> >14</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=15 <c:if test="${day==15}">selected="selected"</c:if> >15</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=16 <c:if test="${day==16}">selected="selected"</c:if> >16</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=17 <c:if test="${day==17}">selected="selected"</c:if> >17</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=18 <c:if test="${day==18}">selected="selected"</c:if> >18</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=19 <c:if test="${day==19}">selected="selected"</c:if> >19</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=20 <c:if test="${day==20}">selected="selected"</c:if> >20</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=21 <c:if test="${day==21}">selected="selected"</c:if> >21</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=22 <c:if test="${day==22}">selected="selected"</c:if> >22</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=23 <c:if test="${day==23}">selected="selected"</c:if> >23</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=24 <c:if test="${day==24}">selected="selected"</c:if> >24</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=25 <c:if test="${day==25}">selected="selected"</c:if> >25</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=26 <c:if test="${day==26}">selected="selected"</c:if> >26</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=27 <c:if test="${day==27}">selected="selected"</c:if> >27</option></c:if>
	  			<c:if test="${lastDayMonth >= 28}"><option value=28 <c:if test="${day==28}">selected="selected"</c:if> >28</option></c:if>
	  			<c:if test="${lastDayMonth >= 29}"><option value=29 <c:if test="${day==29}">selected="selected"</c:if> >29</option></c:if>
	  			<c:if test="${lastDayMonth >= 30}"><option value=30 <c:if test="${day==30}">selected="selected"</c:if> >30</option></c:if>
	  			<c:if test="${lastDayMonth == 31}"><option value=31 <c:if test="${day==31}">selected="selected"</c:if> >31</option></c:if>
	  		</select> 일
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
			<td style={height:150px;text-align:center;vertical-align:middle;<c:if test="${resultList[0].restAt == 'Y'}">color:red;</c:if>} >
				<c:if test="${week == 1}">일</c:if>
				<c:if test="${week == 2}">월</c:if>
				<c:if test="${week == 3}">화</c:if>
				<c:if test="${week == 4}">수</c:if>
				<c:if test="${week == 5}">목</c:if>
				<c:if test="${week == 6}">금</c:if>
				<c:if test="${week == 7}">토</c:if>
			</td>
			<td style={height:150px;text-align:center;vertical-align:middle;<c:if test="${resultList[0].restAt == 'Y'}">color:red;</c:if>} >
				${resultList[0].year}년 ${resultList[0].month}월 ${resultList[0].day}일
			</td>

		    <td style={height:150px;text-align:center;vertical-align:middle;} nowrap STYLE="color:red">
		    <c:forEach var="restde" items="${RestdeList}" varStatus="status">
		    	<c:if test="${resultList[0].year eq restde.year && resultList[0].month eq restde.month && resultList[0].day eq restde.day}">
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
