<!DOCTYPE html>
<%--
  Class Name : EgovIndvdlSchdulManageMainList.jsp
  Description : 메인페이지/일정관리조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.05.08    장동한          최초 생성

    author   : 공통서비스 개발팀 장동한
    since    : 2009.05.08

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javaScript" language="javascript">
function fn_egov_IndvdlSchdulManage(schdulId){
	window.showModalDialog("<c:url value='/cop/smt/sim/EgovIndvdlSchdulManageDetail.do' />?schdulId=" + schdulId , self ,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
}
</script>
<title>메인페이지/일정관리조회</title>
<!-- 일정관리정보 시작 height="80" -->
<table border="0" bgcolor="FFFFFF" width="430" >
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<tr>
	<td><a href="#" onClick="fn_egov_IndvdlSchdulManage('${resultInfo.schdulId}')">${resultInfo.toDay}  ${resultInfo.schdulNm}</a></td>
</tr>
</c:forEach>
</table>
<!-- 일정관리정보 종료 -->
