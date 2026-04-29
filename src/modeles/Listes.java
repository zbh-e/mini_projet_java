package modeles;

public class Listes {
    private Nom[] listeDeClients;
    private int nbrCLients;
    private Nom[] listeDeControle;
    private int listCtrl;

    public Listes(Nom[] l1, int n1, Nom[] l2, int n2) {
        this.listeDeClients = l1;
        this.nbrCLients = n1;
        this.listCtrl = n2;

        this.listeDeControle = l2;

    }

    public void ajoutCtrl() {
    }

    public void deleteCtrl() {
    }

    public void modifCtrl() {
    }

}
// plus de modification....
// cette class peut etre supprimée ou fusionnée avec une autre class