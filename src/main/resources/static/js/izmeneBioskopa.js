var id;
$(document).ready(function(){
	var searchParams=new URLSearchParams(window.location.search);
	 id=searchParams.get('id');
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/bioskopi/"+id,
		dataType:"json",
		success:function(data){
			console.log("SUCCCESS: ",data);
			$("#naziv-izmena").val(data['naziv']);
			$("#adresa-izmena").val(data['adresa']);
			$("#brojCentrale-izmena").val(data['brojTelefona']);
			$("#email-izmena").val(data['email']);
			$("#idMenadzera-izmena").val(data['idMenadzera']);
		},
		error: function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv-izmena").val();
	var adresa=$("#adresa-izmena").val();
	var email=$("#email-izmena").val();
	var brojCentrale=$("#brojCentrale-izmena").val();
	var idMenadzera=$("#idMenadzera-izmena").val();
	var editBioskop=formToJSON(naziv,adresa,email,brojCentrale,idMenadzera);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/bioskopi/izmene/"+id,
		dataType:"json",
		contentType:"application/json",
		data: editBioskop,
		success:function(){
			alert("Uspesno ste editovali");
			window.location.href="svi-bioskopi.html";
		},
		error: function (data) {
            alert("Greška!");
        }
	});
});
function formToJSON(naziv,adresa,email,brojCentrale,idMenadzera){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"email":email,
		"brojTelefona":brojCentrale,
		"idMenadzera":idMenadzera
	});
}