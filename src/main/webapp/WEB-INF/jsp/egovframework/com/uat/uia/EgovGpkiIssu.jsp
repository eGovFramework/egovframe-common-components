<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovGpkiIssu.jsp
  * @Description : 인증서 안내화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.25    박지욱          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.25
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" type="text/css">
<title>MOPAS GPKI 인증서 안내</title>
<style>
.guide_wrap {box-sizing:border-box; width:730px; padding:15px; }
.guide_wrap h1 {font-size:16px; }
.guide_wrap h2 {margin-top:25px; }
.guide_wrap p {margin-top:10px; line-height:18px; }
.guide_wrap table {margin-top:10px; }
.guide_wrap table td {padding:10px; border:1px solid #dff0c7; }
.guide_wrap table td.lb {color:#74aa7d; font-weight:bold; text-align:center; background:#f3f9ea; } 

</style>
</head>
<body>
	<div class="guide_wrap">

		<h1>인증서 안내 페이지</h1>
	
		<h2>행정전자서명 인증서란?</h2>
		
		<p>“행정전자서명 인증서”라 함은 행정전자서명이 진정한 것임을 확인ㆍ증명할 수 있도록 하기 위하여 행정기관, 보조기관, 보좌기관, 전자문서유통 및 행정정보 공공이용, 공공기반, 은행 또는 사용자에게   발급하는 전자적 정보를 말한다.<br /><br />전자거래시 인증서를 사용하면 신원확인, 문서의 위·변조, 거래사실의 부인 방지 등의 효과를 얻을 수   있습니다. 인증서에는 인증서 버전, 인증서 일련번호, 인증서의 유효기간, 발급기관명 및 전자서명 알고리즘 정보, 가입자 이름 및 신원확인정보 등이 포함되어 있다.</p>
	
		<h2>인증서 발급 대상</h2>
	
		<p>행정전자서명 인증서 발급대상은 다음의 2가지로 분류된다.<br />
		<strong>첫째</strong>는 인증관리체계상 인증업무수행 인증기관, 등록기관, 원격등록기관에 해당하는 행정기관이 대상이며<br />
		<strong>둘째</strong>는 정보화시스템에 적용하기 위한 행정기관, 보조기관, 보좌기관, 공무원 그리고 해당 공무원과 행정기관, 보조기관, 보좌기관에서 관리하는 정보통신 장비가 일반 인증서 발급 대상이다.<br />
		<strong>셋째</strong>는 전자문서 유통 및 행정정보 공동이용 공공기관, 은행법으로 지정된 기관과 사용자가 발급대상이다.
		</p>
		
		<h2>인증/등록기관 인증서</h2>
		<table>
			<colgroup>
				<col style="width:120px">
				<col style="">
			</colgroup>
			<tr>
				<td class="lb">최상위 인증기관</td>
				<td>인증관리센터는 행정전자서명 인증업무 수행 등에 활용하도록 최상위인증기관에 인증서를 발급합니다.</td>
			</tr>
			<tr>
				<td class="lb">인증기관용</td>
				<td>인증관리센터는 안전행전부 장관이 지정ㆍ고시하는 정부인증기관을 대상으로 인증업무 수행등에 활용하도록 인증서를 발급합니다.</td>
			</tr>
			<tr>
				<td class="lb">등록기관용</td>
				<td>인증관리센터는 등록기관 등록업무 수행 등에 활용하도록 등록기관 지정 행정기관에 인증서를 발급합니다.</td>
			</tr>
		</table>
		
		<h2>기관/개인 인증서</h2>
		<table>
			<colgroup>
				<col style="width:120px">
				<col style="width:120px">
				<col style="">
			</colgroup>
			<tr>
				<td class="lb" rowspan="3">기관</td>
				<td><strong>기관용</strong></td>
				<td>인증관리센터는 사무관리규정에 따라 관인을 가질수 있는 행정기관, 보조기관, 보좌기관등의 과단위까지의 1개의 기관용   인증서를 발급합니다.</td>
			</tr>
			<tr>
				<td><strong>특수목적용</strong></td>
				<td>인증관리센터는 사무관리규정에 따라 행정기관의 업무를 관인과 같이 처리할 수 없도록 규정한   특수관인을 관리하는 경우에 특수목적용 인증서를 발급합니다.</td>
			</tr>
			<tr>
				<td><strong>서버용</strong></td>
				<td>인증관리센터는 행정기관에서 관리해야할 책임이 있는 정보통신 장비가 일정 규칙에 의해 정보통신 장비가 지속적으로   행정업무를 처리하고자 하는 경우에 서버 단위로 인증서를 발급합니다.</td>
			</tr>
			<tr>
				<td class="lb">개인</td>
				<td colspan="2">인증관리센터는 행정기관 소속 공무원이 사용자인증 및 전자결재, 보안메일 등의   행정업무 또는 전자상거래 등에서 활용하도록 하기 위해 부처별 개인단위로 인증서를 발급합니다.</td>
			</tr>
		</table>
		
		<h2>인증/등록기관</h2>
		<table>
			<colgroup>
				<col style="width:120px">
				<col style="width:88px">
				<col style="">
				<col style="">
			</colgroup>
			<tr>
				<td class="lb">구분</td>
				<td class="lb">ou명</td>
				<td class="lb">cn명</td>
				<td class="lb">DN명(예)</td>
			</tr>
			<tr>
				<td class="lb">최상위 인증기관</td>
				<td>ou=GPK</td>
				<td>cn=Root CA</td>
				<td>cn=Root CA, ou=GPKI, o=Government of Korea,c=KR</td>
			</tr>
			<tr>
				<td class="lb">인증기관</td>
				<td>ou=GPKI</td>
				<td>cn=CA + 기관코드(7) + 일련번호(2)</td>
				<td>cn=CA131000001, ou=GPKI, o=Government of Korea,c=KR</td>
			</tr>
			<tr>
				<td class="lb">등록기관</td>
				<td>cn=해당인증기관,<br />
				ou=GPKI<br /></td>
				<td>cn=RA   + 기관코드(7) + 일련번호(2)</td>
				<td>cn=RA131000001,cn=CA131000001, ou=GPKI, o=Government of Korea,c=KR</td>
			</tr>
		</table>
		
		<h2>기관/개인</h2>
		<table>
			<tr>
				<td class="lb" colspan="2">구분</td>
				<td class="lb">ou명</td>
				<td class="lb">cn명</td>
				<td class="lb">DN명</td>
			</tr>
			<tr>
				<td class="lb">기관 </td>
				<td>기관용</td>
				<td>ou=최하위기관명, ou=상위기관명, ou=최상위기관명</td>
				<td>-</td>
				<td>ou=보안관리팀, ou=전자정부본부, ou=안전행전부, o=Government of Korea, c=KR</td>
			</tr>
			<tr>
				<td class="lb">서버용</td>
				<td>ou=Group of Server</td>
				<td>cn=SVR + 기관코드(7) + 일련번호(3)</td>
				<td>cn=SVR131000001, ou=Group of Server, o=Government of Korea, c=KR</td>
				<td></td>
			</tr>
			<tr >
				<td class="lb">개인 </td>
				<td>&nbsp;</td>
				<td>ou=people, ou=최상위기관명</td>
				<td>cn=기관구분자(3) + 이름 + 일련번호(3)<br />
				*일련번호:동명이인</td>
				<td>cn=001홍길동001, ou=people, ou=안전행전부, o=Government of Korea, c=KR</td>
			</tr>
		</table>
	</div>
	
<!-- 인증서소개 끝 -->
</body>
</html>

