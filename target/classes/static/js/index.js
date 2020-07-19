$(document).ready(function(){
    var ulogovaniKorisnik;
    if(isLoggedIn()){
        $('#login').hide();
        $.ajax({
            //data: {},
            timeout: 1000,
            type: 'GET',
            url: '/api/korisnici/trenutni',

        }).done(function(data, textStatus, jqXHR) {
            ulogovaniKorisnik = data;
            if(ulogovaniKorisnik.uloga==="admin"){
            	$('#korisniciAdmin').show();
            	$('#bioskopiAdmin').show();
            	$('#bioskopiMenadzer').hide();
            }
            if(ulogovaniKorisnik.uloga==="menadzer"){
            	$('#bioskopiMenadzer').show();
            	$('#bioskopiAdmin').hide();
                $('#korisniciAdmin').hide();
            }
            if(ulogovaniKorisnik.uloga==="gledaoc"){
            	$('#bioskopiMenadzer').hide();
                $('#bioskopiAdmin').hide();
                $('#korisniciAdmin').hide();
            }
            
        });      
    }else{
        $('#logout').hide();
        $('#mojProfil').hide();
        $('#bioskopiMenadzer').hide();
        $('#bioskopiAdmin').hide();
        $('#korisniciAdmin').hide();
    }
    
    $('#logout').on('click', function (event){
        $.ajax({
            data: {},
            timeout: 1000,
            type: 'POST',
            url: '/logout'

        }).done(function(data, textStatus, jqXHR) {
            $('#login').show();
            $('#logout').hide();
            $.removeCookie('bioskop', { path: '/' });

        }).fail(function(jqXHR, textStatus, errorThrown) {

            console.error('Help! I cannot get out of here!');
        });
    });

    function isLoggedIn(){//ako postoji cookie to znaci da je logovanje uspesno
        return $.cookie('bioskop');
    }  
});/*
$('#logout').on('click', function (event){
	localStorage.removeItem("korisnik"); 
});*/

$(document).ajaxError(function myErrorHandler(event, xhr, ajaxOptions, thrownError) {
    if (xhr.status === 401) {
        window.location = '/login.html';//ako nije ulogovan
    } else if (xhr.status === 403) {//forbidden
        window.location = '/';
    } else {
        console.error('Postoji problem...');
    }
});
