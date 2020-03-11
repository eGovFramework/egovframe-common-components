/****************************************************************
 *
 * 파일명 : EgovMultiFiles.js
 * 설  명 : 업로드시 여러 파일에 대한 목록 표시 및 처리
 *
 * 수정일         수정자        수정내용
 * ----------   ---------   ----------------------------
 * 2019.10.15   취두영        다중 파일 업로드 개선 (HTML5)
 * 2019.12.06   신용호        EgovMultiFilesChecker 추가하여 확장자 체크 및 용량 체크 하기
 *
 */
function MultiSelector(list_target, max, file_label) {

	// Where to write the list
	this.list_target = list_target;
	this.id = 0;
	// Is there a maximum?
	if (max) {
		this.max = max;
	} else {
		this.max = -1;
	}
	
	this.file_label = file_label;
	/**
	 * Add a new file input element
	 */
	_base = this;
	this.addElement = function(element) {
		// Make sure it's a file input element
		if (element.tagName == 'INPUT' && element.type == 'file') {

			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function() {

				var i = 0;

				var sErrMsg = "첨부파일 개수는 [" + _base.max + "]까지 첨부할 수 있습니다!";
				var files_length = 1;
				// HTML5 지원여부 체크
				if (typeof element.files != "undefined") {
					files_length = element.files.length;
				}

				if (_base.max < files_length) {
					element.value = "";
					alert(sErrMsg);

					while (list_target.firstChild) {
						list_target.removeChild(list_target.firstChild);
					}

					return;
				}

				if (document.getElementById("egov_file_view_table") != null) {
					var sum = files_length
							+ document.getElementById("egov_file_view_table")
									.getElementsByTagName("tr").length;

					if (_base.max < sum) {
						element.value = "";
						alert(sErrMsg);
						return;
					}
				}

				if (files_length > 0) {
					while (list_target.firstChild) {
						list_target.removeChild(list_target.firstChild);
					}
				}

				// Update list
				for (i; i < files_length; i++) {
					this.multi_selector.addListRowNew(this, i);
				}

			};
			// Most recent element
			this.current_element = element;
		} else {
			// This can only be applied to file input elements!
			alert('Error: not a file input element');
		}
		;
	};

	/**
	 * Add a new row to the list of files
	 */
	this.addListRowNew = function(element, i) {

		// Row div
		var new_row = document.createElement('div');
		new_row.className = "file_add_" + i;
		new_row.innerHTML = "<span>" + element.files[i].name
				+ "</span>&nbsp;&nbsp;";

		// Add it to the list
		this.list_target.appendChild(new_row);
	};

};

var EgovMultiFilesChecker = {
	getFileExtension: function(filename) {

		if (filename == null) return "";
	    var __fileLen = filename.length;
	    var __lastDot = filename.lastIndexOf('.');
	    if (__lastDot < 0 ) return "";
	    var __fileExt = filename.substring(__lastDot, __fileLen).toLowerCase();
	 
	    return __fileExt;
	}
	// 결과가 true 인경우 허용
	// 결과가 false 인경우 불가 
	,checkExtensions: function(fileObjId, allowTypes) {
		if ( document.getElementById( fileObjId ) == null ) return false; // file객체가 없으면 승인하지 않는다.
		if ( typeof document.getElementById( fileObjId ).files == "undefined" )
			return this.checkExtensionsOldIE(fileObjId, allowTypes);
		else
			return this.checkExtensionsHTML5(fileObjId, allowTypes);
			
	}
	,checkExtensionsHTML5: function(fileObjId, allowTypes) {
		var __filelen = document.getElementById( fileObjId ).files.length;
		var __fileObjs = document.getElementById( fileObjId ).files;
	    if ( __filelen == 0 ) return true;
	    for(var i=0; i<__fileObjs.length; i++) {
	    	var __fileObj = __fileObjs[i];
	    	console.log(__fileObj.name);
	    	console.log(this.getFileExtension(__fileObj.name));

	    	var __fileExt = this.getFileExtension(__fileObj.name);
	    	if ( __fileExt == "" || allowTypes.indexOf(__fileExt) < 0 ) {
	    		alert("허용되지 않는 확장자 입니다.["+__fileExt+"]");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	,checkExtensionsOldIE: function(fileObjId, allowTypes) {
		var __filelPath = document.getElementById( fileObjId ).value;
	    console.log(__filelPath);
	    console.log(this.getFileExtension(__filelPath));

	    var __fileExt = this.getFileExtension(__filelPath);
    	if ( __fileExt == "" || allowTypes.indexOf(__fileExt) < 0 ) {
    		alert("2.허용되지 않는 확장자 입니다.["+__fileExt+"]");
    		return false;
    	}
	    
	    return true;
	}

	// 결과가 true 인경우 허용
	// 결과가 false 인경우 불가
	,checkFileSize: function(fileObjId, allowSize) {
		if ( document.getElementById( fileObjId ) == null ) return false; // file객체가 없으면 승인하지 않는다.
		if ( typeof document.getElementById( fileObjId ).files == "undefined" )
			return this.checkFileSizeOldIE(fileObjId, allowSize);
		else
			return this.checkFileSizeHTML5(fileObjId, allowSize);
	}
	,checkFileSizeHTML5: function(fileObjId, allowSize) {
		var __filelen = document.getElementById( fileObjId ).files.length;
		var __fileObjs = document.getElementById( fileObjId ).files;
	    if ( __filelen == 0 ) return true;
	    for(var i=0; i<__fileObjs.length; i++) {
	    	var __fileObj = __fileObjs[i];
	    	console.log(__fileObj.name);
	    	console.log(this.getFileExtension(__fileObj.name));
	    	console.log(__fileObj.size);
	    	
	    	if ( __fileObj.size > allowSize ) {
	    		alert("허용되지 않는 파일 사이즈 입니다.["+__fileObj.name+" : "+__fileObj.size+" bytes / "+allowSize+" bytes]");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	// 구형 IE 브라우저의 경우 사이즈 체크의 제한이 있습니다.
	,checkFileSizeOldIE: function(fileObjId, allowSize) {
		
		var __filelPath = document.getElementById( fileObjId ).value;
	    console.log(__filelPath);

    	alert("구형 브라우저에서는 파일 사이즈 체크를 할수 없습니다.");
	    
	    return true;
	}

};

