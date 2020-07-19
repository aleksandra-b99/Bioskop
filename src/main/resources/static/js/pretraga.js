/*$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv1").val();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/naziv/"+naziv,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			//alert("Trazimo film "+naziv);
			//window.location.href="filmovi.html";
			$('#filmovi td').remove();
			var row="<tr>";
			row +="<td>" + data['naziv'] + "</td>";
			row +="<td>" + data['zanr'] + "</td>";
			row +="<td>" + data['ocena'] +"</td>";
			var link="<a class='detalji' id=" + data['id'] + "> detalji </a>";
			row +="<td>" + link +"</td>";
			$('#filmovi').append(row);
		},
		error: function () {
            alert("Greška!");
        }
	});
});
$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var zanr=$("#zanr1").val();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/zanr/"+zanr,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			//alert("Trazimo film "+naziv);
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
		error: function () {
            alert("Greška!");
        }
	});
});*/
/*$(document).on('click','.btn2',function(){
  //Select the parent form and submit
  $(this).parent("form").submit();
  var zanr=$("#zanr1").val();
			$.ajax({
				type:"GET",
				url:"http://localhost:8080/api/filmovi/zanr/"+zanr,
				dataType:"json",
				success:function(data){
					console.log("SUCCESS: ",data);
					//alert("Trazimo film "+naziv);
					$('#filmovi td').remove();
					for(i=0;i<data.length;i++){
						var row="<tr>";
						row +="<td>" + data['naziv'] + "</td>";
						row +="<td>" + data['zanr'] + "</td>";
						row +="<td>" + data['ocena'] +"</td>";
						var link="<a class='detalji' id=" + data['id'] + "> detalji </a>";
						row +="<td>" + link +"</td>";
						$('#filmovi').append(row);
					}
				},
				error: function () {
		            alert("Greška!");
		        }
			});

});*//*
$(document).on('click','.btn1',function(){
	var zanr=$("#zanr1").val();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/zanr/"+zanr,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			//alert("Trazimo film "+naziv);
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
		error: function (data) {
			console.log("ERROR: ",data);
            alert("Greška!");
        }
	});
});*/
/*$(document).on('click','.btn1',function(){
	var naziv=$("#naziv1").val();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/naziv/"+naziv,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			alert("Trazimo film "+naziv);
			$('#filmovi td').remove();
			var row="<tr>";
			row +="<td>" + data['naziv'] + "</td>";
			row +="<td>" + data['zanr'] + "</td>";
			row +="<td>" + data['ocena'] +"</td>";
			var link="<a class='detalji' id=" + data['id'] + "> detalji </a>";
			row +="<td>" + link +"</td>";
			$('#filmovi').append(row);
		},
		error: function (data) {
			console.log("ERROR: ",data);
            alert("Greška!");
        }
	});
});
$(document).on('click','.btn2',function(){
	var zanr=$("#zanr1").val();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/zanr/"+zanr,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			alert("Trazimo zanr "+zanr);
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
		error: function (data) {
			console.log("ERROR: ",data);
            alert("Greška!");
        }
	});
});
$(document).on('click','.btn3',function(){
	var ocena=$("#ocena1").val();
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi/ocena/"+ocena,
		dataType:"json",
		success:function(data){
			console.log("SUCCESS: ",data);
			alert("Trazimo filmove sa ocenom "+ocena);
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
		error: function (data) {
			console.log("ERROR: ",data);
            alert("Greška!");
        }
	});
});
