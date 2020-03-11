<%--
  Class Name : EgovDbMntrngLogDetail.jsp
  Description : DB서비스모니터링로그 상세조회 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.07    김진만          최초 생성
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.08.07
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUtlSysDbm.dbMntrngLogDetail.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
    var varForm = document.getElementById("dbMntrngLogForm");
    varForm.action = "<c:url value='/utl/sys/dbm/getDbMntrngLogList.do'/>";
    varForm.submit()
}
</script>
</head>

<body >

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<DIV class="wTableFrm">

<form name="dbMntrngLogForm"  id="dbMntrngLogForm" action="<c:url value='/utl/sys/dbm/getDbMntrngLog.do'/>" method="post">
    <input name="logId" type="hidden" value="<c:out value='${resultInfo.logId}'/>"/>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>
    <input type="hidden" name="searchKeywordFrom" value="<c:out value='${searchVO.searchKeywordFrom}'/>">
    <input type="hidden" name="searchKeywordTo" value="<c:out value='${searchVO.searchKeywordTo}'/>">

    <!-- 상단 타이틀  영역 -->
	<h2>&nbsp;${pageTitle}</h2>

    <!-- 등록  폼 영역  -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="wTable" summary="<spring:message code="comUtlSysDbm.dbMntrngLogDetail.summary" />"><!-- 등록된 DB서비스모니터링로그에 대한 상세정보를 제공합니다. -->
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap="nowrap" ><spring:message code="comUtlSysDbm.dbMntrng.logID" /></th><!-- 로그ID -->
        <td width="80%" class="left">
            <c:out value="${resultInfo.logId}" escapeXml="false" /> 
        </td>
      </tr>
    
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap="nowrap" ><spring:message code="comUtlSysDbm.dbMntrng.dataSourceName" /></th><!-- 데이타소스명 -->
        <td width="80%" class="left">
            <c:out value="${resultInfo.dataSourcNm}" escapeXml="false" /> 
        </td>
      </tr>
    
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.serverName" /></th><!-- 서버명 -->
        <td class="left">
            <c:out value="${resultInfo.serverNm}" escapeXml="false" /> 
        </td>
      </tr> 
      
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.dbms" /></th><!-- DBMS종류 -->
        <td class="left">
            <c:out value="${resultInfo.dbmsKindNm}" escapeXml="false" /> 
        </td>
      </tr> 
      
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.checkSQL" /></th><!-- 체크SQL -->
        <td class="left">
            <c:out value="${resultInfo.ceckSql}" escapeXml="false" /> 
        </td>
      </tr> 
      
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.managerName" /></th><!-- 관리자명 -->
        <td class="left">
            <c:out value="${resultInfo.mngrNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.managerEmail" /></th><!-- 관리자이메일주소 -->
        <td class="left">
            <c:out value="${resultInfo.mngrEmailAddr}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.status" /></th><!-- 모니터링상태 -->
        <td class="left">
            <c:out value="${resultInfo.mntrngSttusNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.logInfo" /></th><!-- 로그정보 -->
        <td class="left">
            <c:out value="${resultInfo.logInfo}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.monitoringDatetime" /></th><!-- 모니터링시각 -->
        <td class="left">
            <c:out value="${resultInfo.creatDt}" escapeXml="false" /> 
        </td>
      </tr> 
    </table>

    <!-- 목록버튼  -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list(); return false;" />
	</div>
</form>
</DIV>

</body>
</html>