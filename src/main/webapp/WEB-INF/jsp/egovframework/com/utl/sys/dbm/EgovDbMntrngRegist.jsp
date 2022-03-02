<%--
  Class Name : EgovDbMntrngRegist.jsp
  Description : DB서비스모니터링 등록 페이지
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUtlSysDbm.dbMntrngRegist.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<validator:javascript formName="dbMntrng" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_get_db_mntrng_list(){
    var varForm = document.getElementById("dbMntrng");
    varForm.action = "<c:url value='/utl/sys/dbm/getDbMntrngList.do'/>";
    varForm.submit()    
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_db_mntrng(){
    var varForm = document.getElementById("dbMntrng");
    varForm.action =  "<c:url value='/utl/sys/dbm/addDbMntrng.do'/>";
    if(confirm("<spring:message code='common.save.msg' />")){

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

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="dbMntrng"  action="<c:url value='/utl/sys/dbm/addDbMntrng.do'/>" method="post">

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysDbm.dbMntrng.dataSourceName" /> <span class="pilsu">*</span></th><!-- 데이타소스명 -->
			<td class="left">
			    <form:input path="dataSourcNm" size="60" maxlength="60"/>
        		<form:errors path="dataSourcNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysDbm.dbMntrng.serverName" /> <span class="pilsu">*</span></th><!-- 서버명 -->
			<td class="left">
			    <form:input path="serverNm" size="60" maxlength="60"/>
        		<form:errors path="serverNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysDbm.dbMntrng.dbms" /> <span class="pilsu">*</span></th><!-- DBMS종류 -->
			<td class="left">
			    <form:select path="dbmsKind">
				<form:option value="" label="--선택하세요--"/>
				<form:options items="${dbmsKindList}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<form:errors path="dbmsKind" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysDbm.dbMntrng.checkSQL" /> <span class="pilsu">*</span></th><!-- 체크SQL -->
			<td class="left">
			    <form:input path="ceckSql" size="60" maxlength="250"/>
     			<form:errors path="ceckSql" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysDbm.dbMntrng.managerName" /> <span class="pilsu">*</span></th><!-- 관리자명 -->
			<td class="left">
			    <form:input path="mngrNm" size="60" maxlength="60"/>
      			<form:errors path="mngrNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysDbm.dbMntrng.managerEmail" /> <span class="pilsu">*</span></th><!-- 관리자이메일주소 -->
			<td class="left">
			    <form:input path="mngrEmailAddr" size="50" maxlength="50"/>  
       			<form:errors path="mngrEmailAddr" cssClass="error" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_db_mntrng(); return false;" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/dbm/getDbMntrngList.do'></c:url>" onclick="fn_egov_get_db_mntrng_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>