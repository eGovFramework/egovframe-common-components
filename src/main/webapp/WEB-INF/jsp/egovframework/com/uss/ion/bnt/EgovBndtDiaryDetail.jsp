<%
/**
 * @Class Name : EgovBndtDiaryDetail.java
 * @Description : EgovBndtDiaryDetail jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.09.27    최 두 영                 다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtDiaryDetail.title"/></title><!-- 당직일지 상세 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bndtDiary" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>";
}

/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fncEgovBndtDiary() {
		var varFrom = document.getElementById("bndtDiary");
		varFrom.cmd.value  = "updt";
		varFrom.action = "<c:url value='/uss/ion/bnt/selectBndtDiary.do'/>";
		varFrom.submit();   
}

/* ********************************************************
 * 삭제처리화면
 ******************************************************** */
function fncDeleteBndtDiary() {
    var varFrom = document.getElementById("bndtDiary");
    varFrom.action = "<c:url value='/uss/ion/bnt/deleteBndtDiary.do'/>";
    if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
        varFrom.submit();
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="bndtDiary" name="bndtDiary" method="post" action="${pageContext.request.contextPath}/uss/ion/bnt/selectBndtDiary.do">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonBnt.common.submit"/>" title="<spring:message code="comUssIonBnt.common.submit"/>"></div><!-- 전송 -->
<input name="cmd" type="hidden" value="<c:out value='detail'/>"/>
<input name="bndtId" type="hidden" value="<c:out value='${bndtDiaryVO.bndtId}'/>"/>
<input name="bndtDe" type="hidden" value="<c:out value='${bndtDiaryVO.bndtDe}'/>"/>

<div class="board">
	<h1><spring:message code="comUssIonBnt.bndtDiaryDetail.title"/></h1><!-- 제목아이콘이미지 / 당직일지 상세-->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<span class="btn_b"><a href="<c:url value='/uss/ion/bnt/selectBndtDiary.do'/>?cmd=updt&bndtId=<c:out value='${bndtDiaryVO.bndtId}'/>&bndtDe=<c:out value='${bndtCeckManageVO.bndtDe}'/>" onclick="fncEgovBndtDiary(); return false;"><spring:message code="button.update" /></a></span>
				<span class="btn_b"><a href="<c:url value='/uss/ion/bnt/deleteBndtDiary.do'/>?bndtId=<c:out value='${bndtDiaryVO.bndtId}'/>&bndtDe=<c:out value='${bndtDiaryVO.bndtDe}'/>" onclick="fncDeleteBndtDiary(); return false;"><spring:message code="button.delete" /></a></span>
				<span class="btn_b"><a href="<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>" onclick="fncBndtManageList(); return false;"><spring:message code="button.list" /></a></span>
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:40%" />
			<col style="width:30%" />
			<col style="width:30%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCdNm"/></th><!-- 당직체크목록 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCd.good"/></th><!-- 양호 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCd.bad"/></th><!-- 불량 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bndtDiaryList}" var="resultInfo" varStatus="status">
			<input name="bndtCeckCd" type="hidden" value="<c:out value='${resultInfo.bndtCeckCd}'/>"/>
			<input name="bndtCeckSe" type="hidden" value="<c:out value='${resultInfo.bndtCeckSe}'/>"/>
			<tr>
				<td><c:out value="${resultInfo.bndtCeckCdNm}"/></td>
				<c:if test="${resultInfo.bndtCeckSe == '99'}">
			        <td colspan="3">
			           <input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="text" size="70" value="<c:out value='${resultInfo.chckSttus}'/>" maxlength="2000" style="width:90%;" title="<c:out value="${resultInfo.bndtCeckCdNm}"/>"  readOnly>
			        </td>
				</c:if>
				<c:if test="${resultInfo.bndtCeckSe != '99'}">
					<c:if test="${resultInfo.chckSttus == '1'}">
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="1" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.good"/>"   disabled checked></td><!-- 양호 -->
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="2" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.bad"/>"   disabled></td><!-- 불량 -->
					</c:if>
					<c:if test="${resultInfo.chckSttus == '2'}">
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="1" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.good"/>"   disabled></td><!-- 양호 -->
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="2" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.bad"/>"   disabled checked></td><!-- 불량 -->
					</c:if>
				</c:if>
			</tr>   
			</c:forEach>
		</tbody>
	</table>
</div>
</form:form>
</body>
</html>