$("#modify").on('click', () => {
    location.href = `/diary/modify/${diary.id}`
})

$("#delete").on('click', () => {
    location.href = `/diary/delete/${diary.id}`
})

// console.log(diary.id);
/* 댓글 */
let page = 0;
function getList(){
    fetch(`http://localhost:10000/diary-replies/list/${diary.id}?page=${page}`)
        .then((response) => response.json())
        .then((diaryReplies) => {
            let text = "";
            diaryReplies.content.forEach(diaryReply => {
                text += `
                        <div class="comment">
                            <div class="comment-wrapper flex">
                        `
                if(memberId == diaryReply.memberId){
                    text += `
                            <div class="buttons">
                                <button onclick="modifyForm(this)" class="reply-modify">수정</button>
                                <button onclick="remove(this)" class="reply-delete">삭제</button>
                                <input value="${diaryReply.id}" type="hidden">
                            </div>
                            `
                }
                text += `
                                <div class="member-profile">
                                    <span style="font-weight: 700;">${diaryReply.memberName}</span>
                                    <div class="date-wrapper" style="display: flex; align-items: baseline;">
                                        <span style="display: inline">${elapsedTime(diaryReply.createdDate)}</span>
                                    </div>
                                </div>
                                <div style="height: 40px; width: 40px; display: block; position: relative;">
                                    <a class="img-link" href="" target="_blank">
                                        <div style="background-position: 50% 50%; background-repeat: no-repeat; width: 40px; height: 40px;">
                                            <img class="member-img" src="https://a0.muscache.com/im/pictures/user/3b0cf3fd-2bdf-4253-848d-e18f2729c1b5.jpg?im_w=240">
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div style="line-height: 24px;">
                                <span>${diaryReply.replyContent}</span>
                            </div>
                            <div class="modify-content-wrapper">
                                <input name="replyContent" value="${diaryReply.replyContent}" type="text" placeholder="댓글을 입력하세요.">
                                <input value="${diaryReply.id}" type="hidden">
                                <button onclick="modify(this)">수정 완료</button>
                                <button onclick="modifyCancel(this)">수정 취소</button>
                            </div>
                        </div>
                        `
            });
            document.querySelector("#comments").innerHTML += text;
            if(diaryReplies.last){
                $(".more").css('display', 'none');
            }
        });
}

$(document).ready(() => {
    getList();
    getReplyCount();
    checkLike();
    getLikeCount();
});

/* 댓글 더보기 */
$(".more").on('click', () => {
   page++;
   getList();
});

/* 댓글 작성 */
$(".modify-button").on('click', () => {
    let replyContent = $(".comment-writer").val();
    fetch(`http://localhost:10000/diary-replies/write`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
        body: JSON.stringify({memberId: memberId, diaryId: diary.id, replyContent: replyContent})
    })
    .then((response) => {
        if(response.ok){
            page = 0;
            $(".comment-writer").val("");
            document.querySelector("#comments").innerHTML = "";
            getList();
            getReplyCount();
        }
    })
});

/* 댓글 수정 */
function modifyForm(button){
    $(button).parent().parent().next().next().css('display', 'block');
    $(button).parent().parent().next().css('display', 'none');
}

function modifyCancel(button){
    $(button).parent().css('display', 'none');
    $(button).parent().prev().css('display', 'block');
}

function modify(button){
    let replyContent = $(button).prev().prev().val();
    let id = $(button).prev().val();
    fetch(`http://localhost:10000/diary-replies/modify`, {
        method: 'put',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
        body: JSON.stringify({memberId: memberId, diaryId: diary.id, replyContent: replyContent, id: id})
    })
    .then((response) => {
        if(response.ok){
            page = 0;
            $(button).parent().css('display', 'none');
            $(button).parent().prev().css('display', 'block');
            document.querySelector("#comments").innerHTML = "";
            getList();
        }
    })
}

function remove(button){
    let id = $(button).next().val()
    fetch(`http://localhost:10000/diary-replies/delete/${id}`, {
        method: 'delete',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
    })
    .then((response) => {
        if(response.ok){
            page = 0;
            document.querySelector("#comments").innerHTML = "";
            getList();
            getReplyCount();
        }
    })
}

function getReplyCount() {
    let id = diary.id
    fetch(`http://localhost:10000/diary-replies/count/${id}`, {
        method: 'get',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
    })
    .then((response) => response.json())
    .then((count) => {
        $("#comment-count").text(count);
    })
}



//작성시간 함수
function elapsedTime(date) {
    const start = new Date(date);
    const end = new Date();

    const diff = (end - start) / 1000;

    const times = [
        { name: '년', milliSeconds: 60 * 60 * 24 * 365 },
        { name: '개월', milliSeconds: 60 * 60 * 24 * 30 },
        { name: '일', milliSeconds: 60 * 60 * 24 },
        { name: '시간', milliSeconds: 60 * 60 },
        { name: '분', milliSeconds: 60 },
    ];

    for (const value of times) {
        const betweenTime = Math.floor(diff / value.milliSeconds);

        if (betweenTime > 0) {
            return `${betweenTime}${value.name} 전`;
        }
    }
    return '방금 전';
}

/* 좋아요 */
$(".like").on('click', (e) => {
    if($(".like").attr('src') == "/img/diary/heart.svg"){
        fetch(`http://localhost:10000/diary-likes/like`, {
            method: 'post',
            headers: { 'Content-Type': 'application/json;charset=utf-8' },
            body: JSON.stringify({memberId: memberId, diaryId: diary.id})
        })
        .then((response) => {
            if(response.ok){
                $(".like").attr('src', '/img/diary/full-heart.svg');
                getLikeCount();
            }
        })
    } else {
        fetch(`http://localhost:10000/diary-likes/delete/${memberId}/${diary.id}`, {
            method: 'delete',
            headers: { 'Content-Type': 'application/json;charset=utf-8' },
        })
        .then((response) => {
            if(response.ok){
                $(".like").attr('src', '/img/diary/heart.svg');
                getLikeCount();
            }
        })
        $(".like").attr('src', '/img/diary/heart.svg');
    }
});

function checkLike(){
    fetch(`http://localhost:10000/diary-likes/get/${memberId}/${diary.id}`, {
        method: 'get',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
    })
    .then((response) => response.json())
    .then((check) => {
        if(check){
            $(".like").attr('src', '/img/diary/full-heart.svg');
        } else{
            $(".like").attr('src', '/img/diary/heart.svg');
        }
    })
}

function getLikeCount() {
    let id = diary.id
    fetch(`http://localhost:10000/diary-likes/count/${id}`, {
        method: 'get',
        headers: { 'Content-Type': 'application/json;charset=utf-8' },
    })
        .then((response) => response.json())
        .then((count) => {
            $("#like-count").text(count);
        })
}