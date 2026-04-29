public class Generateur {
    private Nom nom ;
    private Nom[] nomsGenere ;
    public Nom[] genererLesNoms () {
        //algorithme de generation
        Nom[] resultat=nomsGenere;
        return resultat;
    }
    public Generateur(Nom nom){
        this.nom=nom;
        this.nomsGenere=this.genererLesNoms();
    }
    public Generateur(Nom nom,Nom[] nomsGenere){
        this.nom=nom;
        this.nomsGenere=nomsGenere;
    }
    

    
}
//Le code sera mieux developpé aux futur