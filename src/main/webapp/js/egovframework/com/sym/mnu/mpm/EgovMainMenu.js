/****************************************************************
 *
 * 파일명 : EgovMainMenu.js
 * 설  명 : 전자정부 공통서비스 메뉴 JavaScript
 *
 * 수정일                 수정자              Function 명
 * ----------    ---------   ----------------------------
 * 2011.09.01    이기하             imgpath 변수는 js를 호출하는
 *                            jsp에서 입력받도록 수정
 * 2021.03.03    신용호             버그 수정
 *
 */

/*
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();;
var openTreeNodes	    = new Array();
var treeIcons			= new Array(6);
//var imgpath         = "./../../../../images/egovframework/com/cmm/utl/";
var treeYeobu       = false;
var chkValue        = "";
var vHtmlCode       = "";

/*
 * 노드 , 트리 구성 이미지 정보
 */
function preloadIcons() {
	treeIcons[0] = new Image();
	treeIcons[0].src = imgpath+"menu_plus.gif";
	treeIcons[1] = new Image();
	treeIcons[1].src = imgpath+"menu_plusbottom.gif";
	treeIcons[2] = new Image();
	treeIcons[2].src = imgpath+"menu_minus.gif";
	treeIcons[3] = new Image();
	treeIcons[3].src = imgpath+"menu_minusbottom.gif";
	treeIcons[4] = new Image();
	treeIcons[4].src = imgpath+"menu_folder.gif";
	treeIcons[5] = new Image();
	treeIcons[5].src = imgpath+"menu_folderopen.gif";
}
/*
* 트리생성함수
*/
function createTree(arrName, vYeobu, checkValue) {
   var startNode, openNode;
	treeNodes = arrName;
	treeYeobu = vYeobu;
	chkValue = checkValue;//"2000000"
	startNode = chkValue;
	if (treeNodes.length > 0) {
		preloadIcons();

		vHtmlCode +="<table width='250' border='0' align='center' cellpadding='0' cellspacing='0'><tr>";
		vHtmlCode +="<td valign='bottom' style='background-repeat:no-repeat'>";

		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var _getTreeArrayId = getTreeArrayId(startNode)
			var nodeValues = treeNodes[getTreeArrayId(startNode)].split("|");
			vHtmlCode +="<div class='LeftMenuTitle'><font color='#00000'><b>" + nodeValues[2] + "</b></font></div></td></tr>"
		} else vHtmlCode +="<img src='"+imgpath+"menu_base.gif' border='0' align='absbottom' alt='' >메뉴목록<br></td></tr>";
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes);
		vHtmlCode +="<tr><td height='30' valign='bottom' style='background-repeat:no-repeat'>&nbsp;</td></tr></table>";
		document.write(vHtmlCode);
	}
}
/*
* 노드위치 확인
*/
function getTreeArrayId(node) {
	for (i=0; i<treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[0]==node) return i;
	}
	return 0;
}
/*
* 트리 노드 열기
*/
function setOpenTreeNodes(openNode) {
	for (i=0; i<treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[0]==openNode) {
			openTreeNodes.push(nodeValues[0]);
			setOpenTreeNodes(nodeValues[1]);
		}
	}
}
/*
* 트리노드 오픈 여부 확인
*/
function isTreeNodeOpen(node) {
   if (treeYeobu){ return true; }
   for (i=0; i<openTreeNodes.length; i++){
	   if (openTreeNodes[i]==node){ return true; }
   }
   return false;
}
/*
* 하위 트리노드 존재여부 확인
*/
function hasChildTreeNode(parentNode) {
	for (i=0; i< treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}
/*
* 트리노드 최하위 여부 확인
*/
function lastTreeSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[1] == parentNode)
			lastChild = nodeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}
/*
* 신규 트리노드 추가
*/
function addTreeNode(parentNode, recursedNodes) {
	for (var i = 0; i < treeNodes.length; i++) {

		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[1] == parentNode) {

			var lastSibling	= lastTreeSibling(nodeValues[0], nodeValues[1]);
			var hasChildNode	= hasChildTreeNode(nodeValues[0]);
			var isNodeOpen = isTreeNodeOpen(nodeValues[0]);
			vHtmlCodeBg      ="<tr><td class='LeftMenuBg'>";
			vHtmlCodeBgList  ="<tr><td class='LeftMenuBgList'>";

			vHtmlCodeEmpty = "";
			// Write out line | empty treeIcons
			for (g=0; g<recursedNodes.length; g++) {
				vHtmlCodeEmpty +="<img src='"+imgpath+"menu_empty.gif' border='0' align='absbottom' alt='' >";
			}

			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);

			if (hasChildNode) {
				vHtmlCode +=vHtmlCodeBg+nodeValues[2]+"</td></tr>";
			} else{
				// Start link
				if(recursedNodes.length==1){
				   vHtmlCode +=vHtmlCodeBg+"<a href=javascript:fn_MovePage('" + i + "');>"+nodeValues[2]+"</a></td></tr>";
				}else{
				   vHtmlCode +=vHtmlCodeBgList+"<a href=javascript:fn_MovePage('" + i + "');>"+nodeValues[2]+"</a></td></tr>";
				}
			}

			if (hasChildNode) {
				vHtmlCode +="<div id='div" + nodeValues[0] + "'";
					if (!isNodeOpen) vHtmlCode +=" style='display: none;'";
				vHtmlCode +=">";
				addTreeNode(nodeValues[0], recursedNodes);
				vHtmlCode +="</div>";
			}
			recursedNodes.pop();
		}
	}
}

if(!Array.prototype.push) {
	function fnArrayPush() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = fnArrayPush;
}
if(!Array.prototype.pop) {
	function fnArrayPop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = fnArrayPop;
}
/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
 function fn_MovePage(nodeNum) {
 
		var nodeValues = treeNodes[nodeNum].split("|");
		parent.location.href = path + nodeValues[5];
}
