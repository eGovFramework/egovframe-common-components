<%
 /**
  * @Class Name  : EgovComDamSpecialistRegist.jsp
  * @Description : EgovComDamSpecialistRegist 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.07.23  박종선          최초 생성
  *	  2011.08.12    정진오		"지식유형명" 항목 필수 항목 표시 image가 보이도록 수정함
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
		<title><spring:message code="comDamSpeSpe.comDamSpecialistRegist.title"/></title><!-- 지식전문가 등록 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js'/>" ></script>
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
		<validator:javascript formName="knoSpecialist" staticJavascript="false" xhtml="true" cdata="false"/>
		
		<script type="text/javaScript" language="javascript">
		<!--
		function initCalendar(){
			$("#vspeConfmDe").datepicker(
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
			
			$("#vspeConfmDe").change(function() {
				$("#speConfmDe").val($(this).val().replace(/-/gi,""));
			});
		}
		
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_KnoSpecialist(){
			initCalendar();
			// 첫 입력란에 포커스..
			knoSpecialist.orgnztId.focus();
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
		 function fn_egov_regist_KnoSpecialist(form){
			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateKnoSpecialist(form)){ 			
					return;
				}else{
					form.cmd.value = "Regist";
					form.action = "<c:url value='/dam/spe/spe/EgovComDamSpecialistRegist.do'/>";								
					form.submit();
				}
			}
		}		
		/* ********************************************************
		 * 사용자검색 팝업
		 ******************************************************** */
		function fn_egov_inqire_user(){
			var retVal;
			var url = "<c:url value='/cop/com/openPopup.do?requestUrl=/cop/com/selectUserList.do&width=850&height=700'/>";
			var openParam = "dialogWidth: 850px; dialogHeight: 550px; resizable: 0, scroll: 1, center: 1";

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
		/* ********************************************************
		 * 지식유형 가져오기
		 ******************************************************** */
		function fn_egov_get_CodeId(form){
		 	form.cmd.value = "";
		 	form.submit();
		}			
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_KnoSpecialist();">
	
	<form:form modelAttribute="knoSpecialist" name="knoSpecialist" method="post">
	
	<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2><spring:message code="comDamSpeSpe.comDamSpecialistRegist.pageTop.title"/></h2><!-- 지식전문가 등록 -->
	
		<!-- 등록폼 -->
		<table class="wTable">
			<colgroup>
				<col style="width:16%" />
				<col style="" />
			</colgroup>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistRegist.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
				<td class="left">
				    <select name="orgnztId" class="select" onchange="fn_egov_get_CodeId(document.knoSpecialist)">
					<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
					<c:forEach var="knoSpecialist" items="${mapTeamList}" varStatus="status">							
					<option value='<c:out value="${knoSpecialist.orgnztId}"/>' <c:if test="${knoSpecialist.orgnztId == mapMaterial.orgnztId}">selected="selected"</c:if> ><c:out value="${knoSpecialist.orgnztNm}"/></option>
					</c:forEach>			  		   
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistRegist.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
				<td class="left">
				    <select name="knoTypeCd" class="select">						
					<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
					<c:forEach var="knoSpecialist" items="${mapMaterialList}" varStatus="status">
					<option value='<c:out value="${knoSpecialist.knoTypeCd}"/>'><c:out value="${knoSpecialist.knoTypeNm}"/></option>
					</c:forEach>			  		   
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistRegist.expertNm"/> <span class="pilsu">*</span></th><!-- 전문가명 -->
				<td class="left">
				    <input name="userNm" type="text" value='<c:out value="${knoSpecialist.userNm}" />' readonly="readonly" maxlength="60" style="width:150px"/>
					<input name="speId" type="hidden" value='<c:out value="${knoSpecialist.speId}" />'>
					<a href="javascript:fn_egov_inqire_user()">
					<img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />" alt="search"/></a>
					<br/><form:errors path="userNm" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistRegist.rank"/> <span class="pilsu">*</span></th><!-- 등급 -->
				<td class="left">
				    <select name="appTypeCd" title="<spring:message code="comDamSpeSpe.comDamSpecialistRegist.rank"/>"><!-- 등급 선택 -->
					<option value="1"><spring:message code="comDamSpeSpe.comDamSpecialistRegist.rankType1"/></option><!-- 수석 -->
					<option value="2"><spring:message code="comDamSpeSpe.comDamSpecialistRegist.rankType2"/></option><!-- 책임 -->
					<option value="3"><spring:message code="comDamSpeSpe.comDamSpecialistRegist.rankType3"/></option><!-- 선임 -->
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistRegist.speExpCn"/> <span class="pilsu">*</span></th><!-- 전문가설명 -->
				<td class="left">
				    <form:textarea path="speExpCn" title="<spring:message code='comDamSpeSpe.comDamSpecialistRegist.speExpCn'/>" cols="100" rows="10" cssClass="txaClass"/><!-- 전문가설명 -->
					<form:errors path="speExpCn"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="comDamSpeSpe.comDamSpecialistRegist.speConfmDe"/> <span class="pilsu">*</span></th><!-- 승인일자 -->
				<td class="left">
				    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
					<input id="speConfmDe" name="speConfmDe" type="hidden" value=""/>
					<input id="vspeConfmDe" name="vspeConfmDe" type="text" title="<spring:message code="comDamSpeSpe.comDamSpecialistRegist.speConfmDe"/>" value=""  maxlength="10" readonly="readonly" style="width:70px"/><!-- 승인일자 -->
				</td>
			</tr>
		</table>
	
		<!-- 하단 버튼 -->
		<div class="btn">
			<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_KnoSpecialist(document.knoSpecialist); return false;" /><!-- 저장 -->
			<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_KnoSpecialist(); return false;" /><!-- 목록 -->
		</div>
		<div style="clear:both;"></div>
	</div>
	
	<!-- <input name="cmd" type="hidden" value="<c:out value='save'/>"> -->
	<input name="cmd" type="hidden" value="<c:out value='Regist'/>">		
	</form:form>
		
	</body>
</html>