<%--
  Class Name : template.jsp
  Description : 설문기본 템플릿
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.08.31    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQri.title.template"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="template" staticJavascript="false" xhtml="true" cdata="false"/>

<form:form name="template" id="template" action="/uss/olp/qri/template.do" method="post">
<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
<tr>
    <td colspan="2">
    	<table width="100%" border="0" cellpadding="2" cellspacing="0">
    	<tr>
    		<td style="background-color:#E3E3E3;">
    		<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" width="15" height="15" alt="<spring:message code='comUssOlpQri.alt.compulsoryInput'/>">${status1.count}. <c:out value="${fn:replace(QestmInfo.qestnCn , crlf , '<br/>')}" escapeXml="false" /><!-- alt="필수입력표시" -->
    		<c:if test="${QestmInfo.mxmmChoiseCo >  1}"><font color="red"><b>(<spring:message code='comUssOlpQri.regist.mxmmChoiseCo'/> ${QestmInfo.mxmmChoiseCo})</b></font></c:if><!-- (최대선택건수 ${QestmInfo.mxmmChoiseCo}) -->
    		</td>
    	</tr>
    	<tr>
    	<td style="padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;background-color:#E3E3E3;">
		    <%-- 객관식 --%>
		    <c:if test="${QestmInfo.qestnTyCode ==  '1'}">
	    	<table border="0" cellpadding="0" cellspacing="0">
	    	<tr>
		    	<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status01">
		    	<c:if test="${QestmInfo.qestnrTmplatId eq QestmItem.qestnrTmplatId && QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">
		    		<td style="background-color:#E3E3E3;">

		    		<%-- 다중체크구현 로직 --%>
		    		<c:if test="${QestmInfo.mxmmChoiseCo ==  '1'}">
		    		<input type="radio" name="${QestmItem.qestnrQesitmId}" value="${QestmItem.qustnrIemId}" style="border:0px;"> <c:out value="${fn:replace(QestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" />
		    		</c:if>

		    		<c:if test="${QestmInfo.mxmmChoiseCo >  1}">
		    		<input type="checkbox" name="${QestmItem.qestnrQesitmId}" value="${QestmItem.qustnrIemId}" id="${QestmItem.qestnrQesitmId}" onClick="fn_egov_checkbox_amout('${QestmItem.qestnrQesitmId}', ${QestmInfo.mxmmChoiseCo}, this)"  title="<spring:message code='comUssOlpQri.title.checkBox'/>"><c:out value="${fn:replace(QestmItem.iemCn , crlf , '<br/>')}" escapeXml="false"/><!-- title="체크박스" -->
		    		</c:if>
		    		<%-- 기타답변여부 --%>
		    		<c:if test="${QestmItem.etcAnswerAt eq  'Y'}">
		    		<input name="ETC_${QestmItem.qustnrIemId}" id="ETC_${QestmItem.qustnrIemId}" type="text" size="73" value="" maxlength="1000" style="width:150px;" title="<spring:message code='comUssOlpQri.title.etcAnswer'/>"><!-- title="기타답변여부" -->
		    		</c:if>
		    		<c:if test="${QestmItem.etcAnswerAt eq  'N' || QestmItem.etcAnswerAt eq ''}">
		    		<input name="ETC_${QestmItem.qustnrIemId}" id="ETC_${QestmItem.qustnrIemId}" type="hidden" size="73" value="" maxlength="1000">
		    		</c:if>
		    		</td>
		    	</c:if>
		    	</c:forEach>
	    	</tr>
	    	</table>
	    	</c:if>

    		<%-- 주관식 --%>
    		<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
    		<textarea name="${QestmInfo.qestnrQesitmId}" id="${QestmInfo.qestnrQesitmId}" class="textarea"  cols="75" rows="4"  style="width:99%;" value="" title="<spring:message code='comUssOlpQri.title.inputSubjQuest'/>"></textarea><!-- title="주관식내용입력" -->
    		</c:if>
	    </td>
    	</tr>
    	</table>
    </td>
</tr>
<tr>
	<td colspan="2" height="1">
	<%-- 최대선택 건수 --%>
	<input type="hidden" name="MXMM_${QestmInfo.qestnrQesitmId}" value="${QestmInfo.mxmmChoiseCo}">
	<%-- 객관식/주관식  타입 --%>
	<input type="hidden" id="qestnTyCode" name="TY_${QestmInfo.qestnrQesitmId}" value="${QestmInfo.qestnTyCode}">
	</td>
</tr>
</c:forEach>
</form:form>
