
function openTrailer(title) {

    const mt = document.getElementById('modalTitle');

    const tm = document.getElementById('trailerModal');

    if (mt) mt.textContent = title;

    if (tm) tm.style.display = 'flex';

}

function closeModal() {

    const tm = document.getElementById('trailerModal');

    if (tm) tm.style.display = 'none';

}

