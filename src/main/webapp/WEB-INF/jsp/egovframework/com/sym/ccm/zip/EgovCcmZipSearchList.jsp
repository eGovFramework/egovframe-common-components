<%--
 /**
  * @Class Name  : EgovCcmZipSearchList.jsp
  * @Description : EgovCcmZipSearchList 화면
  * @Modification Information
  * @
  * @  수정일        수정자              수정내용
  * @ ---------     --------    ---------------------------
  * @ 2009.04.01     이중호              최초 생성
  * @ 2011.11.22     이기하              도로명주소 추가
  * @ 2013.05.23     이기하              RegExp 수정(addr.replace("/^\s+|\s+$/g",""); => addr.replace(/^\s+|\s+$/g,""))
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
--%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymCcmZip.ccmZipSearchList.title"/></title><!-- 우편번호 찾기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/ccm/zip/EgovCcmZipSearchList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_Zip(){
	document.listForm.pageIndex.value = 1;
	document.listForm.searchList.value = document.getElementById("searchList").value;
	document.listForm.searchCondition.value = document.getElementById("searchCondition").value;
	document.listForm.searchCondition2.value = document.getElementById("searchCondition2").value;
   	document.listForm.submit();
}
/* ********************************************************
 * 결과 우편번호,주소 반환
 ******************************************************** */
function fn_egov_return_Zip(zip,addr){
	var retVal   = new Object();
	var sZip     = zip;
	var vZip     = zip.substring(0,3)+zip.substring(3,6);
	var sAddr    = addr.replace(/^\s+|\s+$/g,"");
	retVal.sZip  = sZip;
	retVal.vZip  = vZip;
	retVal.sAddr = sAddr;
	
	setReturnValue(retVal);
	
	parent.window.returnValue = retVal;
	parent.window.close();
}
/* ********************************************************
 * 목록회면 처리 함수
 ******************************************************** */
function fn_egov_list(){
	if (document.getElementById("searchList").value == 1) {
		document.getElementById("searchCondition").style.display="";
		document.getElementById("searchCondition2").style.display="none";
	} else {
		document.getElementById("searchCondition").style.display="none";
		document.getElementById("searchCondition2").style.display="";
	}
}
-->
</script>
</head>

<body onLoad="fn_egov_list()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="listForm" action="<c:url value='/sym/ccm/zip/EgovCcmZipSearchList.do'/>" method="post">

<div class="board" style="width:680px">

	<h1><spring:message code="comSymCcmZip.ccmZipSearchList.title"/></h1><!-- 우편번호 찾기 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchList" id="searchList" title="searchList" onchange="fn_egov_list()"> 
					<option value='1' <c:if test="${searchList == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.SearchAddr"/></option><!-- 주소 -->
					<option value='2' <c:if test="${searchList == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.SearchRdmn"/></option><!-- 도로명주소 -->
				</select>
				<select name="searchCondition" id="searchCondition" title="searchCondition" style="display:none">
				   <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.zip"/></option><!-- 우편번호 -->
				   <option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/></option><!-- 시도명 -->
				   <option value='3' <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.signguNm"/></option><!-- 시군구명 -->
				   <option value='4' <c:if test="${searchVO.searchCondition == '4'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.emdNm"/></option><!-- 읍면동명 -->
				   <option value='5' <c:if test="${searchVO.searchCondition == '5'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.liBuldNm"/></option><!-- 리건물명 -->
				</select>
			   	<select name="searchCondition2" id="searchCondition2" title="searchCondition" style="display:none">
				   <option value='1' <c:if test="${searchVO.searchCondition2 == '1'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.zip"/></option><!-- 우편번호 -->
				   <option value='2' <c:if test="${searchVO.searchCondition2 == '2'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/></option><!-- 시도명 -->
				   <option value='3' <c:if test="${searchVO.searchCondition2 == '3'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.signguNm"/></option><!-- 시군구명 -->
				   <option value='4' <c:if test="${searchVO.searchCondition2 == '4'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.rdmn"/></option><!-- 도로명 -->
				   <option value='5' <c:if test="${searchVO.searchCondition2 == '5'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.buldNm"/></option><!-- 건물명 -->
				   <option value='6' <c:if test="${searchVO.searchCondition2 == '6'}">selected="selected"</c:if>><spring:message code="comSymCcmZip.zipVO.detailBuldNm"/></option><!-- 상세건물명 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword}" maxlength="20" size="20" title="<spring:message code="comSymCcmZip.ccmZipSearchList.inputText"/>" /><!-- 입력창 -->
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire"/>"  title="<spring:message code="title.inquire"/>" onclick="fn_egov_search_Zip();" /><!-- 조회 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:75%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"></th>
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
			<td class="lt_text3" colspan="2">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			<c:if test="${searchList == '1'}">
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
					<tr style="cursor:pointer;cursor:pointer;" onclick="fn_egov_return_Zip( '${resultInfo.zip}', '${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm}');">
						<td><c:out value='${fn:substring(resultInfo.zip, 0,3)}'/>-<c:out value='${fn:substring(resultInfo.zip, 3,6)}'/></td>
						<td>${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm} ${resultInfo.lnbrDongHo}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${searchList == '2'}">
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
					<tr style="cursor:pointer;cursor:pointer;" onclick="fn_egov_return_Zip( '${resultInfo.zip}', '${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.rdmn} ${resultInfo.bdnbrMnnm} <c:if test="${resultInfo.bdnbrSlno != ''}">- ${resultInfo.bdnbrSlno}</c:if> ${resultInfo.buldNm} ${resultInfo.detailBuldNm}');">
						<td><c:out value='${fn:substring(resultInfo.zip, 0,3)}'/>-<c:out value='${fn:substring(resultInfo.zip, 3,6)}'/></td>
						<td>${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.rdmn} ${resultInfo.bdnbrMnnm} <c:if test="${resultInfo.bdnbrSlno != ''}">- ${resultInfo.bdnbrSlno}</c:if> ${resultInfo.buldNm} ${resultInfo.detailBuldNm}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
		</ul>
	</div>
</div>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
<input type=hidden name="searchList">
</form>
</body>
</html>