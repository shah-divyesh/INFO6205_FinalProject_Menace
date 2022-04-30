package Layouts;

import java.util.ArrayList;
import java.util.List;

public class Evaluate {


    public static void main(String[] args) {
        char[][] matrix = {{'O', '.', 'X'}, {'.', 'X', '.'}, {'X', 'X', 'O'}};
        String list = Evaluate.getWinner( matrix);

        System.out.println(list);
    }
    public Evaluate()
    {

    }

    public static String getWinner(char[][] matrix) {
        int rowContainer = 0;
        int columnContainer = 0;
        int diagonalContainer = 0;
        int oppContainer = 0;
        char[] arr = {'O', 'X'};
        String result="";
        MatrixManipulation m = new MatrixManipulation();

        List<String> list = new ArrayList<>();

        for (int K = 0; K < 2; K++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] == arr[K]) {
                        columnContainer++;
                    }
                    if (matrix[j][i] == arr[K]) {
                        rowContainer++;
                    }
                    if(i==j && matrix[i][j]==arr[K])
                    {
                        diagonalContainer++;
                    }
                    if(i+j+1==3 && matrix[i][j]==arr[K])
                    {
                        oppContainer++;
                    }
                }
                if (columnContainer == 3) {
                    result=m.getStringLayout(matrix);

                }
                if (rowContainer == 3) {
                    result=m.getStringLayout(matrix);
                }
                columnContainer = 0;
                rowContainer = 0;
            }

            if(diagonalContainer==3)
            {
                result=m.getStringLayout(matrix);
            }
            diagonalContainer=0;
            if(oppContainer==3)
            {
                result=m.getStringLayout(matrix);
            }
           oppContainer=0;
        }
        return result;
    }

    public static char getFinalWinner(char[][] matrix) {
        int rowContainer = 0;
        int columnContainer = 0;
        int diagonalContainer = 0;
        int oppContainer = 0;
        char[] arr = {'O', 'X'};
        char result=' ';
        MatrixManipulation m = new MatrixManipulation();

        List<String> list = new ArrayList<>();

        for (int K = 0; K < 2; K++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j] == arr[K]) {
                        columnContainer++;
                    }
                    if (matrix[j][i] == arr[K]) {
                        rowContainer++;
                    }
                    if(i==j && matrix[i][j]==arr[K])
                    {
                        diagonalContainer++;
                    }
                    if(i+j+1==3 && matrix[i][j]==arr[K])
                    {
                        oppContainer++;
                    }
                }
                if (columnContainer == 3) {
                    result=arr[K];

                }
                if (rowContainer == 3) {
                    result=arr[K];
                }
                columnContainer = 0;
                rowContainer = 0;
            }

            if(diagonalContainer==3)
            {
                result=arr[K];
            }
            diagonalContainer=0;
            if(oppContainer==3)
            {
                result=arr[K];
            }
            oppContainer=0;
        }
     return result;
    }
}
