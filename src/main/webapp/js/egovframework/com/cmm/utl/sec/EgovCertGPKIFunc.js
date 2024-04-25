document.writeln('<form name=gpkiForm METHOD=POST>');

document.writeln('      <input name=encryptedData type=hidden>');

document.writeln('</form>');





/*********************************************************************/

//				init		                     //

/*********************************************************************/

function Init()

{

	var nResult;

	nResult = GPKISecureWeb.Init(WorkDir, ServerCert, AlgoMode, GNCertType, ValidCertInfo, ReadCertType, KeyStrokeType );

	if( nResult == 1 || nResult == 100)

	{

		return 1;

	}

	else

	{

		const strReturnData = GPKISecureWeb.GetReturnData();

		alert(strReturnData);

		return nResult;

	}

}



/*********************************************************************/

//				UbiKey init	                     //

/*********************************************************************/

function UbiKeyInit()

{

	var nResult;



//	alert(UbikeyVersion + "-" + UbikeyPopupURL + "-" + UbikeyWParam + "-" + UbikeylParam );

	nResult = GPKISecureWeb.UbiKeyInit( UbikeyVersion, UbikeyPopupURL, UbikeyWParam, UbikeylParam );



}



/*********************************************************************/

//				init		                     //

/*********************************************************************/

function EmbInit(form)

{



	var nResult;

	form.pwd.focus();



	nResult = document.EMX.Init(GNCertType, ReadCertType, ValidCertInfo);



	nResult = document.EMX.UbiKeyInit( UbikeyVersion, UbikeyPopupURL, UbikeyWParam, UbikeylParam );



	return nResult;

}





/*********************************************************************/

//                   LoginEmbedded	                //

/*********************************************************************/

function LoginEmbedded(form)

{

	var nResult;					// Return Code

	var strReturnData;

	var strData;

	nResult = Init();

	if( nResult == 117)

		return;



	var sessionID = "";

   	if( form.challenge.value != null)

	{

		strData = "challenge=";

		strData += form.challenge.value;

		sessionID = form.challenge.value;

	}



	if( document.EMX.SetSessionID(sessionID) != 1)

	{

		return;

	}



	if( form.pwd.value == "")

	{

//		alert("비밀번호를 입력하십시오");

		return;

	}



	nResult = document.EMX.Login(WorkDir, form.pwd.value, SiteID, ServerCert, AlgoMode, strData);

	strReturnData = document.EMX.GetReturnData();



	if(nResult == 1 )

	{

		// embedded session -> popup session

		const strSessionID = document.EMX.GetSessionID();

		document.GPKISecureWeb.SetGlobalSessionID(strSessionID);



		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}

}







function CertSavePhone(form)



{	var strData;

	var nResult;

	var strReturnData;

	var strSendData;



	strData= GPKISubmit(form);



	nResult = Init();

	if( nResult == 117)

		return;



	UbiKeyInit();



	nResult = GPKISecureWeb.SaveCertPhone();



	strReturnData = GPKISecureWeb.GetReturnData();



	if( nResult == 1 )

	{



		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}

}



/*********************************************************************/

//                   Login                //

/*********************************************************************/

function Login(form)

{

	var strData;

	var nResult;

	var strReturnData;

	var strSendData;

	strData= GPKISubmit(form);

	nResult = Init();

	if( nResult == 117)

		return;



	UbiKeyInit();





	var sessionID = "";

        if( form.challenge.value != null)

		sessionID = form.challenge.value;



	if( GPKISecureWeb.SetSessionID(sessionID) != 1)

	{

		return;

	}

	nResult = GPKISecureWeb.Login(SiteID, strData);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1 )

	{



		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}



}

/*********************************************************************/

//                   LoginLink	                //

/*********************************************************************/

function LoginLink(link)

{

	var strData;

	var nResult;

	var strReturnData;

	var strSendData;

	nResult = Init();

	if( nResult == 117)

		return;



	strData = GPKILink( link );

	var sessionID = "";

	if( GPKISecureWeb.SetSessionID(sessionID) != 1)

	{

		return;

	}

	nResult = GPKISecureWeb.Login(SiteID, strData);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1)

	{

		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.action = link;

		if ( link.target == "" || link.target == null ) {

			document.gpkiForm.target="_self";

		}else{

			document.gpkiForm.target=link.target;

		}

		link.href = '#';

		document.gpkiForm.submit();

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}

}



//********************************************************************//

//		Logout					              //

//********************************************************************//

function Logout()

{

	var strData;

	var nResult;

	var strReturnData;

	var strSendData;



	nResult = Init();

	if( nResult == 117)

		return;



	nResult = GPKISecureWeb.Logout(SiteID);

	if( nResult == 1 )

	{



//		alert("로그인한 세션이 종료되었습니다.");

		top.location.href = ServiceStartPageURL;

	}

}



/*********************************************************************/

//		       EnvelopedSignData			  //

/*********************************************************************/

function EnvelopedSignData(form)

{

	var strData;

	var nResult;

	var strReturnData;

	var strSendData;

	strData= GPKISubmit( form);

	nResult = Init();

	if( nResult == 117)

		return;



	var sessionID = "";

    	if( form.challenge.value != null)

		sessionID = form.challenge.value;



	if( GPKISecureWeb.SetSessionID(sessionID) != 1)

	{

		return;

	}

	nResult = GPKISecureWeb.EnvelopedSignData(SiteID, strData);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1)

	{

		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();



	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}





}

/*********************************************************************/

//		       EnvelopData			  //

/*********************************************************************/

function EnvelopedData(form)

{

	var strData;

	var nResult;

	var strReturnData;

	var strSendData;

	strData= GPKISubmit( form);

	nResult = Init();

	if( nResult == 117)

		return;



	var sessionID = "";

    	if( form.challenge.value != null)

		sessionID = form.challenge.value;



	if( GPKISecureWeb.SetSessionID(sessionID) != 1)

	{

		return;

	}



	nResult = GPKISecureWeb.EnvelopData(SiteID, strData);

	strReturnData = GPKISecureWeb.GetReturnData();



	if( nResult == 1 )

	{

		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}

}





/*********************************************************************/

//		      SignedDataForm(form)										//

/*********************************************************************/

function SignedDataForm(form)

{

	var strData;

	var nResult;

	var strReturnData;

	nResult = Init();

	if( nResult == 117)

		return;



	strData= GPKISubmit(form)

	strReturnData = SignedData(strData);



	if( strReturnData != "" )

	{

		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

}



/*********************************************************************/

//		      SignedData(data)										//

/*********************************************************************/

function SignedData(data)

{

	var nResult;

	var strReturnData;

	nResult = Init();

	if( nResult == 117)

		return;

	nResult = GPKISecureWeb.SignedData(SiteID, CertOption, data);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1 )

	{

		return strReturnData;

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

		return "";

	}

}



/*********************************************************************/

//		      SignedDataWithVIDCheck(data, IDN)										//

/*********************************************************************/

function SignedDataWithVIDCheck(data, IDN)

{

	var nResult;

	var strReturnData;

	nResult = Init();

	if( nResult == 117)

		return;



	nResult = GPKISecureWeb.SignedDataWithVIDCheck(SiteID, CertOption, data, IDN);

	strReturnData = GPKISecureWeb.GetReturnData();



	if( nResult == 1 )

	{

		return strReturnData;

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

		return "";

	}

}



/*********************************************************************/

//		      EncryptedSignData										//

/*********************************************************************/

function EncryptedSignData(form)

{

	var strData;

	var nResult;

	var strReturnData;

	nResult = Init();

	if( nResult == 117)

		return;

	strData= GPKISubmit( form)

	nResult = GPKISecureWeb.EncryptedSignData(SiteID, CertOption, strData);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1 )

	{

		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

	else

	{

		if( nResult != 106)

			alert(strReturnData);

	}

}





/*********************************************************************/

//		      Encrypt												//

/*********************************************************************/

function Encrypt(form)

{

	var strData;

	var nResult;

	var strReturnData;



	strData= GPKISubmit( form)



	nResult = Init();

	if( nResult == 117)

		return;

	nResult = GPKISecureWeb.Encrypt(SiteID, strData);

	strReturnData = GPKISecureWeb.GetReturnData();



	if( nResult == 1 )

	{

		document.gpkiForm.encryptedData.value = strReturnData;

		document.gpkiForm.method = form.method;

		document.gpkiForm.action = form.action;

		document.gpkiForm.submit();

	}

	else

	{

		alert(strReturnData);

	}

}



/*********************************************************************/

//		      EncryptLink											//

/*********************************************************************/

function EncryptLink(link)

{

	var strData;

	var nResult;

	var strReturnData;

	nResult = Init();

	if( nResult == 117)

		return;

	strData= GPKILink(link);

	link.href += "encryptedData=";

	nResult = GPKISecureWeb.Encrypt(SiteID, strData);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1 )

	{



		strData = replaceEscapeString( strReturnData )

		link.href += strData;

	}

	else

	{

		alert(strReturnData);

	}

}





/*********************************************************************/

//		      Decrypt												//

/*********************************************************************/

function Decrypt(encData)

{

	var strData;

	var nResult;

	var strReturnData = "";



	nResult = Init();

	if( nResult == 117)

		return;



	nResult = GPKISecureWeb.Decrypt(SiteID, encData);

	strReturnData = GPKISecureWeb.GetReturnData();

	if( nResult == 1 )

	{

		return strReturnData;

	}

	else

	{

		alert("Decrypt Fail");

		alert(strReturnData);

		return "";

	}

}



/*********************************************************************/

//		      WrapperTag 											//

/*********************************************************************/

//  <GPKI_ENC> Data </GPKI_ENC>

function WrapperTag(Msg)

{

	var TagData;



	TagData = "<GPKI_ENC>";

	TagData += Msg;

	TagData += "</GPKI_ENC>"



	alert(TagDat);

	return TagData;

}





/*********************************************************************/

//	               GPKISubmit(form)									//

/*********************************************************************/

function GPKISubmit( form )

{

	var queryString = "";

	var qs_index = "";

	var action = "";

	var noenc_qs = "";

	if ( form.action.indexOf('?') != -1 )

	{



		alert(form.action);

		action = form.action;

		document.gpkiForm.action = action.substring( 0, form.action.lastIndexOf('?') );

		queryString = action.substring( action.lastIndexOf('?') + 1, action.length) + '&';

	}

	else

	{

		document.gpkiForm.action = form.action;

	}



	queryString += makeQueryString(form);

	return queryString;



}

/*********************************************************************/

//	               GPKILink(link)		                //

/*********************************************************************/

function GPKILink( link )

{

	const nResult = Init();

	if( nResult == 117)

		return;

	var action = "";

	var queryString = "";

	var noenc_qs = "";

	var strResult ="";

	var strCode = ""

	var strMsg = ""



	if ( link.protocol != "http:" )

	{

//		alert("http 프로토콜만 사용가능합니다");

		return true;

	}



	if (link.search.length < 1)

	{

//		alert("암호화할 Data가 없습니다.");

		return false;

	}



	action = "http://" + link.hostname + ":" + link.port + "/" + link.pathname;

	queryString = link.search.substring( link.search.lastIndexOf('?') + 1, link.search.length);



	link.href = action + "?";

	return queryString;

}



function makeQueryString( form )

{

	var name  =  new Array(form.elements.length);

	var value =  new Array(form.elements.length);

	var flag  = false;

	var j     = 0;

	var plain_text ="";



	const len = form.elements.length;



	for (let i = 0; i < len; i++)

	{



		if( (form.elements[i].type != "button") && (form.elements[i].type != "reset") && (form.elements[i].type != "submit") )

		{

			if (form.elements[i].type == "radio" || form.elements[i].type == "checkbox")

			{

				if (form.elements[i].checked == true)

				{

					name[j] = form.elements[i].name;

					value[j] = form.elements[i].value;

					j++;

				}

			}

			else {

				name[j] = form.elements[i].name;

				if (form.elements[i].type == "select-one")

				{

					var ind = form.elements[i].selectedIndex;

					if (form.elements[i].options[ind].value != '')

						value[j] = form.elements[i].options[ind].value;

					else

						value[j] = form.elements[i].options[ind].text;

				}

				else

				{

					value[j] = form.elements[i].value;

				}

				j++;

			}

		}

	}


	for (let i = 0; i < j; i++)

	{

		if (flag)

			plain_text += "&";

		else

			flag = true;

		plain_text += name[i] ;

		plain_text += "=";

		plain_text += value[i];

	}

	return plain_text;

}



function replaceEscapeString( qureyString )

{

	var i;

	var ch;

	var rstring = '';

	var qstring = '';



	qstring = String(qureyString);



	for (i = 0; i < qstring.length; i++)

	{

		ch = qstring.charAt(i);



		if (ch == ' ')  rstring += '%20';

		else

		if (ch == '%')  rstring += '%25';

		else

		if (ch == '&')  rstring += '%26';

		else

		if (ch == '+')  rstring += '%2B';

		else

		if (ch == '=')  rstring += '%3D';

		else

		if (ch == '?')  rstring += '%3F';

		else rstring += ch;

	}



	return rstring;

}



function embeddedEnterEvent(form)

{

	// Enter Key

	if (event.keyCode == 13)

	{

		LoginEmbedded(form);

	}

	else

		return;

}
