$(document).ready(function() {
	let npage = 1;

	function getNews() {

		$(".elenco").html('');
		$.get(`posts/list/news/9/${npage}`, function(res) {
			
			for(let i = 0; i < res.length; i++) {
				
				$(`<div class="col">
              <div class="card shadow-sm">
                <img src='${res[i].imgCover}' alt="" class="r_img1" style="height: 225px;">
                      
                <div class="card-body">
                  <h1 class="intro-text">${res[i].title}</h1>
                  <p class="card-text">${res[i].text}</p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary button_dettaglio" data-id='${res[i].id}' onclick="document.location='notizia.html'">Leggi</button>
                    </div>
                    <small class="text-muted">${res[i].pubDate}</small>
                  </div>
                </div>
              </div>
            </div>`).appendTo('.elenco');
			}
		})
	}

    getNews();

    /* SERVE AD ANDARE AL DETTAGLIO DEL VIDEOGAME IL CUI BUTTON E' CLICCATO */
    $(".elenco").on("click", ".button_dettaglio", function() {
        var id = +$(this).attr('data-id');
        
        Cookies.set("id", id);
    });

    /* AL CLICK DEL LINKPG MI MODIFICA IL NPAGE E RICHIAMA IL GETVIDEOGAMES */
    $(".pagination").on("click", ".linkpg", function() {
        npage = +$(this).attr('npage');
        getNews();
        $('.linkpg').removeClass('active');
        $(this).toggleClass('active');
    });

});