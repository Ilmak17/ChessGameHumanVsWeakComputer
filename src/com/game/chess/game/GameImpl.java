package com.game.chess.game;

import com.game.chess.board.BoardImpl;
import com.game.chess.board.Board;
import com.game.chess.game.input.SelectedPiece;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Position;
import com.game.chess.pieces.enums.Color;
import com.game.chess.ui.Visual;
import com.game.chess.ui.VisualImpl;
import com.game.chess.game.input.InputHelper;

import java.util.List;
import java.util.Scanner;

import static java.util.Objects.nonNull;

public class GameImpl implements Game {
    private final Board board;
    private final Visual visual;
    private final Scanner scanner;
    private boolean running;
    private boolean isWhiteTurn;
    private boolean drawOffered;

    private static final String MOVE = "1";
    private static final String DRAW = "2";
    private static final String SURRENDER = "3";

    public GameImpl() {
        board = new BoardImpl();
        visual = new VisualImpl(board);
        scanner = new Scanner(System.in);
        this.running = true;
        this.isWhiteTurn = true;
        this.drawOffered = false;
    }

    @Override
    public void startGame() {
        while (running) {
            visual.print();
            System.out.println("Turn of: " + (isWhiteTurn ? "White Player" : "Black Player"));
            String choice = getOption();
            handleChoice(choice);

            if (running) {
                drawOffered = false;
            }
        }

        scanner.close();
    }

    private String getOption() {
        System.out.println("Choose option:");
        System.out.println("1. Move");
        System.out.println("2. Offer / Accept a draw");
        System.out.println("3. Give up");

        return scanner.next();
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case MOVE -> move();
            case DRAW -> offerDraw();
            case SURRENDER -> giveUp();
            default -> System.out.println("Invalid selection. Please try again.");
        }
    }

    private void move() {
        boolean successful = false;

        while (!successful) {
            try {
                SelectedPiece pieceSelection = getSelectedPiece();
                if (!isCorrectColor(pieceSelection.getSelectedPieceIndex())) {
                    System.out.println("Wrong color selected. Turn is for: " + getColor());
                    return;
                }

                successful = tryMove(pieceSelection);
            } catch (Exception e) {
                System.out.println("Piece not found or incorrect input. Please Try again.");
            }
        }

        updateTurn();
        getGameState();
    }

    private void offerDraw() {
        if (drawOffered) {
            System.out.println("The game ended in a draw.");
            running = false;
            return;
        }
        drawOffered = true;
        System.out.println("A draw was offered. Waiting for the other player's decision.");
        updateTurn();
    }

    private boolean tryMove(SelectedPiece selectedPiece) {
        String input = getInput("Select a target field (Example E3): ");
        Position targetPosition = toPosition(input);
        int selectedPieceIndex = selectedPiece.getSelectedPieceIndex();
        List<Piece> pieces = board.getPieces();
        Piece piece = pieces.get(selectedPieceIndex);

        if (piece.isValidMove(targetPosition)) {
            if (!isMoveCausingCheckmate(piece, targetPosition)) {
               Piece piece1 = pieces.get(selectedPieceIndex);
               piece1.move(targetPosition);

                return true;
            }
            System.out.println("Your king is in check or will be after this move.");
        }

        System.out.println("Invalid move. Please try again.");

        return false;
    }

    private void giveUp() {
        System.out.println("The " + getColor() + " Player Gave up. End of the game...");

        running = false;
    }

    private void getGameState() {
        Color color = getColor();
        if (board.isCheckmate(color) && !board.canPreventCheckmate()) {
            System.out.println("Check Mate! " + color);
            running = false;
        }

        if (board.isKingInCheck(color)) {
            System.out.println("The " + color + " King is in check!");
        }
    }

    private boolean isMoveCausingCheckmate(Piece selectedPiece, Position targetPosition) {
        Position currentPosition = selectedPiece.getPosition();
        boolean moveCreatesCheckmate;

        Piece capturedPiece = board.getPieceByPosition(targetPosition);
        if (nonNull(capturedPiece)) capturedPiece.forceMove(new Position(8, 8));

        selectedPiece.forceMove(targetPosition);
        moveCreatesCheckmate = board.isCheckmate(getColor());

        selectedPiece.forceMove(currentPosition);

        if (nonNull(capturedPiece)) capturedPiece.forceMove(targetPosition);

        return moveCreatesCheckmate;
    }

    private SelectedPiece getSelectedPiece() {
        String input = getInput("Pick a piece (example E2): ");
        Position position = toPosition(input);
        int pieceIndex = InputHelper.findPieceIndexByPosition(board.getPieces(), position);

        return SelectedPiece.Builder.newBuilder()
                .selectedPieceIndex(pieceIndex)
                .position(position)
                .build();
    }

    private String getInput(String val) {
        System.out.print(val);

        return scanner.next();
    }

    private boolean isCorrectColor(int pieceIndex) {
        List<Piece> pieces = board.getPieces();

        return (isWhiteTurn && pieces.get(pieceIndex).getColor() == Color.WHITE) ||
                (!isWhiteTurn && pieces.get(pieceIndex).getColor() == Color.BLACK);
    }

    private Position toPosition(String input) {
        char colChar = input.charAt(0);
        char rowChar = input.charAt(1);

        int col = InputHelper.returnCol(colChar);
        int row = InputHelper.returnRow(rowChar);
        return new Position(row, col);
    }

    private Color getColor() {
        return isWhiteTurn ? Color.WHITE : Color.BLACK;
    }

    private void updateTurn() {
        isWhiteTurn = !isWhiteTurn;
    }
}
