/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

/**
 *
 * @author calebdiaz
 */
public class GTNFinishedGameException extends Exception {

    public GTNFinishedGameException(String message) {
        super(message);
    }

    public GTNFinishedGameException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
