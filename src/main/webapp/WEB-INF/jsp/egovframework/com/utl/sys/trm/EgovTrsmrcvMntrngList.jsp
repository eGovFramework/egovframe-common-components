<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUtlSysTrm.trsmrcvMntrngList.title"/></c:set>
<% 
/**
 * @Class Name : EgovTrsmrcvMntrngList.jsp
 * @Description : 송수신모니터링 목록조회
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.19   김진만          최초 생성
 *
 *  @author 김진만
 *  @since 2010.07.19
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${pageTitle}</title>
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
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
        document.frm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrngList.do'/>";
        document.frm.submit();  
    }
    
    function fn_egov_get_detail_view(cntcId) {
        document.frm.cntcId.value = cntcId;
        document.frm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrng.do'/>";
        document.frm.submit();          
    }
    /* ********************************************************
     * 등록 처리 함수 
     ******************************************************** */
    function fn_egov_get_regist_view(){
        document.frm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrngForRegist.do'/>";
        document.frm.submit();  
    }   

    function fn_egov_log_mntrng_list(){
    	document.frm.action = "<c:url value='/utl/sys/trm/getTrsmrcvMntrngLogList.do'/>";
		document.frm.submit();
    }
</script>

</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1>${pageTitle}</h1>

    <form name="frm" id="frm" action="<c:url value='/utl/sys/trm/getTrsmrcvMntrngList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCondition" class="select" title="검색조건">
				<option value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
				<option value="0" <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationName" /></option><!-- 연계명 -->
				<option value="2" <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerName" /></option><!-- 관리자명 -->
				<option value="3" <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if> ><spring:message code="comUtlSysTrm.trsmrcvMntrng.status" /></option><!-- 상태 -->
				</select>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' maxlength="35" size="25" onkeypress="press(event);" title="검색키워드" />
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_get_list('1'); return false;" />
				<span class="btn_b"><a href="<c:url value='/utl/sys/trm/getTrsmrcvMntrngForRegist.do'/>" onclick="fn_egov_get_regist_view(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></span>
				<span class="btn_b"><a href="<c:url value='/utl/sys/trm/getTrsmrcvMntrngLogList.do'/>" onclick="fn_egov_log_mntrng_list(); return false;" title="<spring:message code="button.log" />"><spring:message code="button.log" /></a></span>
			</li>
		</ul>
	</div>

    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}' default='1' />">
    <input name="cntcId" type="hidden" value="">

    </form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:27%" />
			<col style="width:10%" />
			<col style="width:20%" />
			<col style="width:18%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationID" /></th><!-- 연계ID -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.interOperationName" /></th><!-- 연계명 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.testClassName" /></th><!-- 테스트클래스명 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerName" /></th><!-- 관리자명 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.managerEmail" /></th><!-- 관리자이메일주소 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.monitoringDateTime" /></th><!-- 모니터링시각 -->
			</tr>
			<tr>
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.provider" /></th><!-- 제공기관 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.provideSystem" /></th><!-- 제공시스템 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.service" /></th><!-- 제공서비스 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.requester" /></th><!-- 요청기관 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.requestSystem" /></th><!-- 요청시스템 -->
			   <th scope="col"><spring:message code="comUtlSysTrm.trsmrcvMntrng.status" /></th><!-- 상태 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	        <c:if test="${fn:length(resultList) == 0}">
	        <tr> 
	            <td colspan="6">
	            <spring:message code="common.nodata.msg" />
	            </td>
	        </tr>                                              
	        </c:if>
	         <%-- 데이터를 화면에 출력해준다 --%>
	        <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	          <tr>
	                <td>${resultInfo.cntcId}</td>
	                <td>
	                    <form name="item" method="post" action="/utl/sys/trm/getTrsmrcvMntrng.do">
	                        <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
	                        <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>">
	                        <input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword}"/>">
	                        <input type="hidden" name="dataSourcNm" value="">
	                    <span class="link"><input type="submit" value="<c:out value="${resultInfo.cntcNm}"/>" onclick="fn_egov_get_detail_view('<c:out value="${resultInfo.cntcId}"/>'); return false;"></span>
	                    </form>
	                </td>
	                <td>${resultInfo.testClassNm}</td>
	                <td>${resultInfo.mngrNm}</td>
	                <td>${resultInfo.mngrEmailAddr}</td>
	                <td>${resultInfo.creatDt}</td>
	          </tr>   
	          <tr>
	                <td>${resultInfo.provdInsttNm}</td>
	                <td>${resultInfo.provdSysNm}</td>
	                <td>${resultInfo.provdSvcNm}</td>
	                <td>${resultInfo.requstInsttNm}</td>
	                <td>${resultInfo.requstSysNm}</td>
	                <td>${resultInfo.mntrngSttusNm}</td>
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