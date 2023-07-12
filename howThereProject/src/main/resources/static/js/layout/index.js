$('.local-nev-box label div').click(function(e){
    document.querySelectorAll(".local-nev-box label div").forEach(tag => {
        tag.dataset.select = false;
    })
    e.currentTarget.dataset.select = true;
})
$('.local-nev-box label').click(function(e){
    e.currentTarget.focus();
})