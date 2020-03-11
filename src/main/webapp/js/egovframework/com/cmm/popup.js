/****************************************************************
 *
 * 파일명 : popup
 * 설  명 : 팝업 기능을 처리하는 JavaScript
 *
 *    수정일          수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2016.08.05       장동한        1.0             최초생성
 *
 */
/* ********************************************************
 * 팝업창 오픈
 ******************************************************** */
 function fn_egov_popup(sName, sUrl, width, height){

 	var LeftPosition=(screen.width-width)/2;
	var TopPosition=(screen.height-height)/2;

	var oPopup = window.open(sUrl,sName,"width="+width+",height="+height+",top="+TopPosition+",left="+LeftPosition+", scrollbars=no");
	if(oPopup){oPopup.focus();}
 }