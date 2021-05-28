package org.generation.italy.universogames.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.universogames.model.News;
import org.generation.italy.universogames.model.Review;
import org.generation.italy.universogames.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoMySQLPosts extends BasicDao implements IDaoPosts {

	public DaoMySQLPosts(@Value("${db.address}") String dbAddress, @Value("${db.user}") String user,
			@Value("${db.password}") String password) {
		super(dbAddress, user, password);
	}

	/**
	 * @return La lista News (anche la parte News delle Reviews) dalla più recente
	 */
	@Override
	public List<News> news() {

		List<News> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT * FROM news ORDER BY pubdate DESC, pubtime DESC");

		for (Map<String, String> map : maps) {
			News n = new News();
			n.fromMap(map);
			res.add(n);
		}

		return res;
	}

	/**
	 * @return la lista News (senza quelle che sono Reviews) dalla più recente
	 */
	@Override
	public List<News> newsWithoutReviews() {

		List<News> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT news.* FROM news " + " LEFT JOIN reviews ON news.id = idnews "
				+ "WHERE idnews IS NULL " + " ORDER BY pubdate DESC, pubtime DESC");

		for (Map<String, String> map : maps) {
			News n = new News();
			n.fromMap(map);
			res.add(n);
		}

		return res;
	}

	/**
	 * Per avere esattamente num news per pagina (la page di fa vedere i num videogames di quella pagina)
	 * @return la lista News (senza quelle che sono Reviews) dalla più recente
	 */
	@Override
	public List<News> newsWithoutReviews(int num, int page) {

		List<News> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT * FROM (SELECT news.*, ROW_NUMBER() OVER() AS r  FROM news " 
				+ " LEFT JOIN reviews ON news.id = idnews "
				+ " WHERE idnews IS NULL" 
				+ " ORDER BY pubdate DESC, pubtime DESC) AS toview"
				+ " WHERE r BETWEEN ? AND ?", num*(page - 1)+ 1, num*page);
		
		for (Map<String, String> map : maps) {
			News n = new News();
			n.fromMap(map);
			res.add(n);
		}

		return res;
	}
	
	/**
	 * Per avere esattamente num news ma RANDOM-selected
	 * 
	 * @return la lista News (senza quelle che sono Reviews) dalla più recente
	 */
	@Override
	public List<News> newsWithoutReviewsRandom(int num) {

		List<News> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT news.* FROM news " 
								+ " LEFT JOIN reviews ON news.id = idnews "
								+ " WHERE idNews IS NULL " 
								+ " ORDER BY RAND() "
								+ " LIMIT ?", num);

		for (Map<String, String> map : maps) {
			News n = new News();
			n.fromMap(map);
			res.add(n);
		}

		return res;
	}
	
	/**
	 * METODO ACCESSORIO Grazie al parametro idReview, richiama dalla tabella
	 * imgcarousel le immagini relative alla review con id = idReview
	 * 
	 * @param idReview (l'id delal review di cui voglio richiamare le immagini
	 * @return Una Lista di Stringhe (url o filepath delle immagini)
	 */
	private List<String> imgCarousel(int idReview) {
		return getOneColumn("SELECT path FROM imgcarousel WHERE idreview = ?", idReview);
	}

	/**
	 * @return la lista delle Reviews dalla più recente
	 */
	@Override
	public List<Review> reviews() {

		List<Review> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT * FROM reviews" + " INNER JOIN news ON idnews = news.id"
				+ " ORDER BY pubdate DESC, pubtime DESC");

		for (Map<String, String> map : maps) {
			Review r = new Review();
			r.fromMap(map);
			r.setImgCarousel(imgCarousel(r.getId()));
			res.add(r);
		}

		return res;
	}
	
	/**
	 * Per avere esattamente num reviews per pagina (la page di fa vedere i num videogames di quella pagina)
	 * @return la lista delle Reviews dalla più recente
	 */
	@Override
	public List<Review> reviews(int num, int page) {
		
		List<Review> res = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM "
				+ " (SELECT title, imgcover, preview, text, pubdate, pubtime, author, reviews.*, ROW_NUMBER() OVER() AS r "
				+ " FROM reviews "
				+ " INNER JOIN news ON idnews = news.id "
				+ " ORDER BY pubdate DESC) AS toview "
				+ " WHERE r BETWEEN ? AND ?", num*(page - 1)+ 1, num*page);
		
		for (Map<String, String> map : maps) {
			Review r = new Review();
			r.fromMap(map);
			r.setImgCarousel(imgCarousel(r.getId()));
			res.add(r);
		}
		
		return res;
	}
	
	/**
	 * Num notizie senza recensioni, per tag (dalla più recente)
	 */
	@Override
	public List<News> newsPerTag(int num, String tag){
		List<News> res = new ArrayList<>();

		List<Map<String, String>> maps = getAll("SELECT newswr.* FROM ((SELECT news.* FROM news"
				+ " LEFT JOIN reviews ON news.id = reviews.idnews"
				+ " WHERE reviews.idnews IS NULL) as newswr"
				+ " INNER JOIN tags ON newswr.id = tags.idnews) "
				+ " WHERE tags.name = ?"
				+ " ORDER BY pubdate DESC, pubtime DESC"
				+ " LIMIT ?", tag, num);
		
		for (Map<String, String> map : maps) {
			News n = new News();
			n.fromMap(map);
			res.add(n);
		}

		return res;
	}

	/**
	 * @param l'indice (pk ai) relativo alla news
	 * @return l'oggetto News con quell'id
	 */
	@Override
	public News news(int id) {

		News res = null;
		Map<String, String> map = getOne("SELECT * FROM news WHERE id = ?", id);

		if (map != null) {
			res = new News();
			res.fromMap(map);
		}

		return res;
	}
	
	/**
	 * @param l'indice (pk) relativo alla tabella reviews
	 * @return l'oggetto Review relativo a quell'id (con anche la parte della news e
	 *         le immagini di carosello)
	 */
	@Override
	public Review review(int id) {
		Review res = null;
		Map<String, String> map = getOne(
				"SELECT * FROM reviews " + " INNER JOIN news ON idnews = news.id " + " WHERE reviews.id = ?", id);

		if (map != null) {
			res = new Review();
			res.fromMap(map);
			res.setImgCarousel(imgCarousel(id));
		}

		return res;
	}
	
	/**
	 * Restituisce una review in base all'idVideogame
	 */
	@Override
	public Review reviewVG(int idVideogame) {
		Review res = null;
		Map<String, String> map = getOne(
				" SELECT reviews.id, imgcover, title, preview, score, video "
				+ " FROM reviews"
				+ " INNER JOIN news ON idnews = news.id" 
				+ " WHERE idvideogame = ?", idVideogame);

		if (map != null) {
			res = new Review();
			res.fromMap(map);
			res.setImgCarousel(imgCarousel(res.getId()));
		}

		return res;
	}
	
	// PRIMA UN TASTO PER UPLOADARE L'IMMAGINE (IL METODO DI UPLOAD RESTITUISCE IL
	// NOME CHE VA IN UN BOTTONCINO)
	// POI C'È UN BOTTONE FINALE CHE PRENDO TUTTO L'OGGETTO COMPRESO IL NOME DEL
	// FILE DAL BOTTONCINO
	/**
	 * Aggiunge alla tabella news l'oggetto passato da parametro
	 * 
	 * @param oggetto News da aggiungere
	 * @return id della news, ci servirà per aggiungere i tag.
	 */
	@Override
	public int addNews(News news) {
		return insertAndGetId("INSERT INTO news (title,imgcover,text,pubdate,pubtime,author) VALUES (?,?,?,?,?,?)", news.getTitle(),
				news.getImgCover(), news.getText(), news.getPubDate(), news.getPubTime(), news.getAuthor());
	}
	
	/**
	 * aggiunge un tag legato alla news con id = idnews
	 */
	@Override
	public void addTag(String name, int idNews) {
		execute("INSERT INTO tags (name, idnews) VALUES (?,?)", name, idNews);
	}
	

	/**
	 * Dati un oggetto Review - aggiunge alla tabella news le proprietà dell'oggetto
	 * della classe padre(News) - 1 aggiunge alla tabella review le proprietà
	 * dell'oggetto della classe review 2 aggiunge alla tabella review il valore di
	 * idNews relativo all'aggiunta appena fatta in news
	 * 
	 * @param l'oggetto Review da aggiungere al db
	 * @return ritorna l'idNews (ci servirà se vogliamo poi aggiungere tag)
	 */
	@Override
	public int addReview(Review review) {
		int idNews = insertAndGetId(
				"INSERT INTO news (title,imgcover,text,pubdate,pubtime,author) VALUES (?,?,?,?,?,?)", review.getTitle(),
				review.getImgCover(), review.getText(), review.getPubDate(), review.getPubTime(), review.getAuthor());
		int idReview = insertAndGetId("INSERT INTO reviews (idvideogame, score, idnews) VALUES (?,?,?)", review.getIdVideogame(),
				review.getScore(), idNews);
		for(int i=0; i<review.getImgCarousel().size(); i++) {
			addImgCarousel(review.getImgCarousel().get(i), idReview);
		}
		return idNews;
	}

	/**
	 * Elimina dalla tabella news la news relativa all'id (pk) passato da parametro
	 * 
	 * @param id indice della news che si vuole eliminare
	 */
	@Override
	public void deleteNews(int id) {
		execute("DELETE FROM news WHERE id = ?", id);
	}

	/**
	 * Dato un id (indice della review da eliminare) passato da parametro - Grazie
	 * alla voce idNews, elimina dalla tabella news le proprietà dell'oggetto
	 * padre(News) - Elimina dalla tabella reviews la riga con l'id passato da
	 * parametro - Elimina dalla tabella imgcarousel le righe in relazione con la
	 * review appena eliminata
	 * 
	 * @param id indice della review che si vuole eliminare
	 */
	@Override
	public void deleteReview(int id) {
		deleteNews(review(id).getIdNews());
		execute("DELETE FROM reviews WHERE id = ?", id);
		execute("DELETE FROM imgcarousel WHERE idreview = ?", id);

	}

	/**
	 * Dato un parametro News, aggiorna tutte le voci di quell'oggetto nella tabella
	 * news Viene identificato dalla sua proprietà id.
	 * 
	 * @param la nuova versione dell'oggetto News da modificare (la proprietà id
	 *           identifica la riga da modificare)
	 */
	@Override
	public void updateNews(News news) {
		execute("UPDATE news SET title = ?, imgcover = ?, text= ?, pubdate= ?, pubtime = ?, author= ? WHERE id = ?",
				news.getTitle(), news.getImgCover(), news.getText(), news.getPubDate(), news.getPubTime(),
				news.getAuthor(), news.getId());
	}

	
	
	// PRIMA UNA PAGINA DOVE MODIFICHI TUTTO TRANNE LE FOTO (QUANDO CONFERMI, LE
	// FOTO VECCHIE SI CANCELLANO)
	// SECONDA PAGINA DOVE DEVE PER FORZA CARICARLE DA CAPO

	/**
	 * Dato un oggetto Review, aggiorna tutte le voci relative a quell'oggetto nella tabella news e nella tabella reviews
	 * Cancella inoltre le immagini di carosello dell'oggetto dalla tabella ImgCarousel e inserisce le nuove.
	 * @param la nuova versione dell'oggetto Review da modificare (la proprietà id
	 *           identifica la riga della tabella reviews da modificare)
	 */
	@Override
	public void updateReview(Review review) {
		updateNews(news(review.getIdNews()));
		execute("UPDATE reviews SET idvideogame = ?, score = ?, idnews = ? WHERE reviews.id = ?",
				review.getIdVideogame(), review.getScore(), review.getIdNews(), review.getId());
		execute("DELETE FROM imgcarousel WHERE idreview = ?", review.getId());
		for(int i=0; i<review.getImgCarousel().size(); i++) {
			addImgCarousel(review.getImgCarousel().get(i), review.getId());
		}
	}
	
	/**
	 * Metodo per l'aggiunta di un'immagine di carosello nella tabella imgcarousels
	 * @param una stringa percorso file e l'id della review a cui si riferisce
	 */
	@Override
	public void addImgCarousel(String nomeFile, int idReview) {
		execute("INSERT INTO imgcarousel (path, idreview) VALUES(?,?)", nomeFile, idReview);
	}

}
