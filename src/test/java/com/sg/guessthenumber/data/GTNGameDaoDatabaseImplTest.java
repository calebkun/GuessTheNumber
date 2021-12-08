/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
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
@SpringBootTest
@Profile("db")
public class GTNGameDaoDatabaseImplTest {
    
    @Autowired
    GTNGameDao gameDao;
    
    public GTNGameDaoDatabaseImplTest() {
    }
    
    @Before
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    /**
     * Test of beginGame method, of class GTNGameDaoDatabaseImpl.
     */
    @Test
    public void testBeginFindGame() {
        Game game = new Game();
        game.setAnswer("1111");
        game = gameDao.beginGame(game);
        
        Game fromDao = gameDao.findGameById(game.getGameId());
        
        assertEquals(game, fromDao);
    }

    /**
     * Test of getAllGames method, of class GTNGameDaoDatabaseImpl.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setAnswer("1111");
        gameDao.beginGame(game);
        
        Game game2 = new Game();
        game2.setAnswer("1111");
        gameDao.beginGame(game2);
        
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of updateGame method, of class GTNGameDaoDatabaseImpl.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setAnswer("1111");
        game = gameDao.beginGame(game);
        
        Game fromDao = gameDao.findGameById(game.getGameId());
        
        assertEquals(game, fromDao);
        
        game.setAnswer("1234");
        
        gameDao.updateGame(game);
        
        assertNotEquals(game, fromDao);
        
        fromDao = gameDao.findGameById(game.getGameId());
        
        assertEquals(game, fromDao);
    }
    
}
