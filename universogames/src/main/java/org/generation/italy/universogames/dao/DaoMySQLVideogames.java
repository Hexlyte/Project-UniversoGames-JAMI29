package org.generation.italy.universogames.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.universogames.model.RankedVG;
import org.generation.italy.universogames.model.Videogame;
import org.generation.italy.universogames.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoMySQLVideogames extends BasicDao implements IDaoVideogames{

	public DaoMySQLVideogames(
			@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user,
			@Value("${db.password}") String password) {
		super(dbAddress, user, password);
		
	}

	@Override
	public List<Videogame> videogames() {
		List<Videogame> solution = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM videogames");
		
		for (Map<String, String> map : maps) {
			Videogame v = new Videogame();
			v.fromMap(map);
			solution.add(v);
		}
		
		return solution;
	}
	
	@Override
	public List<Videogame> videogamesLast(int num) {
		List<Videogame> solution = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM videogames"
				+ " ORDER BY pubdate DESC"
				+ " LIMIT ?", num);
		
		for (Map<String, String> map : maps) {
			Videogame v = new Videogame();
			v.fromMap(map);
			solution.add(v);
		}
		
		return solution;
	}
	
	/**
	 * num videogames con media voto più alta
	 */
	@Override
	public List<Videogame> videogamesAvg(int num) {
		List<Videogame> solution = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT *,(sumvotes/numbervotes) as avgvotes FROM videogames"
				+ " ORDER BY avgvotes DESC"
				+ " LIMIT ?", num);
		
		for (Map<String, String> map : maps) {
			Videogame v = new Videogame();
			v.fromMap(map);
			solution.add(v);
		}
		
		return solution;
	}
	
	/**
	 * RESTITUISCE LA CLASSIFICA per la home, l'oggetto RankedVG serve solo qui e porta con se 3 info:
	 * - id del videogame. - titolo. - score.
	 * @param num, la lunghezza della lista che voglio chiamare
	 * @return lista lunga num di RankedVG per punteggio in ordine decrescente 
	 */
	@Override
	public List<RankedVG> videogamesRanked(int num) {
		List<RankedVG> solution = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT reviews.idvideogame, videogames.title, score  FROM videogames"
				+ " INNER JOIN reviews ON videogames.id = reviews.idvideogame"
				+ " ORDER BY score DESC"
				+ " LIMIT ?", num);
		
		for (Map<String, String> map : maps) {
			RankedVG v = new RankedVG();
			v.fromMap(map);
			solution.add(v);
		}
		
		return solution;
	}

	
	/**
	 * Per avere esattamente num videogames per pagina (la page di fa vedere i num videogames di quella pagina)
	 * @return la lista dei videogames 
	 */
	@Override
	public List<Videogame> videogames(int num, int page) {
		
		List<Videogame> res = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM(SELECT *, ROW_NUMBER() OVER() AS r "
				+ " FROM videogames ORDER BY pubdate DESC) AS toview "
				+ " WHERE r BETWEEN ? AND ?", num*(page - 1)+ 1, num*page);
		
		for (Map<String, String> map : maps) {
			Videogame v = new Videogame();
			v.fromMap(map);
			res.add(v);
		}
		
		return res;
	}
	
	
	/**
	 * videogames per piattaforma ordinate per pubdate, lista lunga num, per ogni pagina
	 */
	@Override
	public List<Videogame> videogames(int num, int page, String platform) {
		
		List<Videogame> res = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM(SELECT *, ROW_NUMBER() OVER() AS r "
				+ " FROM videogames"
				+ " WHERE platform = ? "
				+ " ORDER BY pubdate DESC) AS toview "
				+ " WHERE r BETWEEN ? AND ? ", platform, num*(page - 1)+ 1, num*page);
		
		for (Map<String, String> map : maps) {
			Videogame v = new Videogame();
			v.fromMap(map);
			res.add(v);
		}
		
		return res;
	}


	@Override
	public Videogame videogame(int id) {
		Videogame solution = null;
		
		Map<String, String> map = getOne("SELECT * FROM videogames WHERE id = ?", id);
		
		if( map != null) {
			solution = new Videogame();
			solution.fromMap(map);
		}
	
		return solution;
	}

	@Override
	public void add(Videogame videogame) {
		execute("INSERT INTO videogames (title, img, pegi, platform, genre, pubdate, description, sumvotes, numbervotes) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				videogame.getTitle(),
				videogame.getImg(),
				videogame.getPegi(),
				videogame.getPlatform(),
				videogame.getGenre(),
				videogame.getPubDate(),
				videogame.getDescription(),
				videogame.getSumVotes(),
				videogame.getNumberVotes());
		
	}

	@Override
	public void delete(int id) {
		execute("DELETE FROM videogames WHERE id = ?", id);
		
	}

	@Override
	public void update(Videogame videogame) {
		execute("UPDATE videogames SET title = ?, img = ?, pegi = ?, platform = ?,"
				+ "genre = ?, pubdate = ?, description = ?, sumvotes = ?, numbervotes = ? WHERE id= ?",
				videogame.getTitle(),
				videogame.getImg(),
				videogame.getPegi(),
				videogame.getPlatform(),
				videogame.getGenre(),
				videogame.getPubDate(),
				videogame.getDescription(),
				videogame.getSumVotes(),
				videogame.getNumberVotes(),
				videogame.getId());
		
	}
	
	

}