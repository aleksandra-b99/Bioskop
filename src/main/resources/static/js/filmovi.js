$(document).ready(function(){
	var kor=$("#jedanFilm");
	kor.hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/filmovi",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['zanr'] + "</td>";
				row +="<td>" + data[i]['ocena'] +"</td>";
				var link="<a class='detalji' id=" + data[i]['id'] + "> detalji </a>";
				row +="<td>" + link +"</td>";
				$('#filmovi').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
});
$(document).on('click','.detalji', function(){
	var korisniciDiv=$("#sviFilmovi");
	korisniciDiv.hide();
	$('#pretraga').hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/filmovi/"+this.id,
		dataType: "json",
		success: function(data){
			console.log("SUCCESS: ",data);
			$('#naziv').append(data['naziv']);
			$('#opis').append(data['opis']);
			$('#zanr').append(data['zanr']);
			$('#ocena').append(data['ocena']);
			$('#trajanje').append(data['trajanje']);
			
			var korisnikDiv=$("#jedanFilm");
			korisnikDiv.show();
		},
		error: function(data){
			console.log("ERROR : ",data);
		}
	});
});
$(document).on('click','#sortNaziv',function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/sortNaziv",
		dataType:"json",
		success:function(data){
			
			console.log("SUCCESS: ",data);
			$('#filmovi td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['zanr'] + "</td>";
				row +="<td>" + data[i]['ocena'] +"</td>";
				var link="<a class='detalji' id=" + data[i]['id'] + "> detalji </a>";
				row +="<td>" + link +"</td>";
				$('#filmovi').append(row);
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on('click','#sortZanr',function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/sortZanr",
		dataType:"json",
		success:function(data){
			
			console.log("SUCCESS: ",data);
			$('#filmovi td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['zanr'] + "</td>";
				row +="<td>" + data[i]['ocena'] +"</td>";
				var link="<a class='detalji' id=" + data[i]['id'] + "> detalji </a>";
				row +="<td>" + link +"</td>";
				$('#filmovi').append(row);
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});
$(document).on('click','#sortOcena',function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/sortOcena",
		dataType:"json",
		success:function(data){
			
			console.log("SUCCESS: ",data);
			$('#filmovi td').remove();
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['naziv'] + "</td>";
				row +="<td>" + data[i]['zanr'] + "</td>";
				row +="<td>" + data[i]['ocena'] +"</td>";
				var link="<a class='detalji' id=" + data[i]['id'] + "> detalji </a>";
				row +="<td>" + link +"</td>";
				$('#filmovi').append(row);
			}
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});