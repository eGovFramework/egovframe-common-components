<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
 /**
  * @Class Name : EgovRlnmPinCnfirm.jsp
  * @Description : 공공IPIN실명확인 JSP
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
<title>실명인증(GPIN)</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<!-- base target="_self" / -->
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fnGpinCnfirm(){
	//현재 화면의 입력정보로 재호출하여 확인..
	//document.cnfirmForm.action = "<c:url value='/sec/rnc/EgovRlnmPinCnfirm.do'/>";
	//핀인증화면을 팝업으로 띄운다.
	//document.cnfirmForm.action = "<c:url value='/sec/rnc/EgovGPinCall.do'/>";
    //jsp 팝업으로 띄운다.
    wWidth = 360;
    wHight = 120;
    wX = (window.screen.width - wWidth) / 2;
    wY = (window.screen.height - wHight) / 2;

    //open 형태로 호출
    //var w = window.open("<c:url value='/sec/rnc/EgovPageLink.do' />?link=cmm/sec/rnc/EgovCallGpin", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);

    //submit 형태로 호출
    var ww = window.open("#","_openWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
    document.cnfirmForm.action="<c:url value='/sec/rnc/EgovPageLink.do' />?link=/egovframework/com/sec/rnc/EgovCallGpin";
    document.cnfirmForm.target="_openWin";
    document.cnfirmForm.submit();
}

function fnIhidnumMove(){
    //GPIN 실명인증 화면으로 이동한다.
    document.cnfirmForm.action = "<c:url value='/sec/rnc/EgovRlnmCnfirm.do'/>";
    document.cnfirmForm.target="";
    document.cnfirmForm.submit();
}

function fnNextMove(){

    // 상담화면에서 실명인증을 위한 파라미터
    var ls_cnsltName    = document.cnfirmForm.nextUrl.value;

    // 상담화면에서 화면 호출시
    if (ls_cnsltName == "C")    {

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

    } else  {
        //다음단계로 이동한다. parameter로 받아서 설정
        //alert("!!!"+"<c:url value='${nextUrl}'/>");
        if(document.cnfirmForm.result.value=="success.user.rlnmPinCnfirm"){
            document.cnfirmForm.action = "<c:url value='${nextUrl}'/>";
            document.cnfirmForm.submit();
        }else{
            alert("<spring:message code="${result}" />");
        }
    }

}
//-->
</script>
</head>
<body>
        <!-- content start -->
        <form name="cnfirmForm" method="post" action="<c:url value='/sec/rnc/EgovRlnmCnfirm.do'/>">
        <input type="hidden" name="sbscrbTy" value="${sbscrbTy}">
        <!-- 주민번호 실명인증 으로 가기위한 초기화값 -->
        <input type="hidden" name="ihidnum" value="${ihidnum}">
        <input type="hidden" name="realname" value="${realname}"><!-- ipin실명확인된 이름realName변수와 구별 -->
        <!-- 실명인증을 재귀적으로 호출하는 경우 다음경로와 다음경로 버튼명을 셋팅함 -->
        <input type="hidden" name="realName" value="${realName}">
        <input type="hidden" name="nextUrl" value="${nextUrl}">
        <input type="hidden" name="nextUrlName" value="${nextUrlName}">
        <table width="717" cellpadding="8" class="table-search" border="0">
            <tr>
            <td width="100%"class="title_left">
            <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목아이콘이미지">&nbsp;공공IPIN 실명확인</td>
            </tr>
        </table>
        <table width="717" border="0"  cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <input type="hidden" name="sbscrbTy_view" value="${sbscrbTy}">
                    <input type="hidden" name="result" value="${result}">
                    <br><c:if test="${not empty result}"><spring:message code="${result}" /></c:if>
                    <br><c:if test="${not empty realName}">실명확인이름: ${realName} </c:if>
                    <br>
                </td>
            </tr>
            <tr>
                <td>* 이용자의 개인정보를 보호하기 위해 웹사이트에 주민등록번호를  제공하지 않고 본인확인 하는 인터넷상의 개인식별번호 서비스입니다.
                <br> (gpin정보는 실명확인시 입력되는 정보로 설정되며 저장되지 않습니다.)
                </td>
            </tr>
            <tr>
                <td>
                <table border="0"  cellspacing="0" cellpadding="0"><!-- 버튼테이블 -->
                <tr>
                    <!-- 실명확인 
	                <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
	                    <a href="javascript:fnGpinCnfirm();"><spring:message code="button.realname" /></a>
	                </td>
	                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                -->
	                <td><span class="button"><input type="submit" value="<spring:message code="button.realname" />" title="<spring:message code="button.realname" />" onclick="javascript:fnGpinCnfirm();return false;"></span></td>
	                <td>&nbsp;</td>
	                <!-- 주민등록번호 실명확인으로 이동 
	                <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
	                    <a href="javascript:fnIhidnumMove();"><spring:message code="button.moveToIhidnum" /></a>
	                </td>
	                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                -->
	                <td><span class="button"><input type="submit" value="<spring:message code="button.moveToIhidnum" />" title="<spring:message code="button.moveToIhidnum" />" onclick="javascript:fnIhidnumMove();return false;"></span></td>
	                <td>&nbsp;</td>
	                <!-- 아이디찾기 
	                <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
	                    <a href="http://www.g-pin.go.kr/jsp/members/find_id.jsp" target="_blank"  title="새 창으로 이동">아이디찾기</a>
	                </td>
	                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                -->
	                <td><span class="button"><a href="http://www.g-pin.go.kr/jsp/members/find_id.jsp" target="_blank"  title="새 창으로 이동">아이디찾기</a></span></td>
	                <td>&nbsp;</td>
	                <!-- 비밀번호찾기 
	                <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
	                    <a href="http://www.g-pin.go.kr/jsp/members/find_pw.jsp" target="_blank"  title="새 창으로 이동">비밀번호 찾기</a>
	                </td>
	                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
	                -->
	                <td><span class="button"><a href="http://www.g-pin.go.kr/jsp/members/find_pw.jsp" target="_blank"  title="새 창으로 이동">비밀번호 찾기</a></span></td>
	                <td>&nbsp;</td>
	                <!-- 공공IPIN센터 
                    <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
                    <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
                        <a href="http://www.g-pin.go.kr/" target="_blank"  title="새 창으로 이동">공공IPIN센터</a>
                    </td>
                    <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
                    -->
                    <td><span class="button"><a href="http://www.g-pin.go.kr/" target="_blank"  title="새 창으로 이동">공공IPIN센터</a></span></td>
	                <td>&nbsp;</td>
                    <!-- 다음단계 이동
                    <td>&nbsp;</td>
                    <td align="right"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
                    <td background="<c:url value='/images/egovframework/com/cmm/btn/bu2_bg.gif' />" class="text_left" nowrap>
                        <a href="javascript:fnNextMove();"><spring:message code="${nextUrlName}" /></a>
                    </td>
                    <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/btn/bu2_right.gif' />" width="8" height="20" alt="버튼이미지"></td>
                     -->
                    <td><span class="button"><input type="submit" value="<spring:message code="${nextUrlName}" />" title="<spring:message code="${nextUrlName}" />" onclick="javascript:fnNextMov();return false;"></span></td>
	            </tr>
	            </table>
	            </td>
            </tr>
        </table>
        </form>
        <!-- content end -->
</body>
</html>
