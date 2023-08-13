const $container = $('.board-list');
const $pageContainer = $('.board-page');
const $deleteButton = $('input.button-delete');
const $searchInput = $('input.select');

const ELEMENT_SIZE_PER_PAGE = 10;
const PAGE_SET_SIZE = 10;

function AdministratorService(requestURL, header, appender) {
    this.page = undefined;
    this.keyword = '';

    $searchInput.on('keyup', (e) => {
        if(e.keyCode !== 13) return;
        this.keyword = $searchInput.val();
        this.shiftPage(1);
    });

    //fn
    let getOffset = () => {
        if(this.page) {
            return Math.floor(this.page.number / PAGE_SET_SIZE)
        }
        return -1;
    }

    this.getPagePromise = function(page) {
        const req = requestURL + "?" +
                    (page != undefined ? `size=${ELEMENT_SIZE_PER_PAGE}&page=` + page + "&" : "") +
                    (this.keyword != '' ? "keyword=" + this.keyword : "");
        return fetch(req).then(response => response.json());
    }

    /**
     * @param page 1부터 카운트
     */
    this.shiftPage = function (page) {
        this.getPagePromise(page - 1).then(json => {
            this.page = json;
            let html = header;

            if(this.page.content.length == 0) {
                $container.html(html + `<div class="program not-found"><span>검색 결과가 없습니다.</span></div>`);
                $pageContainer.html("");
                return;
            }

            this.page.content.forEach(e => html += appender(e));
            $container.html(html);

            this.setPageButtons();
        });
    }

    this.setPageButtons = function () {
        let pageOffset = getOffset();
        let lastOffset = Math.floor(this.page.totalPages / 10);

        //settings =====================================================
        let html = '';

        html += `
            <a class="button ${pageOffset > 0 ? 'first' : 'blocked'}"><<</a>
            <a class="button ${pageOffset > 0 ? 'prev' : 'blocked'}"><</a>
        `;

        for (let i = 0; i < PAGE_SET_SIZE; i++) {
            let idx = (pageOffset) * PAGE_SET_SIZE + i + 1;
            if(idx > this.page.totalPages) break;
            html += `<a class="number">${idx}</a>`;
        }

        html += `
            <a class="button ${pageOffset < lastOffset ? 'next' : 'blocked'}">></a>
            <a class="button ${pageOffset < lastOffset ? 'last' : 'blocked'}">>></a>
        `;

        $pageContainer.html(html);

        //events ====================================================
        $('a.number').each((i, a) => {
            let $a = $(a);
            let idx = $a.text();
            if(this.page.number + 1 == idx) $a.addClass("active");
            $a.on('click', () => {
                $('.active').removeClass("active");
                $a.addClass("active");
                this.shiftPage(idx);
            });
        });

        $('a.blocked').each((i, a) => $(a).on('click', e => e.preventDefault()));

        //< 이전 세트로
        $('.button.prev').on('click', (e) => {
            e.preventDefault();
            let prevPageNumber = (getOffset() - 1) * PAGE_SET_SIZE;
            this.shiftPage(prevPageNumber);
        });

        //> 다음 세트로
        $('.button.next').on('click', (e) => {
            e.preventDefault();
            let nextPageNumber = (getOffset() + 1) * PAGE_SET_SIZE + 1;
            this.shiftPage(nextPageNumber);
        });

        //<< 첫 세트로
        $('.button.first').on('click', (e) => {
            e.preventDefault();
            this.shiftPage(0);
        });

        //>> 마지막 세트로
        $('.button.last').on('click', (e) => {
            e.preventDefault();
            this.shiftPage(this.page.totalPages);
        });
    }

    //init ==================================================
    this.shiftPage(1);

    $deleteButton.on('click', () => {
        $container.children().not('top').each((i, e) => {
            let checked = $(e).first().children().first().first().is(":checked");

            console.log(checked);
            console.log($(e).first().children().first().first());
        });
    });
}

