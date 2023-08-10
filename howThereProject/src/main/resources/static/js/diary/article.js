// const $deleteButton = $("div.delete");
// const $modifyButton = $("div.modify");
//
// $deleteButton.on("click", function () {
//    showWarnModal("해당 일기를 삭제합니다. \n Y/N")
// });
//
// $modifyButton.on("click", function () {
//
// });

function getList(){
   fetch(`api/article/${id}`)
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