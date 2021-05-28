$(document).ready(function() {
	
    /* REPLACE1 */
	function getVideogame() {

		$(".replace1").html('');
		$.get(`videogame/${id}`, function(res) {


           $(
           `  <div class="hero hero--small">
                      <div class="hero__content">
                             <h1 class="big-text">${res[i].title}</h1>
                             <p class="intro-text">${res[i].titoletto}</p>
                     </div>
        
                     <div class="info small-text">

                            <div class="info2">
                                 <div class="col2">
                                     <p>Piattaforma</p>
                                     ${res[i].piattaforma}
                                </div>

                                <div class="col2">
                                     <p>Genere</p>
                                     ${res[i].genere}
                                </div>

                                <div class="col2">
                                     <p>Pegi</p>
                                    ${res[i].pegi}
                                </div>

                                <div class="col2">
                                    <p>Data d'uscita</p>
                                   ${res[i].pubdate}
                                </div>

                                 <div class="col2">
                                    <p>Voto medio</p>
                                    ${res[i].votomedio}
                                 </div>
                            </div>

                     </div>
           
                <video autoplay muted loop id="video-back">
                       <source src="${res[i].video}" type="video/mp4">
                </video>
            
                </div>
                
                
                <div class="box">

                
                <div class="description">
                    <div class="box_title">
                        <h3 class="intro-text">Trama</h3>
                    </div>
                    <div class="description_text">
                    ${res[i].descrizione}
                    </div>
                   </div>
                </div>`).appendTo('.replace1');
        })

    }
    getVideogame();


    
    /* REPLACE2 */ 

    function getNotizie() {

		$(".replace2").html('');
		$.get(`posts/list/newsPerTag/9/${gioco[i].title}`, function(notizie) {


              for(let i = 0; i < notizie.length; i++) {

                $(
					` <div class="box_news">
                           <img src='${notizie[i].img}' alt="" class="img_news">
                           <div class="text_news">${notizie[i].testo}</div>
                     </div>`).appendTo('.replace2');




		      }
        })
    }

    getNotizie();

    

    /* REPLACE 3*/ 
    function getRecensione() {

		$(".replace3").html('');
		$.get(`posts/reviewVG/${gioco[i].id}`, function(recensione) {

            $(
                `     <div class="reviews">
                           <div class="box_title">
                               <h3 class="intro-text">Recensione</h3>
                           </div>

                           <img src='${recensione[i].img}' alt="" class="r_img">
                         
                           <div class="r_text">
                              <h3 class="intro-text">${recensione[i].titoletto}</h3>
                          </div>
                      </div>`).appendTo('.replace3');


        })

    }


   getRecensione();



    /* REPLACE 4*/ 
   function getCarosello() {

      $(".replace4").html('');
      $.get(`posts/reviewVG/${gioco[i].id}`, function(recensione) {

          for( let i = 0; i < notizie.length; i++) {

          $(  `<div class="carousel-cell">
                  <div class="carousel-cell-info">
                  </div>
              </div>`).appendTo('.replace4');
 


          }
     })

   }

  getCarosello();

});