var formAScomparsa = '.form_a_scomparsa';


//bottone per editare email e pass
$(document).ready(function () {
    $('input.profile-edit-btn').click(function () {
        $(this).toggleClass("active");
        $(formAScomparsa).toggleClass("d-none");
    });
});

//storico noelggi
$(document).ready(function () {
    $('#profile-tab').click(function () {
        $('input.profile-edit-btn').addClass("d-none");
    });
});


//info utente
$(document).ready(function () {
    $('#home-tab').click(function () {
        $('input.profile-edit-btn').removeClass("d-none");
    });
});

//bottone conferma cambio mail
$(document).ready(function () {
    $("#newMail, #newMailConferma").on("keyup", function () {
        var t1 = $("#newMail").val();
        var t2 = $("#newMailConferma").val();


        if (String(t1) == String(t2) && String(t1) != "" && (t1).includes("@") && (t1).length > 5) {
            $('input.btn.btn-primary.mail').removeClass("disabled");
        }else {
            $('input.btn.btn-primary.mail').addClass("disabled");
        }
    });
});

//bottone congferma cambio password
$(document).ready(function () {
    $("#newPass, #newPassConferma").on("keyup", function () {
        var t1 = $("#newPass").val();
        var t2 = $("#newPassConferma").val();


        if (String(t1) == String(t2) && String(t1) != "") {

            $('input.btn.btn-primary.pass').removeClass("disabled");
        }else {
            $('input.btn.btn-primary.pass').addClass("disabled");
        }
    });
});

$(document).ready(function (){
    $('input.btn.btn-primary.mail').click(function (){
        alert("Email cambiata con successo");
        $('input.btn.btn-primary.mail').addClass("disabled");
    })
})




