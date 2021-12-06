/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.controllers;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Guess;
import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.service.GTNServiceLayer;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author calebdiaz
 */
@RestController
@RequestMapping("/api/game")
public class GTNController {
    
    private final GTNServiceLayer service;
    
    public GTNController(GTNServiceLayer service){
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<Game> begin(){
        Game result = service.begin();
        if (result == null) {
            return new ResponseEntity("There was an error beginning your game.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    
    @PostMapping("/round")
    public ResponseEntity<Round> guess(@RequestBody Guess guess){
        Round result = service.guess(guess);
        if (result == null) {
            return new ResponseEntity("There was an error playing your round.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping
    public List<Game> allGames(){
        return service.getAllGames();
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> findGameById(@PathVariable int gameId){
        Game result = service.getGameById(gameId);
        if (result == null) {
            return new ResponseEntity("There is no such game.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/round/{gameId}")
    public ResponseEntity<List<Round>> allRoundsById(@PathVariable int gameId){
        List<Round> result = service.getAllRounds(gameId);
        if (result == null) {
            return new ResponseEntity("There is no such game.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    
}
