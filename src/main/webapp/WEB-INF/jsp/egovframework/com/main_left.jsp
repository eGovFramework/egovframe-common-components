<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
 /**
  * @Class Name : left.jsp
  * @Description :  좌측메뉴화면
  * @Modification Information
  * @
  * @ 수정일                수정자           수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용              최초 생성
  * @ 2021.03.03   신용호            URL 표기 버그 수정
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */

  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/mnu/mpm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/mnu/mpm/button/";
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>메뉴정보등록</title>
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
var getContextPath = "${pageContext.request.contextPath}";
var path = "http://" + "${pageContext.request.serverName}" + ":" + "${pageContext.request.serverPort}";
</script>
<script language="javascript1.2" src="<c:url value='/js/egovframework/com/sym/mnu/mpm/EgovMainMenu.js' />" /></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight= "0">
<form name="menuListForm" action ="<c:url value='/sym/mnu/mpm/EgovMenuListSelect.do' />" method="post">
<DIV id="main" style="display:">

<table width="250" cellpadding="8" class="table-search" border="0">
  <tr>
    <td width="250" class="title_left" >
        <div style="width:0px; height:0px;">
		<c:forEach var="result" items="${list_menulist}" varStatus="status" >
		<%-- 2026.07.30 보안 조치 - 속성값 이스케이프(HTML 속성 breakout 저장형 XSS 차단) --%>
		<input type="hidden" name="tmp_menuNm" value="<c:out value='${result.menuNo}'/>|<c:out value='${result.upperMenuId}'/>|<c:out value='${result.menuNm}'/>|<c:out value='${result.relateImagePath}'/>|<c:out value='${result.relateImageNm}'/>|<c:out value='${pageContext.request.contextPath}${result.chkURL}'/>|"/>
		</c:forEach>
		</div>
		<div class="tree" style="overflow: auto; position: relative; z-index: 5; padding: 0pt 0pt 0pt 0px; width: 300px;">
		<script language="javascript">
			var Tree = new Array;
			for (var j = 0; j < document.menuListForm.tmp_menuNm.length; j++) {
				Tree[j] = document.menuListForm.tmp_menuNm[j].value;
			}
			createTree(Tree, true, '<c:out value="${resultVO.tempInt}"/>');
		</script>
		</div>
   </td>
 </tr>
</table>
</DIV>
</form>
</body>
</html>

