package novi.basics;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    //velden

    private Player playerA;
    private Player playerB;
    private Playboard playboard;
    private boolean debug;
    private int roundCounter;
    private int gameCounter;

    private int drawCounter;

    private Scanner userInput;
    private boolean rightAnswer;

    private HashMap<String,Integer> overallWinsPlayers;


    public Game(Player playerA, Player playerB, Playboard playboard, boolean debug,HashMap<String,Integer> overallWinsPlayers) {

        this.playerA = playerA;
        this.playerB = playerB;
        this.playboard = playboard;
        this.roundCounter = 0;
        this.gameCounter = 0;
        this.drawCounter = 0;
        this.debug = debug;
        this.userInput = new Scanner(System.in);
        this.overallWinsPlayers=overallWinsPlayers;

    }

    // aanmaken bord
    public void setUpGame() {

        playboard.setUpPlayboard();

        playboard.printPlayboard();

    }

    // wisselen van speler
    public Player switchPlayer(Player currentPlayer, Player playerA, Player playerB) {
        if (currentPlayer == playerA) {
            return playerB;
        } else {
            return playerA;
        }
    }

    // checken of de speler heeft gewonnen
    public boolean checkIfWon(Playboard playboard, Player currentPlayer) {
        String winnerSequence = currentPlayer.getToken() + currentPlayer.getToken() + currentPlayer.getToken();

        int boardLength = playboard.getBoard().length;

        //check rows

        for (int y = 0; y < boardLength; y = y + 2) {

            String checkIfWinner = playboard.getBoard()[y][0].getToken() + playboard.getBoard()[y][2].getToken() + playboard.getBoard()[y][4].getToken();
            if (winnerSequence.equals(checkIfWinner)) {
                return true;
            }
        }

        // check collums
        for (int x = 0; x < boardLength; x = x + 2) {

            //waarde naast elkaar zetten en vergelijk met driemaal player
            String checkIfWinner = playboard.getBoard()[0][x].getToken() + playboard.getBoard()[2][x].getToken() + playboard.getBoard()[4][x].getToken();
            if (winnerSequence.equals(checkIfWinner)) {
                return true;

            }
        }
        // check diagonals

        if ((playboard.getBoard()[0][0].getToken() + playboard.getBoard()[2][2].getToken() + playboard.getBoard()[4][4].getToken()).equals(winnerSequence)) {
            return true;


        }

        if ((playboard.getBoard()[0][4].getToken() + playboard.getBoard()[2][2].getToken() + playboard.getBoard()[4][0].getToken()).equals(winnerSequence)) {
            return true;

        }


        return false;

    }

    // het aantal potjes tellen
    public void addGameCounter() {
        gameCounter++;
    }

    // Het aantal spelletjes doorven

    public int getGameCounter(){
        return gameCounter;
    }

    // Een gelijkspel doorzetten
    public void addDrawCounter() {
        drawCounter++;
    }

    public int getDrawCounter(){
        return drawCounter;
    }

    // uitprinten score
    public void showScore(Player playerA, Player playerB) {
        System.out.println("Aantal potjes gespeeld:" + gameCounter);
        System.out.println("Aantal potjes gelijkspel:" + drawCounter);
        System.out.println("Aantal potjes gewonnen door " + playerA.getName() + " dit spelletje:" + playerA.getScore());
        System.out.println("Aantal potjes gewonnen door " + playerB.getName() + " dit spelletje:" + playerB.getScore());

        int overallWinsBeforeStartGamePlayerA = overallWinsPlayers.get(playerA.getName().toLowerCase());
        int overallWinsBeforeStartGamePlayerB = overallWinsPlayers.get(playerB.getName().toLowerCase());



        System.out.println("Aantal potjes gewonnen overall door " + playerA.getName() + ":" + (playerA.getScore()+overallWinsBeforeStartGamePlayerA));
        System.out.println("Aantal potjes gewonnen overall door " + playerB.getName() + ":" + (playerB.getScore()+overallWinsBeforeStartGamePlayerB));

    }

    public void addRoundCounter() {
        roundCounter++;
    }

    public void setRoundCounterToNull() {
        roundCounter = 0;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void printMenuAndChooseAction() {
        rightAnswer = false;

        System.out.println("Het menu:" +
                "\n1.Stoppen met spelen" +
                "\n2.het spel resetten" +
                "\n3.Namen veranderen van de spelers" +
                "\n Maak een keuze door het getal in te typen");

        do {

            int answerUser = userInput.nextInt();


            switch (answerUser) {
                case 1:
                    continuePlaying();
                    rightAnswer = true;
                    break;
                case 2:
                    System.out.println("Hier wordt nog aan gewerkt. Dit kan op het moment nog niet");
                    rightAnswer=true;
                case 3:
                    System.out.println("PlayerA, wat zal u naam zijn in dit spel? U speelt met het X teken.");
                    String placeHolderName = userInput.next();
                    playerA.changeName(placeHolderName);
                    System.out.println("PlayerB, wat zal u naam zijn in dit spel? U speelt met het O teken.");
                    placeHolderName = userInput.next();
                    playerB.changeName(placeHolderName);
                    rightAnswer=true;
                    break;
                default:
                    System.out.println("De waarde die je hebt ingevoerd is incorrect. Vul een y of een n in!");
                    rightAnswer = false;
            }
        }
        while (!rightAnswer);

    }

    public boolean continuePlaying() {
        rightAnswer = false;


        do {
            System.out.println("\n\n Wil je stoppen met het spel? y/n");

            String answerUser = userInput.next();

            switch (answerUser) {
                case "n":
                    System.out.println("Leuk dat je nog een keer wilt spelen. Daar gaan we dan");
                    rightAnswer = true;
                    return true;


                case "y":
                    System.out.println("Jammer dat je stopt.");
                    rightAnswer = true;

                    return false;


                default:
                    System.out.println("De waarde die je hebt ingevoerd is incorrect. Vul een y of een n in!");
                    rightAnswer = false;
            }
        }
        while (!rightAnswer);

        return false; //

    }
}

