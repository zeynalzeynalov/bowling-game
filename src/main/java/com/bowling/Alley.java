package com.bowling;

import java.util.ArrayList;
import java.util.List;

public class Alley {

    private String name;
    private List<Game> games;

    public Alley(String name) {

        this.name = name;
        this.games = new ArrayList<>();

        System.out.println(String.format("[ Welcome to bowling alley: \"%s\" ]", name));
    }

    public Game createNewGame(String gameName) {

        Game newGame = new Game(gameName);
        games.add(newGame);
        return newGame;
    }
}
