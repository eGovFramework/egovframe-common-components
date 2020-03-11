<%
 /**
  * @Class Name  : EgovComDamMapMaterialModify.jsp
  * @Description : EgovComDamMapMaterialModify 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.06.30  박종선          최초 생성
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
		<title><spring:message code="comDamMapMat.comDamMapMaterialModify.title"/></title><!-- 지식맵(유형별) 수정 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="mapMaterial" staticJavascript="false" xhtml="true" cdata="false"/>
		
		<script type="text/javaScript" language="javascript">
		<!--
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
		}
		
		/* ********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_initl_MapMaterial(){
			initCalendar();
			// 첫 입력란에 포커스..
			mapMaterial.knoTypeNm.focus();
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
		function fn_egov_modify_MapMaterial(form){
		
			var ls_clYmd = mapMaterial.clYmd.value;
		
			/*if (mapMaterial.clYmd.value !="")	{
				ls_clYmd = ls_clYmd.replace(/-/gi,"");
				mapMaterial.clYmd.value = ls_clYmd;
			}*/
			
			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateMapMaterial(form)){ 			
					return;
				}else{
					if (mapMaterial.clYmd.value !="")	{
					ls_clYmd = ls_clYmd.replace(/-/gi,"");;
					mapMaterial.clYmd.value = ls_clYmd;
					}					
					form.submit();
				}
			}
		}
		
		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_MapMaterial();">
	
	<form:form commandName="mapMaterial" name="mapMaterial" method="post">
			<input name="cmd" type="hidden" value="Modify">
			<form:hidden path="orgnztId"/>
			<form:hidden path="knoTypeCd"/>
	
	
		<div class="wTableFrm">
			<!-- 타이틀 -->
			<h2><spring:message code="comDamMapMat.comDamMapMaterialModify.pageTop.title"/></h2><!-- 지식맵(유형별) 수정 -->
		
			<!-- 등록폼 -->
			<table class="wTable">
				<colgroup>
					<col style="width:16%" />
					<col style="" />
				</colgroup>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialModify.orgnztId"/> <span class="pilsu">*</span></th><!-- 조직ID -->
					<td class="left">
					    ${mapMaterial.orgnztId}
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialModify.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
					<td class="left">
					    ${mapMaterial.orgnztNm}
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialModify.knoTypeCd"/> <span class="pilsu">*</span></th><!-- 지식유형코드 -->
					<td class="left">
					    ${mapMaterial.knoTypeCd}
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialModify.knoTypeNm"/> <span class="pilsu">*</span></th><!-- 지식유형명 -->
					<td class="left">
					    <form:input  path="knoTypeNm" title="<spring:message code='comDamMapMat.comDamMapMaterialModify.knoTypeNm'/>" size="60" maxlength="20"/>
						<form:errors path="knoTypeNm"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialModify.knoUrl"/> <span class="pilsu">*</span></th><!-- 지식URL -->
					<td class="left">
					    <form:input  path="knoUrl" title="<spring:message code='comDamMapMat.comDamMapMaterialModify.knoUrl'/>" size="60" maxlength="100"/>
						<form:errors path="knoUrl"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapMat.comDamMapMaterialModify.clYmd"/> <span class="pilsu">*</span></th><!-- 분류일자 -->
					<td class="left">
					    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />	  
						<input id="clYmd" name="clYmd" type="text" title="<spring:message code="comDamMapMat.comDamMapMaterialModify.clYmd"/>" value="${mapMaterial.clYmd}" readonly="readonly" style="width:70px" /><!-- 분류일자 -->
						<div><form:errors path="clYmd"/></div>
					</td>
				</tr>
			</table>
		
			<!-- 하단 버튼 -->
			<div class="btn">
				<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_MapMaterial(document.mapMaterial); return false;" /><!-- 저장 -->
				<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_MapMaterial(); return false;" /><!-- 목록 -->
			</div>
			<div style="clear:both;"></div>
		</div>
		
		</form:form>
	</body>
</html>