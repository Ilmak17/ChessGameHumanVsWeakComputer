package com.game.chess.game;

import com.game.chess.board.BoardImpl;
import com.game.chess.models.Board;
import com.game.chess.models.Game;
import com.game.chess.pieces.Position;
import com.game.chess.ui.UI;

public class GameImpl implements Game {
    private Board board;
    private UI ui;

    public GameImpl() {
        board = new BoardImpl();
    }

    @Override
    public void startGame() {
        UI ui = new UI();

        ui.printBoard();
    }
}
