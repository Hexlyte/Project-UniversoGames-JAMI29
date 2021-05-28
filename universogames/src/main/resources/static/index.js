$(document).ready(function() {
	function getTreNotizie() {

		$(".trenotizie").html('');
		$.get(`posts/list/news/3/1`, function(res) {
			
			for (let i = 0; i < res.length; i++) {
				
				$(
					`
                    <div class="single_text">
                        <a href="notizia.html" data-id="${res[i].id}" class="linktrenotizie">
                            <h4 class="intro-text tw">${res[i].title}</h4>
                            <p class="tw testo">${res[i].text}</p>
                        </a>
                    </div>
                    `
                    ).appendTo('.trenotizie');
			}
		})
	};

    function getClassifica() {

		$(".classifica").html('');
		$.get(`videogames/ranked/9`, function(res) {
			
			for (let i = 0; i < 9; i++) {
				
				$(
					`
                    <a href="videogame.html" data-id="${res[i].idVideogame}" class="linkclassifica">
                        <div class="box--game">
                            <div class="game--title">${res[i].title}</div>
                            <div class="game--score">${res[i].score}</div>
                        </div>
                    </a>
                    `
                    ).appendTo('.classifica');
			}
		})
	};

    function getRecensioni() {

		$(".recensioni").html('');
		$.get(`posts/list/reviews/2/1`, function(res) {
				
				$(
					`
                    <a href="recensione.html" data-id="${res[0].id}" class="linkrecensioni">
                        <div class="reviews">
                            <div class="r_text">
                                <h3 class="intro-text">${res[0].title}</h3>
                                <p>${res[0].text}</p>
                                <p style="font-weight: bold; text-align: right;">VOTO ${res[1].score}</p>
                            </div>
                            <img src='${res[0].imgCover}' alt="" class="r_img">
                        </div>
                    </a>
                    <a href="recensione.html" data-id="${res[1].id}" class="linkrecensioni">
                        <div class="reviews">
                        <img src='${res[1].imgCover}' alt="" class="r_img">
                            <div class="r_text">
                                <h3 class="intro-text">${res[1].title}</h3>
                                <p>${res[0].text}</p>
                                <p style="font-weight: bold; text-align: right;">VOTO ${res[1].score}</p>
                            </div>
                        </div>
                    </a>
                    `
                    ).appendTo('.recensioni');
		})
	};

    function getNotizieRandom() {

		$(".notizierandom").html('');
		$.get(`posts/list/randomnews/6`, function(res) {
				
				$(
					`
                    <div class="grid_two">
                        <a href="notizia.html" data-id="${res[0].id}" class="linknotizierandom"><div class="dot" style="background: linear-gradient(0deg, rgba(0,0,0,1), rgba(0,0,0,0.2)), url('${res[0].imgCover}') no-repeat center center; background-size: cover;"><div class="dot_text">${res[0].title}</div></div></a>
                        <a href="notizia.html" data-id="${res[1].id}" class="linknotizierandom"><div class="dot" style="background: linear-gradient(0deg, rgba(0,0,0,1), rgba(0,0,0,0.2)), url('${res[1].imgCover}') no-repeat center center; background-size: cover;"><div class="dot_text">${res[1].title}</div></div></a>
                        <a href="notizia.html" data-id="${res[2].id}" class="linknotizierandom"><div class="dot" style="background: linear-gradient(0deg, rgba(0,0,0,1), rgba(0,0,0,0.2)), url('${res[2].imgCover}') no-repeat center center; background-size: cover;"><div class="dot_text">${res[2].title}</div></div></a>
                    </div>
                    
                    <div class="grid_two">
                        <a href="notizia.html" data-id="${res[3].id}" class="linknotizierandom"><div class="dot" style="background: linear-gradient(0deg, rgba(0,0,0,1), rgba(0,0,0,0.2)), url('${res[3].imgCover}') no-repeat center center; background-size: cover;"><div class="dot_text">${res[3].title}</div></div></a>
                        <a href="notizia.html" data-id="${res[4].id}" class="linknotizierandom"><div class="dot" style="background: linear-gradient(0deg, rgba(0,0,0,1), rgba(0,0,0,0.2)), url('${res[4].imgCover}') no-repeat center center; background-size: cover;"><div class="dot_text">${res[4].title}</div></div></a>
                        <a href="notizia.html" data-id="${res[5].id}" class="linknotizierandom"><div class="dot" style="background: linear-gradient(0deg, rgba(0,0,0,1), rgba(0,0,0,0.2)), url('${res[5].imgCover}') no-repeat center center; background-size: cover;"><div class="dot_text">${res[5].title}</div></div></a>
                    </div>
                    `
                    ).appendTo('.notizierandom');
		})
	};

    getTreNotizie();
    getClassifica();
    getRecensioni();
    getNotizieRandom();

    $(".trenotizie").on("click", ".linktrenotizie", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });

    $(".classifica").on("click", ".linkclassifica", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });

    $(".recensioni").on("click", ".linkrecensioni", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });

    $(".notizierandom").on("click", ".linknotizierandom", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });

    $(".notizierandom").on("click", ".linknotizierandom", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });


    $(".piattaforme").on("click", ".playstation", function() {
        var platform = "Playstation";
        
        Cookies.set("platform", platform);
    });

    $(".piattaforme").on("click", ".nintendo", function() {
        var platform = "Nintendo";
        
        Cookies.set("platform", platform);
    });

    $(".piattaforme").on("click", ".xbox", function() {
        var platform = "Xbox";
        
        Cookies.set("platform", platform);
    });

    $(".piattaforme").on("click", ".pc", function() {
        var platform = "PC";
        
        Cookies.set("platform", platform);
    });

});