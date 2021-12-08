/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */

@Repository
@Profile("memory")
public class GTNGameDaoInMemoryImpl implements GTNGameDao {

    private static final List<Game> games = new ArrayList<>();
    private static final List<Round> rounds = new ArrayList<>();
    
    @Override
    public Game beginGame(Game game) {
        
        // Get appropriate id for game
        int nextId = games.stream()
                .mapToInt(i -> i.getGameId())
                .max()
                .orElse(0) + 1;

        game.setGameId(nextId);
        games.add(game);
        
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }

    @Override
    public Game findGameById(int gameId) {
        return games.stream()
                .filter(i -> i.getGameId() == gameId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateGame(Game game) {
        
        int index = 0;
        while (index < games.size()
                && games.get(index).getGameId() != game.getGameId()) {
            index++;
        }

        if (index < games.size()) {
            games.set(index, game);
        }
        return index < games.size();
    }   

    @Override
    public void deleteGameById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
