<%--
  Class Name : EgovDbMntrngUpdate.jsp
  Description : DB서비스모니터링 수정 페이지
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.07.09    김진만          최초 생성
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.07.09
   
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
<c:set var="pageTitle"><spring:message code="comUtlSysDbm.dbMntrngModify.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="dbMntrng" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_db_mntrng(){
    var varForm = document.getElementById("dbMntrng");
    varForm.action = "<c:url value='/utl/sys/dbm/getDbMntrngList.do'/>";
    varForm.submit()
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_db_mntrng(){
    var varForm = document.getElementById("dbMntrng");

    if(confirm("<spring:message code='common.save.msg' />")){
        varForm.action =  "<c:url value='/utl/sys/dbm/updateDbMntrng.do'/>";

        if(!validateDbMntrng(varForm)){             
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

<DIV class="wTableFrm">
<!-- 상단타이틀 -->
<form:form modelAttribute="dbMntrng" id="dbMntrng" action="${pageContext.request.contextPath}/utl/sys/dbm/updateDbMntrng.do" method="post">

    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>
    
    <input type="hidden" name="mntrngSttus" value="<c:out value='${dbMntrng.mntrngSttus}'/>"/>

    <!-- 상단 타이틀  영역 -->
	<h2>&nbsp;${pageTitle}</h2>

    <!-- 등록  폼 영역  -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="wTable" summary="<spring:message code="comUtlSysDbm.dbMntrngModify.summary" />"><!-- DB서비스모니터링 수정기능을 제공한다. -->
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap ><label id="IdDataSourcNm" for="dataSourcNm"><spring:message code="comUtlSysDbm.dbMntrng.dataSourceName" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 데이타소스명 -->
        <td width="80%" nowrap class="left">
            <form:input path="dataSourcNm" size="60"  maxlength="60" readonly="true"  cssClass="readOnlyClass"/>
            <div><form:errors path="dataSourcNm" cssClass="error" /></div>  
        </td>
      </tr>
      
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap ><label id="IdServerNm"  for="serverNm"><spring:message code="comUtlSysDbm.dbMntrng.serverName" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 서버명 -->
        <td class="left">
            <form:input path="serverNm" size="60"  maxlength="60" />
            <div><form:errors path="serverNm" cssClass="error" /></div>  
        </td>
      </tr> 
      
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap ><label id="IdDbmsKind"  for="dbmsKind"><spring:message code="comUtlSysDbm.dbMntrng.dbms" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- DBMS종류 -->
        <td class="left">
             <form:select path="dbmsKind">
                 <form:option value="" label="--선택하세요--"/>
                 <form:options items="${dbmsKindList}" itemValue="code" itemLabel="codeNm"/>
             </form:select>
             <div><form:errors path="dbmsKind" cssClass="error"/></div>
        </td>
      </tr> 
      
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap ><label id="IdCeckSql"  for="ceckSql"><spring:message code="comUtlSysDbm.dbMntrng.checkSQL" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 체크SQL -->
        <td class="left">
          <form:input path="ceckSql" size="60" maxlength="250" cssStyle="width:95%;" />
          <div><form:errors path="ceckSql" cssClass="error" /></div>  
        </td>
      </tr> 
      
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap ><label id="IdMngrNm"  for="mngrNm"><spring:message code="comUtlSysDbm.dbMntrng.managerName" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 관리자명 -->
        <td class="left">
          <form:input path="mngrNm" size="60" maxlength="60" />
          <div><form:errors path="mngrNm" cssClass="error" /></div>  
        </td>
      </tr> 
      <tr> 
        <th width="20%" height="23" class="required_text" nowrap ><label id="IdMngrEmailAddr"  for="mngrEmailAddr"><spring:message code="comUtlSysDbm.dbMntrng.managerEmail" /></label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="필수입력" title="필수입력" width="15" height="15"></th><!-- 관리자이메일주소 -->
        <td class="left">
          <form:input path="mngrEmailAddr" size="50" maxlength="50" />
          <div><form:errors path="mngrEmailAddr" cssClass="error" /></div>
        </td>
      </tr> 
    
    </table>

    <!-- 목록/저장버튼  -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_save_db_mntrng(); return false;" />
		<input class="s_submit" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_db_mntrng(); return false;" />
	</div>

</form:form>
</DIV>

</body>
</html>