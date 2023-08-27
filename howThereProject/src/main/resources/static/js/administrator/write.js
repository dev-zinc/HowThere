
const $fileInput = $('.input-file-label');
const $fileImg = $('.file-img-wrapper');
const $x = $('.delete-img');
const $img = $('.img-wrapper');

$img.hide();
$x.on('click', () => {
    $img.hide();
    console.log($file.files);
    $file.files = [];
});

let service = new DetailService('notice', function (e) {
    let reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);
    reader.onload = function(e) {
        let url = e.target.result;
        if(!url.includes('image')) return;

        let form = new FormData();
        form.enctype = 'multipart/form-data';
        form.append("uploadFile", e.target.files[0]);
        fetch('/files/upload', {
            method: 'POST',
            body: form
        })
            .then(response => response.text())
            .then(fileName => fetch(`/files/display?fileName=t_${fileName}`))
            .then(response => response.text())
            .then(byteArray => {
                $img.prop('src', byteArray);
                $img.show();
                $fileInput.text('수정');
            });
    }
});