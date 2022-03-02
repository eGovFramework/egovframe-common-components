<!DOCTYPE html>
<%--
 /**
  * @Class Name : EgovMenuRegist.jsp
  * @Description : 메뉴정보 등록 화면
  * @Modification Information
  * @
  * @ 수정일               수정자             수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용               최초 생성
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
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/sym/mnu/mpm/"/>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymMnuMpm.menuRegist.title"/></title><!-- 메뉴정보등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="menuManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 메뉴이동 화면 호출 함수
 ******************************************************** */
function mvmnMenuList() {
	window.open("<c:url value='/sym/mnu/mpm/EgovMenuListSelectMvmn.do' />",'Pop_Mvmn','scrollbars=yes,width=600,height=600');
}

/* ********************************************************
* 입력값 validator 함수
******************************************************** */
function fn_validatorMenuList() {

	if(document.menuManageVO.menuNo.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuNo.notNull" />"); return false;} //메뉴번호는 핍수입력 항목입니다.
	if(!checkNumber(document.menuManageVO.menuNo.value)){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuNo.onlyNumber" />"); return false;} //메뉴번호는 숫자만 입력 가능합니다.

	if(document.menuManageVO.menuOrdr.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuOrdr.notNull" />"); return false;} //메뉴순서는 핍수입력 항목입니다.
	if(!checkNumber(document.menuManageVO.menuOrdr.value)){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuOrdr.onlyNumber" />"); return false;} //메뉴순서는 숫자만 입력 가능합니다.

	if(document.menuManageVO.upperMenuId.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.upperMenuId.notNull" />"); return false;} //상위메뉴번호는 핍수입력 항목입니다.
	if(!checkNumber(document.menuManageVO.upperMenuId.value)){alert("<spring:message code="comSymMnuMpm.menuList.validate.upperMenuId.onlyNumber" />"); return false;} //상위메뉴번호는 숫자만 입력 가능합니다.

	if(document.menuManageVO.progrmFileNm.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.progrmFileNm.notNull" />"); return false;} //프로그램파일명은 핍수입력 항목입니다.
	if(document.menuManageVO.menuNm.value == ""){alert("<spring:message code="comSymMnuMpm.menuList.validate.menuNm.notNull" />"); return false;} //메뉴명은 핍수입력 항목입니다.

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

/* ********************************************************
 * 입력처리 함수
 ******************************************************** */
function insertMenuManage(form) {
	if(!fn_validatorMenuList()){return;}
	if(confirm("<spring:message code="common.save.msg" />")){

		if(!validateMenuManageVO(form)){
			return;
		}else{
			form.submit();
		}
	}
}

/* ********************************************************
 * 파일목록조회  함수
 ******************************************************** */
function searchFileNm() {
	document.all.tmp_SearchElementName.value = "progrmFileNm";
	window.open("<c:url value='/sym/prm/EgovProgramListSearch.do' />",'Pop_progrmFileNm','width=500,height=600');
}

/* ********************************************************
 * 목록조회  함수
 ******************************************************** */
function selectList(){
	location.href = "<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do' />";
}
/* ********************************************************
 * 파일명 엔터key 목록조회  함수
 ******************************************************** */
function press() {
    if (event.keyCode==13) {
    	searchFileNm();    // 원래 검색 function 호출
    }
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
                width: 610,
                height: 550,
                title: pagetitle
        	});
        	$dialog.dialog('open');
    	});
	});
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="menuManageVO" name="menuManageVO" method="post" action="${pageContext.request.contextPath}/sym/mnu/mpm/EgovMenuRegistInsert.do">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymMnuMpm.menuRegist.pageTop.title"/></h2><!-- 메뉴 등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymMnuMpm.menuRegist.menuNo"/> <span class="pilsu">*</span></th><!-- 메뉴No -->
			<td class="left">
			    <form:input path="menuNo" maxlength="10" title="<spring:message code='comSymMnuMpm.menuRegist.menuNo'/>" cssStyle="width:50px" /><!-- 메뉴No -->
      			<form:errors path="menuNo" />
			</td>
			<th><spring:message code="comSymMnuMpm.menuRegist.menuOrder"/> <span class="pilsu">*</span></th><!-- 메뉴순서 -->
			<td class="left">
			    <form:input path="menuOrdr" maxlength="10" title="<spring:message code='comSymMnuMpm.menuRegist.menuOrder'/>" cssStyle="width:50px" /><!-- 메뉴순서 -->
      			<form:errors path="menuOrdr" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymMnuMpm.menuRegist.menuNm"/> <span class="pilsu">*</span></th><!-- 메뉴명 -->
			<td class="left">
			    <form:input path="menuNm" maxlength="30" title="<spring:message code='comSymMnuMpm.menuRegist.menuNm'/>" /><!-- 메뉴명 -->
      			<form:errors path="menuNm" />
			</td>
			<th><spring:message code="comSymMnuMpm.menuRegist.upperMenuId"/> <span class="pilsu">*</span></th><!-- 상위메뉴No -->
			<td class="left">
			    <form:input path="upperMenuId" maxlength="10" title="<spring:message code='comSymMnuMpm.menuRegist.upperMenuId'/>" readonly="true" class="readOnlyClass" cssStyle="width:50px" /><!-- 상위메뉴No -->
				<form:errors path="upperMenuId" />
				<a id="popupUpperMenuId" href="<c:url value='/sym/mnu/mpm/EgovMenuListSelectMvmn.do' />" target="_blank" title="<spring:message code="comSymMnuMpm.menuRegist.newWindow"/>"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />"
				alt='' />(<spring:message code="comSymMnuMpm.menuRegist.selectMenuSearch"/>)</a><!-- 새창으로 --><!-- 메뉴선택 검색 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymMnuMpm.menuRegist.progrmFileNm"/> <span class="pilsu">*</span></th><!--  -->
			<td class="left" colspan="3">
			    <form:input path="progrmFileNm" maxlength="60" onkeypress="press();" title="파일명" readonly="true" class="readOnlyClass" cssStyle="width:350px" /><!-- 파일명 -->
			    <form:errors path="progrmFileNm" />
		        <a id="popupProgrmFileNm" href="<c:url value='/sym/prm/EgovProgramListSearch.do'/>?tmp_SearchElementName=progrmFileNm" target="_blank" title="<spring:message code="comSymMnuMpm.menuRegist.newWindow"/>">
					<img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />" alt='' />(<spring:message code="comSymMnuMpm.menuRegist.programFileNameSearch"/>)</a><!-- 새창으로 --><!-- 프로그램파일명 검색 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymMnuMpm.menuRegist.relateImageNm"/> <span class="pilsu">*</span></th><!-- 관련이미지명 -->
			<td class="left">
			    <form:input path="relateImageNm" maxlength="30" title="<spring:message code='comSymMnuMpm.menuRegist.relateImageNm'/>"/><!-- 관련이미지명 -->
	      		<form:errors path="relateImageNm" />
			</td>
			<th><spring:message code="comSymMnuMpm.menuRegist.relateImagePath"/> <span class="pilsu">*</span></th><!-- 관련이미지경로 -->
			<td class="left">
			    <form:input path="relateImagePath" maxlength="30" title="<spring:message code='comSymMnuMpm.menuRegist.relateImagePath'/>"/><!-- 관련이미지경로 -->
	      		<form:errors path="relateImagePath" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymMnuMpm.menuRegist.menuDc"/> <span class="pilsu">*</span></th><!-- 메뉴설명 -->
			<td class="left" colspan="3">
			    <form:textarea path="menuDc" rows="14" cols="75" cssClass="txaClass" title="<spring:message code='comSymMnuMpm.menuRegist.menuDc'/>" cssStyle="height:200px"/><!-- 메뉴설명 -->
      			<form:errors path="menuDc"/>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.create" />' onclick="insertMenuManage(document.forms[0]); return false;" /><!-- 등록 -->
		<span class="btn_s"><a href="<c:url value='/sym/mnu/mpm/EgovMenuManageSelect.do'/>" onclick="selectList(); return false;"><spring:message code="button.list"/></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>

<input type="hidden" name="tmp_SearchElementName" value="">
<input type="hidden" name="tmp_SearchElementVal" value="">
<input name="cmd" type="hidden" value="<c:out value='insert'/>">

</form:form>

</body>
</html>


