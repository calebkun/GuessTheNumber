/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GTNRoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.time.LocalDateTime;
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
public class GTNRoundDaoStubImpl implements GTNRoundDao {
    
    Game onlyGame;
    Round onlyRound;
    
    public GTNRoundDaoStubImpl() {
        onlyGame = new Game();
        onlyGame.setGameId(1);
        onlyGame.setAnswer("1234");
        
        onlyRound = new Round();
        onlyRound.setRoundId(1);
        onlyRound.setGuess("1456");
        onlyRound.setResult("e:1p:1");
        onlyRound.setGuessTime(LocalDateTime.parse("2018-12-30T19:34:50.63"));
        onlyRound.setGame(onlyGame);
    }

    @Override
    public Round beginRound(Round round) {
        return round;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        List<Round> rounds = new ArrayList<>();
        if(game.equals(onlyGame)){
            rounds.add(onlyRound);
            return rounds;
        } else {
            return rounds;
        }
    }

    @Override
    public void deleteRoundById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
