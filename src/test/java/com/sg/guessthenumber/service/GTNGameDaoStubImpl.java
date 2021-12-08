/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GTNGameDao;
import com.sg.guessthenumber.models.Game;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */
@Repository
@Profile("test")
public class GTNGameDaoStubImpl implements GTNGameDao {
    
    Game onlyGame;
    Game onlyOtherGame;
    
    public GTNGameDaoStubImpl(){
        onlyGame = new Game();
        onlyGame.setAnswer("1234");
        onlyGame.setGameId(1);
        onlyGame.setFinished(false);
        
        onlyOtherGame = new Game();
        onlyOtherGame.setAnswer("4321");
        onlyOtherGame.setGameId(2);
        onlyOtherGame.setFinished(true);
    }

    @Override
    public Game beginGame(Game game) {
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        games.add(onlyGame);
        return games;
    }

    @Override
    public Game findGameById(int gameId) {
        if(gameId == onlyGame.getGameId()){
            onlyGame.setFinished(false);
            return onlyGame;
        } else if(gameId == onlyOtherGame.getGameId()){
            return onlyOtherGame;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateGame(Game game) {
        return game.getGameId()==onlyGame.getGameId();
    }

    @Override
    public void deleteGameById(int id) {
        
    }
    
}
