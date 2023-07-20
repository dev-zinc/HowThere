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

function showHeaderProfilMenu(){
    if(document.getElementById('headerProfileMenu')){
        return;
    }
    const tag = "<div id='headerProfileMenu'>" +
        "<a><div><b>로그인</b></div></a>" +
        "<a><div>회원가입</div></a>" +
        "<a><div>도움말</div></a>" +
        "</div>"
    $(".header-profile").append(tag);
}

$("body").click(function(e){
    if(!$(".header-profile").find(e.target).length){
        $("#headerProfileMenu").remove();
    }
})