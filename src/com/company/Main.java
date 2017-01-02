package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
public static int round;
    public static int max= (int)Math.pow(Board.size,2);
    public static char []name={'x','o'};
    public static void main(String[] args) {
        round=0;
        Board brd=new Board();
        char input[][]=Board.play;
        System.out.println("Would you like to play the game?");
        Scanner sc=new Scanner (System.in);
        String res=sc.nextLine();
         int yes=0;
        if (res.equalsIgnoreCase("yes")) {
             yes++;
            System.out.println("The player is an 'x' by default. The location of each move will be chosen by row and column\nA coin will be tossed to determine who goes first.");
            System.out.println("Ties go to the computer.\n");
            System.out.println("Call heads or tails:");
            res = sc.next();
            int player = new Random().nextInt(5002) % 2;
            if (res.trim().equalsIgnoreCase("heads") && player == 0 || res.trim().equalsIgnoreCase("tails") && player == 1) {
                player = 0;
            } else {
                player = 1;
            }
            int start=player;
            while (round < max) {
                int x = -1;
                int y = -1;
                player = player%2;
                if (player == 0) {
                    System.out.println("Select a Column: ");
                    x = sc.nextInt() - 1;
                    System.out.println("Select a row: ");
                    y = sc.nextInt() - 1;
                    if (x > -1 && y > -1 && x < Board.size && y < Board.size) {
                        if (input[x][y] == ' ') {
                            brd.update(x, y, name[player], input);
                            player++;
                            round++;
                            brd.print();
                        } else {
                            System.out.println("There was already a piece there on the board.");
                        }
                    } else {
                        System.out.println("That was an invalid move.");
                    }
                } else {
                    brd.possible(input,player);
                    player++;
                    round++;
                    brd.print();
                }
                 if (Board.evaluate(name[1],input)){
                    System.out.println("The computer won.");
                    break;
                }
                else if (Board.evaluate(name[0],input)){
                    System.out.println("You won! Congratulations!");
                    break;
                }else if(round==max){
                    System.out.println("The game was a Tie. \nThis means the computer won.");
                }
            }
        }
        else{
            System.out.println("Ok. Bye.");
        }
        if(yes==1){
            System.out.println("\n\nThanks for playing!");
        }
    }
}