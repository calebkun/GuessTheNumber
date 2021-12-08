/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.models;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.roundId;
        hash = 23 * hash + Objects.hashCode(this.guess);
        hash = 23 * hash + Objects.hashCode(this.guessTime);
        hash = 23 * hash + Objects.hashCode(this.result);
        hash = 23 * hash + Objects.hashCode(this.game);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return true;
    }
    
    
    
}
