const searchButton = $(".appear-text");
const diaryList = $('.diary-grid');
const navButtons = $(".nav-tabs");

navButtons.each((i, div) => $(div).on('click', function () {
    let it = $(this);
    if(it.hasClass(".selected")) return;

    $(".selected").removeClass("selected");
    it.addClass("selected");

    //do sth
}));

//검색창 focus 시 show
searchButton.hide();

