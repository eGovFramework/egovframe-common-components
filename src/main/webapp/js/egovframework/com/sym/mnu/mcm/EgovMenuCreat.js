/****************************************************************
 *
 * 파일명 : EgovMenuCreat.js
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
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();
var openTreeNodes	    = new Array();
var treeIcons			= new Array(6);
//var imgpath         = "./../../../../../../images/egovframework/com/cmm/utl/";

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
function createTree(arrName ) {
   var startNode, openNode;
	treeNodes = arrName;
	if (treeNodes.length > 0) {
		preloadIcons();
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var nodeValues = treeNodes[getTreeArrayId(startNode)].split("|");
		} else document.write("<input type='checkbox' name='checkAll' class='check2' onclick='javascript:fCheckAll();'>메뉴목록<br>");
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes);
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
	return true;
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
		if (nodeValues[1] == parentNode) lastChild = nodeValues[0];
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
			for (g=0; g<recursedNodes.length; g++) {
				document.write("&nbsp;&nbsp;&nbsp;");
			}
			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);
			document.write("&nbsp;&nbsp;&nbsp;");
			document.write("<input type='checkbox' id='"+i+"' name='checkField' class='check2' ");
			if(nodeValues[4] == 1){ document.write(" checked "); }
			document.write("onclick='javascript:fCheckDir(this.name, this.value,"+i+");' value=" + nodeValues[0] + ">");
			if (hasChildNode) {
				document.write("<img id='icon" + nodeValues[0] + "' src='"+imgpath+"menu_folder")
					if (isNodeOpen) document.write("open");
				document.write(".gif' border='0' alt='Folder' >");
			} else document.write("<img id='icon" + nodeValues[0] + "' src='"+imgpath+"menu_page.gif' border='0' align='absbottom' alt='Page'>");
			document.write("<a href=javascript:parent.temp_aa('" + treeNodes[i] + "');>");
			document.write(nodeValues[2]);
			document.write("</a><br>");
			if (hasChildNode) {
				document.write("<div id='div" + nodeValues[0] + "'");
					if (!isNodeOpen) document.write(" style='display: none;'");
				document.write(">");
				addTreeNode(nodeValues[0], recursedNodes);
				document.write("</div>");
			}
			recursedNodes.pop();
		}
	}
}
/*
* 트리노드 액션(열기,닫기)
*/
function openCloseEx(node, bottom) {
	var treeDiv = document.getElementById("div" + node);
	var treeJoin	= document.getElementById("join" + node);
	var treeIcon = document.getElementById("icon" + node);

	if (treeDiv.style.display == 'none') {
		if (bottom==1) treeJoin.src = treeIcons[3].src;
		else treeJoin.src = treeIcons[2].src;
		treeIcon.src = treeIcons[5].src;
		treeDiv.style.display = '';
	} else {
		if (bottom==1) treeJoin.src = treeIcons[1].src;
		else treeJoin.src = treeIcons[0].src;
		treeIcon.src = treeIcons[4].src;
		treeDiv.style.display = 'none';
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
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.menuCreatManageForm.checkField;
    if(document.menuCreatManageForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckDir(fCheckYB, fValue, fPath){
	var checkField = document.getElementsByName(fCheckYB);
}
