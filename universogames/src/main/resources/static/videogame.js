$(document).ready(function() {
    var id = Cookies.get("id");
    
    function getHero() {
    	
		$(".hero").html('');
		
		$.ajax({
				type: 'GET',
				url: `videogames/${id}`,
				dataType: 'json',
				contentType: 'application/json',
				success: function(res) {

				id = res.id;

				$(` 
                    <div class="hero__content">
                        <h1 class="big-text">${res.title}</h1>
                    </div>
                    
                    <div class="info small-text">
                        <div class="info2">
                            
                            <div class="col2">
                                <p>Piattaforma</p>
                                ${res.platform}
                            </div>
                            
                            <div class="col2">
                                <p>Genere</p>
                                ${res.genre}
                            </div>
                            
                            <div class="col2">
                                <p>Pegi</p>
                                ${res.pegi}
                            </div>
                            
                            <div class="col2">
                                <p>Data d'uscita</p>
                                ${res.pubDate}
                            </div>
                            
                            <div class="col2">
                                <p>Voto</p>
                                <div id="score">
                              
                             	</div>
                            </div>
                            
                        </div>
                    </div>

                    <div class="video" style="position: absolute; z-index: -99; width: 100%; height: 100%; bottom: 250px;">

                    </div>
                `).appendTo('.hero');
                
				getVideo(res.id);
				getScore(res.id);
				getDescription(res.id);
				getNews(res.title);
				getRight(res.id);
			}
		})
	}
	
    function getScore(id) {
	
        $.get(`posts/reviewVG/${id}`, function(res) {
			
            $('#score').append(res.score);
        })
    }
    
    function getVideo(id) {

        $.get(`posts/reviewVG/${id}`, function(res) {
			console.log(res);
  
            $(
                `<iframe height="200%" width="100%" src="https://www.youtube.com/embed/${res.video}?playlist=${res.video}&loop=1&autoplay=1&mute=1"></iframe>`
            ).appendTo('.video');
        })

    }

    function getDescription(id) {

		$(".description").html('');
		$.get(`videogames/${id}`, function(res) {

				$(` 
                    <div class="box_title">
                        <h3 class="intro-text">Trama</h3>
                    </div>
                    <div class="description_text">
                        <p>${res.description}</p>
                    </div>
                `).appendTo('.description');
		})
	}

    function getNews(title) {

		$(".section").html('');

		$.get(`posts/list/newsPerTag/10/${title}`, function(res) {
			
			for(let i = 0; i < res.length; i++) {
				
				$(
					`
					
					 <div class="box_news">
                        <img src="${res[i].imgCover}" class="img_news">
                        <div class="text_news">
                        <a href="notizia.html" data-id='${res[i].id}' class="linknotizia"><div class="normal-text" style="font-weight: bold;">${res[i].title}</div><br />
                            <p>${res[i].text}</p></a>
                        </div>
                    </div>
                   
                    `
                    ).appendTo('.section');
			}
		})
	}

    function getRight(id) {

		$(".reviews").html('');
        $(".carousel-cell").html('');

		$.get(`posts/reviewVG/${id}`, function(res) {
				$(
					`
                    <div class="box_title">
                        <h3 class="intro-text">Recensione</h3>
                    </div>
                
                    <img src="${res.imgCover}" class="r_img">
                    <div class="r_text">
                        <a href="recensione.html" data-id='${res.id}' class="linkrecensione"><h3 class="intro-text">${res.title}</h3>
                        <p>${res.preview}
                        <b style="font-weight: bolder;">Leggi la recensione!</b></p></a>
                    </div>
                    `
                    ).appendTo('.reviews');
                    
                    $(
                        `                 
                           <div class="carousel-cell-info" style="background: url('${res.imgCarousel[0]}') no-repeat center center; background-size: cover;">
                            </div>              
                        `
                        ).appendTo('.imgcarousel0');
                        $(
                        `                 
                           <div class="carousel-cell-info" style="background: url('${res.imgCarousel[1]}') no-repeat center center; background-size: cover;">
                            </div>              
                        `
                        ).appendTo('.imgcarousel1');
                        $(
                        `                 
                           <div class="carousel-cell-info" style="background: url('${res.imgCarousel[2]}') no-repeat center center; background-size: cover;">
                            </div>              
                        `
                        ).appendTo('.imgcarousel2');
		})
	}

    getScore();
    getHero();
    getRight();


    $(".section").on("click", ".linknotizia", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });

    $(".reviews").on("click", ".linkrecensione", function() {
        var idReview = +$(this).attr('data-id');

        Cookies.set("id", idReview);
    });

});