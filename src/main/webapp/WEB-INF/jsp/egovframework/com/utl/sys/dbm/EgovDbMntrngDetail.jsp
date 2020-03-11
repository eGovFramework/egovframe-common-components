<%--
  Class Name : EgovDbMntrngDetail.jsp
  Description : DB서비스모니터링 상세조회 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.07    김진만          최초 생성
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.07.07
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUtlSysDbm.dbMntrngDetail.title"/></c:set>
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
    var varForm = document.getElementById("dbMntrngForm");
    varForm.action = "<c:url value='/utl/sys/dbm/getDbMntrngList.do'/>";
    varForm.submit()
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_update_view(){
    var varForm = document.getElementById("dbMntrngForm");
    
    varForm.action = "<c:url value='/utl/sys/dbm/getDbMntrngForUpdate.do'/>";
    varForm.submit();
}
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
 function fn_egov_delete(){
        var vForm = document.dbMntrngForm;
        if(confirm("<spring:message code='common.delete.msg' />")){
            vForm.action = "<c:url value='/utl/sys/dbm/deleteDbMntrng.do'/>";
            vForm.submit();
        }
}
</script>
</head>

<body >

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<DIV class="wTableFrm">

<form name="dbMntrngForm"  id="dbMntrngForm" action="<c:url value='/utl/sys/dbm/getDbMntrng.do'/>" method="post">
    <input name="dataSourcNm" type="hidden" value="<c:out value='${resultInfo.dataSourcNm}'/>"/>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>

    <!-- 상단 타이틀  영역 -->
	<h2>&nbsp;${pageTitle}</h2>

    <!-- 등록  폼 영역  -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="wTable" summary="<spring:message code="comUtlSysDbm.dbMntrngDetail.summary" />">
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
        <th height="23" class="required_text" ><spring:message code="comUtlSysDbm.dbMntrng.monitoringDatetime" /></th><!-- 모니터링시각 -->
        <td class="left">
            <c:out value="${resultInfo.lastUpdusrPnttm}" escapeXml="false" /> 
        </td>
      </tr> 
    </table>

    <!-- 목록/저장버튼  -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_update_view(); return false;" />
		<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete(); return false;" />
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list(); return false;" />
	</div>

</form>
</DIV>

</body>
</html>