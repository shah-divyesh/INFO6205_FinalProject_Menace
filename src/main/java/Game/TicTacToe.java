package Game;

import DataStructure.FileUtility;
import Layouts.*;
import Logs.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {


        HashMap<String, String> uniqueLayouts = getUniqueLayouts();
        HashMap<String, int[]> mainLayouts = readFromFile();
        MatrixManipulation m = new MatrixManipulation();
        Evaluate e = new Evaluate();
        int moveCount = 1;
        HashMap<String, Integer> menaceMoves = new HashMap<>();
        String currentLayout = ".........";
        while (moveCount <= 5) {
            try {
                if (moveCount <= 4) {
                    currentLayout = meanceTurn(currentLayout, uniqueLayouts, mainLayouts, m, menaceMoves);
                    displayGame(currentLayout);

                    if (moveCount >= 3 && checkForWinner(m, currentLayout, menaceMoves, mainLayouts)) {
                        break;
                    }
                    currentLayout = humanTurn(currentLayout, m);
                    displayGame(currentLayout);
                    if (moveCount >= 3 && checkForWinner(m, currentLayout, menaceMoves, mainLayouts)) {
                        break;
                    }

                } else {
                    StringBuilder sb = new StringBuilder(currentLayout);
                    for (int i = 0; i < sb.length(); i++) {
                        char c = sb.charAt(i);
                        if (c == '.') {
                            sb.setCharAt(i, 'O');
                        }
                    }

                    if (!checkForWinner(m, sb.toString(), menaceMoves, mainLayouts)) {
                        Log.gameLog("Match Draw");
                        updateMenace(menaceMoves, mainLayouts, '.');
                    }

                }

            } catch (Exception exception) {
                System.out.println(exception);
            } finally {
                moveCount++;
            }
        }


    }

    public static boolean checkForWinner(MatrixManipulation m, String currentLayout, HashMap<String, Integer> menaceMoves, HashMap<String, int[]> mainLayouts) {
        char winner = Evaluate.getFinalWinner(m.getMatrixLayout(currentLayout));
        boolean winnerFound = false;
        if (winner == 'O') {
            winnerFound = true;
            Log.gameLog("Menace Won the Game");

            updateMenace(menaceMoves, mainLayouts, 'O');
        }
        if (winner == 'X') {
            winnerFound = true;
            Log.gameLog("Human Won the Game");

            updateMenace(menaceMoves, mainLayouts, 'X');
        }
        return winnerFound;
    }


    public static String meanceTurn(String currentLayout, HashMap<String, String> uniqueLayouts, HashMap<String, int[]> mainLayouts, MatrixManipulation m, HashMap<String, Integer> menaceMoves) {
        String layoutDetails = uniqueLayouts.get(currentLayout);
        String[] layoutDetailsArray = layoutDetails.split("#");
        String layoutAdjusment = layoutDetailsArray[1];
        String layoutAngle = layoutDetailsArray[2];
        StringBuilder similarLayout = new StringBuilder(layoutDetailsArray[0]);
        int[] beadCounts = mainLayouts.get(similarLayout.toString());
        RandomPick pick = new RandomPick(beadCounts);
        int index = pick.pickIndex();
        menaceMoves.put(similarLayout.toString(), index);
        similarLayout.setCharAt(index, 'O');
        String displayLayout = " ";

        if (layoutAdjusment.equals("SYMMETRIC")) {
            displayLayout = m.getSymmetricMatrix(similarLayout.toString(), layoutAngle);
        }
        if (layoutAdjusment.equals("REFLECT")) {
            displayLayout = m.getReflectiveMatrix(similarLayout.toString(), layoutAngle);
        }
        return displayLayout;
    }

    public static String humanTurn(String currentLayout, MatrixManipulation m) {
        System.out.println("Input Row and Column Values in 2 Different Lines");
        System.out.println();
        Scanner in = new Scanner(System.in);
        //String userMove=
        int row = Integer.valueOf(in.nextLine());
        int column = Integer.valueOf(in.nextLine());
        char[][] matrix = m.getMatrixLayout(currentLayout);
        matrix[row][column] = 'X';
        currentLayout = m.getStringLayout(matrix);
        return currentLayout;

    }

    public static void displayGame(String layout) {
        MatrixManipulation m = new MatrixManipulation();

        char[][] matrixlayout = m.getMatrixLayout(layout);

        final int COLUMNS = 4;
        final int ROWS = 4;
        for (int row = 0; row < matrixlayout.length; row++) {
            System.out.print("  " + row);
        }
        System.out.println();
        for (int row = 0; row < matrixlayout.length; row++) {
            for (int col = 0; col < matrixlayout.length; col++) {
                if (col < 1) {
                    System.out.print(row);
                    System.out.print("  " + matrixlayout[row][col]);
                } else {

                    System.out.print("  " + matrixlayout[row][col]);
                }
            }
            System.out.println();
        }
    }

    public static HashMap<String, String> getUniqueLayouts() {
        GenerateLayouts g = new GenerateLayouts();
        HashMap<String, String> uniqueLayouts = new HashMap<>();
        String[] layouts = {".........", "OX.......", "OXOX.....", "OXOXOX..."};
        for (String s : layouts) {
            HashMap<String, String> uniqueLayout = g.getMenaceLayouts(s);
            for (Map.Entry<String, String> entry : uniqueLayout.entrySet()) {
                uniqueLayouts.put(entry.getKey(), entry.getValue());
            }
        }

        return uniqueLayouts;
    }


    /*
    Reading the Data of the previous games played from the file in order to train the menace
     */
    public static HashMap<String, int[]> readFromFile() {
        HashMap<String, int[]> gamePlay = new HashMap<>();
        try {
            gamePlay = FileUtility.readFromFile("DataBackup.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gamePlay;
    }

    /*
    Writing the Data of the games played in the file in order to train the menace
     */
    public static void writeToFile(HashMap<String, int[]> map) {
        try {
            FileUtility.writeToFile("DataBackup.txt", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    Updating the beads value and then writing it back to file in order to train the menace as per the Game result
     */
    public static void updateMenace(HashMap<String, Integer> menaceMoves, HashMap<String, int[]> mainLayouts, char winner) {
        for (String s : menaceMoves.keySet()) {
            if (mainLayouts.containsKey(s)) {
                int index = menaceMoves.get(s);
                int[] updatedArr = mainLayouts.get(s);
                if (winner == 'O') {
                    updatedArr[index] += 3;
                }
                if (winner == 'X') {
                    updatedArr[index]--;
                }
                mainLayouts.put(s, updatedArr);
                writeToFile(mainLayouts);
            }
        }
    }

}
