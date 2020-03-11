<%
 /**
  * @Class Name  : EgovComDamSpecialistModify.jsp
  * @Description : EgovComDamSpecialistModify 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.06.30  박종선          최초 생성
  * @ 2018.08.03  신용호          fn_egov_remove_string을 replace function으로 삭제
  *   2018.09.11  신용호          공통컴포넌트 3.8 개선
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
		<title><spring:message code="comDamSpeSpe.comDamSpecialistModify.title"/></title><!-- 지식전문가 수정 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="knoSpecialist" staticJavascript="false" xhtml="true" cdata="false"/>

		<script type="text/javaScript" language="javascript">
		<!--
		function initCalendar(){
			$("#speConfmDe").datepicker(
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
		function fn_egov_initl_KnoSpecialist(){
			initCalendar();
			// 첫 입력란에 포커스..
			knoSpecialist.appTypeNm.focus();
		}

		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_KnoSpecialist(){
			location.href = "<c:url value='/dam/spe/spe/EgovComDamSpecialistList.do'/>";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		function fn_egov_modify_KnoSpecialist(form){

			var ls_speConfmDe = knoSpecialist.speConfmDe.value;

			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateKnoSpecialist(form)){
					return;
				}else{
					if (knoSpecialist.speConfmDe.value !="")	{
						ls_speConfmDe = ls_speConfmDe.replace(/-/gi,"");
						knoSpecialist.speConfmDe.value = ls_speConfmDe;
					}
					form.submit();
				}
			}
		}

		/* ********************************************************
		 * 사용자검색 팝업
		 ******************************************************** */
		function fn_egov_inqire_user(){
			var retVal;
			var url = "<c:url value='/cop/com/openPopup.do?requestUrl=/cop/com/selectUserList.do&width=850&height=360'/>";
			var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

			retVal = window.showModalDialog(url,"p_userInqire", openParam);

			if(retVal != null){
				var tmp = retVal.split("|");
				document.knoSpecialist.speId.value = tmp[0];
				document.knoSpecialist.userNm.value = tmp[1];
			} 
		}
		
		function showModalDialogCallback(retVal) {
			if(retVal != null){
				var tmp = retVal.split("|");
				document.knoSpecialist.speId.value = tmp[0];
				document.knoSpecialist.userNm.value = tmp[1];
			}	
		}
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_KnoSpecialist();">
	
	<form:form commandName="knoSpecialist" name="knoSpecialist" method="post">
			<form:hidden path="orgnztId"/>
			<form:hidden path="knoTypeCd"/>
			<form:hidden path="speId"/>
			<form:hidden path="appTypeCd"/>
			<input name="cmd" type="hidden" value="Modify">
			
			<div class="wTableFrm">
				<!-- 타이틀 -->
				<h2><spring:message code="comDamSpeSpe.comDamSpecialistModify.pageTop.title"/></h2><!-- 지식전문가 수정 -->
			
				<!-- 등록폼 -->
				<table class="wTable">
					<colgroup>
						<col style="width:16%" />
						<col style="" />
					</colgroup>
					<tr>
						<th><spring:message code="comDamSpeSpe.comDamSpecialistModify.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
						<td class="left">
						    ${knoSpecialist.orgnztNm}
						</td>
					</tr>
					<tr>
						<th><spring:message code="comDamSpeSpe.comDamSpecialistModify.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
						<td class="left">
						    ${knoSpecialist.knoTypeNm}
						</td>
					</tr>
					<tr>
						<th><spring:message code="comDamSpeSpe.comDamSpecialistModify.expertNm"/> <span class="pilsu">*</span></th><!-- 전문가명 -->
						<td class="left">
						    ${knoSpecialist.userNm}
						</td>
					</tr>
					<tr>
						<th><spring:message code="comDamSpeSpe.comDamSpecialistModify.rank"/> <span class="pilsu">*</span></th><!-- 등급 -->
						<td class="left">
						    <select name="appTypeNm" title="<spring:message code="comDamSpeSpe.comDamSpecialistModify.rank"/>">
							<option value="1" <c:if test="${knoSpecialist.appTypeCd == '1'}">selected</c:if> ><spring:message code="comDamSpeSpe.comDamSpecialistModify.rankType1"/></option><!-- 수석 -->
							<option value="2" <c:if test="${knoSpecialist.appTypeCd == '2'}">selected</c:if> ><spring:message code="comDamSpeSpe.comDamSpecialistModify.rankType2"/></option><!-- 책임 -->
							<option value="3" <c:if test="${knoSpecialist.appTypeCd == '3'}">selected</c:if> ><spring:message code="comDamSpeSpe.comDamSpecialistModify.rankType3"/></option><!-- 선임 -->
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="comDamSpeSpe.comDamSpecialistModify.speExpCn"/> <span class="pilsu">*</span></th><!-- 전문가설명 -->
						<td class="left">
						    <textarea name="speExpCn" class="textarea" title="<spring:message code="comDamSpeSpe.comDamSpecialistModify.speExpCn"/>" cols="300" rows="10" style="height:140px">${knoSpecialist.speExpCn}</textarea><!-- 전문가설명 -->
						</td>
					</tr>
					<tr>
						<th><spring:message code="comDamSpeSpe.comDamSpecialistModify.speConfmDe"/> <span class="pilsu">*</span></th><!-- 승인일자 -->
						<td class="left">
						    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
							<input id="speConfmDe" name="speConfmDe" type="text" title="<spring:message code="comDamSpeSpe.comDamSpecialistModify.speConfmDe"/>" value="${knoSpecialist.speConfmDe}"  readonly="readonly" style="width:70px"/><!-- 승인일자달력 -->
							<div><form:errors path="speConfmDe"/></div>
						</td>
					</tr>
				</table>
			
				<!-- 하단 버튼 -->
				<div class="btn">
					<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_KnoSpecialist(document.knoSpecialist); return false;" /><!-- 저장 -->
					<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_KnoSpecialist(); return false;" /><!-- 목록 -->
				</div>
				<div style="clear:both;"></div>
			</div>
			
		</form:form>
		
	</body>
</html>