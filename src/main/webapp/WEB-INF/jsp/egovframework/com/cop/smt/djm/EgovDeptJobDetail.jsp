<%
/**
 * @Class Name : EgovDeptJobDetail.jsp
 * @Description : 부서업무 상세보기
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2010.07.12   장철호          최초 생성
 * @ 2018.09.10   최두영           V3.8 퍼블리싱 점검
 * @ 2018.09.15   최두영           다국어처리
 *
 *  @author 공통컴포넌트개발팀 장철호
 *  @since 2010.07.12
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtDjm.deptJobDetail.title"/></title><!-- 부서업무 상세보기 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- showmodal 대체처리  -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<validator:javascript formName="deptJobVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_DeptJob(){

	}

	function fn_egov_modify_deptjob() {
		document.deptJobVO.action = "<c:url value='/cop/smt/djm/modifyDeptJob.do'/>";
		document.deptJobVO.submit();
	}

	function fn_egov_delete_deptjob(){
		if(confirm("<spring:message code="common.delete.msg"/>")){/* 삭제하시겠습니까? */
			document.deptJobVO.action = "<c:url value='/cop/smt/djm/deleteDeptJob.do'/>";
			document.deptJobVO.submit();
		}
	}

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_list_deptjob(){
		document.deptJobVO.action = "<c:url value='/cop/smt/djm/selectDeptJobList.do'/>";
		document.deptJobVO.submit();
	}

	/* ********************************************************
	* 부서  팝업창열기
	******************************************************** */
	function fn_egov_dept_Dept(strType){
		var arrParam = new Array(1);
		arrParam[0] = window;
		arrParam[1] = strType;

		window.showModalDialog("<c:url value='/cop/smt/djm/selectDeptListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}

	/* ********************************************************
	* 부서업무함  팝업창열기
	******************************************************** */
	function fn_egov_dept_DeptJobBx(strType){
		var arrParam = new Array(1);
		arrParam[0] = window;
		arrParam[1] = strType;
		arrParam[2] = document.deptJobVO.deptId.value;

		window.showModalDialog("<c:url value='/cop/smt/djm/selectDeptJobBxListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}

	/* ********************************************************
	* 아이디  팝업창열기
	******************************************************** */
	function fn_egov_charger_DeptJob(strType){
		var arrParam = new Array(1);
		arrParam[0] = window;
		arrParam[1] = strType;

	 	window.showModalDialog("<c:url value='/uss/ion/ism/selectSanctnerListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}
</script>
</head>
<body onLoad="fn_egov_init_DeptJob()">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="deptJobVO" name="deptJobVO" method="post" action="${pageContext.request.contextPath}/cop/smt/djm/modifyDeptJob.do" >


<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtDjm.deptJobDetail.title"/></h2><!-- 부서업무 상세보기 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptNm"/> <span class="pilsu">*</span></th><!-- 부서 -->
			<td class="left">
			    <c:out value="${deptJob.deptNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptJobBxNm" /> <span class="pilsu">*</span></th><!-- 부서업무함명 -->
			<td class="left">
			    <c:out value="${deptJob.deptJobBxNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptJobNm" /> <span class="pilsu">*</span></th><!-- 부서업무명 -->
			<td class="left">
			    <c:out value="${deptJob.deptJobNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptJobCn" /> <span class="pilsu">*</span></th><!-- 부서업무내용 -->
			<td class="left">
			    <c:out value="${fn:replace(deptJob.deptJobCn , crlf , '<br>')}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.chargerNm" /> <span class="pilsu">*</span></th><!-- 업무담당자 -->
			<td class="left">
			    <c:out value="${deptJob.chargerNm}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.priort" /> <span class="pilsu">*</span></th><!-- 우선순위 -->
			<td class="left">
				<c:forEach items="${priort}" var="priortInfo" varStatus="status">
					<c:if test="${priortInfo.code eq deptJob.priort}">
						<c:out value="${priortInfo.codeNm}" escapeXml="false" />
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobRegist.fileAttach"/> <span class="pilsu">*</span></th><!-- 파일첨부 -->
			<td class="left">
				<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do" >
					<c:param name="param_atchFileId" value="${deptJob.atchFileId}" />
				</c:import>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.update" />" onclick="fn_egov_modify_deptjob(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/djm/deleteDeptJob.do'/>?deptJobId=<c:out value='${deptJobVO.deptJobId}'/>" onclick="fn_egov_delete_deptjob(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="btn_s"><a href="<c:url value='/cop/smt/djm/selectDeptJobList.do'/>?searchWrd=<c:out value='${deptJobVO.searchWrd}'/>&amp;searchCnd=<c:out value='${deptJobVO.searchCnd}'/>&amp;pageIndex=<c:out value='${deptJobVO.pageIndex}'/>&amp;searchDeptId=<c:out value='${deptJobVO.searchDeptId}'/>&amp;searchDeptJobBxId=<c:out value='${deptJobVO.searchDeptJobBxId}'/>" onclick="fn_egov_list_deptjob(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	
	<form:hidden path="deptJobId" />
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${deptJobVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${deptJobVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${deptJobVO.pageIndex}'/>" />
    <input type="hidden" name="searchDeptId" value="<c:out value='${deptJobVO.searchDeptId}'/>" />
    <input type="hidden" name="searchDeptJobBxId" value="<c:out value='${deptJobVO.searchDeptJobBxId}'/>" />
    <!-- 검색조건 유지 -->
    </div>
</form:form>
</body>
</html>
