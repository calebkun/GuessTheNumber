/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.models;

/**
 *
 * @author calebdiaz
 */
public class Game {
    
    private int gameId;
    private String answer;
    private boolean finished = false;
    
    public int getGameId(){
        return this.gameId;
    }
    
    public void setGameId(int gameId){
        this.gameId = gameId;
    }
    
    public String getAnswer(){
        return this.answer;
    }
    
    public void setAnswer(String answer){
        this.answer = answer;
    }
    
    public boolean getFinished(){
        return this.finished;
    }
    
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    
}
