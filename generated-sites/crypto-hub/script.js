
function calcSwap() {

    const amt = parseFloat(document.getElementById('swapAmount').value) || 1;

    const total = amt * 94820;

    const sr = document.getElementById('swapRes');

    if (sr) sr.textContent = `${amt} BTC ≈ ${total.toLocaleString()} USDT (Fee: 0.1%)`;

    alert(`Simulated Swap: ${amt} BTC -> $${total.toLocaleString()} USDT executed successfully!`);

}

