<%
 /**
  * @Class Name  : EgovComDamMapMaterialRegist.jsp
  * @Description : EgovComDamMapMaterialRegist 화면
  * @Modification Information
  * @
  * @ 수정일             수정자           수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.08.12  박종선          최초 생성
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
		<title><spring:message code="comDamMapMat.comDamMapMaterialRegist.title"/></title><!-- 지식맵(지식유형) 등록 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="mapMaterial" staticJavascript="false" xhtml="true" cdata="false"/>
		<script type="text/javaScript" language="javascript">
		<!--
		var knoTypeCdCheckState = 0;

		function initCalendar(){
			$("#vclYmd").datepicker(
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
			
			$("#vclYmd").change(function() {
				$("#clYmd").val($(this).val().replace(/-/gi,""));
			});
		}

		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_MapMaterial(){
			initCalendar();
			// 첫 입력란에 포커스..
			mapMaterial.orgnztId.focus();
			
			$("#btnKnoTypeCd").click(function() {
				if ($("#knoTypeCd").val() == "" || $("#knoTypeCd").val() == null) {
					alert("지식유형코드를 입력해주세요.");
					return;
				} else {
					fn_egov_knoTypeCd_check();
				}
			});
		}		
		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_MapMaterial(){
			location.href = "<c:url value='/dam/map/mat/EgovComDamMapMaterialList.do'/>";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		 function fn_egov_regist_MapMaterial(form){
			var knoTypeCd = $("#knoTypeCd").val();
			if ($("#knoTypeCd").val() == "" || $("#knoTypeCd").val() == null) {
				alert("지식유형코드를 입력해주세요.");
				return;
			}
			if (knoTypeCdCheckState == 0) {
				alert("지식유형코드 중복확인 후 저장하실 수 있습니다.");
				return;
			}

			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateMapMaterial(form)){ 			
					return;
				}else{
					form.submit();
				}
			}
		}
		
		function fn_egov_knoTypeCd_check(){	
			$.ajax({
				type : "POST",
				url : "<c:url value='/dam/map/mat/EgovKnoTypeCdCheckAjax.do'/>",
				data : { "knoTypeCd": $("#knoTypeCd").val() },
				dataType : 'json',
				success : function(returnData, status) {
					if(status == "success") {
						if(returnData.checkCount > 0 ) {
							alert("입력하신 지식유형코드는 이미 사용중입니다.");
							knoTypeCdCheckState = 0;
							return;
						} else {
							alert("입력하신 지식유형코드는 사용하실 수 있습니다.");
							$("#knoTypeCd").attr("readonly","readonly");
							knoTypeCdCheckState = 1;
							return;
						}
					} else{
						alert("ERROR!");
						return;
					} 
				}
			});
		}
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_MapMaterial();">
	
	<form:form modelAttribute="mapMaterial" name="mapMaterial" method="post">
	
		<div class="wTableFrm">
			<!-- 타이틀 -->
			<h2><spring:message code="comDamMapMat.comDamMapMaterialRegist.title"/></h2><!-- 지식맵(지식유형) 등록 -->
		
			<!-- 등록폼 -->
			<table class="wTable">
				<colgroup>
					<col style="width:16%" />
					<col style="" />
				</colgroup>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialRegist.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
					<td class="left">
					    <select name="orgnztId" class="select" title="<spring:message code="comDamMapMat.comDamMapMaterialRegist.orgnztNm"/>"><!-- 조직명 -->
						<c:forEach var="mapMaterial" items="${mapTeam}" varStatus="status">
						<option value='<c:out value="${mapMaterial.orgnztId}"/>'><c:out value="${mapMaterial.orgnztNm}"/></option>
						</c:forEach>			  		   
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialRegist.knoTypeCd"/> <span class="pilsu">*</span></th><!-- 지식유형코드 -->
					<td class="left">
					    <form:input  path="knoTypeCd" title="<spring:message code='comDamMapMat.comDamMapMaterialRegist.knoTypeCd'/>" maxlength="3" style="width:200px"/><!-- 지식유형코드 -->
						<form:errors path="knoTypeCd"/>
						<button id="btnKnoTypeCd" class="btn_s2" onclick="return false;" title="지식유형코드 중복확인">중복확인</button>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialRegist.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
					<td class="left">
					    <form:input  path="knoTypeNm" title="<spring:message code='comDamMapMat.comDamMapMaterialRegist.knoTypeNm'/>" size="60" maxlength="20"/><!-- 지식유형명 -->
						<form:errors path="knoTypeNm"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialRegist.knoUrl"/> <span class="pilsu">*</span></th><!-- 지식URL -->
					<td class="left">
					    <form:input  path="knoUrl" title="<spring:message code='comDamMapMat.comDamMapMaterialRegist.knoUrl'/>" size="60" maxlength="100"/><!-- 지식URL -->
						<form:errors path="knoUrl"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialRegist.clYmd"/> <span class="pilsu">*</span></th><!-- 분류일자 -->
					<td class="left">
					    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
						<input id="clYmd" name="clYmd" type="hidden" value=""/>
						<input id="vclYmd" name="vclYmd" type="text" title="<spring:message code="comDamMapMat.comDamMapMaterialRegist.clYmd"/>" value=""  maxlength="10" readonly="readonly" style="width:70px"/><!-- 분류일자 -->
					</td>
				</tr>
			</table>
		
			<!-- 하단 버튼 -->
			<div class="btn">
				<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_MapMaterial(document.mapMaterial); return false;" /><!-- 저장 -->
				<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_MapMaterial(); return false;" /><!-- 목록 -->
			</div>
			<div style="clear:both;"></div>
		</div>
		
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
		</form:form>
		
	</body>
</html>