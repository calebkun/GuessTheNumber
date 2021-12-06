/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface GTNGameDao {
    
    Game beginGame(Game game);
    List<Game> getAllGames();
    Game findGameById(int gameId);
    boolean updateGame(Game game);

}
