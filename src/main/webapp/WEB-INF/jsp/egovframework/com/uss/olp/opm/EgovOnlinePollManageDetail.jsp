<%
 /**
  * @Class Name : EgovOnlinePollManageDetail.jsp
  * @Description : 온라인POLL관리 상세보기
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09    장동한         최초 생성
  *   2016.06.13 	장동한        표준프레임워크 v3.6 개선
  *  
  *  @author 공통서비스 개발팀 장동한
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpOpm.title"/></c:set>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init_OnlinePollManage(){
		document.getElementById('PollIemView').src="<c:url value='/uss/olp/opm/listOnlinePollItem.do' />?pollId=${onlinePollManage.pollId}";
	}
	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_OnlinePollManage(){
		location.href = "<c:url value='/uss/olp/opm/listOnlinePollManage.do' />";
	}
	/* ********************************************************
	 * 수정처리화면
	 ******************************************************** */
	function fn_egov_modify_OnlinePollManage(){
		var vFrom = document.OnlinePollManageForm;
		vFrom.cmd.value = '';
		vFrom.action = "<c:url value='/uss/olp/opm/updtOnlinePollManage.do' />";;
		vFrom.submit();

	}
	/* ********************************************************
	 * 삭제처리
	 ******************************************************** */
	function fn_egov_delete_OnlinePollManage(){
		var vFrom = document.OnlinePollManageForm;
		if(confirm("삭제 하시겠습니까?")){
			vFrom.cmd.value = 'del';
			vFrom.action = "<c:url value='/uss/olp/opm/detailOnlinePollManage.do' />";
			vFrom.submit();
		}else{
			vFrom.cmd.value = '';
		}
	}
</script>

</head>

<body onLoad="fn_egov_init_OnlinePollManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">

<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.detail" /></h2>


<form name="OnlinePollManageForm" action="<c:url value=''/>" method="post">
<!-- 상세정보 -->
<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
<caption>${pageTitle} <spring:message code="title.create" /></caption>
<colgroup>
	<col style="width: 22%;"><col style="width: ;">
</colgroup>
<tbody>
	<!-- 입력 -->
	<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
	<!-- 선택 -->
	<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
	<!-- POLL명 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollNm"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
		&nbsp;<c:out value="${onlinePollManage.pollNm}" />
		</td>
	</tr>
	<!-- POLL시작일자 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollBeginDe"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
			&nbsp;<c:out value="${fn:substring(onlinePollManage.pollBeginDe, 0, 10)}"/>
		</td>
	</tr>
	<!-- POLL종료일자 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollEndDe"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
			&nbsp;<c:out value="${fn:substring(onlinePollManage.pollEndDe, 0, 10)}"/>
		</td>
	</tr>
	<!-- POLL종류 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollType"/></c:set>
	<tr>
		<th>${title}<span class="pilsu">*</span></th>
		<td class="nopd">
	 			<c:forEach items="${pollKindCodeList}" var="resultInfo" varStatus="pollKindStatus">
					<c:if test="${resultInfo.code eq onlinePollManage.pollKindCode}">
						&nbsp;<c:out value="${resultInfo.codeNm}" />
					</c:if>
				</c:forEach>
		</td>
	</tr>
	<!-- POLL페기유무 -->
	<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollDsuseYn"/></c:set>
	<tr>
		<th>${title}<span class="pilsu">*</span></th>
		<td class="nopd">
    		&nbsp;<c:out value="${onlinePollManage.pollDsuseYn}"/>
		</td>
	</tr>
	<!-- POLL자동페기유무 -->
	<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollAutoDsuseYn"/></c:set>
	<tr>
		<th>${title}<span class="pilsu">*</span></th>
		<td class="nopd">
    			&nbsp;<c:out value="${onlinePollManage.pollAutoDsuseYn}"/>
		</td>
	</tr>
	
</tbody>
</table>
<input name="pollId" type="hidden" value="${onlinePollManage.pollId}">
<input name="cmd" type="hidden" value="<c:out value=''/>"/>
</form>

<!-- 하단 버튼 -->
<div class="btn">
	<form name="formUpdt" action="<c:url value='/uss/olp/opm/updtOnlinePollManage.do'/>" method="post" style="float:left;">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_OnlinePollManage(); return false;">
		<input name="pollId" type="hidden" value="${onlinePollManage.pollId}">
	</form>
	<form name="formDelete" action="<c:url value='/uss/olp/opm/detailOnlinePollManage.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<input type="submit" class="s_submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete_OnlinePollManage(); return false;">
		<input name="pollId" type="hidden" value="${onlinePollManage.pollId}">
		<input name="cmd" type="hidden" value="<c:out value='del'/>"/>
	</form>

	<form name="formList" action="<c:url value='/uss/olp/opm/listOnlinePollManage.do'/>" method="post" style="float:left; margin:0 0 0 3px;">
		<input type="submit" class="s_submit" value="<spring:message code="button.list" />" onclick="fn_egov_search_OnlinePollManage(); return false;">
	</form>
		
</div><div style="clear:both;"></div>


<!--  줄간격조정  -->
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td height="3px"></td>
	</tr>
</table>

<!-- 온라인POLL항목 iframe  -->
<iframe id="PollIemView" src="" title="온라인POLL항목"  width="100%" frameborder="0" scrolling="no" marginwidth="0" marginheight="0">
</iframe>


</DIV>
</body>
</html>
