/* 정렬 탭 */
$(".nav-tabs").each((i, tab) => {
    $(tab).on('click', () => {
        order = $(tab).attr("id");
        $(".selected").removeClass("selected");
        $(tab).addClass("selected");
        page = 0;
        document.querySelector(".diary-grid").innerHTML = "";
        getList();
    })

})

/* 검색 */
$("div.search-button").on("click", function(){
    keyword = $(this).prev().val();
    page = 0;
    document.querySelector(".diary-grid").innerHTML = "";
    getList();
});

let page = 0;
function getList(){
    fetch(`api/list?page=${page}&keyword=${keyword}&order=${order}`)
        .then((response) => response.json())
        .then((diarys) => {
            let text = "";
            diarys.content.forEach(diary => {
                let src = "";
                if(diary.diaryContent.split("img")[1]){
                    src = diary.diaryContent.split("\"").filter(i=>i.includes("data:image"))[0];
                } else {
                    src = "https://images.contentstack.io/v3/assets/bltec2ed8e3c4b1e16d/bltfbcc7f32e0cd6ff5/getting-started-on-airbnb-optimized.jpg";
                }
                text += `
                            <a href="/diary/article/${diary.id}" class="diary-article">
                                <div class="img-wrap">
                                    <div class="diary-img" style="--dls-liteimage-height: 254px; --dls-liteimage-width: auto; --dls-liteimage-background-image: url('data:image/png;base64,null'); --dls-liteimage-background-size: cover;">
                                        <picture>
                                            <img src="${src}"
                                                 style="--dls-liteimage-object-fit: cover; background: #D8D8D8">
                                        </picture>
                                        <div class="background-img" style="--dls-liteimage-background-size: cover; --dls-liteimage-background-image: url(https://images.contentstack.io/v3/assets/bltec2ed8e3c4b1e16d/bltfbcc7f32e0cd6ff5/getting-started-on-airbnb-optimized.jpg);"></div>
                                    </div>
                                </div>
                                <div class="diary-title">${diary.diaryTitle}</div>
                            </a>
                        `
            });
            document.querySelector(".diary-grid").innerHTML += text;
        });
}

$(document).ready(getList());

// 무한 스크롤 (ㅠㅠ 공식이 안 먹어서 내 맘대로..)
window.addEventListener('scroll', () => {
    const currentScroll = window.scrollY;
    const windowHeight = window.innerHeight;
    const bodyHeight = document.body.scrollHeight;
    // console.log(currentScroll + windowHeight + 20, bodyHeight);
    if(currentScroll + windowHeight - 0.5 >= bodyHeight){
        // currentScroll + windowHeight >= bodyHeight
        page++;
        getList();
    }
});

