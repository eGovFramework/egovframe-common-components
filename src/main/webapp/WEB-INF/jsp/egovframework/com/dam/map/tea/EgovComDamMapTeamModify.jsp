<%
 /**
  * @Class Name  : EgovComDamMapTeamModify.jsp
  * @Description : EgovComDamMapTeamModify 화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2010.06.30  박종선          최초 생성
  *   2018.08.03  신용호          fn_egov_remove_string을 replace function으로 삭제
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
		<title><spring:message code="comDamMapTea.comDamMapTeamModify.title"/></title><!-- 지식맵(조직별) 수정 -->
		<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
		<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
		<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
		<validator:javascript formName="mapTeam" staticJavascript="false" xhtml="true" cdata="false"/>

		<script type="text/javaScript" language="javascript">
		<!--
		function initCalendar(){
			$("#clYmd").datepicker(
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
		function fn_egov_initl_MapTeam(){
			initCalendar();
			// 첫 입력란에 포커스..
			mapTeam.orgnztNm.focus();
		}

		/* ********************************************************
		 * 목록 으로 가기
		 ******************************************************** */
		function fn_egov_list_MapTeam(){
			location.href = "<c:url value='/dam/map/tea/EgovComDamMapTeamList.do'/>";
		}
		/* ********************************************************
		 * 저장처리화면
		 ******************************************************** */
		function fn_egov_modify_MapTeam(form){

			var ls_clYmd = mapTeam.clYmd.value;

			/*if (mapTeam.clYmd.value !="")	{
				ls_clYmd = ls_clYmd.replace(/-/gi,"");
				mapTeam.clYmd.value = ls_clYmd;
			}*/

			if(confirm("<spring:message code="common.save.msg" />")){
				if(!validateMapTeam(form)){
					return;
				}else{
					if (mapTeam.clYmd.value !="")	{
						ls_clYmd = ls_clYmd.replace(/-/gi,"");
						mapTeam.clYmd.value = ls_clYmd;
					}
					form.submit();
				}
			}
		}

		-->
		</script>
	</head>

	<body onLoad="fn_egov_initl_MapTeam();">
	
	<form:form commandName="mapTeam" name="mapTeam" method="post">
		<form:hidden path="orgnztId"/>
		<input name="cmd" type="hidden" value="Modify">
		
		<div class="wTableFrm">
			<!-- 타이틀 -->
			<h2><spring:message code="comDamMapTea.comDamMapTeamModify.pageTop.title"/></h2><!-- 지식맵(조직별) 수정 -->
		
			<!-- 등록폼 -->
			<table class="wTable">
				<colgroup>
					<col style="width:16%" />
					<col style="" />
				</colgroup>
				<tr>
					<th><spring:message code="comDamMapTea.comDamMapTeamModify.orgnztId"/> <span class="pilsu">*</span></th><!-- 조직ID -->
					<td class="left">
					    ${mapTeam.orgnztId}
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapTea.comDamMapTeamModify.orgnztNm"/> <span class="pilsu">*</span></th><!-- 조직명 -->
					<td class="left">
					    <form:input  path="orgnztNm" title="<spring:message code='comDamMapTea.comDamMapTeamModify.orgnztNm'/>" size="60" maxlength="20"/><!-- 조직분류명 -->
						<form:errors path="orgnztNm"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapTea.comDamMapTeamModify.knoUrl"/> <span class="pilsu">*</span></th><!-- 지식URL -->
					<td class="left">
					    <form:input  path="knoUrl" title="<spring:message code='comDamMapTea.comDamMapTeamModify.knoUrl'/>" size="60" maxlength="100"/><!-- 지식URL -->
						<form:errors path="knoUrl"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comDamMapTea.comDamMapTeamModify.clYmd"/> <span class="pilsu">*</span></th><!-- 분류일자 -->
					<td class="left">
					    <input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
						<input id="clYmd" name="clYmd" type="text" title="<spring:message code="comDamMapTea.comDamMapTeamModify.clYmd"/>" value="${mapTeam.clYmd}"  readonly="readonly" style="width:70px" /><!-- 분류일달력 -->
						<div><form:errors path="clYmd"/></div>
					</td>
				</tr>
			</table>
		
			<!-- 하단 버튼 -->
			<div class="btn">
				<input class="s_submit" type="submit" value="저장" onclick="fn_egov_modify_MapTeam(document.mapTeam); return false;" /><!-- 저장 -->
				<input class="s_submit" type="submit" value='<spring:message code="button.list" />' onclick="fn_egov_list_MapTeam(); return false;" /><!-- 목록 -->
			</div>
			<div style="clear:both;"></div>
		</div>
		
		</form:form>
		
	</body>
</html>