<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovInsttCodeRecptnMainList.jsp
  * @Description : EgovInsttCodeRecptnMainList 화면
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
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>기관코드수신 목록조회</title>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* ********************************************************
* 상세회면 처리 함수
******************************************************** */
function fn_egov_detail_InsttCode(insttCode){
	var varForm				 = document.codeForm;
	varForm.insttCode.value  = insttCode;
	varForm.submit();
}
-->
</script>
</head>
<body>
<form name="codeForm" method="post" action="<c:url value='/sym/ccm/icr/getInsttCodeDetail.do'/>">
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
<tr>
    <td class="lt_text6" width="30">
    <div class="divDotText" style="width:30px; border:solid 0px;">
		<span class="link"><input title="기관코드" type="text" size="10" value="<c:out value="${resultInfo.insttCode}"/>" onclick="fn_egov_detail_InsttCode('<c:out value="${resultInfo.insttCode}"/>');"></span>
    </div>
    </td>
    <td class="lt_text6"><c:out value="${resultInfo.lowestInsttNm}"/></td>
</tr>
</c:forEach>
</tbody>
</table>
<input name="insttCode" type="hidden"   value="" />
<input name="pageIndex" type="hidden"   value="<c:out value="1"/>" />
</form>
</body>
</html>
