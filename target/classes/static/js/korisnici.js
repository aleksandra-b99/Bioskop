$(document).ready(function(){
	$("#jedanKorisnik").hide();
	$("#rezervisaneProjekcijeGledaoca").hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['id'] + "</td>";
				row +="<td>" + data[i]['ime'] + "</td>";
				row +="<td>" + data[i]['prezime'] + "</td>";
				row +="<td>" + data[i]['uloga'] +"</td>";
				row +="<td>" + data[i]['aktivan'] +"</td>";
				
				var link="<a class='profil' id=" + data[i]['id'] + "> profil </a>";
				row +="<td>" + link +"</td>";
				var aktiviraj="<a class='aktiviraj' id="+ data[i]['id']+">aktiviraj</a>"
				row += "<td class='aktiviraj-kolona'>"+aktiviraj+"</td>";
				var deaktiviraj="<a class='deaktiviraj' id="+ data[i]['id']+">deaktiviraj</a>"
				row += "<td class='deaktiviraj-kolona'>"+deaktiviraj+"</td>";
				var ukloni="<a class='ukloni' id="+ data[i]['id']+">ukloni</a>"
				row += "<td class='ukloni-kolona'>"+ukloni+"</td>";
				$('#korisnici').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});

	if($.cookie('bioskop')){
		$.ajax({
			data: {},
			timeout: 1000,
			type: 'GET',
			url: '/api/korisnici/trenutni'

		}).done(function(data, textStatus, jqXHR) {
			if(data.uloga === "gledaoc"){
				sakrijPoljaZaGledaoca();
			}
			if(data.uloga === "menadzer"){
				sakrijPoljaZaGledaoca();
			}
		})
	}

	function sakrijPoljaZaGledaoca(){
		$(".aktiviraj-kolona").hide();
		$(".deaktiviraj-kolona").hide();
		$(".ukloni-kolona").hide();
		$("#registrujse").hide();
		$("#gledaoc").hide();
		$("#menadzer").hide();
	}
});

$(document).on('click','.profil', function(){
	var korisniciDiv=$("#sviKorisnici");
	korisniciDiv.hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/"+this.id,
		dataType: "json",
		success: function(data){
			console.log("SUCCESS: ",data);
			$('#ime').append(data['ime']);
			$('#prezime').append(data['prezime']);
			$('#uloga').append(data['uloga']);
			$('#lozinka').append(data['lozinka']);
			$('#aktivan').append(data['aktivan']);
			$('#datumRodjenja').append(data['datumRodjenja']);
			$('#korisnickoIme').append(data['korisnickoIme']);
			$('#kontaktTelefon').append(data['kontaktTelefon']);
			$('#eadresa').append(data['eadresa']);
			
			var korisnikDiv=$("#jedanKorisnik");
			korisnikDiv.show();
			//window.location.href="korisnici.html";
		},
		error: function(data){
			console.log("ERROR : ",data);
		}
	});
});

$(document).on('click','#pregledSvihRezervisanihFilmova',function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/korisnici/pregledSvihRezervisanihKarata",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			$('#rezervisaneProjekcijetabela2 td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['nazivFilma'] + "</td>";
				row +="<td>"+data[i]['zanrFilma'] + "</td>";
				row +="<td>"+data[i]['nazivSale']+"</td>";
				row +="<td>" + data[i]['danProjekcije'] + "</td>";
				row +="<td>" + data[i]['pocetak'] + "</td>";
				row +="<td>" + data[i]['cena'] +"</td>";
				var ukloniRezervaciju="<a class='ukloniRezervaciju' id=" + data[i]['id'] + "> ukloniRezervaciju </a>";
				row +="<td>" + ukloniRezervaciju +"</td>";
				$('#rezervisaneProjekcijetabela2').append(row);
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on('click','.deaktiviraj', function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/deaktiviraj/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			$('#aktivan').text(false);
			window.location.href="korisnici.html";
		},
		error: function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on('click','.aktiviraj', function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/aktiviraj/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			$('#aktivan').text(true);
			window.location.href="korisnici.html";
		},
		error: function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on('click','.ukloni',function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/obrisi/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			window.location.href="korisnici.html";
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on("submit","form",function (event){
	event.preventDefault();
	var ime=$("#ime").val();
	var prezime=$("#prezime").val();
	var uloga=$("#uloga").val();
	var lozinka=$("#lozinka").val();
	var korisnickoIme=$("#korisnickoIme").val();
	//var aktivan=$("#aktivan").val();
	var kontaktTelefon=$("#kontaktTelefon").val();
	var datumRodjenja=$("#datumRodjenja").val();
	var eadresa=$("#eadresa").val();

	if(!obaveznaPoljaPopunjena()) {
		return;
	}

	var newKorisnikJSON=formToJSON(ime,prezime,uloga,lozinka,korisnickoIme,false,kontaktTelefon,datumRodjenja,eadresa);
	$.ajax({
		type:"POST",
		url: "http://localhost:8080/api/korisnici",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success: function(data){
			alert(ime+" "+prezime+" je uspesno kreiran kao "+ uloga);
			window.location.href="index.html";
		},
		error: function(requestObject, error, errorThrown){
			if (requestObject.status === 409){
				alert("Korisnicko ime " + korisnickoIme + " vec postoji!");//ovde hvatam exception, pri stvaranju novog korisnika 
			}else{
				alert("Greska!");
			}
		}
	});
});
function formToJSON(ime,prezime,uloga,lozinka,korisnickoIme,aktivan,kontaktTelefon,datumRodjenja,eadresa){
	return JSON.stringify({
		"ime":ime,
		"prezime":prezime,
		"uloga":uloga,
		"lozinka":lozinka,
		"korisnickoIme":korisnickoIme,
		"aktivan":aktivan,
		"kontaktTelefon":kontaktTelefon,
		"datumRodjenja":datumRodjenja,
		"eadresa":eadresa
	});
}

function obaveznaPoljaPopunjena(){
	var errors = [];
	if(korisnickoIme.value == ""){
		errors.push("Korisnicko ime");
	}
	if(lozinka.value == ""){
		errors.push("Lozinka");
	}
	if(ime.value == ""){
		errors.push("Ime");
	}
	if(prezime.value == ""){
		errors.push("Prezime");
	}
	if(uloga.value != "gledaoc" && uloga.value != "menadzer"){
		errors.push("Uloga");
	}
	if(errors.length != 0){
		var message = 'Sledeca obavezna polja nisu popunjena:'
		for(var error of errors){
			message +='\n' + error;
		}
		alert(message);
		return false;
	}
	return true;

}
$(document).on('click','#menadzer',function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/menadzeri",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			$('#korisnici td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['id'] + "</td>";
				row +="<td>" + data[i]['ime'] + "</td>";
				row +="<td>" + data[i]['prezime'] + "</td>";
				row +="<td>" + data[i]['uloga'] +"</td>";
				row +="<td>" + data[i]['aktivan'] +"</td>";
				
				var link="<a class='profil' id=" + data[i]['id'] + "> profil </a>";
				row +="<td>" + link +"</td>";
				var aktiviraj="<a class='aktiviraj' id="+ data[i]['id']+">aktiviraj</a>"
				row += "<td class='aktiviraj-kolona'>"+aktiviraj+"</td>";
				var deaktiviraj="<a class='deaktiviraj' id="+ data[i]['id']+">deaktiviraj</a>"
				row += "<td class='deaktiviraj-kolona'>"+deaktiviraj+"</td>";
				var ukloni="<a class='ukloni' id="+ data[i]['id']+">ukloni</a>"
				row += "<td class='ukloni-kolona'>"+ukloni+"</td>";
				$('#korisnici').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
});
$(document).on('click','#gledaoc',function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/gledaoci",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			$('#korisnici td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['id'] + "</td>";
				row +="<td>" + data[i]['ime'] + "</td>";
				row +="<td>" + data[i]['prezime'] + "</td>";
				row +="<td>" + data[i]['uloga'] +"</td>";
				row +="<td>" + data[i]['aktivan'] +"</td>";
				
				var link="<a class='profil' id=" + data[i]['id'] + "> profil </a>";
				row +="<td>" + link +"</td>";
				var aktiviraj="<a class='aktiviraj' id="+ data[i]['id']+">aktiviraj</a>"
				row += "<td class='aktiviraj-kolona'>"+aktiviraj+"</td>";
				var deaktiviraj="<a class='deaktiviraj' id="+ data[i]['id']+">deaktiviraj</a>"
				row += "<td class='deaktiviraj-kolona'>"+deaktiviraj+"</td>";
				var ukloni="<a class='ukloni' id="+ data[i]['id']+">ukloni</a>"
				row += "<td class='ukloni-kolona'>"+ukloni+"</td>";
				$('#korisnici').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
});