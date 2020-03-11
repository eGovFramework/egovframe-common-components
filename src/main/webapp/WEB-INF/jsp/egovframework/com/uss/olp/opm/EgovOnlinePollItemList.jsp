<%
 /**
  * @Class Name : EgovOnlinePollItemList
  * @Description : 온라인POLL항목 목록 페이지
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.08.05    장동한        최초 생성
  *   2016.06.13 	장동한        표준프레임워크 v3.6 개선
  *  
  *  @author 공통서비스 개발팀 장동한
  *  @since 2009.08.05
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
<c:set var="pageTitle"><spring:message code="comUssOlpOpm.pollItem.title"/></c:set>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<c:set var="resultListCont" value="${resultListCont+1}"/>
</c:forEach>
<c:set var="resultListCont" value="0"/>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript">
	//전역변수 설정
	var ifr= parent.document.all? parent.document.all.PollIemView : parent.document.getElementById("PollIemView") ;

	function do_resize() {
		resizeFrame(1);
	}

	//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
	function resizeFrame(re) {
		if(ifr){
			var innerHeight = document.body.scrollHeight + (document.body.offsetHeight - document.body.clientHeight);
			//if(ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
			//{ifr.style.height = innerHeight;}
			
			ifr.setAttribute("height",innerHeight);
		}
	}
	window.onload = function(){
		do_resize();
	}

	/* ********************************************************
	 * 등록 처리 함수
	 ******************************************************** */
	function fn_egov_regist_OnlinePollItem(){
		var vFrom = document.registForm;


		if(vFrom.registFormCmd.value != 'updt'){
			vFrom.action = "<c:url value='/uss/olp/opm/registOnlinePollItem.do' />";
			vFrom.pollIemId.value = '';
		}

		if(confirm("<spring:message code="common.save.msg" />")){

			 if( vFrom.pollIemNm.value == ""){
					alert("온라인POLL 항목명을  입력해주세요!");
					vFrom.pollIemNm.focus();
					return;
			 }

			//중복된 항목명 체크
			if(!fn_egov_search_OnlinePollItemList(vFrom.pollIemNm.value)){
				alert("[" + vFrom.pollIemNm.value + "]항목명이 중복 되었습니다!");
				vFrom.pollIemNm.value = "";
				return;
			}

			 vFrom.submit();
		}
	}
	/* ********************************************************
	* 삭제 처리 함수
	******************************************************** */
	function fn_egov_del_OnlinePollItem(pollIemId){
		var vFrom = document.registForm;
		vFrom.action = "<c:url value='/uss/olp/opm/delOnlinePollItem.do' />";

		vFrom.pollIemId.value = pollIemId;

		if(confirm("삭제 하시겠습니까?")){
			 vFrom.submit();
		}
	}
	/* ********************************************************
	* 수정화면으로 전환 함수
	******************************************************** */
	function fn_egov_modify_display_OnlinePollItem(pollIemNm, pollIemId){
		var vFrom = document.registForm;
		vFrom.action = "<c:url value='/uss/olp/opm/updtOnlinePollItem.do' />";
		vFrom.pollIemNm.value = pollIemNm;
		vFrom.pollIemId.value = pollIemId;
		vFrom.registFormCmd.value = 'updt';

		document.getElementById('divPollIem').innerHTML = '수정';

	}
	/* ********************************************************
	 * 상세회면 처리 함수
	 ******************************************************** */
	function fn_egov_detail_OnlinePollItem(pollId){
		var vFrom = document.listForm;
		vFrom.pollId.value = pollId;
		vFrom.action = "<c:url value='/uss/olp/opm/detailOnlinePollManage.do' />";
		vFrom.submit();
	}

	/* ********************************************************
	 * 검색 함수
	 ******************************************************** */
	function fn_egov_search_OnlinePollManage(){
		var vFrom = document.listForm;

		vFrom.action = "<c:url value='/uss/olp/opm/listOnlinePollManage.do' />";
		vFrom.submit();
	}

	/* ********************************************************
	* 등록된 항목 유효성 검사
	******************************************************** */
	function fn_egov_search_OnlinePollItemList(sSearch){

		var arrPollItemList = new Array(<c:forEach items="${resultList}" var="resultInfo" varStatus="status">'${resultInfo.pollIemNm}'<c:if test="${status.count != resultListCont}">,</c:if></c:forEach>'');
		for(var i=0;i<arrPollItemList.length;i++){

			if(arrPollItemList[i].trim() == sSearch){
				return false;
			}
		}
		return true;

	}

	/* ********************************************************
	* PROTOTYPE JS FUNCTION
	******************************************************** */
	String.prototype.trim = function(){
		return this.replace(/^\s+|\s+$/g, "");
	}

</script>
<style TYPE="text/css">
	body {
		scrollbar-face-color: #F6F6F6;
		scrollbar-highlight-color: #bbbbbb;
		scrollbar-3dlight-color: #FFFFFF;
		scrollbar-shadow-color: #bbbbbb;
		scrollbar-darkshadow-color: #FFFFFF;
		scrollbar-track-color: #FFFFFF;
		scrollbar-arrow-color: #bbbbbb;
		margin-left:"0px"; margin-right:"0px"; margin-top:"0px"; margin-bottom:"0px";
	}
	
	
	/* 버튼이미지 */
	.btnBackground {
		 background: #4688d2;
		 font-size: 11px;color: #fff;
	}
</style>
</head>
<BODY>
<div class="board">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form name="listForm" action="<c:url value='' />" method="post">
	
<h1>${pageTitle}</h1>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />"  id="pollIemTable">
	<caption>${pageTitle}</caption>
	<colgroup>
		<col style="width: ;">
		<col style="width: 8%;">
		<col style="width: 8%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="comUssOlpOpm.list.item" /></th><!-- 항목명 -->
		<th></th><!-- 수정 -->
		<th></th><!-- 삭제 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="3"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
  		<td class="left"><c:out value="${resultInfo.pollIemNm}"/></td>
  		<td><button class="btn_s2" onClick="fn_egov_modify_display_OnlinePollItem('${resultInfo.pollIemNm}','${resultInfo.pollIemId}'); return false;" title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /></button></td>
  		<td><button class="btn_s2" onClick="fn_egov_del_OnlinePollItem('${resultInfo.pollIemId}'); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
</form>
</div>
	<!--  줄간격조정  -->
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td height="6px"></td>
	</tr>
	</table>
	
	<form name="registForm" action="<c:url value='' />" method="post">
		<input name="pollId" type="hidden" value="${onlinePollItem.pollId}">
		<input name="pollIemId" type="hidden" value="">
		<input name="registFormCmd" type="hidden" value="">

		<!--  온라인POLL항목 입력 -->
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
			    <td width="2px" nowrap></td>
			    <td nowrap><input name="pollIemNm" id="pollIemNm" type="text" size="100" value="" maxlength="255" style="width:100%;" title="POLL 세부항목 입력"></td>
			    <td width="10px" nowrap></td>
			    <td width="40px" align="center" nowrap style=" background: #4688d2; cursor:pointer;cursor:hand;" onClick="JavaScript:fn_egov_regist_OnlinePollItem();">
				<DIV id="divPollIem" style="font-size: 11px;color: #fff;"><spring:message code="button.create" /></DIV>
				</td>
			</tr>
		</table>


<!-- /DIV -->
</form>

</BODY>
</HTML>
