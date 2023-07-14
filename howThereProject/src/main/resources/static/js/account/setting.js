const $modifyButtons = $(".info-modify button");
const $rows = $(".info-block");

let isClicked = new Array($rows.size).fill(false);

$modifyButtons.each((i, button) => {
    $(button).on('click', function (e) {
        e.preventDefault()
        let showColor = isClicked[i] ? "#222222" : "#DDDDDD";
        $rows.filter(j => i != j).each((_, other) => {
            $(other).color = showColor;
        });
    });
});