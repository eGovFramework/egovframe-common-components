<%
 /**
  * @Class Name : EgovOnlinePollManageList.jsp
  * @Description : POLL관리 목록 페이지
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09	장동한		최초 생성
  *   2011.07.06		옥찬우		Tag 변수값수정 ( Line 180 : pollKindCode -> pollKnd )
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
<c:set var="pageTitle"><spring:message code="comUssOlpOpp.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uss/olp/opp/online_poll.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_OnlinePollPartcptn(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_OnlinePollPartcptn(){
	location.href = "<c:url value='/uss/olp/opm/listOnlinePollPartcptn.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_OnlinePollPartcptn(){
	var varFrom = document.OnlinePollPartcptn;

	if(confirm("<spring:message code="common.save.msg" />")){ 
		varFrom.action =  "<c:url value='/uss/olp/opp/registOnlinePollPartcptn.do' />";
		return true;
	}else{
		return false;
	}

	return true;
}


</script>
</head>
<body onLoad="fn_egov_init_OnlinePollPartcptn()">
<div class="wTableFrm">

<!--  상단타이틀 -->
<form id="OnlinePollPartcptn" name="OnlinePollPartcptn" action="<c:url value='/uss/olp/opp/registOnlinePollPartcptn.do' />" onsubmit="return fn_egov_save_OnlinePollPartcptn()" method="post">
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.create" /></h2>


<!-- 등록폼 -->
<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
<caption>${pageTitle} <spring:message code="title.create" /></caption>
<colgroup>
	<col style="width: 16%;"><col style="width: ;">
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
		<c:out value="${PollManage[0].pollNm}" escapeXml="false" />	
		</td>
	</tr>
	<!-- POLL시작일자 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollBeginDe"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
			<c:out value="${PollManage[0].pollBeginDe}" escapeXml="false" />
		</td>
	</tr>
	<!-- POLL종료일자 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollEndDe"/></c:set>
	<tr>
		<th>${title} <span class="pilsu">*</span></th>
		<td class="nopd">
			<c:out value="${PollManage[0].pollEndDe}" escapeXml="false" />
		</td>
	</tr>
	<!-- POLL종류 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollType"/></c:set>
	<tr>
		<th>${title}<span class="pilsu">*</span></th>
		<td class="nopd">
			<c:forEach items="${pollKindCodeList}" var="resultInfo" varStatus="pollKindStatus">
				<c:if test="${resultInfo.code eq PollManage[0].pollKindCode}">
					<c:out value="${resultInfo.codeNm}" escapeXml="false" />
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<!-- POLL 내용 -->
	<c:set var="title"><spring:message code="comUssOlpOpp.regist.pollType"/></c:set>
	<tr>
		<td class="nopd" colspan="2">
		
		<div class="boxType2 mt20">
			<ul class="poll_partcptn">
				<c:forEach items="${PollItem}" var="resultInfo" varStatus="status">
	   			<li><div style="float: left;"><input type="radio" name="pollIemId" value="${resultInfo.pollIemId}" style="border:0px;" <c:if test="${status.count == '1'}">checked</c:if> ></div><div style="float: left;margin-left:13px;"><c:out value="${resultInfo.pollIemNm}" escapeXml="false" /></div></li>
				</c:forEach>
			</ul>
		</div>
    		
		</td>
	</tr>
	
</tbody>
</table>



<!-- 하단 버튼 -->
<div class="btn">
	<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" />
	<span class="btn_s"><a href="<c:url value='/uss/olp/opp/listOnlinePollPartcptn.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

<input name="pollId" type="hidden" value="${PollManage[0].pollId}"/>
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</form>




</DIV>

</body>
</html>
