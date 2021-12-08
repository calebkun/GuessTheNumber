/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.App;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author calebdiaz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Profile("db")
public class GTNRoundDaoDatabaseImplTest {
      
    @Autowired
    GTNRoundDao roundDao;
    
    @Autowired
    GTNGameDao gameDao;
    
    public GTNRoundDaoDatabaseImplTest() {
    }
    
    @Before
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    /**
     * Test of beginRound method, of class GTNRoundDaoDatabaseImpl.
     */
    @Test
    public void testBeginRoundGetRoundsForGame() {
//        Game game = new Game();
//        game.setAnswer("1111");
//        game = gameDao.beginGame(game);
//        
//        Game game2 = new Game();
//        game2.setAnswer("1111");
//        game2 = gameDao.beginGame(game2);
//        
//        Round round1 = new Round();
//        round1.setGuess("1111");
//        round1.setGuessTime(LocalDateTime.parse("2018-12-30T19:34:50.63"));
//        round1.setResult("e:0p:0");
//        round1.setGame(game);
//        round1 = roundDao.beginRound(round1);
//        
//        Round round2 = new Round();
//        round2.setGuess("1234");
//        round2.setGuessTime(LocalDateTime.parse("2018-12-30T19:34:50.63"));
//        round2.setResult("e:0p:0");
//        round2.setGame(game2);
//        round2 = roundDao.beginRound(round2);
//        
//        Round round3 = new Round();
//        round3.setGuess("4321");
//        round3.setGuessTime(LocalDateTime.parse("2018-12-30T19:34:50.63"));
//        round3.setResult("e:0p:0");
//        round3.setGame(game);
//        round3 = roundDao.beginRound(round3);
//        
//        List<Round> rounds = roundDao.getRoundsForGame(game);
//        
//        assertEquals(2, rounds.size());
//        assertTrue(rounds.contains(round1));
//        assertTrue(rounds.contains(round3));
//        assertFalse(rounds.contains(round2));
    }


}
