package test;

import java.util.Scanner;

public class Appli {

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        String mots[] = choix.split(" ");
        SacADos sacADos = new SacADos(mots[1], Float.parseFloat(mots[2]));
        String choixRes = mots[3];
        sacADos.résoudre(choixRes);
        System.out.println(sacADos.toString());


    }
}
