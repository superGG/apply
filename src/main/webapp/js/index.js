(function(){

	var getElement = function(id){

		return document.getElementById(id).value;
	}

	var checkout = function(){

	}

	var getForm = function(){

		var name = getElement("name");
		var classVal = getElement("class");
		var phone = getElement("phone");
		var note = getElement("note");

		params = {
			"userName" : name,
			"userClass" : classVal,
			"phoneNumber" : phone,
			"saying" : note
		}

		return params;

	}

	var createUrl = function(obj){

		var url = "http://localhost:8080/apply/apply.action?";

		for(var p in obj){

			url += (p + "=" + obj[p] + "&");

		}

		url = url.split(0,-1);

		return url;
	}


	var submit = function(){

		var params = getForm();
		var url = createUrl(params);

		var xhr = new XMLHttpRequest();

		xhr.onreadystatechange= function(){

			if(xhr.readyState == 4){

				console.log("ninico");
			}
		}


	    xhr.open("GET",url,true);
	    xhr.send(null);

	}

	document.getElementById("submit").onclick = function(){

		submit();
	}

 })();