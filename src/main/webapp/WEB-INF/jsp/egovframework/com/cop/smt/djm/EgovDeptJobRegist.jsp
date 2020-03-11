<% 
/**
 * @Class Name : EgovDeptJobRegist.jsp
 * @Description : 부서업무 등록
 * @Modification Information
 * @
 * @ 수정일               수정자            수정내용
 * @ ----------   --------   ---------------------------
 * @ 2010.07.12   장철호            최초 생성
 * @ 2018.09.10   최두영            V3.8 퍼블리싱 점검
 * @ 2018.09.14   최두영            다국어처리&퍼블리싱처리
 * @ 2019.12.09   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="comCopSmtDjm.deptJobRegist.title" /></title><!-- 부서업무 등록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="deptJobVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_init_DeptJob(){
		var maxFileNum = document.getElementById('posblAtchFileNumber').value;
		   
	   if(maxFileNum==null || maxFileNum==""){
	     	maxFileNum = 3;
	    }
	        
	   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	   
	   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );		
	   document.getElementsByName("priort")[1].checked = true;
	}

	function fn_egov_insert_deptjob() {
		if (!validateDeptJobVO(document.deptJobVO)){
			return;
		}
		
		var resultExtension = EgovMultiFilesChecker.checkExtensions("egovComFileUploader", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
		if (!resultExtension) return true;
		var resultSize = EgovMultiFilesChecker.checkFileSize("egovComFileUploader", <c:out value='${fileUploadMaxSize}'/>); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
		if (!resultSize) return true;
		
		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.deptJobVO.action = "<c:url value='/cop/smt/djm/insertDeptJob.do'/>";
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
	function fn_egov_charger_DeptJob(strTitle, frmUniqId, frmEmplNo, frmEmplyrNm, frmOrgnztNm){
		var arrParam = new Array(6);
		arrParam[0] = window;
		arrParam[1] = strTitle;
		arrParam[2] = frmUniqId;
		arrParam[3] = frmEmplNo;
		arrParam[4] = frmEmplyrNm;
		arrParam[5] = frmOrgnztNm;

	 	window.showModalDialog("<c:url value='/cop/smt/djm/selectChargerListPopup.do' />", arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
	}

</script>
</head>
<body onLoad="fn_egov_init_DeptJob()" style="margin-top:0">

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="deptJobVO" name="deptJobVO" method="post" action="${pageContext.request.contextPath}/cop/smt/djm/insertDeptJob.do' />" enctype="multipart/form-data">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comCopSmtDjm.deptJobRegist.title" /></h2><!-- 부서업무 등록 -->
	
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptNm" /> <span class="pilsu">*</span></th><!-- 부서 -->
			<td class="left">
				<input id="deptNm" type="text" name="deptNm" value="${deptJobVO.deptNm}" maxlength="20" title="부서" readonly="readonly" style="width:188px" />
				<div><form:errors path="deptNm" cssClass="error"/></div>
				<form:hidden path="deptId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptJobBxNm" /> <span class="pilsu">*</span></th><!-- 부서업무함명 -->
			<td class="left">
			    <form:input path="deptJobBxNm" size="30" readonly="true" maxlength="255" title="부서업무함명" cssStyle="width:188px"/>
				<a href="<c:url value='/cop/smt/djm/selectDeptJobBxListPopup.do' />" target="_blank"  title="새 창으로 이동" onclick="fn_egov_dept_DeptJobBx('typeDeptJobBx'); return false;">
					<img alt="부서업무함" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="부서업무함">
				</a>
				<div><form:errors path="deptJobBxNm" cssClass="error"/></div>
		  		<form:hidden path="deptJobBxId" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptJobNm" /> <span class="pilsu">*</span></th><!-- 제목 -->
			<td class="left">
			    <form:input path="deptJobNm" size="75" maxlength="255" title="제목"/>
				<div><form:errors path="deptJobNm" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.deptJobCn" /> <span class="pilsu">*</span></th><!-- 내용 -->
			<td class="left">
			    <form:textarea path="deptJobCn" rows="5" cols="90" title="내용"/>
				<div><form:errors path="deptJobCn" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.chargerNm" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="chargerNm" size="73" cssClass="txaIpt" readonly="true" maxlength="10" title="담당자" cssStyle="width:188px"/>
				<a href="<c:url value='/cop/smt/djm/selectChargerListPopup.do' />" target="_blank" title="새 창으로 이동" onclick="fn_egov_charger_DeptJob('담당자', 'chargerId', '', 'chargerNm', '');return false;">
					<img alt="담당자" src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif' />" title="담당자" />
				</a>
				<div><form:errors path="chargerNm" cssClass="error"/></div>
		       <form:hidden path="chargerId" />       
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobVO.validate.priort" /> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:radiobutton path="priort" value="1" /><spring:message code="comCopSmtDjm.deptJobRegist.high" /><!-- 높음 -->
				<form:radiobutton path="priort" value="2" cssStyle="margin-left:10px"/><spring:message code="comCopSmtDjm.deptJobRegist.medium" /><!-- 보통 -->
				<form:radiobutton path="priort" value="3" cssStyle="margin-left:10px"/><spring:message code="comCopSmtDjm.deptJobRegist.low" /><!-- 낮음 -->
				<div><form:errors path="priort" cssClass="error"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comCopSmtDjm.deptJobRegist.fileAttach" /> <span class="pilsu">*</span></th><!-- 파일첨부 -->
			<td class="left">
				<table width="580px" cellspacing="0" cellpadding="0" border="0" align="center" class="UseTable">
					<tr>
						<td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부" multiple/></td>
					</tr>
					<tr>
						<td>
					    	<div id="egovComFileList"></div>
					    </td>
					</tr>
		   	    </table>
			</td>
		</tr>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_insert_deptjob(); return false;" />
		<span class="btn_s"><a href="<c:url value='/cop/smt/djm/selectDeptJobList.do'/>?searchWrd=<c:out value='${deptJobVO.searchWrd}'/>&amp;searchCnd=<c:out value='${deptJobVO.searchCnd}'/>&amp;pageIndex=<c:out value='${deptJobVO.pageIndex}'/>&amp;searchDeptId=<c:out value='${deptJobVO.searchDeptId}'/>&amp;searchDeptJobBxId=<c:out value='${deptJobVO.searchDeptJobBxId}'/>" onclick="fn_egov_list_deptjob(); return false;"><spring:message code="button.list" /></a></span>
	</div>	
</div>

	<!-- 첨부파일 갯수 -->
	<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	<!-- //첨부파일 갯수 -->
	<!-- 검색조건 유지 -->
    <input type="hidden" name="searchWrd" value="<c:out value='${deptJobVO.searchWrd}'/>" />
    <input type="hidden" name="searchCnd" value="<c:out value='${deptJobVO.searchCnd}'/>" />
    <input type="hidden" name="pageIndex" value="<c:out value='${deptJobVO.pageIndex}'/>" />
    <input type="hidden" name="searchDeptId" value="<c:out value='${deptJobVO.searchDeptId}'/>" />
    <input type="hidden" name="searchDeptJobBxId" value="<c:out value='${deptJobVO.searchDeptJobBxId}'/>" />
    <!-- 검색조건 유지 -->
</form:form>

</body>
</html>
