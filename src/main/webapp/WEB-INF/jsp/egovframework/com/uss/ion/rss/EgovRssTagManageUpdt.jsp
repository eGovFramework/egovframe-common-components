<%--
  Class Name : EgovRssTagManageUpdt.jsp
  Description : RSS태그관리 수정
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.05    장동한          최초 생성
     2018.10.25    이정은          공통컴포넌트 3.8 개선
 
    author   : 공통서비스 개발팀 장동한
    since    : 2010.08.05
    
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="ussIonRss.rssTagManageUpdt.rssTagManageUpdt"/></title><!-- RSS태그관리 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>"></script>
<validator:javascript formName="rssManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.twitterInfo.pageIndex.value = pageNo;
	document.twitterInfo.action = "<c:url value='/uss/rss/rss/listRssTagManage.do'/>";
   	document.twitterInfo.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_RssTagManage(unityLinkId){
}
/* ********************************************************
* 상세회면 처리 함수
******************************************************** */
function fn_egov_ColumnSetting_RssTagManage(column){

	if( document.getElementById("tableColumn").selectedIndex > -1){

		var sColumnNm = $("#tableColumn option:selected").val();
		//해당컬럼 검색 똑같은거 입력 안되게 감리요청
		var regTest = new RegExp("#"+sColumnNm+"#");
		if( regTest.test(document.getElementById(column).value) ){
			alert(sColumnNm + " <spring:message code="ussIonRss.rssTagManageUpdt.validate.Duplicate"/>");
			return;
		}else{
			document.getElementById(column).value = document.getElementById(column).value + "#"+$("#tableColumn option:selected").val()+"#";
		}
	}
}
/* ********************************************************
* 선택한 테이블 컬럼 가져오기
******************************************************** */
function fn_egov_tableColumn_RssTagManage(sType){

	if( document.getElementById("trgetSvcTable").selectedIndex == 0){
		 $("#tableColumn").html("");
		return;
	}
	
	var tableName = $("#trgetSvcTable option:selected").val();

	   //success:callbackFunction
	$.ajax({
		   type:"GET",
		   url:"<c:url value='/uss/ion/rss/listRssTagManageTableColumnList.do?tableName='/>"+tableName,
		   dataType:"json",
		   success:function(data){

			   $("#tableColumn").html("");
			   
				for(var index=0 ; index < data.length ; index++) {
					//alert(data[index].text);
					$("#tableColumn").get(0).options[index] = new Option(data[index].text, data[index].value);
				}

		   }, error: function(data, status) { alert( status); }
		  });

	if(sType != "Init"){
		//본문 데이터 삭제
		document.getElementById('bdtTitle').value='';
		document.getElementById('bdtLink').value='';
		document.getElementById('bdtDescription').value='';
		document.getElementById('bdtTag').value='';
	}
}

/* ********************************************************
* 저장
******************************************************** */
function fn_egov_save_RssTagManage(){
	var vFrom = document.rssManage;
	
	if(confirm("<spring:message code="common.save.msg" />")){

		vFrom.action = "<c:url value='/uss/ion/rss/updtRssTagManage.do'/>";
		
		if(!validateRssManage(vFrom)){ 			
			return;
		}else{
			vFrom.submit();
		} 
	}
}
</script>

</head>
<body onLoad="fn_egov_tableColumn_RssTagManage('Init')">
<DIV id="content" style="width:712px">
<!-- noscript 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<!-- 등록폼 시작  -->
<form:form modelAttribute="rssManage" name="rssManage" action="" method="post" enctype="multipart/form-data">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="ussIonRss.rssTagManageUpdt.rssTagManageUpdt"/></h2><!-- RSS태그관리 수정 -->

	<!-- 등록폼 -->
	<table class="wTable mb10">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.trgetSvcNm"/> <span class="pilsu">*</span></th><!-- 대상서비스명 -->
			<td class="left">
			    <c:set var="trgetSvcName"><spring:message code="ussIonRss.rssTagManageRegist.trgetSvcNm"/></c:set>
			    <form:input path="trgetSvcNm" size="73" title="${trgetSvcName}" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="trgetSvcNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.trgetSvcTable"/> <span class="pilsu">*</span></th><!-- 대상테이블명 -->
			<td class="left">
			    <form:select path="trgetSvcTable" cssClass="txaIpt" onchange="fn_egov_tableColumn_RssTagManage();">
			    	<c:set var="selectmsg"><spring:message code="input.cSelect"/></c:set>
		            <form:option value="" label="${selectmsg}"/>
		            <form:options items="${trgetSvcTableList}" itemValue="code" itemLabel="code"/>
		        </form:select>
				<div><form:errors path="trgetSvcTable" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.trgetSvcListCo"/> <span class="pilsu">*</span></th><!-- 대상서비스목록개수 -->
			<td class="left">
			    <c:set var="trgetSvcListCount"><spring:message code="ussIonRss.rssTagManageRegist.trgetSvcListCo"/></c:set>
			    <form:input path="trgetSvcListCo" size="73" title="${trgetSvcListCount}" cssClass="txaIpt" maxlength="5"/>
				<div><form:errors path="trgetSvcListCo" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.hderTitle"/> <span class="pilsu">*</span></th><!-- 헤더TITLE -->
			<td class="left">
			    <c:set var="headerTitle"><spring:message code="ussIonRss.rssTagManageRegist.hderTitle"/></c:set>
			    <form:input path="hderTitle" size="73" title="${headerTitle}" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="hderTitle" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.hderLink"/> <span class="pilsu">*</span></th><!-- 헤더LINK  -->
			<td class="left">
			    <c:set var="headerLink"><spring:message code="ussIonRss.rssTagManageRegist.hderLink"/></c:set>
			    <form:input path="hderLink" size="73" title="${headerLink}" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="hderLink" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.hderDescription"/> <span class="pilsu">*</span></th><!-- 헤더DESCRIPTION -->
			<td class="left">
			    <c:set var="headerDescription"><spring:message code="ussIonRss.rssTagManageRegist.hderDescription"/></c:set>
			    <form:textarea path="hderDescription" title="${headerDescription}" rows="3" cols="20" cssClass="txaClass"/>
				<div><form:errors path="hderDescription" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.hderTag"/></th><!-- 헤더TAG -->
			<td class="left">
			    <c:set var="headerTag"><spring:message code="ussIonRss.rssTagManageRegist.hderTag"/></c:set>
			    <form:input path="hderTag" size="73" title="${headerTag}" cssClass="txaIpt" maxlength="255"/>
				<div><form:errors path="hderTag" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.hderEtc"/></th><!-- 헤더ETC -->
			<td class="left">
			    <c:set var="headerEtc"><spring:message code="ussIonRss.rssTagManageRegist.hderEtc"/></c:set>
			    <form:textarea path="hderEtc" title="${headerEtc}" rows="3" cols="20" cssClass="txaClass"/>
				<div><form:errors path="hderEtc" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.tableColumn"/></th><!-- 컬럼 -->
			<td class="left">
			    <c:set var="column"><spring:message code="ussIonRss.rssTagManageRegist.tableColumn"/></c:set>
			    <select name="tableColumn" id="tableColumn" size="7" title="${column}" style="width:99%;height:100px;">
				<option value=""></option>
				</select>
			</td>
		</tr>
	</table>
	
	<table class="wTable">
		<colgroup>
			<col style="width:20%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.bdtTitle"/> <span class="pilsu">*</span></th><!-- 본문TITLE -->
			<td class="left">
			    <a href="#LINK" onclick="fn_egov_ColumnSetting_RssTagManage('bdtTitle')"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_add.gif'/>" alt="컬럼명추가" title="컬럼명추가"></a>
				<c:set var="bTitle"><spring:message code="ussIonRss.rssTagManageRegist.bdtTitle"/></c:set>
				<form:input path="bdtTitle" title="${bTitle}" maxlength="255" cssStyle="width:80%"/>
				<a href="#LINK" onclick="document.getElementById('bdtTitle').value='';"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_delete.gif'/>" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 2px 0px 0px; " alt="컬럼명삭제" title="컬럼명삭제"></a>
				<div><form:errors path="bdtTitle" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.bdtLink"/> <span class="pilsu">*</span></th><!-- 본문LINK -->
			<td class="left">
				<a href="#LINK" onclick="fn_egov_ColumnSetting_RssTagManage('bdtLink')"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_add.gif'/>" alt="컬럼명추가" title="컬럼명추가"></a>	
				<c:set var="bLink"><spring:message code="ussIonRss.rssTagManageRegist.bdtLink"/></c:set>
				<form:input path="bdtLink" title="${bLink}" maxlength="255" cssStyle="width:80%"/>
				<a href="#LINK" onclick="document.getElementById('bdtLink').value='';"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_delete.gif'/>" alt="컬럼명삭제" title="컬럼명삭제"></a>
				<div><form:errors path="bdtLink" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.bdtDescription"/> <span class="pilsu">*</span></th><!-- 본문DESCRIPTION -->
			<td class="left">
			    <a href="#LINK" onclick="fn_egov_ColumnSetting_RssTagManage('bdtDescription')"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_add.gif'/>" alt="컬럼명추가" title="컬럼명추가"></a>
				<c:set var="bDescription"><spring:message code="ussIonRss.rssTagManageRegist.bdtDescription"/></c:set>
				<form:input path="bdtDescription" title="${bDescription}" maxlength="255" cssStyle="width:80%"/>
				<a href="#LINK" onclick="document.getElementById('bdtDescription').value='';"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_delete.gif'/>" alt="컬럼명삭제" title="컬럼명삭제"></a>
				<div><form:errors path="bdtDescription" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.bdtTag"/></th><!-- 본문TAG -->
			<td class="left">
			    <a href="#LINK" onclick="fn_egov_ColumnSetting_RssTagManage('bdtTag')"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_add.gif'/>" alt="컬럼명추가" title="컬럼명추가"></a>
				<c:set var="bTag"><spring:message code="ussIonRss.rssTagManageRegist.bdtTag"/></c:set>
				<form:input path="bdtTag" title="${bTag}" maxlength="255" cssStyle="width:80%"/>
				<a href="#LINK" onclick="document.getElementById('bdtTag').value='';"><img src="<c:url value='/images/egovframework/com/cmm/icon/action_delete.gif'/>" alt="컬럼명삭제" title="컬럼명삭제"></a>
				<div><form:errors path="bdtTag" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="ussIonRss.rssTagManageUpdt.bdtEtc"/></th><!-- 본문ETC -->
			<td class="left">
			    <c:set var="bEtc"><spring:message code="ussIonRss.rssTagManageRegist.bdtEtc"/></c:set>
			    <form:textarea path="bdtEtc" title="${bEtc}" rows="3" cols="20" />
				<div><form:errors path="bdtEtc" cssClass="error"/></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_save_RssTagManage(); return false;" />
		<span class="btn_s"><a href="<c:url value='/uss/ion/rss/listRssTagManage.do'/>" onclick=""><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>

<input name="rssId" type="hidden" value="${rssManage.rssId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</form:form>
</DIV>

</body>
</html>