$("#hostingCheckIn, #hostingCheckOut").datepicker({
  dateFormat: 'yy-mm-dd',
  prevText: '<',
  nextText: '>',
  monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
    '11월', '12월'],
  monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
    '11월', '12월'],
  dayNames: ['일', '월', '화', '수', '목', '금', '토'],
  dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
  dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
  showMonthAfterYear: true,
  yearSuffix: '년',
  minDate: 0
});
$('#summernote').summernote({
  height: 300,                 // 에디터 높이
  minHeight: 500,             // 최소 높이
  maxHeight: 800,             // 최대 높이
  focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
  lang: "ko-KR",					// 한글 설정
  placeholder: '호스팅할 숙소를 소개해주세요.'	//placeholder 설정
});
const mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
      center: new daum.maps.LatLng(house.lon, house.lat), // 지도의 중심좌표
      level: 5 // 지도의 확대 레벨
    };

//지도를 미리 생성
const map = new daum.maps.Map(mapContainer, mapOption);
//마커를 미리 생성
const marker = new daum.maps.Marker({
  map: map
});
marker.setPosition(new daum.maps.LatLng(house.lon, house.lat))

function submit() {
  const content = document.querySelector("#summernote").value.trim();
  if (!nullCheck(content)) {
    alert("소개글을 작성해주세요.");
    return;
  }
  const checkIn = document.querySelector("#hostingCheckIn").value;
  const checkOut = document.querySelector("#hostingCheckOut").value;
  const price = document.querySelector("#price").value;

  const param = {
    houseId: house.id,
    programStartDate: checkIn,
    programEndDate: checkOut,
    programContent: content,
    programPrice: price
  }
  fetch("/host/hosting", {
    method: "POST",
    body: JSON.stringify(param),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => {
    if (res.ok) {

    }
  })
}

function nullCheck(el) {
  return !(el == null || el === "");
}