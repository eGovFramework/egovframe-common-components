<%
 /**
  * @Class Name : EgovBBSMasterUpdt.jsp
  * @Description : EgovBBSMasterUpdt 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  * @ 2016.06.13   김연호              표준프레임워크 v3.6 개선
  * @ 2018.10.15    최두영             표준프레임워크 V3.8 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title><!-- 게시판 마스터 수정 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.getElementById("boardMasterVO").bbsNm.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_bbs(form, bbsId){
	if (!validateBoardMasterVO(form)) {		 			
		return false;		
	} else {
		
		var validateForm = document.getElementById("boardMasterVO");

		//방명록 게시판의 경우 답장 불가, 파일첨부 불가
		if(validateForm.bbsTyCode.value == 'BBST03') {
			if(validateForm.replyPosblAt.value == 'Y') {
				alert("<spring:message code="comCopBbs.boardMasterVO.guestReply" />");
				return;
			}
			if(validateForm.fileAtchPosblAt.value == 'Y') {
				alert("<spring:message code="comCopBbs.boardMasterVO.guestFile" />");
				return;
			}
		} else {
			if(validateForm.fileAtchPosblAt.value == 'Y' && validateForm.atchPosblFileNumber.value == '0') {
				alert('첨부가능파일숫자를 선택하세요.');
				return;
			}
		}
		
		if(confirm("<spring:message code="common.update.msg" />")){	
			form.submit();	
		}					
	}	
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_bbslist() {
	boardMasterVO.action = "<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>";
	boardMasterVO.submit();	
}
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀 -->
<form:form commandName="boardMasterVO" action="${pageContext.request.contextPath}/cop/bbs/updateBBSMaster.do" method="post" onSubmit="fn_egov_updt_bbs(document.forms[0]); return false;">  
<div class="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.update" /></h2><!-- 게시판 마스터 수정 -->

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 게시판명 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.bbsNm"/> </c:set>
		<tr>
			<th><label for="bbsNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="bbsNm" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="bbsNm" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 게시판 소개내용 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.bbsIntrcn"/> </c:set>
		<tr>
			<th><label for="bbsIntrcn">${title} <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="bbsIntrcn" title="${title} ${inputTxt}" cols="300" rows="20"/>
				<div><form:errors path="bbsIntrcn" cssClass="error" /></div>  
			</td>
		</tr>
		
		<!-- 게시판 유형 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.bbsTyCode"/> </c:set>
		<tr>
			<th><label for="bbsTyCode">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="bbsTyCode" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value='' label="--선택하세요--" />
				   <form:options items="${bbsTyCode}" itemValue="code" itemLabel="codeNm" />
				</form:select>
				<div><form:errors path="bbsTyCode" cssClass="error" /></div>       
			</td>
		</tr>
		
		
		<!-- 파일첨부가능여부 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.fileAtchPosblAt"/> </c:set>
		<tr>
			<th><label for="fileAtchPosblAt">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="fileAtchPosblAt" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value='' label="--선택하세요--" />
					<form:option value="Y"  label="예" />
	  		   		<form:option value='N'>아니오</form:option>
				</form:select>
				<div><form:errors path="fileAtchPosblAt" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 첨부가능파일숫자 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.atchPosblFileNumber"/> </c:set>
		<tr>
			<th><label for="atchPosblFileNumber">${title } <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="atchPosblFileNumber" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="0" selected="selected">없음</form:option>
					<form:option value='1'>1</form:option>
	  		   		<form:option value='2'>2</form:option>
	  		   		<form:option value='3'>3</form:option>
				</form:select>
				<div><form:errors path="atchPosblFileNumber" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 추가 선택사항 -->
		<tr>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.option" /></th><!-- 추가 선택사항 -->
			<td class="left">
				<select name="option" class="select" <c:if test="${boardMasterVO.option != 'na'}">disabled="disabled"</c:if> title="추가선택사항">
					<option value='na' <c:if test="${boardMasterVO.option == 'na'}">selected="selected"</c:if>>---<spring:message code="input.select" />--</option>
					<option value='' <c:if test="${boardMasterVO.option == ''}">selected="selected"</c:if>><spring:message code="comCopBbs.boardMasterVO.detail.option1" /></option>
					<c:if test="${useComment == 'true' }">
						<option value='comment' <c:if test="${boardMasterVO.option == 'comment'}">selected="selected"</c:if>><spring:message code="comCopBbs.boardMasterVO.detail.option2" /></option>
					</c:if>
					<c:if test="${useSatisfaction == 'true' }">
						<option value='stsfdg' <c:if test="${boardMasterVO.option == 'stsfdg'}">selected="selected"</c:if>><spring:message code="comCopBbs.boardMasterVO.detail.option3" /></option>
					</c:if>
				</select>
				<spring:message code="comCopBbs.boardMasterVO.detail.option.unabledToModify" /><!-- ※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함) -->
			</td>
		</tr>

		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.useAt"/> </c:set>
		<tr>
			<th><label for="useAt">${title } <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="useAt" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:option value="Y"  label="예" />
	  		   		<form:option value='N'>아니오</form:option>
				</form:select>
				<div><form:errors path="useAt" cssClass="error" /></div>       
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
		<span class="btn_s"><a href="<c:url value='/cop/bbs/selectBBSMasterInfs.do' /><c:if test='${boardMasterVO.cmmntyId != null}'>?cmmntyId=${boardMasterVO.cmmntyId}</c:if>"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	 
</div>

<!-- validator 값 체크용 -->
<input name="replyPosblAt" type="hidden" value="<c:out value='${boardMasterVO.replyPosblAt}'/>">
<input name="cmmntyId" type="hidden" value="<c:out value='${boardMasterVO.cmmntyId}'/>">
<input name="bbsId" type="hidden" value="<c:out value='${boardMasterVO.bbsId}'/>">
</form:form>

</body>
</html>
