<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : EgovDeptJobBxList.jsp
 * @Description : 부서업무함 목록조회
 * @Modification Information
 * @
 * @ 수정일               수정자            수정내용
 * @ ----------   --------   ---------------------------
 * @ 2010.07.06   장철호            최초 생성
 * @ 2018.08.30   최두영            V3.8 오류 점검 
 * @ 2018.09.14   최두영            다국어처리&퍼블리싱처리
 * @ 2019.12.11   신용호            KISA 보안약점 조치 (취약한 API 사용)
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.06
 *  @version 1.0 
 *  @see
 *  
 */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtDjm.deptJobBxList.title" /></title><!-- 부서업무함관리 목록조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function fn_egov_init_deptjobbx(){
		var resultNum = <c:out value='${resultNum}'/>;
		var checkedDeptJobBxId = document.frm.checkedDeptJobBxId.value;

		if(checkedDeptJobBxId != "" && resultNum > 0){
	        for(var i=0; i < resultNum; i++) {
				var checkIdValue = document.deptJobBxCheck[i].check1.id;
		        var splitCheckIdValue = checkIdValue.split("::");
	            if(splitCheckIdValue[0] == checkedDeptJobBxId){
	                document.deptJobBxCheck[i].check1.checked = true;

					document.frm.deptJobBxId.value = checkedDeptJobBxId;
	        		document.frm.indictOrdr.value = splitCheckIdValue[1];
	        		document.frm.deptId.value = splitCheckIdValue[2];
	            }
	        }
		}

		if("<c:out value='${indictOrdrChanged}'/>" == "false"){
			alert("<spring:message code="comCopSmtDjm.deptJobBxList.indictOrdrChanged" />");/* 동일 부서명내에서 부서업무함명의 순서를 변경할 수 있습니다. */ 
		}
	}
	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_deptjobbx('1');
		}
	}
	
	function fn_egov_select_deptjobbx(pageNo) {

		if(document.frm.searchWrd.value != ""){
			if(document.frm.searchCnd.value == ""){
				alert("<spring:message code="comCopSmtDjm.deptJobBxList.searchCnd" />");/* 조회조건을 선택하지 않으셨습니다. */
				return;
			}
		}
		
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_deptjobbx(deptJobBxId) {
		document.frm.deptJobBxId.value = deptJobBxId;
		document.frm.action = "<c:url value='/cop/smt/djm/modifyDeptJobBx.do'/>";
		document.frm.submit();	
	}

	function fn_egov_inqire_deptjobbx_ordr(ordrCnd) {
		var num = 0;
		
		for(var i=0; i < <c:out value='${resultNum}'/>; i++) {
            if(document.deptJobBxCheck[i].check1.checked){
                num ++;
            } 
        }

        if(num == 0){
        	alert("<spring:message code="comCopSmtDjm.deptJobBxList.ordrCnd" />");/* 순서를 수정할 부서업무함을 선택하세요. */
        	return;
        }
        
		document.frm.ordrCnd.value = ordrCnd;
		document.frm.action = "<c:url value='/cop/smt/djm/updateDeptJobBxOrdr.do'/>";
		document.frm.submit();	
	}

	function fn_egov_insert_deptjobbx(){	
		document.frm.action = "<c:url value='/cop/smt/djm/addDeptJobBx.do'/>";
		document.frm.submit();
	}

	function fCheck(num, deptJobBxId, indictOrdr, deptId) {		
		document.frm.deptJobBxId.value = deptJobBxId;
		document.frm.indictOrdr.value = indictOrdr;
		document.frm.deptId.value = deptId;

		var checkNum = num;
	    var checkField = document.deptJobBxCheck[checkNum - 1].check1;

		if(checkField) {
               for(var i=0; i < <c:out value='${resultNum}'/>; i++) {
                   if(document.deptJobBxCheck[i].check1.checked){
                       if((checkNum - 1) != i){ 
                    	   document.deptJobBxCheck[i].check1.checked = false;
                       }
                   } 
               }
        } else {
            checkField.checked = true;
        }
	         
	}
</script>
<style type="text/css">
.c_box {position:relative; }
.c_box .btn {position:absolute; right:-25px; top:-17px; }
.c_box .btn a {display:block; width:19px; height:19px; margin-bottom:2px; border:1px solid #ddd; color:#666; font-size:15px; text-align:center; line-height:19px; }
</style>

</head>
<body onLoad="fn_egov_init_deptjobbx()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="board">
	<h1><spring:message code="comCopSmtDjm.deptJobBxList.title" /></h1><!-- 부서업무함관리 목록조회 -->
	
	<form name="frm" method="post" action="<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input type="hidden" name="deptJobBxId">
		<input type="hidden" name="ordrCnd">
		<input type="hidden" name="deptId">
		<input type="hidden" name="indictOrdr" value="0">
		<input type="hidden" name="checkedDeptJobBxId" value="<c:out value='${searchVO.deptJobBxId}'/>">
		
		<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
			<ul>
				<li>
					<label for=""><spring:message code="select.searchCondition" /> : </label><!-- 조회조건선택 -->
					<select class="select" name="searchCnd" title="<spring:message code="select.searchCondition" />">
						<option value=''>--<spring:message code="input.select" />--</option>
						<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobBxList.deptNm" /></option><!-- 부서명 -->
						<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopSmtDjm.deptJobBxList.deptJobNm" /></option><!-- 부서업무함명 -->
					</select>
					<input class="s_input2 vat" type="text" name="searchWrd" value='<c:out value="${searchVO.searchWrd}"/>' size="15" onkeypress="press(event);" title="<spring:message code="title.search" /> <spring:message code="input.input" />" /><!-- 검색어 입력 -->
					<input class="s_btn" type="submit" value="<spring:message code="title.inquire" />" title="<spring:message code="title.inquire" />" onclick="fn_egov_select_deptjobbx('1'); return false;" /><!-- 조회 -->
					<input class="s_btn" type="submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" />" onclick="fn_egov_insert_deptjobbx('1'); return false;" />
				</li>
			</ul>
		</div>
	</form>
	
	<div class="c_box">
		<table class="board_list">
			<caption><spring:message code="comCopSmtDjm.deptJobBxList.deptJobBxManage" /></caption><!-- 부서업무함관리 -->
			<colgroup>
				<col style="width:5%" />
				<col style="width:5%" />
				<col style="width:25%" />
				<col style="" />
				<col style="width:15%" />
				<col style="width:15%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="table.num" /></th><!-- 번호 -->
					<th scope="col"><spring:message code="comCopSmtDjm.deptJobBxList.deptNm" /></th><!-- 부서명 -->
					<th scope="col"><spring:message code="comCopSmtDjm.deptJobBxList.deptJobNm" /></th><!-- 부서업무함명 -->
					<th scope="col"><spring:message code="comCopSmtDjm.deptJobBxList.finalEditor" /></th><!-- 최종수정자 -->
					<th scope="col"><spring:message code="comCopSmtDjm.deptJobBxList.finalEditDate" /></th><!-- 최종수정일 -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td>
							<form name="deptJobBxCheck" method="post" action="<c:url value='/cop/smt/djm/selectDeptJobBxList.do'/>">
								<input type="radio" name="check1" id="<c:out value="${result.deptJobBxId}"/>::<c:out value="${result.indictOrdr}"/>::<c:out value="${result.deptId}"/>" class="check2" onclick="fCheck('<c:out value="${status.count}"/>', '<c:out value="${result.deptJobBxId}"/>','<c:out value="${result.indictOrdr}"/>','<c:out value="${result.deptId}"/>')" title="선택">
								<input type="submit" value="" style="border : 0px;height : 0px;color :#f7f7f7;display:none" >
							</form>
						</td>
						<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td><c:out value="${result.deptNm}"/></td>
						<td>
							<form name="deptJobBxVO" method="post" action="<c:url value='/cop/smt/djm/modifyDeptJobBx.do'/>">
								<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
								<input name="searchCnd" type="hidden" value="<c:out value='${searchVO.searchCnd}'/>">
								<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>">
								<input type="hidden" name="deptJobBxId" value="<c:out value="${result.deptJobBxId}"/>">
								<span class="link"><input type="submit" value="<c:out value="${result.deptJobBxNm}"/>" onclick="javascript:fn_egov_inqire_deptjobbx('<c:out value="${result.deptJobBxId}"/>'); return false;" style="text-align : left;"></span>
							</form>
						</td>
						<td><c:out value="${result.lastUpdusrNm}"/></td>
						<td><c:out value="${fn:substring(result.lastUpdusrPnttm, 0, 10)}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<div class="btn">
			<a href = "#LINK" onclick="fn_egov_inqire_deptjobbx_ordr('up'); return false;">▲</a>
			<a href = "#LINK" onclick="fn_egov_inqire_deptjobbx_ordr('down'); return false;">▼</a>
		</div>
	</div>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_deptjobbx"/>
		</ul>
	</div>
</div>
</body>
</html>
