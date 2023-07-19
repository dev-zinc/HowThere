const navButtons = $(".nav-tabs");

/**
 * 내비 클릭시 상황을 설정합니다.
 * @param onClick 각 내비 버튼 클릭 시 상태 설정, (idx, button, event)를 매개변수로 사용 가능
 * @param each 각 내비 버튼 초기 상태 설정, (idx, button)을 매개변수로 사용 가능
 */
function onClickEachNavs(onClick, each) {
    navButtons.each((i, div) => {
        $(div).on('click', function (e) {
            if(onClick) onClick(i, div, e);
        });
        if(each) each(i, div);
    });
}