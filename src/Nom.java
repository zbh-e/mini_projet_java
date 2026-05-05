public class Nom {
    private String nomOriginal;
    private String nomTraite;
    private String id;

    public Nom(String nomOriginal, String nomTraite, String id) {
        this.nomOriginal = nomOriginal;
        this.nomTraite = nomTraite;
        this.id = id;
    }

    public String getNomOriginal() {
        return nomOriginal;
    }

    public String getNomTraite() {
        return nomTraite;
    }

    public String getId() {
        return id;
    }

}
