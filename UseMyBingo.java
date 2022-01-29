// >>>>>>>> Begin imports >>>>>>>>

import java.util.Scanner;
import java.util.Random;

// <<<<<<<<< End imports <<<<<<<<<

public class UseMyBingo {
    public static void main(String[] args) {
        int[][] board = {
            { 7, 28, 42, 60, 82},
            { 3, 31, 48, 64, 88},
            { 9, 35,  0, 69, 84},
            {13, 21, 47, 77, 99},
            { 8, 32, 55, 67, 96},
        };
        System.out.println("Welcome to Bingo ! Have fun playing");
        /*Bingo bingo = new Bingo(board);
        PlayBingo(bingo);*/
        BingoStats(1000000);
    }

    public static void PlayBingo(Bingo bingo) {
        Scanner inp = new Scanner(System.in);
        boolean success;
        System.out.println(bingo);
        do {
            System.out.println("Enter the next Bingo number :");
            success = bingo.NextInt(inp.nextInt());
            System.out.println(bingo);

        } while (!success);
        System.out.println("Well done you successfully won at bingo !!");
    }

    public static void BingoStats(int nbBoards) {
        Random rand = new Random();

        Bingo[] bingos = new Bingo[nbBoards];
        for(int i = 0; i<nbBoards; i++)
            bingos[i] = new Bingo();

        int nbTurns = 0;
        while (true){
            nbTurns += 1;
            int nb = rand.nextInt(99);
            for (int i = 0; i < nbBoards; i++){
                if (bingos[i].NextInt(nb)) {
                    System.out.println(bingos[i]);
                    System.out.println("The board nb " + i + " successfully won after " + nbTurns + " turns.");
                    return;
                }
            }
        }

    }



}