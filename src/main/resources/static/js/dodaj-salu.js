/*$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var oznakaSale=$("#oznakaSale").val();
	var kapacitet=$("#kapacitet").val();
	var bioskopId=$("#bioskopId").val();
	var newSalaJSON=formToJSON(oznakaSale,kapacitet,bioskopId);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/bioskopi/dodajSalu",
		dataType:"json",
		contentType:"application/json",
		data: newSalaJSON,
		success:function(){
			alert("Uspesno ste kreirali salu "+oznakaSale);
			window.location.href="bioskopi.html";
		},
		error: function (data) {
            alert("Greška!");
        }
	});
});
function formToJSON(oznakaSale,kapacitet,bioskopId){
	return JSON.stringify({
		"oznakaSale":oznakaSale,
		"kapacitet":kapacitet,
		"bioskopId":bioskopId
	});
}*/
var id;
$(document).on("submit","form",function(event){
	event.preventDefault();
	var searchParams=new URLSearchParams(window.location.search);
	id=searchParams.get('id');
	var oznakaSale=$("#oznakaSale").val();
	var kapacitet=$("#kapacitet").val();
	var newSalaJSON=formToJSON(oznakaSale,kapacitet);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/bioskopi/dodajSalu/"+id,
		dataType:"json",
		contentType:"application/json",
		data: newSalaJSON,
		success:function(){
			alert("Uspesno ste kreirali salu "+oznakaSale);
			window.location.href="bioskopi.html";
		},
		error: function (data) {
            alert("Greška!");
        }
	});
});
function formToJSON(oznakaSale,kapacitet){
	return JSON.stringify({
		"oznakaSale":oznakaSale,
		"kapacitet":kapacitet
	});
}