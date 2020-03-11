/****************************************************************
 *
 * 파일명 : EgovMenuGov.js
 * 설  명 : 전자정부 공통서비스 메뉴 JavaScript
 *
 *   수정일         수정자           Function 명
 * ------------    ---------    ----------------------------
 * 2011.09.01	    이기하			imgpath 변수는 js를 호출하는
 * 								    jsp에서 입력받도록 수정
 *
 *
 */

/*
 * 트리메뉴 임시 생성
 */
// Arrays for nodes and icons
var nodes			= new Array();;
var openNodes	    = new Array();
var icons			= new Array(6);
//var imgpath         = "./../../../../../images/egovframework/com/cmm/utl/"

// Loads all icons that are used in the tree
function preloadIcons() {
	icons[0] = new Image();
	icons[0].src = imgpath+"menu_plus.gif";
	icons[1] = new Image();
	icons[1].src = imgpath+"menu_plusbottom.gif";
	icons[2] = new Image();
	icons[2].src = imgpath+"menu_minus.gif";
	icons[3] = new Image();
	icons[3].src = imgpath+"menu_minusbottom.gif";
	icons[4] = new Image();
	icons[4].src = imgpath+"menu_folder.gif";
	icons[5] = new Image();
	icons[5].src = imgpath+"menu_folderopen.gif";
}
// Create the tree
function createTree(arrName ) {
   var startNode, openNode;
	nodes = arrName;
	if (nodes.length > 0) {
		preloadIcons();
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenNodes(openNode);
		if (startNode !=0) {
			var nodeValues = nodes[getArrayId(startNode)].split("|");
			document.write("<a href='" + nodeValues[3] + "' onmouseover='window.status='" + nodeValues[2] + "';return true;' onmouseout='window.status=' ';return true;'><img src='"+imgpath+"menu_folderopen.gif' border='0' align='absbottom' alt=''>" + nodeValues[2] + "</a><br>");
		} else document.write("<img src='"+imgpath+"menu_base.gif' border='0' align='absbottom' alt='' >트리메뉴<br>");
		var recursedNodes = new Array();
		addNode(startNode, recursedNodes);
	}
}
// Returns the position of a node in the array
function getArrayId(node) {
	for (i=0; i<nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0]==node) return i;
	}
}
// Puts in array nodes that will be open
function setOpenNodes(openNode) {
	for (i=0; i<nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0]==openNode) {
			openNodes.push(nodeValues[0]);
			setOpenNodes(nodeValues[1]);
		}
	}
}
// Checks if a node is open
function isNodeOpen(node) {
	for (i=0; i<openNodes.length; i++)
		if (openNodes[i]==node) return true;
	return false;
}
// Checks if a node has any children
function hasChildNode(parentNode) {
	for (i=0; i< nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}
// Checks if a node is the last sibling
function lastSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode)
			lastChild = nodeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}
// Adds a new node to the tree
function addNode(parentNode, recursedNodes) {
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");

		if (nodeValues[1] == parentNode) {

			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

			// Write out line & empty icons
			for (g=0; g<recursedNodes.length; g++) {
				if (recursedNodes[g] == 1) document.write("<img src='"+imgpath+"menu_line.gif' border='0' align='absbottom' alt='' >");
				else  document.write("<img src='"+imgpath+"menu_empty.gif' border='0' align='absbottom' alt='' >");
			}
			// put in array line & empty icons
			if (ls) recursedNodes.push(0);
			else recursedNodes.push(1);
			// Write out join icons
			if (hcn) {
				if (ls) {
					document.write("<a href='javascript: oc(" + nodeValues[0] + ", 1);'><img id='join" + nodeValues[0] + "' src='"+imgpath);
					 	if (ino) document.write("menu_minus");
						else document.write("menu_plus");
					document.write("bottom.gif' border='0' align='absbottom' alt='Open/Close node' ></a>");
				} else {
					document.write("<a href='javascript: oc(" + nodeValues[0] + ", 0);'><img id='join" + nodeValues[0] + "' src='"+imgpath);
						if (ino) document.write("menu_minus");
						else document.write("menu_plus");
					document.write(".gif' border='0' align='absbottom' alt='Open/Close node' /></a>");
				}
			} else {
				if (ls) document.write("<img src='"+imgpath+"menu_joinbottom.gif' border='0' align='absbottom' alt='' >");
				else document.write("<img src='"+imgpath+"menu_join.gif' border='0' align='absbottom' alt='' >");
			}
			// Start link
			document.write("<a href='" + nodeValues[3] + "' onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");
			// Write out folder & page icons
			if (hcn) {
				document.write("<img id='icon" + nodeValues[0] + "' src='"+imgpath+"menu_folder")
					if (ino) document.write("open");
				document.write(".gif' border='0' alt='Folder' >");
			} else document.write("<img id='icon" + nodeValues[0] + "' src='"+imgpath+"menu_page.gif' border='0' align='absbottom' alt='Page'>");
			// Write out node name
			document.write(nodeValues[2]);
			// End link
			document.write("</a><br>");
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div id='div" + nodeValues[0] + "'");
					if (!ino) document.write(" style='display: none;'");
				document.write(">");
				addNode(nodeValues[0], recursedNodes);
				document.write("</div>");
			}
			// remove last line or empty icon
			recursedNodes.pop();
		}
	}
}
// Opens or closes a node
function oc(node, bottom) {
	var theDiv = document.getElementById("div" + node);
	var theJoin	= document.getElementById("join" + node);
	var theIcon = document.getElementById("icon" + node);

	if (theDiv.style.display == 'none') {
		if (bottom==1) theJoin.src = icons[3].src;
		else theJoin.src = icons[2].src;
		theIcon.src = icons[5].src;
		theDiv.style.display = '';
	} else {
		if (bottom==1) theJoin.src = icons[1].src;
		else theJoin.src = icons[0].src;
		theIcon.src = icons[4].src;
		theDiv.style.display = 'none';
	}
}
// Push and pop not implemented in IE
if(!Array.prototype.push) {
	function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = array_push;
}
if(!Array.prototype.pop) {
	function array_pop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = array_pop;
}
