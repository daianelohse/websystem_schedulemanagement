$(function() {
    $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 4,
        min: new Date(),
        closeOnSelect: true
    });
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