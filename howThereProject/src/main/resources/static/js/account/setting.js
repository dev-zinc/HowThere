const $modifyButtons = $(".info-modify button");
const $rows = $(".info-block");
const $nameModify = $("#name_modify");
const $emailModify = $("#email_modify");
const $phoneNumbersModify = $("#phone_numbers_modify");

let isClicked = new Array($rows.size).fill(false);
let modifyInputs = [$nameModify, $emailModify, $phoneNumbersModify];

$nameModify.hide();
$emailModify.hide();
$phoneNumbersModify.hide();

$modifyButtons.each((i, button) => {
    $(button).on('click', function (e) {
        let showColor;
        let text;

        e.preventDefault()
        if(isClicked.reduce((prev, next) => prev || next) && !isClicked[i]) return;

        isClicked[i] = !isClicked[i];

        if(isClicked[i]) {
            showColor = "#DDDDDD";
            text = "취소";
            modifyInputs[i].show();
        } else {
            showColor = "#222222";
            text = "수정";
            modifyInputs[i].hide();
        }

        $(this).text(text);
        $rows.filter(j => i != j).each((_, other) => {
            $(other).css("color", showColor);
            $(other).find(".info .info-text").css("color", showColor);
        });
    });
});