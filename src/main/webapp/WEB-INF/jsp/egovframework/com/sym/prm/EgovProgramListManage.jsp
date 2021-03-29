<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovProgramListManage.jsp
  * @Description : 프로그램목록 조회 화면
  * @Modification Information
  * @
  * @  수정일             수정자             수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용               최초 생성
  *   2018.09.03   신용호            공통컴포넌트 3.8 개선
  *   2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/prm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/prm/button/";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymPrm.programListManage.title" /></title><!-- 프로그램목록리스트 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.progrmManageForm.checkField;
    if(document.progrmManageForm.checkAll.checked) {
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

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fDeleteProgrmManageList() {
    var checkField = document.progrmManageForm.checkField;
    var ProgrmFileNm = document.progrmManageForm.checkProgrmFileNm;
    var checkProgrmFileNms = "";
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkProgrmFileNms += ((checkedCount==0? "" : ",") + ProgrmFileNm[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
            	checkProgrmFileNms = ProgrmFileNm.value;
            }
        }
    }

    if(checkedCount ==0){
		alert("선택된 메뉴가 없습니다.");
		return false;
    }

    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
	    document.progrmManageForm.checkedProgrmFileNmForDel.value=checkProgrmFileNms;
    	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgrmManageListDelete.do'/>";
    	document.progrmManageForm.submit();
    }
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//	document.menuManageForm.searchKeyword.value =
	document.progrmManageForm.pageIndex.value = pageNo;
	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListManageSelect.do'/>";
   	document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramListManage() {
	document.progrmManageForm.pageIndex.value = 1;
	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListManageSelect.do'/>";
	document.progrmManageForm.submit();
}
/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertProgramListManage() {
   	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListRegist.do'/>";
   	document.progrmManageForm.submit();
}
/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtProgramListDetail(progrmFileNm) {
	document.progrmManageForm.tmp_progrmNm.value = progrmFileNm;
   	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListDetailSelect.do'/>";
   	document.progrmManageForm.submit();
}
/* ********************************************************
 * focus 시작점 지정함수
 ******************************************************** */
 function fn_FocusStart(){
		var objFocus = document.getElementById('F1');
		objFocus.focus();
	}

<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comSymPrm.programListManage.pageTop.title" /></h1><!-- 프로그램목록관리 -->
	<form name="progrmManageForm" action ="<c:url value='/sym/prm/EgovProgramListManageSelect.do' />" method="post">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<input name="checkedProgrmFileNmForDel" type="hidden" />
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comSymPrm.programListManage.programName" /> : </label><!-- 프로그램명 -->
				<input id="F1" class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value='${searchVO.searchKeyword}'/>" size="60" maxlength="60" onkeypress="press();" title="<spring:message code="title.searchCondition" />" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="selectProgramListManage(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/prm/EgovProgramListRegist.do'/>" onclick="insertProgramListManage(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
				<span class="btn_b"><a href="#LINK" onclick="fDeleteProgrmManageList(); return false;" title='<spring:message code="button.delete" />'><spring:message code="button.delete" /></a></span><!-- 삭제 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:20px" />
			<col style="" />
			<col style="width:137px" />
			<col style="width:260px" />
			<col style="width:150px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="fCheckAll();" title="전체선택" /></th>
			   <th scope="col"><spring:message code="comSymPrm.programListManage.programFileName" /></th><!-- 프로그램파일명 -->
			   <th scope="col"><spring:message code="comSymPrm.programListManage.programName" /></th><!-- 프로그램명 -->
			   <th scope="col">URL</th>
			   <th scope="col"><spring:message code="comSymPrm.programListManage.ProgramDescription" /></th><!-- 프로그램설명 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			 <c:if test="${fn:length(list_progrmmanage) == 0}">
			 <tr>
			 <td colspan="5">
				<spring:message code="common.nodata.msg" />
			 </td>
			 </tr>
			 </c:if>
			 
			 <c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
			  <tr>
			    <td>
			       <input type="checkbox" name="checkField" class="check2" title="선택">
			       <input name="checkProgrmFileNm" type="hidden" value="<c:out value='${result.progrmFileNm}'/>"/>
			    </td>
			    <td>
		            <span class="link"><a href="<c:url value='/sym/prm/EgovProgramListDetailSelect.do'/>?tmp_progrmNm=<c:out value="${result.progrmFileNm}"/>"  onclick="selectUpdtProgramListDetail('<c:out value="${result.progrmFileNm}"/>'); return false;">
		
		            <c:if test="${fn:length(result.progrmFileNm)> 22}">
				    	<c:out value="${fn:substring(result.progrmFileNm,0, 22)}"/>...
				    </c:if>
				    <c:if test="${fn:length(result.progrmFileNm)<= 22}">
				    	<c:out value="${result.progrmFileNm}"/>
				    </c:if>
		
		            </a></span>
			    </td>
			    <td>
			    <c:if test="${fn:length(result.progrmKoreanNm)> 12}">
			    	<c:out value="${fn:substring(result.progrmKoreanNm,0, 12)}"/>...
			    </c:if>
			    <c:if test="${fn:length(result.progrmKoreanNm)<= 12}">
			    	<c:out value="${result.progrmKoreanNm}"/>
			    </c:if>
			    </td>
			    <td>
			    <c:if test="${fn:length(result.URL)> 35}">
			    	<c:out value="${fn:substring(result.URL,0, 35)}"/>...
			    </c:if>
			    <c:if test="${fn:length(result.URL)<= 35}">
			    	<c:out value="${result.URL}"/>
			    </c:if>
			
			    </td>
			    <td><c:out value="${result.progrmDc}"/></td>
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
	
	<input type="hidden" name="cmd">
	<input type="hidden" name="tmp_progrmNm">
	</form>

</div>

</body>
</html>