package Layouts;

import java.io.*;

import Game.*;

import java.util.Arrays;

public class Test {


    public static void main(String[] args) {

        int[] arr = new int[9];
        Arrays.fill(arr, 4);
        RandomPick pick = new RandomPick(arr);
        int index = pick.pickIndex();

        System.out.println(index);

    }
}
