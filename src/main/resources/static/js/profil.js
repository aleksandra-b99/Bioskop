$(document).ready(function(){
	var trenutni;
	$('#rezervisaneProjekcijeGledaoca').hide();
	$('#odgledaniFilmovi').hide();
	$('#ocenjeniOdgledaniFilmovi').hide();
	$('#sviOdgledaniFilmovi').hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/korisnici/mojprofil",
		dataType: "json",
		success: function(data){
			console.log("SUCCESS: ",data);
			$('#imeProfil').append(data['ime']);
			$('#prezimeProfil').append(data['prezime']);
			$('#ulogaProfil').append(data['uloga']);
			$('#aktivanProfil').append(data['aktivan']);
			$('#datumRodjenjaProfil').append(data['datumRodjenja']);
			$('#korisnickoImeProfil').append(data['korisnickoIme']);
			$('#kontaktTelefonProfil').append(data['kontaktTelefon']);
			$('#eadresaProfil').append(data['eadresa']);
			if(data.uloga==="admin"){
				$('#pregledSvihRezervisanihFilmova').hide();
				$('#sviOdgledaniFilmoviNEdaOCENA').hide();
				$('#pogledaniFilmoviSaOcenom').hide();
				$('#pogledaniFilmoviBezOcene').hide();
			}
			if(data.uloga==="menadzer"){
				$('#pregledSvihRezervisanihFilmova').hide();
				$('#sviOdgledaniFilmoviNEdaOCENA').hide();
				$('#pogledaniFilmoviSaOcenom').hide();
				$('#pogledaniFilmoviBezOcene').hide();
			}
		},
		error: function(data){
			console.log("ERROR : ",data);
		}
	});
});

$(document).on('click','#pogledaniFilmoviSaOcenom',function(){
	$('#rezervisaneProjekcijeGledaoca').hide();
	$('#odgledaniFilmovi').hide();
	$('#sviOdgledaniFilmovi').hide();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/korisnici/ocenjeniFilmovi/",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS : ",data);
			$('#ocenjeniOdgledaniFilmovitabela td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>"+data[i]['ocena'] + "</td>";
				$('#ocenjeniOdgledaniFilmovitabela').append(row);
				$('#ocenjeniOdgledaniFilmovi').show();
			}
			
		},
		error:function(data){
			console.log("ERROR : ",data);
		}
	});
});
$(document).on('click','#pregledSvihRezervisanihFilmova',function(){
	$('#ocenjeniOdgledaniFilmovi').hide();
	$('#odgledaniFilmovi').hide();
	$('#sviOdgledaniFilmovi').hide();
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
				var kupi="<a class='kupi' id=" + data[i]['id'] + "> kupi</a>";
				row +="<td>" +kupi +"</td>";
				$('#rezervisaneProjekcijetabela2').append(row);
				$('#rezervisaneProjekcijeGledaoca').show();
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
	
});
$(document).on('click','#sviOdgledaniFilmoviNEdaOCENA',function(){
	$('#rezervisaneProjekcijeGledaoca').hide();
	$('#ocenjeniOdgledaniFilmovi').hide();
	$('#odgledaniFilmovi').hide();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/korisnici/sviOdgledaniFilmovi",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			$('#sviOdgledaniFilmovitabela td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>"+data[i]['zanr'] + "</td>";
				$('#sviOdgledaniFilmovitabela').append(row);
				$('#sviOdgledaniFilmovi').show();
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
	
});
$(document).on('click','#pogledaniFilmoviBezOcene',function(){
	$('#rezervisaneProjekcijeGledaoca').hide();
	$('#ocenjeniOdgledaniFilmovi').hide();
	$('#sviOdgledaniFilmovi').hide();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/korisnici/odgledaniFilmovi",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			$('#odgledaniFilmovitabela td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['zanr'] + "</td>";
				var oceni="<a class='oceni' id=" + data[i]['id'] + "> oceni </a>";
				row +="<td>" +oceni+"</td>";
				$('#odgledaniFilmovitabela').append(row);
				$('#odgledaniFilmovi').show();
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
	
});
$(document).on('click','.oceni',function(){
	window.location.href="oceni-film.html?id="+this.id;
});
$(document).on('click','.ukloniRezervaciju',function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/korisnici/ukloniRezervaciju/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			window.location.href="profil.html";
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on('click','.kupi',function(){
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/korisnici/kupiKartu/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			var row="<tr>";
			row +="<td>" + data['naziv'] + "</td>";
			$('#odgledaniFilmovitabela').append(row);
			alert("Uspesno ste kupili kartu za film "+data['naziv']);
			window.location.href="profil.html";
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
});