package novi.basics;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager {

    private Player playerA;
    private Player playerB;
    private Game game;
    private boolean rightAnswer;
    private Scanner userInput;

    private HashMap<String,Integer> overallWinsPlayers;

    public FileManager (Player playerA, Player playerB, Game game,HashMap<String,Integer> overallWinsPlayers) {
        this.playerA=playerA;
        this.playerB=playerB;
        this.game=game;
        this.rightAnswer=false;
        this.userInput=new Scanner(System.in);
        this.overallWinsPlayers=overallWinsPlayers;


    }


    public boolean saveScore (){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            File scoreFile = new File("C:\\Users\\aramw\\OneDrive\\Novi\\BestandenUitProbeersel\\scoreFile.txt");
            FileWriter pen = new FileWriter(scoreFile,true);
            BufferedWriter printer = new BufferedWriter(pen);

            printer.write(dtf.format(now));
            printer.newLine();
            printer.write("Aantal potjes gespeeld:" + game.getGameCounter());
            printer.newLine();
            printer.write("Aantal potjes gelijkspel:" + game.getDrawCounter());
            printer.newLine();
            printer.write("Aantal potjes gewonnen door " + playerA.getName() + ":" + playerA.getScore());
            printer.newLine();
            printer.write("Aantal potjes gewonnen door " + playerB.getName() + ":" + playerB.getScore());
            printer.newLine();
            printer.close();
            return true;
        }
        catch (IOException e){
            System.out.println("U heeft geen toegang tot de locatie waar u het bestand op wilt slaan. Succes ermee");
            return false;
        }



    }

    public void askToSaveScore (){


        do {
            System.out.println("\n\n Wil je jullie score opslaan? y/n");

            String answerUser = userInput.next();

            switch (answerUser) {
                case "n":
                    System.out.println("Top, wij jij wilt");
                    rightAnswer = true;
                    break;

                case "y":
                    rightAnswer = true;

                    boolean savedFile =saveScore();

                    if (savedFile){
                        System.out.println("Het bestand is opgeslagen.");
                    }
                    else {
                        System.out.println("Het is niet gelukt om het bestand op te slaan. Succes ermee. Er is op het moment geen oplossing. Je score gaat dus verloren");
                    }
                    break;
                default:
                    System.out.println("De waarde die je hebt ingevoerd is incorrect. Vul een y of een n in!");
                    rightAnswer = false;
            }
        }
        while (!rightAnswer);

    }

    public void returnOverallWinsPlayersUntilStartGame (Player playerA,Player playerB){


        overallWinsPlayers.put(playerA.getName().toLowerCase(), 0);
        overallWinsPlayers.put(playerB.getName().toLowerCase(), 0);


        try {
            File scoreFile = new File("C:\\Users\\aramw\\OneDrive\\Novi\\BestandenUitProbeersel\\scoreFile.txt");
            Scanner fileScanner = new Scanner(scoreFile);
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine().toLowerCase();
                if(line.contains(playerA.getName().toLowerCase())){
                    System.out.println(line);

                    char placeHolderNumberOfWins = line.charAt(line.length()-1);


                    int placeHolderNumberOfWins2 = Character.getNumericValue(placeHolderNumberOfWins) + overallWinsPlayers.get(playerA.getName().toLowerCase());

                    overallWinsPlayers.put(playerA.getName().toLowerCase(), placeHolderNumberOfWins2);


                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Het document bestaat niet");
        }

        try {
            File scoreFile = new File("C:\\Users\\aramw\\OneDrive\\Novi\\BestandenUitProbeersel\\scoreFile.txt");
            Scanner fileScanner = new Scanner(scoreFile);
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine().toLowerCase();
                if(line.contains(playerB.getName().toLowerCase())){
                    System.out.println(line);

                    char placeHolderNumberOfWins = line.charAt(line.length()-1);

                    int placeHolderNumberOfWins2 = Character.getNumericValue(placeHolderNumberOfWins) + overallWinsPlayers.get(playerB.getName().toLowerCase());

                    overallWinsPlayers.put(playerB.getName().toLowerCase(), placeHolderNumberOfWins2);

                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Het document bestaat niet");
        }


    }



}
