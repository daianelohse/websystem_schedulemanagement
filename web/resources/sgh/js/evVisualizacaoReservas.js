$(document).ready(function() {
    //Troca cor em "Horários de Trabalho"
    $('.pagination li').click(function() {
        $('.pagination li').removeClass("active");
        if (this.id !== "proximo" && this.id !== "anterior") {
            $(this).addClass("active");
        }
    });
});