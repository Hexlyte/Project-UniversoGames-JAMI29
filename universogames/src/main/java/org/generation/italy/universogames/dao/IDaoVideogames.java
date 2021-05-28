package org.generation.italy.universogames.dao;

import java.util.List;

import org.generation.italy.universogames.model.RankedVG;
import org.generation.italy.universogames.model.Videogame;

public interface IDaoVideogames {

    List<Videogame> videogames();
    
    List<Videogame> videogamesLast(int num);
    
    List<Videogame> videogamesAvg(int num);
    
    List<Videogame> videogames(int num, int page);
    
    List<Videogame> videogames(int num, int page, String platform);
    
    List<RankedVG> videogamesRanked(int num);

    Videogame videogame(int id);

    public void add(Videogame videogame);

    public void delete(int id);

    public void update( Videogame videogame);

}