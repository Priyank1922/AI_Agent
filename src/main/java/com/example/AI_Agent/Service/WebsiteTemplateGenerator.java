package com.example.AI_Agent.Service;

import org.springframework.stereotype.Component;

@Component

public class WebsiteTemplateGenerator {

	public static class WebsitePackage {

		public final String slug;

		public final String title;

		public final String html;

		public final String css;

		public final String js;

		public WebsitePackage(String slug, String title, String html, String css, String js) {

			this.slug = slug;

			this.title = title;

			this.html = html;

			this.css = css;

			this.js = js;

		}

	}

	public WebsitePackage generateForPrompt(String prompt) {

		String p = prompt != null ? prompt.toLowerCase() : "";

		if (p.contains("spotify") || p.contains("music") || p.contains("player") || p.contains("song")) {

			return generateSpotifyClone();

		} else if (p.contains("netflix") || p.contains("movie") || p.contains("stream") || p.contains("cinema")) {

			return generateNetflixClone();

		} else if (p.contains("portfolio") || p.contains("resume") || p.contains("cv") || p.contains("personal")) {

			return generatePortfolioSite(prompt);

		} else if (p.contains("saas") || p.contains("startup") || p.contains("landing") || p.contains("product")) {

			return generateSaaSLanding();

		} else if (p.contains("coffee") || p.contains("cafe") || p.contains("restaurant") || p.contains("bakery")
				|| p.contains("food")) {

			return generateCoffeeShop();

		} else if (p.contains("crypto") || p.contains("finance") || p.contains("bitcoin") || p.contains("trading")
				|| p.contains("stock")) {

			return generateCryptoHub();

		} else {
			return generateCustomSite(prompt);
		}
	}

	public java.util.List<WebsitePackage> getAllSampleTemplates() {
		return java.util.List.of(
			generateSpotifyClone(),
			generateNetflixClone(),
			generateSaaSLanding(),
			generatePortfolioSite("Senior Full Stack & AI Engineer Portfolio"),
			generateCoffeeShop(),
			generateCryptoHub()
		);
	}

	public static String deriveSlug(String prompt) {

		if (prompt == null || prompt.trim().isEmpty()) {

			return "my-website";

		}

		String p = prompt.toLowerCase();

		if (p.contains("spotify"))
			return "spotify-clone";

		if (p.contains("netflix"))
			return "netflix-clone";

		if (p.contains("portfolio"))
			return "personal-portfolio";

		if (p.contains("saas"))
			return "saas-landing";

		if (p.contains("coffee") || p.contains("cafe"))
			return "coffee-showcase";

		if (p.contains("crypto"))
			return "crypto-hub";

		String clean = p.replaceAll("[^a-z0-9\\s]", "").trim();

		String[] words = clean.split("\\s+");

		StringBuilder sb = new StringBuilder();

		int count = 0;

		for (String w : words) {

			if (w.length() > 2 && !w.equals("the") && !w.equals("and") && !w.equals("for") && !w.equals("with")
					&& !w.equals("create") && !w.equals("build") && !w.equals("generate") && !w.equals("website")
					&& !w.equals("clone")) {

				if (sb.length() > 0)
					sb.append("-");

				sb.append(w);

				count++;

				if (count >= 3)
					break;

			}

		}

		return sb.length() > 0 ? sb.toString() : "custom-app";

	}

	/*
	 * =========================================================================
	 * 
	 * 1. SPOTIFY CLONE
	 * 
	 * =========================================================================
	 */

	public WebsitePackage generateSpotifyClone() {

		String slug = "spotify-clone";

		String title = "Spotify • Web Player Clone";

		String html = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>Spotify • Web Player Clone</title>

				    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <div class="spotify-app">

				        <!-- Sidebar -->

				        <aside class="sidebar">

				            <div class="brand">

				                <i class="fa-brands fa-spotify"></i>

				                <span>Spotify</span>

				            </div>

				            <nav class="nav-links">

				                <a href="#" class="nav-item active"><i class="fa-solid fa-house"></i> Home</a>

				                <a href="#" class="nav-item"><i class="fa-solid fa-magnifying-glass"></i> Search</a>

				                <a href="#" class="nav-item"><i class="fa-solid fa-book"></i> Your Library</a>

				            </nav>

				            <div class="sidebar-divider"></div>

				            <div class="playlist-header">PLAYLISTS</div>

				            <div class="playlist-list">

				                <div class="playlist-item active" data-song="Starboy • The Weeknd" data-artist="The Weeknd" data-cover="🌟">Today's Top Hits</div>

				                <div class="playlist-item" data-song="Blinding Lights • Synthwave" data-artist="The Weeknd" data-cover="⚡">Discover Weekly</div>

				                <div class="playlist-item" data-song="Lo-Fi Study Beats • Chill" data-artist="ChilledCow" data-cover="☕">Deep Focus Lo-Fi</div>

				                <div class="playlist-item" data-song="Midnight City • M83" data-artist="M83" data-cover="🌃">Late Night Drive</div>

				                <div class="playlist-item" data-song="Cyberpunk 2077 Synth" data-artist="Night City DJ" data-cover="🤖">Electronic Vibes</div>

				            </div>

				        </aside>

				        <!-- Main Content Area -->

				        <main class="main-content">

				            <header class="top-nav">

				                <div class="nav-arrows">

				                    <button class="arrow-btn"><i class="fa-solid fa-chevron-left"></i></button>

				                    <button class="arrow-btn"><i class="fa-solid fa-chevron-right"></i></button>

				                </div>

				                <div class="search-box">

				                    <i class="fa-solid fa-magnifying-glass"></i>

				                    <input type="text" placeholder="What do you want to play?" id="searchBar">

				                </div>

				                <div class="user-profile">

				                    <div class="avatar"><i class="fa-solid fa-user"></i></div>

				                    <span>Priyank</span>

				                </div>

				            </header>

				            <section class="content-body">

				                <h2 class="section-greeting" id="greetingText">Good Evening</h2>

				                <!-- Quick Access Grid -->

				                <div class="quick-grid">

				                    <div class="quick-card" data-title="Today's Top Hits" data-artist="Global Top 50" data-cover="🔥">

				                        <div class="quick-cover" style="background: linear-gradient(135deg, #e11d48, #4f46e5);">🔥</div>

				                        <span>Today's Top Hits</span>

				                        <button class="play-bubble"><i class="fa-solid fa-play"></i></button>

				                    </div>

				                    <div class="quick-card" data-title="Deep Focus Lo-Fi" data-artist="Lofi Girl" data-cover="☕">

				                        <div class="quick-cover" style="background: linear-gradient(135deg, #059669, #0284c7);">☕</div>

				                        <span>Deep Focus Lo-Fi</span>

				                        <button class="play-bubble"><i class="fa-solid fa-play"></i></button>

				                    </div>

				                    <div class="quick-card" data-title="Synthwave Nights" data-artist="Kavinsky" data-cover="🌃">

				                        <div class="quick-cover" style="background: linear-gradient(135deg, #7c3aed, #db2777);">🌃</div>

				                        <span>Synthwave Nights</span>

				                        <button class="play-bubble"><i class="fa-solid fa-play"></i></button>

				                    </div>

				                    <div class="quick-card" data-title="Chill Mix 2026" data-artist="Spotify Mix" data-cover="🎧">

				                        <div class="quick-cover" style="background: linear-gradient(135deg, #d97706, #dc2626);">🎧</div>

				                        <span>Chill Mix 2026</span>

				                        <button class="play-bubble"><i class="fa-solid fa-play"></i></button>

				                    </div>

				                </div>

				                <!-- Made for You Carousel -->

				                <div class="section-header">

				                    <h3>Made For You</h3>

				                    <a href="#">Show all</a>

				                </div>

				                <div class="cards-carousel">

				                    <div class="music-card" data-title="Blinding Lights" data-artist="The Weeknd" data-cover="✨">

				                        <div class="card-img-wrap" style="background: linear-gradient(135deg, #4f46e5, #ec4899);">

				                            <span class="card-icon">✨</span>

				                            <button class="card-play-btn"><i class="fa-solid fa-play"></i></button>

				                        </div>

				                        <h4>Blinding Lights</h4>

				                        <p>The Weeknd • After Hours</p>

				                    </div>

				                    <div class="music-card" data-title="Levitating" data-artist="Dua Lipa" data-cover="🚀">

				                        <div class="card-img-wrap" style="background: linear-gradient(135deg, #06b6d4, #3b82f6);">

				                            <span class="card-icon">🚀</span>

				                            <button class="card-play-btn"><i class="fa-solid fa-play"></i></button>

				                        </div>

				                        <h4>Levitating</h4>

				                        <p>Dua Lipa • Future Nostalgia</p>

				                    </div>

				                    <div class="music-card" data-title="Starboy" data-artist="The Weeknd, Daft Punk" data-cover="⚡">

				                        <div class="card-img-wrap" style="background: linear-gradient(135deg, #8b5cf6, #ec4899);">

				                            <span class="card-icon">⚡</span>

				                            <button class="card-play-btn"><i class="fa-solid fa-play"></i></button>

				                        </div>

				                        <h4>Starboy</h4>

				                        <p>The Weeknd, Daft Punk</p>

				                    </div>

				                    <div class="music-card" data-title="Nightcall" data-artist="Kavinsky" data-cover="🏎️">

				                        <div class="card-img-wrap" style="background: linear-gradient(135deg, #10b981, #06b6d4);">

				                            <span class="card-icon">🏎️</span>

				                            <button class="card-play-btn"><i class="fa-solid fa-play"></i></button>

				                        </div>

				                        <h4>Nightcall</h4>

				                        <p>Kavinsky • Drive Soundtrack</p>

				                    </div>

				                </div>

				            </section>

				        </main>

				        <!-- Player Bar -->

				        <footer class="player-bar">

				            <div class="now-playing">

				                <div class="now-playing-cover" id="currentCover">🔥</div>

				                <div class="now-playing-info">

				                    <h5 id="currentTitle">Today's Top Hits</h5>

				                    <span id="currentArtist">Global Charts</span>

				                </div>

				                <button class="like-btn" id="likeBtn"><i class="fa-regular fa-heart"></i></button>

				            </div>

				            <div class="player-controls">

				                <div class="control-buttons">

				                    <button class="sub-btn" id="shuffleBtn" title="Shuffle"><i class="fa-solid fa-shuffle"></i></button>

				                    <button class="sub-btn" id="prevBtn" title="Previous"><i class="fa-solid fa-backward-step"></i></button>

				                    <button class="main-play-btn" id="mainPlayBtn" title="Play/Pause"><i class="fa-solid fa-play"></i></button>

				                    <button class="sub-btn" id="nextBtn" title="Next"><i class="fa-solid fa-forward-step"></i></button>

				                    <button class="sub-btn" id="repeatBtn" title="Repeat"><i class="fa-solid fa-repeat"></i></button>

				                </div>

				                <div class="progress-container">

				                    <span class="time-stamp" id="currentTime">0:00</span>

				                    <div class="progress-bar-wrap" id="progressBarWrap">

				                        <div class="progress-bar-fill" id="progressBarFill" style="width: 25%;"></div>

				                    </div>

				                    <span class="time-stamp" id="totalDuration">3:42</span>

				                </div>

				            </div>

				            <div class="extra-controls">

				                <button class="sub-btn"><i class="fa-solid fa-list"></i></button>

				                <button class="sub-btn"><i class="fa-solid fa-volume-high"></i></button>

				                <div class="volume-slider-wrap" id="volumeWrap">

				                    <div class="volume-bar-fill" id="volumeFill" style="width: 75%;"></div>

				                </div>

				            </div>

				        </footer>

				    </div>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String css = """

				* {

				    margin: 0;

				    padding: 0;

				    box-sizing: border-box;

				    font-family: 'Plus Jakarta Sans', -apple-system, BlinkMacSystemFont, sans-serif;

				}

				body {

				    background-color: #000000;

				    color: #ffffff;

				    overflow: hidden;

				    height: 100vh;

				}

				.spotify-app {

				    display: grid;

				    grid-template-columns: 240px 1fr;

				    grid-template-rows: 1fr 90px;

				    height: 100vh;

				    width: 100vw;

				}

				/* Sidebar */

				.sidebar {

				    background-color: #000000;

				    padding: 24px 16px;

				    display: flex;

				    flex-direction: column;

				    gap: 20px;

				}

				.brand {

				    display: flex;

				    align-items: center;

				    gap: 8px;

				    font-size: 24px;

				    font-weight: 800;

				    color: #ffffff;

				    padding-left: 8px;

				}

				.brand i {

				    font-size: 32px;

				    color: #1db954;

				}

				.nav-links {

				    display: flex;

				    flex-direction: column;

				    gap: 8px;

				}

				.nav-item {

				    display: flex;

				    align-items: center;

				    gap: 16px;

				    color: #b3b3b3;

				    text-decoration: none;

				    font-weight: 600;

				    font-size: 14px;

				    padding: 10px 12px;

				    border-radius: 6px;

				    transition: all 0.2s ease;

				}

				.nav-item:hover, .nav-item.active {

				    color: #ffffff;

				    background-color: #1a1a1a;

				}

				.nav-item i {

				    font-size: 18px;

				}

				.sidebar-divider {

				    height: 1px;

				    background-color: #282828;

				    margin: 4px 8px;

				}

				.playlist-header {

				    font-size: 11px;

				    letter-spacing: 1px;

				    color: #a7a7a7;

				    font-weight: 700;

				    padding-left: 12px;

				}

				.playlist-list {

				    display: flex;

				    flex-direction: column;

				    gap: 6px;

				    overflow-y: auto;

				}

				.playlist-item {

				    font-size: 13.5px;

				    color: #b3b3b3;

				    padding: 8px 12px;

				    border-radius: 6px;

				    cursor: pointer;

				    transition: all 0.2s;

				    white-space: nowrap;

				    overflow: hidden;

				    text-overflow: ellipsis;

				}

				.playlist-item:hover {

				    color: #ffffff;

				    background-color: #181818;

				}

				.playlist-item.active {

				    color: #1db954;

				    font-weight: 600;

				}

				/* Main Content */

				.main-content {

				    background: linear-gradient(180deg, #1e1b4b 0%, #121212 280px);

				    overflow-y: auto;

				    padding: 16px 32px 32px;

				    border-radius: 8px;

				    margin: 8px 8px 0 0;

				}

				.top-nav {

				    display: flex;

				    align-items: center;

				    justify-content: space-between;

				    margin-bottom: 24px;

				    gap: 16px;

				}

				.nav-arrows {

				    display: flex;

				    gap: 8px;

				}

				.arrow-btn {

				    width: 32px;

				    height: 32px;

				    border-radius: 50%;

				    background-color: rgba(0,0,0,0.7);

				    border: none;

				    color: #ffffff;

				    cursor: pointer;

				}

				.search-box {

				    display: flex;

				    align-items: center;

				    gap: 10px;

				    background-color: #242424;

				    padding: 10px 16px;

				    border-radius: 500px;

				    width: 320px;

				}

				.search-box i { color: #b3b3b3; }

				.search-box input {

				    background: transparent;

				    border: none;

				    outline: none;

				    color: #ffffff;

				    font-size: 13px;

				    width: 100%;

				}

				.user-profile {

				    display: flex;

				    align-items: center;

				    gap: 8px;

				    background-color: rgba(0,0,0,0.6);

				    padding: 4px 12px 4px 4px;

				    border-radius: 500px;

				    font-size: 13px;

				    font-weight: 700;

				}

				.user-profile .avatar {

				    width: 28px;

				    height: 28px;

				    border-radius: 50%;

				    background-color: #535353;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				}

				.section-greeting {

				    font-size: 30px;

				    font-weight: 800;

				    margin-bottom: 20px;

				}

				.quick-grid {

				    display: grid;

				    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));

				    gap: 16px;

				    margin-bottom: 36px;

				}

				.quick-card {

				    display: flex;

				    align-items: center;

				    background-color: rgba(255,255,255,0.1);

				    border-radius: 6px;

				    overflow: hidden;

				    cursor: pointer;

				    position: relative;

				    transition: background 0.3s;

				}

				.quick-card:hover {

				    background-color: rgba(255,255,255,0.2);

				}

				.quick-cover {

				    width: 64px;

				    height: 64px;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    font-size: 24px;

				    flex-shrink: 0;

				}

				.quick-card span {

				    font-weight: 700;

				    font-size: 14px;

				    padding: 0 16px;

				}

				.play-bubble {

				    width: 44px;

				    height: 44px;

				    border-radius: 50%;

				    background-color: #1db954;

				    color: #000000;

				    border: none;

				    position: absolute;

				    right: 12px;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    box-shadow: 0 8px 16px rgba(0,0,0,0.3);

				    opacity: 0;

				    transform: translateY(8px);

				    transition: all 0.3s;

				    cursor: pointer;

				}

				.quick-card:hover .play-bubble {

				    opacity: 1;

				    transform: translateY(0);

				}

				.section-header {

				    display: flex;

				    justify-content: space-between;

				    align-items: flex-end;

				    margin-bottom: 16px;

				}

				.section-header h3 { font-size: 22px; font-weight: 700; }

				.section-header a { font-size: 12px; color: #b3b3b3; text-decoration: none; font-weight: 700; }

				.section-header a:hover { text-decoration: underline; }

				.cards-carousel {

				    display: grid;

				    grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));

				    gap: 20px;

				}

				.music-card {

				    background-color: #181818;

				    padding: 16px;

				    border-radius: 8px;

				    cursor: pointer;

				    transition: background 0.3s;

				    position: relative;

				}

				.music-card:hover {

				    background-color: #282828;

				}

				.card-img-wrap {

				    width: 100%;

				    aspect-ratio: 1/1;

				    border-radius: 6px;

				    margin-bottom: 14px;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    position: relative;

				    box-shadow: 0 8px 24px rgba(0,0,0,0.5);

				}

				.card-icon { font-size: 40px; }

				.card-play-btn {

				    position: absolute;

				    bottom: 8px;

				    right: 8px;

				    width: 44px;

				    height: 44px;

				    border-radius: 50%;

				    background-color: #1db954;

				    color: #000;

				    border: none;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    opacity: 0;

				    transform: translateY(8px);

				    transition: all 0.25s ease;

				    cursor: pointer;

				}

				.music-card:hover .card-play-btn {

				    opacity: 1;

				    transform: translateY(0);

				}

				.music-card h4 {

				    font-size: 14px;

				    font-weight: 700;

				    margin-bottom: 6px;

				    white-space: nowrap;

				    overflow: hidden;

				    text-overflow: ellipsis;

				}

				.music-card p {

				    font-size: 12px;

				    color: #a7a7a7;

				    white-space: nowrap;

				    overflow: hidden;

				    text-overflow: ellipsis;

				}

				/* Player Bar */

				.player-bar {

				    grid-column: 1 / -1;

				    background-color: #181818;

				    border-top: 1px solid #282828;

				    display: flex;

				    align-items: center;

				    justify-content: space-between;

				    padding: 0 20px;

				}

				.now-playing {

				    display: flex;

				    align-items: center;

				    gap: 14px;

				    width: 250px;

				}

				.now-playing-cover {

				    width: 56px;

				    height: 56px;

				    border-radius: 4px;

				    background: #333;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    font-size: 24px;

				}

				.now-playing-info h5 { font-size: 13.5px; font-weight: 600; margin-bottom: 2px; }

				.now-playing-info span { font-size: 11.5px; color: #a7a7a7; }

				.like-btn {

				    background: transparent;

				    border: none;

				    color: #b3b3b3;

				    cursor: pointer;

				    font-size: 16px;

				}

				.like-btn.active { color: #1db954; }

				.player-controls {

				    display: flex;

				    flex-direction: column;

				    align-items: center;

				    gap: 6px;

				    max-width: 500px;

				    width: 45%;

				}

				.control-buttons {

				    display: flex;

				    align-items: center;

				    gap: 18px;

				}

				.sub-btn {

				    background: transparent;

				    border: none;

				    color: #b3b3b3;

				    font-size: 14px;

				    cursor: pointer;

				    transition: color 0.2s;

				}

				.sub-btn:hover { color: #ffffff; }

				.main-play-btn {

				    width: 36px;

				    height: 36px;

				    border-radius: 50%;

				    background: #ffffff;

				    color: #000000;

				    border: none;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    cursor: pointer;

				    transition: transform 0.2s;

				}

				.main-play-btn:hover { transform: scale(1.08); }

				.progress-container {

				    display: flex;

				    align-items: center;

				    gap: 10px;

				    width: 100%;

				}

				.time-stamp { font-size: 11px; color: #a7a7a7; min-width: 32px; text-align: center; }

				.progress-bar-wrap {

				    flex: 1;

				    height: 4px;

				    background-color: #4d4d4d;

				    border-radius: 2px;

				    cursor: pointer;

				    position: relative;

				}

				.progress-bar-fill {

				    height: 100%;

				    background-color: #ffffff;

				    border-radius: 2px;

				    transition: background 0.2s;

				}

				.progress-bar-wrap:hover .progress-bar-fill {

				    background-color: #1db954;

				}

				.extra-controls {

				    display: flex;

				    align-items: center;

				    gap: 12px;

				    width: 200px;

				    justify-content: flex-end;

				}

				.volume-slider-wrap {

				    width: 80px;

				    height: 4px;

				    background: #4d4d4d;

				    border-radius: 2px;

				    cursor: pointer;

				}

				.volume-bar-fill {

				    height: 100%;

				    background: #ffffff;

				    border-radius: 2px;

				}

				""";

		String js = """

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

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

	/*
	 * =========================================================================
	 * 
	 * 2. PERSONAL PORTFOLIO
	 * 
	 * =========================================================================
	 */

	public WebsitePackage generatePortfolioSite(String prompt) {

		String slug = "personal-portfolio";

		String title = "Portfolio • Software Engineer & AI Specialist";

		String html = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>Priyank • Full Stack Developer & AI Engineer</title>

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&family=JetBrains+Mono:wght@400;500;600&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <!-- Header Navigation -->

				    <header class="navbar">

				        <a href="#" class="logo">

				            <span class="logo-badge">&lt;/&gt;</span>

				            <span class="logo-text">Priyank<strong>.dev</strong></span>

				        </a>

				        <nav class="nav-links">

				            <a href="#about">About</a>

				            <a href="#skills">Skills</a>

				            <a href="#projects">Projects</a>

				            <a href="#experience">Experience</a>

				            <a href="#contact" class="btn-primary">Get In Touch</a>

				        </nav>

				    </header>

				    <!-- Hero Section -->

				    <section class="hero-section">

				        <div class="hero-badge">

				            <span class="status-indicator"></span>

				            Available for New Projects & Freelance

				        </div>

				        <h1 class="hero-title">

				            Crafting Intelligent Web Apps & <span class="gradient-text">Autonomous AI Agents</span>

				        </h1>

				        <p class="hero-subtitle">

				            Senior Full-Stack Engineer specializing in Spring Boot, Spring AI, React, Next.js, and multi-tool LLM architectures. Turning complex engineering into seamless user experiences.

				        </p>

				        <div class="hero-cta-group">

				            <a href="#projects" class="btn-primary btn-large">

				                <i class="fa-solid fa-code"></i> Explore Work

				            </a>

				            <a href="#contact" class="btn-secondary btn-large">

				                <i class="fa-regular fa-paper-plane"></i> Contact Me

				            </a>

				        </div>

				        <div class="stats-banner">

				            <div class="stat-card">

				                <h3>5+</h3>

				                <p>Years Experience</p>

				            </div>

				            <div class="stat-card">

				                <h3>40+</h3>

				                <p>Shipped Projects</p>

				            </div>

				            <div class="stat-card">

				                <h3>99.9%</h3>

				                <p>Code Quality & Uptime</p>

				            </div>

				            <div class="stat-card">

				                <h3>100%</h3>

				                <p>Client Satisfaction</p>

				            </div>

				        </div>

				    </section>

				    <!-- Skills Section -->

				    <section class="section" id="skills">

				        <div class="section-title-wrap">

				            <span class="pill-label">TECH STACK</span>

				            <h2>Core Competencies & Technologies</h2>

				        </div>

				        <div class="skills-grid">

				            <div class="skill-card">

				                <div class="skill-icon" style="background:#e0f2fe; color:#0284c7;"><i class="fa-solid fa-brain"></i></div>

				                <h3>AI & LLM Integration</h3>

				                <p>Spring AI, OpenAI API, Anthropic, OpenRouter, Tool Calling, Vector Stores & RAG.</p>

				            </div>

				            <div class="skill-card">

				                <div class="skill-icon" style="background:#ecfdf5; color:#059669;"><i class="fa-brands fa-java"></i></div>

				                <h3>Backend Architecture</h3>

				                <p>Java, Spring Boot 3, RESTful Microservices, PostgreSQL, Redis, Hibernate.</p>

				            </div>

				            <div class="skill-card">

				                <div class="skill-icon" style="background:#f5f3ff; color:#7c3aed;"><i class="fa-brands fa-react"></i></div>

				                <h3>Frontend Mastery</h3>

				                <p>React.js, Next.js, TypeScript, Vanilla CSS3, Responsive Design, WebSockets.</p>

				            </div>

				            <div class="skill-card">

				                <div class="skill-icon" style="background:#fff1f2; color:#e11d48;"><i class="fa-solid fa-cloud"></i></div>

				                <h3>Cloud & DevOps</h3>

				                <p>Docker, Kubernetes, AWS, CI/CD Pipelines, Linux Server Administration.</p>

				            </div>

				        </div>

				    </section>

				    <!-- Projects Section -->

				    <section class="section" id="projects">

				        <div class="section-title-wrap">

				            <span class="pill-label">SHOWCASE</span>

				            <h2>Featured Projects</h2>

				        </div>

				        <div class="project-filters">

				            <button class="filter-btn active" data-filter="all">All Projects</button>

				            <button class="filter-btn" data-filter="ai">AI Agents</button>

				            <button class="filter-btn" data-filter="fullstack">Full Stack</button>

				            <button class="filter-btn" data-filter="web">Web Apps</button>

				        </div>

				        <div class="projects-grid">

				            <div class="project-card" data-category="ai">

				                <div class="project-img" style="background: linear-gradient(135deg, #4f46e5, #06b6d4);">

				                    <span class="project-badge">Spring AI + LLM</span>

				                </div>

				                <div class="project-content">

				                    <h3>Nexus AI Agent Studio</h3>

				                    <p>Autonomous multi-tool agent equipped with real-time weather querying, FX currency exchange, precision math reasoning, and website cloning.</p>

				                    <div class="tag-list">

				                        <span>Spring Boot</span><span>Spring AI</span><span>Vanilla CSS</span>

				                    </div>

				                    <div class="project-links">

				                        <a href="#" class="btn-sm"><i class="fa-solid fa-arrow-up-right-from-square"></i> Live Demo</a>

				                        <a href="#" class="btn-sm-ghost"><i class="fa-brands fa-github"></i> GitHub</a>

				                    </div>

				                </div>

				            </div>

				            <div class="project-card" data-category="fullstack">

				                <div class="project-img" style="background: linear-gradient(135deg, #059669, #10b981);">

				                    <span class="project-badge">Full Stack</span>

				                </div>

				                <div class="project-content">

				                    <h3>Enterprise Cloud Analytics</h3>

				                    <p>High-throughput telemetry dashboard tracking millions of events per second with sub-second querying and automated alerts.</p>

				                    <div class="tag-list">

				                        <span>Java</span><span>PostgreSQL</span><span>Next.js</span>

				                    </div>

				                    <div class="project-links">

				                        <a href="#" class="btn-sm"><i class="fa-solid fa-arrow-up-right-from-square"></i> Live Demo</a>

				                        <a href="#" class="btn-sm-ghost"><i class="fa-brands fa-github"></i> GitHub</a>

				                    </div>

				                </div>

				            </div>

				            <div class="project-card" data-category="web">

				                <div class="project-img" style="background: linear-gradient(135deg, #d97706, #dc2626);">

				                    <span class="project-badge">FinTech</span>

				                </div>

				                <div class="project-content">

				                    <h3>Real-time FX & Crypto Terminal</h3>

				                    <p>Institutional grade foreign exchange and cryptocurrency live order book with interactive charts and automated arbitrage calculator.</p>

				                    <div class="tag-list">

				                        <span>WebSockets</span><span>Chart.js</span><span>Redis</span>

				                    </div>

				                    <div class="project-links">

				                        <a href="#" class="btn-sm"><i class="fa-solid fa-arrow-up-right-from-square"></i> Live Demo</a>

				                        <a href="#" class="btn-sm-ghost"><i class="fa-brands fa-github"></i> GitHub</a>

				                    </div>

				                </div>

				            </div>

				        </div>

				    </section>

				    <!-- Contact Section -->

				    <section class="section" id="contact">

				        <div class="contact-card">

				            <h2>Let's build something extraordinary together</h2>

				            <p>Have a project in mind or interested in collaboration? Feel free to reach out directly.</p>

				            <form class="contact-form" id="contactForm">

				                <div class="form-row">

				                    <input type="text" placeholder="Your Name" required>

				                    <input type="email" placeholder="Your Email Address" required>

				                </div>

				                <textarea placeholder="Tell me about your project or inquiry..." rows="4" required></textarea>

				                <button type="submit" class="btn-primary btn-large" style="width:100%; justify-content:center;">

				                    <i class="fa-regular fa-paper-plane"></i> Send Message

				                </button>

				            </form>

				        </div>

				    </section>

				    <!-- Footer -->

				    <footer class="footer">

				        <p>&copy; 2026 Priyank. Built with passion & clean code.</p>

				        <div class="social-links">

				            <a href="#"><i class="fa-brands fa-github"></i></a>

				            <a href="#"><i class="fa-brands fa-linkedin"></i></a>

				            <a href="#"><i class="fa-brands fa-twitter"></i></a>

				        </div>

				    </footer>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String css = """

				:root {

				    --bg: #090d16;

				    --surface: #111827;

				    --surface-hover: #1f2937;

				    --primary: #6366f1;

				    --primary-hover: #4f46e5;

				    --text-primary: #f9fafb;

				    --text-muted: #9ca3af;

				    --border: #374151;

				}

				* {

				    margin: 0;

				    padding: 0;

				    box-sizing: border-box;

				    font-family: 'Plus Jakarta Sans', sans-serif;

				    scroll-behavior: smooth;

				}

				body {

				    background-color: var(--bg);

				    color: var(--text-primary);

				    line-height: 1.6;

				}

				.navbar {

				    display: flex;

				    justify-content: space-between;

				    align-items: center;

				    padding: 20px 48px;

				    background: rgba(9, 13, 22, 0.85);

				    backdrop-filter: blur(12px);

				    position: sticky;

				    top: 0;

				    z-index: 100;

				    border-bottom: 1px solid rgba(255,255,255,0.06);

				}

				.logo {

				    display: flex;

				    align-items: center;

				    gap: 10px;

				    text-decoration: none;

				    color: #fff;

				    font-size: 20px;

				    font-weight: 700;

				}

				.logo-badge {

				    background: var(--primary);

				    color: #fff;

				    padding: 4px 8px;

				    border-radius: 6px;

				    font-family: 'JetBrains Mono', monospace;

				    font-size: 13px;

				}

				.nav-links {

				    display: flex;

				    align-items: center;

				    gap: 28px;

				}

				.nav-links a {

				    color: var(--text-muted);

				    text-decoration: none;

				    font-weight: 500;

				    font-size: 14.5px;

				    transition: color 0.2s;

				}

				.nav-links a:hover { color: #fff; }

				.btn-primary {

				    background: var(--primary);

				    color: #fff !important;

				    padding: 10px 20px;

				    border-radius: 8px;

				    font-weight: 600;

				    transition: all 0.2s;

				    border: none;

				    cursor: pointer;

				    display: inline-flex;

				    align-items: center;

				    gap: 8px;

				    text-decoration: none;

				}

				.btn-primary:hover {

				    background: var(--primary-hover);

				    transform: translateY(-2px);

				    box-shadow: 0 8px 20px rgba(99, 102, 241, 0.3);

				}

				.btn-secondary {

				    background: var(--surface);

				    color: #fff !important;

				    padding: 10px 20px;

				    border-radius: 8px;

				    font-weight: 600;

				    border: 1px solid var(--border);

				    cursor: pointer;

				    display: inline-flex;

				    align-items: center;

				    gap: 8px;

				    text-decoration: none;

				    transition: all 0.2s;

				}

				.btn-secondary:hover {

				    background: var(--surface-hover);

				    border-color: #6b7280;

				}

				.btn-large {

				    padding: 14px 28px;

				    font-size: 15px;

				    border-radius: 10px;

				}

				.hero-section {

				    padding: 80px 24px 60px;

				    max-width: 900px;

				    margin: 0 auto;

				    text-align: center;

				    display: flex;

				    flex-direction: column;

				    align-items: center;

				}

				.hero-badge {

				    display: inline-flex;

				    align-items: center;

				    gap: 8px;

				    background: rgba(99, 102, 241, 0.12);

				    border: 1px solid rgba(99, 102, 241, 0.3);

				    color: #a5b4fc;

				    padding: 6px 16px;

				    border-radius: 500px;

				    font-size: 13px;

				    font-weight: 600;

				    margin-bottom: 24px;

				}

				.status-indicator {

				    width: 8px;

				    height: 8px;

				    background: #10b981;

				    border-radius: 50%;

				    box-shadow: 0 0 10px #10b981;

				}

				.hero-title {

				    font-size: 48px;

				    font-weight: 800;

				    line-height: 1.18;

				    margin-bottom: 20px;

				    letter-spacing: -0.5px;

				}

				.gradient-text {

				    background: linear-gradient(135deg, #818cf8, #38bdf8, #ec4899);

				    -webkit-background-clip: text;

				    -webkit-text-fill-color: transparent;

				}

				.hero-subtitle {

				    font-size: 17px;

				    color: var(--text-muted);

				    max-width: 700px;

				    margin-bottom: 32px;

				}

				.hero-cta-group {

				    display: flex;

				    gap: 16px;

				    margin-bottom: 60px;

				}

				.stats-banner {

				    display: grid;

				    grid-template-columns: repeat(4, 1fr);

				    gap: 24px;

				    width: 100%;

				    background: var(--surface);

				    border: 1px solid var(--border);

				    padding: 24px 32px;

				    border-radius: 16px;

				}

				.stat-card h3 {

				    font-size: 32px;

				    font-weight: 800;

				    color: #818cf8;

				}

				.stat-card p {

				    font-size: 13px;

				    color: var(--text-muted);

				}

				.section {

				    padding: 80px 48px;

				    max-width: 1200px;

				    margin: 0 auto;

				}

				.section-title-wrap {

				    text-align: center;

				    margin-bottom: 48px;

				}

				.pill-label {

				    font-size: 11.5px;

				    letter-spacing: 1.5px;

				    font-weight: 700;

				    color: var(--primary);

				    background: rgba(99,102,241,0.1);

				    padding: 4px 12px;

				    border-radius: 500px;

				}

				.section-title-wrap h2 {

				    font-size: 32px;

				    font-weight: 800;

				    margin-top: 10px;

				}

				.skills-grid {

				    display: grid;

				    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));

				    gap: 24px;

				}

				.skill-card {

				    background: var(--surface);

				    border: 1px solid var(--border);

				    padding: 28px;

				    border-radius: 14px;

				    transition: all 0.3s;

				}

				.skill-card:hover {

				    transform: translateY(-4px);

				    border-color: var(--primary);

				}

				.skill-icon {

				    width: 48px;

				    height: 48px;

				    border-radius: 10px;

				    display: flex;

				    align-items: center;

				    justify-content: center;

				    font-size: 22px;

				    margin-bottom: 16px;

				}

				.skill-card h3 { font-size: 18px; margin-bottom: 8px; }

				.skill-card p { font-size: 13.5px; color: var(--text-muted); }

				.project-filters {

				    display: flex;

				    justify-content: center;

				    gap: 12px;

				    margin-bottom: 32px;

				}

				.filter-btn {

				    background: var(--surface);

				    border: 1px solid var(--border);

				    color: var(--text-muted);

				    padding: 8px 18px;

				    border-radius: 500px;

				    cursor: pointer;

				    font-weight: 600;

				    font-size: 13px;

				    transition: all 0.2s;

				}

				.filter-btn.active, .filter-btn:hover {

				    background: var(--primary);

				    color: #fff;

				    border-color: var(--primary);

				}

				.projects-grid {

				    display: grid;

				    grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));

				    gap: 28px;

				}

				.project-card {

				    background: var(--surface);

				    border: 1px solid var(--border);

				    border-radius: 16px;

				    overflow: hidden;

				    transition: all 0.3s;

				}

				.project-card:hover {

				    transform: translateY(-6px);

				    box-shadow: 0 16px 32px rgba(0,0,0,0.4);

				    border-color: #6b7280;

				}

				.project-img {

				    height: 180px;

				    position: relative;

				    padding: 16px;

				}

				.project-badge {

				    background: rgba(0,0,0,0.6);

				    backdrop-filter: blur(8px);

				    padding: 4px 10px;

				    border-radius: 6px;

				    font-size: 11px;

				    font-weight: 600;

				}

				.project-content {

				    padding: 24px;

				}

				.project-content h3 { font-size: 19px; margin-bottom: 8px; }

				.project-content p { font-size: 13.5px; color: var(--text-muted); margin-bottom: 16px; }

				.tag-list {

				    display: flex;

				    gap: 8px;

				    flex-wrap: wrap;

				    margin-bottom: 20px;

				}

				.tag-list span {

				    background: rgba(255,255,255,0.06);

				    padding: 4px 10px;

				    border-radius: 6px;

				    font-size: 11.5px;

				    font-family: 'JetBrains Mono', monospace;

				}

				.project-links {

				    display: flex;

				    gap: 12px;

				}

				.btn-sm {

				    background: var(--primary);

				    color: #fff;

				    padding: 6px 14px;

				    border-radius: 6px;

				    font-size: 12.5px;

				    font-weight: 600;

				    text-decoration: none;

				}

				.btn-sm-ghost {

				    background: transparent;

				    border: 1px solid var(--border);

				    color: #fff;

				    padding: 6px 14px;

				    border-radius: 6px;

				    font-size: 12.5px;

				    font-weight: 600;

				    text-decoration: none;

				}

				.contact-card {

				    background: linear-gradient(135deg, #1e1b4b, #111827);

				    border: 1px solid var(--border);

				    border-radius: 20px;

				    padding: 48px;

				    text-align: center;

				    max-width: 700px;

				    margin: 0 auto;

				}

				.contact-card h2 { font-size: 28px; margin-bottom: 10px; }

				.contact-card p { color: var(--text-muted); margin-bottom: 28px; }

				.contact-form {

				    display: flex;

				    flex-direction: column;

				    gap: 14px;

				}

				.form-row {

				    display: grid;

				    grid-template-columns: 1fr 1fr;

				    gap: 14px;

				}

				.contact-form input, .contact-form textarea {

				    background: rgba(0,0,0,0.4);

				    border: 1px solid var(--border);

				    padding: 12px 16px;

				    border-radius: 8px;

				    color: #fff;

				    font-size: 14px;

				    outline: none;

				}

				.contact-form input:focus, .contact-form textarea:focus {

				    border-color: var(--primary);

				}

				.footer {

				    border-top: 1px solid var(--border);

				    padding: 32px 48px;

				    display: flex;

				    justify-content: space-between;

				    align-items: center;

				    color: var(--text-muted);

				    font-size: 13.5px;

				}

				.social-links {

				    display: flex;

				    gap: 18px;

				}

				.social-links a {

				    color: var(--text-muted);

				    font-size: 18px;

				    transition: color 0.2s;

				}

				.social-links a:hover { color: #fff; }

				""";

		String js = """

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

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

	/*
	 * =========================================================================
	 * 
	 * 3. NETFLIX CLONE
	 * 
	 * =========================================================================
	 */

	public WebsitePackage generateNetflixClone() {

		String slug = "netflix-clone";

		String title = "Netflix • Stream Movies & Series Clone";

		String html = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>Netflix • Watch TV Shows & Movies</title>

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;600;700;800&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <header class="netflix-header">

				        <div class="left-nav">

				            <span class="netflix-logo">NETFLIX</span>

				            <nav class="nav-links">

				                <a href="#" class="active">Home</a>

				                <a href="#">TV Shows</a>

				                <a href="#">Movies</a>

				                <a href="#">New & Popular</a>

				                <a href="#">My List</a>

				            </nav>

				        </div>

				        <div class="right-nav">

				            <i class="fa-solid fa-magnifying-glass"></i>

				            <i class="fa-regular fa-bell"></i>

				            <div class="user-badge">P</div>

				        </div>

				    </header>

				    <section class="billboard">

				        <div class="billboard-content">

				            <div class="series-badge"><span class="n-logo">N</span> SERIES</div>

				            <h1 class="movie-title">STRANGER THINGS 5</h1>

				            <p class="movie-desc">

				                When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces and one strange little girl.

				            </p>

				            <div class="billboard-btns">

				                <button class="btn-play" onclick="openTrailer('Stranger Things 5')"><i class="fa-solid fa-play"></i> Play</button>

				                <button class="btn-info" onclick="openTrailer('Stranger Things 5')"><i class="fa-solid fa-circle-info"></i> More Info</button>

				            </div>

				        </div>

				    </section>

				    <main class="rows-container">

				        <section class="movie-row">

				            <h2>Trending Now</h2>

				            <div class="row-posters">

				                <div class="poster-card" style="background: linear-gradient(135deg, #e11d48, #4f46e5);" onclick="openTrailer('Cyberpunk: Edgerunners')">

				                    <span class="card-emoji">🤖</span>

				                    <div class="poster-info">

				                        <h4>Cyberpunk: Edgerunners</h4>

				                        <span>98% Match • Sci-Fi</span>

				                    </div>

				                </div>

				                <div class="poster-card" style="background: linear-gradient(135deg, #059669, #0284c7);" onclick="openTrailer('Squid Game: The Challenge')">

				                    <span class="card-emoji">🦑</span>

				                    <div class="poster-info">

				                        <h4>Squid Game Season 2</h4>

				                        <span>95% Match • Thriller</span>

				                    </div>

				                </div>

				                <div class="poster-card" style="background: linear-gradient(135deg, #7c3aed, #db2777);" onclick="openTrailer('Wednesday')">

				                    <span class="card-emoji">🖤</span>

				                    <div class="poster-info">

				                        <h4>Wednesday</h4>

				                        <span>97% Match • Mystery</span>

				                    </div>

				                </div>

				                <div class="poster-card" style="background: linear-gradient(135deg, #d97706, #dc2626);" onclick="openTrailer('Interstellar')">

				                    <span class="card-emoji">🚀</span>

				                    <div class="poster-info">

				                        <h4>Interstellar</h4>

				                        <span>99% Match • Space</span>

				                    </div>

				                </div>

				            </div>

				        </section>

				        <section class="movie-row">

				            <h2>Top 10 Movies Today</h2>

				            <div class="row-posters">

				                <div class="poster-card" style="background: linear-gradient(135deg, #2563eb, #1e40af);" onclick="openTrailer('Inception')">

				                    <span class="card-emoji">🌀</span>

				                    <div class="poster-info">

				                        <h4>Inception</h4>

				                        <span>#1 in Movies Today</span>

				                    </div>

				                </div>

				                <div class="poster-card" style="background: linear-gradient(135deg, #ca8a04, #991b1b);" onclick="openTrailer('Dune: Part Two')">

				                    <span class="card-emoji">🪐</span>

				                    <div class="poster-info">

				                        <h4>Dune: Part Two</h4>

				                        <span>#2 in Movies Today</span>

				                    </div>

				                </div>

				                <div class="poster-card" style="background: linear-gradient(135deg, #475569, #0f172a);" onclick="openTrailer('The Dark Knight')">

				                    <span class="card-emoji">🦇</span>

				                    <div class="poster-info">

				                        <h4>The Dark Knight</h4>

				                        <span>#3 in Movies Today</span>

				                    </div>

				                </div>

				            </div>

				        </section>

				    </main>

				    <div class="modal-overlay" id="trailerModal">

				        <div class="modal-box">

				            <h3 id="modalTitle">Movie Title</h3>

				            <p style="color:#aaa; margin: 12px 0 24px;">Now streaming in Ultra HD 4K with Dolby Atmos Spatial Audio.</p>

				            <div style="background:#000; height:180px; border-radius:8px; display:flex; align-items:center; justify-content:center; font-size:40px;">🎬 Live Player Preview</div>

				            <button class="btn-play" style="margin-top:20px; width:100%; justify-content:center;" onclick="closeModal()">Close Preview</button>

				        </div>

				    </div>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String css = """

				* { margin:0; padding:0; box-sizing:border-box; font-family:'Plus Jakarta Sans',sans-serif; }

				body { background-color: #141414; color: #fff; overflow-x: hidden; }

				.netflix-header {

				    display: flex;

				    justify-content: space-between;

				    align-items: center;

				    padding: 18px 48px;

				    position: fixed;

				    top: 0;

				    width: 100%;

				    z-index: 100;

				    background: linear-gradient(180deg, rgba(0,0,0,0.8) 0%, transparent 100%);

				}

				.netflix-logo { color: #e50914; font-size: 26px; font-weight: 900; letter-spacing: 2px; }

				.left-nav { display: flex; align-items: center; gap: 32px; }

				.nav-links { display: flex; gap: 20px; }

				.nav-links a { color: #e5e5e5; text-decoration: none; font-size: 14px; transition: color 0.2s; }

				.nav-links a.active, .nav-links a:hover { color: #fff; font-weight: 700; }

				.right-nav { display: flex; align-items: center; gap: 20px; font-size: 18px; cursor: pointer; }

				.user-badge { width: 32px; height: 32px; background: #e50914; border-radius: 4px; display:flex; align-items:center; justify-content:center; font-weight:700; font-size:14px; }

				.billboard {

				    height: 75vh;

				    background: linear-gradient(90deg, rgba(20,20,20,0.95) 0%, rgba(20,20,20,0.4) 60%, transparent 100%), linear-gradient(135deg, #7f1d1d, #1e1b4b);

				    display: flex;

				    align-items: center;

				    padding: 0 48px;

				}

				.billboard-content { max-width: 600px; }

				.series-badge { display: flex; align-items: center; gap: 6px; font-weight: 700; font-size: 13px; letter-spacing: 3px; color: #aaa; margin-bottom: 12px; }

				.n-logo { color: #e50914; font-weight: 900; font-size: 18px; }

				.movie-title { font-size: 48px; font-weight: 900; line-height: 1.1; margin-bottom: 16px; }

				.movie-desc { font-size: 15px; color: #ddd; line-height: 1.5; margin-bottom: 24px; }

				.billboard-btns { display: flex; gap: 14px; }

				.btn-play { background: #fff; color: #000; border: none; padding: 10px 24px; border-radius: 4px; font-weight: 700; font-size: 15px; cursor: pointer; display:flex; align-items:center; gap:8px; }

				.btn-info { background: rgba(109,109,110,0.7); color: #fff; border: none; padding: 10px 24px; border-radius: 4px; font-weight: 700; font-size: 15px; cursor: pointer; display:flex; align-items:center; gap:8px; }

				.rows-container { padding: 24px 48px 60px; margin-top: -80px; position: relative; z-index: 10; }

				.movie-row { margin-bottom: 36px; }

				.movie-row h2 { font-size: 20px; font-weight: 700; margin-bottom: 14px; }

				.row-posters { display: grid; grid-template-columns: repeat(auto-fill, minmax(220px, 1fr)); gap: 14px; }

				.poster-card { height: 130px; border-radius: 6px; padding: 14px; display: flex; flex-direction: column; justify-content: space-between; cursor: pointer; transition: transform 0.3s; position: relative; }

				.poster-card:hover { transform: scale(1.06); }

				.card-emoji { font-size: 32px; }

				.poster-info h4 { font-size: 14px; font-weight: 700; }

				.poster-info span { font-size: 11px; color: #eee; }

				.modal-overlay { position: fixed; inset: 0; background: rgba(0,0,0,0.8); display: none; align-items: center; justify-content: center; z-index: 200; }

				.modal-box { background: #181818; padding: 32px; border-radius: 12px; width: 480px; max-width: 90vw; }

				""";

		String js = """

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

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

	/*
	 * =========================================================================
	 * 
	 * 4. SAAS STARTUP LANDING
	 * 
	 * =========================================================================
	 */

	public WebsitePackage generateSaaSLanding() {

		String slug = "saas-landing";

		String title = "FlowPilot • AI Workflow Automation";

		String html = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>FlowPilot • AI Workflow Automation</title>

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <header class="navbar">

				        <div class="logo">⚡ FlowPilot</div>

				        <nav class="nav">

				            <a href="#features">Features</a>

				            <a href="#pricing">Pricing</a>

				            <a href="#" class="btn-primary">Start Free Trial</a>

				        </nav>

				    </header>

				    <section class="hero">

				        <div class="badge">🚀 v2.0 Released with Autonomous Multi-Agent Workflows</div>

				        <h1>Automate Any Workflow with <span class="gradient">Intelligent AI Agents</span></h1>

				        <p>Deploy production-ready LLM agents that read data, call APIs, and execute complex workflows without writing repetitive code.</p>

				        <div class="cta-row">

				            <button class="btn-primary btn-lg">Get Started Free</button>

				            <button class="btn-secondary btn-lg"><i class="fa-solid fa-play"></i> Watch 2-min Demo</button>

				        </div>

				    </section>

				    <section class="pricing-section" id="pricing">

				        <h2>Transparent, Predictable Pricing</h2>

				        <p style="color:#64748b; margin-bottom: 24px;">Start free and scale as your automation grows.</p>

				        <div class="billing-toggle">

				            <span>Monthly</span>

				            <input type="checkbox" id="priceToggle">

				            <span>Yearly (Save 20%)</span>

				        </div>

				        <div class="pricing-grid">

				            <div class="price-card">

				                <h3>Starter</h3>

				                <div class="price" id="priceStarter">$29<span>/mo</span></div>

				                <ul class="features">

				                    <li>✓ 5 Active AI Agents</li>

				                    <li>✓ 10,000 Tool Executions</li>

				                    <li>✓ Standard API Access</li>

				                </ul>

				                <button class="btn-secondary" style="width:100%;">Choose Starter</button>

				            </div>

				            <div class="price-card popular">

				                <div class="popular-badge">MOST POPULAR</div>

				                <h3>Pro Studio</h3>

				                <div class="price" id="pricePro">$79<span>/mo</span></div>

				                <ul class="features">

				                    <li>✓ Unlimited AI Agents</li>

				                    <li>✓ 250,000 Tool Executions</li>

				                    <li>✓ Real-time Webhooks & Integrations</li>

				                    <li>✓ Priority 24/7 Support</li>

				                </ul>

				                <button class="btn-primary" style="width:100%;">Start 14-Day Free Trial</button>

				            </div>

				            <div class="price-card">

				                <h3>Enterprise</h3>

				                <div class="price">Custom</div>

				                <ul class="features">

				                    <li>✓ Dedicated Private LLM Cluster</li>

				                    <li>✓ Custom On-premise Deployments</li>

				                    <li>✓ 99.99% SLA Guarantee</li>

				                </ul>

				                <button class="btn-secondary" style="width:100%;">Contact Sales</button>

				            </div>

				        </div>

				    </section>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String css = """

				* { margin:0; padding:0; box-sizing:border-box; font-family:'Plus Jakarta Sans',sans-serif; }

				body { background:#0f172a; color:#f8fafc; line-height:1.6; }

				.navbar { display:flex; justify-content:space-between; align-items:center; padding:20px 48px; border-bottom:1px solid #1e293b; }

				.logo { font-size:22px; font-weight:800; color:#38bdf8; }

				.nav { display:flex; gap:24px; align-items:center; }

				.nav a { color:#94a3b8; text-decoration:none; font-weight:600; }

				.btn-primary { background:#38bdf8; color:#0f172a; padding:10px 22px; border-radius:8px; font-weight:700; border:none; cursor:pointer; text-decoration:none; }

				.btn-secondary { background:#1e293b; color:#fff; padding:10px 22px; border-radius:8px; font-weight:700; border:1px solid #334155; cursor:pointer; }

				.btn-lg { padding:14px 28px; font-size:16px; border-radius:10px; }

				.hero { text-align:center; padding:90px 24px 60px; max-width:850px; margin:0 auto; }

				.badge { display:inline-block; background:rgba(56,189,248,0.1); border:1px solid rgba(56,189,248,0.3); color:#38bdf8; padding:6px 16px; border-radius:500px; font-size:13px; font-weight:600; margin-bottom:24px; }

				.hero h1 { font-size:48px; font-weight:800; line-height:1.2; margin-bottom:20px; }

				.gradient { background:linear-gradient(135deg, #38bdf8, #818cf8, #c084fc); -webkit-background-clip:text; -webkit-text-fill-color:transparent; }

				.hero p { font-size:17px; color:#94a3b8; margin-bottom:32px; }

				.cta-row { display:flex; justify-content:center; gap:16px; }

				.pricing-section { padding:80px 48px; text-align:center; max-width:1100px; margin:0 auto; }

				.billing-toggle { display:flex; justify-content:center; align-items:center; gap:12px; margin-bottom:40px; font-weight:600; }

				.pricing-grid { display:grid; grid-template-columns:repeat(auto-fit, minmax(280px, 1fr)); gap:24px; }

				.price-card { background:#1e293b; border:1px solid #334155; padding:36px 28px; border-radius:16px; text-align:left; position:relative; }

				.price-card.popular { border-color:#38bdf8; transform:scale(1.04); background:#1e293b; }

				.popular-badge { position:absolute; top:-12px; left:50%; transform:translateX(-50%); background:#38bdf8; color:#0f172a; padding:4px 12px; border-radius:500px; font-size:11px; font-weight:800; }

				.price { font-size:36px; font-weight:800; margin:16px 0; }

				.price span { font-size:14px; color:#94a3b8; }

				.features { list-style:none; margin-bottom:24px; display:flex; flex-direction:column; gap:12px; font-size:14px; color:#cbd5e1; }

				""";

		String js = """

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

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

	/*
	 * =========================================================================
	 * 
	 * 5. COFFEE SHOP & BISTRO
	 * 
	 * =========================================================================
	 */

	public WebsitePackage generateCoffeeShop() {

		String slug = "coffee-showcase";

		String title = "BrewLab • Artisanal Roasters & Cafe";

		String html = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>BrewLab • Artisanal Specialty Roasters</title>

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <header class="header">

				        <div class="brand">☕ BrewLab Artisans</div>

				        <div class="cart-btn" onclick="toggleCart()">🛒 Cart (<span id="cartCount">0</span>)</div>

				    </header>

				    <section class="hero">

				        <h1>Handcrafted Coffee, Roasted with Passion</h1>

				        <p>Ethically sourced single-origin beans, precision brewed to perfection.</p>

				    </section>

				    <main class="menu-grid">

				        <div class="item-card">

				            <h3>Ethiopian Yirgacheffe Pour-Over</h3>

				            <p>Floral notes of bergamot, jasmine, and citrus lemon zest.</p>

				            <div class="item-bottom">

				                <span class="price">$5.50</span>

				                <button class="btn-add" onclick="addToCart('Ethiopian Pour-Over', 5.50)">Add to Order</button>

				            </div>

				        </div>

				        <div class="item-card">

				            <h3>Caramel Macchiato Nitro Brew</h3>

				            <p>Cold brewed for 18 hours with velvety organic oat foam & vanilla.</p>

				            <div class="item-bottom">

				                <span class="price">$6.25</span>

				                <button class="btn-add" onclick="addToCart('Nitro Caramel', 6.25)">Add to Order</button>

				            </div>

				        </div>

				        <div class="item-card">

				            <h3>Artisanal Almond Croissant</h3>

				            <p>Freshly baked daily with French butter and almond frangipane.</p>

				            <div class="item-bottom">

				                <span class="price">$4.75</span>

				                <button class="btn-add" onclick="addToCart('Almond Croissant', 4.75)">Add to Order</button>

				            </div>

				        </div>

				    </main>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String css = """

				* { margin:0; padding:0; box-sizing:border-box; font-family:'Plus Jakarta Sans',sans-serif; }

				body { background:#1c130d; color:#fdfaf6; }

				.header { display:flex; justify-content:space-between; align-items:center; padding:20px 48px; border-bottom:1px solid #332014; }

				.brand { font-size:22px; font-weight:800; color:#d4a373; }

				.cart-btn { background:#d4a373; color:#1c130d; padding:8px 18px; border-radius:500px; font-weight:700; cursor:pointer; }

				.hero { text-align:center; padding:60px 24px; }

				.hero h1 { font-size:40px; color:#faedcd; margin-bottom:12px; }

				.hero p { color:#d4a373; }

				.menu-grid { display:grid; grid-template-columns:repeat(auto-fit, minmax(280px, 1fr)); gap:24px; padding:20px 48px 60px; max-width:1100px; margin:0 auto; }

				.item-card { background:#281b12; border:1px solid #3d291b; padding:24px; border-radius:14px; display:flex; flex-direction:column; justify-content:space-between; }

				.item-card h3 { font-size:18px; color:#faedcd; margin-bottom:8px; }

				.item-card p { font-size:13.5px; color:#d4a373; margin-bottom:20px; }

				.item-bottom { display:flex; justify-content:space-between; align-items:center; }

				.price { font-size:20px; font-weight:800; color:#faedcd; }

				.btn-add { background:#d4a373; color:#1c130d; border:none; padding:8px 16px; border-radius:6px; font-weight:700; cursor:pointer; }

				""";

		String js = """

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

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

	/*
	 * =========================================================================
	 * 
	 * 6. CRYPTO & FINANCIAL HUB
	 * 
	 * =========================================================================
	 */

	public WebsitePackage generateCryptoHub() {

		String slug = "crypto-hub";

		String title = "CryptoPulse • Real-Time Trading Terminal";

		String html = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>CryptoPulse • Market Terminal</title>

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;600;700;800&family=JetBrains+Mono:wght@500;600&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <header class="header">

				        <div class="logo">⚡ CryptoPulse</div>

				        <div class="status"><span class="dot"></span> Live Market Feed</div>

				    </header>

				    <div class="ticker-bar">

				        <span>BTC/USD: $94,820 <strong style="color:#10b981;">+3.4%</strong></span> •

				        <span>ETH/USD: $3,640 <strong style="color:#10b981;">+5.1%</strong></span> •

				        <span>SOL/USD: $218 <strong style="color:#10b981;">+8.2%</strong></span>

				    </div>

				    <main class="main">

				        <div class="card">

				            <h3>Total Portfolio Value</h3>

				            <div class="val">$148,250.00 USD</div>

				            <p style="color:#10b981;">+$4,210.50 (24h PnL)</p>

				        </div>

				        <div class="card">

				            <h3>Instant Swap</h3>

				            <input type="number" value="1" id="swapAmount" style="background:#1e293b; color:#fff; border:1px solid #334155; padding:8px; border-radius:6px; width:100%; margin:8px 0;">

				            <button onclick="calcSwap()" style="background:#10b981; color:#000; font-weight:700; border:none; padding:10px; width:100%; border-radius:6px; cursor:pointer;">Swap BTC for USDT</button>

				            <div id="swapRes" style="margin-top:8px; font-size:13px; color:#94a3b8;">1 BTC ≈ 94,820.00 USDT</div>

				        </div>

				    </main>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String css = """

				* { margin:0; padding:0; box-sizing:border-box; font-family:'Plus Jakarta Sans',sans-serif; }

				body { background:#0b0f19; color:#f8fafc; padding:24px; }

				.header { display:flex; justify-content:space-between; align-items:center; margin-bottom:16px; }

				.logo { font-size:22px; font-weight:800; color:#10b981; }

				.status { font-size:12px; color:#10b981; display:flex; align-items:center; gap:6px; }

				.dot { width:8px; height:8px; background:#10b981; border-radius:50%; }

				.ticker-bar { background:#111827; border:1px solid #1f2937; padding:10px 16px; border-radius:8px; font-family:'JetBrains Mono',monospace; font-size:13px; margin-bottom:24px; }

				.main { display:grid; grid-template-columns:repeat(auto-fit, minmax(300px, 1fr)); gap:20px; }

				.card { background:#111827; border:1px solid #1f2937; padding:24px; border-radius:12px; }

				.val { font-size:32px; font-weight:800; margin:8px 0; font-family:'JetBrains Mono',monospace; }

				""";

		String js = """

				function calcSwap() {

				    const amt = parseFloat(document.getElementById('swapAmount').value) || 1;

				    const total = amt * 94820;

				    const sr = document.getElementById('swapRes');

				    if (sr) sr.textContent = `${amt} BTC ≈ ${total.toLocaleString()} USDT (Fee: 0.1%)`;

				    alert(`Simulated Swap: ${amt} BTC -> $${total.toLocaleString()} USDT executed successfully!`);

				}

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

	/*
	 * =========================================================================
	 * 
	 * 7. UNIVERSAL CUSTOM WEBSITE GENERATOR
	 * 
	 * =========================================================================
	 */

	private static String escapeHtml(String value) {
		return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\\\"", "&quot;")
				.replace("'", "&#39;");
	}

	public WebsitePackage generateCustomSite(String prompt) {

		String slug = deriveSlug(prompt);

		String title = prompt != null && !prompt.trim().isEmpty() ? prompt : "Modern Web Application";

		String htmlTemplate = """

				<!DOCTYPE html>

				<html lang="en">

				<head>

				    <meta charset="UTF-8">

				    <meta name="viewport" content="width=device-width, initial-scale=1.0">

				    <title>__TITLE__</title>

				    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">

				    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

				    <link rel="stylesheet" href="style.css">

				</head>

				<body>

				    <header class="app-header">

				        <div class="brand">🚀 __TITLE__</div>

				        <nav class="nav">

				            <a href="#overview">Overview</a>

				            <a href="#features">Features</a>

				            <a href="#contact" class="btn-action">Get Started</a>

				        </nav>

				    </header>

				    <section class="hero-section" id="overview">

				        <div class="pill-badge">✨ Autonomous AI Generated Project</div>

				        <h1>__TITLE__</h1>

				        <p class="hero-desc">Tailored responsive web application with modern layout, clean CSS architecture, and interactive components built by Nexus AI Agent.</p>

				        <div class="hero-actions">

				            <button class="btn-primary" onclick="showDemo()"><i class="fa-solid fa-wand-magic-sparkles"></i> Explore Interactive Demo</button>

				            <a href="#features" class="btn-secondary">View Features</a>

				        </div>

				    </section>

				    <section class="features-section" id="features">

				        <div class="features-grid">

				            <div class="feature-box">

				                <div class="feat-icon">⚡</div>

				                <h3>High Performance</h3>

				                <p>Engineered with vanilla JavaScript and optimized CSS tokens for ultra-fast loading.</p>

				            </div>

				            <div class="feature-box">

				                <div class="feat-icon">📱</div>

				                <h3>Fully Responsive</h3>

				                <p>Pixel-perfect adaptive layout optimized for mobile screens, tablets, and desktop displays.</p>

				            </div>

				            <div class="feature-box">

				                <div class="feat-icon">🎨</div>

				                <h3>Modern Aesthetics</h3>

				                <p>Rich dark mode aesthetic with glassmorphism, glowing accents, and smooth transitions.</p>

				            </div>

				        </div>

				    </section>

				    <footer class="app-footer" id="contact">

				        <p>Generated by Nexus AI Autonomous Studio • Built for Priyank</p>

				    </footer>

				    <script src="script.js"></script>

				</body>

				</html>

				""";

		String html = htmlTemplate.replace("__TITLE__", escapeHtml(title));

		String css = """

				* { margin:0; padding:0; box-sizing:border-box; font-family:'Plus Jakarta Sans',sans-serif; }

				body { background:#0f172a; color:#f8fafc; line-height:1.6; }

				.app-header { display:flex; justify-content:space-between; align-items:center; padding:20px 48px; border-bottom:1px solid #1e293b; background:rgba(15,23,42,0.9); backdrop-filter:blur(10px); position:sticky; top:0; z-index:100; }

				.brand { font-size:20px; font-weight:800; color:#38bdf8; }

				.nav { display:flex; gap:20px; align-items:center; }

				.nav a { color:#94a3b8; text-decoration:none; font-weight:600; font-size:14px; }

				.btn-action { background:#38bdf8; color:#0f172a !important; padding:8px 18px; border-radius:6px; font-weight:700; }

				.hero-section { text-align:center; padding:80px 24px 60px; max-width:800px; margin:0 auto; }

				.pill-badge { display:inline-block; background:rgba(56,189,248,0.1); border:1px solid rgba(56,189,248,0.3); color:#38bdf8; padding:4px 14px; border-radius:500px; font-size:12px; font-weight:700; margin-bottom:20px; }

				.hero-section h1 { font-size:42px; font-weight:800; line-height:1.2; margin-bottom:16px; background:linear-gradient(135deg, #f8fafc, #94a3b8); -webkit-background-clip:text; -webkit-text-fill-color:transparent; }

				.hero-desc { font-size:16px; color:#94a3b8; margin-bottom:32px; }

				.hero-actions { display:flex; justify-content:center; gap:14px; }

				.btn-primary { background:#38bdf8; color:#0f172a; border:none; padding:12px 24px; border-radius:8px; font-weight:700; font-size:14px; cursor:pointer; }

				.btn-secondary { background:#1e293b; color:#fff; border:1px solid #334155; padding:12px 24px; border-radius:8px; font-weight:700; font-size:14px; text-decoration:none; }

				.features-section { padding:40px 48px 80px; max-width:1100px; margin:0 auto; }

				.features-grid { display:grid; grid-template-columns:repeat(auto-fit, minmax(280px, 1fr)); gap:24px; }

				.feature-box { background:#1e293b; border:1px solid #334155; padding:28px; border-radius:14px; }

				.feat-icon { font-size:28px; margin-bottom:12px; }

				.feature-box h3 { font-size:18px; margin-bottom:8px; }

				.feature-box p { font-size:13.5px; color:#94a3b8; }

				.app-footer { border-top:1px solid #1e293b; padding:32px; text-align:center; color:#64748b; font-size:13px; }

				""";

		String js = """

				function showDemo() {

				    alert('🎉 Welcome to the interactive demo of ' + document.title + '!');

				}

				""";

		return new WebsitePackage(slug, title, html, css, js);

	}

}