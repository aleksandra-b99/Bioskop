var id;
$(document).ready(function(){
	var searchParams=new URLSearchParams(window.location.search);
	 id=searchParams.get('id');
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/bioskopi/sala/"+id,
		dataType:"json",
		success:function(data){
			console.log("SUCCCESS: ",data);
			$("#naziv-izmenaSale").val(data['oznakaSale']);
			$("#kap-izmenaSale").val(data['kapacitet']);
		},
		error: function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var oznakaSale=$("#naziv-izmenaSale").val();
	var kapacitet=$("#kap-izmenaSale").val();
	var editSala=formToJSON(oznakaSale,kapacitet);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/bioskopi/izmeneSale/"+id,
		dataType:"json",
		contentType:"application/json",
		data: editSala,
		success:function(){
			alert("Uspesno ste editovali");
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