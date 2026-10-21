
document.addEventListener('DOMContentLoaded', () => {

    const filterBtns = document.querySelectorAll('.filter-btn');

    const projectCards = document.querySelectorAll('.project-card');

    filterBtns.forEach(btn => {

        btn.addEventListener('click', () => {

            filterBtns.forEach(b => b.classList.remove('active'));

            btn.classList.add('active');

            const filter = btn.getAttribute('data-filter');

            projectCards.forEach(card => {

                if (filter === 'all' || card.getAttribute('data-category') === filter) {

                    card.style.display = 'block';

                } else {

                    card.style.display = 'none';

                }

            });

        });

    });

    const form = document.getElementById('contactForm');

    if (form) {

        form.addEventListener('submit', (e) => {

            e.preventDefault();

            alert('🎉 Thank you for reaching out! Priyank will respond within 24 hours.');

            form.reset();

        });

    }

});

