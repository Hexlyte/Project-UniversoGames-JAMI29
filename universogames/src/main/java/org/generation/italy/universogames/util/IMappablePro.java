package org.generation.italy.universogames.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Interfaccia creata durante il corso, principalmente per la trasformazione da mappa a oggetto e viceversa.
 * In modo da facilitare le interazioni con il DB.
 *
 */
public interface IMappablePro {

	default Map<String, String> toMap() {
		Map<String, String> ris = new HashMap<>();
		
		Class<? extends IMappablePro> classe = getClass();
		
		Method[] metodi = classe.getMethods();
		
		for (Method metodo : metodi) {
			
			String nomeMetodo = metodo.getName();
			
			
			if (!nomeMetodo.equalsIgnoreCase("getclass") && 
					(nomeMetodo.startsWith("get") || nomeMetodo.startsWith("is"))) {
				
				try {
					
					Object v = metodo.invoke(this);
					
					String valore = v == null ? "" : v.toString();				
					
					int indiceDiPartenza = nomeMetodo.startsWith("get") ? 3 : 2;
					
					String chiave = nomeMetodo.substring(indiceDiPartenza).toLowerCase();
					
					ris.put(chiave, valore);
				} catch (IllegalAccessException |
						IllegalArgumentException |
						InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		ris.put("Class", classe.getSimpleName());
		
		return ris;
	}
	
	default void fromMap(Map<String, String> map) {
		
		Class<? extends IMappablePro> thisClass = getClass();
		
		Method[] metodi = thisClass.getMethods();
		
		for (Method metodo : metodi) {
			
			String nomeMetodo = metodo.getName();
			
			if (nomeMetodo.startsWith("set")) {
				
				String chiave = nomeMetodo.substring(3).toLowerCase();
				
				String valore = map.get(chiave);
				
				Class<?> tipoParametro = metodo.getParameterTypes()[0];
			
				try {
					if (tipoParametro.equals(boolean.class)) {
						metodo.invoke(this, valore.equals("1") || valore.equals("true"));
					} else if (tipoParametro.equals(char.class)) {
					
						metodo.invoke(this, valore.charAt(0));
					} else if (tipoParametro.isPrimitive()) {
						
 						Class<?> tipoBoxato = Array.get(Array.newInstance(tipoParametro, 1), 0).getClass();
						
						Method[] metodiBoxati = tipoBoxato.getMethods();
					
						for (Method metodoBoxato : metodiBoxati) {
							
							if (metodoBoxato.getName().startsWith("parse") &&
									metodoBoxato.getParameterCount() == 1) {
								
								metodo.invoke(this, metodoBoxato.invoke(null, valore));
								break;
							}
						}
					} else {
						metodo.invoke(this, valore);
					}					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
}