var id;
$(document).ready(function(){
	var searchParams=new URLSearchParams(window.location.search);
	 id=searchParams.get('id');
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/sale/"+id,
		dataType:"json",
		success:function(data){
			console.log("SUCCCESS: ",data);
			$("#nazivSaleTLnaslov").val(data['oznakaSale']);
		},
		error: function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var nazivFilma=$("#nazivFilmaTL").val();
	var zanrFilma=$("#zanrTL").val();
	var opisFilma=$("#opisFilmaTL").val();
	var trajanjeFilma=$("#trajanjeFilmaTL").val();
	var cena=$("#cenaTL").val();
	var danProjekcije=$("#danProjkcijeTL").val();
	var pocetak=$("#pocetakTL").val();
	
	var editTL=formToJSON(nazivFilma,zanrFilma,opisFilma,trajanjeFilma,cena,pocetak,danProjekcije);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/terminskaLista/"+id,
		dataType:"json",
		contentType:"application/json",
		data: editTL,//koristi kontroler
		success:function(data){
			console.log("SUCCES: ",data);
			alert("Uspesno ste kreirali terminsku listu");
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