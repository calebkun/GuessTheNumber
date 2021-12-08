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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Objects.hashCode(this.answer);
        hash = 53 * hash + (this.finished ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }
    
    
    
}
