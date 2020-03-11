<!DOCTYPE html>
<%
 /**
  * @Class Name  : EgovCcmAdministCode.jsp
  * @Description : EgovCcmAdministCode 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymCcmAdc.ccmAdministCode.upperCode"/> <spring:message code="title.list" /></c:set>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">

	/* ********************************************************
	 * 초기화
	 ******************************************************** */
	function fnInit(){
		getDialogArguments();
		var varParam        = window.dialogArguments;
		var varForm			= document.getElementById("Form");
		var pForm			= parent.document.getElementById("pForm");
		if (varParam.administZoneSe) {
			var administZoneSe = varParam.administZoneSe;
			if(pForm.init.value != "OK") {
				pForm.init.value  = "OK";
				varForm.action      = "<c:url value='/sym/ccm/adc/EgovCcmAdministCode.do'/>";
				varForm.searchCondition.value  = administZoneSe;
				varForm.submit();
			}
		}
	}
	
	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function linkPage(pageNo){
		document.listForm.pageIndex.value = pageNo;
		document.listForm.action = "<c:url value='/sym/ccm/adc/EgovCcmAdministCode.do'/>";
	   	document.listForm.submit();
	}
	
	/* ********************************************************
	 * 조회 처리
	 ******************************************************** */
	function fnSearch(){
		document.listForm.pageIndex.value = 1;
	   	document.listForm.submit();
	}
	
	/* ********************************************************
	* 결과 반환
	******************************************************** */
	function fnReturnDay(administZoneCode,administZoneNm){
		var retVal   = new Object();
		retVal.administZoneCode = administZoneCode;
		retVal.administZoneNm   = administZoneNm;
		setReturnValue(retVal);
		parent.window.returnValue = retVal;
		parent.window.close();
	}
</script>
</head>

<body onLoad="javascript:fnInit();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
<form name="listForm" action="<c:url value='/sym/ccm/adc/EgovCcmAdministCode.do'/>" method="post">
	<h1>${pageTitle}</h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<c:if test="${searchVO.searchCondition == '1'}"><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddrName" /></c:if> <!-- 법정동 지역명 -->
				<c:if test="${searchVO.searchCondition == '2'}"><spring:message code="comSymCcmAdc.ccmAdministCode.admAddrName" /></c:if> <!-- 행정동 지역명 -->
			
				<input id="searchKeyword" class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword}" size="25" onkeypress="press();" title="<spring:message code="comSymCcmAdc.seachWrd.searchAddress" />" />
				
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fnSearch(); return false;" /> <!-- 조회 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption>로그인정책 관리</caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th>
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.cls" /></th> <!-- 구분 -->
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.administCode" /></th> <!-- 행정구역코드 -->
			   <th scope="col"><spring:message code="comSymCcmAdc.ccmAdministCode.administName" /></th> <!-- 행정구역명 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;cursor:hand;" onclick="fnReturnDay('${resultInfo.administZoneCode}','${resultInfo.administZoneNm}');">
				<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
				<td>
					<c:choose>
						<c:when test='${resultInfo.administZoneSe == "1"}'><spring:message code="comSymCcmAdc.ccmAdministCode.lawAddr" /></c:when>
						<c:when test='${resultInfo.administZoneSe == "2"}'><spring:message code="comSymCcmAdc.ccmAdministCode.admAddr" /></c:when>
					</c:choose>
				</td>
				<td>${resultInfo.administZoneCode}</td>
				<td class="left">${resultInfo.administZoneNm}</td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="4">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_tmplatInfo"/>
		</ul>
	</div>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>	
<input name="searchCondition" type=hidden value="<c:out value='${searchVO.searchCondition}'/>"/>
</form>
<form name="Form" id="Form" method="post" action="<c:url value='/sym/ccm/adc/EgovCcmAdministCode.do'/>">
	<input type=hidden name="administZoneSe">
	<input type=hidden name="administZoneCode">
	<input type=hidden name="searchCondition">
	<input type="submit" id="invisible" class="invisible">
</form>

</div>

</body>
</html>
