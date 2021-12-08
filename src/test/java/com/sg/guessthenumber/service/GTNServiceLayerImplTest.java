/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.models.Guess;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author calebdiaz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GTNServiceLayerImplTest {
    
    @Autowired
    GTNServiceLayer service;
    
    public GTNServiceLayerImplTest() {
    }

    /**
     * Test of begin method, of class GTNServiceLayerImpl.
     */
    @Test
    public void testBeginAndAnswer() {
        Game returned = service.begin();
        
        assertNotNull(returned);
        
        Guess guess = new Guess();
        guess.setGuess(returned.getAnswer());
        guess.setGameId(1);
        
        try{ // checking that generated answer meets specifications by treating it as guess
            service.guess(guess);
        } catch (Exception e){
            fail("Exception should not have been thrown.");
        }
    }

    /**
     * Test of guess method, of class GTNServiceLayerImpl.
     */
    @Test
    public void testGuessFinishedGame() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("1234");
        guess.setGameId(2);
        
        try{
            service.guess(guess);
            fail("Expected exception was not thrown.");
        } catch (GTNInvalidGuessException e){
            fail("Incorrect exception thrown.");
        } catch (GTNFinishedGameException e){
            return;
        }
    }
    
    @Test
    public void testGuessNoGame() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("1234");
        guess.setGameId(3);
        
        assertNull(service.guess(guess));
    }
    
    @Test
    public void testGuessNonNumerical() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("123e");
        guess.setGameId(1);
        
        try{
            service.guess(guess);
            fail("Expected exception was not thrown.");
        } catch (GTNFinishedGameException e){
            fail("Incorrect exception thrown.");
        } catch (GTNInvalidGuessException e){
            return;
        }
    }
    
    @Test
    public void testGuessLengthUnder() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("123");
        guess.setGameId(1);
        
        try{
            service.guess(guess);
            fail("Expected exception was not thrown.");
        } catch (GTNFinishedGameException e){
            fail("Incorrect exception thrown.");
        } catch (GTNInvalidGuessException e){
            return;
        }
    }

    @Test
    public void testGuessLengthOver() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("12345");
        guess.setGameId(1);
        
        try{
            service.guess(guess);
            fail("Expected exception was not thrown.");
        } catch (GTNFinishedGameException e){
            fail("Incorrect exception thrown.");
        } catch (GTNInvalidGuessException e){
            return;
        }
    }
    
    @Test
    public void testGuessDuplicateDigits() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("1231");
        guess.setGameId(1);
        
        try{
            service.guess(guess);
            fail("Expected exception was not thrown.");
        } catch (GTNFinishedGameException e){
            fail("Incorrect exception thrown.");
        } catch (GTNInvalidGuessException e){
            return;
        }
    }
    
    @Test
    public void testGuessValid() throws Exception {
        Guess guess = new Guess();
        guess.setGuess("1235");
        guess.setGameId(1);

        Round round = new Round();
        
        try{
            round = service.guess(guess);
        } catch (Exception e){
            fail("Exception should not have been thrown.");
        }
        
        assertNotNull(round);
        assertEquals(guess.getGuess(), round.getGuess());
        assertEquals(guess.getGameId(), round.getGame().getGameId());
    }
    
    @Test
    public void testGuessResult() throws Exception {
        // Exact matches
        Guess guess = new Guess();
        guess.setGuess("1235");
        guess.setGameId(1);
        
        Round round = service.guess(guess);
        assertEquals(round.getResult(), "e:3p:0");
        
        // Partial matches
        guess = new Guess();
        guess.setGuess("4321");
        guess.setGameId(1);
        
        round = service.guess(guess);
        assertEquals(round.getResult(), "e:0p:4");
        
        // No matches
        guess = new Guess();
        guess.setGuess("5678");
        guess.setGameId(1);
        
        round = service.guess(guess);
        assertEquals(round.getResult(), "e:0p:0");
        
        // Correct guess
        guess = new Guess();
        guess.setGuess("1234");
        guess.setGameId(1);
        
        round = service.guess(guess);
        assertEquals(round.getResult(), "e:4p:0");
    }
    
    /**
     * Test of getAllGames method, of class GTNServiceLayerImpl.
     */
    @Test
    public void testGetAllGames() {
//        List<Game> games = service.getAllGames();
//        
//        for(Game g : games){
//            if(g.getFinished()){
//                assertEquals(g.getAnswer(),"xxxx");
//            }
//        }
    }
    
}
