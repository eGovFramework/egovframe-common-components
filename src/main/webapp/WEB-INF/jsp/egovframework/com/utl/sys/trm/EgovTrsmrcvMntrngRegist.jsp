<%--
  Class Name : EgovTrsmrcvMntrngRegist.jsp
  Description : 송수신모니터링 등록 페이지
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
<c:set var="pageTitle"><spring:message code="comUtlSysTrm.trsmrcvMntrngRegist.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" >
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<validator:javascript formName="trsmrcvMntrng" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_get_list(){
    var varForm = document.getElementById("trsmrcvMntrng");
    varForm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrngList.do' />";
    varForm.submit()    
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
    var varForm = document.getElementById("trsmrcvMntrng");
    varForm.action = "<c:url value='/utl/sys/trm/addTrsmrcvMntrng.do' />";
    
    if(!validateTrsmrcvMntrng(varForm)){             
        return;
    }else{
    	if(confirm("<spring:message code='common.save.msg' />")){
        	varForm.submit(); 
        }    
    }
    
}

/* ********************************************************
* 연계검색 팝업화면
******************************************************** */
function fn_egov_popup_cntc_list(){
    
    var retVal;
    var url = "<c:url value='/utl/sys/trm/getCntcListPopup.do' />";
    var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

    retVal = window.showModalDialog(url,window,openParam);     

}

function showModalDialogCallback(retVal) {
}

</script>
</head>
<body>

<%-- noscript 테그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="trsmrcvMntrng" id="trsmrcvMntrng"  action="" method="post">

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />">

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
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationID" /> <span class="pilsu">*</span></th><!-- 연계ID -->
			<td class="left">
			    <form:input path="cntcId" maxlength="8" readonly="true" cssClass="readOnlyClass" cssStyle="width:128px"/>
		        <form:errors path="cntcId" cssClass="error" />
		        <a href="#LINK" onclick="fn_egov_popup_cntc_list(); return false;"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />" alt="<spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationPopup" />"/></a><!-- 연계정보조회팝업 제공 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationName" /> <span class="pilsu">*</span></th><!-- 연계명 -->
			<td class="left">
			    <form:input path="cntcNm" size="60" maxlength="100" readonly="true" cssClass="readOnlyClass"/>
        		<form:errors path="cntcNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.provider" /></th><!-- 제공기관 -->
			<td class="left">
			    <label id="provdInsttNm"><c:out value="${resultInfo.provdInsttNm}" escapeXml="false" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.provideSystem" /></th><!-- 제공시스템 -->
			<td class="left">
			    <label id="provdSysNm"><c:out value="${resultInfo.provdSysNm}" escapeXml="false" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.service" /></th><!-- 제공서비스 -->
			<td class="left">
			    <label id="provdSvcNm"><c:out value="${resultInfo.provdSvcNm}" escapeXml="false" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.requester" /></th><!-- 요청기관 -->
			<td class="left">
			    <label id="requstInsttNm"><c:out value="${resultInfo.requstInsttNm}" escapeXml="false" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.requestSystem" /></th><!-- 요청시스템 -->
			<td class="left">
			    <label id="requstSysNm"><c:out value="${resultInfo.requstSysNm}" escapeXml="false" /></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.testClassName" /> <span class="pilsu">*</span></th><!-- 테스트클래스명 -->
			<td class="left">
			    <form:input path="testClassNm" size="100" maxlength="255"/>
      			<form:errors path="testClassNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerName" /> <span class="pilsu">*</span></th><!-- 관리자명 -->
			<td class="left">
			    <form:input path="mngrNm" size="60" maxlength="60"/>
      			<form:errors path="mngrNm" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerEmail" /> <span class="pilsu">*</span></th><!-- 관리자이메일주소 -->
			<td class="left">
			    <form:input path="mngrEmailAddr" size="50" maxlength="50"/>  
       			<form:errors path="mngrEmailAddr" cssClass="error" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save(); return false;" />
		<span class="btn_s"><a href="<c:url value='/utl/sys/trm/getTrsmrcvMntrngList.do'/>" onclick="fn_egov_get_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

</form:form>

</body>
</html>