<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	 * @Class Name : EgovInsertImage.jsp
	 * @Description : 웹에디터 이미지 upload 기능을 위한 팝업 화면 (기존 insert_image.html 대체)
	 * @Modification Information
	 * @
	 * @  수정일      	수정자          수정내용
	 * @ -----------   --------    ---------------------------
	 * @ 2009.08.26   	한성곤          최초 생성
	 * @ 2012.12.18   	이기하          image alt 생성기능 추가
	 *
	 *  @author 공통컴포넌트개발팀 한성곤
	 *  @since 2009.08.26
	 *  @version 1.0 
	 *  @see
	 */

	//htmlArea v3.0 - Copyright (c) 2002, 2003 interactivetools.com, inc.
	//This copyright notice MUST stay intact for use (see license.txt).
	//
	//Portions (c) dynarch.com, 2003
	//
	//A free WYSIWYG editor replacement for <textarea> fields.
	//For full source code and docs, visit http://www.htmlarea.com/
	//
	//Version 3.0 developed by Mihai Bazon.
	//http://dynarch.com/mishoo
	//
	//$Id: popup.js,v 1.12 2005/01/24 18:29:48 itools Exp $
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>이미지 추가</title>
<script type="text/javascript">
	function getAbsolutePos(el) {
		var r = {
			x : el.offsetLeft,
			y : el.offsetTop
		};
		if (el.offsetParent) {
			var tmp = getAbsolutePos(el.offsetParent);
			r.x += tmp.x;
			r.y += tmp.y;
		}
		return r;
	};

	function comboSelectValue(c, val) {
		var ops = c.getElementsByTagName("option");
		for ( var i = ops.length; --i >= 0;) {
			var op = ops[i];
			op.selected = (op.value == val);
		}
		c.value = val;
	};

	function __dlg_onclose() {
		opener.Dialog._return(null);
	};

	function __dlg_init(bottom) {
		var body = document.body;
		var body_height = 0;
		if (typeof bottom == "undefined") {
			var div = document.createElement("div");
			body.appendChild(div);
			var pos = getAbsolutePos(div);
			body_height = pos.y;
		} else {
			var pos = getAbsolutePos(bottom);
			body_height = pos.y + bottom.offsetHeight;
		}
		window.dialogArguments = opener.Dialog._arguments;
		if (!document.all) {
			//window.sizeToContent();
			//window.sizeToContent();	// for reasons beyond understanding,
			// only if we call it twice we get the
			// correct size.
			window.addEventListener("unload", __dlg_onclose, true);
			window.innerWidth = body.offsetWidth + 5;
			window.innerHeight = body_height + 2;
			// center on parent
			var x = opener.screenX + (opener.outerWidth - window.outerWidth)
					/ 2;
			var y = opener.screenY + (opener.outerHeight - window.outerHeight)
					/ 2;
			window.moveTo(x, y);
			if (navigator.userAgent.toLowerCase().indexOf("chrome") != -1) { // chrome 경우는 alert()을 추가해 주어야만 사이징이 됨... 허허
				alert('삽입할 그림에 대한 정보를 설정해 주십시오.');
				self.resizeTo(body.offsetWidth + 50, body_height + 90);
			} else {
				self.resizeTo(body.offsetWidth + 50, body_height + 90);
			}
		} else { // IE
			// window.dialogHeight = body.offsetHeight + 50 + "px";
			// window.dialogWidth = body.offsetWidth + "px";
			window.resizeTo(body.offsetWidth + 30, body_height + 90);
			var ch = body.clientHeight;
			var cw = body.clientWidth;
			window.resizeBy(body.offsetWidth - cw, body_height - ch);
			var W = body.offsetWidth;
			var H = 2 * body_height - ch;
			var x = (screen.availWidth - W) / 2;
			var y = (screen.availHeight - H) / 2;
			window.moveTo(x, y);
		}
		document.body.onkeypress = __dlg_close_on_esc;
	};

	function __dlg_translate(i18n) {
		var types = [ "input", "select", "legend", "span", "option", "td",
				"button", "div" ];
		for ( var type = 0; type < types.length; ++type) {
			var spans = document.getElementsByTagName(types[type]);
			for ( var i = spans.length; --i >= 0;) {
				var span = spans[i];
				if (span.firstChild && span.firstChild.data) {
					var txt = i18n[span.firstChild.data];
					if (txt)
						span.firstChild.data = txt;
				}
				if (span.title) {
					var txt = i18n[span.title];
					if (txt)
						span.title = txt;
				}
			}
		}
		var txt = i18n[document.title];
		if (txt)
			document.title = txt;
	};

	//closes the dialog and passes the return info upper.
	function __dlg_close(val) {
		opener.Dialog._return(val);
		window.close();
	};

	function __dlg_close_on_esc(ev) {
		ev || (ev = window.event);
		if (ev.keyCode == 27) {
			window.close();
			return false;
		}
		return true;
	};
</script>
<script type="text/javascript">
	window.resizeTo(420, 120);
	function Init() {
		__dlg_init();

		var param = window.dialogArguments;

		if (param) {
			document.getElementById("f_url").value = param["f_url"];
			document.getElementById("f_alt").value = param["f_alt"];
			document.getElementById("f_border").value = param["f_border"];
			document.getElementById("f_align").value = param["f_align"];
			document.getElementById("f_vert").value = param["f_vert"];
			document.getElementById("f_horiz").value = param["f_horiz"];

			window.ipreview.location.replace(param.f_url);
		} else {
			if (document.getElementById("f_url").value) {
				window.ipreview.location.replace(document
						.getElementById("f_url").value);
			}
		}

		document.getElementById("f_url").focus();
		
		var msg = "${msg}";
		if ( msg!="" ) alert(msg);
	};

	function onOK() {

		var required = {
			"f_url" : "You must enter the URL"
		};

		for ( var i in required) {
			var el = document.getElementById(i);

			if (!el.value) {
				alert(required[i]);
				el.focus();

				return false;
			}
		}

		// pass data back to the calling window

		var fields = [ "f_url", "f_alt", "f_align", "f_border", "f_horiz",
				"f_vert" ];

		var param = new Object();

		for ( var i in fields) {
			var id = fields[i];
			var el = document.getElementById(id);

			param[id] = el.value;
		}

		// alert(param["f_url"]);
		// alert(param["f_alt"]);

		__dlg_close(param);

		return false;
	};

	function onCancel() {
		__dlg_close(null);

		return false;
	};

	function onPreview() {
		var f_url = document.getElementById("f_url");
		var url = f_url.value;

		if (!url) {
			alert("You have to enter an URL first");
			f_url.focus();

			return false;
		}

		window.ipreview.location.replace(url);

		return false;
	};

	function onUpload() {
		var f_upload = document.getElementById("f_upload");
		var file = f_upload.value;

		if (!file) {
			alert("You have to select image file first");
			f_upload.focus();

			return false;
		}

		var frm = document.getElementsByTagName("form")[0];

		frm.submit();

		return false;
	};
</script>

<style type="text/css">
html,body {
	background: ButtonFace;
	color: ButtonText;
	font: 11px Tahoma, Verdana, sans-serif;
	margin: 0px;
	padding: 0px;
}

body {
	padding: 5px;
}

table {
	font: 11px Tahoma, Verdana, sans-serif;
}

form p {
	margin-top: 5px;
	margin-bottom: 5px;
}

.fl {
	width: 9em;
	float: left;
	padding: 2px 5px;
	text-align: right;
}

.fr {
	width: 6em;
	float: left;
	padding: 2px 5px;
	text-align: right;
}

fieldset {
	padding: 0px 10px 5px 5px;
}

select,input,button {
	font: 11px Tahoma, Verdana, sans-serif;
}

button {
	width: 70px;
}

.space {
	padding: 2px;
}

.title {
	background: #ddf;
	color: #000;
	font-weight: bold;
	font-size: 120%;
	padding: 3px 10px;
	margin-bottom: 10px;
	border-bottom: 1px solid black;
	letter-spacing: 2px;
}

form {
	padding: 0px;
	margin: 0px;
}
</style>
</head>
<body onload="Init()">
	<div class="title">이미지 추가</div>
	<form action="" method="post" enctype="multipart/form-data">
		<table border="0" width="100%" style="padding: 0px; margin: 0px">
			<tbody>
				<tr>
					<td style="width: 7em; text-align: right"><label
						for="f_upload">Image Upload:</label></td>
					<td><input type="file" name="file" id="f_upload"
						style="width: 100%" title="Select upload image here" />
						<button name="upload" onclick="return onUpload();"
							title="Upload image file">Upload</button></td>
				</tr>
				<tr>
					<td style="width: 7em; text-align: right"><label
						for="f_url">Image URL:</label></td>
					<td><input type="text" name="url" id="f_url"
						style="width: 75%" title="Enter the image URL here" value="${url}" />
						<button name="preview" onclick="return onPreview();"
							title="Preview the image in a new window">Preview</button></td>
				</tr>
				<tr>
					<td style="width: 7em; text-align: right"><label
						for="f_alt">Alternate text:</label></td>
					<td><input type="text" name="alt" id="f_alt" value="${alt}"
						style="width: 100%" title="For browsers that don't support images" /></td>
				</tr>
			</tbody>
		</table>

		<p />

		<fieldset style="float: left; margin-left: 5px;">

			<legend>Layout</legend>

			<div class="space"></div>

			<div class="fl">
				<label for="f_align">Alignment:</label>
			</div>

			<select size="1" name="align" id="f_align"
				title="Positioning of this image">
				<option value="">Not set</option>
				<option value="left">Left</option>
				<option value="right">Right</option>
				<option value="texttop">Texttop</option>
				<option value="absmiddle">Absmiddle</option>
				<option value="baseline" selected="selected">Baseline</option>
				<option value="absbottom">Absbottom</option>
				<option value="bottom">Bottom</option>
				<option value="middle">Middle</option>
				<option value="top">Top</option>
			</select>

			<p />

			<div class="fl">
				<label for="f_border">Border thickness:</label>
			</div>

			<input type="text" name="border" id="f_border" size="5"
				title="Leave empty for no border" />

			<div class="space"></div>

		</fieldset>

		<fieldset style="float: right; margin-right: 5px;">

			<legend>Spacing</legend>

			<div class="space"></div>

			<div class="fr"><label for="f_horiz">Horizontal:</label></div>

			<input type="text" name="horiz" id="f_horiz" size="5"
				title="Horizontal padding" />
			<p />

			<div class="fr"><label for="f_vert">Vertical:</label></div>

			<input type="text" name="vert" id="f_vert" size="5"
				title="Vertical padding" />

			<div class="space"></div>

		</fieldset>

		<br clear="all" />

		<table width="100%" style="margin-bottom: 0.2em">
			<tr>
				<td valign="bottom"><label for="ipreview">Image Preview:</label><br /> <iframe
						name="ipreview" id="ipreview" frameborder="0"
						style="border: 1px solid gray;" height="200" width="300" src=""
						title="이미지 미리보기"></iframe>
				</td>
				<td valign="bottom" style="text-align: right">
					<button type="button" name="ok" onclick="return onOK();">OK</button>
					<br />
					<button type="button" name="cancel" onclick="return onCancel();">Cancel</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>