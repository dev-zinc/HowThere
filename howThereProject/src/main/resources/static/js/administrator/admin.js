const $container = $('.board-list');
const PAGE_SIZE = 10;
const PAGE_SET_SIZE = 5;
const $searchInput = $('input.select');

const $prevButton = $('.button.prev');
const $nextButton = $('.button.next');
const $firstButton = $('.button.first');
const $lastButton = $('.button.last');

const administratorService = (() => {
    let page;
    let header, appender, requestURL;

    function init() {
        page = getPage(0);
        setPage(page);
    }

    function registerEvents(get) {
        $searchInput.on('search', function () {
            keyword = $(this).text();
            get()
        });

        //< 이전 세트로
        $prevButton.on('click', function (e) {
            e.preventDefault();
            page = prevSet(page);
            get()
        });

        //> 다음 세트로
        $nextButton.on('click', function (e) {
            e.preventDefault();
            page = nextSet(page);
        });

        //<< 첫 세트로
        $firstButton.on('click', function (e) {
            e.preventDefault();
            page = 1;
            get(++page)
        });

        //>> 마지막 세트로
        $lastButton.on('click', function (e) {
            e.preventDefault();
            page;
        });
    }

    function getPage(page, keyword) {
        let req = requestURL + "?" +
                    page ? `size=${PAGE_SIZE}&page=` + page + "&" : "" +
                    keyword ? "keyword=" + keyword : "";
        return fetch(req).then(response => response.json());
    }

    function setPage(page) {
        let html = header;
        page.content.forEach(e => html += appender(e));
        $container.html(html);

        page.first ? $firstButton.show() : $firstButton.hide();
        page.last ? $lastButton.show() : $lastButton.hide();
        page.number / PAGE_SET_SIZE !== 0 ? $prevButton.show() : $prevButton.hide();
        page.number / PAGE_SET_SIZE !== page.totalPages / PAGE_SET_SIZE - 1 ? $nextButton.show() : $nextButton.hide();
        page.first ? $firstButton.show() : $firstButton.hide();
    }

    function prevSet(page) {
        return ((page / PAGE_SET_SIZE)) * PAGE_SET_SIZE + 1;
    }

    function nextSet(page) {
        return ((page / PAGE_SET_SIZE) + 2) * PAGE_SET_SIZE + 1;
    }

    return {
        init:init, registerEvents:registerEvents, header:header, appender:appender, requestURL:requestURL
    };
})();