/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.models;

import java.util.Objects;

/**
 *
 * @author calebdiaz
 */
public class Guess {
    
    private String guess;
    private int gameId;
    
    public void setGuess(String guess){
        this.guess = guess;
    }
    
    public String getGuess(){
        return this.guess;
    }
    
    public void setGameId(int gameId){
        this.gameId = gameId;
    }
    
    public int getGameId(){
        return this.gameId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.guess);
        hash = 89 * hash + this.gameId;
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
        final Guess other = (Guess) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        return true;
    }
    
    
}
