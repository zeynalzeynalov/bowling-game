package com.utilities;

import com.scoring.BoardFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class InputManager {

    public static List<int[]> readFromFile(String fileName) {

        try(Scanner scanner = new Scanner(InputManager.class.getClass().getResourceAsStream("/"+fileName))) {

            int countOfInputs = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            return getScoreCollection(scanner, countOfInputs);
        }
        catch (Exception e) {

            System.out.println(String.format("ERROR: File %s not found!", fileName));
        }

        return new ArrayList<int[]>();
    }

    public static List<int[]> readFromConsole() {

        try(Scanner scanner = new Scanner(System.in)) {

            System.out.print("Please enter total input line count: ");
            int countOfInputs = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.out.println(String.format("Please enter %d lines of ball results below:", countOfInputs));

            return normalizePinsLists(getScoreCollection(scanner, countOfInputs));
        }
        catch (Exception e) {

            System.out.println(e.toString());
        }

        return new ArrayList<int[]>();
    }

    //<editor-fold desc="Internal helper methods">

    private static List<int[]> normalizePinsLists(List<int[]> scoreCollection) {

        for(int[] scores : scoreCollection) {
            for(int i = 0; i < scores.length; i++) {
                if( scores[i] > BoardFrame.maxPinsCount)
                    scores[i] = BoardFrame.maxPinsCount;
            }
        }

        return scoreCollection;
    }

    private static List<int[]> getScoreCollection(Scanner scanner, int countOfLines) {

        List<int[]> scoreCollection = new ArrayList<>();

        for (int i = 0; i < countOfLines; i++) {
            String[] pinsString = cleanExtraSpaces(scanner.nextLine()).split(" ");

            ArrayList<Integer> pinsInt = new ArrayList<>();
            for(String pin : pinsString)
            try {
                pinsInt.add( Integer.parseInt(pin) );
            }
            catch (NumberFormatException e)
            { }

            scoreCollection.add(pinsInt.stream().mapToInt(Integer::intValue).toArray());
        }

        scanner.close();
        return scoreCollection;
    }

    private static String cleanExtraSpaces(String line) {

        return line.replace("\t", " ");
    }

    //</editor-fold>
}
