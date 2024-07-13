package com.bowling;

import org.junit.*;

public class BowlingGameTest {

    Alley alley = new Alley("Test");

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void startingScoreIsZero() {

        Game game = alley.createNewGame("No Score");
        game.addPlayer(new Player("Test"));
        game.print();

        Assert.assertEquals(0, game.getScore());
    }

    @Test
    public void allBallsMissed() {

        Game game = alley.createNewGame("All Balls Missed");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 20, 0);
        game.print();

        Assert.assertEquals(0, game.getScore());
    }

    @Test
    public void allBallsMissedLastTwoTenPins() {

        Game game = alley.createNewGame("All Balls Missed Last Two Ten Pins");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 19, 0);
        game.getCurrentPlayer().throwBall(10);
        game.getCurrentPlayer().throwBall(10);
        game.print();

        Assert.assertEquals(20, game.getScore());
    }

    @Test
    public void allBallsKnockedOnePin() {

        Game game = alley.createNewGame("All Balls Knocked One Pin");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 20, 1);
        game.print();

        Assert.assertEquals(20, game.getScore());
    }

    @Test
    public void allBallsKnockedTwoPins() {

        Game game = alley.createNewGame("All Balls Knocked Two Pins");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 20, 2);
        game.print();

        Assert.assertEquals(40, game.getScore());
    }

    @Test
    public void allBallsKnockedStrike() {

        Game game = alley.createNewGame("All Balls Knocked Strike");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 12, 10);
        game.print();

        Assert.assertEquals(300, game.getScore());
    }

    @Test
    public void allBallsKnockedSpareAndLastStrike() {

        Game game = alley.createNewGame("All Balls Knocked Spare And Last Strike");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 20, 5);
        game.getCurrentPlayer().throwBall(10);
        game.print();

        Assert.assertEquals(155, game.getScore());
    }

    @Test
    public void allBallsKnockedSpareAndLastMiss() {

        Game game = alley.createNewGame("All Balls Knocked Spare And Last Miss");
        game.addPlayer(new Player("Test"));
        ballThrowRepeat(game, 20, 5);
        game.getCurrentPlayer().throwBall(0);
        game.print();

        Assert.assertEquals(145, game.getScore());
    }

    @Test
    public void scoreWillBeOneHundredThirtyThree() {

        Game game = alley.createNewGame("Score Will Be One Hundred Thirty Three");
        game.addPlayer(new Player("Test"));
        game.getCurrentPlayer().throwBall(new int[] {1,4, 4,5, 6,4, 5,5, 10, 0,1, 7,3, 6,4, 10, 2, 8, 6});
        game.print();

        Assert.assertEquals(133, game.getScore());
    }

    @Test
    public void allFirstBallsKnockedOneSecondMissed() {

        Game game = alley.createNewGame("All First Balls Knocked One Second Missed");
        game.addPlayer(new Player("Test"));
        ballThrowRepeatWithAlternating(game, 20, 1, 0);
        game.print();

        Assert.assertEquals(10, game.getScore());
    }

    @After
    public void tearDown() throws Exception {

        alley = null;
    }

    //<editor-fold desc="Helper methods">

    private void ballThrowRepeat(Game game, int ballCount, int knockedPinsCount) {

        for(int i = 0; i < ballCount; i++)
            game.getCurrentPlayer().throwBall(knockedPinsCount);
    }

    private void ballThrowRepeatWithAlternating(Game game, int ballCount, int first, int second) {

        for(int i = 0; i < 20; i++)
            game.getCurrentPlayer().throwBall(i % 2 == 0 ? first : second);
    }

    //</editor-fold>
}