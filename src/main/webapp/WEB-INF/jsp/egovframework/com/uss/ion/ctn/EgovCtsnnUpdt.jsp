<%
/**
 * @Class Name  : EgovCtsnnUpdt.java
 * @Description : EgovCtsnnUpdt.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.16    최 두 영     퍼블리싱 점검/오류개선
 * @ 2018.09.18    최 두 영     다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<title><spring:message code="comUssIonCtn.ctsnnUpdt.title"/></title><!-- 경조사 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="ctsnnManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

	function initCalendar(){
		$("#occrrDe").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
		         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
		         , buttonImageOnly: true
		        
		         , showMonthAfterYear: true
		         , showOtherMonths: true
			     , selectOtherMonths: true
				
		         , changeMonth: true // 월선택 select box 표시 (기본은 false)
		         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
		         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
		});		
		
		$("#brth").datepicker(
		        {dateFormat:'yy-mm-dd'
		         , showOn: 'button'
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
 * 목록 으로 가기
 ******************************************************** */
function fncEgovCtsnnManageList(){
	location.href = "<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fncUpdtCtsnnManage() {
	    var varFrom = document.getElementById("ctsnnManage");
	    varFrom.action = "<c:url value='/uss/ion/ctn/updtCtsnnManage.do'/>";

	    var ctsnnNm = varFrom.ctsnnNm.value;
	    if(ctsnnNm == null || ctsnnNm == "") {
	    	alert("<spring:message code="comUssIonCtn.ctsnnUpdt.EnterCtsnnNm"/>"); //경조명을 입력하세요.
	    	return;
	    }

	    var trgterNm = varFrom.trgterNm.value;
	    if(trgterNm == null || trgterNm == "") {
	    	alert("<spring:message code="comUssIonCtn.ctsnnUpdt.EnterTrgterNm"/>"); //대상자명을 입력하세요.
	    	return;
	    }

	    if(confirm("<spring:message code="common.save.msg" />")){/* 저장하시겠습니까? */
	       if(!validateCtsnnManage(varFrom)){
	    	   return;
	       }else{
	           varFrom.submit();
	       } 
	    }
	}

</script>
</head>

<body onLoad="initCalendar();">
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form commandName="ctsnnManage" name="ctsnnManage" method="post">
<form:hidden path="ctsnnId"/>
<form:hidden path="usid"/>
<form:hidden path="reqstDe"/>
<form:hidden path="infrmlSanctnId"/>
<form:hidden path="sanctnerId"/>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonCtn.ctsnnUpdt.title"/></h2><!-- 경조사 수정 -->
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnUpdt.ctsnnAplyr"/></h3><!-- 경조 신청자 -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.usNm"/> <span class="pilsu">*</span></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.usNm}'/>
			    <input type="hidden" name="usNm" id="usNm" value="${ctsnnManageVO.usNm}"/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.usOrgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.orgnztNm}'/>
			</td>
		</tr>
	</table>

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.ctsnnNm"/> <span class="pilsu">*</span></th><!-- 경조명 -->
			<td class="left" colspan="3">
			    <form:input path="ctsnnNm" maxlength="100" title="경조명"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.ctsnnCd"/> <span class="pilsu">*</span></th><!-- 경조구분 -->
			<td class="left">
			    <form:select path="ctsnnCd" title="경조구분">
			      <form:options items="${ctsnnCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.occrrDe"/> <span class="pilsu">*</span></th><!-- 발생일 -->
			<td class="left">
			<form:input path="occrrDe" title="경조 발생일" maxlength="10" readonly="true" style="width:70px"/>
			      <%-- <form:hidden path="occrrDe" />
			      <input name="occrrDeView" id="occrrDeView" type="text" value="<c:out value="${ctsnnManageVO.occrrDe  }"/>"  readonly="readonly" title="경조 발생일"
			      	onclick="fn_egov_NormalCalendar(document.ctsnnManage, document.ctsnnManage.occrrDe, document.ctsnnManage.occrrDeView);"  style="width:70px" /> --%>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.trgterNm"/> <span class="pilsu">*</span></th><!-- 대상자명 -->
			<td class="left" colspan="3">
			    <form:input path="trgterNm" maxlength="20" title="대상자명"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.relate"/> <span class="pilsu">*</span></th><!-- 관계 -->
			<td class="left">
			    <form:select path="relate" title="관계">
			      <form:options items="${relateCodeList}" itemValue="code" itemLabel="codeNm"/>
		      </form:select>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.brth"/> <span class="pilsu">*</span></th><!-- 생년월일 -->
			<td class="left">
			      <form:input path="brth" title="생년월일" maxlength="10" readonly="true"  style="width:70px"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnUpdt.remark"/></th><!-- 비고 -->
			<td class="left" colspan="3">
			    <form:textarea path="remark" rows="4" cols="70" cssClass="txaClass" title="비고"/>
      			<form:errors   path="remark"/>
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnUpdt.infrmlSanctnId"/></h3><!-- 결재권자  -->
	
	<!-- 결재권자 정보 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8">
		<c:param name="infrmlSanctnId" value="${ctsnnManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 정보 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtCtsnnManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/ctn/selectCtsnnManageList.do'/>?searchCondition=1" onclick="fncEgovCtsnnManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
</body>
</html>