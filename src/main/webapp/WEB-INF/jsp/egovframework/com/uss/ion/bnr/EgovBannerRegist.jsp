<%--
/**
 * @Class Name  : EgovBannerRegist.jsp
 * @Description : EgovBannerRegist.jsp
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
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussIonBnr.bannerRegist.bannerRegist"/></title><!-- 배너관리 등록 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="banner" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectBannerList() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/uss/ion/bnr/selectBannerList.do'/>";
    varFrom.submit();       
}

function fncBannerInsert() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/uss/ion/bnr/addBanner.do'/>";

    if(confirm("<spring:message code="ussIonBnr.bannerRegist.saveImage"/>")){/* 저장 하시겠습니까? */
        if(!validateBanner(varFrom)){           
            return;
        }else{
            if(varFrom.bannerImage.value != '') {
                varFrom.submit();
            } else {
                alert("<spring:message code="ussIonBnr.bannerRegist.ImageReq"/>");/* 배너이미지는 필수 입력값입니다. */
                return;
            }
        } 
    }
}

function fncOnChangeImage() {
	var varFrom = document.getElementById("banner");
	varFrom.bannerImage.value = varFrom.file_1.value;
}

function fncBannerDelete() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/uss/ion/bnr/removeBanner.do'/>";
    if(confirm("<spring:message code="ussIonBnr.bannerRegist.deleteImage"/>")){/* 삭제 하시겠습니까? */
        varFrom.submit();
    }
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form modelAttribute="banner" method="post" action="${pageContext.request.contextPath}/uss/ion/bnr/addBanner.do" enctype="multipart/form-data"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonBnr.bannerRegist.bannerRegist"/></h2><!-- 배너관리 등록 -->

	<!-- 등록폼 -->
	<input type="hidden" name="posblAtchFileNumber" value="1" >
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.bannerId"/> <span class="pilsu">*</span></th><!-- 배너ID -->
			<td class="left">
				<input id="bannerId" type="text" name="bannerId" value="<c:out value='${banner.bannerId}'/>" title="<spring:message code="ussIonBnr.bannerRegist.bannerId"/>" readonly="readonly" style="width:188px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.bannerNm"/> <span class="pilsu">*</span></th><!-- 배너명 -->
			<td class="left">
				<input id="bannerNm" type="text" name="bannerNm" value="<c:out value='${banner.bannerNm}'/>" title="<spring:message code="ussIonBnr.bannerRegist.bannerNm"/>" maxLength="10" style="width:188px" />
				<form:errors path="bannerNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.linkUrl"/> <span class="pilsu">*</span></th><!-- 링크URL -->
			<td class="left">
				<input id="linkUrl" type="text" name="linkUrl" value="<c:out value='${banner.linkUrl}'/>" title="<spring:message code="ussIonBnr.bannerRegist.linkUrl"/>" maxLength="255" />
				<form:errors path="linkUrl" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.bannerImage"/> <span class="pilsu">*</span></th><!-- 배너이미지 -->
			<td class="left">
				<div class="egov_file_box" style="display:inline-block">
				<label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label> <!-- 파일선택 -->
				<input type="file" name="file_1" id="egovfile_0" title="<spring:message code="ussIonBnr.bannerRegist.bannerImage"/>" onchange="fncOnChangeImage();" />
				</div><input name="bannerImage" id="bannerImage" type="text" title="<spring:message code="ussIonBnr.bannerRegist.bannerImage"/>" value="<c:out value="${banner.bannerImage}"/>" maxLength="30" readonly="readonly" style="width:525px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.bannerDc"/> </th><!-- 배너설명 -->
			<td class="left">
				<input id="bannerDc" type="text" name="bannerDc" value="<c:out value='${banner.bannerDc}'/>" title="<spring:message code="ussIonBnr.bannerRegist.bannerDc"/>" maxLength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.sortOrdr"/> <span class="pilsu">*</span></th><!-- 정렬순서 -->
			<td class="left">
				<input id="sortOrdr" type="text" name="sortOrdr" title="<spring:message code="ussIonBnr.bannerRegist.sortOrdr"/>" value="<c:out value='${banner.sortOrdr}'/>" maxLength="5" style="width:68px" />
				<form:errors path="sortOrdr" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.reflctAtt"/> <span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
				<select id="reflctAt" name="reflctAt" title="<spring:message code="ussIonBnr.bannerRegist.reflctAtt"/>">
					<option value="Y" <c:if test="${banner.reflctAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${banner.reflctAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonBnr.bannerRegist.regDate"/> <span class="pilsu">*</span></th><!-- 등록일시 -->
			<td class="left">
				<input id="regDate" type="text" name="regDate" value="<c:out value="${banner.regDate}"/>" title="<spring:message code="ussIonBnr.bannerRegist.regDate"/>" maxLength="20" readonly="readonly" style="width:128px" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fncBannerInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/bnr/selectBannerList.do'/>?pageIndex=<c:out value='${bannerVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${bannerVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectBannerList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${bannerVO.searchCondition}'/>" >
<input type="hidden" name="searchKeyword" value="<c:out value='${bannerVO.searchKeyword}'/>" >
<input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>" >

<!-- 검색조건 유지 -->
</form:form>
</body>
</html>

