$(document).ready(function() {
	let npage = 1;

	function getVideogames() {

		$(".elenco").html('');
		$.get(`videogames/last/9/${npage}`, function(res) {
			
			for(let i = 0; i < res.length; i++) {
				
				$(
					`<div class="col">
					    <div class="card shadow-sm">
					        <img src='${res[i].img}' alt="" class="r_img1" style="height: 225px;">
			            <div class="card-body">
				            <h1 class="intro-text">${res[i].title}</h1>
				            <div class="table">
					            <table>
				     	            <tr>
									    <th>Genere</th>
						                <td>${res[i].genre}</td>
								    </tr>
									<tr>
									    <th>Piattaforma</th>
									    <td>${res[i].platform}</td>
							        </tr>
							        <tr>
							            <th>PEGI</th>
							            <td>${res[i].pegi}</td>
						            </tr>
						            <tr>
						                <th>Data pubblicazione</th>
						                <td>${res[i].pubDate}</td>
					                </tr>

						        </table>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary button_dettaglio" data-id='${res[i].id}' onclick="document.location='videogame.html'">Visualizza</button>
                                </div>
                            </div>
			            </div>
			         </div>
			        </div>`).appendTo('.elenco');
			}
		})
	}

    getVideogames();

    /* SERVE AD ANDARE AL DETTAGLIO DEL VIDEOGAME IL CUI BUTTON E' CLICCATO */
    $(".elenco").on("click", ".button_dettaglio", function() {
        const id = +$(this).attr('data-id');

		Cookies.set("id", id);
    });

    /* AL CLICK DEL LINKPG MI MODIFICA IL NPAGE E RICHIAMA IL GETVIDEOGAMES */
    $(".pagination").on("click", ".linkpg", function() {
        npage = +$(this).attr('npage');
        getVideogames();
        $('.linkpg').removeClass('active');
        $(this).toggleClass('active');
    });

});