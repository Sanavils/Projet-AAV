package test;

public class Arbre {
    private Arbre leftTree, rightTree;
    private Objet[] listeObj;
    private static float borneInf;
    private float borneSup;

    public Arbre(Objet[] tabObjet, int z,float poidMax ){
        if (z <= tabObjet.length) {
            this.listeObj = new Objet[tabObjet.length];
            for (int i = 0; i < tabObjet.length; i++) {
                this.listeObj[i] = tabObjet[i];
                //this.leftTree = new Arbre(this.listeObj);
                //this.rightTree = new Arbre();
            }

            if (z != tabObjet.length) {
                this.leftTree = new Arbre(tabObjet, z + 1, poidMax);
                listeObj[z] = tabObjet[z];
                if (SacADos.poidsDuSac(tabObjet)<=poidMax){
                    this.rightTree = new Arbre(tabObjet, z+1, poidMax);
                }
            }
        }
    }

    public String toString() {
        //ordre infixe
        String s="";
        if(leftTree!=null)
            s+=leftTree.toString();
        s+="{";
        for(int i = 0; i < this.listeObj.length; i++) {
            s += this.listeObj[i].toString() + ";";
        }
        s+="}";
        if(rightTree!=null)
            s+=rightTree.toString();
        return s;
    }

    public boolean estVide() {
        if (this!=null && this.listeObj!=null)
            return false;
        else
            return true;
    }

    public void calculBorneInf(){
        if (this.PrixObj() > Arbre.borneInf){
            Arbre.borneInf = this.PrixObj();
        }
    }

    public void calaculBorneSup(Objet[] listeObj){
        float resultat = 0;
        resultat = this.PrixObj();
        for (int i =0; i<listeObj.length;i++){
            resultat += listeObj[i].getPrix();
        }
        this.borneSup = resultat;
    }


    public float PrixObj(){
        float prix = 0;
        for (int p = 0; p< listeObj.length; ++p){
            if (this.listeObj[p] != null){
                prix = listeObj[p].getPrix();
            }
        }
        return prix;
    }

   /*public Arbre(Objet[] tabObjet, int z , int x,float poidsMax ){
        this.listeObj = new Objet[z];
        for (int i = 0; i < z; i++) {
            this.listeObj[i] = tabObjet[i];
        }

        if (x < listeObj.length) {
         //   if(SacADos.poidsDuSac(this.listeObj)<poidsMax)
                this.leftTree = new Arbre(tabObjet, z, x + 1, poidsMax);
                rightTree = new Arbre(tabObjet, z + 1, x + 1, poidsMax);
         }
   }*/
}

