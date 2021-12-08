/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

/**
 *
 * @author calebdiaz
 */
public class GTNInvalidGuessException extends Exception {

    public GTNInvalidGuessException(String message) {
        super(message);
    }

    public GTNInvalidGuessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
