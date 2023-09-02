const localNev = $('.local-nev-box > label > div');
localNev.click(function(e){
    document.querySelectorAll(".local-nev-box label div").forEach(tag => {
        tag.dataset.select = false;
    })
    e.currentTarget.dataset.select = true;
    let region = e.currentTarget.dataset.region;
    console.log(region);
    fetch(`api/list?region=${region}`)
        .then(response => {
            console.log(response);
            return response.json()
        })
        .then(programs => {
            console.log(programs);
            let html = '';
            programs.forEach(program => {
                let img = `<img src="/files/display?fileName=${program.filePath}t_${program.fileUuid}_${program.fileName}" alt="">`
                html += `
                <div>
                    <a href="/program/detail?id=${program.id}">
                        <div class="house-wrap">
                            <div class="house-info">
                                <div class="house-name">${program.programAddress}</div>
                                <div class="period">${program.programStartDate.substring(0, 10)} - ${program.programEndDate.substring(0, 10)}</div>
                                <div class="price">
                                    <span>₩</span>
                                    <span>${program.programPrice}</span>
                                    <span> /박</span>
                                </div>
                            </div>
                            <div class="house-img-wrap">
                                ${program.filePath != null ? img : ''}
                            </div>
                        </div>
                    </a>
                </div>
                `;
            });

            $('#hostingList').html(html);
        });
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

