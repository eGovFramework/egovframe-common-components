<%
/**
 * @Class Name : EgovBndtManageBndeListPop.java
 * @Description : EgovBndtManageBndeListPop jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영              퍼블리싱 점검, 소스 오류 점검 
 * @ 2018.09.27    최 두 영              다국어처리
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
<title><spring:message code="comUssIonBnt.bndtManageBndeListPop.title"/></title><!-- 당직일괄등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">

	/* ********************************************************
	 * 등록 처리 함수 
	 ******************************************************** */
	function fncBndtManageBndeRegist(){
		var varFrom = document.getElementById("listForm");
		//var checkField = varFrom.bndtCheck;
		var bndtId = varFrom.bndtId;
		var bndtDe = varFrom.bndtDe;
		var searchKeyword  = varFrom.searchKeyword;
		var checkBndtManage = "";
		var checkedCount     = 0;
		
		if(bndtId.length > 1){
			searchKeyword.value = bndtDe[0].value.substring(0,6);
			for(var i=0; i < bndtId.length; i++){
				checkBndtManage += ((checkedCount==0? "" : "$")+bndtDe[i].value+","+bndtId[i].value);
				checkedCount++;
			}
		} else {
			//
			checkBndtManage = bndtDe.value+","+bndtId.value;
		}
	
		varFrom.checkedBndtManageForInsert.value=checkBndtManage;
		varFrom.action = "<c:url value='/uss/ion/bnt/insertBndtManageBnde.do'/>";
	
		if(confirm("<spring:message code="common.save.msg" />")){/* 저장 하시겠습니까? */
			varFrom.submit();
		}
	}

	/* ********************************************************
	 * 엑셀체크 처리 함수 
	 ******************************************************** */
	function fncBndtManageBndeCheck(){
	    if(checkFile()){
		   document.listForm.action ="<c:url value='/uss/ion/bnt/EgovBndtManageListPopAction.do'/>";
		   document.listForm.cmd.value = "bnde";
	       document.listForm.submit();
	   }
	}

	/* ********************************************************
	* 당직엑셀일괄등록시 등록파일 체크 함수
	******************************************************** */
	function checkFile(){ 
		if(document.listForm.file.value==""){
		   alert("<spring:message code="comUssIonBnt.common.validate.fileNull"/>");/* 업로드 할 파일을 지정해 주세요. */
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
		     alert("<spring:message code="comUssIonBnt.common.validate.formNotMatch"/>");/* 파일 형식이 맞지 않습니다.\\n xls,XLS,xlsx,XLSX 만\\n 업로드가 가능합니다! */
		     return false;
		}
	}
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonBnt.bndtManageBndeListPop.title"/></h1><!-- 당직일괄등록 -->
	
	<span>※Excel example files Location : ../WEB-INF/jsp/egovframework/com/uss/ion/bnt/example</span>
	
	<form name="listForm" id="listForm" action="<c:url value='/uss/ion/bnt/EgovBndtManageListPopAction.do'/>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="checkedBndtManageForInsert">
	<input type="hidden" name="searchKeyword">
	<input type="hidden" name="cmd">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<span class="btn_b"><a href="#LINK" onclick="fncBndtManageBndeRegist(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	
	<table class="wTable mb10">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonBnt.bndtManageBndeListPop.bndtExcelFile"/></th><!-- 당직자 엑셀파일 -->
			<td class="left">
			    <input type = "file" name="file" title="<spring:message code="comUssIonBnt.bndtManageBndeListPop.file"/>" style="width:200px"/><!-- 일괄파일 -->
			    <input class="btn_01" type="submit" value="<spring:message code="comUssIonBnt.bndtManageBndeListPop.upload"/>" onclick="fncBndtManageBndeCheck();return false;" /><!-- 업로드 -->
			</td>
		</tr>
	</table>
	
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:30%" />
			<col style="width:30%" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtDe"/></th><!-- 당직일자 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtIdName"/></th><!-- 당직자명 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.orgnztNm"/></th><!-- 소속명 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bndtManageList}" var="resultInfo" varStatus="status">
			<input type="hidden" name="bndtDe" id="bndtDe" value="${resultInfo.bndtDe}">
			<input type="hidden" name="bndtId" id="bndtId" value="${resultInfo.tempBndtId}">
			<tr>
				<td>
					<font <c:if test="${(resultInfo.dateWeek ) == 1}"> color="red" </c:if> <c:if test="${(resultInfo.dateWeek ) == 7}"> color="blue"</c:if> size='2'>
						<c:out value="${resultInfo.tempBndtWeek}"/>
					</font>
				</td>
				<td>${resultInfo.tempBndtNm}</td>
				<td>${resultInfo.tempOrgnztNm}</td>
			</tr>   
			</c:forEach>
			
			<c:if test="${fn:length(bndtManageList) == 0}">
				<tr> 
					<td class="lt_text3" colspan="3">
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
