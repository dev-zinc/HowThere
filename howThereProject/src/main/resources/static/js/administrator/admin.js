
const $container = $('.board-list');
const $pageContainer = $('.board-page');
const $deleteButton = $('input.button-delete');
const $searchInput = $('input.select');

const ELEMENT_SIZE_PER_PAGE = 10;
const PAGE_SET_SIZE = 10;

function select(name) {
    $('li.active').removeClass('active');
    $(`li.${name}`).addClass('active');
}

class PaginationService {
    constructor(request, header, appender, isDetailed, onClick) {
        this.request = request;
        this.header = header;
        this.appender = appender;
        this.isDetailed = isDetailed;
        this.page = undefined;
        this.keyword = '';

        //init
        this.shiftPage(1);

        $searchInput.on('keyup', (e) => {
            if (e.keyCode !== 13) return;
            this.keyword = $searchInput.val();
            this.shiftPage(1);
        });

        $deleteButton.on('click', () => {
            let list = [];
            $('.element').each((i, e) => {
                let checked = $(e).find('input').is(':checked');
                if (checked) {
                    list.push(this.page.content[i].id);
                }
            });
            console.log(list);
            fetch(`/administrator/api/${request}/${onClick}`, {
                method: 'POST',
                headers: { 'Content-type': "application/json;charset=utf-8" },
                body: JSON.stringify(list)
            }).then(_ => this.shiftPage(this.page.number + 1), console.log);
        });

        select(request);
    }

    //fn
    getOffset() {
        if(this.page) {
            return Math.floor(this.page.number / PAGE_SET_SIZE);
        }
        return -1;
    }

    getPagePromise(page) {
        const req = `/administrator/api/${this.request}?` +
                    (page != undefined ? `size=${ELEMENT_SIZE_PER_PAGE}&page=` + page + "&" : "") +
                    (this.keyword != '' ? "keyword=" + this.keyword : "");
        return fetch(req).then(response => response.json());
    }

    /**
     * @param page 1부터 카운트
     */
    shiftPage(page) {
        select(this.request);
        this.getPagePromise(page - 1).then(json => {
            this.page = json;
            console.log(json);
            let html = this.header;

            if(this.page.content.length == 0) {
                $container.html(html + `<div class="program not-found"><span>검색 결과가 없습니다.</span></div>`);
                $pageContainer.html("");
                return;
            }

            this.page.content.forEach(e => html += this.appender(e));
            $container.html(html);

            if(this.isDetailed) {
                $('.element').each((i, element) => $(element).on('click', (e) => {
                    if($(e.target).is('input')) return;
                    if($(e.target).hasClass('checkbox')) return;
                    location.href = `/administrator/${this.request}/detail?id=${this.page.content[i].id}`;
                }));
            }

            this.setPageButtons();
        });
    }

    setPageButtons() {
        let pageOffset = this.getOffset();
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
            let prevPageNumber = (this.getOffset() - 1) * PAGE_SET_SIZE;
            this.shiftPage(prevPageNumber);
        });

        //> 다음 세트로
        $('.button.next').on('click', (e) => {
            e.preventDefault();
            let nextPageNumber = (this.getOffset() + 1) * PAGE_SET_SIZE + 1;
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
}
