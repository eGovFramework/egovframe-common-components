<!DOCTYPE html>
<%@ page language="java" contentType ="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovBkmkMenuManageRegist.jsp
  * @Description : 바로가기메뉴관리 등록
  * @Modification Information
  * @
  * @ 수정일                수정자             수정내용
  * @ ----------   --------    ---------------------------
  * @ 2009.09.25   윤성록             최초 생성
  *   2018.09.10   신용호             표준프레임워크 v3.8 개선
  *
  *  @author 공통컴포넌트팀 윤성록
  *  @since 2009.09.25
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comSymMnuBmm.bkmkMenuManageList.pageTop.title" /></title><!-- 바로가기메뉴등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
		<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
		<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bkmk" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_regist_bkmkInf(){

		if (!validateBkmk(document.bkmk)){
			return;
		}

		if(confirm("<spring:message code='comSymMnuBmm.bkmkMenuManageRegist.validate.confirm.regist' />")){ //등록하시겠습니까?
			document.bkmk.action = "<c:url value='/sym/mnu/bmm/registBkmkInf.do'/>";
			document.bkmk.submit();
		}
	}


	function fn_egov_select_bkmkInfs(){
		document.bkmk.action = "<c:url value='/sym/mnu/bmm/selectBkmkMenuManageList.do'/>";
		document.bkmk.submit();
	}


	function fn_egov_inqire_menu(){
		var retVal;
		var width = 780;
		var height = 700;
		var url = "<c:url value='/sym/mnu/bmm/openPopup.do?requestUrl=/sym/mnu/bmm/selectMenuList.do'/>&width="+(width-20)+"&height="+(height-20);
		var openParam = "dialogWidth: "+width+"px; dialogHeight: "+height+"px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_userInqire", openParam);

		if(retVal != null){

			var tmp = retVal.split(",");

			document.bkmk.menuId.value = tmp[0];
			document.bkmk.menuNm.value = tmp[1];

			document.bkmk.action = "<c:url value='/sym/mnu/bmm/addBkmkInf.do'/>";
			document.bkmk.submit();
		}
	}
	
	function modalDialogCallback(retVal) {
		if(retVal != null){

			var tmp = retVal.split(",");

			document.bkmk.menuId.value = tmp[0];
			document.bkmk.menuNm.value = tmp[1];

			document.bkmk.action = "<c:url value='/sym/mnu/bmm/addBkmkInf.do'/>";
			document.bkmk.submit();
		}
	}

</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#popupInqireMenu').click(function (e) {
        	e.preventDefault();
            
            var pagetitle = $(this).attr("title");
            var page = "<c:url value='/sym/mnu/bmm/selectMenuList.do'/>";
        	
            var $dialog = $('<div></div>')
	            .html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
	            .dialog({
	            	autoOpen: false,
	                modal: true,
	                height: 750,
	                width: 770,
	                title: pagetitle
	        	});
        	$dialog.dialog('open');
    	});

	});
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form  name="bkmk" method="post" action = "<c:url value='/sym/mnu/bmm/registBkmkInf.do'/>">
<input type = "hidden" name = "menuId" value = '<c:out value="${bkmkMenuManage.menuId}" />'>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comSymMnuBmm.bkmkMenuManageRegist.pageTop.title" /></h2><!-- 바로가기메뉴등록 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comSymMnuBmm.bkmkMenuManageRegist.menuName" /> <span class="pilsu">*</span></th><!-- 메뉴명 -->
			<td class="left">
			    <input name="menuNm" type="text" size="30"  value="<c:out value="${bkmkMenuManage.menuNm}" escapeXml="false" />" maxlength="60" style="width: 237px" readonly="readonly" class="readOnlyClass" title="메뉴명입력"/>
	      		<!-- <a href="javascript:fn_egov_inqire_menu();" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />"
	     			style="vertical-align: middle" alt="<spring:message code="comSymMnuBmm.bkmkMenuManageRegist.selectMenu" />" title="<spring:message code="comSymMnuBmm.bkmkMenuManageRegist.selectMenu" />"></a> --><!-- 메뉴선택 -->
	     		<a id="popupInqireMenu" title="메뉴선택" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/egovframework/com/cmm/icon/search2.gif' />"
	     			style="vertical-align: middle" alt="<spring:message code="comSymMnuBmm.bkmkMenuManageRegist.selectMenu" />" title="<spring:message code="comSymMnuBmm.bkmkMenuManageRegist.selectMenu" />"></a>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comSymMnuBmm.bkmkMenuManageRegist.menuURL" /> <span class="pilsu">*</span></th><!-- 메뉴 URL -->
			<td class="left">
			    <input name="progrmStrePath" type="text" size="30" value="<c:out value="${bkmkMenuManage.progrmStrePath}"/>"  maxlength="90" style="width: 235px" readonly="readonly" class="readOnlyClass" title="<spring:message code="comSymMnuBmm.bkmkMenuManageRegist.enterMenuURL" />"/><!-- 메뉴URL입력 -->
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_regist_bkmkInf(); return false;" /><!-- 저장 -->
		<span class="btn_s"><a href="<c:url value='/sym/mnu/bmm/selectBkmkMenuManageList.do'/>?pageIndex=<c:out value='${searchVO.pageIndex}'/>" onclick="fn_egov_select_bkmkInfs(); return false;"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>
</div>
	
</form>

</body>
</html>
