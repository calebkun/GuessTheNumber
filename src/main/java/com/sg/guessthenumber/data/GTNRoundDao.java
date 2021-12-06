/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface GTNRoundDao {
    
    Round beginRound(Round round);
    List<Round> getRoundsForGame(Game game);

}
