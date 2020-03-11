<%--
/**
 * @Class Name : EgovServerEqpmnRelateRegist.java
 * @Description : EgovServerEqpmnRelateRegist jsp
 * @Modification Information
 * @
 * @ 수정일               수정자              수정내용
 * @ ----------   --------    ---------------------------
 * @ 2010.07.01   lee.m.j     최초 생성
 *   2018.09.07   신용호             공통컴포넌트 3.8 개선
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
<title><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.title"/></title><!-- 서버H/W관계 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncCheckAll() {
    var checkField = document.listForm.delYn;
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

function fncManageChecked() {

    var checkId = document.listForm.checkId;
    var checkRegYn = document.listForm.regYn;
    var checkOrgRegYn = document.listForm.orgRegYn;
    
    var returnValue = "";
    var returnRegYns = "";
    var checkedCount = 0;
    var returnBoolean = false;

    if(checkId) {
        if(checkId.length > 1) {
            for(var i=0; i<checkId.length; i++) {
                if(checkRegYn[i].value != checkOrgRegYn[i].value) {
                    checkedCount++;

                    if(returnValue == "") {
                        returnValue = checkId[i].value;
                        returnRegYns = checkRegYn[i].value;
                    } else { 
                        returnValue = returnValue + ";" + checkId[i].value;
                        returnRegYns = returnRegYns + ";" + checkRegYn[i].value;
                    }
                }
            }

            if(checkedCount > 0) 
                returnBoolean = true;
            else {
                alert("<spring:message code="comSymSymSrv.serverEqpmnRelateRegist.validate.noServer"/>");
                returnBoolean = false;
            }

        } else {

            returnValue = checkId.value;
            returnRegYns = checkRegYn.value;
            returnBoolean = true;

        }
    } else {
        alert("<spring:message code="comSymSymSrv.serverEqpmnRelateRegist.validate.noResults"/>");
    }

    document.listForm.serverEqpmnIds.value = returnValue;
    document.listForm.regYns.value = returnRegYns;

    return returnBoolean;
}

function fncSelectServerEqpmnRelateList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerEqpmnRelateList.do'/>";
    document.listForm.submit();
}

function fncSelectServerList(){
    // document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerList.do'/>";
    document.listForm.submit();
}

function fncAddServerEqpmnRelateInsert() {
    if(fncManageChecked()) {
        if(confirm("<spring:message code="comSymSymSrv.serverEqpmnRelateRegist.validate.save"/>")) {
            document.listForm.action = "<c:url value='/sym/sym/srv/saveServerEqpmnRelate.do'/>";
            document.listForm.submit();
        }
    } else return;
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sym/sym/srv/selectServerEqpmnRelateList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
        fncSelectServerEqpmnRelateList('1');
    }
}
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.pageTop.title"/></h1><!-- 서버H/W 등록 -->

	<form name="listForm" action="<c:url value='/sym/sym/srv/selectServerEqpmnRelateList.do'/>" method="post">
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.serverEqpmnNm"/> : </label>
				<input class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${server.serverId}"/>' size="20" onkeypress="press();" readonly="readonly" />
				<input id="strServerNm" class="s_input2 vat" name="searchKeyword" type="text" value='<c:out value="${server.serverNm}"/>' size="20" onkeypress="press();" title="<spring:message code="title.search"/>" readonly="readonly" /><!-- 검색 -->
				
				<span class="btn_b"><a href="<c:url value='/sym/sym/srv/saveServerEqpmnRelate.do'/>?pageIndex=<c:out value='${serverEqpmnRelateVO.pageIndex}'/>" onclick="fncAddServerEqpmnRelateInsert(); return false;" title='<spring:message code="button.save" />'><spring:message code="button.save" /></a></span><!-- 저장 -->
				<span class="btn_b"><a href="#" onclick="self.close();"><spring:message code="button.close" /></a></span><!-- 닫기 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:25%" />
			<col style="width:25%" />
			<col style="width:20%" />
			<col style="width:14%" />
			<col style="width:15%" />
			<col style="width:1%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.serverEqpmnId"/></th><!-- 서버H/W ID -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.serverEqpmnNm"/></th><!-- 서버H/W 명 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.serverEqpmnIp"/></th><!-- 서버H/W IP -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.serverEqpmnMngrNm"/></th><!-- 관리자 -->
			   <th scope="col"><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.regYn"/></th><!-- 등록여부 -->
			   <th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="serverEqpmnRelate" items="${serverEqpmnRelateList}" varStatus="status">
			  <tr>
			    <td><c:out value="${serverEqpmnRelate.serverEqpmnId}"/></td>
			    <td><c:out value="${serverEqpmnRelate.serverEqpmnNm}"/></td>
			    <td><c:out value="${serverEqpmnRelate.serverEqpmnIp}"/></td>
			    <td><c:out value="${serverEqpmnRelate.serverEqpmnMngrNm}"/></td>
			    <td>
					<label for="regYn">
						<select name="regYn" id="regYn">
							<option value="Y" <c:if test="${serverEqpmnRelate.regYn == 'Y' }">selected</c:if>><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.regYn.Y"/></option><!-- 등록 -->
							<option value="N" <c:if test="${serverEqpmnRelate.regYn == 'N' }">selected</c:if>><spring:message code="comSymSymSrv.serverEqpmnRelateRegist.regYn.N"/></option><!-- 미등록 -->
						</select>
					</label>
			    </td>
			    <td>
			    <input type="hidden" name="checkId" value="<c:out value="${serverEqpmnRelate.serverEqpmnId}"/>" />
			    <input type="hidden" name="orgRegYn" value="<c:out value="${serverEqpmnRelate.regYn}"/>"/>
			    </td>
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

	<input type="hidden" name="serverId" value="<c:out value="${server.serverId}"/>"/>
	<input type="hidden" name="strServerId" value="<c:out value="${server.serverId}"/>"/>
	<input type="hidden" name="serverEqpmnIds"/>
	<input type="hidden" name="regYns"/>
	<input type="hidden" name="pageIndex" value="<c:if test="${empty serverEqpmnRelateVO.pageIndex }">1</c:if><c:if test="${!empty serverEqpmnRelateVO.pageIndex }"><c:out value='${serverEqpmnRelateVO.pageIndex}'/></c:if>">
	</form>
	
</div>

</body>
</html>
 