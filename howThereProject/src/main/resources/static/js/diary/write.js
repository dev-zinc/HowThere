
$(document).ready(() =>{
    $('#summernote').summernote({
        placeholder: '즐거웠던 기억과 추억을 되새겨보세요',
        tabsize: 2,
        height: 500,
        // callbacks:{
        //     onImageUpload : function(files){
        //         for (var i = files.length - 1; i >= 0; i--) {
        //             uploadSummernoteImageFile(files[i], this);
        //         }
        //     }
        // }
    })
});

$('#diary-submit').on('click', () => {
    diaryContent = $("#summernote").summernote('code');
    $('#content-input').val(diaryContent);
    $('#house-id-input').val(houseId);
    $('#member-id-input').val(memberId);
    $('#write-form').submit();
})
