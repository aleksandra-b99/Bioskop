$(document).ready(function(){
	var pe=$("#sveTerminskeListeUsali");
	pe.hide();
	$.ajax({
		type:"GET",
		url: "http://localhost:8080/api/sale",
		dataType:"json",
		success: function(data){
			console.log("SUCCESS: ", data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>" + data[i]['id'] + "</td>";
				row +="<td>" + data[i]['oznakaSale'] + "</td>";
				row +="<td>" + data[i]['kapacitet'] + "</td>";

				var termini="<a class='termini' id="+ data[i]['id']+">termisnka lista</a>"
				row += "<td>"+termini+"</td>";
				var izmeni="<a class='izmeniSalu' id="+ data[i]['id']+">izmeni</a>"
				row += "<td>"+izmeni+"</td>";
				var obrisi="<a class='obrisiSalu' id="+ data[i]['id']+">obrisi</a>"
				row += "<td>"+obrisi+"</td>";
				$('#salee').append(row);
			}
		},
		error: function (data){
			console.log("ERROR: ", data);
		}
	});
});/*
$(document).on('click','.termini',function(){
	$('#sveSalee').hide();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/sale/terminskaLista/"+this.id,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
				row +="<td>"+data[i]['nazivFilma'] + "</td>";
				row +="<td>"+data[i]['danProjekcije'] + "</td>";
				row +="<td>"+data[i]['pocetak'] + "</td>";
				row +="<td>"+data[i]['cena'] + "</td>";
				row +="<td>"+data[i]['brojRezervacija'] + "</td>";
				var rezervisi="<a class='rezervisi' id=" + data[i]['id'] + "> rezervisi </a>";
				row +="<td>" + rezervisi +"</td>";
				$('#terminskeListeUsali').append(row);
				
			}
			var p=$("#sveTerminskeListeUsali");
			p.show();
		},
		error:function(data){
			console.log("ERROR: ",data);
		}
		
	});
});*/
