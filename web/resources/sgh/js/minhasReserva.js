/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('#minhasReservas').dataTable({
        "sDom": '<lfr>t<<"#btNovo">ip>',
        "oLanguage": {
            "sSearch": "",
            "sInfoEmpty": "",
            "sInfoFiltered": "",
            "sZeroRecords": "Nenhum resultado encontrado",
            "oPaginate": {
                "sPrevious": "Anterior",
                "sNext": "Próximo",
                "sLast": "Último",
                "sFirst": "Primeiro"
            },
            "sInfo": "Mostrando página _PAGE_ de _PAGES_",
            "sLengthMenu": "Resultados por página _MENU_"
        }});
    
});



function abreModalEditar(id){
    $("#modalEditar").openModal();
    $(".inputEditar").val(id);
    document.getElementById("modalForm:ideditar").value = id;
}

function abreModalNegar(id){
    $("#modalExcluir").openModal();
    $(".inputExcluir").val(id);
    document.getElementById("modalForm:idexcluir").value = id;
}

function fecharModalEditar(){
    $('#modalEditar').closeModal();
}
function fecharModalExcluir(){
    $('#modalExcluir').closeModal();
}





