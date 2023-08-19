function searchAddress(e) {
  if (e.keyCode === 13) {
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

          const result = results[0]; //첫번째 결과의 값을 활용

          // 해당 주소에 대한 좌표를 받아서
          const coords = new daum.maps.LatLng(result.y, result.x);
          document.querySelector("#latitude").value = coords.La;
          document.querySelector("#longitude").value = coords.Ma;
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

// TODO: submit 하기 전에 필수 데이터 있는지 검증 절차 필요함.

function beforeSubmit() {
  const maxGuestCnt = document.querySelector("input[name='maxGuestCnt']");
  const maxPetCnt = document.querySelector("input[name='maxPetCnt']");
  maxGuestCnt.value = document.querySelector("#maxGuestCnt").innerHTML;
  maxPetCnt.value = document.querySelector("#maxPetCnt").innerHTML;
  if (validation()) {
       document.querySelector("#form").submit();
  }
}

const imageTag = document.getElementById("thumbImgFile");
imageTag.addEventListener('change', function () {
  document.querySelector("#thumbImg").style.display = "block";
  loadImg(this);
});

function loadImg(value) {
  if (value.files && value.files[0]) {
    const reader = new FileReader();

    reader.onload = function (e) {
      document.querySelector("#thumbSvg").style.display = "none";
      document.querySelector("#thumbImg")
      .setAttribute('src', e.target.result);
    }

    reader.readAsDataURL(value.files[0]);
  }
}

function validation() {
  const houseTitle = document.querySelector(
      "input[name='houseTitle']").value.trim();
  if (!nullCheck(houseTitle)) {
    alert("숙소 이름을 입력해주세요.");
    return false;
  }
  const address = document.querySelector("input[name='address']").value;
  if (!nullCheck(address)) {
    alert("주소를 입력해주세요.");
    return false;
  }
  const addressDetail = document.querySelector(
      "input[name='addressDetail']").value.trim();
  if (!nullCheck(addressDetail)) {
    alert("상세 주소를 입력해주세요.");
    return false;
  }
  const summernote = document.querySelector("#summernote").value.trim();
  if (!nullCheck(summernote)) {
    alert("소개글을 작성해주세요.");
    return false;
  }
  const thumbnail = document.querySelector("input[name='thumbnail']").value;
  if (!nullCheck(thumbnail)) {
    alert("대표 사진을 등록해주세요.");
    return false;
  }
  const houseImg = document.querySelector("input[name='houseImg']").value;
  if (!nullCheck(houseImg)) {
    alert("숙소 사진을 등록해주세요.");
    return false;
  }
  const maxGuestCnt = document.querySelector("input[name='maxGuestCnt']").value;
  if (Number.parseInt(maxGuestCnt) === 0) {
    alert("최대인원을 설정해주세요.");
    return false;
  }

  return true;
}

function nullCheck(el) {
  return !(el == null || el === "");
}