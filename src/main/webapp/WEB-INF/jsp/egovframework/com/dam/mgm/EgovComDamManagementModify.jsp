<%
 /**
  * @Class Name  : EgovComDamManagementModify.jsp
  * @Description : EgovComDamManagementModify 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2010.08.17  박종선             최초 생성
  * @ 2018.08.03  신용호             fn_egov_remove_string을 replace function으로 삭제
  * 
  *  @author 공통서비스팀 
  *  @since 2010.05.01
  *  @version 1.0
  *  @see
  *  
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title><spring:message code="comDamMgm.comDamManagementModify.title"/></title><!-- 지식정보 수정 -->
		<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="knoManagement" staticJavascript="false" xhtml="true" cdata="false"/>
		
		<script type="text/javaScript" language="javascript">
		<!--
		function initCalendar(){
			$("#junkYmd").datepicker(
			        {dateFormat:'yy-mm-dd'
			         , showOn: 'both'
			         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
			         , buttonImageOnly: true
			        
			         , showMonthAfterYear: true
			         , showOtherMonths: true
				     , selectOtherMonths: true
					
			         , changeMonth: true // 월선택 select box 표시 (기본은 false)
			         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
			         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
			});
		}

		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_KnoManagement(){
			initCalendar();
			// 첫 입력란에 포커스..
			knoManagement.knoAps.focus();

		}
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_KnoManagement(){
			location.href = "<c:url value='/dam/mgm/EgovComDamManagementList.do'/>";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		function fn_egov_modify_KnoManagement(form){
		
			var ls_junkYmd = knoManagement.junkYmd.value;
		
			if (knoManagement.junkYmd.value !="")	{
				ls_junkYmd = ls_junkYmd.replace(/-/gi,"");
				knoManagement.junkYmd.value = ls_junkYmd;
			}
			
			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateKnoManagement(form)){ 			
					return;
				}else{
					form.submit();
				}
			}
		}
		
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_KnoManagement();">
	
	<form:form modelAttribute="knoManagement" name="knoManagement" action="${pageContext.request.contextPath}/dam/mgm/EgovComDamManagementModify.do" method="post">	
						
	<input name="cmd" type="hidden" value="Modify">
	<input name="knoId" type="hidden" value="<c:out value='${knoManagement.knoId}'/>">
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comDamMgm.comDamManagementModify.pageTop.title"/></h2><!-- 지식정보 수정 -->
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				    ${knoManagement.orgnztNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    ${knoManagement.knoTypeNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.frstRegisterPnttm"/> <span class="pilsu">*</span></th><!-- 등록일자 -->
				<td class="left">
				    ${knoManagement.frstRegisterPnttm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.userNm"/> <span class="pilsu">*</span></th><!-- 등록자명 -->
				<td class="left">
				    ${knoManagement.userNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
				<td class="left">
				    ${knoManagement.knoNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
				<td class="left">
				    <textarea name="knoCn" class="textarea" title="<spring:message code="comDamMgm.comDamManagementModify.knoCn"/>" cols="300" rows="5" style="width:450px;" readonly>${knoManagement.knoCn}</textarea><!-- 지식내용 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.othbcAt"/> <span class="pilsu">*</span></th><!-- 공개여부 -->
				<td class="left">
				    <c:choose>
					<c:when test="${knoManagement.othbcAt == 'Y'}">
						<spring:message code="cop.public" />
					</c:when>
					<c:otherwise>
						<spring:message code="cop.private" />
					</c:otherwise>
				    </c:choose>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.appYmd"/> <span class="pilsu">*</span></th><!-- 평가일자 -->
				<td class="left">
				    <c:if test="${knoManagement.appYmd == null}"><spring:message code="comDamMgm.comDamManagementModify.status.proceeding"/></c:if><!-- 진행중 -->
		    		<c:if test="${knoManagement.appYmd != null}">${knoManagement.appYmd}</c:if>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.speNm"/> <span class="pilsu">*</span></th><!-- 평가자명 -->
				<td class="left">
				    ${knoManagement.speNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.knoAps"/> <span class="pilsu">*</span></th><!-- 평가결과 -->
				<td class="left">
				    <c:if test="${knoManagement.knoAps == null}"><spring:message code="comDamMgm.comDamManagementModify.status.evaluating"/></c:if><!-- 평가중 -->
				    <c:if test="${knoManagement.knoAps == '1'}"><spring:message code="comDamMgm.comDamManagementModify.status.approved"/></c:if><!-- 승인 -->
				    <c:if test="${knoManagement.knoAps == '2'}"><spring:message code="comDamMgm.comDamManagementModify.status.denied"/></c:if><!-- 반려 -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.knoAps"/> <span class="pilsu">*</span></th><!-- 지식정제 -->
				<td class="left">
				    <select name="knoAps" title="<spring:message code="comDamMgm.comDamManagementModify.knoAps"/>"><!-- 지식정제 선택 -->
					    <option value="3" <c:if test="${knoManagement.knoAps == '3'}">selected</c:if> ><spring:message code="comDamMgm.comDamManagementModify.status.disposal"/></option><!-- 폐기 -->
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.junkYmd"/></th><!-- 폐기일자 -->
				<td class="left">
				    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />	  
					<input id="junkYmd" name="junkYmd" type="text" title="<spring:message code="comDamMgm.comDamManagementModify.junkYmd"/>" value="${knoManagement.junkYmd}"  readonly="readonly" style="width:70px"/><!-- 폐기일자달력 -->
					<div><form:errors path="junkYmd"/></div>
				</td>
			</tr>
			<!-- 첨부목록을 보여주기 위한 -->  
			<c:if test="${knoManagement.atchFileId ne null && knoManagement.atchFileId ne ''}">
			<tr>
				<th><spring:message code="comDamMgm.comDamManagementModify.atchFileList"/></th><!-- 첨부파일 목록 -->
				<td class="left">
				    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
						<c:param name="param_atchFileId" value="${knoManagement.atchFileId}" />
					</c:import>
				</td>
			</tr>
			</c:if>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_KnoManagement(document.forms[0]); return false;" /><!-- 저장 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_KnoManagement(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
	</div>
		
	</form:form>
	</body>
</html>