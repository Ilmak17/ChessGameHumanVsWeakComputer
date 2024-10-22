package com.game.chess.game;

import com.game.chess.board.BoardImpl;
import com.game.chess.models.Board;
import com.game.chess.models.Game;

public class GameImpl implements Game {
    private Board board;

    public GameImpl() {
        board = new BoardImpl();
    }

    @Override
    public void startGame() {

    }
}
