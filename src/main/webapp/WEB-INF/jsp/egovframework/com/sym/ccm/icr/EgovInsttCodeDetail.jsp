<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovInsttCodeDetail.jsp
  * @Description : EgovInsttCodeDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.08.11   이중호              최초 생성
  *
  *  @author 공통컴포넌트팀
  *  @since 2009.08.11
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.title"/></c:set>
<html lang="ko">
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_InsttCode(){
    location.href = "<c:url value='/sym/ccm/icr/getInsttCodeRecptnList.do' />";
}
-->
</script>
</head>
<body>
    <!--
<form name="Form" method="post">
    <input name="insttCode" action="" type="hidden">
</form>
-->
<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>
	
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.insttCode" /> <span class="pilsu">*</span></th> <!-- 기관코드 -->
			<td class="left">
			    ${result.insttCode}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.allInsttNm" /> <span class="pilsu">*</span></th> <!-- 전체기관명 -->
			<td class="left">
			    ${result.allInsttNm}
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.changede" /> <span class="pilsu">*</span></th> <!-- 변경일자 -->
			<td class="left">
			    <c:out value='${fn:substring(result.changede, 0,4)}'/>-<c:out value='${fn:substring(result.changede, 4,6)}'/>-<c:out value='${fn:substring(result.changede, 6,8)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.changeTime" /> <span class="pilsu">*</span></th> <!-- 변경시간 -->
			<td class="left">
			    <c:out value='${fn:substring(result.changeTime, 0,2)}'/>:<c:out value='${fn:substring(result.changeTime, 2,4)}'/>:<c:out value='${fn:substring(result.changeTime, 4,6)}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc" /> <span class="pilsu">*</span></th> <!-- 폐지유무 -->
			<td class="left">
		      <select title="<spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc" />" name="ablEnnc" disabled="disabled">
		          <option value="0" <c:if test="${result.ablEnnc == '0'}">selected="selected"</c:if> ><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc0" /></option>
		          <option value="1" <c:if test="${result.ablEnnc == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc1" /></option>
		      </select>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
      <form name="formList" action="<c:url value='/sym/ccm/icr/getInsttCodeRecptnList.do'/>" method="post">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_InsttCodeRecptn(); return false;" /> <!-- 목록 -->
	  </form>
	</div>
	
	<h3 class="tit02"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.subTitle" /><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.subTitle" /></h3> <!-- 기관코드수신 이력 -->
	
	<table class="board_list">
		<caption><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.subTitle" /></caption> <!-- 기관코드수신 이력 -->
		<colgroup>
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col1" /></th>
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col2" /></th> <!-- 발생일자 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col3" /></th> <!-- 최하위기관명 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col4" /></th> <!-- 변경구분 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col5" /></th> <!-- 처리구분 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col6" /></th> <!-- 생성일자 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col7" /></th> <!-- 변경일자 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col8" /></th> <!-- 폐지유무 -->
			   <th scope="col"><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.col9" /></th> <!-- 폐지일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${insttCodeRecptnList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fnDetail('${resultInfo.administZoneSe}', '${resultInfo.administZoneCode}');">
			    <td class="lt_text3" nowrap><c:out value="${status.count}"/></td>
			    <td class="lt_text3" nowrap><c:out value='${fn:substring(resultInfo.occrrDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.occrrDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.occrrDe, 6,8)}'/></td>
			    <td class="lt_text"  nowrap><c:out value="${resultInfo.lowestInsttNm}"/></td>
			    <td class="lt_text" nowrap>
			        <select title="<spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.changeSeCode" />" name="changeSeCode" class="select" disabled="disabled"> <!-- 변경구분 -->
			            <option value=''><c:out value="${resultInfo.changeSeCode}"/></option>
			            <c:forEach var="result" items="${changeSeCodeList}" varStatus="status">
			            <option value='<c:out value="${result.code}"/>' <c:if test="${result.code == resultInfo.changeSeCode}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
			            </c:forEach>
			        </select>
			    </td>
			    <td class="lt_text" nowrap>
			        <select title="<spring:message code="comSymCcmIcr.insttCodeRecptnDetail.results.processSe" />" name="processSe" class="select" disabled="disabled"> <!-- 처리구분 -->
			            <option value=''><c:out value="${resultInfo.processSe}"/></option>
			            <c:forEach var="result" items="${processSeList}" varStatus="status">
			            <option value='<c:out value="${result.code}"/>' <c:if test="${result.code == resultInfo.processSe}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
			            </c:forEach>
			        </select>
			    </td>
			    <td class="lt_text3"  nowrap><c:out value='${fn:substring(resultInfo.creatDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.creatDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.creatDe, 6,8)}'/></td>
			    <td class="lt_text3"  nowrap><c:out value='${fn:substring(resultInfo.changede, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.changede, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.changede, 6,8)}'/></td>
			    <td class="lt_text3"  nowrap>
			      <select title="<spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc" />" name="ablEnnc" disabled="disabled"> <!-- 폐지유무 -->
			          <option value="0" <c:if test="${resultInfo.ablEnnc == '0'}">selected="selected"</c:if> ><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc0" /></option>
			          <option value="1" <c:if test="${resultInfo.ablEnnc == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmIcr.insttCodeRecptnDetail.ablEnnc1" /></option>
			      </select>
			    </td>
			    <td class="lt_text3"  nowrap><c:out value='${fn:substring(resultInfo.ablDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.ablDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.ablDe, 6,8)}'/></td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(insttCodeRecptnList) == 0}">
			<tr>
				<td colspan="5">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
	
</div>


</body>
</html>
