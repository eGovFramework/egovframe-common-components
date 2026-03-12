<%--
/**
 * @Class Name  : EgovLoginScrinImageUpdt.java
 * @Description : EgovLoginScrinImageUpdt.jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 * @ 2018.09.17    이정은                         공통컴포넌트 3.8 개선
 *
 *  @author lee.m.j
 *  @since 2009.08.11
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
<title><spring:message code="ussIonLsi.loginScrinImageUpdt.loginScrinImageUpdt"/></title><!-- 로그인화면이미지 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">

function fncSelectLoginScrinImageList() {
    var varFrom = document.getElementById("loginScrinImageVO");
    varFrom.action = "<c:url value='/uss/ion/lsi/selectLoginScrinImageList.do'/>";
    varFrom.submit();
}

function fncLoginScrinImageUpdate() {
    var varFrom = document.getElementById("loginScrinImageVO");
    varFrom.action = "<c:url value='/uss/ion/lsi/updtLoginScrinImage.do'/>";

    if(!validateLoginScrinImage(varFrom)){
        return;
    }

    if(confirm("<spring:message code="ussIonLsi.loginScrinImageUpdt.saveImage"/>")){/* 저장 하시겠습니까? */
        varFrom.submit();
    }
}

function fncLoginScrinImageDelete() {
    var varFrom = document.getElementById("loginScrinImageVO");
    varFrom.action = "<c:url value='/uss/ion/lsi/removeLoginScrinImage.do'/>";
    if(confirm("<spring:message code="ussIonLsi.loginScrinImageUpdt.deleteImage"/>")){/* 삭제 하시겠습니까? */
        varFrom.submit();
    }
}

function fncOnChangeImage() {
	var varFrom = document.getElementById("loginScrinImageVO");
	varFrom.image.value = varFrom.file_1.value;
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form modelAttribute="loginScrinImageVO" id="loginScrinImageVO" method="post" action="${pageContext.request.contextPath}/uss/ion/lsi/updtLoginScrinImage.do" enctype="multipart/form-data">
<input type="hidden" name="posblAtchFileNumber" value="1" >
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonLsi.loginScrinImageUpdt.loginScrinImageUpdt"/></h2><!-- 로그인화면이미지 수정 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="width:84%" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageId"/> <span class="pilsu">*</span></th><!-- 이미지ID -->
			<td class="left"><c:out value="${loginScrinImageVO.imageId}"/></td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageNm"/> <span class="pilsu">*</span></th><!-- 이미지명 -->
			<td class="left">
			    <form:input path="imageNm" id="imageNm" title='<spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageNm"/>' maxLength="10" style="width:188px" />
			    <div><form:errors path="imageNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageUpdt.mainImage"/> <span class="pilsu">*</span></th><!-- 이미지 -->
			<td class="left">
			    <div class="egov_file_box" style="display:inline-block">
				<label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label> <!-- 파일선택 -->
				<input type="file" name="file_1" id="egovfile_0" title='<spring:message code="ussIonLsi.loginScrinImageUpdt.mainImage"/>' onchange="fncOnChangeImage();" /><!-- 파일선택 -->
				</div><input name="image" id="image" title='<spring:message code="ussIonLsi.loginScrinImageUpdt.mainImage"/>' type="text" value='<c:out value="${loginScrinImageVO.image}"/>' maxLength="30" readonly="readonly" style="width:525px" />
				<div><form:errors path="image" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageDc"/></th><!-- 이미지설명 -->
			<td class="left">
			    <input name="imageDc" id="imageDc" title='<spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageDc"/>' type="text" value="<c:out value='${loginScrinImageVO.imageDc}'/>" maxLength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageReflctAt"/> <span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
			    <select name="reflctAt" id="reflctAt" title='<spring:message code="ussIonLsi.loginScrinImageUpdt.mainImageReflctAt"/>'>
					<option value="Y" <c:if test="${loginScrinImageVO.reflctAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${loginScrinImageVO.reflctAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncLoginScrinImageUpdate(); return false;" />
		<span class="btn_s"><a href="#" onclick="fncLoginScrinImageDelete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/lsi/selectLoginScrinImageList.do'/>?pageIndex=<c:out value='${loginScrinImageVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${loginScrinImageVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectLoginScrinImageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="imageId" value="<c:out value='${loginScrinImageVO.imageId}'/>">
<input type="hidden" name="searchCondition" value="<c:out value='${loginScrinImageVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${loginScrinImageVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${loginScrinImageVO.pageIndex}'/>">
<!-- 기존 이미지 정보 유지 (파일을 선택하지 않은 경우) -->
<input type="hidden" name="image" id="hiddenImage" value="<c:out value='${loginScrinImageVO.image}'/>">
<input type="hidden" name="imageFile" id="hiddenImageFile" value="<c:out value='${loginScrinImageVO.imageFile}'/>">
</form:form>
</body>
</html>
