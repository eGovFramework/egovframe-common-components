<%
 /**
  * @Class Name : EgovCpyrhtPrtcPolicyDetailInqure.jsp
  * @Description : EgovCpyrhtPrtcPolicyDetailInqure 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  * @ 2018.09.03   이정은              공통컴포넌트 3.8 개선 
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussSamCpy.cpyrhtPrtcPolicyDetailInqire.cpyrhtPrtcPolicyDetailInqire"/></title><!-- 저작권보호정책 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_cpyrhtprtcpolicylist() {

	document.CpyrhtPrtcPolicyForm.action = "<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do'/>";
	document.CpyrhtPrtcPolicyForm.submit();
		
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_updt_cpyrhtprtcpolicycn(cpyrhtId){

	// Update하기 위한 키값을 셋팅
	document.CpyrhtPrtcPolicyForm.cpyrhtId.value = cpyrhtId;	
	document.CpyrhtPrtcPolicyForm.action = "<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyCnUpdtView.do'/>";
	document.CpyrhtPrtcPolicyForm.submit();	
	
}

/**********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_egov_delete_cpyrhtprtcpolicycn(cpyrhtId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값을 셋팅
		document.CpyrhtPrtcPolicyForm.cpyrhtId.value = cpyrhtId;	
		document.CpyrhtPrtcPolicyForm.action = "<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyCnDelete.do'/>";
		document.CpyrhtPrtcPolicyForm.submit();
			
	}	
	
}

</script>
</head>

<body>
<form name="CpyrhtPrtcPolicyForm" action="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyDetailInqire.do'/>" method="post">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussSamCpy.cpyrhtPrtcPolicyDetailInqire.cpyrhtPrtcPolicyDetailInqire"/></h2><!-- 저작권보호정책 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussSamCpy.cpyrhtPrtcPolicyDetailInqire.cpyrhtPrtcPolicyCn"/></th><!-- 저작권보호정책내용 -->
			<td class="left">
			   <textarea class="textarea" name="cpyrhtPrtcPolicyCn" cols="70" rows="25" readonly="readonly" title="저작권보호정책내용" style="height:300px"><c:out value="${result.cpyrhtPrtcPolicyCn}"/></textarea> 
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussSamCpy.cpyrhtPrtcPolicyDetailInqire.lastUpdtPnttm"/></th><!-- 등록일자 -->
			<td class="left">
				<c:out value="${result.lastUpdusrPnttm}"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_updt_cpyrhtprtcpolicycn('<c:out value="${result.cpyrhtId}"/>'); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyCnDelete.do'/>?cpyrhtId=<c:out value='${result.cpyrhtId}'/>" onclick="fn_egov_delete_cpyrhtprtcpolicycn('<c:out value="${result.cpyrhtId}"/>'); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do'/>" onclick="fn_egov_inqire_cpyrhtprtcpolicylist(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
	<input name="cpyrhtId" type="hidden" value="">
	
</div>

</form>

</body>
</html>
