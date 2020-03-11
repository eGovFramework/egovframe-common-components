<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% 
 /**
  * @Class Name : EgovNcrdInqirePopup.jsp
  * @Description : 명함정보조회팝업	
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.30   이삼섭           최초 생성
  * @ 2018.09.13   최두영          다국어처리
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.30
  *  @version 1.0
  *  @see
  *  
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopNcm.ncrdInqirePopup.title" /></title><!-- 명함정보조회 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">

<script type="text/javascript">
function fn_egov_delete_ncrdInf(){
	if (confirm('<spring:message code="common.delete.msg" />')) {
		document.nameCard.action = "<c:url value='/cop/ncm/deleteNcrdInf.do'/>?ncrdId=<c:out value='${ncrdVO.ncrdId}'/>";
		document.nameCard.submit();					
	}
}
</script>
</head>
<body>
<form:form commandName="nameCard" name="nameCard" method="post" >

<div class="board">
	<!-- 타이틀 -->
	<h1><spring:message code="comCopNcm.ncrdInqirePopup.title" /></h1><!-- 명함정보조회 -->
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="cop.ncrdNm" /></th><!-- 이름 -->
			<td class="left"colspan="3"><c:out value="${ncrdVO.ncrdNm}" />  &nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="cop.cmpnyNm" /></th><!-- 회사명 -->
			<td class="left"><c:out value="${ncrdVO.cmpnyNm}" />  &nbsp;</td>
			<th><spring:message code="cop.deptNm" /></th><!-- 부서명 -->
			<td class="left"><c:out value="${ncrdVO.deptNm}" />  &nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="cop.ofcpsNm" /></th><!-- 직위 -->
			<td class="left"><c:out value="${ncrdVO.ofcpsNm}" /> &nbsp;</td>
			<th><spring:message code="cop.clsfNm" /></th><!-- 직급 -->
			<td class="left"><c:out value="${ncrdVO.clsfNm}" />  &nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="cop.emailAdres" /></th><!-- 이메일주소 -->
			<td class="left"><c:out value="${ncrdVO.emailAdres}" />  &nbsp;</td>
			<th><spring:message code="cop.telNo" /></th><!-- 전화번호 -->
			<td class="left"><c:out value="${ncrdVO.nationNo}" />-<c:out value="${ncrdVO.areaNo}" />-<c:out value="${ncrdVO.middleTelNo}" />-<c:out value="${ncrdVO.endTelNo}" />  &nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="cop.mbtlNum" /></th><!-- 휴대폰번호 -->
			<td class="left" colspan="3"><c:out value="${ncrdVO.idntfcNo}" />-<c:out value="${ncrdVO.middleMbtlNum}" />-<c:out value="${ncrdVO.endMbtlNum}" />  &nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="cop.adres" /></th><!-- 주소 -->
			<td class="left" colspan="3"><c:out value="${ncrdVO.adres}" /> &nbsp;</td>
		</tr>
		<tr>
			<th><spring:message code="cop.extrlUserAt" /></th><!-- 외부사용자여부 -->
			<td class="left" colspan="3">
				<c:choose>
			    	<c:when test="${ncrdVO.extrlUserAt == 'Y'}">
			    		<spring:message code="cop.extrlUser" />
			    	</c:when>
			    	<c:otherwise>
			    		<spring:message code="cop.intrlUser" />
			    	</c:otherwise>
			    </c:choose>
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.remark" /></th><!-- 비고 -->
			<td class="left" colspan="3"><c:out value="${ncrdVO.remark}" /> &nbsp;</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 --> 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/cop/ncm/selectNcrdInfs.do'/>"><spring:message code='button.list' /></a></span> <!-- 목록 -->
		<span class="btn_s"><a href="<c:url value='/cop/ncm/deleteNcrdInf.do'/>?ncrdId=<c:out value='${ncrdVO.ncrdId}'/>" onclick="fn_egov_delete_ncrdInf(); return false;"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
	</div>
</div>

</form:form>
</body>
</html>
