package novi.basics;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean debug = false;
        String placeHolderNamePlayerA = "Aram";
        String placeHolderNamePlayerB = "Hans";
        Player playerA = new Player(placeHolderNamePlayerA, "X ");
        Player playerB = new Player(placeHolderNamePlayerB, "O ");
        Playboard playboard = new Playboard();
        Player currentPlayer = playerA;
        int inputPlayersY = 0;
        int inputPlayersX = 0;
        boolean hasWon;
        boolean continuePlaying;
        HashMap<String,Integer> overallWinsPlayers = new HashMap<>();
        Game game = new Game(playerA, playerB, playboard, debug, overallWinsPlayers);
        FileManager fileManager= new FileManager(playerA,playerB, game,overallWinsPlayers);
        boolean tokenIsPlaced;


        System.out.println("Goedemorgen vriendjes en vriendinnetjes, het is tijd voor het legendarische boter kaas en eieren spel. Julie moeten alleen eerst een naam kiezen");

        if (!debug) {
            System.out.println("PlayerA, wat zal u naam zijn in dit spel? U speelt met het X teken.");
            placeHolderNamePlayerA = userInput.next();
            playerA.changeName(placeHolderNamePlayerA);
            System.out.println("PlayerB, wat zal u naam zijn in dit spel? U speelt met het O teken.");
            placeHolderNamePlayerB = userInput.next();
            playerB.changeName(placeHolderNamePlayerB);
        }

        fileManager.returnOverallWinsPlayersUntilStartGame(playerA,playerB);



        do {
            game.setUpGame();


            do {
                game.addRoundCounter();

                System.out.println("\nOp welke plek wil je een " + currentPlayer.getToken() + "zetten, meneer/mevrouw/of-alles-er-tussen-in " + currentPlayer.getName() + "? Het eerste getal is de Y en het tweede getal de X op het bord");


                boolean validAnswer;
                if (!debug) {
                    do {

                        do {

                            System.out.print("Y=");
                            String placeHolderInputPlayersY = userInput.next();

                            switch (placeHolderInputPlayersY) {

                                case "0":
                                case "2":
                                case "4":
                                    inputPlayersY = Integer.valueOf(placeHolderInputPlayersY);
                                    validAnswer = true;
                                    break;
                                case "m":
                                    game.printMenuAndChooseAction();
                                    validAnswer = false;
                                    return;
                                default:
                                    System.out.println("U heeft geen 0, 2 of een 4 ingevuld. Probeer het nog eens.\nAls je wilt stoppen of resetten, druk dan op m voor het menu");
                                    validAnswer = false;
                            }
                        }
                        while (!validAnswer);


                        do {

                            System.out.print("X=");
                            String placeHolderInputPlayerX = userInput.next();

                            switch (placeHolderInputPlayerX) {

                                case "0":
                                case "2":
                                case "4":
                                    inputPlayersX = Integer.valueOf(placeHolderInputPlayerX);
                                    validAnswer = true;
                                    break;
                                case "m":
                                    game.printMenuAndChooseAction();
                                default:
                                    System.out.println("U heeft geen 0, 2 of een 4 ingevuld. Probeer het nog eens.\nAls je wilt stoppen of resetten, druk dan op m voor het menu");
                                    validAnswer = false;
                            }
                        }
                        while (!validAnswer);

                        tokenIsPlaced = playboard.adaptPlayboard(inputPlayersY, inputPlayersX, currentPlayer);
                    }
                    while (!tokenIsPlaced);
                } else {
                    inputPlayersX = 0;
                    inputPlayersY = 0;
                    playboard.adaptPlayboard(inputPlayersY, inputPlayersX, currentPlayer);
                    inputPlayersX = 0;
                    inputPlayersY = 2;
                    playboard.adaptPlayboard(inputPlayersY, inputPlayersX, currentPlayer);
                    inputPlayersX = 0;
                    inputPlayersY = 4;
                    playboard.adaptPlayboard(inputPlayersY, inputPlayersX, currentPlayer);
                }

                playboard.printPlayboard();

                hasWon = game.checkIfWon(playboard, currentPlayer);


                if (hasWon) {

                    System.out.println("\n\nYesssss, je hebt gewonnen, berenmeneer " + currentPlayer.getName());

                    if (currentPlayer == playerA) {
                        playerA.addScore();
                        break;
                    } else {
                        playerB.addScore();
                        break;
                    }


                } else {

                    currentPlayer = game.switchPlayer(currentPlayer, playerA, playerB);
                }
            }
            while (game.getRoundCounter() < 9);

            if (!hasWon && game.getRoundCounter() == 9) {

                System.out.println("Helaas niemand heeft gewonnen");
                game.addDrawCounter();

            }


            currentPlayer = game.switchPlayer(currentPlayer, playerA, playerB); //The other play starts at the next game

            game.addGameCounter();
            game.setRoundCounterToNull();
            game.showScore(playerA, playerB);
            continuePlaying = game.continuePlaying();

        }
        while(continuePlaying);

        fileManager.askToSaveScore();

        System.out.println("Nog een prettige dag. Geniet van de zon als die er is");

    }

    }









