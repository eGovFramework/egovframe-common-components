<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>

<%
 /**
  * @Class Name : EgovRlnmCnfirm.jsp
  * @Description : 주민번호 실명확인 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.31   조재영          최초 생성
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.31
  *  @version 1.0
  *  @see
  *
  */
%>

<head>
<title>실명인증</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<!-- base target="_self" / -->
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fnIhidnumCnfirm(){
	//현재 화면의 입력정보로 재호출하여 확인..
	document.cnfirmForm.action = "<c:url value='/sec/rnc/EgovRlnmCnfirm.do'/>";
    document.cnfirmForm.submit();
}

function fnGpinMove(){
    //GPIN 실명인증 화면으로 이동한다.
    document.cnfirmForm.action = "<c:url value='/sec/rnc/EgovRlnmPinCnfirm.do'/>";
    document.cnfirmForm.result.value="";
    document.cnfirmForm.submit();
}
function fnNextMove(){

	// 상담화면에서 실명인증을 위한 파라미터
	var ls_cnsltName 	= document.cnfirmForm.nextUrl.value;

	//다음단계로 이동한다. parameter로 받아서 설정
	//alert("!!!"+"<c:url value='${nextUrl}'/>");
	if(document.cnfirmForm.result.value=="success.user.rlnmCnfirm") {
	//if(true){

		// 상담 및 Q&A를 위한 코드
		if (ls_cnsltName == "C")	{

			/* var opener = window.dialogArguments; */
			var opener;
 
			if (window.dialogArguments) {
			    opener = window.dialogArguments; // Support IE
			} else {
			    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
			}

			// 부모화면으로 실명을 넘긴다.
			ls_realname = document.cnfirmForm.realname.value;

			opener.document.getElementById("realname").value = ls_realname;

			// 부모화면으로 결과값을 True 넘긴다.
			window.returnValue = true;
			window.close();

		} else	{
    		document.cnfirmForm.action = "<c:url value='${nextUrl}'/>";
    		document.cnfirmForm.submit();
		}

	}else{
    	alert("<spring:message code="${result}" />");
	}

}

//-->
</script>
</head>
<body>
<!-- content start -->
        <form name="cnfirmForm" method="post" action="">
        <input type="hidden" name="sbscrbTy" value="${sbscrbTy}">
        <!-- GPIN 실명인증 으로 가기위한 초기화값 -->
        <input type="hidden" name="realName" value="${realName}"><!-- 주민등록확인의 realname 변수와 구별  -->
        <!-- 실명인증을 재귀적으로 호출하는 경우 다음경로와 다음경로 버튼명을 셋팅함 -->
        <input type="hidden" name="nextUrl" value="${nextUrl}">
        <input type="hidden" name="nextUrlName" value="${nextUrlName}">
        <table width="717" cellpadding="8" class="table-search" border="0">
            <tr>
            <td width="100%"class="title_left">
            <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">&nbsp;주민등록 실명확인</td>
            </tr>
        </table>
        <table width="717" border="0"  cellspacing="0" cellpadding="0">
            <tr>
	            <td>
	                &nbsp;이름:<input type="text" name="realname" value="${realname}" title="이름입력">
	                &nbsp;주민등록번호 <input type="text" name="ihidnum" value="${ihidnum}" title="주민등록번호입력">(-없이 13자리)
                    <!-- 사용자유형 --><input type="hidden" name="sbscrbTy_view" value="${sbscrbTy}">
                    <!-- 결과코드 -->
                    <input type="hidden" name="result" value="${result}">
                    <input type="hidden" name="result_tmp" value="${result_tmp}">
                    <br><c:if test="${not empty result}"><spring:message code="${result}" /></c:if>
                    <br>
                    <br>
	            </td>
            </tr>
            <tr>
	            <td>
	            * 입력하신 주민등록번호는  실명인증을 하는데만 이용되며 저장되지 않습니다<br>
	            * 개정 "주민등록법"에 의해 타인의 주민등록번호를 부정사용하는 자는 3년 이하의 징역 또는 1천만원. 이하의 벌금이 부과될 수 있습니다.
	            관련법률: 주민등록법 제37조(벌칙) 제9호(시행일 2006.09.24)
	            만약, 타인의 주민번호를 도용하여 온라인 회원 가입을 하신 이용자분들은 지금 즉시 명의 도용을 중단하시길 바랍니다.

	            </td>
            </tr>
            <tr>
                <td>
                <table border="0"  cellspacing="0" cellpadding="0"><!-- 버튼테이블 -->
                <tr>
	                <!-- 실명확인 -->
	                <!-- 
	                <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
	                    <a href="javascript:fnIhidnumCnfirm();"><spring:message code="button.realname" /></a>
	                </td>
	                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                -->
	                <!-- GPIN실명확인으로 이동 -->
	                <!-- 
	                <td>&nbsp;</td>
	                <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
	                    <a href="javascript:fnGpinMove();"><spring:message code="button.moveToGpin" /></a>
	                </td>	                 
	                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                -->
	                <!-- 다음단계 이동 -->
	                <!-- 
                    <td>&nbsp;</td>
                    <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
                    <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
                        <a href="javascript:fnNextMove();"><spring:message code="${nextUrlName}" /></a>
                    </td>
                    <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
                     -->
                     <td><span class="button"><input type="submit" value="<spring:message code="button.realname" />" title="<spring:message code="button.realname" />" onclick="javascript:fnIhidnumCnfirm();return false;"></span></td>
                     <td>&nbsp;</td>
                     <td><span class="button"><input type="button" value="<spring:message code="button.moveToGpin" />" title="<spring:message code="button.moveToGpin" />" onclick="javascript:fnGpinMove();return false;"></span></td>
                     <td>&nbsp;</td>
                     <td><span class="button"><input type="button" value="<spring:message code="${nextUrlName}" />" title="<spring:message code="${nextUrlName}" />" onclick="javascript:fnNextMove();return false;"></span></td>
                     
                </tr>
                </table>
                </td>
            </tr>
        </table>
        </form>
        <!-- content end -->
</body>
</html>
