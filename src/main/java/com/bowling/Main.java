package com.bowling;

import com.simulation.BowlingGameSimulation;
import com.utilities.InputManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        if( isCurrentModeSimulation(args) ) {
            BowlingGameSimulation simulation = new BowlingGameSimulation();
            simulation.setPlayersCount(5);
            simulation.start();
        }
        else {
            Alley center = new Alley("Munich");
            Game game = center.createNewGame("Warming Up");

            int playerNumber = 1;
            for(int[] pins : getScoreCollection(args)) {
                Player player = new Player("Junior " + playerNumber++);
                game.addPlayer(player);
                player.throwBall(pins);
            }

            game.print();
        }
    }

    //<editor-fold desc="Internal helper methods">

    private static boolean isCurrentModeSimulation(String[] args) {

        return args.length == 1 && args[0].equals("simulation");
    }

    private static boolean isCurrentModeFileInput(String[] args) {

        return args.length >= 1 && args[0].equals("inputfile");
    }

    private static String getInputFileName(String[] args) {

        return args.length == 2 ? args[1] : "SampleInput1.txt";
    }

    private static List<int[]> getScoreCollection(String[] args) {

        String fileName = getInputFileName(args);
        return isCurrentModeFileInput(args) ? InputManager .readFromFile(fileName) : InputManager.readFromConsole();
    }

    //</editor-fold>
}
