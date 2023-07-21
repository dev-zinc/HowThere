const searchButton = $(".appear-text");
const diaryList = $('.diary-grid');

onClickEachNavs((i, btn) => {
    let nav = $(btn);
    if(nav.hasClass(".selected")) return;

    $(".selected").removeClass("selected");
    nav.addClass("selected");

    //do sth
});

//검색창 focus 시 show
searchButton.hide();

