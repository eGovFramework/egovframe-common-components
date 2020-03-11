<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCcmZipRegist.jsp
  * @Description : EgovCcmZipRegist 화면
  * @Modification Information
  * @
  * @ 수정일               수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.04.01   이중호            최초 생성
  * @ 2017.08.30   양희훈            표준프레임워크 v3.7 개선
  * @ 2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comSymCcmZip.zipVO.title"/></c:set>
<html lang="ko">
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cop/bbs/style.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="zip" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_Zip(){
	location.href = "<c:url value='/sym/ccm/zip/EgovCcmZipList.do' />";
}
/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_egov_regist_Zip(form){
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateZip(form)){
			return;
		}else{
			form.submit();
		}
	}
}
/* ********************************************************
 * 주소검색
 ******************************************************** */
function goAddSearch() {
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
    var pop = window.open("<c:url value='/sym/ccm/zip/EgovAdressPop.do' />","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
    
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(zipNo,rnMgtSn,siNm,sggNm,roadFullAddr,buldMnnm,buldSlno,bdNm,detBdNmList){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.zip.zip.value = zipNo; 										/* 우편번호 */
	document.zip.rdmnCode.value = rnMgtSn; 						/* 도로명코드 */
	document.zip.ctprvnNm.value = siNm; 								/* 시도명 */
	document.zip.signguNm.value = sggNm; 							/* 시군구명 */
	document.zip.rdmn.value = roadFullAddr; 						    /* 도로명 */
	document.zip.bdnbrMnnm.value = buldMnnm; 					/* 건물본번 */
	document.zip.bdnbrSlno.value = buldSlno; 						/* 건물부번 */
	document.zip.buldNm.value = bdNm; 								/* 건물명 */
	document.zip.detailBuldNm.value = detBdNmList; 				/* 상세건물명 */
}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript>
<form:form commandName="zip" name="zip" method="post">
<div class="note">

<!-- 상단 타이틀  영역 -->
  <h1>
  <c:set var="titleZip"><spring:message code="comSymCcmZip.zipVO.zipCreate"/></c:set>
  <c:set var="titleRdmn"><spring:message code="comSymCcmZip.zipVO.rdmnCreate"/></c:set>
  <c:if test="${searchList == '1'}">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;${titleZip }</h1></td>
  </c:if>
  <c:if test="${searchList == '2'}">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;${titleRdmn }</h1></td>
  </c:if>

<!-- 등록  폼 영역  -->
<c:set var="titleMessage"><spring:message code="comSymCcmZip.zipVO.zipMessage"/></c:set>
<table class="tbl_note" summary="<spring:message code="comSymCcmZip.zipVO.summaryInsert"/>"><!-- 우편번호, 시도명, 시군구명, 읍면동명, 리건물명, 번지동호를 입력하는 우편번호 등록 테이블입니다. -->
<caption>${pageTitle } <spring:message code="title.create" /></caption>
<colgroup>
	<col style="width: 20%;"><col style="width: ;">
</colgroup>
<tbody>
  <c:if test="${searchList == '1'}">
  	  <!-- 우편번호  -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.zip"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="zip">${title} <span class="pilsu">*</span></label></th>
	    <td class="left">
	      <form:input path="zip" size="5" maxlength="5" id="zip"/>
	      <form:errors path="zip"/> &nbsp;* ${titleMessage } <!-- 우편번호의 '-'를 제외하고 입력하시오. -->
	    </td>
	  </tr>
	  <!-- 시도명  -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="ctprvnNm">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="ctprvnNm" size="20" maxlength="20" id="ctprvnNm"/>
	      <form:errors path="ctprvnNm"/>
	    </td>
	  </tr>
	  <!-- 시군구명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.signguNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="signguNm">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="signguNm" size="20" maxlength="20" id="signguNm"/>
	      <form:errors path="signguNm"/>
	    </td>
	  </tr>
	  <!-- 읍면동명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.emdNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="emdNm">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="emdNm" size="30" maxlength="30" id="emdNm"/>
	      <form:errors path="emdNm"/>
	    </td>
	  </tr>
	  <!-- 리건물명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.liBuldNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="liBuldNm">${title}</label></th>
	    <td>
	      <form:input  path="liBuldNm" size="60" maxlength="60" id="liBuldNm"/>
	      <form:errors path="liBuldNm"/>
	    </td>
	  </tr>
	  <!-- 번지동호 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.lnbrDongHo"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="lnbrDongHo">${title}</label></th>
	    <td>
	      <form:input  path="lnbrDongHo" size="20" maxlength="20" id="lnbrDongHo"/>
	      <form:errors path="lnbrDongHo"/>
	    </td>
	  </tr>
	  <input type=hidden name="rdmnCode" id="rdmnCode" value="0"/>
	  <input type=hidden name="rdmn" id="rdmn" value="0"/>
  </c:if>
  <c:if test="${searchList == '2'}">
  	  <!-- 우편번호  -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.zip"/></c:set>
	  <c:set var="address"><spring:message code="comSymCcmZip.zipVO.rdmnSearch"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="zip">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="zip" size="5" maxlength="5" id="zip" name="zip"/>
	      <form:errors path="zip"/> <input type="button" class="btn_s" onClick="goAddSearch();" value="${address }"/>
	    </td>
	  </tr>
	  <!-- 도로명코드  -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.rdmnCode"/></c:set>
  	  <tr>
	    <th class="ic_none"><label for="rdmnCode">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="rdmnCode" size="12" maxlength="12" id="rdmnCode"/>
	      <form:errors path="rdmnCode"/>
	    </td>
	  </tr>
	  <!-- 시도명  -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="ctprvnNm">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="ctprvnNm" size="20" maxlength="20" id="ctprvnNm"/>
	      <form:errors path="ctprvnNm"/>
	    </td>
	  </tr>
	  <!-- 시군구명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.signguNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="signguNm">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="signguNm" size="20" maxlength="20" id="signguNm"/>
	      <form:errors path="signguNm"/>
	    </td>
	  </tr>
	  <!-- 도로명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.rdmn"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="rdmn">${title} <span class="pilsu">*</label></th>
	    <td>
	      <form:input  path="rdmn" size="60" maxlength="60" id="rdmn"/>
	      <form:errors path="rdmn"/>
	    </td>
	  </tr>
	  <!-- 건물번호본번 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.bdnbrMnnm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="bdnbrMnnm">${title}</label></th>
	    <td>
	      <form:input  path="bdnbrMnnm" size="5" maxlength="5" id="bdnbrMnnm"/>
	      <form:errors path="bdnbrMnnm"/>
	    </td>
	  </tr>
	  <!-- 건물번호부번 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.bdnbrSlno"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="bdnbrSlno">${title}</label></th>
	    <td>
	      <form:input  path="bdnbrSlno" size="5" maxlength="5" id="bdnbrSlno"/>
	      <form:errors path="bdnbrSlno"/>
	    </td>
	  </tr>
	  <!-- 건물명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.buldNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="buldNm">${title}</label></th>
	    <td>
	      <form:input  path="buldNm" size="60" maxlength="60" id="buldNm"/>
	      <form:errors path="buldNm"/>
	    </td>
	  </tr>
	  <!-- 상세건물명 -->
	  <c:set var="title"><spring:message code="comSymCcmZip.zipVO.detailBuldNm"/></c:set>
	  <tr>
	    <th class="ic_none"><label for="detailBuldNm">${title}</label></th>
	    <td>
	      <form:input  path="detailBuldNm" size="60" maxlength="60" id="detailBuldNm"/>
	      <form:errors path="detailBuldNm"/>
	    </td>
	  </tr>
	  <input type=hidden name="emdNm" id="emdNm" value="0"/>
  </c:if>
  </tbody>
</table>

<!-- 목록/저장버튼  -->
<div class="txt-cnt mt20">
  <input type="submit" class="btnStyle02 bg_gray" value="<spring:message code="button.list" />" onclick="fn_egov_list_Zip(); return false;"> <!-- 목록 -->
  <input type="submit" class="btnStyle02" value="<spring:message code="button.create" />" onclick="fn_egov_regist_Zip(document.zip); return false;"></span> <!-- 등록 -->
</div><div style="clear:both;"></div>

<input name="cmd" type="hidden" value="<c:out value='save'/>">
<input name="searchList" type="hidden" value="<c:out value='${searchList}'/>">
</form:form>
</div>
</body>
</html>
