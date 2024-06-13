<%
/**
 * @Class Name : EgovConfmPopup.java
 * @Description : EgovConfmPopup jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.08.02    이      용                최초 생성
 * @ 2018.09.12    최 두 영                 퍼블리싱 점검&다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.02
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtC" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	*승인 처리화면
	******************************************************** */
	function fncPopUpConfm() {
		var varFrom = document.all["targetForm"];
		getDialogArguments();
		var opener = parent.window.dialogArguments;
		if(opener[2] != '' && opener[0].document.getElementById(opener[2]) != null){
			opener[0].document.getElementById(opener[2]).value = varFrom.returnResn.value;
		}
		if(opener[3] != '' && opener[0].document.getElementById(opener[3]) != null){
			opener[0].document.getElementById(opener[3]).value = "C";
		}
		if(confirm("<spring:message code="common.acknowledgement.msg" />")){ /* 승인하시겠습니까? */
			opener[0].fncConfm();
			parent.window.close();
		}
	}
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtC" /></h2><!-- 승인 -->

	<form id="targetForm" name="targetForm" method="post" action="#LINK">
	<input type="hidden" name="cmd">
	<input type="hidden" name="checkedEventRceptForConfm">

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonIsm.infrmlSanctnDetail.opinion" /> <span class="pilsu">*</span></th><!-- 의견 -->
			<td class="left">
			    <textarea id="returnResn" name="returnResn" class="txaClass" rows="4" cols="70" title="<spring:message code="comUssIonIsm.infrmlSanctnDetail.opinion" />"><c:out value='${rwardManageVO.returnResn}'/></textarea>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtC" />" onclick="fncPopUpConfm(); return false;" />
		<span class="btn_s"><a href="#LINK" onclick="parent.window.close(); return false;"><spring:message code="button.close" /></a></span><!-- 닫기 -->
	</div>
	<div style="clear:both;"></div>
	</form>
</div>
</body>
</html>