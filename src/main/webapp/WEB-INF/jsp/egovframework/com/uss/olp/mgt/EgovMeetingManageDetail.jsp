<%--
  Class Name : EgovMeetingManageDetail.jsp
  Description : 회의정보 상세조회 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2018.09.13    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussOlpMgt.meetingManageDetail.meetingManageDetail"/></title><!-- 회의관리 상세조회 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_MeetingManage(){


}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_MeetingManage(){
	location.href = "<c:url value='/uss/olp/mgt/EgovMeetingManageList.do' />";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_egov_modify_MeetingManage(){
	var varFrom = document.getElementById("MeetingManageForm");

	varFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageModify.do' />";;
	varFrom.submit();
}
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
 function fn_egov_delete_MeetingManage(){
		var vFrom = document.MeetingManageForm;
		if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
			vFrom.cmd.value = 'del';
			vFrom.action = "<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do' />";
			vFrom.submit();
		}else{
			vFrom.cmd.value = '';
		}
}
</script>
</head>
<body onLoad="fn_egov_init_MeetingManage();">

<form name="MeetingManageForm"  id="MeetingManageForm" action="<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do' />" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussOlpMgt.meetingManageDetail.meetingManageDetail"/></h2><!-- 회의관리 상세조회 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgNm"/> <span class="pilsu">*</span></th><!-- 회의제목 -->
			<td class="left">${resultList[0].mtgNm}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgMtrCn"/> <span class="pilsu">*</span></th><!-- 회의 안건 내용 -->
			<td class="left">${resultList[0].mtgMtrCn}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgSn"/> <span class="pilsu">*</span></th><!-- 회의순서 -->
			<td class="left">${resultList[0].mtgSn}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgCo"/> <span class="pilsu">*</span></th><!-- 회의회차 -->
			<td class="left">${resultList[0].mtgCo}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgDe"/> <span class="pilsu">*</span></th><!-- 회의일자 -->
			<td class="left">${resultList[0].mtgDe}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgPlace"/> <span class="pilsu">*</span></th><!-- 회의장소 -->
			<td class="left">${resultList[0].mtgPlace}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgBeginTime"/> <span class="pilsu">*</span></th><!-- 회의시작시간 -->
			<td class="left">
				<c:forTokens var="one"
		            items="${resultList[0].mtgBeginTime}"
		            delims=":" varStatus="sts">
	  				<c:if test="${sts.count == 1}">${one}<spring:message code="ussOlpMgt.meetingManageDetail.mtgHH"/></c:if><!-- 시 -->
			        <c:if test="${sts.count == 2}">${one}<spring:message code="ussOlpMgt.meetingManageDetail.mtgMM"/></c:if><!-- 분 -->
				</c:forTokens>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgEndTime"/> <span class="pilsu">*</span></th><!-- 회의종료시간 -->
			<td class="left">
				<c:forTokens var="one"
		            items="${resultList[0].mtgEndTime}"
		            delims=":" varStatus="sts">
	  				<c:if test="${sts.count == 1}">${one}<spring:message code="ussOlpMgt.meetingManageDetail.mtgHH"/></c:if><!-- 시 -->
			        <c:if test="${sts.count == 2}">${one}<spring:message code="ussOlpMgt.meetingManageDetail.mtgMM"/></c:if><!-- 분 -->
				</c:forTokens>&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.clsdrMtgAt"/> <span class="pilsu">*</span></th><!-- 비공개 회의여부 -->
			<td class="left">
				<c:if test="${resultList[0].clsdrMtgAt eq '1'}"><spring:message code="input.yes"/></c:if><!-- 예 -->
				<c:if test="${resultList[0].clsdrMtgAt eq ''}"><spring:message code="input.no"/></c:if><!-- 아니오 -->&nbsp;
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.readngBeginDe"/> <span class="pilsu">*</span></th><!-- 열람 개시일 -->
			<td class="left">${resultList[0].readngBeginDe}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.readngAt"/> <span class="pilsu">*</span></th><!-- 열람 여부 -->
			<td class="left">${resultList[0].readngAt}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgResultCn"/></th><!-- 회의결과 내용 -->
			<td class="left">${resultList[0].mtgResultCn}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtgResultEnnc"/></th><!-- 회의결과 여부 -->
			<td class="left">
				<c:if test="${resultList[0].mtgResultEnnc eq '1'}"><spring:message code="input.yes"/></c:if><!-- 예 -->
				<c:if test="${resultList[0].mtgResultEnnc eq ''}"><spring:message code="input.no"/>&nbsp;</c:if><!-- 아니오 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.etcMatter"/></th><!-- 기타 사항 -->
			<td class="left">${resultList[0].etcMatter}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mngtDeptNm"/></th><!-- 주관부서 -->
			<td class="left">${resultList[0].mngtDeptNm}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mnaerIds"/></th><!-- 주관자ID -->
			<td class="left">${resultList[0].mnaerIds}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mnaerNm"/></th><!-- 주관자명 -->
			<td class="left">${resultList[0].mnaerNm}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mnaerDeptNm"/></th><!-- 주관자부서 -->
			<td class="left">${resultList[0].mnaerDeptNm}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.mtnAt"/></th><!-- 회의여부 -->
			<td class="left">${resultList[0].mtnAt}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.nonatdrnCo"/></th><!-- 불참석자수 -->
			<td class="left">${resultList[0].nonatdrnCo}&nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="ussOlpMgt.meetingManageDetail.atdrnCo"/></th><!-- 참석자수 -->
			<td class="left">${resultList[0].atdrnCo}&nbsp;</td>
		</tr>
	</table>
<input name="mtgId" type="hidden" value="${resultList[0].mtgId}">
<input name="cmd" type="hidden" value="">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
</form>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<form name="formUpdt" action="<c:url value='/uss/olp/mgt/EgovMeetingManageModify.do'/>" method="post" style="display:inline">
		<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_MeetingManage(); return false;" />
		<input name="mtgId" type="hidden" value="${resultList[0].mtgId}">
		</form>
		
		<form name="formDelete" action="<c:url value='/uss/olp/mgt/EgovMeetingManageDetail.do'/>" method="post" style="display:inline">
		<input class="s_submit" type="submit" value="<spring:message code="button.delete" />" onclick="fn_egov_delete_MeetingManage(); return false;">
		<input name="mtgId" type="hidden" value="${resultList[0].mtgId}">
		<input name="cmd" type="hidden" value="del">
		</form>
		
		<form name="formList" action="<c:url value='/uss/olp/mgt/EgovMeetingManageList.do'/>" method="post" style="display:inline">
		<input class="s_submit"type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_MeetingManage(); return false;">
		</form>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>
