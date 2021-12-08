/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GTNGameDao;
import com.sg.guessthenumber.data.GTNRoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Guess;
import com.sg.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */

@Repository
public class GTNServiceLayerImpl implements GTNServiceLayer {

    private GTNGameDao gameDao;
    private GTNRoundDao roundDao;
    
    public GTNServiceLayerImpl(GTNGameDao gameDao, GTNRoundDao roundDao){
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    @Override
    public Game begin() {
        Game game = new Game();
 
        game.setAnswer(generateAnswer());
        
        return gameDao.beginGame(game);
    }

    @Override
    public Round guess(Guess guess) throws GTNInvalidGuessException,
            GTNFinishedGameException {
        Round round = new Round();
        Game game = gameDao.findGameById(guess.getGameId());
        
        validateGuess(guess.getGuess());
        
        if(game == null){
            return null;
        } else if(game.getFinished()){
            throw new GTNFinishedGameException("Cannot begin round. This game has already been completed.");
        } else {
            String result = calculateResult(guess.getGuess(), game.getAnswer());
            
            if(verifyCorrectGuess(result)){
                game.setFinished(true);
                gameDao.updateGame(game);
            }

            round.setGuess(guess.getGuess());
            round.setGuessTime(LocalDateTime.now());
            round.setGame(game);
            round.setResult(result);

            return roundDao.beginRound(round);
        }
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        games.stream()
                .forEach((g) -> {
                    if(!g.getFinished()){
                        g.setAnswer("xxxx");
                    }
                });
        
        return games;
    }

    @Override
    public Game getGameById(int gameId) {
        return gameDao.findGameById(gameId);
    }

    @Override
    public List<Round> getAllRounds(int gameId) {
        Game game = gameDao.findGameById(gameId);
        if(game != null){
            return roundDao.getRoundsForGame(game);
        } else {
            return null;
        }
    }
    
    private String generateAnswer(){
        List<Integer> numbers = new ArrayList<>();
        
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String answer = "";
        for(int i = 0; i < 4; i++){
            answer += numbers.get(i).toString();
        }
        
        return answer;
    }
    
    public String validateGuess(String guess) throws GTNInvalidGuessException {
        // throw exception if the string contains non-numerical characters
        try {
            Integer.parseInt(guess);
        } catch(NumberFormatException e){
            throw new GTNInvalidGuessException("Invalid guess. Contains non-numeric characters.");
        }

        // throw exception if guess is not 4 digits
        if(guess.length() != 4){
            throw new GTNInvalidGuessException("Invalid guess. Guess must be 4 digits.");
        }
        
        // check if guess contains duplicate digits
        List<Character> ch = guess.chars()
                .mapToObj((c) -> (char) c)
                .collect(Collectors.toList());
        
        Set<Character> set = new HashSet<>(ch);
        if(set.size() != 4){
            throw new GTNInvalidGuessException("Invalid guess. Contains duplicate digits.");
        }
        
        return guess;
    }
    
    private String calculateResult(String guess, String answer){
        // convert strings to char arrays for parsing
        char[] chGuess = guess.toCharArray();
        char[] chAnswer = answer.toCharArray();
        
        // check for matches
        int e = 0;
        int p = 0;
        for(int i = 0; i < chGuess.length; i++){
           if(chGuess[i] == chAnswer[i]){ // exact match
               e++;
           } else if(answer.contains(String.valueOf(chGuess[i]))){ // partial match
               p++;
           }
        }
        
        String result = "e:"+e+"p:"+p;
        
        return result;
    }
    
    private boolean verifyCorrectGuess(String result){
        int e = Integer.parseInt(String.valueOf(result.charAt(2)));
        return e == 4;
    }
    
}
