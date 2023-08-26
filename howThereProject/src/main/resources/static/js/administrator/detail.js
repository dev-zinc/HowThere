
const $toList = $('.button-list');
const $file = $('#input-file');
class DetailService {
    constructor(requestURI, onFileChange) {
        $(`li.${requestURI}`).addClass('active');
        $toList.on('click', () => location.href = `/administrator/${requestURI}`);
        if(onFileChange) {
            $file.on('change', onFileChange);
        }
    }
}

