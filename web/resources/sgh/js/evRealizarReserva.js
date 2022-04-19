//var obj
$(function() {
    $('select').material_select();
    $('.collapsible').collapsible({
        accordion: false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
                //console.log(this);
    });

    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 4, // Creates a dropdown of 15 years to control year
        min: new Date(),
        closeOnSelect: true

    });


//    function salvarReservaLocal() {
//    
//    expandableOpen(object)
//    expandableOpen(object);
//    }


    $(".btAdicionarReserva").click(function() {

    });

//    $("#comboTipoAmb select").attr('disabled', 'true');
//    $("#inputData input").attr('disabled', 'true');
//    $("#inputHorarios input").attr('disabled', 'true');
//    $("#comboLocal select").on('change', function() {
//        $("#comboTipoAmb select").removeAttr('disabled');
//        $("#comboTipoAmb select").attr('disabled', 'false');
//    });
});


function selecionarAmbiente(elSelecionado) {
    var selAnterior = $("#lista").find("a.active");
    selAnterior.removeClass("active");
    $(elSelecionado).addClass("active");
}
$(function() {

    var texto = $(".btSalvarReserva input[type=hidden]").val();
    console.log(texto);

    if (texto == "Sucesso") {
        Materialize.toast("Reserva salva com sucesso", 4000);
        $(".toast").css("background-color", "green");
    } else if (texto == "Erro") {
        Materialize.toast("Ocorreu um erro ao salvar a reserva! Contate o administrador do sistema!", 4000);
        $(".toast").css("background-color", "red");
    } else {
        Materialize.toast(texto, 4000);
        $(".toast").css("background-color", "amber");
    }

});

