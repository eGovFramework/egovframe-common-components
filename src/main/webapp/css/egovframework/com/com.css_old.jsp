<%@ page contentType="text/css; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
/*
  Css Name : com.css
  Description : 공통 CSS
  Modification Information

      수정일        수정자         수정내용
     ----------    --------    ---------------------------
     2016.06.08     서민영        최초 생성

    author   : 공통컴포넌트 개발팀 서민영
    since    : 2016.06.08
*/
@charset "utf-8";

body{ 
	margin-top:0px;
	margin-left:5px;
	margin-bottom: 0;
	margin-right:: 0;
	font-family:'돋움', '굴림', 'Arial', 'AppleGothic', 'sans-serif'; 
	font-size: 12px;
}

div,h1,h2,h3,h4,h5,form,fieldset,p,button,table,th,td { margin: 0; padding: 0;}
div,table,em,strong,h1,h2,h3,h4,h5,h6,form,fieldset,p,th,td,input,textarea,select,button { color: #666; }

fieldset, img { border: 0; }
textarea { width: 100%; }
select, input, img, button, label { vertical-align: middle; }
ul, ol { list-style : none; } 
caption span, .hide, legend { position: absolute; left: -1000%; top: 0; }
table { border-collapse: collapse; border-spacing: 0; width: 100%; }
a { color: #666; text-decoration: none; vertical-align: middle; }
a:hover { color: #000; text-decoration: none; }
button { border: none; }
textarea,
input[type="file"],
input[type="password"],
input[type="text"] { color: #727272; height: 20px; border: 1px solid #dedede; padding-left: 5px; }
textarea[readonly="readonly"],
button[readonly="readonly"],
select[readonly="readonly"],
input[readonly="readonly"],
textarea[disabled="disabled"],
button[disabled="disabled"],
select[disabled="disabled"],
input[disabled="disabled"] { color: #999; background: #f7f7f7; }
input[type="password"],
input[type="file"],
input[type="text"] { /*height:15px;*/ vertical-align: middle; }
input[type="file"] { width: 100%; }
input[type="radio"] { vertical-align: text-bottom; }

.board { position: relative; width: 730px; }
.board h1, .board h2 { float: left; color: #333; font-size: 16px; font-weight: 600; }
.board h1 { margin-top: 15px; }
.board h2 { margin: 15px 0 10px 0; }
.board h3 { color: #333;  font-size: 14px; font-weight: 600; margin: 50px 0 10px 0; }
.board h3 b { color: #2277d7; }
.search_box { float: right; margin-bottom: 7px; }
.search_box ul li { float: left; margin-left: 5px; }
.search_box select { padding: 3px 3px 3px 4px; border: 1px solid #dedede; }
.search_box input.s_input { width: 140px; padding-left: 5px; margin-right: 3px; }
.search_box input.s_btn { height: 24px; padding: 0 10px; border: none; color: #fff; background: #4688d2; border-radius: 2px; cursor: pointer; }
.search_box input.s_btn:hover { background: #7dabdf; }


/* table style */
.board_list { width: 100%; border-top: 2px solid #4688d2; }
.board_list caption { display: none; }
.board_list thead th,
.board_list tbody th { color: #222 ; font-size: 13px; font-weight: 600; background: #f4f4f4; padding: 12px 0; border-bottom: 1px solid #d9d9d9; }
.board_list tbody tr.odd {  }
.board_list tbody.ov tr:hover { background: #f9f9f9; }
.board_list tbody th { text-align: left; padding-left: 14px; }
.board_list tbody th.vtop { vertical-align: top; }
.board_list tbody th span.pilsu { color: #d0270b; font-weight: 500; margin-left: 3px; }
.board_list tbody td { text-align: center; padding: 11px 5px; border-bottom: 1px solid #d9d9d9; }
.board_list tbody td.left { text-align: left; padding: 6px 8px; }
.board_list tbody td.cnt { text-align: left; padding: 12px 10px 20px; line-height: 160%; }
.board_list tbody td.nopd { text-align: left; padding: 0; }
.board_list tbody td a:hover { text-decoration: underline; }
.board_list tbody td input.bbs_Nm { width: 99%; }
.board_list tbody td input.tmp { width: 160px; }
.board_list tbody td textarea { width: 99%; height: 100px; }
.board_list tbody td textarea.txt { width: 96%; height: 300px; margin: 8px; padding: 5px; }
.board_list tbody td textarea.re_txt { float: left; width: 80%; }
.board_list tbody td a.re_btn { float: right; padding: 43px 30px; margin: 1px 0 0 0; font-size: 12px; font-weight: 600; }
.board_list tbody td a.re_btn:hover { text-decoration: none; }
.board_list tbody td select { padding: 2px 0 3px 2px; border: 1px solid #dedede; }
.board_list tbody td img { margin-left: 7px; }
.top_line { border-top: 1px solid #d9d9d9; }



/* toolbar */
.toolbar { height: 52px; background-color: #e5e5e5; padding-bottom: 4px; border-bottom: 1px solid #dedede; }
	.group { float: left; padding: 4px 0 0 8px; }
	.group ul { float: left; padding: 0; margin: 0 3px 0 0; }
	.group ul li { float: left; margin-right: 3px; }
	.group ul.format li { margin: 2px 0 0 0; }
	.group ul.sep { border-left: 1px solid #ccc; padding: 0 3px; border-right: 1px solid #ccc; }

	.group input[type="button"] { width: 22px; height: 20px; line-height: 20px; text-indent: -10000em; border: none; cursor: pointer; }
	.group input[type="button"]:hover, .group input.on { background-color: #fff; border: 1px solid #dedede; }
	.ed_bold { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_format_bold.gif') left top no-repeat; }
	.ed_italic { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_format_italic.gif') 2px top no-repeat; }
	.ed_underline { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_format_underline.gif') 1px top no-repeat; }
	.ed_strike { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_format_strike.gif') 1px top no-repeat; }
	.ed_sub { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_format_sub.gif') 1px 1px no-repeat; }
	.ed_sup { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_format_sup.gif') 1px 1px no-repeat; }
	.ed_copy { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_copy.gif') left top no-repeat; }
	.ed_cut { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_cut.gif') 1px top no-repeat; }
	.ed_paste { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_paste.gif') 1px top no-repeat; }
	.ed_undo { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_undo.gif') 1px top no-repeat; }
	.ed_redo { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_redo.gif') 1px 1px no-repeat; }
	.ed_rmformat { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_rmformat.gif') 1px top no-repeat; }
	.ed_killword { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_killword.gif') 1px top no-repeat; }
	.ed_al_left { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_align_left.gif') 1px top no-repeat; }
	.ed_al_center { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_align_center.gif') 1px top no-repeat; }
	.ed_al_right { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_align_right.gif') 1px top no-repeat; }
	.ed_al_justify { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_align_justify.gif') 1px top no-repeat; }
	.ed_right { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_left_to_right.gif') 1px top no-repeat; }
	.ed_left { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_right_to_left.gif') 1px top no-repeat; }
	.ed_num { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_list_num.gif') 1px top no-repeat; }
	.ed_bullet { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_list_bullet.gif') 1px top no-repeat; }
	.ed_indent_less { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_indent_less.gif') 1px top no-repeat; }
	.ed_indent_more { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_indent_more.gif') 1px top no-repeat; }
	.ed_fg { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_color_fg.gif') 1px top no-repeat; }
	.ed_bg { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_color_bg.gif') 1px top no-repeat; }
	.ed_hr { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_hr.gif') 1px top no-repeat; }
	.ed_link { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_link.gif') 1px top no-repeat; }
	.ed_image { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_image.gif') 1px top no-repeat; }
	.ed_table { background: url('../images/egovframework/com/cmm/paging/toolbar/insert_table.gif') 1px top no-repeat; }
	.ed_html { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_html.gif') 1px top no-repeat; }
	.ed_fullscreen { background: url('../images/egovframework/com/cmm/paging/toolbar/fullscreen_maximize.gif') 1px top no-repeat; }
	.ed_help { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_help.gif') 1px top no-repeat; }
	.ed_about { background: url('../images/egovframework/com/cmm/paging/toolbar/ed_about.gif') 1px top no-repeat; }
.toolbar select { font-size: 11px; }

.reply { float: left; clear: both; border-top: 2px solid #4688d2; }
.reply ul { float: left; margin: 0; padding: 0; }
.reply ul li { float: left; border-bottom: 1px solid #d9d9d9; padding: 20px 20px 14px; }
.reply .top { height: 17px; padding-bottom: 12px; }
.reply .top strong { color: #333; font-weight: 600; }
.reply .top span.bar { color: #d3d3d3; font-size: 10px; padding: 0 9px; }
.reply .top span.date { color: #999; }
.reply .txt { line-height: 19px; }
.reply .bottom { float: right; margin: 10px 0 6px; }


/* 파일첨부버튼 */
.file_input_textbox { 	float: left; width: 170px; color:#666; border:1px solid #cfcfcf !important; margin-right: 4px; }
.file_input_div { position: relative; width: 66px; height: 24px; overflow: hidden; }
.file_input_button { position: absolute; width: 66px; height: 24px; background: #4688d2; border-radius: 1px; font-size: 11px; color: #fff; border: none; }
.file_input_hidden { position: absolute; font-size: 45px; right: 0; top: 0; 	opacity: 0; cursor: pointer; 
	filter:alpha(opacity=0);	
	-ms-filter:"alpha(opacity=0)";
	-khtml-opacity:0;
	-moz-opacity:0;
}

/* pagination */
.pagination { float: left; display: inline; width: 100%; text-align: center; margin-top: 15px; }
.pagination ul { display: inline-block; }
.pagination ul li { float: left; margin: 0 2px;  }
.pagination ul li a { display: block; color: #999; width: 26px; height: 26px; line-height: 26px; border: 1px solid #e0e0e0; }
.pagination ul li.first a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_first.gif"/>') 8px 9px no-repeat; text-indent: -10000em; }
.pagination ul li.prev a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_prev.gif"/>') 11px 9px no-repeat; text-indent: -10000em; }
.pagination ul li.next a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_next.gif"/>') 12px 9px no-repeat; text-indent: -10000em; }
.pagination ul li.last a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_last.gif"/>') 10px 9px no-repeat; text-indent: -10000em; }
.pagination ul li.first a:hover { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_first_on.gif"/>') no-repeat; }
.pagination ul li.prev a:hover { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_prev_on.gif"/>') no-repeat; }
.pagination ul li.next a:hover { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_next_on.gif"/>/') no-repeat; }
.pagination ul li.current a,
.pagination ul li a:hover { border: none; color: #fff; font-size: 15px; font-weight: 600; width: 28px; height: 28px; line-height: 28px; background: #4688d2; }

.paging { float: left; display: inline; width: 100%; font-size: 1em; text-align: center; margin: 15px 0 20px; }
.paging ul { display: inline-block; margin: 0; padding: 0; }
.paging ul li { float: left; margin: 0 3px; }
.paging ul li a { display: block; width: 15px; color: #888; text-decoration: none; }
.paging ul li.first a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_first.gif"/>') center 5px no-repeat; text-indent: -10000em; }
.paging ul li.prev a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_prev.gif"/>') center 5px no-repeat; text-indent: -10000em; }
.paging ul li.next a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_next.gif"/>') center 5px no-repeat; text-indent: -10000em; }
.paging ul li.last a { background: url('<c:url value="/images/egovframework/com/cmm/paging/pagination_last.gif"/>') center 5px no-repeat; text-indent: -10000em; }
.paging strong { display: block; padding: 0 5px; color: #2277d7; }


/* button style */
button { padding: 5px 12px 7px 12px; background: #4688d2; font-weight: 600; color: #fff; border-radius: 2px; margin-left: 4px; cursor: pointer; }
button:hover { background: #7dabdf; }
.btn_s { padding: 4px 11px 5px; background-color: #4688d2; color: #fff; font-size: 11px; border-radius: 1px; }
.btn_s:hover { color: #c7dbf1; }
.btn { float: right; margin-top: 18px; }