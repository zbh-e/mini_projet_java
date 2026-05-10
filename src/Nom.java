import java.util.List;
import java.util.Objects;

/**
 * Représente un nom dans le pipeline de traitement.
 *
 * Cycle de vie :
 * 1. nomOriginal — valeur brute saisie / chargée
 * 2. nomNormalise — après les étapes de normalisation (casse, accents, espaces,
 * etc.)
 * 3. nomTraite — tokens finaux après tokenisation / filtrage
 */
public class Nom {

    private final String id;
    private final String nomOriginal;

    /** Valeur intermédiaire produite par les préprocesseurs de normalisation. */
    private String nomNormalise;

    /** Tokens finaux produits par le préprocesseur de tokenisation. */
    private List<String> nomTraite;

    // ------------------------------------------------------------------ //
    // Constructeurs
    // ------------------------------------------------------------------ //

    /**
     * Constructeur principal : crée un Nom à partir de son identifiant et
     * de sa valeur brute. {@code nomNormalise} est initialisé à
     * {@code nomOriginal} pour que les préprocesseurs puissent chaîner.
     */
    public Nom(String id, String nomOriginal) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nomOriginal = Objects.requireNonNull(nomOriginal, "nomOriginal must not be null");
        this.nomNormalise = nomOriginal; // point de départ de la normalisation
    }

    /**
     * Constructeur de commodité sans identifiant (id = "").
     */
    public Nom(String nomOriginal) {
        this("", nomOriginal);
    }

    // ------------------------------------------------------------------ //
    // Getters / Setters
    // ------------------------------------------------------------------ //

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
    // Utilitaires
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return "Nom{id='" + id + "', original='" + nomOriginal
                + "', normalise='" + nomNormalise
                + "', traite=" + nomTraite + "}";
    }
}