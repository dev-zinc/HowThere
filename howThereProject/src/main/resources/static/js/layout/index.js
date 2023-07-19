const localNev = $('.local-nev-box > label > div');
localNev.click(function(e){
    document.querySelectorAll(".local-nev-box label div").forEach(tag => {
        tag.dataset.select = false;
    })
    e.currentTarget.dataset.select = true;
})
// $('.local-nev-box > label').click(function(e){
//     e.currentTarget.focus();
// })
$(localNev[0]).trigger('click');