package com.github.andreytondo.chess.dao;

import com.github.andreytondo.chess.models.ChessGame;
import com.github.andreytondo.chess.persistence.AbstractDAO;

public class ChessGameDAO extends AbstractDAO<Integer, ChessGame> {

    public ChessGameDAO() {
        super(ChessGame.class);
    }
}
