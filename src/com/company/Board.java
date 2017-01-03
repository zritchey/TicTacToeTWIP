package com.company;

import java.util.Arrays;

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

    public static void update(int x,int y, char c, char[][]a){
        a[x][y]=c;
        play=a;
    }
    public static void update(int[]a,char c, char[][]b){
        update(a[0],a[1],c,b);
    }

    public static void possible(char[][]brd){
        int[]final_spot={-1,-1};
        int free[][]=find(' ',brd);
        int[][] o = evaluater(' ', 'o', brd);
        int comp[] = new int[o.length];
        for (int j = 0; j < o.length; j++) {
            comp[j] = Math.max(Math.max(o[j][0], o[j][1]), Math.max(o[j][2], o[j][3]));
        }
        int max2 = comp[0];
        for (int j = 1; j < comp.length; j++) {
            max2 = Math.max(comp[j], max2);
        }
        int[][] x = evaluater(' ', 'x', brd);
        int play[] = new int[x.length];
        for (int j = 0; j < x.length; j++) {
            play[j] = x[j][0]+ x[j][1]+ x[j][2]+ x[j][3];
        }
        int max1 = play[0];
        for (int j = 1; j < play.length; j++) {
            max1 = Math.max(play[j], max1);
        }
        int indx=0;
        int [] sums=new int [x.length];
        if(max2==size-1){
            indx=Arrays.binarySearch(comp,max2);
        }
        else if(brd[1][1]==' '){
            int []a={1,1};
            indx=Match(free,a);
        }
        else {
        int rem=0;
            for (int j = 0; j < free.length; j++) {
                if (sums[j] >= rem) {
                    indx = j;
                    rem=sums[j];
                }
            }
        }
        final_spot[0]=free[indx][0];
        final_spot[1]=free[indx][1];
        update(final_spot,'o',brd);
        System.out.println("The computer chose ("+(final_spot[0]+1)+", "+(final_spot[1]+1)+")" );
    }

    public static int[][] find(char player,char[][]brd){
        int [][]a=new int [size*size][2];
        int count=0;
        for (int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(brd[i][j]==player){
                    a[count][0]=i;
                    a[count][1]=j;
                    count++;
                }
            }
        }
        int[][]b=new int [count][2];
        System.arraycopy(a,0,b,0,count);
        return b;
    }

    public static int [][] evaluater(char basis,char player,char[][]brd){
       int[][]a=find(basis,brd);
        int [][]free=new int[a.length][];
        System.arraycopy(a,0,free,0,a.length);
        int [][]choice=new int[a.length][4];// multidimensional array is used to store (row, column,forward,back)
        for (int p=0;p<free.length;p++) {
            int domain= (size-1)-free[p][0];
            int range=(size-1)-free[p][1];
            int back=0;
            int forward=0;
            int row=0;
            int column=0;
            for (int y = 0; y < size; y++) {
                if(brd[free[p][0]][y]==player){
                column++;
                }
                if (brd[y][free[p][1]]==player){
                    row++;
                }
                if (brd[y][y]==player){
                        forward++;
                    }
                if (brd[y][(size-1)-y] == player) {
                    back++;
                }
            }
            choice [p][0]=row;
            choice[p][1]=column;
            choice[p][2]=forward;
            choice[p][3]=back;
        }
        return choice;
    }

    public static int[][]evaluater(char player,char[][] brd){
        return evaluater(player,player,brd);
    }

    public static boolean evaluate(char e,char[][]brd){
        int[][]choice=evaluater(e,brd);
        int num=0;
        for(int p=0;p<choice.length;p++){
            num=Math.max(Math.max(Math.max(choice[p][0],choice[p][1]),Math.max(choice[p][2],choice[p][3])),num);
        }
        return num==size;
    }

    public static int Match(int[][]s,int[]f){
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