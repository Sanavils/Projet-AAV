package test;

public class Arbre {
    private Arbre leftTree, rightTree;
    private Objet[] listeObj;
    private float borneInf;
    private float borneSup;

    public Arbre(Objet[] tabObjet, int z,float poidMax ){
        if (z < tabObjet.length) {
            this.listeObj = new Objet[tabObjet.length];
            for (int i = 0; i < tabObjet.length; i++) {
                this.listeObj[i] = tabObjet[i];
                //this.leftTree = new Arbre(this.listeObj);
                //this.rightTree = new Arbre();
            }

            if (z != listeObj.length) {
                this.leftTree = new Arbre(tabObjet, z + 1, poidMax);
                listeObj[z] = tabObjet[z];
                //if ()
            }
        }
    }


       /* public Arbre(Objet[] tabObjet, int z , int x,float poidsMax ){
            this.listeObj = new Objet[z];
            for (int i = 0; i < z; i++) {
                this.listeObj[i] = tabObjet[i];
            }

            if (x < listeObj.length) {
             //   if(SacADos.poidsDuSac(this.listeObj)<poidsMax) {
                    this.leftTree = new Arbre(tabObjet, z, x + 1, poidsMax);
                    rightTree = new Arbre(tabObjet, z + 1, x + 1, poidsMax);
                }
            }*/
        }

