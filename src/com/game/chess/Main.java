package com.game.chess;

import com.game.chess.game.GameImpl;
import com.game.chess.game.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new GameImpl();
        game.startGame();
    }
}
