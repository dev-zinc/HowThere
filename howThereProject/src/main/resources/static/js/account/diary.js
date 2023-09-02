const $writeButton = $('#writeDiary');
const $programListButton = $('#programList');

$writeButton.on('click', function () {
    location.href = "/account/program";
});

$programListButton.on('click', function () {
    location.href = "/program/list";
});