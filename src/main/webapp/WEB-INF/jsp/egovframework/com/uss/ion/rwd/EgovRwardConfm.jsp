<%
/**
 * @Class Name  : EgovRwardConfm.java
 * @Description : EgovRwardConfm.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이용				최초 생성
 * @ 2018.09.19    최두영			다국어처리
 * @ 2024.10.29    권태성			debugger 코드 제거(modalDialogCallback())
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
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonRwd.rwardConfm.title"/></title><!-- 포상승인 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="rwardManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

	/* ********************************************************
	 * 포상승인목록 으로 가기
	 ******************************************************** */
	function fncEgovRwardConfmList(){
		location.href = "<c:url value='/uss/ion/rwd/EgovRwardConfmList.do'/>";
	}

	<c:if test="${rwardManageVO.confmAt eq 'A' }">
	
	var popUpCode=0;
	   
	function modalDialogCallback(retVal) {
		if(retVal != null){

			var tmp = retVal.split(",");		
			
			var varForm	= document.all["rwardManage"];
			
			varForm.returnResn.value = tmp[0];						
 			varForm.confmAt.value = tmp[1]; 
			
		    varForm.action = "<c:url value='/uss/ion/rwd/updtRwardConfm.do'/>";
			
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

<form:form modelAttribute="rwardManage" name="rwardManage" method="post" action="${pageContext.request.contextPath}/uss/ion/rwd/EgovRwardConfm.do">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonRwd.common.submit"/>" title="<spring:message code="comUssIonRwd.common.submit"/>"></div><!-- 전송 -->
<form:hidden  path="rwardId"/>
<form:hidden  path="rwardManId"/>
<form:hidden  path="rwardCd"/>
<form:hidden  path="rwardDe" value="${rwardManageVO.rwardDe}"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="confmAt"/>
<form:hidden  path="returnResn"/>
<form:hidden  path="rwardNm"/>
<form:hidden  path="pblenCn"/>
<form:hidden  path="sanctnerId"/>
<input type="hidden" name="posblAtchFileNumber" value="3" />
<input type="hidden" name="rwardManNm" id="rwardManNm" value="포상자" />
<input type="hidden" name="sanctnDtNm" id="sanctnDtNm" value="승인권자"/>
</form:form>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonRwd.rwardConfm.title"/></h2><!-- 포상승인 -->

	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonRwd.common.searchNm"/> <span class="pilsu">*</span></th><!-- 포상자 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardManNm}'/>
			</td>
			<th><spring:message code="comUssIonRwd.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${rwardManageVO.orgnztNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.searchKeyword"/> <span class="pilsu">*</span></th><!-- 포상구분 -->
			<td class="left" colspan="3">
			    <c:out value='${rwardManageVO.rwardCdNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.rwardNm"/> <span class="pilsu">*</span></th><!-- 포상명 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardNm}'/>
			</td>
			<th><spring:message code="comUssIonRwd.common.rwardDe"/> <span class="pilsu">*</span></th><!-- 포상일자 -->
			<td class="left">
			    <c:out value='${rwardManageVO.rwardDe}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRwd.common.pblenCn"/> <span class="pilsu">*</span></th><!-- 공적사항 -->
			<td class="left" colspan="3">
			    <textarea id="pblenCn" name="pblenCn" class="txaClass" rows="4" cols="70" title="공적사항" readOnly><c:out value='${rwardManageVO.pblenCn}'/></textarea>
			</td>
		</tr>
		<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
  		<c:if test="${result.atchFileId != ''}">
		<tr>
			<th><spring:message code="comUssIonRwd.rwardUpdt.atchFileIdList"/> <span class="pilsu">*</span></th><!-- 첨부파일목록 -->
			<td class="left" colspan="3">
			    <c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
					<c:param name="param_atchFileId" value="${egovc:encrypt(rwardManageVO.atchFileId)}" />
				</c:import>&nbsp
			</td>
		</tr>
		</c:if>	
		<!-- 첨부파일 테이블 레이아웃 End.-->
	</table>
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonRwd.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 정보 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8">
		<c:param name="infrmlSanctnId" value="${rwardManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 정보 Include -->
	
	<!-- 하단 버튼 -->
	<div class="btn">		
		<c:if test="${rwardManageVO.confmAt eq 'A' }">
			<input id="confirmAgree" class="s_submit" type="submit" value="<spring:message code="comUssIonRwd.common.confmAt.C"/>" title="<spring:message code="comUssIonRwd.common.confmAt.C"/>" /><!-- 승인 -->
			<span class="btn_s"><a id="confirmDisAgree" title="<spring:message code="comUssIonRwd.common.confmAt.R"/>"><spring:message code="comUssIonRwd.common.confmAt.R"/></a></span><!-- 반려 -->
		</c:if>
		<span class="btn_s"><a href="<c:url value='/uss/ion/rwd/EgovRwardConfmList.do'/>?searchCondition=1" onclick="fncEgovRwardConfmList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>