import java.util.List;
import java.util.Objects;


public class Nom {

    private final String id;
    private final String nomOriginal;

    
    private String nomNormalise;

    
    private List<String> nomTraite;

    
    public Nom(String id, String nomOriginal) {
        this.id           = Objects.requireNonNull(id,          "id must not be null");
        this.nomOriginal  = Objects.requireNonNull(nomOriginal, "nomOriginal must not be null");
        this.nomNormalise = nomOriginal; // point de départ de la normalisation
    }

    
    public Nom(String nomOriginal) {
        this("", nomOriginal);
    }

    
    public String getId() {
        return id;
    }

    public String getNomOriginal() {
        return nomOriginal;
    }

    public String getNomNormaliser() {
        return nomNormalise;
    }

    public void setNomNormaliser(String nomNormalise) {
        this.nomNormalise = Objects.requireNonNull(nomNormalise, "nomNormaliser must not be null");
    }

    public List<String> getNomTraite() {
        return nomTraite;
    }

    public void setNomTraiter(List<String> nomTraite) {
        this.nomTraite = Objects.requireNonNull(nomTraite, "nomTraite must not be null");
    }

    // ------------------------------------------------------------------ //
    //  Utilitaires
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return "Nom{id='" + id + "', original='" + nomOriginal
             + "', normalise='" + nomNormalise
             + "', traite=" + nomTraite + "}";
    }
}