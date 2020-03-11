<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/"/>
<%
 /**
  * @Class Name : EgovTemplateInqirePop.jsp
  * @Description : 템플릿 목록 조회 팝업화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.18   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.18
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_tmplatInfo('1');
		}
	}
	function fn_egov_select_tmplatInfo(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/tpl/selectTemplateInfsPop.do'/>";
		document.frm.submit();
	}

	function fn_egov_returnTmplatInfo(tmplatId, tmplatNm){
		var retVal = tmplatId +"|"+tmplatNm;
		parent.fn_egov_returnValue(retVal);
	}

</script>
<title>템플릿 목록</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>
<form name="frm" action ="<c:url value='/cop/tpl/selectTemplateInfsPop.do'/>" method="post">
<input type="hidden" name="tmplatId" value="" />
	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="40%"class="title_left">
	  	<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">&nbsp;템플릿 목록
	  	</h1>
	  </td>
	  <th >
	  </th>
	  <td width="10%" >
	   		<select name="searchCnd" class="select" title="검색조건선택">
			   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >템플릿명</option>
			   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >템플릿구분</option>
		   </select>
		</td>
	  <td width="35%">
	    <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
	  </td>
	  <th width="10%">
	   <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
		 <input name="typeFlag" type="hidden" value="<c:out value='${typeFlag}'/>"/>
	      <td><img src="${ImgUrl}btn/bu2_left.gif"  width="8" height="20" alt="버튼이미지"></td>
	      <td class="btnBackground" nowrap><input type="submit" value="<spring:message code="button.inquire" />" onclick="fn_egov_select_tmplatInfo('1'); return false;" class="btnNew" style="height:20px;width:26px;padding:0px 0px 0px 0px;" ></td>
	      <td><img src="${ImgUrl}btn/bu2_right.gif"  width="8" height="20" alt="버튼이미지"></td>
	    </tr>
	   </table>
	  </th>
	 </tr>
	</table>
	<table width="100%" cellpadding="8" class="table-line" border="0"  summary="번호, 템플릿명, 템플릿구분, 템플릿경로, 사용여부, 등록일자, 선택   목록입니다"  >
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th scope="col" class="title" width="5%" nowrap>번호</th>
	    <th scope="col" class="title" width="15%" nowrap>템플릿명</th>
	    <th scope="col" class="title" width="10%" nowrap>템플릿구분</th>
	    <th scope="col" class="title" width="37%" nowrap>템플릿경로</th>
	    <th scope="col" class="title" width="5%" nowrap>사용여부</th>
	    <th scope="col" class="title" width="10%" nowrap>등록일자</th>
	    <th scope="col" class="title" width="8%" nowrap>선택</th>
	  </tr>
	 </thead>
	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.tmplatNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.tmplatSeCodeNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.tmplatCours}"/></td>
		    <td class="lt_text3" nowrap>
		    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
		    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
		    </td>
			<td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td	>
			<td class="lt_text3" nowrap>
		    	<c:if test="${result.useAt == 'Y'}">
		    		<input type="button" name="selectTmplat" value="선택"
		    			onClick="javascript:fn_egov_returnTmplatInfo('<c:out value="${result.tmplatId}"/>','<c:out value="${result.tmplatNm}"/>')" />
		    	</c:if>
			</td>
		  </tr>
		 </c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td class="lt_text3" nowrap colspan="7" ><spring:message code="common.nodata.msg" /></td>
		  </tr>
		 </c:if>

	 </tbody>
	 <!--tfoot>
	  <tr class="">
	   <td colspan=6 align="center"></td>
	  </tr>
	 </tfoot -->
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_select_tmplatInfo" />
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td><img src="<c:out value="${ImgUrl}" />btn/bu2_left.gif" width="8" height="20" alt="버튼이미지"></td>
		<td class="btnBackground" nowrap><a href="#LINK" onclick="javascript:parent.close(); return false;">닫기</a></td>
		<td><img src="<c:out value="${ImgUrl}" />btn/bu2_right.gif" width="8" height="20" alt="버튼이미지"></td>
	</tr>
	</table>
	</div>
</form>
</body>
</html>
