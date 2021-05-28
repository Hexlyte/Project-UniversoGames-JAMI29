$(document).ready(function() {
	
	var id = Cookies.get("id");
	
	function getNotizia() {

		$(".replace").html('');
		$.get(`posts/news/${id}`, function(res) {
				
				$(` <div class="hero">
					    <div class="hero__title">
                            <h1 class="big-text">${res.title}</h1>
                        </div>

                        <video autoplay muted loop id="video-back">
                             <source src="video/joystick.mp4" type="video/mp4">
                        </video> 
                    </div>
                    <div class="box">

                        <div class="content">
                            <h3 class="small-text">${res.author} -  ${res.pubDate} ${res.pubTime}</h3>
                            <p class="intro-text">${res.preview}</p>
                            <p>${res.text}</p>
                        </div>
                    </div>`).appendTo('.replace');
		})
	}

    getNotizia();
});