<%
/**
 * @Class Name : EgovVcatnManageDetail.java
 * @Description : EgovVcatnManageDetail.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영     퍼블리싱 점검
 * @ 2018.09.18    최두영       다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonVct.vcatnManageDetail.title"/></title><!-- 휴가상세조회 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<validator:javascript formName="vcatnManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncEgovVcatnManageList(){
	location.href = "<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>";
}
<c:if test="${vcatnManageVO.confmAt eq 'A' }">
	/* ********************************************************
	* 수정화면으로  바로가기
	******************************************************** */
	function fncEgovVcatnManage() {
			var varFrom = document.getElementById("vcatnManage");
			varFrom.action = "<c:url value='/uss/ion/vct/EgovVcatnManageDetail.do'/>";
			varFrom.submit();   
	}
	/* ********************************************************
	* 삭제처리화면
	******************************************************** */
	function fncDeleteVcatnManage() {
		    var varFrom = document.getElementById("vcatnManage");
		    varFrom.action = "<c:url value='/uss/ion/vct/deleteVcatnManage.do'/>";
		    if(confirm("<spring:message code="common.delete.msg"/>")){ /* 삭제 하시겠습니까? */
	            varFrom.submit();
		    }
	}
</c:if>
-->
</script>
</head>

<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2 class=""><spring:message code="comUssIonVct.vcatnManageDetail.title"/></h2><!-- 휴가상세조회 -->

	<form name="vcatnManage" id="vcatnManage" method="post" >
	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
		<input type="hidden" name="applcntId" value="<c:out value='${vcatnManageVO.applcntId}'/>"/>
		<input type="hidden" name="vcatnSe"   value="<c:out value='${vcatnManageVO.vcatnSe}'/>"/>
		<input type="hidden" name="bgnde"     value="<c:out value='${vcatnManageVO.bgnde}'/>"/>
		<input type="hidden" name="endde"     value="<c:out value='${vcatnManageVO.endde}'/>"/>
		<input type="hidden" name="occrrncYear" value="<c:out value='${vcatnManageVO.occrrncYear}'/>"/>
		<input type="hidden" name="cmd"       value="updt"/>
	</form>

	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.vcatnManageDetail.vcatnApply"/></h3><!-- 휴가신청 -->
	<!-- 등록폼 -->
	<table class="wTable mb20">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonVct.common.applcntNm"/></th><!-- 신청자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.applcntNm}'/>
			</td>
			<th><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.orgnztNm}'/>
			</td>
		</tr>
		<tr>
		<th><spring:message code="comUssIonVct.common.vcatnTotalInfo"/></th><!-- 신청자 연차정보 -->
			<td class="left" colspan="4">
				<span>[<spring:message code="comUssIonVct.common.occrncYrycCo"/>: ${vcatnManageVO.occrncYrycCo}  ]</span><!-- 발생연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.useYrycCo"/>: ${vcatnManageVO.useYrycCo}  ]</span><!-- 사용연차 -->
				<span style="margin-left:20px">[<spring:message code="comUssIonVct.common.remndrYrycCo"/>: ${vcatnManageVO.remndrYrycCo}  ]</span><!-- 잔여연차 -->
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnSe"/> <span class="pilsu">*</span></th><!-- 휴가구분 -->
			<td class="left" colspan="3">
				<c:out value='${vcatnManageVO.vcatnSeNm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.startDate"/> <span class="pilsu">*</span></th><!-- 시작일자 -->
			<td class="left">
			    <c:out value='${vcatnManageVO.bgnde}'/>
			</td>
			<c:if test="${vcatnManageVO.vcatnSe ne '02' }">
				<th><spring:message code="comUssIonVct.common.endDate"/> <span class="pilsu">*</span></th><!-- 종료일자 -->
				<td class="left">
				    <c:out value='${vcatnManageVO.endde}'/>
				</td>
			</c:if>
			<c:if test="${vcatnManageVO.vcatnSe eq '02' }">
				<th><spring:message code="comUssIonVct.common.noonSe"/> <span class="pilsu">*</span></th><!-- 정오구분 -->
				<td class="left">
				    <c:if test="${vcatnManageVO.noonSe eq '1' }"><spring:message code="comUssIonVct.common.noonSe1"/> </c:if><!-- 오전 -->
					<c:if test="${vcatnManageVO.noonSe eq '2' }"><spring:message code="comUssIonVct.common.noonSe2"/> </c:if><!-- 오후 -->
				</td>
			</c:if>
		</tr>
		<tr>
			<th><spring:message code="comUssIonVct.common.vcatnResn"/> <span class="pilsu">*</span></th><!-- 휴가사유 -->
			<td class="left" colspan="3">
			    <textarea id="remark" name="vcatnResn" class="txta01" rows="4" cols="70" title="<spring:message code="comUssIonVct.common.vcatnResn"/>" readonly="readonly"><c:out value='${vcatnManageVO.vcatnResn}' escapeXml="false" /></textarea><!-- 휴가사유 -->
			</td>
		</tr>
	</table>	
	
	<h3 class="tit02" style="margin:0 0 5px 0"><spring:message code="comUssIonVct.common.infrmlSanctnRegist"/></h3><!-- 결재권자 -->
	
	<!-- 결재권자 정보 Include -->
	<c:import url="/uss/ion/ism/selectInfrmlSanctn.do" charEncoding="utf-8"> 
		<c:param name="infrmlSanctnId" value="${vcatnManageVO.infrmlSanctnId}"/>
	</c:import>
	<!-- //결재권자 정보 Include -->

	<!-- 하단 버튼 -->
	<div class="btn">
		<c:if test="${vcatnManageVO.confmAt eq 'A' }">
		<form id="updtForm" name="updtForm" action="<c:url value='/uss/ion/vct/EgovVcatnManageDetail.do'/>" method="post" style="display:inline-block; vertical-align:top">  
		<input type="hidden" name="cmd"       value="updt"/>
		<input type="hidden" name="applcntId" value="<c:out value='${vcatnManageVO.applcntId}'/>"/>
		<input type="hidden" name="vcatnSe"   value="<c:out value='${vcatnManageVO.vcatnSe}'/>"/>
		<input type="hidden" name="bgnde"     value="<c:out value='${vcatnManageVO.bgnde}'/>"/>
		<input type="hidden" name="endde"     value="<c:out value='${vcatnManageVO.endde}'/>"/>
		<input type="hidden" name="occrrncYear" value="<c:out value='${vcatnManageVO.occrrncYear}'/>"/>
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fncEgovVcatnManage(); return false;" />
		</form>
		
		<form id="deleteForm" name="deleteForm" action="<c:url value='/uss/ion/vct/deleteVcatnManage.do'/>" method="post" style="display:inline-block; vertical-align:top">  
		<input type="hidden" name="applcntId" value="<c:out value='${vcatnManageVO.applcntId}'/>"/>
		<input type="hidden" name="vcatnSe"   value="<c:out value='${vcatnManageVO.vcatnSe}'/>"/>
		<input type="hidden" name="bgnde"     value="<c:out value='${vcatnManageVO.bgnde}'/>"/>
		<input type="hidden" name="endde"     value="<c:out value='${vcatnManageVO.endde}'/>"/>
		<input type="hidden" name="occrrncYear" value="<c:out value='${vcatnManageVO.occrrncYear}'/>"/>
		<input class="s_submit" type="submit" value='<spring:message code="button.delete" />' onclick="fncDeleteVcatnManage(); return false;" />
		</form>
		</c:if>
		
		<span class="btn_s"><a href="<c:url value='/uss/ion/vct/EgovVcatnManageList.do'/>?searchCondition=1" onclick="fncEgovVcatnManageList(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>