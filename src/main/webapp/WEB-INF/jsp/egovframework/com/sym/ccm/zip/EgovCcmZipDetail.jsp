<%
 /**
  * @Class Name  : EgovCcmZipDetail.jsp
  * @Description : EgovCcmZipDetail 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호		최초 생성
  * @ 2017.09.01   양희훈		표준프레임워크 v3.7 개선
  * @ 2024.10.29   권태성		수정 페이지 신규 경로로 변경, 2015년 개정된 5자리 우편번호에 맞게 우편번호 표기 방식 개선, 이전페이지로 이동하도록 수정
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
<title>${pageTitle } <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cop/bbs/style.css' />">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_Zip() {
	var varForm = document.getElementById("Form");
	varForm.action = "<c:url value='/sym/ccm/zip/EgovCcmZipList.do'/>";
	varForm.submit();
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_modify_Zip(){
	var varForm				 = document.getElementById("Form");
			varForm.action           = "<c:url value='/sym/ccm/zip/EgovCcmZipModifyView.do'/>";
	if (${searchList} == "1") {
		varForm.zip.value        = "${result.zip}";
		varForm.sn.value         = "${result.sn}";
	} else {
		varForm.rdmnCode.value   = "${result.rdmnCode}";
		varForm.sn.value         = "${result.sn}";
	}
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete_Zip(){
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		var varForm				 = document.getElementById("Form");
		varForm.action           = "<c:url value='/sym/ccm/zip/EgovCcmZipRemove.do'/>";
		if (${searchList} == "1") {
			varForm.zip.value        = "${result.zip}";
			varForm.sn.value         = "${result.sn}";
		} else {
			varForm.rdmnCode.value   = "${result.rdmnCode}";
			varForm.sn.value         = "${result.sn}";
		}
		varForm.submit();
	}
}
-->
</script>
</head>
<body>
<div class="note">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript> <!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="100%" class="title_left"><h1 class="title_left">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="<spring:message code="comSymCcmZip.zipVO.altImg"/>">&nbsp;${pageTitle } <spring:message code="title.detail" /></h1></td>
 </tr>
</table>
<table class="tbl_note" width="700" border="0" cellpadding="0" cellspacing="1" summary="<spring:message code="comSymCcmZip.zipVO.summaryDetail"/>"> <!-- 우편번호, 시도명, 시군구명, 읍면동명, 리건물명, 번지동호를 가지고 있는 우편번호 상세조회 테이블이다. -->
<CAPTION style="display: none;">${pageTitle } <spring:message code="title.detail" /></CAPTION>
  <c:if test="${searchList == '1'}">
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.zip"/> <span class="pilsu">*</span></th> <!-- 우편번호 -->
	    <td>${result.zip}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/> <span class="pilsu">*</span></th> <!-- 시도명 -->
	    <td>${result.ctprvnNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.signguNm"/> <span class="pilsu">*</span></th> <!-- 시군구명 -->
	    <td>${result.signguNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.emdNm"/> <span class="pilsu">*</span></th> <!-- 읍면동명 -->
	    <td>${result.emdNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.liBuldNm"/></th> <!-- 리건물명 -->
	    <td>${result.liBuldNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.lnbrDongHo"/></th><!-- 번지동호 -->
	    <td>${result.lnbrDongHo}</td>
	  </tr>
  </c:if>
  <c:if test="${searchList == '2'}">
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.zip"/> <span class="pilsu">*</span></th><!-- 우편번호 -->
	    <td>${result.zip}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.rdmnCode"/> <span class="pilsu">*</span></th><!-- 도로명코드 -->
	    <td>${result.rdmnCode}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/> <span class="pilsu">*</span></th> <!-- 시도명 -->
	    <td>${result.ctprvnNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.signguNm"/> <span class="pilsu">*</span></th> <!-- 시군구명 -->
	    <td>${result.signguNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.rdmn"/> <span class="pilsu">*</span></th> <!-- 도로명 -->
	    <td>${result.rdmn}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.bdnbrMnnm"/></th> <!-- 건물번호본번 -->
	    <td>${result.bdnbrMnnm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.bdnbrSlno"/></th> <!-- 건물번호부번 -->
	    <td>${result.bdnbrSlno}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.buldNm"/></th> <!-- 건물명 -->
	    <td>${result.buldNm}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap ><spring:message code="comSymCcmZip.zipVO.detailBuldNm"/></th> <!-- 상세건물명 -->
	    <td>${result.detailBuldNm}</td>
	  </tr>
  </c:if>
</table>
<table width="700" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10"></td>
  </tr>
</table>
<div class="txt-cnt mt20">
<input class="btnStyle02" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_Zip(); return false;"></td>
<input class="btnStyle02" type="submit" value="<spring:message code="title.delete" />" onclick="fn_egov_delete_Zip(); return false;"></td>
<input class="btnStyle02" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_Zip(); return false;"></td>
</div>
<form name="Form" id="Form" method="post" action="">
	<input type=hidden name="zip">
	<input type=hidden id="sn" name="sn" value="${not empty result.sn ? result.sn : 0}" />
	<input type=hidden id="rdmnCode" name="rdmnCode" />
	<input type=hidden id="searchList" name="searchList" value="${searchVO.searchList}" />
	<input type=hidden id="searchCondition2" name="searchCondition2" value="${searchVO.searchKeyword}" />
	<input type=hidden id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
</form>
</div>
</body>
</html>
