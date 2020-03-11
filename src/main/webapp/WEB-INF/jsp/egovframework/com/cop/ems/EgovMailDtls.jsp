<%
 /**
  * @Class Name : EgovMailDtls.jsp
  * @Description : 발송메일 내역 조회 화면
  * @Modification Information
  * @
  * @  수정일       수정자                   수정내용
  * @ -------      ---------    ---------------------------
  * @ 2009.03.11    박지욱          최초 생성
  * @ 2011.09.30    이기하          데이터 없을시 메시지 추가
  *   2016.06.13    장동한         표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.11
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comCopSymEms.regist.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cop/ems/selectSndngMailList.do' />";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/cop/ems/selectSndngMailList.do' />";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnRegist(){
	document.listForm.action = "<c:url value='/cop/ems/insertSndngMailView.do' />";
   	document.listForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fnDetail(mid){
	document.detailForm.action = "<c:url value='/cop/ems/selectSndngMailDetail.do' />";
	document.detailForm.mssageId.value = mid;
   	document.detailForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
	var checkField = document.listForm.checkField;
    var id = document.listForm.checkId;
    var fileId = document.listForm.checkFileId;
    var checkedIds = "";
    var checkedFildIds = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedFildIds += ((checkedCount==0? "" : ",") + fileId[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
                checkedFildIds = fileId.value;
            }
        }
    }
    if(checkedIds.length > 0) {
    	var ret = confirm("삭제하시겠습니까?");
    	if (ret == true) {
    		document.deleteForm.mssageId.value=checkedIds;
    		document.deleteForm.atchFileIdList.value=checkedFildIds;
    		document.deleteForm.action = "<c:url value='/cop/ems/deleteSndngMailList.do' />";
    	    document.deleteForm.submit();
    	}
    }
}
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fnCheckAll(){
	var checkField = document.listForm.checkField;
    if(document.listForm.checkAll.checked) {
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
-->
</script>
</head>
<body onload="document.listForm.searchCondition.focus();">

<!-- 발송메일 목록 -->

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="board">
		<h1>${pageTitle} <spring:message code="title.list" /></h1>
		<form name="listForm" action="<c:url value='/cop/ems/selectSndngMailList.do'/>" method="post">
		  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		    
			<div class="search_box" title="<spring:message code="comCopSymEms.regist.title" />">
				<ul>
					<li>
						<select name="searchCondition" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
							<option selected value=''>--<spring:message code="input.select" />--</option>
							<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><spring:message code="comCopSymEms.searchCondition.title" /></option><!-- 약관명 -->
							<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><spring:message code="comCopSymEms.searchCondition.cn" /></option><!-- 내용 -->
							<option value="3"  <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if>><spring:message code="comCopSymEms.searchCondition.sender" /></option><!-- 보낸이 -->
						</select>
					</li>
					<!-- 검색키워드 및 조회버튼 -->
					<li>
						<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
						<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
					</li>
				</ul>
			</div>

			
		<table class="board_list" summary="<spring:message code="comCopSymEms.list.summary" arguments="${pageTitle}" />">
		<caption><spring:message code="comCopSymEms.regist.title" /> <spring:message code="title.list" /></caption>
		<colgroup>
			<col style="width: 5%;" />
			<col style="width: 5%;" />
			<col style="width: 15%;" />
			<col style="width: 20%;" />
			<col style="width: %;" />
			<col style="width: 20%;" />
		</colgroup>
		<thead>
		<tr>
			<th><input type="checkbox" name="checkAll" class="check2" onClick="javascript:fnCheckAll();" title="전체선택" id="checkAll" /></th><!-- 전체선택 -->
			<th><spring:message code="comCopSymEms.list.status" /></th><!-- 상태 -->
			<th><spring:message code="comCopSymEms.list.sender" /></th><!-- 발신자 -->
			<th><spring:message code="comCopSymEms.list.receiver" /></th><!-- 수신자 -->
			<th class="board_th_link"><spring:message code="comCopSymEms.list.title" /></th><!-- 제목 -->
			<th><spring:message code="comCopSymEms.list.regdate" /></th><!-- 날짜 -->
		</tr>
		</thead>
		<tbody class="ov">
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
		<tr>
			<td>
			<input type="checkbox" name="checkField" class="check2" title="선택">
			<input name="checkId" type="hidden" value="<c:out value='${resultInfo.mssageId}'/>" title=""/>
			<input name="checkFileId" type="hidden" value="<c:out value='${resultInfo.atchFileId}'/>" title=""/>
			</td>
			<td>${resultInfo.sndngResultCode}</td>
			<td>${resultInfo.dsptchPerson}</td>
			<td>${resultInfo.recptnPerson}</td>
			<td><a href="<c:url value='/cop/ems/selectSndngMailDetail.do' />" onclick="fnDetail('${resultInfo.mssageId}'); return false;">${resultInfo.sj}</a></td>
			<td>${resultInfo.sndngDe}</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
				
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/></ul>
		</div>
	
			
		</form>
		<div style="display:none;">
			<!-- 상세조회할 발송메일ID를 담는 폼 -->
			<form name="detailForm" action="<c:url value='/cop/ems/selectSndngMailDetail.do'/>" method="post">
				<input name="mssageId" type="hidden" value=""/>
				<input type="submit" id="invisible" class="invisible"/>
			</form>
			<!-- 삭제할 발송메일ID(여러 ID를 ,로 묶어 만들어진 데이터)를 담는 폼 -->
			<form name="deleteForm" action="<c:url value='/cop/ems/deleteSndngMailList.do'/>" method="post">
				<input name="mssageId" type="hidden" value=""/>
				<input name="atchFileIdList" type="hidden" value=""/>
				<input type="submit" class="invisible"/>
			</form>
		</div>

</div><!-- end div board -->
</body>
</html>

