function searchAddress(e) {
    if (e.keyCode == 13) {
        $("#address-search-btn").trigger("click");
    }
}

const mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

//지도를 미리 생성
const map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
const geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
const marker = new daum.maps.Marker({
    map: map
});

function openAddressSearchPopup() {
    new daum.Postcode({
        oncomplete: function (data) {
            const {address, sido, sigungu} = data;

            // 주소 정보를 해당 필드에 넣는다.
            $("#address").val(address);
            $("#sido").val(sido);
            $("#sigungu").val(sigungu);

            $(".address-wrap>div").removeClass("hidden");

            // 주소로 상세 정보를 검색
            geocoder.addressSearch(address, function (results, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    var coords = new daum.maps.LatLng(result.y, result.x);
                    // 지도를 보여준다.
                    mapContainer.style.display = "block";
                    map.relayout();
                    // 지도 중심을 변경한다.
                    map.setCenter(coords);
                    // 마커를 결과값으로 받은 위치로 옮긴다.
                    marker.setPosition(coords)
                }
            });
        }
    }).open({
        q: $("#searchAddress").val() // 팝업 오픈 시 검색어 전달
    });
}

$(function () {
    //여기 아래 부분
    $('#summernote').summernote({
        height: 300,                 // 에디터 높이
        minHeight: 500,             // 최소 높이
        maxHeight: 800,             // 최대 높이
        focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '호스팅할 숙소를 소개해주세요.'	//placeholder 설정
    });

    $("#searchAddress").on("keyup", searchAddress);

    $(".maxCount").click(function (e) {
        const {for: id, oper} = e.currentTarget.dataset;
        const $target = $("#" + id);
        const orgVal = $target.text();

        switch (oper) {
            case 'minus':
                if (orgVal == 0) {// orgVal 가 0이면
                    return;
                }
                $target.text(Number(orgVal) - 1);
                break;
            case 'plus':
                $target.text(Number(orgVal) + 1);
                break;
        }
    })
});

function registerHouse() {
    // const form = document.querySelector("#form");
    // const files = document.querySelector("#imgFileAddBtn").files;
    // const guestCnt = document.querySelector("#maxGuestCnt").innerHTML;
    // const petCnt = document.querySelector("#maxPetCnt").innerHTML;
    // form.append(files);
    // form.append(guestCnt);
    // form.append(petCnt);
    return true;
}

function submit() {
    const form = document.querySelector("#form");
    const files = document.querySelector("input[name='houseImg']").files;
    const maxGuestCnt = document.querySelector("input[name='maxGuestCnt']");
    const maxPetCnt = document.querySelector("input[name='maxPetCnt']");
    maxGuestCnt.value = document.querySelector("#maxGuestCnt").innerHTML;
    maxPetCnt.value = document.querySelector("#maxPetCnt").innerHTML;
    for (let i = 0; i < files.length; i++) {
        const image = files[i];
        form.append(image);
    }
    form.append(maxGuestCnt);
    form.append(maxPetCnt);
    form.submit();
}

function upload(){
    const files = document.querySelector("input[name='houseImg']").files;
    const formData = new FormData();

    for (let i = 0; i < files.length; i++) {
        const image = files[i];
        formData.append('uploadFile', image);
    }

    fetch("/host/upload", {
        method: "POST",
        body: formData
    }).then(res => console.log(res));
}