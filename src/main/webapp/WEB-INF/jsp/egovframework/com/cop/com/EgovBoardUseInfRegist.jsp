<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovBoardUseInfRegist.jsp
  * @Description : 게시판  사용정보  등록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.02   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.02
  *  @version 1.0
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardUseInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_select_bbsUseInfs(){
		document.boardUseInf.action = "<c:url value='/cop/com/selectBBSUseInfs.do'/>";
		document.boardUseInf.submit();
	}

	function fn_egov_regist_bbsUseInf(){
		if (!validateBoardUseInf(document.boardUseInf)){
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.boardUseInf.param_trgetType.value = document.boardUseInf.trgetType.value;
			document.boardUseInf.action = "<c:url value='/cop/com/insertBBSUseInf.do'/>";
			document.boardUseInf.submit();
		}
	}

	function fn_egov_inqire_bbsInf(){
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do' />?requestUrl=/cop/bbs/SelectBBSMasterInfsPop.do&width=850&height=520";
		var openParam = "dialogWidth: 900px; dialogHeight: 520px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_cmmntyInqire", openParam, "bbsCallback");
		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardUseInf.bbsId.value = tmp[0];
			document.boardUseInf.bbsNm.value = tmp[1];
		}
	}
	
	function bbsCallback(retVal) {
		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardUseInf.bbsId.value = tmp[0];
			document.boardUseInf.bbsNm.value = tmp[1];
		}
	}

	function fn_egov_selectTargetType(obj) {

		var retVal;
		var _strType = obj.value;

		if (_strType == 'CMMNTY') {
			retVal = fn_egov_inqire_cmmnty();
		} else if (_strType == 'CLUB') {
			retVal = fn_egov_inqire_club();
		} else if (_strType == '') {
			retVal = "|";
		} else {
			retVal = "SYSTEM_DEFAULT_BOARD"+"|"+"시스템 활용";
		}

		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardUseInf.trgetId.value = tmp[0];
			document.boardUseInf.trgetNm.value = tmp[1];
		}
	}

	function fn_egov_inqire_cmmnty() {
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do' />?requestUrl=/cop/cmy/selectCmmntyInfsByPop.do&width=850&height=360";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_cmmntyInqire", openParam, "communityCallback");
		return retVal;
	}
	
	function communityCallback(retVal) {
		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardUseInf.trgetId.value = tmp[0];
			document.boardUseInf.trgetNm.value = tmp[1];
		}
	}

	function fn_egov_inqire_club() {
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do' />?requestUrl=/cop/clb/selectClubInfsByPop.do&width=850&height=360";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_cmmntyInqire", openParam, "clubCallback");
		return retVal;
	}
	
	function clubCallback(retVal) {
		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardUseInf.trgetId.value = tmp[0];
			document.boardUseInf.trgetNm.value = tmp[1];
		}
	}
</script>
<title>게시판 사용등록</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body>
<form:form commandName="boardUseInf" name="boardUseInf" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input type="hidden" name="param_trgetType" value="" />

<div id="border" style="width:730px">
	<table width="100%" cellpadding="8" class="table-search" border="0"  summary="게시판명, 커뮤니티 동호회 정보  입니다">
	 <tr>
	  <td width="100%"class="title_left">
	  	<h1>
	  		<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">&nbsp;게시판 사용등록
	  	</h1>
	  </td>
	 </tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register">
	  <tr>
	    <th width="30%" height="23" class="required_text" nowrap ><spring:message code="cop.bbsNm" />
	    <img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
	    <td width="70%" nowrap colspan="3">
	      <input name="bbsId" type="hidden" />
	      <input name="bbsNm" type="text" size="40" value=""  maxlength="40" title="게시판명" readonly />
	      &nbsp;<a href="#LINK" onclick="fn_egov_inqire_bbsInf();" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif' />"
	     			width="15" height="15" align="middle" alt="새창" /></a>
 		<br/><form:errors path="bbsId" />
	    </td>
	  </tr>
	  <tr>
	    <th width="30%" height="23" class="required_text" nowrap >
	    	<label for="trgetType">
	    		<spring:message code="cop.trgetNm" />
	    	</label>
	    	<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
	    </th>
	    <td width="70%" nowrap colspan="3">
		   <select name="trgetType" class="select" title="<spring:message code="cop.trgetNm" />" onChange="javascript:fn_egov_selectTargetType(this)"  >
			   <option selected value=''>--선택하세요--</option>
			   <c:if test="${useCommunity == 'true'}">
			   	<option value="CMMNTY" >커뮤니티</option>
			   </c:if>
			   <c:if test="${useClub == 'true'}">
			   	<option value="CLUB" >동호회</option>
			   </c:if>
			   <option value="SYSTEM" >시스템</option>
		   </select>
		   <input type="hidden" name="trgetId" value="" >
		   <input type="text" name="trgetNm" value=""  size="40" title="<spring:message code="cop.trgetNm" />" readOnly >
		   <br/><form:errors path="trgetId" />
	    </td>
	  </tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	  <!--
      <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
      <a href="javascript:fn_egov_regist_bbsUseInf();" onclick="fn_egov_regist_bbsUseInf(); return false;"><spring:message code="button.create" /></a>
	  </td>
	  <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td> -->

	  <td><span class="button"><input type="submit" title="<spring:message code="button.create" />" value="<spring:message code="button.create" />" onclick="fn_egov_regist_bbsUseInf(); return false;"></span></td>

      <td width="10"></td>
      <td><span class="button">
      <a href="<c:url value='/cop/com/selectBBSUseInfs.do'/>?searchCondition=1" onclick="fn_egov_select_bbsUseInfs(); return false;"><spring:message code="button.list" /></a>
      </span></td>
	</tr>
	</table>
	</div>
</div>
</form:form>
</body>
</html>
