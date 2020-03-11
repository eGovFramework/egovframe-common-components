<!DOCTYPE html>


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--
  Class Name : EgovADressBookMainList.jsp
  Description : 주소록 메인 목록 페이지
  Modification Information

      수정일     수정자           수정내용
    -------    --------    ---------------------------
     2008.09.25   윤성록       최초 생성
     2016.12.13   최두영       JSP명 변경
    author   : 공통컴포넌트팀  윤성록
    since    : 2009.09.25
--%>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style TYPE="text/css">
body {
margin-left:"0px"; margin-right:"0px"; margin-top:"0px"; margin-bottom:"0px";
}
</style>
<title>주소록 목록 메인리스트</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fncSelectAddress(AddressId) {
    document.subForms.adbkId.value = AddressId;
    document.subForms.action = "<c:url value='/cop/adb/updateAdbkInf.do'/>";
    document.subForms.submit();
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<form name="subForms" method="post" action="<c:url value='/cop/adb/updateAdbkInf.do'/>">
<div id="border" style="width:200px">
	<table width="100%" cellpadding="8" class="table-line" summary="주소록에 대한 목록을 제공한다.(마이페이지용)">
	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		 <c:if test="${status.count < 6}">
		  <tr>
		    <td class="lt_text6">
			<span class="link"><input type="text" value="<c:out value="${result.adbkNm}"/>" onclick="fncSelectAddress('<c:out value="${result.adbkId}"/>');" title="해당목록상세조회"></span>
   			</td>
   			 <td class="lt_text6" width="60px"><c:out value="${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}"/></td>
		 </tr>
		</c:if>
		</c:forEach>
	 </tbody>
	</table>
</div>
<input name="adbkId" type="hidden" value=""/>
</form>
</body>
</html>
