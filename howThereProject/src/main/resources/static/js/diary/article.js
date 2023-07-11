const $topButton = $('.top-btn');
$topButton.hide()

$(window).scroll(function () {
    if($(this).scrollTop() > 150)
        $topButton.show()
    else
        $topButton.hide()
})