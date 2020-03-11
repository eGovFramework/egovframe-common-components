<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.03    박지욱          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" type="text/css">
<script language='javascript' src='/js/egovframework/com/uat/uia/EgovGpkiVariables.js' />'></script>
<script language='javascript' src='/js/egovframework/com/uat/uia/EgovGpkiInstall.js' />'></script>
<script language='javascript' src='/js/egovframework/com/uat/uia/EgovGpkiFunction.js' />'></script>
<OBJECT ID="GPKISecureWeb" CLASSID = "CLSID:C8223F3A-1420-4245-88F2-D874FC081572">
</OBJECT>
<title>GPKI 인증서 등록</title>
<script>

function sendDn() {
	if (typeof(opener.fn_egov_dn_info_setting) == 'undefined') {
	    alert('메인 화면이 변경되거나 없습니다');
	    this.close();

	} else {
		var dn = document.GpkiLoginForm.dn.value;
	    opener.fn_egov_dn_info_setting(dn);
	    this.close();
	}
}

</script>
</head>
<body>
  <!--인증서등록 테이블 시작-->
  <form name="GpkiLoginForm" action ="<c:url value='/uat/uia/actionGpkiRegist.do'/>" method="post">
<table width="303" border="0" cellspacing="8" cellpadding="0">
     <tr height="10">
     </tr>
     <tr>
	<td width="40%"class="title_left"><img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle" alt="제목아이콘이미지">&nbsp;인증서 DN 추출</td>
     </tr>
     <tr>
	<td width="303" height="210" valign="top" style="background-image: url(/images/egovframework/com/uat/uia/login_bg01.gif);">
		<table width="303" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr height="20">
		    <td width="30">
     		  </tr>
		  <tr>
		    <table height="150" border="0" cellspacing="0" cellpadding="0" align="center">
			  <tr>
			  <td>
                <!-- 인증서 ActiveX 삽입 -->
                <object id="EMX" classid="CLSID:4725E26C-87F5-4D06-B743-A645DC6623D9" width = "285" height = "145">
                  <param name="GNTYPE", value=GNCertType>
                  <param name="WORKDIR", value=WorkDir>
                  <param name="CERTTYPE", value=ReadCertType>
                  <param name="VALIDCERTOIDINFO", value=ValidCertInfo>
                </object>
                <!-- 인증서 ActiveX 삽입 끝-->
                 </td>
                 </tr>
               </table>
  		  </tr>
		</table width="303" border="0" align="center" cellpadding="0" cellspacing="0">
		<table>
		  <tr>
  		    <table border="0" cellspacing="0" cellpadding="0" align="left">
			  <tr>
			    <td class="required_text">인증서비밀번호&nbsp;&nbsp;
                <input type="password" size="13" maxlength="16" name="pwd" onkeydown="embeddedEnterEvent(this.form)" style="ime-mode: disabled;" tabindex="7" title="인증서비밀번호입력">&nbsp;&nbsp;
				<td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
				<td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap><center><a href="javascript:LoginEmbedded(document.GpkiLoginForm);">DN값 추출</a></center></td>
				<td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
                <input type="hidden" name="challenge" value='<c:out value="${challenge}"/>'>
              </tr>
              <tr>
	           <td class="required_text">DN값&nbsp;&nbsp;
	           <input type="text" size="13" maxlength="16" name="dn" value='<c:out value="${dn}"/>' title="DN값입력">&nbsp;&nbsp;
		       <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
		       <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap><center><a href="javascript:sendDn();">DN값 전달</a></center></td>
		       <td><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	          </tr>
            </table>
		  </tr>

		</table>
	</td>
     </tr>
   </table>
  </form>
  <!--인증서등록 테이블 끝-->
</body>
</body>
</html>
