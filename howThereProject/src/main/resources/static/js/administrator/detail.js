
const $toList = $('.button-list');

class DetailService {
    constructor(requestURI) {
        $toList.on('click', () => location.href = `/administrator/${requestURI}`);
    }
}

