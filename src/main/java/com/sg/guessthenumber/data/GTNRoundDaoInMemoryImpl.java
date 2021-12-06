/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */
@Repository
@Profile("memory")
public class GTNRoundDaoInMemoryImpl implements GTNRoundDao {
    
    private static final List<Round> rounds = new ArrayList<>();

    @Override
    public Round beginRound(Round round) {
        // Get appropriate id for game
        int nextId = rounds.stream()
                .mapToInt(i -> i.getRoundId())
                .max()
                .orElse(0) + 1;

        round.setRoundId(nextId);
        rounds.add(round);
        
        return round;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        List<Round> roundsForGame = rounds.stream()
                .filter((r) -> game.getGameId() == r.getGame().getGameId())
                .collect(Collectors.toList());
        return roundsForGame;
    }
            
}
