<%
/**
 * @Class Name : EgovMtgPlaceUpdt.java
 * @Description : EgovMtgPlaceUpdt.jsp
 * @Modification Information 
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.06.29    이      용                최초 생성
 * @ 2018.08.21    최 두 영           퍼블리싱 점검/비품정보 기능제거
 * @ 2018.09.11    최 두  영          다국어처리 적용
 *
 *  @author 이      용
 *  @since 2010.06.29
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
<title><spring:message code="comUssIonMtg.mtgPlaceUpdt.title" /></title><!-- 회의실 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="mtgPlaceManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

<!--
function fncSelectMtgPlaceManageList() {
    var varFrom = document.getElementById("mtgPlaceManage");
    varFrom.action = "<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>";
    varFrom.submit();       
}

function fncUpdtMtgPlace() {
     var varFrom = document.getElementById("mtgPlaceManage");

     if(varFrom.opnBeginTm.value == ""){
		  alert("<spring:message code="comUssIonMtg.mtgPlaceRegist.selectStartTime" />");
		  return;
	  }
	  if(varFrom.opnEndTm.value == ""){
		  alert("<spring:message code="comUssIonMtg.mtgPlaceRegist.selectCloseTime" />");
		  return;
	  }
     if(parseInt(varFrom.opnBeginTm.value.substring(0,2)) >= parseInt(varFrom.opnEndTm.value.substring(0,2))){
          alert("<spring:message code="comUssIonMtg.mtgPlaceRegist.checkOpenTime" />");
          return;
	  }
     
     varFrom.action = "<c:url value='/uss/ion/mtg/updtMtgPlace.do'/>";

     if(confirm("<spring:message code="common.save.msg" />")){
	     if(!validateMtgPlaceManage(varFrom)){           
	         return;
	     }else{
	         varFrom.submit();
	     } 
     }
}

-->

</script>
</head> 
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="mtgPlaceManage" name="mtgPlaceManage" method="post" action="${pageContext.request.contextPath}/uss/ion/mtg/updtMtgPlace.do"  enctype="multipart/form-data"> 
<input type="hidden" name="returnUrl"           value="/uss/ion/mtg/selectMtgPlaceManage.do" />
<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />  
<input type="hidden" name="cmd" value="update">
<input type="hidden" name="mtgPlaceId" value ="<c:out value='${mtgPlaceManage.mtgPlaceId}'/>">
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${mtgPlaceManageVO.searchCondition}'/>" >
<input type="hidden" name="searchKeyword" value="<c:out value='${mtgPlaceManageVO.searchKeyword}'/>" >
<input type="hidden" name="pageIndex" value="<c:out value='${mtgPlaceManageVO.pageIndex}'/>" >

<!--  첨부파일 테이블 레이아웃 End. /cmm/fms/selectImageFileInfs.do -->
	<c:if test="${mtgPlaceManage.atchFileId eq null || mtgPlaceManage.atchFileId eq ''}">
	 	<input type="hidden" name="fileListCnt" value="0" />
	 	<input type="hidden" name="atchFileAt" value="N">
	</c:if> 
	
	<c:if test="${mtgPlaceManage.atchFileId ne null && mtgPlaceManage.atchFileId ne ''}">
	 	<input type="hidden" name="atchFileAt" value="Y"> 
	</c:if>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonMtg.mtgPlaceUpdt.title" /></h2>

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:25%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /><span class="pilsu">*</span></th><!-- 회의실명 -->
			<td class="left" colspan="3">
				<c:set var="mtgPlaceNm"><spring:message code="comUssIonMtg.mtgPlaceManageList.mtgPlaceNm" /></c:set>
			    <form:input  path="mtgPlaceNm" title="${mtgPlaceNm}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceRegist.occupancy" /><span class="pilsu">*</span></th><!-- 수용가능인원 -->
			<td class="left">
				<select name="aceptncPosblNmpr" title="수용가능인원">
			       	<option value="5"   <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '5'}" > selected </c:if>>5<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="10"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '10'}"> selected </c:if>>10<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="15"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '15'}"> selected </c:if>>15<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="20"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '20'}"> selected </c:if>>20<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="25"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '25'}"> selected </c:if>>25<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="30"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '30'}"> selected </c:if>>30<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="50"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '50'}"> selected </c:if>>50<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="70"  <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '70'}"> selected </c:if>>70<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
			       	<option value="100" <c:if test="${mtgPlaceManage.aceptncPosblNmpr == '100'}"> selected </c:if>>100<spring:message code="comUssIonMtg.mtgPlaceManageList.persons" /></option>
		      	</select>
			</td>
			<th><spring:message code="comUssIonMtg.mtgPlaceRegist.openTime" /> <span class="pilsu">*</span></th> <!-- 개방시간 -->
			<td class="left">
				<select name="opnBeginTm" title="<spring:message code="comUssIonMtg.mtgPlaceRegist.openTimeFrom" />"><!-- 개방시작시간 -->
			       	<option value="08:00" <c:if test="${mtgPlaceManage.opnBeginTm == '08:00'}"> selected </c:if>>08:00</option>
			       	<option value="09:00" <c:if test="${mtgPlaceManage.opnBeginTm == '09:00'}"> selected </c:if>>09:00</option>
			       	<option value="10:00" <c:if test="${mtgPlaceManage.opnBeginTm == '10:00'}"> selected </c:if>>10:00</option>
			       	<option value="11:00" <c:if test="${mtgPlaceManage.opnBeginTm == '11:00'}"> selected </c:if>>11:00</option>
			       	<option value="12:00" <c:if test="${mtgPlaceManage.opnBeginTm == '12:00'}"> selected </c:if>>12:00</option>
			       	<option value="13:00" <c:if test="${mtgPlaceManage.opnBeginTm == '13:00'}"> selected </c:if>>13:00</option>
			       	<option value="14:00" <c:if test="${mtgPlaceManage.opnBeginTm == '14:00'}"> selected </c:if>>14:00</option>
			       	<option value="15:00" <c:if test="${mtgPlaceManage.opnBeginTm == '15:00'}"> selected </c:if>>15:00</option>
			       	<option value="16:00" <c:if test="${mtgPlaceManage.opnBeginTm == '16:00'}"> selected </c:if>>16:00</option>
			       	<option value="17:00" <c:if test="${mtgPlaceManage.opnBeginTm == '17:00'}"> selected </c:if>>17:00</option>
			       	<option value="18:00" <c:if test="${mtgPlaceManage.opnBeginTm == '18:00'}"> selected </c:if>>18:00</option>
			       	<option value="19:00" <c:if test="${mtgPlaceManage.opnBeginTm == '19:00'}"> selected </c:if>>19:00</option>
			       	<option value="20:00" <c:if test="${mtgPlaceManage.opnBeginTm == '20:00'}"> selected </c:if>>20:00</option>
			       	<option value="21:00" <c:if test="${mtgPlaceManage.opnBeginTm == '21:00'}"> selected </c:if>>21:00</option>
		      	</select>
		      	~  
				<select name="opnEndTm" title="<spring:message code="comUssIonMtg.mtgPlaceRegist.openTimeTo" />"><!-- 개방종료시간 -->
			       	<option value="08:00" <c:if test="${mtgPlaceManage.opnEndTm == '08:00'}"> selected </c:if>>08:00</option>
			       	<option value="09:00" <c:if test="${mtgPlaceManage.opnEndTm == '09:00'}"> selected </c:if>>09:00</option>
			       	<option value="10:00" <c:if test="${mtgPlaceManage.opnEndTm == '10:00'}"> selected </c:if>>10:00</option>
			       	<option value="11:00" <c:if test="${mtgPlaceManage.opnEndTm == '11:00'}"> selected </c:if>>11:00</option>
			       	<option value="12:00" <c:if test="${mtgPlaceManage.opnEndTm == '12:00'}"> selected </c:if>>12:00</option>
			       	<option value="13:00" <c:if test="${mtgPlaceManage.opnEndTm == '13:00'}"> selected </c:if>>13:00</option>
			       	<option value="14:00" <c:if test="${mtgPlaceManage.opnEndTm == '14:00'}"> selected </c:if>>14:00</option>
			       	<option value="15:00" <c:if test="${mtgPlaceManage.opnEndTm == '15:00'}"> selected </c:if>>15:00</option>
			       	<option value="16:00" <c:if test="${mtgPlaceManage.opnEndTm == '16:00'}"> selected </c:if>>16:00</option>
			       	<option value="17:00" <c:if test="${mtgPlaceManage.opnEndTm == '17:00'}"> selected </c:if>>17:00</option>
			       	<option value="18:00" <c:if test="${mtgPlaceManage.opnEndTm == '18:00'}"> selected </c:if>>18:00</option>
			       	<option value="19:00" <c:if test="${mtgPlaceManage.opnEndTm == '19:00'}"> selected </c:if>>19:00</option>
			       	<option value="20:00" <c:if test="${mtgPlaceManage.opnEndTm == '20:00'}"> selected </c:if>>20:00</option>
			       	<option value="21:00" <c:if test="${mtgPlaceManage.opnEndTm == '21:00'}"> selected </c:if>>21:00</option>
		      	</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceManageList.location" /><span class="pilsu">*</span></th><!-- 위치 -->
			<td class="left" colspan="3">
			 	<c:set var="selectLoacation"><spring:message code="comUssIonMtg.mtgPlaceRegist.locationSelection" /></c:set>
			 	<c:set var="locationDetail"><spring:message code="comUssIonMtg.mtgPlaceRegist.locationDetail" /></c:set>
				<form:select path="lcSe" title="${selectLoaction}">
					<form:options items="${lcSeCode}" itemValue="code" itemLabel="codeNm"/>
		        </form:select>
		        <form:input  path="lcDetail" title="${locationDetail}" cssStyle="width:509px" />
			</td>
		</tr>
		<!--  첨부파일 테이블 레이아웃 설정 Start.. -->
		<c:if test="${mtgPlaceManage.atchFileId ne null && mtgPlaceManage.atchFileId ne ''}">
		<tr> 
			<th><spring:message code="comUssIonMtg.mtgPlaceDetail.imgFileList" /></th>
			<td colspan="3">
				<!-- c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" -->
				<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
				<c:param name="param_atchFileId" value="${mtgPlaceManage.atchFileId}" />
				</c:import>								
			</td>
		</tr>
		</c:if>
		<tr>
			<th><spring:message code="comUssIonMtg.mtgPlaceRegist.imageUpload" /></th>
			<td class="left" colspan="3">
				<c:set var="attachments"><spring:message code="comUssIonMtg.mtgPlaceRegist.attachments" /></c:set>
				<input name="file_1" id="egovComFileUploader" type="file" multiple title="${attachments}" />
				<div id="egovComFileList"></div>
			</td>
		</tr>
	</table>	

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fncUpdtMtgPlace(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/mtg/selectMtgPlaceManageList.do'/>?searchCondition=1" onclick="fncSelectMtgPlaceManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</form:form>
<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
   var maxFileNum = document.getElementById('posblAtchFileNumber').value;
   if(maxFileNum==null || maxFileNum==""){
     maxFileNum = 3;
   }
   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->
</body>
</html>