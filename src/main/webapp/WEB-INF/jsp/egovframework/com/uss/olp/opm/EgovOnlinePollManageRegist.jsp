<%
 /**
  * @Class Name : EgovOnlinePollManageRegist.jsp
  * @Description : 온라인POLL관리 등록
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2008.03.09    장동한          최초 생성
  *   2016.06.13   장동한              표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스팀 
  *  @since 2008.03.09
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpOpm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="onlinePollManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_OnlinePollManage(){

	$("#pollBeginDe").datepicker(  
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


	$("#pollEndDe").datepicker( 
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
function fn_egov_list_OnlinePollManage(){
	location.href = "<c:url value='/uss/olp/opm/listOnlinePollManage.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_OnlinePollManage(){
	var varFrom = document.onlinePollManage;

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/uss/olp/opm/registOnlinePollManage.do' />";

		if(!validateOnlinePollManage(varFrom)){
			return;
		}else{

			var pollBeginDe = Number( document.getElementById('pollBeginDe').value.replaceAll("-","") );
			var pollEndDe = Number( document.getElementById('pollEndDe').value.replaceAll("-","") );

			if(pollBeginDe > pollEndDe || pollEndDe < pollBeginDe ){
				alert("POLL시작일자는 POLL종료일자 보다 클수 없고,\nPOLL종료일자는 POLL시작일자 보다 작을수 없습니다. ");
				return;
			}

			varFrom.submit();
		}
	}
}

/* ********************************************************
 * 설문지정보 팝업창열기
 ******************************************************** */
 function fn_egov_QustnrManageListPopup_QustnrItemManage(){

 	window.showModalDialog("<c:url value='/uss/olp/qmc/EgovQustnrManageListPopup.do' />", self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");

}
 /* ********************************************************
 * PROTOTYPE JS FUNCTION
 ******************************************************** */
 String.prototype.trim = function(){
 	return this.replace(/^\s+|\s+$/g, "");
 }

 String.prototype.replaceAll = function(src, repl){
 	 var str = this;
 	 if(src == repl){return str;}
 	 while(str.indexOf(src) != -1) {
 	 	str = str.replace(src, repl);
 	 }
 	 return str;
 }
</script>
</head>
<body onLoad="fn_egov_init_OnlinePollManage()">
<div class="wTableFrm">

<form:form modelAttribute="onlinePollManage" name="onlinePollManage" id="onlinePollManage" action="" method="post" onsubmit="fn_egov_save_OnlinePollManage(); return false;">
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 선택하세요 -->
		<c:set var="selectTxt"><spring:message code="input.select" /></c:set>
		<!-- POLL명 -->
		<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollNm"/></c:set>
		<tr>
			<th><label for="pollNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="pollNm" title="${title} ${inputTxt}" size="73" maxlength="255" />
   				<div><form:errors path="pollNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- POLL시작일자 -->
		<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollBeginDe"/></c:set>
		<tr>
			<th><label for="pollBeginDe">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:input path="pollBeginDe" size="10" maxlength="10" readonly="true" style="width:70px;" />
				<form:errors path="pollBeginDe" cssClass="error"/>
			</td>
		</tr>
		<!-- 종료일자 -->
		<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollEndDe"/></c:set>
		<tr>
			<th><label for="pollEndDe">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
      			<form:input path="pollEndDe" size="10" maxlength="10" readonly="true" style="width:70px;" />
				<form:errors path="pollEndDe" cssClass="error"/>
			</td>
		</tr>
		<!-- POLL구분 -->
		<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollType"/></c:set>
		<tr>
			<th><label for="pollKindCode">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		        <form:select path="pollKindCode">
		            <form:option value="" label="${selectTxt}"/>
		            <form:options items="${pollKindCodeList}" itemValue="code" itemLabel="codeNm"/>
		        </form:select>
			</td>
		</tr>
		<!-- POLL페기유무 -->
		<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollDsuseYn"/></c:set>
		<tr>
			<th><label for="pollDsuseYn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		 		<div style="float:left;"><input type="radio" name="pollDsuseYn" value="N"  checked></div>
		 		<div style="float:left;margin:0 0 0 10px;">N</div>
		   		<div style="float:left;margin:0 0 0 10px;"><input type="radio" name="pollDsuseYn" value="Y"></div>
		   		<div style="float:left;margin:0 0 0 10px;">Y</div>
		   		<div style="clear:both;"></div>
			</td>
		</tr>
		<!-- POLL자동페기유무 -->
		<c:set var="title"><spring:message code="comUssOlpOpm.regist.pollAutoDsuseYn"/></c:set>
		<tr>
			<th><label for="pollAutoDsuseYn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
		    	<div style="float:left;"><input type="radio" name="pollAutoDsuseYn" value="N"></div>
		    	<div style="float:left;margin:0 0 0 10px;">N</div>
		   		<div style="float:left;margin:0 0 0 10px;"><input type="radio" name="pollAutoDsuseYn" value="Y" checked></div>
		   		<div style="float:left;margin:0 0 0 10px;">Y</div>
		   		<div style="clear:both;"></div>
			</td>
		</tr>	
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="btn_s"><a href="<c:url value='/uss/olp/opm/listOnlinePollManage.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>
</DIV>

</body>
</html>
