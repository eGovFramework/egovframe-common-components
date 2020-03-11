<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Class Name : EgovLeft.jsp
  Description : 개별 배포 페이지 메뉴
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.05.15    장동한          최초 생성

    author   : 공통서비스 개발팀 장동한
    since    : 2009.05.15

--%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>메뉴</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style>
/* Black 텍스트 Hyperlink Style */
A:link { font-size:9pt; font-family:돋움;color:#000000; text-decoration:none; }
A:visited { font-size:9pt; font-family:돋움;color:#000000; text-decoration:none; }
A:active { font-size:9pt; font-family:돋움;color:red; text-decoration:none; }
A:hover { font-size:9pt; font-family:돋움;color:red;text-decoration:none;}

	/* 등록 테이블 */
.table-register{BORDER-TOP: #D2D4D1 1px solid;BORDER-bottom: #D2D4D1 1px solid;BORDER-left: #D2D4D1 1px solid;BORDER-right: #D2D4D1 1px solid; border-collapse: collapse;}
.table-register th{ padding-left:2;padding-right:5;background-color: #E4EAF8; Text-align: right ;}
.table-register td{ padding-left:2;padding-right:5;background-color: #F7F7F7;}

td {font-family: "돋움"; font-size: 9pt; height:20px; font-weight:normal;}
th {font-family: "돋움"; font-size: 9pt; color:#000000; font-weight:normal;}

body {font-family: "돋움"; font-size: 9pt; color:#000000; font-weight:normal;}

.tdG  {font-family: "돋움"; font-size: 9pt; color:red; height:20px; font-weight:bold ;}

.title_left{ font-family:"돋움"; font-size:9pt; color:#000000 ; font-weight: bold ; vertical-align: middle; text-align:left }
.title_center{ font-family:"돋움"; font-size:9pt; color:#000000 ; font-weight: bold ; vertical-align: middle; text-align:center }
</style>
</head>



<body leftmargin="1px" rightmargin="2px" marginwidth="0px" topmargin="0px" marginheight="0px" >

<table width="100%" height="100%" border="0">
<tr>
	<td align="center"  valign="top">
	<!-- 메뉴    시작  -->
	<table width="100%" cellpadding="8" class="table-search" border="0">
	<tr>
		<td width="100%" class="title_center"><b>메뉴</b><td>
	</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<tr>
	  <td align="center" nowrap><a href="<c:url value='/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do' />" target="content">저작권보호정책</a></td>
	 </tr>
	</table>

	</td>
</tr>
</table>


</body>
</html>
