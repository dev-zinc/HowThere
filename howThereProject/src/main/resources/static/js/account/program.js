onClickEachNavs((i, btn) => {
    let nav = $(btn);
    if(nav.hasClass(".selected")) return;

    $(".selected").removeClass("selected");
    nav.addClass("selected");

    //do sth
});