package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
                                    ///ArrayList of Arraylists?
                                    ///use trees and combinatorial game theory
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
        play[1][2]='x';
        play[0][1]='x';
        play[2][0]='x';
        play[1][1]='x';
        play[0][0]='x';
    }
    public static void print(){
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




        System.out.println("The computer chose ("+(final_spot[0]+1)+", "+(final_spot[1]+1)+")" );
    }



    public void possible(char[][]brd,int p){

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
 
        ArrayList aryl=new ArrayList();

       char [][]free=new char[size][size];
        for(int i=0;i<a.length;i++) {

            System.arraycopy(brd, 0, free, 0, count);
            free[a[i][0]][a[i][1]]=Main.name[p];
            p=(p+1)%2;
            if(i>1)
            possible(free,p);
            aryl.add(free);
        }
        aryl.trimToSize();
       aryl.toArray();

    }


    public static boolean evaluate(char player){
        int [][]a=new int [size*size][2];
        int count=0;
        for (int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(play[i][j]==player){
                    a[count][0]=i;
                    a[count][1]=j;
                    count++;
                }
            }
        }
        int [][]free=new int[count][];
        System.arraycopy(a,0,free,0,count);
        int [][]choice=new int[count][4];// multidimensional array is used to store (row, column,forward,back)
        for (int p=0;p<free.length;p++) {
            int domain= (size-1)-free[p][0];
            int range=(size-1)-free[p][1];
            int back=0;
            int forward=0;
            int row=0;
            int column=0;
            for (int y = 0; y < size; y++) {
                if(play[free[p][0]][y]==player){
                column++;
            }


                    if (play[y][free[p][1]]==player){
                        row++;
                    }


                if (play[y][y]==player){
                        forward++;
                    }

                if (play[y][(size-1)-y] == player) {
                    back++;
                }

            }
            System.out.println(row+"\n"+column+"\n"+forward+"\n"+back);
            choice [p][0]=row;
            choice[p][1]=column;
            choice[p][2]=forward;
            choice[p][3]=back;
        }
        int num[]=new int[choice.length];
        for(int p=0;p<choice.length;p++){
            num[p]=Math.max(Math.max(choice[p][0],choice[p][1]),Math.max(choice[p][2],choice[p][3]));
            System.out.println(Arrays.toString(free[p])+"\t"+num[p]+"\t"+Arrays.toString(choice[p]));
        }
        print();
        int full=0;
        for(int j=0;j<num.length;j++){
            full=Math.max(full,num[j]);
        }
        return full==size;
    }


    public int Match(int[][]s,int[]f){
        int here=-1;
        for (int i=s.length-1 ;i>=0;i--){
            boolean t=true;
            for(int j=0;j<f.length;j++) {
                t=t&&(s[i][j]==f[j]);
            }
            if(t){
                here=i;
                break;
            }
        }
        return here;
    }
}
