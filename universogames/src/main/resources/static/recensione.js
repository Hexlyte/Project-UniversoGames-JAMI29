$(document).ready(function() {

	var id = Cookies.get("id");

	function getRecensione() {

		$(".replace").html('');
		$.get(`posts/reviews/${id}`, function(res) {

				$(` 
                    <div class="hero">
					    <div class="hero__title">
                            <h1 class="big-text">${res.title}</h1>
                        </div>

                        <div style="position: absolute; z-index: -99; width: 100%; height: 100%; bottom: 250px;">
                            <iframe height="200%" width="100%" src="https://www.youtube.com/embed/${res.video}?playlist=${res.video}&loop=1&autoplay=1&mute=1"></iframe>
                        </div>
                    </div>
                    <div class="box">
                        <div class="content">
                            <h3 class="small-text">${res.author} -  ${res.pubDate} ${res.pubTime}</h3>
                            <p class="intro-text">${res.preview}</p>
                            <p>${res.text}</p>
							<p class="intro-text" style="text-align: center;">VOTO ${res.score}</p>
                        </div>

                    </div>`).appendTo('.replace');
		})
	}

    getRecensione();

});