const $container = $('.board-list');
const $pageContainer = $('.board-page');

const ELEMENT_SIZE_PER_PAGE = 10;
const PAGE_SET_SIZE = 10;
const $searchInput = $('input.select');

const $prevButton = $('.button.prev');
const $nextButton = $('.button.next');
const $firstButton = $('.button.first');
const $lastButton = $('.button.last');

function AdministratorService(requestURL, header, appender) {
    this.requestURL = requestURL;
    this.header = header;
    this.appender = appender;

    this.page = undefined;
    this.keyword = '';

    this.init = function() {
        this.getPagePromise(0).then(json => {
            this.page = json;
            let html = this.header;
            this.page.content.forEach(e => html += appender(e));
            $container.html(html);
            this.setPageButtons();
        });
    }

    this.registerEvents = () => {
        $searchInput.on('search', () => {
            this.keyword = $searchInput.text();
        });

        //< 이전 세트로
        $prevButton.on('click', (e) => {
            e.preventDefault();
            if (this.page.number <= PAGE_SET_SIZE) return;
            this.prevSet(this.page.number);
        });

        //> 다음 세트로
        $nextButton.on('click', (e) => {
            e.preventDefault();
            if (this.page.size < 10) return;
            this.nextSet(this.page.number);
        });

        //<< 첫 세트로
        $firstButton.on('click', (e) => {
            e.preventDefault();
            if (this.page.number <= PAGE_SET_SIZE) return;
            this.shiftPage(0);
        });

        //>> 마지막 세트로
        $lastButton.on('click', (e) => {
            e.preventDefault();
            if (this.page.size < 10) return;
            this.shiftPage(this.page.totalPages - 1);
        });
    }

    this.getPagePromise = function(page, keyword) {
        const req = this.requestURL + "?" +
                    (page ? `size=${ELEMENT_SIZE_PER_PAGE}&page=` + page + "&" : "") +
                    (keyword ? "keyword=" + keyword : "");
        return fetch(req).then(response => response.json());
    }

    this.shiftPage = function (page) {
        this.getPagePromise(page).then(json => this.page = json);
    }

    this.setPageButtons = function (pageNumber) {
        let pageSet = Math.floor(this.page.number / PAGE_SET_SIZE) + 1;
        let totalSet = this.page.totalPages % PAGE_SET_SIZE
        let html = `
            <a class="button first"><<</a>
            <a class="button prev"><</a>
        `;

        for (let i = 0; i < this.page.size; i++) {
            html += `<a id="${this.page.number}" class="number">${this.page.pageable.offset + i + 1}</a>`;
        }

        html += `
            <a class="button next">></a>
            <a class="button last">>></a>
        `;

        $pageContainer.html(html);
        $pageContainer.find(`#${this.page.number}`).addClass("active");
        $pageContainer.filter((i, a) => $(a).hasClass("number")).eq((i, a) => {
            $(a).on('click', () => {

            });
        });
    }

    this.prevSet = function (page) {
        this.shiftPage(((page / PAGE_SET_SIZE)) * PAGE_SET_SIZE + 1);
    }

    this.nextSet = function (page) {
        this.shiftPage(((page / PAGE_SET_SIZE) + 2) * PAGE_SET_SIZE + 1);
    }
}