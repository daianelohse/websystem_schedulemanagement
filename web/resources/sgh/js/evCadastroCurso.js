var semestresAtivos;
$(document).ready(function() {
    $('select').material_select();
    semestresAtivos = 1;
    for (i = 1; i < 11; i++) {
        $("#btFecharUc" + semestresAtivos).fadeOut();
    }
});

function addSemestre() {
    if (semestresAtivos < 11) {
        $("#btFecharUc" + semestresAtivos).fadeIn();
        $("#btFecharUc" + (semestresAtivos - 1)).fadeOut();
        $("#sem" + semestresAtivos).fadeIn();      
        semestresAtivos++;
    } else {
        $("#menAddSem").show();
        $("#btAdicionarSemestre").css("pointer-events", "none");
        $("#btAdicionarSemestre").removeClass("red").addClass("grey");
    }
}

function habilitarAddSemestre() {
    var val = $("#modalidade select").val();
    if (val === "") {
        $("#btAdicionarSemestre").addClass("disabled");
        desabilitarDivsUc()
    } else {
        $("#btAdicionarSemestre").removeClass("disabled");
    }
}

function desabilitarDivsUc() {

    for (var i = 0; i < 11; i++) {
        $("#sem" + i).fadeOut();
    }
    semestresAtivos = 1;
}

function fecharUc(number) {
    $("#sem" + number).fadeOut();
    $("#btFecharUc" + (number - 1)).fadeIn();
    $("#btFecharUc" + (number + 1)).fadeOut();
    semestresAtivos--;
    $("#menAddSem").hide();
}
