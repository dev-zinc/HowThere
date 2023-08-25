// function showHeaderProfilMenu(){
//     if(document.getElementById('headerProfileMenu')){
//         return;
//     }
//     const tag = "<div id='headerProfileMenu'>" +
//         "<a><div><b>로그인</b></div></a>" +
//         "<a><div>회원가입</div></a>" +
//         "<a><div>1:1 문의</div></a>" +
//         "<a><div>공지사항</div></a>" +
//         "<a><div>도움말</div></a>" +
//         "</div>"
//     $(".header-profile").append(tag);
// }
//
// $("body").click(function(e){
//     if(!$(".header-profile").find(e.target).length){
//         $("#headerProfileMenu").remove();
//     }
// })



const noneLoginHeaderProfileIcon = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 32 32\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 100%; width: 100%; fill: currentcolor;\">\n" +
    "                                    <path d=\"M16 .7C7.56.7.7 7.56.7 16S7.56 31.3 16 31.3 31.3 24.44 31.3 16 24.44.7 16 .7zm0 28c-4.02 0-7.6-1.88-9.93-4.81a12.43 12.43 0 0 1 6.45-4.4A6.5 6.5 0 0 1 9.5 14a6.5 6.5 0 0 1 13 0 6.51 6.51 0 0 1-3.02 5.5 12.42 12.42 0 0 1 6.45 4.4A12.67 12.67 0 0 1 16 28.7z\">\n" +
    "                                    </path>\n" +
    "                                </svg>";

const profileIcon = memberInfo?.memberProfile ? `<img src="${memberInfo.memberProfile}">` : noneLoginHeaderProfileIcon;
document.querySelector(".header-profile-icon").innerHTML = profileIcon;

const headerProfileMenu = {
    login : "<div id='headerProfileMenu'>" +
        "<a href='/account/menu'><div><b>마이페이지</b></div></a>" +
        "<a href='/one_to_one_question/question_list'><div>1:1 문의</div></a>" +
        "<a href='/announcement/announcement_list'><div>공지사항</div></a>" +
        "<a href='/help/help'><div>도움말</div></a>" +
        "<a href='/member/logout'><div><b>로그아웃</b></div></a>" +
        "</div>",
    noneLogin : "<div id='headerProfileMenu'>" +
        "<a href='/member/login'><div><b>로그인</b></div></a>" +
        "<a href='/announcement/announcement_list'><div>공지사항</div></a>" +
        "<a href='/help/help'><div>도움말</div></a>" +
        "</div>"
}
function showHeaderProfilMenu(){
    if(document.getElementById('headerProfileMenu')){
        return;
    }
    $(".header-profile").append(headerProfileMenu[memberInfo ? 'login' : 'noneLogin']);
}

$("body").click(function(e){
    if(!$(".header-profile").find(e.target).length){
        $("#headerProfileMenu").remove();
    }
})