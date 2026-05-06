public class Triplet {
    private double score;
    private Nom nom1;
    private Nom nom2;
    public Triplet(Nom nom1,Nom nom2,int s){
        this.nom1=nom1;
        this.nom2=nom2;
        this.score=s;
    }
    public Nom getNom1(){
        return nom1;
    }
    public Nom getNom2(){
        return nom2;
    }
    public double getScore(){
        return score;
    }
    
}
