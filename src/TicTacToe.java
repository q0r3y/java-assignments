import java.util.Scanner;

public class TicTacToe {

    // TODO
    // Input validation
    // Correct timing of checkWin

    private Cells[][] board;
    private boolean gameOver;

    private enum Cells {
        X, O, EMPTY;
    }

    public TicTacToe() {
        board = new Cells[][] {
                // board[0][0] , board[0][1] , board[0][2]
                // board[1][0] , board[1][1] , board[1][2]
                // board[2][0] , board[2][1] , board[2][2]

                {Cells.EMPTY, Cells.EMPTY, Cells.EMPTY},
                {Cells.EMPTY, Cells.EMPTY, Cells.EMPTY},
                {Cells.EMPTY, Cells.EMPTY, Cells.EMPTY},
            };
        gameOver = false;
        System.out.println("Player1 = X");
        System.out.println("Player2 = O\n");
        Cells player1 = Cells.X;
        Cells player2 = Cells.O;
        play(player1, player2);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    public void play(Cells player1, Cells player2) {
        while (!(gameOver)) {
            playerMove(player1);
            playerMove(player2);
        }
    }

    public void playerMove(Cells player) {

        boolean playerMoved = false;
        drawBoard();

        while(!(playerMoved)) {
            boolean isNumber;

            System.out.println(player+" pick a row: ");
            Scanner rowIn = new Scanner(System.in);
            int row = rowIn.nextInt();

            System.out.println(player+" pick a column: ");
            Scanner columnIn = new Scanner(System.in);
            int column = columnIn.nextInt();

            if (board[row][column].equals(Cells.EMPTY)) {
                board[row][column] = player;
                playerMoved = true;
            } else {
                System.out.println("   (!) A player is already in that position try again.\n");
            }
        }
        checkWin(player);
    }

    public boolean allMatch(Cells cell1, Cells cell2, Cells cell3) {
        return ((cell1 != Cells.EMPTY) && (cell1 == cell2) && (cell2 == cell3));
    }

    public boolean isBoardFull() {
        for (Cells[] cell : board) {
            for (Cells status : cell) {
                if (!(status.equals(Cells.EMPTY))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkRow() {
        for (int i = 0; i < 3; i++) {
            if(allMatch(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkColumn() {
        for(int i = 0; i < 3; i++) {
            if(allMatch(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonal () {
        return ((allMatch(board[0][0], board[1][1], board[2][2])) || (allMatch(board[0][2], board[1][1], board[2][0])));
    }

    public void checkWin(Cells player) {
        if(checkRow() || checkColumn() || checkDiagonal() || isBoardFull()){
            gameOver = true;
            System.out.println("\n~~~~ Congratulations! "+player+" WINS! ~~~~\n");
        }
    }


    public void drawBoard () {
        System.out.println("Column 0  |  Column 1  |  Column 2");
        System.out.println("===============================\n");
        System.out.println("Row 0|  "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
        System.out.println("     -----------------------");
        System.out.println("Row 1|  "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
        System.out.println("     -----------------------");
        System.out.println("Row 2|  "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
        System.out.println("\n===============================");

    }

}
