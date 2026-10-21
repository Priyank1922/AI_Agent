
const toggle = document.getElementById('priceToggle');

if (toggle) {

    toggle.addEventListener('change', () => {

        const isYearly = toggle.checked;

        const ps = document.getElementById('priceStarter');

        const pp = document.getElementById('pricePro');

        if (ps) ps.innerHTML = isYearly ? '$23<span>/mo ($276/yr)</span>' : '$29<span>/mo</span>';

        if (pp) pp.innerHTML = isYearly ? '$63<span>/mo ($756/yr)</span>' : '$79<span>/mo</span>';

    });

}

