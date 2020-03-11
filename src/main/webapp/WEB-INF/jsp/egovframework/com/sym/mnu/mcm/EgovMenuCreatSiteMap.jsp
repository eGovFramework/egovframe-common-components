<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="egovframework.com.cmm.service.Globals"  %>
<%
 /**
  * @Class Name : EgovMenuCreatSiteMap.jsp
  * @Description : 메뉴사이트맵 생성 화면
  * @Modification Information
  * @
  * @ 수정일               수정자             수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용               최초 생성
  * @ 2011.07.29   서준식            사이트맵 저장경로 수정
  *   2018.09.10   신용호            표준프레임워크 v3.8 개선
  *   2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */

  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/mnu/mcm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/mnu/mcm/button/";
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymMnuMpm.MenuCreatSiteMap.title" /></title><!-- 메뉴사이트맵생성 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
var getContextPath = "${pageContext.request.contextPath}";
</script>
<script language="javascript1.2" type="text/javaScript" src="<c:url value='/js/egovframework/com/sym/mnu/mcm/EgovMenuCreatSiteMap.js' />"></script>
<script language="javascript1.2" type="text/javaScript">
<!--
/*절대 path 사이트맵이 저장될 장소의  절대 패스*/
//var vRootPath    = "C:/egovframework/workspace2/sym.mnu.mcm/src/main/webapp";   // Window webapp 위치
//var vRootPath    = "/product/jeus/webhome/was_com/egovframework-com-1_0/egovframework-com-1_0_war___"; // Unix webapp 위치
/* 절대 path내  사이트맵 jsp를 저장할 장소 지정 */
//var vSiteMapPath = "/html/egovframework/com/sym/mnu/mcm/";


/* ********************************************************
 * 조회 함수
 ******************************************************** */
function selectMenuCreatSiteMap() {
	document.menuCreatManageSiteMapForm.scrtyEstbstrgetId.value = opener.document.menuCreatManageForm.scrtyEstbstrgetId.value;
	document.menuCreatManageSiteMapForm.action = "<c:url value='/sym/mnu/mcm/EgovMenuCreatSiteMapSelect.do'/>";
    document.menuCreatManageSiteMapForm.submit();
}

/* ********************************************************
 * jsp 생성 함수
 ******************************************************** */
function CreatSiteMap() {
	fHtmlCreat_Head();
	console.log("vHtmlCode = "+vHtmlCode);
	usrID = document.menuCreatManageSiteMapForm.creatPersonId.value;
	authorCode = document.menuCreatManageSiteMapForm.authorCode.value;
	document.menuCreatManageSiteMapForm.valueHtml.value    = vHtmlCode;
	document.menuCreatManageSiteMapForm.bndeFileNm.value   = authorCode+"_SiteMap.jsp";
	//document.menuCreatManageSiteMapForm.tmp_rootPath.value = vRootPath;
	//document.menuCreatManageSiteMapForm.bndeFilePath.value = vSiteMapPath;
	document.menuCreatManageSiteMapForm.mapCreatId.value   = authorCode;
	document.menuCreatManageSiteMapForm.action = "<c:url value='/sym/mnu/mcm/EgovMenuCreatSiteMapInsert.do'/>";
    document.menuCreatManageSiteMapForm.submit();
}

/* ********************************************************
* 메뉴 호출 함수
******************************************************** */
function fCallUrl(url) {
	window.open(url,'dokdo','width=800,height=600,menubar=no,toolbar=no,location=no,resizable=no,status=no,scrollbars=no,top=300,left=700');
}

<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>

</head>
<body>
<noscript class="noScriptTitle"><spring:message code="comSymMnuMpm.MenuCreatSiteMap.title" /></noscript>

<form name="menuCreatManageSiteMapForm" action ="<c:url value='/sym/mnu/mcm/EgovMenuCreatSiteMapSelect.do' />" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comSymMnuMpm.MenuCreatSiteMap.send" />" title="<spring:message code="comSymMnuMpm.MenuCreatSiteMap.send" />"></div><!-- 전송 -->
<input name="valueHtml"      type="hidden" />
<input name="creatPersonId"  type="hidden" value ="<c:out value='${resultVO.creatPersonId}'/>" />
<input name="bndeFileNm"     type="hidden" />
<input name="bndeFilePath"   type="hidden" />
<input name="mapCreatId"     type="hidden" />
<input name="tmp_rootPath"   type="hidden" />

<div class="board" style="width:530px">
	<h1><spring:message code="comSymMnuMpm.MenuCreatSiteMap.pageTop.title" /></h1><!-- 메뉴사이트맵생성 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for=""><spring:message code="comSymMnuMpm.MenuCreatSiteMap.authCode" /> : </label><!-- 권한코드 -->
				<input class="s_input2 vat" name="authorCode" type="text" value="<c:out value='${resultVO.authorCode}'/>" size="20" maxlength="30" title="<spring:message code="comSymMnuMpm.MenuCreatSiteMap.authName" />" readonly="readonly" /><!-- 권한명 -->
				<input class="s_input2 vat" name="chkCreat" type="text" value="<c:out value='${resultBoolean.chkCreat}'/>" size="10" maxlength="10" title="<spring:message code="comSymMnuMpm.MenuCreatSiteMap.authCode" />" readonly="readonly" /><!-- 권한코드 -->
				
				<span class="btn_b"><a href="#LINK" onclick="CreatSiteMap(); return false;" title="<spring:message code="comSymMnuMpm.MenuCreatSiteMap.createSitemap" />"><spring:message code="comSymMnuMpm.MenuCreatSiteMap.createSitemap" /></a></span><!-- 사이트맵생성 -->
			</li>
		</ul>
	</div>
	
	<c:forEach var="result1" items="${list_menulist}" varStatus="status" >
	<input type="hidden" name="tmp_menuNmVal" value="${result1.menuNo}|${result1.upperMenuId}|${result1.menuNm}|${result1.menuOrdr}|${result1.chkURL}|">
	</c:forEach>
	
	<div class="tree" style="width:480px;" id="treeSiteMap">
		<script language="javascript" type="text/javaScript">
			var Tree = new Array;
			var baseObj = document.getElementById("treeSiteMap");
			if ( typeof document.menuCreatManageSiteMapForm.tmp_menuNmVal == "undefined" 
					|| typeof document.menuCreatManageSiteMapForm.tmp_menuNmVal.length == "undefined" ) {
            	alert("<spring:message code="comSymMnuMpm.MenuCreatSiteMap.validate.menuNmVal.none2" />"); //사이트맵 생성 데이타가 존재하지 않습니다. \n 메뉴를 생성하신 후 작업하세요.
            	window.close();
			} else {
				for (var j = 0; j < document.menuCreatManageSiteMapForm.tmp_menuNmVal.length; j++) {
					Tree[j] = document.menuCreatManageSiteMapForm.tmp_menuNmVal[j].value;
				}
				createTree(baseObj,Tree);
            }
		</script>
	</div>
</div>

</form>

</body>
</html>


