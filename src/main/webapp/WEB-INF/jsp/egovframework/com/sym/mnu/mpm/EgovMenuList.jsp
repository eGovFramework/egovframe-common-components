<!DOCTYPE html>

<%--
 /**
  * @Class Name : EgovMenuList.jsp
  * @Description : 메뉴목록 화면
  * @Modification Information
  * @
  * @ 수정일               수정자             수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용               최초 생성
  *   2013.10.04   이기하            메뉴트리 위치 변경
  *   2018.09.10   신용호            표준프레임워크 v3.8 개선
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */

  /* Image Path 설정 */
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
//String imagePath_icon   = "/images/egovframework/com/sym/mnu/mpm/icon/";
//String imagePath_button = "/images/egovframework/com/sym/mnu/mpm/button/";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymMnuMpm.menuList.title" /></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
</script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

<script language="javascript1.2" type="text/javaScript" src="<c:url value='/js/egovframework/com/sym/mnu/mpm/EgovMenuList.js' />"></script>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 메뉴등록 처리 함수
 ******************************************************** */
function insertMenuList() {
	if(!fn_validatorMenuList()){return;}
    if(document.menuManageVO.tmp_CheckVal.value == "U"){alert("<spring:message code="comSymMnuMpm.menuList.validate.checkVal" />"); return;} //상세조회시는 수정혹은 삭제만 가능합니다.
	document.menuManageVO.action = "<c:url value='/sym/mnu/mpm/EgovMenuListInsert.do'/>";
	menuManageVO.submit();

}

/* ********************************************************
 * 메뉴수정 처리 함수
 ******************************************************** */
function updateMenuList() {
    if(!fn_validatorMenuList()){return;}
    if(document.menuManageVO.tmp_CheckVal.value != "U"){alert("<spring:message code="comSymMnuMpm.menuList.validate.checkVal.update" />"); return;} //상세조회시는 수정혹은 삭제만 가능합니다. 초기화 하신 후 등록하세요.
	document.menuManageVO.action = "<c:url value='/sym/mnu/mpm/EgovMenuListUpdt.do'/>";
	menuManageVO.submit();
}

/* ********************************************************
 * 메뉴삭제 처리 함수
 ******************************************************** */
function deleteMenuList() {
    if(!fn_validatorMenuList()){return;}
    if(document.menuManageVO.tmp_CheckVal.value != "U"){alert("<spring:message code="comSymMnuMpm.menuList.validate.checkVal" />"); return;} //상세조회시는 수정혹은 삭제만 가능합니다.
	document.menuManageVO.action = "<c:url value='/sym/mnu/mpm/EgovMenuListDelete.do'/>";
	menuManageVO.submit();
}

/* ********************************************************
 * 메뉴리스트 조회 함수
 ******************************************************** */
function selectMenuList() {
    document.menuManageVO.action = "<c:url value='/sym/mnu/mpm/EgovMenuListSelect.do'/>";
    document.menuManageVO.submit();
}

/* ********************************************************
 * 초기화 함수
 ******************************************************** */
function initlMenuList() {
	document.menuManageVO.menuNo.value="";
	document.menuManageVO.menuOrdr.value="";
	document.menuManageVO.menuNm.value="";
	document.menuManageVO.upperMenuId.value="";
	document.menuManageVO.menuDc.value="";
	document.menuManageVO.relateImagePath.value="";
	document.menuManageVO.relateImageNm.value="";
	document.menuManageVO.progrmFileNm.value="";
	document.menuManageVO.menuNo.readOnly=false;
	document.menuManageVO.tmp_CheckVal.value = "";
}

/* ********************************************************
 * 조회 함수

 ******************************************************** */
function selectMenuListTmp() {
	document.menuManageVO.req_RetrunPath.value = "/sym/mnu/mpm/EgovMenuList";
    document.menuManageVO.action = "<c:url value='/sym/mnu/mpm/EgovMenuListSelectTmp.do'/>";
    document.menuManageVO.submit();
}

/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
 function choiceNodes(nodeNum) {
		var nodeValues = treeNodes[nodeNum].split("|");
		document.menuManageVO.menuNo.value = nodeValues[4];
		document.menuManageVO.menuOrdr.value = nodeValues[5];
		document.menuManageVO.menuNm.value = nodeValues[6];
		document.menuManageVO.upperMenuId.value = nodeValues[7];
		document.menuManageVO.menuDc.value = nodeValues[8];
		document.menuManageVO.relateImagePath.value = nodeValues[9];
		document.menuManageVO.relateImageNm.value = nodeValues[10];
		document.menuManageVO.progrmFileNm.value = nodeValues[11];
		document.menuManageVO.menuNo.readOnly=true;
		document.menuManageVO.tmp_CheckVal.value = "U";
}

/* ********************************************************
 * 입력값 validator 함수
 ******************************************************** */
function fn_validatorMenuList() {

	if(document.menuManageVO.menuNo.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuNo.notNull" />"); return false;} //메뉴번호는 Not Null 항목입니다.
	if(!checkNumber(document.menuManageVO.menuNo.value)){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuNo.onlyNumber" />"); return false;} //메뉴번호는 숫자만 입력 가능합니다.

	if(document.menuManageVO.menuOrdr.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuOrdr.notNull" />"); return false;} //메뉴순서는 Not Null 항목입니다.
	if(!checkNumber(document.menuManageVO.menuOrdr.value)){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuOrdr.onlyNumber" />"); return false;} //메뉴순서는 숫자만 입력 가능합니다.

	if(document.menuManageVO.upperMenuId.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.upperMenuId.notNull" />"); return false;} //상위메뉴번호는 Not Null 항목입니다.
	if(!checkNumber(document.menuManageVO.upperMenuId.value)){alert("<spring:message code="comSymMnuMpm.menuList.validate.upperMenuId.onlyNumber" />"); return false;} //상위메뉴번호는 숫자만 입력 가능합니다.

	if(document.menuManageVO.progrmFileNm.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.progrmFileNm.notNull" />"); return false;} //프로그램파일명은 Not Null 항목입니다.
	if(document.menuManageVO.menuNm.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuNm.notNull" />"); return false;} //메뉴명은 Not Null 항목입니다.

    return true;
}

/* ********************************************************
 * 필드값 Number 체크 함수
 ******************************************************** */
function checkNumber(str) {
    var flag=true;
    if (str.length > 0) {
        for (i = 0; i < str.length; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                flag=false;
            }
        }
    }
    return flag;
}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
<script type="text/javascript">
    $(document).ready(function () {
    	// 파일검색 화면 호출 함수
        $('#popupProgrmFileNm').click(function (e) {
        	e.preventDefault();
            //var page = $(this).attr("href");
            var pagetitle = $(this).attr("title");
            var page = "<c:url value='/sym/prm/EgovProgramListSearchNew.do'/>";
            var $dialog = $('<div></div>')
            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
            .dialog({
            	autoOpen: false,
                modal: true,
                width: 550,
                height: 650,
                title: pagetitle
        	});
        	$dialog.dialog('open');
    	});
        // 메뉴이동 화면 호출 함수
        $('#popupUpperMenuId').click(function (e) {
        	e.preventDefault();
            //var page = $(this).attr("href");
            var pagetitle = $(this).attr("title");
            var page = "<c:url value='/sym/mnu/mpm/EgovMenuListSelectMvmnNew.do'/>";
            var $dialog = $('<div style="overflow:hidden;padding: 0px 0px 0px 0px;"></div>')
            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
            .dialog({
            	autoOpen: false,
                modal: true,
                width: 600,
                height: 550,
                title: pagetitle
        	});
        	$dialog.dialog('open');
    	});
	});
</script>

</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div id="border" style="width:730px">
<table border="0">
  <tr>
    <td width="700">
<!-- ********** 여기서 부터 본문 내용 *************** -->


<form name="menuManageVO" action ="<c:url value='/sym/mnu/mpm/EgovMenuListInsert.do' />" method="post">
<input type="hidden" name="req_RetrunPath" value="/sym/mnu/mpm/EgovMenuList">

<div class="board">
	<h1 style="background-position:left 3px"><spring:message code="comSymMnuMpm.menuList.pageTop.title" /></h1><!-- 메뉴 목록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<span class="btn_b"><a href="<c:url value='/sym/mnu/mpm/EgovMenuListSelect.do'/>" onclick="initlMenuList(); return false;" title="<spring:message code="button.init" />"><spring:message code="button.init" /></a></span><!-- 초기화 -->
				<input class="s_btn" type="submit" value='<spring:message code="button.save" />' title='<spring:message code="button.save" />' onclick="insertMenuList(); return false;" />
				<span class="btn_b"><a href="#LINK" onclick="updateMenuList(); return false;" title='<spring:message code="button.update" />'><spring:message code="button.update" /></a></span>
				<span class="btn_b"><a href="#LINK" onclick="deleteMenuList(); return false;" title='<spring:message code="button.delete" />'><spring:message code="button.delete" /></a></span>
			</li>
		</ul>
	</div>
</div>



<div id="main" style="display:">

<%-- <table width="717" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="100%" class="title_left">
   <h1><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" alt="">&nbsp;메뉴 목록</h1></td>
 </tr>
</table> --%>


<table>
	<colgroup>
		<col style="width:240px" />
		<col style="" />
	</colgroup>
  <tr>
   <td style="vertical-align:top">
	<c:forEach var="result" items="${list_menulist}" varStatus="status" >
	<input type="hidden" name="tmp_menuNmVal" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.menuNo}|${result.menuOrdr}|${result.menuNm}|${result.upperMenuId}|${result.menuDc}|${result.relateImagePath}|${result.relateImageNm}|${result.progrmFileNm}|">
	</c:forEach>
	
	<div class="tree" style="overflow:scroll; width:218px; height:383px; padding:5px; border:1px solid #ddd">
		<script language="javascript" type="text/javaScript">
		    var chk_Object = true;
		    var chk_browse = "";
			if (eval(document.menuManageVO.req_RetrunPath)=="[object]") chk_browse = "IE";
			if (eval(document.menuManageVO.req_RetrunPath)=="[object NodeList]") chk_browse = "Fox";
			if (eval(document.menuManageVO.req_RetrunPath)=="[object Collection]") chk_browse = "safai";
	
			var Tree = new Array;
			if(chk_browse=="IE"&&eval(document.menuManageVO.tmp_menuNmVal)!="[object]"){
			   alert("<spring:message code="comSymMnuMpm.menuList.validate.chkBrowse" />"); //메뉴 목록 데이타가 존재하지 않습니다.
			   chk_Object = false;
			}
			if(chk_browse=="Fox"&&eval(document.menuManageVO.tmp_menuNmVal)!="[object NodeList]"){
			   alert("<spring:message code="comSymMnuMpm.menuList.validate.chkBrowse" />"); //메뉴 목록 데이타가 존재하지 않습니다.
			   chk_Object = false;
			}
			if(chk_browse=="safai"&&eval(document.menuManageVO.tmp_menuNmVal)!="[object Collection]"){
				   alert("<spring:message code="comSymMnuMpm.menuList.validate.chkBrowse" />"); //메뉴 목록 데이타가 존재하지 않습니다.
				   chk_Object = false;
			}
			if( chk_Object ){
				for (var j = 0; j < document.menuManageVO.tmp_menuNmVal.length; j++) {
					Tree[j] = document.menuManageVO.tmp_menuNmVal[j].value;
			    }
			    createTree(Tree);
	           }else{
	               alert("<spring:message code="comSymMnuMpm.menuList.validate.chkObject" />"); //메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.
	           }
		</script>
	</div>
   </td>
   <%-- <td width="*" class="title_left">
	   <table border="0" cellspacing="0" cellpadding="0" align="left">
		<tr>
          <td width="90%"></td>
          <td><span class="button"><a href="<c:url value='/sym/mnu/mpm/EgovMenuListSelect.do'/>" onclick="initlMenuList(); return false;">초기화</a></span></td>
          <td width="2%"></td>
          <td><span class="button"><input type="submit" value="<spring:message code="button.save" />" onclick="insertMenuList(); return false;"></span></td>
          <td width="2%"></td>
          <td><span class="button"><a href="#LINK" onclick="updateMenuList(); return false;"><spring:message code="button.update" /></a></span></td>
          <td width="2%"></td>
          <td><span class="button"><a href="#LINK" onclick="deleteMenuList(); return false;"><spring:message code="button.delete" /></a></span></td>
		</tr>
	   </table>
   </td> --%>
   <td style="vertical-align:top">

		<table class="wTable" >
			<colgroup>
				<col style="width:30%" />
				<col style="" />
			</colgroup>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.menuNo" /> <span class="pilsu">*</span></th><!-- 메뉴No -->
		    <td class="left">
		      <input name="menuNo" type="text" value=""  maxlength="10" title="<spring:message code="comSymMnuMpm.menuList.menuNo" />" style="width:68px"/>
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.menuOrdr" /> <span class="pilsu">*</span></th><!-- 메뉴순서 -->
		    <td class="left">
		      <input name="menuOrdr" type="text" value=""  maxlength="10" title="<spring:message code="comSymMnuMpm.menuList.menuOrdr" />" style="width:68px"/>
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.menuNm" /> <span class="pilsu">*</span></th><!-- 메뉴명 -->
		    <td class="left">
		      <input name="menuNm" type="text" size="30" value=""  maxlength="30" title="<spring:message code="comSymMnuMpm.menuList.menuNm" />">
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.upperMenuId" /> <span class="pilsu">*</span></th><!-- 상위메뉴No -->
		    <td class="left">
		    <input name="upperMenuId" type="text" value=""  maxlength="10" title="<spring:message code="comSymMnuMpm.menuList.upperMenuId" />" style="width:190px"/>
	        <a id="popupUpperMenuId" href="/sym/mnu/mpm/EgovMenuListSelectMvmn.do" target="_blank" title="<spring:message code="comSymMnuMpm.menuList.upperMenuId" />" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />"
	         alt='' width="15" height="15" />(<spring:message code="comSymMnuMpm.menuList.mvmnMenuList" />)</a><!-- 메뉴선택 검색 -->
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.progrmFileNm" /> <span class="pilsu">*</span></th><!-- 파일명 -->
		    <td class="left">
	        <input name="progrmFileNm" type="text" size="30" value=""  maxlength="60" title="<spring:message code="comSymMnuMpm.menuList.progrmFileNm" />" style="width:190px"/>
	        <a id="popupProgrmFileNm" href="/sym/prm/EgovProgramListSearch.do" target="_blank" title="<spring:message code="comSymMnuMpm.menuList.progrmFileNm" />" style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />"
	         alt='' width="15" height="15" />(<spring:message code="comSymMnuMpm.menuList.searchFileNm" />)</a>
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.relateImageNm" /> <span class="pilsu">*</span></th><!-- 관련이미지명 -->
		    <td width="70%" nowrap>
		      <input name="relateImageNm" type="text" size="30" value=""  maxlength="30" title="<spring:message code="comSymMnuMpm.menuList.relateImageNm" />">
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.relateImagePath" /> <span class="pilsu">*</span></th><!-- 관련이미지경로 -->
		    <td>
		      <input name="relateImagePath" type="text" size="30" value=""  maxlength="60" title="<spring:message code="comSymMnuMpm.menuList.relateImagePath" />">
		    </td>
		  </tr>
		  <tr>
		    <th><spring:message code="comSymMnuMpm.menuList.menuDc" /></th><!-- 메뉴설명 -->
		    <td width="70%">
		      &nbsp; <textarea name="menuDc" class="textarea"  cols="45" rows="8"  style="width:350px;" title="<spring:message code="comSymMnuMpm.menuList.menuDc" />"></textarea>
		    </td>
		  </tr>
		</table>

   </td>
 </tr>
</table>

    <input type="hidden" name="tmp_SearchElementName" value="">
    <input type="hidden" name="tmp_SearchElementVal" value="">
    <input type="hidden" name="tmp_CheckVal" value="">
</div>

</form>

<!-- ********** 여기까지 내용 *************** -->
</td>
</tr>
</table>
</DIV>
</body>
</html>

