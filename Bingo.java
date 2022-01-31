// >>>>>>>> Begin imports >>>>>>>>

import java.util.Random;

// <<<<<<<<< End imports <<<<<<<<<

public class Bingo {
    // >>>>>>>> Begin variables >>>>>>>>

    private int[][] board;
    private boolean[][] booleans;

    // <<<<<<<<< End variables <<<<<<<<<

    // >>>>>>>> Begin Creators >>>>>>>>

    public Bingo(int[][] board) {
        this.board = board;
        this.booleans = new boolean[5][5];
        this.booleans [2][2] = true;
    }

    public Bingo(){
        this.board = RandomBoard();
        this.booleans = new boolean[5][5];
        this.booleans [2][2] = true;
    }

    // <<<<<<<<< End Creators <<<<<<<<<

    private static int[][] RandomBoard() {
        int[][] board = new int[5][5];
        Random rand = new Random();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++){
                do {
                    board[i][j] = rand.nextInt(20) + 20*j;
                } while (board[i][j] == 0);
            }
        board[2][2] = 0;
        return board;
    }

    public boolean NextInt(int nb) {
        int j = nb/20;
        for(int i = 0; i < 5; i++){
            if(this.board[i][j] == nb){
                this.booleans[i][j] = true;
                return this.Checkwins(i, j);
            }
        }
        return false;
    }

    public boolean Checkwins() {
        for(int i = 0; i < 5; i++){
            boolean winline = true;
            boolean wincol = true;
            for(int j = 0; j<5 && (winline || wincol); j++){
                wincol &= this.booleans[i][j];
                winline &= this.booleans[j][i];
            }
            if (wincol || winline)
                return true;
        }
        return false;
    }

    public boolean Checkwins(int x, int y) {
        boolean winline = true;
        boolean wincol = true;
        for(int i = 0; i < 5 && (winline || wincol); i++){
            wincol &= this.booleans[x][i];
            winline &= this.booleans[i][y];
        }
        return wincol || winline;
    }

    public String toString(){
        String mystring = "";
        for(int i = 0; i < 5; i++){
            mystring += "|";
            for(int j = 0; j < 5; j++)
                mystring += /*("(" + i + "," + j + ") ") +*/ (this.board[i][j] < 10 ? "  " : " ") + this.board[i][j] + " |";
            mystring += "\t\t|";
            for(int j = 0; j < 5; j++)
                mystring += (this.booleans[i][j] ? " x" : "  ") + " |";
            mystring += "\n";
        }
        return mystring;
    }



}