import java.util.List;

public class Nom {
    private String nomOriginal;
    private List<String> nomTraite;
    private String id;

    public Nom(String nomOriginal, String id) {
        this.nomOriginal = nomOriginal;
        this.id = id;
    }

    public Nom(List<String> nomTraite) {
        super();
        this.nomTraite = nomTraite;
    }

    public String getNomOriginal() {
        return nomOriginal;
    }

    public String getId() {
        return id;
    }

    public List<String> getNomTraite() {
        return nomTraite;
    }

    public void setNomTraite(List<String> nomTraite) {
        this.nomTraite = nomTraite;
    }

}