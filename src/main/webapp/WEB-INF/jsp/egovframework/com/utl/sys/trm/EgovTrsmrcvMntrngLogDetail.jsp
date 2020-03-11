<%--
  Class Name : EgovTrsmrcvMntrngLogDetail.jsp
  Description : 송수신모니터링로그 상세조회 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.17    김진만          최초 생성
 
    author   : 공통서비스 개발팀 김진만
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUtlSysTrm.trsmrcvMntrngLogDetail.title"/></c:set>
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
    var varForm = document.getElementById("trsmrcvMntrngLogForm");
    varForm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrngLogList.do'/>";
    varForm.submit()
}
</script>
</head>

<body >

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<DIV class="wTableFrm">

<form name="trsmrcvMntrngLogForm"  id="trsmrcvMntrngLogForm" action="<c:url value='/utl/sys/trm/getTrsmrcvMntrngLogList.do'/>" method="post">
    <input name="cntcId" type="hidden" value="<c:out value='${resultInfo.cntcId}'/>"/>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="searchKeywordFrom" value="<c:out value='${searchVO.searchKeywordFrom}'/>">
    <input type="hidden" name="searchKeywordTo" value="<c:out value='${searchVO.searchKeywordTo}'/>">
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>

    <!-- 상단 타이틀  영역 -->
	<h2>&nbsp;${pageTitle}</h2>

    <!-- 등록  폼 영역  -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="wTable" summary="<spring:message code="comUtlSysTrm.trsmrcvMntrngLogDetail.summary" />">
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap="nowrap" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.logID" /></th><!-- 로그ID -->
        <td width="80%" class="left">
            <c:out value="${resultInfo.logId}" escapeXml="false" /> 
        </td>
      </tr>
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap="nowrap" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationID" /></th><!-- 연계ID -->
        <td width="80%" class="left">
            <c:out value="${resultInfo.cntcId}" escapeXml="false" /> 
        </td>
      </tr>
    
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationName" /></th><!-- 연계명 -->
        <td class="left">
            <c:out value="${resultInfo.cntcNm}" escapeXml="false" /> 
        </td>
      </tr> 
      
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.provider" /></th><!-- 제공기관 -->
        <td class="left">
            <c:out value="${resultInfo.provdInsttNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.provideSystem" /></th><!-- 제공시스템 -->
        <td class="left">
            <c:out value="${resultInfo.provdSysNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.service" /></th><!-- 제공서비스 -->
        <td class="left">
            <c:out value="${resultInfo.provdSvcNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.requester" /></th><!-- 요청기관 -->
        <td class="left">
            <c:out value="${resultInfo.requstInsttNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.requestSystem" /></th><!-- 요청시스템 -->
        <td class="left">
            <c:out value="${resultInfo.requstSysNm}" escapeXml="false" /> 
        </td>
      </tr> 
      
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.testClassName" /></th><!-- 테스트클래스명 -->
        <td class="left">
            <c:out value="${resultInfo.testClassNm}" escapeXml="false" /> 
        </td>
      </tr> 
      
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerName" /></th><!-- 관리자명 -->
        <td class="left">
            <c:out value="${resultInfo.mngrNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerEmail" /></th><!-- 관리자이메일주소 -->
        <td class="left">
            <c:out value="${resultInfo.mngrEmailAddr}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.status" /></th><!-- 모니터링상태 -->
        <td class="left">
            <c:out value="${resultInfo.mntrngSttusNm}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.logInfo" /></th><!-- 로그정보 -->
        <td class="left">
            <c:out value="${resultInfo.logInfo}" escapeXml="false" /> 
        </td>
      </tr> 
      <tr> 
        <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.monitoringDateTime" /></th><!-- 모니터링시각 -->
        <td class="left">
            <c:out value="${resultInfo.creatDt}" escapeXml="false" /> 
        </td>
      </tr> 
    </table>

    <!-- 목록/저장버튼  -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list(); return false;" />
	</div>

</form>
</DIV>

</body>
</html>