$(document).ready(function(){
	READ();
});

function READ(){

	alert("Anderson");

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/Planck/people",
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
	
	
	var the_json = '{fullname":"anderson", "birthdate":"Nov 21, 2013", "email":"anderson.alves@dce.ufpb", "password":"1234567"}';

	return the_json;
};
