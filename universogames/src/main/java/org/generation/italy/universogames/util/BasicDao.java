package org.generation.italy.universogames.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//VERSIONE di BasicDao MODIFICATA DA FEDERICO, HA AGGIUNTO IL METODO GETONECOLUMN

/**
 * Questa classe ha lo scopo di fornire gli strumenti base per effettuare ORM.
 * Stabilita la connessione � in grado di eseguire query e restituire
 * in caso di necessit� una lista di mappe o una mappa che descrive un resultset
 * La conessione di questo dao � sempre aperta!
 * @author trito
 */
public abstract class BasicDao {

	private Connection connection;
	
	public BasicDao(String dbAddress, String user, String password) {
		super();
		try {
			connection = DriverManager.getConnection(dbAddress, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Hai inserito il connettore?");
			System.out.println("Il dbAddress, lo user e la password sono corretti?");
		}
	}
	
	/**
	 * Lista contenente mappe che descrivono delle entities nella persistenza.
	 * La mappa � praticamente la rappresentazione di una RIGA di una tabella.
	 * La lista quindi � l'insieme delle righe di una tabella
	 * @param sql la query da inviare al DB
	 * @param conditions il/i valore/i da sostituire ai placeholders della query
	 * @return La lista contenente tutte le mappe restituire dal DB in base alla
	 * 			query inserita
	 */
	public List<Map<String, String>> getAll(String sql, Object... conditions) {
		
		List<Map<String, String>> ris = new ArrayList<>();
		
		try {
			ResultSet rs = executeQuery(sql, conditions);
			
			while (rs.next()) {
				Map<String, String> map = mapFromRS(rs);
				
				ris.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ris;
	}

	/**
	 * Restituisce il ResultSet risultante da una query
	 * @param sql la query da inviare
	 * @param conditions il/i valore/i da sostituire ai placeholders della query
	 * @return L'oggetto ResultSet risultante dal PreparedStatement in base alla
	 * 			query inserita
	 * @throws SQLException
	 */
	private ResultSet executeQuery(String sql, Object... conditions) throws SQLException {
		return preparedStm(sql, conditions).executeQuery();
	}

	/**
	 * Restituisce l'oggetto di tipo PreparedStatement per effetuare query
	 * 
	 * String sql = "SELECT * FROM tabella WHERE id = ?" => conditions[0] => 1
	 * ?(1) => conditions[0]
	 * ?(2) => conditions[1]
	 * @param sql la query da inviare
	 * @param conditions il/i valore/i da sostituire ai placeholders della query
	 * @return il PreparedStatement contenente la query con i placeholders sostituiti
	 * @throws SQLException
	 */
	private PreparedStatement preparedStm(String sql, Object... conditions) throws SQLException {
		PreparedStatement stm = connection.prepareStatement(sql);
		
		// itero i parametri e assegno al ? della query la condizione corrispondente
		for (int i = 0; i < conditions.length; i++) {
			stm.setObject(i + 1, conditions[i]);
		}
		
		return stm;
	}

	/**
	 * Restituire la mappa di una singola riga di un ResultSet dove la chiave
	 * rappresenta il nome della colonna della tabella e il valore quello nella cella
	 * di quella determinata riga
	 * @param rs il ResultSet ottenuto dal DB eseguendo una determinata query
	 * @return la mappa che desccrive la singola riga di un ResultSet
	 * @throws SQLException
	 */
	private Map<String, String> mapFromRS(ResultSet rs) throws SQLException {
		Map<String, String> map = new HashMap<>();
		
		ResultSetMetaData meta = rs.getMetaData();
		
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			map.put(meta.getColumnName(i), rs.getString(i));
		}
		
		return map;
	}
	
	/**
	 * Mappa che descrive un'entit� nella persistenza
	 * @param sql la query da inviare
	 * @param conditions il/i valore/i da sostituire ai placeholders della query
	 * @return La mappa restituita dal DB in base alla query inserita
	 */
	public Map<String, String> getOne(String sql, Object... conditions) {
		Map<String, String> ris = null;
		
		try {
			ResultSet rs = executeQuery(sql, conditions);
			
			if (rs.next()) {
				ris = mapFromRS(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ris;
	}
	
	
	/**
	 * Restituisce la lista di stringhe della prima colonna di un resultSet
	 * @param sql
	 * @param conditions
	 * @return
	 */
	public List<String> getOneColumn(String sql, Object... conditions) {
		List<String> ris = new ArrayList<>();
		
		try {
			ResultSet rs = executeQuery(sql, conditions);
			
			while (rs.next()) {
				ris.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ris;
	}
	
	/**
	 * Esegue una query impostando prima le condizioni
	 * @param sql la query da inviare
	 * @param conditions il/i valore/i da sostituire ai placeholders della query
	 */
	public void execute(String sql, Object... conditions) {
		try {
			preparedStm(sql, conditions).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce un prepared statemen che � in grado di fornire
	 * l'id generato dall'inserimento
	 * @param sql
	 * @param conditions
	 * @return il PreparedStatement contenente la query con i placeholders
	 * 	       sostituiti e con la comunicazione di voler restituta la pk
	 * @throws SQLException
	 */
	private PreparedStatement preparedStatementWithGeneratedKey(String sql, Object... conditions) throws SQLException {
		
		PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		for (int i = 0; i < conditions.length; i++) {
			stm.setObject(i + 1, conditions[i]);
		}
		
		return stm;
	}
	
	/**
	 * Metodo che effettua una insert e restituisce l'id che viene auto
	 * generato dal DB
	 * @param sql
	 * @param conditions
	 * @return l'id generato da db pre questo insert
	 */
	public int insertAndGetId(String sql, Object... conditions) {
		int id = 0;
		
		try {
			PreparedStatement stm = preparedStatementWithGeneratedKey(sql, conditions);
			
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
}