var id;
$(document).ready(function(){
	var searchParams=new URLSearchParams(window.location.search);
	 id=searchParams.get('id');
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/terminskaLista/detaljiProjekcije/"+id,
		dataType:"json",
		success:function(data){
			console.log("SUCCCESS: ",data);
			$("#nazivFilmaTL-izmena").val(data['nazivFilma']);
			$("#zanrTL-izmena").val(data['zanrFilma']);
			$("#opisFilmaTL-izmena").val(data['opisFilma']);
			$("#trajanjeFilmaTL-izmena").val(data['trajanjeFilma']);
			$("#cenaTL-izmena").val(data['cena']);
			$("#danProjkcijeTL-izmena").val(data['danProjekcije']);
			$("#pocetakTL-izmena").val(data['pocetak']);
			
		},
		error: function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var nazivFilma=$("#nazivFilmaTL-izmena").val();
	var zanrFilma=$("#zanrTL-izmena").val();
	var opisFilma=$("#opisFilmaTL-izmena").val();
	var trajanjeFilma=$("#trajanjeFilmaTL-izmena").val();
	var cena=$("#cenaTL-izmena").val();
	var danProjekcije=$("#danProjkcijeTL-izmena").val();
	var pocetak=$("#pocetakTL-izmena").val();
	
	var editTLizmena=formToJSON(nazivFilma,zanrFilma,opisFilma,trajanjeFilma,cena,pocetak,danProjekcije);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/terminskaLista/izmeni/"+id,
		dataType:"json",
		contentType:"application/json",
		data: editTLizmena,//koristi kontroler
		success:function(){
			alert("Uspesno ste izmenili terminsku listu");
			window.location.href="bioskopi.html";
		},
		error: function (data) {
			console.log("ERROR: ",data);
            alert("Greška!");
        }
	});
});
function formToJSON(nazivFilma,zanrFilma,opisFilma,trajanjeFilma,cena,pocetak,danProjekcije){
	return JSON.stringify({
		"nazivFilma":nazivFilma,
		"zanrFilma":zanrFilma,
		"opisFilma":opisFilma,
		"trajanjeFilma":trajanjeFilma,
		"cena":cena,
		"pocetak":pocetak,
		"danProjekcije":danProjekcije
	});
}