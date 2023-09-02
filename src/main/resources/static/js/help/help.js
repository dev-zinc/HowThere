$(document).ready(function(){
    $("#applicant").on('click', function(){
        $("#applicant").addClass('active');
        $("#seller").removeClass('active');

        $("#applicant-div").removeClass("d-none")
        $("#seller-div").addClass('d-none')
    })



    $("#seller").on('click', function(){
        $("#seller").addClass('active');
        $("#applicant").removeClass('active');

        $("#seller-div").removeClass("d-none")
        $("#applicant-div").addClass("d-none")
    })
})