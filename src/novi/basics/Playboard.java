package novi.basics;

public class Playboard {

    private Field [][] board;


    public Playboard()
    {
        this.board = new Field [5][5];

    }

    public void setUpPlayboard(){

        for (int x = 1; x < board.length; x = x + 2) {
            for (int y = 0; y < board.length; y = y + 2) {
                board[y][x] = new Field("|");
            }

        }

        for (int x = 0; x < board.length; x = x + 2) {
            for (int y = 1; y < board.length; y = y + 2) {
                board[y][x] = new Field("--");
            }

        }

        for (int y = 1; y < board.length; y = y + 2) {
            for (int x = 1; x < board.length; x = x + 2) {
                board[y][x] = new Field("+");
            }
        }

        for (int y = 0; y < board.length; y = y + 2) {//to fill the board with numbers,
            for (int x = 0; x < board.length; x = x + 2) {
                    board[x][y] = new Field (String.valueOf(x) + String.valueOf(y));
                }
            }
        }

    public void printPlayboard() {
        System.out.println("");


        //Print the 2d Array

        for (Field[] eachRow : board){
            for (Field j : eachRow) {
                System.out.print(j.getToken() + "\t");
            }
            System.out.println("");
        }
    }

    public boolean adaptPlayboard(int inputPlayersY, int inputPlayersX, Player currentPlayer){

        String currentPlayerToken = currentPlayer.getToken();
        boolean tokenIsPlaced = board[inputPlayersY][inputPlayersX].setToken(currentPlayerToken);

        if (tokenIsPlaced) {
            return true;
        }
        else{
            System.out.println("Dit veld is al bezet. Probeer nog maar een keer.");
            return false;
        }

    }

    public Field [][] getBoard(){
        return board;
    }
}
