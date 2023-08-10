const searchButton = $(".appear-text");
const diaryList = $('.diary-grid');

onClickEachNavs((i, btn) => {
    let nav = $(btn);
    if(nav.hasClass(".selected")) return;

    $(".selected").removeClass("selected");
    nav.addClass("selected");

    //do sth
});

//검색창 focus 시 show
// searchButton.hide();

/* 검색 */
$("button.search-button").on("click", function(){
    keyword = $(this).prev().val();
    console.log(keyword);
    page = 0;
    location.href = `/diary/list?keyword=${keyword}`;
});

// 일기 넣기
// console.log(pagination);

// $(document).ready(showDiary(pagination));


// function showDiary(pagination){
//     let text = "";
//     if(pagination.size != 0){
//         pagination.content.forEach(diary => {
//             text += `
//                 <a href="/diary/article/${diary.id}" class="diary-article">
//                     <div class="img-wrap">
//                         <div class="diary-img" style="--dls-liteimage-height: 254px; --dls-liteimage-width: auto; --dls-liteimage-background-image: url('data:image/png;base64,null'); --dls-liteimage-background-size: cover;">
//                             <picture>
//                                 <img src="https://images.contentstack.io/v3/assets/bltec2ed8e3c4b1e16d/bltfbcc7f32e0cd6ff5/getting-started-on-airbnb-optimized.jpg"
//                                      style="--dls-liteimage-object-fit: cover;">
//                             </picture>
//                             <div class="background-img" style="--dls-liteimage-background-size: cover; --dls-liteimage-background-image: url(https://images.contentstack.io/v3/assets/bltec2ed8e3c4b1e16d/bltfbcc7f32e0cd6ff5/getting-started-on-airbnb-optimized.jpg);"></div>
//                         </div>
//                     </div>
//                     <div class="diary-title">${diary.diaryTitle}</div>
//                 </a>
//             `
//         });
//     }
//     $('div.diary-grid').html(text);
// }

let page = 0;
function getList(){
    fetch(`api/list?page=${page}&keyword=${keyword}`)
        .then((response) => response.json())
        .then((diarys) => {
            console.log(diarys);
            console.log(keyword);
            let text = "";
            diarys.content.forEach(diary => {
                text += `
                            <a href="/diary/article/${diary.id}" class="diary-article">
                                <div class="img-wrap">
                                    <div class="diary-img" style="--dls-liteimage-height: 254px; --dls-liteimage-width: auto; --dls-liteimage-background-image: url('data:image/png;base64,null'); --dls-liteimage-background-size: cover;">
                                        <picture>
                                            <img src="https://images.contentstack.io/v3/assets/bltec2ed8e3c4b1e16d/bltfbcc7f32e0cd6ff5/getting-started-on-airbnb-optimized.jpg"
                                                 style="--dls-liteimage-object-fit: cover;">
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
    const bodyHeight = document.body.clientHeight;
    // console.log(currentScroll + 100, bodyHeight);
    if(currentScroll + 160 >= bodyHeight * (page + 1)){
        page++;
        getList();
    }
});

