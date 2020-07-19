jQuery(document).ready(function ($) {
    $('#loginform').submit(function (event) {
        event.preventDefault();
        var data = 'username=' + $('#korisnickoIme').val() + '&password=' + $('#lozinka').val();
        $.ajax({
            data: data,
            timeout: 1000,
            type: 'POST',
            url: '/login'
        }).done(function(data) {
            // var preLoginInfo = JSON.parse($.cookie('dashboard.pre.login.request'));
            // window.location = preLoginInfo.url;
            $.cookie('bioskop', '123');//pravim svoj kuki radi provere
        	//localStorage.setItem("korisnik", data.id);
            alert('Uspesno ste se ulogovali \nDobro dosli!')
            window.location = "/"
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert('Niste uneli tacnu lozinku i korisnicko ime ili niste jos aktivirani od strane admina');
        });
    });
});