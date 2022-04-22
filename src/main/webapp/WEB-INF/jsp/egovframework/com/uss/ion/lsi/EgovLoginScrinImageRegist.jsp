<%--
/**
 * @Class Name  : EgovLoginScrinImageRegist.java
 * @Description : EgovLoginScrinImageRegist.jsp
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
<title><spring:message code="ussIonLsi.loginScrinImageRegist.loginScrinImageRegist"/></title><!-- 로그인화면이미지 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="loginScrinImage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectLoginScrinImageList() {
    var varFrom = document.getElementById("loginScrinImage");
    varFrom.action = "<c:url value='/uss/ion/lsi/selectLoginScrinImageList.do'/>";
    varFrom.submit();       
}

function fncLoginScrinImageInsert() {

    var varFrom = document.getElementById("loginScrinImage");
    varFrom.action = "<c:url value='/uss/ion/lsi/addLoginScrinImage.do'/>";

    if(confirm("<spring:message code="ussIonLsi.loginScrinImageRegist.saveImage"/>")){/* 저장 하시겠습니까? */
        if(!validateLoginScrinImage(varFrom)){           
            return;
        }else{
            if(varFrom.loginImage.value != '') {
                varFrom.submit();
            } else {
                alert("<spring:message code="ussIonLsi.loginScrinImageRegist.ImageReq"/>");/* 이미지는 필수 입력값입니다.*/      
                return;
            }
        } 
    }
}

function fncOnChangeImage() {
    var varFrom = document.getElementById("loginScrinImage");
    varFrom.loginImage.value = varFrom.file_1.value;
}

</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->
<form:form modelAttribute="loginScrinImage" method="post" action="${pageContext.request.contextPath}/uss/ion/lsi/addLoginScrinImage.do' />" enctype="multipart/form-data"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonLsi.loginScrinImageRegist.loginScrinImageRegist"/></h2><!-- 로그인화면이미지 등록 -->

	<!-- 등록폼 -->

<input type="hidden" name="posblAtchFileNumber" value="1" >
<input type="hidden" name="image" >
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageRegist.mainImageId"/> <span class="pilsu">*</span></th><!-- 이미지ID -->
			<td class="left">
			    <input id="imageId" type="text" name="imageId" value="<c:out value='${loginScrinImage.imageId}'/>" title="<spring:message code="ussIonLsi.loginScrinImageRegist.mainImageId"/>" size="20" readonly="readonly" style="width:188px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageRegist.mainImageNm"/> <span class="pilsu">*</span></th><!-- 이미지명 -->
			<td class="left">
			    <input id="imageNm" type="text" name="imageNm" value="<c:out value='${loginScrinImage.imageNm}'/>" title="<spring:message code="ussIonLsi.loginScrinImageRegist.mainImageNm"/>" maxLength="10" style="width:188px" />&nbsp;<form:errors path="imageNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageRegist.mainImage"/> <span class="pilsu">*</span></th><!-- 이미지 -->
			<td class="left">
			    <div class="egov_file_box" style="display:inline-block">
				<label for="egovfile_0" id="file_label"><spring:message code="title.attachedFileSelect"/></label><!-- 파일선택 --> 
				<input type="file" name="file_1" id="egovfile_0" title="<spring:message code="title.attachedFileSelect"/>" onchange="fncOnChangeImage();" />
				<input name="loginImage" id="loginImage" type="text" value="" maxLength="30" readonly="readonly" style="width:525px" />
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageRegist.mainImageDc"/></th><!-- 이미지설명 -->
			<td class="left">
			    <input id="imageDc" type="text" name="imageDc" value="<c:out value='${loginScrinImage.imageDc}'/>" title="<spring:message code="ussIonLsi.loginScrinImageRegist.mainImageDc"/>" maxLength="100" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageRegist.mainImageReflctAt"/> <span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
			    <select name="reflctAt" id="reflctAt" title="<spring:message code="ussIonLsi.loginScrinImageRegist.mainImageReflctAt"/>">
					<option value="Y" <c:if test="${loginScrinImage.reflctAt == 'Y'}">selected</c:if> >Y</option>
					<option value="N" <c:if test="${loginScrinImage.reflctAt == 'N'}">selected</c:if> >N</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonLsi.loginScrinImageRegist.mainImageregDate"/> <span class="pilsu">*</span></th><!-- 등록일시 -->
			<td class="left">
			    <input id="regDate" type="text" name="regDate" value="<c:out value="${loginScrinImage.regDate}"/>" title="<spring:message code="ussIonLsi.loginScrinImageRegist.mainImageregDate"/>" size="20" readonly="readonly" style="width:18 8px" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncLoginScrinImageInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/lsi/selectLoginScrinImageList.do'/>?pageIndex=<c:out value='${loginScrinImageVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${loginScrinImageVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectLoginScrinImageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${loginScrinImageVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${loginScrinImageVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${loginScrinImageVO.pageIndex}'/>">
<!-- 검색조건 유지 -->
</form:form>
</body>
</html>

