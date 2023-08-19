
const $toList = $('.button-list');
const $write = $('.button-write');
const $textarea = $('textarea.comment-content');

class DetailService {
    constructor(requestURI) {
        $toList.on('click', () => location.href = `/administrator/${requestURI}`);
        $write.on('click', () => {
            let content = $textarea.val();
            fetch(`/administrator/${requestURI}/write`, {
                method: "POST",
                headers: { 'Content-type': "application/json;charset=utf-8" },
                body: JSON.stringify({content: content, questionId: inquiry.id})
            }).then(response => {
                if(response.ok) location.href = `/administrator/${requestURI}/detail?id=${inquiry.id}`;
            });
            location.href = requestURI
        });
    }
}

