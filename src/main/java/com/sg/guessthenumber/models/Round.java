/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author calebdiaz
 */
public class Round {
    
    private int roundId;
    private String guess;
    private LocalDateTime guessTime;
    private String result;
    private Game game;

    public int getRoundId(){
        return this.roundId;
    }
    
    public void setRoundId(int roundId){
        this.roundId = roundId;
    }
    
    public String getGuess(){
        return this.guess;
    }
    
    public void setGuess(String guess){
        this.guess = guess;
    }
    
    public LocalDateTime getGuessTime(){
        return this.guessTime;
    }
    
    public void setGuessTime(LocalDateTime guessTime){
        this.guessTime = guessTime;
    }
    
    public String getResult(){
        return this.result;
    }
    
    public void setResult(String result){
        this.result = result;
    }
    
    public Game getGame(){
        return this.game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
}
