
let cart = 0;

function addToCart(item, price) {

    cart++;

    const cc = document.getElementById('cartCount');

    if (cc) cc.textContent = cart;

    alert(`Added ${item} ($${price.toFixed(2)}) to your order! Total items: ${cart}`);

}

function toggleCart() {

    alert(`Your cart has ${cart} item(s). Ready for checkout.`);

}

