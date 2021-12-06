/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.models;

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
}
