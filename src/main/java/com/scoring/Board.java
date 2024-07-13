package com.scoring;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {

    private BoardFrame[] frames;
    private int currentFrameIndex;
    private BoardFrameBall lastBallReference;
    public final static int frameCount = 10;

    public Board() {

        frames = new BoardFrame[frameCount];

        for(int i=0; i < frameCount - 1; i++)
            frames[i] = new BoardFrame(i);

        frames[frameCount - 1] = new BoardFrameLast(frameCount - 1);
    }

    public boolean saveBallResult(int knockedPinsCount) throws InvalidPinCountException {

        currentFrame().saveBallResult(knockedPinsCount);
        bindBalls();

        boolean isThisFrameComplete = currentFrame().isCompleted();

        if( !isLastFrame() && currentFrame().isCompleted() )
            moveToNextFrame();

        return isThisFrameComplete;
    }

    public int getScore() {

        return Arrays.stream(frames).map(BoardFrame::getScore).mapToInt(Integer::intValue).sum();
    }

    public boolean isBoardClosed() {

        for (BoardFrame frame : frames)
            if( !frame.isCompleted() )
                return false;

        return true;
    }

    @Override
    public String toString() {

        return String.format("Board: [%s ]", Arrays.asList(frames).stream().map(BoardFrame::toString).collect(Collectors.joining(" | ")));
    }

    //<editor-fold desc="Internal helper methods">

    private BoardFrame currentFrame() {

        return frames[currentFrameIndex];
    }

    private boolean isLastFrame() {

        return currentFrameIndex == frames.length - 1;
    }

    private int moveToNextFrame() {

        return currentFrameIndex++;
    }

    private void bindBalls() {

        if( lastBallReference != null )
            lastBallReference.setNextBall(currentFrame().getLastBall());

        lastBallReference = currentFrame().getLastBall();
    }

    //</editor-fold>
}
