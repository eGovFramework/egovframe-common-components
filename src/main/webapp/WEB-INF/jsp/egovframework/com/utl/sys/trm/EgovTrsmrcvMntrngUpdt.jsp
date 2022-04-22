<%--
  Class Name : EgovTrsmrcvMntrngUpdate.jsp
  Description : 송수신모니터링 수정 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.22    김진만          최초 생성
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.07.22
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUtlSysTrm.trsmrcvMntrngUpdt.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="trsmrcvMntrng" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_get_list(){
    var varForm = document.getElementById("trsmrcvMntrng");
    varForm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrngList.do'/>";
    varForm.submit()
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
    var varForm = document.getElementById("trsmrcvMntrng");

    if(confirm("<spring:message code='common.save.msg' />")){
        varForm.action =  "<c:url value='/utl/sys/trm/updateTrsmrcvMntrng.do'/>";

        if(!validateTrsmrcvMntrng(varForm)){             
            return;
        }else{
            varForm.submit();
        } 
    }
}

</script>
</head>
<body>
<%-- noscript 태그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">					
<!-- 상단타이틀 -->
<form:form modelAttribute="trsmrcvMntrng" id="trsmrcvMntrng" action="<c:url value='/utl/sys/trm/updateTrsmrcvMntrng.do'/>" method="post">

    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>
    
    <input type="hidden" name="cntcId" value="<c:out value='${trsmrcvMntrng.cntcId}'/>"/>
    <input type="hidden" name="mntrngSttus" value="<c:out value='${trsmrcvMntrng.mntrngSttus}'/>"/>
    <!-- 상단 타이틀  영역 -->
	<h2>&nbsp;${pageTitle}</h2>

    <!-- 등록  폼 영역  -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="wTable" summary="<spring:message code="comUtlSysTrm.trsmrcvMntrngUpdt.summary" />"><!-- 송수신모니터링 수정기능을 제공한다. -->
  <tr> 
    <th width="20%" height="23" class="required_text" nowrap ><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationID" /></th><!-- 연계ID -->
    <td width="80%" nowrap class="left">
        <c:out value="${trsmrcvMntrng.cntcId}" escapeXml="false" /> 
    </td>
  </tr>
  
  <tr> 
    <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationName" /></th><!-- 연계명 -->
    <td class="left">
        <c:out value="${trsmrcvMntrng.cntcNm}" escapeXml="false" /> 
    </td>
  </tr> 
  
  <tr> 
    <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.provider" /></th><!-- 제공기관 -->
    <td class="left">
        <c:out value="${trsmrcvMntrng.provdInsttNm}" escapeXml="false" /> 
    </td>
  </tr> 
  <tr> 
    <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.provideSystem" /></th><!-- 제공시스템 -->
    <td class="left">
        <c:out value="${trsmrcvMntrng.provdSysNm}" escapeXml="false" /> 
    </td>
  </tr> 
  <tr> 
    <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.service" /></th><!-- 제공서비스 -->
    <td class="left">
        <c:out value="${trsmrcvMntrng.provdSvcNm}" escapeXml="false" /> 
    </td>
  </tr> 
  <tr> 
    <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.requester" /></th><!-- 요청기관 -->
    <td class="left">
        <c:out value="${trsmrcvMntrng.requstInsttNm}" escapeXml="false" /> 
    </td>
  </tr> 
  <tr> 
    <th height="23" class="required_text" ><spring:message code="comUtlSysTrm.trsmrcvMntrng.requestSystem" /></th><!-- 요청시스템 -->
    <td class="left">
        <c:out value="${trsmrcvMntrng.requstSysNm}" escapeXml="false" /> 
    </td>
  </tr> 
  
  <tr> 
    <th width="20%" height="23" class="required_text" nowrap ><label id="idTestClassNm" for="testClassNm"><spring:message code="comUtlSysTrm.trsmrcvMntrng.testClassName" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 테스트클래스명 -->
    <td class="left">
      <form:input path="testClassNm" size="100" maxlength="255" />
      <form:errors path="testClassNm" cssClass="error" />
    </td>
  </tr> 
  
  <tr> 
    <th width="20%" height="23" class="required_text" nowrap ><label id="IdMngrNm" for="mngrNm"><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerName" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 관리자명 -->
    <td class="left">
      <form:input path="mngrNm" size="60" maxlength="60"/>
      <form:errors path="mngrNm" cssClass="error" /> 
    </td>
  </tr> 
  <tr> 
    <th width="20%" height="23" class="required_text" nowrap ><label id="IdMngrEmailAddr" for="mngrEmailAddr"><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerEmail" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 관리자이메일주소 -->
    <td class="left">
      <form:input path="mngrEmailAddr" size="50" maxlength="50"/>  
       <form:errors path="mngrEmailAddr" cssClass="error" />
    </td>
  </tr> 
  </table>  
    <!-- 목록/저장버튼  -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_save(); return false;" />
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_get_list(); return false;" />
	</div>
</form:form>
</DIV>

</body>
</html>