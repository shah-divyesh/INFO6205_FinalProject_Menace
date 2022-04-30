package Layouts;

public class MatrixManipulation {


    public static void main(String[] args)
    {
        char[][] matrix={{'X','.','.'},{'.','.','O'},{'.','.','.'}};

      //.....XO..  .X....O..

        MatrixManipulation m=new MatrixManipulation();
        char[][] matrix2=m.matrixVerticalReflect(matrix);

        System.out.println(m.getStringLayout(matrix));
        System.out.println(m.getStringLayout(matrix2));
    }
    public void matrixTranspose(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < m; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    //reflection about the vertical axis, the horizontal axis, and
    //the two diagonal axes all give different board positions Nonetheless these eight positions are
    //essentially the same as far as the rules of the game of tic-tac-toe are concerned.
    public void matrixReflect(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        char[][] newMatrix = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }

    }

    public char[][] matrixVerticalReflect(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        char[][] newMatrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                newMatrix[i][j] = matrix[i][n - j - 1];
            }
        }
        return newMatrix;
    }

    public char[][] matrixHorizontalReflect(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        char[][] newMatrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[i][j] = matrix[n - i - 1][j];
            }
        }
        return newMatrix;
    }

    public char[][] matrixStraightDiagonalReflect(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        char[][] newMatrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        return newMatrix;
    }

    public char[][] matrixOppositeDiagonalReflect(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        char[][] newMatrix = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m ; j++) {

                newMatrix[n-i-1][m-j-1] = matrix[j][i];
            }
        }
        return newMatrix;
    }

    public  char[][] getMatrixLayout(String str) {
        char[][] layout = new char[3][3];

        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                layout[i][j] = str.charAt(count++);
            }
        }
        return layout;
    }
    public  String getStringLayout(char[][] layout) {
        StringBuilder sb = new StringBuilder();

        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(layout[i][j]);
            }
        }
        return sb.toString();
    }

    public String getSymmetricMatrix(String matrix,String adjustment)
    {
        int angle=Integer.parseInt(adjustment);
        if(angle==0)
        {
            return matrix;
        }
        char[][] resultMatrix=getMatrixLayout(matrix);
        for(int i=0;i<angle;i++)
        {
            matrixTranspose(resultMatrix);
            matrixReflect(resultMatrix);
        }

        return getStringLayout(resultMatrix);

    }

    public String getReflectiveMatrix(String matrix,String adjustment)
    {
        if(adjustment.equals("VERTICAL"))
        {
            return getStringLayout(matrixVerticalReflect(getMatrixLayout(matrix)));
        }
        else if(adjustment.equals("HORIZONTAL"))
        {
            return getStringLayout(matrixHorizontalReflect(getMatrixLayout(matrix)));
        }
        else if(adjustment.equals("STRAIGHT"))
        {
            return getStringLayout(matrixStraightDiagonalReflect(getMatrixLayout(matrix)));
        }
        else if(adjustment.equals("OPPOSITE"))
        {
            return getStringLayout(matrixOppositeDiagonalReflect(getMatrixLayout(matrix)));
        }
        return "";
    }
}
