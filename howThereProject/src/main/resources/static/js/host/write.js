$(function() {
    //여기 아래 부분
    $('#summernote').summernote({
        height: 300,                 // 에디터 높이
        minHeight: 500,             // 최소 높이
        maxHeight: 800,             // 최대 높이
        focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '호스팅할 숙소를 소개해주세요.'	//placeholder 설정
    });
});