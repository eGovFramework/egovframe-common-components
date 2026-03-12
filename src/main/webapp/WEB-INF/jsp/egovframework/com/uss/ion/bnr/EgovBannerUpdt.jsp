<%--
/**
 * @Class Name  : EgovBannerUpdt.jsp
 * @Description : EgovBannerUpdt.jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 * @ 2018.08.30    이정은               공통컴포넌트 3.8 개선 
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonBnr.bannerUpdt.bannerUpdt"/></title><!-- 배너관리 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">

function fncSelectBannerList() {
    var varFrom = document.getElementById("bannerVO") || document.forms["bannerVO"];
    varFrom.action = "<c:url value='/uss/ion/bnr/selectBannerList.do'/>";
    varFrom.submit();
}

function fncBannerUpdate() {
    var varFrom = document.getElementById("bannerVO") || document.forms["bannerVO"];
    varFrom.action = "<c:url value='/uss/ion/bnr/updtBanner.do'/>";
	if(!validateBanner(varFrom)){
		return;
	}
    if(confirm("<spring:message code="ussIonBnr.bannerUpdt.saveImage"/>")){/* 저장 하시겠습니까? */
		varFrom.submit();
    }
}

function fncBannerDelete() {
    var varFrom = document.getElementById("bannerVO") || document.forms["bannerVO"];
    varFrom.action = "<c:url value='/uss/ion/bnr/removeBanner.do'/>";
    if(confirm("<spring:message code="ussIonBnr.bannerUpdt.deleteImage"/>")){/* 삭제 하시겠습니까? */
        varFrom.submit();
    }
}

function fncOnChangeImage() {
	var varFrom = document.getElementById("bannerVO") || document.forms["bannerVO"];
	varFrom.bannerImage.value = varFrom.file_1.value;
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonBnr.bannerUpdt.bannerUpdt"/></h2>

	<!-- 등록폼 -->
<form:form id="bannerVO" modelAttribute="bannerVO" method="post" action="${pageContext.request.contextPath}/uss/ion/bnr/updtBanner.do" enctype="multipart/form-data"> 
<input type="hidden" name="posblAtchFileNumber" value="1"  >
<input type="hidden" name="bannerId" value="<c:out value='${bannerVO.bannerId}'/>" />
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.bannerId"/></th><!-- 배너ID -->
			<td class="left"><c:out value='${bannerVO.bannerId}'/></td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.bannerNm"/> <span class="pilsu">*</span></th><!-- 배너명 -->
			<td class="left">
			    <input name="bannerNm" id="bannerNm" title="<spring:message code="ussIonBnr.bannerUpdt.bannerNm"/>" type="text" value="<c:out value='${bannerVO.bannerNm}'/>" maxLength="10" style="width:188px" />
				<div><form:errors path="bannerNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.linkUrl"/> <span class="pilsu">*</span></th><!-- 링크URL -->
			<td class="left">
			    <input name="linkUrl" id="linkUrl" title="<spring:message code="ussIonBnr.bannerUpdt.linkUrl"/>" type="text" value="<c:out value='${bannerVO.linkUrl}'/>" maxLength="255" /><div><form:errors path="linkUrl" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.bannerImage"/> <span class="pilsu">*</span></th><!-- 배너이미지 -->
			<td class="left">
			    <div class="egov_file_box" style="display:inline-block">
				<label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label><!--  파일선택 -->
				<input type="file" name="file_1" id="egovfile_0" title="<spring:message code="ussIonBnr.bannerUpdt.bannerImage"/>" onchange="fncOnChangeImage();"/> 
				</div>
        		<input name="bannerImage" id="bannerImage" type="text" title="<spring:message code="ussIonBnr.bannerUpdt.bannerImage"/>" value="<c:out value="${bannerVO.bannerImage}"/>" maxLength="30" readonly="readonly" style="width:525px" />
				<div><form:errors path="bannerImage" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.bannerDc"/></th><!-- 배너설명 -->
			<td class="left">
			    <input name="bannerDc" id="bannerDc" title="<spring:message code="ussIonBnr.bannerUpdt.bannerDc"/>" type="text" value="<c:out value='${bannerVO.bannerDc}'/>" maxLength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.sortOrdr"/> <span class="pilsu">*</span></th><!-- 정렬순서 -->
			<td class="left">
			    <input name="sortOrdr" id="sortOrdr" title="<spring:message code="ussIonBnr.bannerUpdt.sortOrdr"/>" type="text" value="<c:out value='${bannerVO.sortOrdr}'/>" maxLength="5" style="width:68px" oninput="this.value=this.value.replace(/[^0-9]/g,'');" /><div><form:errors path="sortOrdr" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerUpdt.reflctAtt"/> <span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
				<select name="reflctAt" id="reflctAt" title="<spring:message code="ussIonBnr.bannerUpdt.reflctAtt"/>">
					<option value="Y" <c:if test="${bannerVO.reflctAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${bannerVO.reflctAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncBannerUpdate(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnr/removeBanner.do'/>?bannerId=<c:out value='${bannerVO.bannerId}'/>" onclick="fncBannerDelete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnr/selectBannerList.do'/>?pageIndex=<c:out value='${bannerVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${bannerVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectBannerList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${bannerVO.searchCondition}'/>" >
<input type="hidden" name="searchKeyword" value="<c:out value='${bannerVO.searchKeyword}'/>" >
<input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>" >
</form:form>
</body>
</html>

