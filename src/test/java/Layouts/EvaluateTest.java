package Layouts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateTest {


    @Test
    void getFinalWinner() {

        char[][] input={{'O', 'O', 'O'}, {'.', 'X', '.'}, {'X', 'X', '.'}};
        assertEquals('O',Evaluate.getFinalWinner(input));
    }
}