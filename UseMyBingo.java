// >>>>>>>> Begin imports >>>>>>>>

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// <<<<<<<<< End imports <<<<<<<<<

public class UseMyBingo {
    public static void main(String[] args) {
        System.out.println("Welcome to Bingo ! Have fun playing");
        System.out.println("The program arguments are : ");
        PrintArray(args);
        boolean rnd = args.length >= 2 && args[1].equals("r");

        if (args.length > 0){
            switch (args[0]) {
                case "FFF":
                    FFF(rnd);
                    break;
                case "stats" :
                    BingoStats


                default:
                    break;
            }
        }


        /*long totaltime = 0;
        for(int i = 0; i < 10; i++){
            long start = System.currentTimeMillis();
            BingoStats(1000000);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            totaltime += timeElapsed;
        }*/

        //System.out.println(totaltime);

    }

    public static void PrintArray(String[] arr) {
        System.out.print("{");
        for(int i = 0; i < arr.length -1; i++){
            System.out.print(arr[i] + ", ");
        }
        if(arr.length > 0)
            System.out.print(arr[arr.length -1]);
        System.out.println("}\n");
    }

    public static void FFF(boolean rnd) {
        int[][] board = {
            { 7, 28, 42, 60, 82},
            { 3, 31, 48, 64, 88},
            { 9, 35,  0, 69, 84},
            {13, 21, 47, 77, 99},
            { 8, 32, 55, 67, 96},
        };
        Bingo bingo;
        if (rnd)
            bingo = new Bingo();
        else
            bingo = new Bingo(board);
        PlayBingo(bingo);

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
        List<Integer> numberPassed = new ArrayList<Integer>();
        Bingo[] bingos = new Bingo[nbBoards];
        for(int i = 0; i<nbBoards; i++)
            bingos[i] = new Bingo();

        int nbTurns = 0;
        while (true){
            nbTurns += 1;
            int nb = rand.nextInt(99);
            numberPassed.add(nb);
            for (int i = 0; i < nbBoards; i++){
                if (bingos[i].NextInt(nb)) {
                    System.out.println(bingos[i]);
                    System.out.println("The board nb " + i + " successfully won after " + nbTurns + " turns.");
                    System.out.println("After having passed the numbers : \n"+ numberPassed);
                    return;
                }
            }
        }

    }



}