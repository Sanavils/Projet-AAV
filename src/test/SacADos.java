package test;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class SacADos {
    private Objet[] tabObjet;
    private float poidsMax;


    public  SacADos(String chemin, float poidsMax){
        int nbObjet =0;
        int i = 0;
        this.poidsMax = poidsMax;

        //List<Objet> objets = new ArrayList<>();
        File file = new File(chemin);

        //BufferedReader bufferedReader = null;

        try {
            Scanner in = new Scanner(file);
            Scanner in2 = new Scanner(file);

            /*FileReader fileReader = new FileReader(file);
            bufferedReader= new BufferedReader(fileReader);
            String ligne = bufferedReader.readLine();
            String ligne2 = bufferedReader.readLine();*/

            while (in.hasNextLine()){
                in.nextLine();
                ++nbObjet;
            }


            this.tabObjet = new Objet[nbObjet];

            while ( in2.hasNextLine()){
                String motsLigne[] = in2.nextLine().split(";");
                this.tabObjet[i]= new Objet(motsLigne[0],Float.parseFloat(motsLigne[1]),Float.parseFloat(motsLigne[2]));
                ++i;
            }

            in.close();
            in2.close();

        } catch (FileNotFoundException e) {
            System.err.printf("le fichier %s n'existe pas", file.toString() );
        }

    }

    public void résoudre(String choixRes){
        if (choixRes.equals("glouton")){
            this.glouton();
        }
        if (choixRes.equals("dynamique")){
            this.dynamique();
        }
        if (choixRes.equals("PSE")){
            this.pse();
        }
    }




    public void glouton(){
        this.tabObjet = quicksort(this.tabObjet, 0, this.tabObjet.length - 1);
        for (int x= 0; x< this.tabObjet.length; ++x){
            tabObjet[x].estStocké(true);
            if (poidsDuSac(tabObjet)>poidsMax){
                tabObjet[x].estStocké(false);
            }
        }
    }

    private Objet getObjet(int i) {
        return this.tabObjet[i];
    }

    public float poidsDuSac(Objet[] tabObjet){
        float poid = 0;
        for (int p = 0; p<tabObjet.length; ++p){
            if (tabObjet[p] != null){
                if (tabObjet[p].getStocakge() == true){
                    poid = tabObjet[p].getPoids();
                }
            }
        }
        return poid;
    }

    private Objet[] quicksort(Objet[] tabObjet, int Premier, int Dernier) {
        if (Premier < Dernier){
            Pivot pivot= new Pivot();
            int choix = ChoixPivot(tabObjet, Premier, Dernier);
            pivot = Ranger(tabObjet,Premier,Dernier, choix);
            quicksort(pivot.getTab(), Premier,pivot.getPivot()-1);
            quicksort(pivot.getTab(), pivot.getPivot()+1,Dernier );
        }
        return tabObjet;
    }

    private Pivot Ranger(Objet[] tabObjet, int premier, int dernier, int pivot) {
        Pivot pivot2= new Pivot();
        tabObjet = echanger(tabObjet, pivot, dernier);
        int a = premier;

        for (int z= premier;z<dernier;z++){
            if (tabObjet[z].compare(tabObjet[dernier])>0){
                echanger(tabObjet, z,a);
                a++;
            }
        }
        tabObjet = echanger(tabObjet, dernier,a);
        pivot2.setTableau(tabObjet);
        pivot2.setPivot(a);
        return pivot2;

    }

    private Objet[] echanger(Objet[] tabObjet, int pivot, int dernier) {
        Objet tmp = tabObjet[pivot];
        tabObjet[pivot] = tabObjet[dernier];
        tabObjet[dernier] = tmp;
        return tabObjet;
    }

    private int ChoixPivot(Objet[] tabObjet, int Premier, int Dernier) {
        return (Premier + Dernier)/2;
    }

    public String toString(){
        String s = "";
        for (Objet o : this.tabObjet){
            if (o.getStocakge() == true){
                s+= o.toString() + System.lineSeparator();
            }
        }
        return s;
    }


    public void dynamique(){
        float [][] Mat = new float[tabObjet.length][(int) (poidsMax+1)];
        for (int j = 0; j < poidsMax; j++) {
            if (tabObjet[0].getPoids() > j)
                Mat[0][j] = 0;
            else
                Mat[0][j] = tabObjet[0].getPrix();
        }

        for(int i=1; i<tabObjet.length;i++) {
            for (int j = 0; j < poidsMax+1; j++) {
                if (tabObjet[i].getPoids() > j)
                    Mat[i][j] = Mat[i-1][j];
                else
                    Mat[i][j] =  Math.max(Mat[i-1][j], Mat[i-1][(int) (j-tabObjet[i].getPoids())] + tabObjet[i].getPrix());
            }
        }
        int i= tabObjet.length-1;
        int j = (int) poidsMax;

        while (Mat[i][j] == Mat[i][j-1]) {
            j--;
        }
        while(j>0) {
            while(i>0 && Mat[i][j]==Mat[i-1][j]) {
                i--;
            }
            j= j-(int) (tabObjet[i].getPoids());
            if (j>=0) {
                this.tabObjet[i].estStocké(true);
            }
            i--;
        }
    }



    public void pse(){
        Arbre arbre = new Arbre(tabObjet, 0,poidsMax);
        System.out.println(arbre);
    }


}
