/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('select').material_select();
});
function isNumber(event) {
    if (event) {
        var charCode = (event.which) ? event.which : event.keyCode;
        console.log(charCode);
        if (charCode >= 48 && charCode <= 57) {
            return true;
        }

        if (charCode >= 96 && charCode <= 105) {
            return true;
        }
        if (charCode === 8 || charCode === 9) {

            return true;
        }
        return false;
    }
    return false;
}

function message(message) {

    alert(message);
}


var submitou = false;

$(function() {

    var texto = $(".btCadTurma input[type=hidden]").val();
    console.log(texto);


    if (texto == "Sucesso") {
        Materialize.toast("Turma salva com sucesso", 4000);
        $(".toast").css("background-color", "green");
    } else if (texto == "Erro") {
        Materialize.toast("Ocorreu um erro ao salvar a turma! Contate o administrador do sistema!", 4000);
        $(".toast").css("background-color", "red");
    } else{
        Materialize.toast(texto, 4000);
    }

});