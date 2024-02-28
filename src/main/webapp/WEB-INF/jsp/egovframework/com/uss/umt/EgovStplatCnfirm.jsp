<%
 /**
  * @Class Name : EgovStplatCnfirm.jsp
  * @Description : 약관확인 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.31    조재영          최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.31
  *  @version 1.0
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comUssUmt.stplatCnfirmt.title"/></c:set>
<%pageContext.setAttribute("crlf", "\r\n"); %>

<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<base target="_self">
<script type="text/javaScript" language="javascript" defer="defer">
<!--
function fnAgree(){
	var checkField = document.QustnrManageForm.checkField;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                }else{
                    alert("<spring:message code="comUssUmt.stplatCnfirmtValidate.disagreeStplatCnfirmt" />"); //약관에 동의하지 않으면 회원으로 가입할 수 없습니다.
                    checkField[i].focus();
                    return;
                }
            }
        } else {
            if(checkField.checked) {
            }else{
            	alert("<spring:message code="comUssUmt.stplatCnfirmtValidate.disagreeStplatCnfirmt" />"); //약관에 동의하지 않으면 회원으로 가입할 수 없습니다.
                checkField[i].focus();
                return;
            }
        }
    }

	document.QustnrManageForm.action = "<c:url value='/uss/umt/EgovRlnmCnfirm.do'/>";
    document.QustnrManageForm.submit();
}

function fnDisAgree(){
	alert("<spring:message code="comUssUmt.stplatCnfirmtValidate.disagreeStplatCnfirmt" />"); //약관에 동의하지 않으면 회원가입을 하실수 없습니다.
	return;
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="QustnrManageForm" method="post">
<div class="wTableFrm">
		<!-- 타이틀 -->
		<h2>${pageTitle}</h2>
		
        <!-- content start -->
        <input type="hidden" name="sbscrbTy" value="${sbscrbTy}"/>
        <!-- 실명인증의 기본옵션은 주민번호 실명확인임 : 주민번호 실명인증 으로 가기위한 초기화값 -->
        <input type="hidden" name="ihidnum" value=""/>
        <input type="hidden" name="realname" value=""/>
        <!-- 실명인증후 다음단계에 대한 셋팅정보 -->
        <input type="hidden" name ="nextUrlName" value="button.subscribe"/>
        <input type="hidden" name ="nextUrl" value=
        <c:if test="${sbscrbTy == 'USR01'}">0</c:if>
        <c:if test="${sbscrbTy == 'USR02'}">1</c:if>
        <c:if test="${empty sbscrbTy}">-1</c:if>
        />
        <c:forEach var="result" items="${stplatList}" varStatus="status">
        <!-- 약관확인 -->
        <table class="wTable">
	        <tr><th><spring:message code="comUssUmt.stplatCnfirmt.useStplatCn" /></th></tr>
            
            <tr>
                <td><label for="useStplatCn">
                <textarea id="useStplatCn" cols="120" rows="15"><c:out value="${result.useStplatCn}" escapeXml="false" /></textarea>
                </label>
                </td>
            </tr>
            <tr>
                <td><label for="checkField">
                    <input name="checkField" type="checkbox" title=<spring:message code="comUssUmt.stplatCnfirmt.useStplatCn" /> ><spring:message code="comUssUmt.stplatCnfirmtMsg.useStplat" />
                    <input name="checkuseStplatCn" type="hidden" value="<c:out value='${result.useStplatId}'/>">
                    </label>
                </td>
            </tr>
        </table>
        <!-- 정보동의내용 -->
        <table class="wTable">
            <tr><th><spring:message code="comUssUmt.stplatCnfirmt.infoProvdAgreCn" /></th></tr>
            <tr>
                <td><label for="infoProvdAgeCn">
                <textarea id="infoProvdAgeCn" cols="120" rows="15"><c:out value="${result.infoProvdAgeCn}" escapeXml="false" /></textarea>
                </label>
                <!-- <c:out value="${fn:replace(result.infoProvdAgeCn , crlf , '<br/>')}" escapeXml="false" /> -->
                </td>
            </tr>
            <tr>
                <td><label for="checkField">
                    <input name="checkField" title="<spring:message code="comUssUmt.stplatCnfirmt.infoProvdAgreCn" />"  type="checkbox"><spring:message code="comUssUmt.stplatCnfirmtMsg.infoProvdAgre" />
                    <input name="checkinfoProvdAgeCn" type="hidden" value="<c:out value='${result.useStplatId}'/>">
                    </label>
                </td>
            </tr>
        </table>    
        </c:forEach>
        
	<div class="btn">
		<button class="btn_s2" onClick="fnDisAgree(); return false;" title="<spring:message code="button.disagree" /> <spring:message code="input.button" />"><spring:message code="button.disagree" /></button>
		<input type="submit" class="s_submit" onclick="fnAgree(); return false;" value="<spring:message code="button.agree" />" title="<spring:message code="button.agree" /> <spring:message code="button.save" />" />
	</div><div style="clear:both;"></div>
	

</div>
</form>
        <!-- content end -->
</body>
</html>
