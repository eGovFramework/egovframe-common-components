<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovNcrdUpdt.jsp
  * @Description : 명함수정
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.30   이삼섭          최초 생성
  * @ 2018.09.13   최두영          다국어처리
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.30
  *  @version 1.0
  *  @see
  *
  */
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopNcm.ncrdUpdt.title" /></title><!-- 명함수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="nameCard" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_select_ncrdInfs(){
		document.nameCard.action = "<c:url value='/cop/ncm/selectMyNcrdUseInf.do'/>";
		document.nameCard.submit();
	}

	function fn_egov_updt_ncrdInf(){
		if (!validateNameCard(document.nameCard)){
			return;
		}

		if (confirm('<spring:message code="common.update.msg" />')) {
			document.nameCard.action = "<c:url value='/cop/ncm/updateNcrdInf.do'/>";
			document.nameCard.submit();
		}
	}

	function fn_egov_inqire_user(){
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do?requestUrl=/cop/com/selectUserList.do&width=850&height=360'/>";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_userInqire", openParam);

		if(retVal != null){
			var tmp = retVal.split("|");
			document.nameCard.ncrdTrgterId.value = tmp[0];
			document.nameCard.ncrdNm.value = tmp[1];
		}
	}
	
	//showModalDialog 대체 수정
	function showModalDialogCallback(retVal) {
		if (retVal) {
			var tmp = retVal.split("|");
			document.nameCard.ncrdTrgterId.value = tmp[0];
			document.nameCard.ncrdNm.value = tmp[1];
		}
	}
</script>
</head>
<body>

<form:form modelAttribute="nameCard" name="nameCard" method="post" >
<div style="visibility:hidden;display:none;">
	<input name="iptSubmit" type="submit" value="전송" title="전송">
</div>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input type="hidden" name="ncrdId" value="<c:out value='${ncrdVO.ncrdId}'/>" />
<input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />


<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopNcm.ncrdUpdt.title" /></h2><!-- 명함수정 -->
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="width:34%" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="cop.ncrdNm" /> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<input id="ncrdNm" type="text" name="ncrdNm" value='<c:out value="${ncrdVO.ncrdNm}" />' maxlength="60" style="width:231px" />
				<input name="ncrdTrgterId" type="hidden" value='<c:out value="${ncrdVO.ncrdTrgterId}" />' /><br />
				<form:errors path="ncrdNm" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.cmpnyNm" /> </th>
			<td class="left">
				<input id="cmpnyNm" type="text" name="cmpnyNm" title="회사명수정" value='<c:out value="${ncrdVO.cmpnyNm}" />' maxlength="60" />
			</td>
			<th><spring:message code="cop.deptNm" /> </th>
			<td class="left">
				<input id="deptNm" type="text" name="deptNm" value='<c:out value="${ncrdVO.deptNm}" />' title="이름수정" maxlength="60" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.ofcpsNm" /> </th>
			<td class="left">
				<input id="ofcpsNm" type="text" name="ofcpsNm" value='<c:out value="${ncrdVO.ofcpsNm}" />' title="직위명수정" maxlength="60" />
			</td>
			<th><spring:message code="cop.clsfNm" /> </th>
			<td class="left">
				<input id="clsfNm" type="text" name="clsfNm" value='<c:out value="${ncrdVO.clsfNm}" />' title="직급명수정" maxlength="60" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.emailAdres" /> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<input id="emailAdres" type="text" name="emailAdres" value='<c:out value="${ncrdVO.emailAdres}" />' title="이메일주소" maxlength="60" /><br />
	      		<form:errors path="emailAdres" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.telNo" /> </th>
			<td class="left" colspan="3">
				<input id="nationNo" type="text" name="nationNo" value='<c:out value="${ncrdVO.nationNo}" />' maxlength="5" style="width:50px" /> -
				<input id="areaNo" type="text" name="areaNo" value='<c:out value="${ncrdVO.areaNo}" />' maxlength="5" style="width:50px" /> -
				<input id="middleTelNo" type="text" name="middleTelNo" value='<c:out value="${ncrdVO.middleTelNo}" />' maxlength="5" style="width:50px" /> -
				<input id="endTelNo" type="text" name="endTelNo" value='<c:out value="${ncrdVO.endTelNo}" />' maxlength="5" style="width:50px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.mbtlNum" /> </th>
			<td class="left" colspan="3">
				<select id="idntfcNo" class="select" name="idntfcNo">
					<option selected value=''>--<spring:message code="comCopNcm.ncrdRegist.idNo" />--</option>
					<option value="010" <c:if test="${ncrdVO.idntfcNo == '010'}"> selected="selected" </c:if> >010</option>
					<option value="011" <c:if test="${ncrdVO.idntfcNo == '011'}"> selected="selected" </c:if> >011</option>
					<option value="016" <c:if test="${ncrdVO.idntfcNo == '016'}"> selected="selected" </c:if> >016</option>
					<option value="017" <c:if test="${ncrdVO.idntfcNo == '017'}"> selected="selected" </c:if> >017</option>
					<option value="018" <c:if test="${ncrdVO.idntfcNo == '018'}"> selected="selected" </c:if> >018</option>
					<option value="019" <c:if test="${ncrdVO.idntfcNo == '019'}"> selected="selected" </c:if> >019</option>
				</select>
				-
				<input id="middleMbtlNum" type="text" name="middleMbtlNum" value='<c:out value="${ncrdVO.middleMbtlNum}" />' maxlength="5" style="width:50px" /> -
				<input id="endMbtlNum" type="text" name="endMbtlNum" value='<c:out value="${ncrdVO.endMbtlNum}" />' maxlength="5" style="width:50px" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.adres" /> </th>
			<td class="left" colspan="3">
				<input type="hidden" name="zipCode" value=""  />
				<input type="hidden" name="v_zipCode" value=""  />
				<input id="adres" type="text" name="adres" value='<c:out value="${ncrdVO.adres}" />' readonly="readonly" onclick="javascript:fn_egov_ZipSearch(document.frm, document.frm.v_zipCode, document.frm.zipCode, document.frm.adres);" style="width:50px; margin-bottom:2px" />
				<a href="#LINK" onclick="fn_egov_ZipSearch(document.nameCard, document.nameCard.v_zipCode, document.nameCard.zipCode, document.nameCard.adres)" style="selector-dummy: expression(this.hideFocus=false);"><img alt="검색" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" /></a><br />
				<input id="detailAdres" type="text" name="detailAdres" value='<c:out value="${ncrdVO.detailAdres}" />' maxlength="80" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.extrlUserAt" /> </th>
			<td class="left">
				<spring:message code="cop.extrlUser" /> : <input type="radio" name="extrlUserAt" class="radio2" value="Y" <c:if test="${ncrdVO.extrlUserAt == 'Y'}"> checked="checked"</c:if> />&nbsp;
	     		<spring:message code="cop.intrlUser" /> : <input type="radio" name="extrlUserAt" class="radio2" value="N"  <c:if test="${ncrdVO.extrlUserAt == 'N'}"> checked="checked"</c:if> />
			</td>
			<th><spring:message code="cop.publicAt" /> <span class="pilsu">*</span></th>
			<td class="left">
				<spring:message code="cop.public" /> : <input type="radio" name="othbcAt" class="radio2" value="Y" <c:if test="${ncrdVO.othbcAt == 'Y'}"> checked="checked"</c:if> />&nbsp;
		     	<spring:message code="cop.private" /> : <input type="radio" name="othbcAt" class="radio2" value="N"  <c:if test="${ncrdVO.othbcAt == 'N'}"> checked="checked"</c:if> />
		     	<br/><form:errors path="othbcAt" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="cop.remark" /> </th>
			<td class="left" colspan="3">
				<input id="remark" type="text" name="remark" value='<c:out value="${ncrdVO.remark}" />' size="90" maxlength="90" />
			</td>
		</tr>
	</table>
	
	<div class="btn">
		<input class="s_submit" type="button" onclick="fn_egov_updt_ncrdInf()" value="<spring:message code='button.update' />"/>
		<input class="s_submit" type="button" onclick="fn_egov_select_ncrdInfs('1')" value="<spring:message code='button.list' />"/>
	</div>
</div>

</form:form>
</body>
</html>
