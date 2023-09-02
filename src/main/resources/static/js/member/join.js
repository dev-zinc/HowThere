const $email = $('div.email-wrapper');
const $birth = $('div.birth-wrapper');
const $nameUp = $('div.name-up');
const $nameDown = $('div.name-down');
let clickCheckUp = 0;
let clickCheckDown = 0;

/* 이름 */
$nameUp.on('click', function(){
    $nameUp.addClass('name-wrapper-active');
    $nameUp.find('div.name-write').addClass('email-write-active');
    $nameUp.find('div.name-input-wrapper').addClass('email-input-wrapper-active');
    $('.border-wrapper-up').addClass('border-wrapper-active');
})

$nameUp.on('focusout blur', function(){
    if($('#firstname-join').val().length == 0){
        $nameUp.find('div.name-write').removeClass('email-write-active');
        $nameUp.find('div.name-input-wrapper').removeClass('email-input-wrapper-active');
    }
    $('.border-wrapper-up').removeClass('border-wrapper-active');
    $nameUp.removeClass('name-wrapper-active');
})

$nameDown.on('click', function(){
    $nameDown.addClass('name-wrapper-active');
    $nameDown.find('div.name-write').addClass('email-write-active');
    $nameDown.find('div.name-input-wrapper').addClass('email-input-wrapper-active');
    $('.border-wrapper-down').addClass('border-wrapper-active');
})

$nameDown.on('focusout blur', function(){
    if($('#lastname-join').val().length == 0){
        $nameDown.find('div.name-write').removeClass('email-write-active');
        $nameDown.find('div.name-input-wrapper').removeClass('email-input-wrapper-active');
    }
    $('.border-wrapper-down').removeClass('border-wrapper-active');
    $nameDown.removeClass('name-wrapper-active');

})

/* 생년월일 */
$birth.on('click', function(){
    $birth.addClass('birth-wrapper-active');
    $birth.find('div.birth-write').addClass('birth-write-active');
    $birth.find('div.birth-input-wrapper').addClass('birth-input-wrapper-active');
})

$birth.on('focusout blur', function(){
    if($('input.birth-input').val().length == 0){
        $birth.find('div.birth-write').removeClass('birth-write-active');
        $birth.find('div.birth-input-wrapper').removeClass('birth-input-wrapper-active');
    }
    $birth.removeClass('birth-wrapper-active');
})

/* 이메일 */
$email.on('click', function(){
    $email.addClass('email-wrapper-active');
    $email.find('div.email-write').addClass('email-write-active');
    $email.find('div.email-input-wrapper').addClass('email-input-wrapper-active');
})

$email.on('focusout blur', function(){
    if($('input.email-input').val().length == 0){
        $email.find('div.email-write').removeClass('email-write-active');
        $email.find('div.email-input-wrapper').removeClass('email-input-wrapper-active');
    }
    $email.removeClass('email-wrapper-active');
})

/* 체크박스 */
$('.check-up').on('click', function(e){
    clickCheckUp++;
    console.log(clickCheckUp)
    if(clickCheckUp % 2 == 0){
        $('.checkbox-inside.up').removeClass('checkbox-inside-active');
    } else {
        $('.checkbox-inside.up').addClass('checkbox-inside-active');
    }
})

$('.check-down').on('click', function(e){
    clickCheckDown++;
    console.log(clickCheckDown)
    if(clickCheckDown % 2 == 0){
        $('.checkbox-inside.down').removeClass('checkbox-inside-active');
    } else {
        $('.checkbox-inside.down').addClass('checkbox-inside-active');
    }
})