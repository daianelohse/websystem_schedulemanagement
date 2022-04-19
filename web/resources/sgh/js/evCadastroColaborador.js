var colunaHabilitada = true;
$(document).ready(function() {
    $('select').material_select();
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 70// Creates a dropdown of 15 years to control year
    });
    $('.tooltipped').tooltip({delay: 50});
    
   
    //Troca cor em "Horários de Trabalho"
    $('.select-wrapper').click(function() {
        if (colunaHabilitada) {
            var tipo = $(this).find("input").val();
            if (tipo === "Disponível") {
                //mudar para azul
                $(this).find("input").css("background-color", "#1565c0").css("color", "#fff");
                //$(this).find("input").
            } else if (tipo === "Indisponível") {
                //mudar para vermelho
                $(this).find("input").css("background-color", "#e53935").css("color", "#fff");
            } else if (tipo === "Preferencial") {
                //mudar para verde
                $(this).find("input").css("background-color", "#43a047").css("color", "#fff");
            }
            ;
        }
    });
    $('#seg, #ter, #qua, #qui, #sex, #sab').prop('checked', false);

//    desabilitaHabilitaCampo('segunda');
//    desabilitaHabilitaCampo('terca');
//    desabilitaHabilitaCampo('quarta');
//    desabilitaHabilitaCampo('quinta');
//    desabilitaHabilitaCampo('sexta');
//    desabilitaHabilitaCampo('sabado');
});
//
//function desabilitaHabilitaCampo(aclass) {
//    $("." + aclass + " input").attr('disabled', 'disabled');
//    $("." + aclass + " input, ." + aclass + " .caret").addClass('inputDesativada');
//}
//
//function desabilitaDiaSemana(dia) {
//    var idCheckBox = dia.substring(0, 3);
//    console.log(idCheckBox);
//    colunaHabilitada = document.getElementById(idCheckBox).checked;
//
//    if (colunaHabilitada) {
//        $("." + dia + " input").removeAttr('disabled');
//        $("." + dia + " input, ." + dia + " .caret").removeClass('inputDesativada');
//    } else {
//        desabilitaHabilitaCampo(dia);
//    }
//}

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

function isNameValid(event) {
    if (event) {
        var charCode = (event.which) ? event.which : event.keyCode;
        console.log(charCode);
        if(charCode === 32 || (charCode >= 65 && charCode<=90) ||
        (charCode >= 97 && charCode <= 122) || charCode === 8 || charCode === 46 || charCode === 9 
        || (charCode >= 37 && charCode<=40)){
            
            return true;
        }
        
        return false;
    }
    return false;
}
var submitou = false;
$(function() {

    var texto = $(".btCadColaborador input[type=hidden]").val();
    console.log(texto);


    if (texto == "Sucesso") {
        Materialize.toast("Colaborador salvo com sucesso", 4000);
        $(".toast").css("background-color", "green");
    } else if (texto == "Erro") {
        Materialize.toast("Ocorreu um erro ao salvar o colaborador! Contate o administrador do sistema!", 4000);
        $(".toast").css("background-color", "red");
    } else{
        Materialize.toast(texto, 4000);
    }

});

