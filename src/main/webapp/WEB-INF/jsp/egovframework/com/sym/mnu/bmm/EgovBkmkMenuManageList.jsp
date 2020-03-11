<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgoBkmkMenuManageList.jsp
  * @Description :  등록한 바로가기메뉴 목록 조회
  * @Modification Information
  * @
  * @ 수정일                수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.09.25   윤성록            최초 생성
  *   2018.09.10   신용호            표준프레임워크 v3.8 개선
  *   2018.10.05   신용호            showModalDialogCallback 추가
  *
  *  @author 공통컴포넌트개발팀  윤성록
  *  @since 2009.09.25
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymMnuBmm.bkmkMenuManageList.title" /></title><!-- 바로가기 메뉴관리 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript">


/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.frm.check1;
    if(document.frm.all_check.checked) {
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
function fn_egov_deleteBkmkInf(){
    var checkField = document.frm.check1;
    var menuId = document.frm.checkMenuId;
    var userId = document.frm.checkUserId;
    var checkMenuIds = "";

    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkMenuIds += ((checkedCount==0? "" : ",") + menuId[i].value);
                    checkedCount++;
                }
            }
            if(checkedCount == 0){
            	alert("선택된 메뉴가 없습니다.");
    			return;
            }
        } else{
            if(checkField.checked) {
                checkMenuIds = menuId.value;
            }else{
            	alert("선택된 메뉴가 없습니다.");
    			return;
            }
        }
    }

	if(confirm("삭제하시겠습니까?")){
   		document.frm.checkMenuIds.value=checkMenuIds;
    	document.frm.action = "<c:url value='/sym/mnu/bmm/EgovBkmkMenuManageDelete.do'/>";
    	document.frm.submit();
    }
}

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_bkmkInfs('1');
		}
	}

	function fn_egov_select_bkmkInfs(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/sym/mnu/bmm/selectBkmkMenuManageList.do'/>";
		document.frm.submit();
	}

	function fn_egov_add_bkmkInfs(){
		document.frm.action = "<c:url value='/sym/mnu/bmm/addBkmkInf.do'/>";
		document.frm.submit();
	}

	function fn_egov_preview_bkmkInfs(){
		var retVal;
		var url = "<c:url value='/sym/mnu/bmm/openPopup.do?requestUrl=/sym/mnu/bmm/previewBkmkInf.do&width=850&height=360'/>";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";
		retVal = window.showModalDialog(url,"p_userInqire", openParam);

		if(retVal != null){
			document.frm.action = retVal;
			document.frm.submit();
		}
	}
	
	function showModalDialogCallback(src) {
		alert(src);
	}
	
</script>

</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comSymMnuBmm.bkmkMenuManageRegist.title" /></h1><!-- 바로가기 메뉴관리 -->

	<form name="frm" action = "<c:url value='/sym/mnu/bmm/selectBkmkMenuManageList.do' />" method="post">
	<input type="hidden" name="checkMenuIds" value = "" >
	<input type="hidden" name="searchCnd" value ="0" >
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comSymMnuBmm.bkmkMenuManageList.menuName" /> : </label><!-- 메뉴명 -->
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' size="35" maxlength="35" onkeypress="press(event);" title="<spring:message code="comSymMnuBmm.bkmkMenuManageList.enterSearchName" />" style="width: 200px" /><!-- 검색단어입력 -->
				<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_bkmkInfs('1'); return false;" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/sym/mnu/bmm/openPopup.do?requestUrl=/sym/mnu/bmm/previewBkmkInf.do&amp;width=850&amp;height=360'/>" onclick="fn_egov_preview_bkmkInfs(); return false;" title="<spring:message code="comSymMnuBmm.bkmkMenuManageList.newWindow" />"><spring:message code="comSymMnuBmm.bkmkMenuManageList.preview" /></a></span><!-- 새창 --><!-- 미리보기 -->
				<span class="btn_b"><a href="<c:url value= 'fn_egov_deleteBkmkInf()'/>" onclick="fn_egov_deleteBkmkInf(); return false;" title="<spring:message code="title.delete" />"><spring:message code="title.delete" /></a></span><!-- 삭제 -->
				<span class="btn_b"><a href="<c:url value='/sym/mnu/bmm/addBkmkInf.do'/>" onclick="fn_egov_add_bkmkInfs(); return false;" title="<spring:message code="button.create" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:40%" />
			<col style="width:50%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="all_check" class="check2" onclick = "fCheckAll();"></th>
			   <th scope="col"><spring:message code="comSymMnuBmm.bkmkMenuManageList.menuName" /></th><!-- 메뉴명 -->
			   <th scope="col"><spring:message code="comSymMnuBmm.bkmkMenuManageList.menuURL" /></th><!-- 메뉴URL -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><input type="checkbox" name="check1" class="check2">
			    	<input name="checkMenuId" type="hidden" value="<c:out value='${result.menuId}'/>">
			   		<input name="checkUserId" type="hidden" value="<c:out value='${result.userId}'/>"></td>
			    <td>
			    	<span><c:out value="${result.menuNm}" escapeXml="false"/></span>
			    </td>
			    <td><c:out value="${result.progrmStrePath}"/></td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td class="lt_text3" nowrap colspan="3" ><spring:message code="common.nodata.msg" /></td>
			  </tr>
			 </c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_bkmkInfs"/>
		</ul>
	</div>

	</form>

</div>

</body>
</html>
