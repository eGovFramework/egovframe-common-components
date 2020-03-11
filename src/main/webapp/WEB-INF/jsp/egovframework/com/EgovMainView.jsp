<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="comSymMnuMpm.mainView.mainViewTitle"/></title><!-- 행정안전부 공통서비스 테스트 사이트 -->
<script language="javascript" src="<c:url value='/js/egovframework/com/main.js' />"></script>
<script language="javascript">
function chk_all(val) {

	var arr_chk = document.getElementsByName("chk");

		if (val == "Y") {

			for(i=0;i< arr_chk.length; i++) {
				arr_chk[i].checked =true;
			}
		}
		else if(val == "N") {
			for(i=0;i< arr_chk.length; i++) {
				arr_chk[i].checked =false;
			}
		}
}

</script>
</head>

<body topmargin="0" leftmargin="0">

<!-- header -->
<c:import url="./head.jsp" />

<!-- contents -->
<div>
	
	<!-- 상단 -->
	<div class="mp_top">
		<div class="l"><!-- left layout -->
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectBBSListPortlet"/></h3><!-- 생성된 게시판 목록 -->
			<div style="height:150px">
				<c:import url="/cop/bbs/selectBBSListPortlet.do" />
			</div>
			
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectCommuMasterListPortlet"/></h3><!-- 생성된 커뮤니티 목록 -->
			<div style="height:150px">
				<c:import url="/cop/cmy/selectCommuMasterListPortlet.do" />
			</div>
			
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.selectBlogListPortlet"/></h3><!-- 생성된 블로그 목록 -->
			<div style="height:181px">
				<c:import url="/cop/bbs/selectBlogListPortlet.do" />
			</div>
		</div>
		
		<div class="r"><!-- right layout -->
			<!-- 부서일정관리  -->
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.deptSchdulManageMainList"/></h3><!-- 부서일정관리 -->
			<div style="height:150px">
			<c:import charEncoding="utf-8" url="/cop/smt/sdm/EgovDeptSchdulManageMainList.do" ></c:import>
			</div>
			
			<!-- 나의일정관리 -->
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.indvdlSchdulManageMainList"/></h3><!-- 나의일정관리 -->
			<div style="height:150px">
			<c:import charEncoding="utf-8" url="/cop/smt/sim/EgovIndvdlSchdulManageMainList.do" ></c:import>
			</div>
			
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.exchangeRateInformation"/></h3><!-- 환율정보 -->
			<iframe src="http://fx.kebhana.com/fxportal/jsp/RS/DEPLOY_EXRATE/fxrate_all.html" width="100%" height="181" border="0" frameborder="no" scrolling="no" marginwidth="0" hspace="0" vspace="0"></iframe>
		</div>
	</div>
	
	<!-- 배너 -->
	<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.getBannerImage"/></h3><!-- 배너 -->
	<div class="mp_bn">
		<c:import url="/uss/ion/bnr/getBannerImage.do" charEncoding="utf-8">
			<c:param name="atchFileId" value="${banner.bannerImageFile}" />
		</c:import>
	</div>

<!-- bottom -->
<c:import url="./main_bottom.jsp" />
</div><!-- contents -->

</body>
</html>
