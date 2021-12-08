/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */
@Repository
@Profile("db")
public class GTNGameDaoDatabaseImpl implements GTNGameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GTNGameDaoDatabaseImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Game beginGame(Game game) {
        final String sql = "INSERT INTO game(id, answer, finished) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getGameId());
            statement.setString(2, game.getAnswer());
            statement.setBoolean(3, game.getFinished());
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbcTemplate.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game findGameById(int gameId) {
        final String sql = "SELECT * FROM game WHERE id = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE game SET "
                + "answer = ?, "
                + "finished = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.getFinished(),
                game.getGameId()) > 0;
    }

    @Override
    public void deleteGameById(int id) {
        final String DELETE_ROUND_BY_GAME = "DELETE FROM gtnround WHERE gameId = ?";
        jdbcTemplate.update(DELETE_ROUND_BY_GAME, id);
        
        final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
        jdbcTemplate.update(DELETE_GAME, id);
    }
    
    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
}
