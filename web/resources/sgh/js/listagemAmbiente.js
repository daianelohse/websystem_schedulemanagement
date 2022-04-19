/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('#listagemAmbiente').dataTable({
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

function abreModalConfirmacao(sigla, id){
    $("#modal1").openModal();
    $("#ambiente").html(sigla);
    $(".inputAmbiente").val(id);
    document.getElementById("modalForm:idexcluir").value = id;
}

function fecharModal(){
    $('#modal1').closeModal();
}




