<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovBatchOpertListPopup.jsp
 * @Description : 배치작업관리 목록조회팝업
 * @Modification Information
 * @
 * @ 수정일               수정자             수정내용
 * @ ----------   --------   ---------------------------
 * @ 2010.08.24   김진만            최초 생성
 *   2018.09.18   신용호            공통컴포넌트 3.8 개선
 *
 *  @author 김진만
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymBat.batchOpertListPopup.title"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javascript">

    function press(event) {
        if (event.keyCode==13) {
            fn_egov_get_list('1');
        }
    }

    function fn_egov_get_list(pageNo) {
        if (document.frm.searchKeyword.value != "") {
            if (document.frm.searchCondition.value == "") {
                alert("<spring:message code="comSymBat.batchOpertListPopup.validate.searchKeyword"/>");
                return;
            }
        }
        document.frm.pageIndex.value = pageNo; 
        document.frm.action = "<c:url value='/sym/bat/getBatchOpertList.do?popupAt=Y'/>";
        document.frm.submit();  
    }
    
    // 팝업검색 결과를 호출자에게 리턴하고 화면을 닫는다.
    function fn_egov_return_batch_opert(batchOpertId, batchOpertNm) {
    	getDialogArguments();
        var opener = parent.window.dialogArguments;

        opener.document.getElementById("batchOpertId").value = batchOpertId;
        opener.document.getElementById("batchOpertNm").value = batchOpertNm;

//         setReturnValue(true);
        
        parent.window.returnValue = true;
        parent.window.close();
    }
    
</script>

</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymBat.batchOpertListPopup.pageTop.title"/></h1><!-- 배치작업 조회 -->

    <form name="frm" id="frm" action="<c:url value='/sym/bat/getBatchOpertList.do?popupAt=Y'/>" method="post">

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCondition" class="select" title="<spring:message code="comSymBat.batchOpertListPopup.searchCondition"/>" ><!-- 검색조건구분 -->
				<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
				<option value="0" <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comSymBat.batchOpertListPopup.batchOpertNm"/></option><!-- 배치작업명 -->
				<option value="1" <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comSymBat.batchOpertListPopup.batchProgrm"/></option><!-- 배치프로그램 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' maxlength="35" size="35" onkeypress="press(event);" title="검색키워드" /><!-- 검색키워드 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_get_list('1'); return false;" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}' default="1"/>">
    <input name="batchOpertId" type="hidden" value="">
    </form>
	

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="" />
			<col style="width:20%" />
			<col style="width:40%" />
			<col style="width:15%" />
			<col style="width:10%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymBat.batchOpertListPopup.batchOpertId"/></th><!-- 배치작업ID -->
			   <th scope="col"><spring:message code="comSymBat.batchOpertListPopup.batchOpertNm"/></th><!-- 배치작업명 -->
			   <th scope="col"><spring:message code="comSymBat.batchOpertListPopup.batchProgrm"/></th><!-- 배치프로그램 -->
			   <th scope="col"><spring:message code="comSymBat.batchOpertListPopup.paramtr"/></th><!-- 파라미터 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	        <c:if test="${fn:length(resultList) == 0}">
	        <tr> 
	            <td colspan="5">
	            <spring:message code="common.nodata.msg" />
	            </td>
	        </tr>                                              
	        </c:if>
	         <%-- 데이터를 화면에 출력해준다 --%>
	        <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	          <tr>
                <td>${resultInfo.batchOpertId}</td>
                <td>${resultInfo.batchOpertNm}</td>
                <td>${resultInfo.batchProgrm}</td>
                <td>${resultInfo.paramtr}</td>
                <td>
                  <input class="btn01" type="submit" value="선택" onclick="fn_egov_return_batch_opert('<c:out value="${resultInfo.batchOpertId}"/>', '<c:out value="${resultInfo.batchOpertNm}"/>'); return false;">
                </td>
	          </tr>   
	        </c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_get_list"/>
		</ul>
	</div>
</div>

</body>
</html>