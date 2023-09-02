const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
const options = { //지도를 생성할 때 필요한 기본 옵션
  center: new kakao.maps.LatLng(program.lon, program.lat), //지도의 중심좌표.
  level: 3, //지도의 레벨(확대, 축소 정도)
};

const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 마커가 표시될 위치입니다
const markerPosition = new kakao.maps.LatLng(program.lon,
    program.lat);

// 마커를 생성합니다
const marker = new kakao.maps.Marker({
  position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

function panTo() {
  // 이동할 위도 경도 위치를 생성합니다
  var moveLatLon = new kakao.maps.LatLng(program.lon, program.lat);

  // 지도 중심을 부드럽게 이동시킵니다
  // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
  map.panTo(moveLatLon);
}

const pic = document.querySelectorAll(".program-img");
pic.forEach(e => {
  e.addEventListener("click", panTo);
})

document.querySelectorAll('.carousel-item')[0].classList.add('active');
document.querySelector('.program-price').innerHTML = Number.parseInt(
    program.programPrice).toLocaleString();
document.querySelector("#price").value = Number.parseInt(document.querySelector("#price").value).toLocaleString();