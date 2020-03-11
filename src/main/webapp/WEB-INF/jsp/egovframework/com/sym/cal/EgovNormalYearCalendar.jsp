<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovNormalYearCalendar.jsp
  * @Description : EgovNormalYearCalendar 화면
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

<c:set var="year_b3" value="${resultList_1[0].year-3}"/>
<c:set var="year_b2" value="${resultList_1[0].year-2}"/>
<c:set var="year_b1" value="${resultList_1[0].year-1}"/>
<c:set var="year"    value="${resultList_1[0].year}"  />
<c:set var="year_a1" value="${resultList_1[0].year+1}"/>
<c:set var="year_a2" value="${resultList_1[0].year+2}"/>
<c:set var="year_a3" value="${resultList_1[0].year+3}"/>
<c:set var="month"   value="${resultList_1[0].month}" />

<html lang="ko">
<head>
<title>일반달력</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" />
<script type="text/javaScript" language="javascript">

/* ********************************************************
* 연월변경
******************************************************** */
function fn_egov_change_Calendar(form){
	form.submit();
}

</script>
<title>일반달력 연간</title>
</head>

<body>
<DIV id="content">
<!-- ------------------------------------------------------------------ 상단타이틀 -->
<form name="normalYearCalendar" action ="<c:url value='/sym/cal/EgovNormalYearCalendar.do'/>" method="post">
	<input type="hidden" name="init" value="${init}" />
	<input type="hidden" name="day" />
	<table width="700" cellpadding="8" class="table-search" border="0">
	  <tr>
	    <td width="400" class="title_left"><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle" alt="제목아이콘이미지">&nbsp;일반달력 연간</td>
	  	<td>
	  		<div style={text-align:right;}>
	  		<select name="year" onChange="javascript:fn_egov_change_Calendar(document.normalYearCalendar);" title="연도선택">
	  			<c:if test="${year_b3 > 0 && year_b3 < 10000}"><option value="${year_b3}"                    >${year_b3}</option></c:if>
	  			<c:if test="${year_b2 > 0 && year_b2 < 10000}"><option value="${year_b2}"                    >${year_b2}</option></c:if>
	  			<c:if test="${year_b1 > 0 && year_b1 < 10000}"><option value="${year_b1}"                    >${year_b1}</option></c:if>
	  			<c:if test="${year    > 0 && year    < 10000}"><option value="${year   }" selected="selected">${year   }</option></c:if>
	  			<c:if test="${year_a1 > 0 && year_a1 < 10000}"><option value="${year_a1}"                    >${year_a1}</option></c:if>
	  			<c:if test="${year_a2 > 0 && year_a2 < 10000}"><option value="${year_a2}"                    >${year_a2}</option></c:if>
	  			<c:if test="${year_a3 > 0 && year_a3 < 10000}"><option value="${year_a3}"                    >${year_a3}</option></c:if>
	  		</select> 년
	  		</div>
	  	</td>
	  </tr>
	</table>
<!-- 1월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">1 월</td>
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
	 		<c:forEach var="result_1" items="${resultList_1}" varStatus="status">
				<c:choose>
					<c:when test='${result_1.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_1.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_1.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_1.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_1.day}
	    <c:forEach var="restde_1" items="${RestdeList_1}" varStatus="status">
	    	<c:if test="${result_1.year eq restde_1.year && result_1.month eq restde_1.month && result_1.day eq restde_1.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_1.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_1.day}
	    <c:forEach var="restde_1" items="${RestdeList_1}" varStatus="status">
	    	<c:if test="${result_1.year eq restde_1.year && result_1.month eq restde_1.month && result_1.day eq restde_1.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_1.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_1.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 1월 종료 -->

<!-- 2월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">2 월</td>
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
	 		<c:forEach var="result_2" items="${resultList_2}" varStatus="status">
				<c:choose>
					<c:when test='${result_2.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_2.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_2.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_2.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_2.day}
	    <c:forEach var="restde_2" items="${RestdeList_2}" varStatus="status">
	    	<c:if test="${result_2.year eq restde_2.year && result_2.month eq restde_2.month && result_2.day eq restde_2.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_2.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_2.day}
	    <c:forEach var="restde_2" items="${RestdeList_2}" varStatus="status">
	    	<c:if test="${result_2.year eq restde_2.year && result_2.month eq restde_2.month && result_2.day eq restde_2.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_2.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_2.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 2월 종료 -->
<!-- 3월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">3 월</td>
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
	 		<c:forEach var="result_3" items="${resultList_3}" varStatus="status">
				<c:choose>
					<c:when test='${result_3.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_3.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_3.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_3.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_3.day}
	    <c:forEach var="restde_3" items="${RestdeList_3}" varStatus="status">
	    	<c:if test="${result_3.year eq restde_3.year && result_3.month eq restde_3.month && result_3.day eq restde_3.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_3.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_3.day}
	    <c:forEach var="restde_3" items="${RestdeList_3}" varStatus="status">
	    	<c:if test="${result_3.year eq restde_3.year && result_3.month eq restde_3.month && result_3.day eq restde_3.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_3.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_3.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 3월 종료 -->
<!-- 4월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">4 월</td>
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
	 		<c:forEach var="result_4" items="${resultList_4}" varStatus="status">
				<c:choose>
					<c:when test='${result_4.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_4.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_4.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_4.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_4.day}
	    <c:forEach var="restde_4" items="${RestdeList_4}" varStatus="status">
	    	<c:if test="${result_4.year eq restde_4.year && result_4.month eq restde_4.month && result_4.day eq restde_4.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_4.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_4.day}
	    <c:forEach var="restde_4" items="${RestdeList_4}" varStatus="status">
	    	<c:if test="${result_4.year eq restde_4.year && result_4.month eq restde_4.month && result_4.day eq restde_4.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_4.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_4.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 4월 종료 -->
<!-- 5월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">5 월</td>
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
	 		<c:forEach var="result_5" items="${resultList_5}" varStatus="status">
				<c:choose>
					<c:when test='${result_5.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_5.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_5.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_5.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_5.day}
	    <c:forEach var="restde_5" items="${RestdeList_5}" varStatus="status">
	    	<c:if test="${result_5.year eq restde_5.year && result_5.month eq restde_5.month && result_5.day eq restde_5.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_5.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_5.day}
	    <c:forEach var="restde_5" items="${RestdeList_5}" varStatus="status">
	    	<c:if test="${result_5.year eq restde_5.year && result_5.month eq restde_5.month && result_5.day eq restde_5.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_5.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_5.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 5월 종료 -->
<!-- 6월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">6 월</td>
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
	 		<c:forEach var="result_6" items="${resultList_6}" varStatus="status">
				<c:choose>
					<c:when test='${result_6.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_6.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_6.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_6.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_6.day}
	    <c:forEach var="restde_6" items="${RestdeList_6}" varStatus="status">
	    	<c:if test="${result_6.year eq restde_6.year && result_6.month eq restde_6.month && result_6.day eq restde_6.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_6.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_6.day}
	    <c:forEach var="restde_6" items="${RestdeList_6}" varStatus="status">
	    	<c:if test="${result_6.year eq restde_6.year && result_6.month eq restde_6.month && result_6.day eq restde_6.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_6.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_6.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 6월 종료 -->
<!-- 7월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">7 월</td>
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
	 		<c:forEach var="result_7" items="${resultList_7}" varStatus="status">
				<c:choose>
					<c:when test='${result_7.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_7.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_7.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_7.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_7.day}
	    <c:forEach var="restde_7" items="${RestdeList_7}" varStatus="status">
	    	<c:if test="${result_7.year eq restde_7.year && result_7.month eq restde_7.month && result_7.day eq restde_7.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_7.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_7.day}
	    <c:forEach var="restde_7" items="${RestdeList_7}" varStatus="status">
	    	<c:if test="${result_7.year eq restde_7.year && result_7.month eq restde_7.month && result_7.day eq restde_7.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_7.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_7.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 7월 종료 -->
<!-- 8월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">8 월</td>
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
	 		<c:forEach var="result_8" items="${resultList_8}" varStatus="status">
				<c:choose>
					<c:when test='${result_8.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_8.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_8.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_8.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_8.day}
	    <c:forEach var="restde_8" items="${RestdeList_8}" varStatus="status">
	    	<c:if test="${result_8.year eq restde_8.year && result_8.month eq restde_8.month && result_8.day eq restde_8.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_8.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_8.day}
	    <c:forEach var="restde_8" items="${RestdeList_8}" varStatus="status">
	    	<c:if test="${result_8.year eq restde_8.year && result_8.month eq restde_8.month && result_8.day eq restde_8.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_8.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_8.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 8월 종료 -->
<!-- 9월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">9 월</td>
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
	 		<c:forEach var="result_9" items="${resultList_9}" varStatus="status">
				<c:choose>
					<c:when test='${result_9.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_9.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_9.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_9.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_9.day}
	    <c:forEach var="restde_9" items="${RestdeList_9}" varStatus="status">
	    	<c:if test="${result_9.year eq restde_9.year && result_9.month eq restde_9.month && result_9.day eq restde_9.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_9.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_9.day}
	    <c:forEach var="restde_9" items="${RestdeList_9}" varStatus="status">
	    	<c:if test="${result_9.year eq restde_9.year && result_9.month eq restde_9.month && result_9.day eq restde_9.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_9.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_9.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 9월 종료 -->
<!-- 10월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">10 월</td>
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
	 		<c:forEach var="result_10" items="${resultList_10}" varStatus="status">
				<c:choose>
					<c:when test='${result_10.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_10.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_10.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_10.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_10.day}
	    <c:forEach var="restde_10" items="${RestdeList_10}" varStatus="status">
	    	<c:if test="${result_10.year eq restde_10.year && result_10.month eq restde_10.month && result_10.day eq restde_10.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_10.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_10.day}
	    <c:forEach var="restde_10" items="${RestdeList_10}" varStatus="status">
	    	<c:if test="${result_10.year eq restde_10.year && result_10.month eq restde_10.month && result_10.day eq restde_10.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_10.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_10.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 10월 종료 -->
<!-- 11월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">11 월</td>
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
	 		<c:forEach var="result_11" items="${resultList_11}" varStatus="status">
				<c:choose>
					<c:when test='${result_11.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_11.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_11.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_11.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_11.day}
	    <c:forEach var="restde_11" items="${RestdeList_11}" varStatus="status">
	    	<c:if test="${result_11.year eq restde_11.year && result_11.month eq restde_11.month && result_11.day eq restde_11.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_11.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_11.day}
	    <c:forEach var="restde_11" items="${RestdeList_11}" varStatus="status">
	    	<c:if test="${result_11.year eq restde_11.year && result_11.month eq restde_11.month && result_11.day eq restde_11.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_11.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_11.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 11월 종료 -->
<!-- 12월 시작 -->
	<table cellpadding="8" class="table-search" border="0" style={top-margin:30;}>
	  <tr>
		<td class="title_left">12 월</td>
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
	 		<c:forEach var="result_12" items="${resultList_12}" varStatus="status">
				<c:choose>
					<c:when test='${result_12.day == ""}'>
				 		<c:choose>
					 		<c:when test='${result_12.weeks != 6}'>
		<td style={height:50px;}> </td>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
				 		<c:choose>
					 		<c:when test='${result_12.restAt == "Y" }'>
						 		<c:choose>
							 		<c:when test='${result_12.week == 7}'>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_12.day}
	    <c:forEach var="restde_12" items="${RestdeList_12}" varStatus="status">
	    	<c:if test="${result_12.year eq restde_12.year && result_12.month eq restde_12.month && result_12.day eq restde_12.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_12.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
    <c:out value="</tr>" escapeXml="false"/>
    <c:out value="<tr>" escapeXml="false"/>
									</c:when>
									<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:red;} nowrap >${result_12.day}
	    <c:forEach var="restde_12" items="${RestdeList_12}" varStatus="status">
	    	<c:if test="${result_12.year eq restde_12.year && result_12.month eq restde_12.month && result_12.day eq restde_12.day}"><table><tr><td style={text-decoration:none;} nowrap><div style='width:70px;border:solid 0px;'>${restde_12.restdeNm}</div></td></tr></table></c:if>
	    </c:forEach>
	    </td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
	    <td style={height:50px;text-align:left;vertical-align:top;color:black;} nowrap >${result_12.day}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	 </tbody>
	</table>
<!-- 12월 종료 -->
	<table cellpadding="8" class="table-search" border="0" style={height:50px;}>
	  <tr>
		<td class="title_left"> </td>
	  </tr>
	</table>

</form>
</DIV>
</body>
</html>