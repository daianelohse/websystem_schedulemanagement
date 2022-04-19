var menuAberto = false;
function menuLateral() {
    $("#menuLateral").animate({width: 'toggle'}, 350);
    if (menuAberto) {
        $("#mascara").fadeOut();
        menuAberto = false;
    } else {
        $("#mascara").fadeIn();
        menuAberto = true;
    }
}

var subMenuAberto = false;
function abrirSubMenuCadastros() {
    console.log("entrei");
    if (subMenuAberto) {
        $("#subMenuCadastros").slideUp();
        $("#iconCadsMenu").html("expand_more");
        subMenuAberto = false;
    } else {
        $("#subMenuCadastros").slideDown();
        $("#iconCadsMenu").html("expand_less");
        subMenuAberto = true;
    }
}