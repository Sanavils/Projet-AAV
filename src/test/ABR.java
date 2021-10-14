public class ABR {
    private ABR leftTree, rightTree;
    private Objet[] tabValeurs;
    private static float borneInf;
    private float borneSup;
    private static Objet[] tabMeillValeurs;

    public ABR(Objet[] listeObjSac,Objet[] tabObjet ,int profondeur,float poidsMax) {
        if (profondeur <= listeObjSac.length) {
            this.tabValeurs = new Objet[listeObjSac.length];
            for (int i = 0; i < listeObjSac.length; i++) {
                if(tabObjet[i]!=null)
                    this.tabValeurs[i] = tabObjet[i];
            }

            this.calculBorneSup(listeObjSac,profondeur);
            this.calculBorneInf();

            if(profondeur!= listeObjSac.length) {
                this.leftTree = new ABR(listeObjSac, tabObjet, profondeur + 1, poidsMax);
                tabObjet[profondeur]=listeObjSac[profondeur];
                if(this.poidsListeObj(tabObjet)<=poidsMax && borneSup>borneInf) {
                    this.rightTree = new ABR(listeObjSac, tabObjet, profondeur + 1, poidsMax);
                }
                tabObjet[profondeur]=null;
            }
        }
    }

    public void rechercheMeillValeur (int taille){
        if(this.estUneFeuille()) {
            if(this.prixListeObj(tabValeurs)>this.prixListeObj(tabMeillValeurs))
                tabMeillValeurs=new Objet[tabValeurs.length];
                tabMeillValeurs=this.tabValeurs;
        }
        else{
            if(leftTree!=null)
                leftTree.rechercheMeillValeur(taille);
            if(rightTree!=null)
                rightTree.rechercheMeillValeur(taille);
        }
    }

    private boolean estUneFeuille() {
        if(leftTree==null && rightTree==null)
            return true;
        else return false;
    }

    public float poidsListeObj(Objet[] tab){
        float poids = 0;
        for (int p = 0; p<tab.length; ++p){
            if (tab[p] != null)
                poids += tab[p].getPoids();
        }
        return poids;
    }
    public float prixListeObj (Objet[] tab){
        float prix = 0;
        if(tab!=null) {
            for (int p = 0; p < tab.length; ++p) {
                if (tab[p] != null)
                    prix += tab[p].getPrix();
            }
        }
        return prix;
    }

    public String toString() {
        //ordre infixe
        String s="";
        if(leftTree!=null)
            s+=leftTree.toString();
        s+="{";
        for(int i = 0; i < this.tabValeurs.length; i++) {
            if(this.tabValeurs[i]!=null)
                s += this.tabValeurs[i].toString() + ";";
        }
        s+="}";
        if(rightTree!=null)
            s+=rightTree.toString();
        return s;
    }

    public void calculBorneInf(){
        if(this.prixListeObj(tabValeurs)>borneInf)
            borneInf=this.prixListeObj(tabValeurs);
    }

    public void calculBorneSup(Objet[] listeObjSac, int profondeur){
        float resultat = 0;
        resultat+=this.prixListeObj(tabValeurs);
        for(int i=profondeur;i< listeObjSac.length;i++)
            resultat+=listeObjSac[i].getPrix();
        this.borneSup=resultat;
    }

    public static Objet[] getTabMeillValeurs() {
        return tabMeillValeurs;
    }
