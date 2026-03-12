<%--
/**
 * @Class Name  : EgovMainImageRegist.jsp
 * @Description : EgovMainImageRegist.jsp
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
<title><spring:message code="uss.ion.msi.mainImageRegist.mainImageRegist"/></title><!-- 메인화면이미지 등록 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">

function fncSelectMainImageList() {
    var varFrom = document.getElementById("mainImageVO") || document.forms["mainImageVO"];
    varFrom.action = "<c:url value='/uss/ion/msi/selectMainImageList.do'/>";
    varFrom.submit();
}

function fncMainImageInsert() {
    var varFrom = document.getElementById("mainImageVO") || document.forms["mainImageVO"];
    if (!varFrom) return;
    varFrom.action = "<c:url value='/uss/ion/msi/addMainImage.do'/>";
    if (!validateMainImage(varFrom)) {
        return;
    }
    if(confirm("<spring:message code="uss.ion.msi.mainImageRegist.saveImage"/>")){/* 저장 하시겠습니까? */
		varFrom.submit();
    }
}

function fncOnChangeImage() {
	var varFrom = document.getElementById("mainImageVO") || document.forms["mainImageVO"];
	varFrom.image.value = varFrom.file_1.value;
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form id="mainImageVO" modelAttribute="mainImageVO" method="post" action="${pageContext.request.contextPath}/uss/ion/msi/addMainImage.do" enctype="multipart/form-data">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.msi.mainImageRegist.mainImageRegist"/></h2><!-- 메인화면이미지 등록 -->

	<!-- 등록폼 -->
	<input type="hidden" name="posblAtchFileNumber" value="1" >
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageRegist.mainImageNm"/> <span class="pilsu">*</span></th><!-- 이미지명 -->
			<td class="left">
				<input id="imageNm" type="text" name="imageNm" value="<c:out value='${mainImageVO.imageNm}'/>" title="<spring:message code="uss.ion.msi.mainImageRegist.mainImageNm"/>" maxLength="10" style="width:188px" />
				<div><form:errors path="imageNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageRegist.mainImage"/> <span class="pilsu">*</span></th><!-- 이미지 -->
			<td class="left">
				<div class="egov_file_box" style="display:inline-block">
				<label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label> <!-- 파일선택 -->
				<input type="file" name="file_1" id="egovfile_0" title="<spring:message code="uss.ion.msi.mainImageRegist.mainImage"/>" onchange="fncOnChangeImage();" />
				</div><input name="image" id="image" type="text" title="<spring:message code="uss.ion.msi.mainImageRegist.mainImage"/>" value="<c:out value="${mainImageVO.image}"/>" maxLength="30" readonly="readonly" style="width:525px" />
				<div><form:errors path="image" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageRegist.mainImageDc"/> </th><!-- 이미지설명 -->
			<td class="left">
				<input id="imageDc" type="text" name="imageDc" value="<c:out value='${mainImageVO.imageDc}'/>" title="<spring:message code="uss.ion.msi.mainImageRegist.mainImageDc"/>" maxLength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageRegist.mainImageReflctAt"/> <span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
				<select id="reflctAt" name="reflctAt" title="<spring:message code="uss.ion.msi.mainImageRegist.mainImageReflctAt"/>">
					<option value="Y" <c:if test="${mainImageVO.reflctAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${mainImageVO.reflctAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fncMainImageInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/msi/selectMainImageList.do'/>?pageIndex=<c:out value='${mainImageVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${mainImageVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectMainImageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${mainImageVO.searchCondition}'/>" >
<input type="hidden" name="searchKeyword" value="<c:out value='${mainImageVO.searchKeyword}'/>" >
<input type="hidden" name="pageIndex" value="<c:out value='${mainImageVO.pageIndex}'/>" >

<!-- 검색조건 유지 -->
</form:form>
</body>
</html>
