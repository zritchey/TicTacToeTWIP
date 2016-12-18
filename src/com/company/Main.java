package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
public static int round;
    public static int max= (int)Math.pow(Board.size,2);
    public static char []name={'x','o'};
    public static void main(String[] args) {

        Board brd=new Board();

        char input[][]=brd.returnp();
        System.out.println("Would you like to play the game?");
        Scanner sc=new Scanner (System.in);
        String res=sc.nextLine();
        if (res.equalsIgnoreCase("yes")) {
            System.out.println("The player is an 'x' by default. The location of each move will be chosen by row and column\nA coin will be tossed to determine who goes first ");
            System.out.println("Call heads or tails:");
            res = sc.next();
            int player = new Random().nextInt(5002) % 2;

            if (res.trim().equalsIgnoreCase("heads") && player == 0 || res.trim().equalsIgnoreCase("tails") && player == 1) {
                player = 0;
            } else {
                player = 1;
            }

            while (round < max) {

                int x = -1;
                int y = -1;
                player = player%2;
                if (player == 0) {
                    System.out.println("Select a row: ");
                    x = sc.nextInt() - 1;
                    System.out.println("Select a Column: ");
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
                    brd.computer();
                    player++;
                    round++;
                    brd.print();
                }
            }
        }
    }

}