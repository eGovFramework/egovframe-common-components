<%
/**
 * @Class Name  : EgovCtsnnConfm.java
 * @Description : EgovCtsnnConfm.jsp
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
<title><spring:message code="comUssIonCtn.ctsnnConfm.title"/></title><!-- 경조사승인 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ctsnnManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

	/* ********************************************************
	 * 승인목록 으로 가기
	 ******************************************************** */
	function fncEgovCtsnnConfmList(){
		location.href = "<c:url value='/uss/ion/ctn/EgovCtsnnConfmList.do'/>";
	}

	<c:if test="${ctsnnManageVO.confmAt eq 'A' }">
	
	var popUpCode=0;
	   
	function modalDialogCallback(retVal) {
		if(retVal != null){
			
			var tmp = retVal.split(",");		
			
			var varForm	= document.all["ctsnnManage"];
			
			varForm.returnResn.value = tmp[0];						
 			varForm.confmAt.value = tmp[1]; 
			
		    varForm.action = "<c:url value='/uss/ion/ctn/updtCtsnnConfm.do'/>";
			
			varForm.submit();
		}
	}
		
	function layerPopUp(){
				
           var pagetitle = $(this).attr("title");
           var page;
           
           if(popUpCode == 0){
				page = "<c:url value='/uss/ion/ism/EgovConfmPopupNew.do'/>";
           }else if(popUpCode == 1){
        	  	page = "<c:url value='/uss/ion/ism/EgovReturnPopupNew.do'/>";
           }else{
        	   alert("page Null")
           }
           
           var $dialog = $('<div></div>')
            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
            .dialog({
            	autoOpen: false,
                modal: true,
                height: 250,
                width: 800,
                title: pagetitle
        	});
       	$dialog.dialog('open');
	};
	
	$(document).ready(function () {	 
	        $('#confirmAgree').click(function (e) {
	        	popUpCode = 0;
	        	e.preventDefault();	            
	        	layerPopUp();
	    	});
	        $('#confirmDisAgree').click(function (e) {
	        	popUpCode = 1;
	        	e.preventDefault();	            
	        	layerPopUp();
	    	});
	});	    
</c:if>	
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonCtn.ctsnnConfm.title"/></h2><!-- 경조사 승인 -->

	<form:form modelAttribute="ctsnnManage" name="ctsnnManage" method="post" action="#LINK">
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonCtn.ctsnnConfm.submit"/>" title="<spring:message code="comUssIonCtn.ctsnnConfm.submit"/>"></div><!-- 전송 -->
	<form:hidden  path="ctsnnId"/>
	<form:hidden  path="usid"/>
	<form:hidden  path="ctsnnCd"/>
	<form:hidden  path="reqstDe"/>
	<form:hidden  path="infrmlSanctnId"/>
	<form:hidden  path="confmAt"/>
	<form:hidden  path="sanctnerId"/>
	<form:hidden  path="ctsnnNm"/>
	<form:hidden  path="occrrDe"/>
	<form:hidden  path="brth"/>
	<form:hidden  path="relate"/>
	<form:hidden  path="trgterNm"/>
	<form:hidden  path="returnResn"/>

	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnConfm.ctsnnAplyr"/></h3><!--경조신청자  -->
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.usNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.usNm}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.orgnztNm"/></th><!-- 소속 -->
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
			<th><spring:message code="comUssIonCtn.ctsnnConfm.ctsnnNm"/> <span class="pilsu">*</span></th><!-- 경조명 -->
			<td class="left" colspan="3">
			    <c:out value='${ctsnnManageVO.ctsnnNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.ctsnnCdNm"/> <span class="pilsu">*</span></th><!-- 경조구분 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.ctsnnCdNm}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.occrrDe"/> <span class="pilsu">*</span></th><!-- 발생일 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.occrrDe}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.trgterNm"/> <span class="pilsu">*</span></th><!-- 대상자명 -->
			<td class="left" colspan="3">
			    <c:out value='${ctsnnManageVO.trgterNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.relateNm"/> <span class="pilsu">*</span></th><!-- 관계 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.relateNm}'/>
			</td>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.brth"/> <span class="pilsu">*</span></th><!-- 생년월일 -->
			<td class="left">
			    <c:out value='${ctsnnManageVO.brth}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonCtn.ctsnnConfm.remark"/></th><!-- 비고 -->
			<td class="left" colspan="3">
			    <textarea id="remark" name="remark" class="txaClass" rows="4" cols="70" readonly title="<spring:message code="comUssIonCtn.ctsnnConfm.remark"/>"><c:out value='${ctsnnManageVO.remark}'/></textarea><!-- 비고 -->
			</td>
		</tr>
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonCtn.ctsnnConfm.infrmlSanctnId"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 정보 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8">
		<c:param name="infrmlSanctnId" value="${ctsnnManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 정보 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${ctsnnManageVO.confmAt eq 'A' }">
			<input  id="confirmAgree" class="s_submit" type="submit" value="<spring:message code="comUssIonCtn.ctsnnConfm.agree"/>" onclick="fncConfmCtsnnManage(); return false;" /><!-- 승인 -->
			<span class="btn_s"><a id="confirmDisAgree" title="<spring:message code="comUssIonCtn.ctsnnConfm.disagree"/>"><spring:message code="comUssIonCtn.ctsnnConfm.disagree"/></a></span><!-- 반려 -->
	    </c:if>
	    <span class="btn_s"><a href="<c:url value='/uss/ion/ctn/EgovCtsnnConfmList.do'/>?searchCondition=1" onclick="fncEgovCtsnnConfmList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</form:form>
</div>

</body>
</html>