<%--
/**
 * @Class Name  : EgovIntnetSvcGuidanceRegist.jsp
 * @Description : EgovIntnetSvcGuidanceRegist.jsp
 * @Modification Information
 * @
 * @ 수정일                수정자           수정내용
 * @ ----------   --------   ---------------------------
 * @ 2009.02.01   lee.m.j    최초 생성
 * @ 2018.08.20   이정은            공통컴포넌트 3.8 개선
 * @ 2019.12.10   신용호            KISA 보안약점 조치 (HTMLArea Editor삭제)
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
<title><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegist" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="intnetSvcGuidance" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록
 ******************************************************** */
function fncSelectIntnetSvcGuidanceList() {
    var varFrom = document.getElementById("intnetSvcGuidance");
    varFrom.action = "<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>";
    varFrom.submit();
}
/* ********************************************************
 * 저장
 ******************************************************** */
function fncIntnetSvcGuidanceInsert() {

	document.intnetSvcGuidance.onsubmit();

    var varFrom = document.getElementById("intnetSvcGuidance");
    varFrom.action = "<c:url value='/uss/ion/isg/addIntnetSvcGuidance.do'/>";

    if(confirm("<spring:message code="common.save.msg" />")){
        if(!validateIntnetSvcGuidance(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

</script>

</head>

<body>

<%-- noscript 태그 --%>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다 -->

<form:form modelAttribute="intnetSvcGuidance" name="intnetSvcGuidance" method="post" action="${pageContext.request.contextPath}/uss/ion/isg/addIntnetSvcGuidance.do" >

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegist"/></h2><!-- 인터넷서비스안내 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceId" /><span class="pilsu">*</span></th><!-- 인터넷서비스ID -->
			<td class="left">
			    <input name="intnetSvcId" id="intnetSvcId" title="<spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceId" />" type="text" value="<c:out value='${intnetSvcGuidance.intnetSvcId}'/>" class="readOnlyClass" readonly="readonly" style="width:150px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceNm" /> <span class="pilsu">*</span></th><!-- 인터넷서비스명 -->
			<td class="left">
			    <input name="intnetSvcNm" id="intnetSvcNm" title="<spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceNm" />" type="text" value="<c:out value='${intnetSvcGuidance.intnetSvcNm}'/>" maxLength="20" />&nbsp;<form:errors path="intnetSvcNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceDc" /><span class="pilsu">*</span></th><!-- 인터넷서비스설명 -->
			<td class="left">
			    <textarea id="intnetSvcDc" name="intnetSvcDc" title="<spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceDc" />" class="textarea" rows="15" cols="80" style="height:356px"><c:out value="${intnetSvcGuidance.intnetSvcDc}" escapeXml="false" /></textarea><form:errors path="intnetSvcDc" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceReflctAt" /><span class="pilsu">*</span></th><!-- 반영여부 -->
			<td class="left">
			    <select name="reflctAt" id="reflctAt" title="<spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceReflctAt" />">
		          <option value="Y" <c:if test="${intnetSvcGuidance.reflctAt == 'Y'}">selected</c:if> >Y</option>
		          <option value="N" <c:if test="${intnetSvcGuidance.reflctAt == 'N'}">selected</c:if> >N</option>
		      </select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegDate" /><span class="pilsu">*</span></th><!-- 등록일시 -->
			<td class="left">
			    <input name="regDate" id="regDate" type="text" value="<c:out value="${intnetSvcGuidance.regDate}"/>" maxLength="50" class="readOnlyClass" readonly="readonly" title="<spring:message code="uss.ion.isg.intnetSvcGuidanceRegist.intnetSvcGuidanceRegDate" />" style="width:150px" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncIntnetSvcGuidanceInsert(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/isg/selectIntnetSvcGuidanceList.do'/>?pageIndex=<c:out value='${intnetSvcGuidanceVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${intnetSvcGuidanceVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectIntnetSvcGuidanceList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${intnetSvcGuidanceVO.searchCondition}'/>">
<input type="hidden" name="searchKeyword" value="<c:out value='${intnetSvcGuidanceVO.searchKeyword}'/>">
<input type="hidden" name="pageIndex" value="<c:out value='${intnetSvcGuidanceVO.pageIndex}'/>">
<!-- 검색조건 유지 -->
</form:form>

</body>
</html>

