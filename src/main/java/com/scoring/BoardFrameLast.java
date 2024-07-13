package com.scoring;

import java.util.stream.Collectors;

public class BoardFrameLast extends BoardFrame {

    public final static int maxPinsCount = 30;

    public BoardFrameLast(int frameIndex) {

        super(frameIndex);
    }

    protected boolean isExceedingMaxPinCount(int knockedPinsCount) {

        int totalPins = totalKnockedPins() + knockedPinsCount;

        if( totalPins > 3 * maxPinsCount )
            return true;

        return false;
    }

    protected boolean isCompleted() {

        if((isStrike() || isSpare()))
            if(balls.size() == 3)
                return true;
            else
                return false;
        else
            if(balls.size() > 1)
                return true;
            else
                return false;
    }

    @Override
    public String toString() {

        return String.format("%8s = %2s", balls.stream().map(BoardFrameBall::toString).collect(Collectors.joining(",")), getScore());
    }
}
