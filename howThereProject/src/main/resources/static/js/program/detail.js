const dateDepart = $('#startDate');
const dateReturn = $('#endDate');

$("#startDate, #endDate").datepicker({
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
}).on('change', function () {
  const startDate = dateDepart.val();
  const endDate = dateReturn.val() === "" ? startDate : dateReturn.val();

  if (startDate > endDate) {
    dateDepart.val(endDate);
  }
});

const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
const options = { //지도를 생성할 때 필요한 기본 옵션
  center: new kakao.maps.LatLng(33.450580, 126.574942), //지도의 중심좌표.
  level: 3, //지도의 레벨(확대, 축소 정도)
};

const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 마커가 표시될 위치입니다
const markerPosition = new kakao.maps.LatLng(34.70174173174594,
    127.75758201900709);

// 마커를 생성합니다
const marker = new kakao.maps.Marker({
  position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'dragend', function () {

  // 지도 중심좌표를 얻어옵니다
  var latlng = map.getCenter();

  var message = '변경된 지도 중심좌표는 ' + latlng.getLat() + ' 이고, ';
  message += '경도는 ' + latlng.getLng() + ' 입니다';

  // var resultDiv = document.getElementById('result');
  // resultDiv.innerHTML = message;

  console.log(message);
});
// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);

function panTo() {
  // 이동할 위도 경도 위치를 생성합니다
  var moveLatLon = new kakao.maps.LatLng(34.70174173174594, 127.75758201900709);

  // 지도 중심을 부드럽게 이동시킵니다
  // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
  map.panTo(moveLatLon);
}

const pic = document.querySelectorAll(".program-img");
pic.forEach(e => {
  e.addEventListener("click", panTo);
})

document.querySelectorAll('.carousel-item')[0].classList.add('active');
document.querySelector('.program-price').innerHTML = Number.parseInt(program.programPrice).toLocaleString();
