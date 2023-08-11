function showHeaderProfilMenu(){
    if(document.getElementById('headerProfileMenu')){
        return;
    }
    const tag = "<div id='headerProfileMenu'>" +
        "<a><div><b>로그인</b></div></a>" +
        "<a><div>회원가입</div></a>" +
        "<a><div>1:1 문의</div></a>" +
        "<a><div>공지사항</div></a>" +
        "<a><div>도움말</div></a>" +
        "</div>"
    $(".header-profile").append(tag);
}

$("body").click(function(e){
    if(!$(".header-profile").find(e.target).length){
        $("#headerProfileMenu").remove();
    }
})