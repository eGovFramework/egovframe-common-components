<%--
  Class Name : EgovQustnrQestnManageStatistics.jsp
  Description : 설문문항 통계보기
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.20    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.19

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comUssOlpQqm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uss/olp/opp/online_poll.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrQestnManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrQestnManage(){
	var vFrom = document.QustnrQestnManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_QustnrQestnManage(){
	var vFrom = document.QustnrQestnManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageModify.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_QustnrQestnManage(){
	var vFrom = document.QustnrQestnManageForm;
	if(confirm("<spring:message code='common.delete.msg'/>")){ //삭제 하시겠습니까?
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageDetail.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrQestnManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="comUssOlpQqm.regist.statistics" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="comUssOlpQqm.regist.statistics" /></caption>
	<colgroup>
		<col style="width:30%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문지정보(제목) -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnrCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultList[0].qestnrSj}"/>
			</td>
		</tr>
		<!-- 질문순번 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnSn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultList[0].qestnSn}"/>
			</td>
		</tr>
		<!-- 질문유형 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnTyCode"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:if test="${resultList[0].qestnTyCode == '1'}"><spring:message code="comUssOlpQqm.regist.objectiveQuest"/></c:if><!-- 객관식 -->
	    		<c:if test="${resultList[0].qestnTyCode == '2'}"><spring:message code="comUssOlpQqm.regist.subjectiveQuest"/></c:if><!-- 주관식 -->
			</td>
		</tr>
		<!-- 질문내용 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultList[0].qestnCn , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		<!-- 최대선택건수 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.mxmmChoiseCo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<!-- ${resultList[0].mxmmChoiseCo} -->
  				<c:out value="${resultList[0].mxmmChoiseCo}"/>
			</td>
		</tr>
		<!-- 설문항목 결과 -->
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status1">
		
		<c:set var="title"><spring:message code="comUssOlpQqm.result.iemCn"/></c:set>
		
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" >
			
			<!-- 객관식 중 선택된 항목이 있을 때 -->
			<c:if test="${resultInfo.qestnTyCode ==  '1'}">
			
			<div class="boxType2 mt20">
			<c:set var="chartCheck" value="0" />
			<dl class="poll_chart" style="width:550px;padding-left: 0px;">
			<c:forEach items="${statisticsList}" var="statisticsList" varStatus="status2">
				<c:set var="chartCheck" value="0" />
				<dd>
				<ul>
				<li>
					<table>						
						<th ><c:out value="${statisticsList.iemCn}" escapeXml="false" /></th>
						<td>
							<span class="g_bar2">
								<span class="g_org" style="width:${statisticsList.qustnrPercent}px;"></span>
							</span>
						</td>
						<td style="width:50px;">			
							(${statisticsList.qustnrPercent})%
						</td>
						<c:set var="chartCheck" value="${chartCheck+1}" />
					</table>	
				</li>
				</ul>
				</dd>
	  		</c:forEach>
			<c:set var="chartCheck" value="0" />
			</dl>
			</div>	
			</c:if>
			</td>
		</tr>
	</c:forEach>
		
		
		<!-- 응답자답변내용 결과 -->		
		<c:set var="title"><spring:message code="comUssOlpQqm.result.respondAnswerCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<table border="0" cellpadding="0" cellspacing="0">
			    <c:forEach items="${statisticsList2}" var="statisticsList2" varStatus="status">
			    <c:if test="${statisticsList2.respondAnswerCn ne ''}">
					<tr>
					<td style="border-bottom:1px solid white;">
					<c:out value="${fn:replace(statisticsList2.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
					</td>
					</tr>
				</c:if>
			    </c:forEach>
			    </table>
			</td>
		</tr>
		
		
		<!-- 기타답변내용 결과 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.result.etcAnswerCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<table border="0" cellpadding="0" cellspacing="0" >
    			<c:forEach items="${statisticsList2}" var="statisticsList2" varStatus="status">
     			<c:if test="${statisticsList2.etcAnswerCn ne ''}">
	    			<tr>
		    		<td style="border-bottom:1px solid white;">
		    	 	<c:out value="${fn:replace(statisticsList2.etcAnswerCn , crlf , '<br/>')}" escapeXml="false" />
		    		</td>
	    			</tr>
	  			</c:if>
    			</c:forEach>			
    			</table>
			</td>
		</tr>

	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 목록 버튼 -->
		<form name="QustnrQestnManageForm" action="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do'/>" method="post" onsubmit="fn_egov_list_QustnrQestnManage(); return false;" style="float:left;">
		<input type="submit" class="s_submit" value="<spring:message code='button.list' />" onclick="fn_egov_list_QustnrQestnManage(); return false;">
		</form>
		
	</div><div style="clear:both;"></div>

<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
<input name="cmd" type="hidden" value="<c:out value=''/>"/>
<%-- 설문지정보 상태유지 --%>
<c:if test="${qustnrQestnManageVO.searchMode == 'Y'}">
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qustnrQestnManageVO.qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
<input name="searchMode" id="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">
</c:if>
	
</div>

</body>
</html>