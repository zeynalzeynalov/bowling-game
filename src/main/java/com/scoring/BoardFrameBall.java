package com.scoring;

public class BoardFrameBall {

    private int knockedPins;
    private BoardFrameBall nextBall;

    public BoardFrameBall(int knockedPinsCount) {

        this.knockedPins = knockedPinsCount;
    }

    protected int getKnockedPins() {

        return knockedPins;
    }

    protected int getNextFirstBallPinsCount() {

        return nextBall == null ? 0 : nextBall.getKnockedPins();
    }

    protected int getNextSecondBallPinsCount() {

        return nextBall == null ? 0 : nextBall.getNextBall() == null ? 0 : nextBall.getNextBall().getKnockedPins();
    }

    protected void setNextBall(BoardFrameBall nextBall) {

        this.nextBall = nextBall;
    }

    @Override
    public String toString() {

        return String.format("%s", knockedPins);
    }

    //<editor-fold desc="Internal helper methods">

    private BoardFrameBall getNextBall() {

        return nextBall;
    }

    //</editor-fold>
}