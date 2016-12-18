package com.company;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zr162 on 12/16/16.
 */
public class Board {
    public static final int size=3;
    public static char[][]play;
    public Board(){
        play =new char[size][size];
        for(char[]a:play) {
            Arrays.fill(a, ' ');
        }

    }
    public void print(){
        for(int i=0;i<size;i++){
            String sep="\n";
            for(int j=0;j<size;j++){
                sep+="____";
                System.out.print(" "+ play[j][i]+" ");
                if(j!=size-1)
                    System.out.print("|");
            }
            if(i<size-1)
                System.out.println(sep);
        }
        System.out.println();
    }
    public void update(int x,int y, char c, char[][]a){
        a[x][y]=c;
        play=a;

    }
    public void update(int[]a,char c, char[][]b){
        update(a[0],a[1],c,b);
    }

    public char [][] returnp(){
        return play;
    }

    public void computer(int r ,char[][]bored){
        int[]final_spot={-1,-1};
         int [][]a=new int [size*size][2];
        int count=0;
        for (int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(play[i][j]==' '){
                    a[count][0]=i;
                    a[count][1]=j;
                    count++;
                }
            }
        }
        int [][]free=new int[count][];
        System.arraycopy(a,0,free,0,count);
        a=new int[5][2];
        a[0][0]=size/2;
        a[0][1]=size/2;
        int s=0;
        for (int j=0;j<size;j+=(size-1)){
            for(int i=0;i<size;i+=(size-1)){
                s++;
                a[s][0]=i;
                a[s][1]=j;

            }
        }

        int [][]choice=new int[count][4];// multidimensional array is used to store (row, column,forward,back)
        for (int p=0;p<free.length;p++) {
            int domain= (size-1)-free[p][0];
            int range=(size-1)-free[p][1];
            int back=0;
            int forward=0;
            int row=0;
            int column=0;
            for (int y = 0; y < size-1; y++) {
                for (int x = 0; x < size-1; x++) {
                    if (play[free[p][0]+(x-free[p][0])][free[p][1]]==Main.name[1]){
                        row++;
                    }
                    if(play[free[p][0]][free[p][1]+(y-free[p][1])]==Main.name[1]){
                        column++;
                    }
                    if (play[free[p][0]+(x-free[p][0])][free[p][1]+(y-free[p][1])]==Main.name[1]){
                        forward++;
                    }
                    if(x<domain&&y<range) {
                        if (play[free[p][ 0] - (x -domain)][free[p][1] - (y - range)] == Main.name[1]) {
                            back++;     // this is all very problematic
                        }
                    }
                }
            }
            choice [p][0]=row;
            choice[p][1]=column;
            choice[p][2]=forward;
            choice[p][3]=back;

        }
        int num[]=new int[choice.length];
        for(int p=0;p<choice.length;p++){

            num[p]=Math.max(Math.max(choice[p][0],choice[p][1]),Math.max(choice[p][2],choice[p][3]));
        }

        int pick1 = Arrays.binarySearch(num, size-2);
        for(s=0;s<num.length;s++){
            num[s]=choice[s][0]+choice[s][1]+choice[s][2]+choice[s][3];
        }
        if(r<=2){
            while(1==1){
                int ran=new Random().nextInt(a.length);
                int find=Arrays.binarySearch(free,a[ran]);
                if(find>-1){
                    update(a[ran],Main.name[1],bored);
                    final_spot=free[find];
                    break;
                }
            }

        }
        else if (pick1>-1){
            update(free[pick1],Main.name[1],bored);
            final_spot=free[pick1];
        }
        else{
            int indx=0;
            for(s=0;s<num.length;s++){
                indx=Math.max(indx,num[s]);
            }
            indx=Arrays.binarySearch(num,indx);
            update(free[indx],Main.name[1],bored);
            final_spot=free[indx];


        }
        System.out.println("The computer chose "+Arrays.toString(final_spot));
    }
    public boolean evaluate(){         ////// the array must be evaluated perpendicularly and //
                                            // diagonally after every move, to determine if a player has won.
return true;       ///have to implement this class
    }
}