<%
/**
 * @Class Name  : EgovEventRceptConfm.java
 * @Description : EgovEventRceptConfm jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 *   2011.08.17    	정진오		선택입력사항을 표시하는 이미지 icon 경로가
 * 								다른 패키지로 되어 있던 것을 당해 패키지 경로로 수정함   		    
 *
 *  @author 이      용
 *  @since 2010.07.20
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonEvt.eventRceptConfm.title"/></title><!-- 행사접수승인 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	 /*설명 : 행사승인목록 페이지 조회 */
	 function linkPage(pageNo){
		var varForm				 = document.all["listForm"];
		 if(varForm.searchMonth.value !=""){
			 if(varForm.searchYear.value ==""){
				 alert("<spring:message code="comUssIonEvt.common.validate.searchYearValue"/>");/* 전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요. */
				 return;
			 } 
		 }

		varForm.searchCondition.value = "1";
		varForm.pageIndex.value   = pageNo;
		varForm.action            = "<c:url value='/uss/ion/evt/selectEventRceptConfmList.do'/>";
	 	varForm.target           ="_self";
		varForm.submit();
	 }
	
	/* ********************************************************
	 * 조회 처리 
	 ******************************************************** */
	 /*설명 : 목록 조회 */
	 function fncSelectEventRceptConfm(pageNo){
		 var varForm			  = document.all["listForm"];

		 if(varForm.searchMonth.value !=""){
			 if(varForm.searchYear.value ==""){
				 alert("<spring:message code="comUssIonEvt.common.validate.searchYearValue"/>");/* 전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요. */
				 return;
			 } 
		 }
		 //varForm.searchCondition.value = "1";
		 varForm.pageIndex.value  = pageNo;
		 varForm.action           = "<c:url value='/uss/ion/evt/selectEventRceptConfmList.do'/>";
		 varForm.target           ="_self";
		 varForm.submit();
	 }
	
	var popUpCode=0;
	   
	function modalDialogCallback(retVal) {
		if(retVal != null){
			
			var tmp = retVal.split(",");		
			
			var varForm	= document.all["listForm"];
			
			varForm.returnResn.value = tmp[0];						
			varForm.cmd.value = tmp[1];
			
		    varForm.action = "<c:url value='/uss/ion/evt/updtEventAtdrn.do'/>";
			
			varForm.submit();
		}
	}
		
	function layerPopUp(){
		
		var varForm	= document.all["listForm"];
		
	   	varForm.checkedEventRceptForConfm.value = fncEventConfmProcess();
	   	
	   	if(varForm.checkedEventRceptForConfm.value == "F"){
	   	    alert("<spring:message code="comUssIonEvt.eventRceptConfm.validate.checkedEvent"/>");/* 승인처리할 대상이 존재하지 않습니다. 승인처리할 대상을 선택하신 후 승인처리 가능합니다. */
	       	return;
		}else{
		
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
		}
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
	
	 /* ********************************************************
	  * 멀티처리 프로세스
	  * fncEventConfmProcess
	  * param null
	  ******************************************************** */
	 function fncEventConfmProcess(){
		var varForm	       = document.all["listForm"];
	    var checkField        = varForm.eventRceptCheck;
	    var eventId           = varForm.eventId;
	    var applcntId         = varForm.applcntId;
	    var infrmlSanctnId    = varForm.infrmlSanctnId;
	    var reqstDe           = varForm.reqstDe;
	    var checkedEventRcept = "";
	    var checkedValue;
	    var checkedCount = 0;
	    if(checkField) {
	    	if(checkField.length > 1) {
	             for(var i=0; i < checkField.length; i++) {
	                 if(checkField[i].checked) {
	               	  	 checkedValue = checkField[i].value;
	               	  	 checkedEventRcept += ((checkedCount==0? "" : "$")+eventId[checkedValue].value+","+applcntId[checkedValue].value+","+infrmlSanctnId[checkedValue].value+","+reqstDe[checkedValue].value);
	                     checkedCount++;
	                 }
	             }
	             if(checkedCount == 0) checkedEventRcept = "F"; 
	         } else {
	             if(checkField.checked) {
	           	  	checkedValue = checkField.value;
	           	  	if(eventId.length > 1) checkedEventRcept = eventId[checkedValue].value+","+applcntId[checkedValue].value+","+infrmlSanctnId[checkedValue].value+","+reqstDe[checkedValue].value;
	           	  	else checkedEventRcept = eventId.value+","+applcntId.value+","+infrmlSanctnId.value+","+reqstDe.value;
	             }else{
	            	checkedEventRcept = "F"; 
	             }
	         }
	    }else checkedEventRcept = "F"; 
	    return checkedEventRcept;
	 }
	 
	 /* ********************************************************
	  * 행사 상세회면 팝업 호출함수
	  ******************************************************** */
	 function fncEventReqstDetailPop(eventId){
	 	var varForm				 = document.all["listFormPop"];
	 	var openParam            = "left=10, top=0, width=750, height=450";
	 	varForm.eventId.value    = eventId;
	 	varForm.cmd.value        = "popup";
	 	var myWin = window.open("about:blank","EventReqstDetailPop",openParam);
	 	
	 	varForm.method ="post";
	 	varForm.action = "<c:url value='/uss/ion/evt/EgovEventReqstDetail.do'/>";
	 	varForm.target ="EventReqstDetailPop";
	 	varForm.submit();
	 }

	 /* ********************************************************
	 * 체크 박스 선택 함수
	 ******************************************************** */
	 function fn_egov_checkAll(){

	 	var FLength = document.getElementsByName("eventRceptCheck").length;
	 	var checkAllValue = document.getElementById('checkAll').checked;

	 	//undefined
	 	if( FLength == 1){
		 	document.listForm.eventRceptCheck.checked = checkAllValue;
	 	}else if(FLength > 1){
 			for(var i=0; i < FLength; i++) {
 				document.getElementsByName("eventRceptCheck")[i].checked = checkAllValue;	
 			}
	 	}
	 }
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonEvt.eventRceptConfm.eventRcptMngList"/></h1><!-- 행사접수승인관리 목록 -->
		<form name="listFormPop" action="<c:url value='/uss/ion/evt/selectEventRceptConfmList.do'/>" method="post">
	<div style="visibility:hidden;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonEvt.common.submit"/>" title="<spring:message code="comUssIonEvt.common.submit"/>"></div><!-- 전송 -->
	    <input type="hidden" name="eventId">
	    <input type="hidden" name="cmd">
	</form>
	<form name="listForm" action="#" method="post">
	<input type="hidden" name="searchCondition">
    <input type="hidden" name="cmd">
    <input type="hidden" name="confmAt">
    <input type="hidden" name="checkedEventRceptForConfm">
    <input type="hidden" name="returnResn">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty eventManageVO.pageIndex }">1</c:if><c:if test="${!empty eventManageVO.pageIndex }"><c:out value='${eventManageVO.pageIndex}'/></c:if>">
	
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonEvt.common.eventTemp3"/> : </label><!-- 행사구분 -->
				<select name="searchSe" title="<spring:message code="comUssIonEvt.common.eventTemp3"/>"><!-- 행사구분 -->
		        	<option value="" <c:if test="${eventManageVO.searchSe eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
		            <c:forEach items="${eventSeCode}" var="result" varStatus="status">
			       	   <option value="<c:out value="${result.code }"/>" <c:if test="${eventManageVO.searchSe eq result.code }">selected</c:if>><c:out value="${result.codeNm }"/></option>
		            </c:forEach>
		      	</select>
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonEvt.common.eventYearMonth"/> : </label><!-- 행사년월 -->
				<select name="searchYear" title="<spring:message code="comUssIonEvt.common.searchYear"/>"><!-- 행사년 -->
					<option value="" <c:if test="${eventManageVO.searchYear eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
					<c:forEach items="${yearList}" var="result" varStatus="status">
					<option value="<c:out value="${result }"/>"  <c:if test="${eventManageVO.searchYear eq result}">selected</c:if>><c:out value="${result }"/></option>
					</c:forEach>
				</select><spring:message code="comUssIonEvt.common.year"/><!-- 년 -->
				<select name="searchMonth" title="<spring:message code="comUssIonEvt.common.searchMonth"/>"><!-- 행사월 -->
					<option value="" <c:if test="${eventManageVO.searchMonth eq '' }">selected</c:if>><spring:message code="comUssIonEvt.common.selectedAll"/></option><!-- 전체 -->
					<option value="01" <c:if test="${eventManageVO.searchMonth eq '01' }">selected</c:if>>01</option>
					<option value="02" <c:if test="${eventManageVO.searchMonth eq '02' }">selected</c:if>>02</option>
					<option value="03" <c:if test="${eventManageVO.searchMonth eq '03' }">selected</c:if>>03</option>
					<option value="04" <c:if test="${eventManageVO.searchMonth eq '04' }">selected</c:if>>04</option>
					<option value="05" <c:if test="${eventManageVO.searchMonth eq '05' }">selected</c:if>>05</option>
					<option value="06" <c:if test="${eventManageVO.searchMonth eq '06' }">selected</c:if>>06</option>
					<option value="07" <c:if test="${eventManageVO.searchMonth eq '07' }">selected</c:if>>07</option>
					<option value="08" <c:if test="${eventManageVO.searchMonth eq '08' }">selected</c:if>>08</option>
					<option value="09" <c:if test="${eventManageVO.searchMonth eq '09' }">selected</c:if>>09</option>
					<option value="10" <c:if test="${eventManageVO.searchMonth eq '10' }">selected</c:if>>10</option>
					<option value="11" <c:if test="${eventManageVO.searchMonth eq '11' }">selected</c:if>>11</option>
					<option value="12" <c:if test="${eventManageVO.searchMonth eq '12' }">selected</c:if>>12</option>
				</select><spring:message code="comUssIonEvt.common.month"/><!-- 월 -->
				
				<label for="" style="margin-left:10px"><spring:message code="comUssIonEvt.common.eventNm"/> : </label><!-- 행사명 -->
				<input name="searchNm" type="text" size="20" value="${eventManageVO.searchNm}"  maxlength="100" title="<spring:message code="comUssIonEvt.common.eventNm"/>"/><!-- 행사명 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectEventRceptConfm('1'); return false;" />
				<input class="s_btn" id="confirmAgree" type="submit" value="<spring:message code="comUssIonEvt.common.confrm"/>" title="<spring:message code="comUssIonEvt.common.confrm"/>" /><!-- 승인 -->
				<input class="s_btn" id="confirmDisAgree" type="submit" value="<spring:message code="comUssIonEvt.common.return"/>" title="<spring:message code="comUssIonEvt.common.return"/>" /><!-- 반려 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:5%" />
			<col style="" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:14%" />
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:14%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="checkAll" id="checkAll" class="check2" onClick="javascript:fn_egov_checkAll()" title="전체선택"></th>
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventNm"/></th><!-- 행사명 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventPlace"/></th><!-- 행사장소 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventTemp3"/></th><!-- 행사구분 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventBeginDe.eventEndDe"/></th><!-- 행사일자 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.eventTemp1"/></th><!-- 기간 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.applcntNm"/></th><!-- 신청자 -->
			   <th scope="col"><spring:message code="comUssIonEvt.common.sanctnDt"/></th><!-- 승인일자 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventRceptConfmList}" var="resultInfo" varStatus="status">
				<input type="hidden" name="eventId"         value="${resultInfo.eventId}"/>
				<input type="hidden" name="applcntId"       value="${resultInfo.applcntId}"/>
				<input type="hidden" name="infrmlSanctnId"  value="${resultInfo.infrmlSanctnId}"/>
				<input type="hidden" name="reqstDe"         value="${resultInfo.reqstDe}"/>
			<tr>
				<td>
					<c:if test="${resultInfo.confmAt eq 'A'}">
				        <input type="checkbox" name="eventRceptCheck" style="border:0px;" value ="${status.count-1}"  title="select">
		 			</c:if>
		 			<c:if test="${resultInfo.confmAt eq 'C'}"><spring:message code="comUssIonEvt.common.confrm"/></c:if><!-- 승인 -->
		 			<c:if test="${resultInfo.confmAt eq 'R'}"><spring:message code="comUssIonEvt.common.return"/></c:if><!-- 반려 -->		
				</td>
			    <td><span class="link"><a href="<c:url value='/uss/ion/evt/EgovEventReqstDetail.do?cmd=popup&'/>eventId=${resultInfo.eventId}" target="_blank" title="<spring:message code="comUssIonEvt.common.toNewWindow"/>" onclick="fncEventReqstDetailPop('<c:out value="${resultInfo.eventId}"/>'); return false;"><c:out value="${resultInfo.eventNm}"/></a></span></td>    <!--  새창으로 -->
				<td><c:out value="${resultInfo.eventPlace}"/></td>
				<td><c:out value="${resultInfo.eventSeNm}"/></td>
				<td><c:out value="${resultInfo.eventBeginDe}"/> ~ <br><c:out value="${resultInfo.eventEndDe}"/></td>
				<td><c:out value="${resultInfo.eventTemp1}"/><spring:message code="comUssIonEvt.common.days"/></td><!-- 일간 -->
				<td><c:out value="${resultInfo.applcntNm}"/></td>
				<td><c:out value="${resultInfo.sanctnDt}"/></td>
			</tr>    
			</c:forEach>
			
			<c:if test="${fn:length(eventRceptConfmList) == 0}">
				<tr> 
					<td colspan="8">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
	</form>
</div>
</body>
</html>
