import java.util.*;
public class Battleships {

    public static Scanner input=new Scanner (System.in);
    public static Random rand = new Random();

    public static String[][] ocean= new String[10][10];//to Create the main ocean in using of 2D array
    public static String[][] ocean2= new String[10][10];// Ocean2 ,

    public static int userShips=0;
    public static int computerShips=0;

    public static void main ( String[] args){
        intro();
        userLoc(ocean); //deploy user ships.
        compLoc(ocean); //deploy computer ships
        battle();
    }

    public static void intro(){
        System.out.println("**** Welcome to Battle Ships game ****");

        printMap(ocean);
    }

    public static void printMap(String[][] ocean){
        System.out.println("  0123456789  ");

        for(int row =0; row<ocean.length;row++){ // To create | |:
            System.out.print(row+"|");
            for (int col=0; col<ocean[row].length;col++){
                if(ocean[row][col]==null){
                    System.out.print(" ");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789  ");
    }

    public static void printMap2(String[][] ocean2){

        System.out.println("Ocean2");
        System.out.println("  0123456789  ");
        for(int row =0; row<ocean2.length;row++){
            System.out.print(row+"|");
            for (int col=0; col<ocean2[row].length;col++){
                if(ocean2[row][col]==null){
                    System.out.print(" ");
                } else {
                    System.out.print(ocean2[row][col]);
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789  ");
    }

    public static void userLoc(String[][] ocean){
        System.out.println("Deploy your ships:");

        while (userShips<5){
            System.out.print("Enter X coordinate for your ship: ");
            int col=input.nextInt();
            System.out.print("Enter Y coordinate for your "+(userShips+1)+". ship: ");
            int row= input.nextInt();


            if (row>9||col>9){//as we can't move the ships out of ocean
                System.out.println("The coordinate you entered is out of range, please try again");
            } else if(ocean [row][col]!=null) { //we can't placed more ships in one placed
                System.out.println("The coordinate you entered is already used, please try again");
            } else {
                ocean [row][col]="@";
                userShips++;
            }
        }
    }

    public static void compLoc(String[][] ocean){
        System.out.println("Computer is deploying ships:");

        while (computerShips<5){
            int row=rand.nextInt(10), col=rand.nextInt(10) ;

            if (ocean [row][col]==null&&ocean2 [row][col]==null){//you cannot place the ship on a location that is already taken by another ship (player’s or computer’s)
                System.out.println("Ship Deployed. ");
                ocean2[row][col] = "@";//place ships in Ocean2
                computerShips++;
            }
        }

        System.out.println("\n--------------------------------------\n");
    }

    public static void userTurn(){

        System.out.println("\nYOUR TURN");
        int turn=0;

        while (turn==0) {

            System.out.print("Enter X coordinate: ");
            int col = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            int row = input.nextInt();


            if (row > 9 || col > 9) {//you can’t shot outside the 10 by 10 grid
                System.out.println("The coordinate you entered is out of range, please try again.\n");
            }else if(ocean[row][col] =="!"||ocean[row][col] == "x"||ocean[row][col]=="-"){
                System.out.println("The coordinate you entered was already used, please try again.\n");
            } else if (ocean2[row][col] == "@") {//computer loses ship).
                System.out.println("Boom! You sunk the computer's ship!");
                ocean[row][col] = "!";
                ocean2[row][col] = "!";
                computerShips--;
                turn++;
            } else if (ocean[row][col] == "@") { //player loses ship.
                System.out.println("Oh no!, you sunk your own ship! :(");
                ocean[row][col] = "x";
                userShips--;
                turn++;
            }else {//Player missed. No ship on the entered coordinates
                System.out.println("Sorry, you missed!");
                ocean[row][col]="-";
                turn++;
            }
        }
    }

    public static void computerTurn(){
        System.out.println("COMPUTER'S TURN");
        int turn=0;

        while (turn==0){

            int x=rand.nextInt(10);;
            int y=rand.nextInt(10);;

            if(ocean2[x][y]=="@") {//Computer guessed coordinates of its own ship (computer loses ship).
                System.out.println("The Computer sunk one of its own ships!");
                ocean[x][y]="!";
                ocean2[x][y]="!";
                computerShips--;
                turn++;
            } else if (ocean[x][y]=="@") {
                System.out.println("The Computer sunk one of your ships!");
                ocean[x][y]="x";
                ocean2[x][y]="x";
                userShips--;
                turn++;
            }else if(ocean[x][y] =="!"||ocean[x][y] == "x"||ocean2[x][y]=="-"){

            } else {//Computer missed. No ship on guessed coordinates.
                System.out.println("Computer missed!");
                ocean2[x][y]="-";
                turn++;
            }
        }

    }

    public static void battle(){
        printMap(ocean); //print updated map
        System.out.println("\nYour ships: "+userShips+" | Computer ships: "+computerShips+"\n------------------------");

        while (userShips>0&&computerShips>0) {
            userTurn();
            computerTurn();
            printMap(ocean);
            System.out.println("\nYour ships: "+userShips+" | Computer ships: "+computerShips+"\n--------------------");
        }

        if (userShips==0){
            System.out.println("\n*** GAME OVER ***");
            System.out.println("\nYour ships: "+userShips+" | Computer ships: "+computerShips+"\n--------------------");
        } else if (computerShips==0){
            System.out.println("Hooray! You win the Battle :)");
            System.out.println("\nYour ships: "+userShips+" | Computer ships: "+computerShips+"\n--------------------");
        }

    }

}

