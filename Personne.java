public abstract class Personne {
    protected String nomComplet;
    protected String id;

    public Personne(String nomComplet, String id) {
        this.nomComplet = nomComplet;
        this.id = id;

    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getId() {
        return id;
    }

}
