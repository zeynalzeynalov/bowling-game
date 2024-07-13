package com.scoring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardFrame {

    private int frameIndex;
    protected List<BoardFrameBall> balls = new ArrayList<>();
    public final static int maxPinsCount = 10;

    public BoardFrame(int frameIndex) {

        this.frameIndex = frameIndex;
    }

    protected int getScore() {

        if( isStrike() )
            return maxPinsCount +
                    getFirstBall().getNextFirstBallPinsCount() +
                    getFirstBall().getNextSecondBallPinsCount();
        else
        if( isSpare() )
            return maxPinsCount +
                    getSecondBall().getNextFirstBallPinsCount();
        else
            return totalKnockedPins();
    }

    public boolean saveBallResult(int knockedPinsCount) throws InvalidPinCountException {

        if( isExceedingMaxPinCount(knockedPinsCount) )
            throw(new InvalidPinCountException("Invalid pin count - Total pins exeeds 10!"));

        return balls.add(new BoardFrameBall(knockedPinsCount));
    }

    public BoardFrameBall getLastBall() {

        return balls.size() == 0 ? null : balls.get(balls.size() - 1);
    }

    @Override
    public String toString() {

        return String.format("%4s = %2s", balls.stream().map(BoardFrameBall::toString).collect(Collectors.joining(",")), getScore());
    }

    //<editor-fold desc="Internal helper methods">

    protected boolean isExceedingMaxPinCount(int knockedPinsCount) {

        int totalPins = totalKnockedPins() + knockedPinsCount;

        if( totalPins > BoardFrame.maxPinsCount )
            return true;

        return false;
    }

    protected boolean isCompleted() {

        if ( isStrike() || balls.size() == 2 )
            return true;
        else
            return false;
    }

    protected boolean isStrike() {

        return balls.size() >= 1 && getFirstBall().getKnockedPins() == maxPinsCount;
    }

    protected boolean isSpare() {

        return balls.size() >= 2 && getFirstBall().getKnockedPins() + getSecondBall().getKnockedPins() == maxPinsCount;
    }

    protected int totalKnockedPins() {

        return balls.stream().map(BoardFrameBall::getKnockedPins).mapToInt(Integer::intValue).sum();
    }

    private BoardFrameBall getFirstBall() {

        return balls.get(0);
    }

    private BoardFrameBall getSecondBall() {

        return balls.get(1);
    }

    //</editor-fold>
}
