
const $fileName = $('.fileName');
const $fileUuid = $('.fileUuid');
const $filePath = $('.filePath');
const $fileSize = $('.fileSize');
const $fileInput = $('.input-file-label');
const $x = $('.delete-img');
const $imgWrapper = $('.img-wrapper');
const $img = $('.img-wrapper img');

$imgWrapper.hide();

$x.on('click', () => {
    $imgWrapper.hide();
    $fileInput.text("업로드");
    $file.val("");
});

if(announcementDetailDTO) {
    if(announcementDetailDTO.fileName) {
        setFileThumb({
            fileName: announcementDetailDTO.fileName ? announcementDetailDTO.fileName : '',
            fileUuid: announcementDetailDTO.fileUuid ? announcementDetailDTO.fileUuid : '',
            filePath: announcementDetailDTO.filePath ? announcementDetailDTO.filePath : '',
            fileSize: announcementDetailDTO.fileSize ? announcementDetailDTO.fileSize : ''
        });
    }
}

let service = new DetailService('notice', function (e) {
    let reader = new FileReader();
    let file = e.target.files[0];
    reader.readAsDataURL(file);
    reader.onload = e => {
        let url = e.target.result;
        if(!url.includes('image')) return;
        let form = new FormData();
        form.enctype = 'multipart/form-data;charset=utf-8';
        form.append("uploadFile", file);
        fetch('/files/upload', {
            method: 'POST',
            body: form
        }).then(response => response.json())
            .then(files => setFileThumb(files[0]));
    };
});

function setFileThumb(file) {
    let fileURL = `/files/display?fileName=${file.filePath}t_${file.fileUuid}_${file.fileName}`;
    $img.attr('src', fileURL);
    $imgWrapper.show();
    $fileInput.text('수정');
    $fileName.val(file.fileName);
    $fileUuid.val(file.fileUuid);
    $filePath.val(file.filePath);
    $fileSize.val(file.fileSize);
}