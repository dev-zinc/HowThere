function backToOrgHeader(){
    $(".back").remove();

    const $headerWrap = $(".header-wrap");
    $headerWrap.removeClass('header-wrap-after');
    $headerWrap.addClass('header-wrap-before');

    $("header").removeClass('after');

    $(".search-detail-wrap").css({
        animation : "slideOut 1s",
    });

    document.getElementsByClassName("search-box-div")[0].style.cssText = '';

    $(".search-box-wrap").css("animation", "search-box-wrap_back .5s");

    $(".search-detail-box").addClass("search-detail-box-before");
}

$(".search-box-btn").click(function(e){
    // search-detail-box 보이게 변경
    $(".search-detail-box").removeClass("search-detail-box-before");

    // search-detail-wrap 보이게 변경
    $('.search-detail-wrap').css({
        animation : "slideEnter .5s"
    });

    $(".search-box-div").css({
        transition : "transform 30ms ease 0s, opacity 30ms ease 0s, visibility 0ms ease 30ms;",
        transform : "scale(2.857142857142857,1.375) translateY(58px)",
        pointerEvents : "none",
        opacity : "0",
    })

    // 애니메이션 초기화, 테두리 안보이게
    $(".search-box-wrap").css({
        animation : '',
        border : '0px solid #DDDDDD',
    })

    const $headerWrap = $(".header-wrap");
    $headerWrap.append("<div class='back' onclick='backToOrgHeader()'></div>");
    $headerWrap.removeClass('header-wrap-before');
    $headerWrap.addClass('header-wrap-after');

    $("header").addClass('after');

    switch(e.currentTarget.children[0].textContent){
        case '지역': $('label.search-active').trigger('click');
            break;
        case '거주기간':
            $($('.search-title-wrap')[0]).trigger('click');
            setTimeout(function(){$('#checkIn').focus();}, 250);
            break;
        case '인원':$($('.search-title-wrap')[2]).trigger('click');
            break;
    }
});

$(".search-active, .search-title-wrap").click(function(e){
    e.preventDefault();
    if(e.currentTarget.dataset.select === 'true'){
        return;
    }
    // data-select 속성 초기화 및 클릭한 해당 태그만 true로 설정
    document.querySelector(".search-active").dataset.select = false;
    document.querySelectorAll(".search-title-wrap").forEach(el => el.dataset.select = false);
    e.currentTarget.dataset.select = true;
})

$(".choose-wrap table td").click(function(e){
    $("#place").val(e.currentTarget.textContent);
})

$(".delete-input-wrap > div > button").click(function(e){
    $("#" + e.currentTarget.dataset.for).val('');
})

$("#checkIn, #checkOut").datepicker({
    dateFormat: 'yy-mm-dd',
    prevText: '<',
    nextText: '>',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년',
    minDate: 0
});

$(".count").click(function(e){
    const {for : id, oper} = e.currentTarget.dataset;
    const $target = $("#" + id);
    const orgVal = $target.text();

    switch(oper){
        case 'minus':
            if(orgVal == 0){// orgVal 가 0이면
                return;
            }
            $target.text(Number(orgVal) - 1);
            break;
        case 'plus': $target.text(Number(orgVal) + 1);
            break;
    }

    let txt = '';
    const $guestCnt = $('#guestCnt');
    const $petCnt = $("#petCnt");
    if($guestCnt.text() != 0){
        txt += "게스트 " + $guestCnt.text() + "명";
    }

    if(txt && $petCnt.text() != 0){
        txt += ", ";
    }

    if($petCnt.text() != 0){
        txt += "반려동물 " + $petCnt.text() + "마리";
    }
    $("#guestTotal").val(txt);
})

$("button[data-for='guestTotal']").click(function(){
    $("#guestCnt").text(0);
    $("#petCnt").text(0);
})


const noneLoginHeaderProfileIcon = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 32 32\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 100%; width: 100%; fill: currentcolor;\">\n" +
    "                                    <path d=\"M16 .7C7.56.7.7 7.56.7 16S7.56 31.3 16 31.3 31.3 24.44 31.3 16 24.44.7 16 .7zm0 28c-4.02 0-7.6-1.88-9.93-4.81a12.43 12.43 0 0 1 6.45-4.4A6.5 6.5 0 0 1 9.5 14a6.5 6.5 0 0 1 13 0 6.51 6.51 0 0 1-3.02 5.5 12.42 12.42 0 0 1 6.45 4.4A12.67 12.67 0 0 1 16 28.7z\">\n" +
    "                                    </path>\n" +
    "                                </svg>";

fetch("/member/info")
    .then(rep => rep.json())
    .then(json => {
        const {memberProfile} = json;
        const profileIcon = memberProfile ? `<img src="${json.memberProfile}">` : noneLoginHeaderProfileIcon;
        document.querySelector(".header-profile-icon").innerHTML = profileIcon;
        isLogin = true;
    });

let isLogin = false;
const headerProfileMenu = {
    login : "<div id='headerProfileMenu'>" +
        "<a href='/member/logout'><div><b>로그아웃</b></div></a>" +
        "<a href=''><div>1:1 문의</div></a>" +
        "<a href='/announcement/announcement_list'><div>공지사항</div></a>" +
        "<a href='/help/help'><div>도움말</div></a>" +
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
    $(".header-profile").append(headerProfileMenu[isLogin ? 'login' : 'noneLogin']);
}

$("body").click(function(e){
    if(!$(".header-profile").find(e.target).length){
        $("#headerProfileMenu").remove();
    }
})