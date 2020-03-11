<!-- import되는 jsp이므로 헤더 관련 내용은 제거되어야 함
<!DOCTYPE html>
-->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovSatisfactionList.jsp
  * @Description : 만족도
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.06.29   한성곤          최초 생성
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.29
  *  @version 1.0
  *  @see
  *
  */
%>
<c:if test="${type == 'head'}">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="satisfaction" staticJavascript="false" xhtml="true" cdata="false"/>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
function fn_egov_insert_satisfactionList() {
	if (!validateSatisfaction(document.frm)){
		return;
	}

	if (confirm('<spring:message code="common.regist.msg" />')) {
		document.frm.action = "<c:url value='/cop/stf${prefix}/insertSatisfaction.do'/>";
		document.frm.submit();
	}
}

function fn_egov_updt_satisfactionList() {
	if (!validateSatisfaction(document.frm)){
		return;
	}

	if (confirm('<spring:message code="common.update.msg" />')) {
		document.frm.modified.value = "true";
		document.frm.action = "<c:url value='/cop/stf${prefix}/updateSatisfaction.do'/>";
		document.frm.submit();
	}
}

function fn_egov_selectSatisfactionForupdt(satisfactionNo, index) {
	<c:if test="${anonymous == 'true'}">
	var passwordObject;

	if (typeof(document.frm.testPassword.length) == 'undefined') {
		password = document.frm.testPassword;
	} else {
		password = document.frm.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	document.frm.confirmPassword.value = password.value;
	</c:if>
	document.frm.stsfdgNo.value = satisfactionNo;
	document.frm.action = "<c:url value='/cop/bbs${prefix}/selectArticleDetail.do'/>";
	document.frm.submit();
}

function fn_egov_deleteSatisfactionList(satisfactionNo, index) {
	<c:if test="${anonymous == 'true'}">
	var passwordObject;

	if (typeof(document.frm.testPassword.length) == 'undefined') {
		password = document.frm.testPassword;
	} else {
		password = document.frm.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	document.frm.confirmPassword.value = password.value;
	</c:if>

	if (confirm('<spring:message code="common.delete.msg" />')) {
		document.frm.modified.value = "true";
		document.frm.stsfdgNo.value = satisfactionNo;
		document.frm.action = "<c:url value='/cop/stf${prefix}/deleteSatisfaction.do'/>";
		document.frm.submit();
	}
}

function fn_egov_select_satisfactionList(pageNo) {
	document.frm.subPageIndex.value = pageNo;
	document.frm.stsfdgNo.value = '';
	document.frm.action = "<c:url value='/cop/bbs${prefix}/selectArticleDetail.do'/>";
	document.frm.submit();
}
</script>

</c:if>

<!-- import되는 jsp이므로 헤더 관련 tag는 제거되어야 함 
<title><spring:message code="comCopCmt.articleSatisfactionVO.button" /></title><!-- 만족도 -->
<c:if test="${type == 'body'}">
<c:set var="satisfactionTitle"><spring:message code="comCopCmt.articleSatisfactionVO.button"/></c:set>
<input name="subPageIndex" type="hidden" value="<c:out value='${searchVO.subPageIndex}'/>">
<input name="stsfdgNo" type="hidden" value="<c:out value='${searchVO.stsfdgNo}'/>">
<input name="modified" type="hidden" value="false">

<input name="confirmPassword" type="hidden">

<c:if test="${anonymous != 'true'}">
<input type="hidden" name="stsfdgPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

	<h3><spring:message code="comCopCmt.articleSatisfactionVO.title" /> - <c:out value="${resultCnt}"/>개 &nbsp;&nbsp;&nbsp;(전체 :
			   <c:choose>
				<c:when test="${summary > 4.0}"><span title="<c:out value='${summary}'/>">★★★★★</span></c:when>
				<c:when test="${summary > 3.0}"><span title="<c:out value='${summary}'/>">★★★★☆</span></c:when>
				<c:when test="${summary > 2.0}"><span title="<c:out value='${summary}'/>">★★★☆☆</span></c:when>
				<c:when test="${summary > 1.0}"><span title="<c:out value='${summary}'/>">★★☆☆☆</span></c:when>
				<c:when test="${summary > 0.0}"><span title="<c:out value='${summary}'/>">★☆☆☆☆</span></c:when>
				<c:otherwise></c:otherwise>
			   </c:choose>)</h3>

	<div class="reply">
		<ul>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<li>
				<div class="top">
		    		<c:choose>
		    			<c:when test="${not empty result.wrterNm}">
		    			<b><c:out value="${result.wrterNm}" /></b>&nbsp;
		    			</c:when>
		    			<c:otherwise>
		    			<b><c:out value="${result.frstRegisterNm}" /></b>&nbsp;
		    			</c:otherwise>
		    		</c:choose>
		     		<c:out value="${result.frstRegisterPnttm}" /><br>
				</div>
				<p class="txt">
		     		<c:choose>
		     		<c:when test="${result.stsfdg == '5'}"><span title="<spring:message code="comCopCmt.articleSatisfactionVO.level5" />">★★★★★</span></c:when>
		     		<c:when test="${result.stsfdg == '4'}"><span title="<spring:message code="comCopCmt.articleSatisfactionVO.level4" />">★★★★☆</span></c:when>
		     		<c:when test="${result.stsfdg == '3'}"><span title="<spring:message code="comCopCmt.articleSatisfactionVO.level3" />">★★★☆☆</span></c:when>
		     		<c:when test="${result.stsfdg == '2'}"><span title="<spring:message code="comCopCmt.articleSatisfactionVO.level2" />">★★☆☆☆</span></c:when>
		     		<c:when test="${result.stsfdg == '1'}"><span title="<spring:message code="comCopCmt.articleSatisfactionVO.level1" />">★☆☆☆☆</span></c:when>
		     		<c:otherwise><span title="해당없음">☆☆☆☆☆</span></c:otherwise>
		     		</c:choose>&nbsp;
		     		<c:out value="${result.stsfdgCn}" />
				</p>
				<div class="bottom">
					<c:if test="${result.wrterId == sessionUniqId}">
					<span class="btn_s"><a href="javascript:fn_egov_selectSatisfactionForupdt('<c:out value="${result.stsfdgNo}" />', '<c:out value="${status.index}" />');"  title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /> </a></span>&nbsp;
					<span class="btn_s"><a href="javascript:fn_egov_deleteSatisfactionList('<c:out value="${result.stsfdgNo}" />', '<c:out value="${status.index}" />');"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
					</c:if>
				</div>
			</li>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<li>
		  		<p class="txt"><spring:message code="comCopCmt.articleSatisfactionVO.nodata" /></p><!-- 만족도가 없습니다. -->
	  		</li>
			 </c:if>
		</ul>
	</div>


	<!-- paging navigation -->
		<div class="paging">
			<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_commentList"/>
			</ul>
		</div>

<div class="wTableFrm" >
<table class="board_list top_line">
	<tr>
		<th><spring:message code="table.reger" /><span class="pilsu">*</span></th><!-- 작성자 -->
	    <td width="80%" class="left">
	      <input name="wrterNm" type="text" size="20" value='<c:out value="${searchVO.wrterNm}" />' maxlength="20" title="<spring:message code="table.reger" /><spring:message code="input.input" />"><!-- 작성자입력 -->
	      <br>
	      <form:errors path="wrterNm" />
	    </td>
	</tr>
	<tr>
		<th><spring:message code="comCopCmt.articleSatisfactionVO.button" /><span class="pilsu">*</span></th><!-- 만족도 -->
	    <td>
	    	<table border="0" width="100%">
	    		<tr>
	       			<td width="20%" align="center">
	       				<input type="radio" name="stsfdg" value="5" <c:if test="${searchVO.stsfdg == '5'}"> checked="checked"</c:if>>
	       				<span title="<spring:message code="comCopCmt.articleSatisfactionVO.level5" />">★★★★★</span>
	       			</td>
	       			<td width="20%" align="center">
	       				<input type="radio" name="stsfdg" value="4" <c:if test="${searchVO.stsfdg == '4'}"> checked="checked"</c:if>>
	       				<span title="<spring:message code="comCopCmt.articleSatisfactionVO.level4" />">★★★★☆</span>
	       			</td>
	       			<td width="20%" align="center">
	       				<input type="radio" name="stsfdg" value="3" <c:if test="${searchVO.stsfdg == '3'}"> checked="checked"</c:if>>
	       				<span title="<spring:message code="comCopCmt.articleSatisfactionVO.level3" />">★★★☆☆</span>
	       			</td>
	       			<td width="20%" align="center">
	       				<input type="radio" name="stsfdg" value="2" <c:if test="${searchVO.stsfdg == '2'}"> checked="checked"</c:if>>
	       				<span title="<spring:message code="comCopCmt.articleSatisfactionVO.level2" />">★★☆☆☆</span>
	       			</td>
	       			<td width="20%" align="center">
	       				<input type="radio" name="stsfdg" value="1" <c:if test="${searchVO.stsfdg == '1'}"> checked="checked"</c:if>>
	       				<span title="<spring:message code="comCopCmt.articleSatisfactionVO.level1" />">★☆☆☆☆</span>
	       			</td>
	      		</tr>
	      	</table>
	      <form:errors path="stsfdg" />
	    </td>
	  </tr>
	<tr>
		<th height="23"><spring:message code="comCopCmt.articleSatisfactionVO.content" /></th><!-- 내용 -->
	    <td>
	      <textarea name="stsfdgCn" class="textarea"  cols="50" rows="2"  style="width:100%;" title="내용입력"><c:out value="${searchVO.stsfdgCn}" /></textarea>
	      <form:errors path="stsfdgCn" />
	    </td>
	  </tr>
	  <c:if test="${anonymous == 'true'}">
		  <tr>
		    <th height="23" class="emphasisRight"><spring:message code="comCopCmt.articleSatisfactionVO.password" /><!-- 패스워드 -->
		    <img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
		    <td colspan="3">
		      <input name="stsfdgPassword" type="password" size="20" value="" maxlength="20" title="<spring:message code="comCopCmt.articleSatisfactionVO.password" />">
		    </td>
		  </tr>
	  	</c:if>
	</table>

	<div class="btn">
		<c:choose>
			<c:when test="${searchVO.stsfdgNo == ''}">
		      <span style="float:left; margin:0 0 0 3px;"><a href="javascript:fn_egov_insert_satisfactionList();" class="btn_s re_btn" title="${satisfactionTitle}<spring:message code="button.create" />">${satisfactionTitle}<spring:message code="button.create" /></a></span>
			</c:when>
			<c:otherwise>
		      <span style="float:left; margin:0 0 0 3px;"><a href="javascript:fn_egov_updt_satisfactionList();" class="btn_s re_btn" title="${satisfactionTitle}<spring:message code="button.update" />">${satisfactionTitle}<spring:message code="button.update" /></a></span>
			</c:otherwise>
		</c:choose>
		      
		<span style="float:left; margin:0 0 0 3px;"><a href="javascript:fn_egov_select_satisfactionList('1');" class="btn_s re_btn" title="${satisfactionTitle}<spring:message code="button.init" />">${satisfactionTitle}<spring:message code="button.init" /></a></span>
	</div>
</div>	
<c:if test="${not empty subMsg}">
<script>
	alert("<c:out value='${subMsg}'/>");
</script>
</c:if>
</c:if>
