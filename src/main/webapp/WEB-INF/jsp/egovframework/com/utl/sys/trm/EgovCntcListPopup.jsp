<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovCntcListPopup.jsp
 * @Description : 연계 목록조회
 * @Modification Information
 * @
 * @ 수정일               수정자            수정내용
 * @ ----------   --------   ---------------------------
 * @ 2010.07.22   김진만            최초 생성
 * @ 2020.06.30   신용호            화면 오류 수정
 *
 *  @author 김진만
 *  @since 2010.07.22
 *  @version 1.0 
 *  @see
 *  
 */
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>연계 조회</title>
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
                alert("검색조건을 선택하세요.");
                return;
            }
        }
        document.frm.pageIndex.value = pageNo; 
        document.frm.action = "<c:url value='/utl/sys/trm/getCntcList.do'/>";
        document.frm.submit();  
    }

    // 팝업검색 결과를 호출자에게 리턴하고 화면을 닫는다.
    function fn_egov_return_cntc(cntcId, cntcNm, provdInsttNm, provdSysNm, provdSvcNm, requstInsttNm, requstSysNm) {
    	getDialogArguments();
        var opener = parent.window.dialogArguments;

        opener.document.getElementById("cntcId").value = cntcId;
        opener.document.getElementById("cntcNm").value = cntcNm;
        opener.document.getElementById("provdInsttNm").innerHTML = provdInsttNm;
        opener.document.getElementById("provdSysNm").innerHTML = provdSysNm;
        opener.document.getElementById("provdSvcNm").innerHTML = provdSvcNm;
        opener.document.getElementById("requstInsttNm").innerHTML = requstInsttNm;
        opener.document.getElementById("requstSysNm").innerHTML = requstSysNm;

        setReturnValue(true);
        parent.window.returnValue = true;
        parent.window.close();
    }
    
</script>

</head>
<body>

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div class="board">
	<h1>연계 조회</h1>

	<div class="search_box" title="이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다.">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="검색조건">
				<option value=''>--선택하세요--</option>
				<option value="0" <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> >연계명</option>
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' maxlength="35" size="35" onkeypress="press(event);" title="검색조건" />
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_get_list('1'); return false;" />
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:5%" />
			
		</colgroup>
		<thead>
			<tr>
			   <th scope="col">연계명</th>
			   <th scope="col">제공기관</th>
			   <th scope="col">제공시스템</th>
			   <th scope="col">제공서비스</th>
			   <th scope="col">요청기관</th>
			   <th scope="col">요청시스템</th>
			   <th scope="col">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	        <c:if test="${fn:length(resultList) == 0}">
	        <tr> 
	            <td colspan="7">
	            <spring:message code="common.nodata.msg" />
	            </td>
	        </tr>                                              
	        </c:if>
	         <%-- 데이터를 화면에 출력해준다 --%>
	        <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	          <tr>
	              <td>${resultInfo.cntcNm}</td>
	              <td>${resultInfo.provdInsttNm}</td>
	              <td>${resultInfo.provdSysNm}</td>
	              <td>${resultInfo.provdSvcNm}</td>
	              <td>${resultInfo.requstInsttNm}</td>
	              <td>${resultInfo.requstSysNm}</td>
	              <td>
	                <input class="btn01" type="submit" value="선택" 
	                    onclick="fn_egov_return_cntc('<c:out value="${resultInfo.cntcId}"/>', '<c:out value="${resultInfo.cntcNm}"/>', 
	                                                 '<c:out value="${resultInfo.provdInsttNm}"/>', '<c:out value="${resultInfo.provdSysNm}"/>', 
	                                                 '<c:out value="${resultInfo.provdSvcNm}"/>', '<c:out value="${resultInfo.requstInsttNm}"/>',
	                                                 '<c:out value="${resultInfo.requstSysNm}"/>'); return false;">
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