package test;

import java.util.Scanner;

public class Appli {


    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        String mots[] = choix.split(" ");
        //float tmp = );
        new SacADos(mots[1], Float.parseFloat(mots[2]));


    }
}
