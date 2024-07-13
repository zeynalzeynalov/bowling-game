package com.simulation;

import com.bowling.Alley;
import com.bowling.Game;
import com.bowling.Player;

public class BowlingGameSimulation {

    private int playerCount = 2;

    public void start() {

        Alley alley = new Alley("Simulation");
        Game game = alley.createNewGame("Simulation");

        for(int i = 0; i < playerCount; i++)
            game.addPlayer(new Player(String.valueOf(i)));

        game.start();
    }

    public void setPlayersCount(int playerCount) {

        this.playerCount = playerCount;
    }
}
