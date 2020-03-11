<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovAnnvrsryDetail.java
 * @Description : EgovAnnvrsryDetail jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.30    이      용                최초 생성
 * @ 2018.08.13    최 두 영       퍼블리싱 검증
 * @ 2018.09.19    최 두 영       다국어처리 
 *
 *  @author 이      용
 *  @since 2010.06.30
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
 
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonAns.annvrsryDetail.title"/></title><!-- 기념일 상세 조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*설명 : 기념일 목록 조회 */
function fncSelectAnnvrsryManageList(pageNo){
    document.DetailForm.searchCondition.value = "1";
    document.DetailForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>";
    document.DetailForm.submit();
}

/*설명 : 기념일 수정조회 */
function fncSelectAnnvrsryManage() {
	document.DetailForm.cmd.value  = "update";
    document.DetailForm.action = "<c:url value='/uss/ion/ans/selectAnnvrsryManage.do'/>";
    document.DetailForm.submit();   
}
/*설명 : 기념일 삭제처리*/
function fncDeleteAnnvrsry() {
    var varFrom = document.getElementById("deleteForm");
    varFrom.action = "<c:url value='/uss/ion/ans/deleteAnnvrsryManage.do'/>";
    if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제 하시겠습니까? */
        varFrom.submit();
    }
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonAns.annvrsryDetail.annvrsryDetail"/></h2><!-- 기념일 상세 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp1"/> <span class="pilsu">*</span></th><!-- 신청자 -->
			<td class="left"><c:out value="${annvrsryManageVO.annvrsryTemp1      }"/></td>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp2"/> <span class="pilsu">*</span></th><!-- 소속 -->
			<td class="left"><c:out value="${annvrsryManageVO.annvrsryTemp2      }"/></td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp3"/> <span class="pilsu">*</span></th><!-- 기념일구분 -->
			<td class="left"><c:out value="${annvrsryManageVO.annvrsryTemp3      }"/></td>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp4"/> <span class="pilsu">*</span></th><!-- 기념일 -->
			<td class="left"><c:out value="${annvrsryManageVO.annvrsryTemp4      }"/>&nbsp;&nbsp;
	    <c:if test="${'1' eq annvrsryManageVO.reptitSe}"><b><spring:message code="comUssIonAns.common.reptitSeEvery"/></b></c:if></td><!-- 매년반복 -->
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryNm"/> <span class="pilsu">*</span></th><!-- 기념일제목 -->
			<td class="left" colspan="3"><c:out value="${annvrsryManageVO.annvrsryNm      }"/></td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.memo"/></th><!-- 메모 -->
			<td class="left" colspan="3"><textarea id="textArae" style="width:95%;height:100px;" title="<spring:message code="comUssIonAns.common.memo"/>" readOnly><c:out value="${annvrsryManageVO.memo      }"  escapeXml="false"/></textarea></td><!-- 메모 -->
		</tr>
		<tr>
			<th><spring:message code="comUssIonAns.common.annvrsryBeginDe"/></th><!-- 알림시작일 -->
			<td class="left">
				D-<c:out value="${annvrsryManageVO.annvrsryBeginDe      }"/><spring:message code="comUssIonAns.common.annvrsryBeginDdayAlarm"/><!-- 일전 알림 -->
			</td>
			<th><spring:message code="comUssIonAns.common.annvrsryTemp5"/></th><!-- 알림설정 -->
			<td class="left">
				<c:out value="${annvrsryManageVO.annvrsryTemp5      }"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<form id="selectForm" name="selectForm" action="<c:url value='/uss/ion/ans/selectAnnvrsryManage.do'/>" method="post" style="display:inline-block; vertical-align:top">  
		<input type="hidden" name="cmd"        value="update">
		<input type="hidden" name="annId"      value="<c:out value="${annvrsryManageVO.annId      }"/>">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fncSelectAnnvrsryManage(); return false;" />
		</form>
		
		<form id="deleteForm" name="deleteForm" method="post" action="<c:url value='/uss/ion/ans/deleteAnnvrsryManage.do'/>" style="display:inline-block; vertical-align:top">
		<input type="hidden" name="annId"   value="<c:out value="${annvrsryManageVO.annId      }"/>">
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fncDeleteAnnvrsry(); return false;" />
        </form>
        
        <span class="btn_s"><a href="<c:url value='/uss/ion/ans/selectAnnvrsryManageList.do'/>?searchCondition=1" onclick="fncSelectAnnvrsryManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>