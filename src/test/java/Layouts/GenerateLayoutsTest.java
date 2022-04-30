package Layouts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateLayoutsTest {

    @Test
    void swap() {

        assertEquals("spa",GenerateLayouts.swap("psa",0,1));
    }
}