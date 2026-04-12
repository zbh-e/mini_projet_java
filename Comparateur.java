public class Comparateur {
    private Nom nom1 ;
    private Nom nom2 ;
    private int score ;

    public int comparer(Nom nom1, Nom nom2){
        //algorithme de comparaison ....
        int s =0;
        
        return s ;



    }
    public Comparateur(Nom nom1,Nom nom2){
        this.nom1=nom1;
        this.nom2=nom2;
        this.score=comparer(nom1,nom2);

    } 
    public Comparateur(Nom nom1,Nom nom2, int s){
        this.nom1=nom1;
        this.nom2=nom2;
        this.score=s;
    }

    
    public int getScore (Nom nom1,Nom nom2){
        
        return this.score ;
    }

}
