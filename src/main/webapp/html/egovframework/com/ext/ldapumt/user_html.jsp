<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<body>
	<form name='form1' id='form1'>
	<input type='hidden' name='dn' id='dn'>			
	<table border='1px' style='border:1px solid #E7E7E7'>
		<tr id='line1'>
			<td style='background-color: #F6F6F6'>
				ID
			</td>
			<td>
				<input type='text' name='cn' id='cn' style='border:1px solid #ffffff' readonly>
			</td>
			<!-- 이름 -->
			<td style='background-color: #F6F6F6'>
				<spring:message code="extLdapumtDpt.user.name" />
			</td>
			<td>
				<input type='text' name='displayName' id='displayName' style='border:1px solid #ffffff'>	
			</td>
		</tr>
		<tr id='line2'>
			<!-- 소속부서 -->
			<td>
				<spring:message code="extLdapumtDpt.user.dept" />
			</td>
			<td style='background-color: #F6F6F6'>
				<input type='text' name='ou' id='ou' style='border:1px solid #ffffff'>
			</td>
			<!-- 부서코드 -->
			<td>
				<spring:message code="extLdapumtDpt.user.deptCode" />
			</td>
			<td style='background-color: #F6F6F6'>
				<input type='text' name='ouCode' id='ouCode' style='border:1px solid #ffffff'>
			</td>
		</tr>
		<tr id='line3'>
			<!-- 소속기관 -->
			<td style='background-color: #F6F6F6'>
				<spring:message code="extLdapumtDpt.user.deptOrg" />
			</td>
			<td>
				<input type='text' name='companyName' id='companyName' style='border:1px solid #ffffff'>
			</td>
			<!-- 기관코드 -->
			<td style='background-color: #F6F6F6'>
				<spring:message code="extLdapumtDpt.user.deptOrgCode" /> 
			</td>
			<td>
				<input type='text' name='topouCode' id='topouCode' style='border:1px solid #ffffff'>
			</td>
		</tr>
		<tr id='line4'>
			<!-- 사용자전체이름 -->
			<td>
				<spring:message code="extLdapumtDpt.user.userFullName" />
			</td>
			<td colspan='3'  style='background-color: #F6F6F6'>
				<input type='text' name='userFullName' width='100%' id='userFullName' style='border:1px solid #ffffff;width:100%'>
			</td>
		</tr>		
		<tr>	
			<!-- 조직전체이름 -->	
			<td style='background-color: #F6F6F6'>
				<spring:message code="extLdapumtDpt.user.orgFullName" />			
			</td>
			<td colspan='3'>
				<input type='text' name='ucOrgFullName' width='100%' id='ucOrgFullName' style='border:1px solid #ffffff;width:100%'>
			</td>
		</tr>
	</table>
	</form>
	<table style='width:100%'>
		<tr>
			<td style='text-align:left;vertical-align:middle' >
				<button onClick='modifyOrgManage("user");'><spring:message code="button.save" /></button> <!-- 저장 -->
			</td>
		</tr>
	</table>
	</body>
</html>