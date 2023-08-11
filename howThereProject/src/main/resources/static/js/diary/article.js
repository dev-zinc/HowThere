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
            // console.log(diaryReplies);
            let text = "";
            diaryReplies.content.forEach(diaryReply => {
                text += `
                        <div class="comment">
                            <div class="comment-wrapper flex">
                        `
                if(memberId == diaryReply.memberId){
                    text += `
                            <div class="buttons">
                                <button class="reply-modify">수정</button>
                                <button class="reply-delete">삭제</button>
                            </div>
                            `
                }
                text += `
                                <div class="member-profile">
                                    <span style="font-weight: 700;">${diaryReply.memberName}</span>
                                    <div class="date-wrapper" style="display: flex; align-items: baseline;">
                                        <span style="display: inline">2023년 7월</span>
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
                        </div>
                        `
            });
            document.querySelector("#comments").innerHTML += text;
            if(diaryReplies.last){
                $(".more").css('display', 'none');
            }
        });
}

$(document).ready(getList());
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
        }
    })
});

