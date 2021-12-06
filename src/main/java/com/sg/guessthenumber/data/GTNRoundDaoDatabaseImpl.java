/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.data.GTNGameDaoDatabaseImpl.GameMapper;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author calebdiaz
 */
@Repository
@Profile("db")
public class GTNRoundDaoDatabaseImpl implements GTNRoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GTNRoundDaoDatabaseImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public Round beginRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO gtnround(guess, guessTime, result, gameId) VALUES(?,?,?,?)";
        jdbcTemplate.update(INSERT_ROUND,
                round.getGuess(),
                Timestamp.valueOf(round.getGuessTime()),
                round.getResult(),
                round.getGame().getGameId());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);
        
        return round;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        final String SELECT_ROUNDS_FOR_GAME = "SELECT * FROM gtnround WHERE gameId = ?";
        List<Round> rounds = jdbcTemplate.query(SELECT_ROUNDS_FOR_GAME, 
                new RoundMapper(), game.getGameId());
        
        addGameToRounds(rounds);
        
        return rounds;
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("id"));
            round.setGuess(rs.getString("guess"));
            round.setGuessTime(rs.getTimestamp("guessTime").toLocalDateTime());
            return round;
        }
    }
    
    private Game getGameForRound(Round round){
        final String SELECT_GAME_FOR_ROUND = "SELECT g.* FROM game g "
                + "JOIN gtnround r ON g.id = r.gameId WHERE r.id = ?";
        return jdbcTemplate.queryForObject(SELECT_GAME_FOR_ROUND, new GameMapper(), 
                round.getRoundId());
    }
    
    private void addGameToRounds(List<Round> rounds){
        for(Round round : rounds) {
            round.setGame(getGameForRound(round));
        }
    }
}
