package test;

public class Objet {
    private String nom;
    private float poids;
    private float prix;
    private float rapport;
    private boolean stockage;


    public Objet(String Nom, float Poids, float Prix){
        nom = Nom;
        poids = Poids;
        prix= Prix;
        rapport = Poids/Prix;
        stockage = true;
    }


    public String toString(){
        return String.format("Objet : %s, Poids : %f, Prix : %f", nom, poids, prix);
    }

    public int compare(Objet objet) {
        if (rapport>((Objet)objet).getRapport()){
            return 1;

        }else
            if (rapport == ((Objet)objet).getRapport()){
                return 0;
            }else {
                return -1;
            }
    }

    private float getRapport() {
        return this.rapport;

    }

    public void estStocké(boolean b) {
        this.stockage = b;
    }

    public boolean getStocakge() {
        return this.stockage;
    }

    public float getPoids() {
        return this.poids;
    }
}
