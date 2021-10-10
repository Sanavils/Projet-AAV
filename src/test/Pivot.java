package test;

public class Pivot {

    private Objet[] tableau;
    private int pivot;

    public Objet[] getTab(){
        return this.tableau;
    }

    public int setPivot(int P){
       return this.pivot=P;
    }

    public void setTableau(Objet[] tableau) {
        this.tableau = tableau;
    }

    public int getPivot(){
        return this.pivot;
    }



}
