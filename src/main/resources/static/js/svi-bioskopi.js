$(document).ready(function(){
	
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/bioskopi/",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['id'] + "</td>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['adresa'] + "</td>";
				row +="<td>" + data[i]['brojCentrale'] + "</td>"
				row +="<td>" + data[i]['email'] +"</td>";

				var izmeni="<a class='izmeniAdmin' id="+ data[i]['id']+">izmeni</a>"
				row += "<td>"+izmeni+"</td>";
				var obrisi="<a class='obrisiBioskop' id="+ data[i]['id']+">obrisi</a>"
				row += "<td>"+obrisi+"</td>";
				$('#bioskopiAdmin').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
});
$(document).on('click','.obrisiBioskop',function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/bioskopi/obrisi/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			window.location.href="svi-bioskopi.html";
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on('click','.izmeniAdmin',function(){
	window.location.href="izmeneBioskopa.html?id="+this.id;
});
$(document).on('click','#napraviIzmenuBioskopaid',function(event){
	var naziv=$("#naziv-izmena").val();
	var adresa=$("#adresa-izmena").val();
	var email=$("#email-izmena").val();
	var brojCentrale=$("#brojCentrale-izmena").val();
	var idMenadzera=$("#idMenadzera-izmena").val();
	var newBioskop1JSON=formToJSON(naziv,adresa,email,brojCentrale,idMenadzera);
	$.ajax({
		type:"POST",
		url: "http://localhost:8080/api/bioskopi/izmene/"+this.id,
		dataType:"json",
		contentType:"application/json",
		data: newBioskop1JSON,
		success:function(){
			console.log("SUCCESS: ");
			alert("jee");
			window.location.href="svi-bioskopi.html";
		},
		error:function(){
			console.log("ERROR: ");
			alert("greska");
		}
	});
});
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var email=$("#email").val();
	var brojCentrale=$("#brojCentrale").val();
	var idMenadzera=$("#idMenadzera").val();
	var newBioskopJSON=formToJSON(naziv,adresa,email,brojCentrale,idMenadzera);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/bioskopi",
		dataType:"json",
		contentType:"application/json",
		data: newBioskopJSON,
		success:function(){
			alert("Uspesno ste kreirali bioskop "+naziv);
			window.location.href="svi-bioskopi.html";
		},
		error: function (data) {
            alert("Ne postoji menadzer sa upisanim id-jem. \nPodseti se u listi korisnika :)");
            window.location.href="korisnici.html";
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