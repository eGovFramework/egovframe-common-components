<%
/**
 * @Class Name : EgovInfrmlSanctnDetail.java
 * @Description : EgovInfrmlSanctnDetail.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.08.31    장철호                최초 생성
 * @ 2019.09.10    최두영                퍼블리싱
 * @ 2019.09.12    최두영                다국어처리
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
		    <c:out value='${infrmlSanctnVO.sanctnerNm}'/>
		</td>
		<th><spring:message code="comUssIonIsm.infrmlSanctnRegist.orgnztNm" /></th><!-- 소속 -->
		<td class="left">
		    <c:out value='${infrmlSanctnVO.sanctnerOrgnztNm}'/>
		</td>
	</tr>
	<c:if test="${infrmlSanctnVO.confmAt ne 'A'}">
	<tr>
		<th><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAt" /></th><!-- 승인여부 -->
		<td class="left">
			<c:if test="${infrmlSanctnVO.confmAt eq 'A'}"><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtA" /></c:if><!-- 신청중 -->
			<c:if test="${infrmlSanctnVO.confmAt eq 'C'}"><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtC" /></c:if><!-- 승인 -->
			<c:if test="${infrmlSanctnVO.confmAt eq 'R'}"><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtR" /></c:if><!-- 반려 -->
		</td>
		<th><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmDate" /></th><!-- 승인일자 -->
		<td class="left">
		    <c:out value='${infrmlSanctnVO.sanctnDt}'/>
		</td>
	</tr>
	</c:if>
	<c:if test="${infrmlSanctnVO.confmAt eq 'C'}">
	<tr>
		<th><spring:message code="comUssIonIsm.infrmlSanctnDetail.opinion" /></th><!-- 의견 -->
		<td class="left" colspan="3">
			[<spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtC" />]<c:out value='${infrmlSanctnVO.returnResn}'/><!-- 승인 -->
		</td>
	</tr>
	</c:if>
	<c:if test="${infrmlSanctnVO.confmAt eq 'R'}">
	<tr>
		<th><spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtReason" /></th><!-- 반려사유 -->
		<td class="left" colspan="3">
			[<spring:message code="comUssIonIsm.infrmlSanctnDetail.confmAtR" />]<c:out value='${infrmlSanctnVO.returnResn}'/><!-- 반려 -->
		</td>
	</tr>
	</c:if>
</table>