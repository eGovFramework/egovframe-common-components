<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/"/>

<%
 /**
  * @Class Name : EgovUserListPop.jsp
  * @Description : 사용자 목록화면
  * @Modification Information
  * @
  * @ 수정일             수정자          수정내용
  * @ ----------  --------  ---------------------------
  * @ 2009.04.06  이삼섭          최초 생성
  *   2018.09.11  신용호          공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.06
  *  @version 1.0
  *  @see
  *
  */
%>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopCom.userListPop.title"/></title><!-- 사용자 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_userInfo('1');
		}
	}
	function fn_egov_returnUserInfo(uniqId, userNm, userId){
		var retVal = uniqId +"|"+userNm+"|"+userId;
		parent.fn_egov_returnValue(retVal);

	}

	function fn_egov_select_userInfo(pageIndex){
		var _target = document.frm.targetMethod.value;
		var actionUrl;

		if(_target == 'selectClubOprtrList'){
			actionUrl = "<c:url value='/cop/com/selectClubOprtrList.do'/>";
		}else if(_target == 'selectClubUserList'){
			actionUrl = "<c:url value='/cop/com/selectClubUserList.do'/>";
		}else if(_target == 'selectCmmntyMngrList'){
			actionUrl = "<c:url value='/cop/com/selectCmmntyMngrList.do'/>";
		}else if(_target == 'selectCmmntyUserList'){
			actionUrl = "<c:url value='/cop/com/selectCmmntyUserList.do'/>";
		}else{
			actionUrl = "<c:url value='/cop/com/selectUserList.do'/>";
		}
		document.frm.pageIndex.value = pageIndex;
		document.frm.action = actionUrl;
		document.frm.submit();
	}

	function fn_egov_close(){
		parent.closeWindow();
	}
</script>
</head>
<body>

<div class="board" style="width:800px">
	<h1><spring:message code="comCopCom.userListPop.pageTop.title"/></h1><!-- 사용자 목록 -->

	<form name="frm" action ="<c:url value='/cop/com/selectUserList.do'/>" method="post">
	<input type="hidden" name="targetMethod" value="${targetMethod}" />
	<input type="hidden" name="trgetId" value="${trgetId}" />
	<input type="hidden" name="PopFlag" value="Y" />

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<select name="searchCnd" class="select" title="<spring:message code="select.searchCondition"/>">
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopCom.userListPop.userNm"/></option><!-- 사용자명 -->
				</select>
				<input class="s_input2 vat" name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' size="35" maxlength="35" onkeypress="press(event);" title="<spring:message code="title.search"/>" /><!-- 검색어 입력 -->
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="javascript:fn_egov_select_userInfo('1');return false;" title="<spring:message code="button.inquire" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
	
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="width:23%" />
			<col style="width:12%" />
			<col style="width:8%" />
			<col style="width:8%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comCopCom.userListPop.index"/></th><!-- 번호 -->
			   <th scope="col"><spring:message code="comCopCom.userListPop.userId"/></th><!-- 사용자아이디 -->
			   <th scope="col"><spring:message code="comCopCom.userListPop.userNm"/></th><!-- 사용자명 -->
			   <th scope="col"><spring:message code="comCopCom.userListPop.userAdres"/></th><!-- 주소 -->
			   <th scope="col"><spring:message code="comCopCom.userListPop.userEmail"/></th><!-- 이메일 -->
			   <th scope="col"><spring:message code="comCopCom.userListPop.useAt"/></th><!-- 사용여부 -->
			   <th scope="col"><spring:message code="comCopCom.userListPop.select"/></th><!-- 선택 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			  <tr>
			    <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
			    <td><c:out value="${result.userId}" /></td>
			    <td><c:out value="${result.userNm}" /></td>
			    <td><c:out value="${result.userAdres}" /></td>
			    <td><c:out value="${result.userEmail}" /></td>
			    <td>
			    <c:choose>
			    	<c:when test="${result.useAt == 'Y'}">
			    		<spring:message code="button.use" />
			    	</c:when>
			    	<c:otherwise>
			    		<spring:message code="button.notUsed" />
			    	</c:otherwise>
			    </c:choose>
			    </td>
				<td>
				<c:if test="${result.useAt == 'Y' || result.deletedAt == 'Y'}">
		    		<input type="button" name="selectUser" value="<spring:message code="input.cSelect"/>" title="<spring:message code="input.cSelect"/>"
			    			onclick="fn_egov_returnUserInfo('<c:out value="${result.uniqId}" />','<c:out value="${result.userNm}" />','<c:out value="${result.userId}" />');"  /><!-- 선택 -->
				</c:if>
				</td>
			  </tr>
			 </c:forEach>
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td colspan="7"><spring:message code="common.nodata.msg" /></td>
			  </tr>
			 </c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_userInfo"/>
		</ul>
	</div>
</div>

</body>
</html>
