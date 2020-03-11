<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<html>
		<body>
		<form name='form1' id='form1' method='post'>
		<input type='hidden' name='dn' id='dn'>			
		<table border='1px' style='border:1px solid #E7E7E7'>
			<tr id='line1'>
				<!-- 부서명 -->
				<td style='background-color: #F6F6F6'>
					<spring:message code="extLdapumtDpt.dept.dept" />
				</td>
				<td>
					<input type='text' name='ou' id='ou' style='border:1px solid #ffffff' readonly>
				</td>
				<!-- 부서코드 -->
				<td style='background-color: #F6F6F6'>
					<spring:message code="extLdapumtDpt.dept.ouCode" /> 
				</td>
				<td>
					<input type='text' name='ouCode' id='ouCode' style='border:1px solid #ffffff'>	
				</td>
			</tr>
			<tr id='line2'>
				<!-- 부서장 -->
				<td>
					<spring:message code="extLdapumtDpt.dept.ucChieftitle" />
				</td>
				<td style='background-color: #F6F6F6'>
					<input type='text' name='ucChieftitle' id='ucChieftitle' style='border:1px solid #ffffff'>
				</td>
				<!-- 조직명(전체) -->
				<td>
					<spring:message code="extLdapumtDpt.dept.ucOrgFullName" />
				</td>
				<td style='background-color: #F6F6F6'>
					<input type='text' name='ucOrgFullName' id='ucOrgFullName' style='border:1px solid #ffffff'>
				</td>
			</tr>
			<tr id='line3'>
				<!-- 상위부서코드 -->
				<td style='background-color: #F6F6F6'>
					<spring:message code="extLdapumtDpt.dept.parentouCode" />
				</td>
				<td>
					<input type='text' name='parentouCode' id='parentouCode' style='border:1px solid #ffffff'>
				</td>
				<!-- 기관코드 -->
				<td style='background-color: #F6F6F6'>
					<spring:message code="extLdapumtDpt.dept.repouCode" /> 
				</td>
				<td>
					<input type='text' name='repouCode' id='repouCode' style='border:1px solid #ffffff'>
				</td>
			</tr>
			<tr id='line4'>
				<!--문서수신여부 -->
				<td>
					<spring:message code="extLdapumtDpt.dept.ouReceiveDocumentYN" />
				</td>
				<td style='background-color: #F6F6F6'>
					<input type='text' name='ouReceiveDocumentYN' id='ouReceiveDocumentYN' style='border:1px solid #ffffff'>
				</td>
				<!-- 관인부서여부 -->
				<td>
					<spring:message code="extLdapumtDpt.dept.ouSendOutDocumentYN" />
				</td>
				<td style='background-color: #F6F6F6'>
					<input type='text' name='ouSendOutDocumentYN' id='ouSendOutDocumentYN' style='border:1px solid #ffffff'>
				</td>
			</tr>
		</table>
		</form>
		<table style='width:100%'>
			<tr>
				<td style='text-align:left;vertical-align:middle' >
					<button onClick='modifyOrgManage("dept");'><spring:message code="button.save" /></button> <!-- 저장 --> 
				</td>
			</tr>
		</table>
		</body>
		</html>
