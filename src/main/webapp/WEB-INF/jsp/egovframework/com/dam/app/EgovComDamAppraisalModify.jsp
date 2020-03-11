<%
 /**
  * @Class Name  : EgovComDamAppraisalModify.jsp
  * @Description : EgovComDamAppraisalModify 화면
  * @Modification Information
  * @
  * @  수정일            수정자             수정내용
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
		<title><spring:message code="comDamApp.comDamAppraisalModify.title"/></title><!-- 지식평가 수정 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="knoAppraisal" staticJavascript="false" xhtml="true" cdata="false"/>

		<script type="text/javaScript" language="javascript">
		<!--
		function initCalendar(){
			$("#appYmd").datepicker(
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
		function fn_egov_initl_KnoAppraisal(){
			initCalendar();
			// 첫 입력란에 포커스..
			knoAppraisal.appYmd.focus();

		}
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_KnoAppraisal(){
			location.href = "<c:url value='/dam/app/EgovComDamAppraisalList.do' />";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		function fn_egov_modify_KnoAppraisal(form){

			var ls_appYmd = knoAppraisal.appYmd.value;

			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateKnoAppraisal(form)){
					return;
				}else{
					if (knoAppraisal.appYmd.value !="")	{
						ls_appYmd = ls_appYmd.replace(/-/gi,"");
						knoAppraisal.appYmd.value = ls_appYmd;
					}
					form.submit();
				}
			}
		}

		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_KnoAppraisal();">
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comDamApp.comDamAppraisalModify.pageTop.title"/></h2><!-- 지식평가 수정 -->
	
		<form:form commandName="knoAppraisal" name="knoAppraisal" method="post">

		<input name="cmd" type="hidden" value="Modify">
		<input name="knoId" type="hidden" value="<c:out value='${knoAppraisal.knoId}'/>">
		<form:hidden path="orgnztId"/>
		<form:hidden path="knoTypeCd"/>
		<form:hidden path="knoNm"/>
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				   ${knoAppraisal.orgnztNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    ${knoAppraisal.knoTypeNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.knoNm"/> <span class="pilsu">*</span></th><!-- 지식명 -->
				<td class="left">
				   ${knoAppraisal.knoNm}
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.knoCn"/> <span class="pilsu">*</span></th><!-- 지식내용 -->
				<td class="left">
				   <textarea name="knoCn" class="textarea" title="<spring:message code="comDamApp.comDamAppraisalModify.knoCn"/>" cols="300" rows="10" style="width:450px;" readonly>${knoAppraisal.knoCn}</textarea><!-- 지식내용 -->
				</td>
			</tr>
			<!-- 첨부목록을 보여주기 위한 -->
			<c:if test="${knoAppraisal.atchFileId ne null && knoAppraisal.atchFileId ne ''}">
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.atchFileList"/></th><!-- 첨부파일 목록 -->
				<td class="left">
				    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
						<c:param name="param_atchFileId" value="${knoAppraisal.atchFileId}" />
					</c:import>
				</td>
			</tr>
			</c:if>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.appYmd"/> <span class="pilsu">*</span></th><!-- 평가일자 -->
				<td class="left">
					<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
					<input id="appYmd" name="appYmd" type="text" title="<spring:message code="comDamApp.comDamAppraisalModify.appYmd"/>" value="${knoAppraisal.appYmd}"  readonly="readonly" style="width:70px" /><!-- 평가일달력 -->
					<div><form:errors path="appYmd"/></div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamApp.comDamAppraisalModify.knoAps"/> <span class="pilsu">*</span></th><!-- 평가결과 -->
				<td class="left">
					<select name="knoAps" title="<spring:message code="comDamApp.comDamAppraisalModify.knoAps"/>"><!-- 평가결과 선택 -->
						<option value="1" <c:if test="${knoAppraisal.knoAps == '1'}">selected</c:if> ><spring:message code="comDamApp.comDamAppraisalModify.status.approved"/></option><!-- 승인 -->
						<option value="2" <c:if test="${knoAppraisal.knoAps == '2'}">selected</c:if> ><spring:message code="comDamApp.comDamAppraisalModify.status.denied"/></option><!-- 반려 -->
					</select>
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_KnoAppraisal(document.forms[0]); return false;" /><!-- 저장 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_KnoAppraisal(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
		
		</form:form>
		
	</div>
	
	</body>
</html>