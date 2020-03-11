<%--
/**
 * @Class Name  : EgovMainImageUpdt.java
 * @Description : EgovMainImageUpdt jsp
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="uss.ion.msi.mainImageUpdt.mainImageUpdt" /></title><!-- 메인화면이미지 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mainImage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectMainImageList() {
    var varFrom = document.getElementById("mainImage");
    varFrom.action = "<c:url value='/uss/ion/msi/selectMainImageList.do'/>";
    varFrom.submit();       
}

function fncMainImageUpdate() {
    var varFrom = document.getElementById("mainImage");
    varFrom.action = "<c:url value='/uss/ion/msi/updtMainImage.do'/>";

    if(confirm("<spring:message code="uss.ion.msi.mainImageUpdt.saveImage" />")){/* 저장 하시겠습니까? */
        if(!validateMainImage(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
    }
}

function fncMainImageDelete() {
    var varFrom = document.getElementById("mainImage");
    varFrom.action = "<c:url value='/uss/ion/msi/removeMainImage.do'/>";
    if(confirm("<spring:message code="uss.ion.msi.mainImageUpdt.deleteImage" />")){/* 삭제 하시겠습니까 */
        varFrom.submit();
    }
}

function fncOnChangeImage() {
    var varFrom = document.getElementById("mainImage");
    varFrom.mainImage.value = varFrom.file_1.value;
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.msi.mainImageUpdt.mainImageUpdt" /></h2><!-- 메인화면이미지 수정 -->

	<!-- 등록폼 -->
<form:form commandName="mainImage" method="post" action="${pageContext.request.contextPath}/uss/ion/msi/updtMainImage.do' />" enctype="multipart/form-data">  
<input type="hidden" name="posblAtchFileNumber" value="1" >
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageUpdt.mainImageId" /> <span class="pilsu">*</span></th><!-- 이미지ID -->
			<td class="left">
			    <input name="imageId" id="imageId" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImageId" />" type="text" value="<c:out value='${mainImage.imageId}'/>" readonly="readonly" style="width:188px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageUpdt.mainImageNm" /> <span class="pilsu">*</span></th><!-- 이미지명 -->
			<td class="left">
			    <input name="imageNm" id="imageNm" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImageNm" />" type="text" value="<c:out value='${mainImage.imageNm}'/>" maxLength="10" size="30" style="width:188px" />&nbsp;<form:errors path="imageNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageUpdt.mainImage" /> <span class="pilsu">*</span></th><!-- 이미지 -->
			<td class="left">
			    <div class="egov_file_box" style="display:inline-block">
				<label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label> 
				<input type="file" name="file_1" id="egovfile_0" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImage" />" onchange="fncOnChangeImage();"/> 
				</div><input name="mainImage" id="mainImage" type="text" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImage" />" value="<c:out value="${mainImage.image}"/>" maxLength="30" readonly="readonly" style="width:525px" /><!-- 이미지 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageUpdt.mainImageDc" /></th><!-- 이미지설명 -->
			<td class="left">
			    <input name="imageDc" id="imageDc" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImageDc" />" type="text" value="<c:out value='${mainImage.imageDc}'/>" maxLength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageUpdt.mainImageReflctAt" /> <span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
			    <select name="reflctAt" id="reflctAt" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImageReflctAt" />">
					<option value="Y" <c:if test="${mainImage.reflctAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${mainImage.reflctAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.msi.mainImageUpdt.mainImageregDate" /></th><!-- 등록일시 -->
			<td class="left">
			    <input name="regDate" id="regDate" title="<spring:message code="uss.ion.msi.mainImageUpdt.mainImageregDate" />" type="text" value="<c:out value="${fn:substring(mainImage.regDate,0,19)}"/>" maxLength="50" readonly="readonly" style="width:188px" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncMainImageUpdate(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/msi/removeMainImage.do'/>?imageId=<c:out value='${mainImageVO.imageId}'/>" onclick="fncMainImageDelete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/ion/msi/selectMainImageList.do'/>?pageIndex=<c:out value='${mainImageVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${mainImageVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectMainImageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${mainImageVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${mainImageVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${mainImageVO.pageIndex}'/>">
</form:form>
</body>
</html>

