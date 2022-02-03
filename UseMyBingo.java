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

        System.out.println("Choose the Bingo game you want to play");
        System.out.println("game : Regular game in which you enter the number as they come");
        System.out.println("stats : Creates n random boards and returns the first one to succeed with random generated numbers");
        //System.out.println("");
        //System.out.println("");
        String[] games = {"game", "stats"};
        int opt = GetStrInput(games, "Please enter one of the previous given answers");

        switch (games[0]) {
            case "game":
                UsualGame();
                break;
            case "stats" :
                BingoStats(15);


            default:
                break;
        }

    }

    public static int GetStrInput(String[] options, String sentence) {
        String answer = "";
        Scanner inp = new Scanner(System.in);

        do{
            answer = inp.nextLine();
            for(int i = 0; i < options.length; i++){
                if(answer.equals(options[i]))
                    return i;
            }
            System.out.println(sentence + " and not " + answer);
            PrintArray(options);
        }while (true);
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

    public static String boardToString(int[][] board){
        String mystring = "";
        for(int i = 0; i < 5; i++){
            mystring += "|";
            for(int j = 0; j < 5; j++)
                mystring += (board[i][j] < 10 ? "  " : " ") + board[i][j] + " |";
            mystring += "\n";
        }
        return mystring;
    }

    public static int PromptNb(int mini, int maxi) {
        System.out.printf("Please enter a number between %d and %d :\n", mini, maxi);
        Scanner inp = new Scanner(System.in);

        int nb = inp.nextInt();
        while (mini > nb || nb > maxi){
            System.out.printf("Please enter a number between %d and %d :\n", mini, maxi);
            nb = inp.nextInt();
        }
        return nb;
    }

    public static int[][] Fillboard() {
        int[][] limits = {{1, 19}, {20, 39}, {40, 59}, {60, 79}, {80, 99}};
        int[][] board = new int[5][5];
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++){
                System.out.println(boardToString(board));
                board[j][i] = PromptNb(limits[i][0], limits[i][1]);
            }

            return board;
    }

    public static void UsualGame() {
        Scanner inp = new Scanner(System.in);

        System.out.println("Choose the Bingo board you want to play with");
        System.out.println("rnd : Generates a random board");
        System.out.println("in : Uses the precreated board");
        System.out.println("new : Allows you to give your own new board");

        String[] boardTypes = {"rnd", "in", "new"};
        int type = GetStrInput(boardTypes, "Please enter one of the previous given answers");
        int[][] board = {
            { 7, 28, 42, 60, 82},
            { 3, 31, 48, 64, 88},
            { 9, 35,  0, 69, 84},
            {13, 21, 47, 77, 99},
            { 8, 32, 55, 67, 96},
        };
        Bingo bingo;
        switch (boardTypes[type]) {
            case "rnd" :
                bingo = new Bingo();
                break;
            case "in" :
                bingo = new Bingo(board);
                break;
            case "new":
                bingo = new Bingo(Fillboard());
                break;
            default:
                bingo = new Bingo();
                break;
        }

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