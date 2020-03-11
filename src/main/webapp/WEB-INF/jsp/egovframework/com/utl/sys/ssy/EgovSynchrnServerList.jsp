<%--
/**
 * @Class Name  : EgovSynchrnServerList.java
 * @Description : EgovSynchrnServerList jsp
 * @Modification Information
 * @
 * @  수정일              수정자               수정내용
 * @ ----------   --------     ---------------------------
 * @ 2010.07.01   lee.m.j      최초 생성
 * @ 2018.11.08   이정은               공통컴포넌트 3.8 개선
 * @ 2019.12.09   신용호               KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *
 *  @author lee.m.j
 *  @since 2010.07.01
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comUtlSysSsy.synchrnServer.title" /><spring:message code="title.list" /></title><!-- 동기화대상 서버 목록 -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/button.css' />">

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncSelectSynchrnServerList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>";
    document.listForm.submit();
}

function fncSelectSynchrnServer(serverId) {
    document.listForm.serverId.value = serverId;
    document.listForm.action = "<c:url value='/utl/sys/ssy/getSynchrnServer.do'/>";
    document.listForm.submit();   
}

function fncAddSynchrnServerInsert() {
    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    } 
    document.listForm.action = "<c:url value='/utl/sys/ssy/addViewSynchrnServer.do'/>";
    document.listForm.submit(); 
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
        fncSelectSynchrnServerList('1');
    }
}

function fncProcessSynchrn(){

    if(document.hiddenForm.totalRecordCount.value == null || 
       document.hiddenForm.totalRecordCount.value == '' ||
       document.hiddenForm.totalRecordCount.value == 0) {
        alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.noList"/>");/* 동기화 대상목록이 없습니다 */
        return; 
    }
    
    if(confirm("<spring:message code='comUtlSysSsy.sync.msg' />")) {
        document.listForm.action = "<c:url value='/utl/sys/ssy/processSynchrn.do'/>";
        document.listForm.submit();
    }
}

function fncUploadFile(){	
	if(document.fileForm.file.value.length > 0){
		
		var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
		if (!resultExtension) return true;
		var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
		if (!resultSize) return true;
		
    	document.fileForm.action = "<c:url value='/utl/sys/ssy/uploadFile.do'/>";
    	document.fileForm.submit();
	}else{
		alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.noSelectedFile"/>");/* 선택된  파일이 없습니다. */
		return;
	}
}

function fncDeleteFile(deleteFileNm){
    if(confirm("<spring:message code='common.delete.msg' />")){
        document.fileForm.deleteFileNm.value = deleteFileNm;
        document.fileForm.action = "<c:url value='/utl/sys/ssy/deleteFile.do'/>";
        document.fileForm.submit();
    }
}

function fncCheckAll() {
    var checkField = document.deleteForm.delYn;
    if(document.deleteForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.deleteForm.delYn;
    var checkId = document.deleteForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;
    
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else 
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0) 
                returnBoolean = true;
            else {
                alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.noSelectedFile"/>");/* 선택된  파일이 없습니다. */
                returnBoolean = false;
            }
        } else {
            if(document.deleteForm.delYn.checked == false) {
                alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.noSelectedFile"/>");/* 선택된 파일이 없습니다. */
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("<spring:message code="comUtlSysSsy.synchrnServer.validate.noQueryResult"/>");/* 조회된 결과가 없습니다. */
    }

    document.deleteForm.deleteFiles.value = returnValue;
    return returnBoolean;
}

function fncFileListDelete() {
    if(fncManageChecked()) {
        if(confirm("<spring:message code='common.delete.msg' />")) {
            document.deleteForm.action = "<c:url value='/utl/sys/ssy/deleteFile.do'/>";
            document.deleteForm.submit();
        }
    }
}

function checkResultMessage() {
	
	var resultMessage = "<c:out value='${fileUploadResultMessage}'/>";
	if ( resultMessage != "" ) alert(resultMessage);
}

-->
</script>

</head>

<body onload="checkResultMessage();">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form name="listForm" action="<c:url value='/utl/sys/ssy/selectSynchrnServerList.do'/>" method="post">

<div class="board">
	<h1><spring:message code="comUtlSysSsy.synchrnServer.title" />  <spring:message code="title.management" /></h1><!-- 동기화대상 서버 관리 -->

	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<label for=""><spring:message code="comUtlSysSsy.synchrnServer.serverNm.label" /> : </label>
				<input id="strSynchrnServerNm" class="s_input2 vat" name="strSynchrnServerNm" type="text" value='<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>' size="30" onkeypress="press();" title="검색" />
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectSynchrnServerList('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/ssy/addViewSynchrnServer.do'/>?pageIndex=<c:out value='${synchrnServerVO.pageIndex}'/>&amp;strSynchrnServerNm=<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>" onclick="fncAddSynchrnServerInsert(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				<span class="btn_b"><a href="<c:url value='/utl/sys/ssy/processSynchrn.do'/>?pageIndex=<c:out value='${synchrnServerVO.pageIndex}'/>" onclick="fncProcessSynchrn(); return false;" title="동기화"><spring:message code="comUtlSysSsy.synchrnServer.synch"/></a></span><!-- 동기화 -->
			</li>
		</ul>
	</div>
<input type="hidden" name="serverId">
<input type="hidden" name="pageIndex" value="<c:if test="${empty synchrnServerVO.pageIndex }">1</c:if><c:if test="${!empty synchrnServerVO.pageIndex }"><c:out value='${synchrnServerVO.pageIndex}'/></c:if>">
</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:20%" />
			<col style="width:15%" />
			<col style="width:15%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.serverId.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.serverNm.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.serverIp.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.reflctAt.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.lastUpdusrId.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="synchrnServer" items="${synchrnServerList}" varStatus="status">
			  <tr>
			    <td>
			        <form name="item" method="post" action="<c:url value='/utl/sys/ssy/getSynchrnServer.do'/>">
			            <input type="hidden" name="serverId" value="<c:out value="${synchrnServer.serverId}"/>">
			            <input type="hidden" name="pageIndex" value="<c:out value='${synchrnServerVO.pageIndex}'/>">
			            <input type="hidden" name="strSynchrnServerNm" value="<c:out value="${synchrnServerVO.strSynchrnServerNm}"/>">
			            <span class="link"><input type="submit" value="<c:out value="${synchrnServer.serverId}"/>" onclick="fncSelectSynchrnServer('<c:out value="${synchrnServer.serverId}"/>'); return false;"></span>
			        </form>
			    </td>
			    <td><c:out value="${synchrnServer.serverNm}"/></td>
			    <td><c:out value="${synchrnServer.serverIp}"/></td>
			    <td><c:out value="${synchrnServer.reflctAt}"/></td>
			    <td><c:out value="${synchrnServer.lastUpdusrId}"/></td>
			  </tr>
			 </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>

<form name="hiddenForm">
    <input type="hidden" name="totalRecordCount" value="<c:out value="${paginationInfo.totalRecordCount}"/>"/>
</form>

<div class="wTableFrm">
<form name="fileForm" action="<c:url value='/utl/sys/ssy/uploadFile.do'/>" method="post" enctype="multipart/form-data">
	<!-- 타이틀 -->
	<h2><spring:message code="comUtlSysSsy.synchrnServerFile.title" />  <spring:message code="title.create" /></h2><!-- 동기화대상 파일 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUtlSysSsy.synchrnServer.fileSelect.label" /></th>
			<td class="left">
			    <input id="egovComFileUploader" name="file" type="file" size="30" title="<spring:message code='comUtlSysSsy.synchrnServerFile.title' />" >
			</td>
		</tr>
	</table>
</form>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.create" />' onclick="fncUploadFile(); return false;" />
	</div>
	<div style="clear:both;"></div>
</div>

<div class="board">
<form name="deleteForm" action="<c:url value='/utl/sys/ssy/deleteFile.do'/>" method="post">
	<h1><spring:message code="comUtlSysSsy.synchrnServerFile.title" />  <spring:message code="title.list" /></h1> <!-- 동기화대상 파일 목록 -->

	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<input class="s_btn" type="submit" value='<spring:message code="button.delete" />' title='<spring:message code="button.delete" />' onclick="fncFileListDelete(); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:10%" />
			<col style="width:80%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="fncCheckAll()" title="전체선택체크박스" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.seq.label" /></th>
			   <th scope="col"><spring:message code="comUtlSysSsy.synchrnServer.fileNm.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="file" items="${fileList}" varStatus="status">
			<tr>
				<td><input type="checkbox" name="delYn" value="<c:out value="${file}"/>"><input type="hidden" name="checkId" value="<c:out value="${file}"/>" /></td>
				<td><c:out value="${status.count}"/></td>
				<td><c:out value="${file}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="hidden" name="pageIndex" value="<c:out value='${synchrnServerVO.pageIndex}'/>" />
	<input type="hidden" name="deleteFiles" />
</form>
</div>
</body>
</html>