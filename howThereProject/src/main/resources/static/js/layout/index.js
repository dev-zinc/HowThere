const localNev = $('.local-nev-box > label > div');
localNev.click(function(e){
    document.querySelectorAll(".local-nev-box label div").forEach(tag => {
        tag.dataset.select = false;
    })
    e.currentTarget.dataset.select = true;
})

function moveSlide(e){
    const {oper, for : id, moveWidth} = e.currentTarget.dataset;
    const targetListWrap = $(`#${id}`)[0];
    const aleadyMove = targetListWrap.dataset.transX ?? 0;
    const maxMove = targetListWrap.offsetWidth - targetListWrap.scrollWidth;
    let movePx;
    if(oper == "plus"){
        movePx = Number(aleadyMove) + Number(moveWidth);
    }else{
        movePx = Number(aleadyMove) - Number(moveWidth);
    }

    if(movePx > 0){
        movePx = 0;
    }else if(maxMove > movePx){
        movePx = maxMove;
    }
    targetListWrap.dataset.transX = movePx;
    targetListWrap.style.transform = `translateX(${movePx}px)`;
}