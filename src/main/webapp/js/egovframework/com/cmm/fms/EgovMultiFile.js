/**
 * Convert a single file-input element into a 'multiple' input list
 * Usage:
 *
 *   1. Create a file input element (no name)
 *      eg. <input type="file" id="first_file_element">
 *
 *   2. Create a DIV for the output to be written to
 *      eg. <div id="files_list"></div>
 *
 *   3. Instantiate a MultiSelector object, passing in the DIV and an (optional) maximum number of files
 *      eg. var multi_selector = new MultiSelector( document.getElementById( 'files_list' ), 3 );
 *
 *   4. Add the first element
 *      eg. multi_selector.addElement( document.getElementById( 'first_file_element' ) );
 */

function MultiSelector( list_target, max , file_label ){

	// Where to write the list
	this.list_target = list_target;
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	
	this.update_count = 0;
	// Is there a maximum?
	if( max ){
		this.max = max;
	} else {
		this.max = -1;
	};
	this.file_label = file_label
	
	this.current_count = 0;
	//console.log('update!!!!!!');
	/**
	 * Add a new file input element
	 */
	_base = this;
	this.addElement = function( element ){

		//console.log('update_count>'+this.update_count);
		//console.log('max>'+this.max);
		//console.log('count>'+this.count);
		
		// Make sure it's a file input element
		if( element.tagName == 'INPUT' && element.type == 'file' ){
			
			//console.log('check step addElement start');

			// Element name -- what number am I?
			element.name = 'file_' + this.id;
			element.id =  'egovfile_' + this.id;
			element.class = "";
			
			//document.getElementById(this.file_label).setAttribute("for","egovfile_"+this.id);
			//console.log('file_label>'+element.name);

			this.id++;

			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function(){
				
				var sErrMsg = "첨부파일 갯수는 ["+_base.max+"]이상 첨부할 수 없습니다!";
				if( _base.update_count > 0 ){
					if( _base.count > (_base.max-_base.update_count) ){
						element.value = "";
						alert(sErrMsg);	return;
					}
				}
				
				if( _base.max > 0  && _base.count > _base.max ){
					element.value = "";
					alert(sErrMsg); return;
				}

				// New file input
				var new_element = document.createElement( 'input' );
				new_element.type = 'file';

				// Add new element
				this.parentNode.insertBefore( new_element, this );

				// Apply 'update' to element
				this.multi_selector.addElement( new_element );

				// Update list
				this.multi_selector.addListRowNew( this );
				
				// Hide this: we can't use display:none because Safari doesn't like it
				this.style.position = 'absolute';
				this.style.left = '-1000px';
				this.style.top = '-1000px';
				this.style.display = 'none';
				this.style.visibility = 'hidden';
				this.style.width = '0';
				this.style.height = '0';
				this.style.overflow = 'hidden';

				new_element.onkeypress = function(){
					return false;
				};

			};
			// If we've reached maximum number, disable input element
			if( this.max != -1 && this.count >= this.max ){
				//element.disabled = true;
			};

			// File element counter
			this.count++;
			// Most recent element
			this.current_element = element;
			
		} else {
			// This can only be applied to file input elements!
			alert( 'Error: not a file input element' );
		};

	};

	/**
	 * Add a new row to the list of files
	 */
	this.addListRowNew = function( element ){

		// Row div
		var new_row = document.createElement( 'div' );
		new_row.className = "file_add";
		
		// Delete button
		var new_row_button = document.createElement( 'input' );
		new_row_button.type = 'button';
		new_row_button.value = 'Delete';
		new_row_button.setAttribute("style", "margin: 1px; padding: 3px;");
		//new_row_button.style.top = '3px';

		// References
		new_row.element = element;
		
		// Delete function
		new_row_button.onclick= function(){

			// Remove element from form
			this.parentNode.element.parentNode.removeChild( this.parentNode.element );

			// Remove this row from the list
			this.parentNode.parentNode.removeChild( this.parentNode );

			// Decrement counter
			this.parentNode.element.multi_selector.count--;

			// Re-enable input element (if it's disabled)
			this.parentNode.element.multi_selector.current_element.disabled = false;

			//    which nixes your already queued uploads
			return false;
		};
		// Set row value
		//new_row.innerHTML = element.value;
		new_row.innerHTML = "<span>"+element.value+"</span>&nbsp;&nbsp;";
		
		// Add button
		new_row.appendChild( new_row_button );

		// Add it to the list
		this.list_target.appendChild( new_row );
	};
	
	/**
	 * Add a new row to the list of files
	 */
	this.addListRow = function( element ){

		// Row div
		var new_row = document.createElement( 'div' );

		// Delete button
		var new_row_button = document.createElement( 'input' );
		new_row_button.type = 'button';
		new_row_button.value = 'Delete';

		// References
		new_row.element = element;

		// Delete function
		new_row_button.onclick= function(){

			// Remove element from form
			this.parentNode.element.parentNode.removeChild( this.parentNode.element );

			// Remove this row from the list
			this.parentNode.parentNode.removeChild( this.parentNode );

			// Decrement counter
			this.parentNode.element.multi_selector.count--;

			// Re-enable input element (if it's disabled)
			this.parentNode.element.multi_selector.current_element.disabled = false;

			//    which nixes your already queued uploads
			return false;
		};

		// Set row value
		new_row.innerHTML = element.value;

		// Add button
		new_row.appendChild( new_row_button );

		// Add it to the list
		this.list_target.appendChild( new_row );
	};

};