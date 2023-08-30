
const $fileInput = $('.input-file-label');
const $fileImg = $('.file-img-wrapper');
const $x = $('.delete-img');
const $img = $('.img-wrapper');

$img.hide();
$x.on('click', () => {
    $img.hide();
    $file.val("");
});

let service = new DetailService('notice', function (e) {
    let reader = new FileReader();
    let file = e.target.files[0];
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        let url = e.target.result;
        if(!url.includes('image')) return;
        console.log(e.target);
        let form = new FormData();
        form.enctype = 'multipart/form-data;charset=utf-8';
        form.append("uploadFile", file);
        fetch('/files/upload', {
            method: 'POST',
            body: form
        })
            .then(response => response.json())
            .then(fileNames => {
                console.log(fileNames);
                return fetch(`/files/display?fileName=t_${fileNames[0]}`)
            })
            .then(response => response.text())
            .then(byteArray => {
                $img.prop('src', byteArray);
                $img.show();
                $fileInput.text('수정');
            });
    }
});