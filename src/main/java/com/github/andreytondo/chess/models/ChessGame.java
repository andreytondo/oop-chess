package com.github.andreytondo.chess.models;

import com.github.andreytondo.chess.game.GameState;
import com.github.andreytondo.chess.persistence.utils.Column;
import com.github.andreytondo.chess.persistence.utils.Entity;
import com.github.andreytondo.chess.persistence.utils.Id;
import com.github.andreytondo.chess.persistence.utils.Table;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Table(name = "game")
public class ChessGame implements Entity<Integer> {

    @Id
    @Column(name = "game_id")
    private Integer id;

    @Column(name = "fen")
    private String fen;

    @Column(name = "state")
    private GameState state;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        String data = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(createdAt.toLocalDateTime());
        return id + " - (" + data + ")" + " - " + state.getDescription();
    }
}
