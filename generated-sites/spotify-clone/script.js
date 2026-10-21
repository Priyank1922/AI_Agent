
document.addEventListener('DOMContentLoaded', () => {

    let isPlaying = false;

    let currentSeconds = 0;

    const totalSeconds = 222; // 3:42

    let timer = null;

    const playBtn = document.getElementById('mainPlayBtn');

    const playIcon = playBtn ? playBtn.querySelector('i') : null;

    const progressBar = document.getElementById('progressBarFill');

    const currentTimeEl = document.getElementById('currentTime');

    const currentTitle = document.getElementById('currentTitle');

    const currentArtist = document.getElementById('currentArtist');

    const currentCover = document.getElementById('currentCover');

    const likeBtn = document.getElementById('likeBtn');

    function togglePlay() {

        isPlaying = !isPlaying;

        if (isPlaying) {

            if (playIcon) {

                playIcon.classList.remove('fa-play');

                playIcon.classList.add('fa-pause');

            }

            timer = setInterval(() => {

                currentSeconds++;

                if (currentSeconds > totalSeconds) currentSeconds = 0;

                updateProgress();

            }, 1000);

        } else {

            if (playIcon) {

                playIcon.classList.remove('fa-pause');

                playIcon.classList.add('fa-play');

            }

            clearInterval(timer);

        }

    }

    function updateProgress() {

        if (!progressBar) return;

        const percent = (currentSeconds / totalSeconds) * 100;

        progressBar.style.width = percent + '%';

        const mins = Math.floor(currentSeconds / 60);

        const secs = currentSeconds % 60;

        if (currentTimeEl) currentTimeEl.textContent = `${mins}:${secs < 10 ? '0' : ''}${secs}`;

    }

    if (playBtn) playBtn.addEventListener('click', togglePlay);

    // Track click handlers

    document.querySelectorAll('.music-card, .quick-card, .playlist-item').forEach(card => {

        card.addEventListener('click', () => {

            const title = card.getAttribute('data-title') || card.getAttribute('data-song') || 'Blinding Lights';

            const artist = card.getAttribute('data-artist') || 'The Weeknd';

            const cover = card.getAttribute('data-cover') || '🎵';

            if (currentTitle) currentTitle.textContent = title;

            if (currentArtist) currentArtist.textContent = artist;

            if (currentCover) currentCover.textContent = cover;

            currentSeconds = 0;

            updateProgress();

            if (!isPlaying) togglePlay();

        });

    });

    if (likeBtn) {

        likeBtn.addEventListener('click', () => {

            likeBtn.classList.toggle('active');

            const icon = likeBtn.querySelector('i');

            if (icon) {

                icon.classList.toggle('fa-regular');

                icon.classList.toggle('fa-solid');

            }

        });

    }

    // Progress Scrubber

    const pbWrap = document.getElementById('progressBarWrap');

    if (pbWrap) {

        pbWrap.addEventListener('click', (e) => {

            const rect = e.currentTarget.getBoundingClientRect();

            const clickX = e.clientX - rect.left;

            const fraction = clickX / rect.width;

            currentSeconds = Math.floor(fraction * totalSeconds);

            updateProgress();

        });

    }

});

