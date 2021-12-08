
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Guess;
import com.sg.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface GTNServiceLayer {
    
    Game begin();
    Round guess(Guess guess) throws GTNInvalidGuessException,
            GTNFinishedGameException;
    List<Game> getAllGames();
    Game getGameById(int gameId);
    List<Round> getAllRounds(int gameId);
    
}
