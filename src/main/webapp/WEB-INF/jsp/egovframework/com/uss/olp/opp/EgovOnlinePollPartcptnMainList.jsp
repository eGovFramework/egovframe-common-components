<%--
  Class Name : EgovOnlinePollPartcptnMainList.jsp
  Description : 온라인POLL 메인 목록 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.09.21    장동한          최초 생성
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.09.21
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--  자마스크립트 메세지 출력 --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>온라인POLL 메인리스트</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectPoll(pollId) {
    document.pollForm.pollId.value = pollId;
    document.pollForm.submit();     
}
-->
</script>
</head>
<body>
<form name="pollForm" method="post" action="<c:url value='/uss/olp/opp/registOnlinePollPartcptn.do'/>">
<table width="200" cellpadding="0" class="table-line" border="0">
<tbody>
<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
<c:if test="${fn:length(resultList) == 0}">
<tr> 
<td class="lt_text6" colspan="2">
	<spring:message code="common.nodata.msg" />
</td>
</tr>   	          				 			   
</c:if>
<%-- 데이터를 화면에 출력해준다 --%>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<c:if test="${status.count < 6}">
<tr>
    <td class="lt_text6" width="80">
    <div class="divDotText" style="width:80px; border:solid 0px;">
		<span class="link"><input type="text" size="14" value="<c:out value="${resultInfo.pollNm}"/>" onclick="fncSelectPoll('<c:out value="${resultInfo.pollId}"/>');" title="POLL선택"></span>	   	
    </div>
    </td>
    <td class="lt_text6" width="60px"><c:out value="${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}"/></td>
</tr>
</c:if>
</c:forEach>
</tbody>  
</table>
<input name="pollId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="1"/>
</form>
</body>
</html>
