<% 
/**
 * @Class Name : EgovLeaderSttusRegist.jsp
 * @Description : 간부상태 등록
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.10.19   장철호          최초 생성
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.10.19
 *  @version 1.0 
 *  @see
 *  
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="/images/egovframework/com/cop/smt/lsm/"/>
<c:set var="CssUrl" value="/css/egovframework/com/cop/smt/lsm/"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="leaderSttusVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_LeaderSttus(){
		
		if("${leaderIdDuplicated}" == "true"){
			alert("등록하려는 간부상태 정보가 이미 등록되어 있습니다.");
		}

		document.getElementsByName('leaderSttus')[0].checked = true;
	}

	function fn_egov_insert_leadersttus() {
		if (!validateLeaderSttusVO(document.leaderSttusVO)){
			return;
		}
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.leaderSttusVO.action = "<c:url value='/cop/smt/lsm/mng/insertLeaderSttus.do'/>";
			document.leaderSttusVO.submit();					
		}
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_leadersttus(){
		document.leaderSttusVO.action = "<c:url value='/cop/smt/lsm/mng/selectLeaderSttusList.do'/>";
		document.leaderSttusVO.submit();	
	}	

	/* ********************************************************
	* 아이디  팝업창열기
	* fn_egov_schdulCharger_LeaderSchdule
	* param 타이틀, ID 폼명, 사원번호 폼명, 사원명 폼명, 부서명 폼명
	******************************************************** */
	function fn_egov_schdulCharger_LeaderSchdule(strTitle, frmUniqId, frmEmplNo, frmEmplyrNm, frmOrgnztNm){
		var arrParam = new Array(6);
		arrParam[0] = window;
		arrParam[1] = strTitle;
		arrParam[2] = frmUniqId;
		arrParam[3] = frmEmplNo;
		arrParam[4] = frmEmplyrNm;
		arrParam[5] = frmOrgnztNm;

	 	window.showModalDialog("<c:url value='/cop/smt/lsm/selectEmplyrListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}

</script>
<title>간부상태 등록</title>
</head>
<body onLoad="fn_egov_init_LeaderSttus()">

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="border" style="width:730px">

<form:form modelAttribute="leaderSttusVO" name="leaderSttusVO" method="post" action="${pageContext.request.contextPath}/cop/smt/lsm/mng/insertLeaderSttus.do' />">

	<table width="100%" cellpadding="8" class="table-search" border="0">
	 <tr>
	  <td width="100%"class="title_left">
	   <h1><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">
	   &nbsp;간부상태 등록</h1></td>
	 </tr>
	</table>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="이 표는 간부상태 정보를  등록하기 위한 표이며, 간부, 상태 정보로 구성되어 있습니다 .">
	<caption>간부상태 등록</caption>
	<tbody>
	  <tr> 
		<th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="orgnztNm">부서명</label><img src="${ImgUrl}icon/no_required.gif' />" width="15" height="15" alt="선택항목"></th>
		<td width="80%" >
			<input type="text" id="orgnztNm" name="orgnztNm" size="15" maxlength="10" title="부서명" class="readOnlyClass" readOnly>
		</td>
	  </tr>
	  <tr> 
		<th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="leaderName">간부명</label><img src="${ImgUrl}icon/required.gif" width="15" height="15" alt="필수항목"></th>
		<td width="80%" >
			<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td width="100px" style="padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;">
			<form:input path="leaderNm" size="15" readonly="true" maxlength="10" title="간부명"/>
			</td>
			<td style="padding:0px 0px 0px 2px;margin:0px 0px 0px 0px;">
			<a href="<c:url value='/cop/smt/lsm/selectEmplyrListPopup.do' />" target="_blank"  title="새 창으로 이동"  onClick="fn_egov_schdulCharger_LeaderSchdule('간부', 'leaderId', '', 'leaderNm', 'orgnztNm');return false;">
			<img src="${ImgUrl}icon/search2.gif' />" style="border:0px" alt="간부선택 검색" title="간부선택 검색">
			</a>
			</td>
		</tr>
		</table>
		<div><form:errors path="leaderNm" cssClass="error"/></div>
       <form:hidden path="leaderId" />
		</td>
	  </tr>
	  <tr> 
	    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="leaderSttus">간부상태</label><img src="${ImgUrl}icon/required.gif" width="15" height="15" alt="필수항목"></th>
	    <td width="80%" >
			<form:select path="leaderSttus" title="간부상태">
	            <form:options items="${leaderSttus}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
			<div><form:errors path="leaderSttus" cssClass="error"/></div>
	    </td>
	  </tr>
	</tbody>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td height="10"></td>
	  </tr>
	</table>
  	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	  <td><span class="button"><input type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_insert_leadersttus(); return false;"></span></td>
      <td>&nbsp;&nbsp;</td>
      <td><span class="button"><a href="<c:url value='/cop/smt/lsm/mng/selectLeaderSttusList.do'/>?searchWrd=<c:out value='${leaderSttusVO.searchWrd}'/>&amp;searchCnd=<c:out value='${leaderSttusVO.searchCnd}'/>&amp;pageIndex=<c:out value='${leaderSttusVO.pageIndex}'/>" onclick="fn_egov_list_leadersttus(); return false;"><spring:message code="button.list" /></a></span></td>
	</tr>
	</table>
	</div>
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${leaderSttusVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${leaderSttusVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${leaderSttusVO.pageIndex}'/>" />
    <!-- 검색조건 유지 -->
</form:form>

</div>

</body>
</html>
