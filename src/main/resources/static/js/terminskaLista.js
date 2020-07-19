$(document).ready(function(){
	$("#rezervacijahtml").hide();
	$("#rezervisaneProjekcije").hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/terminskaLista",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			/*var x = localStorage.getItem("korisnik"); 
			$.ajax({
				type:"GET",
				url:"http://localhost:8080/api/korisnici/"+x,
				dataType:"json",
				success:function(data1){
					for(i=0;i<data.length;i++){
						var row="<tr>";
						row +="<td>" + data[i]['nazivFilma'] + "</td>";
						row +="<td>"+data[i]['zanrFilma'] + "</td>";
						row +="<td>" + data[i]['danProjekcije'] + "</td>";
						row +="<td>" + data[i]['pocetak'] + "</td>";
						row +="<td>" + data[i]['cena'] +"</td>";
						var vise="<a class='detaljiOprojekciji' id=" + data[i]['id'] + "> vise </a>";
						row +="<td>" + vise+"</td>";
						
						var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
						row +="<td>" + rezervisi +"</td>";
						$('#terminskeListe').append(row);
					}
				}
			});*/
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['nazivFilma'] + "</td>";
				row +="<td>"+data[i]['zanrFilma'] + "</td>";
				row +="<td>" + data[i]['danProjekcije'] + "</td>";
				row +="<td>" + data[i]['pocetak'] + "</td>";
				row +="<td>" + data[i]['cena'] +"</td>";
				var vise="<a class='detaljiOprojekciji' id=" + data[i]['id'] + "> vise </a>";
				row +="<td>" + vise+"</td>";
				
				var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
				row +="<td>" + rezervisi +"</td>";
				$('#terminskeListe').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
	//ne radi
	if($.cookie('bioskop')){
		$.ajax({
			data: {},
			timeout: 1000,
			type: 'GET',
			url: '/api/korisnici/trenutni'

		}).done(function(data, textStatus, jqXHR) {
			if(data.uloga === "admin"){
				$("#rezervisi-kolona").hide();	
			}
			if(data.uloga === "menadzer"){
				$("#rezervisi-kolona").hide();
			}
		})
	}
});

$(document).on('click','.detaljiOprojekciji', function(){
	$("#sveTerminskeListe").hide();
	$('#pretraga').hide();
	$('#jumbotron').hide();
	$("#rezervisaneProjekcije").hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/terminskaLista/detaljiProjekcije/"+this.id,
		dataType: "json",
		success: function(data){
			console.log("SUCCESS: ",data);
			$('#film').append(data['nazivFilma']);
			$('#ocenaFilma').append(data['ocena']);
			$('#opis').append(data['opisFilma']);
			$('#zanrFilma').append(data['zanrFilma']);
			$('#rezervisanoMesta').append(data['brojRezervacija']);
			$('#ukupnoMesta').append(data['kapacitetSale']);
			$('#salaUkojojJeProjekcija').append(data['nazivSale']);
			$('#pocetak').append(data['pocetak']);
			$('#datum').append(data['danProjekcije']);
			$("#rezervacijahtml").show();
		},
		error: function(data){
			console.log("ERROR : ",data);
		}
	});
});

$(document).on('click','.rezervisi',function(){
	$("#sveTerminskeListe").hide();
	$("#rezervacijahtml").hide();
	$('#pretraga').hide();
	$('#jumbotron').hide();
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/korisnici/rezervacijaKarte/"+this.id,
		dataType:"json",
		contentType:"application/json",
		success: function(data){
			console.log("SUCCESS: ", data);
			alert("Uspesno ste rezervisali film "+data['nazivFilma']+". Datum projekcije je "+data['danProjekcije']+",a vreme je" +data['pocetak']);
			window.location.href="terminskaLista.html";
		},
		error: function(requestObject, error, errorThrown,data){
			if (requestObject.status === 403){
				alert("Popunjena su mesta pa nije moguce rezervisati kartu! Pokusaj neki drugi termin");
				window.location.href="terminskaLista.html";
			}else{
				alert("Onemogucena je rezervacija \nNiste ulogovani kao gledaoc");
				window.location.href="terminskaLista.html";
			}
		}
	});
});/*
$(document).on('click','#pretrazi',function(){
	  var naziv=$("#naziv1").val();
				$.ajax({
					type:"GET",
					url:"http://localhost:8080/api/terminskaLista/naziv/"+naziv,
					dataType:"json",
					success:function(data){
						console.log("SUCCESS: ",data);
						alert("Trazimo film "+naziv);
						$('#terminskeListe td').remove();
						for(i=0;i<data.length;i++){
							var row="<tr>";
							row +="<td>" + data[i]['nazivFilma'] + "</td>";
							row +="<td>" + data[i]['zanrFilma'] + "</td>";
							row +="<td>" + data[i]['danProjekcije'] +"</td>";
							row +="<td>" + data[i]['pocetak'] +"</td>";
							row +="<td>" + data[i]['cena'] +"</td>";
							var vise="<a class='detaljiOprojekciji' id=" + data['id'] + "> vise </a>";
							row +="<td>" + vise+"</td>";
							var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
							row +="<td>" + rezervisi +"</td>";
							$('#terminskeListe').append(row);
						}
					},
					error: function () {
			            alert("Greška!");
			        }
				});
});/*
$(document).on('click','#pretrazi',function(){
	  var naziv=$("#naziv1").val();
	  var zanr=$("#zanr1").val();
				$.ajax({
					type:"GET",
					url:"http://localhost:8080/api/terminskaLista/naziv/"+naziv +"/"+ zanr,
					dataType:"json",
					success:function(data){
						console.log("SUCCESS: ",data);
						alert("Trazimo film "+naziv+", zanr: "+zanr);
						$('#terminskeListe td').remove();
						for(i=0;i<data.length;i++){
							var row="<tr>";
							row +="<td>" + data[i]['nazivFilma'] + "</td>";
							row +="<td>" + data[i]['zanrFilma'] + "</td>";
							row +="<td>" + data[i]['danProjekcije'] +"</td>";
							row +="<td>" + data[i]['pocetak'] +"</td>";
							row +="<td>" + data[i]['cena'] +"</td>";
							var vise="<a class='detaljiOprojekciji' id=" + data['id'] + "> vise </a>";
							row +="<td>" + vise+"</td>";
							var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
							row +="<td>" + rezervisi +"</td>";
							$('#terminskeListe').append(row);
						}
					},
					error: function () {
			            alert("Greška!");
			        }
				});
});*/
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var nazivFilma=$("#naziv1").val();
	var zanrFilma=$("#zanr1").val();
	var cena=$("#cena1").val();
	var danProjekcije=$("#danProjkcije1").val();
	var pocetak=$("#pocetak1").val();
	
	var pretragaTL=formToJSON(pocetak,cena,nazivFilma,danProjekcije,zanrFilma);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/terminskaLista/pretraga",
		dataType:"json",
		contentType:"application/json",
		data: pretragaTL,//koristi kontroler
		success:function(data){
			alert("pretrazujemo film...");
			console.log("SUCCESS: ",data);
			$('#terminskeListe td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['nazivFilma'] + "</td>";
				row +="<td>" + data[i]['zanrFilma'] + "</td>";
				row +="<td>" + data[i]['danProjekcije'] +"</td>";
				row +="<td>" + data[i]['pocetak'] +"</td>";
				row +="<td>" + data[i]['cena'] +"</td>";
				var vise="<a class='detaljiOprojekciji' id=" + data['id'] + "> vise </a>";
				row +="<td>" + vise+"</td>";
				var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
				row +="<td>" + rezervisi +"</td>";
				$('#terminskeListe').append(row);
			}
		},
		error: function (data) {
			console.log("ERROR: ",data);
            alert("Greška! Trazena konstrukcija ne postoji.");
        }
	});
});
function formToJSON(pocetak,cena,nazivFilma,danProjekcije,zanrFilma){
	return JSON.stringify({
		"nazivFilma":nazivFilma,
		"zanrFilma":zanrFilma,
		"cena":cena,
		"danProjekcije":danProjekcije,
		"pocetak":pocetak
	});
}
$(document).on('click','#sortCena',function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/terminskaLista/sortCena",
		dataType:"json",
		success:function(data){
			
			console.log("SUCCESS: ",data);
			$('#terminskeListe td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['nazivFilma'] + "</td>";
				row +="<td>" + data[i]['zanrFilma'] + "</td>";
				row +="<td>" + data[i]['danProjekcije'] +"</td>";
				row +="<td>" + data[i]['pocetak'] +"</td>";
				row +="<td>" + data[i]['cena'] +"</td>";
				var vise="<a class='detaljiOprojekciji' id=" + data['id'] + "> vise </a>";
				row +="<td>" + vise+"</td>";
				var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
				row +="<td>" + rezervisi +"</td>";
				$('#terminskeListe').append(row);
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
