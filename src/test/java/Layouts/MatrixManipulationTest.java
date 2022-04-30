package Layouts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixManipulationTest {
    MatrixManipulation m=new MatrixManipulation();
    @Test
    void matrixVerticalReflect() {
        char[][] input={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        char[][] result={{'O','.','.'},{'.','.','.'},{'.','.','.'}};
        assertArrayEquals(result, m.matrixVerticalReflect(input));
    }

    @Test
    void matrixHorizontalReflect() {

        char[][] input={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        char[][] result={{'.','.','.'},{'.','.','.'},{'.','.','O'}};
        assertArrayEquals(result, m.matrixHorizontalReflect(input));
    }

    @Test
    void matrixStraightDiagonalReflect() {

        char[][] input={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        char[][] result={{'.','.','.'},{'.','.','.'},{'O','.','.'}};
        assertArrayEquals(result, m.matrixStraightDiagonalReflect(input));
    }

    @Test
    void matrixOppositeDiagonalReflect() {

        char[][] input={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        char[][] result={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        assertArrayEquals(result, m.matrixOppositeDiagonalReflect(input));
    }

    @Test
    void getMatrixLayout() {
        String input="..O......";
        char[][] result={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        assertArrayEquals(result, m.getMatrixLayout(input));
    }

    @Test
    void getStringLayout() {

        String result="..O......";
        char[][] input={{'.','.','O'},{'.','.','.'},{'.','.','.'}};
        assertEquals(result, m.getStringLayout(input));
    }

    @Test
    void getSymmetricMatrix() {

        String result="..O......";
        String angle="0";
        assertEquals(result, m.getSymmetricMatrix(result,angle));
    }

    @Test
    void getReflectiveMatrix() {

        String input="..O......";
        String output="O........";
        String adjustment="VERTICAL";
        assertEquals(output, m.getReflectiveMatrix(input,adjustment));
    }
}