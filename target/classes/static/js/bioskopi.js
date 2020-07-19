$(document).ready(function(){
	var t=$("#sveSaleUbioskopu");
	t.hide();
	$("#sveTerminskeListeUsali").hide();
	
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/bioskopi/bioskopiMenadzera",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			$('#sale td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['id'] + "</td>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['adresa'] + "</td>";
				row +="<td>" + data[i]['brojCentrale'] + "</td>"
				row +="<td>" + data[i]['email'] +"</td>";
				
				var sale="<a class='saleBioskopa' id=" + data[i]['id'] + "> sale </a>";
				row +="<td>" + sale +"</td>";
				var dodajSalu="<a class='dodaj' id=" + data[i]['id'] + "> dodaj </a>";
				row +="<td>" + dodajSalu +"</td>";
				$('#bioskopi').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
});
$(document).on('click','.saleBioskopa',function(){
	var s=$("#sviBioskopi");
	s.hide();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/bioskopi/sale/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			//$('#sale td').remove();
			for(i=0;i<data.length;i++){
				
				var row="<tr>";
				row +="<td>"+data[i]['id'] + "</td>";
				row +="<td>"+data[i]['oznakaSale'] + "</td>";
				row +="<td>"+data[i]['kapacitet'] + "</td>";
				var termini="<a class='termini' id="+ data[i]['id']+">termisnka lista</a>"
				row += "<td>"+termini+"</td>";
				var kreirajTL="<a class='kreirajTL' id=" + data[i]['id'] + "> kreiraj </a>";
				row +="<td>" + kreirajTL +"</td>";
				var obrisi="<a class='obrisiSalu' id="+ data[i]['id']+">obrisi</a>"
				row += "<td>"+obrisi+"</td>";
				var izmeni="<a class='izmeniSalu' id="+ data[i]['id']+">izmeni</a>"
				row += "<td>"+izmeni+"</td>";
				$('#sale').append(row);
				
			}
			var p=$("#sveSaleUbioskopu");
			p.show();
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on('click','.termini',function(){
	$("#sveSaleUbioskopu").hide();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/bioskopi/sale/terminskaLista/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>"+data[i]['nazivFilma'] + "</td>";
				row +="<td>"+data[i]['zanrFilma'] + "</td>";
				row +="<td>"+data[i]['danProjekcije'] + "</td>";
				row +="<td>"+data[i]['pocetak'] + "</td>";
				row +="<td>"+data[i]['cena'] + "</td>";
				var izmeni="<a class='izmeniTL' id="+ data[i]['id']+">izmeni</a>"
				row += "<td>"+izmeni+"</td>";
				$('#terminskeListeUsali').append(row);
				
			}
			var p=$("#sveTerminskeListeUsali");
			p.show();
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on('click','.obrisiSalu',function(){
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/sale/obrisi/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			//window.location.href="sale.html";
			window.location.href="bioskopi.html";
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
	});
});
$(document).on("submit","form",function(event){
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
}
$(document).on('click','.izmeniSalu',function(){
	window.location.href="izmenaSale.html?id="+this.id;
});
$(document).on('click','.dodaj',function(){
	window.location.href="dodaj-salu.html?id="+this.id;
});
$(document).on('click','.kreirajTL',function(){
	window.location.href="kreiraj-TerminskuListu.html?id="+this.id;
});
$(document).on('click','.izmeniTL',function(){
	window.location.href="izmeni-TerminskuListu.html?id="+this.id;
});























