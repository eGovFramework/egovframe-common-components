<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymLogTlg.trsmrcvLog.test"/></c:set>
<%
 /**
  * @Class Name : EgovTrsmrcvLogRegist.jsp
  * @Description : 송수신 로그 등록 화면
  * @Modification Information
  * @
  * @  수정일                  수정자          수정내용
  * @ -------        --------  ---------------------------
  * @ 2009.03.09      이삼섭          최초 생성
  * @ 2011.07.08      이기하          패키지 분리로 경로 수정(sym.log -> sym.log.tlg)
  * @ 2018.08.08      윤창원          다국어 처리
  * @ 2018.08.09      신용호          코드입력 자리수 수정
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function fn_egov_regist_TrsmrcvLog(pTrsmrcvSeCode){
		document.frm.trsmrcvSeCode.value = pTrsmrcvSeCode;
		document.frm.action = "<c:url value='/sym/log/tlg/InsertTrsmrcvLog.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_TrsmrcvLog(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/sym/log/tlg/SelectTrsmrcvLogList.do'/>";
		document.frm.submit();
	}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="frm" method="post" action="<c:url value='/sym/log/tlg/InsertTrsmrcvLog.do'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="trsmrcvSeCode" type="hidden" />

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}</h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.linkId" /> <span class="pilsu">*</span></th> <!-- 연계ID -->
			<td class="left">
			    <input id="cntcId" type="text" name="cntcId" value="CNTC0001" size="60" maxlength="8" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.providerId" /> <span class="pilsu">*</span></th> <!-- 제공기관ID -->
			<td class="left">
			    <input id="provdInsttId" type="text" name="provdInsttId" value="INS00001" size="60" maxlength="8" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.providerSystemId" /> <span class="pilsu">*</span></th> <!-- 제공시스템ID -->
			<td class="left">
			    <input id="provdSysId" type="text" name="provdSysId" value="SYS00001" size="60" maxlength="8" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.providerServiceId" /> <span class="pilsu">*</span></th> <!-- 제공서비스ID -->
			<td class="left">
			    <input id="provdSvcId" type="text" name="provdSvcId" value="SVC00001" size="60" maxlength="8" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymLogTlg.trsmrcvLog.requestOrgId" /> <span class="pilsu">*</span></th> <!-- 요청기관ID -->
			<td class="left">
			    <input id="requstInsttId" type="text" name="requstInsttId" value="INS00011" size="60" maxlength="8" />
			</td>
		</tr>
		<tr>
			<th>요청시스템ID <span class="pilsu">*</span></th>
			<td class="left">
			    <input id="requstSysId" type="text" name="requstSysId" value="SYS00031" size="60" maxlength="8" />
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.transferRequest" />" onclick="fn_egov_regist_TrsmrcvLog('S01'); return false;" /> <!-- 전송요청 -->
		<input class="s_submit" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.transferComplete" />" onclick="fn_egov_regist_TrsmrcvLog('S02'); return false;" /> <!-- 전송완료 -->
		<input class="s_submit" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.transferFailed" />" onclick="fn_egov_regist_TrsmrcvLog('S03'); return false;" /> <!-- 전송실패 -->
		<input class="s_submit" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.receiveRequest" />" onclick="fn_egov_regist_TrsmrcvLog('S04'); return false;" /> <!-- 수신요청 -->
		<input class="s_submit" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.receiveComplete" />" onclick="fn_egov_regist_TrsmrcvLog('S05'); return false;" /> <!-- 수신완료 -->
		<input class="s_submit" type="submit" value="<spring:message code="comSymLogTlg.trsmrcvLog.receiveFailed" />" onclick="fn_egov_regist_TrsmrcvLog('S06'); return false;" /> <!-- 수신실패 -->
		<input class="s_submit" type="submit" value="<spring:message code="title.list" />" onclick="fn_egov_regist_TrsmrcvLog('1'); return false;" /> <!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>


</form>

</body>
</html>
