<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovMenuManage.jsp
  * @Description : 메뉴관리 조회 화면
  * @Modification Information
  * @
  * @  수정일             수정자          수정내용
  * @ -------     --------  ---------------------------
  * @ 2009.03.10   이용            최초 생성
  	  2011.07.27     서준식          메뉴 삭제 자바스크립트 오류 수정
  	  2018.08.09     신용호          삭제시 목록이 1개인경우 예외처리 수정
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/mnu/mpm/icon";
  String imagePath_button = "/images/egovframework/com/sym/mnu/mpm/button";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymMnuMpm.menuManage.title"/></title><!-- 메뉴관리리스트 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.menuManageForm.checkField;
    if(document.menuManageForm.checkAll.checked) {
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
function fDeleteMenuList() {
    var checkField = document.menuManageForm.checkField;
    var menuNo = document.menuManageForm.checkMenuNo;
    var checkMenuNos = "";
    var checkedCount = 0;
    if(checkField) {

    	if(typeof(checkField.length) != "undefined") {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkMenuNos += ((checkedCount==0? "" : ",") + menuNo[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkMenuNos = menuNo.value;
                checkedCount = 1;
            }
        }
    }

    if(checkedCount ==0){
		alert("선택된 메뉴가 없습니다.");
		return false;
    }

    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
	    document.menuManageForm.checkedMenuNoForDel.value=checkMenuNos;
	    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageListDelete.do'/>";
	    document.menuManageForm.submit();
    }
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//	document.menuManageForm.searchKeyword.value =
	document.menuManageForm.pageIndex.value = pageNo;
	document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>";
   	document.menuManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectMenuManageList() {
	document.menuManageForm.pageIndex.value = 1;
	document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>";
	document.menuManageForm.submit();
}

/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertMenuManage() {
   	document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuRegistInsert.do'/>";
   	document.menuManageForm.submit();
}

/* ********************************************************
 * 일괄처리 화면호출 함수
 ******************************************************** */
/* function bndeInsertMenuManage() {
	   	document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuRegistInsert.do'/>";
	   	document.menuManageForm.submit();
	}
 */
function bndeInsertMenuManage() {
   	document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuBndeRegist.do'/>";
   	document.menuManageForm.submit();
}
/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtMenuManageDetail(menuNo) {
	document.menuManageForm.req_menuNo.value = menuNo;
   	document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageListDetailSelect.do'/>";
   	document.menuManageForm.submit();
}
/* ********************************************************
 * 최초조회 함수
 ******************************************************** */
function fMenuManageSelect(){
    document.menuManageForm.action = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>";
    document.menuManageForm.submit();
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>

</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="menuManageForm" action ="<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>" method="post">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="checkedMenuNoForDel" type="hidden" />
<input name="req_menuNo" type="hidden"  />

<div class="board">
	<h1><spring:message code="comSymMnuMpm.menuManage.pageTop.title"/></h1><!-- 메뉴관리리스트 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymMnuMpm.menuManage.menuNm"/> : </label><!-- 메뉴명 -->
				<input class="s_input2 vat" name="searchKeyword" type="text" value="${searchVO.searchKeyword }" size="25" title="<spring:message code="title.searchCondition"/>" /><!-- 검색조건 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="selectMenuManageList(); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/mnu/mpm/EgovMenuRegistInsert.do'/>" onclick="bndeInsertMenuManage(); return false;" title="<spring:message code="button.bulkUpload" />"><spring:message code="button.bulkUpload" /></a></span><!-- 일괄등록 -->
				<span class="btn_b"><a href="<c:url value='/sym/mnu/mpm/EgovMenuRegistInsert.do'/>" onclick="insertMenuManage(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span><!-- 등록 -->
				<span class="btn_b"><a href="#" onclick="fDeleteMenuList(); return false;" title='<spring:message code="button.delete" />'><spring:message code="button.delete" /></a></span><!-- 삭제 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:30px" />
			<col style="width:100px" />
			<col style="width:120px" />
			<col style="width:200px" />
			<col style="width:167px" />
			<col style="width:100px" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="fCheckAll();" title="전체선택"/></th><!-- 전체선택 -->
			   <th scope="col"><spring:message code="comSymMnuMpm.menuManage.menuNo"/></th><!-- 메뉴ID -->
			   <th scope="col"><spring:message code="comSymMnuMpm.menuManage.menuNmHn"/></th><!-- 메뉴한글명 -->
			   <th scope="col"><spring:message code="comSymMnuMpm.menuManage.progrmFileNm"/></th><!-- 프로그램파일명 -->
			   <th scope="col"><spring:message code="comSymMnuMpm.menuManage.menuDc"/></th><!-- 메뉴설명 -->
			   <th scope="col"><spring:message code="comSymMnuMpm.menuManage.upperMenuId"/></th><!-- 상위메뉴ID -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${list_menumanage}" varStatus="status">
			  <tr>
			    <td>
			       <input type="checkbox" name="checkField" class="check2" title="선택"/>
			       <input name="checkMenuNo" type="hidden" value="<c:out value='${result.menuNo}'/>"/>
			    </td>
			    <td><c:out value="${result.menuNo}"/></td>
			    <td style="cursor:hand;">
			       <span class="link"><a href="<c:url value='/sym/mnu/mpm/EgovMenuManageListDetailSelect.do?req_menuNo='/>${result.menuNo}" onclick="selectUpdtMenuManageDetail('<c:out value="${result.menuNo}"/>'); return false;"><c:out value="${result.menuNm}"/></a></span>
			    </td>
			    <td><c:out value="${result.progrmFileNm}"/></td>
			    <td><c:out value="${result.menuDc}"/></td>
			    <td><c:out value="${result.upperMenuId}"/></td>
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
</form>
</body>
</html>

