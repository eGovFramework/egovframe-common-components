<%
 /**
  * @Class Name  : EgovCcmExcelZipRegist.jsp
  * @Description : EgovCcmExcelZipRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmZip.zipVO.title"/></c:set>
<html lang="ko">
<head>
<title>${pageTitle} <spring:message code="comSymCcmZip.zipVO.excelFile" /> <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cop/bbs/style.css' />">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_Zip(){
	location.href = "<c:url value='/sym/ccm/zip/EgovCcmZipList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_ExcelZip(){
	var varForm				 = document.getElementById("Form");

	// 파일 확장명 확인
	var arrExt      = "xls";
	var arrExt1     = "xlsx";
	var objInput    = varForm.elements["fileNm"];
	var strFilePath = objInput.value;
	var arrTmp      = strFilePath.split(".");
	var strExt      = arrTmp[arrTmp.length-1].toLowerCase();

	if (!(arrExt == strExt || arrExt1 == strExt)) {
		alert("엑셀 파일을 첨부하지 않았습니다.\n확인후 다시 처리하십시오. ");
		abort;
	}
	varForm.action           = "<c:url value='/sym/ccm/zip/EgovCcmExcelZipRegist.do' />";
	varForm.searchList.value = ${searchList};
	varForm.submit();

}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript>
<!-- 엑셀 등록 메시지  -->
${sResult}
<DIV id="note" style="width:712px">
<!-- 상단타이틀 -->
<form name="Form" id="Form" action="<c:url value='/sym/ccm/zip/EgovCcmZipRegist.do'/>" method="post" enctype="multipart/form-data" >
<!-- 상단 타이틀  영역 -->
<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="100%"class="title_left"><h1 class="title_left">
  <c:if test ="${searchList == '1'}">
	<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="<spring:message code="comSymCcmZip.zipVO.altImg"/>">&nbsp;${pageTitle} <spring:message code="comSymCcmZip.zipVO.SearchAddrr" /> <spring:message code="comSymCcmZip.zipVO.excelFile" /> <spring:message code="title.create" /></h1></td><!-- 우편번호 일반주소 엑셀파일 등록 -->
  </c:if>
  <c:if test ="${searchList == '2'}">
	<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="<spring:message code="comSymCcmZip.zipVO.altImg"/>">&nbsp;${pageTitle} <spring:message code="comSymCcmZip.zipVO.SearchRdmn" /> <spring:message code="comSymCcmZip.zipVO.excelFile" /> <spring:message code="title.create" /></h1></td> <!-- 우편번호 도로명주소 엑셀파일 등록 -->
  </c:if>
 </tr>
</table>

<!-- 등록  폼 영역  -->
<table class="tbl_note" width="700" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="<spring:message code="comSymCcmZip.zipVO.summaryExcel"/>"> <!-- 우편번호 엑셀파일을 첨부할 수 있는 등록 테이블입니다. -->
<CAPTION style="display: none;">${pageTitle} <spring:message code="comSymCcmZip.zipVO.excelFile"/> <spring:message code="title.create" /></CAPTION> <!-- 우편번호 엑셀파일 등록 -->
  <tr>
    <th>${pageTitle} <spring:message code="comSymCcmZip.zipVO.excelFile"/></th>
  	<td>
  	<input type="text" id="fileNm" name="fileNm" class="file_input_textbox" style="width: 200px; " />
  	<div class="file_input_div">
		<input type="button" class="file_input_button" value="<spring:message code="comSymCcmZip.zipVO.fileSelect" />" /> <!-- 파일선택 -->
		<input type="file" name="file_1" class="file_input_hidden" onchange="javascript: document.getElementById('fileNm').value = this.value" />
	</div>
  	</td>
  </tr>
</table>
<table width="700" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10"></td>
  </tr>
</table>

<!-- 줄간격조정  -->
<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>
<!-- 목록/저장버튼  -->
<div class="txt-cnt mt20">
		<input type="submit" class="btnStyle02 bg_gray" value="<spring:message code="button.list" />" onClick="fn_egov_list_Zip(); return false;" /> <!-- 목록 -->
		<input type="submit" class="btnStyle02" value="<spring:message code="button.save" />" onClick="fn_egov_regist_ExcelZip(); return false;" /> <!-- 저장 -->
	</div>

<input name="cmd" type="hidden" value="<c:out value='ExcelZipRegist'/>"/>
<input name="searchList" type="hidden" value="${searchList}"/>
</form>
</DIV>
</body>
</html>
