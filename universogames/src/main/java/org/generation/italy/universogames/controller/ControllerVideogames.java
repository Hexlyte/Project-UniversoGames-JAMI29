package org.generation.italy.universogames.controller;

import java.util.List;

import org.generation.italy.universogames.dao.IDaoVideogames;
import org.generation.italy.universogames.model.RankedVG;
import org.generation.italy.universogames.model.Videogame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videogames")
public class ControllerVideogames {

    @Autowired
    private IDaoVideogames dao;

    @GetMapping
    public List<Videogame> get() {
        return dao.videogames();
    }
    
    @GetMapping("/avgvotes/{num}")
    public List<Videogame> getVideogamesAvg(@PathVariable int num) {
        return dao.videogamesAvg(num);
    }
    
    @GetMapping("/ranked/{num}")
    public List<RankedVG> getVideogamesRanked(@PathVariable int num) {
        return dao.videogamesRanked(num);
    }
    
    @GetMapping("/last/{num}")
    public List<Videogame> getVideogamesLast(@PathVariable int num) {
        return dao.videogamesLast(num);
    }
    
    @GetMapping("/last/{num}/{page}")
    public List<Videogame> getVideogames(@PathVariable int num, @PathVariable int page) {
        return dao.videogames(num, page);
    }
    
    @GetMapping("/last/{platform}/{num}/{page}")
    public List<Videogame> getVideogames(@PathVariable int num, @PathVariable int page,@PathVariable String platform) {
        return dao.videogames(num, page, platform);
    }

    @GetMapping("/{id}")
    public Videogame get(@PathVariable int id) {
        return dao.videogame(id);
    }

    @PostMapping
    public void add(@RequestBody Videogame videogame) {
        dao.add(videogame);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        dao.delete(id);
    }

    @PutMapping
    public void update(@RequestBody Videogame videogame) {
        dao.update(videogame);
    }
}