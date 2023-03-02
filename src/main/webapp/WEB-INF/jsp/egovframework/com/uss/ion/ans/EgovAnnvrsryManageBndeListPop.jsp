<%
/**
 * @Class Name : EgovAnnvrsryManageBndeListPop.java
 * @Description : EgovAnnvrsryManageBndeListPop.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.10.25    이      용                최초 생성
 * @ 2018.09.19    최 두 영       다국어처리 
 *
 *  @author 이      용
 *  @since 2010.10.25
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
<title><spring:message code="comUssIonAns.annvrsryManageBndeListPop.title"/> </title><!-- 기념일일괄등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/js/egovframework/com/cmm/jquery-1.12.4.min.js' />"></script>
<script type="text/javaScript" language="javascript">

	/* ********************************************************
	 * 등록 처리 함수 
	 ******************************************************** */
	function fncAnnvrsryManageBndeRegist(){
		var varForm = document.getElementById("listForm");

		var usid           = varForm.usid;
		var annvrsryDe     = varForm.annvrsryDe;
		var cldrSe         = varForm.cldrSe;
		var annvrsrySe     = varForm.annvrsrySe;
		var annvrsryNm     = varForm.annvrsryNm;
		var reptitSe       = varForm.reptitSe;
   
		var checkAnnvrsryManage = "";
		var checkedCount     = 0;

		if(usid.length > 1){
			for(var i=0; i < usid.length; i++){
				checkAnnvrsryManage += ((checkedCount==0? "" : "$")+usid[i].value+","+annvrsryDe[i].value+","+cldrSe[i].value+","+annvrsrySe[i].value+","+annvrsryNm[i].value+","+reptitSe[i].value);
				checkedCount++;
			}
		} else {
			checkAnnvrsryManage = usid.value+","+annvrsryDe.value+","+cldrSe.value+","+annvrsrySe.value+","+annvrsryNm.value+","+reptitSe.value;
		}

		varForm.checkedAnnvrsryManageForInsert.value=checkAnnvrsryManage;
		varForm.action = "<c:url value='/uss/ion/ans/insertAnnvrsryManageBnde.do'/>";
		
		var formData = new FormData();
		formData.append("searchCondition", varForm.searchCondition.value);
		formData.append("checkedAnnvrsryManageForInsert", varForm.checkedAnnvrsryManageForInsert.value);
		formData.append("searchKeyword", varForm.searchKeyword.value);
		formData.append("cmd", varForm.cmd.value);
		formData.append("usid", varForm.usid.value);
		formData.append("annvrsryDe", varForm.annvrsryDe.value);
		formData.append("cldrSe", varForm.cldrSe.value);
		formData.append("annvrsrySe", varForm.annvrsrySe.value);
		formData.append("annvrsryNm", varForm.annvrsryNm.value);
		formData.append("reptitSe", varForm.reptitSe.value);

		if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */			
			$.ajax({
				type : "post",
				enctype : "multipart/form-data",
				url : varForm.action,
				data : formData,
				processData : false,
				contentType : false,
				success : function(data) {
					parent.window.fncPageReload();
					self.close();
				},
				error : function(request, status, error) {
					alert("등록 실패");
				}
			});
		}
	}

	/* ********************************************************
	 * 엑셀체크 처리 함수 
	 ******************************************************** */
	function fncAnnvrsryManageBndeCheck(){
	   var varForm = document.getElementById("listForm");
	   if(checkFile()){
		   varForm.action ="<c:url value='/uss/ion/ans/EgovAnnvrsryManageListPopAction.do'/>";
		   varForm.cmd.value = "bnde";
		   varForm.submit();
	   }
	}
	
	/* ********************************************************
	* 당직엑셀일괄등록시 등록파일 체크 함수
	******************************************************** */
	function checkFile(){ 
		if(document.listForm.file.value==""){
		   alert("<spring:message code="comUssIonAns.annvrsryManageBndeListPop.validate.fileValue"/>");/* 업로드 할 파일을 지정해 주세요.  */
		   return false;
		}
	
		var  str_dotlocation,str_ext,str_low;
		str_value  = document.listForm.file.value;
		str_low   = str_value.toLowerCase(str_value);
		str_dotlocation = str_low.lastIndexOf(".");
		str_ext   = str_low.substring(str_dotlocation+1);
		
		switch (str_ext) {
		  case "xls" :
		  case "xlsx" :
			 return true;
		     break;
		  default:
		     alert("<spring:message code="comUssIonAns.annvrsryManageBndeListPop.validate.fileForm"/>");/* 파일 형식이 맞지 않습니다.\n xls,XLS,xlsx,XLSX 만\n 업로드가 가능합니다! */
		     return false;
		}
	}
		
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonAns.annvrsryManageBndeListPop.title"/></h1><!-- 기념일일괄등록 -->
	<span>※Excel example file Location : ../WEB-INF/jsp/egovframework/com/uss/ion/ans/example/excelAnniversay.xls</span>
	<form name="listForm" id="listForm" action="<c:url value='/uss/ion/ans/EgovAnnvrsryManageListPopAction.do'/>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="checkedAnnvrsryManageForInsert">
	<input type="hidden" name="searchKeyword">
	<input type="hidden" name="cmd">

<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonAns.annvrsryManageBndeListPop.excelFile"/>: </label><!-- 기념일 엑셀파일 -->
				<input type = "file" name="file" title="<spring:message code="comUssIonAns.annvrsryManageBndeListPop.AllFile"/>" style="width:300px; background:#fff"/><!-- 일괄파일 -->				
				<input class="s_btn vat" type="submit" value="<spring:message code="comUssIonAns.annvrsryManageBndeListPop.upload"/>" title="<spring:message code="comUssIonAns.annvrsryManageBndeListPop.upload"/>" onclick="fncAnnvrsryManageBndeCheck(); return false;" /><!-- 업로드 -->
				<span class="btn_b vat"><a href="#LINK" onclick="fncAnnvrsryManageBndeRegist(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:20%" />
			<col style="" />
			<col style="width:20%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryNm"/></th><!-- 기념일명 -->
			   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryTemp3"/></th><!-- 기념일구분 -->
			   <th scope="col"><spring:message code="comUssIonAns.common.annvrsryDe"/></th><!-- 기념일(양/음) -->
			   <th scope="col"><spring:message code="comUssIonAns.common.reptitSe"/></th><!-- 반복여부 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${annvrsryManageList}" var="resultInfo" varStatus="status">
			<input type="hidden" name="usid"       id="usid"       value="${resultInfo.usid}">
			<input type="hidden" name="annvrsryDe" id="annvrsryDe" value="${resultInfo.annvrsryDe}">
			<input type="hidden" name="cldrSe"     id="cldrSe"     value="${resultInfo.cldrSe}">
			<input type="hidden" name="annvrsrySe" id="annvrsrySe" value="${resultInfo.annvrsrySe}">
			<input type="hidden" name="annvrsryNm" id="annvrsryNm" value="${resultInfo.annvrsryNm}">
			<input type="hidden" name="reptitSe"   id="reptitSe"   value="${resultInfo.reptitSe}">
			<tr>
				<td><c:out value="${resultInfo.annvrsryNm}"/></td>
				<td><c:out value="${resultInfo.annvrsrySe}"/></td>
				<td><c:out value="${resultInfo.annvrsryDe}"/>
		       		<c:if test="${!empty resultInfo.cldrSe }">(<c:if test='${resultInfo.cldrSe == "1"}'><spring:message code="comUssIonAns.annvrsryManageBndeListPop.cldrSe1"/></c:if><%-- 양 --%><c:if test='${resultInfo.cldrSe == "2"}'><spring:message code="comUssIonAns.annvrsryManageBndeListPop.cldrSe2"/></c:if>)</c:if></td><!-- 음 -->
				<td><c:out value="${resultInfo.reptitSe}"/></td>
			</tr>   
			</c:forEach>
			
			<c:if test="${fn:length(annvrsryManageList) == 0}">
				<tr> 
					<td class="lt_text3" colspan="4">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>
		</form>
</div>
</body>
</html>
