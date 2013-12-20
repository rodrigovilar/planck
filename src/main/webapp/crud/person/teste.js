$(document).ready(function(){
	READ();
});

function READ(){

	alert("Post test");

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/Planck/api/people",
		data: BUILDJSON(),
		dataType: "json",
		contentType:  "application/json",
		processData: true,
		headers: {
			Accept: "application/json"
		},
		complete: function(json){
			alert("People created successfully");
			}
	});
};

function BUILDJSON(){
	
	var the_json = '{"fullname":"andersoon", "birthdate":"29-11-1999", "email":"andersoon.alves@dce.ufpb", "password":"1234567"}';

	return the_json;
};