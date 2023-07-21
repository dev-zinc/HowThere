const $writeButton = $('#writeDiary');
const $programListButton = $('#programList');

$writeButton.on('click', function () {
    location.href = "/diary/write";
});

$programListButton.on('click', function () {
    location.href = "/account/program";
});