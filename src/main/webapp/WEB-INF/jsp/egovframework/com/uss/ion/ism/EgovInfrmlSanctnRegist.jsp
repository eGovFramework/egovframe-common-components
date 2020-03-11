<%
/**
 * @Class Name : EgovInfrmlSanctnRegist.java
 * @Description : EgovInfrmlSanctnRegist.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.08.31    장철호                최초 생성
 * @ 2018.09.12     최두영               다국어처리
 *
 *  @author 장철호
 *  @since 2010.08.31
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/uss/ion/ism/EgovInfrmlSanction.js"/>"></script>
<table class="wTable">
	<colgroup>
		<col style="width:16%" />
		<col style="" />
		<col style="width:16%" />
		<col style="" />
	</colgroup>
	<tr>
		<th><spring:message code="comUssIonIsm.infrmlSanctnRegist.sanctnDtNm" /> <span class="pilsu">*</span></th><!-- 승인권자명 -->
		<td class="left">
		    <input name="sanctnDtNm" id="sanctnDtNm" value="${infSanctnDtNm}" type="text" title="<spring:message code="comUssIonIsm.infrmlSanctnRegist.sanctnDtNm" />" readonly="readonly" style="width:128px" />
			<form:hidden  path="sanctnerId" id="sanctnerId"/>
			<form:errors  path="sanctnerId"/>
			
			<a href="<c:url value='/uss/ion/ism/selectSanctnerListPopup.do'/>" target="_blank"  title="<spring:message code="comUssIonIsm.infrmlSanctnRegist.contractor" />"  onclick="fn_egov_sanctner('<spring:message code="comUssIonIsm.infrmlSanctnRegist.sanctnDtNm" />', 'sanctnerId', '', 'sanctnDtNm', 'orgnztNm', '<c:url value='/uss/ion/ism/selectSanctnerListPopup.do'/>');return false;"><img alt="" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" /></a><!-- 결재자지정 -->
		</td>
		<th><spring:message code="comUssIonIsm.infrmlSanctnRegist.orgnztNm" /></th>
		<td class="left">
		    <input name="orgnztNm" id="orgnztNm" value="${infOrgnztNm}" type="text" title="<spring:message code="comUssIonIsm.infrmlSanctnRegist.orgnztNm" />" readonly="readonly" /><!-- 소속 -->
		</td>
	</tr>
</table>