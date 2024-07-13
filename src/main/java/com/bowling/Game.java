package com.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String laneName;
    private List<Player> players = new ArrayList<>();
    public int currentPlayer;

    public Game(String laneName) {

        this.laneName = laneName;
        System.out.printf("[ Bowling game started at lane: \"%s\" ]\n", laneName);
    }

    public int getScore() {

        return getCurrentPlayer().getScoreBoard().getScore();
    }

    public void addPlayer(Player player) {

        player.setCurrentGame(this);
        players.add(player);
    }

    public Player getCurrentPlayer() {

        return players.get(currentPlayer % players.size());
    }

    public void start() {

        for(Player player : players)
            player.start();

        for(Player player : players) {
            try {
                player.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        print();
    }

    public void print() {

        StringBuilder stringBuilder = new StringBuilder("[ Bowling game at lane: \"" + laneName + "\" ]\n");

        for (Player player : players)
            stringBuilder.append(String.format("[ Player \"%s\" | Final score: %3d | %s ]\n",
                    player.getPlayerName(),
                    player.getScoreBoard().getScore(),
                    player.getScoreBoard().toString()) );

        System.out.println(stringBuilder);
    }
}
