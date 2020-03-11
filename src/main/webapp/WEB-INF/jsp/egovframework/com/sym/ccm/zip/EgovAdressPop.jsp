<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
	//request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
	//request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
    String inputYn = request.getParameter("inputYn"); 
    String zipNo = request.getParameter("zipNo");
    String rnMgtSn = request.getParameter("rnMgtSn");
    String siNm  = request.getParameter("siNm");
	String sggNm  = request.getParameter("sggNm");
	String roadFullAddr = request.getParameter("roadFullAddr");
	String buldMnnm  = request.getParameter("buldMnnm");
	String buldSlno  = request.getParameter("buldSlno");
	String bdNm  = request.getParameter("bdNm");
	String detBdNmList  = request.getParameter("detBdNmList");
	 
	/* 필요시 사용
	String engAddr = request.getParameter("engAddr");
	String addrDetail = request.getParameter("addrDetail"); 
	String admCd    = request.getParameter("admCd");
	String roadAddrPart1 = request.getParameter("roadAddrPart1"); 
	String roadAddrPart2 = request.getParameter("roadAddrPart2"); 
	String jibunAddr = request.getParameter("jibunAddr");
	String bdMgtSn  = request.getParameter("bdMgtSn");
	String bdKdcd  = request.getParameter("bdKdcd");
	String emdNm  = request.getParameter("emdNm");
	String liNm  = request.getParameter("liNm");
	String rn  = request.getParameter("rn");
	String udrtYn  = request.getParameter("udrtYn");
	String mtYn  = request.getParameter("mtYn");
	String lnbrMnnm  = request.getParameter("lnbrMnnm");
	String lnbrSlno  = request.getParameter("lnbrSlno");
	String emdNo  = request.getParameter("emdNo"); 
	*/

%>
</head>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc11.co.kr";

function init(){
	var url = location.href;
	//var url = window.opener.document.URL;
	var confmKey = "U01TX0FVVEgyMDE3MDgyNjE1MjQwNTI0MzI2";
	var resultType = "4"; // 도로명주소 검색결과 화면 출력내용, 1 : 도로명, 2 : 도로명+지번, 3 : 도로명+상세건물명, 4 : 도로명+지번+상세건물명
	var inputYn= '<c:out value="${inputYn}"/>';
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="http://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
		//document.form.action="http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do"; //모바일 웹인 경우, 인터넷망
		document.form.submit();
	}else{
		opener.jusoCallBack("<c:out value="${zipNo}"/>","<c:out value="${rnMgtSn}"/>","<c:out value="${siNm}"/>","<c:out value="${sggNm}"/>","<c:out value="${roadFullAddr}"/>","<c:out value="${buldMnnm}"/>","<c:out value="${buldSlno}"/>","<c:out value="${bdNm}"/>","<c:out value="${detBdNmList}"/>");
		window.close();
		}
}
</script>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
		<!-- 
		<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->
	</form>
</body>
</html>