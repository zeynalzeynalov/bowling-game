package com.bowling;

import com.scoring.Board;
import com.scoring.BoardFrame;
import com.scoring.InvalidPinCountException;

import java.util.Random;

public class Player extends Thread {

    private String name;
    private Game currentGame;
    private Board scoreBoard;
    private boolean printActions;

    public Player(String name) {
        this.name = name;
        this.scoreBoard = new Board();
    }

    @Override
    public void run() {

        try {
            synchronized (currentGame) {

                while ( canContinueToGame() ) {

                    while( willWaitOthers() ) {
                        currentGame.wait();
                    }

                    int maxPinsCount = BoardFrame.maxPinsCount;
                    int lastPinsCount = 0;

                    do {
                        lastPinsCount = getRandomNumber(maxPinsCount);

                        if(printActions)
                            System.out.println("Player \"" + name + "\" threw " + lastPinsCount);

                        maxPinsCount -= lastPinsCount;

                    } while( !throwBall(lastPinsCount) );

                    currentGame.currentPlayer++;
                    currentGame.notifyAll();
                }

                System.out.println(String.format("[ Player \"%s\" finished game ]", name));
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    protected boolean throwBall(int knockedPinsCount) {

        boolean result = false;

        try {
            result =  scoreBoard.saveBallResult(knockedPinsCount);
        }
        catch (InvalidPinCountException ex) {
            printException(ex.toString());
            result = false;
        }

        return result;
    }

    protected void throwBall(int[] knockedPinsCounts) {

        for(int pins : knockedPinsCounts) {
            try {
                scoreBoard.saveBallResult(pins);
            }
            catch (InvalidPinCountException ex) {
                printException(ex.toString());
                return;
            }

            if(scoreBoard.isBoardClosed())
                return;
        }
    }

    protected Board getScoreBoard() {

        return scoreBoard;
    }

    protected void setCurrentGame(Game currentGame) {

        this.currentGame = currentGame;
    }

    protected String getPlayerName() {

        return name;
    }

    protected void setPrintActions(boolean printActions) {

        this.printActions = printActions;
    }

    //<editor-fold desc="Internal helper methods">

    private int getRandomNumber(int max) {

        Random randomNumber = new Random();
        return randomNumber.nextInt(max + 1);
    }

    private boolean willWaitOthers() {

        return !currentGame.getCurrentPlayer().getPlayerName().equals(this.name);
    }

    private boolean canContinueToGame() {

        return !scoreBoard.isBoardClosed();
    }

    private void printException(String ex) {

        System.out.println(String.format("[ WARNING: Player %s - %s]", getPlayerName(), ex));
    }

    //</editor-fold>
}